package za.co.idea.ip.portal.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.portlet.PortletSession;

import com.liferay.faces.portal.context.LiferayFacesContext;

@ManagedBean(name = "sessionVariablesController")
@SessionScoped
public class SessionVariablesController {
	private ArrayList<String> values;

	public void initializePage() {
		PortletSession session = LiferayFacesContext.getInstance().getPortletSession();
		values = new ArrayList<String>();
		for (String key : session.getAttributeMap().keySet())
			values.add(key);
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
}
