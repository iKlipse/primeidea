package za.co.idea.ip.jobs;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import za.co.idea.ip.orm.bean.IpGroupUser;
import za.co.idea.ip.orm.bean.IpReview;
import za.co.idea.ip.orm.dao.IpGroupUserDAO;
import za.co.idea.ip.orm.dao.IpReviewDAO;

@SuppressWarnings({ "rawtypes" })
public class AllocateReviewUserJob {
	private static final Logger logger = Logger.getLogger(AllocateReviewUserJob.class);
	private IpReviewDAO ipReviewDAO;
	private IpGroupUserDAO ipGroupUserDAO;

	public void executeInternal() {
		List unalloc = ipReviewDAO.findUnAllocatedReviews();
		if (unalloc == null)
			logger.info("No allocations to be processed");
		else {
			logger.info("processing :: " + unalloc.size() + " review allocations");
			try {
				for (Object object : unalloc) {
					try {
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
								userCnts.put(0, userCnt);
							} else {
								TreeSet<Long> userCnt = userCnts.get(selRevs.size());
								if (userCnt == null)
									userCnt = new TreeSet<Long>();
								userCnt.add(igu.getIpUser().getUserId());
								userCnts.put(selRevs.size(), userCnt);
							}
						}
						if (userCnts.keySet().iterator().hasNext() && userCnts.get(userCnts.keySet().iterator().next()).iterator().hasNext()) {
							Long selUserId = userCnts.get(userCnts.keySet().iterator().next()).iterator().next();
							ipReviewDAO.updateReviewer(ipReview.getRevEntityId(), ipReview.getRevEntityName(), selUserId);
						}
					} catch (Exception e) {
						logger.error(e, e);
					}
				}
			} catch (Exception e) {
				logger.error(e, e);
			}
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
