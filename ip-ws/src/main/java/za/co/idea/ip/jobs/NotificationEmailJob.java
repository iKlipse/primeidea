package za.co.idea.ip.jobs;

import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpGroupUser;
import za.co.idea.ip.orm.bean.IpNotif;
import za.co.idea.ip.orm.bean.IpNotifGroup;
import za.co.idea.ip.orm.dao.IpGroupUserDAO;
import za.co.idea.ip.orm.dao.IpNotifDAO;
import za.co.idea.ip.orm.dao.IpNotifGroupDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;

@SuppressWarnings("rawtypes")
public class NotificationEmailJob extends QuartzJobBean implements StatefulJob {
	private IpNotifDAO ipNotifDAO;
	private IpNotifGroupDAO ipNotifGroupDAO;
	private IpGroupUserDAO ipGroupUserDAO;
	private IpUserDAO ipUserDAO;
	private MailSender sender;
	private static final Logger logger = Logger.getLogger(NotificationEmailJob.class);

	@Transactional(propagation = Propagation.REQUIRED)
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		try {
			List notifications = ipNotifDAO.findAll();
			for (Object object : notifications) {
				IpNotif ipNotif = (IpNotif) object;
				if (ipNotif.getNotifList() == null || ipNotif.getNotifList().length() == 0) {
					List notifList = ipNotifGroupDAO.findByIngNotifId((Object) ipNotif.getNotifId());
					boolean delete = false;
					if (notifList != null && !notifList.isEmpty()) {
						for (Object obj : notifList) {
							IpNotifGroup group = (IpNotifGroup) obj;
							List groupList = ipGroupUserDAO.fetchByGroupId(group.getIngGrpId());
							for (Object usr : groupList) {
								IpGroupUser igu = (IpGroupUser) usr;
								SimpleMailMessage message = new SimpleMailMessage();
								message.setFrom("noreply@primedia.co.za");
								message.setText(ipNotif.getNotifBody());
								message.setSubject(ipNotif.getNotifSubject());
								message.setTo(igu.getIpUser().getUserEmail());
								try {
									sender.send(message);
									delete = true;
								} catch (Exception ex) {
									logger.error("Error: " + ex.getMessage());
								}
							}
						}
						if (delete) {
							ipNotifGroupDAO.deleteByNotifId(ipNotif.getNotifId());
						}
					} else {
						SimpleMailMessage message = new SimpleMailMessage();
						message.setFrom("noreply@primedia.co.za");
						message.setText(ipNotif.getNotifBody());
						message.setSubject(ipNotif.getNotifSubject());
						message.setTo(StringUtils.split(ipNotif.getNotifList(), ";"));
						try {
							sender.send(message);
							delete = true;
						} catch (Exception ex) {
							logger.error("Error: " + ex.getMessage());
						}
					}
					if (delete) {
						ipNotifDAO.deleteByNotifId(ipNotif.getNotifId());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IpGroupUserDAO getIpGroupUserDAO() {
		return ipGroupUserDAO;
	}

	public void setIpGroupUserDAO(IpGroupUserDAO ipGroupUserDAO) {
		this.ipGroupUserDAO = ipGroupUserDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpNotifDAO getIpNotifDAO() {
		return ipNotifDAO;
	}

	public void setIpNotifDAO(IpNotifDAO ipNotifDAO) {
		this.ipNotifDAO = ipNotifDAO;
	}

	public IpNotifGroupDAO getIpNotifGroupDAO() {
		return ipNotifGroupDAO;
	}

	public void setIpNotifGroupDAO(IpNotifGroupDAO ipNotifGroupDAO) {
		this.ipNotifGroupDAO = ipNotifGroupDAO;
	}

	public MailSender getSender() {
		return sender;
	}

	public void setSender(MailSender sender) {
		this.sender = sender;
	}

}
