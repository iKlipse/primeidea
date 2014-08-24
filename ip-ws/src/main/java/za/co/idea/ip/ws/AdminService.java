package za.co.idea.ip.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.bean.IpFuncGroup;
import za.co.idea.ip.orm.bean.IpFunction;
import za.co.idea.ip.orm.bean.IpGroup;
import za.co.idea.ip.orm.bean.IpGroupUser;
import za.co.idea.ip.orm.bean.IpLogin;
import za.co.idea.ip.orm.bean.IpUser;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.orm.dao.IpFuncGroupDAO;
import za.co.idea.ip.orm.dao.IpFunctionDAO;
import za.co.idea.ip.orm.dao.IpGroupDAO;
import za.co.idea.ip.orm.dao.IpGroupUserDAO;
import za.co.idea.ip.orm.dao.IpLoginDAO;
import za.co.idea.ip.orm.dao.IpNativeSQLDAO;
import za.co.idea.ip.orm.dao.IpSecqListDAO;
import za.co.idea.ip.orm.dao.IpUserDAO;
import za.co.idea.ip.ws.bean.FunctionMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.bean.UserStatisticsMessage;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path(value = "/as")
public class AdminService {
	private static final Logger logger = Logger.getLogger(AdminService.class);
	private IpGroupDAO ipGroupDAO;
	private IpUserDAO ipUserDAO;
	private IpLoginDAO ipLoginDAO;
	private IpFunctionDAO ipFunctionDAO;
	private IpNativeSQLDAO ipNativeSQLDAO;
	private IpGroupUserDAO ipGroupUserDAO;
	private IpFuncGroupDAO ipFuncGroupDAO;
	private IpBlobDAO ipBlobDAO;
	private IpSecqListDAO ipSecqListDAO;

