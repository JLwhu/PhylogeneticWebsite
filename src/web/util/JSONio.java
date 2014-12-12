package web.util;

import java.io.IOException;
import java.io.PrintWriter;

public class JSONio {
	public void writeJsonFile(String outFilename,String jsonString) throws IOException{	
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(outFilename);
			pw.printf(jsonString);
		} finally {
			if (pw != null)
				pw.close();
		}
				
	}

}
