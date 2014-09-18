package cn.vobile.opm.web.shell.action.userpage;

import cn.vobile.opm.web.shell.bean.Opmuser;
import cn.vobile.opm.web.shell.dao.DaoCreator;
import cn.vobile.opm.web.shell.dao.OpmuserDAO;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteUserAction extends ActionSupport {

	private String checkedId;
	private static OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");

	@Override
	public String execute() throws Exception {
		String[] idStrings = checkedId.split(",");
		for (String id : idStrings) {
			System.out.println(id);
			Opmuser user = opmuserDAO.findById(Integer.parseInt(id));
			user.setIsEnabled(0);
			opmuserDAO.update(user);
		}
		return super.execute();
	}

	public String getCheckedId() {
		return checkedId;
	}

	public void setCheckedId(String checkedId) {
		this.checkedId = checkedId;
	}

}
