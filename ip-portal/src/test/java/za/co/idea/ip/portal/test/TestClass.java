package za.co.idea.ip.portal.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.ws.bean.report.ReportQueryMessage;
import za.co.idea.ip.ws.bean.report.RewardsReportMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

public class TestClass {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");

	public static WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	public static void main(String[] args) throws JRException {
		testRRDEVRep();
	}

	public static void testRRDEVRep() throws JRException {
		WebClient repClient = createCustomClient("http://172.27.0.113:8080/ip-ws/ip/rps/rr");
		ArrayList<RewardsReportMessage> rrms = new ArrayList<RewardsReportMessage>(repClient.accept(MediaType.APPLICATION_JSON).postAndGetCollection(new ReportQueryMessage(), RewardsReportMessage.class));
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rrms);
		JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\ws\\git\\primeidea\\ip-portal\\src\\main\\resources\\rewards_report.jasper", null, ds);
		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\docs\\rewards_report.pdf");
	}

	public static void testDBReports() {
		Connection conn = null;
		try {
			conn = getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(BUNDLE.getString("base.dir") + File.separator + "group_user_activity.jasper", null, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\docs\\group_user_activity.pdf");
			jasperPrint = JasperFillManager.fillReport("C:\\ws\\git\\primeidea\\ip-portal\\src\\main\\resources\\idea_summary_group.jasper", null, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\docs\\idea_summary_group.pdf");
			jasperPrint = JasperFillManager.fillReport("C:\\ws\\git\\primeidea\\ip-portal\\src\\main\\resources\\rewards_report.jasper", null, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\docs\\rewards_report.pdf");
			jasperPrint = JasperFillManager.fillReport("C:\\ws\\git\\primeidea\\ip-portal\\src\\main\\resources\\user_activity.jasper", null, conn);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\docs\\user_activity.pdf");
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpdb", "root", "Pa%5W0Rd");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->" + ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}
