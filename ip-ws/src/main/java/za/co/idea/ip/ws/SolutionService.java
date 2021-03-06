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
import za.co.idea.ip.orm.bean.IpChallenge;
import za.co.idea.ip.orm.bean.IpNotif;
import za.co.idea.ip.orm.bean.IpSolution;
import za.co.idea.ip.orm.bean.IpSolutionCat;
import za.co.idea.ip.orm.bean.IpSolutionStatus;
import za.co.idea.ip.orm.bean.IpUser;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpNotifDAO;
import za.co.idea.ip.orm.dao.IpReviewDAO;
import za.co.idea.ip.orm.dao.IpSolutionCatDAO;
import za.co.idea.ip.orm.dao.IpSolutionDAO;
import za.co.idea.ip.orm.dao.IpSolutionStatusDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/ss")
public class SolutionService {
	private static final Logger logger = Logger.getLogger(SolutionService.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");
	private IpSolutionDAO ipSolutionDAO;
	private IpSolutionCatDAO ipSolutionCatDAO;
	private IpSolutionStatusDAO ipSolutionStatusDAO;
	private IpUserDAO ipUserDAO;
	private IpChallengeDAO ipChallengeDAO;
	private IpBlobDAO ipBlobDAO;
	private IpReviewDAO ipReviewDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpNotifDAO ipNotifDAO;

	@GET
	@Path("/solution/check/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Boolean checkTitle(@PathParam("title") String title) {
		try {
			List solByTitle = ipSolutionDAO.findBySolTitle(title);
			Boolean ret = (solByTitle != null && solByTitle.size() > 0);
			return ret;
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}

	@POST
	@Path("/solution/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createSolution(SolutionMessage solution) {
		IpSolution ipSolution = new IpSolution();
		try {
			IpChallenge ipChallenge = ipChallengeDAO.findById(solution.getChalId());
			ipSolution.setIpChallenge(ipChallenge);
			ipSolution.setIpSolutionCat(ipSolutionCatDAO.findById(ipChallenge.getIpChallengeCat().getCcId()));
			ipSolution.setIpSolutionStatus(ipSolutionStatusDAO.findById(solution.getStatusId()));
			IpUser ipUser = ipUserDAO.findById(solution.getCrtdById());
			ipSolution.setIpUser(ipUser);
			ipSolution.setSolCrtdDt(solution.getCrtdDt());
			ipSolution.setSolDesc(solution.getDesc());
			ipSolution.setSolId(solution.getId());
			ipSolution.setSolTags(solution.getTags());
			ipSolution.setSolTitle(solution.getTitle());
			ipSolution.setSolReviewCnt(solution.getRvIdCnt());
			ipSolutionDAO.save(ipSolution);
			try {
				IpNotif ipNotif = new IpNotif();
				ipNotif.setNotifAttach(null);
				ipNotif.setNotifId(java.util.UUID.randomUUID().toString());
				ipNotif.setNotifStatus("n");
				ipNotif.setNotifSubject("New Solution submitted");
				ipNotif.setNotifBody(ipSolution.getIpUser().getUserScreenName() + " has submitted solution to your challenge " + ipChallenge.getChalTitle());
				ipNotif.setNotifCrtdDate(new Date());
				ipNotif.setNotifEntityId(null);
				ipNotif.setNotifEntityTblName(null);
				ipNotif.setNotifList(ipUser.getUserEmail());
				ipNotifDAO.save(ipNotif);
			} catch (Exception e) {
				logger.error("Error while creating notification: " + e);
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
	@Path("/solution/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateSolution(SolutionMessage solution) {
		IpSolution ipSolution = new IpSolution();
		try {
			ipSolution.setIpChallenge(ipChallengeDAO.findById(solution.getChalId()));
			ipSolution.setIpSolutionCat(ipSolutionCatDAO.findById(solution.getCatId()));
			ipSolution.setIpSolutionStatus(ipSolutionStatusDAO.findById(solution.getStatusId()));
			ipSolution.setIpUser(ipUserDAO.findById(solution.getCrtdById()));
			ipSolution.setSolCrtdDt(solution.getCrtdDt());
			ipSolution.setSolDesc(solution.getDesc());
			ipSolution.setSolId(solution.getId());
			ipSolution.setSolTags(solution.getTags());
			ipSolution.setSolTitle(solution.getTitle());
			ipSolution.setSolReviewCnt(solution.getRvIdCnt());
			ipSolutionDAO.merge(ipSolution);
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

	private SolutionMessage getSolutionMessage(IpSolution ipSolution) {
		SolutionMessage solution = new SolutionMessage();
		try {
			solution.setChalId(ipSolution.getIpChallenge().getChalId());
			solution.setCatId(ipSolution.getIpSolutionCat().getScId());
			solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
			solution.setCrtdById(ipSolution.getIpUser().getUserId());
			solution.setCrtByName(ipSolution.getIpUser().getUserScreenName());
			solution.setCrtdDt(ipSolution.getSolCrtdDt());
			solution.setDesc(ipSolution.getSolDesc());
			solution.setId(ipSolution.getSolId());
			solution.setTags(ipSolution.getSolTags());
			solution.setTitle(ipSolution.getSolTitle());
			solution.setCatName(ipSolution.getIpSolutionCat().getScDesc());
			solution.setStatusName(ipSolution.getIpSolutionStatus().getSsDesc());
			solution.setRvIdCnt(ipSolution.getSolReviewCnt());
			IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
			if (ipBlob != null) {
				solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
				solution.setSolImgAvl(true);
				solution.setFileName(ipBlob.getBlobName());
				solution.setContentType(ipBlob.getBlobContentType());
				solution.setBlobUrl("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/fds?blobId=" + ipBlob.getBlobId());
			} else {
				solution.setSolImgAvl(false);
			}
			IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
			if (blob != null) {
				solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
				solution.setCrtByImgAvail(true);
			} else
				solution.setCrtByImgAvail(false);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return solution;
	}

	@GET
	@Path("/solution/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listSolution() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findAll();
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/list/user/access/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listSolutionByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		logger.debug("Control handled in listSolutionByUser() of /solution/list/user/access/{id} service ");
		try {
			logger.info("User Id :" + id);
			List solutions = ipSolutionDAO.findByUserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error("Error in service : " + e.getMessage());
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/list/user/created/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listSolutionCreatedByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findCreatedByUserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/list/status/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listSolutionByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByStatusId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/list/status/{sid}/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listSolutionByStatusIdUserId(@PathParam("sid") Integer sid, @PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByStatusIdUserId(sid, id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/list/chal/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listSolutionByChal(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByChalId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/list/reviewStatus/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends SolutionMessage> List<T> listReviewSolutionsByUserId(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findReviewSolByserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = getSolutionMessage(ipSolution);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/cat/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listSolutionCat() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutionCats = ipSolutionCatDAO.findAll();
			for (Object object : solutionCats) {
				IpSolutionCat cat = (IpSolutionCat) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(cat.getScId());
				message.setDesc(cat.getScDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/status/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listSolutionStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutionStatuses = ipSolutionStatusDAO.findAll();
			for (Object object : solutionStatuses) {
				IpSolutionStatus status = (IpSolutionStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getSsId());
				message.setDesc(status.getSsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/status/list/{curr}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listNextSolutionStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutionStatuses = ipSolutionStatusDAO.findNext(curr);
			for (Object object : solutionStatuses) {
				IpSolutionStatus status = (IpSolutionStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getSsId());
				message.setDesc(status.getSsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/solution/cat/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getSolutionCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpSolutionCat cat = ipSolutionCatDAO.findById(id);
			message.setId(cat.getScId());
			message.setDesc(cat.getScDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/solution/rev/user/{id}/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateReviewer(@PathParam("id") Long id, @PathParam("uid") Long uid) {
		ResponseMessage message = new ResponseMessage();
		try {
			ipReviewDAO.updateReviewer(id, "ip_solution", uid);
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
	@Path("/solution/status/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getSolutionStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpSolutionStatus status = ipSolutionStatusDAO.findById(id);
			message.setId(status.getSsId());
			message.setDesc(status.getSsDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/solution/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public SolutionMessage getSolution(@PathParam("id") Long id) {
		SolutionMessage solution = new SolutionMessage();
		try {
			IpSolution ipSolution = ipSolutionDAO.findById(id);
			solution = getSolutionMessage(ipSolution);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return solution;
	}

	public IpSolutionDAO getIpSolutionDAO() {
		return ipSolutionDAO;
	}

	public void setIpSolutionDAO(IpSolutionDAO ipSolutionDAO) {
		this.ipSolutionDAO = ipSolutionDAO;
	}

	public IpSolutionCatDAO getIpSolutionCatDAO() {
		return ipSolutionCatDAO;
	}

	public void setIpSolutionCatDAO(IpSolutionCatDAO ipSolutionCatDAO) {
		this.ipSolutionCatDAO = ipSolutionCatDAO;
	}

	public IpSolutionStatusDAO getIpSolutionStatusDAO() {
		return ipSolutionStatusDAO;
	}

	public void setIpSolutionStatusDAO(IpSolutionStatusDAO ipSolutionStatusDAO) {
		this.ipSolutionStatusDAO = ipSolutionStatusDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpChallengeDAO getIpChallengeDAO() {
		return ipChallengeDAO;
	}

	public void setIpChallengeDAO(IpChallengeDAO ipChallengeDAO) {
		this.ipChallengeDAO = ipChallengeDAO;
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

	public IpNativeSQLDAO getIpNativeSQLDAO() {
		return ipNativeSQLDAO;
	}

	public void setIpNativeSQLDAO(IpNativeSQLDAO ipNativeSQLDAO) {
		this.ipNativeSQLDAO = ipNativeSQLDAO;
	}

	public IpNotifDAO getIpNotifDAO() {
		return ipNotifDAO;
	}

	public void setIpNotifDAO(IpNotifDAO ipNotifDAO) {
		this.ipNotifDAO = ipNotifDAO;
	}
}
