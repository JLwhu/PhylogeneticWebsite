package web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.SpeciesNameDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.SpeciesName;
import web.service.BaseService;
import web.service.SpeciesNameService;
import web.util.Page;

public class SpeciesNameServiceImpl extends BaseServiceImpl implements SpeciesNameService{

	private Log log = LogFactory.getLog(SpeciesNameServiceImpl.class);
	private SpeciesNameDao speciesNameDao;
	
	public SpeciesNameDao getSpeciesNameDao() {
		return speciesNameDao;
	}
	public void setSpeciesNameDao(SpeciesNameDao speciesNameDao) {
		this.speciesNameDao = speciesNameDao;
	}
	
	public String getSpeciesScientificName(String commonName) throws ServiceException, DaoException{
		return speciesNameDao.getSpeciesScientificNameByCommonName(commonName);
	}
	
	public String getSpeciesCommonName(String scientificName) throws ServiceException, DaoException{
		return speciesNameDao.getSpeciesCommonNameByScientificName(scientificName);
	}
	
	//
	/*public Page getSpeciesList(Page page) throws ServiceException, DaoException{
		List speciesList = null;
		speciesList = SpeciesNameDao.getSpeciesList(page);
		return new Page(speciesList, page.getMaxRowCount(), page.getCurPage(), page.getPageSize());
	}
		
	
			public List getSpeciesListStartswith(String spe) throws ServiceException, DaoException{
		List speciesList = null;
try {
			speciesList = SpeciesNameDao.getSpeciesStartingWith(spe);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return speciesList;
	}*/
	

	
	
	/*
	public void addSpecies(Species species) throws ServiceException{
		try {
			SpeciesDao.addSpecies(species);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	//
	public void modifySpecies(Species Species) throws ServiceException{
		try {
			SpeciesDao.modifySpecies(Species);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	//
	public void deleteSpeciesByID(String SpeciesID) throws ServiceException{
		try {
			SpeciesDao.deleteSpecies(SpeciesID);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	//
	public Species selectSpeciesByID(Long SpeciesID) throws ServiceException{
		Species Species = new Species();
		try {
			Species=SpeciesDao.selectSpeciesByID(SpeciesID);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return Species;
	}
	
	//
	public Page getSpeciesByID(Page page, String SpeciesID) throws ServiceException{
		List speciesList = null;
		try {
			speciesList = SpeciesDao.getSpeciesListByID(page,SpeciesID);
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return new Page(speciesList, page.getMaxRowCount(), page.getCurPage(), page.getPageSize());
	}
	
	//
	public List<String> getAllSpeciesNum() throws ServiceException{
		List<String> SpeciesNum = new ArrayList();
		try {
			SpeciesNum = SpeciesDao.getAllSpeciesNum();
		} catch (DaoException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return SpeciesNum;
	
	}*/
}
