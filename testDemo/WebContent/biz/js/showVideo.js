function showContentVideo(path,id){
	if(path.indexOf("mp3")>0){
	    var showVideoId="showVideo"+id;

		var obj = document.getElementById(showVideoId);
		
		var a = obj.getElementsByTagName("audio");
		if(a.length>0){
			return ;
		}else{
			$("#"+showVideoId).append('&nbsp;&nbsp;&nbsp;<audio src="'+API_PATH+'/videos/'+path+'" autoplay loop  controls></audio>');
			}
	}else{
		playVideo(path,id);
	}
}
function playVideo(videoPath,id){//点击播放视频

	var showClassId="watch_play"+id;
	var fileurl=API_PATH+"/Video/"+videoPath;
	flashurl =PUBLIC_PATH+'/public/flash/myflvPlayBack.swf?url='+fileurl+'&skin='+PUBLIC_PATH+'/public/flash/MinimaFlatCustomColorAll.swf&autoplay=4';
	Win.open({
		title : "视频解析",
		mask : true,
		html : "<div id='myplayer'>&nbsp;</div>",
		width: 740,
		height: 510
			});
	FlashPlayer($id('myplayer'),flashurl,{id:'player',"wh":[718,428]});
}