package za.co.idea.ip.orm.bean;

/**
 * IpStateTran entity. @author MyEclipse Persistence Tools
 */

public class IpStateTran implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -3375781700090567124L;
	private Integer tranId;
	private String tranEntity;
	private Integer tranCurrState;
	private Integer tranNextState;

	// Constructors

	/** default constructor */
	public IpStateTran() {
	}

	/** full constructor */
	public IpStateTran(String tranEntity, Integer tranCurrState, Integer tranNextState) {
		this.tranEntity = tranEntity;
		this.tranCurrState = tranCurrState;
		this.tranNextState = tranNextState;
	}

	// Property accessors

	public Integer getTranId() {
		return this.tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public String getTranEntity() {
		return this.tranEntity;
	}

	public void setTranEntity(String tranEntity) {
		this.tranEntity = tranEntity;
	}

	public Integer getTranCurrState() {
		return this.tranCurrState;
	}

	public void setTranCurrState(Integer tranCurrState) {
		this.tranCurrState = tranCurrState;
	}

	public Integer getTranNextState() {
		return this.tranNextState;
	}

	public void setTranNextState(Integer tranNextState) {
		this.tranNextState = tranNextState;
	}

}