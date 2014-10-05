package za.co.idea.ip.portal.util;

import java.util.Collections;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

public class GroupBeanConverter implements Converter {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return getGroupById(Long.valueOf(arg2));
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Long) arg2).toString();
	}

	private WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	private GroupBean getGroupById(Long pGrpId) {
		GroupBean bean = new GroupBean();
		WebClient groupByIdClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/get/" + pGrpId);
		GroupMessage groupMessage = groupByIdClient.accept(MediaType.APPLICATION_JSON).get(GroupMessage.class);
		groupByIdClient.close();
		bean.setgId(groupMessage.getgId());
		bean.setGeMail(groupMessage.getGeMail());
		bean.setgName(groupMessage.getgName());
		bean.setIsActive(groupMessage.getIsActive());
		bean.setSelAdmUser(groupMessage.getAdmUserId());
		bean.setSelPGrp(groupMessage.getpGrpId());
		return bean;
	}

}
