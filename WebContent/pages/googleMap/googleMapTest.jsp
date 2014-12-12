<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7]>         <html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8]>         <html class="ie ie8" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en">         <!--<![endif]-->
<head>
<title>Real&#45;time Earthquake Map</title>
<!-- 130204,212040,3 -->
	<script type="text/javascript" src="/template/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="/template/js/master_20120330.js"></script>
<link rel="alternate" type="application/atom+xml" href="/earthquakes/feed/atom/4.5/week"/><script type="text/javascript" src="/template/widgets/ui/ui.js"></script><meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords" content="aftershock,earthquake,epicenter,fault,foreshock,geologist,geophysics,hazard,hypocenter,intensity,intensity scale,magnitude,magnitude scale,mercalli,plate,richter,seismic,seismicity,seismogram,seismograph,seismologist,seismology,subduction,tectonics,tsunami,quake,sismologico,sismologia"/>
<meta name="google-site-verification" content="5Bw3LIEvwx8C0JA5hWKosAweW7kXIw0LS5OR438MOqU" />
<meta name="description" content="USGS Earthquake Hazards Program, responsible for monitoring, reporting, and researching earthquakes and earthquake hazards"/>

<!--[if lt IE 7]>
<link rel="stylesheet" href="/template/css/ietextonly.css" type="text/css" media="all" />
<![endif]-->

<!--[if gte IE 7]><!-->
	<link rel="stylesheet" type="text/css" media="all" href="/template/css/reset.css"/>
	<link rel="stylesheet" type="text/css" media="all" href="/template/css/master.css"/>
<!--<![endif]-->

<link rel="stylesheet" media="print" type="text/css" href="/template/css/print.css" />

<!--[if gte IE 7]><!--><link rel="stylesheet" type="text/css" href="/earthquakes/map/compiled_20130103213230.css"/><link rel="stylesheet" type="text/css" href="/template/widgets/ui/ui.css"/><link rel="stylesheet" type="text/css" href="/template/widgets/dateTime/dateTime.css"/><!--<![endif]--></head>

<body>

<div id="header"><div id="usgsbanner"><div>
	<a href="http://www.usgs.gov/"><img src="/template/images/usgs.jpg" alt="USGS - science for a changing world" title="U.S. Geological Survey Home Page" /></a>
	<ul>
		<li><a href="http://www.usgs.gov/">USGS Home</a></li>
		<li><a href="http://www.usgs.gov/ask/">Contact USGS</a></li>
		<li><a href="http://search.usgs.gov/">Search USGS</a></li>
	</ul>
</div></div>

<div id="usgstitle"><div>
	<p><a href="/">Earthquake Hazards Program</a></p>
	<ul id="utilities">
	<li class="invisiblelink"><a href="#startcontent" class="invisiblelink">Skip to main content</a></li>
<li><a href="/">Home</a></li><li><a href="/aboutus/">About Us</a></li><li><a href="/contactus/regional.php">Contact Us</a></li>	<li id="search">
		<form method="get" action="http://www.google.com/search" id="header-search-form" target="_blank" class="search-form">
			<span class="search-wrap">
				<input type="text" name="q" size="10" maxlength="50" title="Search Text" class="search-text" />
				<input type="image" src="/template/images/sbox_button.gif" alt="Search" class="search-button" />
			</span>
			<input type="hidden" name="q" value="site:earthquake.usgs.gov" class="search-siteonly" />
		</form>
	</li>
	</ul>
</div></div>
</div>

<hr />
<div id="sitenav"><ul><li><strong><a href="/earthquakes/?source=sitenav">Earthquakes</a></strong></li><li><a href="/hazards/?source=sitenav">Hazards</a></li><li><a href="/learn/?source=sitenav">Learn</a></li><li><a href="/prepare/?source=sitenav">Prepare</a></li><li><a href="/monitoring/?source=sitenav">Monitoring</a></li><li><a href="/research/?source=sitenav">Research</a></li></ul>
</div>
<hr />

<div id="content">
  <a id="startcontent"></a>

  <h1>Real&#45;time Earthquake Map</h1>


<script type="text/javascript">/*<![CDATA[*/
	// Show a loading dialog box.
	var dialog = $([
		'<div>',
			'<img src="/images/loadingbar.gif" alt="Loading..."/>',
			'<p style="margin:0;padding:0;font-size:.95em;color:#666666;">',
				'Loading earthquake data&hellip;',
			'</p>',
		'</div>'
	].join('')).dialog({
		title: 'Initializing Application&hellip;',
		modal: true,
		width: '235px',
		height: '50px'
	});
/*]]>*/</script>

<div class="logos">
	<a class="logo" href="/monitoring/anss/"
		><img src="images/anss_mini.jpg" alt="ANSS Logo" title="ANSS Logo"/></a>
	<a class="logo" href="/monitoring/gsn/"
		><img src="images/gsn_mini.jpg" alt="GSN Logo" title="GSN Logo" /></a>
</div>