	@POST
	@Path("/group/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.NESTED)
	public ResponseMessage createGroup(GroupMessage group) {
		IpGroup ipGroup = new IpGroup();
		ipGroup.setGroupId(group.getgId());
		ipGroup.setGroupEmail(group.getGeMail());
		ipGroup.setGroupName(group.getgName());
		ipGroup.setGroupStatus(((group.getIsActive() != null && group.getIsActive()) ? "y" : "n"));
		if (group.getpGrpId() != null && group.getpGrpId().longValue() >= 0)
			ipGroup.setIpGroup(ipGroupDAO.findById(group.getpGrpId()));
		if (group.getAdmUserId() != null && group.getAdmUserId().longValue() >= 0)
			ipGroup.setIpUser(ipUserDAO.findById(group.getAdmUserId()));
		try {
			ipGroupDAO.save(ipGroup);
			if (group.getUserIdList() != null && group.getUserIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpGroupUser.class, group.getUserIdList().length);
				int i = 0;
				for (Long userId : group.getUserIdList()) {
					IpGroupUser ipGroupUser = new IpGroupUser();
					ipGroupUser.setIpGroup(ipGroup);
					ipGroupUser.setIpUser(ipUserDAO.findById(userId));
					ipGroupUser.setGuId(ids[i]);
					ipGroupUserDAO.save(ipGroupUser);
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

	@PUT
	@Path("/group/modify")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage updateGroup(GroupMessage group) {
		IpGroup ipGroup = new IpGroup();
		ipGroup.setGroupId(group.getgId());
		ipGroup.setGroupEmail(group.getGeMail());
		ipGroup.setGroupName(group.getgName());
		ipGroup.setGroupStatus(((group.getIsActive() != null && group.getIsActive()) ? "y" : "n"));
		if (group.getpGrpId() != null && group.getpGrpId().longValue() >= 0)
			ipGroup.setIpGroup(ipGroupDAO.findById(group.getpGrpId()));
		if (group.getAdmUserId() != null && group.getAdmUserId().longValue() >= 0)
			ipGroup.setIpUser(ipUserDAO.findById(group.getAdmUserId()));
		try {
			ipGroupDAO.merge(ipGroup);
			ipGroupUserDAO.deleteByGroupId(ipGroup.getGroupId());
			if (group.getUserIdList() != null && group.getUserIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpGroupUser.class, group.getUserIdList().length);
				int i = 0;
				for (Long userId : group.getUserIdList()) {
					IpGroupUser ipGroupUser = new IpGroupUser();
					ipGroupUser.setIpGroup(ipGroup);
					ipGroupUser.setIpUser(ipUserDAO.findById(userId));
					ipGroupUser.setGuId(ids[i]);
					ipGroupUserDAO.save(ipGroupUser);
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

	@GET
	@Path("/group/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends GroupMessage> List<T> listGroup() {
		List<T> ret = new ArrayList<T>();
		try {
			List groups = ipGroupDAO.findAll();
			for (Object object : groups) {
				IpGroup ipGroup = (IpGroup) object;
				GroupMessage group = new GroupMessage();
				group.setGeMail(ipGroup.getGroupEmail());
				group.setgId(ipGroup.getGroupId());
				group.setgName(ipGroup.getGroupName());
				group.setIsActive(ipGroup.getGroupStatus().equalsIgnoreCase("y"));
				if (ipGroup.getIpGroup() != null){
					group.setpGrpId(ipGroup.getIpGroup().getGroupId());
				    group.setpGrpName(ipGroup.getIpGroup().getGroupName());
					}
				if (ipGroup.getIpUser() != null){
					group.setAdmUserId(ipGroup.getIpUser().getUserId());
				    group.setAdmUserName(ipGroup.getIpUser().getUserScreenName());
					}
				List guList = ipGroupUserDAO.fetchByGroupId(ipGroup.getGroupId());
				Long[] uList = new Long[guList.size()];
				int i = 0;
				for (Object guObj : guList) {
					IpGroupUser gu = (IpGroupUser) guObj;
					uList[i] = gu.getIpUser().getUserId();
					i++;
				}
				group.setUserIdList(uList);
				ret.add((T) group);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/group/hierarchy/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public String hierarchyGroup(@PathParam("id") Long id) {
		String ret = "";
		try {
			ret = ipGroupDAO.getGroupHierarchy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/func/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends FunctionMessage> List<T> listFunction() {
		List<T> ret = new ArrayList<T>();
		try {
			List functions = ipFunctionDAO.findAll();
			for (Object object : functions) {
				IpFunction ipFunction = (IpFunction) object;
				FunctionMessage function = new FunctionMessage();
				function.setFuncId(ipFunction.getFuncId());
				function.setFuncName(ipFunction.getFuncName());
				List fgs = ipFuncGroupDAO.fetchByFuncId(ipFunction.getFuncId());
				if (fgs != null) {
					Long[] gList = new Long[fgs.size()];
					int i = 0;
					for (Object obj : fgs) {
						IpFuncGroup fg = (IpFuncGroup) obj;
						gList[i] = fg.getIpGroup().getGroupId();
						i++;
					}
					function.setGroupIdList(gList);
				}
				ret.add((T) function);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/func/group/list/{grpId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends FunctionMessage> List<T> listFunctionByGroup(@PathParam("grpId") Long grpId) {
		List<T> ret = new ArrayList<T>();
		try {
			List functions = ipFuncGroupDAO.fetchByGroupId(grpId);
			if (functions != null) {
				for (Object object : functions) {
					IpFunction ipFunction = ((IpFuncGroup) object).getIpFunction();
					FunctionMessage function = new FunctionMessage();
					function.setFuncId(ipFunction.getFuncId());
					function.setFuncName(ipFunction.getFuncName());
					List fgs = ipFuncGroupDAO.fetchByFuncId(ipFunction.getFuncId());
					if (fgs != null) {
						Long[] gList = new Long[fgs.size()];
						int i = 0;
						for (Object obj : fgs) {
							IpFuncGroup fg = (IpFuncGroup) obj;
							gList[i] = fg.getIpGroup().getGroupId();
							i++;
						}
						function.setGroupIdList(gList);
					}
					ret.add((T) function);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/func/list/user/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public String[] listFunctionByUser(@PathParam("id") Long id) {
		String[] ret = null;
		try {
			List functions = ipFunctionDAO.getFunctionByUserId(id);
			ret = new String[functions.size()];
			int i = 0;
			for (Object object : functions) {
				ret[i] = (String) object;
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/func/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public FunctionMessage getFunctionById(@PathParam("id") Long id) {
		FunctionMessage function = new FunctionMessage();
		try {
			IpFunction ipFunction = ipFunctionDAO.findById(id);
			function.setFuncId(ipFunction.getFuncId());
			function.setFuncName(ipFunction.getFuncName());
			List fgs = ipFuncGroupDAO.fetchByFuncId(ipFunction.getFuncId());
			if (fgs != null) {
				Long[] gList = new Long[fgs.size()];
				int i = 0;
				for (Object obj : fgs) {
					IpFuncGroup fg = (IpFuncGroup) obj;
					gList[i] = fg.getIpGroup().getGroupId();
					i++;
				}
				function.setGroupIdList(gList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return function;
	}

	@GET
	@Path("/group/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public GroupMessage getGroupById(@PathParam("id") Long id) {
		GroupMessage group = new GroupMessage();
		try {
			IpGroup ipGroup = ipGroupDAO.findById(id);
			group.setGeMail(ipGroup.getGroupEmail());
			group.setgId(ipGroup.getGroupId());
			group.setgName(ipGroup.getGroupName());
			group.setIsActive(ipGroup.getGroupStatus().equalsIgnoreCase("y"));
			if (ipGroup.getIpGroup() != null)
				group.setpGrpId(ipGroup.getIpGroup().getGroupId());
			if (ipGroup.getIpUser() != null)
				group.setAdmUserId(ipGroup.getIpUser().getUserId());
			List guList = ipGroupUserDAO.fetchByGroupId(id);
			Long[] uList = new Long[guList.size()];
			int i = 0;
			for (Object guObj : guList) {
				IpGroupUser gu = (IpGroupUser) guObj;
				uList[i] = gu.getIpUser().getUserId();
				i++;
			}
			group.setUserIdList(uList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

	@POST
	@Path("/user/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage createUser(UserMessage user) {
		try {
			IpUser ipUser = new IpUser();
			ipUser.setUserId(user.getuId());
			ipUser.setUserContact(user.getContact());
			ipUser.setUserEmail(user.geteMail());
			ipUser.setUserFName(user.getfName());
			ipUser.setUserIdNum(user.getIdNum());
			ipUser.setUserLName(user.getlName());
			ipUser.setUserScreenName(user.getScName());
			ipUser.setUserSkills(user.getSkills());
			ipUser.setUserEmployeeId(user.getEmployeeId());
			ipUser.setUserStatus(((user.getIsActive() != null && user.getIsActive()) ? "y" : "n"));
			if (user.getGroupId() != null)
				ipUser.setIpGroup(ipGroupDAO.findById(user.getGroupId()));
			if (user.getFbHandle() != null && user.getFbHandle().length() > 0)
				ipUser.setUserFbHandle(user.getFbHandle());
			if (user.getBio() != null && user.getBio().length() > 0)
				ipUser.setUserBio(user.getBio());
			if (user.getmName() != null && user.getmName().length() > 0)
				ipUser.setUserMName(user.getmName());
			if (user.getTwHandle() != null && user.getTwHandle().length() > 0)
				ipUser.setUserTwHandle(user.getTwHandle());
			ipUser.setUserIdNum(user.getIdNum());
			ipUserDAO.save(ipUser);
			IpLogin ipLogin = new IpLogin();
			ipLogin.setIpUser(ipUser);
			ipLogin.setLoginName(ipUser.getUserScreenName());
			ipLogin.setLoginId(user.getuId());
			ipLogin.setIpSecqList(ipSecqListDAO.findById(user.getSecQ()));
			ipLogin.setLoginSecA(Base64.encodeBase64URLSafeString(DigestUtils.md5(user.getSecA().getBytes())));
			ipLogin.setLoginPwd(Base64.encodeBase64URLSafeString(DigestUtils.md5(user.getPwd().getBytes())));
			try {
				ipLoginDAO.save(ipLogin);
			} catch (Exception e) {
				e.printStackTrace();
				ipUserDAO.delete(ipUser);
				throw new RuntimeException("Cannot create user :: " + e.getMessage());
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

	@POST
	@Path("/func/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage createFunction(FunctionMessage function) {
		try {
			IpFunction ipFunction = new IpFunction();
			ipFunction.setFuncId(function.getFuncId());
			ipFunction.setFuncName(function.getFuncName());
			ipFunction.setFuncCrtdDt(new Date(System.currentTimeMillis()));
			ipFunction.setIpUser(ipUserDAO.findById(function.getCrtdBy()));
			ipFunction.setFuncIsCore("n");
			ipFunctionDAO.save(ipFunction);
			if (function.getGroupIdList() != null && function.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpFuncGroup.class, function.getGroupIdList().length);
				int i = 0;
				for (Long gId : function.getGroupIdList()) {
					IpFuncGroup ipFuncGroup = new IpFuncGroup();
					ipFuncGroup.setFgId(ids[i]);
					ipFuncGroup.setIpFunction(ipFunction);
					ipFuncGroup.setIpGroup(ipGroupDAO.findById(gId));
					ipFuncGroupDAO.save(ipFuncGroup);
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

	@PUT
	@Path("/func/modify")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage updateFunction(FunctionMessage function) {
		try {
			IpFunction ipFunction = new IpFunction();
			ipFunction.setFuncId(function.getFuncId());
			ipFunction.setFuncName(function.getFuncName());
			ipFunction.setFuncCrtdDt(new Date(System.currentTimeMillis()));
			ipFunction.setIpUser(ipUserDAO.findById(function.getCrtdBy()));
			ipFunction.setFuncIsCore("y");
			ipFunctionDAO.merge(ipFunction);
			ipFuncGroupDAO.deleteByFunctionId(ipFunction.getFuncId());
			if (function.getGroupIdList() != null && function.getGroupIdList().length > 0) {
				Long[] ids = ipNativeSQLDAO.getNextIds(IpFuncGroup.class, function.getGroupIdList().length);
				int i = 0;
				for (Long gId : function.getGroupIdList()) {
					IpFuncGroup ipFuncGroup = new IpFuncGroup();
					ipFuncGroup.setFgId(ids[i]);
					ipFuncGroup.setIpFunction(ipFunction);
					ipFuncGroup.setIpGroup(ipGroupDAO.findById(gId));
					ipFuncGroupDAO.save(ipFuncGroup);
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

	@PUT
	@Path("/user/modify")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage updateUser(UserMessage user) {
		try {
			IpUser ipUser = new IpUser();
			ipUser.setUserId(user.getuId());
			ipUser.setUserContact(user.getContact());
			ipUser.setUserEmail(user.geteMail());
			ipUser.setUserFName(user.getfName());
			ipUser.setUserIdNum(user.getIdNum());
			ipUser.setUserLName(user.getlName());
			ipUser.setUserScreenName(user.getScName());
			ipUser.setUserSkills(user.getSkills());
			ipUser.setUserEmployeeId(user.getEmployeeId());
			ipUser.setUserStatus(((user.getIsActive() != null && user.getIsActive()) ? "y" : "n"));
			if (user.getGroupId() != null)
				ipUser.setIpGroup(ipGroupDAO.findById(user.getGroupId()));
			if (user.getFbHandle() != null && user.getFbHandle().length() > 0)
				ipUser.setUserFbHandle(user.getFbHandle());
			if (user.getBio() != null && user.getBio().length() > 0)
				ipUser.setUserBio(user.getBio());
			if (user.getmName() != null && user.getmName().length() > 0)
				ipUser.setUserMName(user.getmName());
			if (user.getTwHandle() != null && user.getTwHandle().length() > 0)
				ipUser.setUserTwHandle(user.getTwHandle());
			ipUserDAO.merge(ipUser);
			IpLogin ipLogin = ipLoginDAO.fetchLoginById(ipUser.getUserId());
			ipLogin.setLoginName(user.getScName());
			ipLoginDAO.merge(ipLogin);
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
	@Path("/user/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends UserMessage> List<T> listUser() {
		List<T> ret = new ArrayList<T>();
		try {
			List users = ipUserDAO.findAll();
			for (Object object : users) {
				IpUser ipUser = (IpUser) object;
				UserMessage user = new UserMessage();
				user.setuId(ipUser.getUserId());
				user.setContact(ipUser.getUserContact());
				user.seteMail(ipUser.getUserEmail());
				user.setfName(ipUser.getUserFName());
				user.setIdNum(ipUser.getUserIdNum());
				user.setlName(ipUser.getUserLName());
				user.setScName(ipUser.getUserScreenName());
				user.setSkills(ipUser.getUserSkills());
				user.setIsActive(ipUser.getUserStatus().equalsIgnoreCase("y"));
				user.setEmployeeId(ipUser.getUserEmployeeId());
				if (ipUser.getIpGroup() != null) {
					user.setGroupId(ipUser.getIpGroup().getGroupId());
					user.setPriGroupName(ipGroupDAO.findById(ipUser.getIpGroup().getGroupId()).getGroupName());
				}
				if (ipUser.getUserFbHandle() != null && ipUser.getUserFbHandle().length() > 0)
					user.setFbHandle(ipUser.getUserFbHandle());
				if (ipUser.getUserBio() != null && ipUser.getUserBio().length() > 0)
					user.setBio(ipUser.getUserBio());
				if (ipUser.getUserMName() != null && ipUser.getUserMName().length() > 0)
					user.setmName(ipUser.getUserMName());
				if (ipUser.getUserTwHandle() != null && ipUser.getUserTwHandle().length() > 0)
					user.setTwHandle(ipUser.getUserTwHandle());
				ret.add((T) user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/user/list/sort/pg")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends UserMessage> List<T> listUserSortedByPrimaryGroup() {
		List<T> ret = new ArrayList<T>();
		try {
			List users = ipUserDAO.fetchSortByPrimaryGroup();
			for (Object object : users) {
				IpUser ipUser = (IpUser) object;
				UserMessage user = new UserMessage();
				user.setuId(ipUser.getUserId());
				user.setContact(ipUser.getUserContact());
				user.seteMail(ipUser.getUserEmail());
				user.setfName(ipUser.getUserFName());
				user.setIdNum(ipUser.getUserIdNum());
				user.setlName(ipUser.getUserLName());
				user.setScName(ipUser.getUserScreenName());
				user.setSkills(ipUser.getUserSkills());
				user.setIsActive(ipUser.getUserStatus().equalsIgnoreCase("y"));
				user.setEmployeeId(ipUser.getUserEmployeeId());
				if (ipUser.getIpGroup() != null) {
					user.setGroupId(ipUser.getIpGroup().getGroupId());
					user.setPriGroupName(ipGroupDAO.findById(ipUser.getIpGroup().getGroupId()).getGroupName());
				}
				if (ipUser.getUserFbHandle() != null && ipUser.getUserFbHandle().length() > 0)
					user.setFbHandle(ipUser.getUserFbHandle());
				if (ipUser.getUserBio() != null && ipUser.getUserBio().length() > 0)
					user.setBio(ipUser.getUserBio());
				if (ipUser.getUserMName() != null && ipUser.getUserMName().length() > 0)
					user.setmName(ipUser.getUserMName());
				if (ipUser.getUserTwHandle() != null && ipUser.getUserTwHandle().length() > 0)
					user.setTwHandle(ipUser.getUserTwHandle());
				ret.add((T) user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/user/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public UserMessage getUserById(@PathParam("id") Long id) {
		UserMessage user = new UserMessage();
		try {
			IpUser ipUser = ipUserDAO.findById(id);
			user.setuId(ipUser.getUserId());
			user.setContact(ipUser.getUserContact());
			user.seteMail(ipUser.getUserEmail());
			user.setfName(ipUser.getUserFName());
			user.setIdNum(ipUser.getUserIdNum());
			user.setlName(ipUser.getUserLName());
			user.setScName(ipUser.getUserScreenName());
			user.setSkills(ipUser.getUserSkills());
			user.setEmployeeId(ipUser.getUserEmployeeId());
			user.setIsActive(ipUser.getUserStatus().equalsIgnoreCase("y"));
			if (ipUser.getIpGroup() != null) {
				user.setGroupId(ipUser.getIpGroup().getGroupId());
				user.setPriGroupName(ipGroupDAO.findById(ipUser.getIpGroup().getGroupId()).getGroupName());
			}
			if (ipUser.getUserFbHandle() != null && ipUser.getUserFbHandle().length() > 0)
				user.setFbHandle(ipUser.getUserFbHandle());
			if (ipUser.getUserBio() != null && ipUser.getUserBio().length() > 0)
				user.setBio(ipUser.getUserBio());
			if (ipUser.getUserMName() != null && ipUser.getUserMName().length() > 0)
				user.setmName(ipUser.getUserMName());
			if (ipUser.getUserTwHandle() != null && ipUser.getUserTwHandle().length() > 0)
				user.setTwHandle(ipUser.getUserTwHandle());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@PUT
	@Path("/user/rpw")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage resetPassword(String[] param) {
		try {
			try {
				ipLoginDAO.updatePassword(param[0], param[1]);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Cannot merge login :: " + e.getMessage());
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

	@PUT
	@Path("/user/rsec")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseMessage resetSecurity(String[] param) {
		try {
			try {
				ipLoginDAO.updateSecurity(param[0], Integer.valueOf(param[1]), Base64.encodeBase64URLSafeString(DigestUtils.md5(param[2].getBytes())));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Cannot merge login :: " + e.getMessage());
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

	@GET
	@Path("/user/check/screenName/{sc}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean checkScreenName(@PathParam("sc") String sc) {
		try {
			List usersByScName = ipUserDAO.findByUserScreenName(sc);
			Boolean ret = (usersByScName != null && usersByScName.size() > 0);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GET
	@Path("/user/check/email/{email}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean checkEmail(@PathParam("email") String email) {
		try {
			List usersByEmail = ipUserDAO.findByUserEmail(email);
			Boolean ret = (usersByEmail != null && usersByEmail.size() > 0);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GET
	@Path("/user/check/idNumber/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean checkIDNumber(@PathParam("id") Long id) {
		try {
			List usersByIDNumber = ipUserDAO.findByUserIdNum(id);
			Boolean ret = (usersByIDNumber != null && usersByIDNumber.size() > 0);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GET
	@Path("/user/check/employeeId/{eId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean checkEmployeeID(@PathParam("eId") String eId) {
		try {
			List usersByEmployeeID = ipUserDAO.findByUserEmployeeId(eId);
			Boolean ret = (usersByEmployeeID != null && usersByEmployeeID.size() > 0);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GET
	@Path("/gen/{clazz}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Long getNextId(@PathParam("clazz") String clazz) {
		Long ret = -1l;
		try {
			ret = ipNativeSQLDAO.getNextId(Class.forName("za.co.idea.ip.orm.bean." + clazz));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GET
	@Path("/user/login/{login}/{pwd}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public UserMessage login(@PathParam("login") String login, @PathParam("pwd") String pwd) {
		UserMessage user = new UserMessage();
		try {
			List logins = ipLoginDAO.verifyLogin(login, pwd);
			IpLogin ipLogin = (IpLogin) logins.get(0);
			IpUser ipUser = ipLogin.getIpUser();
			if (ipUser.getUserStatus().equalsIgnoreCase("n")) {
				user.setuId(-999l);
			} else {
				user.setuId(ipUser.getUserId());
				user.setContact(ipUser.getUserContact());
				user.seteMail(ipUser.getUserEmail());
				user.setfName(ipUser.getUserFName());
				user.setIdNum(ipUser.getUserIdNum());
				user.setlName(ipUser.getUserLName());
				user.setScName(ipUser.getUserScreenName());
				user.setSkills(ipUser.getUserSkills());
				if (ipUser.getIpGroup() != null)
					user.setGroupId(ipUser.getIpGroup().getGroupId());
				user.setPriGroupName(ipGroupDAO.findById(ipUser.getIpGroup().getGroupId()).getGroupName());
				user.setIsActive(ipUser.getUserStatus().equalsIgnoreCase("y"));
				if (ipUser.getUserFbHandle() != null && ipUser.getUserFbHandle().length() > 0)
					user.setFbHandle(ipUser.getUserFbHandle());
				if (ipUser.getUserBio() != null && ipUser.getUserBio().length() > 0)
					user.setBio(ipUser.getUserBio());
				if (ipUser.getUserMName() != null && ipUser.getUserMName().length() > 0)
					user.setmName(ipUser.getUserMName());
				if (ipUser.getUserTwHandle() != null && ipUser.getUserTwHandle().length() > 0)
					user.setTwHandle(ipUser.getUserTwHandle());
				user.setLastLoginDt(ipLogin.getLoginLastDt());
				ipLogin.setLoginLastDt(new Date(System.currentTimeMillis()));
				ipLoginDAO.merge(ipLogin);
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipUser.getUserId(), "ip_user");
				if (blob != null) {
					user.setImgPath("ip_user/" + ipUser.getUserId() + "/" + blob.getBlobName());
					user.setImgAvail(true);
				} else
					user.setImgAvail(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@GET
	@Path("/user/verify/{login}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public UserMessage verify(@PathParam("login") String login) {
		UserMessage user = new UserMessage();
		try {
			List logins = ipLoginDAO.fetchLogin(login);
			IpLogin ipLogin = (IpLogin) logins.get(0);
			IpUser ipUser = ipLogin.getIpUser();
			user.setuId(ipUser.getUserId());
			user.setContact(ipUser.getUserContact());
			user.seteMail(ipUser.getUserEmail());
			user.setfName(ipUser.getUserFName());
			user.setIdNum(ipUser.getUserIdNum());
			user.setlName(ipUser.getUserLName());
			user.setScName(ipUser.getUserScreenName());
			user.setSkills(ipUser.getUserSkills());
			user.setIsActive(ipUser.getUserStatus().equalsIgnoreCase("y"));
			user.setLastLoginDt(ipLogin.getLoginLastDt());
			user.setSecA(ipLogin.getLoginSecA());
			user.setSecQ(ipLogin.getIpSecqList().getIslId());
			if (ipUser.getIpGroup() != null) {
				user.setGroupId(ipUser.getIpGroup().getGroupId());
				IpBlob grpBlob = ipBlobDAO.getBlobByEntity(user.getGroupId(), "ip_group");
				user.setGrpImgAvail(true);
				user.setGrpImgPath("ip_group/" + user.getGroupId() + "/" + grpBlob.getBlobName());
			} else
				user.setGrpImgAvail(false);
			IpBlob blob = ipBlobDAO.getBlobByEntity(ipUser.getUserId(), "ip_user");
			if (blob != null) {
				user.setImgPath("ip_user/" + ipUser.getUserId() + "/" + blob.getBlobName());
				user.setImgAvail(true);
			} else
				user.setImgAvail(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@GET
	@Path("/user/stats/{userId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public UserStatisticsMessage getDetailsByUserId(@PathParam("userId") Long id) {
		UserStatisticsMessage userStats = new UserStatisticsMessage();
		try {
			Long solCount = ipUserDAO.findSolutionCount(id);
			Long chalCount = ipUserDAO.findChallengeCount(id);
			Long whishListCount = ipUserDAO.findWhishlistCount(id);
			Long ideasCount = ipUserDAO.findIdeasCount(id);
			Long totalCount = solCount + chalCount + whishListCount + ideasCount;
			userStats.setUserId(id);
			userStats.setChallengesCount(chalCount);
			userStats.setIdeasCount(ideasCount);
			userStats.setSolutionsCount(solCount);
			userStats.setWhishListCount(whishListCount);
			userStats.setTotalCount(totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userStats;
	}

	@GET
	@Path("/user/stats/topList")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends UserStatisticsMessage> List<T> getTopCountsUser() {
		Set<UserStatisticsMessage> userStatsSet = new TreeSet<UserStatisticsMessage>();
		List<T> usersStatsList = new ArrayList<T>();
		logger.info("Control handle din getTopCountsUser of service /user/stats/topList ");
		try {
			List users = ipUserDAO.findAll();
			logger.info("Users list : " + users);
			for (Object object : users) {
				IpUser ipUser = (IpUser) object;
				Long solCount = ipUserDAO.findSolutionCount(ipUser.getUserId());
				Long chalCount = ipUserDAO.findChallengeCount(ipUser.getUserId());
				Long whishListCount = ipUserDAO.findWhishlistCount(ipUser.getUserId());
				Long ideasCount = ipUserDAO.findIdeasCount(ipUser.getUserId());
				Long totalCount = solCount + chalCount + whishListCount + ideasCount;
				UserStatisticsMessage userStats = new UserStatisticsMessage();
				userStats.setUserId(ipUser.getUserId());
				userStats.setChallengesCount(chalCount);
				userStats.setIdeasCount(ideasCount);
				userStats.setSolutionsCount(solCount);
				userStats.setWhishListCount(whishListCount);
				userStats.setTotalCount(totalCount);
				IpBlob blob = ipBlobDAO.getBlobByEntity(ipUser.getUserId(), "ip_user");
				if (blob != null) {
					userStats.setImgPath("ip_user/" + ipUser.getUserId() + "/" + blob.getBlobName());
					userStats.setImgAvail(true);
				} else {
					userStats.setImgAvail(false);
				}
				IpBlob grpBlob = ipBlobDAO.getBlobByEntity(ipUser.getIpGroup().getGroupId(), "ip_group");
				if (grpBlob != null && ipUser.getIpGroup() != null) {
					userStats.setGrpImgPath("ip_group/" + ipUser.getIpGroup().getGroupId() + "/" + grpBlob.getBlobName());
					userStats.setGrpImgAvail(true);
				} else {
					userStats.setGrpImgAvail(false);
				}
				userStatsSet.add(userStats);
			}
			int i = 0;
			for (UserStatisticsMessage object : userStatsSet) {
				i++;
				if (i <= 5) {
					usersStatsList.add((T) object);
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return usersStatsList;
	}

	public IpGroupDAO getIpGroupDAO() {
		return ipGroupDAO;
	}

	public void setIpGroupDAO(IpGroupDAO ipGroupDAO) {
		this.ipGroupDAO = ipGroupDAO;
	}

	public IpUserDAO getIpUserDAO() {
		return ipUserDAO;
	}

	public void setIpUserDAO(IpUserDAO ipUserDAO) {
		this.ipUserDAO = ipUserDAO;
	}

	public IpLoginDAO getIpLoginDAO() {
		return ipLoginDAO;
	}

	public void setIpLoginDAO(IpLoginDAO ipLoginDAO) {
		this.ipLoginDAO = ipLoginDAO;
	}

	public IpFunctionDAO getIpFunctionDAO() {
		return ipFunctionDAO;
	}

	public void setIpFunctionDAO(IpFunctionDAO ipFunctionDAO) {
		this.ipFunctionDAO = ipFunctionDAO;
	}

	public IpNativeSQLDAO getIpNativeSQLDAO() {
		return ipNativeSQLDAO;
	}

	public void setIpNativeSQLDAO(IpNativeSQLDAO ipNativeSQLDAO) {
		this.ipNativeSQLDAO = ipNativeSQLDAO;
	}

	public IpGroupUserDAO getIpGroupUserDAO() {
		return ipGroupUserDAO;
	}

	public void setIpGroupUserDAO(IpGroupUserDAO ipGroupUserDAO) {
		this.ipGroupUserDAO = ipGroupUserDAO;
	}

	public IpFuncGroupDAO getIpFuncGroupDAO() {
		return ipFuncGroupDAO;
	}

	public void setIpFuncGroupDAO(IpFuncGroupDAO ipFuncGroupDAO) {
		this.ipFuncGroupDAO = ipFuncGroupDAO;
	}

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

	public IpSecqListDAO getIpSecqListDAO() {
		return ipSecqListDAO;
	}

	public void setIpSecqListDAO(IpSecqListDAO ipSecqListDAO) {
		this.ipSecqListDAO = ipSecqListDAO;
	}
}
