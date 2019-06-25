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
import com.xworkz.securepassword.dto.SignUpDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.repository.ForgotPasswordRepository;
import com.xworkz.securepassword.service.ForgotPasswordService;

@Controller
public class ForgotPasswordController {
	private static Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
	@Autowired
	private ForgotPasswordService forgotpasswordService;

	@Autowired
	private ForgotPasswordRepository forgotPasswordrepo;

	public ForgotPasswordController() {

		logger.info("object is create dfor:" + this.getClass().getSimpleName());

	}

	@RequestMapping(value = "forgotpassword", method = RequestMethod.POST)
	public ModelAndView forgotPasswordController(HttpServletRequest request) {
		logger.info("invoked forgotPasswordController");
		try {
			String username = request.getParameter("userName");
			String email = request.getParameter("email");
			long mobileNo = Long.parseLong(request.getParameter("mobileNo"));
			boolean entityExist = forgotpasswordService.forgotPasswordService(username, email, mobileNo);
			if (entityExist) {
				return new ModelAndView(ApplicationConstants.FORGOT, StringConstants.SUCCESS,
						StringConstants.FORGOT_SUCCESS);
			} else {
				return new ModelAndView(ApplicationConstants.FORGOT, StringConstants.USER_NOT_FOUND,
						StringConstants.USER_NOT_FOUND_MESSAGES);
			}

		} catch (Exception e) {
			logger.debug("Exception occur in forgotPasswordController\t" + e.getMessage());
		}
		return null;

	}

	@RequestMapping(value = "resetpassword", method = RequestMethod.GET)
	public ModelAndView resetpasswordcontoller(HttpServletRequest request) {
		logger.info("invoked resetpasswordcontoller");
		try {
			String username = request.getParameter("userName");
			SignUpDTO userExist = forgotpasswordService.findkUserByName(username);

			if (userExist != null) {
				if (userExist.isFirstLog()) {
					return new ModelAndView(ApplicationConstants.EXCEPTION_HANDLING, StringConstants.ALREADY_UPDATED,
							StringConstants.ALREADY_UPDATED_MESSAGE);
				}
				return new ModelAndView(ApplicationConstants.RESET, StringConstants.RESET,
						StringConstants.RESET_MESSAGE);
			} else {
				return new ModelAndView(ApplicationConstants.EXCEPTION_HANDLING, StringConstants.USER_NOT_FOUND_IN_LINK,
						StringConstants.USER_NOT_FOUND_IN_LINK_MESSAGE);
			}

		} catch (Exception e) {
			logger.debug("Exception occur in resetpasswordcontoller\t" + e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "setPasswordUpdate", method = RequestMethod.POST)
	public ModelAndView setPasswordUpdateController(HttpServletRequest request) {
		logger.info("invoked forgotPasswordController");
		try {
			String setPassword = request.getParameter("setPassword");
			String confirmsetPassword = request.getParameter("confirmsetPassword");
			String setPhrase = request.getParameter("setPhrase");
			if (setPassword.equals(confirmsetPassword)) {
				HttpSession session = request.getSession(false);
				UserEntity userEntity = (UserEntity) session.getAttribute("userEntity");
				userEntity.setPassword(setPassword);
				userEntity.setSecurePhrase(setPhrase);
				boolean resetSuccess = forgotpasswordService.resetPasswordUpdateService(userEntity);
				if (resetSuccess) {
					return new ModelAndView(ApplicationConstants.EXCEPTION_HANDLING, StringConstants.SUCCESS,
							StringConstants.RESET_SUCCESS_MESSAGE);
				} else {
					return new ModelAndView(ApplicationConstants.EXCEPTION_HANDLING, StringConstants.FAILED,
							StringConstants.RESET_PASSWORD_FAILED);
				}

			} else {
				return new ModelAndView(ApplicationConstants.RESET, StringConstants.PASSWORD_NOT_SAME,
						StringConstants.PASSWORD_NOT_SAME_MESSAGE);
			}

		} catch (Exception e) {
			logger.debug("Exception occur in forgotPasswordController\t" + e.getMessage());
		}
		return null;
	}

}
