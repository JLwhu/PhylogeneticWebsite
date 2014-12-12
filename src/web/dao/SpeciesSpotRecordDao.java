package web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import web.exception.DaoException;
import web.model.SpeciesSpotRecord;

public interface SpeciesSpotRecordDao extends BaseDao {
	
	public SpeciesSpotRecord getSpeciesSpotRecordByID(Integer recordID) throws DaoException;

	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws DaoException ;
	
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
	public List getSpeciesSpotYearList() throws DaoException;
	public void saveSpeciesSpotRecord(SpeciesSpotRecord sr) throws DaoException;

}