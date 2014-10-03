package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import za.co.idea.ip.hook.service.ClpSerializer;
import za.co.idea.ip.hook.service.IpGroupLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class IpGroupClp extends BaseModelImpl<IpGroup> implements IpGroup {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6692217882935061903L;
	private long _groupId;
    private long _groupAdminId;
    private long _groupParentId;
    private String _groupName;
    private String _groupStatus;
    private String _groupEmail;
    private String _groupIsCore;
    private BaseModel<?> _ipGroupRemoteModel;

    public IpGroupClp() {
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
    public long getPrimaryKey() {
        return _groupId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setGroupId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _groupId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getGroupId() {
        return _groupId;
    }

    @Override
    public void setGroupId(long groupId) {
        _groupId = groupId;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_ipGroupRemoteModel, groupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getGroupAdminId() {
        return _groupAdminId;
    }

    @Override
    public void setGroupAdminId(long groupAdminId) {
        _groupAdminId = groupAdminId;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupAdminId", long.class);

                method.invoke(_ipGroupRemoteModel, groupAdminId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getGroupParentId() {
        return _groupParentId;
    }

    @Override
    public void setGroupParentId(long groupParentId) {
        _groupParentId = groupParentId;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupParentId", long.class);

                method.invoke(_ipGroupRemoteModel, groupParentId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGroupName() {
        return _groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        _groupName = groupName;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupName", String.class);

                method.invoke(_ipGroupRemoteModel, groupName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGroupStatus() {
        return _groupStatus;
    }

    @Override
    public void setGroupStatus(String groupStatus) {
        _groupStatus = groupStatus;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupStatus", String.class);

                method.invoke(_ipGroupRemoteModel, groupStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGroupEmail() {
        return _groupEmail;
    }

    @Override
    public void setGroupEmail(String groupEmail) {
        _groupEmail = groupEmail;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupEmail", String.class);

                method.invoke(_ipGroupRemoteModel, groupEmail);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGroupIsCore() {
        return _groupIsCore;
    }

    @Override
    public void setGroupIsCore(String groupIsCore) {
        _groupIsCore = groupIsCore;

        if (_ipGroupRemoteModel != null) {
            try {
                Class<?> clazz = _ipGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupIsCore", String.class);

                method.invoke(_ipGroupRemoteModel, groupIsCore);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIpGroupRemoteModel() {
        return _ipGroupRemoteModel;
    }

    public void setIpGroupRemoteModel(BaseModel<?> ipGroupRemoteModel) {
        _ipGroupRemoteModel = ipGroupRemoteModel;
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

        Class<?> remoteModelClass = _ipGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ipGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IpGroupLocalServiceUtil.addIpGroup(this);
        } else {
            IpGroupLocalServiceUtil.updateIpGroup(this);
        }
    }

    @Override
    public IpGroup toEscapedModel() {
        return (IpGroup) ProxyUtil.newProxyInstance(IpGroup.class.getClassLoader(),
            new Class[] { IpGroup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IpGroupClp clone = new IpGroupClp();

        clone.setGroupId(getGroupId());
        clone.setGroupAdminId(getGroupAdminId());
        clone.setGroupParentId(getGroupParentId());
        clone.setGroupName(getGroupName());
        clone.setGroupStatus(getGroupStatus());
        clone.setGroupEmail(getGroupEmail());
        clone.setGroupIsCore(getGroupIsCore());

        return clone;
    }

    @Override
    public int compareTo(IpGroup ipGroup) {
        int value = 0;

        value = getGroupName().compareTo(ipGroup.getGroupName());

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

        if (!(obj instanceof IpGroupClp)) {
            return false;
        }

        IpGroupClp ipGroup = (IpGroupClp) obj;

        long primaryKey = ipGroup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{groupId=");
        sb.append(getGroupId());
        sb.append(", groupAdminId=");
        sb.append(getGroupAdminId());
        sb.append(", groupParentId=");
        sb.append(getGroupParentId());
        sb.append(", groupName=");
        sb.append(getGroupName());
        sb.append(", groupStatus=");
        sb.append(getGroupStatus());
        sb.append(", groupEmail=");
        sb.append(getGroupEmail());
        sb.append(", groupIsCore=");
        sb.append(getGroupIsCore());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("za.co.idea.ip.hook.model.IpGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupAdminId</column-name><column-value><![CDATA[");
        sb.append(getGroupAdminId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupParentId</column-name><column-value><![CDATA[");
        sb.append(getGroupParentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupName</column-name><column-value><![CDATA[");
        sb.append(getGroupName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupStatus</column-name><column-value><![CDATA[");
        sb.append(getGroupStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupEmail</column-name><column-value><![CDATA[");
        sb.append(getGroupEmail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupIsCore</column-name><column-value><![CDATA[");
        sb.append(getGroupIsCore());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
