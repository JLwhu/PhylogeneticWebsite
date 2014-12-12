package web.util;
import java.util.ArrayList;
import java.util.List;

/**
 * page seperator
 * @author lj
 * @version 1.0
 * @since   JDK1.5.0
 * 
 */
public class Page {
	public final static int DEFUALT_PAGE_SIZE=50;
    private int curPage     = 0;    //当�?页　从１开始
    private int pageSize    = 50;   //以�?页的数�?�个数    
    private int maxRowCount = 0;    //总记录数
    private int maxPage = 0;        //最大页数
    private List list = null;       //当�?记录列表
    private String url=null;
    private String paramNames=null;
    private String paramValues=null;

    /**
     * 
     * @param curPage
     * @param pageSize
     */
    public Page(int curPage, int pageSize){
    	this.curPage  = curPage;
    	this.pageSize = pageSize;
    }
    
    /**
     * @param list list of page
     * @param maxRowCount
     * @param curPage
     * @param pageSize
     */
    public Page(List list, int maxRowCount, int curPage, int pageSize) {
        this.maxRowCount = maxRowCount;
        this.pageSize    = pageSize;
        
        this.maxPage = (this.maxRowCount + pageSize - 1) / pageSize;
        
        if (curPage < 1)
        	this.curPage = 1;
        else if (curPage > this.maxPage)
            this.curPage = this.maxPage;
        else
        	this.curPage = curPage;
        
        this.list = list;
    }

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getMaxRowCount() {
		return maxRowCount;
	}

	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public String getParamNames() {
		return paramNames;
	}

	public void setParamNames(String paramNames) {
		this.paramNames = paramNames;
	}

	public String getParamValues() {
		return paramValues;
	}

	public void setParamValues(String paramValues) {
		this.paramValues = paramValues;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static void setPageInfo(List list,Page page){
		int len=list.size();
		page.setMaxRowCount(len);
		page.setMaxPage((len-1)/page.getPageSize()+1);
		if(page.getCurPage()>page.getMaxPage())page.setCurPage(page.getMaxPage());
		int curRow=(page.getCurPage()-1)*page.getPageSize()+1;
		List list2=new ArrayList();
		for(int i=curRow;i<curRow+page.getPageSize()&&i<=page.getMaxRowCount();i++){
			list2.add(list.get(i-1));
		}
		page.setList(list2);
	}
}
