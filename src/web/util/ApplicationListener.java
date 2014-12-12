/*
 * $Id: ApplicationListener.java 582626 2007-10-07 13:26:12Z mrdon $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts2.interceptor.ApplicationAware;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public final class ApplicationListener implements ServletContextListener,ApplicationAware {

    // ------------------------------------------------------ Manifest Constants
	//Map appProMap=new HashMap();
	
	public static final String UPLOADPATH = "uploadPath";
	public static final String MAINTREEFILE = "treeFile";
	public static final String OUTTREEFILE = "outTreeFile";
		 
    // ------------------------------------------------------ Instance Variables

	//add by li
	public static ConcurrentHashMap<String, String> IPMap;
	public static final long PERIOD_DAY = 24*60*60*1000; 
	private Timer timer;


    /**
     * <p>The <code>ServletContext</code> for this web application.</p>
     */
    private ServletContext context = null;
    private Properties properties=new Properties();


    /**
     * <p>Logging output for this plug in instance.</p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    // ------------------------------------------------------------- Properties


    private String pathname = "/WEB-INF/database.xml";

    /**
     * <p>Return the application resource path to the database.</p>
     *
     * @return application resource path path to the database
     */
    public String getPathname() {
        return (this.pathname);
    }

    /**
     * <p>Set the application resource path to the database.</p>
     *
     * @param pathname to the database
     */
    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    // ------------------------------------------ ServletContextListener Methods

    public void contextDestroyed(ServletContextEvent event) {

        log.info("Finalizing memory database plug in");
        context = null;
        timer.cancel();

    }


    public void contextInitialized(ServletContextEvent event) {

        log.info("Initializing memory database plug in from '" +
                pathname + "'");
        
        // Remember our associated ServletContext
        this.context = event.getServletContext();
        
    	String prefix =  context.getRealPath("/");
        // Get the inputStream
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("conf/application.properties");

        Properties properties = new Properties();

        // load the inputStream using the Properties
        try {
			properties.load(inputStream);
			
	        // get the value of the property
	        //System.out.print(properties.getProperty("uploadPath"));
	        context.setAttribute("uploadPath", properties.getProperty("uploadPath"));
	        
			context.setAttribute("treFilePhyloAnalysis",
					properties.getProperty("treFilePhyloAnalysis"));
			context.setAttribute("sciCommonNameMappingFile",
					properties.getProperty("sciCommonNameMappingFile"));
						
			context.setAttribute("allTaxaNameFile", properties.getProperty("allTaxaNameFile"));
			context.setAttribute("treeFile", properties.getProperty("treeFile"));
			context.setAttribute("seqIDFile",
					properties.getProperty("seqIDFile"));
			context.setAttribute("phySeqFile",
					properties.getProperty("phySeqFile"));
			context.setAttribute("outTreeFile",
					properties.getProperty("outTreeFile"));
			context.setAttribute("subTreeImageFile",
					properties.getProperty("subTreeImageFile"));
			
			
			context.setAttribute("IPLimitPeriod", properties.getProperty("IPLimitPeriod"));
			context.setAttribute("IPLimitInPeriod", properties.getProperty("IPLimitInPeriod"));
			context.setAttribute("IPLimitPerDay", properties.getProperty("IPLimitPerDay"));
			context.setAttribute("ServerLimitPerDay", properties.getProperty("ServerLimitPerDay"));
			context.setAttribute("CleanupPeriod", properties.getProperty("CleanupPeriod"));
			

	   //     appProMap.put("uploadPath", properties.getProperty("uploadPath"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Error Loading property file");
		}
        
        //add by li
        IPMap = new ConcurrentHashMap<String, String>();
		
		Calendar nextDay = Calendar.getInstance();
		Date date = new Date();
		nextDay.setTime(date);
		
		int hour = nextDay.get(Calendar.HOUR_OF_DAY);
		int min = nextDay.get(Calendar.MINUTE);
		int sec = nextDay.get(Calendar.SECOND);
		
		nextDay.add(Calendar.HOUR_OF_DAY, 24 - hour);
		nextDay.roll(Calendar.MINUTE, 60 - min);
		nextDay.roll(Calendar.SECOND, 60 - sec);
		
		long currentTime = date.getTime();
		long nextDayBeginTime = nextDay.getTimeInMillis();
		long delay = nextDayBeginTime - currentTime;
		
		timer = new Timer("Cleanup temp files each day", true);
		timer.schedule(new CleanupFileTask(), delay, 
			Integer.parseInt((String) context.getAttribute("CleanupPeriod"))*PERIOD_DAY);
		//timer.schedule(new CleanupFileTask(), 0, 3*1000);


    }

    // -------------------------------------------------------- Private Methods


	@Override
	public void setApplication(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}


}
