package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IpGroup}.
 * </p>
 *
 * @author VMPattamatta
 * @see IpGroup
 * @generated
 */
public class IpGroupWrapper implements IpGroup, ModelWrapper<IpGroup> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6233545137787169210L;
	private IpGroup _ipGroup;

    public IpGroupWrapper(IpGroup ipGroup) {
        _ipGroup = ipGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return IpGroup.class;
    }

    @Override
    public String getModelClassName() {
        return IpGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("groupId", getGroupId());
        attributes.put("groupAdminId", getGroupAdminId());
        attributes.put("groupParentId", getGroupParentId());
        attributes.put("groupName", getGroupName());
        attributes.put("groupStatus", getGroupStatus());
        attributes.put("groupEmail", getGroupEmail());
        attributes.put("groupIsCore", getGroupIsCore());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }

        Long groupAdminId = (Long) attributes.get("groupAdminId");

        if (groupAdminId != null) {
            setGroupAdminId(groupAdminId);
        }

        Long groupParentId = (Long) attributes.get("groupParentId");

        if (groupParentId != null) {
            setGroupParentId(groupParentId);
        }

        String groupName = (String) attributes.get("groupName");

        if (groupName != null) {
            setGroupName(groupName);
        }

        String groupStatus = (String) attributes.get("groupStatus");

        if (groupStatus != null) {
            setGroupStatus(groupStatus);
        }

        String groupEmail = (String) attributes.get("groupEmail");

        if (groupEmail != null) {
            setGroupEmail(groupEmail);
        }

        String groupIsCore = (String) attributes.get("groupIsCore");

        if (groupIsCore != null) {
            setGroupIsCore(groupIsCore);
        }
    }

    /**
    * Returns the primary key of this ip group.
    *
    * @return the primary key of this ip group
    */
    @Override
    public long getPrimaryKey() {
        return _ipGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ip group.
    *
    * @param primaryKey the primary key of this ip group
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ipGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the group ID of this ip group.
    *
    * @return the group ID of this ip group
    */
    @Override
    public long getGroupId() {
        return _ipGroup.getGroupId();
    }

    /**
    * Sets the group ID of this ip group.
    *
    * @param groupId the group ID of this ip group
    */
    @Override
    public void setGroupId(long groupId) {
        _ipGroup.setGroupId(groupId);
    }

    /**
    * Returns the group admin ID of this ip group.
    *
    * @return the group admin ID of this ip group
    */
    @Override
    public long getGroupAdminId() {
        return _ipGroup.getGroupAdminId();
    }

    /**
    * Sets the group admin ID of this ip group.
    *
    * @param groupAdminId the group admin ID of this ip group
    */
    @Override
    public void setGroupAdminId(long groupAdminId) {
        _ipGroup.setGroupAdminId(groupAdminId);
    }

    /**
    * Returns the group parent ID of this ip group.
    *
    * @return the group parent ID of this ip group
    */
    @Override
    public long getGroupParentId() {
        return _ipGroup.getGroupParentId();
    }

    /**
    * Sets the group parent ID of this ip group.
    *
    * @param groupParentId the group parent ID of this ip group
    */
    @Override
    public void setGroupParentId(long groupParentId) {
        _ipGroup.setGroupParentId(groupParentId);
    }

    /**
    * Returns the group name of this ip group.
    *
    * @return the group name of this ip group
    */
    @Override
    public java.lang.String getGroupName() {
        return _ipGroup.getGroupName();
    }

    /**
    * Sets the group name of this ip group.
    *
    * @param groupName the group name of this ip group
    */
    @Override
    public void setGroupName(java.lang.String groupName) {
        _ipGroup.setGroupName(groupName);
    }

    /**
    * Returns the group status of this ip group.
    *
    * @return the group status of this ip group
    */
    @Override
    public java.lang.String getGroupStatus() {
        return _ipGroup.getGroupStatus();
    }

    /**
    * Sets the group status of this ip group.
    *
    * @param groupStatus the group status of this ip group
    */
    @Override
    public void setGroupStatus(java.lang.String groupStatus) {
        _ipGroup.setGroupStatus(groupStatus);
    }

    /**
    * Returns the group email of this ip group.
    *
    * @return the group email of this ip group
    */
    @Override
    public java.lang.String getGroupEmail() {
        return _ipGroup.getGroupEmail();
    }

    /**
    * Sets the group email of this ip group.
    *
    * @param groupEmail the group email of this ip group
    */
    @Override
    public void setGroupEmail(java.lang.String groupEmail) {
        _ipGroup.setGroupEmail(groupEmail);
    }

    /**
    * Returns the group is core of this ip group.
    *
    * @return the group is core of this ip group
    */
    @Override
    public java.lang.String getGroupIsCore() {
        return _ipGroup.getGroupIsCore();
    }

    /**
    * Sets the group is core of this ip group.
    *
    * @param groupIsCore the group is core of this ip group
    */
    @Override
    public void setGroupIsCore(java.lang.String groupIsCore) {
        _ipGroup.setGroupIsCore(groupIsCore);
    }

    @Override
    public boolean isNew() {
        return _ipGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ipGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ipGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ipGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ipGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ipGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ipGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ipGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ipGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ipGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ipGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IpGroupWrapper((IpGroup) _ipGroup.clone());
    }

    @Override
    public int compareTo(IpGroup ipGroup) {
        return _ipGroup.compareTo(ipGroup);
    }

    @Override
    public int hashCode() {
        return _ipGroup.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<IpGroup> toCacheModel() {
        return _ipGroup.toCacheModel();
    }

    @Override
    public IpGroup toEscapedModel() {
        return new IpGroupWrapper(_ipGroup.toEscapedModel());
    }

    @Override
    public IpGroup toUnescapedModel() {
        return new IpGroupWrapper(_ipGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ipGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ipGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ipGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IpGroupWrapper)) {
            return false;
        }

        IpGroupWrapper ipGroupWrapper = (IpGroupWrapper) obj;

        if (Validator.equals(_ipGroup, ipGroupWrapper._ipGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IpGroup getWrappedIpGroup() {
        return _ipGroup;
    }

    @Override
    public IpGroup getWrappedModel() {
        return _ipGroup;
    }

    @Override
    public void resetOriginalValues() {
        _ipGroup.resetOriginalValues();
    }
}
