package za.co.idea.web.util;

import java.io.Serializable;

public class MenuListener implements Serializable {
	private static final long serialVersionUID = 4738500332075945536L;

	public String showCreateUser() {
		return "admcu";
	}

	public String showCreateGroup() {
		return "admcg";
	}

	public String showViewGroups() {
		return "admvg";
	}

	public String showCreateIdea() {
		return "ideaci";
	}

	public String showViewIdeas() {
		return "ideavw";
	}

}
