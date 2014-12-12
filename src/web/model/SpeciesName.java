

	package web.model;

	//import java.io.Serializable;
	 

	public class SpeciesName{  // implements Serializable
	     
	   // private static final Integer serialVersionUID = -8767337896773261247L;
	 
	    private Integer speciesid;
	    private String genus;
	    private String scientificName;
	    private String commonName;
	    
	 

	    public Integer getSpeciesid() {
	        return speciesid;
	    }
	    public void setSpeciesid(Integer speciesid) {
	        this.speciesid = speciesid;
	    }

	    public String getGenus() {
	        return genus;
	    }
	    public void setGenus(String genus) {
	        this.genus = genus;
	    }
	    public String getScientificName() {
	        return scientificName;
	    }
	    public void setScientificName(String scientificName) {
	        this.scientificName = scientificName;
	    }

	    public String getCommonName() {
	        return commonName;
	    }
	    public void setCommonName(String commonName) {
	        this.commonName = commonName;
	    }

	}
