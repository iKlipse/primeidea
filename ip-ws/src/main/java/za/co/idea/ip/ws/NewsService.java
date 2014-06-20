package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import za.co.idea.ip.orm.bean.IpNews;
import za.co.idea.ip.orm.dao.IpNewsDAO;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/ns")
public class NewsService {

	private IpNewsDAO ipNewsDAO;

	@POST
	@Path("/news/add")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage createNews(NewsMessage news) {
		try {
			IpNews ipNews = new IpNews();
			ipNews.setNewsId(news.getnId());
			ipNews.setnTitle(news.getnTitle());
			ipNews.setnStartDate(news.getStartDate());
			ipNews.setnEndDate(news.getEndDate());
			ipNews.setnContent(news.getContent());
			ipNewsDAO.save(ipNews);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@PUT
	@Path("/news/modify")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage updateClaim(NewsMessage news) {
		try {
			IpNews ipNews = new IpNews();
			ipNews.setNewsId(news.getnId());
			ipNews.setnTitle(news.getnTitle());
			ipNews.setnStartDate(news.getStartDate());
			ipNews.setnEndDate(news.getEndDate());
			ipNews.setnContent(news.getContent());
			ipNewsDAO.merge(ipNews);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@GET
	@Path("/news/list")
	@Produces("application/json")
	public <T extends NewsMessage> List<T> listNews() {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipNewsDAO.findAll();
			for (Object object : vals) {
				IpNews news = (IpNews) object;
				NewsMessage message = new NewsMessage();
				message.setnId(news.getNewsId());
				message.setContent(news.getnContent());
				message.setStartDate(news.getnStartDate());
				message.setEndDate(news.getnEndDate());
				message.setnTitle(news.getnTitle());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/news/get/{id}")
	@Produces("application/json")
	public NewsMessage getNewsById(@PathParam("id") Long id) {
		NewsMessage message = new NewsMessage();
		try {
			IpNews news = ipNewsDAO.findById(id);
			message.setnId(news.getNewsId());
			message.setContent(news.getnContent());
			message.setStartDate(news.getnStartDate());
			message.setEndDate(news.getnEndDate());
			message.setnTitle(news.getnTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public IpNewsDAO getIpNewsDAO() {
		return ipNewsDAO;
	}

	public void setIpNewsDAO(IpNewsDAO ipNewsDAO) {
		this.ipNewsDAO = ipNewsDAO;
	}

}
