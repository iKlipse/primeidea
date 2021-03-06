package za.co.idea.ip.jobs;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SolutionsViewImagesSyncJob extends QuartzJobBean implements StatefulJob {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-web");
	private static final Logger logger = Logger.getLogger(SolutionsViewImagesSyncJob.class);

	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug("control handled in executeInternal() method");
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		File file = new File(wac.getServletContext().getRealPath("/resources/images"));
		File srcFile = new File(BUNDLE.getString("base.dir") + File.separator + "ip_solution");
		File destFile = new File(file.getAbsolutePath() + File.separator + "ip_solution");
		try {
			FileUtils.copyDirectory(srcFile, destFile);
		} catch (IOException e) {
			logger.error("Error : "+e.getMessage());
			e.printStackTrace();
		}
	}

}
