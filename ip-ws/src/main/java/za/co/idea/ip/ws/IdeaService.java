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
import za.co.idea.ip.orm.bean.IpIdea;
import za.co.idea.ip.orm.bean.IpIdeaCat;
import za.co.idea.ip.orm.bean.IpIdeaGroup;
import za.co.idea.ip.orm.bean.IpIdeaStatus;
import za.co.idea.ip.orm.bean.IpNotif;
import za.co.idea.ip.orm.bean.IpUser;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpIdeaCatDAO;
import za.co.idea.ip.orm.dao.IpIdeaDAO;
import za.co.idea.ip.orm.dao.IpIdeaGroupDAO;
import za.co.idea.ip.orm.dao.IpIdeaStatusDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpNotifDAO;
import za.co.idea.ip.orm.dao.IpReviewDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/is")
public class IdeaService {
	private static final Logger logger = Logger.getLogger(IdeaService.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");
	private IpIdeaDAO ipIdeaDAO;
	private IpIdeaCatDAO ipIdeaCatDAO;
	private IpIdeaStatusDAO ipIdeaStatusDAO;
	private IpUserDAO ipUserDAO;
	private IpIdeaGroupDAO ipIdeaGroupDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpGroupDAO ipGroupDAO;
	private IpBlobDAO ipBlobDAO;
	private IpReviewDAO ipReviewDAO;
	private IpNotifDAO ipNotifDAO;

	@GET
	@Path("/idea/check/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Boolean checkTitle(@PathParam("title") String ideaTitle) {
		try {
			List ideasByTitle = ipIdeaDAO.findByIdeaTitle(ideaTitle);
			Boolean ret = (ideasByTitle != null && ideasByTitle.size() > 0);
			return ret;
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}

	@GET
	@Path("/idea/cat/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listIdeaCat() {
		List<T> ret = new ArrayList<T>();
		try {
			List ideaCats = ipIdeaCatDAO.findAll();
			for (Object object : ideaCats) {
				IpIdeaCat cat = (IpIdeaCat) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(cat.getIcId());
				message.setDesc(cat.getIcDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/status/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listIdeaStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List ideaStatuses = ipIdeaStatusDAO.findAll();
			for (Object object : ideaStatuses) {
				IpIdeaStatus status = (IpIdeaStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getIsId());
				message.setDesc(status.getIsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/status/list/{curr}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listNextIdeaStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideaStatuses = ipIdeaStatusDAO.findNext(curr);
			for (Object object : ideaStatuses) {
				IpIdeaStatus status = (IpIdeaStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getIsId());
				message.setDesc(status.getIsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/cat/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getIdeaCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpIdeaCat cat = ipIdeaCatDAO.findById(id);
			message.setId(cat.getIcId());
			message.setDesc(cat.getIcDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/idea/status/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getIdeaStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpIdeaStatus status = ipIdeaStatusDAO.findById(id);
			message.setId(status.getIsId());
			message.setDesc(status.getIsDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@POST
	@Path("/idea/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createIdea(IdeaMessage idea) {
		IpIdea ipIdea = new IpIdea();
		ipIdea.setIdeaId(idea.getIdeaId());
		ipIdea.setIdeaBa(idea.getIdeaBa());
		ipIdea.setIdeaDate(idea.getCrtdDate());
		ipIdea.setIdeaDesc(idea.getIdeaDesc());
		ipIdea.setIdeaTag(idea.getIdeaTag());
		ipIdea.setIdeaTitle(idea.getIdeaTitle());
		if (idea.getSelCatId() != null && idea.getSelCatId().longValue() >= 0)
			ipIdea.setIpIdeaCat(ipIdeaCatDAO.findById(idea.getSelCatId().intValue()));
		if (idea.getSetStatusId() != null && idea.getSetStatusId().longValue() >= 0)
			ipIdea.setIpIdeaStatus(ipIdeaStatusDAO.findById(idea.getSetStatusId().intValue()));
		if (idea.getCrtdById() != null && idea.getCrtdById().longValue() >= 0)
			ipIdea.setIpUser(ipUserDAO.findById(idea.getCrtdById()));
		try {
			ipIdeaDAO.save(ipIdea);
			if (idea.getGroupIdList() != null && idea.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpIdeaGroup.class, idea.getGroupIdList().length);
				int i = 0;
				for (Long gId : idea.getGroupIdList()) {
					if (ipGroupDAO.findById(gId) != null) {
						IpIdeaGroup ipIdeaGroup = new IpIdeaGroup();
						ipIdeaGroup.setIgId(ids[i]);
						ipIdeaGroup.setIpIdea(ipIdea);
						ipIdeaGroup.setIpGroup(ipGroupDAO.findById(gId));
						ipIdeaGroup.setIgCrtdDt(new Date());
						ipIdeaGroupDAO.save(ipIdeaGroup);
						i++;
					}
				}
			}
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			logger.error(e, e);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@PUT
	@Path("/idea/modify")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateIdea(IdeaMessage idea) {
		IpIdea ipIdea = new IpIdea();
		ipIdea.setIdeaId(idea.getIdeaId());
		ipIdea.setIdeaBa(idea.getIdeaBa());
		ipIdea.setIdeaDate(idea.getCrtdDate());
		ipIdea.setIdeaDesc(idea.getIdeaDesc());
		ipIdea.setIdeaTag(idea.getIdeaTag());
		ipIdea.setIdeaTitle(idea.getIdeaTitle());
		if (idea.getSelCatId() != null && idea.getSelCatId().longValue() >= 0)
			ipIdea.setIpIdeaCat(ipIdeaCatDAO.findById(idea.getSelCatId().intValue()));
		if (idea.getSetStatusId() != null && idea.getSetStatusId().longValue() >= 0)
			ipIdea.setIpIdeaStatus(ipIdeaStatusDAO.findById(idea.getSetStatusId().intValue()));
		if (idea.getCrtdById() != null && idea.getCrtdById().longValue() >= 0)
			ipIdea.setIpUser(ipUserDAO.findById(idea.getCrtdById()));
		try {
			ipIdeaDAO.merge(ipIdea);
			ipIdeaGroupDAO.deleteByIdeaId(idea.getIdeaId());
			if (idea.getGroupIdList() != null && idea.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpIdeaGroup.class, idea.getGroupIdList().length);
				int i = 0;
				for (Long gId : idea.getGroupIdList()) {
					if (ipGroupDAO.findById(gId) != null) {
						IpIdeaGroup ipIdeaGroup = new IpIdeaGroup();
						ipIdeaGroup.setIgId(ids[i]);
						ipIdeaGroup.setIpIdea(ipIdea);
						ipIdeaGroup.setIpGroup(ipGroupDAO.findById(gId));
						ipIdeaGroup.setIgCrtdDt(new Date());
						ipIdeaGroupDAO.save(ipIdeaGroup);
						i++;
					}
				}
			}

			String subject = "";
			String body = "";
			boolean notification = false;
			IpUser user = (IpUser) ipUserDAO.findById(idea.getCrtdById());
			if (idea.getSetStatusId() != null && idea.getSetStatusId() == 3) {
				notification = true;
				subject = "Random Idea is under 'In Review' status";
				body = "Your Idea, '" + idea.getIdeaTitle() + ": " + idea.getIdeaDesc() + "' has gone under review";

			} else if (idea.getSetStatusId() != null && idea.getSetStatusId() == 4) {
				notification = true;
				subject = "Random Idea is Short-list status";
				body = "Your Idea, '" + idea.getIdeaTitle() + ": " + idea.getIdeaDesc() + "' has been shortlitsed";

			} else if (idea.getSetStatusId() != null && idea.getSetStatusId() == 5) {
				notification = true;
				subject = "Random Idea is Approved status";
				body = "Your Idea, '" + idea.getIdeaTitle() + ": " + idea.getIdeaDesc() + "' has been Approved";

			} else if (idea.getSetStatusId() != null && idea.getSetStatusId() == 6) {
				notification = true;
				subject = "Random Idea is Implemented status";
				body = user.getUserScreenName() + "from '" + user.getIpGroup().getGroupName() + "' is an innovation champion. " + "Your Idea, '" + idea.getIdeaTitle() + ": " + idea.getIdeaDesc() + "' has been rolled out.";

			} else if (idea.getSetStatusId() != null && idea.getSetStatusId() == 7) {
				notification = true;
				subject = "Random Idea is Parked status";
				body = "Your Idea, '" + idea.getIdeaTitle() + ": " + idea.getIdeaDesc() + "' has gone under Parked status";

			} else if (idea.getSetStatusId() != null && idea.getSetStatusId() == 8) {
				notification = true;
				subject = "Random Idea is Closed status";
				body = "Your Idea, '" + idea.getIdeaTitle() + ": " + idea.getIdeaDesc() + "' has gone under Closed status";

			}
			if (notification) {
				try {

					IpNotif ipNotif = new IpNotif();
					ipNotif.setNotifAttach(null);
					ipNotif.setNotifId(java.util.UUID.randomUUID().toString());
					ipNotif.setNotifStatus("n");
					ipNotif.setNotifSubject(subject);
					ipNotif.setNotifBody(body);
					ipNotif.setNotifCrtdDate(new Date());
					ipNotif.setNotifEntityId(null);
					ipNotif.setNotifEntityTblName(null);
					ipNotif.setNotifList(user.getUserEmail());
					ipNotifDAO.save(ipNotif);
				} catch (Exception e) {
					logger.error("Error while creating notification: " + e);
				}
			}

			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			logger.error(e, e);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	private IdeaMessage getIdeaMessage(IpIdea ipIdea) {
		IdeaMessage idea = new IdeaMessage();
		try {
			idea.setIdeaId(ipIdea.getIdeaId());
			idea.setIdeaBa(ipIdea.getIdeaBa());
			idea.setCrtdDate(ipIdea.getIdeaDate());
			idea.setIdeaDesc(ipIdea.getIdeaDesc());
			idea.setIdeaTag(ipIdea.getIdeaTag());
			idea.setIdeaTitle(ipIdea.getIdeaTitle());
			if (ipIdea.getIpIdeaCat() != null)
				idea.setSelCatId(ipIdea.getIpIdeaCat().getIcId().longValue());
			if (ipIdea.getIpIdeaStatus() != null) {
				idea.setSetStatusId(ipIdea.getIpIdeaStatus().getIsId().longValue());
				idea.setStatusName(ipIdea.getIpIdeaStatus().getIsDesc());
			}
			if (ipIdea.getIpUser() != null) {
				idea.setCrtdById(ipIdea.getIpUser().getUserId());
				idea.setCrtdByName(ipIdea.getIpUser().getUserScreenName());
			}
			idea.setRvIdCnt(ipIdea.getIdeaReviewCnt());
			List val = ipIdeaGroupDAO.fetchByIdeaId(ipIdea.getIdeaId());
			if (val != null) {
				Long[] grps = new Long[val.size()];
				int i = 0;
				for (Object obj : val) {
					IpIdeaGroup group = (IpIdeaGroup) obj;
					if (group != null && group.getIpGroup() != null) {
						grps[i] = group.getIpGroup().getGroupId();
						i++;
					}
				}
				idea.setGroupIdList(grps);
			}
			IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipIdea.getIdeaId(), "ip_idea");
			if (ipBlob != null) {
				idea.setImgAvail(true);
				idea.setFileName(ipBlob.getBlobName());
				idea.setBlobUrl("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/fds?blobId=" + ipBlob.getBlobId());
			} else
				idea.setImgAvail(false);
			IpBlob blob = ipBlobDAO.getBlobByEntity(ipIdea.getIpUser().getUserId(), "ip_user");
			if (blob != null) {
				idea.setCrtByImgAvail(true);
				idea.setCrtByImgPath("ip_user/" + ipIdea.getIpUser().getUserId() + "/" + blob.getBlobName());
			} else
				idea.setCrtByImgAvail(false);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return idea;
	}

	@GET
	@Path("/idea/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaMessage> List<T> listIdea() {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findAll();
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = getIdeaMessage(ipIdea);
				ret.add((T) idea);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/list/user/access/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaMessage> List<T> listIdeaByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findByUserId(id);
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				if (ipIdea.getIpUser().getUserId().longValue() != id.longValue() && ipIdea.getIpIdeaStatus().getIsId().intValue() < 2)
					continue;
				IdeaMessage idea = getIdeaMessage(ipIdea);
				ret.add((T) idea);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/list/user/created/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaMessage> List<T> listIdeaCreatedByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findCreatedByUserId(id);
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = getIdeaMessage(ipIdea);
				ret.add((T) idea);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/list/status/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaMessage> List<T> listIdeaByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findByStatusId(id);
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = getIdeaMessage(ipIdea);
				ret.add((T) idea);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/list/status/{sid}/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaMessage> List<T> listIdeaByStatusUser(@PathParam("sid") Integer sid, @PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findByStatusIdUserId(sid, id);
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = getIdeaMessage(ipIdea);
				ret.add((T) idea);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/list/reviewStatus/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaMessage> List<T> listReviewIdeasByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findReviewIdeasByUserId(id);
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = getIdeaMessage(ipIdea);
				ret.add((T) idea);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/idea/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public IdeaMessage getIdea(@PathParam("id") Long id) {
		IdeaMessage idea = new IdeaMessage();
		try {
			IpIdea ipIdea = ipIdeaDAO.findById(id);
			idea = getIdeaMessage(ipIdea);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return idea;
	}

	@GET
	@Path("/idea/rev/user/{id}/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateReviewer(@PathParam("id") Long id, @PathParam("uid") Long uid) {
		ResponseMessage message = new ResponseMessage();
		try {
			ipReviewDAO.updateReviewer(id, "ip_idea", uid);
		} catch (Exception e) {
			message.setStatusCode(-999);
			message.setStatusDesc("Failure :: " + e.getMessage());
			return message;
		}
		message.setStatusCode(200);
		message.setStatusDesc("Success");
		return message;
	}

	public IpIdeaDAO getIpIdeaDAO() {
		return ipIdeaDAO;
	}

	public void setIpIdeaDAO(IpIdeaDAO ipIdeaDAO) {
		this.ipIdeaDAO = ipIdeaDAO;
	}

	public IpIdeaCatDAO getIpIdeaCatDAO() {
		return ipIdeaCatDAO;
	}

	public void setIpIdeaCatDAO(IpIdeaCatDAO ipIdeaCatDAO) {
		this.ipIdeaCatDAO = ipIdeaCatDAO;
	}

	public IpIdeaStatusDAO getIpIdeaStatusDAO() {
		return ipIdeaStatusDAO;
	}

	public void setIpIdeaStatusDAO(IpIdeaStatusDAO ipIdeaStatusDAO) {
		this.ipIdeaStatusDAO = ipIdeaStatusDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpIdeaGroupDAO getIpIdeaGroupDAO() {
		return ipIdeaGroupDAO;
	}

	public void setIpIdeaGroupDAO(IpIdeaGroupDAO ipIdeaGroupDAO) {
		this.ipIdeaGroupDAO = ipIdeaGroupDAO;
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

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

	public IpReviewDAO getIpReviewDAO() {
		return ipReviewDAO;
	}

	public void setIpReviewDAO(IpReviewDAO ipReviewDAO) {
		this.ipReviewDAO = ipReviewDAO;
	}

	public IpNotifDAO getIpNotifDAO() {
		return ipNotifDAO;
	}

	public void setIpNotifDAO(IpNotifDAO ipNotifDAO) {
		this.ipNotifDAO = ipNotifDAO;
	}
}
