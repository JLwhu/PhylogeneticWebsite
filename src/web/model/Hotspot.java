/*
 * @author JingLiu
 * @version 1.0
 * @date Jan 14, 2013
 */
package web.model;

import javax.persistence.Entity;
import javax.persistence.Table;



// TODO: Auto-generated Javadoc
/**
 * The Class Hotspot.
 */
public class Hotspot  implements java.io.Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The locid. */
	private String locid;
	
	/** The countrycode. */
	private String countrycode;
	
	/** The subnational1code. */
	private String subnational1code;
	
	/** The subnational2code. */
	private String subnational2code;
	
	/** The lat. */
	private String lat;
	
	/** The lng. */
	private String lng;
	
	/** The locname. */
	private String locname;
	
	/**
	 * Gets the locid.
	 *
	 * @return the locid
	 */
	public String getLocid() {
		return locid;
	}

	/**
	 * Sets the locid.
	 *
	 * @param locid the new locid
	 */
	public void setLocid(String locid) {
		this.locid = locid;
	}
	
	/**
	 * Gets the countrycode.
	 *
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	}

	/**
	 * Sets the countrycode.
	 *
	 * @param countrycode the new countrycode
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	/**
	 * Gets the subnational1code.
	 *
	 * @return the subnational1code
	 */
	public String getSubnational1code() {
		return subnational1code;
	}

	/**
	 * Sets the subnational1code.
	 *
	 * @param subnational1code the new subnational1code
	 */
	public void setSubnational1code(String subnational1code) {
		this.subnational1code = subnational1code;
	}
	
	/**
	 * Gets the subnational2code.
	 *
	 * @return the subnational2code
	 */
	public String getSubnational2code() {
		return subnational2code;
	}

	/**
	 * Sets the subnational2code.
	 *
	 * @param subnational2code the new subnational2code
	 */
	public void setSubnational2code(String subnational2code) {
		this.subnational2code = subnational2code;
	}

	/**
	 * Gets the lat.
	 *
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * Sets the lat.
	 *
	 * @param lat the new lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	/**
	 * Gets the lng.
	 *
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * Sets the lng.
	 *
	 * @param lng the new lng
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	/**
	 * Gets the locname.
	 *
	 * @return the locname
	 */
	public String getLocname() {
		return locname;
	}
	
	/**
	 * Sets the locname.
	 *
	 * @param locname the new locname
	 */
	public void setLocname(String locname) {
		this.locname = locname;
	}

	

}
