package web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import web.exception.DaoException;
import web.model.MongoSpeciesName;
import web.mongoConf.SpringMongoConfig;
import web.util.Page;

public interface MongoSpeciesNameDao {

	public MongoSpeciesName getSpeciesNameByid(Integer id) throws DaoException ;
	public String getIdByScientificName(String scientificName) throws DaoException;	
	public String getIdByCommonName(String commonName) throws DaoException;
	public MongoSpeciesName getSpeciesNameByScientificName(String ScientificName) throws DaoException ;
	public MongoSpeciesName getSpeciesNameByCommonName(String CommonName) throws DaoException ;
	public String getSpeciesCommonNameByScientificName(String scientificName) throws DaoException ;
	public String getSpeciesScientificNameByCommonName(String commonName) throws DaoException;
	public Map getAllSpeciesNameMap() throws DaoException;
	public Map getAllSpeciesScientificNameMap() throws DaoException;	
	public Map getAllSpeciesCommonNameMap() throws DaoException;
	public Map getAllSpeciesNames() throws DaoException;
	public List getAllSpeciesScientificNameList() throws DaoException;	
	public List getAllSpeciesCommonNameList() throws DaoException;

}
