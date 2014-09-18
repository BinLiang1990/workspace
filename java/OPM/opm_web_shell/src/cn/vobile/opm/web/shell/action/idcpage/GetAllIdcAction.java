/*
 * @(#)GetAllIdcAction.java    1.0 2012-8-13
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.shell.action.idcpage;

import java.util.List;

import cn.vobile.opm.web.shell.bean.Opmidc;
import cn.vobile.opm.web.shell.service.IdcService;
import cn.vobile.opm.web.shell.service.impl.IdcServiceImple;

import com.opensymphony.xwork2.ActionSupport;

/**
 * GetAllIdcAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-13
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GetAllIdcAction extends ActionSupport {
	private List<Opmidc> opmidcs;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		IdcService idcService = new IdcServiceImple();
		this.opmidcs = idcService.getAllOpmidcs();

		return super.execute();
	}

	/**
	 * @return the opmidcs
	 */
	public List<Opmidc> getOpmidcs() {
		return opmidcs;
	}

	/**
	 * @param opmidcs
	 *            the opmidcs to set
	 */
	public void setOpmidcs(List<Opmidc> opmidcs) {
		this.opmidcs = opmidcs;
	}
}
