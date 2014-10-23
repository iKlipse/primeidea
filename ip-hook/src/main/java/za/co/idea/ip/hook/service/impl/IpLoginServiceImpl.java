package za.co.idea.ip.hook.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import za.co.idea.ip.hook.model.IpLogin;
import za.co.idea.ip.hook.service.IpUserServiceUtil;
import za.co.idea.ip.hook.service.base.IpLoginServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.ac.AccessControlled;

/**
 * The implementation of the ip login remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link za.co.idea.ip.hook.service.IpLoginService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author VMPattamatta
 * @see za.co.idea.ip.hook.service.base.IpLoginServiceBaseImpl
 * @see za.co.idea.ip.hook.service.IpLoginServiceUtil
 */
public class IpLoginServiceImpl extends IpLoginServiceBaseImpl {
	@AccessControlled(guestAccessEnabled = true)
	public boolean validateIpLogin(String loginName, String password) {
		try {
			List<IpLogin> ipLogins = ipLoginPersistence.findBygetIpLoginScreenName_Password(loginName, Base64.encodeBase64URLSafeString(DigestUtils.md5(password)));
			if (Validator.isNull(ipLogins))
				return false;
			if (ipLogins.size() != 1)
				return false;
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return true;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean validateReminder(String loginName, String reminderAnswer) {
		try {
			List<IpLogin> ipLogins = ipLoginPersistence.findBygetIpLoginScreenName_LoginSecA(loginName, Base64.encodeBase64URLSafeString(DigestUtils.md5(reminderAnswer)));
			if (Validator.isNull(ipLogins))
				return false;
			if (ipLogins.size() != 1)
				return false;
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return true;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean updatePasswordIpLogin(String loginName, String password, long companyId, boolean passwordReset) {
		if (IpUserServiceUtil.validateIpUser(loginName)) {
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
			ipLogin.setLoginPwd(Base64.encodeBase64URLSafeString(DigestUtils.md5(password)));
			try {
				ipLoginPersistence.updateImpl(ipLogin);
			} catch (SystemException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@AccessControlled(guestAccessEnabled = true)
	public IpLogin getIpLogin(String loginName) {
		if (IpUserServiceUtil.validateIpUser(loginName)) {
			List<IpLogin> ipLogins = null;
			try {
				ipLogins = ipLoginPersistence.findBygetIpLoginScreenName(loginName);
				if (Validator.isNull(ipLogins))
					return null;
				if (ipLogins.size() != 1)
					return null;
			} catch (SystemException e) {
				e.printStackTrace();
			}
			return ipLogins.get(0);
		}
		return null;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean setLoginLastDate(String loginName) {
		IpLogin ipLogin = getIpLogin(loginName);
		if (Validator.isNotNull(ipLogin)) {
			Date date = new Date();
			ipLogin.setLoginLastDt(date);
			try {
				ipLoginPersistence.updateImpl(ipLogin);
			} catch (SystemException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	@AccessControlled(guestAccessEnabled = true)
	public boolean updateReminder(String loginName, int reminderQuestion, String reminderAnswer) {
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
		ipLogin.setLoginSecQ(reminderQuestion);
		ipLogin.setLoginSecA(Base64.encodeBase64URLSafeString(DigestUtils.md5(reminderAnswer)));
		try {
			ipLoginPersistence.updateImpl(ipLogin);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return true;
	}

}
