package za.co.idea.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunctionBean implements Serializable {

	private static final long serialVersionUID = -5754104006986083662L;
	private Long funcId;
	private String funcName;
	private List<Long> groupIdList;

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public List<Long> getGroupIdList() {
		if (groupIdList == null)
			groupIdList = new ArrayList<Long>();
		return groupIdList;
	}

	public void setGroupIdList(List<Long> groupIdList) {
		this.groupIdList = groupIdList;
	}

}
