package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.ws.util.CustomObjectMapper;

public class AccessController implements Serializable {
	private static final long serialVersionUID = -1940992788177836525L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private List<String> functions;
	private boolean createUserEnabled;
	private boolean createGroupEnabled;
	private boolean createRewardsEnabled;
	private boolean createChallengeEnabled;
	private boolean createSolutionEnabled;
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
	private boolean createAllocatePoints;
	private boolean viewRewardsEnabled;
	private boolean onlineStoreEnabled;
	private boolean submitClaimEnabled;
	private boolean viewClaimsEnabled;
	private boolean viewChallengeEnabled;
	private boolean viewPublishedChallengesEnabled;
	private boolean submitSolutionEnabled;
	private boolean viewSolutionEnabled;
	private boolean viewOpenSolutionEnabled;
	private boolean viewRandomIdeaEnabled;
	private boolean viewOpenRandomIdeaEnabled;
	private boolean viewUserEnabled;
	private boolean uploadUserEnabled;
	private boolean viewGroupsEnabled;
	private boolean viewFunctionsEnabled;
	private boolean viewNewsEnabled;
	private boolean sendBroadcastEnabled;
	private boolean adminEnabled;
	private boolean reviewChallengeEnabled;
	private boolean reviewSolutionEnabled;
	private boolean reviewIdeaEnabled;

