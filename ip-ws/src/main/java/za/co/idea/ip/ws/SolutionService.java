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

import za.co.idea.ip.orm.bean.IpSolution;
import za.co.idea.ip.orm.bean.IpSolutionCat;
import za.co.idea.ip.orm.bean.IpSolutionStatus;
import za.co.idea.ip.orm.dao.IpChallengeDAO;
import za.co.idea.ip.orm.dao.IpSolutionCatDAO;
import za.co.idea.ip.orm.dao.IpSolutionDAO;
import za.co.idea.ip.orm.dao.IpSolutionStatusDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/ss")
public class SolutionService {
	private IpSolutionDAO ipSolutionDAO;
	private IpSolutionCatDAO ipSolutionCatDAO;
	private IpSolutionStatusDAO ipSolutionStatusDAO;
	private IpUserDAO ipUserDAO;
	private IpChallengeDAO ipChallengeDAO;

	@POST
	@Path("/solution/add")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage createSolution(SolutionMessage solution) {
		IpSolution ipSolution = new IpSolution();
		try {
			ipSolution.setIpChallenge(ipChallengeDAO.findById(solution.getChalId()));
			ipSolution.setIpSolutionCat(ipSolutionCatDAO.findById(solution.getCatId()));
			ipSolution.setIpSolutionStatus(ipSolutionStatusDAO.findById(solution.getStatusId()));
			ipSolution.setIpUser(ipUserDAO.findById(solution.getCrtdById()));
			ipSolution.setSolCrtdDt(solution.getCrtdDt());
			ipSolution.setSolDesc(solution.getDesc());
			ipSolution.setSolId(solution.getId());
			ipSolution.setSolTags(solution.getTags());
			ipSolution.setSolTitle(solution.getTitle());
			ipSolutionDAO.save(ipSolution);
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

	@PUT
	@Path("/solution/modify")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseMessage updateSolution(SolutionMessage solution) {
		IpSolution ipSolution = new IpSolution();
		try {
			ipSolution.setIpChallenge(ipChallengeDAO.findById(solution.getChalId()));
			ipSolution.setIpSolutionCat(ipSolutionCatDAO.findById(solution.getCatId()));
			ipSolution.setIpSolutionStatus(ipSolutionStatusDAO.findById(solution.getStatusId()));
			ipSolution.setIpUser(ipUserDAO.findById(solution.getCrtdById()));
			ipSolution.setSolCrtdDt(solution.getCrtdDt());
			ipSolution.setSolDesc(solution.getDesc());
			ipSolution.setSolId(solution.getId());
			ipSolution.setSolTags(solution.getTags());
			ipSolution.setSolTitle(solution.getTitle());
			ipSolutionDAO.merge(ipSolution);
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
	@Path("/solution/list")
	@Produces("application/json")
	public <T extends SolutionMessage> List<T> listSolution() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findAll();
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/{id}")
	@Produces("application/json")
	public <T extends SolutionMessage> List<T> listSolutionByUser(@PathParam("id") Long id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByUserId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/list/status/{id}")
	@Produces("application/json")
	public <T extends SolutionMessage> List<T> listSolutionByStatus(@PathParam("id") Integer id) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutions = ipSolutionDAO.findByStatusId(id);
			for (Object object : solutions) {
				IpSolution ipSolution = (IpSolution) object;
				SolutionMessage solution = new SolutionMessage();
				solution.setChalId(ipSolution.getIpChallenge().getChalId());
				solution.setCatId(ipSolution.getIpSolutionCat().getScId());
				solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
				solution.setCrtdById(ipSolution.getIpUser().getUserId());
				solution.setCrtdDt(ipSolution.getSolCrtdDt());
				solution.setDesc(ipSolution.getSolDesc());
				solution.setId(ipSolution.getSolId());
				solution.setTags(ipSolution.getSolTags());
				solution.setTitle(ipSolution.getSolTitle());
				ret.add((T) solution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/cat/list")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listSolutionCat() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutionCats = ipSolutionCatDAO.findAll();
			for (Object object : solutionCats) {
				IpSolutionCat cat = (IpSolutionCat) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(cat.getScId());
				message.setDesc(cat.getScDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/status/list")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listSolutionStatus() {
		List<T> ret = new ArrayList<T>();
		try {
			List solutionStatuses = ipSolutionStatusDAO.findAll();
			for (Object object : solutionStatuses) {
				IpSolutionStatus status = (IpSolutionStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getSsId());
				message.setDesc(status.getSsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/status/list/{curr}")
	@Produces("application/json")
	public <T extends MetaDataMessage> List<T> listNextSolutionStatus(@PathParam("curr") Integer curr) {
		List<T> ret = new ArrayList<T>();
		try {
			List solutionStatuses = ipSolutionStatusDAO.findNext(curr);
			for (Object object : solutionStatuses) {
				IpSolutionStatus status = (IpSolutionStatus) object;
				MetaDataMessage message = new MetaDataMessage();
				message.setId(status.getSsId());
				message.setDesc(status.getSsDesc());
				ret.add((T) message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/solution/cat/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getSolutionCatById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpSolutionCat cat = ipSolutionCatDAO.findById(id);
			message.setId(cat.getScId());
			message.setDesc(cat.getScDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/solution/status/get/{id}")
	@Produces("application/json")
	public MetaDataMessage getSolutionStatusById(@PathParam("id") Integer id) {
		MetaDataMessage message = new MetaDataMessage();
		try {
			IpSolutionStatus status = ipSolutionStatusDAO.findById(id);
			message.setId(status.getSsId());
			message.setDesc(status.getSsDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@GET
	@Path("/solution/get/{id}")
	@Produces("application/json")
	public SolutionMessage getSolution(@PathParam("id") Long id) {
		SolutionMessage solution = new SolutionMessage();
		try {
			IpSolution ipSolution = ipSolutionDAO.findById(id);
			solution.setChalId(ipSolution.getIpChallenge().getChalId());
			solution.setCatId(ipSolution.getIpSolutionCat().getScId());
			solution.setStatusId(ipSolution.getIpSolutionStatus().getSsId());
			solution.setCrtdById(ipSolution.getIpUser().getUserId());
			solution.setCrtdDt(ipSolution.getSolCrtdDt());
			solution.setDesc(ipSolution.getSolDesc());
			solution.setId(ipSolution.getSolId());
			solution.setTags(ipSolution.getSolTags());
			solution.setTitle(ipSolution.getSolTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return solution;
	}

	public IpSolutionDAO getIpSolutionDAO() {
		return ipSolutionDAO;
	}

	public void setIpSolutionDAO(IpSolutionDAO ipSolutionDAO) {
		this.ipSolutionDAO = ipSolutionDAO;
	}

	public IpSolutionCatDAO getIpSolutionCatDAO() {
		return ipSolutionCatDAO;
	}

	public void setIpSolutionCatDAO(IpSolutionCatDAO ipSolutionCatDAO) {
		this.ipSolutionCatDAO = ipSolutionCatDAO;
	}

	public IpSolutionStatusDAO getIpSolutionStatusDAO() {
		return ipSolutionStatusDAO;
	}

	public void setIpSolutionStatusDAO(IpSolutionStatusDAO ipSolutionStatusDAO) {
		this.ipSolutionStatusDAO = ipSolutionStatusDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpChallengeDAO getIpChallengeDAO() {
		return ipChallengeDAO;
	}

	public void setIpChallengeDAO(IpChallengeDAO ipChallengeDAO) {
		this.ipChallengeDAO = ipChallengeDAO;
	}
}
