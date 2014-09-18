/*
 * @(#)ExportHistoryExcelAction.java    1.0 2012-8-10
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.ophistorypage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.vobile.opm.web.bean.UserOpHistory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ExportHistoryExcelAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-10
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ExportOpHistoryExcelAction extends ActionSupport {
	private InputStream excelStream;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		HSSFSheet hssfsheet = hssfworkbook.createSheet();
		HSSFRow hssfrow = hssfsheet.createRow(0);

		// hssfrow.createCell((short) 0).setCellValue("姓名");
		hssfrow.createCell(0, 0).setCellValue("编号");
		hssfrow.createCell(1, 0).setCellValue("操作人员");
		hssfrow.createCell(2, 0).setCellValue("操作类型");
		hssfrow.createCell(3, 0).setCellValue("服务");
		hssfrow.createCell(4, 0).setCellValue("报警点");
		hssfrow.createCell(5, 0).setCellValue("报警点IP");
		hssfrow.createCell(6, 0).setCellValue("修改前值");
		hssfrow.createCell(7, 0).setCellValue("修改后值");
		hssfrow.createCell(8, 0).setCellValue("修改日期");

		int i = 1;

		try {
			List<UserOpHistory> userOpHistories = (List<UserOpHistory>) ServletActionContext
					.getRequest().getSession().getAttribute("userOpHistories");

			for (UserOpHistory userOpHistory : userOpHistories) {
				hssfrow = hssfsheet.createRow(i);
				i++;
				try {
					hssfrow.createCell(0, 0)
							.setCellValue(userOpHistory.getId());
					hssfrow.createCell(1, 0).setCellValue(
							userOpHistory.getOpmuser().getUsername());
					hssfrow.createCell(2, 0).setCellValue(
							userOpHistory.getConvertedOperation());
					hssfrow.createCell(3, 0).setCellValue(
							userOpHistory.getLivemonitor().getServiceName());
					hssfrow.createCell(4, 0).setCellValue(
							userOpHistory.getLivemonitor().getComponentName());
					hssfrow.createCell(5, 0)
							.setCellValue(
									userOpHistory.getLivemonitor()
											.getServerIpaddress());

					hssfrow.createCell(8, 0).setCellValue(
							userOpHistory.getConvertCreatedAt());
				} catch (Exception e) {
				}
				try {
					hssfrow.createCell(6, 0).setCellValue(
							userOpHistory.getOriginalValue());
				} catch (Exception e) {
				}
				try {
					hssfrow.createCell(7, 0).setCellValue(
							userOpHistory.getChangeValue());
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		hssfworkbook.write(baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		setExcelStream(new ByteArrayInputStream(bytes, 0, bytes.length));
		baos.close();

		return super.execute();
	}

	/**
	 * @return the excelStream
	 */
	public InputStream getExcelStream() {
		return excelStream;
	}

	/**
	 * @param excelStream
	 *            the excelStream to set
	 */
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

}
