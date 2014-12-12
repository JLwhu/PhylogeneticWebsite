package web.dao;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
 
import web.exception.DaoException;
import web.mongoConf.MongoConn;
import web.mongoConf.SpringMongoConfig;
import web.model.MongoSpeciesRecord;
import web.model.SpeciesSpotRecord;;

public interface MongoSpeciesSpotRecordDao {
	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws DaoException ;	
	public List getSpeciesSpotRecordByLocationRectangle(double lat, double lng,
			double latend, double lngend, MongoConn conn) throws DaoException;  //
	public List getSpeciesSpotRecordByYearLocation(double lat, double lng,
			double diameter, String year) throws DaoException ;
	public List getSpeciesSpotRecordByMonthLocation(double lat, double lng,
			double diameter, String startMonth, String endMonth) throws DaoException;
	public List getSpeciesSpotRecordByYearMonthLocation(double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws DaoException;
	public List getSpeciesSpotRecordByPeriodLocation(double lat, double lng,
			double diameter, Date startdate, Date endDate) throws DaoException ;
	public List getSpeciesSpotRecordByYear(String year) throws DaoException;
	public List getSpeciesSpotRecordByYearMonth(String year,String startMonth, String endMonth) throws DaoException;
	public List getSpeciesSpotRecordByMonth(String month) throws DaoException;
	public List getSpeciesSpotRecordByMonthRange(String startMonth,String endMonth) throws DaoException;
	public List getSpeciesSpotRecordByPeriod(Date startdate, Date endDate) throws DaoException;
	
	public List getSpeciesSpotRecordBySpeciesid(String speciesid) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidYear(String speciesid,String year) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidMonth(String speciesid,String startMonth, String endMonth) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidYearMonth(String speciesid,String year, String startMonth, String endMonth) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidPeriod(String speciesid, Date startdate, Date endDate) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocation(String speciesid, double lat, double lng,
			double diameter) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocationYear(String speciesid, double lat, double lng,
			double diameter, String year) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocationYearMonth(String speciesid, double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocationPeriod(String speciesid, double lat, double lng,
			double diameter, Date startdate, Date endDate) throws DaoException;
	
	public List getSpeciesSpotYearList() throws DaoException;
	
	public void saveSpeciesSpotRecord(MongoSpeciesRecord msr) throws DaoException;
}
