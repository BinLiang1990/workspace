/*
 * @(#)DeleteAListAction.java    1.0 2012-8-7
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.searchpage;

import org.apache.log4j.Logger;

import cn.vobile.opm.web.dao.DaoCreator;
import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.dao.LivemonitorDAO;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author wang_lin
 * 
 */
@SuppressWarnings("serial")
public class AjaxDeleteAListAction extends ActionSupport {

	private String id;
	private String opmuser_userid;
	private LivemonitorDAO livemonitorDAO = (LivemonitorDAO) DaoCreator
			.createDao("LivemonitorDAO");
	private static final Logger log = Logger
			.getLogger(AjaxDeleteAListAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

		String[] idStrings = id.split(",");
		for (String delid : idStrings) {
			Livemonitor livemonitor = livemonitorDAO.findById(Integer
					.parseInt(delid));
			livemonitor.setIsDeleted(true);
			livemonitorDAO.update(livemonitor);
		}
		StringBuilder record = new StringBuilder();
		record.append(opmuser_userid);
		record.append(" deleted alarm points: ");
		record.append(id);
		log.info(record);
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the opmuser_userid
	 */
	public String getOpmuser_userid() {
		return opmuser_userid;
	}

	/**
	 * @param opmuser_userid
	 *            the opmuser_userid to set
	 */
	public void setOpmuser_userid(String opmuser_userid) {
		this.opmuser_userid = opmuser_userid;
	}

}
