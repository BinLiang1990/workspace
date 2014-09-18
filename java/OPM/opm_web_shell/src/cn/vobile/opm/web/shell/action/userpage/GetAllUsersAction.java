package cn.vobile.opm.web.shell.action.userpage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.vobile.opm.web.shell.bean.Opmuser;
import cn.vobile.opm.web.shell.dao.DaoCreator;
import cn.vobile.opm.web.shell.dao.OpmuserDAO;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GetAllUsersAction extends ActionSupport {

	private static final OpmuserDAO opmuserDAO = (OpmuserDAO) DaoCreator
			.createDao("OpmuserDAO");
	private static final Logger log = LoggerFactory
			.getLogger(GetAllUsersAction.class);

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			InputStream inStream = request.getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(
					inStream);
			String str = (String) objectInputStream.readObject();
			if (!"sync".equals(str)) {
				return super.execute();
			}
			log.warn("Server receives syncing DB request");
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream outputStream = response.getOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					outputStream);
			@SuppressWarnings("unchecked")
			List<Opmuser> opmusers = opmuserDAO.findAll();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < opmusers.size(); ++i) {
				JSONObject jsonObject = new JSONObject();
				Opmuser opmuser = opmusers.get(i);
				jsonObject.put("id", opmuser.getId());
				jsonObject.put("userid", opmuser.getUserid());
				jsonObject.put("passwd", opmuser.getPasswd());
				jsonObject.put("is_enabled", opmuser.getIsEnabled());
				jsonObject.put("usergroup", opmuser.getUsergroup());
				jsonObject.put("username", opmuser.getUsername());
				jsonObject.put("phone", opmuser.getPhone());
				jsonObject.put("note", opmuser.getNote());
				jsonArray.put(jsonObject);
			}
			objectOutputStream.writeObject(jsonArray.toString());
			objectOutputStream.flush();
			objectOutputStream.close();
			objectInputStream.close();
		} catch (IOException e) {
			log.warn(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.warn(e.getMessage());
		}
		return super.execute();
	}

}
