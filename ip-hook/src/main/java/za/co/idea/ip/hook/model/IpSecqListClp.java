package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import za.co.idea.ip.hook.service.ClpSerializer;
import za.co.idea.ip.hook.service.IpSecqListLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class IpSecqListClp extends BaseModelImpl<IpSecqList>
    implements IpSecqList {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8715712074364424496L;
	private long _islId;
    private String _islDesc;
    private BaseModel<?> _ipSecqListRemoteModel;

    public IpSecqListClp() {
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
    public long getPrimaryKey() {
        return _islId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setIslId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _islId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getIslId() {
        return _islId;
    }

    @Override
    public void setIslId(long islId) {
        _islId = islId;

        if (_ipSecqListRemoteModel != null) {
            try {
                Class<?> clazz = _ipSecqListRemoteModel.getClass();

                Method method = clazz.getMethod("setIslId", long.class);

                method.invoke(_ipSecqListRemoteModel, islId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIslDesc() {
        return _islDesc;
    }

    @Override
    public void setIslDesc(String islDesc) {
        _islDesc = islDesc;

        if (_ipSecqListRemoteModel != null) {
            try {
                Class<?> clazz = _ipSecqListRemoteModel.getClass();

                Method method = clazz.getMethod("setIslDesc", String.class);

                method.invoke(_ipSecqListRemoteModel, islDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIpSecqListRemoteModel() {
        return _ipSecqListRemoteModel;
    }

    public void setIpSecqListRemoteModel(BaseModel<?> ipSecqListRemoteModel) {
        _ipSecqListRemoteModel = ipSecqListRemoteModel;
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

        Class<?> remoteModelClass = _ipSecqListRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ipSecqListRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IpSecqListLocalServiceUtil.addIpSecqList(this);
        } else {
            IpSecqListLocalServiceUtil.updateIpSecqList(this);
        }
    }

    @Override
    public IpSecqList toEscapedModel() {
        return (IpSecqList) ProxyUtil.newProxyInstance(IpSecqList.class.getClassLoader(),
            new Class[] { IpSecqList.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IpSecqListClp clone = new IpSecqListClp();

        clone.setIslId(getIslId());
        clone.setIslDesc(getIslDesc());

        return clone;
    }

    @Override
    public int compareTo(IpSecqList ipSecqList) {
        int value = 0;

        if (getIslId() < ipSecqList.getIslId()) {
            value = -1;
        } else if (getIslId() > ipSecqList.getIslId()) {
            value = 1;
        } else {
            value = 0;
        }

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

        if (!(obj instanceof IpSecqListClp)) {
            return false;
        }

        IpSecqListClp ipSecqList = (IpSecqListClp) obj;

        long primaryKey = ipSecqList.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{islId=");
        sb.append(getIslId());
        sb.append(", islDesc=");
        sb.append(getIslDesc());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("za.co.idea.ip.hook.model.IpSecqList");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>islId</column-name><column-value><![CDATA[");
        sb.append(getIslId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>islDesc</column-name><column-value><![CDATA[");
        sb.append(getIslDesc());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
