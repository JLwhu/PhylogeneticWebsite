package web.service;

import java.util.List;

import web.exception.ServiceException;
import web.model.Operatelog;
import web.util.Page;

public interface SysLogService {
	public Page findSysLog(Page page) throws ServiceException;
	public Page findSysLogSearch(Page page, String username, String startDate,String endStart, String operatetype) throws ServiceException;
	public void saveSysLog(Operatelog olog) throws ServiceException;
	public void deleteSysLog(Long logId) throws ServiceException;
	public void delteSysLog() throws ServiceException;
	public String getMaxID() throws ServiceException;
	/**
	 *
	 */
	//public List findLogByCon(String hql, String[] properties, Object[] values, Page page, String orderbyStr) throws ServiceException;

}
