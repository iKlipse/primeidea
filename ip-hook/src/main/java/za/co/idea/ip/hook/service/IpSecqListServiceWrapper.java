package za.co.idea.ip.hook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpSecqListService}.
 *
 * @author VMPattamatta
 * @see IpSecqListService
 * @generated
 */
public class IpSecqListServiceWrapper implements IpSecqListService,
    ServiceWrapper<IpSecqListService> {
    private IpSecqListService _ipSecqListService;

    public IpSecqListServiceWrapper(IpSecqListService ipSecqListService) {
        _ipSecqListService = ipSecqListService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ipSecqListService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ipSecqListService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ipSecqListService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IpSecqListService getWrappedIpSecqListService() {
        return _ipSecqListService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIpSecqListService(IpSecqListService ipSecqListService) {
        _ipSecqListService = ipSecqListService;
    }

    @Override
    public IpSecqListService getWrappedService() {
        return _ipSecqListService;
    }

    @Override
    public void setWrappedService(IpSecqListService ipSecqListService) {
        _ipSecqListService = ipSecqListService;
    }
}
