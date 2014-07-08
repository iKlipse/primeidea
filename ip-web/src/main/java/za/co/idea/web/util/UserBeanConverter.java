package za.co.idea.web.util;

import java.util.Collections;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.web.ui.bean.UserBean;

public class UserBeanConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return getUserById(Long.valueOf(arg2));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Long) arg2).toString();
	}

	private WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	private UserBean getUserById(Long id) {
		UserBean bean = new UserBean();
		WebClient userByIdClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/get/" + id);
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
