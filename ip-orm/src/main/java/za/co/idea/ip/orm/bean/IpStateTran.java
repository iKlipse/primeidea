package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IpStateTran entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_state_tran", catalog = "lpdb")
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
	public IpStateTran(String tranEntity, Integer tranCurrState, Integer tranNextState, String tranIsUi, Date tranCrtdDt) {
		this.tranEntity = tranEntity;
		this.tranCurrState = tranCurrState;
		this.tranNextState = tranNextState;
		this.tranIsUi = tranIsUi;
		this.tranCrtdDt = tranCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "tran_id", unique = true, nullable = false)
	public Integer getTranId() {
		return this.tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	@Column(name = "tran_entity", nullable = false, length = 450)
	public String getTranEntity() {
		return this.tranEntity;
	}

	public void setTranEntity(String tranEntity) {
		this.tranEntity = tranEntity;
	}

	@Column(name = "tran_curr_state", nullable = false)
	public Integer getTranCurrState() {
		return this.tranCurrState;
	}

	public void setTranCurrState(Integer tranCurrState) {
		this.tranCurrState = tranCurrState;
	}

	@Column(name = "tran_next_state", nullable = false)
	public Integer getTranNextState() {
		return this.tranNextState;
	}

	public void setTranNextState(Integer tranNextState) {
		this.tranNextState = tranNextState;
	}

	@Column(name = "tran_is_ui", nullable = false, length = 1)
	public String getTranIsUi() {
		return this.tranIsUi;
	}

	public void setTranIsUi(String tranIsUi) {
		this.tranIsUi = tranIsUi;
	}

	@Column(name = "tran_crtd_dt", nullable = false, length = 19)
	public Date getTranCrtdDt() {
		return this.tranCrtdDt;
	}

	public void setTranCrtdDt(Date tranCrtdDt) {
		this.tranCrtdDt = tranCrtdDt;
	}

}