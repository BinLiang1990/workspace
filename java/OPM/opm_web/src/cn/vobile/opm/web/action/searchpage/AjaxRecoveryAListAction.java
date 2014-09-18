package cn.vobile.opm.web.action.searchpage;

import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AjaxRecoveryAListAction extends ActionSupport {

	private String id;
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");

	@Override
	public String execute() throws Exception {
		String[] idStrings = id.split(",");
		for (String delid : idStrings) {
			Livemonitor livemonitor = livemonitorDAO.findById(Integer
					.parseInt(delid));
			livemonitor.setIsDeleted(false);
			livemonitorDAO.update(livemonitor);
		}
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
