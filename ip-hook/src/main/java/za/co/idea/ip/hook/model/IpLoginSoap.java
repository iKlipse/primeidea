package za.co.idea.ip.hook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link za.co.idea.ip.hook.service.http.IpLoginServiceSoap}.
 *
 * @author VMPattamatta
 * @see za.co.idea.ip.hook.service.http.IpLoginServiceSoap
 * @generated
 */
public class IpLoginSoap implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5656774372647106322L;
	private long _loginId;
    private long _loginUserId;
    private String _loginName;
    private String _loginPwd;
    private Date _loginLastDt;
    private int _loginSecQ;
    private String _loginSecA;

    public IpLoginSoap() {
    }

    public static IpLoginSoap toSoapModel(IpLogin model) {
        IpLoginSoap soapModel = new IpLoginSoap();

        soapModel.setLoginId(model.getLoginId());
        soapModel.setLoginUserId(model.getLoginUserId());
        soapModel.setLoginName(model.getLoginName());
        soapModel.setLoginPwd(model.getLoginPwd());
        soapModel.setLoginLastDt(model.getLoginLastDt());
        soapModel.setLoginSecQ(model.getLoginSecQ());
        soapModel.setLoginSecA(model.getLoginSecA());

        return soapModel;
    }

    public static IpLoginSoap[] toSoapModels(IpLogin[] models) {
        IpLoginSoap[] soapModels = new IpLoginSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IpLoginSoap[][] toSoapModels(IpLogin[][] models) {
        IpLoginSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IpLoginSoap[models.length][models[0].length];
        } else {
            soapModels = new IpLoginSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IpLoginSoap[] toSoapModels(List<IpLogin> models) {
        List<IpLoginSoap> soapModels = new ArrayList<IpLoginSoap>(models.size());

        for (IpLogin model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IpLoginSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _loginId;
    }

    public void setPrimaryKey(long pk) {
        setLoginId(pk);
    }

    public long getLoginId() {
        return _loginId;
    }

    public void setLoginId(long loginId) {
        _loginId = loginId;
    }

    public long getLoginUserId() {
        return _loginUserId;
    }

    public void setLoginUserId(long loginUserId) {
        _loginUserId = loginUserId;
    }

    public String getLoginName() {
        return _loginName;
    }

    public void setLoginName(String loginName) {
        _loginName = loginName;
    }

    public String getLoginPwd() {
        return _loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        _loginPwd = loginPwd;
    }

    public Date getLoginLastDt() {
        return _loginLastDt;
    }

    public void setLoginLastDt(Date loginLastDt) {
        _loginLastDt = loginLastDt;
    }

    public int getLoginSecQ() {
        return _loginSecQ;
    }

    public void setLoginSecQ(int loginSecQ) {
        _loginSecQ = loginSecQ;
    }

    public String getLoginSecA() {
        return _loginSecA;
    }

    public void setLoginSecA(String loginSecA) {
        _loginSecA = loginSecA;
    }
}
