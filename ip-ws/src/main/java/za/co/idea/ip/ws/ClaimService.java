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

import za.co.idea.ip.orm.bean.IpClaim;
import za.co.idea.ip.orm.bean.IpClaimStatus;
import za.co.idea.ip.orm.dao.IpClaimDAO;
import za.co.idea.ip.orm.dao.IpClaimStatusDAO;
import za.co.idea.ip.orm.dao.IpRewardsDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/cls")
public class ClaimService {
	private IpUserDAO ipUserDAO;
	private IpClaimDAO ipClaimDAO;
	private IpClaimStatusDAO ipClaimStatusDAO;
	private IpRewardsDAO ipRewardsDAO;

	@POST
	@Path("/claim/add")
	@Consumes("application/json")
	@Produces("application/json")
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
	@Path("/claim/modify")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage updateClaim(ClaimMessage claim) {
		try {
			IpClaim ipClaim = new IpClaim();
			ipClaim.setClaimCrtdDt(claim.getClaimCrtdDt());
			ipClaim.setClaimDesc(claim.getClaimDesc());
			ipClaim.setClaimId(claim.getClaimId());
			ipClaim.setIpClaimStatus(ipClaimStatusDAO.findById(claim.getcStatusId()));
			ipClaim.setIpRewards(ipRewardsDAO.findById(claim.getRewardsId()));
			ipClaim.setIpUser(ipUserDAO.findById(claim.getUserId()));
			ipClaimDAO.merge(ipClaim);
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
	@Path("/claim/list")
	@Produces("application/json")
	public <T extends ClaimMessage> List<T> listClaim() {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimDAO.findAll();
			for (Object object : vals) {
				IpClaim claim = (IpClaim) object;
				ClaimMessage message = new ClaimMessage();
				message.setClaimCrtdDt(claim.getClaimCrtdDt());
				message.setClaimDesc(claim.getClaimDesc());
				message.setClaimId(claim.getClaimId());
				message.setcStatusId(claim.getIpClaimStatus().getCsId());
				message.setRewardsId(claim.getIpRewards().getRwId());
				message.setUserId(claim.getIpUser().getUserId());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/claim/get/{id}")
	@Produces("application/json")
	public ClaimMessage getClaimById(@PathParam("id") Long id) {
		ClaimMessage message = new ClaimMessage();
		try {
			IpClaim claim = ipClaimDAO.findById(id);
			message.setClaimCrtdDt(claim.getClaimCrtdDt());
			message.setClaimDesc(claim.getClaimDesc());
			message.setClaimId(claim.getClaimId());
			message.setcStatusId(claim.getIpClaimStatus().getCsId());
			message.setRewardsId(claim.getIpRewards().getRwId());
			message.setUserId(claim.getIpUser().getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/claim/list/{id}")
	@Produces("application/json")
	public <T extends ClaimMessage> List<T> listClaimByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimDAO.findByStatusId(id);
			for (Object object : vals) {
				IpClaim claim = (IpClaim) object;
				ClaimMessage message = new ClaimMessage();
				message.setClaimCrtdDt(claim.getClaimCrtdDt());
				message.setClaimDesc(claim.getClaimDesc());
				message.setClaimId(claim.getClaimId());
				message.setcStatusId(claim.getIpClaimStatus().getCsId());
				message.setRewardsId(claim.getIpRewards().getRwId());
				message.setUserId(claim.getIpUser().getUserId());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/claim/list/{id}")
	@Produces("application/json")
	public <T extends ClaimMessage> List<T> listClaimByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List vals = ipClaimDAO.findByUserId(id);
			for (Object object : vals) {
				IpClaim claim = (IpClaim) object;
				ClaimMessage message = new ClaimMessage();
				message.setClaimCrtdDt(claim.getClaimCrtdDt());
				message.setClaimDesc(claim.getClaimDesc());
				message.setClaimId(claim.getClaimId());
				message.setcStatusId(claim.getIpClaimStatus().getCsId());
				message.setRewardsId(claim.getIpRewards().getRwId());
				message.setUserId(claim.getIpUser().getUserId());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/claim/status/list")
	@Produces("application/json")
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/claim/status/list/{curr}")
	@Produces("application/json")
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/claim/status/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getClaimStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpClaimStatus status = ipClaimStatusDAO.findById(id);
			message.setId(status.getCsId());
			message.setDesc(status.getCsDesc());
		} catch (Exception e) {
			e.printStackTrace();
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

}
