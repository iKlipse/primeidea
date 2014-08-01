package za.co.idea.ip.portal.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.ip.portal.bean.MetaDataBean;
import za.co.idea.ip.portal.util.IdNumberGen;

@ManagedBean(name = "metaDataController")
@SessionScoped
public class MetaDataController {

	private HashMap<String, String> metaList;
	private boolean showAddPanel;
	private boolean showModPanel;
	private boolean showAddBtn;
	private List<MetaDataBean> beans;
	private MetaDataBean bean;
	private String table;
	private String selId;
	private String selVal;
	private boolean showMetadataMaintain;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private String returnView;
	private String toView;

	private WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}
	
	public void initializePage() {
		try {
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = false;
			this.table = "";
			this.beans = null;
			showMetadataMaintain=true;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					this.showAddPanel = false;
					this.showModPanel = false;
					this.showAddBtn = false;
					this.table = "";
					this.beans = null;
					showMetadataMaintain=true;
					break;
				default:
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}
	
	public String redirectMain() {
		switch (Integer.valueOf(returnView)) {
		case 1:
			return "lani";
		case 2:
			return "adminv";
		default:
			return "";
		}
	}

	public void showMetadataMaintain() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = false;
		this.table = "";
		this.beans = null;
		showMetadataMaintain=true;
	}

	public String showMetaData() {
		this.showAddPanel = false;
		this.showModPanel = true;
		selId = bean.getId().toString();
		selVal = bean.getDesc();
		showMetadataMaintain=true;
		return "";
	}

	public String showMetaDataAdd() {
		bean = new MetaDataBean();
		selId = String.valueOf((COUNTER.getNextId(table).intValue()));
		selVal = "";
		this.showAddBtn = false;
		this.showAddPanel = true;
		this.showModPanel = false;
		showMetadataMaintain=true;
		return "";
	}

	public String updateMetaData() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = true;
		if (verifyMetadata(selVal)) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Create duplicate values", "Cannot Create duplicate values");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			this.showAddPanel = false;
			this.showModPanel = true;
			showMetadataMaintain=true;
			return "";
		}
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/modify");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = fetchAllMetadata();
			showMetadataMaintain=true;
			return "";
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String deleteMetaData() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = true;
		showMetadataMaintain=true;
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/delete");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = fetchAllMetadata();
			return "mdata";
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String addMetaData() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = true;
		if (verifyMetadata(selVal)) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Create duplicate values", "Cannot Create duplicate values");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			this.showAddPanel = true;
			this.showModPanel = false;
			return "";
		}
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/add");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = fetchAllMetadata();
			return "";
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void changeMetaData() {
		if (table.equalsIgnoreCase("")) {
			beans = new ArrayList<MetaDataBean>();
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = false;
		} else {
			beans = fetchAllMetadata();
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = true;
		}
	}

	private List<MetaDataBean> fetchAllMetadata() {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/" + table);
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			ret.add(bean);
		}
		return ret;
	}

	public HashMap<String, String> getMetaList() {
		if (metaList == null) {
			metaList = new HashMap<String, String>();
			metaList.put("Security Questions", "IpSecqList");
			metaList.put("Category", "IpCategory");
			metaList.put("Rewards Category", "IpRewardsCat");
		}
		return metaList;
	}

	private boolean verifyMetadata(String desc) {
		boolean ret = false;
		for (MetaDataBean bean : fetchAllMetadata()) {
			if (bean.getDesc().equalsIgnoreCase(desc)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public void setMetaList(HashMap<String, String> metaList) {
		this.metaList = metaList;
	}

	public boolean isShowAddPanel() {
		return showAddPanel;
	}

	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}

	public boolean isShowModPanel() {
		return showModPanel;
	}

	public void setShowModPanel(boolean showModPanel) {
		this.showModPanel = showModPanel;
	}

	public boolean isShowAddBtn() {
		return showAddBtn;
	}

	public void setShowAddBtn(boolean showAddBtn) {
		this.showAddBtn = showAddBtn;
	}

	public List<MetaDataBean> getBeans() {
		if (beans == null)
			beans = new ArrayList<MetaDataBean>();
		return beans;
	}

	public void setBeans(List<MetaDataBean> beans) {
		this.beans = beans;
	}

	public MetaDataBean getBean() {
		if (bean == null)
			bean = new MetaDataBean();
		return bean;
	}

	public void setBean(MetaDataBean bean) {
		this.bean = bean;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSelId() {
		return selId;
	}

	public String getSelVal() {
		return selVal;
	}

	public void setSelId(String selId) {
		this.selId = selId;
	}

	public void setSelVal(String selVal) {
		this.selVal = selVal;
	}

	public boolean isShowMetadataMaintain() {
		return showMetadataMaintain;
	}

	public void setShowMetadataMaintain(boolean showMetadataMaintain) {
		this.showMetadataMaintain = showMetadataMaintain;
	}

	public String getReturnView() {
		return returnView;
	}

	public void setReturnView(String returnView) {
		this.returnView = returnView;
	}

	public String getToView() {
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

}
