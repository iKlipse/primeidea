package za.co.idea.ip.hook.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import za.co.idea.ip.hook.model.IpGroupClp;
import za.co.idea.ip.hook.model.IpLoginClp;
import za.co.idea.ip.hook.model.IpSecqListClp;
import za.co.idea.ip.hook.model.IpUserClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unchecked")
public class ClpSerializer {
    private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
    private static String _servletContextName;
    private static boolean _useReflectionToTranslateThrowable = true;

    public static String getServletContextName() {
        if (Validator.isNotNull(_servletContextName)) {
            return _servletContextName;
        }

        synchronized (ClpSerializer.class) {
            if (Validator.isNotNull(_servletContextName)) {
                return _servletContextName;
            }

            try {
                ClassLoader classLoader = ClpSerializer.class.getClassLoader();

                Class<?> portletPropsClass = classLoader.loadClass(
                        "com.liferay.util.portlet.PortletProps");

                Method getMethod = portletPropsClass.getMethod("get",
                        new Class<?>[] { String.class });

                String portletPropsServletContextName = (String) getMethod.invoke(null,
                        "ip-hook-deployment-context");

                if (Validator.isNotNull(portletPropsServletContextName)) {
                    _servletContextName = portletPropsServletContextName;
                }
            } catch (Throwable t) {
                if (_log.isInfoEnabled()) {
                    _log.info(
                        "Unable to locate deployment context from portlet properties");
                }
            }

            if (Validator.isNull(_servletContextName)) {
                try {
                    String propsUtilServletContextName = PropsUtil.get(
                            "ip-hook-deployment-context");

                    if (Validator.isNotNull(propsUtilServletContextName)) {
                        _servletContextName = propsUtilServletContextName;
                    }
                } catch (Throwable t) {
                    if (_log.isInfoEnabled()) {
                        _log.info(
                            "Unable to locate deployment context from portal properties");
                    }
                }
            }

            if (Validator.isNull(_servletContextName)) {
                _servletContextName = "ip-hook";
            }

            return _servletContextName;
        }
    }

    public static Object translateInput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(IpGroupClp.class.getName())) {
            return translateInputIpGroup(oldModel);
        }

        if (oldModelClassName.equals(IpLoginClp.class.getName())) {
            return translateInputIpLogin(oldModel);
        }

        if (oldModelClassName.equals(IpSecqListClp.class.getName())) {
            return translateInputIpSecqList(oldModel);
        }

        if (oldModelClassName.equals(IpUserClp.class.getName())) {
            return translateInputIpUser(oldModel);
        }

        return oldModel;
    }

    public static Object translateInput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateInput(curObj));
        }

        return newList;
    }

    public static Object translateInputIpGroup(BaseModel<?> oldModel) {
        IpGroupClp oldClpModel = (IpGroupClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIpGroupRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIpLogin(BaseModel<?> oldModel) {
        IpLoginClp oldClpModel = (IpLoginClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIpLoginRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIpSecqList(BaseModel<?> oldModel) {
        IpSecqListClp oldClpModel = (IpSecqListClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIpSecqListRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

    public static Object translateInputIpUser(BaseModel<?> oldModel) {
        IpUserClp oldClpModel = (IpUserClp) oldModel;

        BaseModel<?> newModel = oldClpModel.getIpUserRemoteModel();

        newModel.setModelAttributes(oldClpModel.getModelAttributes());

        return newModel;
    }

	public static Object translateInput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateInput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateInput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Object translateOutput(BaseModel<?> oldModel) {
        Class<?> oldModelClass = oldModel.getClass();

        String oldModelClassName = oldModelClass.getName();

        if (oldModelClassName.equals(
                    "za.co.idea.ip.hook.model.impl.IpGroupImpl")) {
            return translateOutputIpGroup(oldModel);
        }

        if (oldModelClassName.equals(
                    "za.co.idea.ip.hook.model.impl.IpLoginImpl")) {
            return translateOutputIpLogin(oldModel);
        }

        if (oldModelClassName.equals(
                    "za.co.idea.ip.hook.model.impl.IpSecqListImpl")) {
            return translateOutputIpSecqList(oldModel);
        }

        if (oldModelClassName.equals("za.co.idea.ip.hook.model.impl.IpUserImpl")) {
            return translateOutputIpUser(oldModel);
        }

        return oldModel;
    }

    public static Object translateOutput(List<Object> oldList) {
        List<Object> newList = new ArrayList<Object>(oldList.size());

        for (int i = 0; i < oldList.size(); i++) {
            Object curObj = oldList.get(i);

            newList.add(translateOutput(curObj));
        }

        return newList;
    }

    public static Object translateOutput(Object obj) {
        if (obj instanceof BaseModel<?>) {
            return translateOutput((BaseModel<?>) obj);
        } else if (obj instanceof List<?>) {
            return translateOutput((List<Object>) obj);
        } else {
            return obj;
        }
    }

    public static Throwable translateThrowable(Throwable throwable) {
        if (_useReflectionToTranslateThrowable) {
            try {
                UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

                objectOutputStream.writeObject(throwable);

                objectOutputStream.flush();
                objectOutputStream.close();

                UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
                        0, unsyncByteArrayOutputStream.size());

                Thread currentThread = Thread.currentThread();

                ClassLoader contextClassLoader = currentThread.getContextClassLoader();

                ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
                        contextClassLoader);

                throwable = (Throwable) objectInputStream.readObject();

                objectInputStream.close();

                return throwable;
            } catch (SecurityException se) {
                if (_log.isInfoEnabled()) {
                    _log.info("Do not use reflection to translate throwable");
                }

                _useReflectionToTranslateThrowable = false;
            } catch (Throwable throwable2) {
                _log.error(throwable2, throwable2);

                return throwable2;
            }
        }

        Class<?> clazz = throwable.getClass();

        String className = clazz.getName();

        if (className.equals(PortalException.class.getName())) {
            return new PortalException();
        }

        if (className.equals(SystemException.class.getName())) {
            return new SystemException();
        }

        if (className.equals("za.co.idea.ip.hook.NoSuchIpGroupException")) {
            return new za.co.idea.ip.hook.NoSuchIpGroupException();
        }

        if (className.equals("za.co.idea.ip.hook.NoSuchIpLoginException")) {
            return new za.co.idea.ip.hook.NoSuchIpLoginException();
        }

        if (className.equals("za.co.idea.ip.hook.NoSuchIpSecqListException")) {
            return new za.co.idea.ip.hook.NoSuchIpSecqListException();
        }

        if (className.equals("za.co.idea.ip.hook.NoSuchIpUserException")) {
            return new za.co.idea.ip.hook.NoSuchIpUserException();
        }

        return throwable;
    }

    public static Object translateOutputIpGroup(BaseModel<?> oldModel) {
        IpGroupClp newModel = new IpGroupClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIpGroupRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIpLogin(BaseModel<?> oldModel) {
        IpLoginClp newModel = new IpLoginClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIpLoginRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIpSecqList(BaseModel<?> oldModel) {
        IpSecqListClp newModel = new IpSecqListClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIpSecqListRemoteModel(oldModel);

        return newModel;
    }

    public static Object translateOutputIpUser(BaseModel<?> oldModel) {
        IpUserClp newModel = new IpUserClp();

        newModel.setModelAttributes(oldModel.getModelAttributes());

        newModel.setIpUserRemoteModel(oldModel);

        return newModel;
    }
}
