package za.co.idea.web.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AccessController {

	private Long userId;
	private boolean createUserEnabled;
	private boolean createGroupEnabled;
	private boolean createFunctionEnabled;
	private boolean createRewardsEnabled;
	private boolean createChallengeEnabled;
	private boolean createIdeaEnabled;
	private boolean editUserEnabled;
	private boolean editGroupEnabled;
	private boolean editFunctionEnabled;
	private boolean editRewardsEnabled;
	private boolean editChallengeEnabled;
	private boolean editIdeaEnabled;

	public void initializeAccess() {
		if (userId == null) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Initialize access without User Session", "Cannot Initialize access without User Session");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {

		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isCreateUserEnabled() {
		return createUserEnabled;
	}

	public void setCreateUserEnabled(boolean createUserEnabled) {
		this.createUserEnabled = createUserEnabled;
	}

	public boolean isCreateGroupEnabled() {
		return createGroupEnabled;
	}

	public void setCreateGroupEnabled(boolean createGroupEnabled) {
		this.createGroupEnabled = createGroupEnabled;
	}

	public boolean isCreateFunctionEnabled() {
		return createFunctionEnabled;
	}

	public void setCreateFunctionEnabled(boolean createFunctionEnabled) {
		this.createFunctionEnabled = createFunctionEnabled;
	}

	public boolean isCreateRewardsEnabled() {
		return createRewardsEnabled;
	}

	public void setCreateRewardsEnabled(boolean createRewardsEnabled) {
		this.createRewardsEnabled = createRewardsEnabled;
	}

	public boolean isCreateChallengeEnabled() {
		return createChallengeEnabled;
	}

	public void setCreateChallengeEnabled(boolean createChallengeEnabled) {
		this.createChallengeEnabled = createChallengeEnabled;
	}

	public boolean isCreateIdeaEnabled() {
		return createIdeaEnabled;
	}

	public void setCreateIdeaEnabled(boolean createIdeaEnabled) {
		this.createIdeaEnabled = createIdeaEnabled;
	}

	public boolean isEditUserEnabled() {
		return editUserEnabled;
	}

	public void setEditUserEnabled(boolean editUserEnabled) {
		this.editUserEnabled = editUserEnabled;
	}

	public boolean isEditGroupEnabled() {
		return editGroupEnabled;
	}

	public void setEditGroupEnabled(boolean editGroupEnabled) {
		this.editGroupEnabled = editGroupEnabled;
	}

	public boolean isEditFunctionEnabled() {
		return editFunctionEnabled;
	}

	public void setEditFunctionEnabled(boolean editFunctionEnabled) {
		this.editFunctionEnabled = editFunctionEnabled;
	}

	public boolean isEditRewardsEnabled() {
		return editRewardsEnabled;
	}

	public void setEditRewardsEnabled(boolean editRewardsEnabled) {
		this.editRewardsEnabled = editRewardsEnabled;
	}

	public boolean isEditChallengeEnabled() {
		return editChallengeEnabled;
	}

	public void setEditChallengeEnabled(boolean editChallengeEnabled) {
		this.editChallengeEnabled = editChallengeEnabled;
	}

	public boolean isEditIdeaEnabled() {
		return editIdeaEnabled;
	}

	public void setEditIdeaEnabled(boolean editIdeaEnabled) {
		this.editIdeaEnabled = editIdeaEnabled;
	}

}
