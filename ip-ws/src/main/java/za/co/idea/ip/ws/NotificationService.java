package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpNotif;
import za.co.idea.ip.orm.bean.IpNotifGroup;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpNotifDAO;
import za.co.idea.ip.orm.dao.IpNotifGroupDAO;
import za.co.idea.ip.ws.bean.NotificationMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/nos")
public class NotificationService {

	private IpNotifDAO ipNotifDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpNotifGroupDAO ipNotifGroupDAO;
	private IpBlobDAO ipBlobDAO;

	@POST
	@Path("/notif/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage createNotification(NotificationMessage notif) {
		try {
			IpNotif ipNotif = new IpNotif();
			ipNotif.setNotifAttach(notif.getNotifAttach());
			ipNotif.setNotifBody(notif.getNotifBody());
			ipNotif.setNotifCrtdDate(notif.getNotifCrtdDate());
			ipNotif.setNotifEntityId(notif.getNotifEntityId());
			ipNotif.setNotifEntityTblName(notif.getNotifEntityTblName());
			ipNotif.setNotifId(notif.getNotifId());
			ipNotif.setNotifStatus(notif.getNotifStatus());
			ipNotif.setNotifSubject(notif.getNotifSubject());
			ipNotifDAO.save(ipNotif);
			if (notif.getGroupIdList() != null && notif.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpNotifGroup.class, notif.getGroupIdList().length);
				int i = 0;
				if (notif.getGroupIdList() != null && notif.getGroupIdList().length > 0)
					for (Long gId : notif.getGroupIdList()) {
						IpNotifGroup ipNotifGroup = new IpNotifGroup();
						ipNotifGroup.setIngId(ids[i]);
						ipNotifGroup.setIngGrpId(gId);
						ipNotifGroup.setIngNotifId(notif.getNotifId());
						ipNotifGroupDAO.save(ipNotifGroup);
						i++;
					}
			}
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@DELETE
	@Path("/notif/delete")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage deleteNotification(NotificationMessage notif) {
		try {
			IpNotif ipNotif = new IpNotif();
			ipNotif.setNotifAttach(notif.getNotifAttach());
			ipNotif.setNotifBody(notif.getNotifBody());
			ipNotif.setNotifCrtdDate(notif.getNotifCrtdDate());
			ipNotif.setNotifEntityId(notif.getNotifEntityId());
			ipNotif.setNotifEntityTblName(notif.getNotifEntityTblName());
			ipNotif.setNotifId(notif.getNotifId());
			ipNotif.setNotifStatus(notif.getNotifStatus());
			ipNotif.setNotifSubject(notif.getNotifSubject());
			ipNotifDAO.delete(ipNotif);
			ipNotifGroupDAO.deleteByNotifId(notif.getNotifId());
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@GET
	@Path("/notif/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends NotificationMessage> List<T> listNotification() {
		List<T> ret = new ArrayList<T>();
		try {
			List notifications = ipNotifDAO.findAll();
			for (Object object : notifications) {
				IpNotif ipNotif = (IpNotif) object;
				NotificationMessage notif = new NotificationMessage();
				notif.setNotifAttach(ipNotif.getNotifAttach());
				notif.setNotifBody(ipNotif.getNotifBody());
				notif.setNotifCrtdDate(ipNotif.getNotifCrtdDate());
				notif.setNotifEntityId(ipNotif.getNotifEntityId());
				notif.setNotifEntityTblName(ipNotif.getNotifEntityTblName());
				notif.setNotifId(ipNotif.getNotifId());
				notif.setNotifStatus(ipNotif.getNotifStatus());
				notif.setNotifSubject(ipNotif.getNotifSubject());
				List val = ipNotifGroupDAO.fetchByNotifId(ipNotif.getNotifId());
				if (val != null) {
					Long[] grps = new Long[val.size()];
					int i = 0;
					for (Object obj : val) {
						grps[i] = ((IpNotifGroup) obj).getIngGrpId();
						i++;
					}
					notif.setGroupIdList(grps);
				}
				ret.add((T) notif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public IpNotifDAO getIpNotifDAO() {
		return ipNotifDAO;
	}

	public void setIpNotifDAO(IpNotifDAO ipNotifDAO) {
		this.ipNotifDAO = ipNotifDAO;
	}

	public IpNativeSQLDAO getIpNativeSQLDAO() {
		return ipNativeSQLDAO;
	}

	public void setIpNativeSQLDAO(IpNativeSQLDAO ipNativeSQLDAO) {
		this.ipNativeSQLDAO = ipNativeSQLDAO;
	}

	public IpNotifGroupDAO getIpNotifGroupDAO() {
		return ipNotifGroupDAO;
	}

	public void setIpNotifGroupDAO(IpNotifGroupDAO ipNotifGroupDAO) {
		this.ipNotifGroupDAO = ipNotifGroupDAO;
	}

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

}
