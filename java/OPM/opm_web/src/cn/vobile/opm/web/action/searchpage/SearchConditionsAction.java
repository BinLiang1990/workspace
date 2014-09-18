package cn.vobile.opm.web.action.searchpage;

import java.util.ArrayList;
import java.util.List;

import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.SearchService;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.SearchServiceImpl;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * provide support for search page form
 * 
 * @author wang_lin
 * @version 3.1
 */
@SuppressWarnings("serial")
public class SearchConditionsAction extends ActionSupport {

	private String serverName, componentName, tag, opmUserId, firstAlarmPerson,
			secondAlarmPerson;
	private List<String> serviceName, alarmLevel, alarmTimeRegion, status,
			tagForSearch, tagForSearchList, serviceNameList, alarmPersonList;
	private long delayTimeLow, delayTimeHigh;
	private SearchService searchService = new SearchServiceImpl();
	private UserService userService = new UserServiceImpl();
	private List<Livemonitor> searchResult;
	private List<Opmuser> opsOpmusersList;

	@Override
	public String execute() throws Exception {
		updateList();
		searchResult = searchService.searchAlarmPoints(serverName.trim(),
				componentName.trim(), serviceName, alarmLevel, alarmTimeRegion,
				status, delayTimeLow, delayTimeHigh, firstAlarmPerson,
				secondAlarmPerson);
		return super.execute();
	}

	public void updateList() {
		serviceNameList = searchService.getServiceNameList();
		opsOpmusersList = userService.getOpsOpmUsers();
	}

	public String listToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));
		sb.append(";");
		for (int i = 1; i < list.size(); ++i) {
			sb.append(list.get(i));
			sb.append(";");
		}
		return sb.toString();
	}

	public List<String> stringToList(String str) {
		List<String> list = new ArrayList<String>();
		String[] strings = str.split(";");
		for (String string : strings) {
			list.add(string);
		}
		return list;
	}

	public List<String> getTagForSearchList() {
		return tagForSearchList;
	}

	public List<String> getServiceNameList() {
		return serviceNameList;
	}

	public List<Livemonitor> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Livemonitor> searchResult) {
		this.searchResult = searchResult;
	}

	public List<String> getTagForSearch() {
		return tagForSearch;
	}

	public void setTagForSearch(List<String> tagForSearch) {
		this.tagForSearch = tagForSearch;
	}

	public String getOpmUserId() {
		return opmUserId;
	}

	public void setOpmUserId(String opmUserId) {
		this.opmUserId = opmUserId;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public List<String> getServiceName() {
		return serviceName;
	}

	public void setServiceName(List<String> serviceName) {
		this.serviceName = serviceName;
	}

	public List<String> getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(List<String> alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public List<String> getAlarmTimeRegion() {
		return alarmTimeRegion;
	}

	public void setAlarmTimeRegion(List<String> alarmTimeRegion) {
		this.alarmTimeRegion = alarmTimeRegion;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public long getDelayTimeLow() {
		return delayTimeLow;
	}

	public void setDelayTimeLow(long delayTimeLow) {
		this.delayTimeLow = delayTimeLow;
	}

	public long getDelayTimeHigh() {
		return delayTimeHigh;
	}

	public void setDelayTimeHigh(long delayTimeHigh) {
		this.delayTimeHigh = delayTimeHigh;
	}

	public List<String> getAlarmPersonList() {
		return alarmPersonList;
	}

	public void setAlarmPersonList(List<String> alarmPersonList) {
		this.alarmPersonList = alarmPersonList;
	}

	public List<Opmuser> getOpsOpmusersList() {
		return opsOpmusersList;
	}

	public void setOpsOpmusersList(List<Opmuser> opsOpmusersList) {
		this.opsOpmusersList = opsOpmusersList;
	}

	public String getFirstAlarmPerson() {
		return firstAlarmPerson;
	}

	public void setFirstAlarmPerson(String firstAlarmPerson) {
		this.firstAlarmPerson = firstAlarmPerson;
	}

	public String getSecondAlarmPerson() {
		return secondAlarmPerson;
	}

	public void setSecondAlarmPerson(String secondAlarmPerson) {
		this.secondAlarmPerson = secondAlarmPerson;
	}

}
