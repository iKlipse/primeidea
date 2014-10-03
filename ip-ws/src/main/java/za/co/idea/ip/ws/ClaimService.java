package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import za.co.idea.ip.orm.bean.IpClaim;
import za.co.idea.ip.orm.bean.IpClaimStatus;
import za.co.idea.ip.orm.bean.IpNotif;
import za.co.idea.ip.orm.bean.IpUser;
import za.co.idea.ip.orm.dao.IpClaimDAO;
import za.co.idea.ip.orm.dao.IpClaimStatusDAO;
import za.co.idea.ip.orm.dao.IpNotifDAO;
import za.co.idea.ip.orm.dao.IpRewardsDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/cls")
public class ClaimService {
	private static final Logger logger = Logger.getLogger(ClaimService.class);
	private IpUserDAO ipUserDAO;
	private IpClaimDAO ipClaimDAO;
	private IpClaimStatusDAO ipClaimStatusDAO;
	private IpRewardsDAO ipRewardsDAO;
	private IpNotifDAO ipNotifDAO;

	@POST
	@Path("/claim/add")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public ResponseMessage createClaim(ClaimMessage claim) {
		try {
			IpClaim ipClaim = new IpClaim();
			ipClaim.setClaimCrtdDt(claim.getClaimCrtdDt());
			ipClaim.setClaimDesc(claim.getClaimDesc());
			ipClaim.setClaimId(claim.getClaimId());
			ipClaim.setIpClaimStatus(ipClaimStatusDAO.findById(claim.getcStatusId()));
			ipClaim.setIpRewards(ipRewardsDAO.findById(claim.getRewardsId()));
			ipClaim.setIpUser(ipUserDAO.findById(claim.getUserId()));
			ipClaimDAO.save(ipClaim);
			try {
				IpUser user = (IpUser) ipUserDAO.findById(claim.getUserId());
				IpNotif ipNotif = new IpNotif();
				ipNotif.setNotifAttach(null);
				ipNotif.setNotifId(java.util.UUID.randomUUID().toString());
				ipNotif.setNotifStatus("n");
				ipNotif.setNotifSubject("Claim Submitted");
				ipNotif.setNotifBody("You have claimed for reward: " + ipRewardsDAO.findById(claim.getRewardsId()).getRwTitle());
				ipNotif.setNotifCrtdDate(new Date());
				ipNotif.setNotifEntityId(null);
				ipNotif.setNotifEntityTblName(null);
				ipNotif.setNotifList(user.getUserEmail());
				ipNotifDAO.save(ipNotif);
			} catch (Exception e) {
				logger.error("Error while creating : " + e);
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
	@Path("/claim/modify")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public ResponseMessage updateClaim(ClaimMessage claim) {
		try {
			IpClaim ipClaim = new IpClaim();
			ipClaim.setClaimCrtdDt(claim.getClaimCrtdDt());
			ipClaim.setClaimDesc(claim.getClaimDesc());
			ipClaim.setClaimId(claim.getClaimId());
			ipClaim.setIpClaimStatus(ipClaimStatusDAO.findById(claim.getcStatusId()));
			ipClaim.setIpRewards(ipRewardsDAO.findById(claim.getRewardsId()));
			ipClaim.setIpUser(ipUserDAO.findById(claim.getUserId()));
			ipClaim.setClaimComment(claim.getClaimComment());
			ipClaimDAO.merge(ipClaim);
			String subject = "";
			String body = "";
			boolean notification = false;
			if (claim.getcStatusId() != null && claim.getcStatusId() == 2) {
				notification = true;
				subject = "Claim is Approved status";
				body = "Your Reward '" + ipRewardsDAO.findById(claim.getRewardsId()).getRwTitle() + "' is on its way";
			} else if (claim.getcStatusId() != null && claim.getcStatusId() == 3) {
				notification = true;
				subject = "Claim is Rejected status";
				body = "You have just missed it, Reward '" + ipRewardsDAO.findById(claim.getRewardsId()).getRwTitle() + "' cannot be claimed";
			} else if (claim.getcStatusId() != null && claim.getcStatusId() == 4) {
				notification = true;
				subject = "Claim is Delivered status";
				body = "Your Reward '" + ipRewardsDAO.findById(claim.getRewardsId()).getRwTitle() + "' has delivered";
			}
			if (notification) {
				try {
					IpUser user = (IpUser) ipUserDAO.findById(claim.getUserId());
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

	private ClaimMessage getClaimMessage(IpClaim claim) {
		ClaimMessage message = new ClaimMessage();
		try {
			message.setClaimComment(claim.getClaimComment());
			message.setClaimCrtdDt(claim.getClaimCrtdDt());
			message.setClaimDesc(claim.getClaimDesc());
			message.setClaimId(claim.getClaimId());
			message.setcStatusId(claim.getIpClaimStatus().getCsId());
			message.setcStatusName(claim.getIpClaimStatus().getCsDesc());
			message.setRewardsId(claim.getIpRewards().getRwId());
			message.setUserId(claim.getIpUser().getUserId());
			message.setUserName(claim.getIpUser().getUserScreenName());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/claim/list")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public <T extends ClaimMessage> List<T> listClaim() {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimDAO.findAll();
			for (Object object : vals) {
				IpClaim claim = (IpClaim) object;
				ClaimMessage message = getClaimMessage(claim);
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/claim/get/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public ClaimMessage getClaimById(@PathParam("id") Long id) {
		ClaimMessage message = new ClaimMessage();
		try {
			IpClaim claim = ipClaimDAO.findById(id);
			message = getClaimMessage(claim);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/claim/list/status/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public <T extends ClaimMessage> List<T> listClaimByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimDAO.findByStatusId(id);
			for (Object object : vals) {
				IpClaim claim = (IpClaim) object;
				ClaimMessage message = getClaimMessage(claim);
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/claim/list/user/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public <T extends ClaimMessage> List<T> listClaimByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimDAO.findByUserId(id);
			for (Object object : vals) {
				IpClaim claim = (IpClaim) object;
				ClaimMessage message = getClaimMessage(claim);
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/claim/status/list")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public <T extends MetaDataMessage> List<T> listClaimStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimStatusDAO.findAll();
			for (Object object : vals) {
				IpClaimStatus status = (IpClaimStatus) object;
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
	@Path("/claim/status/list/{curr}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public <T extends MetaDataMessage> List<T> listNextClaimStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimStatusDAO.findNext(curr);
			for (Object object : vals) {
				IpClaimStatus status = (IpClaimStatus) object;
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
	@Path("/claim/status/get/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional
	public MetaDataMessage getClaimStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpClaimStatus status = ipClaimStatusDAO.findById(id);
			message.setId(status.getCsId());
			message.setDesc(status.getCsDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpClaimDAO getIpClaimDAO() {
		return ipClaimDAO;
	}

	public void setIpClaimDAO(IpClaimDAO ipClaimDAO) {
		this.ipClaimDAO = ipClaimDAO;
	}

	public IpClaimStatusDAO getIpClaimStatusDAO() {
		return ipClaimStatusDAO;
	}

	public void setIpClaimStatusDAO(IpClaimStatusDAO ipClaimStatusDAO) {
		this.ipClaimStatusDAO = ipClaimStatusDAO;
	}

	public IpRewardsDAO getIpRewardsDAO() {
		return ipRewardsDAO;
	}

	public void setIpRewardsDAO(IpRewardsDAO ipRewardsDAO) {
		this.ipRewardsDAO = ipRewardsDAO;
	}

	public IpNotifDAO getIpNotifDAO() {
		return ipNotifDAO;
	}

	public void setIpNotifDAO(IpNotifDAO ipNotifDAO) {
		this.ipNotifDAO = ipNotifDAO;
	}

}
