package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import za.co.idea.ip.hook.model.IpSecqList;
import za.co.idea.ip.hook.service.IpSecqListLocalServiceUtil;

/**
 * @author VMPattamatta
 * @generated
 */
public abstract class IpSecqListActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IpSecqListActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IpSecqListLocalServiceUtil.getService());
        setClass(IpSecqList.class);

        setClassLoader(za.co.idea.ip.hook.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("islId");
    }
}
