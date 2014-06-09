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

import za.co.idea.ip.orm.bean.IpChallenge;
import za.co.idea.ip.orm.bean.IpChallengeCat;
import za.co.idea.ip.orm.bean.IpChallengeStatus;
import za.co.idea.ip.orm.dao.IpChallengeCatDAO;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpChallengeStatusDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/cs")
public class ChallengeService {
	private IpChallengeDAO ipChallengeDAO;
	private IpChallengeCatDAO ipChallengeCatDAO;
	private IpChallengeStatusDAO ipChallengeStatusDAO;
	private IpUserDAO ipUserDAO;

	@POST
	@Path("/challenge/add")
	@Consumes("application/json")
	@Produces("application/json")
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
			ipChallengeDAO.save(ipChallenge);
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
	@Path("/challenge/modify")
	@Consumes("application/json")
	@Produces("application/json")
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
			ipChallengeDAO.merge(ipChallenge);
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
	@Path("/challenge/list")
	@Produces("application/json")
	public <T extends ChallengeMessage> List<T> listChallenge() {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findAll();
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = new ChallengeMessage();
				challenge.setId(ipChallenge.getChalId());
				challenge.setCatId(ipChallenge.getIpChallengeCat().getCcId());
				challenge.setCrtdById(ipChallenge.getIpUser().getUserId());
				challenge.setCrtdDt(ipChallenge.getChalCrtdDt());
				challenge.setDesc(ipChallenge.getChalDesc());
				challenge.setExprDt(ipChallenge.getChalExpiryDt());
				challenge.setHoverText(ipChallenge.getChalHoverTxt());
				challenge.setLaunchDt(ipChallenge.getChalLaunchDt());
				challenge.setStatusId(ipChallenge.getIpChallengeStatus().getCsId());
				challenge.setTag(ipChallenge.getChalTags());
				challenge.setTitle(ipChallenge.getChalTitle());
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/challenge/list/{id}")
	@Produces("application/json")
	public <T extends ChallengeMessage> List<T> listChallengeByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findByUserId(id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = new ChallengeMessage();
				challenge.setId(ipChallenge.getChalId());
				challenge.setCatId(ipChallenge.getIpChallengeCat().getCcId());
				challenge.setCrtdById(ipChallenge.getIpUser().getUserId());
				challenge.setCrtdDt(ipChallenge.getChalCrtdDt());
				challenge.setDesc(ipChallenge.getChalDesc());
				challenge.setExprDt(ipChallenge.getChalExpiryDt());
				challenge.setHoverText(ipChallenge.getChalHoverTxt());
				challenge.setLaunchDt(ipChallenge.getChalLaunchDt());
				challenge.setStatusId(ipChallenge.getIpChallengeStatus().getCsId());
				challenge.setTag(ipChallenge.getChalTags());
				challenge.setTitle(ipChallenge.getChalTitle());
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/challenge/list/status/{id}")
	@Produces("application/json")
	public <T extends ChallengeMessage> List<T> listChallengeByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List challenges = ipChallengeDAO.findByStatusId(id);
			for (Object object : challenges) {
				IpChallenge ipChallenge = (IpChallenge) object;
				ChallengeMessage challenge = new ChallengeMessage();
				challenge.setId(ipChallenge.getChalId());
				challenge.setCatId(ipChallenge.getIpChallengeCat().getCcId());
				challenge.setCrtdById(ipChallenge.getIpUser().getUserId());
				challenge.setCrtdDt(ipChallenge.getChalCrtdDt());
				challenge.setDesc(ipChallenge.getChalDesc());
				challenge.setExprDt(ipChallenge.getChalExpiryDt());
				challenge.setHoverText(ipChallenge.getChalHoverTxt());
				challenge.setLaunchDt(ipChallenge.getChalLaunchDt());
				challenge.setStatusId(ipChallenge.getIpChallengeStatus().getCsId());
				challenge.setTag(ipChallenge.getChalTags());
				challenge.setTitle(ipChallenge.getChalTitle());
				ret.add((T) challenge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/challenge/cat/list")
	@Produces("application/json")
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/challenge/status/list")
	@Produces("application/json")
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/challenge/status/list/{curr}")
	@Produces("application/json")
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/challenge/cat/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getChallengeCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpChallengeCat cat = ipChallengeCatDAO.findById(id);
			message.setId(cat.getCcId());
			message.setDesc(cat.getCcDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/challenge/status/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getChallengeStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpChallengeStatus status = ipChallengeStatusDAO.findById(id);
			message.setId(status.getCsId());
			message.setDesc(status.getCsDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/challenge/get/{id}")
	@Produces("application/json")
	public ChallengeMessage getChallenge(@PathParam("id") Long id) {
		ChallengeMessage challenge = new ChallengeMessage();
		try {
			IpChallenge ipChallenge = ipChallengeDAO.findById(id);
			challenge.setId(ipChallenge.getChalId());
			challenge.setCatId(ipChallenge.getIpChallengeCat().getCcId());
			challenge.setCrtdById(ipChallenge.getIpUser().getUserId());
			challenge.setCrtdDt(ipChallenge.getChalCrtdDt());
			challenge.setDesc(ipChallenge.getChalDesc());
			challenge.setExprDt(ipChallenge.getChalExpiryDt());
			challenge.setHoverText(ipChallenge.getChalHoverTxt());
			challenge.setLaunchDt(ipChallenge.getChalLaunchDt());
			challenge.setStatusId(ipChallenge.getIpChallengeStatus().getCsId());
			challenge.setTag(ipChallenge.getChalTags());
			challenge.setTitle(ipChallenge.getChalTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return challenge;
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

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}
}
