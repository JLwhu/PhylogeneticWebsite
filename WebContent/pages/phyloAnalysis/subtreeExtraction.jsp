<%@ page import="org.apache.struts2.ServletActionContext" pageEncoding="UTF-8"%>
<%@ include file="/pages/include/head.jsp" %>

<title>Subtree Extraction</title>
	
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
		'debug'		 	 : false,	//debug mode
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
		'uploader'	 	 : 'subtreeExtraction',
		'uploadLimit'	 : 600,
		'width'		 	 : 160,

        'onFallback' : function() {
            alert('Flash was not detected.');
        },
        
        'onSelectError' : function(file, errorCode, errorMsg){
              //alert(file.name + errorCode + errorMsg);
        },
        
        'onUploadStart' : function(file) {
        	var c1=$(":radio[name='inputChoice']:checked").attr("value");
        	var c2=$(":radio[name='nameChoice']:checked").attr("value");
        	$("#uploadFile").uploadify("settings", "formData", { 'inputChoice':c1, 'nameChoice':c2 }); 
        },
         
       	'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
            $('#message').html("Uploading and Analysizing...");
        },
       	
       	'onUploadError' : function(file, errorCode, errorMsg, errorString) {
       		//alert(errorMsg);
       		switch (errorMsg) {
       		case "2020":
       			$('#message').html("Upload too many times in a short time...");
       			break;
       		case "2121":
       			$('#message').html("Reached user's upload limit in a day...");
       			break;
       		case "2222":
       			$('#message').html("Reached server's upload limit in a day...");
       			break;
       		case "2323":
       			$('#message').html("Upload file failed...");
       			break;
       		case "2424":
       			$('#message').html("Anlysize file failed, please check out file's type...");
       			break;
       		}
       		
            //alert('The file ' + file.name + ' could not be uploaded: ' + errorCode + errorMsg);
        },
        
        'onUploadSuccess' : function(file, data, response) {
        	if (response) {
        		$('#message').html("Done...");
            	var d = eval("("+data+")");
            	window.location.href = "showSubtreeExtractionResult?fileName=" + d.outTreFileName +
            		"&outputPath=" + d.fileSavePath + "&mainTreeImageFileName=" + d.mainTreeImageFileName +
            		"&subTreeImageFileName=" + d.subTreeImageFileName + "&mainTreeDiversity=" +
            		d.mainTreeDiversity + "&subTreeDiversity=" + d.subTreeDiversity;
        	}
        	
        }
        
        });
});

</script>

<div class="body">
	<div class="custom">
	
		<h2>Execute Subtree Extraction!</h2>
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
				<s:radio label="Input File Format" name="inputChoice" list="#{'1':'Ebird Science Name','2':'Ebird Common Name','3':'Ebird Science & Common Name','4':'Plain Science Name','5':'Plain Common Name'}" value="1" />
			</tr>
		
			<tr>
				<s:radio label="Output Name" name="nameChoice" list="#{'1':'Scientific Name','2':'Common Name'}" value="1" />
			</tr>
			
			<tr><td colspan="2" align="center">
				<input type="button" value="Submmit" onClick="javascript:$('#uploadFile').uploadify('upload','*')"/>
			</td></tr>
			
		</table>
	</div>
</div>
<%@ include file="/pages/include/foot.jsp" %>
