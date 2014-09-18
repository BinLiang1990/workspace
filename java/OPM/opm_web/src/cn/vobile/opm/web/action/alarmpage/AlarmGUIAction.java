package cn.vobile.opm.web.action.alarmpage;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.vobile.opm.web.dao.Livemonitor;
import cn.vobile.opm.web.service.AlarmPointService;
import cn.vobile.opm.web.service.impl.AlarmPointServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AlarmGUIAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		InputStream inStream = request.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
		Object object = objectInputStream.readObject();
		if (null != object) {
			boolean alarm = false;
			AlarmPointService alarmPointService = new AlarmPointServiceImpl();
			List<Livemonitor> active_upLivemonitors = alarmPointService
					.getActiveUpLivemonitors(alarmPointService
							.getUpLivemonitors());
			for (Livemonitor livemonitor : active_upLivemonitors) {
				// 性能优化 1对多
				if (livemonitor.getLatestHistory().getIsAccepted() == false) {
					alarm = true;
					break;
				}
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream outputStream = response.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					outputStream);
			objectOutputStream.writeObject(alarm);
			objectOutputStream.flush();
			objectOutputStream.close();
		}
		objectInputStream.close();
		return super.execute();
	}
}
