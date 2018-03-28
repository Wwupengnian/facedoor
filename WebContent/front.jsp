<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Photograph</title>
<!--   <script type="text/javascript">
  $(document).ready(function(){
		//$("p").text("confidence:"+ajax.responseText);
	  $("tt").text(str);
	  alert(str);
	});
  </script> -->
</head>
<body>
	<video id="video" width="480" height="320" controls>
	</video>
	<div>
		<p hidden>
			<button id="capture">Take Picture</button>
		</p>
		<p hidden>
			<button id="begin">Begin</button>
		</p>
		<p hidden>
			<button id="stop">Stop</button>
		</p>

	</div>
	<p id="conf" class="conf"></p>
	<div id="change" style="width: 480px; height: 20px">state</div>


	<canvas id="canvas" width="480" height="320"></canvas>



	<script>
  
  function base64Img2Blob(code){
                var parts = code.split(';base64,');
                var contentType = parts[0].split(':')[1];
                var raw = window.atob(parts[1]);
                var rawLength = raw.length;
				//var dlButton = document.getElementById("downloadImageBtn");
    		     //bindButtonEvent(dlButton, "click", saveAsLocalImage);
 
                var uInt8Array = new Uint8Array(rawLength);
				
 
                for (var i = 0; i < rawLength; ++i) {
                  uInt8Array[i] = raw.charCodeAt(i);
                }
 
                return new Blob([uInt8Array], {type: contentType});
            }
            

	
  function saveAsLocalImage () {
            	var myCanvas = document.getElementById("canvas");
        		
        		var image = myCanvas.toDataURL("image/png").replace("image/png", "image/octet-stream"); 
        		//window.location.href=image; // it will save locally
				
				
        	}
  
   function bindButtonEvent(element, type, handler)
            {
            	   if(element.addEventListener) {
            	      element.addEventListener(type, handler, false);
            	   } else {
            	      element.attachEvent('on'+type, handler);
            	   }
			}
  
  
    //访问用户媒体设备的兼容方法
    function getUserMedia(constraints, success, error) {
      if (navigator.mediaDevices.getUserMedia) {
        //最新的标准API
        navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
      } else if (navigator.webkitGetUserMedia) {
        //webkit核心浏览器
        navigator.webkitGetUserMedia(constraints,success, error)
      } else if (navigator.mozGetUserMedia) {
        //firfox浏览器
        navigator.mozGetUserMedia(constraints, success, error);
      } else if (navigator.getUserMedia) {
        //旧版API
        navigator.getUserMedia(constraints, success, error);
      }
    }

    
    let video = document.getElementById('video');
    let canvas = document.getElementById('canvas');
    let context = canvas.getContext('2d');

    function success(stream) {
      //兼容webkit核心浏览器
      let CompatibleURL = window.URL || window.webkitURL;
      //将视频流设置为video元素的源
      //console.log(stream);

      //video.src = CompatibleURL.createObjectURL(stream);
      video.srcObject = stream;
      video.play();
    }

    function error(error) {
      //console.log(`访问用户媒体设备失败${error.name}, ${error.message}`);
    }

    if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
      //调用用户媒体设备, 访问摄像头
      getUserMedia({video : {width: 480, height: 320}}, success, error);
    } else {
     // alert('不支持访问用户媒体');
    }

    document.getElementById('capture').addEventListener('click', function () {
      context.drawImage(video, 0, 0, 480, 320);     
    
    })
	
	document.getElementById('begin').addEventListener('click', begin())
	
	document.getElementById('stop').addEventListener('click', stop())
	
	
	
	
	var ajax = null;
	//利用ajax对图片进行上传,以下功能有待检验
	
	function uploadPicture(){
	
	
		    var canvas = document.getElementById("canvas");
			var img = canvas.toDataURL();
			
			 
			if (window.XMLHttpRequest) {
				ajax = new XMLHttpRequest();
			} else {
				ajax = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			//ajax.open("POST", "test.jsp", true);
			ajax.open("POST", "Search", true);
			ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			ajax.send("img=" + img);
			//$("#conf").text("confidence:");
	
	}
	
	function updateData(){
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4 && ajax.status == 200) {
				var str = ajax.responseText;
			
		
				document.getElementById("conf").innerHTML = "confidence:"+ajax.responseText;
			
			if(str*1>80&&str*1<100){
				document.getElementById("change").innerHTML = "YES";
				document.getElementById("change").style.backgroundColor = "green";
			}else{
				document.getElementById("change").innerHTML = "NO";
				document.getElementById("change").style.backgroundColor = "red";
			}
			}
		}
	}
	
	
		function autoClick()
		{
		   document.getElementById('capture').click();
		}
		
		
		function begin(){
			var i1 = setInterval("autoClick()","500");
			//毫秒
			var i2 = setInterval("uploadPicture()","1000");
			var i3 = setInterval("updateData()","500");
		}
		   
	
	    function stop(){
			//clearInterval(i1);
			//clearInterval(i2);
			//video.stop();
			/* 
	    	window.opener=null;
	    	swindow.open('','_self');
	    	window.close();
			 */
		}
	/* 
		 function refresh(){
			url = location.href;
			self.location.replace(url);
		}
		
		onload = refresh;  */
  </script>
</body>
</html>