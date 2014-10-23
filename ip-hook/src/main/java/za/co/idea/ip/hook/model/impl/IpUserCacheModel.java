package za.co.idea.ip.hook.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import za.co.idea.ip.hook.model.IpUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing IpUser in entity cache.
 *
 * @author VMPattamatta
 * @see IpUser
 * @generated
 */
public class IpUserCacheModel implements CacheModel<IpUser>, Externalizable {
    public long userId;
    public String userFName;
    public String userLName;
    public String userMName;
    public long userIdNum;
    public String userScreenName;
    public String userEmail;
    public String userContact;
    public String userSkills;
    public String userBio;
    public String userFbHandle;
    public String userTwHandle;
    public String userStatus;
    public String userEmployeeId;
    public long priGrpId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", userFName=");
        sb.append(userFName);
        sb.append(", userLName=");
        sb.append(userLName);
        sb.append(", userMName=");
        sb.append(userMName);
        sb.append(", userIdNum=");
        sb.append(userIdNum);
        sb.append(", userScreenName=");
        sb.append(userScreenName);
        sb.append(", userEmail=");
        sb.append(userEmail);
        sb.append(", userContact=");
        sb.append(userContact);
        sb.append(", userSkills=");
        sb.append(userSkills);
        sb.append(", userBio=");
        sb.append(userBio);
        sb.append(", userFbHandle=");
        sb.append(userFbHandle);
        sb.append(", userTwHandle=");
        sb.append(userTwHandle);
        sb.append(", userStatus=");
        sb.append(userStatus);
        sb.append(", userEmployeeId=");
        sb.append(userEmployeeId);
        sb.append(", priGrpId=");
        sb.append(priGrpId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IpUser toEntityModel() {
        IpUserImpl ipUserImpl = new IpUserImpl();

        ipUserImpl.setUserId(userId);

        if (userFName == null) {
            ipUserImpl.setUserFName(StringPool.BLANK);
        } else {
            ipUserImpl.setUserFName(userFName);
        }

        if (userLName == null) {
            ipUserImpl.setUserLName(StringPool.BLANK);
        } else {
            ipUserImpl.setUserLName(userLName);
        }

        if (userMName == null) {
            ipUserImpl.setUserMName(StringPool.BLANK);
        } else {
            ipUserImpl.setUserMName(userMName);
        }

        ipUserImpl.setUserIdNum(userIdNum);

        if (userScreenName == null) {
            ipUserImpl.setUserScreenName(StringPool.BLANK);
        } else {
            ipUserImpl.setUserScreenName(userScreenName);
        }

        if (userEmail == null) {
            ipUserImpl.setUserEmail(StringPool.BLANK);
        } else {
            ipUserImpl.setUserEmail(userEmail);
        }

        if (userContact == null) {
            ipUserImpl.setUserContact(StringPool.BLANK);
        } else {
            ipUserImpl.setUserContact(userContact);
        }

        if (userSkills == null) {
            ipUserImpl.setUserSkills(StringPool.BLANK);
        } else {
            ipUserImpl.setUserSkills(userSkills);
        }

        if (userBio == null) {
            ipUserImpl.setUserBio(StringPool.BLANK);
        } else {
            ipUserImpl.setUserBio(userBio);
        }

        if (userFbHandle == null) {
            ipUserImpl.setUserFbHandle(StringPool.BLANK);
        } else {
            ipUserImpl.setUserFbHandle(userFbHandle);
        }

        if (userTwHandle == null) {
            ipUserImpl.setUserTwHandle(StringPool.BLANK);
        } else {
            ipUserImpl.setUserTwHandle(userTwHandle);
        }

        if (userStatus == null) {
            ipUserImpl.setUserStatus(StringPool.BLANK);
        } else {
            ipUserImpl.setUserStatus(userStatus);
        }

        if (userEmployeeId == null) {
            ipUserImpl.setUserEmployeeId(StringPool.BLANK);
        } else {
            ipUserImpl.setUserEmployeeId(userEmployeeId);
        }

        ipUserImpl.setPriGrpId(priGrpId);

        ipUserImpl.resetOriginalValues();

        return ipUserImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        userId = objectInput.readLong();
        userFName = objectInput.readUTF();
        userLName = objectInput.readUTF();
        userMName = objectInput.readUTF();
        userIdNum = objectInput.readLong();
        userScreenName = objectInput.readUTF();
        userEmail = objectInput.readUTF();
        userContact = objectInput.readUTF();
        userSkills = objectInput.readUTF();
        userBio = objectInput.readUTF();
        userFbHandle = objectInput.readUTF();
        userTwHandle = objectInput.readUTF();
        userStatus = objectInput.readUTF();
        userEmployeeId = objectInput.readUTF();
        priGrpId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(userId);

        if (userFName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userFName);
        }

        if (userLName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userLName);
        }

        if (userMName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userMName);
        }

        objectOutput.writeLong(userIdNum);

        if (userScreenName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userScreenName);
        }

        if (userEmail == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userEmail);
        }

        if (userContact == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userContact);
        }

        if (userSkills == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userSkills);
        }

        if (userBio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userBio);
        }

        if (userFbHandle == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userFbHandle);
        }

        if (userTwHandle == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userTwHandle);
        }

        if (userStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userStatus);
        }

        if (userEmployeeId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userEmployeeId);
        }

        objectOutput.writeLong(priGrpId);
    }
}
