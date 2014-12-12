package web.mongoConf;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

public class MongoConn {
	public Mongo mongo;
	public SimpleMongoDbFactory dbFactory;
	public MongoTemplate mongoTemplate;
	public boolean openDbConnection() {
	    try {
	        MongoOptions options = new MongoOptions();
	        options.connectionsPerHost = 100;
	        mongo = new Mongo("localhost", options);
	        dbFactory = new SimpleMongoDbFactory(mongo, "phylogenetics");
	        MappingMongoConverter converter = new MappingMongoConverter(dbFactory, new MongoMappingContext());
	        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
	        mongoTemplate = new MongoTemplate(dbFactory, converter);

	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	public boolean closeDbConnection() {
	    try {
	        mongoTemplate = null;
	        dbFactory = null;
	        mongo.close();

	        return true;
	    }catch (Exception e) {
	        return false;
	    }
	}
 
}
