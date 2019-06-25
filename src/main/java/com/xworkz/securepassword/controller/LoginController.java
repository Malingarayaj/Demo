package com.xworkz.securepassword.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.securepassword.constants.ApplicationConstants;
import com.xworkz.securepassword.constants.StringConstants;
import com.xworkz.securepassword.dto.LogInDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.LogInControllerException;
import com.xworkz.securepassword.service.LogInService;

@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LogInService logInService;
	
	public LoginController() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}
	@RequestMapping(value = "logIn.do", method = RequestMethod.POST)
	public ModelAndView logIn(LogInDTO dto, HttpServletRequest request) throws LogInControllerException {
		logger.debug("invoked login method in controller with args\t" + dto);
		try {
			UserEntity userFound = logInService.logIn(dto);
			if (userFound !=null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userEntity", userFound);
				session.setMaxInactiveInterval(60*5);
				logger.debug("in login username is \t" + dto.getUserName());
				return new ModelAndView(ApplicationConstants.SECURE_PHRASE_PAGE);
			} else {
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, StringConstants.USER_NOT_FOUND,
						"Please enter the correct Credentials");
			}

		} catch (Exception e) {
			logger.debug("Exception occured in logIn controller\t" + e.getMessage());
			throw new LogInControllerException("Exception thrown in logIn controller\t" + e.getMessage());
		}
	}

}