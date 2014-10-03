package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpReview;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpReviewDAO;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.ReviewMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/rvs")
public class ReviewService {
	private static final Logger logger = Logger.getLogger(ReviewService.class);
	private IpReviewDAO ipReviewDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpGroupDAO ipGroupDAO;

	@POST
	@Path("/review/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseMessage createReview(ReviewMessage message) {
		try {
			Long[] ids = ipNativeSQLDAO.getNextIds(IpReview.class, message.getGroupId().length);
			int i = 0;
			for (Long grpId : message.getGroupId()) {
				IpReview rv = new IpReview();
				rv.setIpGroup(ipGroupDAO.findById(grpId));
				rv.setRevEntityId(message.getEntityId());
				rv.setRevEntityName(message.getTblNm());
				rv.setRevEntityStatusId(message.getStatusId());
				rv.setRevId(ids[i]);
				rv.setRevCrtdDt(new Date());
				ipReviewDAO.save(rv);
				i++;
			}
			ResponseMessage ret = new ResponseMessage();
			ret.setStatusCode(0);
			ret.setStatusDesc("Success");
			return ret;
		} catch (Exception e) {
			logger.error(e, e);
			ResponseMessage ret = new ResponseMessage();
			ret.setStatusCode(1);
			ret.setStatusDesc("Java Exception " + e.getMessage());
			return ret;
		}
	}

	@PUT
	@Path("/review/modify")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseMessage updateReview(ReviewMessage message) {
		try {
			Long[] ids = ipNativeSQLDAO.getNextIds(IpReview.class, message.getGroupId().length);
			int i = 0;
			if (message.getEntityId() != null) {
				logger.info("Entity Id----" + message.getEntityId());
				ipReviewDAO.deleteByEntityIdEntityName(message.getEntityId(), message.getTblNm(), message.getStatusId());
			}

			for (Long grpId : message.getGroupId()) {
				IpReview rv = new IpReview();
				rv.setIpGroup(ipGroupDAO.findById(grpId));
				rv.setRevEntityId(message.getEntityId());
				rv.setRevEntityName(message.getTblNm());
				rv.setRevEntityStatusId(message.getStatusId());
				rv.setRevId(ids[i]);
				rv.setRevCrtdDt(new Date());
				ipReviewDAO.save(rv);
				i++;
			}
			ResponseMessage ret = new ResponseMessage();
			ret.setStatusCode(0);
			ret.setStatusDesc("Success");
			logger.info("Success Message ----");
			return ret;
		} catch (Exception e) {
			logger.error("Error while saving reviewer: " + e);
			ResponseMessage ret = new ResponseMessage();
			ret.setStatusCode(1);
			ret.setStatusDesc("Java Exception " + e.getMessage());
			return ret;
		}
	}

	@GET
	@Path("/review/list/{entityId}/{tblNm}/{status}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public ReviewMessage listReviewsByEntity(@PathParam("entityId") Long entityId, @PathParam("tblNm") String tblNm, @PathParam("status") Integer status) {
		List rvs = ipReviewDAO.findByEntityIdEntityName(entityId, tblNm, status);
		ReviewMessage message = new ReviewMessage();
		message.setEntityId(entityId);
		message.setStatusId(status);
		message.setTblNm(tblNm);
		if (rvs != null && rvs.size() > 0) {
			Long[] grps = new Long[rvs.size()];
			int i = 0;
			for (Object object : rvs) {
				IpReview review = (IpReview) object;
				grps[i] = review.getIpGroup().getGroupId();
			}
			message.setGroupId(grps);
		}
		return message;
	}

	@GET
	@Path("/review/list/all/{entityId}/{tblNm}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public <T extends ReviewMessage> List<T> listAllReviewsByEntity(@PathParam("entityId") Long entityId, @PathParam("tblNm") String tblNm) {
		List<T> ret = new ArrayList<T>();
		int i = ipReviewDAO.findReviewStatusCount(entityId, tblNm);
		for (int j = 0; j < i; j++) {
			List rvs = ipReviewDAO.findByEntityIdEntityName(entityId, tblNm, j + 1);
			ReviewMessage message = new ReviewMessage();
			message.setEntityId(entityId);
			message.setStatusId(j);
			message.setTblNm(tblNm);
			if (rvs != null && rvs.size() > 0) {
				Long[] grps = new Long[rvs.size()];
				int k = 0;
				for (Object object : rvs) {
					IpReview review = (IpReview) object;
					grps[k] = review.getIpGroup().getGroupId();
					k++;
				}
				message.setGroupId(grps);
			}
			ret.add((T) message);
		}
		return ret;
	}

	@GET
	@Path("/review/list/cnt/{entityId}/{tblNm}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public Integer listReviewsByEntity(@PathParam("entityId") Long entityId, @PathParam("tblNm") String tblNm) {
		return ipReviewDAO.findReviewStatusCount(entityId, tblNm);
	}

	public IpReviewDAO getIpReviewDAO() {
		return ipReviewDAO;
	}

	public void setIpReviewDAO(IpReviewDAO ipReviewDAO) {
		this.ipReviewDAO = ipReviewDAO;
	}

	public IpNativeSQLDAO getIpNativeSQLDAO() {
		return ipNativeSQLDAO;
	}

	public void setIpNativeSQLDAO(IpNativeSQLDAO ipNativeSQLDAO) {
		this.ipNativeSQLDAO = ipNativeSQLDAO;
	}

	public IpGroupDAO getIpGroupDAO() {
		return ipGroupDAO;
	}

	public void setIpGroupDAO(IpGroupDAO ipGroupDAO) {
		this.ipGroupDAO = ipGroupDAO;
	}
}
