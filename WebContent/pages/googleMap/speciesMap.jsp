<%@ include file="/pages/include/head.jsp"%>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<link rel="stylesheet" href="../../js/jquery-ui.css" />
<script src="../../js/jquery-1.8.3.js"></script>
<script src="../../js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#slider-monthrange").slider({
			range : true,
			min : 1,
			max : 12,
			step : 1,
			values : [ 3, 6 ],
			slide : function(event, ui) {
				$("#Month").val(ui.values[0] + " - " + ui.values[1]);
			}
		});
		$("#Month").val(
				$("#slider-monthrange").slider("values", 0) + " - "
						+ $("#slider-monthrange").slider("values", 1));
	});
</script>
<script>
	$(function() {
		$("#startDatepicker").datepicker();
		$("#endDatepicker").datepicker();
	});
</script>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh2FFSMsHIVaM-ZzZiXTC3D1BviMDPoPg&sensor=false">
	
</script>
<script type="text/javascript" src="../../js/markerclusterer.packed.js"></script>
<script type="text/javascript" src="data.json"></script>
<script type="text/javascript">
	var map;
	var markerList = new Array();
	var sUrl = "";
	var choice = "";
	var lat;
	var lng;
	var diameter;
	var pinpoint;
	var circle;
	var options;

	function initialize() {
		var mapOptions = {
			zoom : 7,
			center : new google.maps.LatLng(40, -100),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		//google.maps.MapTypeId.TERRAIN
		};
		map = new google.maps.Map(document.getElementById('map_canvas'),
				mapOptions);
	}

	function getOptions() {
		sUrl = "";
		options = "";

		var year = document.getElementsByName("year")[0].value;
		var month = document.getElementsByName("Month")[0].value;
		var index = month.indexOf("-");
		var startMonth = month.substring(0, index - 1);
		var endMonth = month.substring(index + 2);
		var startDate = document.getElementsByName("startDate")[0].value;//=date.substring(0,index-1);
		var endDate = document.getElementsByName("endDate")[0].value;//=month.substring(index+2);

		var isYear = false;
		var isMonth = false;
		var isDate = false;
		var isLocation = false;
		var isSpecies = false;
		var oSpecies;

		if ($('input:checkbox[name=byYear]').is(':checked')) {
			isYear = true;
		}
		if ($('input:checkbox[name=byMonth]').is(':checked')) {
			isMonth = true;
		}
		if ($('input:checkbox[name=byDate]').is(':checked')) {
			isDate = true;
		}
		if ($('input:checkbox[name=byLocation]').is(':checked')) {
			isLocation = true;
		}

		if (isYear && !isMonth && !isDate && !isLocation) {
			if (year) {
				options = "ByYear";
				sUrl = 'speciesSpotRecordJsonByYear.action?year=' + year;
			} else {
				alert("Please Choose Year!");
			}
		} else if ((!isYear && isMonth && !isDate && !isLocation)) {
			if (month) {
				options = "ByMonth";
				sUrl = 'speciesSpotRecordJsonByMonth.action' + '?startMonth='
						+ startMonth + '&endMonth=' + endMonth;
			} else {
				alert("Please Choose Month!");
			}
		} else if ((!isYear && !isMonth && isDate && !isLocation)) {
			if (startDate && endDate) {
				options = "ByDate";
				sUrl = 'speciesSpotRecordJsonByPeriod.action?startDate='
						+ startDate + '&endDate=' + endDate;
			} else {
				alert("Please Input Date!");
			}
		} else if ((!isYear && !isMonth && !isDate && isLocation)) {
			options = "ByLocation";
			sUrl = 'speciesSpotRecordJsonByLocation.action?lat=' + lat
					+ '&lng=' + lng + '&diameter=' + diameter;
		} else if ((isYear && isMonth && !isDate && !isLocation)) {
			if (year && month) {
				options = "ByYearMonth";
				sUrl = 'speciesSpotRecordJsonByYearMonth.action?year=' + year
						+ '&startMonth=' + startMonth + '&endMonth=' + endMonth;
			} else {
				alert("Please Choose Year & Month!");
			}
		} else if ((isYear && !isMonth && !isDate && isLocation)) {
			if (year && month) {
				options = "ByYearLocation";
				sUrl = 'speciesSpotRecordJsonByYearLocation.action?lat=' + lat
						+ '&lng=' + lng + '&diameter=' + diameter + '&year='
						+ year;
			} else {
				alert("Please Choose Year!");
			}
		} else if ((!isYear && isMonth && !isDate && isLocation)) {
			if (month) {
				options = "ByMonthLocation";
				sUrl = 'speciesSpotRecordJsonByMonthLocation.action?lat=' + lat
						+ '&lng=' + lng + '&diameter=' + diameter
						+ '&startMonth=' + startMonth + '&endMonth=' + endMonth;
			} else {
				alert("Please Choose Month!");
			}
		} else if ((isYear && isMonth && !isDate && isLocation)) {
			if (year && month) {
				options = "ByYearMonthLocation";
				sUrl = 'speciesSpotRecordJsonByYearMonthLocation.action?lat='
						+ lat + '&lng=' + lng + '&diameter=' + diameter
						+ '&year=' + year + '&startMonth=' + startMonth
						+ '&endMonth=' + endMonth;
			} else {
				alert("Please Choose Year & Month!");
			}
		} else if ((!isYear && !isMonth && isDate && isLocation)) {
			if (startDate && endDate) {
				options = "ByDateLocation";
				sUrl = 'speciesSpotRecordJsonByPeriodLocation.action?lat='
						+ lat + '&lng=' + lng + '&diameter=' + diameter
						+ '&startDate=' + startDate + '&endDate=' + endDate;
			} else {
				alert("Please Input Date!");
			}
		}
	}

	function drawpoints() {

		var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
		var oRequest = new XMLHttpRequest();

		clearAllpoints();

		getOptions();
		//	alert(sUrl);
		if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
			function XMLHttpRequest() {
				var arrSignatures = [ "MSXML2.XMLHTTP.5.0",
						"MSXML2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0",
						"MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];

				for ( var i = 0; i < arrSignatures.length; i++) {
					try {
						var oRequest = new ActiveXObject(arrSignatures[i]);
						return oRequest;
					} catch (oError) { /*ignore*/
					}
				}

				throw new Error("MSXML is not installed on your system.");
			}
		}

		if (bXmlHttpSupport) {
			oRequest.onreadystatechange = function() {
				if (oRequest.readyState == 4) {
					oSpecies = JSON.parse(eval('(' + oRequest.responseText
							+ ')'));
					var image = '../../images/marker.png';
					alert(oSpecies.speciesRecord.length);
					for ( var i = 0; i < oSpecies.speciesRecord.length; i++) {

						var lat = oSpecies.speciesRecord[i].lat;
						var lng = oSpecies.speciesRecord[i].lng;
						var latLng = new google.maps.LatLng(lat, lng);
						//	markerList[i] = latLng;

						//	markerList[i] = new google.maps.LatLng(lat, lng);
						//	var marker = new GMarker(latlng, {icon: icon});

						var marker = new google.maps.Marker({
							position : latLng,
							map : map,
							icon : image//,
						//	title : oSpecies.speciesRecord[i].scientificName
						});
						markerList.push(marker);

					}

				}
			};
			oRequest.open('POST', sUrl);
			oRequest.send(null);
		}

	}

	function clearAllpoints() {
		for ( var i = 0; i < markerList.length; i++) {
			markerList[i].setMap(null);
		}
		markerList = new Array();
	}

	function drawCircle() {
		if ($('input:checkbox[name=byLocation]').is(':checked')) {
			// Create a draggable marker which will later on be binded to a
			// Circle overlay.
			var image = '../../images/marker.png';
			pinpoint = new google.maps.Marker({
				map : map,
				//icon: image,
				position : new google.maps.LatLng(40, -100),
				draggable : true,
				title : 'Drag me!'
			});

			// Add a Circle overlay to the map.
			circle = new google.maps.Circle({
				///center: marker,
				map : map,
				radius : 30000, // 300 km
				editable : true,
				visble : true

			});

			// Since Circle and Marker both extend MVCObject, you can bind them
			// together using MVCObject's bindTo() method.  Here, we're binding
			// the Circle's center to the Marker's position.
			// http://code.google.com/apis/maps/documentation/v3/reference.html#MVCObject
			circle.bindTo('center', pinpoint, 'position');
			diameter = circle.getRadius() / 100000;
			var center = circle.getCenter();
			lat = center.lat();
			lng = center.lng();

			google.maps.event.addListener(circle, 'radius_changed', function() {
				diameter = circle.getRadius() / 100000;
				center = circle.getCenter();
				lat = center.lat();
				lng = center.lng();
				//	drawpoints();
				//   alert(center.lat());
			});
			google.maps.event.addListener(circle, 'center_changed', function() { // marker, 'dragend', function() {
				diameter = circle.getRadius() / 100000;
				center = circle.getCenter();
				lat = center.lat();
				lng = center.lng();
			});

		} else {
			circle.setMap(null);
			pinpoint.setMap(null);
		}

	}

	function showSpeciesTree() {
		//var resultJSON = JSON.stringify(oSpecies);
		//	alert(resultJSON);
		//	popitup("speciesTreeOnMap.jsp");
		//var show = "mapResPass.jsp?options="+options+"&"+sUrl.substring(sUrl.indexOf('?')+1);
		//alert(show);
		getOptions();

		var nameChoiceRadio = document.getElementsByName("nameChoice");
		var nameChoice;
		for ( var i = 0; i < nameChoiceRadio.length; i++) {
			if (nameChoiceRadio[0].checked) {
				nameChoice = 1;
			} else {
				nameChoice = 2;
			}
		}
		//var nameChoice = document.getElementsByName("nameChoice")[0].value;  //document.getElementById("nameChoice").value;
		//alert("mapResPass.jsp?options="+options+"&"+sUrl.substring(sUrl.indexOf('?')+1)+"&nameChoice="+nameChoice);
		clearAllOptions();
		window.location.href="mapResPass.jsp?options=" + options + "&"
		+ sUrl.substring(sUrl.indexOf('?') + 1) + "&nameChoice="
		+ nameChoice;
//		popitup("mapResPass.jsp?options=" + options + "&"
//				+ sUrl.substring(sUrl.indexOf('?') + 1) + "&nameChoice="
//				+ nameChoice);

	}

	function popitup(url) {

		newwindow = window.open(url, 'name', 'height=200,width=150');
		if (window.focus) {
			newwindow.focus()
		}
		return false;
	}
	
	function clearAllOptions() {
		
		$('input:checkbox[name=byYear]').attr('checked',false);
		$('input:checkbox[name=byMonth]').attr('checked',false);
		$('input:checkbox[name=byDate]').attr('checked',false);
		$('input:checkbox[name=byLocation]').attr('checked',false);

	}
	

	window.onload = initialize;
