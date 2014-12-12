
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>MarkerClusterer Example </title>
   <script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh2FFSMsHIVaM-ZzZiXTC3D1BviMDPoPg&sensor=false">
    <script type="text/javascript" src="data.json"></script>
    <script type="text/javascript">
      document.write('<script type="text/javascript" src="../../js/markerclusterer' + (document.location.search.indexOf('packed') > -1 ? '_packed' : '') + '.js"><' + '/script>');
    </script>
    <script type="text/javascript">
      function initialize() {
        if(GBrowserIsCompatible()) {
        	
        	var center = new google.maps.LatLng(37.4419, -122.1419);
        	var options = {
        	  'zoom': 13,
        	  'center': center,
        	  'mapTypeId': google.maps.MapTypeId.ROADMAP
        	};

        	var map = new google.maps.Map(document.getElementById("map"), options);

        	var markers = [];
        	for (var i = 0; i < 100; i++) {
        	  var latLng = new google.maps.LatLng(data.photos[i].latitude,
        	      data.photos[i].longitude);
        	  var marker = new google.maps.Marker({'position': latLng});
        	  markers.push(marker);
        	}
        	var markerCluster = new MarkerClusterer(map, markers);

        }
      }
    </script>
  </head>
  <body onload="initialize()" >
    <h3>A simple example of MarkerClusterer (100 markers)</h3>
    <p><a href="?packed">Packed</a>&nbsp;|&nbsp;<a href="?">Unpacked</a> version of the script.</p>
    <div id="map" style="width:600px;height:400px;"></div>
  </body>
</html>