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

import za.co.idea.ip.orm.bean.IpBlob;
import za.co.idea.ip.orm.dao.IpBlobDAO;
import za.co.idea.ip.ws.bean.AttachmentMessage;

@Path(value = "/ds")
public class DocumentUploadService {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-ws");
	private IpBlobDAO ipBlobDAO;

	@POST
	@Path("/doc/upload/{blobId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Path("/doc/download/{blobId}/{name}")
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

	public IpBlobDAO getIpBlobDAO() {
		return ipBlobDAO;
	}

	public void setIpBlobDAO(IpBlobDAO ipBlobDAO) {
		this.ipBlobDAO = ipBlobDAO;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doc/update")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doc/create")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doc/getId/{entityId}/{entityTblNm}")
	public Long getId(@PathParam("entityId") Long entityId, @PathParam("entityTblNm") String entityTblNm) {
		Long ret = -999l;
		IpBlob blob = ipBlobDAO.getBlobByEntity(entityId, entityTblNm);
		if (blob != null)
			ret = blob.getBlobId();
		return ret;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/doc/getName/{blobId}")
	public String getName(@PathParam("blobId") Long blobId) {
		String ret = "";
		IpBlob blob = ipBlobDAO.findById(blobId);
		if (blob != null)
			ret = blob.getBlobName();
		return ret;
	}
}
