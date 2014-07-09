package za.co.idea.ip.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import za.co.idea.ip.orm.dao.IpChallengeDAO;

public class ChallengeExpiryJob extends QuartzJobBean implements StatefulJob {
	private IpChallengeDAO ipChallengeDAO;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		ipChallengeDAO.updateStatusOnExpiry();
	}

	public IpChallengeDAO getIpChallengeDAO() {
		return ipChallengeDAO;
	}

	public void setIpChallengeDAO(IpChallengeDAO ipChallengeDAO) {
		this.ipChallengeDAO = ipChallengeDAO;
	}

}
