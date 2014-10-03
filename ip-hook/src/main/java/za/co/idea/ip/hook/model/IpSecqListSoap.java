package za.co.idea.ip.hook.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link za.co.idea.ip.hook.service.http.IpSecqListServiceSoap}.
 *
 * @author VMPattamatta
 * @see za.co.idea.ip.hook.service.http.IpSecqListServiceSoap
 * @generated
 */
public class IpSecqListSoap implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7507755531629014497L;
	private long _islId;
    private String _islDesc;

    public IpSecqListSoap() {
    }

    public static IpSecqListSoap toSoapModel(IpSecqList model) {
        IpSecqListSoap soapModel = new IpSecqListSoap();

        soapModel.setIslId(model.getIslId());
        soapModel.setIslDesc(model.getIslDesc());

        return soapModel;
    }

    public static IpSecqListSoap[] toSoapModels(IpSecqList[] models) {
        IpSecqListSoap[] soapModels = new IpSecqListSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IpSecqListSoap[][] toSoapModels(IpSecqList[][] models) {
        IpSecqListSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IpSecqListSoap[models.length][models[0].length];
        } else {
            soapModels = new IpSecqListSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IpSecqListSoap[] toSoapModels(List<IpSecqList> models) {
        List<IpSecqListSoap> soapModels = new ArrayList<IpSecqListSoap>(models.size());

        for (IpSecqList model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IpSecqListSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _islId;
    }

    public void setPrimaryKey(long pk) {
        setIslId(pk);
    }

    public long getIslId() {
        return _islId;
    }

    public void setIslId(long islId) {
        _islId = islId;
    }

    public String getIslDesc() {
        return _islDesc;
    }

    public void setIslDesc(String islDesc) {
        _islDesc = islDesc;
    }
}
