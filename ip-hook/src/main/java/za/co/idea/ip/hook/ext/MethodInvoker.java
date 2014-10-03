package za.co.idea.ip.hook.ext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;

public class MethodInvoker {
	// Private Constants
	private static final String LOGIN_UTIL_FQCN = "com.liferay.portlet.login.util.LoginUtil";
	private static final String LOGIN_METHOD = "login";
	private static final Class<?>[] LOGIN_PARAM_TYPES = new Class<?>[] { HttpServletRequest.class, HttpServletResponse.class, String.class, String.class, boolean.class, String.class };

	public static Object invokeLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String handle, String password, boolean rememberMe, String authType) throws Exception {

		Class<?> loginUtilClass = ClassResolverUtil.resolveByPortalClassLoader(LOGIN_UTIL_FQCN);
		MethodKey methodKey = new MethodKey(loginUtilClass, LOGIN_METHOD, LOGIN_PARAM_TYPES);

		return PortalClassInvoker.invoke(false, methodKey, httpServletRequest, httpServletResponse, handle, password, rememberMe, authType);
	}

}
