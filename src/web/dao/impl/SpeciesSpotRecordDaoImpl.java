package web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import web.dao.SpeciesSpotRecordDao;
import web.exception.DaoException;
import web.model.MongoSpeciesRecord;
import web.model.SpeciesSpotRecord;

public class SpeciesSpotRecordDaoImpl extends BaseDaoImpl implements SpeciesSpotRecordDao{
	public SpeciesSpotRecord getSpeciesSpotRecordByID(Integer recordID) throws DaoException{
		Session session = getSessionFactory().openSession();
		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where recordid = ?");
			query.setInteger(0, recordID);
			query.setMaxResults(1);
			return (SpeciesSpotRecord) query.uniqueResult();
		} finally {
			this.clear(session);
		}
	};

	public List getSpeciesSpotRecordByLocation(double lat, double lng,
			double diameter) throws DaoException {
		
		
	/*	Connection connection = null;
	    try
	    {
	      // the mysql driver string
	    	
	      Class.forName("com.mysql.jdbc.Driver");
	    
	      // the mysql url
	      String url = "jdbc:mysql://localhost/phylogenetics";
	      
	      // get the mysql database connection
	      connection = DriverManager.getConnection(url,"root", "1234");
	      Statement statement = null;
	      ResultSet resultSet = null;
	      PreparedStatement preparedStatement = null;
	      // Statements allow to issue SQL queries to the database
	      long a=System.currentTimeMillis();
	     // statement = connection.createStatement();
	      // Result set get the result of the SQL query
	      preparedStatement = connection.prepareStatement("select recordid,speciesid,lat,lng,spotdate from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>?");
	      preparedStatement.setDouble(1, (lat+diameter) % 180);
	      preparedStatement.setDouble(2, (lat-diameter) % 180);
	      preparedStatement.setDouble(3, (lng+diameter) % 180);
	      preparedStatement.setDouble(4, (lng-diameter) % 180);
	      resultSet = preparedStatement.executeQuery();
	      System.out.println("\r<br>SQL excution time1 "+(System.currentTimeMillis()-a)/1000f+" seconds");
	      connection.close();
	      
	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	      System.exit(2);
	    }
		
		*/
		
		
		Session session = getSessionFactory().openSession();

		try {
			long a=System.currentTimeMillis();
		/*	Query query1 = session
					.createSQLQuery("select recordid,speciesid,lat,lng,spotdate  from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>?");
			//.addEntity(SpeciesSpotRecord.class)
			query1.setDouble(0, (lat+diameter) % 180);
			query1.setDouble(1, (lat-diameter) % 180);
			query1.setDouble(2, (lng+diameter) % 180);
			query1.setDouble(3, (lng-diameter) % 180);
			List temp1 = query1.list();
			System.out.println("\r<br>SQL excution time2 "+(System.currentTimeMillis()-a)/1000f+" seconds");	
			
			a=System.currentTimeMillis();*/
			Query query = session
					.createQuery("from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>?");
			query.setDouble(0, (lat+diameter) % 180);
			query.setDouble(1, (lat-diameter) % 180);
			query.setDouble(2, (lng+diameter) % 180);
			query.setDouble(3, (lng-diameter) % 180);
			List spotRecList = query.list();
			System.out.println("\r<br>SQL excution time "+(System.currentTimeMillis()-a)/1000f+" seconds");		

			double distance;
			for (int i=spotRecList.size()-1;i>=0;i--){
				SpeciesSpotRecord ssr = (SpeciesSpotRecord)spotRecList.get(i);
			//	distance = Math.acos(Math.sin(lat) *Math.sin(ssr.getLat()) + Math.cos(lat)*Math.cos(ssr.getLat())*Math.cos(lng - ssr.getLng()))*diameter;
				distance = Math.sqrt((ssr.getLat()-lat)*(ssr.getLat()-lat)+(ssr.getLng()-lng)*(ssr.getLng()-lng));
				if (distance >= diameter) {
					spotRecList.remove(ssr);
				}
			}
			
			
			return spotRecList;
		} finally {
			this.clear(session);
		}

	};
	
	public List getSpeciesSpotRecordByYearLocation(double lat, double lng,
			double diameter, String year) throws DaoException {
		Session session = getSessionFactory().openSession();

		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>? and YEAR(spotDate) = ?");
			query.setDouble(0, (lat+diameter) % 180);
			query.setDouble(1, (lat-diameter) % 180);
			query.setDouble(2, (lng+diameter) % 180);
			query.setDouble(3, (lng-diameter) % 180);
			query.setString(4, year);

			List spotRecList = query.list();
			double distance;
			for (int i=spotRecList.size()-1;i>=0;i--){
				SpeciesSpotRecord ssr = (SpeciesSpotRecord)spotRecList.get(i);
			//	distance = Math.acos(Math.sin(lat) *Math.sin(ssr.getLat()) + Math.cos(lat)*Math.cos(ssr.getLat())*Math.cos(lng - ssr.getLng()))*diameter;
				distance = Math.sqrt((ssr.getLat()-lat)*(ssr.getLat()-lat)+(ssr.getLng()-lng)*(ssr.getLng()-lng));
				if (distance >= diameter) {
					spotRecList.remove(ssr);
				}
			}
			return spotRecList;
		} finally {
			this.clear(session);
		}

	};
	
	public List getSpeciesSpotRecordByMonthLocation(double lat, double lng,
			double diameter, String startMonth, String endMonth) throws DaoException {
		Session session = getSessionFactory().openSession();

		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>? and MONTH(spotdate) >= ? and MONTH(spotdate) <= ?");
			query.setDouble(0, (lat+diameter) % 180);
			query.setDouble(1, (lat-diameter) % 180);
			query.setDouble(2, (lng+diameter) % 180);
			query.setDouble(3, (lng-diameter) % 180);
			query.setString(4, startMonth);
			query.setString(5, endMonth);

			List spotRecList = query.list();
			double distance;
			for (int i=spotRecList.size()-1;i>=0;i--){
				SpeciesSpotRecord ssr = (SpeciesSpotRecord)spotRecList.get(i);
			//	distance = Math.acos(Math.sin(lat) *Math.sin(ssr.getLat()) + Math.cos(lat)*Math.cos(ssr.getLat())*Math.cos(lng - ssr.getLng()))*diameter;
				distance = Math.sqrt((ssr.getLat()-lat)*(ssr.getLat()-lat)+(ssr.getLng()-lng)*(ssr.getLng()-lng));
				if (distance >= diameter) {
					spotRecList.remove(ssr);
				}
			}
			return spotRecList;
		} finally {
			this.clear(session);
		}
	};
	
	public List getSpeciesSpotRecordByYearMonthLocation(double lat, double lng,
			double diameter, String year, String startMonth, String endMonth) throws DaoException {
		Session session = getSessionFactory().openSession();

		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>? and YEAR(spotDate) = ? and MONTH(spotdate) >= ? and MONTH(spotdate) <= ?");
			query.setDouble(0, (lat+diameter) % 180);
			query.setDouble(1, (lat-diameter) % 180);
			query.setDouble(2, (lng+diameter) % 180);
			query.setDouble(3, (lng-diameter) % 180);
			query.setString(4, year);
			query.setString(5, startMonth);
			query.setString(6, endMonth);

			List spotRecList = query.list();
			double distance;
			for (int i=spotRecList.size()-1;i>=0;i--){
				SpeciesSpotRecord ssr = (SpeciesSpotRecord)spotRecList.get(i);
			//	distance = Math.acos(Math.sin(lat) *Math.sin(ssr.getLat()) + Math.cos(lat)*Math.cos(ssr.getLat())*Math.cos(lng - ssr.getLng()))*diameter;
				distance = Math.sqrt((ssr.getLat()-lat)*(ssr.getLat()-lat)+(ssr.getLng()-lng)*(ssr.getLng()-lng));
				if (distance >= diameter) {
					spotRecList.remove(ssr);
				}
			}
			return spotRecList;
		} finally {
			this.clear(session);
		}
	};
	
	public List getSpeciesSpotRecordByPeriodLocation(double lat, double lng,
			double diameter, Date startdate, Date endDate) throws DaoException {
		Session session = getSessionFactory().openSession();

		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where lat <? and lat >? and lng<? and lng>? and spotdate >= ? and spotdate<=?");
			query.setDouble(0, (lat+diameter) % 180);
			query.setDouble(1, (lat-diameter) % 180);
			query.setDouble(2, (lng+diameter) % 180);
			query.setDouble(3, (lng-diameter) % 180);
			query.setDate(4, startdate);
			query.setDate(5, endDate);

			List spotRecList = query.list();
			double distance;
			for (int i=spotRecList.size()-1;i>=0;i--){
				SpeciesSpotRecord ssr = (SpeciesSpotRecord)spotRecList.get(i);
			//	distance = Math.acos(Math.sin(lat) *Math.sin(ssr.getLat()) + Math.cos(lat)*Math.cos(ssr.getLat())*Math.cos(lng - ssr.getLng()))*diameter;
				distance = Math.sqrt((ssr.getLat()-lat)*(ssr.getLat()-lat)+(ssr.getLng()-lng)*(ssr.getLng()-lng));
				if (distance >= diameter) {
					spotRecList.remove(ssr);
				}
			}
			return spotRecList;
		} finally {
			this.clear(session);
		}
	};
	
	public List getSpeciesSpotRecordByYear(String year) throws DaoException{
		Session session = getSessionFactory().openSession();
		try {
		  //    long a=System.currentTimeMillis();
			Query query = session
					.createQuery("from SpeciesSpotRecord where YEAR(spotDate) = ?");
			query.setString(0, year);

			List<SpeciesSpotRecord> spotRecList= query.list();
			
		//	System.out.println("\r<br>SQL excution time1 "+(System.currentTimeMillis()-a)/1000f+" seconds");
		/*	List<SpeciesSpotRecord> spotRecList = new ArrayList <SpeciesSpotRecord> ();
			for (Iterator it = query.iterate(); it.hasNext();) {
				SpeciesSpotRecord speciesSpotRecord = (SpeciesSpotRecord) it.next(); 
				spotRecList.add(speciesSpotRecord);
			}*/
			return spotRecList;
		} finally {
			this.clear(session);
		}
		
	};
	public List getSpeciesSpotRecordByYearMonth(String year,String startMonth, String endMonth) throws DaoException{
		Session session = getSessionFactory().openSession();
		try {

			Query query = session
					.createQuery("from SpeciesSpotRecord where YEAR(spotDate) = ? and MONTH(spotdate) >= ? and MONTH(spotdate) <= ?");
			query.setString(0, year);
			query.setString(1, startMonth);
			query.setString(2, endMonth);

			List<SpeciesSpotRecord> spotRecList= query.list();
			return spotRecList;
		} finally {
			this.clear(session);
		}
		
	};
	public List getSpeciesSpotRecordByMonth(String month) throws DaoException{
		Session session = getSessionFactory().openSession();
		
		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where MONTH(spotdate) = ?");
			query.setString(0, month);
			
			List spotRecList = query.list();
			return spotRecList;
		} finally {
			this.clear(session);
		}
		
	};
	public List getSpeciesSpotRecordByMonthRange(String startMonth,String endMonth) throws DaoException{
		Session session = getSessionFactory().openSession();
		
		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where MONTH(spotdate) >= ? and MONTH(spotdate) <= ?");
			query.setString(0, startMonth);
			query.setString(1, endMonth);
			
			List spotRecList = query.list();
			return spotRecList;
		} finally {
			this.clear(session);
		}
		
	};
	public List getSpeciesSpotRecordByPeriod(Date startdate, Date endDate) throws DaoException{
		Session session = getSessionFactory().openSession();
		
		try {
			Query query = session
					.createQuery("from SpeciesSpotRecord where spotdate >= ? and spotdate<=?");
			query.setDate(0, startdate);
			query.setDate(1, endDate);
			
			List spotRecList = query.list();
			return spotRecList;
		} finally {
			this.clear(session);
		}
		
	};
	
	public List getSpeciesSpotYearList() throws DaoException{
		Session session = getSessionFactory().openSession();
		
		try {
			Query query = session
					.createSQLQuery("select distinct Year(spotdate) from SpeciesSpotRecord order by Year(spotdate)");
			
			List yearList = query.list();
			return yearList;
		} finally {
			this.clear(session);
		}
		
	};
	
	public void saveSpeciesSpotRecord(SpeciesSpotRecord sr) throws DaoException{
		Session session = getSessionFactory().openSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(sr);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			this.clear(session);
		}

	}


}
