package cn.vobile.opm.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.dao.OpmuserDAO;
import cn.vobile.opm.web.dao.SearchConditions;
import cn.vobile.opm.web.dao.SearchConditionsDAO;
import cn.vobile.opm.web.service.SearchService;

public class SearchServiceImpl implements SearchService {

	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");
	private SearchConditionsDAO searchConditionsDAO = (SearchConditionsDAO) DaoCreator
			.createDao("SearchConditionsDAO");

	@Override
	public List<String> getServiceNameList() {
		return livemonitorDAO.findGroupByServiceName();
	}

	@Override
	public void saveSearchConditions(SearchConditions searchConditions) {
		if (searchConditions.getTag().length() > 0) {
			searchConditionsDAO.save(searchConditions);
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getTagList(String opmUserId) {
		return searchConditionsDAO.getTagList(opmUserId);
	}

	@Override
	public void deleteTag(String tag) {
		searchConditionsDAO.delete((SearchConditions) searchConditionsDAO
				.findByTag(tag).get(0));
	}

	@Override
	public SearchConditions findTag(String tag) {
		return (SearchConditions) searchConditionsDAO.findByTag(tag).get(0);
	}

	@Override
	public List<Livemonitor> searchAlarmPoints(String serverName,
			String componentName, List<String> serviceName,
			List<String> alarmLevel, List<String> alarmTimeRegion,
			List<String> status, long delayTimeLow, long delayTimeHigh,
			String firstAlarmPerson, String secondAlarmPerson) {

		List<Livemonitor> livemonitors = livemonitorDAO.findAllInHistory();
		List<Livemonitor> matchList = new ArrayList<Livemonitor>();
		for (Livemonitor livemonitor : livemonitors) {
			Pattern serverNamePattern = Pattern.compile(serverName,
					Pattern.CASE_INSENSITIVE);
			Matcher serverNameMatcher = serverNamePattern.matcher("");
			Matcher ipMatcher = serverNamePattern.matcher(livemonitor
					.getServerIpaddress());
			Pattern componentNamePattern = Pattern.compile(componentName,
					Pattern.CASE_INSENSITIVE);
			Matcher componentNameMatcher = componentNamePattern
					.matcher(livemonitor.getComponentName());
			if (!(serverName.length() == 0 || ipMatcher.find() || serverNameMatcher
					.find())) {
				continue;
			}
			if (!(componentName.length() == 0 || componentNameMatcher.find())) {
				continue;
			}
			if (!(serviceName.contains(livemonitor.getServiceName()) || serviceName
					.size() == 0)) {
				continue;
			}
			if (!(alarmLevel.contains(livemonitor.getAlarmLevel()) || alarmLevel
					.size() == 0)) {
				continue;
			}
			if (!(alarmTimeRegion.contains(livemonitor.getAlarmTimeRegion()) || alarmTimeRegion
					.size() == 0)) {
				continue;
			}

			if (!(status.contains(livemonitor.getHistory().getStatus()) || status
					.size() == 0)) {
				continue;
			}
			if (!(delayTimeLow <= livemonitor.getAlarmSpot().getIntervalTime()
					&& delayTimeHigh >= livemonitor.getAlarmSpot()
							.getIntervalTime() || (delayTimeHigh == 0 && delayTimeLow == 0))) {
				continue;
			}
			if (firstAlarmPerson.length() > 0) {
				Opmuser firstOpmuser = (Opmuser) opmuserDAO.findByUsername(
						firstAlarmPerson.split(",")[0]).get(0);
				if (livemonitor.getAlarmSpot().getOwner().length() < 2
						|| !firstOpmuser
								.getId()
								.toString()
								.equals(livemonitor.getAlarmSpot().getOwner()
										.split(",")[0])) {
					continue;
				}
			}
			if (secondAlarmPerson.length() > 0) {
				Opmuser secondOpmuser = (Opmuser) opmuserDAO.findByUsername(
						secondAlarmPerson.split(",")[0]).get(0);
				if (livemonitor.getAlarmSpot().getOwner().length() < 2
						|| !secondOpmuser
								.getId()
								.toString()
								.equals(livemonitor.getAlarmSpot().getOwner()
										.split(",")[1])) {
					continue;
				}
			}
			matchList.add(livemonitor);
		}
		return matchList;
	}
}
