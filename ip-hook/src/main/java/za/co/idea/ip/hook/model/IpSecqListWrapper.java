package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IpSecqList}.
 * </p>
 *
 * @author VMPattamatta
 * @see IpSecqList
 * @generated
 */
public class IpSecqListWrapper implements IpSecqList, ModelWrapper<IpSecqList> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1731701717330913156L;
	private IpSecqList _ipSecqList;

    public IpSecqListWrapper(IpSecqList ipSecqList) {
        _ipSecqList = ipSecqList;
    }

    @Override
    public Class<?> getModelClass() {
        return IpSecqList.class;
    }

    @Override
    public String getModelClassName() {
        return IpSecqList.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("islId", getIslId());
        attributes.put("islDesc", getIslDesc());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long islId = (Long) attributes.get("islId");

        if (islId != null) {
            setIslId(islId);
        }

        String islDesc = (String) attributes.get("islDesc");

        if (islDesc != null) {
            setIslDesc(islDesc);
        }
    }

    /**
    * Returns the primary key of this ip secq list.
    *
    * @return the primary key of this ip secq list
    */
    @Override
    public long getPrimaryKey() {
        return _ipSecqList.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ip secq list.
    *
    * @param primaryKey the primary key of this ip secq list
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ipSecqList.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the isl ID of this ip secq list.
    *
    * @return the isl ID of this ip secq list
    */
    @Override
    public long getIslId() {
        return _ipSecqList.getIslId();
    }

    /**
    * Sets the isl ID of this ip secq list.
    *
    * @param islId the isl ID of this ip secq list
    */
    @Override
    public void setIslId(long islId) {
        _ipSecqList.setIslId(islId);
    }

    /**
    * Returns the isl desc of this ip secq list.
    *
    * @return the isl desc of this ip secq list
    */
    @Override
    public java.lang.String getIslDesc() {
        return _ipSecqList.getIslDesc();
    }

    /**
    * Sets the isl desc of this ip secq list.
    *
    * @param islDesc the isl desc of this ip secq list
    */
    @Override
    public void setIslDesc(java.lang.String islDesc) {
        _ipSecqList.setIslDesc(islDesc);
    }

    @Override
    public boolean isNew() {
        return _ipSecqList.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ipSecqList.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ipSecqList.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ipSecqList.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ipSecqList.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ipSecqList.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ipSecqList.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ipSecqList.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ipSecqList.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ipSecqList.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ipSecqList.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IpSecqListWrapper((IpSecqList) _ipSecqList.clone());
    }

    @Override
    public int compareTo(IpSecqList ipSecqList) {
        return _ipSecqList.compareTo(ipSecqList);
    }

    @Override
    public int hashCode() {
        return _ipSecqList.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<IpSecqList> toCacheModel() {
        return _ipSecqList.toCacheModel();
    }

    @Override
    public IpSecqList toEscapedModel() {
        return new IpSecqListWrapper(_ipSecqList.toEscapedModel());
    }

    @Override
    public IpSecqList toUnescapedModel() {
        return new IpSecqListWrapper(_ipSecqList.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ipSecqList.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ipSecqList.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ipSecqList.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IpSecqListWrapper)) {
            return false;
        }

        IpSecqListWrapper ipSecqListWrapper = (IpSecqListWrapper) obj;

        if (Validator.equals(_ipSecqList, ipSecqListWrapper._ipSecqList)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IpSecqList getWrappedIpSecqList() {
        return _ipSecqList;
    }

    @Override
    public IpSecqList getWrappedModel() {
        return _ipSecqList;
    }

    @Override
    public void resetOriginalValues() {
        _ipSecqList.resetOriginalValues();
    }
}
