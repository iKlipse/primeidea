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

import za.co.idea.ip.orm.bean.IpCategory;
import za.co.idea.ip.orm.bean.IpChallengeCat;
import za.co.idea.ip.orm.bean.IpChallengeStatus;
import za.co.idea.ip.orm.bean.IpClaimStatus;
import za.co.idea.ip.orm.bean.IpIdeaCat;
import za.co.idea.ip.orm.bean.IpIdeaStatus;
import za.co.idea.ip.orm.bean.IpRewardsCat;
import za.co.idea.ip.orm.bean.IpRewardsStatus;
import za.co.idea.ip.orm.bean.IpSecqList;
import za.co.idea.ip.orm.bean.IpSolutionCat;
import za.co.idea.ip.orm.bean.IpSolutionStatus;
import za.co.idea.ip.orm.dao.IpCategoryDAO;
import za.co.idea.ip.orm.dao.IpChallengeCatDAO;
import za.co.idea.ip.orm.dao.IpChallengeStatusDAO;
import za.co.idea.ip.orm.dao.IpClaimStatusDAO;
import za.co.idea.ip.orm.dao.IpIdeaCatDAO;
import za.co.idea.ip.orm.dao.IpIdeaStatusDAO;
import za.co.idea.ip.orm.dao.IpRewardsCatDAO;
import za.co.idea.ip.orm.dao.IpRewardsStatusDAO;
import za.co.idea.ip.orm.dao.IpSecqListDAO;
import za.co.idea.ip.orm.dao.IpSolutionCatDAO;
import za.co.idea.ip.orm.dao.IpSolutionStatusDAO;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/ms")
public class MetaDataService {
	private IpChallengeCatDAO ipChallengeCatDAO;
	private IpChallengeStatusDAO ipChallengeStatusDAO;
	private IpClaimStatusDAO ipClaimStatusDAO;
	private IpIdeaCatDAO ipIdeaCatDAO;
	private IpIdeaStatusDAO ipIdeaStatusDAO;
	private IpRewardsCatDAO ipRewardsCatDAO;
	private IpRewardsStatusDAO ipRewardsStatusDAO;
	private IpSolutionCatDAO ipSolutionCatDAO;
	private IpSolutionStatusDAO ipSolutionStatusDAO;
	private IpSecqListDAO ipSecqListDAO;
	private IpCategoryDAO ipCategoryDAO;

