package web.service;

import java.util.Date;
import java.util.List;

import web.exception.DaoException;
import web.exception.ServiceException;

public interface MongoSpeciesSpotRecordService {
	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws ServiceException, DaoException;
	public List getSpeciesSpotRecordByYearLocation(double lat, double lng,
			double diameter, String year) throws ServiceException,DaoException ;
	public List getSpeciesSpotRecordByMonthLocation(double lat, double lng,
			double diameter, String startMonth, String endMonth) throws ServiceException,DaoException;
	public List getSpeciesSpotRecordByYearMonthLocation(double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws ServiceException,DaoException;
	public List getSpeciesSpotRecordByPeriodLocation(double lat, double lng,
			double diameter, Date startdate, Date endDate) throws ServiceException, DaoException ;

	public List getSpeciesSpotRecordByYear(String year) throws ServiceException, DaoException;
	public List getSpeciesSpotRecordByYearMonth(String year,String startMonth, String endMonth) throws ServiceException, DaoException;
	public List getSpeciesSpotRecordByMonth(String month) throws ServiceException, DaoException;
	public List getSpeciesSpotRecordByMonthRange(String startMonth,String endMonth) throws ServiceException, DaoException;
	public List getSpeciesSpotRecordByPeriod(Date startdate, Date endDate) throws ServiceException, DaoException;
	
	public List getSpeciesSpotRecordBySpeciesid(String speciesid) throws ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidYear(String speciesid,String year) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidMonth(String speciesid,String startMonth, String endMonth) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidYearMonth(String speciesid,String year, String startMonth, String endMonth) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidPeriod(String speciesid, Date startdate, Date endDate) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocation(String speciesid, double lat, double lng,
			double diameter) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocationYear(String speciesid, double lat, double lng,
			double diameter, String year) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocationYearMonth(String speciesid, double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws  ServiceException, DaoException;
	public List getSpeciesSpotRecordBySpeciesidLocationPeriod(String speciesid, double lat, double lng,
			double diameter, Date startdate, Date endDate) throws  ServiceException, DaoException;
	
	public List getSpeciesSpotYearList() throws ServiceException, DaoException;
}