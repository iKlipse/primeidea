package za.co.idea.web.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.DefaultWebRequestor;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.User;

public class SocialConnectorValidator implements Validator {

	protected static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-web");

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		if (arg1.getAttributes().get("function").toString().equalsIgnoreCase("FB")) {
			AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken("1404712079799728", "fe051d7dcf717d4838629e31807fdcb3");
			DefaultFacebookClient client = new DefaultFacebookClient(accessToken.getAccessToken(), new ProxyWebRequestor(), new DefaultJsonMapper());
			String query = "select * from user where profile_url=:profile";
			Parameter parameter = Parameter.with("profile", "http://www.facebook.com/" + arg2.toString());
			List<User> users = client.executeFqlQuery(query, User.class, new Parameter[] { parameter });
			if (users.size() == 0)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Facebook Handle.", "Invalid Facebook Handle."));
		} else if (arg1.getAttributes().get("function").toString().equalsIgnoreCase("TW")) {
			Twitter twitter = null;
			if (Boolean.valueOf(BUNDLE.getString("is.proxy"))) {
				ConfigurationBuilder cb = new ConfigurationBuilder();
				cb.setHttpProxyHost(BUNDLE.getString("proxy.host"));
				cb.setHttpProxyPort(Integer.parseInt(BUNDLE.getString("proxy.port")));
				twitter = new TwitterFactory(cb.build()).getInstance();
			} else {
				twitter = TwitterFactory.getSingleton();
			}
			twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken("339569490-OiewT7WXGInQIuO6gANQTWxctBgX4TQaprXqcHYK", "3F4LfwGzF4uOJMeTpXG54XcUX0tfB8pwMbF4o8RnmEqFv"));
		}
	}

	protected class ProxyWebRequestor extends DefaultWebRequestor {
		protected HttpURLConnection openConnection(URL url) throws IOException {
			if (Boolean.valueOf(BUNDLE.getString("is.proxy"))) {
				InetSocketAddress proxyLocation = new InetSocketAddress(BUNDLE.getString("proxy.host"), Integer.parseInt(BUNDLE.getString("proxy.port")));
				Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyLocation);
				return (HttpURLConnection) url.openConnection(proxy);
			} else {
				return (HttpURLConnection) url.openConnection();
			}
		}
	}
}
