package za.co.idea.ip.jobs;

import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpSolutionDAO;

public class ChallengeExpiryJob {
	private IpChallengeDAO ipChallengeDAO;
	private IpSolutionDAO ipSolutionDAO;

	public void executeInternal() {
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
