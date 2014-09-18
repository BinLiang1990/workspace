package cn.vobile.opm.web.action.monitorpage;

import java.util.List;

import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GetAllMonitorAction extends ActionSupport {

	private UserService userService = new UserServiceImpl();
	private List<Opmuser> opsOpmusersList = userService.getOpsOpmUsers();

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public List<Opmuser> getOpsOpmusersList() {
		return opsOpmusersList;
	}

	public void setOpsOpmusersList(List<Opmuser> opsOpmusersList) {
		this.opsOpmusersList = opsOpmusersList;
	}

}
