package web.service;

import java.util.List;
import java.util.Map;

import web.exception.DaoException;
import web.exception.ServiceException;

public interface MongoSpeciesNameService {
	public String getSpeciesScientificName(String commonName) throws ServiceException, DaoException;
	
	public String getSpeciesCommonName(String scientificName) throws ServiceException, DaoException;
	
	public List getSpeciesScientificNameList() throws ServiceException, DaoException;
	public List getSpeciesCommonNameList() throws ServiceException, DaoException;
	
	public Map getSpeciesScientificNameMap() throws ServiceException, DaoException;
	public Map getSpeciesCommonNameMap() throws ServiceException, DaoException;
	
	public String getIdByScientificName(String scientificName) throws ServiceException, DaoException;	
	public String getIdByCommonName(String commonName) throws ServiceException, DaoException;
}
