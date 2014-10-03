package za.co.idea.ip.hook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link za.co.idea.ip.hook.service.http.IpGroupServiceSoap}.
 *
 * @author VMPattamatta
 * @see za.co.idea.ip.hook.service.http.IpGroupServiceSoap
 * @generated
 */
public class IpGroupSoap implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -338570314169212998L;
	private long _groupId;
    private long _groupAdminId;
    private long _groupParentId;
    private String _groupName;
    private String _groupStatus;
    private String _groupEmail;
    private String _groupIsCore;

    public IpGroupSoap() {
    }

    public static IpGroupSoap toSoapModel(IpGroup model) {
        IpGroupSoap soapModel = new IpGroupSoap();

        soapModel.setGroupId(model.getGroupId());
        soapModel.setGroupAdminId(model.getGroupAdminId());
        soapModel.setGroupParentId(model.getGroupParentId());
        soapModel.setGroupName(model.getGroupName());
        soapModel.setGroupStatus(model.getGroupStatus());
        soapModel.setGroupEmail(model.getGroupEmail());
        soapModel.setGroupIsCore(model.getGroupIsCore());

        return soapModel;
    }

    public static IpGroupSoap[] toSoapModels(IpGroup[] models) {
        IpGroupSoap[] soapModels = new IpGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IpGroupSoap[][] toSoapModels(IpGroup[][] models) {
        IpGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IpGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new IpGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IpGroupSoap[] toSoapModels(List<IpGroup> models) {
        List<IpGroupSoap> soapModels = new ArrayList<IpGroupSoap>(models.size());

        for (IpGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IpGroupSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _groupId;
    }

    public void setPrimaryKey(long pk) {
        setGroupId(pk);
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getGroupAdminId() {
        return _groupAdminId;
    }

    public void setGroupAdminId(long groupAdminId) {
        _groupAdminId = groupAdminId;
    }

    public long getGroupParentId() {
        return _groupParentId;
    }

    public void setGroupParentId(long groupParentId) {
        _groupParentId = groupParentId;
    }

    public String getGroupName() {
        return _groupName;
    }

    public void setGroupName(String groupName) {
        _groupName = groupName;
    }

    public String getGroupStatus() {
        return _groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        _groupStatus = groupStatus;
    }

    public String getGroupEmail() {
        return _groupEmail;
    }

    public void setGroupEmail(String groupEmail) {
        _groupEmail = groupEmail;
    }

    public String getGroupIsCore() {
        return _groupIsCore;
    }

    public void setGroupIsCore(String groupIsCore) {
        _groupIsCore = groupIsCore;
    }
}
