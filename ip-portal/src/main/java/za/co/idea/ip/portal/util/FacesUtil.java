package za.co.idea.ip.portal.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {
	public static String getBaseURL(final FacesContext facesContext) throws MalformedURLException {
		return getBaseURL(facesContext.getExternalContext());
	}

	public static String getBaseURL(final ExternalContext externalContext) throws MalformedURLException {
		return getBaseURL((HttpServletRequest) externalContext.getRequest());
	}

	public static String getBaseURL(final HttpServletRequest request) throws MalformedURLException {
		return new URL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath()).toString();
	}

	public static boolean lengthValidation(String str, int minLimit, int maxLimit) {
		int intLength = str.length();
		if (intLength >= minLimit && intLength <= maxLimit) {
			return true;
		} else {
			return false;
		}

	}

}
