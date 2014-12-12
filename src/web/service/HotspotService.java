package web.service;

import web.dao.HotspotDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.Hotspot;

public interface HotspotService extends BaseService {
	
	
	public HotspotDao getHotspotDao();
	public void setHotspotDao(HotspotDao hotspotDao);
	public Hotspot getHotspotByLocId(String locid) throws ServiceException, DaoException;

}
