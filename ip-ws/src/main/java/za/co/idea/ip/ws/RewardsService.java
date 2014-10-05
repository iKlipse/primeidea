package za.co.idea.ip.ws;

import java.util.ArrayList;
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

import za.co.idea.ip.orm.bean.IpAllocation;
import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.bean.IpPoints;
import za.co.idea.ip.orm.bean.IpRewards;
import za.co.idea.ip.orm.bean.IpRewardsCat;
import za.co.idea.ip.orm.dao.IpAllocationDAO;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpPointsDAO;
import za.co.idea.ip.orm.dao.IpRewardsCatDAO;
import za.co.idea.ip.orm.dao.IpRewardsDAO;
import za.co.idea.ip.orm.dao.IpRewardsGroupDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.AllocationMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/rs")
public class RewardsService {
	private static final Logger logger = Logger.getLogger(RewardsService.class);
	private IpUserDAO ipUserDAO;
	private IpRewardsCatDAO ipRewardsCatDAO;
	private IpRewardsDAO ipRewardsDAO;
	private IpRewardsGroupDAO ipRewardsGroupDAO;
	private IpAllocationDAO ipAllocationDAO;
	private IpPointsDAO ipPointsDAO;
	private IpGroupDAO ipGroupDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpBlobDAO ipBlobDAO;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");

