package web.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

public class PropertiesReader extends HttpServlet {
	private Properties properties;

    public void doSomeOperation() throws IOException {
    	
    	String prefix =  getServletContext().getRealPath("/");
	    String file = getInitParameter("application.properties");
        // Get the inputStream
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("conf/application.properties");

        Properties properties = new Properties();

        System.out.println("InputStream is: " + inputStream);

        // load the inputStream using the Properties
        properties.load(inputStream);
        // get the value of the property
        String propValue = properties.getProperty("abc");

        System.out.println("Property value is: " + propValue);
    }
    public String getProperty(String key){
    	return properties.getProperty(key);
    }
	    

	}