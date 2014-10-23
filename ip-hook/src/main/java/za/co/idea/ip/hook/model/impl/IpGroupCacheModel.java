package za.co.idea.ip.hook.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import za.co.idea.ip.hook.model.IpGroup;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing IpGroup in entity cache.
 *
 * @author VMPattamatta
 * @see IpGroup
 * @generated
 */
public class IpGroupCacheModel implements CacheModel<IpGroup>, Externalizable {
    public long groupId;
    public long groupAdminId;
    public long groupParentId;
    public String groupName;
    public String groupStatus;
    public String groupEmail;
    public String groupIsCore;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{groupId=");
        sb.append(groupId);
        sb.append(", groupAdminId=");
        sb.append(groupAdminId);
        sb.append(", groupParentId=");
        sb.append(groupParentId);
        sb.append(", groupName=");
        sb.append(groupName);
        sb.append(", groupStatus=");
        sb.append(groupStatus);
        sb.append(", groupEmail=");
        sb.append(groupEmail);
        sb.append(", groupIsCore=");
        sb.append(groupIsCore);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IpGroup toEntityModel() {
        IpGroupImpl ipGroupImpl = new IpGroupImpl();

        ipGroupImpl.setGroupId(groupId);
        ipGroupImpl.setGroupAdminId(groupAdminId);
        ipGroupImpl.setGroupParentId(groupParentId);

        if (groupName == null) {
            ipGroupImpl.setGroupName(StringPool.BLANK);
        } else {
            ipGroupImpl.setGroupName(groupName);
        }

        if (groupStatus == null) {
            ipGroupImpl.setGroupStatus(StringPool.BLANK);
        } else {
            ipGroupImpl.setGroupStatus(groupStatus);
        }

        if (groupEmail == null) {
            ipGroupImpl.setGroupEmail(StringPool.BLANK);
        } else {
            ipGroupImpl.setGroupEmail(groupEmail);
        }

        if (groupIsCore == null) {
            ipGroupImpl.setGroupIsCore(StringPool.BLANK);
        } else {
            ipGroupImpl.setGroupIsCore(groupIsCore);
        }

        ipGroupImpl.resetOriginalValues();

        return ipGroupImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        groupId = objectInput.readLong();
        groupAdminId = objectInput.readLong();
        groupParentId = objectInput.readLong();
        groupName = objectInput.readUTF();
        groupStatus = objectInput.readUTF();
        groupEmail = objectInput.readUTF();
        groupIsCore = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(groupId);
        objectOutput.writeLong(groupAdminId);
        objectOutput.writeLong(groupParentId);

        if (groupName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(groupName);
        }

        if (groupStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(groupStatus);
        }

        if (groupEmail == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(groupEmail);
        }

        if (groupIsCore == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(groupIsCore);
        }
    }
}
