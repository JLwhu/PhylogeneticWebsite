package web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.MongoSpeciesSpotRecordDao;
import web.dao.impl.MongoSpeciesSpotRecordDaoImpl;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.service.MongoSpeciesSpotRecordService;

public class MongoSpeciesSpotRecordServiceImpl implements MongoSpeciesSpotRecordService{
	private MongoSpeciesSpotRecordDao mongoSpeciesSpotRecordDao=new MongoSpeciesSpotRecordDaoImpl();
	private Log log = LogFactory.getLog(MongoSpeciesSpotRecordServiceImpl.class);
	
	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByLocation(lat, lng, diameter);
		return sprlist;
	}
	
	public List getSpeciesSpotRecordByYearLocation(double lat, double lng,
			double diameter, String year) throws ServiceException, DaoException {
		List sprlist = mongoSpeciesSpotRecordDao
				.getSpeciesSpotRecordByYearLocation(lat, lng, diameter, year);
		return sprlist;
	}	
	
	public List getSpeciesSpotRecordByMonthLocation(double lat, double lng,
			double diameter, String startMonth, String endMonth) throws ServiceException,DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByMonthLocation(lat, lng, diameter, startMonth, endMonth);
		return sprlist;			
	};
	public List getSpeciesSpotRecordByYearMonthLocation(double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws ServiceException,DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByYearMonthLocation(lat, lng, diameter, year, startMonth, endMonth);
		return sprlist;				
	};
	public List getSpeciesSpotRecordByPeriodLocation(double lat, double lng,
			double diameter, Date startdate, Date endDate) throws ServiceException, DaoException {
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByPeriodLocation(lat, lng, diameter, startdate, endDate);
		return sprlist;		
	};

	public List getSpeciesSpotRecordByYear(String year) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByYear(year);
		return sprlist;
		
	};
	
	public List getSpeciesSpotRecordByYearMonth(String year,String startMonth, String endMonth) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByYearMonth(year,startMonth,endMonth);
		return sprlist;
		
	};
	public List getSpeciesSpotRecordByMonth(String month) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByMonth(month);
		return sprlist;
		
	};
	
	public List getSpeciesSpotRecordByMonthRange(String startMonth,String endMonth) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByMonthRange(startMonth, endMonth);
		return sprlist;
	};
	public List getSpeciesSpotRecordByPeriod(Date startdate, Date endDate) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordByPeriod(startdate, endDate);
		return sprlist;
	};
	
	
	
	public List getSpeciesSpotRecordBySpeciesid(String speciesid) throws ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesid(speciesid);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidYear(String speciesid,String year) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidYear(speciesid,year);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidMonth(String speciesid,String startMonth, String endMonth) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidMonth(speciesid,startMonth, endMonth);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidYearMonth(String speciesid,String year, String startMonth, String endMonth) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidYearMonth(speciesid, year, startMonth, endMonth);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidPeriod(String speciesid, Date startdate, Date endDate) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidPeriod(speciesid, startdate, endDate);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidLocation(String speciesid, double lat, double lng,
			double diameter) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidLocation(speciesid, lat, lng, diameter);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidLocationYear(String speciesid, double lat, double lng,
			double diameter, String year) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidLocationYear(speciesid, lat, lng, diameter,year);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidLocationYearMonth(String speciesid, double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidLocationYearMonth(speciesid, lat, lng, diameter,year,startMonth, endMonth);
		return sprlist;
	}
	public List getSpeciesSpotRecordBySpeciesidLocationPeriod(String speciesid, double lat, double lng,
			double diameter, Date startdate, Date endDate) throws  ServiceException, DaoException{
		List sprlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotRecordBySpeciesidLocationPeriod(speciesid, lat, lng, diameter,startdate, endDate);
		return sprlist;
	}	
	
	public List getSpeciesSpotYearList() throws ServiceException, DaoException{
		List yearlist =  mongoSpeciesSpotRecordDao.getSpeciesSpotYearList();
		return yearlist;
	};
	
	
	
}
