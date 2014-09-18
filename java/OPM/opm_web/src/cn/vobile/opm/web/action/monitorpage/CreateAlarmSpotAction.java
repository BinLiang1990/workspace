package cn.vobile.opm.web.action.monitorpage;

import java.sql.Timestamp;
import java.util.List;

import cn.vobile.opm.web.dao.AlarmSpot;
import cn.vobile.opm.web.dao.Opmuser;
import cn.vobile.opm.web.service.UserService;
import cn.vobile.opm.web.service.impl.AlarmSpotServiceImpl;
import cn.vobile.opm.web.service.impl.UserServiceImpl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CreateAlarmSpotAction extends ActionSupport {

	private String name;
	private String firstAlarmPerson;
	private String secondAlarmPerson;
	private int intervalTime;
	private String alarmLevel;
	private String type;
	private String criticality;
	private String alarmTimeRegion;
	private String noAlarmStartTime;
	private String noAlarmEndTime;
	private String description;
	private UserService userService = new UserServiceImpl();
	private List<Opmuser> opsOpmusersList = userService.getOpsOpmUsers();
	private AlarmSpotServiceImpl alarmSpotServiceImpl = new AlarmSpotServiceImpl();

	@Override
	public String execute() throws Exception {
		if (!alarmSpotServiceImpl.isLegal(name)) {
			ActionContext ctx = ActionContext.getContext();
			ctx.put("req", "该报警点已被注册或者格式错误");
		} else {
			String[] names = name.split("/");
			for (String spot : names) {
				spot = spot.trim();
				AlarmSpot alarmSpot = new AlarmSpot();
				alarmSpot.setName(spot);
				alarmSpot.setAlarmTimeRegion(alarmTimeRegion);
				alarmSpot
						.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				alarmSpot.setCriticality(criticality);
				alarmSpot.setDescription(description);
				Timestamp hibernateStartTime = noAlarmStartTime.length() > 0 ? Timestamp
						.valueOf(noAlarmStartTime) : null;
				alarmSpot.setHibernateStartTime(hibernateStartTime);
				Timestamp hibernateStopTime = noAlarmEndTime.length() > 0 ? Timestamp
						.valueOf(noAlarmEndTime) : null;
				alarmSpot.setHibernateStopTime(hibernateStopTime);
				alarmSpot.setIntervalTime(intervalTime);
				alarmSpot.setLevel(alarmLevel);
				String firstAlarmPersonId = firstAlarmPerson.length() > 0 ? alarmSpotServiceImpl
						.getAlarmPersonId(firstAlarmPerson) : "";
				String secondAlarmPersonId = secondAlarmPerson.length() > 0 ? alarmSpotServiceImpl
						.getAlarmPersonId(secondAlarmPerson)
						: firstAlarmPersonId;
				alarmSpot.setOwner(firstAlarmPersonId + ","
						+ secondAlarmPersonId);
				alarmSpot.setType(type);
				alarmSpot.setStatus("ok");
				alarmSpot
						.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				alarmSpotServiceImpl.save(alarmSpot);
			}

		}
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstAlarmPerson() {
		return firstAlarmPerson;
	}

	public void setFirstAlarmPerson(String firstAlarmPerson) {
		this.firstAlarmPerson = firstAlarmPerson;
	}

	public String getSecondAlarmPerson() {
		return secondAlarmPerson;
	}

	public void setSecondAlarmPerson(String secondAlarmPerson) {
		this.secondAlarmPerson = secondAlarmPerson;
	}

	public int getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	public String getAlarmTimeRegion() {
		return alarmTimeRegion;
	}

	public void setAlarmTimeRegion(String alarmTimeRegion) {
		this.alarmTimeRegion = alarmTimeRegion;
	}

	public String getNoAlarmStartTime() {
		return noAlarmStartTime;
	}

	public void setNoAlarmStartTime(String noAlarmStartTime) {
		this.noAlarmStartTime = noAlarmStartTime;
	}

	public String getNoAlarmEndTime() {
		return noAlarmEndTime;
	}

	public void setNoAlarmEndTime(String noAlarmEndTime) {
		this.noAlarmEndTime = noAlarmEndTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Opmuser> getOpsOpmusersList() {
		return this.opsOpmusersList;
	}

	public void setOpsOpmusersList(List<Opmuser> opsOpmusersList) {
		this.opsOpmusersList = opsOpmusersList;
	}

}
