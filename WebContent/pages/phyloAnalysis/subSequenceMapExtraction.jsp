<%@ include file="/pages/include/head.jsp" %>
<title>SequenceMap Extraction</title>
	
<script type="text/javascript" src="../../js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../../js/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="../../js/uploadify.css">
<style>
	.custom{margin-top:100px; margin-left: 50px; margin-right: auto; height:600px;}
</style>
<script type="text/javascript">
$(function(){
	$("#uploadFile").uploadify({

		'auto'		  	 : false,	//auto upload
		'buttonText'     : 'Select Files',
		'checkExisting'	 : false,
		'debug'		 	 : true,	//debug mode
		'fileObjName'	 : 'uploadFile',
		'fileSizeLimit'	 : '8.1MB',
		'fileTypeDesc'   : 'csv files',
		'fileTypeExts'   : '*.csv',
		'height'	 	 : 30,
		'method'	 	 : 'post',	//Post or Get
		'multi'		 	 : false,
		'preventCaching' : true,
		'progressData'   : 'percentage',	//percentage or speed
		'queueID'	 	 : 'fileQueue',
		'queueSizeLimit' : 600,
		'removeCompleted': true,
		'removeTimeout'  : 3,
		'requeueErrors'  : false,
		'successTimeout' : 30,
		'swf'		 	 : '../../js/uploadify.swf',
		'uploader'	 	 : 'subSequenceMapExtraction',
		'uploadLimit'	 : 600,
		'width'		 	 : 160,

        'onFallback' : function() {
            alert('Flash was not detected.');
        },
        
        'onSelectError' : function(file, errorCode, errorMsg){
              //alert(file.name + errorCode + errorMsg);
        },
        
        'onUploadStart' : function(file) {
        	var c1=$(":radio[name='choice']:checked").attr("value");
        	$("#uploadFile").uploadify("settings", "formData", { 'choice':c1}); 
        },
         
       	'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#message').html("Uploading and Analysizing...");
        },
       	
       	'onUploadError' : function(file, errorCode, errorMsg, errorString) {
       		//alert(errorMsg);
       		switch (errorMsg) {
       		case "2020":
       			$('#message').html("短时间内上传次数过多...");
       			break;
       		case "2121":
       			$('#message').html("达到每天上传上限...");
       			break;
       		case "2222":
       			$('#message').html("达到服务器上传上限...");
       			break;
       		case "2323":
       			$('#message').html("上传文件失败...");
       			break;
       		case "2424":
       			$('#message').html("上传文件失败，请检查文档类型...");
       			break;
       		}
       		
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorCode + errorMsg);
        },
        
        'onUploadSuccess' : function(file, data, response) {
        	if (response) {
        		$('#message').html("Done...");
            	var d = eval("("+data+")");
            	window.location.href = "showSubSequenceMapExtractionResult?fileName=" + d.outTreFileName +
            		"&choice=" + d.choice + "&missingTaxa=" + d.missingTaxa;
        	}
        	
        }
        
        });
});

</script>

<div class="body">
	<div class="custom">
		<h2>Execute Sub Gene Sequence ID Map Extraction!</h2>
		<table>
			<tr><td align="right">
					<i>Subtree Name List(.csv file):</i>
				</td>
				<td><table>
					<tr><td>
							<input type="file" id="uploadFile" name="uploadFile"/>	
						</td>
						<td width="400px">
							<div id="fileQueue"></div>
						</td>
						<td>
							<p id="message"></p>
					</td></tr>
				</table></td>
			</tr>
			
			<tr>
				<s:radio label="Output File" name="choice" list="#{'1':'Sequence ID File','2':'Sub Tree File'}" value="1" />
			</tr>
			
			<tr><td colspan=2 align="center">
				<input type="button" value="Submmit" onClick="javascript:$('#uploadFile').uploadify('upload','*')"/>
			</td></tr>
			
		</table>
	</div>
</div>
<%@ include file="/pages/include/foot.jsp" %>