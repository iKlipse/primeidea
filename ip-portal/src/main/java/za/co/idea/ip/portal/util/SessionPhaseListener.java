package za.co.idea.ip.portal.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

public class SessionPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 3201240827257809143L;

	public void afterPhase(PhaseEvent pe) {
		System.out.println(pe.getPhaseId() + " :: " + pe.getFacesContext().getViewRoot());
	}

	public void beforePhase(PhaseEvent pe) {
		ResourceBundle bundle = ResourceBundle.getBundle("ip-portal");
		boolean bypassAuth = Boolean.valueOf(bundle.getString("bypass.auth"));
		PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		User user = (User) request.getAttribute(WebKeys.USER);
		if (!bypassAuth && (user == null || user.getScreenName() == null)) {
			String url;
			try {
				url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/c/portal/logout").toString();
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
