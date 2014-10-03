package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import za.co.idea.ip.hook.model.IpGroup;
import za.co.idea.ip.hook.service.IpGroupLocalServiceUtil;

/**
 * @author VMPattamatta
 * @generated
 */
public abstract class IpGroupActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IpGroupActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IpGroupLocalServiceUtil.getService());
        setClass(IpGroup.class);

        setClassLoader(za.co.idea.ip.hook.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("groupId");
    }
}
