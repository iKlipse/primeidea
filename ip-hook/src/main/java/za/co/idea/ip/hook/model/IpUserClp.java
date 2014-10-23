package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import za.co.idea.ip.hook.service.ClpSerializer;
import za.co.idea.ip.hook.service.IpUserLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class IpUserClp extends BaseModelImpl<IpUser> implements IpUser {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2304072745710660077L;
	private long _userId;
    private String _userUuid;
    private String _userFName;
    private String _userLName;
    private String _userMName;
    private long _userIdNum;
    private String _userScreenName;
    private String _userEmail;
    private String _userContact;
    private String _userSkills;
    private String _userBio;
    private String _userFbHandle;
    private String _userTwHandle;
    private String _userStatus;
    private String _userEmployeeId;
    private long _priGrpId;
    private BaseModel<?> _ipUserRemoteModel;

    public IpUserClp() {
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
    public long getPrimaryKey() {
        return _userId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setUserId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _userId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_ipUserRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public String getUserFName() {
        return _userFName;
    }

    @Override
    public void setUserFName(String userFName) {
        _userFName = userFName;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserFName", String.class);

                method.invoke(_ipUserRemoteModel, userFName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserLName() {
        return _userLName;
    }

    @Override
    public void setUserLName(String userLName) {
        _userLName = userLName;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserLName", String.class);

                method.invoke(_ipUserRemoteModel, userLName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserMName() {
        return _userMName;
    }

    @Override
    public void setUserMName(String userMName) {
        _userMName = userMName;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserMName", String.class);

                method.invoke(_ipUserRemoteModel, userMName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserIdNum() {
        return _userIdNum;
    }

    @Override
    public void setUserIdNum(long userIdNum) {
        _userIdNum = userIdNum;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserIdNum", long.class);

                method.invoke(_ipUserRemoteModel, userIdNum);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserScreenName() {
        return _userScreenName;
    }

    @Override
    public void setUserScreenName(String userScreenName) {
        _userScreenName = userScreenName;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserScreenName",
                        String.class);

                method.invoke(_ipUserRemoteModel, userScreenName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserEmail() {
        return _userEmail;
    }

    @Override
    public void setUserEmail(String userEmail) {
        _userEmail = userEmail;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserEmail", String.class);

                method.invoke(_ipUserRemoteModel, userEmail);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserContact() {
        return _userContact;
    }

    @Override
    public void setUserContact(String userContact) {
        _userContact = userContact;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserContact", String.class);

                method.invoke(_ipUserRemoteModel, userContact);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserSkills() {
        return _userSkills;
    }

    @Override
    public void setUserSkills(String userSkills) {
        _userSkills = userSkills;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserSkills", String.class);

                method.invoke(_ipUserRemoteModel, userSkills);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserBio() {
        return _userBio;
    }

    @Override
    public void setUserBio(String userBio) {
        _userBio = userBio;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserBio", String.class);

                method.invoke(_ipUserRemoteModel, userBio);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserFbHandle() {
        return _userFbHandle;
    }

    @Override
    public void setUserFbHandle(String userFbHandle) {
        _userFbHandle = userFbHandle;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserFbHandle", String.class);

                method.invoke(_ipUserRemoteModel, userFbHandle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserTwHandle() {
        return _userTwHandle;
    }

    @Override
    public void setUserTwHandle(String userTwHandle) {
        _userTwHandle = userTwHandle;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserTwHandle", String.class);

                method.invoke(_ipUserRemoteModel, userTwHandle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserStatus() {
        return _userStatus;
    }

    @Override
    public void setUserStatus(String userStatus) {
        _userStatus = userStatus;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserStatus", String.class);

                method.invoke(_ipUserRemoteModel, userStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserEmployeeId() {
        return _userEmployeeId;
    }

    @Override
    public void setUserEmployeeId(String userEmployeeId) {
        _userEmployeeId = userEmployeeId;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserEmployeeId",
                        String.class);

                method.invoke(_ipUserRemoteModel, userEmployeeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPriGrpId() {
        return _priGrpId;
    }

    @Override
    public void setPriGrpId(long priGrpId) {
        _priGrpId = priGrpId;

        if (_ipUserRemoteModel != null) {
            try {
                Class<?> clazz = _ipUserRemoteModel.getClass();

                Method method = clazz.getMethod("setPriGrpId", long.class);

                method.invoke(_ipUserRemoteModel, priGrpId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIpUserRemoteModel() {
        return _ipUserRemoteModel;
    }

    public void setIpUserRemoteModel(BaseModel<?> ipUserRemoteModel) {
        _ipUserRemoteModel = ipUserRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _ipUserRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_ipUserRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IpUserLocalServiceUtil.addIpUser(this);
        } else {
            IpUserLocalServiceUtil.updateIpUser(this);
        }
    }

    @Override
    public IpUser toEscapedModel() {
        return (IpUser) ProxyUtil.newProxyInstance(IpUser.class.getClassLoader(),
            new Class[] { IpUser.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IpUserClp clone = new IpUserClp();

        clone.setUserId(getUserId());
        clone.setUserFName(getUserFName());
        clone.setUserLName(getUserLName());
        clone.setUserMName(getUserMName());
        clone.setUserIdNum(getUserIdNum());
        clone.setUserScreenName(getUserScreenName());
        clone.setUserEmail(getUserEmail());
        clone.setUserContact(getUserContact());
        clone.setUserSkills(getUserSkills());
        clone.setUserBio(getUserBio());
        clone.setUserFbHandle(getUserFbHandle());
        clone.setUserTwHandle(getUserTwHandle());
        clone.setUserStatus(getUserStatus());
        clone.setUserEmployeeId(getUserEmployeeId());
        clone.setPriGrpId(getPriGrpId());

        return clone;
    }

    @Override
    public int compareTo(IpUser ipUser) {
        int value = 0;

        value = getUserScreenName().compareTo(ipUser.getUserScreenName());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IpUserClp)) {
            return false;
        }

        IpUserClp ipUser = (IpUserClp) obj;

        long primaryKey = ipUser.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", userFName=");
        sb.append(getUserFName());
        sb.append(", userLName=");
        sb.append(getUserLName());
        sb.append(", userMName=");
        sb.append(getUserMName());
        sb.append(", userIdNum=");
        sb.append(getUserIdNum());
        sb.append(", userScreenName=");
        sb.append(getUserScreenName());
        sb.append(", userEmail=");
        sb.append(getUserEmail());
        sb.append(", userContact=");
        sb.append(getUserContact());
        sb.append(", userSkills=");
        sb.append(getUserSkills());
        sb.append(", userBio=");
        sb.append(getUserBio());
        sb.append(", userFbHandle=");
        sb.append(getUserFbHandle());
        sb.append(", userTwHandle=");
        sb.append(getUserTwHandle());
        sb.append(", userStatus=");
        sb.append(getUserStatus());
        sb.append(", userEmployeeId=");
        sb.append(getUserEmployeeId());
        sb.append(", priGrpId=");
        sb.append(getPriGrpId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("za.co.idea.ip.hook.model.IpUser");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userFName</column-name><column-value><![CDATA[");
        sb.append(getUserFName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userLName</column-name><column-value><![CDATA[");
        sb.append(getUserLName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userMName</column-name><column-value><![CDATA[");
        sb.append(getUserMName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userIdNum</column-name><column-value><![CDATA[");
        sb.append(getUserIdNum());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userScreenName</column-name><column-value><![CDATA[");
        sb.append(getUserScreenName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userEmail</column-name><column-value><![CDATA[");
        sb.append(getUserEmail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userContact</column-name><column-value><![CDATA[");
        sb.append(getUserContact());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userSkills</column-name><column-value><![CDATA[");
        sb.append(getUserSkills());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userBio</column-name><column-value><![CDATA[");
        sb.append(getUserBio());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userFbHandle</column-name><column-value><![CDATA[");
        sb.append(getUserFbHandle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userTwHandle</column-name><column-value><![CDATA[");
        sb.append(getUserTwHandle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userStatus</column-name><column-value><![CDATA[");
        sb.append(getUserStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userEmployeeId</column-name><column-value><![CDATA[");
        sb.append(getUserEmployeeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priGrpId</column-name><column-value><![CDATA[");
        sb.append(getPriGrpId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