	public AccessController(Long userId) {
		if (userId == null) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Initialize access without User Session", "Cannot Initialize access without User Session");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/func/list/user/" + userId, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
			client.header("Content-Type", MediaType.APPLICATION_JSON);
			client.header("Accept", MediaType.APPLICATION_JSON);
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

	public boolean isCreateSolutionEnabled() {
		createSolutionEnabled = functions.contains("Create Solution");
		return createSolutionEnabled;
	}

	public void setCreateSolutionEnabled(boolean createSolutionEnabled) {
		this.createSolutionEnabled = createSolutionEnabled;
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

	public boolean isCreateAllocatePoints() {
		createAllocatePoints = functions.contains("Allocate Points");
		return createAllocatePoints;
	}

	public void setCreateAllocatePoints(boolean createAllocatePoints) {
		this.createAllocatePoints = createAllocatePoints;
	}

	public boolean isViewRewardsEnabled() {
		viewRewardsEnabled = functions.contains("View Rewards");
		return viewRewardsEnabled;
	}

	public void setViewRewardsEnabled(boolean viewRewardsEnabled) {
		this.viewRewardsEnabled = viewRewardsEnabled;
	}

	public boolean isOnlineStoreEnabled() {
		onlineStoreEnabled = functions.contains("Online Store");
		return onlineStoreEnabled;
	}

	public void setOnlineStoreEnabled(boolean onlineStoreEnabled) {
		this.onlineStoreEnabled = onlineStoreEnabled;
	}

	public boolean isSubmitClaimEnabled() {
		submitClaimEnabled = functions.contains("Submit Claim");
		return submitClaimEnabled;
	}

	public void setSubmitClaimEnabled(boolean submitClaimEnabled) {
		this.submitClaimEnabled = submitClaimEnabled;
	}

	public boolean isViewClaimsEnabled() {
		viewClaimsEnabled = functions.contains("View Claims");
		return viewClaimsEnabled;
	}

	public void setViewClaimsEnabled(boolean viewClaimsEnabled) {
		this.viewClaimsEnabled = viewClaimsEnabled;
	}

	public boolean isViewChallengeEnabled() {
		viewChallengeEnabled = functions.contains("View Challenge");
		return viewChallengeEnabled;
	}

	public void setViewChallengeEnabled(boolean viewChallengeEnabled) {
		this.viewChallengeEnabled = viewChallengeEnabled;
	}

	public boolean isViewPublishedChallengesEnabled() {
		viewPublishedChallengesEnabled = functions.contains("View Published Challenges");
		return viewPublishedChallengesEnabled;
	}

	public void setViewPublishedChallengesEnabled(boolean viewPublishedChallengesEnabled) {
		this.viewPublishedChallengesEnabled = viewPublishedChallengesEnabled;
	}

	public boolean isSubmitSolutionEnabled() {
		submitSolutionEnabled = functions.contains("Submit Solution");
		return submitSolutionEnabled;
	}

	public void setSubmitSolutionEnabled(boolean submitSolutionEnabled) {
		this.submitSolutionEnabled = submitSolutionEnabled;
	}

	public boolean isViewSolutionEnabled() {
		viewSolutionEnabled = functions.contains("View Solution");
		return viewSolutionEnabled;
	}

	public void setViewSolutionEnabled(boolean viewSolutionEnabled) {
		this.viewSolutionEnabled = viewSolutionEnabled;
	}

	public boolean isViewOpenSolutionEnabled() {
		viewOpenSolutionEnabled = functions.contains("View Open Solution");
		return viewOpenSolutionEnabled;
	}

	public void setViewOpenSolutionEnabled(boolean viewOpenSolutionEnabled) {
		this.viewOpenSolutionEnabled = viewOpenSolutionEnabled;
	}

	public boolean isViewRandomIdeaEnabled() {
		viewRandomIdeaEnabled = functions.contains("View Random Idea");
		return viewRandomIdeaEnabled;
	}

	public void setViewRandomIdeaEnabled(boolean viewRandomIdeaEnabled) {
		this.viewRandomIdeaEnabled = viewRandomIdeaEnabled;
	}

	public boolean isViewOpenRandomIdeaEnabled() {
		viewOpenRandomIdeaEnabled = functions.contains("View Open Random Idea");
		return viewOpenRandomIdeaEnabled;
	}

	public void setViewOpenRandomIdeaEnabled(boolean viewOpenRandomIdeaEnabled) {
		this.viewOpenRandomIdeaEnabled = viewOpenRandomIdeaEnabled;
	}

	public boolean isViewUserEnabled() {
		viewUserEnabled = functions.contains("View User");
		return viewUserEnabled;
	}

	public void setViewUserEnabled(boolean viewUserEnabled) {
		this.viewUserEnabled = viewUserEnabled;
	}

	public boolean isUploadUserEnabled() {
		uploadUserEnabled = functions.contains("Upload User");
		return uploadUserEnabled;
	}

	public void setUploadUserEnabled(boolean uploadUserEnabled) {
		this.uploadUserEnabled = uploadUserEnabled;
	}

	public boolean isViewGroupsEnabled() {
		viewGroupsEnabled = functions.contains("View Groups");
		return viewGroupsEnabled;
	}

	public void setViewGroupsEnabled(boolean viewGroupsEnabled) {
		this.viewGroupsEnabled = viewGroupsEnabled;
	}

	public boolean isViewFunctionsEnabled() {
		viewFunctionsEnabled = functions.contains("View Functions");
		return viewFunctionsEnabled;
	}

	public void setViewFunctionsEnabled(boolean viewFunctionsEnabled) {
		this.viewFunctionsEnabled = viewFunctionsEnabled;
	}

	public boolean isViewNewsEnabled() {
		viewNewsEnabled = functions.contains("View News");
		return viewNewsEnabled;
	}

	public void setViewNewsEnabled(boolean viewNewsEnabled) {
		this.viewNewsEnabled = viewNewsEnabled;
	}

	public boolean isSendBroadcastEnabled() {
		sendBroadcastEnabled = functions.contains("Send Broadcast");
		return sendBroadcastEnabled;
	}

	public void setSendBroadcastEnabled(boolean sendBroadcastEnabled) {
		this.sendBroadcastEnabled = sendBroadcastEnabled;
	}

	public boolean isAdminEnabled() {
		adminEnabled = functions.contains("Admin");
		return adminEnabled;
	}

	public void setAdminEnabled(boolean adminEnabled) {
		this.adminEnabled = adminEnabled;
	}

	public boolean isReviewChallengeEnabled() {
		reviewChallengeEnabled = functions.contains("Review Challenge");
		return reviewChallengeEnabled;
	}

	public void setReviewChallengeEnabled(boolean reviewChallengeEnabled) {
		this.reviewChallengeEnabled = reviewChallengeEnabled;
	}

	public boolean isReviewSolutionEnabled() {
		reviewSolutionEnabled = functions.contains("Review Solution");
		return reviewSolutionEnabled;
	}

	public void setReviewSolutionEnabled(boolean reviewSolutionEnabled) {
		this.reviewSolutionEnabled = reviewSolutionEnabled;
	}

	public boolean isReviewIdeaEnabled() {
		reviewIdeaEnabled = functions.contains("Review Idea");
		return reviewIdeaEnabled;
	}

	public void setReviewIdeaEnabled(boolean reviewIdeaEnabled) {
		this.reviewIdeaEnabled = reviewIdeaEnabled;
	}

}
