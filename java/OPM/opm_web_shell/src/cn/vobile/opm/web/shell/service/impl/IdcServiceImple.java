/*
 * @(#)IdcServiceImple.java    1.0 2012-8-13
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import cn.vobile.opm.web.shell.bean.Opmidc;
import cn.vobile.opm.web.shell.common.OpmTimer;
import cn.vobile.opm.web.shell.dao.DaoCreator;
import cn.vobile.opm.web.shell.dao.OpmidcDAO;
import cn.vobile.opm.web.shell.service.IdcService;

/**
 * IdcServiceImple.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-13
 * @since 1.0
 */
public class IdcServiceImple implements IdcService {
	private OpmidcDAO opmidcDAO;

	/**
	 *
	 */
	public IdcServiceImple() {
		this.opmidcDAO = (OpmidcDAO) DaoCreator.createDao("OpmidcDAO");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.shell.service.IdcService#getAllOpmidcs()
	 */
	@SuppressWarnings("unchecked")
	public List<Opmidc> getAllOpmidcs() {
		return opmidcDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vobile.opm.web.shell.service.IdcService#addAnIdc(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public int addAnIdc(String idc_name, String idc_local)
			throws ParseException {
		OpmTimer opmTimer = new OpmTimer();
		Opmidc opmidc = new Opmidc(idc_name, idc_local,
				Timestamp.valueOf(opmTimer.getNowTimeString()));
		opmidcDAO.save(opmidc);

		return ((Opmidc) opmidcDAO.findByExample(opmidc).get(0)).getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.vobile.opm.web.shell.service.IdcService#updateIdc(int,
	 * java.lang.String, java.lang.String)
	 */
	public void updateIdc(int idc_id, String image_name, String idc_description) {
		Opmidc opmidc = opmidcDAO.findById(idc_id);
		opmidc.setImage(image_name);
		opmidc.setDescription(idc_description);

		opmidcDAO.update(opmidc);
	}
}
