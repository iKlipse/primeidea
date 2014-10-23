package za.co.idea.ip.hook.service.impl;

import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import za.co.idea.ip.hook.NoSuchIpUserException;
import za.co.idea.ip.hook.model.IpLogin;
import za.co.idea.ip.hook.model.IpUser;
import za.co.idea.ip.hook.service.base.IpUserServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the ip user remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link za.co.idea.ip.hook.service.IpUserService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author VMPattamatta
 * @see za.co.idea.ip.hook.service.base.IpUserServiceBaseImpl
 * @see za.co.idea.ip.hook.service.IpUserServiceUtil
 */
public class IpUserServiceImpl extends IpUserServiceBaseImpl {
	@AccessControlled(guestAccessEnabled = true)
	public boolean validateIpUser(String loginName) {
		try {
			List<IpUser> ipUsers = ipUserPersistence.findBygetIpUserScreenName(loginName);
			if (Validator.isNull(ipUsers))
				return false;
			if (ipUsers.size() != 1)
				return false;
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return true;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean validateLiferayUser(String loginName, long companyId) {
		try {
			User user = UserLocalServiceUtil.fetchUserByScreenName(companyId, loginName);
			if (Validator.isNull(user))
				return false;
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return true;
	}

	@AccessControlled(guestAccessEnabled = true)
	public IpUser getIpUser(String loginName, String password) {
		IpUser ipUser = null;
		IpLogin ipLogin = null;
		try {
			List<IpLogin> ipLogins = ipLoginPersistence.findBygetIpLoginScreenName_Password(loginName, Base64.encodeBase64URLSafeString(DigestUtils.md5(password)));
			if (Validator.isNull(ipLogins))
				return null;
			if (ipLogins.size() != 1)
				return null;
			ipLogin = ipLogins.get(0);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		try {
			ipUser = ipUserPersistence.findByPrimaryKey(ipLogin.getLoginUserId());
		} catch (NoSuchIpUserException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return ipUser;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean addLiferayUser(long userId, String firstName, String lastName, String email, String password, String screenName, long creatorUserId, long companyId, ServiceContext serviceContext) {
		long[] emptyLong = {};
		try {
			UserLocalServiceUtil.addUser(creatorUserId, companyId, false, password, password, false, screenName, email, 0l, "", new Locale("en-US"), firstName, null, lastName, 0, 0, false, 1, 1, 2000, null, emptyLong, emptyLong, emptyLong, emptyLong, false, serviceContext);
		} catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return true;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean updateIpUser(String loginName, String emailAddress, String firstName, String lastName, long companyId) {
		if (validateIpUser(loginName)) {
			List<IpLogin> ipLogins = null;
			try {
				ipLogins = ipLoginPersistence.findBygetIpLoginScreenName(loginName);
				if (Validator.isNull(ipLogins))
					return false;
				if (ipLogins.size() != 1)
					return false;
			} catch (SystemException e) {
				e.printStackTrace();
			}

			IpLogin ipLogin = ipLogins.get(0);
			IpUser ipUser = null;
			try {
				ipUser = ipUserPersistence.findByPrimaryKey(ipLogin.getLoginUserId());
			} catch (NoSuchIpUserException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			ipUser.setUserScreenName(loginName);
			ipUser.setUserFName(firstName);
			ipUser.setUserLName(lastName);
			ipUser.setUserEmail(emailAddress);
			try {
				ipUserPersistence.updateImpl(ipUser);
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
