package za.co.idea.ip.jobs;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import za.co.idea.ip.orm.bean.IpGroupUser;
import za.co.idea.ip.orm.bean.IpReview;
import za.co.idea.ip.orm.dao.IpGroupUserDAO;
import za.co.idea.ip.orm.dao.IpReviewDAO;

@SuppressWarnings({ "rawtypes" })
public class AllocateReviewUserJob extends QuartzJobBean implements StatefulJob {
	private IpReviewDAO ipReviewDAO;
	private IpGroupUserDAO ipGroupUserDAO;

	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		List unalloc = ipReviewDAO.findUnAllocatedReviews();
		for (Object object : unalloc) {
			TreeMap<Integer, TreeSet<Long>> userCnts = new TreeMap<Integer, TreeSet<Long>>();
			IpReview ipReview = (IpReview) object;
			List igus = ipGroupUserDAO.fetchByGroupId(ipReview.getIpGroup().getGroupId());
			for (Object objectA : igus) {
				IpGroupUser igu = (IpGroupUser) objectA;
				List selRevs = ipReviewDAO.findReviewsByUserId(igu.getIpUser().getUserId());
				if (selRevs == null || selRevs.size() == 0) {
					TreeSet<Long> userCnt = userCnts.get(0);
					if (userCnt == null)
						userCnt = new TreeSet<Long>();
					userCnt.add(igu.getIpUser().getUserId());
				} else {
					TreeSet<Long> userCnt = userCnts.get(selRevs.size());
					if (userCnt == null)
						userCnt = new TreeSet<Long>();
					userCnt.add(igu.getIpUser().getUserId());
				}
			}
			Long selUserId = userCnts.get(userCnts.keySet().iterator().next()).iterator().next();
			ipReviewDAO.updateReviewer(ipReview.getRevEntityId(), ipReview.getRevEntityName(), selUserId);
		}
	}

	public IpReviewDAO getIpReviewDAO() {
		return ipReviewDAO;
	}

	public void setIpReviewDAO(IpReviewDAO ipReviewDAO) {
		this.ipReviewDAO = ipReviewDAO;
	}

	public IpGroupUserDAO getIpGroupUserDAO() {
		return ipGroupUserDAO;
	}

	public void setIpGroupUserDAO(IpGroupUserDAO ipGroupUserDAO) {
		this.ipGroupUserDAO = ipGroupUserDAO;
	}

}
