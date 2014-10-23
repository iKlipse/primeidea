package za.co.idea.ip.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.report.GroupUserActivityMessage;
import za.co.idea.ip.ws.bean.report.IdeaSummaryMessage;
import za.co.idea.ip.ws.bean.report.ReportQueryMessage;
import za.co.idea.ip.ws.bean.report.RewardsReportMessage;
import za.co.idea.ip.ws.bean.report.UserActivityMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

@ManagedBean(name = "reportController")
@SessionScoped
public class ReportController {
	private static final Logger logger = Logger.getLogger(ReportController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private boolean disGrp;
	private boolean disUser;
	private boolean disEndDt;
	private boolean disUA;
	private boolean disGUA;
	private boolean disRR;
	private boolean disIS;
	private ReportQueryMessage bean;
	private List<UserActivityMessage> uams;
	private List<GroupUserActivityMessage> guams;
	private List<RewardsReportMessage> rrms;
	private List<IdeaSummaryMessage> isms;
	private List<GroupBean> grps;
	private List<UserBean> users;
	private StreamedContent genUARep;
	private StreamedContent genGUARep;
	private StreamedContent genISRep;
	private StreamedContent genRRRep;

	public static WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	public void showUserActivtyReport() {
		disGrp = false;
		disUser = false;
		disEndDt = false;
		disUA = true;
		disGUA = false;
		disRR = false;
		disIS = false;
		bean = new ReportQueryMessage();
	}

	public void showGroupUserActivtyReport() {
		disGrp = false;
		disUser = false;
		disEndDt = false;
		disUA = false;
		disGUA = true;
		disRR = false;
		disIS = false;
		bean = new ReportQueryMessage();
	}

	public void showRewardsReport() {
		disGrp = false;
		disUser = false;
		disEndDt = false;
		disUA = false;
		disGUA = false;
		disRR = true;
		disIS = false;
		bean = new ReportQueryMessage();
	}

	public void showIdeaSummaryReport() {
		disGrp = false;
		disUser = false;
		disEndDt = false;
		disUA = false;
		disGUA = false;
		disRR = false;
		disIS = true;
		bean = new ReportQueryMessage();
	}

	public void generateRRep() {
		try {
			if (bean.getGrpId().longValue() == -999)
				bean.setGrpId(null);
			if (bean.getUsrId().longValue() == -999)
				bean.setUsrId(null);
			WebClient repClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rps/rr");
			rrms = new ArrayList<RewardsReportMessage>(repClient.accept(MediaType.APPLICATION_JSON).postAndGetCollection(bean, RewardsReportMessage.class));
			repClient.close();
			disGrp = false;
			disUser = false;
			disEndDt = false;
			disUA = false;
			disGUA = false;
			disRR = true;
			disIS = false;
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	public void generateUARep() {
		try {
			if (bean.getGrpId().longValue() == -999)
				bean.setGrpId(null);
			if (bean.getUsrId().longValue() == -999)
				bean.setUsrId(null);
			WebClient repClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rps/ua");
			uams = new ArrayList<UserActivityMessage>(repClient.accept(MediaType.APPLICATION_JSON).postAndGetCollection(bean, UserActivityMessage.class));
			repClient.close();
			disGrp = false;
			disUser = false;
			disEndDt = false;
			disUA = true;
			disGUA = false;
			disRR = false;
			disIS = false;
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	public void generateGUARep() {
		try {
			if (bean.getGrpId().longValue() == -999)
				bean.setGrpId(null);
			if (bean.getUsrId().longValue() == -999)
				bean.setUsrId(null);
			WebClient repClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rps/gua");
			guams = new ArrayList<GroupUserActivityMessage>(repClient.accept(MediaType.APPLICATION_JSON).postAndGetCollection(bean, GroupUserActivityMessage.class));
			repClient.close();
			disGrp = false;
			disUser = false;
			disEndDt = false;
			disUA = false;
			disGUA = true;
			disRR = false;
			disIS = false;
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	public void generateISRep() {
		try {
			if (bean.getGrpId().longValue() == -999)
				bean.setGrpId(null);
			if (bean.getUsrId().longValue() == -999)
				bean.setUsrId(null);
			WebClient repClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rps/isg");
			isms = new ArrayList<IdeaSummaryMessage>(repClient.accept(MediaType.APPLICATION_JSON).postAndGetCollection(bean, IdeaSummaryMessage.class));
			repClient.close();
			disGrp = false;
			disUser = false;
			disEndDt = false;
			disUA = false;
			disGUA = false;
			disRR = false;
			disIS = true;
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	public boolean isDisGrp() {
		return disGrp;
	}

	public void setDisGrp(boolean disGrp) {
		this.disGrp = disGrp;
	}

	public boolean isDisUser() {
		return disUser;
	}

	public void setDisUser(boolean disUser) {
		this.disUser = disUser;
	}

	public boolean isDisEndDt() {
		return disEndDt;
	}

	public void setDisEndDt(boolean disEndDt) {
		this.disEndDt = disEndDt;
	}

	public boolean isDisUA() {
		return disUA;
	}

	public void setDisUA(boolean disUA) {
		this.disUA = disUA;
	}

	public boolean isDisGUA() {
		return disGUA;
	}

	public void setDisGUA(boolean disGUA) {
		this.disGUA = disGUA;
	}

	public boolean isDisRR() {
		return disRR;
	}

	public void setDisRR(boolean disRR) {
		this.disRR = disRR;
	}

	public boolean isDisIS() {
		return disIS;
	}

	public void setDisIS(boolean disIS) {
		this.disIS = disIS;
	}

	public ReportQueryMessage getBean() {
		if (bean == null)
			bean = new ReportQueryMessage();
		return bean;
	}

	public void setBean(ReportQueryMessage bean) {
		this.bean = bean;
	}

	public List<UserActivityMessage> getUams() {
		if (uams == null)
			uams = new ArrayList<UserActivityMessage>();
		return uams;
	}

	public void setUams(List<UserActivityMessage> uams) {
		this.uams = uams;
	}

	public List<GroupUserActivityMessage> getGuams() {
		if (guams == null)
			guams = new ArrayList<GroupUserActivityMessage>();
		return guams;
	}

	public void setGuams(List<GroupUserActivityMessage> guams) {
		this.guams = guams;
	}

	public List<RewardsReportMessage> getRrms() {
		if (rrms == null)
			rrms = new ArrayList<RewardsReportMessage>();
		return rrms;
	}

	public void setRrms(List<RewardsReportMessage> rrms) {
		this.rrms = rrms;
	}

	public List<IdeaSummaryMessage> getIsms() {
		if (isms == null)
			isms = new ArrayList<IdeaSummaryMessage>();
		return isms;
	}

	public void setIsms(List<IdeaSummaryMessage> isms) {
		this.isms = isms;
	}

	public List<GroupBean> getGrps() {
		grps = RESTServiceHelper.fetchActiveGroups();
		return grps;
	}

	public void setGrps(List<GroupBean> grps) {
		this.grps = grps;
	}

	public List<UserBean> getUsers() {
		users = RESTServiceHelper.fetchActiveUsers();
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

	public StreamedContent getGenUARep() {
		logger.info("Generating User Activity Report");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(BUNDLE.getString("base.dir") + File.separator + "user_activity.jasper", null, getConnection());
			genUARep = new DefaultStreamedContent(new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)), "application/pdf", "download.pdf");
		} catch (JRException e) {
			logger.error(e, e);
		}
		return genUARep;
	}

	public void setGenUARep(StreamedContent genUARep) {
		this.genUARep = genUARep;
	}

	public StreamedContent getGenGUARep() {
		logger.info("Generating Group User Activity Report");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(BUNDLE.getString("base.dir") + File.separator + "group_user_activity.jasper", null, getConnection());
			genUARep = new DefaultStreamedContent(new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)), "application/pdf", "download.pdf");
		} catch (JRException e) {
			logger.error(e, e);
		}
		return genGUARep;
	}

	public void setGenGUARep(StreamedContent genGUARep) {
		this.genGUARep = genGUARep;
	}

	public StreamedContent getGenISRep() {
		logger.info("Generating Idea Summary Report");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(BUNDLE.getString("base.dir") + File.separator + "idea_summary_group.jasper", null, getConnection());
			genISRep = new DefaultStreamedContent(new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)), "application/pdf", "download.pdf");
		} catch (JRException e) {
			logger.error(e, e);
		}
		return genISRep;
	}

	public void setGenISRep(StreamedContent genISRep) {
		this.genISRep = genISRep;
	}

	public StreamedContent getGenRRRep() {
		logger.info("Generating Rewards Report");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(BUNDLE.getString("base.dir") + File.separator + "rewards_report.jasper", null, getConnection());
			genRRRep = new DefaultStreamedContent(new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)), "application/pdf", "download.pdf");
		} catch (JRException e) {
			logger.error(e, e);
		}
		return genRRRep;
	}

	public void setGenRRRep(StreamedContent genRRRep) {
		this.genRRRep = genRRRep;
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(BUNDLE.getString("rep.db.url"), BUNDLE.getString("rep.db.user"), BUNDLE.getString("rep.db.pwd"));
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->" + ex.getMessage());
			return null;
		}
	}

}
