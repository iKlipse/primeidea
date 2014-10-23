package za.co.idea.ip.hook.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import za.co.idea.ip.hook.model.IpSecqList;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing IpSecqList in entity cache.
 *
 * @author VMPattamatta
 * @see IpSecqList
 * @generated
 */
public class IpSecqListCacheModel implements CacheModel<IpSecqList>,
    Externalizable {
    public long islId;
    public String islDesc;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{islId=");
        sb.append(islId);
        sb.append(", islDesc=");
        sb.append(islDesc);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IpSecqList toEntityModel() {
        IpSecqListImpl ipSecqListImpl = new IpSecqListImpl();

        ipSecqListImpl.setIslId(islId);

        if (islDesc == null) {
            ipSecqListImpl.setIslDesc(StringPool.BLANK);
        } else {
            ipSecqListImpl.setIslDesc(islDesc);
        }

        ipSecqListImpl.resetOriginalValues();

        return ipSecqListImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        islId = objectInput.readLong();
        islDesc = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(islId);

        if (islDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(islDesc);
        }
    }
}
