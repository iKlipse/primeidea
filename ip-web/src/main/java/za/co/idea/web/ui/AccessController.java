package za.co.idea.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.ws.util.CustomObjectMapper;

public class AccessController implements Serializable {
	private static final long serialVersionUID = -1940992788177836525L;
	private List<String> functions;
	private boolean createUserEnabled;
	private boolean createGroupEnabled;
	private boolean createFunctionEnabled;
	private boolean createRewardsEnabled;
	private boolean createChallengeEnabled;
	private boolean createIdeaEnabled;
	private boolean createNewsEnabled;
	private boolean editUserEnabled;
	private boolean editGroupEnabled;
	private boolean editFunctionEnabled;
	private boolean editRewardsEnabled;
	private boolean editChallengeEnabled;
	private boolean editClaimEnabled;
	private boolean editSolutionEnabled;
	private boolean editIdeaEnabled;
	private boolean editNewsEnabled;
	private boolean maintainPointAllocEnabled;
	private boolean maintainMetaDataEnabled;

	public AccessController(Long userId) {
		if (userId == null) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Initialize access without User Session", "Cannot Initialize access without User Session");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/as/func/list/user/" + userId, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
			client.header("Content-Type", "application/json");
			client.header("Accept", "application/json");
			String[] funcs = client.accept(MediaType.APPLICATION_JSON).get(String[].class);
			functions = new ArrayList<String>();
			for (String string : funcs) {
				functions.add(string);
			}
		}
	}

	public boolean isCreateUserEnabled() {
		createUserEnabled = functions.contains("Create User");
		return createUserEnabled;
	}

	public void setCreateUserEnabled(boolean createUserEnabled) {
		this.createUserEnabled = createUserEnabled;
	}

	public boolean isCreateGroupEnabled() {
		createGroupEnabled = functions.contains("Create Group");
		return createGroupEnabled;
	}

	public void setCreateGroupEnabled(boolean createGroupEnabled) {
		this.createGroupEnabled = createGroupEnabled;
	}

	public boolean isCreateFunctionEnabled() {
		createFunctionEnabled = functions.contains("Create Function");
		return createFunctionEnabled;
	}

	public void setCreateFunctionEnabled(boolean createFunctionEnabled) {
		this.createFunctionEnabled = createFunctionEnabled;
	}

	public boolean isCreateRewardsEnabled() {
		createRewardsEnabled = functions.contains("Create Rewards");
		return createRewardsEnabled;
	}

	public void setCreateRewardsEnabled(boolean createRewardsEnabled) {
		this.createRewardsEnabled = createRewardsEnabled;
	}

	public boolean isCreateChallengeEnabled() {
		createChallengeEnabled = functions.contains("Create Challenge");
		return createChallengeEnabled;
	}

	public void setCreateChallengeEnabled(boolean createChallengeEnabled) {
		this.createChallengeEnabled = createChallengeEnabled;
	}

	public boolean isCreateIdeaEnabled() {
		createIdeaEnabled = functions.contains("Create Idea");
		return createIdeaEnabled;
	}

	public void setCreateIdeaEnabled(boolean createIdeaEnabled) {
		this.createIdeaEnabled = createIdeaEnabled;
	}

	public boolean isEditUserEnabled() {
		editUserEnabled = functions.contains("Edit User");
		return editUserEnabled;
	}

	public void setEditUserEnabled(boolean editUserEnabled) {
		this.editUserEnabled = editUserEnabled;
	}

	public boolean isEditGroupEnabled() {
		editGroupEnabled = functions.contains("Edit Group");
		return editGroupEnabled;
	}

	public void setEditGroupEnabled(boolean editGroupEnabled) {
		this.editGroupEnabled = editGroupEnabled;
	}

	public boolean isEditFunctionEnabled() {
		editFunctionEnabled = functions.contains("Edit Function");
		return editFunctionEnabled;
	}

	public void setEditFunctionEnabled(boolean editFunctionEnabled) {
		this.editFunctionEnabled = editFunctionEnabled;
	}

	public boolean isEditRewardsEnabled() {
		editRewardsEnabled = functions.contains("Edit Rewards");
		return editRewardsEnabled;
	}

	public void setEditRewardsEnabled(boolean editRewardsEnabled) {
		this.editRewardsEnabled = editRewardsEnabled;
	}

	public boolean isEditChallengeEnabled() {
		editChallengeEnabled = functions.contains("Edit Challenge");
		return editChallengeEnabled;
	}

	public void setEditChallengeEnabled(boolean editChallengeEnabled) {
		this.editChallengeEnabled = editChallengeEnabled;
	}

	public boolean isEditIdeaEnabled() {
		editIdeaEnabled = functions.contains("Edit Idea");
		return editIdeaEnabled;
	}

	public void setEditIdeaEnabled(boolean editIdeaEnabled) {
		this.editIdeaEnabled = editIdeaEnabled;
	}

	public List<String> getFunctions() {
		return functions;
	}

	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}

	public boolean isMaintainPointAllocEnabled() {
		maintainPointAllocEnabled = functions.contains("Maintain Point Allocation");
		return maintainPointAllocEnabled;
	}

	public void setMaintainPointAllocEnabled(boolean maintainPointAllocEnabled) {
		this.maintainPointAllocEnabled = maintainPointAllocEnabled;
	}

	public boolean isMaintainMetaDataEnabled() {
		maintainMetaDataEnabled = functions.contains("Maintain MetaData");
		return maintainMetaDataEnabled;
	}

	public void setMaintainMetaDataEnabled(boolean maintainMetaDataEnabled) {
		this.maintainMetaDataEnabled = maintainMetaDataEnabled;
	}

	public boolean isEditClaimEnabled() {
		editClaimEnabled = functions.contains("Edit Claim");
		return editClaimEnabled;
	}

	public void setEditClaimEnabled(boolean editClaimEnabled) {
		this.editClaimEnabled = editClaimEnabled;
	}

	public boolean isEditSolutionEnabled() {
		editSolutionEnabled = functions.contains("Edit Solution");
		return editSolutionEnabled;
	}

	public void setEditSolutionEnabled(boolean editSolutionEnabled) {
		this.editSolutionEnabled = editSolutionEnabled;
	}

	public boolean isCreateNewsEnabled() {
		createNewsEnabled = functions.contains("Create News");
		return createNewsEnabled;
	}

	public void setCreateNewsEnabled(boolean createNewsEnabled) {
		this.createNewsEnabled = createNewsEnabled;
	}

	public boolean isEditNewsEnabled() {
		editNewsEnabled = functions.contains("Edit News");
		return editNewsEnabled;
	}

	public void setEditNewsEnabled(boolean editNewsEnabled) {
		this.editNewsEnabled = editNewsEnabled;
	}

}