	@POST
	@Path("/rewards/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createReward(RewardsMessage rewards) {
		try {
			IpRewards ipRewards = new IpRewards();
			ipRewards.setIpRewardsCat(ipRewardsCatDAO.findById(rewards.getrCatId()));
			ipRewards.setRwCrtdDt(rewards.getRwCrtdDt());
			ipRewards.setRwDesc(rewards.getRwDesc());
			ipRewards.setRwExpiryDt(rewards.getRwExpiryDt());
			ipRewards.setRwHoverText(rewards.getRwHoverText());
			ipRewards.setRwId(rewards.getRwId());
			ipRewards.setRwLaunchDt(rewards.getRwLaunchDt());
			ipRewards.setRwStockCodeNum(rewards.getRwStockCodeNum());
			ipRewards.setRwTag(rewards.getRwTag());
			ipRewards.setRwTitle(rewards.getRwTitle());
			ipRewards.setRwValue(rewards.getRwValue());
			ipRewards.setRwPrice(rewards.getRwPrice());
			ipRewards.setRwQuantity(rewards.getRwQuantity());
			ipRewardsDAO.save(ipRewards);
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
	@Path("/rewards/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateReward(RewardsMessage rewards) {
		try {
			IpRewards ipRewards = new IpRewards();
			ipRewards.setIpRewardsCat(ipRewardsCatDAO.findById(rewards.getrCatId()));
			ipRewards.setRwCrtdDt(rewards.getRwCrtdDt());
			ipRewards.setRwDesc(rewards.getRwDesc());
			ipRewards.setRwExpiryDt(rewards.getRwExpiryDt());
			ipRewards.setRwHoverText(rewards.getRwHoverText());
			ipRewards.setRwId(rewards.getRwId());
			ipRewards.setRwLaunchDt(rewards.getRwLaunchDt());
			ipRewards.setRwStockCodeNum(rewards.getRwStockCodeNum());
			ipRewards.setRwTag(rewards.getRwTag());
			ipRewards.setRwTitle(rewards.getRwTitle());
			ipRewards.setRwValue(rewards.getRwValue());
			ipRewards.setRwPrice(rewards.getRwPrice());
			ipRewards.setRwQuantity(rewards.getRwQuantity());
			ipRewardsDAO.merge(ipRewards);
			ipRewardsGroupDAO.deleteByRewardsId(rewards.getRwId());
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

	private RewardsMessage getRewardsMessage(IpRewards ipRewards) {
		RewardsMessage rewards = new RewardsMessage();
		try {
			rewards.setrCatId(ipRewards.getIpRewardsCat().getRcId());
			rewards.setRwCrtdDt(ipRewards.getRwCrtdDt());
			rewards.setRwDesc(ipRewards.getRwDesc());
			rewards.setRwExpiryDt(ipRewards.getRwExpiryDt());
			rewards.setRwHoverText(ipRewards.getRwHoverText());
			rewards.setRwId(ipRewards.getRwId());
			rewards.setRwLaunchDt(ipRewards.getRwLaunchDt());
			rewards.setRwStockCodeNum(ipRewards.getRwStockCodeNum());
			rewards.setRwTag(ipRewards.getRwTag());
			rewards.setRwTitle(ipRewards.getRwTitle());
			rewards.setRwValue(ipRewards.getRwValue());
			rewards.setRwPrice(ipRewards.getRwPrice());
			rewards.setRwQuantity(ipRewards.getRwQuantity());
			rewards.setrCatName(ipRewards.getIpRewardsCat().getRcDesc());
			IpBlob ipBlob = ipBlobDAO.getBlobByEntity(ipRewards.getRwId(), "ip_rewards");
			if (ipBlob != null) {
				rewards.setRwUrl("ip_rewards/" + ipRewards.getRwId() + "/" + ipBlob.getBlobName());
				rewards.setRwImgAvail(true);
				rewards.setBlobUrl("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/fds?blobId=" + ipBlob.getBlobId());
			} else {
				rewards.setRwImgAvail(false);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return rewards;
	}

	@GET
	@Path("/rewards/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends RewardsMessage> List<T> listRewards() {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findAll();
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = getRewardsMessage(ipRewards);
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/rewards/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public RewardsMessage getRewardsById(@PathParam("id") Long id) {
		RewardsMessage rewards = new RewardsMessage();
		try {
			IpRewards ipRewards = ipRewardsDAO.findById(id);
			rewards = getRewardsMessage(ipRewards);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return rewards;
	}

	@GET
	@Path("/rewards/list/avail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends RewardsMessage> List<T> listAvailableRewards() {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findByAvail();
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = getRewardsMessage(ipRewards);
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/rewards/list/cat/{catId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends RewardsMessage> List<T> listRewardsByCat(@PathParam("catId") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findByCatId(id);
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = getRewardsMessage(ipRewards);
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/rewards/list/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends RewardsMessage> List<T> listRewardsByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findByUserId(id);
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = getRewardsMessage(ipRewards);
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/rewards/cat/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends MetaDataMessage> List<T> listRewardsCat() {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardsCats = ipRewardsCatDAO.findAll();
			for (Object object : rewardsCats) {
				IpRewardsCat cat = (IpRewardsCat) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(cat.getRcId());
				message.setDesc(cat.getRcDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/rewards/cat/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public MetaDataMessage getRewardsCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpRewardsCat cat = ipRewardsCatDAO.findById(id);
			message.setId(cat.getRcId());
			message.setDesc(cat.getRcDesc());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@POST
	@Path("/points/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createPoint(PointMessage point) {
		try {
			IpPoints ipPoints = new IpPoints();
			ipPoints.setIpAllocation(ipAllocationDAO.findById(point.getAllocId()));
			ipPoints.setIpUser(ipUserDAO.findById(point.getUserId()));
			ipPoints.setPointId(point.getPointId());
			ipPoints.setPointValue(point.getPointValue());
			ipPoints.setComments(point.getComments());
			ipPoints.setPointCrtdDt(point.getCrtdDt());
			ipPointsDAO.save(ipPoints);
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
	@Path("/points/modify")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updatePoint(PointMessage point) {
		try {
			IpPoints ipPoints = new IpPoints();
			ipPoints.setIpAllocation(ipAllocationDAO.findById(point.getAllocId()));
			ipPoints.setIpUser(ipUserDAO.findById(point.getUserId()));
			ipPoints.setPointId(point.getPointId());
			ipPoints.setPointValue(point.getPointValue());
			ipPoints.setComments(point.getComments());
			ipPoints.setPointCrtdDt(point.getCrtdDt());
			ipPointsDAO.merge(ipPoints);
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

	private PointMessage getPointMessage(IpPoints ipPoints) {
		PointMessage message = new PointMessage();
		try {
			message.setAllocId(ipPoints.getIpAllocation().getAllocId());
			message.setPointId(ipPoints.getPointId());
			message.setPointValue(ipPoints.getPointValue());
			message.setComments(ipPoints.getComments());
			message.setCrtdDt(ipPoints.getPointCrtdDt());
			message.setUserId(ipPoints.getIpUser().getUserId());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/points/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public PointMessage getPointsById(@PathParam("id") Long id) {
		PointMessage message = new PointMessage();
		try {
			IpPoints ipPoints = ipPointsDAO.findById(id);
			message = getPointMessage(ipPoints);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return message;
	}

	@GET
	@Path("/points/get/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends PointMessage> List<T> listPointsByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List points = ipPointsDAO.findByUser(id);
			for (Object object : points) {
				IpPoints ipPoints = (IpPoints) object;
				PointMessage message = getPointMessage(ipPoints);
				ret.add((T) message);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@POST
	@Path("/alloc/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage createAllocation(AllocationMessage alloc) {
		try {
			IpAllocation ipAllocation = new IpAllocation();
			ipAllocation.setAllocDesc(alloc.getAllocDesc());
			ipAllocation.setAllocEntity(alloc.getAllocEntity());
			ipAllocation.setAllocId(alloc.getAllocId());
			ipAllocation.setAllocStatusId(alloc.getAllocStatusId());
			ipAllocation.setAllocVal(alloc.getAllocVal());
			ipAllocation.setAllocCrtdDt(alloc.getAllocCrtdDt());
			ipAllocationDAO.save(ipAllocation);
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
	@Path("/alloc/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage deleteAllocation(AllocationMessage alloc) {
		try {
			List allocated = ipAllocationDAO.getUtilisedAllocation(alloc.getAllocEntity());
			if (allocated != null && allocated.size() > 0) {
				ResponseMessage message = new ResponseMessage();
				message.setStatusCode(2);
				message.setStatusDesc("Points Allocation Active cannot be deleted.");
				return message;
			} else {
				IpAllocation ipAllocation = new IpAllocation();
				ipAllocation.setAllocDesc(alloc.getAllocDesc());
				ipAllocation.setAllocEntity(alloc.getAllocEntity());
				ipAllocation.setAllocId(alloc.getAllocId());
				ipAllocation.setAllocStatusId(alloc.getAllocStatusId());
				ipAllocation.setAllocVal(alloc.getAllocVal());
				ipAllocation.setAllocCrtdDt(alloc.getAllocCrtdDt());
				ipAllocationDAO.delete(ipAllocation);
				ResponseMessage message = new ResponseMessage();
				message.setStatusCode(0);
				message.setStatusDesc("Success");
				return message;
			}
		} catch (Exception e) {
			logger.error(e, e);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@PUT
	@Path("/alloc/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public ResponseMessage updateAllocation(AllocationMessage alloc) {
		try {
			IpAllocation ipAllocation = new IpAllocation();
			ipAllocation.setAllocDesc(alloc.getAllocDesc());
			ipAllocation.setAllocEntity(alloc.getAllocEntity());
			ipAllocation.setAllocId(alloc.getAllocId());
			ipAllocation.setAllocStatusId(alloc.getAllocStatusId());
			ipAllocation.setAllocVal(alloc.getAllocVal());
			ipAllocation.setAllocCrtdDt(alloc.getAllocCrtdDt());
			ipAllocationDAO.merge(ipAllocation);
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

	private AllocationMessage getAllocationMessage(IpAllocation allocation) {
		AllocationMessage msg = new AllocationMessage();
		try {
			msg.setAllocDesc(allocation.getAllocDesc());
			msg.setAllocEntity(allocation.getAllocEntity());
			msg.setAllocId(allocation.getAllocId());
			msg.setAllocStatusId(allocation.getAllocStatusId());
			msg.setAllocVal(allocation.getAllocVal());
			msg.setAllocCrtdDt(allocation.getAllocCrtdDt());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return msg;
	}

	@GET
	@Path("/alloc/list")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends AllocationMessage> List<T> listAllocation() {
		List<T> ret = new ArrayList<T>();
		try {
			List alloc = ipAllocationDAO.findAll();
			for (Object object : alloc) {
				IpAllocation allocation = (IpAllocation) object;
				AllocationMessage msg = getAllocationMessage(allocation);
				ret.add((T) msg);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/alloc/list/{entity}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends AllocationMessage> List<T> listAllocationByEntity(@PathParam("entity") String entity) {
		List<T> ret = new ArrayList<T>();
		try {
			List alloc = ipAllocationDAO.getAllocationByEntity(entity);
			for (Object object : alloc) {
				IpAllocation allocation = (IpAllocation) object;
				AllocationMessage msg = getAllocationMessage(allocation);
				ret.add((T) msg);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/alloc/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public AllocationMessage getAllocationById(@PathParam("id") Integer id) {
		AllocationMessage ret = new AllocationMessage();
		try {
			IpAllocation allocation = ipAllocationDAO.findById(id);
			ret = getAllocationMessage(allocation);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/reward/check/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Boolean checkTitle(@PathParam("title") String title) {
		try {
			List rwByTitle = ipRewardsDAO.findByRwTitle(title);
			Boolean ret = (rwByTitle != null && rwByTitle.size() > 0);
			return ret;
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpRewardsCatDAO getIpRewardsCatDAO() {
		return ipRewardsCatDAO;
	}

	public void setIpRewardsCatDAO(IpRewardsCatDAO ipRewardsCatDAO) {
		this.ipRewardsCatDAO = ipRewardsCatDAO;
	}

	public IpRewardsDAO getIpRewardsDAO() {
		return ipRewardsDAO;
	}

	public void setIpRewardsDAO(IpRewardsDAO ipRewardsDAO) {
		this.ipRewardsDAO = ipRewardsDAO;
	}

	public IpRewardsGroupDAO getIpRewardsGroupDAO() {
		return ipRewardsGroupDAO;
	}

	public void setIpRewardsGroupDAO(IpRewardsGroupDAO ipRewardsGroupDAO) {
		this.ipRewardsGroupDAO = ipRewardsGroupDAO;
	}

	public IpAllocationDAO getIpAllocationDAO() {
		return ipAllocationDAO;
	}

	public void setIpAllocationDAO(IpAllocationDAO ipAllocationDAO) {
		this.ipAllocationDAO = ipAllocationDAO;
	}

	public IpPointsDAO getIpPointsDAO() {
		return ipPointsDAO;
	}

	public void setIpPointsDAO(IpPointsDAO ipPointsDAO) {
		this.ipPointsDAO = ipPointsDAO;
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

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

}
