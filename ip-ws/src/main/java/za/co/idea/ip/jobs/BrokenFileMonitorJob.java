package za.co.idea.ip.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.dao.IpBlobDAO;

public class BrokenFileMonitorJob extends QuartzJobBean implements StatefulJob {
	private IpBlobDAO ipBlobDAO;

	@Transactional(propagation = Propagation.NESTED)
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
	}

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

}
