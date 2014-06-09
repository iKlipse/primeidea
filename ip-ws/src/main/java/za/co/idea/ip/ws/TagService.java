package za.co.idea.ip.ws;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import za.co.idea.ip.orm.bean.IpTag;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpIdeaDAO;
import za.co.idea.ip.orm.dao.IpSolutionDAO;
import za.co.idea.ip.orm.dao.IpTagDAO;
import za.co.idea.ip.orm.dao.IpTagEntityTypeDAO;
import za.co.idea.ip.orm.dao.IpTagTypeDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.TagMessage;

@SuppressWarnings("unchecked")
@Path(value = "/ts")
public class TagService {
	private IpTagDAO ipTagDAO;
	private IpTagEntityTypeDAO ipTagEntityTypeDAO;
	private IpTagTypeDAO ipTagTypeDAO;
	private IpUserDAO ipUserDAO;
	private IpIdeaDAO ipIdeaDAO;
	private IpChallengeDAO ipChallengeDAO;
	private IpSolutionDAO ipSolutionDAO;

	@GET
	@Path("/tag/get/{entityId}/{teId}/{ttId}")
	@Produces("application/json")
	public <T extends TagMessage> List<T> getTag(@PathParam("entityId") Long entityId, @PathParam("teId") Integer teId, @PathParam("ttId") Integer ttId) {
		List<T> ret = new ArrayList<T>();
		try {
			List<IpTag> tags = ipTagDAO.getTagByFilterA(entityId, teId, ttId);
			for (IpTag ipTag : tags) {
				TagMessage message = new TagMessage();
				message.setEntityId(entityId);
				message.setTeId(teId);
				message.setTtId(ttId);
				message.setTagText(ipTag.getTagText());
				message.setUsrScreenName(ipTag.getIpUser().getUserScreenName());
				message.setUserFullName(ipTag.getIpUser().getUserFName() + " " + ((ipTag.getIpUser().getUserMName() != null && ipTag.getIpUser().getUserMName().length() > 0) ? (ipTag.getIpUser().getUserMName() + " ") : "") + ipTag.getIpUser().getUserLName());
				message.setUserId(ipTag.getIpUser().getUserId());
				message.setTagId(ipTag.getTagId());
				message.setTagDate(ipTag.getTagDate());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/tag/getByUser/{entityId}/{teId}/{ttId}/{userId}")
	@Produces("application/json")
	public <T extends TagMessage> List<T> getTagByUser(@PathParam("entityId") Long entityId, @PathParam("teId") Integer teId, @PathParam("ttId") Integer ttId, @PathParam("userId") Long userId) {
		List<T> ret = new ArrayList<T>();
		try {
			List<IpTag> tags = ipTagDAO.getTagByFilterB(entityId, teId, ttId, userId);
			for (IpTag ipTag : tags) {
				TagMessage message = new TagMessage();
				message.setEntityId(entityId);
				message.setTeId(teId);
				message.setTtId(ttId);
				message.setTagText(ipTag.getTagText());
				message.setUsrScreenName(ipTag.getIpUser().getUserScreenName());
				message.setUserFullName(ipTag.getIpUser().getUserFName() + " " + ((ipTag.getIpUser().getUserMName() != null && ipTag.getIpUser().getUserMName().length() > 0) ? (ipTag.getIpUser().getUserMName() + " ") : "") + ipTag.getIpUser().getUserLName());
				message.setUserId(ipTag.getIpUser().getUserId());
				message.setTagId(ipTag.getTagId());
				message.setTagDate(ipTag.getTagDate());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@POST
	@Path("/tag/add")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage createTag(TagMessage tag) {
		try {
			if (!tag.isDuplicate() && ipTagDAO.getTagByFilterB(tag.getEntityId(), tag.getTeId(), tag.getTtId(), tag.getUserId()).size() != 0) {
				ResponseMessage message = new ResponseMessage();
				message.setStatusCode(1);
				message.setStatusDesc("Tag Already exists. Cannot Create Duplicate");
				return message;
			} else {
				IpTag ipTag = new IpTag();
				ipTag.setIpTagEntityType(ipTagEntityTypeDAO.findById(tag.getTeId()));
				ipTag.setIpTagType(ipTagTypeDAO.findById(tag.getTtId()));
				ipTag.setIpUser(ipUserDAO.findById(tag.getUserId()));
				ipTag.setTagEntityId(tag.getEntityId());
				ipTag.setTagId(tag.getTagId());
				ipTag.setTagText(tag.getTagText());
				ipTag.setTagDate(new Timestamp(System.currentTimeMillis()));
				ipTagDAO.save(ipTag);
				ResponseMessage message = new ResponseMessage();
				message.setStatusCode(0);
				message.setStatusDesc("Success");
				return message;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@POST
	@Path("/tag/del")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage deleteTag(Long tagId) {
		try {
			ipTagDAO.delete(ipTagDAO.findById(tagId));
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

	public IpTagDAO getIpTagDAO() {
		return ipTagDAO;
	}

	public void setIpTagDAO(IpTagDAO ipTagDAO) {
		this.ipTagDAO = ipTagDAO;
	}

	public IpTagEntityTypeDAO getIpTagEntityTypeDAO() {
		return ipTagEntityTypeDAO;
	}

	public void setIpTagEntityTypeDAO(IpTagEntityTypeDAO ipTagEntityTypeDAO) {
		this.ipTagEntityTypeDAO = ipTagEntityTypeDAO;
	}

	public IpTagTypeDAO getIpTagTypeDAO() {
		return ipTagTypeDAO;
	}

	public void setIpTagTypeDAO(IpTagTypeDAO ipTagTypeDAO) {
		this.ipTagTypeDAO = ipTagTypeDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
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

	public IpSolutionDAO getIpSolutionDAO() {
		return ipSolutionDAO;
	}

	public void setIpSolutionDAO(IpSolutionDAO ipSolutionDAO) {
		this.ipSolutionDAO = ipSolutionDAO;
	}

}
