package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.bean.IpNews;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpNewsDAO;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/ns")
public class NewsService {

	private IpNewsDAO ipNewsDAO;
	private IpBlobDAO ipBlobDAO;
	private static final Logger logger = Logger.getLogger(NewsService.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");

	@POST
	@Path("/news/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createNews(NewsMessage news) {
		try {
			logger.debug("Control handled in createNews() of service ns/news/add call ");
			IpNews ipNews = new IpNews();
			ipNews.setNewsId(news.getnId());
			ipNews.setNewsTitle(news.getnTitle());
			ipNews.setNewsStartDate(news.getStartDate());
			ipNews.setNewsEndDate(news.getEndDate());
			ipNews.setNewsContent(news.getContent());
			ipNews.setNewsCrtdDt(new Date());
			logger.info("Before saving news details");
			ipNewsDAO.save(ipNews);
			logger.info("After saving new details");
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			logger.info("Before returning the response from service : " + message);
			return message;
		} catch (Exception e) {
			logger.error("Error in create news : " + e.getMessage());
			logger.error(e, e);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			logger.info("Before returning the response from service : " + message);
			return message;
		}
	}

	@PUT
	@Path("/news/modify")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateNews(NewsMessage news) {
		try {
			logger.debug("Control handled in updateNews() of service ns/news/modify call ");
			IpNews ipNews = new IpNews();
			ipNews.setNewsId(news.getnId());
			ipNews.setNewsTitle(news.getnTitle());
			ipNews.setNewsStartDate(news.getStartDate());
			ipNews.setNewsEndDate(news.getEndDate());
			ipNews.setNewsContent(news.getContent());
			ipNews.setNewsCrtdDt(news.getNewsCrtdDt());
			logger.info("Before updating news details for new id : " + news.getnId());
			ipNewsDAO.merge(ipNews);
			logger.info("News details are updated success fully");
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			logger.info("Before returning the response from service : " + message);
			return message;
		} catch (Exception e) {
			logger.error("Error in update news : " + e.getMessage());
			logger.error(e, e);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			logger.info("Before returning the response from service : " + message);
			return message;
		}
	}

	private NewsMessage getNewsMessage(IpNews news) {
		NewsMessage message = new NewsMessage();
		try {
			message.setnId(news.getNewsId());
			message.setContent(news.getNewsContent());
			message.setStartDate(news.getNewsStartDate());
			message.setEndDate(news.getNewsEndDate());
			message.setnTitle(news.getNewsTitle());
			message.setNewsCrtdDt(news.getNewsCrtdDt());
			IpBlob ipBlob = ipBlobDAO.getBlobByEntity(news.getNewsId(), "ip_news");
			if (ipBlob != null) {
				message.setNewsUrl("ip_news/" + news.getNewsId() + "/" + ipBlob.getBlobName());
				message.setNwImgAvail(true);
				message.setBlobUrl("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/fds?blobId=" + ipBlob.getBlobId());
				message.setFileName(ipBlob.getBlobName());
			} else {
				message.setNwImgAvail(false);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/news/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends NewsMessage> List<T> listNews() {
		List<T> ret = new ArrayList<T>();
		try {
			logger.debug("Control handled in listNews() of service /news/list call ");
			List vals = ipNewsDAO.findAll();
			logger.info("After retrieving all news details from IpNewsDAO  ");
			for (Object object : vals) {
				IpNews news = (IpNews) object;
				NewsMessage message = getNewsMessage(news);
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error("Error while getting news : " + e.getMessage());
			logger.error(e, e);
		}
		logger.info("Before returning news data response from service : " + ret);
		return ret;
	}

	@GET
	@Path("/news/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public NewsMessage getNewsById(@PathParam("id") Long id) {
		NewsMessage message = new NewsMessage();
		try {
			logger.debug("Control handled in getNewsById() of service /news/get/{id} call");
			IpNews news = ipNewsDAO.findById(id);
			logger.info("After retrieving news details based on Id from IpNewsDAO  ");
			message = getNewsMessage(news);
		} catch (Exception e) {
			logger.error("Error while news based on news id  : " + e.getMessage());
			logger.error(e, e);
		}
		return message;
	}

	public IpNewsDAO getIpNewsDAO() {
		return ipNewsDAO;
	}

	public void setIpNewsDAO(IpNewsDAO ipNewsDAO) {
		this.ipNewsDAO = ipNewsDAO;
	}

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

}
