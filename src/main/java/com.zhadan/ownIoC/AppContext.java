package com.zhadan.ownIoC;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/3/13
 * Time: 6:22 PM
 */
public interface AppContext {
    public void init(String xmlFile);

    public Object getBean(String name);

    public <T> T getBean(String name, Class<T> clazz);
}
