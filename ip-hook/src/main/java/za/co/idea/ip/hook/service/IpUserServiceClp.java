package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpUser;

import com.liferay.portal.service.InvokableService;

/**
 * @author VMPattamatta
 * @generated
 */
public class IpUserServiceClp implements IpUserService {
	private InvokableService _invokableService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;

	public IpUserServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "getBeanIdentifier";

		_methodParameterTypes0 = new String[] {};

		_methodName1 = "setBeanIdentifier";

		_methodParameterTypes1 = new String[] { "java.lang.String" };

		_methodName3 = "validateIpUser";

		_methodParameterTypes3 = new String[] { "java.lang.String" };

		_methodName4 = "validateLiferayUser";

		_methodParameterTypes4 = new String[] { "java.lang.String", "long" };

		_methodName5 = "getIpUser";

		_methodParameterTypes5 = new String[] { "java.lang.String", "java.lang.String" };

		_methodName6 = "addLiferayUser";

		_methodParameterTypes6 = new String[] { "long", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "long", "long", "com.liferay.portal.service.ServiceContext" };

		_methodName7 = "updateIpUser";

		_methodParameterTypes7 = new String[] { "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "long" };
	}

	@Override
	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0, _methodParameterTypes0, new Object[] {});
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return (java.lang.String) ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableService.invokeMethod(_methodName1, _methodParameterTypes1, new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name, java.lang.String[] parameterTypes, java.lang.Object[] arguments) throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean validateIpUser(java.lang.String loginName) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName3, _methodParameterTypes3, new Object[] { ClpSerializer.translateInput(loginName) });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return ((Boolean) returnObj).booleanValue();
	}

	@Override
	public boolean validateLiferayUser(java.lang.String loginName, long companyId) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName4, _methodParameterTypes4, new Object[] { ClpSerializer.translateInput(loginName),

			companyId });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return ((Boolean) returnObj).booleanValue();
	}

	@Override
	public IpUser getIpUser(java.lang.String loginName, java.lang.String password) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName5, _methodParameterTypes5, new Object[] { ClpSerializer.translateInput(loginName),

			ClpSerializer.translateInput(password) });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return (IpUser) ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public boolean addLiferayUser(long userId, java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String password, java.lang.String screenName, long creatorUserId, long companyId, com.liferay.portal.service.ServiceContext serviceContext) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName6, _methodParameterTypes6, new Object[] { userId,

			ClpSerializer.translateInput(firstName),

			ClpSerializer.translateInput(lastName),

			ClpSerializer.translateInput(email),

			ClpSerializer.translateInput(password),

			ClpSerializer.translateInput(screenName),

			creatorUserId,

			companyId,

			ClpSerializer.translateInput(serviceContext) });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return ((Boolean) returnObj).booleanValue();
	}

	@Override
	public boolean updateIpUser(java.lang.String loginName, java.lang.String emailAddress, java.lang.String firstName, java.lang.String lastName, long companyId) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName7, _methodParameterTypes7, new Object[] { ClpSerializer.translateInput(loginName),

			ClpSerializer.translateInput(emailAddress),

			ClpSerializer.translateInput(firstName),

			ClpSerializer.translateInput(lastName),

			companyId });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return ((Boolean) returnObj).booleanValue();
	}
}
