package cn.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.test.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {

	// 请求参数绑定到pojo
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createUser(User user) {
		// userService.createUser(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/registerSuccess");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	// 占位符,请求参数 http://127.0.0.1:8080/web/user/handle?wanglin
	@RequestMapping(value = "/{vari}", params = "wanglin")
	public String handle1(@PathVariable("vari") String username) {
		System.out.println(username);
		return "main/success";
	}

	// 请求参数绑定到方法入参
	// http://127.0.0.1:8080/web/user/handle2?username=wanglin&password=123
	@RequestMapping("/handle2")
	public String handle2(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println(username + password);
		return "main/success";
	}
}
