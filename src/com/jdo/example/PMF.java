package com.jdo.example;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PMF {
    
	public static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {
    	
    }

    public static PersistenceManagerFactory get() {
        
    	return pmfInstance;
    }
}