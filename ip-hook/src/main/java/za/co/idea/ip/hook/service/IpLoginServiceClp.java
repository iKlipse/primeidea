package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpLogin;

import com.liferay.portal.service.InvokableService;

/**
 * @author VMPattamatta
 * @generated
 */
public class IpLoginServiceClp implements IpLoginService {
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
	private String _methodName8;
	private String[] _methodParameterTypes8;

	public IpLoginServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "getBeanIdentifier";

		_methodParameterTypes0 = new String[] {};

		_methodName1 = "setBeanIdentifier";

		_methodParameterTypes1 = new String[] { "java.lang.String" };

		_methodName3 = "validateIpLogin";

		_methodParameterTypes3 = new String[] { "java.lang.String", "java.lang.String" };

		_methodName4 = "validateReminder";

		_methodParameterTypes4 = new String[] { "java.lang.String", "java.lang.String" };

		_methodName5 = "updatePasswordIpLogin";

		_methodParameterTypes5 = new String[] { "java.lang.String", "java.lang.String", "long", "boolean" };

		_methodName6 = "getIpLogin";

		_methodParameterTypes6 = new String[] { "java.lang.String" };

		_methodName7 = "setLoginLastDate";

		_methodParameterTypes7 = new String[] { "java.lang.String" };

		_methodName8 = "updateReminder";

		_methodParameterTypes8 = new String[] { "java.lang.String", "int", "java.lang.String" };
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
	public boolean validateIpLogin(java.lang.String loginName, java.lang.String password) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName3, _methodParameterTypes3, new Object[] { ClpSerializer.translateInput(loginName),

			ClpSerializer.translateInput(password) });
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
	public boolean validateReminder(java.lang.String loginName, java.lang.String reminderAnswer) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName4, _methodParameterTypes4, new Object[] { ClpSerializer.translateInput(loginName),

			ClpSerializer.translateInput(reminderAnswer) });
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
	public boolean updatePasswordIpLogin(java.lang.String loginName, java.lang.String password, long companyId, boolean passwordReset) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName5, _methodParameterTypes5, new Object[] { ClpSerializer.translateInput(loginName),

			ClpSerializer.translateInput(password),

			companyId,

			passwordReset });
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
	public IpLogin getIpLogin(java.lang.String loginName) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName6, _methodParameterTypes6, new Object[] { ClpSerializer.translateInput(loginName) });
		} catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getClass().getName() + " is not a valid exception");
			}
		}

		return (IpLogin) ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public boolean setLoginLastDate(java.lang.String loginName) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName7, _methodParameterTypes7, new Object[] { ClpSerializer.translateInput(loginName) });
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
	public boolean updateReminder(java.lang.String loginName, int reminderQuestion, java.lang.String reminderAnswer) {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName8, _methodParameterTypes8, new Object[] { ClpSerializer.translateInput(loginName),

			reminderQuestion,

			ClpSerializer.translateInput(reminderAnswer) });
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
