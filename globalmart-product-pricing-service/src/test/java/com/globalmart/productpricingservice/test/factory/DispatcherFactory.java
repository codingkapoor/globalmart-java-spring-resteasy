package com.globalmart.productpricingservice.test.factory;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.plugins.spring.SpringResourceFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DispatcherFactory {

	public static Dispatcher createMockDispatcher(String springContextFileName, Class<?> beanClass) {
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();

		SpringBeanProcessor springBeanProcessor = new SpringBeanProcessor(dispatcher);

		ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(springContextFileName);
		springContext.addBeanFactoryPostProcessor(springBeanProcessor);

		SpringResourceFactory springResourceFactory = new SpringResourceFactory(createConventionalBeanName(beanClass), springContext, beanClass);
		dispatcher.getRegistry().addResourceFactory(springResourceFactory);
		
		return dispatcher;
	}
	
	private static String createConventionalBeanName(Class<?> beanClass) {
		String str = beanClass.getSimpleName();
		return Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}

}