<div id="application">
<p>
	<em>To use this earthquake mapping interface, you must enable javascript</em>,
	otherwise <a href="/earthquakes/feed/">use our earthquake feeds instead</a>.
</p>
</div>


<p class="disclaimer clear right">
Old version:
<a href="/earthquakes/recenteqsww/">World</a>
<a href="/earthquakes/recenteqsus/">US</a>
<a href="/earthquakes/recenteqscanv/"><abbr title="California/Nevada">CA/NV</abbr></a>
</p>
</div>

<hr />

<div id="content_footer">


<div id="share">
  <p>Share this page:</p>
  <ul>
    <li class="facebook"><a class="offsite" rel="nofollow" href="http://www.facebook.com/sharer.php?t=Real%26%2345%3Btime+Earthquake+Map&amp;u=http%3A%2F%2Fearthquake.usgs.gov%2Fearthquakes%2Fmap%2F" title="Share on Facebook">Facebook</a></li>    <li class="twitter"><a class="offsite" rel="nofollow" href="http://twitter.com/share?text=Real%26%2345%3Btime+Earthquake+Map+%28via+%40usgs%29&amp;url=http%3A%2F%2Fearthquake.usgs.gov%2Fearthquakes%2Fmap%2F" title="Share on Twitter">Twitter</a></li>    <li class="googlemarks"><a class="offsite" rel="nofollow" href="https://www.google.com/bookmarks/mark?op=add&amp;title=Real%26%2345%3Btime+Earthquake+Map&amp;annotation=&amp;nui=1&amp;service=bookmarks&amp;bkmk=http%3A%2F%2Fearthquake.usgs.gov%2Fearthquakes%2Fmap%2F" title="Share on Google">Google</a></li>    <li class="email"><a class="offsite" rel="nofollow" href="mailto:?subject=Real%26%2345%3Btime%20Earthquake%20Map&amp;body=http%3A%2F%2Fearthquake.usgs.gov%2Fearthquakes%2Fmap%2F" title="Share on Email">Email</a></li>  </ul>
</div>

</div>
<hr />

<div id="sitemap">
  <ul>
    <li>
      <a href="/earthquakes/?source=sitemap" class="first">Earthquakes</a>
      <ul>
        <li><a href="/earthquakes/map/">Real&#45;time Earthquakes</a></li>
        <li><a href="/earthquakes/feed/">Get Real&#45;time Data Sent to You</a></li>
        <li><a href="/earthquakes/dyfi/">Did You Feel It?</a></li>
        <li><a href="/earthquakes/eqinthenews/">Significant EQ Archive</a></li>
        <li><a href="/earthquakes/eqarchives/epic/">Search EQ Archives</a></li>
        <li><a href="/earthquakes/eqarchives/">&ldquo;Top 10&rdquo; Lists &amp; Maps</a></li>
        <li><a href="/earthquakes/region.php">Info by Region</a></li>
        <li><a href="/earthquakes/states/seismicity/">US Seismicity Map</a></li>
        <li><a href="/earthquakes/world/seismicity_maps/">World Seismicity Maps</a></li>
      </ul>
    </li>
    <li>
      <a href="/hazards/?source=sitemap">Hazards</a>
      <ul>
        <li><a href="/hazards/qfaults/">Faults</a></li>
        <li><a href="/hazards/products/">Hazard Maps &amp; Data</a></li>
        <li><a href="/hazards/designmaps/">Seismic Design</a></li>
        <li><a href="/hazards/apps/">Hazard Analysis Tools</a></li>
        <li><a href="/hazards/products/scenario/">EQ Scenarios</a></li>
      </ul>
    </li>
    <li>
      <a href="/learn/?source=sitemap">Learn</a>
      <ul>
        <li><a href="/learn/topics/">EQ Topics for Education</a></li>
        <li><a href="http://www.usgs.gov/faq/index.php?action=show&amp;cat=113"><abbr title="Frequently Asked Questions">FAQ</abbr></a></li>
        <li><a href="/learn/glossary/">EQ Glossary</a></li>
        <li><a href="/learn/kids/">For Kids</a></li>
        <li><a href="/learn/kml.php">Google Earth/KML Files</a></li>        
        <li><a href="/earthquakes/eqarchives/poster/">EQ Summary Posters</a></li>
        <li><a href="/learn/photos.php">Photos</a></li>
        <li><a href="/learn/publications/">Publications</a></li>
      </ul>
    </li>
    <li>
      <a href="/prepare/?source=sitemap">Prepare</a>
      <ul>
        <li><a href="/prepare/">How do I prepare?</a></li>
        <li><a href="http://www.shakeout.org/">Great ShakeOut Drills</a></li>
        <li><a href="http://urbanearth.usgs.gov/">Multi-Hazards Project</a></li>
      </ul>
    </li>
    <li>
      <a href="/monitoring/?source=sitemap">Monitoring</a>
      <ul>
        <li><a href="/regional/neic/">NEIC</a></li>
        <li><a href="/monitoring/anss/"><abbr title="Advanced National Seismic System">ANSS</abbr> &ndash; United States</a></li>
        <li><a href="/monitoring/gsn/"><abbr title="Global Seismic Network">GSN</abbr> &ndash; World</a></li>
        <li><a href="/monitoring/netquakes/">Volunteer Monitoring</a></li>
        <li><a href="/regional/asl/"><abbr title="Albuquerque Seismo Lab">ASL</abbr> &ndash; Albuquerque</a></li>
        <li><a href="/monitoring/netquakes/">Volunteer Monitoring</a></li>
        <li><a href="/monitoring/operations/">Network Operations</a></li>
        <li><a href="/monitoring/helicorders.php">Seismogram Displays</a></li>
        <li><a href="/monitoring/buildings/">Buildings</a></li>
        <li><a href="http://nsmp.wr.usgs.gov/"><abbr title="National Strong Motion Project">NSMP</abbr> &ndash; Strong Motion</a></li>
        <li><a href="/monitoring/deformation/">Crustal Deformation</a></li>
      </ul>
    </li>
    <li>
      <a href="/research/?source=sitemap">Research</a>
      <ul>
        <li><a href="/research/">Projects</a></li>
        <li><a href="/research/">Science Centers</a></li>
        <li><a href="/research/data/">Data</a></li>
        <li><a href="/research/dyfi/">DYFI?</a></li>
        <li><a href="/research/pager/">PAGER</a></li>
        <li><a href="/research/shakemap/">ShakeMap</a></li>
        <li><a href="/research/earlywarning/">Early Warning</a></li>
        <li><a href="/research/software/">Software</a></li>
        <li><a href="/research/external/">External Support</a></li>
      </ul>
    </li>
  </ul>
