package cn.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.test.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {

	// @Resource
	// private UserService userService;
	// @ModelAttribute("user")
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
}
