package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import za.co.idea.ip.hook.model.IpUser;
import za.co.idea.ip.hook.service.IpUserLocalServiceUtil;

/**
 * @author VMPattamatta
 * @generated
 */
public abstract class IpUserActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IpUserActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IpUserLocalServiceUtil.getService());
        setClass(IpUser.class);

        setClassLoader(za.co.idea.ip.hook.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("userId");
    }
}
