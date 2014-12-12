package web.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.geo.Box;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

import web.dao.MongoSpeciesSpotRecordDao;
import web.exception.DaoException;
import web.model.MongoRecordYear;
import web.model.MongoSpeciesRecord;
import web.model.SpeciesSpotRecord;
import web.mongoConf.MongoConn;
import web.mongoConf.SpringMongoConfig;

public class MongoSpeciesSpotRecordDaoImpl implements MongoSpeciesSpotRecordDao{
	private MongoConn conn= new MongoConn();
	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws DaoException {
		// For XML
		// ApplicationContext ctx = new
		// GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");

*/
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				new Query(Criteria.where("loc").within(circle)),
				MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
		
	}
	
	public List getSpeciesSpotRecordByLocationRectangle(double lat, double lng,
			double latend, double lngend, MongoConn conn) throws DaoException { // 
	//	conn.openDbConnection();

		Box box = new Box(new double[] { lng , lat }, new double[] { lngend,
				latend });
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				new Query(Criteria.where("loc").within(box)),
				MongoSpeciesRecord.class);
	//	conn.closeDbConnection();
		return mongoSpeciesRecords;

	}
	
	public List getSpeciesSpotRecordByYearLocation(double lat, double lng,
			double diameter, String year) throws DaoException {
		// For Annotation
	/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		*/
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);
		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("year").is(Integer.valueOf(year))
			)
		);
	//	List<MongoSpeciesRecord> mongoSpeciesRecords = mongoOperation.find(
	//			query, MongoSpeciesRecord.class);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
		
	}
	public List getSpeciesSpotRecordByMonthLocation(double lat, double lng,
			double diameter, String startMonth, String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");

*/
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);

		Query query = new Query();
		query.addCriteria(Criteria
				.where("loc")
				.within(circle)
				.andOperator(
						Criteria.where("month")
								.gte(Integer.valueOf(startMonth)),
						Criteria.where("month").lte(Integer.valueOf(endMonth))));
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByYearMonthLocation(double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");

*/
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);
		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("year").is(Integer.valueOf(year)),
				Criteria.where("month").gte(Integer.valueOf(startMonth)),
				Criteria.where("month").lte(Integer.valueOf(endMonth))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByPeriodLocation(double lat, double lng,
			double diameter, Date startdate, Date endDate) throws DaoException {
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");

*/
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);
		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("spotdate").gte(startdate),
				Criteria.where("spotdate").lte(endDate)
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByYear(String year) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				new Query(Criteria.where("year").is(Integer.valueOf(year))),
				MongoSpeciesRecord.class);
		conn.closeDbConnection();
		
/*		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date startDate;
		List<MongoSpeciesRecord> mongoSpeciesRecords = null;
		try {
			startDate = dateFormat.parse(year+"-01-01");
			Date endDate = dateFormat.parse(year+"-12-31");
			
			Query query = new Query();
			query.addCriteria(
				Criteria.where("spotdate).lte(endDate)
				.andOperator(
					Criteria.where("spotdate").gte(startDate)
				)
			);
			
			mongoSpeciesRecords = mongoOperation.find(
					query,MongoSpeciesRecord.class);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByYearMonth(String year,String startMonth, String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("year").is(Integer.valueOf(year))
			.andOperator(
				Criteria.where("month").gte(Integer.valueOf(startMonth)),
				Criteria.where("month").lte(Integer.valueOf(endMonth))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByMonth(String month) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("month").is(Integer.valueOf(month))
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByMonthRange(String startMonth,String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("month").gte(Integer.valueOf(startMonth))
			.andOperator(
				Criteria.where("month").lte(Integer.valueOf(endMonth))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	public List getSpeciesSpotRecordByPeriod(Date startdate, Date endDate) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("spotdate").gte(startdate)
			.andOperator(
				Criteria.where("spotdate").lte(endDate)
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesid(String speciesid) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("speciesid").is(Integer.valueOf(speciesid))
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidYear(String speciesid,String year) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("speciesid").is(speciesid)
		.andOperator(
				Criteria.where("year").is(year)
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidMonth(String speciesid,String startMonth, String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("speciesid").is(speciesid)
		.andOperator(
				Criteria.where("month").gte(Integer.valueOf(startMonth)),
				Criteria.where("month").lte(Integer.valueOf(endMonth))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidYearMonth(String speciesid,String year, String startMonth, String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("speciesid").is(speciesid)
		.andOperator(
				Criteria.where("year").is(year),
				Criteria.where("month").gte(Integer.valueOf(startMonth)),
				Criteria.where("month").lte(Integer.valueOf(endMonth))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidPeriod(String speciesid, Date startdate, Date endDate) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Query query = new Query();
		query.addCriteria(
			Criteria.where("speciesid").is(speciesid)
		.andOperator(
				Criteria.where("spotdate").gte(startdate),
				Criteria.where("spotdate").lte(endDate)
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidLocation(String speciesid, double lat, double lng,
			double diameter) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("speciesid").is(speciesid)
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidLocationYear(String speciesid, double lat, double lng,
			double diameter, String year) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("speciesid").is(speciesid),
				Criteria.where("year").is(Integer.valueOf(year))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidLocationYearMonth(String speciesid, double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("speciesid").is(speciesid),
				Criteria.where("year").is(Integer.valueOf(year)),
				Criteria.where("month").gte(Integer.valueOf(startMonth)),
				Criteria.where("month").lte(Integer.valueOf(endMonth))
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	public List getSpeciesSpotRecordBySpeciesidLocationPeriod(String speciesid, double lat, double lng,
			double diameter, Date startdate, Date endDate) throws DaoException{
		// For Annotation
		/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
		SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
		.getBean("mongoTemplate");
*/	
		conn.openDbConnection();
		Circle circle = new Circle(lng, lat, diameter);		
		Query query = new Query();
		query.addCriteria(
			Criteria.where("loc").within(circle)
			.andOperator(
				Criteria.where("speciesid").is(speciesid),
				Criteria.where("spotdate").gte(startdate),
				Criteria.where("spotdate").lte(endDate)
			)
		);
		List<MongoSpeciesRecord> mongoSpeciesRecords = conn.mongoTemplate.find(
				query, MongoSpeciesRecord.class);
		conn.closeDbConnection();
		return mongoSpeciesRecords;
	}
	
	
	public List getSpeciesSpotYearList() throws DaoException{
		// For Annotation
	/*	ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");*/
		conn.openDbConnection();
	//	List<MongoRecordYear> mongoRecordYears = mongoOperation.findAll(MongoRecordYear.class);
		List<MongoRecordYear> mongoRecordYears = conn.mongoTemplate.findAll(MongoRecordYear.class);
		List<Integer> reordYears = new ArrayList<Integer>();
		for (MongoRecordYear temp:mongoRecordYears){
			reordYears.add(temp.getYear());
		}
		conn.closeDbConnection();
		return reordYears;
	}
	
	public void saveSpeciesSpotRecord(MongoSpeciesRecord msr) throws DaoException{
		// For Annotation
/*		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
		mongoOperation.save(msr);*/
		
//		if (openDbConnection()){
			conn.mongoTemplate.save(msr);
			
	//	};
		
		
	}	

}
