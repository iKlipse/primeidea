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

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.bean.IpChallenge;
import za.co.idea.ip.orm.bean.IpSolution;
import za.co.idea.ip.orm.bean.IpSolutionCat;
import za.co.idea.ip.orm.bean.IpSolutionStatus;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
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
	private IpSolutionDAO ipSolutionDAO;
	private IpSolutionCatDAO ipSolutionCatDAO;
	private IpSolutionStatusDAO ipSolutionStatusDAO;
	private IpUserDAO ipUserDAO;
	private IpChallengeDAO ipChallengeDAO;
	private IpBlobDAO ipBlobDAO;
	private static final Logger logger = Logger.getLogger(SolutionService.class);

	@GET
	@Path("/solution/check/title/{title}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean checkTitle(@PathParam("title") String title) {
		try {
			List solByTitle = ipSolutionDAO.findBySolTitle(title);
			Boolean ret = (solByTitle != null && solByTitle.size() > 0);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@POST
	@Path("/solution/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage createSolution(SolutionMessage solution) {
		IpSolution ipSolution = new IpSolution();
		try {
			IpChallenge ipChallenge = ipChallengeDAO.findById(solution.getChalId());
			ipSolution.setIpChallenge(ipChallengeDAO.findById(solution.getChalId()));
			ipSolution.setIpSolutionCat(ipSolutionCatDAO.findById(ipChallenge.getIpChallengeCat().getCcId()));
			ipSolution.setIpSolutionStatus(ipSolutionStatusDAO.findById(solution.getStatusId()));
			ipSolution.setIpUser(ipUserDAO.findById(solution.getCrtdById()));
			ipSolution.setSolCrtdDt(solution.getCrtdDt());
			ipSolution.setSolDesc(solution.getDesc());
			ipSolution.setSolId(solution.getId());
			ipSolution.setSolTags(solution.getTags());
			ipSolution.setSolTitle(solution.getTitle());
			ipSolutionDAO.save(ipSolution);
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
	@Path("/solution/modify")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
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
			ipSolutionDAO.merge(ipSolution);
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
	@Path("/solution/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listSolution() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findAll();
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipSolution.getIpUser().getUserFName() + " " + ipSolution.getIpUser().getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
				if (blob != null) {
					solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
					solution.setCrtByImgAvail(true);
				} else
					solution.setCrtByImgAvail(false);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/user/access/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listSolutionByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		logger.debug("Control handled in listSolutionByUser() of /solution/list/user/access/{id} service ");
		try {
			logger.info("User Id :" + id);
			List solutions = ipSolutionDAO.findByUserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipSolution.getIpUser().getUserFName() + " " + ipSolution.getIpUser().getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
				if (blob != null) {
					solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
					solution.setCrtByImgAvail(true);
				} else
					solution.setCrtByImgAvail(false);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			logger.error("Error in service : " + e.getMessage());
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/user/created/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listSolutionCreatedByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findCreatedByUserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipSolution.getIpUser().getUserFName() + " " + ipSolution.getIpUser().getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
				if (blob != null) {
					solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
					solution.setCrtByImgAvail(true);
				} else
					solution.setCrtByImgAvail(false);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/status/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listSolutionByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByStatusId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipSolution.getIpUser().getUserFName() + " " + ipSolution.getIpUser().getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
				if (blob != null) {
					solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
					solution.setCrtByImgAvail(true);
				} else
					solution.setCrtByImgAvail(false);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/status/{sid}/user/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listSolutionByStatusIdUserId(@PathParam("sid") Integer sid, @PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByStatusIdUserId(sid, id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipSolution.getIpUser().getUserFName() + " " + ipSolution.getIpUser().getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
				if (blob != null) {
					solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
					solution.setCrtByImgAvail(true);
				} else
					solution.setCrtByImgAvail(false);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/chal/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listSolutionByChal(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByChalId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipSolution.getIpUser().getUserFName() + " " + ipSolution.getIpUser().getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
				if (blob != null) {
					solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
					solution.setCrtByImgAvail(true);
				} else
					solution.setCrtByImgAvail(false);
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/reviewStatus/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends SolutionMessage> List<T> listReviewSolutionsByUserId(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findReviewSolByserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtByName(ipUserDAO.findById(ipSolution.getIpUser().getUserId()).getUserFName() + " " + ipUserDAO.findById(ipSolution.getIpUser().getUserId()).getUserLName());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipSolution.getSolId(), "ip_solution");
				if (ipBlob != null) {
					solution.setSolImg("ip_solution/" + ipSolution.getSolId() + "/" + ipBlob.getBlobName());
					solution.setSolImgAvl(true);
					solution.setFileName(ipBlob.getBlobName());
					solution.setContentType(ipBlob.getBlobContentType());
				} else {
					solution.setSolImgAvl(false);
				}
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/cat/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/status/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/status/list/{curr}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/cat/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public MetaDataMessage getSolutionCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpSolutionCat cat = ipSolutionCatDAO.findById(id);
			message.setId(cat.getScId());
			message.setDesc(cat.getScDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/solution/status/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public MetaDataMessage getSolutionStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpSolutionStatus status = ipSolutionStatusDAO.findById(id);
			message.setId(status.getSsId());
			message.setDesc(status.getSsDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/solution/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public SolutionMessage getSolution(@PathParam("id") Long id) {
		SolutionMessage solution = new SolutionMessage();
		try {
			IpSolution ipSolution = ipSolutionDAO.findById(id);
			solution.setChalId(ipSolution.getIpChallenge().getChalId());
			solution.setCatId(ipSolution.getIpSolutionCat().getScId());
			solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
			solution.setCrtdById(ipSolution.getIpUser().getUserId());
			solution.setCrtdDt(ipSolution.getSolCrtdDt());
			solution.setDesc(ipSolution.getSolDesc());
			solution.setId(ipSolution.getSolId());
			solution.setTags(ipSolution.getSolTags());
			solution.setTitle(ipSolution.getSolTitle());
			IpBlob blob = ipBlobDAO.getBlobByEntity(ipSolution.getIpUser().getUserId(), "ip_user");
			if (blob != null) {
				solution.setCrtByImgPath("ip_user/" + ipSolution.getIpUser().getUserId() + "/" + blob.getBlobName());
				solution.setCrtByImgAvail(true);
			} else
				solution.setCrtByImgAvail(false);
		} catch (Exception e) {
			e.printStackTrace();
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
}
