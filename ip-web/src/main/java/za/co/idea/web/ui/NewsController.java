package za.co.idea.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.web.ui.bean.NewsBean;
import za.co.idea.web.util.IdNumberGen;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

public class NewsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8898258487836934087L;
	private NewsBean newsBean;
	private List<NewsBean> viewNewsBeans;
	private static final IdNumberGen COUNTER = new IdNumberGen();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public String showCreateNews() {
		try {
			newsBean = new NewsBean();
			return "nscc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewNews() {
		try {
			viewNewsBeans = fetchAllNews();
			return "nsvc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditNews() {
		return "nsec";
	}

	public String showSummaryNews() {
		return "nssc";
	}

	public String saveNews() {
		try {
			WebClient addNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/add");
			NewsMessage message = new NewsMessage();
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setnId(COUNTER.getNextId("IpNews"));
			message.setStartDate(newsBean.getnStartDate());
			message.setEndDate(newsBean.getnEndDate());
			ResponseMessage response = addNewsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addNewsClient.close();
			if (response.getStatusCode() == 0) {
				return showViewNews();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateNews() {
		try {
			WebClient updateNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/modify");
			NewsMessage message = new NewsMessage();
			message.setnId(newsBean.getnId());
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setStartDate(newsBean.getnStartDate());
			message.setEndDate(newsBean.getnEndDate());
			ResponseMessage response = updateNewsClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateNewsClient.close();
			if (response.getStatusCode() == 0) {
				return showViewNews();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	private List<NewsBean> fetchAllNews() {
		List<NewsBean> ret = new ArrayList<NewsBean>();
		WebClient fetchNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/list");
		Collection<? extends NewsMessage> news = new ArrayList<NewsMessage>(fetchNewsClient.accept(MediaType.APPLICATION_JSON).getCollection(NewsMessage.class));
		fetchNewsClient.close();
		for (NewsMessage message : news) {
			NewsBean bean = new NewsBean();
			bean.setnId(message.getnId());
			bean.setnContent(message.getContent());
			bean.setnEndDate(message.getEndDate());
			bean.setnStartDate(message.getStartDate());
			bean.setnTitle(message.getnTitle());
			ret.add(bean);
		}
		return ret;
	}

	public NewsBean getNewsBean() {
		return newsBean;
	}

	public void setNewsBean(NewsBean newsBean) {
		this.newsBean = newsBean;
	}

	public List<NewsBean> getViewNewsBeans() {
		return viewNewsBeans;
	}

	public void setViewNewsBeans(List<NewsBean> viewNewsBeans) {
		this.viewNewsBeans = viewNewsBeans;
	}
}
