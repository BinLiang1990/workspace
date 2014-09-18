package cn.vobile.opm.web.action.searchpage;

import java.util.List;

import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.SearchService;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.SearchServiceImpl;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * SearchService.java provide form elements for search page
 * 
 * @author <A HREF="mailto:wang_lin@vobile.cn">wanglin</A>
 * @version 3.1 2013-5-29
 * @since 3.1
 */
@SuppressWarnings("serial")
public class GetSearchAction extends ActionSupport {

	private List<String> serviceNameList;
	private List<Opmuser> opsOpmusersList;
	private SearchService searchService = new SearchServiceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	public String execute() throws Exception {
		serviceNameList = searchService.getServiceNameList();
		opsOpmusersList = userService.getOpsOpmUsers();
		return super.execute();
	}

	public List<String> getServiceNameList() {
		return serviceNameList;
	}

	public void setServiceNameList(List<String> serviceName) {
		this.serviceNameList = serviceName;
	}

	public List<Opmuser> getOpsOpmusersList() {
		return opsOpmusersList;
	}

	public void setOpsOpmusersList(List<Opmuser> opsOpmusersList) {
		this.opsOpmusersList = opsOpmusersList;
	}

}
