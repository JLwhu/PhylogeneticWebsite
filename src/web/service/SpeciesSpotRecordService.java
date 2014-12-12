package web.service;

import java.util.Date;
import java.util.List;

import web.dao.SpeciesSpotRecordDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.SpeciesSpotRecord;

public interface SpeciesSpotRecordService {
	public SpeciesSpotRecordDao getSpeciesSpotRecordDao() ;
	public void setSpeciesSpotRecordDao(SpeciesSpotRecordDao speciesSpotRecordDao) ;
	public SpeciesSpotRecord getSpeciesRecordById(Integer recordID) throws ServiceException, DaoException;
			
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
	public List getSpeciesSpotYearList() throws ServiceException, DaoException;

}
