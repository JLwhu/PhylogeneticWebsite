<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String lat = String.valueOf(39.2483237 );
	String lng = String.valueOf(-123.1224597);

	
	lat = request.getParameter("lat");
	lng = request.getParameter("lng");
%>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    </style>
    <script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh2FFSMsHIVaM-ZzZiXTC3D1BviMDPoPg&sensor=false">
    </script>
    <script type="text/javascript">
    var map;
    var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
    var sUrl = 'speciesSpotRecordJson.action';
    var oRequest = new XMLHttpRequest();	
    
    if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) { /*ignore*/ }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
    }  
    
    function initialize() {
      var mapOptions = {
        zoom: 2,
        center: new google.maps.LatLng(<%=lat%>,<%=lng%>),
        mapTypeId: google.maps.MapTypeId.ROADMAP//google.maps.MapTypeId.TERRAIN
      };
      map = new google.maps.Map(document.getElementById('map_canvas'),
            mapOptions);

     
     
     if(bXmlHttpSupport) {

         
         oRequest.onreadystatechange = function() {
             if(oRequest.readyState == 4) {      
                 var oSpecies = JSON.parse(eval('(' + oRequest.responseText + ')'));
                 //alert(oSpecies.speciesRecord.length);
           //      var speciesReocrd = document.getElementById('abc'); 
               //  alert(speciesReocrd.toString());
           //      var sSpecies = '<th width="20%">commonName' + oSpecies.speciesRecord[0].commonName + '</th>';
            //     sSpecies += ('<th width="25%">scientificName' + oSpecies.speciesRecord[0].scientificName + '</th>');
            //     sSpecies += ('<th width="25%">SpotDate' + oSpecies.speciesRecord[0].spotdate + '</th>');
            //     sSpecies += ('<th width="15%">lng' + oSpecies.speciesRecord[0].lng + '</th>');
           //      sSpecies += ('<th width="15%">lat' + oSpecies.speciesRecord[0].lat + '</th>');

           //      speciesReocrd.innerHTML = sSpecies;
            //     alert(speciesReocrd);
            
            
                     for (var i = 0; i < oSpecies.speciesRecord.length; i++) {
             var lat = oSpecies.speciesRecord[i].lat;
             var lng = oSpecies.speciesRecord[i].lng;
             var latLng = new google.maps.LatLng(lat,lng);
             var marker = new google.maps.Marker({
               position: latLng,
               map: map
             });
           }
             }
         };
         oRequest.open('POST', sUrl);
         oRequest.send(null);
         
         

     }
    }

   
    </script>

  </head>
  <body onload="initialize()">
  
 
    <div id="map_canvas" style="width:100%; height:80%"></div>
     <s:form action="showHotspot" method="POST"
		enctype="multipart/form-data">
		<s:textfield label="Input Hotspot LocID" name="locid" />
		<s:submit />
	</s:form>
 
  </body>
</html>