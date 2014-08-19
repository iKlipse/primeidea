package za.co.idea.ip.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.ws.bean.AttachmentMessage;

@Path(value = "/ds")
public class DocumentUploadService {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");
	private IpBlobDAO ipBlobDAO;
	protected static final Logger logger = Logger.getLogger(DocumentUploadService.class);

	@POST
	@Path("/doc/upload/{blobId}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public Response doUpload(final Attachment stream, @PathParam("blobId") Long blobId) {
		try {
			IpBlob blob = ipBlobDAO.findById(blobId);
			if (blob != null) {
				File file = new File(BUNDLE.getString("base.dir") + File.separator + blob.getBlobEntityTblNm() + File.separator + blob.getBlobEntityId() + File.separator + blob.getBlobName());
				if (file.getParentFile().exists())
					FileUtils.cleanDirectory(file.getParentFile());
				else
					file.getParentFile().mkdirs();
				file.createNewFile();
				FileOutputStream data = new FileOutputStream(file);
				byte[] buf = new byte[4096];
				long totalSize = 0l;
				while (stream.getDataHandler().getInputStream().read(buf) > 0) {
					totalSize += buf.length;
					data.write(buf);
					data.flush();
				}
				data.close();
				try {
					blob.setBlobSize(totalSize);
					ipBlobDAO.merge(blob);
				} catch (Exception e) {
					return Response.status(Status.INTERNAL_SERVER_ERROR).build();
				}
				return Response.ok().build();
			} else
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/doc/download/{blobId}/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Transactional(propagation = Propagation.REQUIRED)
	public Attachment doDownload(@PathParam("blobId") Long blobId, @PathParam("name") String name) {
		try {
			IpBlob blob = ipBlobDAO.findById(blobId);
			if (blob != null) {
				File file = new File(BUNDLE.getString("base.dir") + File.separator + blob.getBlobEntityTblNm() + File.separator + blob.getBlobEntityId() + File.separator + blob.getBlobName());
				FileInputStream reader = new FileInputStream(file);
				Attachment attachment = new Attachment(blob.getBlobId().toString(), reader, new ContentDisposition("attachment;filename=" + name));
				return attachment;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PUT
	@Path("/doc/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public Response updateDocument(AttachmentMessage message) {
		try {
			IpBlob blob = new IpBlob();
			blob.setBlobContentType(message.getBlobContentType());
			blob.setBlobEntityId(message.getBlobEntityId());
			blob.setBlobEntityTblNm(message.getBlobEntityTblNm());
			blob.setBlobId(message.getBlobId());
			blob.setBlobName(message.getBlobName());
			ipBlobDAO.merge(blob);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/doc/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public Response createDocument(AttachmentMessage message) {
		try {
			IpBlob blob = new IpBlob();
			blob.setBlobContentType(message.getBlobContentType());
			blob.setBlobEntityId(message.getBlobEntityId());
			blob.setBlobEntityTblNm(message.getBlobEntityTblNm());
			blob.setBlobId(message.getBlobId());
			blob.setBlobName(message.getBlobName());
			ipBlobDAO.save(blob);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/doc/getId/{entityId}/{entityTblNm}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public Long getId(@PathParam("entityId") Long entityId, @PathParam("entityTblNm") String entityTblNm) {
		Long ret = -999l;
		IpBlob blob = ipBlobDAO.getBlobByEntity(entityId, entityTblNm);
		if (blob != null)
			ret = blob.getBlobId();
		return ret;
	}

	@GET
	@Path("/doc/getName/{blobId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getName(@PathParam("blobId") Long blobId) {
		String ret = "";
		IpBlob blob = ipBlobDAO.findById(blobId);
		if (blob != null)
			ret = blob.getBlobName();
		return ret;
	}

	@GET
	@Path("/doc/getContentType/{blobId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public String getMimeType(@PathParam("blobId") Long blobId) {
		String mimeType = "";
		try {
			logger.info("in try block of getMimeTpe() of service /doc/getContentType/{blobId}");
			IpBlob blob = ipBlobDAO.findById(blobId);
			if (blob != null)
				mimeType = blob.getBlobContentType();
			logger.info("blob type in service : " + mimeType);
			return mimeType;

		} catch (Exception e) {
			logger.error("Error in contetype service :" + e.getMessage());
			e.printStackTrace();
			return mimeType;
		}
	}

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}
}