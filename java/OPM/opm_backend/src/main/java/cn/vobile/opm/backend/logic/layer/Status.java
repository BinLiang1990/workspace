/*
 * @(#)Status.java    1.0 2012-7-24
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.backend.logic.layer;

/**
 * Status.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-7-24
 * @since 1.0
 */
public enum Status {
	ACTIVE_UP("ACTIVE_UP"), ACTIVE_DOWN("ACTIVE_DOWN"), WARNING("WARNING"), WARNING_UP(
			"WARNING_UP"), WARNING_DOWN("WARNING_DOWN"), INACTIVE("INACTIVE"), OK(
			"OK"), DELETED("DELETED");

	/**
	 *
	 */
	private Status(String status) {
		this.setStatus(status);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
}
