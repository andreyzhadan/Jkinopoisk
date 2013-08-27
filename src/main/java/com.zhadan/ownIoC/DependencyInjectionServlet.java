package com.zhadan.ownIoC;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.apache.log4j.Logger.getLogger;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/3/13
 * Time: 6:15 PM
 */
public class DependencyInjectionServlet extends HttpServlet {
    private static final long serialVersionUID = -5974623604427510550L;
    private static final String APP_CTX_PATH = "appCtxPath";
    private static final String APP_CTX_CLASS = "appCtxClass";
    //static(one instance for all extended) or non static(one instance for every servlet)
    private static final Logger baseLogger = getLogger(DependencyInjectionServlet.class.getSimpleName());

    //init calls just first time for every servlet
    @Override
    public void init() throws ServletException {
        //get init context params
        String appCtxPath = getInitContextParam(APP_CTX_PATH); //zhadanApplicationContext
        String appCtxClass = getInitContextParam(APP_CTX_CLASS);  //AppContextSimple

        try {
            AppContext appCtx = initApplicationContext(appCtxPath, appCtxClass);
            injectFields(appCtx);
        } catch (Exception e) {
            baseLogger.error("Exception in DependencyInjectionServlet " + e);
        }
    }

    private AppContext initApplicationContext(String appCtxPath, String appCtxClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        AppContext appCtx = (AppContext) Class.forName(appCtxClass).newInstance();
        baseLogger.debug("OK: Create instance of " + appCtxClass + " appCtx= " + appCtx);

        appCtx.init(appCtxPath);
        baseLogger.debug("OK: Init instance of " + appCtxClass);

        return appCtx;
    }

    private void injectFields(AppContext appCtx) throws Exception {
        //!important this is context dependent - so it's instance of current class
        Field[] fields = this.getClass().getDeclaredFields();
        baseLogger.debug("I find fields: " + Arrays.toString(fields));
        for (Field field : fields) {
            field.setAccessible(true);
            ZhadanInject annotation = field.getAnnotation(ZhadanInject.class);
            if (annotation != null) {
                baseLogger.debug("Find field marked by @ZhadanInject: " + field);
                String beanName = annotation.value();
                injectFieldWithName(appCtx, field, beanName);
            }
        }
    }

    private void injectFieldWithName(AppContext appCtx, Field field, String beanName) throws Exception {
        Object bean = appCtx.getBean(beanName);
        if (bean == null) {
            throw new Exception("There is no bean with name " + beanName);
        }
        field.set(this, bean);
    }

    private String getInitContextParam(String param) throws ServletException {
        String value = this.getServletContext().getInitParameter(param);
        baseLogger.debug("load " + param + " -> " + value);

        if (value == null) {
            baseLogger.error("I need this param " + param);
            throw new ServletException(param + " init param null");
        }
        return value;
    }
}
