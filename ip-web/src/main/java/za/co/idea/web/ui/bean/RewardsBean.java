package za.co.idea.web.ui.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import za.co.idea.ip.ws.util.CustomObjectMapper;

public class RewardsBean implements Serializable {
	private static final long serialVersionUID = -2381598448445463625L;
	private Long rwId;
	private Integer rCatId;
	private String rwTitle;
	private String rwDesc;
	private Integer rwValue;
	private String rwStockCodeNum;
	private String rwHoverText;
	private Date rwLaunchDt;
	private Date rwExpiryDt;
	private String rwTag;
	private String rwBlob;
	private Date rwCrtdDt;
	private String rwFileName;
	private String rwFileType;
	private Double rwPrice;
	private Long rwQuantity;
	private List<Long> groupIdList;
	private String rwUrl;
	private StreamedContent rwImg;
	private boolean rwTaggable;
	private boolean rwClaimable;
	private boolean rwImgAvail;

	public Long getRwId() {
		return rwId;
	}

	public void setRwId(Long rwId) {
		this.rwId = rwId;
	}

	public Integer getrCatId() {
		return rCatId;
	}

	public void setrCatId(Integer rCatId) {
		this.rCatId = rCatId;
	}

	public String getRwTitle() {
		return rwTitle;
	}

	public void setRwTitle(String rwTitle) {
		this.rwTitle = rwTitle;
	}

	public String getRwDesc() {
		return rwDesc;
	}

	public void setRwDesc(String rwDesc) {
		this.rwDesc = rwDesc;
	}

	public Integer getRwValue() {
		return rwValue;
	}

	public void setRwValue(Integer rwValue) {
		this.rwValue = rwValue;
	}

	public String getRwStockCodeNum() {
		return rwStockCodeNum;
	}

	public void setRwStockCodeNum(String rwStockCodeNum) {
		this.rwStockCodeNum = rwStockCodeNum;
	}

	public String getRwHoverText() {
		return rwHoverText;
	}

	public void setRwHoverText(String rwHoverText) {
		this.rwHoverText = rwHoverText;
	}

	public Date getRwLaunchDt() {
		return rwLaunchDt;
	}

	public void setRwLaunchDt(Date rwLaunchDt) {
		this.rwLaunchDt = rwLaunchDt;
	}

	public Date getRwExpiryDt() {
		return rwExpiryDt;
	}

	public void setRwExpiryDt(Date rwExpiryDt) {
		this.rwExpiryDt = rwExpiryDt;
	}

	public String getRwTag() {
		return rwTag;
	}

	public void setRwTag(String rwTag) {
		this.rwTag = rwTag;
	}

	public String getRwBlob() {
		return rwBlob;
	}

	public void setRwBlob(String rwBlob) {
		this.rwBlob = rwBlob;
	}

	public Date getRwCrtdDt() {
		return rwCrtdDt;
	}

	public void setRwCrtdDt(Date rwCrtdDt) {
		this.rwCrtdDt = rwCrtdDt;
	}

	public String getRwFileName() {
		return rwFileName;
	}

	public void setRwFileName(String rwFileName) {
		this.rwFileName = rwFileName;
	}

	public String getRwFileType() {
		return rwFileType;
	}

	public void setRwFileType(String rwFileType) {
		this.rwFileType = rwFileType;
	}

	public Double getRwPrice() {
		return rwPrice;
	}

	public void setRwPrice(Double rwPrice) {
		this.rwPrice = rwPrice;
	}

	public Long getRwQuantity() {
		return rwQuantity;
	}

	public void setRwQuantity(Long rwQuantity) {
		this.rwQuantity = rwQuantity;
	}

	public List<Long> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Long> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public boolean isRwTaggable() {
		return rwTaggable;
	}

	public void setRwTaggable(boolean rwTaggable) {
		this.rwTaggable = rwTaggable;
	}

	public boolean isRwClaimable() {
		return rwClaimable;
	}

	public void setRwClaimable(boolean rwClaimable) {
		this.rwClaimable = rwClaimable;
	}

	public boolean isRwImgAvail() {
		return rwImgAvail;
	}

	public void setRwImgAvail(boolean rwImgAvail) {
		this.rwImgAvail = rwImgAvail;
	}

	public StreamedContent getRwImg() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			WebClient client = WebClient.create(rwUrl, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
			client.header("Content-Type", "application/json");
			client.header("Accept", MediaType.MULTIPART_FORM_DATA);
			Attachment attachment = client.accept(MediaType.MULTIPART_FORM_DATA).get(Attachment.class);
			if (attachment != null)
				try {
					rwImg = new DefaultStreamedContent(attachment.getDataHandler().getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return rwImg;
		}
	}

	public void setRwImg(StreamedContent rwImg) {
		this.rwImg = rwImg;
	}

	public String getRwUrl() {
		return rwUrl;
	}

	public void setRwUrl(String rwUrl) {
		this.rwUrl = rwUrl;
	}
}