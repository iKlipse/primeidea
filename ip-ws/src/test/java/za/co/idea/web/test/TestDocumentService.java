package za.co.idea.web.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.ws.util.CustomObjectMapper;

public class TestDocumentService {
	public static void main(String[] args) throws Exception {
		new TestDocumentService().testUpload();
	}

	public void testUpload() throws FileNotFoundException {
		WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/multiUpload/115/true", Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
		client.header("Accept", MediaType.APPLICATION_JSON);
		Response response = client.accept(MediaType.APPLICATION_JSON).post(new Attachment("115", new FileInputStream(new File("/software/button_lightbulb.png")), new ContentDisposition("attachment;filename=button_lightbulb.png")));
		System.out.println(response.getStatus());
	}

	public void testDownload() throws IOException {
		WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/download/115/button_lightbulb.png", Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.MULTIPART_FORM_DATA);
		Attachment attachment = client.accept(MediaType.MULTIPART_FORM_DATA).get(Attachment.class);
		File file = new File("c:\\ws\\logon.jpg");
		file.createNewFile();
		FileOutputStream stream = new FileOutputStream(file);
		byte[] b = new byte[1024];
		InputStream inputStream = attachment.getDataHandler().getInputStream();
		while (true) {
			int ret = inputStream.read(b);
			if (ret < 0)
				break;
			stream.write(b);
		}
		stream.flush();
		stream.close();
	}
}
