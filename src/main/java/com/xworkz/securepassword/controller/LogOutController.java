package com.xworkz.securepassword.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.securepassword.constants.ApplicationConstants;

@Controller
@RequestMapping("/")
public class LogOutController {
	private static final Logger logger = LoggerFactory.getLogger(LogOutController.class);

	public LogOutController() {
		logger.info("created.....\t" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public ModelAndView logOutAccount(HttpServletRequest request) {
		logger.info("invoking a logOutAccount() in LogOutController");
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
				logger.info("session closed()");
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
						"your Session timeout.. please login again");
			} else {
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE);
			}
		} catch (Exception e) {
			logger.info("Exception occur during invoking logout() in LogOutController" + e.getMessage());
		}
		return null;

	}

}
