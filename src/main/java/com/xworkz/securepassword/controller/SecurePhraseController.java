package com.xworkz.securepassword.controller;

import java.util.Date;
import java.util.List;

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
import com.xworkz.securepassword.dto.LogInHistoryDTO;
import com.xworkz.securepassword.dto.UserCredentialDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.SecurePhraseCtlrException;
import com.xworkz.securepassword.service.LogInHistoryService;
import com.xworkz.securepassword.service.UserCredentialService;
import com.xworkz.securepassword.util.EcryptionDecryption;

@Controller
@RequestMapping("/")
public class SecurePhraseController {
	private static final Logger logger = LoggerFactory.getLogger(SecurePhraseController.class);

	@Autowired
	private EcryptionDecryption encryptDecrypt;
	
	@Autowired
	private LogInHistoryService logInHistoryService;

	@Autowired
	private UserCredentialService credentialService;

	public SecurePhraseController() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}

	@RequestMapping(value = "secureLogIn.do", method = RequestMethod.POST)
	public ModelAndView secureLogin(HttpServletRequest request) throws SecurePhraseCtlrException {
		logger.debug("invoking secure login with args\t");
		String phrase = request.getParameter("securePhrase");
		try {
			HttpSession httpsession = request.getSession(false);
			if (httpsession != null) {

				if (httpsession.getAttribute("userEntity") != null) {
					UserEntity entity = (UserEntity) httpsession.getAttribute("userEntity");
					if (phrase != null) {
						if (phrase.equals(EcryptionDecryption.decrypt(entity.getSecurePhrase()))) {
							LogInHistoryDTO dto = logInHistoryService.getLogInDateService(entity.getUserName());
							logger.debug("this is date from db.............." + dto);
							LogInHistoryDTO historyDTO = new LogInHistoryDTO();
							historyDTO.setUserName(entity.getUserName());
							historyDTO.setLastLogIn(new Date());
							logInHistoryService.saveLogInDateService(historyDTO);
							List<UserCredentialDTO> credentialDto = credentialService.getAllCredential(entity.getUserName());
							// logger.debug(credentialDto);
							return new ModelAndView(ApplicationConstants.HOME_PAGE, StringConstants.USER_NAME,
									entity.getUserName()).addObject("user", credentialDto).
									addObject("allCredential", "active").addObject("lastLogInDate", "Last LogIn date :" + dto.getLastLogIn());
						} else {
							return new ModelAndView(ApplicationConstants.SECURE_PHRASE_PAGE,StringConstants.INVALID_PHRASE, "Invalid Phrase");
						}
					} else {
						return new ModelAndView(ApplicationConstants.SECURE_PHRASE_PAGE, StringConstants.EMPTY_FIELDS,"Add credentials");
					}
				} else {
					return new ModelAndView(ApplicationConstants.SECURE_PHRASE_PAGE, StringConstants.EMPTY_FIELDS,"Add credentials");
				}
			} else {
				return new ModelAndView(ApplicationConstants.LOG_IN_PAGE, "message","your Session timeout please login again");
			}

		} catch (Exception e) {
			logger.debug("Exception occure in Secure login \t" + e.getMessage());
			throw new SecurePhraseCtlrException("Exception thrown in SecurePhraseController\t" + e.getMessage());
		}

	}
}
