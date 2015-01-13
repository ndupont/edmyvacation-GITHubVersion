package com.ellisdon.portal.mv.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.*;

public class MyVacationLogger extends Logger  {
	static{
		PropertyConfigurator.configure("log4j.properties");
	}
	
	protected MyVacationLogger(String name) {
		super(name);
	}
}
