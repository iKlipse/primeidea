package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import za.co.idea.ip.orm.bean.IpIdea;
import za.co.idea.ip.orm.bean.IpIdeaCat;
import za.co.idea.ip.orm.bean.IpIdeaGroup;
import za.co.idea.ip.orm.bean.IpIdeaStatus;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpIdeaCatDAO;
import za.co.idea.ip.orm.dao.IpIdeaDAO;
import za.co.idea.ip.orm.dao.IpIdeaGroupDAO;
import za.co.idea.ip.orm.dao.IpIdeaStatusDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/is")
public class IdeaService {
	private IpIdeaDAO ipIdeaDAO;
	private IpIdeaCatDAO ipIdeaCatDAO;
	private IpIdeaStatusDAO ipIdeaStatusDAO;
	private IpUserDAO ipUserDAO;
	private IpIdeaGroupDAO ipIdeaGroupDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpGroupDAO ipGroupDAO;

	@GET
	@Path("/idea/cat/list")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listIdeaCat() {
		List<T> ret = new ArrayList<T>();
		try {
			List ideaCats = ipIdeaCatDAO.findAll();
			for (Object object : ideaCats) {
				IpIdeaCat cat = (IpIdeaCat) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(cat.getIcId());
				message.setDesc(cat.getIcDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/idea/status/list")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listIdeaStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List ideaStatuses = ipIdeaStatusDAO.findAll();
			for (Object object : ideaStatuses) {
				IpIdeaStatus status = (IpIdeaStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getIsId());
				message.setDesc(status.getIsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/idea/status/list/{curr}")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listNextIdeaStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideaStatuses = ipIdeaStatusDAO.findNext(curr);
			for (Object object : ideaStatuses) {
				IpIdeaStatus status = (IpIdeaStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getIsId());
				message.setDesc(status.getIsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/idea/cat/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getIdeaCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpIdeaCat cat = ipIdeaCatDAO.findById(id);
			message.setId(cat.getIcId());
			message.setDesc(cat.getIcDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/idea/status/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getIdeaStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpIdeaStatus status = ipIdeaStatusDAO.findById(id);
			message.setId(status.getIsId());
			message.setDesc(status.getIsDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@POST
	@Path("/idea/add")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage createIdea(IdeaMessage idea) {
		IpIdea ipIdea = new IpIdea();
		ipIdea.setIdeaId(idea.getIdeaId());
		ipIdea.setIdeaBa(idea.getIdeaBa());
		ipIdea.setIdeaDate(idea.getCrtdDate());
		ipIdea.setIdeaDesc(idea.getIdeaDesc());
		ipIdea.setIdeaTag(idea.getIdeaTag());
		ipIdea.setIdeaTitle(idea.getIdeaTitle());
		if (idea.getSelCatId() != null && idea.getSelCatId().longValue() >= 0)
			ipIdea.setIpIdeaCat(ipIdeaCatDAO.findById(idea.getSelCatId().intValue()));
		if (idea.getSetStatusId() != null && idea.getSetStatusId().longValue() >= 0)
			ipIdea.setIpIdeaStatus(ipIdeaStatusDAO.findById(idea.getSetStatusId().intValue()));
		if (idea.getCrtdById() != null && idea.getCrtdById().longValue() >= 0)
			ipIdea.setIpUser(ipUserDAO.findById(idea.getCrtdById()));
		try {
			ipIdeaDAO.save(ipIdea);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			if (idea.getGroupIdList() != null && idea.getGroupIdList().length > 0)
				for (Long gId : idea.getGroupIdList()) {
					IpIdeaGroup ipIdeaGroup = new IpIdeaGroup();
					ipIdeaGroup.setIgId(ipNativeSQLDAO.getNextId(IpIdeaGroup.class));
					ipIdeaGroup.setIpIdea(ipIdea);
					ipIdeaGroup.setIpGroup(ipGroupDAO.findById(gId));
					ipIdeaGroupDAO.save(ipIdeaGroup);
				}
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@PUT
	@Path("/idea/modify")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage updateIdea(IdeaMessage idea) {
		IpIdea ipIdea = new IpIdea();
		ipIdea.setIdeaId(idea.getIdeaId());
		ipIdea.setIdeaBa(idea.getIdeaBa());
		ipIdea.setIdeaDate(idea.getCrtdDate());
		ipIdea.setIdeaDesc(idea.getIdeaDesc());
		ipIdea.setIdeaTag(idea.getIdeaTag());
		ipIdea.setIdeaTitle(idea.getIdeaTitle());
		if (idea.getSelCatId() != null && idea.getSelCatId().longValue() >= 0)
			ipIdea.setIpIdeaCat(ipIdeaCatDAO.findById(idea.getSelCatId().intValue()));
		if (idea.getSetStatusId() != null && idea.getSetStatusId().longValue() >= 0)
			ipIdea.setIpIdeaStatus(ipIdeaStatusDAO.findById(idea.getSetStatusId().intValue()));
		if (idea.getCrtdById() != null && idea.getCrtdById().longValue() >= 0)
			ipIdea.setIpUser(ipUserDAO.findById(idea.getCrtdById()));
		try {
			ipIdeaDAO.merge(ipIdea);
			ResponseMessage message = new ResponseMessage();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			ipIdeaGroupDAO.deleteByIdeaId(idea.getIdeaId());
			if (idea.getGroupIdList() != null && idea.getGroupIdList().length > 0)
				for (Long gId : idea.getGroupIdList()) {
					IpIdeaGroup ipIdeaGroup = new IpIdeaGroup();
					ipIdeaGroup.setIgId(ipNativeSQLDAO.getNextId(IpIdeaGroup.class));
					ipIdeaGroup.setIpIdea(ipIdea);
					ipIdeaGroup.setIpGroup(ipGroupDAO.findById(gId));
					ipIdeaGroupDAO.save(ipIdeaGroup);
				}
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
	@Path("/idea/list")
	@Produces("application/json")
	public <T extends IdeaMessage> List<T> listIdea() {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findAll();
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = new IdeaMessage();
				idea.setIdeaId(ipIdea.getIdeaId());
				idea.setIdeaBa(ipIdea.getIdeaBa());
				idea.setCrtdDate(ipIdea.getIdeaDate());
				idea.setIdeaDesc(ipIdea.getIdeaDesc());
				idea.setIdeaTag(ipIdea.getIdeaTag());
				idea.setIdeaTitle(ipIdea.getIdeaTitle());
				if (ipIdea.getIpIdeaCat() != null)
					idea.setSelCatId(ipIdea.getIpIdeaCat().getIcId().longValue());
				if (ipIdea.getIpIdeaStatus() != null)
					idea.setSetStatusId(ipIdea.getIpIdeaStatus().getIsId().longValue());
				if (ipIdea.getIpUser() != null)
					idea.setCrtdById(ipIdea.getIpUser().getUserId());
				List val = ipIdeaGroupDAO.fetchByIdeaId(ipIdea.getIdeaId());
				if (val != null) {
					Long[] grps = new Long[val.size()];
					int i = 0;
					for (Object obj : val) {
						grps[i] = ((IpIdeaGroup) obj).getIpGroup().getGroupId();
						i++;
					}
					idea.setGroupIdList(grps);
				}

				ret.add((T) idea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/idea/list/{id}")
	@Produces("application/json")
	public <T extends IdeaMessage> List<T> listIdeaByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List ideas = ipIdeaDAO.findByUserId(id);
			for (Object object : ideas) {
				IpIdea ipIdea = (IpIdea) object;
				IdeaMessage idea = new IdeaMessage();
				idea.setIdeaId(ipIdea.getIdeaId());
				idea.setIdeaBa(ipIdea.getIdeaBa());
				idea.setCrtdDate(ipIdea.getIdeaDate());
				idea.setIdeaDesc(ipIdea.getIdeaDesc());
				idea.setIdeaTag(ipIdea.getIdeaTag());
				idea.setIdeaTitle(ipIdea.getIdeaTitle());
				if (ipIdea.getIpIdeaCat() != null)
					idea.setSelCatId(ipIdea.getIpIdeaCat().getIcId().longValue());
				if (ipIdea.getIpIdeaStatus() != null)
					idea.setSetStatusId(ipIdea.getIpIdeaStatus().getIsId().longValue());
				if (ipIdea.getIpUser() != null)
					idea.setCrtdById(ipIdea.getIpUser().getUserId());
				ret.add((T) idea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/idea/get/{id}")
	@Produces("application/json")
	public IdeaMessage getIdea(@PathParam("id") Long id) {
		IdeaMessage idea = new IdeaMessage();
		try {
			IpIdea ipIdea = ipIdeaDAO.findById(id);
			idea.setIdeaId(ipIdea.getIdeaId());
			idea.setIdeaBa(ipIdea.getIdeaBa());
			idea.setCrtdDate(ipIdea.getIdeaDate());
			idea.setIdeaDesc(ipIdea.getIdeaDesc());
			idea.setIdeaTag(ipIdea.getIdeaTag());
			idea.setIdeaTitle(ipIdea.getIdeaTitle());
			idea.setIdeaTitle(ipIdea.getIdeaTitle());
			if (ipIdea.getIpIdeaCat() != null)
				idea.setSelCatId(ipIdea.getIpIdeaCat().getIcId().longValue());
			if (ipIdea.getIpIdeaStatus() != null)
				idea.setSetStatusId(ipIdea.getIpIdeaStatus().getIsId().longValue());
			if (ipIdea.getIpUser() != null)
				idea.setCrtdById(ipIdea.getIpUser().getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idea;
	}

	public IpIdeaDAO getIpIdeaDAO() {
		return ipIdeaDAO;
	}

	public void setIpIdeaDAO(IpIdeaDAO ipIdeaDAO) {
		this.ipIdeaDAO = ipIdeaDAO;
	}

	public IpIdeaCatDAO getIpIdeaCatDAO() {
		return ipIdeaCatDAO;
	}

	public void setIpIdeaCatDAO(IpIdeaCatDAO ipIdeaCatDAO) {
		this.ipIdeaCatDAO = ipIdeaCatDAO;
	}

	public IpIdeaStatusDAO getIpIdeaStatusDAO() {
		return ipIdeaStatusDAO;
	}

	public void setIpIdeaStatusDAO(IpIdeaStatusDAO ipIdeaStatusDAO) {
		this.ipIdeaStatusDAO = ipIdeaStatusDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpIdeaGroupDAO getIpIdeaGroupDAO() {
		return ipIdeaGroupDAO;
	}

	public void setIpIdeaGroupDAO(IpIdeaGroupDAO ipIdeaGroupDAO) {
		this.ipIdeaGroupDAO = ipIdeaGroupDAO;
	}

	public IpNativeSQLDAO getIpNativeSQLDAO() {
		return ipNativeSQLDAO;
	}

	public void setIpNativeSQLDAO(IpNativeSQLDAO ipNativeSQLDAO) {
		this.ipNativeSQLDAO = ipNativeSQLDAO;
	}

	public IpGroupDAO getIpGroupDAO() {
		return ipGroupDAO;
	}

	public void setIpGroupDAO(IpGroupDAO ipGroupDAO) {
		this.ipGroupDAO = ipGroupDAO;
	}
}
