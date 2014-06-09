package za.co.idea.web.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import za.co.idea.web.ui.bean.UserBean;

public class SessionPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 3201240827257809143L;

	@Override
	public void afterPhase(PhaseEvent pe) {
		System.out.println(pe.getPhaseId() + " :: " + pe.getFacesContext().getViewRoot());
	}

	@Override
	public void beforePhase(PhaseEvent pe) {
		FacesContext context = pe.getFacesContext();
		ResourceBundle bundle = ResourceBundle.getBundle("ip-web");
		boolean bypassAuth = Boolean.valueOf(bundle.getString("bypass.auth"));
		UserBean users = (UserBean) context.getExternalContext().getSessionMap().get("user");
		System.out.println(pe.getPhaseId() + " :: " + context.getViewRoot());
		if (!bypassAuth && !(context.getViewRoot() == null) && !(StringUtils.contains(((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURL().toString(), "login")) && !(StringUtils.contains(((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURL().toString(), "rPwd"))) {
			if (users == null) {
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("Invalid User Session or Session Expired");
				message.setDetail("Please Login Again");
				context.addMessage(null, message);
				context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
