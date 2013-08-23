package com.zhadan.ownIoC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: azhadan
 * Date: 8/3/13
 * Time: 10:33 PM
 */
//xml stored into war F**K
//loads whole model in memory
public class AppContextDom implements AppContext {
    private Document document;

    //init for every servlet, how to prevent this?
    @Override
    public void init(String xmlFile) {
        try {
            //init inputStream
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(xmlFile);

            //init DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            this.document = documentBuilder.parse(in);
            this.document.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        try {
            NodeList nList = this.document.getElementsByTagName("bean");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String beanName = element.getAttribute("name");
                    String beanClass = element.getAttribute("class");
                    if (beanName.equals(name)) {
                        return (T) newInstance(beanClass);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Object newInstance(String beanClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> beanClass = Class.forName(beanClassName);
        return beanClass.newInstance();
    }
}
