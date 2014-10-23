package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IpConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_config", catalog = "lpdb")
public class IpConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 845519725292513945L;
	private Integer configId;
	private String configKey;
	private String configValue;
	private String configEnv;
	private Date createdDate;
	private String createdBy;

	// Constructors

	/** default constructor */
	public IpConfig() {
	}

	/** full constructor */
	public IpConfig(String configKey, String configValue, String configEnv, Date createdDate, String createdBy) {
		this.configKey = configKey;
		this.configValue = configValue;
		this.configEnv = configEnv;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	// Property accessors
	@Id
	@Column(name = "config_id", unique = true, nullable = false)
	public Integer getConfigId() {
		return this.configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	@Column(name = "config_key", nullable = false, length = 65535)
	public String getConfigKey() {
		return this.configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	@Column(name = "config_value", nullable = false, length = 65535)
	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@Column(name = "config_env", nullable = false, length = 45)
	public String getConfigEnv() {
		return this.configEnv;
	}

	public void setConfigEnv(String configEnv) {
		this.configEnv = configEnv;
	}

	@Column(name = "created_date", nullable = false, length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "created_by", nullable = false, length = 65535)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}