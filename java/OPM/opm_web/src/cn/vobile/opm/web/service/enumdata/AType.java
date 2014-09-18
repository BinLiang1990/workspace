/*
 * @(#)AType.java    1.0 2012-8-21
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.enumdata;

/**
 * AType.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-21
 * @since 1.0
 */
public enum AType {
	FUNCTION("功能"), MODULE("模块"), DATA("数据");
	/**
	 *
	 */
	private AType(String type) {
		this.type = type;
	}

	private String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
