package web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.MongoSpeciesSpotRecordDao;
import web.dao.SpeciesSpotRecordDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.SpeciesSpotRecord;
import web.service.SpeciesSpotRecordService;

public class SpeciesSpotRecordServiceImpl implements SpeciesSpotRecordService{
	private SpeciesSpotRecordDao speciesSpotRecordDao;
	private Log log = LogFactory.getLog(SpeciesSpotRecordServiceImpl.class);
	
	public SpeciesSpotRecordDao getSpeciesSpotRecordDao() {
		return speciesSpotRecordDao;
	}
	public void setSpeciesSpotRecordDao(SpeciesSpotRecordDao speciesSpotRecordDao) {
		this.speciesSpotRecordDao = speciesSpotRecordDao;
	}
	
	public SpeciesSpotRecord getSpeciesRecordById(Integer recordID) throws ServiceException, DaoException{
		SpeciesSpotRecord speciesSpotRecord =  speciesSpotRecordDao.getSpeciesSpotRecordByID(recordID);
		return speciesSpotRecord;
	}
	
	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws ServiceException, DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByLocation(lat, lng, diameter);
		return sprlist;
	}
	
	public List getSpeciesSpotRecordByYearLocation(double lat, double lng,
			double diameter, String year) throws ServiceException,DaoException {
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByYearLocation(lat, lng, diameter, year);
		return sprlist;		
	};
	public List getSpeciesSpotRecordByMonthLocation(double lat, double lng,
			double diameter, String startMonth, String endMonth) throws ServiceException,DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByMonthLocation(lat, lng, diameter, startMonth, endMonth);
		return sprlist;			
	};
	public List getSpeciesSpotRecordByYearMonthLocation(double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws ServiceException,DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByYearMonthLocation(lat, lng, diameter, year, startMonth, endMonth);
		return sprlist;				
	};
	public List getSpeciesSpotRecordByPeriodLocation(double lat, double lng,
			double diameter, Date startdate, Date endDate) throws ServiceException, DaoException {
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByPeriodLocation(lat, lng, diameter, startdate, endDate);
		return sprlist;		
	};

	public List getSpeciesSpotRecordByYear(String year) throws ServiceException, DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByYear(year);
		return sprlist;
		
	};
	
	public List getSpeciesSpotRecordByYearMonth(String year,String startMonth, String endMonth) throws ServiceException, DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByYearMonth(year,startMonth,endMonth);
		return sprlist;
		
	};
	public List getSpeciesSpotRecordByMonth(String month) throws ServiceException, DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByMonth(month);
		return sprlist;
		
	};
	
	public List getSpeciesSpotRecordByMonthRange(String startMonth,String endMonth) throws ServiceException, DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByMonthRange(startMonth, endMonth);
		return sprlist;
	};
	public List getSpeciesSpotRecordByPeriod(Date startdate, Date endDate) throws ServiceException, DaoException{
		List sprlist =  speciesSpotRecordDao.getSpeciesSpotRecordByPeriod(startdate, endDate);
		return sprlist;
	};
	public List getSpeciesSpotYearList() throws ServiceException, DaoException{
		List yearlist =  speciesSpotRecordDao.getSpeciesSpotYearList();
		return yearlist;
	};
}
