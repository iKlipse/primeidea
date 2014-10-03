package za.co.idea.ip.hook.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import za.co.idea.ip.hook.service.ClpSerializer;
import za.co.idea.ip.hook.service.IpLoginLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IpLoginClp extends BaseModelImpl<IpLogin> implements IpLogin {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6344902666913798195L;
	private long _loginId;
    private long _loginUserId;
    private String _loginUserUuid;
    private String _loginName;
    private String _loginPwd;
    private Date _loginLastDt;
    private int _loginSecQ;
    private String _loginSecA;
    private BaseModel<?> _ipLoginRemoteModel;

    public IpLoginClp() {
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
    public long getPrimaryKey() {
        return _loginId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setLoginId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _loginId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getLoginId() {
        return _loginId;
    }

    @Override
    public void setLoginId(long loginId) {
        _loginId = loginId;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginId", long.class);

                method.invoke(_ipLoginRemoteModel, loginId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getLoginUserId() {
        return _loginUserId;
    }

    @Override
    public void setLoginUserId(long loginUserId) {
        _loginUserId = loginUserId;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginUserId", long.class);

                method.invoke(_ipLoginRemoteModel, loginUserId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLoginUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getLoginUserId(), "uuid", _loginUserUuid);
    }

    @Override
    public void setLoginUserUuid(String loginUserUuid) {
        _loginUserUuid = loginUserUuid;
    }

    @Override
    public String getLoginName() {
        return _loginName;
    }

    @Override
    public void setLoginName(String loginName) {
        _loginName = loginName;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginName", String.class);

                method.invoke(_ipLoginRemoteModel, loginName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLoginPwd() {
        return _loginPwd;
    }

    @Override
    public void setLoginPwd(String loginPwd) {
        _loginPwd = loginPwd;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginPwd", String.class);

                method.invoke(_ipLoginRemoteModel, loginPwd);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLoginLastDt() {
        return _loginLastDt;
    }

    @Override
    public void setLoginLastDt(Date loginLastDt) {
        _loginLastDt = loginLastDt;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginLastDt", Date.class);

                method.invoke(_ipLoginRemoteModel, loginLastDt);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getLoginSecQ() {
        return _loginSecQ;
    }

    @Override
    public void setLoginSecQ(int loginSecQ) {
        _loginSecQ = loginSecQ;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginSecQ", int.class);

                method.invoke(_ipLoginRemoteModel, loginSecQ);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLoginSecA() {
        return _loginSecA;
    }

    @Override
    public void setLoginSecA(String loginSecA) {
        _loginSecA = loginSecA;

        if (_ipLoginRemoteModel != null) {
            try {
                Class<?> clazz = _ipLoginRemoteModel.getClass();

                Method method = clazz.getMethod("setLoginSecA", String.class);

                method.invoke(_ipLoginRemoteModel, loginSecA);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIpLoginRemoteModel() {
        return _ipLoginRemoteModel;
    }

    public void setIpLoginRemoteModel(BaseModel<?> ipLoginRemoteModel) {
        _ipLoginRemoteModel = ipLoginRemoteModel;
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

        Class<?> remoteModelClass = _ipLoginRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ipLoginRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IpLoginLocalServiceUtil.addIpLogin(this);
        } else {
            IpLoginLocalServiceUtil.updateIpLogin(this);
        }
    }

    @Override
    public IpLogin toEscapedModel() {
        return (IpLogin) ProxyUtil.newProxyInstance(IpLogin.class.getClassLoader(),
            new Class[] { IpLogin.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IpLoginClp clone = new IpLoginClp();

        clone.setLoginId(getLoginId());
        clone.setLoginUserId(getLoginUserId());
        clone.setLoginName(getLoginName());
        clone.setLoginPwd(getLoginPwd());
        clone.setLoginLastDt(getLoginLastDt());
        clone.setLoginSecQ(getLoginSecQ());
        clone.setLoginSecA(getLoginSecA());

        return clone;
    }

    @Override
    public int compareTo(IpLogin ipLogin) {
        int value = 0;

        if (getLoginId() < ipLogin.getLoginId()) {
            value = -1;
        } else if (getLoginId() > ipLogin.getLoginId()) {
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

        if (!(obj instanceof IpLoginClp)) {
            return false;
        }

        IpLoginClp ipLogin = (IpLoginClp) obj;

        long primaryKey = ipLogin.getPrimaryKey();

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

        sb.append("{loginId=");
        sb.append(getLoginId());
        sb.append(", loginUserId=");
        sb.append(getLoginUserId());
        sb.append(", loginName=");
        sb.append(getLoginName());
        sb.append(", loginPwd=");
        sb.append(getLoginPwd());
        sb.append(", loginLastDt=");
        sb.append(getLoginLastDt());
        sb.append(", loginSecQ=");
        sb.append(getLoginSecQ());
        sb.append(", loginSecA=");
        sb.append(getLoginSecA());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("za.co.idea.ip.hook.model.IpLogin");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>loginId</column-name><column-value><![CDATA[");
        sb.append(getLoginId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loginUserId</column-name><column-value><![CDATA[");
        sb.append(getLoginUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loginName</column-name><column-value><![CDATA[");
        sb.append(getLoginName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loginPwd</column-name><column-value><![CDATA[");
        sb.append(getLoginPwd());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loginLastDt</column-name><column-value><![CDATA[");
        sb.append(getLoginLastDt());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loginSecQ</column-name><column-value><![CDATA[");
        sb.append(getLoginSecQ());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loginSecA</column-name><column-value><![CDATA[");
        sb.append(getLoginSecA());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