</script>


<div style="width: 20%; float: left">

	<p>
		<input type="checkbox" name="byYear">By Year</input>	</p>
	<p>	<s:url id="yearList" action="autoYears.action" />
		<sx:autocompleter label="Year" href="%{yearList}" name="year" />
	</p>
	<p>
		<input type="checkbox" name="byMonth">By Month</input></p>
		<p> <label
			for="Month">Month range:</label> <input type="text" name="Month"
			id="Month" style="border: 0; color: #f6931f; font-weight: bold;" />
	
	<div id="slider-monthrange"></div>
</p>

	<p>
		<input type="checkbox" name="byDate">By Date</input>	</p>
	<p>	Start Date: <input type="text" name="startDate" id="startDatepicker" />	</p>
		<p>	End Date: <input type="text" name="endDate" id="endDatepicker" /></p>
	</p>
	<p>
		<input type="checkbox" name="byLocation" id="byLocation"
			onClick="drawCircle()">By Location</input>
	</p>
			<p>
	<%--<input type="submit" id="submit" name="submit" value="Show Points"
		onclick="drawpoints()" />  

	<input type="submit" id="clear"
		name="clear" value="Clear All Points" onclick="clearAllpoints()" /> --%>
	<input type="submit" id="submit" name="submit"
			value="Show Species Tree" onclick="showSpeciesTree()" />
			</p>
	<p> 
	<s:radio label="Output Name" name="nameChoice" id="nameChoice"
			list="#{'1':'Scientific Name','2':'Common Name'}" value="1" />
		</p>


</div>



<div id="map_canvas" style="width: 78%; height: 80%; float: right"></div>



<%@ include file="/pages/include/foot.jsp"%>