package za.co.idea.ip.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpSolutionDAO;

public class ChallengeExpiryJob extends QuartzJobBean implements StatefulJob {
	private IpChallengeDAO ipChallengeDAO;
	private IpSolutionDAO ipSolutionDAO;

	@Transactional(propagation = Propagation.NESTED)
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		ipChallengeDAO.updateStatusOnExpiry();
		ipSolutionDAO.updateStatusOnExpiry();
	}

	public IpChallengeDAO getIpChallengeDAO() {
		return ipChallengeDAO;
	}

	public void setIpChallengeDAO(IpChallengeDAO ipChallengeDAO) {
		this.ipChallengeDAO = ipChallengeDAO;
	}

	public IpSolutionDAO getIpSolutionDAO() {
		return ipSolutionDAO;
	}

	public void setIpSolutionDAO(IpSolutionDAO ipSolutionDAO) {
		this.ipSolutionDAO = ipSolutionDAO;
	}

}
