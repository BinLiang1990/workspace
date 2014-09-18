package cn.vobile.opm.gui.main;

import cn.vobile.opm.gui.controller.AlarmMessageController;

public class AlarmGUIStart {

	public static void main(String[] args) {
		Thread query = new Thread(new AlarmMessageController());
		query.start();
	}
}
