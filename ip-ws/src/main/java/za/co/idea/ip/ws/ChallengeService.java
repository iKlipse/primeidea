package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.bean.IpChallenge;
import za.co.idea.ip.orm.bean.IpChallengeCat;
import za.co.idea.ip.orm.bean.IpChallengeGroup;
import za.co.idea.ip.orm.bean.IpChallengeStatus;
import za.co.idea.ip.orm.bean.IpNotif;
import za.co.idea.ip.orm.bean.IpUser;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpChallengeCatDAO;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpChallengeGroupDAO;
import za.co.idea.ip.orm.dao.IpChallengeStatusDAO;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpNotifDAO;
import za.co.idea.ip.orm.dao.IpReviewDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/cs")
public class ChallengeService {
	private static final Logger logger = Logger.getLogger(ChallengeService.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");
	private IpChallengeDAO ipChallengeDAO;
	private IpChallengeCatDAO ipChallengeCatDAO;
	private IpChallengeStatusDAO ipChallengeStatusDAO;
	private IpUserDAO ipUserDAO;
	private IpGroupDAO ipGroupDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpChallengeGroupDAO ipChallengeGroupDAO;
	private IpBlobDAO ipBlobDAO;
	private IpReviewDAO ipReviewDAO;
	private IpNotifDAO ipNotifDAO;

	@POST
	@Path("/challenge/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createChallenge(ChallengeMessage challenge) {
		IpChallenge ipChallenge = new IpChallenge();
		try {
			ipChallenge.setChalCrtdDt(challenge.getCrtdDt());
			ipChallenge.setChalDesc(challenge.getDesc());
			ipChallenge.setChalExpiryDt(challenge.getExprDt());
			ipChallenge.setChalHoverTxt(challenge.getHoverText());
			ipChallenge.setChalId(challenge.getId());
			ipChallenge.setChalLaunchDt(challenge.getLaunchDt());
			ipChallenge.setChalTags(challenge.getTag());
			ipChallenge.setChalTitle(challenge.getTitle());
			ipChallenge.setIpChallengeCat(ipChallengeCatDAO.findById(challenge.getCatId()));
			ipChallenge.setIpChallengeStatus(ipChallengeStatusDAO.findById(challenge.getStatusId()));
			ipChallenge.setIpUser(ipUserDAO.findById(challenge.getCrtdById()));
			ipChallenge.setChalReviewCnt(challenge.getRvIdCnt());
			ipChallengeDAO.save(ipChallenge);
			if (challenge.getGroupIdList() != null && challenge.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpChallengeGroup.class, challenge.getGroupIdList().length);
				int i = 0;
				for (Long gId : challenge.getGroupIdList()) {
					IpChallengeGroup ipChallengeGroup = new IpChallengeGroup();
					ipChallengeGroup.setCgId(ids[i]);
					ipChallengeGroup.setIpChallenge(ipChallenge);
					ipChallengeGroup.setIpGroup(ipGroupDAO.findById(gId));
					ipChallengeGroup.setCgCrtdDt(new Date());
					ipChallengeGroupDAO.save(ipChallengeGroup);
					i++;
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
	@Path("/challenge/modify")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateChallenge(ChallengeMessage challenge) {
		IpChallenge ipChallenge = new IpChallenge();
		try {
			ipChallenge.setChalCrtdDt(challenge.getCrtdDt());
			ipChallenge.setChalDesc(challenge.getDesc());
			ipChallenge.setChalExpiryDt(challenge.getExprDt());
			ipChallenge.setChalHoverTxt(challenge.getHoverText());
			ipChallenge.setChalId(challenge.getId());
			ipChallenge.setChalLaunchDt(challenge.getLaunchDt());
			ipChallenge.setChalTags(challenge.getTag());
			ipChallenge.setChalTitle(challenge.getTitle());
			ipChallenge.setIpChallengeCat(ipChallengeCatDAO.findById(challenge.getCatId()));
			ipChallenge.setIpChallengeStatus(ipChallengeStatusDAO.findById(challenge.getStatusId()));
			ipChallenge.setIpUser(ipUserDAO.findById(challenge.getCrtdById()));
			ipChallenge.setChalReviewCnt(challenge.getRvIdCnt());
			ipChallengeDAO.merge(ipChallenge);
			ipChallengeGroupDAO.deleteByChallengeId(challenge.getId());
			if (challenge.getGroupIdList() != null && challenge.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpChallengeGroup.class, challenge.getGroupIdList().length);
				int i = 0;
				for (Long gId : challenge.getGroupIdList()) {
					IpChallengeGroup ipChallengeGroup = new IpChallengeGroup();
					ipChallengeGroup.setCgId(ids[i]);
					ipChallengeGroup.setIpChallenge(ipChallenge);
					ipChallengeGroup.setIpGroup(ipGroupDAO.findById(gId));
					ipChallengeGroup.setCgCrtdDt(new Date());
					ipChallengeGroupDAO.save(ipChallengeGroup);
					i++;
				}
			}

			String subject = "";
			String body = "";
			boolean notification = false;
			IpUser user = (IpUser) ipUserDAO.findById(challenge.getCrtdById());
			if (challenge.getStatusId() != null && challenge.getStatusId() == 2) {
				notification = true;
				subject = "Challenge is under Review status";
				body = "Your challenge, '" + challenge.getTitle() + ": " + challenge.getDesc() + "' has gone under review";

			} else if (challenge.getStatusId() != null && challenge.getStatusId() == 3) {
				notification = true;
				subject = "Challenge is Approved status";
				body = "Your challenge, '" + challenge.getTitle() + ": " + challenge.getDesc() + "' has been approved";

			} else if (challenge.getStatusId() != null && challenge.getStatusId() == 4) {
				notification = true;
				subject = "Challenge is Publish status";
				body = "Your challenge, '" + challenge.getTitle() + ": " + challenge.getDesc() + "' has been published";

			} else if (challenge.getStatusId() != null && challenge.getStatusId() == 5) {
				notification = true;
				subject = "Challenge is Removed status";
				body = "Your challenge, '" + challenge.getTitle() + ": " + challenge.getDesc() + "' has been removed";

			} else if (challenge.getStatusId() != null && challenge.getStatusId() == 6) {
				notification = true;
				subject = "Challenge is Rejected status";
				body = "Your challenge, '" + challenge.getTitle() + ": " + challenge.getDesc() + "' has been rejected";

			} else if (challenge.getStatusId() != null && challenge.getStatusId() == 7) {
				notification = true;
				subject = "Challenge is Expired status";
				body = "Your challenge, '" + challenge.getTitle() + ": " + challenge.getDesc() + "' has been expired";

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

	private ChallengeMessage getChallengeMessage(IpChallenge ipChallenge) {
		ChallengeMessage challenge = new ChallengeMessage();
		try {
			challenge.setId(ipChallenge.getChalId());
			challenge.setCatId(ipChallenge.getIpChallengeCat().getCcId());
			challenge.setCrtdById(ipChallenge.getIpUser().getUserId());
			challenge.setCrtdByName(ipChallenge.getIpUser().getUserFName() + " " + ipChallenge.getIpUser().getUserLName());
			challenge.setCrtdDt(ipChallenge.getChalCrtdDt());
			challenge.setDesc(ipChallenge.getChalDesc());
			challenge.setExprDt(ipChallenge.getChalExpiryDt());
			challenge.setHoverText(ipChallenge.getChalHoverTxt());
			challenge.setLaunchDt(ipChallenge.getChalLaunchDt());
			challenge.setStatusId(ipChallenge.getIpChallengeStatus().getCsId());
			challenge.setTag(ipChallenge.getChalTags());
			challenge.setTitle(ipChallenge.getChalTitle());
			challenge.setCatName(ipChallenge.getIpChallengeCat().getCcDesc());
			challenge.setStatusName(ipChallenge.getIpChallengeStatus().getCsDesc());
			challenge.setRvIdCnt(ipChallenge.getChalReviewCnt());
			List val = ipChallengeGroupDAO.fetchByChallengeId(ipChallenge.getChalId());
			if (val != null) {
				Long[] grps = new Long[val.size()];
				int i = 0;
				for (Object obj : val) {
					IpChallengeGroup group = (IpChallengeGroup) obj;
					if (group != null && group.getIpGroup() != null) {
						grps[i] = ((IpChallengeGroup) obj).getIpGroup().getGroupId();
						i++;
					}
				}
				challenge.setGroupIdList(grps);
			}
			IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipChallenge.getChalId(), "ip_challenge");
			if (ipBlob != null) {
				challenge.setFileName(ipBlob.getBlobName());
				challenge.setImgAvail(true);
				challenge.setBlobUrl("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/fds?blobId=" + ipBlob.getBlobId());
			} else
				challenge.setImgAvail(false);
			IpBlob blob = ipBlobDAO.getBlobByEntity(ipChallenge.getIpUser().getUserId(), "ip_user");
			if (blob != null) {
				challenge.setCrtByImgAvail(true);
				challenge.setCrtByImgPath("ip_user/" + ipChallenge.getIpUser().getUserId() + "/" + blob.getBlobName());
			} else
				challenge.setCrtByImgAvail(false);
		} catch (Exception e) {
			logger.error(e, e);
		}

		return challenge;
	}

	@GET
	@Path("/challenge/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends ChallengeMessage> List<T> listChallenge() {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findAll();
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = getChallengeMessage(ipChallenge);
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ChallengeMessage getById(@PathParam("id") Long id) {
		ChallengeMessage challenge = new ChallengeMessage();
		try {
			IpChallenge ipChallenge = ipChallengeDAO.findById(id);
			challenge = getChallengeMessage(ipChallenge);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return challenge;
	}

	@GET
	@Path("/challenge/list/user/access/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends ChallengeMessage> List<T> listChallengeByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findByUserId(id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = getChallengeMessage(ipChallenge);
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/list/user/created/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends ChallengeMessage> List<T> listChallengeCreatedByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findCreatedByUserId(id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = getChallengeMessage(ipChallenge);
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/list/status/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends ChallengeMessage> List<T> listChallengeByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findByStatusId(id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = getChallengeMessage(ipChallenge);
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/list/status/{sid}/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends ChallengeMessage> List<T> listChallengeByStatusIdUserId(@PathParam("sid") Integer sid, @PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findByStatusIdUserId(sid, id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = getChallengeMessage(ipChallenge);
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/list/reviewStatus/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends ChallengeMessage> List<T> listReviewChallengesByUserId(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findReviewChalsByUserId(id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = getChallengeMessage(ipChallenge);
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/cat/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listChallengeCat() {
		List<T> ret = new ArrayList<T>();
		try {
			List challengeCats = ipChallengeCatDAO.findAll();
			for (Object object : challengeCats) {
				IpChallengeCat cat = (IpChallengeCat) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(cat.getCcId());
				message.setDesc(cat.getCcDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/status/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listChallengeStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List challengeStatuses = ipChallengeStatusDAO.findAll();
			for (Object object : challengeStatuses) {
				IpChallengeStatus status = (IpChallengeStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getCsId());
				message.setDesc(status.getCsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/status/list/{curr}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listNextChallengeStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List challengeStatuses = ipChallengeStatusDAO.findNext(curr);
			for (Object object : challengeStatuses) {
				IpChallengeStatus status = (IpChallengeStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getCsId());
				message.setDesc(status.getCsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/challenge/cat/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getChallengeCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpChallengeCat cat = ipChallengeCatDAO.findById(id);
			message.setId(cat.getCcId());
			message.setDesc(cat.getCcDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/challenge/status/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getChallengeStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpChallengeStatus status = ipChallengeStatusDAO.findById(id);
			message.setId(status.getCsId());
			message.setDesc(status.getCsDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/challenge/rev/user/{id}/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateReviewer(@PathParam("id") Long id, @PathParam("uid") Long uid) {
		ResponseMessage message = new ResponseMessage();
		try {
			ipReviewDAO.updateReviewer(id, "ip_challenge", uid);
		} catch (Exception e) {
			message.setStatusCode(-999);
			message.setStatusDesc("Failure :: " + e.getMessage());
			return message;
		}
		message.setStatusCode(200);
		message.setStatusDesc("Success");
		return message;
	}

	@GET
	@Path("/challenge/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ChallengeMessage getChallenge(@PathParam("id") Long id) {
		ChallengeMessage challenge = new ChallengeMessage();
		try {
			IpChallenge ipChallenge = ipChallengeDAO.findById(id);
			challenge = getChallengeMessage(ipChallenge);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return challenge;
	}

	@GET
	@Path("/challenge/check/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Boolean checkTitle(@PathParam("title") String title) {
		try {
			List solByTitle = ipChallengeDAO.findByChalTitle(title);
			Boolean ret = (solByTitle != null && solByTitle.size() > 0);
			return ret;
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}

	public IpChallengeDAO getIpChallengeDAO() {
		return ipChallengeDAO;
	}

	public void setIpChallengeDAO(IpChallengeDAO ipChallengeDAO) {
		this.ipChallengeDAO = ipChallengeDAO;
	}

	public IpChallengeCatDAO getIpChallengeCatDAO() {
		return ipChallengeCatDAO;
	}

	public void setIpChallengeCatDAO(IpChallengeCatDAO ipChallengeCatDAO) {
		this.ipChallengeCatDAO = ipChallengeCatDAO;
	}

	public IpChallengeStatusDAO getIpChallengeStatusDAO() {
		return ipChallengeStatusDAO;
	}

	public void setIpChallengeStatusDAO(IpChallengeStatusDAO ipChallengeStatusDAO) {
		this.ipChallengeStatusDAO = ipChallengeStatusDAO;
	}

	public IpUserDAO getIpUserByChalCrtdByDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpGroupDAO getIpGroupDAO() {
		return ipGroupDAO;
	}

	public IpNativeSQLDAO getIpNativeSQLDAO() {
		return ipNativeSQLDAO;
	}

	public void setIpGroupDAO(IpGroupDAO ipGroupDAO) {
		this.ipGroupDAO = ipGroupDAO;
	}

	public void setIpNativeSQLDAO(IpNativeSQLDAO ipNativeSQLDAO) {
		this.ipNativeSQLDAO = ipNativeSQLDAO;
	}

	public IpChallengeGroupDAO getIpChallengeGroupDAO() {
		return ipChallengeGroupDAO;
	}

	public void setIpChallengeGroupDAO(IpChallengeGroupDAO ipChallengeGroupDAO) {
		this.ipChallengeGroupDAO = ipChallengeGroupDAO;
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

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}
}
