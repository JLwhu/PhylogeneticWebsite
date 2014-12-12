<%
	//String resultJSON ;
	//resultJSON = request.getParameter("resultJSON");
	//System.out.print(resultJSON);
	String options;
	options = request.getParameter("options");
	String url = "subtreeMap.action?options="+options;
	
	if (options.equals("ByYear")){
		String year= request.getParameter("year");
		url=url+"&year="+year;			
	}else if (options.equals("ByMonth")){
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		url=url+"&startMonth="+startMonth;
		url=url+"&endMonth="+endMonth;
	}else if (options.equals("ByDate")){
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		url=url+"&startDate="+startDate;
		url=url+"&endDate="+endDate;
	}else if (options.equals("ByLocation")){
		String lat= request.getParameter("lat");
		String lng= request.getParameter("lng");
		String diameter= request.getParameter("diameter");
		url=url+"&lat="+lat;	
		url=url+"&lng="+lng;
		url=url+"&diameter="+diameter;
	}else if (options.equals("ByYearMonth")){
		String year= request.getParameter("year");
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		url=url+"&year="+year;	
		url=url+"&startMonth="+startMonth;
		url=url+"&endMonth="+endMonth;
	}else if (options.equals("ByYearLocation")){
		String year= request.getParameter("year");
		String lat= request.getParameter("lat");
		String lng= request.getParameter("lng");
		String diameter= request.getParameter("diameter");
		url=url+"&year="+year;
		url=url+"&lat="+lat;	
		url=url+"&lng="+lng;
		url=url+"&diameter="+diameter;		
	}else if (options.equals("ByMonthLocation")){
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		url=url+"&startMonth="+startMonth;
		url=url+"&endMonth="+endMonth;
		
		String lat= request.getParameter("lat");
		String lng= request.getParameter("lng");
		String diameter= request.getParameter("diameter");
		url=url+"&lat="+lat;	
		url=url+"&lng="+lng;
		url=url+"&diameter="+diameter;
	}else if (options.equals("ByYearMonthLocation")){
		String year= request.getParameter("year");
		url=url+"&year="+year;	
		
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		url=url+"&startMonth="+startMonth;
		url=url+"&endMonth="+endMonth;
		
		String lat= request.getParameter("lat");
		String lng= request.getParameter("lng");
		String diameter= request.getParameter("diameter");
		url=url+"&lat="+lat;	
		url=url+"&lng="+lng;
		url=url+"&diameter="+diameter;

	}else if (options.equals("ByDateLocation")){
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		url=url+"&startDate="+startDate;
		url=url+"&endDate="+endDate;
		
		String lat= request.getParameter("lat");
		String lng= request.getParameter("lng");
		String diameter= request.getParameter("diameter");
		url=url+"&lat="+lat;	
		url=url+"&lng="+lng;
		url=url+"&diameter="+diameter;
	}
	String namechoice = request.getParameter("nameChoice");
	url=url+"&nameChoice="+namechoice;
//	System.out.print(url+"***");
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <META HTTP-EQUIV="Refresh" CONTENT="0;URL=<%=url%>>">
</head>

<body>
<p>Loading ...</p>
</body>
</html>
