package com.zhadan.ownIoC;

import com.zhadan.dao.ActorDaoImpl;
import com.zhadan.dao.MovieDaoImpl;
import com.zhadan.dao.UserDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/3/13
 * Time: 6:23 PM
 */
//TODO do it with xml parser
//xml stored into war F**K
public class AppContextSimple implements AppContext {

    private String xmlFile;

    @Override
    public void init(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        if ("actorDao".equals(beanName)) {
            return (T) new ActorDaoImpl();
        }
        if ("movieDao".equals(beanName)) {
            return (T) new MovieDaoImpl();
        }
        if ("userDao".equals(beanName)) {
            return (T) new UserDaoImpl();
        }
        throw new IllegalArgumentException("No entity for name " + beanName);
    }
}