	@POST
	@Path("/add")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseMessage addMetaData(MetaDataMessage mData) {
		boolean success = false;
		String msg = "Invalid Table Selected :: " + mData;
		if (mData.getTable().equalsIgnoreCase("IpChallengeCat")) {
			IpChallengeCat cat = new IpChallengeCat();
			cat.setCcDesc(mData.getDesc());
			cat.setCcId(mData.getId());
			try {
				ipChallengeCatDAO.save(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpIdeaCat")) {
			IpIdeaCat cat = new IpIdeaCat();
			cat.setIcDesc(mData.getDesc());
			cat.setIcId(mData.getId());
			try {
				ipIdeaCatDAO.save(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpRewardsCat")) {
			IpRewardsCat cat = new IpRewardsCat();
			cat.setRcDesc(mData.getDesc());
			cat.setRcId(mData.getId());
			try {
				ipRewardsCatDAO.save(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpSolutionCat")) {
			IpSolutionCat cat = new IpSolutionCat();
			cat.setScDesc(mData.getDesc());
			cat.setScId(mData.getId());
			try {
				ipSolutionCatDAO.save(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpSecqList")) {
			IpSecqList cat = new IpSecqList();
			cat.setIslDesc(mData.getDesc());
			cat.setIslId(mData.getId());
			try {
				ipSecqListDAO.save(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpCategory")) {
			IpCategory cat = new IpCategory();
			cat.setCategoryDesc(mData.getDesc());
			cat.setCategoryId(mData.getId());
			try {
				ipCategoryDAO.save(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		}
		if (success) {
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} else {
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(msg);
			return message;
		}
	}

	@PUT
	@Path("/modify")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseMessage updateMetaData(MetaDataMessage mData) {
		boolean success = false;
		String msg = "Invalid Table Selected :: " + mData;
		if (mData.getTable().equalsIgnoreCase("IpChallengeCat")) {
			IpChallengeCat cat = new IpChallengeCat();
			cat.setCcDesc(mData.getDesc());
			cat.setCcId(mData.getId());
			try {
				ipChallengeCatDAO.merge(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpIdeaCat")) {
			IpIdeaCat cat = new IpIdeaCat();
			cat.setIcDesc(mData.getDesc());
			cat.setIcId(mData.getId());
			try {
				ipIdeaCatDAO.merge(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpRewardsCat")) {
			IpRewardsCat cat = new IpRewardsCat();
			cat.setRcDesc(mData.getDesc());
			cat.setRcId(mData.getId());
			try {
				ipRewardsCatDAO.merge(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpSolutionCat")) {
			IpSolutionCat cat = new IpSolutionCat();
			cat.setScDesc(mData.getDesc());
			cat.setScId(mData.getId());
			try {
				ipSolutionCatDAO.merge(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpSecqList")) {
			IpSecqList cat = new IpSecqList();
			cat.setIslDesc(mData.getDesc());
			cat.setIslId(mData.getId());
			try {
				ipSecqListDAO.merge(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpCategory")) {
			IpCategory cat = new IpCategory();
			cat.setCategoryDesc(mData.getDesc());
			cat.setCategoryId(mData.getId());
			try {
				ipCategoryDAO.merge(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		}
		if (success) {
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} else {
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(msg);
			return message;
		}
	}

	@PUT
	@Path("/delete")
	@Produces("application/json")
	@Consumes("application/json")
	public ResponseMessage deleteMetaData(MetaDataMessage mData) {
		boolean success = false;
		String msg = "Invalid Table Selected :: " + mData;
		if (mData.getTable().equalsIgnoreCase("IpChallengeCat")) {
			IpChallengeCat cat = new IpChallengeCat();
			cat.setCcDesc(mData.getDesc());
			cat.setCcId(mData.getId());
			try {
				ipChallengeCatDAO.delete(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpIdeaCat")) {
			IpIdeaCat cat = new IpIdeaCat();
			cat.setIcDesc(mData.getDesc());
			cat.setIcId(mData.getId());
			try {
				ipIdeaCatDAO.delete(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpRewardsCat")) {
			List depend = ipRewardsCatDAO.listDependentCat();
			if (depend != null && depend.size() > 0) {
				success = false;
				msg = "Rewards Dependant on Category";
			} else {
				IpRewardsCat cat = new IpRewardsCat();
				cat.setRcDesc(mData.getDesc());
				cat.setRcId(mData.getId());
				try {
					ipRewardsCatDAO.delete(cat);
					success = true;
				} catch (Exception e) {
					e.printStackTrace();
					success = false;
					msg = e.getMessage();
				}
			}
		} else if (mData.getTable().equalsIgnoreCase("IpSolutionCat")) {
			IpSolutionCat cat = new IpSolutionCat();
			cat.setScDesc(mData.getDesc());
			cat.setScId(mData.getId());
			try {
				ipSolutionCatDAO.delete(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpSecqList")) {
			IpSecqList cat = new IpSecqList();
			cat.setIslDesc(mData.getDesc());
			cat.setIslId(mData.getId());
			try {
				ipSecqListDAO.delete(cat);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				msg = e.getMessage();
			}
		} else if (mData.getTable().equalsIgnoreCase("IpCategory")) {
			List depend = ipCategoryDAO.listDependentCat(mData.getId().longValue());
			if (depend != null && depend.size() > 0) {
				success = false;
				msg = "Entities Dependant on Category";
			} else {
				IpCategory cat = new IpCategory();
				cat.setCategoryDesc(mData.getDesc());
				cat.setCategoryId(mData.getId());
				try {
					ipCategoryDAO.delete(cat);
					success = true;
				} catch (Exception e) {
					e.printStackTrace();
					success = false;
					msg = e.getMessage();
				}
			}
		}
		if (success) {
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} else {
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(msg);
			return message;
		}
	}

	@GET
	@Path("/list/{table}")
	@Produces("application/json")
	@Consumes("application/json")
	public <T extends MetaDataMessage> List<T> listByTable(@PathParam("table") String table) {
		List<T> ret = new ArrayList<T>();
		try {
			if (table.equalsIgnoreCase("ip_challenge_status")) {
				List vals = ipChallengeStatusDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpChallengeStatus cat = (IpChallengeStatus) object;
					message.setId(cat.getCsId());
					message.setDesc(cat.getCsDesc());
					message.setTable("ip_challenge_status");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("ip_idea_status")) {
				List vals = ipIdeaStatusDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpIdeaStatus cat = (IpIdeaStatus) object;
					message.setId(cat.getIsId());
					message.setDesc(cat.getIsDesc());
					message.setTable("ip_idea_status");
					ret.add((T) message);
				}
			}
			if (table.equalsIgnoreCase("ip_claim_status")) {
				List vals = ipClaimStatusDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpClaimStatus cat = (IpClaimStatus) object;
					message.setId(cat.getCsId());
					message.setDesc(cat.getCsDesc());
					message.setTable("ip_claim_status");
					ret.add((T) message);
				}
			}
			if (table.equalsIgnoreCase("ip_solution_status")) {
				List vals = ipSolutionStatusDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpSolutionStatus cat = (IpSolutionStatus) object;
					message.setId(cat.getSsId());
					message.setDesc(cat.getSsDesc());
					message.setTable("ip_solution_status");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("ip_rewards_status")) {
				List vals = ipRewardsStatusDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpRewardsStatus cat = (IpRewardsStatus) object;
					message.setId(cat.getRsId());
					message.setDesc(cat.getRsDesc());
					message.setTable("ip_rewards_status");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("IpChallengeCat")) {
				List vals = ipChallengeCatDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpChallengeCat cat = (IpChallengeCat) object;
					message.setId(cat.getCcId());
					message.setDesc(cat.getCcDesc());
					message.setTable("ip_challenge_cat");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("IpIdeaCat")) {
				List vals = ipIdeaCatDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpIdeaCat cat = (IpIdeaCat) object;
					message.setDesc(cat.getIcDesc());
					message.setId(cat.getIcId());
					message.setTable("ip_idea_cat");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("IpRewardsCat")) {
				List vals = ipRewardsCatDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpRewardsCat cat = (IpRewardsCat) object;
					message.setDesc(cat.getRcDesc());
					message.setId(cat.getRcId());
					message.setTable("ip_rewards_cat");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("IpSolutionCat")) {
				List vals = ipSolutionCatDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpSolutionCat cat = (IpSolutionCat) object;
					message.setDesc(cat.getScDesc());
					message.setId(cat.getScId());
					message.setTable("ip_solution_cat");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("IpSecqList")) {
				List vals = ipSecqListDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpSecqList cat = (IpSecqList) object;
					message.setDesc(cat.getIslDesc());
					message.setId(cat.getIslId());
					message.setTable("ip_secq_list");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("IpCategory")) {
				List vals = ipCategoryDAO.findAll();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpCategory cat = (IpCategory) object;
					message.setDesc(cat.getCategoryDesc());
					message.setId(cat.getCategoryId());
					message.setTable("ip_category");
					ret.add((T) message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/list/non/{table}")
	@Produces("application/json")
	@Consumes("application/json")
	public <T extends MetaDataMessage> List<T> listNonAllocatedByTable(@PathParam("table") String table) {
		List<T> ret = new ArrayList<T>();
		try {
			if (table.equalsIgnoreCase("ip_challenge_status")) {
				List vals = ipChallengeStatusDAO.findAllNonAlloc();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpChallengeStatus cat = (IpChallengeStatus) object;
					message.setId(cat.getCsId());
					message.setDesc(cat.getCsDesc());
					message.setTable("ip_challenge_status");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("ip_idea_status")) {
				List vals = ipIdeaStatusDAO.findAllNonAlloc();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpIdeaStatus cat = (IpIdeaStatus) object;
					message.setId(cat.getIsId());
					message.setDesc(cat.getIsDesc());
					message.setTable("ip_idea_status");
					ret.add((T) message);
				}
			}
			if (table.equalsIgnoreCase("ip_claim_status")) {
				List vals = ipClaimStatusDAO.findAllNonAlloc();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpClaimStatus cat = (IpClaimStatus) object;
					message.setId(cat.getCsId());
					message.setDesc(cat.getCsDesc());
					message.setTable("ip_claim_status");
					ret.add((T) message);
				}
			}
			if (table.equalsIgnoreCase("ip_solution_status")) {
				List vals = ipSolutionStatusDAO.findAllNonAlloc();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpSolutionStatus cat = (IpSolutionStatus) object;
					message.setId(cat.getSsId());
					message.setDesc(cat.getSsDesc());
					message.setTable("ip_solution_status");
					ret.add((T) message);
				}
			} else if (table.equalsIgnoreCase("ip_rewards_status")) {
				List vals = ipRewardsStatusDAO.findAllNonAlloc();
				for (Object object : vals) {
					MetaDataMessage message = new MetaDataMessage();
					IpRewardsStatus cat = (IpRewardsStatus) object;
					message.setId(cat.getRsId());
					message.setDesc(cat.getRsDesc());
					message.setTable("ip_rewards_status");
					ret.add((T) message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
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

	public IpClaimStatusDAO getIpClaimStatusDAO() {
		return ipClaimStatusDAO;
	}

	public void setIpClaimStatusDAO(IpClaimStatusDAO ipClaimStatusDAO) {
		this.ipClaimStatusDAO = ipClaimStatusDAO;
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

	public IpRewardsCatDAO getIpRewardsCatDAO() {
		return ipRewardsCatDAO;
	}

	public void setIpRewardsCatDAO(IpRewardsCatDAO ipRewardsCatDAO) {
		this.ipRewardsCatDAO = ipRewardsCatDAO;
	}

	public IpRewardsStatusDAO getIpRewardsStatusDAO() {
		return ipRewardsStatusDAO;
	}

	public void setIpRewardsStatusDAO(IpRewardsStatusDAO ipRewardsStatusDAO) {
		this.ipRewardsStatusDAO = ipRewardsStatusDAO;
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

	public IpSecqListDAO getIpSecqListDAO() {
		return ipSecqListDAO;
	}

	public void setIpSecqListDAO(IpSecqListDAO ipSecqListDAO) {
		this.ipSecqListDAO = ipSecqListDAO;
	}

	public IpCategoryDAO getIpCategoryDAO() {
		return ipCategoryDAO;
	}

	public void setIpCategoryDAO(IpCategoryDAO ipCategoryDAO) {
		this.ipCategoryDAO = ipCategoryDAO;
	}
}
