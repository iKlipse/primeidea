package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpClaim;
import za.co.idea.ip.orm.bean.IpGroupUserActivity;
import za.co.idea.ip.orm.bean.IpIdeaGrpCnt;
import za.co.idea.ip.orm.bean.IpUserActivity;
import za.co.idea.ip.orm.dao.IpClaimDAO;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpGroupUserActivityDAO;
import za.co.idea.ip.orm.dao.IpIdeaGrpCntDAO;
import za.co.idea.ip.orm.dao.IpUserActivityDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.report.GroupUserActivityMessage;
import za.co.idea.ip.ws.bean.report.IdeaSummaryMessage;
import za.co.idea.ip.ws.bean.report.ReportQueryMessage;
import za.co.idea.ip.ws.bean.report.RewardsReportMessage;
import za.co.idea.ip.ws.bean.report.UserActivityMessage;

@Path(value = "/rps")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportService {
	private static final Logger logger = Logger.getLogger(RewardsService.class);
	private IpClaimDAO ipClaimDAO;
	private IpUserActivityDAO ipUserActivityDAO;
	private IpGroupUserActivityDAO ipGroupUserActivityDAO;
	private IpIdeaGrpCntDAO ipIdeaGrpCntDAO;
	private IpGroupDAO ipGroupDAO;
	private IpUserDAO ipUserDAO;

	@POST
	@Path("/ua")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends UserActivityMessage> List<T> userActivity(ReportQueryMessage msg) {
		logger.info("Generating user activity report");
		List<T> ret = new ArrayList<T>();
		List vals = ipUserActivityDAO.initializeAll();
		for (Object obj : vals) {
			if (obj == null)
				continue;
			IpUserActivity val = (IpUserActivity) obj;
			if (((msg != null) && (msg.getGrpId() != null)) && !(val.getId().getGroupName().equalsIgnoreCase(ipGroupDAO.findById(msg.getGrpId()).getGroupName())))
				continue;
			UserActivityMessage uamsg = new UserActivityMessage();
			uamsg.setCntb(val.getId().getCntb());
			uamsg.setCntc(val.getId().getCntc());
			uamsg.setDta(val.getId().getDta());
			uamsg.setDtb(val.getId().getDtb());
			uamsg.setGroupName(val.getId().getGroupName());
			uamsg.setUserFName(val.getId().getUserFName());
			uamsg.setUserLName(val.getId().getUserLName());
			ret.add((T) uamsg);
		}
		return ret;
	}

	@POST
	@Path("/gua")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends GroupUserActivityMessage> List<T> groupUserActivity(ReportQueryMessage msg) {
		logger.info("Generating group user activity report");
		List<T> ret = new ArrayList<T>();
		List vals = ipGroupUserActivityDAO.initializeAll();
		for (Object obj : vals) {
			if (obj == null)
				continue;
			IpGroupUserActivity val = (IpGroupUserActivity) obj;
			if (((msg != null) && (msg.getGrpId() != null)) && !(val.getId().getGroupName().equalsIgnoreCase(ipGroupDAO.findById(msg.getGrpId()).getGroupName())))
				continue;
			GroupUserActivityMessage guamsg = new GroupUserActivityMessage();
			guamsg.setCnta(val.getId().getCnta());
			guamsg.setCntb(val.getId().getCntb());
			guamsg.setCntc(val.getId().getCntc());
			guamsg.setGroupName(val.getId().getGroupName());
			guamsg.setTotcnt(val.getId().getTotcnt());
			ret.add((T) guamsg);
		}
		return ret;
	}

	@POST
	@Path("/rr")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends RewardsReportMessage> List<T> rewardsReport(ReportQueryMessage msg) {
		logger.info("Generating rewards report");
		List<T> ret = new ArrayList<T>();
		List clms = ipClaimDAO.initializeAll();
		for (Object obj : clms) {
			if (obj == null)
				continue;
			IpClaim clm = (IpClaim) obj;
			if (msg.getGrpId() != null && !(msg.getGrpId().longValue() == clm.getIpUser().getIpGroup().getGroupId().longValue()))
				continue;
			if (msg.getUsrId() != null && !(msg.getUsrId().longValue() == clm.getIpUser().getUserId().longValue()))
				continue;
			if (msg.getEndDate() != null && !((clm.getClaimCrtdDt().after(msg.getStartDate()) && (clm.getClaimCrtdDt().before(msg.getEndDate())))))
				continue;
			if (msg.getStartDate() != null && msg.getEndDate() == null && clm.getClaimCrtdDt().before(msg.getStartDate()))
				continue;
			RewardsReportMessage rmsg = new RewardsReportMessage();
			rmsg.setClmDt(clm.getClaimCrtdDt().toString());
			rmsg.setClmStatus(clm.getIpClaimStatus().getCsDesc());
			rmsg.setfName(clm.getIpUser().getUserFName());
			rmsg.setGroup(clm.getIpUser().getIpGroup().getGroupName());
			rmsg.setlName(clm.getIpUser().getUserLName());
			rmsg.setrDesc(clm.getIpRewards().getRwTitle());
			ret.add((T) rmsg);
		}
		return ret;
	}

	@POST
	@Path("/isg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public <T extends IdeaSummaryMessage> List<T> ideaSummary(ReportQueryMessage msg) {
		logger.info("Generating idea summary report");
		List<T> ret = new ArrayList<T>();
		List vals = ipIdeaGrpCntDAO.initializeAll();
		for (Object obj : vals) {
			if (obj == null)
				continue;
			IpIdeaGrpCnt val = (IpIdeaGrpCnt) obj;
			if (((msg != null) && (msg.getGrpId() != null)) && !(val.getId().getGroupName().equalsIgnoreCase(ipGroupDAO.findById(msg.getGrpId()).getGroupName())))
				continue;
			IdeaSummaryMessage ismsg = new IdeaSummaryMessage();
			ismsg.setCnta(val.getId().getCnta());
			ismsg.setCntb(val.getId().getCntb());
			ismsg.setCntc(val.getId().getCntc());
			ismsg.setCntd(val.getId().getCntd());
			ismsg.setGroupName(val.getId().getGroupName());
			ret.add((T) ismsg);
		}
		return ret;
	}

	public IpClaimDAO getIpClaimDAO() {
		return ipClaimDAO;
	}

	public void setIpClaimDAO(IpClaimDAO ipClaimDAO) {
		this.ipClaimDAO = ipClaimDAO;
	}

	public IpUserActivityDAO getIpUserActivityDAO() {
		return ipUserActivityDAO;
	}

	public void setIpUserActivityDAO(IpUserActivityDAO ipUserActivityDAO) {
		this.ipUserActivityDAO = ipUserActivityDAO;
	}

	public IpGroupUserActivityDAO getIpGroupUserActivityDAO() {
		return ipGroupUserActivityDAO;
	}

	public void setIpGroupUserActivityDAO(IpGroupUserActivityDAO ipGroupUserActivityDAO) {
		this.ipGroupUserActivityDAO = ipGroupUserActivityDAO;
	}

	public IpIdeaGrpCntDAO getIpIdeaGrpCntDAO() {
		return ipIdeaGrpCntDAO;
	}

	public void setIpIdeaGrpCntDAO(IpIdeaGrpCntDAO ipIdeaGrpCntDAO) {
		this.ipIdeaGrpCntDAO = ipIdeaGrpCntDAO;
	}

	public IpGroupDAO getIpGroupDAO() {
		return ipGroupDAO;
	}

	public void setIpGroupDAO(IpGroupDAO ipGroupDAO) {
		this.ipGroupDAO = ipGroupDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}
}
