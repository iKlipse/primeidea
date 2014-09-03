package za.co.idea.ip.jobs;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpChallenge;
import za.co.idea.ip.orm.bean.IpIdea;
import za.co.idea.ip.orm.bean.IpSolution;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpIdeaDAO;
import za.co.idea.ip.orm.dao.IpSolutionDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;

@SuppressWarnings("rawtypes")
public class UserNotificationsJob extends QuartzJobBean implements StatefulJob {
	private IpChallengeDAO ipChallengeDAO;
	private IpSolutionDAO ipSolutionDAO;
	private IpIdeaDAO ipIdeaDAO;
	private IpUserDAO ipUserDAO;
	private MailSender sender;

	@Transactional(propagation = Propagation.REQUIRED)
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		List ideas = ipIdeaDAO.findByStatusId(1);
		for (Object obj : ideas) {
			IpIdea idea = (IpIdea) obj;
			SimpleMailMessage message = new SimpleMailMessage();
			message.setText("Idea in Draft Status");
			message.setSubject("Idea '" + idea.getIdeaTitle() + "' is in Draft Status");
			message.setTo(idea.getIpUserByIdeaUserId().getUserEmail());
			sender.send(message);
		}

		List challenges = ipChallengeDAO.findByStatusId(1);
		for (Object obj : challenges) {
			IpChallenge challenge = (IpChallenge) obj;
			SimpleMailMessage message = new SimpleMailMessage();
			message.setText("Challenge in Draft Status");
			message.setSubject("Challenge '" + challenge.getChalTitle() + "' is in Draft Status");
			message.setTo(challenge.getIpUserByChalCrtdBy().getUserEmail());
			sender.send(message);
		}

		List solutions = ipSolutionDAO.findByStatusId(1);
		for (Object obj : solutions) {
			IpSolution solution = (IpSolution) obj;
			SimpleMailMessage message = new SimpleMailMessage();
			message.setText("Solution in Draft Status");
			message.setSubject("Solution '" + solution.getSolTitle() + "' is in Draft Status");
			message.setTo(solution.getIpUser().getUserEmail());
			sender.send(message);
		}
	}

	public IpIdeaDAO getIpIdeaDAO() {
		return ipIdeaDAO;
	}

	public void setIpIdeaDAO(IpIdeaDAO ipIdeaDAO) {
		this.ipIdeaDAO = ipIdeaDAO;
	}

	public IpChallengeDAO getIpChallengeDAO() {
		return ipChallengeDAO;
	}

	public void setIpChallengeDAO(IpChallengeDAO ipChallengeDAO) {
		this.ipChallengeDAO = ipChallengeDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public MailSender getSender() {
		return sender;
	}

	public void setSender(MailSender sender) {
		this.sender = sender;
	}

	public IpSolutionDAO getIpSolutionDAO() {
		return ipSolutionDAO;
	}

	public void setIpSolutionDAO(IpSolutionDAO ipSolutionDAO) {
		this.ipSolutionDAO = ipSolutionDAO;
	}

}
