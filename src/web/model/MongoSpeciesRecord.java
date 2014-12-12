package web.model;

import java.lang.annotation.Target;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Reference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "speciesrecords")
public class MongoSpeciesRecord implements java.io.Serializable {

	/** The Constant serialVersionUID. */
//	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	// private String recordName;
	// @DBRef()
	// private MongoSpeciesName speciesName;
	private Integer speciesid;
	private Date spotDate;
	private Integer year;
	private Integer month;
	@GeoSpatialIndexed
	private double[] loc;
	@Transient
	private double latitute;
	@Transient
	private double logitute;
	private String recordName;
	private Integer abundance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * public String getRecordName() { return recordName; }
	 * 
	 * public void setRecordName(String recordName) { this.recordName =
	 * recordName; }
	 * 
	 * public MongoSpeciesName getSpeciesName(){ return speciesName; }
	 */
	
	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public Integer getSpecisid() {
		return speciesid;
	}

	public void setSpecisid(Integer speciesid) {
		this.speciesid = speciesid;
	}

	public Date getSpotDate() {
		return spotDate;
	}

	public void setSpotDate(Date spotDate) {
		this.spotDate = spotDate;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	/*
	 * @PersistenceConstructor MongoSpeciesRecord(Integer specisid, double[]
	 * location) { super(); this.specisid = specisid; this.position = position;
	 * }
	 * 
	 * public MongoSpeciesRecord(Integer specisid, double x, double y) {
	 * super(); this.specisid = specisid; this.position = new double[] { x, y };
	 * }
	 */

	public double[] getLoc() {
		return loc;
	}
	
	public void setLoc(double[] loc) {
		this.loc = loc;
	}

	public double getLat() {
		if (loc != null) {
			this.latitute = loc[1];

		}
		return latitute;
	}

	public void setLat(double latitute) {

		this.latitute = latitute;

	}

	public double getLng() {
		if (loc != null) {
			this.logitute = loc[0];

		}
		return logitute;
	}

	public void setLng(double logitute) {

		this.logitute = logitute;
	}

	public Integer getAbundance() {
		return abundance;
	}

	public void setAbundance(Integer abundance) {
		this.abundance = abundance;
	}

}
