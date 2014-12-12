package web.service.impl;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.HotspotDao;
import web.exception.DaoException;
import web.exception.ServiceException;
import web.model.Hotspot;
import web.service.BaseService;
import web.service.HotspotService;

public class HotspotServiceImpl  extends BaseServiceImpl implements HotspotService{
	private HotspotDao hotspotDao;
	private Log log = LogFactory.getLog(HotspotServiceImpl.class);

	public HotspotDao getHotspotDao() {
		return hotspotDao;
	}
	public void setHotspotDao(HotspotDao hotspotDao) {
		this.hotspotDao = hotspotDao;
	}
	
	public Hotspot getHotspotByLocId(String locid) throws ServiceException, DaoException{
		Hotspot hotspot =  hotspotDao.getHotspotByLocid(locid);
		return hotspot;
	}

	
}
