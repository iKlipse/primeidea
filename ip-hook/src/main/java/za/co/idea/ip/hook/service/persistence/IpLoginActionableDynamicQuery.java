package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import za.co.idea.ip.hook.model.IpLogin;
import za.co.idea.ip.hook.service.IpLoginLocalServiceUtil;

/**
 * @author VMPattamatta
 * @generated
 */
public abstract class IpLoginActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IpLoginActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IpLoginLocalServiceUtil.getService());
        setClass(IpLogin.class);

        setClassLoader(za.co.idea.ip.hook.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("loginId");
    }
}
