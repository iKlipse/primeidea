package za.co.idea.ip.hook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpGroupService}.
 *
 * @author VMPattamatta
 * @see IpGroupService
 * @generated
 */
public class IpGroupServiceWrapper implements IpGroupService,
    ServiceWrapper<IpGroupService> {
    private IpGroupService _ipGroupService;

    public IpGroupServiceWrapper(IpGroupService ipGroupService) {
        _ipGroupService = ipGroupService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ipGroupService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ipGroupService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ipGroupService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IpGroupService getWrappedIpGroupService() {
        return _ipGroupService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIpGroupService(IpGroupService ipGroupService) {
        _ipGroupService = ipGroupService;
    }

    @Override
    public IpGroupService getWrappedService() {
        return _ipGroupService;
    }

    @Override
    public void setWrappedService(IpGroupService ipGroupService) {
        _ipGroupService = ipGroupService;
    }
}
