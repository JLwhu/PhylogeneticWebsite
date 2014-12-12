package web.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import web.dao.MongoSpeciesNameDao;
import web.exception.DaoException;
import web.model.MongoSpeciesName;
import web.model.MongoSpeciesRecord;
import web.model.SpeciesName;
import web.mongoConf.MongoConn;
import web.mongoConf.SpringMongoConfig;
import web.util.Page;

public class MongoSpeciesNameDaoImpl implements MongoSpeciesNameDao {

	private MongoConn conn= new MongoConn();
	public MongoSpeciesName getSpeciesNameByid(Integer id) throws DaoException {
		// For Annotation
	/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("speciesid").is(id));
	//	MongoSpeciesName mongoSpeciesName = mongoOperation.findOne(query,
	//			MongoSpeciesName.class);
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(query,
				MongoSpeciesName.class);
		conn.closeDbConnection();
		return mongoSpeciesName;

	}
	public String getIdByScientificName(String scientificName) throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("scientificName").is(scientificName));
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(query,
				MongoSpeciesName.class);
		conn.closeDbConnection();
		
		if (mongoSpeciesName!=null)
			return String.valueOf(mongoSpeciesName.getSpeciesid());
		else
			return null;

	}
	
	public String getIdByCommonName(String commonName) throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("commonName").is(commonName));
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(query,
				MongoSpeciesName.class);
		conn.closeDbConnection();
		
		return String.valueOf(mongoSpeciesName.getSpeciesid());

	}
	public MongoSpeciesName getSpeciesNameByScientificName(String ScientificName) throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/
		
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("scientificName").is(ScientificName)					
		);
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(
				query, MongoSpeciesName.class);
		conn.closeDbConnection();
		
		return mongoSpeciesName;
	}
	public MongoSpeciesName getSpeciesNameByCommonName(String commonName) throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/
		
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("commonName").is(commonName)					
		);
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(
				query, MongoSpeciesName.class);
		conn.closeDbConnection();
		
		return mongoSpeciesName;
	}

	public String getSpeciesCommonNameByScientificName(String scientificName)
			throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(Criteria.where("scientificName").is(scientificName));
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(query,
				MongoSpeciesName.class);
		conn.closeDbConnection();
		
		return mongoSpeciesName.getCommonName();
	}
	public String getSpeciesScientificNameByCommonName(String commonName) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/
		
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("commonName").is(commonName)					
		);
		MongoSpeciesName mongoSpeciesName = conn.mongoTemplate.findOne(
				query, MongoSpeciesName.class);
		conn.closeDbConnection();
		
		return mongoSpeciesName.getScientificName();
	}

	public Map getAllSpeciesNameMap() throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Map resMap = new HashMap();
		List<MongoSpeciesName> mongoSpeciesNameList = conn.mongoTemplate.findAll(MongoSpeciesName.class);

		Iterator<MongoSpeciesName> iterator = mongoSpeciesNameList.iterator();
		while (iterator.hasNext()) {
			MongoSpeciesName sn = iterator.next();
			resMap.put(sn.getScientificName(), sn.getCommonName());
		}
		conn.closeDbConnection();
		
		return resMap;
	}
	
	public Map getAllSpeciesNames() throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Map resMap = new HashMap();
		List<MongoSpeciesName> mongoSpeciesNameList = conn.mongoTemplate
				.findAll(MongoSpeciesName.class);

		Iterator<MongoSpeciesName> iterator = mongoSpeciesNameList.iterator();
		while (iterator.hasNext()) {
			MongoSpeciesName sn = iterator.next();
			resMap.put(sn.getSpeciesid(), sn);
		}
		conn.closeDbConnection();
		
		return resMap;
	}
	
	public Map getAllSpeciesScientificNameMap() throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Map resMap = new HashMap();
		List<MongoSpeciesName> mongoSpeciesNameList = conn.mongoTemplate
				.findAll(MongoSpeciesName.class);

		Iterator<MongoSpeciesName> iterator = mongoSpeciesNameList.iterator();
		while (iterator.hasNext()) {
			MongoSpeciesName sn = iterator.next();
			resMap.put(sn.getSpeciesid(), sn.getScientificName());
		}
		conn.closeDbConnection();
		
		return resMap;
	}
	
	public Map getAllSpeciesCommonNameMap() throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		Map resMap = new HashMap();
		List<MongoSpeciesName> mongoSpeciesNameList = conn.mongoTemplate
				.findAll(MongoSpeciesName.class);

		Iterator<MongoSpeciesName> iterator = mongoSpeciesNameList.iterator();
		while (iterator.hasNext()) {
			MongoSpeciesName sn = iterator.next();
			resMap.put(sn.getSpeciesid(), sn.getCommonName());
		}
		conn.closeDbConnection();
		
		return resMap;
	}
	
	public List getAllSpeciesScientificNameList() throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/
		
		conn.openDbConnection();
		List<String> res = new ArrayList<String>();
		List<MongoSpeciesName> mongoSpeciesNameList = conn.mongoTemplate
				.findAll(MongoSpeciesName.class);

		Iterator<MongoSpeciesName> iterator = mongoSpeciesNameList.iterator();
		while (iterator.hasNext()) {
			MongoSpeciesName sn = iterator.next();
			res.add(sn.getScientificName());
		}
		conn.closeDbConnection();
		
		return res;
	}
	
	public List getAllSpeciesCommonNameList() throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");*/

		conn.openDbConnection();
		List<String> res = new ArrayList<String>();
		List<MongoSpeciesName> mongoSpeciesNameList = conn.mongoTemplate
				.findAll(MongoSpeciesName.class);

		Iterator<MongoSpeciesName> iterator = mongoSpeciesNameList.iterator();
		while (iterator.hasNext()) {
			MongoSpeciesName sn = iterator.next();
			res.add(sn.getCommonName());
		}
		conn.closeDbConnection();
		
		return res;
	}

}
