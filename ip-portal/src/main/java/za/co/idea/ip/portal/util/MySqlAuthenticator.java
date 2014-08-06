package za.co.idea.ip.portal.util;

import java.util.Date;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.idea.ip.ws.bean.UserMessage;

import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.UserLocalServiceUtil;

public class MySqlAuthenticator implements Authenticator {
	private static final Logger logger = Logger.getLogger(MySqlAuthenticator.class);
	private static final int AUTH_BY_EMAIL_ID = 1;
	private static final int AUTH_BY_SCREEN_NAME = 2;
	private static final int AUTH_BY_USER_ID = 3;

	@Override
	public int authenticateByEmailAddress(long companyId, String emailAddress, String password, Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {
		authenticate(companyId, emailAddress, password, AUTH_BY_EMAIL_ID);
		return Authenticator.SUCCESS;
	}

	@Override
	public int authenticateByScreenName(long companyId, String screenName, String password, Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {
		authenticate(companyId, screenName, password, AUTH_BY_SCREEN_NAME);
		return Authenticator.SUCCESS;
	}

	@Override
	public int authenticateByUserId(long companyId, long userId, String password, Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {
		authenticate(companyId, String.valueOf(userId), password, AUTH_BY_USER_ID);
		return Authenticator.SUCCESS;
	}

	private int authenticate(long companyId, String authPrinciple, String password, int authType) {
		switch (authType) {
		case AUTH_BY_EMAIL_ID:
			return Authenticator.FAILURE;
		case AUTH_BY_SCREEN_NAME:
			WebClient loginClient = RESTServiceHelper.createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/login/" + authPrinciple + "/" + Base64.encodeBase64URLSafeString(DigestUtils.md5(password.getBytes())));
			UserMessage userMessage = loginClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			loginClient.close();
			if (userMessage != null && userMessage.getuId() != null && userMessage.getuId() == -999l) {
				return Authenticator.FAILURE;
			} else if (userMessage == null || userMessage.getuId() == null || userMessage.getScName() == null) {
				return Authenticator.FAILURE;
			} else {
				User user = null;
				try {
					user = UserLocalServiceUtil.fetchUserByScreenName(companyId, authPrinciple);
					if (user == null) {
						user = new UserImpl();
						user.setCompanyId(companyId);
						user.setScreenName(authPrinciple);
						user.setEmailAddress("info@primedia.co.za");
						user.setPassword(password);
						user.setCreateDate(new Date());
						user.setFirstName("Portal User");
						UserLocalServiceUtil.addUser(user);
					}
				} catch (Exception e) {
					logger.error(e, e);

				}
				return Authenticator.SUCCESS;
			}
		case AUTH_BY_USER_ID:
			return Authenticator.FAILURE;
		default:
			return Authenticator.FAILURE;
		}
	}
}
