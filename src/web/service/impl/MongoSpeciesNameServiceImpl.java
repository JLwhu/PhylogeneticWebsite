package web.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.MongoSpeciesNameDao;
import web.dao.impl.MongoSpeciesNameDaoImpl;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.service.MongoSpeciesNameService;
import web.service.MongoSpeciesSpotRecordService;

public class MongoSpeciesNameServiceImpl implements MongoSpeciesNameService{
	private MongoSpeciesNameDao mongoSpeciesNameDao=new MongoSpeciesNameDaoImpl();
	private Log log = LogFactory.getLog(MongoSpeciesNameServiceImpl.class);
	public String getSpeciesScientificName(String commonName) throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getSpeciesScientificNameByCommonName(commonName);
	}	
	
	public String getSpeciesCommonName(String scientificName) throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getSpeciesCommonNameByScientificName(scientificName);
	}

	public List getSpeciesScientificNameList() throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getAllSpeciesScientificNameList();
	}
	public List getSpeciesCommonNameList() throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getAllSpeciesCommonNameList();
	}
	
	public Map getSpeciesScientificNameMap() throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getAllSpeciesScientificNameMap();
	}
	public Map getSpeciesCommonNameMap() throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getAllSpeciesCommonNameMap();
	}
	public String getIdByScientificName(String scientificName) throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getIdByScientificName(scientificName);
	}
	public String getIdByCommonName(String commonName) throws ServiceException, DaoException{
		return mongoSpeciesNameDao.getIdByCommonName(commonName);
	}

}
