/*
 * @(#)IsAccpted.java    1.0 2012-8-16
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.enumdata;

/**
 * IsAccpted.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-16
 * @since 1.0
 */
public enum IsResponsed {
	FALSE(" href=\"javascript:visNew('submitResponse')\">未响应</a>"), TRUE(
			"></a>已响应");
	private String result;

	/**
	 *
	 */
	private IsResponsed(String result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

}
