package za.co.idea.ip.ws.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.dao.IpBlobDAO;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/fds")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = -1460153975600326876L;
	private static final Logger logger = Logger.getLogger(FileDownloadServlet.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");
	private String baseDir;
	private ApplicationContext ctx;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownloadServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		baseDir = BUNDLE.getString("base.dir");
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		logger.info(baseDir);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Transactional
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Long blobId = Long.valueOf(request.getParameter("blobId"));
			logger.info("downloading id :: " + blobId);
			IpBlobDAO dao = (IpBlobDAO) ctx.getBean("IpBlobDAO");
			IpBlob blob = dao.findById(blobId);
			OutputStream out = response.getOutputStream();
			Boolean inline = Boolean.valueOf(request.getParameter("inline"));
			if (inline == null || !inline)
				response.setHeader("Content-Disposition", "attachment; filename=" + blob.getBlobName() + ";");
			else
				response.setHeader("Content-Disposition", "inline; filename=" + blob.getBlobName() + ";");
			FileInputStream in = new FileInputStream(new File(baseDir + File.separator + blob.getBlobEntityTblNm() + File.separator + blob.getBlobEntityId() + File.separator + blob.getBlobName()));
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

}
