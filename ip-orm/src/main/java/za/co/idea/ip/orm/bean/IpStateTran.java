package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpStateTran entity. @author MyEclipse Persistence Tools
 */

public class IpStateTran implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -316549226191412821L;
	private Integer tranId;
	private String tranEntity;
	private Integer tranCurrState;
	private Integer tranNextState;
	private String tranIsUi;
	private Date tranCrtdDt;

	// Constructors

	/** default constructor */
	public IpStateTran() {
	}

	/** full constructor */
	public IpStateTran(String tranEntity, Integer tranCurrState,
			Integer tranNextState, String tranIsUi, Date tranCrtdDt) {
		this.tranEntity = tranEntity;
		this.tranCurrState = tranCurrState;
		this.tranNextState = tranNextState;
		this.tranIsUi = tranIsUi;
		this.tranCrtdDt = tranCrtdDt;
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

	public String getTranIsUi() {
		return this.tranIsUi;
	}

	public void setTranIsUi(String tranIsUi) {
		this.tranIsUi = tranIsUi;
	}

	public Date getTranCrtdDt() {
		return this.tranCrtdDt;
	}

	public void setTranCrtdDt(Date tranCrtdDt) {
		this.tranCrtdDt = tranCrtdDt;
	}

}