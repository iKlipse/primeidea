package za.co.idea.ip.portal.util;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class FileSyncListener
 *
 */
@WebListener
public class FileSyncListener implements ServletContextListener {
	private static final Logger logger = Logger.getLogger(FileSyncListener.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");

	/**
	 * Default constructor.
	 */
	public FileSyncListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Server Path :: " + sce.getServletContext().getRealPath(File.separator));
		File file = new File(sce.getServletContext().getRealPath("/resources/images"));
		File srcFile = new File(BUNDLE.getString("base.dir"));
		File destFile = new File(file.getAbsolutePath());
		try {
			FileUtils.copyDirectory(srcFile, destFile);
		} catch (IOException e) {
			logger.error("Error : " + e.getMessage());

		}
	}

}
