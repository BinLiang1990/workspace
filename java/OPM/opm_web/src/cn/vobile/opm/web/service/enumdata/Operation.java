/*
 * @(#)Operation.java    1.0 2012-8-7
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.service.enumdata;

/**
 * Operation.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-7
 * @since 1.0
 */
public enum Operation {

	DELETE("delete node"), DELAY_CG("change delay time"), ALARM_CG(
			"change alarm level"), NO_ALRM_ST("set no alarm time"), TYPE_CG(
			"change type"), CRITI_CG("change criticality"), OTHER("other"), DELETE_CH(
			"删除报警点"), DELAY_CG_CH("调整响应时间"), ALARM_CG_CH("更改报警级别"), NO_ALRM_ST_CH(
			"设置报警忽略"), TYPE_CG_CH("修改报警类型"), CRITI_CG_CH("修改报警程度"), OTHER_CH(
			"其他");

	/**
	 *
	 */
	private Operation(String operation) {
		setOperation(operation);
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	private String operation;
}
