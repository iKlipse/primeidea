package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IpLogin}.
 * </p>
 *
 * @author VMPattamatta
 * @see IpLogin
 * @generated
 */
public class IpLoginWrapper implements IpLogin, ModelWrapper<IpLogin> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7613310420693538286L;
	private IpLogin _ipLogin;

    public IpLoginWrapper(IpLogin ipLogin) {
        _ipLogin = ipLogin;
    }

    @Override
    public Class<?> getModelClass() {
        return IpLogin.class;
    }

    @Override
    public String getModelClassName() {
        return IpLogin.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("loginId", getLoginId());
        attributes.put("loginUserId", getLoginUserId());
        attributes.put("loginName", getLoginName());
        attributes.put("loginPwd", getLoginPwd());
        attributes.put("loginLastDt", getLoginLastDt());
        attributes.put("loginSecQ", getLoginSecQ());
        attributes.put("loginSecA", getLoginSecA());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long loginId = (Long) attributes.get("loginId");

        if (loginId != null) {
            setLoginId(loginId);
        }

        Long loginUserId = (Long) attributes.get("loginUserId");

        if (loginUserId != null) {
            setLoginUserId(loginUserId);
        }

        String loginName = (String) attributes.get("loginName");

        if (loginName != null) {
            setLoginName(loginName);
        }

        String loginPwd = (String) attributes.get("loginPwd");

        if (loginPwd != null) {
            setLoginPwd(loginPwd);
        }

        Date loginLastDt = (Date) attributes.get("loginLastDt");

        if (loginLastDt != null) {
            setLoginLastDt(loginLastDt);
        }

        Integer loginSecQ = (Integer) attributes.get("loginSecQ");

        if (loginSecQ != null) {
            setLoginSecQ(loginSecQ);
        }

        String loginSecA = (String) attributes.get("loginSecA");

        if (loginSecA != null) {
            setLoginSecA(loginSecA);
        }
    }

    /**
    * Returns the primary key of this ip login.
    *
    * @return the primary key of this ip login
    */
    @Override
    public long getPrimaryKey() {
        return _ipLogin.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ip login.
    *
    * @param primaryKey the primary key of this ip login
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ipLogin.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the login ID of this ip login.
    *
    * @return the login ID of this ip login
    */
    @Override
    public long getLoginId() {
        return _ipLogin.getLoginId();
    }

    /**
    * Sets the login ID of this ip login.
    *
    * @param loginId the login ID of this ip login
    */
    @Override
    public void setLoginId(long loginId) {
        _ipLogin.setLoginId(loginId);
    }

    /**
    * Returns the login user ID of this ip login.
    *
    * @return the login user ID of this ip login
    */
    @Override
    public long getLoginUserId() {
        return _ipLogin.getLoginUserId();
    }

    /**
    * Sets the login user ID of this ip login.
    *
    * @param loginUserId the login user ID of this ip login
    */
    @Override
    public void setLoginUserId(long loginUserId) {
        _ipLogin.setLoginUserId(loginUserId);
    }

    /**
    * Returns the login user uuid of this ip login.
    *
    * @return the login user uuid of this ip login
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getLoginUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLogin.getLoginUserUuid();
    }

    /**
    * Sets the login user uuid of this ip login.
    *
    * @param loginUserUuid the login user uuid of this ip login
    */
    @Override
    public void setLoginUserUuid(java.lang.String loginUserUuid) {
        _ipLogin.setLoginUserUuid(loginUserUuid);
    }

    /**
    * Returns the login name of this ip login.
    *
    * @return the login name of this ip login
    */
    @Override
    public java.lang.String getLoginName() {
        return _ipLogin.getLoginName();
    }

    /**
    * Sets the login name of this ip login.
    *
    * @param loginName the login name of this ip login
    */
    @Override
    public void setLoginName(java.lang.String loginName) {
        _ipLogin.setLoginName(loginName);
    }

    /**
    * Returns the login pwd of this ip login.
    *
    * @return the login pwd of this ip login
    */
    @Override
    public java.lang.String getLoginPwd() {
        return _ipLogin.getLoginPwd();
    }

    /**
    * Sets the login pwd of this ip login.
    *
    * @param loginPwd the login pwd of this ip login
    */
    @Override
    public void setLoginPwd(java.lang.String loginPwd) {
        _ipLogin.setLoginPwd(loginPwd);
    }

    /**
    * Returns the login last dt of this ip login.
    *
    * @return the login last dt of this ip login
    */
    @Override
    public java.util.Date getLoginLastDt() {
        return _ipLogin.getLoginLastDt();
    }

    /**
    * Sets the login last dt of this ip login.
    *
    * @param loginLastDt the login last dt of this ip login
    */
    @Override
    public void setLoginLastDt(java.util.Date loginLastDt) {
        _ipLogin.setLoginLastDt(loginLastDt);
    }

    /**
    * Returns the login sec q of this ip login.
    *
    * @return the login sec q of this ip login
    */
    @Override
    public int getLoginSecQ() {
        return _ipLogin.getLoginSecQ();
    }

    /**
    * Sets the login sec q of this ip login.
    *
    * @param loginSecQ the login sec q of this ip login
    */
    @Override
    public void setLoginSecQ(int loginSecQ) {
        _ipLogin.setLoginSecQ(loginSecQ);
    }

    /**
    * Returns the login sec a of this ip login.
    *
    * @return the login sec a of this ip login
    */
    @Override
    public java.lang.String getLoginSecA() {
        return _ipLogin.getLoginSecA();
    }

    /**
    * Sets the login sec a of this ip login.
    *
    * @param loginSecA the login sec a of this ip login
    */
    @Override
    public void setLoginSecA(java.lang.String loginSecA) {
        _ipLogin.setLoginSecA(loginSecA);
    }

    @Override
    public boolean isNew() {
        return _ipLogin.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ipLogin.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ipLogin.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ipLogin.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ipLogin.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ipLogin.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ipLogin.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ipLogin.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ipLogin.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ipLogin.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ipLogin.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IpLoginWrapper((IpLogin) _ipLogin.clone());
    }

    @Override
    public int compareTo(IpLogin ipLogin) {
        return _ipLogin.compareTo(ipLogin);
    }

    @Override
    public int hashCode() {
        return _ipLogin.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<IpLogin> toCacheModel() {
        return _ipLogin.toCacheModel();
    }

    @Override
    public IpLogin toEscapedModel() {
        return new IpLoginWrapper(_ipLogin.toEscapedModel());
    }

    @Override
    public IpLogin toUnescapedModel() {
        return new IpLoginWrapper(_ipLogin.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ipLogin.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ipLogin.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ipLogin.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IpLoginWrapper)) {
            return false;
        }

        IpLoginWrapper ipLoginWrapper = (IpLoginWrapper) obj;

        if (Validator.equals(_ipLogin, ipLoginWrapper._ipLogin)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IpLogin getWrappedIpLogin() {
        return _ipLogin;
    }

    @Override
    public IpLogin getWrappedModel() {
        return _ipLogin;
    }

    @Override
    public void resetOriginalValues() {
        _ipLogin.resetOriginalValues();
    }
}
