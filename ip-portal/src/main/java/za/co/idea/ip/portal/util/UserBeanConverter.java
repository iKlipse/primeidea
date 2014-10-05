package za.co.idea.ip.portal.util;

import java.util.Collections;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

public class UserBeanConverter implements Converter {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return getUserById(Long.valueOf(arg2));
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

	private UserBean getUserById(Long id) {
		UserBean bean = new UserBean();
		WebClient userByIdClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/get/" + id);
		UserMessage userMessage = userByIdClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userByIdClient.close();
		bean.setBio(userMessage.getBio());
		bean.setContact(userMessage.getContact());
		bean.seteMail(userMessage.geteMail());
		bean.setFbHandle(userMessage.getFbHandle());
		bean.setfName(userMessage.getfName());
		bean.setIdNum(userMessage.getIdNum());
		bean.setIsActive(userMessage.getIsActive());
		bean.setlName(userMessage.getlName());
		bean.setmName(userMessage.getmName());
		bean.setPwd(userMessage.getPwd());
		bean.setScName(userMessage.getScName());
		bean.setSkills(userMessage.getSkills());
		bean.setTwHandle(userMessage.getTwHandle());
		bean.setIsActive(true);
		bean.setPriGroupName(userMessage.getPriGroupName());
		bean.setuId(userMessage.getuId());
		return bean;
	}

}
