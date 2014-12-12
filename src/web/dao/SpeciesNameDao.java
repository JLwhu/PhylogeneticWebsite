package web.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import web.exception.DaoException;
import web.model.Hotspot;
import web.model.SpeciesName;
import web.util.Page;

public interface SpeciesNameDao  extends BaseDao{
	
	public SpeciesName getSpeciesNameByid(Integer id) throws DaoException ;
	public SpeciesName getSpeciesNameByScientificName(String ScientificName) throws DaoException ;
	public SpeciesName getSpeciesNameByCommonName(String CommonName) throws DaoException ;
	public String getSpeciesCommonNameByScientificName(String scientificName) throws DaoException ;
	public String getSpeciesScientificNameByCommonName(String commonName) throws DaoException;
	public Map getAllSpeciesNames() throws DaoException;
	
	public List getSpeciesList(Page page) throws DaoException;
	public List getSpeciesSciStartingWith(String spe) throws DaoException;
	
	public List getSpeciesComStartingWith(String spe) throws DaoException ;
	public Map getAllSpeciesScientificNameMap() throws DaoException;

}