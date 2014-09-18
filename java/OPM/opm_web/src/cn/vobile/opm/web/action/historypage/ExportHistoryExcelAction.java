/*
 * @(#)ExportHistoryExcelAction.java    1.0 2012-8-10
 *
 * Copyright © 2012 Vobile, Inc. All rights reserved.
 */
package cn.vobile.opm.web.action.historypage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.vobile.opm.web.bean.AlarmPointStatusChangingHistory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ExportHistoryExcelAction.java
 * 
 * @author <A HREF="mailto:huang_panpan@vobile.cn">Panpan</A>
 * @version 1.0 2012-8-10
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ExportHistoryExcelAction extends ActionSupport {
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
		hssfrow.createCell(0, 0).setCellValue("系统");
		hssfrow.createCell(1, 0).setCellValue("报警点");
		hssfrow.createCell(2, 0).setCellValue("种类");
		hssfrow.createCell(3, 0).setCellValue("描述");
		hssfrow.createCell(4, 0).setCellValue("报警级别");
		hssfrow.createCell(5, 0).setCellValue("Server / IP");
		hssfrow.createCell(6, 0).setCellValue("关键程度");
		hssfrow.createCell(7, 0).setCellValue("记录时间");
		hssfrow.createCell(8, 0).setCellValue("结束时间");
		hssfrow.createCell(9, 0).setCellValue("持续时间（分）");
		hssfrow.createCell(10, 0).setCellValue("受理人");
		hssfrow.createCell(11, 0).setCellValue("受理时间");
		hssfrow.createCell(12, 0).setCellValue("报警人");
		hssfrow.createCell(13, 0).setCellValue("报警时间");
		hssfrow.createCell(14, 0).setCellValue("响应");
		hssfrow.createCell(15, 0).setCellValue("响应人");
		hssfrow.createCell(16, 0).setCellValue("响应时间");
		hssfrow.createCell(17, 0).setCellValue("反馈");
		hssfrow.createCell(18, 0).setCellValue("备注");
		hssfrow.createCell(19, 0).setCellValue("状态改变");
		hssfrow.createCell(20, 0).setCellValue("GS响应时间（分）");
		int i = 1;

		try {
			List<AlarmPointStatusChangingHistory> alarmPointStatusChangingHistories = (List<AlarmPointStatusChangingHistory>) ServletActionContext
					.getRequest().getSession()
					.getAttribute("alarmPointStatusChangingHistories");

			for (AlarmPointStatusChangingHistory alarmPointStatusChangingHistory : alarmPointStatusChangingHistories) {
				hssfrow = hssfsheet.createRow(i);
				i++;
				try {
					hssfrow.createCell(0, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getServiceName());
					hssfrow.createCell(1, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getComponentName());
					hssfrow.createCell(2, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getConvertedType());
					hssfrow.createCell(3, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getDescription());
					hssfrow.createCell(4, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getAlarmLevel()
									+ " / "
									+ alarmPointStatusChangingHistory
											.getOriginalAlarmHistory()
											.getLivemonitor()
											.getAlarmTimeRegion());
					hssfrow.createCell(5, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getServerIpaddress());
					hssfrow.createCell(6, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory().getLivemonitor()
									.getCriticality());
					hssfrow.createCell(7, 0).setCellValue(
							alarmPointStatusChangingHistory.getStartTime());
					hssfrow.createCell(8, 0).setCellValue(
							alarmPointStatusChangingHistory.getStopTime());
					hssfrow.createCell(9, 0).setCellValue(
							alarmPointStatusChangingHistory.getDuration());
				} catch (Exception e) {
				}

				try {
					hssfrow.createCell(10, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getAcceptedPerson());
					hssfrow.createCell(11, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getConvertedAcceptedTime());

				} catch (Exception e) {
				}
				try {
					hssfrow.createCell(12, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getAlarmedPerson());
					hssfrow.createCell(13, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getConvertedAlarmedTime());
				} catch (Exception e) {
				}
				try {
					hssfrow.createCell(14, 0)
							.setCellValue(
									getChinessResponse(alarmPointStatusChangingHistory
											.getOriginalAlarmHistory()
											.getIsResponsed()));
					hssfrow.createCell(15, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getResponsedPerson());
					hssfrow.createCell(16, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getConvertedResponsedTime());
					hssfrow.createCell(17, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getResponsedHandling());
					hssfrow.createCell(18, 0).setCellValue(
							alarmPointStatusChangingHistory
									.getOriginalAlarmHistory()
									.getResponsedNote());
				} catch (Exception e) {
				}

				hssfrow.createCell(19, 0).setCellValue(
						alarmPointStatusChangingHistory
								.getOriginalAlarmHistory().getStatus());
				hssfrow.createCell(20, 0).setCellValue(
						alarmPointStatusChangingHistory.getResponseTime());
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
	 * @param isResponsed
	 * @return
	 */
	private String getChinessResponse(Boolean isResponsed) {
		if (isResponsed) {
			return "已受理";
		}
		return "未受理";
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
