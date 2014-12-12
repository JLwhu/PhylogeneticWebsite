package web.dao;

import web.exception.DaoException;
import web.model.Hotspot;

public interface HotspotDao extends BaseDao{  

	public Hotspot getHotspotByLocid(String locid) throws DaoException ;

	}