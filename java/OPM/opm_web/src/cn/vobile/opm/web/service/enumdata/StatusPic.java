/*
 * @(#)StatusPic.java    1.0 2012-8-11
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.enumdata;

/**
 * StatusPic.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-11
 * @since 1.0
 */
public enum StatusPic {
	OK_PIC("<img src=\"images/ok.png\" title=\"ok\" value=\"ok\" />"), ACTIVE_UP_PIC(
			"<img src=\"images/active_up.png\" title=\"active up\" value=\"active up\" />"), ACTIVE_DOWN_PIC(
			"<img src=\"images/active_down.png\" title=\"active down\"  value=\"active down\" />"), WARNING_PIC(
			"<img src=\"images/warning.png\" title=\"warning\" value=\"warning\" />"), INACTIVE_PIC(
			"<img src=\"images/inactive.png\" title=\"inactive\" value=\"inactive\" />");
	;

	/**
	 *
	 */
	private StatusPic(String statusPic) {
		setStatusPic(statusPic);
	}

	/**
	 * @return the statusPic
	 */
	public String getStatusPic() {
		return statusPic;
	}

	/**
	 * @param statusPic
	 *            the statusPic to set
	 */
	public void setStatusPic(String statusPic) {
		this.statusPic = statusPic;
	}

	private String statusPic;
}
