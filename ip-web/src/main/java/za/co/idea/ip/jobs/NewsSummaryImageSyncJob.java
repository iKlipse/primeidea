package za.co.idea.ip.jobs;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class NewsSummaryImageSyncJob extends QuartzJobBean implements StatefulJob {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-web");

	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		File file = new File(wac.getServletContext().getRealPath("/resources/images"));
		File srcFile = new File(BUNDLE.getString("base.dir") + File.separator + "ip_news");
		File destFile = new File(file.getAbsolutePath() + File.separator + "ip_news");
		try {
			FileUtils.copyDirectory(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
