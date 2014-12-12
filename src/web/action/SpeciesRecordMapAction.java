package web.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.MongoSpeciesRecord;
import web.model.SpeciesSpotRecord;
import web.service.MongoSpeciesNameService;
import web.service.MongoSpeciesSpotRecordService;
import web.service.SpeciesSpotRecordService;
import web.service.impl.MongoSpeciesNameServiceImpl;
import web.service.impl.MongoSpeciesSpotRecordServiceImpl;
import web.util.JSONio;

public class SpeciesRecordMapAction    extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private Log log = LogFactory.getLog(SpeciesRecordMapAction.class);
	
	//search by location
	private String lat;
	private String lng;
	private String diameter;
	
	//search by date
	private String startDate;
	private String endDate;
	private String year;
	private String name;
	private String startMonth;
	private String endMonth;
	private Map<String, String> years = new HashMap<String, String>();
	private Map<String, String> names = new HashMap<String, String>();
	
	private List<SpeciesSpotRecord> result;
	private String resultJSON;
	
	private SpeciesSpotRecordService speciesSpotRecordService;

	private MongoSpeciesSpotRecordService mongoSpeciesSpotRecordService=new MongoSpeciesSpotRecordServiceImpl();
	private MongoSpeciesNameService mongoSpeciesNameService=new MongoSpeciesNameServiceImpl();
	
	public SpeciesSpotRecordService getSpeciesSpotRecordService() {
		return speciesSpotRecordService;
	}
	public void setSpeciesSpotRecordService(SpeciesSpotRecordService speciesSpotRecordService) {
		this.speciesSpotRecordService = speciesSpotRecordService;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
	
	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}
	
	public Map<String, String> getYears() {
		return years;
	}

	public void setYears(Map<String, String> years) {
		this.years = years;
	}
	
	public Map<String, String> getNames() {
		return names;
	}

	public void setNames(Map<String, String> names) {
		this.names = names;
	}


	public String getSpeciesSpotYearList() {
		try {
			/*		List<Integer> yearlist = speciesSpotRecordService.getSpeciesSpotYearList();
			for (int i=0;i<yearlist.size();i++){
				years.put(String.valueOf(yearlist.get(i)), String.valueOf(yearlist.get(i)));
			}*/
			
			List<Integer> yearlist = mongoSpeciesSpotRecordService.getSpeciesSpotYearList();
			for (int i=0;i<yearlist.size();i++){
				years.put(String.valueOf(yearlist.get(i)), String.valueOf(yearlist.get(i)));
			}			
			
		} catch (ServiceException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Get Year List Error.");
		}
		
		return SUCCESS;
		
	}
	
	public String getSpeciesScientificNameList() {
		try {
	//		List<Integer> namelist = mongoSpeciesNameService.getSpeciesScientificNameList();
	//		for (int i=0;i<namelist.size();i++){
	//			names.put(String.valueOf(namelist.get(i)), String.valueOf(namelist.get(i)));
	//		}	
			
			HashMap nameMap = (HashMap) mongoSpeciesNameService.getSpeciesScientificNameMap();
			Iterator iter = nameMap.entrySet().iterator(); 
			while (iter.hasNext()) { 
			    Map.Entry entry = (Map.Entry) iter.next(); 
			    Object key = entry.getKey(); 
			    Object val = entry.getValue(); 
			    names.put(String.valueOf(val), String.valueOf(val));
			//    System.out.println(String.valueOf(key)+" "+ String.valueOf(val));
			} 
		} catch (ServiceException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
		
	}
	
	public String getSpeciesSpotRecordByLocation() {

		try {
//			result = speciesSpotRecordService.getSpeciesSpotRecordByLocation(
//					Double.parseDouble(lat), Double.parseDouble(lng),
//					Double.parseDouble(diameter));
			
		//	result = speciesSpotRecordService.getSpeciesSpotRecordByLocation(
		//			Double.parseDouble(lat), Double.parseDouble(lng),
		//			Double.parseDouble(diameter));
		//	resultJSON = createSpeciesSpotRecordJSONObject(result).toString();
			
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter));
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getSpeciesSpotRecordByYearLocation() {

		try {
		/*	result = speciesSpotRecordService.getSpeciesSpotRecordByYearLocation(
			Double.parseDouble(lat), Double.parseDouble(lng),
			Double.parseDouble(diameter), year);
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYearLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter), year);
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getSpeciesSpotRecordByMonthLocation() {

		try {
		/*	result = speciesSpotRecordService.getSpeciesSpotRecordByMonthLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter),startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByMonthLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter),startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getSpeciesSpotRecordByYearMonthLocation() {

		try {
		/*	result = speciesSpotRecordService.getSpeciesSpotRecordByYearMonthLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter),year,startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYearMonthLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter),year,startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getSpeciesSpotRecordByPeriodLocation() {

		try {
	/*		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			result = speciesSpotRecordService.getSpeciesSpotRecordByPeriodLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter),df.parse(startDate), df.parse(endDate));
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString(); */
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByPeriodLocation(
					Double.parseDouble(lat), Double.parseDouble(lng),
					Double.parseDouble(diameter),df.parse(startDate), df.parse(endDate));
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	public String getSpeciesSpotRecordByYear() {

		try {
			/*result = speciesSpotRecordService
					.getSpeciesSpotRecordByYear(year);
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYear(year);
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getSpeciesSpotRecordByYearMonth() {

		try {
			/*result = speciesSpotRecordService
					.getSpeciesSpotRecordByYearMonth(year,startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByYearMonth(year,startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public String getSpeciesSpotRecordByMonthRange() {

		try {
		/*	result = speciesSpotRecordService
					.getSpeciesSpotRecordByMonthRange(startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByMonthRange(startMonth,endMonth);
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getSpeciesSpotRecordByPeriod() {

		try {
		/*	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			result = speciesSpotRecordService.getSpeciesSpotRecordByPeriod(df.parse(startDate), df.parse(endDate));
			resultJSON = createSpeciesSpotRecordJSONObject(result).toString();*/
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordByPeriod(df.parse(startDate), df.parse(endDate));
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getSpeciesSpotRecordBySpeciesid() {
		try {
			String id = mongoSpeciesNameService.getIdByScientificName(name);
			List<MongoSpeciesRecord> result1 = mongoSpeciesSpotRecordService.getSpeciesSpotRecordBySpeciesid(id);
			System.out.println(result1.size());
			resultJSON = createSpeciesSpotRecordJSONObjectMongo(result1).toString();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	


	public JSONObject createSpeciesSpotRecordJSONObject(List<SpeciesSpotRecord> result) throws JSONException {
		JSONArray resultJSONArray = new JSONArray();
		int count = result.size();
		for (int i = 0; i < count; i++) {
			SpeciesSpotRecord speciesSpotRecord = (SpeciesSpotRecord) result
					.get(i);
			JSONObject obj = new JSONObject();
			obj.put("id", speciesSpotRecord.getRecordid());
			obj.put("commonName", speciesSpotRecord.getSpeciesName()
					.getCommonName());
			obj.put("scientificName", speciesSpotRecord.getSpeciesName()
					.getScientificName());
			obj.put("spotdate", speciesSpotRecord.getSpotDate());
			obj.put("lat", String.valueOf(speciesSpotRecord.getLat()));
			obj.put("lng", String.valueOf(speciesSpotRecord.getLng()));
			resultJSONArray.put(obj);
		}
		JSONObject resObj = new JSONObject();
		resObj.put("speciesRecord", resultJSONArray);
		return resObj;		
	}

	public JSONObject createSpeciesSpotRecordJSONObjectMongo(List<MongoSpeciesRecord> result) throws JSONException {
		JSONArray resultJSONArray = new JSONArray();
		int count = result.size();
		for (int i = 0; i < count; i++) {
			MongoSpeciesRecord speciesSpotRecord = (MongoSpeciesRecord) result
					.get(i);
			JSONObject obj = new JSONObject();
	//		obj.put("id", speciesSpotRecord.getId());
	//		obj.put("commonName", speciesSpotRecord.getSpeciesName()
	//				.getCommonName());
	//		obj.put("scientificName", speciesSpotRecord.getSpeciesName()
	//				.getScientificName());
	//		obj.put("spotdate", speciesSpotRecord.getSpotDate());
			obj.put("lat", String.valueOf(speciesSpotRecord.getLat()));
			obj.put("lng", String.valueOf(speciesSpotRecord.getLng()));
			resultJSONArray.put(obj);
		}
		JSONObject resObj = new JSONObject();
		resObj.put("speciesRecord", resultJSONArray);
	//	System.out.print("\r\n"+resObj.toString());
		return resObj;		
	}


}
