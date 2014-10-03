package za.co.idea.ip.hook.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import za.co.idea.ip.hook.model.IpLogin;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IpLogin in entity cache.
 *
 * @author VMPattamatta
 * @see IpLogin
 * @generated
 */
public class IpLoginCacheModel implements CacheModel<IpLogin>, Externalizable {
    public long loginId;
    public long loginUserId;
    public String loginName;
    public String loginPwd;
    public long loginLastDt;
    public int loginSecQ;
    public String loginSecA;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{loginId=");
        sb.append(loginId);
        sb.append(", loginUserId=");
        sb.append(loginUserId);
        sb.append(", loginName=");
        sb.append(loginName);
        sb.append(", loginPwd=");
        sb.append(loginPwd);
        sb.append(", loginLastDt=");
        sb.append(loginLastDt);
        sb.append(", loginSecQ=");
        sb.append(loginSecQ);
        sb.append(", loginSecA=");
        sb.append(loginSecA);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IpLogin toEntityModel() {
        IpLoginImpl ipLoginImpl = new IpLoginImpl();

        ipLoginImpl.setLoginId(loginId);
        ipLoginImpl.setLoginUserId(loginUserId);

        if (loginName == null) {
            ipLoginImpl.setLoginName(StringPool.BLANK);
        } else {
            ipLoginImpl.setLoginName(loginName);
        }

        if (loginPwd == null) {
            ipLoginImpl.setLoginPwd(StringPool.BLANK);
        } else {
            ipLoginImpl.setLoginPwd(loginPwd);
        }

        if (loginLastDt == Long.MIN_VALUE) {
            ipLoginImpl.setLoginLastDt(null);
        } else {
            ipLoginImpl.setLoginLastDt(new Date(loginLastDt));
        }

        ipLoginImpl.setLoginSecQ(loginSecQ);

        if (loginSecA == null) {
            ipLoginImpl.setLoginSecA(StringPool.BLANK);
        } else {
            ipLoginImpl.setLoginSecA(loginSecA);
        }

        ipLoginImpl.resetOriginalValues();

        return ipLoginImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        loginId = objectInput.readLong();
        loginUserId = objectInput.readLong();
        loginName = objectInput.readUTF();
        loginPwd = objectInput.readUTF();
        loginLastDt = objectInput.readLong();
        loginSecQ = objectInput.readInt();
        loginSecA = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(loginId);
        objectOutput.writeLong(loginUserId);

        if (loginName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(loginName);
        }

        if (loginPwd == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(loginPwd);
        }

        objectOutput.writeLong(loginLastDt);
        objectOutput.writeInt(loginSecQ);

        if (loginSecA == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(loginSecA);
        }
    }
}
