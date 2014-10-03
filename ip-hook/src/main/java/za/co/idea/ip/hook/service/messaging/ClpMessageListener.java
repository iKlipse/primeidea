package za.co.idea.ip.hook.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import za.co.idea.ip.hook.service.ClpSerializer;
import za.co.idea.ip.hook.service.IpGroupLocalServiceUtil;
import za.co.idea.ip.hook.service.IpGroupServiceUtil;
import za.co.idea.ip.hook.service.IpLoginLocalServiceUtil;
import za.co.idea.ip.hook.service.IpLoginServiceUtil;
import za.co.idea.ip.hook.service.IpSecqListLocalServiceUtil;
import za.co.idea.ip.hook.service.IpSecqListServiceUtil;
import za.co.idea.ip.hook.service.IpUserLocalServiceUtil;
import za.co.idea.ip.hook.service.IpUserServiceUtil;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            IpGroupLocalServiceUtil.clearService();

            IpGroupServiceUtil.clearService();
            IpLoginLocalServiceUtil.clearService();

            IpLoginServiceUtil.clearService();
            IpSecqListLocalServiceUtil.clearService();

            IpSecqListServiceUtil.clearService();
            IpUserLocalServiceUtil.clearService();

            IpUserServiceUtil.clearService();
        }
    }
}
