package com.zhadan.ownIoC;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;

import static org.apache.log4j.Logger.getLogger;


/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 23.08.13
 * Time: 14:08
 */
public class SpringInitServlet extends HttpServlet {
    private static final Logger baseLogger = getLogger(SpringInitServlet.class.getSimpleName());
    private static final ApplicationContext context;

    static {
        baseLogger.info("First incoming in SpringInitServlet...");
        context = new ClassPathXmlApplicationContext(
                "ApplicationContext.xml");
    }

    private static final long serialVersionUID = -1559461563277942928L;

    public static ApplicationContext getContext() {
        return context;
    }
}
