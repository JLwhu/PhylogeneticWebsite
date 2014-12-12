package web.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import web.model.MongoSpeciesRecord;
import web.model.SpeciesSpotRecord;
import web.service.MongoSpeciesSpotRecordService;
import web.service.SpeciesSpotRecordService;
import web.service.SubtreeExtractionService;
import web.service.impl.MongoSpeciesSpotRecordServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class SubtreeOnMapAction extends ActionSupport {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The out file name. */
	private String outFileName;
	private String outputPath;
	/** The sub tree diversity. */
	private String subTreeDiversity;
	private String subTreeImageFileName; 
	private String nameChoice;
//	private String resultJSON;
	private String options;
	private String errMsg;
	
	//search by location
	private String lat;
	private String lng;
	private String diameter;
	
	//search by date
	private String startDate;
	private String endDate;
	private String year;
	private String startMonth;
	private String endMonth;
	
	private SubtreeExtractionService subtreeExtractionService;
	private SpeciesSpotRecordService speciesSpotRecordService;
	private MongoSpeciesSpotRecordService mongoSpeciesSpotRecordService=new MongoSpeciesSpotRecordServiceImpl();
	

    public void setsubtreeExtractionService(SubtreeExtractionService subtreeExtractionService) {
        this.subtreeExtractionService = subtreeExtractionService;
    }
    public SubtreeExtractionService getsubtreeExtractionService() {
        return this.subtreeExtractionService;
    }
	
	public SpeciesSpotRecordService getSpeciesSpotRecordService() {
		return speciesSpotRecordService;
	}
	public void setSpeciesSpotRecordService(SpeciesSpotRecordService speciesSpotRecordService) {
		this.speciesSpotRecordService = speciesSpotRecordService;
	}
    
	/*public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}*/

	public void setOptions(String options) {
		this.options = options;
	}

	public String getOptions() {
		return options;
	}
	
	public String getLat() {
		return lat;
	}
	
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLng() {
		return lng;
	}
	
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	
	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

	public String getOutFileName() {
		return outFileName;
	}
	
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}
	
	public void setNameChoice(String nameChoice) {
		this.nameChoice = nameChoice;
	}

	public String getNameChoice() {
		return nameChoice;
	}
	
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}
	
	public void setSubTreeImageFileName(String subTreeImageFileName) {
		this.subTreeImageFileName = subTreeImageFileName;
	}

	public String getSubTreeDiversity() {
		return subTreeDiversity;
	}

	public void setSubTreeDiversity(String subTreeDiversity) {
		this.subTreeDiversity = subTreeDiversity;
	}

	public String getSubTreeImageFileName() {
		return subTreeImageFileName;
	}
	
    public String subtreeExtraction() throws Exception {
    		List<String> subTreeNamelist = new ArrayList<String>();
    		String treFileName;
    		//String outFilename;
    		String resOutTreFileName;
    		String resSubImage;
    		String path;
    		
    		ServletContext context = ServletActionContext.getServletContext();
    	
    		path = (String)context.getAttribute("uploadPath");
    		path = ServletActionContext.getServletContext().getRealPath(
    				path);
    		path = path+"\\";
    		setOutputPath(path);
    		treFileName = (String)context.getAttribute("treeFile");
    		treFileName = ServletActionContext.getServletContext().getRealPath(
    				treFileName);
    		
    		outFileName = (String)context.getAttribute("outTreeFile");
			outFileName = randomizeFilename(path, outFileName);
			//setOutFileName(outFileName);
			resOutTreFileName = path+ outFileName;
			
			
			subTreeImageFileName = (String)context.getAttribute("subTreeImageFile");
			subTreeImageFileName = randomizeFilename(path,subTreeImageFileName);
			//setSubTreeImageFileName(subTreeImageFileName);
			resSubImage = path + subTreeImageFileName;
			
			List<SpeciesSpotRecord> result = null;
			List<MongoSpeciesRecord> result1 = null;
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
			long a=System.currentTimeMillis();

			
			if (options.equals("ByYear")){
			//	result = speciesSpotRecordService.getSpeciesSpotRecordByYear(year);		
				result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYear(year);		
			}else if (options.equals("ByMonth")){
				//result = speciesSpotRecordService
				//		.getSpeciesSpotRecordByMonthRange(startMonth,endMonth);
				result1 = mongoSpeciesSpotRecordService
						.getSpeciesSpotRecordByMonthRange(startMonth,endMonth);
			}else if (options.equals("ByDate")){
				result = speciesSpotRecordService.getSpeciesSpotRecordByPeriod(df.parse(startDate), df.parse(endDate));
			}else if (options.equals("ByLocation")){
				double d1=Double.parseDouble(lat);
				double d2=Double.parseDouble(lng);
				double d3=Double.parseDouble(diameter);
			//	result = speciesSpotRecordService.getSpeciesSpotRecordByLocation(
			//			d1, d2,
			//			d3);
				result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByLocation(
						d1, d2,
						d3);
			}else if (options.equals("ByYearMonth")){
		//		result = speciesSpotRecordService
		//				.getSpeciesSpotRecordByYearMonth(year,startMonth,endMonth);
				result1 = mongoSpeciesSpotRecordService
						.getSpeciesSpotRecordByYearMonth(year,startMonth,endMonth);
			}else if (options.equals("ByYearLocation")){
			//	result = speciesSpotRecordService.getSpeciesSpotRecordByYearLocation(
			//			Double.parseDouble(lat), Double.parseDouble(lng),
			//			Double.parseDouble(diameter), year);				
				result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYearLocation(
						Double.parseDouble(lat), Double.parseDouble(lng),
						Double.parseDouble(diameter), year);
			}else if (options.equals("ByMonthLocation")){
		//		result = speciesSpotRecordService.getSpeciesSpotRecordByMonthLocation(
		//				Double.parseDouble(lat), Double.parseDouble(lng),
		//				Double.parseDouble(diameter),startMonth,endMonth);
				result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByMonthLocation(
						Double.parseDouble(lat), Double.parseDouble(lng),
						Double.parseDouble(diameter),startMonth,endMonth);
			}else if (options.equals("ByYearMonthLocation")){
			//	result = speciesSpotRecordService.getSpeciesSpotRecordByYearMonthLocation(
		//				Double.parseDouble(lat), Double.parseDouble(lng),
		//				Double.parseDouble(diameter),year,startMonth,endMonth);
				result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYearMonthLocation(
						Double.parseDouble(lat), Double.parseDouble(lng),
						Double.parseDouble(diameter),year,startMonth,endMonth);
			}else if (options.equals("ByDateLocation")){
		//		result = speciesSpotRecordService.getSpeciesSpotRecordByPeriodLocation(
		//				Double.parseDouble(lat), Double.parseDouble(lng),
		//				Double.parseDouble(diameter),df.parse(startDate), df.parse(endDate));
				result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByPeriodLocation(
						Double.parseDouble(lat), Double.parseDouble(lng),
						Double.parseDouble(diameter),df.parse(startDate), df.parse(endDate));
			}
			
			List<Integer> subTreeSpeciesIdList = new ArrayList<Integer>();
			if (result1==null || result1.size()<1)  {
				errMsg = "No Records Found!";
				return ERROR;
			}
			
			for(int i=0;i<result1.size();i++){
    			Integer temp = result1.get(i).getSpecisid();
    			if (subTreeSpeciesIdList.indexOf((Object)temp)<0){
    				subTreeSpeciesIdList.add(temp);
    			};		
    		}
			
    /*		for(int i=0;i<result.size();i++){
    			String temp = result.get(i).getSpeciesName().getScientificName();
    			if (subTreeNamelist.indexOf((Object)temp)<0){
    				subTreeNamelist.add(temp);
    			};    			
    			//jsonObjSpecies.getString("commonName");    			
    		}*/
    		

    		long b=System.currentTimeMillis();
    	//	System.out.println("\r<br>excution time "+(b-a)/1000f+" seconds");
			
     	/*	JSONObject jsonObj = new JSONObject(resultJSON);
    		JSONArray jsonarr=jsonObj.getJSONArray("speciesRecord");
    		for(int i=0;i<jsonarr.length();i++){
    			JSONObject jsonObjSpecies = new JSONObject(jsonarr.getString(i));
    			subTreeNamelist.add(jsonObjSpecies.getString("scientificName"));
    			//jsonObjSpecies.getString("commonName");
    			
    		}*/
    		nameChoice=nameChoice.substring(0,1);
    		System.out.println(nameChoice);
   //			subtreeExtractionService.subtreeExtraction(subTreeNamelist, treFileName, resOutTreFileName,resSubImage,nameChoice);
    		
    		String mappingFileName = (String)context.getAttribute("sciCommonNameMappingFile");
    		mappingFileName = ServletActionContext.getServletContext().getRealPath(
    				mappingFileName);
    		subtreeExtractionService.subtreeExtractionId(subTreeSpeciesIdList, treFileName, mappingFileName,resOutTreFileName,resSubImage,nameChoice);
   			
   	//		System.out.println("\r<br>excution time "+(System.currentTimeMillis()-a)/1000f+" seconds");
   			subTreeDiversity = String.valueOf(subtreeExtractionService.getSubTreeDiversity());
   			

            return SUCCESS;
        }
    
    public String randomizeFilename(String path, String filename) throws Exception {
		Random random = new Random();
        String resfilename = filename.substring(0,filename.lastIndexOf(".")) + random.nextLong()
                + filename.substring(filename.lastIndexOf("."));
        while (new File(path+resfilename).exists()) {
        	resfilename = filename.substring(0,filename.lastIndexOf(".")) + random.nextLong()
                    + filename.substring(filename.lastIndexOf("."));
        }
        return resfilename;    
    }
    
    

}
