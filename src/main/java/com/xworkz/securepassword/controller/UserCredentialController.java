package com.xworkz.securepassword.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.securepassword.constants.ApplicationConstants;
import com.xworkz.securepassword.constants.StringConstants;
import com.xworkz.securepassword.dto.UserCredentialDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.service.UserCredentialService;

@Controller
@RequestMapping("/")
public class UserCredentialController {
	private static final Logger logger = LoggerFactory.getLogger(UserCredentialController.class);
	@Autowired
	private UserCredentialService credentialService;

	public UserCredentialController() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "addCredentials.do", method = RequestMethod.POST)
	public ModelAndView addCredential(UserCredentialDTO dto, HttpServletRequest request) {
		logger.debug("invoking addCredential with args:\t" + dto);
		try {
			HttpSession httpSession = request.getSession(false);

			if (httpSession != null) {
				if (httpSession.getAttribute("userEntity") != null) {
					UserEntity entity = (UserEntity) httpSession.getAttribute("userEntity");
					if (!StringUtils.isEmpty((dto.getAppName()))) {
						dto.setLoggedUser(entity.getUserName());
						boolean entityFromDb = credentialService.addCredential(dto);
						if (entityFromDb) {
							List<UserCredentialDTO> credentialDto = credentialService.getAllCredential(entity.getUserName());
							return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,
									entity.getUserName()).addObject("user", credentialDto).addObject("allCredential",
											"active");
						} else {
							List<UserCredentialDTO> credentialDto = credentialService
									.getAllCredential(entity.getUserName());
							return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,
									entity.getUserName())
											.addObject("existingUser", "App username already exist...give new name")
											.addObject("activeAddCredential", "active").addObject("user", credentialDto)
											.addObject("userExist", dto);
						}

					} else {
						List<UserCredentialDTO> credentialDto = credentialService
								.getAllCredential(entity.getUserName());
						return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,
								entity.getUserName()).addObject("appNameNull", "App name is required .......")
										.addObject("activeAddCredential", "active").addObject("user", credentialDto)
										.addObject("userExist", dto);
					}
				} else {
					return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
							"your Session timeout please login again");
				}
			}
		} catch (Exception e) {
			logger.debug("Exception occur in addCredential\t" + e.getMessage());

		}

		return null;
	}

	@RequestMapping(value = "editUserCredintials", method = RequestMethod.GET)
	public ModelAndView editUserCredintialController(HttpServletRequest request) {
		logger.debug("invoking editUserCredintials in contoller\t");
		System.out.println("invoking editUserCredintials in contoller\t");
		try {
			HttpSession httpSession = request.getSession(false);
			if (httpSession != null) {
				if (httpSession.getAttribute("userEntity") != null) {
					UserEntity entity = (UserEntity) httpSession.getAttribute("userEntity");
					String appname = request.getParameter("name");
					String UserName = request.getParameter("user");
					logger.debug("data from edit\t" + appname + UserName);

					UserCredentialDTO dtoFromDb = credentialService.editUserCredintialService(appname, UserName);
					if (dtoFromDb != null) {
						List<UserCredentialDTO> credentialDto = credentialService.getAllCredential(entity.getUserName());
						return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,entity.getUserName()).addObject("user", credentialDto)
								.addObject("editCredential", "active").addObject("credential", dtoFromDb);
					}
				} else {
					return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
							"your Session timeout please login again");
				}
			} else {
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
						"your Session timeout please login again");
			}
		} catch (Exception e) {
			logger.debug("Exception occur in editUserCredintials\t" + e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "updateCredentials", method = RequestMethod.POST)
	public ModelAndView updateUserCredintialController(UserCredentialDTO dto, HttpServletRequest request) {
		logger.debug("invoking updateUserCredintialController\t" + dto);
		logger.debug("invoking updateUserCredintialController\t" + dto);
		try {
			HttpSession httpSession = request.getSession(false);

			if (httpSession != null) {
				if (httpSession.getAttribute("userEntity") != null) {
					UserEntity entity = (UserEntity) httpSession.getAttribute("userEntity");
					dto.setLoggedUser(entity.getUserName());
					UserCredentialDTO dto2 = credentialService.updateUserCredintialService(dto);
					if (dto2 != null) {
						System.out.println("summane bande illige");
						List<UserCredentialDTO> credentialDto = credentialService.getAllCredential(entity.getUserName());
						return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,
								entity.getUserName()).addObject("user", credentialDto).addObject("allCredential","active");
						}
				} else {
					return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
							"your Session timeout please login again");
				}
			} else {
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
						"your Session timeout please login again");
			}

		} catch (Exception e) {
			logger.debug("Exception occure in updateUserCredintialController\t" + e.getMessage());
		}

		return null;

	}

	@RequestMapping(value = "deleteUserCredintials", method = RequestMethod.GET)
	public ModelAndView deleteCredintialController(HttpServletRequest request) {
		logger.debug("invoking deleteCredintialController\t");
		try {
			HttpSession httpSession = request.getSession(false);

			if (httpSession != null) {
				if (httpSession.getAttribute("userEntity") != null) {
					UserEntity entity = (UserEntity) httpSession.getAttribute("userEntity");
					String appName = request.getParameter("appName");
					String userName = request.getParameter("userName");
					credentialService.deleteCredintialService(appName, userName);
					System.err.println("data from delete\t" + appName + userName);
					List<UserCredentialDTO> credentialDto = credentialService.getAllCredential(entity.getUserName());
					return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,
							entity.getUserName()).addObject("user", credentialDto).addObject("allCredential", "active");
				} else {
					return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
							"your Session timeout please login again");
				}
			} else {
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message",
						"your Session timeout please login again");
			}
		} catch (Exception e) {
			logger.debug("Exception occure in deleteCredintialController\t" + e.getMessage());
		}

		return null;
	}
}
