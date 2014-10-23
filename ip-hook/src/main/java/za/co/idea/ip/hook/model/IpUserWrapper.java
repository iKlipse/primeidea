package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IpUser}.
 * </p>
 *
 * @author VMPattamatta
 * @see IpUser
 * @generated
 */
public class IpUserWrapper implements IpUser, ModelWrapper<IpUser> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3644864062490116633L;
	private IpUser _ipUser;

    public IpUserWrapper(IpUser ipUser) {
        _ipUser = ipUser;
    }

    @Override
    public Class<?> getModelClass() {
        return IpUser.class;
    }

    @Override
    public String getModelClassName() {
        return IpUser.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("userFName", getUserFName());
        attributes.put("userLName", getUserLName());
        attributes.put("userMName", getUserMName());
        attributes.put("userIdNum", getUserIdNum());
        attributes.put("userScreenName", getUserScreenName());
        attributes.put("userEmail", getUserEmail());
        attributes.put("userContact", getUserContact());
        attributes.put("userSkills", getUserSkills());
        attributes.put("userBio", getUserBio());
        attributes.put("userFbHandle", getUserFbHandle());
        attributes.put("userTwHandle", getUserTwHandle());
        attributes.put("userStatus", getUserStatus());
        attributes.put("userEmployeeId", getUserEmployeeId());
        attributes.put("priGrpId", getPriGrpId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String userFName = (String) attributes.get("userFName");

        if (userFName != null) {
            setUserFName(userFName);
        }

        String userLName = (String) attributes.get("userLName");

        if (userLName != null) {
            setUserLName(userLName);
        }

        String userMName = (String) attributes.get("userMName");

        if (userMName != null) {
            setUserMName(userMName);
        }

        Long userIdNum = (Long) attributes.get("userIdNum");

        if (userIdNum != null) {
            setUserIdNum(userIdNum);
        }

        String userScreenName = (String) attributes.get("userScreenName");

        if (userScreenName != null) {
            setUserScreenName(userScreenName);
        }

        String userEmail = (String) attributes.get("userEmail");

        if (userEmail != null) {
            setUserEmail(userEmail);
        }

        String userContact = (String) attributes.get("userContact");

        if (userContact != null) {
            setUserContact(userContact);
        }

        String userSkills = (String) attributes.get("userSkills");

        if (userSkills != null) {
            setUserSkills(userSkills);
        }

        String userBio = (String) attributes.get("userBio");

        if (userBio != null) {
            setUserBio(userBio);
        }

        String userFbHandle = (String) attributes.get("userFbHandle");

        if (userFbHandle != null) {
            setUserFbHandle(userFbHandle);
        }

        String userTwHandle = (String) attributes.get("userTwHandle");

        if (userTwHandle != null) {
            setUserTwHandle(userTwHandle);
        }

        String userStatus = (String) attributes.get("userStatus");

        if (userStatus != null) {
            setUserStatus(userStatus);
        }

        String userEmployeeId = (String) attributes.get("userEmployeeId");

        if (userEmployeeId != null) {
            setUserEmployeeId(userEmployeeId);
        }

        Long priGrpId = (Long) attributes.get("priGrpId");

        if (priGrpId != null) {
            setPriGrpId(priGrpId);
        }
    }

    /**
    * Returns the primary key of this ip user.
    *
    * @return the primary key of this ip user
    */
    @Override
    public long getPrimaryKey() {
        return _ipUser.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ip user.
    *
    * @param primaryKey the primary key of this ip user
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ipUser.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this ip user.
    *
    * @return the user ID of this ip user
    */
    @Override
    public long getUserId() {
        return _ipUser.getUserId();
    }

    /**
    * Sets the user ID of this ip user.
    *
    * @param userId the user ID of this ip user
    */
    @Override
    public void setUserId(long userId) {
        _ipUser.setUserId(userId);
    }

    /**
    * Returns the user uuid of this ip user.
    *
    * @return the user uuid of this ip user
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUser.getUserUuid();
    }

    /**
    * Sets the user uuid of this ip user.
    *
    * @param userUuid the user uuid of this ip user
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _ipUser.setUserUuid(userUuid);
    }

    /**
    * Returns the user f name of this ip user.
    *
    * @return the user f name of this ip user
    */
    @Override
    public java.lang.String getUserFName() {
        return _ipUser.getUserFName();
    }

    /**
    * Sets the user f name of this ip user.
    *
    * @param userFName the user f name of this ip user
    */
    @Override
    public void setUserFName(java.lang.String userFName) {
        _ipUser.setUserFName(userFName);
    }

    /**
    * Returns the user l name of this ip user.
    *
    * @return the user l name of this ip user
    */
    @Override
    public java.lang.String getUserLName() {
        return _ipUser.getUserLName();
    }

    /**
    * Sets the user l name of this ip user.
    *
    * @param userLName the user l name of this ip user
    */
    @Override
    public void setUserLName(java.lang.String userLName) {
        _ipUser.setUserLName(userLName);
    }

    /**
    * Returns the user m name of this ip user.
    *
    * @return the user m name of this ip user
    */
    @Override
    public java.lang.String getUserMName() {
        return _ipUser.getUserMName();
    }

    /**
    * Sets the user m name of this ip user.
    *
    * @param userMName the user m name of this ip user
    */
    @Override
    public void setUserMName(java.lang.String userMName) {
        _ipUser.setUserMName(userMName);
    }

    /**
    * Returns the user ID num of this ip user.
    *
    * @return the user ID num of this ip user
    */
    @Override
    public long getUserIdNum() {
        return _ipUser.getUserIdNum();
    }

    /**
    * Sets the user ID num of this ip user.
    *
    * @param userIdNum the user ID num of this ip user
    */
    @Override
    public void setUserIdNum(long userIdNum) {
        _ipUser.setUserIdNum(userIdNum);
    }

    /**
    * Returns the user screen name of this ip user.
    *
    * @return the user screen name of this ip user
    */
    @Override
    public java.lang.String getUserScreenName() {
        return _ipUser.getUserScreenName();
    }

    /**
    * Sets the user screen name of this ip user.
    *
    * @param userScreenName the user screen name of this ip user
    */
    @Override
    public void setUserScreenName(java.lang.String userScreenName) {
        _ipUser.setUserScreenName(userScreenName);
    }

    /**
    * Returns the user email of this ip user.
    *
    * @return the user email of this ip user
    */
    @Override
    public java.lang.String getUserEmail() {
        return _ipUser.getUserEmail();
    }

    /**
    * Sets the user email of this ip user.
    *
    * @param userEmail the user email of this ip user
    */
    @Override
    public void setUserEmail(java.lang.String userEmail) {
        _ipUser.setUserEmail(userEmail);
    }

    /**
    * Returns the user contact of this ip user.
    *
    * @return the user contact of this ip user
    */
    @Override
    public java.lang.String getUserContact() {
        return _ipUser.getUserContact();
    }

    /**
    * Sets the user contact of this ip user.
    *
    * @param userContact the user contact of this ip user
    */
    @Override
    public void setUserContact(java.lang.String userContact) {
        _ipUser.setUserContact(userContact);
    }

    /**
    * Returns the user skills of this ip user.
    *
    * @return the user skills of this ip user
    */
    @Override
    public java.lang.String getUserSkills() {
        return _ipUser.getUserSkills();
    }

    /**
    * Sets the user skills of this ip user.
    *
    * @param userSkills the user skills of this ip user
    */
    @Override
    public void setUserSkills(java.lang.String userSkills) {
        _ipUser.setUserSkills(userSkills);
    }

    /**
    * Returns the user bio of this ip user.
    *
    * @return the user bio of this ip user
    */
    @Override
    public java.lang.String getUserBio() {
        return _ipUser.getUserBio();
    }

    /**
    * Sets the user bio of this ip user.
    *
    * @param userBio the user bio of this ip user
    */
    @Override
    public void setUserBio(java.lang.String userBio) {
        _ipUser.setUserBio(userBio);
    }

    /**
    * Returns the user fb handle of this ip user.
    *
    * @return the user fb handle of this ip user
    */
    @Override
    public java.lang.String getUserFbHandle() {
        return _ipUser.getUserFbHandle();
    }

    /**
    * Sets the user fb handle of this ip user.
    *
    * @param userFbHandle the user fb handle of this ip user
    */
    @Override
    public void setUserFbHandle(java.lang.String userFbHandle) {
        _ipUser.setUserFbHandle(userFbHandle);
    }

    /**
    * Returns the user tw handle of this ip user.
    *
    * @return the user tw handle of this ip user
    */
    @Override
    public java.lang.String getUserTwHandle() {
        return _ipUser.getUserTwHandle();
    }

    /**
    * Sets the user tw handle of this ip user.
    *
    * @param userTwHandle the user tw handle of this ip user
    */
    @Override
    public void setUserTwHandle(java.lang.String userTwHandle) {
        _ipUser.setUserTwHandle(userTwHandle);
    }

    /**
    * Returns the user status of this ip user.
    *
    * @return the user status of this ip user
    */
    @Override
    public java.lang.String getUserStatus() {
        return _ipUser.getUserStatus();
    }

    /**
    * Sets the user status of this ip user.
    *
    * @param userStatus the user status of this ip user
    */
    @Override
    public void setUserStatus(java.lang.String userStatus) {
        _ipUser.setUserStatus(userStatus);
    }

    /**
    * Returns the user employee ID of this ip user.
    *
    * @return the user employee ID of this ip user
    */
    @Override
    public java.lang.String getUserEmployeeId() {
        return _ipUser.getUserEmployeeId();
    }

    /**
    * Sets the user employee ID of this ip user.
    *
    * @param userEmployeeId the user employee ID of this ip user
    */
    @Override
    public void setUserEmployeeId(java.lang.String userEmployeeId) {
        _ipUser.setUserEmployeeId(userEmployeeId);
    }

    /**
    * Returns the pri grp ID of this ip user.
    *
    * @return the pri grp ID of this ip user
    */
    @Override
    public long getPriGrpId() {
        return _ipUser.getPriGrpId();
    }

    /**
    * Sets the pri grp ID of this ip user.
    *
    * @param priGrpId the pri grp ID of this ip user
    */
    @Override
    public void setPriGrpId(long priGrpId) {
        _ipUser.setPriGrpId(priGrpId);
    }

    @Override
    public boolean isNew() {
        return _ipUser.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ipUser.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ipUser.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ipUser.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ipUser.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ipUser.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ipUser.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ipUser.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ipUser.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ipUser.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ipUser.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IpUserWrapper((IpUser) _ipUser.clone());
    }

    @Override
    public int compareTo(IpUser ipUser) {
        return _ipUser.compareTo(ipUser);
    }

    @Override
    public int hashCode() {
        return _ipUser.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<IpUser> toCacheModel() {
        return _ipUser.toCacheModel();
    }

    @Override
    public IpUser toEscapedModel() {
        return new IpUserWrapper(_ipUser.toEscapedModel());
    }

    @Override
    public IpUser toUnescapedModel() {
        return new IpUserWrapper(_ipUser.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ipUser.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ipUser.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ipUser.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IpUserWrapper)) {
            return false;
        }

        IpUserWrapper ipUserWrapper = (IpUserWrapper) obj;

        if (Validator.equals(_ipUser, ipUserWrapper._ipUser)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IpUser getWrappedIpUser() {
        return _ipUser;
    }

    @Override
    public IpUser getWrappedModel() {
        return _ipUser;
    }

    @Override
    public void resetOriginalValues() {
        _ipUser.resetOriginalValues();
    }
}
