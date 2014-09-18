//package cn.vobile.opm.web.bean;
//
//import java.text.ParseException;
//
//import cn.vobile.opm.web.common.OpmTimer;
//import cn.vobile.opm.web.dao.AlarmHistory;
//import cn.vobile.opm.web.service.enumdata.AType;
//import cn.vobile.opm.web.service.enumdata.IsAccpted;
//import cn.vobile.opm.web.service.enumdata.IsAlarmed;
//import cn.vobile.opm.web.service.enumdata.IsResponsed;
//import cn.vobile.opm.web.service.enumdata.Status;
//import cn.vobile.opm.web.service.enumdata.StatusPic;
//
///**
// * Livemonitor entity. @author MyEclipse Persistence Tools
// */
//
//@SuppressWarnings("serial")
//public class Livemonitor extends cn.vobile.opm.web.dao.Livemonitor {
//	// /
//	private long duration;
//
//	private boolean accepted;
//	private boolean alarmed;
//	private boolean responsed;
//
//	private String status;
//
//	private AlarmHistory history;
//
//	private String statusPic;
//	//
//	private String convertedCSTTime;
//	private String convertedNoAlarmStartTime;
//	private String convertedNoAlarmEndTime;
//	private String convertedIsAccpted;
//	private String convertedIsAlarmed;
//	private String convertedIsResponsed;
//	private String convertedType;
//
//	/**
//	 * @return the duration
//	 */
//	@Override
//	public long getDuration() {
//		return duration;
//	}
//
//	/**
//	 * @param duration
//	 *            the duration to set
//	 */
//	@Override
//	public void setDuration(long duration) {
//		this.duration = duration;
//	}
//
//	/**
//	 * @return the accepted
//	 */
//	public boolean isAccepted() {
//		return accepted;
//	}
//
//	/**
//	 * @param accepted
//	 *            the accepted to set
//	 */
//	public void setAccepted(boolean accepted) {
//		this.accepted = accepted;
//	}
//
//	/**
//	 * @return the alarmed
//	 */
//	public boolean isAlarmed() {
//		return alarmed;
//	}
//
//	/**
//	 * @param alarmed
//	 *            the alarmed to set
//	 */
//	public void setAlarmed(boolean alarmed) {
//		this.alarmed = alarmed;
//	}
//
//	/**
//	 * @return the responsed
//	 */
//	public boolean isResponsed() {
//		return responsed;
//	}
//
//	/**
//	 * @param responsed
//	 *            the responsed to set
//	 */
//	public void setResponsed(boolean responsed) {
//		this.responsed = responsed;
//	}
//
//	/**
//	 * @return the status
//	 */
//	public String getStatus() {
//		return status;
//	}
//
//	/**
//	 * @param status
//	 *            the status to set
//	 */
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	/**
//	 * @return the history
//	 */
//	@Override
//	public AlarmHistory getHistory() {
//		return history;
//	}
//
//	/**
//	 * @param history
//	 *            the history to set
//	 */
//	public void setHistory(AlarmHistory history) {
//
//		this.history = history;
//	}
//
//	/**
//	 * @return the convertedCSTTime
//	 */
//	@Override
//	public String getConvertedCSTTime() {
//		try {
//			OpmTimer opmTimer = new OpmTimer();
//			this.convertedCSTTime = opmTimer.convertToCSTString(lastUpdateTime)
//					.split("\\.")[0];
//		} catch (Exception e) {
//		}
//		return convertedCSTTime;
//	}
//
//	/**
//	 * @param convertedCSTTime
//	 *            the convertedCSTTime to set
//	 */
//	@Override
//	public void setConvertedCSTTime(String convertedCSTTime) {
//		this.convertedCSTTime = convertedCSTTime;
//	}
//
//	/**
//	 * @return the convertedNoAlarmStartTime
//	 */
//	public String getConvertedNoAlarmStartTime() {
//		try {
//			if (invalidEndTime()) {
//				return null;
//			}
//		} catch (ParseException e1) {
//			return null;
//		}
//
//		try {
//			OpmTimer opmTimer = new OpmTimer();
//			this.convertedNoAlarmStartTime = opmTimer.convertToCSTString(
//					noAlarmStartTime).split("\\.")[0];
//
//		} catch (Exception e) {
//		}
//		return convertedNoAlarmStartTime;
//	}
//
//	/**
//	 * @param convertedNoAlarmStartTime
//	 *            the convertedNoAlarmStartTime to set
//	 */
//	public void setConvertedNoAlarmStartTime(String convertedNoAlarmStartTime) {
//		this.convertedNoAlarmStartTime = convertedNoAlarmStartTime;
//	}
//
//	/**
//	 * @return the convertedNoAlarmEndTime
//	 */
//	public String getConvertedNoAlarmEndTime() {
//
//		try {
//			if (invalidEndTime()) {
//				return null;
//			}
//		} catch (ParseException e1) {
//			return null;
//		}
//		try {
//			OpmTimer opmTimer = new OpmTimer();
//			this.convertedNoAlarmEndTime = opmTimer.convertToCSTString(
//					noAlarmEndTime).split("\\.")[0];
//		} catch (Exception e) {
//		}
//		return convertedNoAlarmEndTime;
//	}
//
//	/**
//	 * @return
//	 * @throws ParseException
//	 */
//	private boolean invalidEndTime() throws ParseException {
//		if (noAlarmEndTime == null) {
//			return true;
//		}
//		OpmTimer opmTimer = new OpmTimer();
//		if (opmTimer.afterTheGiventime(noAlarmEndTime)) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * @param convertedNoAlarmEndTime
//	 *            the convertedNoAlarmEndTime to set
//	 */
//	public void setConvertedNoAlarmEndTime(String convertedNoAlarmEndTime) {
//		this.convertedNoAlarmEndTime = convertedNoAlarmEndTime;
//	}
//
//	/**
//	 * @return the statusPic
//	 */
//	public String getStatusPic() {
//		String status = this.getHistory().getStatus();
//
//		if (status.equals(Status.OK.getStatus())) {
//			this.setStatusPic(StatusPic.OK_PIC.getStatusPic());
//		} else if (status.equals(Status.ACTIVE_DOWN.getStatus())) {
//			this.setStatusPic(StatusPic.ACTIVE_DOWN_PIC.getStatusPic());
//		} else if (status.equals(Status.ACTIVE_UP.getStatus())) {
//			this.setStatusPic(StatusPic.ACTIVE_UP_PIC.getStatusPic());
//		} else if (status.equals(Status.INACTIVE.getStatus())) {
//			this.setStatusPic(StatusPic.INACTIVE_PIC.getStatusPic());
//		} else if (status.equals(Status.WARNING_UP.getStatus())) {
//			this.setStatusPic(StatusPic.WARNING_PIC.getStatusPic());
//		} else {
//		}
//
//		return statusPic;
//	}
//
//	/**
//	 * @param statusPic
//	 *            the statusPic to set
//	 */
//	public void setStatusPic(String statusPic) {
//		this.statusPic = statusPic;
//	}
//
//	/**
//	 * @return the convertedIsAccpted
//	 */
//	@Override
//	public String getConvertedIsAccpted() {
//		return convertedIsAccpted;
//	}
//
//	/**
//	 * @param convertedIsAccpted
//	 *            the convertedIsAccpted to set
//	 */
//	@Override
//	public void setConvertedIsAccpted(String convertedIsAccpted) {
//		this.convertedIsAccpted = convertedIsAccpted;
//	}
//
//	/**
//	 * @param isAccepted
//	 */
//	@Override
//	public void setConvertedIsAccpted(Boolean isAccepted) {
//		if (isAccepted) {
//			this.setConvertedIsAccpted(IsAccpted.TRUE.getResult());
//		} else {
//			this.setConvertedIsAccpted(IsAccpted.FALSE.getResult());
//		}
//	}
//
//	/**
//	 * @return the convertedIsAlarmed
//	 */
//	@Override
//	public String getConvertedIsAlarmed() {
//		return convertedIsAlarmed;
//	}
//
//	/**
//	 * @param convertedIsAlarmed
//	 *            the convertedIsAlarmed to set
//	 */
//	@Override
//	public void setConvertedIsAlarmed(String convertedIsAlarmed) {
//		this.convertedIsAlarmed = convertedIsAlarmed;
//	}
//
//	/**
//	 * @param isAlarmed
//	 */
//	@Override
//	public void setConvertedIsAlarmed(Boolean isAlarmed) {
//		if (isAlarmed) {
//			this.setConvertedIsAlarmed(IsAlarmed.TRUE.getResult());
//		} else {
//			this.setConvertedIsAlarmed(IsAlarmed.FALSE.getResult());
//		}
//	}
//
//	/**
//	 * @return the convertedIsResponsed
//	 */
//	@Override
//	public String getConvertedIsResponsed() {
//		return convertedIsResponsed;
//	}
//
//	/**
//	 * @param convertedIsResponsed
//	 *            the convertedIsResponsed to set
//	 */
//	@Override
//	public void setConvertedIsResponsed(String convertedIsResponsed) {
//		this.convertedIsResponsed = convertedIsResponsed;
//	}
//
//	/**
//	 * @param isResponsed
//	 */
//	@Override
//	public void setConvertedIsResponsed(Boolean isResponsed) {
//		if (isResponsed) {
//			this.setConvertedIsResponsed(IsResponsed.TRUE.getResult());
//		} else {
//			this.setConvertedIsResponsed(IsResponsed.FALSE.getResult());
//		}
//	}
//
//	/**
//	 * @return the convertedType
//	 */
//	@Override
//	public String getConvertedType() {
//		if (type.equals("module")) {
//			this.convertedType = AType.MODULE.getType();
//		} else if (type.equals("function")) {
//			this.convertedType = AType.FUNCTION.getType();
//		} else {
//			this.convertedType = AType.DATA.getType();
//		}
//		return convertedType;
//	}
//
//	/**
//	 * @param convertedType
//	 *            the convertedType to set
//	 */
//	@Override
//	public void setConvertedType(String convertedType) {
//		this.convertedType = convertedType;
//	}
// }
