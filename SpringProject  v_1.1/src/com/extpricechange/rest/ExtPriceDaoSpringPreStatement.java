package com.extpricechange.rest;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class ExtPriceDaoSpringPreStatement {
	public static void process() throws Exception
	{try{
		
		Resource res = new ClassPathResource("spconfig.xml");
		BeanFactory factory = new XmlBeanFactory(res);
		SpringExtPriceInsert in =(SpringExtPriceInsert)factory.getBean("id3");
		in.saveDataPreparedStatement();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
}
