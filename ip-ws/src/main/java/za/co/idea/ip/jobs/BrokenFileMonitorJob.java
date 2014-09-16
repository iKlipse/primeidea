package za.co.idea.ip.jobs;

import za.co.idea.ip.orm.dao.IpBlobDAO;

public class BrokenFileMonitorJob {
	private IpBlobDAO ipBlobDAO;

	public void executeInternal() {
	}

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

}