</div>

<hr />

<div id="usgsnav">
	<ul>
		<li><a href="http://www.usgs.gov/laws/accessibility.html" title="Accessibility Policy (Section 508)">Accessibility</a></li>
		<li><a href="http://www.usgs.gov/foia/"><abbr title="Freedom of Information Act">FOIA</abbr></a></li>
		<li><a href="http://www.usgs.gov/laws/privacy.html" title="Privacy policies of the U.S. Geological Survey.">Privacy</a></li>
		<li><a href="http://www.usgs.gov/laws/policies_notices.html" title="Policies and notices that govern information posted on USGS Web sites.">Policies and Notices</a></li>
		<li><a href="http://www.nehrp.gov" class="nehrp">In partnership with <abbr title="National Earthquake Hazards Reduction Program">n<span class="e">e</span>hrp</abbr></a></li>
		<li><a href="http://www.takepride.gov/" class="takepride" title="Take Pride in America"><img src="/template/images/takePride.jpg" alt="Take Pride in America" width="60" height="58" /></a></li>
		<li><a href="http://www.usa.gov/" class="usagov" title="USAGov: Government Made Easy"><img src="/template/images/usagov.jpg" alt="USA.gov" width="90" height="26" /></a></li>
	</ul>
</div>
<div id="footer">
	<p>
		<a href="http://www.doi.gov/index.cfm">U.S. Department of the Interior</a> | <a href="http://www.usgs.gov/">U.S. Geological Survey</a><br />
		Page URL: http://earthquake.usgs.gov/earthquakes/map/<br />
		Page Contact Information: <a target="_blank" href="/contactus/?to=lisa&amp;subject=Latest+Earthquake+Maps">Contact Us</a><br />
		Page Last Modified: January 03, 2013 22:08:48 UTC	</p>
	<small class="disclaimer"><img src="/template/images/small_offsite_arrow.gif" alt="Offsite Link" title="Offsite Link"> <a href="http://www.doi.gov/disclaimer.cfm" title="Department of the Interior - Disclaimer of Liability and Endorsement">DOI</a> and <a href="http://www.usgs.gov/laws/info_policies.html" title="USGS - Information Policies">USGS</a> link policies apply.</small></div>
<script type="text/javascript" src="/earthquakes/map/compiled_20130103213230.js"></script><script type="text/javascript" src="/contactus/js/ContactValidator.js"></script><script type="text/javascript" src="/contactus/js/FAQFilter.js"></script><script type="text/javascript" src="/contactus/js/contactus.js"></script><script type="text/javascript" src="/template/widgets/ui/ui.js"></script><script type="text/javascript" src="/template/widgets/FileSave/BlobBuilder.js"></script><script type="text/javascript" src="/template/widgets/FileSave/FileSaver.js"></script><script type="text/javascript" src="/template/widgets/FlashSave/FlashSave.js"></script><script type="text/javascript" src="/template/widgets/FlashSave/swfobject/swfobject.js"></script><script type="text/javascript" src="/library/usgs/namespace.js"></script><script type="text/javascript" src="/template/widgets/dateTime/dateTime.js"></script>
<!--[if lt IE 7]><script type="text/javascript" src="/js/minmax.js"></script><![endif]-->
<!--[if lt IE 8]><script type="text/javascript" src="/template/js/offsite.js"></script><![endif]-->

</body>
</html>
