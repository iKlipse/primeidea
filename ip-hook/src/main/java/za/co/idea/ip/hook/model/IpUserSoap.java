package za.co.idea.ip.hook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link za.co.idea.ip.hook.service.http.IpUserServiceSoap}.
 *
 * @author VMPattamatta
 * @see za.co.idea.ip.hook.service.http.IpUserServiceSoap
 * @generated
 */
public class IpUserSoap implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2331796634364671997L;
	private long _userId;
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

    public IpUserSoap() {
    }

    public static IpUserSoap toSoapModel(IpUser model) {
        IpUserSoap soapModel = new IpUserSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setUserFName(model.getUserFName());
        soapModel.setUserLName(model.getUserLName());
        soapModel.setUserMName(model.getUserMName());
        soapModel.setUserIdNum(model.getUserIdNum());
        soapModel.setUserScreenName(model.getUserScreenName());
        soapModel.setUserEmail(model.getUserEmail());
        soapModel.setUserContact(model.getUserContact());
        soapModel.setUserSkills(model.getUserSkills());
        soapModel.setUserBio(model.getUserBio());
        soapModel.setUserFbHandle(model.getUserFbHandle());
        soapModel.setUserTwHandle(model.getUserTwHandle());
        soapModel.setUserStatus(model.getUserStatus());
        soapModel.setUserEmployeeId(model.getUserEmployeeId());
        soapModel.setPriGrpId(model.getPriGrpId());

        return soapModel;
    }

    public static IpUserSoap[] toSoapModels(IpUser[] models) {
        IpUserSoap[] soapModels = new IpUserSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IpUserSoap[][] toSoapModels(IpUser[][] models) {
        IpUserSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IpUserSoap[models.length][models[0].length];
        } else {
            soapModels = new IpUserSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IpUserSoap[] toSoapModels(List<IpUser> models) {
        List<IpUserSoap> soapModels = new ArrayList<IpUserSoap>(models.size());

        for (IpUser model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IpUserSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _userId;
    }

    public void setPrimaryKey(long pk) {
        setUserId(pk);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserFName() {
        return _userFName;
    }

    public void setUserFName(String userFName) {
        _userFName = userFName;
    }

    public String getUserLName() {
        return _userLName;
    }

    public void setUserLName(String userLName) {
        _userLName = userLName;
    }

    public String getUserMName() {
        return _userMName;
    }

    public void setUserMName(String userMName) {
        _userMName = userMName;
    }

    public long getUserIdNum() {
        return _userIdNum;
    }

    public void setUserIdNum(long userIdNum) {
        _userIdNum = userIdNum;
    }

    public String getUserScreenName() {
        return _userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        _userScreenName = userScreenName;
    }

    public String getUserEmail() {
        return _userEmail;
    }

    public void setUserEmail(String userEmail) {
        _userEmail = userEmail;
    }

    public String getUserContact() {
        return _userContact;
    }

    public void setUserContact(String userContact) {
        _userContact = userContact;
    }

    public String getUserSkills() {
        return _userSkills;
    }

    public void setUserSkills(String userSkills) {
        _userSkills = userSkills;
    }

    public String getUserBio() {
        return _userBio;
    }

    public void setUserBio(String userBio) {
        _userBio = userBio;
    }

    public String getUserFbHandle() {
        return _userFbHandle;
    }

    public void setUserFbHandle(String userFbHandle) {
        _userFbHandle = userFbHandle;
    }

    public String getUserTwHandle() {
        return _userTwHandle;
    }

    public void setUserTwHandle(String userTwHandle) {
        _userTwHandle = userTwHandle;
    }

    public String getUserStatus() {
        return _userStatus;
    }

    public void setUserStatus(String userStatus) {
        _userStatus = userStatus;
    }

    public String getUserEmployeeId() {
        return _userEmployeeId;
    }

    public void setUserEmployeeId(String userEmployeeId) {
        _userEmployeeId = userEmployeeId;
    }

    public long getPriGrpId() {
        return _priGrpId;
    }

    public void setPriGrpId(long priGrpId) {
        _priGrpId = priGrpId;
    }
}
