package web.dao;

import java.util.List;

import web.exception.DaoException;
import web.util.Page;

public interface SysLogDao extends BaseDao {
	public List findSysLog(Page page) throws DaoException;
	public List findSysLogSearch(Page page,String username,String startDate,String endStart,String opertypeName) throws DaoException;
	public void deleteSysLog(Long logId) throws DaoException;
}
