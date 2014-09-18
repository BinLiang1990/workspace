package cn.vobile.opm.web.service;

import java.util.List;

import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.SearchConditions;

/**
 * SearchService.java
 * 
 * @author <A HREF="mailto:wang_lin@vobile.cn">wanglin</A>
 * @version 3.1 2013-5-29
 * @since 3.1
 */
public interface SearchService {

	List<String> getServiceNameList();

	void saveSearchConditions(SearchConditions searchConditions);

	@SuppressWarnings("rawtypes")
	List getTagList(String opmUserId);

	void deleteTag(String tag);

	List<Livemonitor> searchAlarmPoints(String serverName,
			String componentName, List<String> serviceName,
			List<String> alarmLevel, List<String> alarmTimeRegion,
			List<String> status, long delayTimeLow, long delayTimeHigh,
			String firstAlarmPerson, String secondAlarmPerson);

	SearchConditions findTag(String tag);
}
