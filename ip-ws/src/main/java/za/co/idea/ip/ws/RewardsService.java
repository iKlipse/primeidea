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

import za.co.idea.ip.orm.bean.IpPoints;
import za.co.idea.ip.orm.bean.IpRewards;
import za.co.idea.ip.orm.bean.IpRewardsCat;
import za.co.idea.ip.orm.bean.IpRewardsStatus;
import za.co.idea.ip.orm.dao.IpAllocationDAO;
import za.co.idea.ip.orm.dao.IpPointsDAO;
import za.co.idea.ip.orm.dao.IpRewardsCatDAO;
import za.co.idea.ip.orm.dao.IpRewardsDAO;
import za.co.idea.ip.orm.dao.IpRewardsGroupDAO;
import za.co.idea.ip.orm.dao.IpRewardsStatusDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/rs")
public class RewardsService {
	private IpUserDAO ipUserDAO;
	private IpRewardsCatDAO ipRewardsCatDAO;
	private IpRewardsDAO ipRewardsDAO;
	private IpRewardsStatusDAO ipRewardsStatusDAO;
	private IpRewardsGroupDAO ipRewardsGroupDAO;
	private IpAllocationDAO ipAllocationDAO;
	private IpPointsDAO ipPointsDAO;

	@POST
	@Path("/rewards/add")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage createReward(RewardsMessage rewards) {
		try {
			IpRewards ipRewards = new IpRewards();
			ipRewards.setIpRewardsCat(ipRewardsCatDAO.findById(rewards.getrCatId()));
			ipRewards.setIpRewardsStatus(ipRewardsStatusDAO.findById(rewards.getrStatusId()));
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
			ipRewardsDAO.save(ipRewards);
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
	@Path("/rewards/modify")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage updateReward(RewardsMessage rewards) {
		try {
			IpRewards ipRewards = new IpRewards();
			ipRewards.setIpRewardsCat(ipRewardsCatDAO.findById(rewards.getrCatId()));
			ipRewards.setIpRewardsStatus(ipRewardsStatusDAO.findById(rewards.getrStatusId()));
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
			ipRewardsDAO.merge(ipRewards);
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
	@Path("/rewards/list")
	@Produces("application/json")
	public <T extends RewardsMessage> List<T> listRewards() {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findAll();
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = new RewardsMessage();
				rewards.setrCatId(ipRewards.getIpRewardsCat().getRcId());
				rewards.setrStatusId(ipRewards.getIpRewardsStatus().getRsId());
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
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/rewards/get/{id}")
	@Produces("application/json")
	public RewardsMessage getRewardsById(@PathParam("id") Long id) {
		RewardsMessage rewards = new RewardsMessage();
		try {
			IpRewards ipRewards = ipRewardsDAO.findById(id);
			rewards.setrCatId(ipRewards.getIpRewardsCat().getRcId());
			rewards.setrStatusId(ipRewards.getIpRewardsStatus().getRsId());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rewards;
	}

	@GET
	@Path("/rewards/list/{id}")
	@Produces("application/json")
	public <T extends RewardsMessage> List<T> listRewardsByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findByStatusId(id);
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = new RewardsMessage();
				rewards.setrCatId(ipRewards.getIpRewardsCat().getRcId());
				rewards.setrStatusId(ipRewards.getIpRewardsStatus().getRsId());
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
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/rewards/list/{id}")
	@Produces("application/json")
	public <T extends RewardsMessage> List<T> listRewardsByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardList = ipRewardsDAO.findByUserId(id);
			for (Object object : rewardList) {
				IpRewards ipRewards = (IpRewards) object;
				RewardsMessage rewards = new RewardsMessage();
				rewards.setrCatId(ipRewards.getIpRewardsCat().getRcId());
				rewards.setrStatusId(ipRewards.getIpRewardsStatus().getRsId());
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
				ret.add((T) rewards);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/rewards/cat/list")
	@Produces("application/json")
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
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/rewards/status/list")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listRewardsStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardsStatus = ipRewardsStatusDAO.findAll();
			for (Object object : rewardsStatus) {
				IpRewardsStatus status = (IpRewardsStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getRsId());
				message.setDesc(status.getRsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/rewards/status/list/{curr}")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listNextRewardsStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List rewardsStatus = ipRewardsStatusDAO.findNext(curr);
			for (Object object : rewardsStatus) {
				IpRewardsStatus status = (IpRewardsStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getRsId());
				message.setDesc(status.getRsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/rewards/cat/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getRewardsCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpRewardsCat cat = ipRewardsCatDAO.findById(id);
			message.setId(cat.getRcId());
			message.setDesc(cat.getRcDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/rewards/status/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getRewardsStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpRewardsStatus status = ipRewardsStatusDAO.findById(id);
			message.setId(status.getRsId());
			message.setDesc(status.getRsDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@POST
	@Path("/points/add")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseMessage createPoint(PointMessage point) {
		try {
			IpPoints ipPoints = new IpPoints();
			ipPoints.setIpAllocation(ipAllocationDAO.findById(point.getAllocId()));
			ipPoints.setIpUser(ipUserDAO.findById(point.getUserId()));
			ipPoints.setPointId(point.getPointId());
			ipPoints.setPointValue(point.getPointValue());
			ipPointsDAO.save(ipPoints);
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
	@Path("/points/modify")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseMessage updatePoint(PointMessage point) {
		try {
			IpPoints ipPoints = new IpPoints();
			ipPoints.setIpAllocation(ipAllocationDAO.findById(point.getAllocId()));
			ipPoints.setIpUser(ipUserDAO.findById(point.getUserId()));
			ipPoints.setPointId(point.getPointId());
			ipPoints.setPointValue(point.getPointValue());
			ipPointsDAO.merge(ipPoints);
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
	@Path("/points/get/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public PointMessage getPointsById(@PathParam("id") Long id) {
		PointMessage message = new PointMessage();
		try {
			IpPoints ipPoints = ipPointsDAO.findById(id);
			message.setAllocId(ipPoints.getIpAllocation().getAllocId());
			message.setPointId(ipPoints.getPointId());
			message.setPointValue(ipPoints.getPointValue());
			message.setUserId(ipPoints.getIpUser().getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@PUT
	@Path("/points/get/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public <T extends PointMessage> List<T> listPointsByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List points = ipPointsDAO.findByUser(id);
			for (Object object : points) {
				IpPoints ipPoints = (IpPoints) object;
				PointMessage message = new PointMessage();
				message.setAllocId(ipPoints.getIpAllocation().getAllocId());
				message.setPointId(ipPoints.getPointId());
				message.setPointValue(ipPoints.getPointValue());
				message.setUserId(ipPoints.getIpUser().getUserId());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
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

	public IpRewardsStatusDAO getIpRewardsStatusDAO() {
		return ipRewardsStatusDAO;
	}

	public void setIpRewardsStatusDAO(IpRewardsStatusDAO ipRewardsStatusDAO) {
		this.ipRewardsStatusDAO = ipRewardsStatusDAO;
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

}
