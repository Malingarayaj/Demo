package com.xworkz.securepassword.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.securepassword.constants.ApplicationConstants;
import com.xworkz.securepassword.constants.StringConstants;
import com.xworkz.securepassword.dto.ChangePasswordDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.ChangePasswordException;
import com.xworkz.securepassword.exception.ControllerException;
import com.xworkz.securepassword.service.ChangePasswordService;

@Controller
@RequestMapping("/")
public class ChangePasswordController {
	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);

	public ChangePasswordController() {
		logger.info("object is created:" + this.getClass().getSimpleName());
	}

	@Autowired
	private ChangePasswordService passwordService;

	@RequestMapping(value = "changepassword.do", method = RequestMethod.GET)
	public ModelAndView changepassword(@RequestParam(value = "userName") String userName) throws ControllerException {
		logger.debug("invoked changePassword method with args\t" + userName);
		try {
			UserEntity userFromDb = passwordService.checkUserByUserName(userName);
			if (userFromDb != null) {
				/*
				 * HttpSession session = request.getSession();
				 * session.setAttribute("userFromEmail", userName);
				 * 
				 */
				if (userFromDb.isFirstLog() == true) {
					logger.debug("userpresent .....Already changed password with username\t" + userName);
					return new ModelAndView(ApplicationConstants.SOLUTION_FOR_EXCEPTION, StringConstants.USER_NAME,
							"sorry....! Already change your password pls login again....!");
				} else {
					logger.debug("userpresent .....will update your password with username\t" + userName);
					return new ModelAndView(ApplicationConstants.CONFIRM_PASSWORD, StringConstants.USER_NAME, userName);

				}
			} else {
				logger.debug("user not present ..... with username\t" + userName);
				return new ModelAndView(ApplicationConstants.SOLUTION_FOR_EXCEPTION, StringConstants.USER_NOT_FOUND,
						"User not found dont change your name in link...");
			}

		} catch (Exception e) {
			logger.debug("Exception in SignUpController\t" + e.getMessage());
			throw new ControllerException("Exception thrown in SignUpController\t" + e.getMessage());

		}

	}

	@RequestMapping(value = "changedPassword", method = RequestMethod.POST)
	public ModelAndView changePassword(ChangePasswordDTO dto) throws ChangePasswordException {
		logger.debug("invoked changePassword in changepassword controller\t" + dto);
		HashMap<String, String> map1 = new HashMap<String, String>();

		try {

			map1 = passwordService.changePassword(dto);
			if (map1.containsKey("updateSuccess")) {
				return new ModelAndView(ApplicationConstants.SOLUTION_FOR_EXCEPTION, StringConstants.UPDATE_SUCCESS,
						map1.get("updateSuccess"));
			} else if (map1.containsKey("AlredyUpdated")) {
				return new ModelAndView(ApplicationConstants.CHANGE_PWD_PAGE, StringConstants.ALREADY_UPDATED,
						map1.get("AlredyUpdated"));
			} else if (map1.containsKey("wrongPwd")) {
				return new ModelAndView(ApplicationConstants.CHANGE_PWD_PAGE, StringConstants.WRONG_PASSWORD,
						map1.get("wrongPwd"));
			} else if (map1.containsKey("pwdLenth")) {
				return new ModelAndView(ApplicationConstants.CHANGE_PWD_PAGE, StringConstants.PASSWORD_LENGTH,
						map1.get("pwdLenth"));
			} else if (map1.containsKey("pwdNotSame")) {
				return new ModelAndView(ApplicationConstants.CHANGE_PWD_PAGE, StringConstants.PASSWORD_NOT_SAME,
						map1.get("pwdNotSame"));
			} else if (map1.containsKey("emptyFields")) {
				return new ModelAndView(ApplicationConstants.CHANGE_PWD_PAGE, StringConstants.EMPTY_FIELDS,
						map1.get("emptyFields"));

			} else if (map1.containsKey("userNull")) {
				return new ModelAndView(ApplicationConstants.CHANGE_PWD_PAGE, StringConstants.USER_NULL,
						map1.get("userNull"));
			}

		} catch (Exception e) {
			logger.debug("Exception occured in ChangePassword Controller\t" + e.getMessage());
			throw new ChangePasswordException("Exception thrown in ChangePasswordController");
		}
		return null;

	}
}
