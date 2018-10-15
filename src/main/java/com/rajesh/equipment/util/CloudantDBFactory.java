package com.rajesh.equipment.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

public class CloudantDBFactory
{
	final static Logger logger = Logger.getLogger(CloudantDBFactory.class);	
	final static Properties prop = getLocalProperties("util.properties");
	
	static CloudantClient client = null;
	
	static
	{
		logger.info("Initializing Cloudant Client...");
		
		client = ClientBuilder.account(prop.getProperty("account"))
                .username(prop.getProperty("username"))
                .password(prop.getProperty("password"))
                .build();
	}

	public static Database getDatabase() 
	{
		return client.database(prop.getProperty("db"), false);
	}
	
	
	public static Properties getLocalProperties(String fileName){
        Properties properties = new Properties();
        InputStream inputStream = CloudantDBFactory.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
	
}
