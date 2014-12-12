package web.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="speciesrecords") 
public class SpeciesSpotRecord implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer recordid;

	private String recordName;

	private Integer speciesid;
	// private String scientificName;
	// private String commonName;

	private double lat;
	private double lng;
	private Date spotDate;
	private SpeciesName speciesName;
	private Integer abundance;

	public Integer getRecordid() {
		return recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	
	 public Integer getSpeciesid() { return speciesid; }
	 
	 
	 public void setSpeciesid(Integer speciesid) { this.speciesid = speciesid;
	  }


	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Date getSpotDate() {
		return spotDate;
	}

	public void setSpotDate(Date spotDate) {
		this.spotDate = spotDate;
	}

	/*public SpeciesSpotRecord() {
	}
	public SpeciesSpotRecord(Integer recordid,String recordName,double lat, double lng, Date spotDate,SpeciesName speciesName) {
		this.recordid = recordid;
		this.recordName = recordName;
		this.lat = lat;
		this.lng = lng;
		this.spotDate = spotDate;
		this.speciesName = speciesName;
	}*/

	/*
	 * public String getScientificName() { return scientificName; } public void
	 * setScientificName(String scientificName) { this.scientificName =
	 * scientificName; }
	 * 
	 * public String getCommonName() { return commonName; }
	 * 
	 * public void setCommonName(String commonName) { this.commonName =
	 * commonName; }
	 */

	public SpeciesName getSpeciesName() {
		return this.speciesName;
	}

	public void setSpeciesName(SpeciesName speciesName) {
		this.speciesName = speciesName;
	}
	
	public Integer getAbundance() {
		return abundance;
	}

	public void setAbundance(Integer abundance) {
		this.abundance = abundance;
	}
}
