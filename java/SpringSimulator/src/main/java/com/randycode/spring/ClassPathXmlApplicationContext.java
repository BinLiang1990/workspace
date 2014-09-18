package com.randycode.spring;

import java.lang.reflect.Method;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class ClassPathXmlApplicationContext implements BeanFactory {

	private Map<String, Object> beans = new HashMap<String, Object>();
	
	public ClassPathXmlApplicationContext() throws Exception {
		SAXBuilder sb = new SAXBuilder();
		Document doc = (Document) sb.build(this.getClass().getClassLoader().getResourceAsStream("beans.xml"));
		Element root = doc.getRootElement();
		List list = root.getChildren("bean");
		for(int i = 0; i < list.size(); ++i) {
			Element element = (Element)list.get(i);
			String id = element.getAttributeValue("id");
			String clazz = element.getAttributeValue("class");
			Object object = Class.forName(clazz).newInstance();
			beans.put(id, object);
			for(Element propertyElement : (List<Element>)element.getChildren("property")) {
				String name = propertyElement.getAttributeValue("name");
				String bean = propertyElement.getAttributeValue("bean");
				Object beanObject = beans.get(bean);
				String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				Method method = object.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
				method.invoke(object, beanObject);
			}
		}
	}
	
	@Override
	public Object getBean(String name) {
		return beans.get(name);
	}

}
