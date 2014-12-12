package web.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recordsyear")
public class MongoRecordYear {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Integer year;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
