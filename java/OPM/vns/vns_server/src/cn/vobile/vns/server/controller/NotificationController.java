package cn.vobile.vns.server.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.vobile.vns.model.Notification;
import cn.vobile.vns.server.rabbitmq.NotificationProducer;

/**
 * A controller that receives json can send message or email.
 * 
 * @author wang_lin
 * 
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {

	@Resource
	private NotificationProducer notificationProducer;

	private static Logger logger = Logger
			.getLogger(NotificationController.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addNotification(@RequestBody Notification noti) {
		try {
			notificationProducer.sendMessage(noti);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
