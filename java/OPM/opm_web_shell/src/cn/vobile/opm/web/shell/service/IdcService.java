/*
 * @(#)IdcService.java    1.0 2012-8-13
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.service;

import java.text.ParseException;
import java.util.List;

import cn.vobile.opm.web.shell.bean.Opmidc;

/**
 * IdcService.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-13
 * @since 1.0
 */
public interface IdcService {
	public List<Opmidc> getAllOpmidcs();

	/**
	 * @param idc_name
	 * @param idc_local
	 * @param idc_description
	 * @return
	 * @throws ParseException
	 */
	public int addAnIdc(String idc_name, String idc_local)
			throws ParseException;

	/**
	 * @param idc_id
	 * @param image_name
	 * @param idc_description
	 */
	public void updateIdc(int idc_id, String image_name, String idc_description);
}
