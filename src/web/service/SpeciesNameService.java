package web.service;

import java.util.List;

import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.SpeciesName;
import web.util.Page;


public interface SpeciesNameService extends BaseService{


	public String getSpeciesScientificName(String commonName) throws ServiceException, DaoException;
	
	public String getSpeciesCommonName(String scientificName) throws ServiceException, DaoException;
//	public Page getSpeciesList(Page page) throws ServiceException;
//	public List getSpeciesListStartswith(String spe) throws ServiceException;
	
/*	public void addSpecies(Species Species) throws ServiceException;
	public void modifySpecies(Species Species) throws ServiceException;
	public void deleteSpeciesByID(String SpeciesID) throws ServiceException;
	
	public Species selectSpeciesByID(Long SpeciesID) throws ServiceException;
	public Page getSpeciesByID(Page page, String SpeciesID) throws ServiceException;
	public List<String> getAllSpeciesNum() throws ServiceException;*/
	
}
