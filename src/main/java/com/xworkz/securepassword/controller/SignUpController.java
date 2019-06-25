package com.xworkz.securepassword.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.securepassword.constants.ApplicationConstants;
import com.xworkz.securepassword.constants.StringConstants;
import com.xworkz.securepassword.dto.SignUpDTO;
import com.xworkz.securepassword.exception.ControllerException;
import com.xworkz.securepassword.service.SignUpService;

@Controller
@RequestMapping("/")
public class SignUpController {
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private SignUpService signUpService;

	private HashMap<String, String> map = new HashMap<String, String>();

	public SignUpController() {
		System.out.println(" "+ "object created.....\t" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "signUp.do", method = RequestMethod.POST)
	public ModelAndView signUp(SignUpDTO signUpDTO) throws ControllerException {

		// logger.info("Entering the signUp() in controller\t" + signUpDTO);
		logger.debug("Entering the signUp() in controller\t" + signUpDTO);

		try {
			logger.debug("checking logger debug......in controller");
			HashMap<String, String> map = signUpService.saveSignUp(signUpDTO);

			if (map.containsKey("success")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.SUCCESS, map.get("success"));

			} else if (map.containsKey("exist")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.EXIST, map.get("exist"));

			} else if (map.containsKey("emptyField")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.EMPTY_FIELDS,
						map.get("emptyField"));

			} else if (map.containsKey("emptyName")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.EMPTY_NAME,
						map.get("emptyName"));

			} else if (map.containsKey("nameLength")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.NAME_LENGTH,
						map.get("nameLength"));

			} else if (map.containsKey("EmptyNo")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.EMPTY_NO,
						map.get("EmptyNo"));

			} else if (map.containsKey("invalidNo")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.INVALID_NO,
						map.get("invalidNo"));

			} else if (map.containsKey("emptyMail")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.EMPTY_MAIL,
						map.get("emptyMail"));

			} else if (map.containsKey("wrongEmail")) {

				return new ModelAndView(ApplicationConstants.SIGN_UP_PAGE, StringConstants.WRONG_MAIL,
						map.get("wrongEmail"));
			}

		} catch (Exception e) {

			logger.debug("Exception in SignUpController\t" + e.getMessage());

			// controllerAdvice for throws exception
//			throw new ControllerException("Exception thrown in SignUpController\t" + e.getMessage());
		}
		return null;
	}


}
