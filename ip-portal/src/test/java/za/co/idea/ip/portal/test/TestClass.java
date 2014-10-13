package za.co.idea.ip.portal.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class TestClass {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");

	public static void main(String[] args) {
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
