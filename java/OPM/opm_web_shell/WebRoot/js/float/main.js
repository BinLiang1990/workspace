// JavaScript Document
function $(id){return document.getElementById(id);}
//-------------------------------------------------------------------------------
//显示隐藏层
function showShj(obj){
	var BCW = document.body.clientWidth;
	var BCH = document.body.clientHeight;
	var DCW = document.documentElement.clientWidth;
	var DCH = document.documentElement.clientHeight;
	var ST = document.documentElement.scrollTop;
	var SL = document.documentElement.scrollLeft;
	if(navigator.userAgent.indexOf('Chrome')>0 || navigator.userAgent.indexOf('Safari')>0){ST = document.body.scrollTop;SL = document.body.scrollLeft;}			//针对谷歌浏览器
	if(DCH >= BCH)
		obj.style.height = DCH + 17 + "px";
	else
		obj.style.height = BCH + 17 + "px";

	if(DCW + SL >= BCW)
		obj.style.width = DCW + 17 + SL +"px";
	else
		obj.style.width = BCW + 17 + SL + "px";
	obj.style.display = "block";
}

//-------------------------------------------------------------------------------
//隐藏层
function dishiddenShj(obj){
	obj.style.display="none";
}
function disblockShj(obj){
	obj.style.display="block";
}
function vishiddenShj(id){
	obj.style.visibility="hidden";
}
//-------------------------------------------------------------------------------
//鼠标拖动
function moveEvent(e,id){
	var isIE = (document.all)?true:false;
	//navigator.userAgent.toLowerCase().indexOf("msie") != -1;
	//var event=window.event||event;
	var obj = document.getElementById(id);
	var drag = true;
	var xx=isIE?event.x:e.pageX;	//起始x坐标值
	var yy=isIE?event.y:e.pageY;	//起始y坐标值
	var L = obj.offsetLeft;	//起始left值
	var T = obj.offsetTop;	//起始top值
	var W = obj.clientWidth;	//div宽度
	var H = obj.clientHeight;	//div高度
	var ST = document.documentElement.scrollTop; //获取滚动条位置
	var SL = document.documentElement.scrollLeft; //获取滚动条位置
	if(navigator.userAgent.indexOf('Chrome')>0 || navigator.userAgent.indexOf('Safari')>0){ST = document.body.scrollTop;SL = document.body.scrollLeft;}			//针对谷歌浏览器
	var PW = document.documentElement.clientWidth; //页面可见宽度
	var PH = document.documentElement.clientHeight; //页面可见高度
	document.onmousemove = function(e) {
		if (drag) {
			x=isIE?event.x:e.pageX;		if(x<0)x=0;	//结束x坐标值
			y=isIE?event.y:e.pageY;		if(y<0)y=0;	//结束y坐标值
			var objLeft = L-xx+x;	//计算出结束拖到的left值
			var objTop = T-yy+y;	//计算出结束拖到的top值
			obj.style.opacity = 0.7;
			obj.style.cursor = "move";
			//根据情况对left值进行判断
			if(objLeft < SL){

				obj.style.left = SL	//不能超出左边

			}else{

				if(objLeft > PW - W + SL){

					obj.style.left=PW - W + SL + "px"	//不能超出右边

				}else{

					obj.style.left = objLeft + "px"
				}

			}
			//根据情况对top值进行判断
			if(objTop < ST){
				obj.style.top=ST		//不能超出顶部
			}else{

				if(objTop > PH - H + ST){

					obj.style.top  = PH - H + ST + "px"	//不能超出底部
				}else{
					obj.style.top  = objTop + "px"
				}

			}
		}
	}
	document.onmouseup=function(){
		obj.style.opacity = 1;
		obj.style.cursor = "default";
		drag = false;
	}
}
//---------------------------------------------------------------------------------
//获取父结点
function getParentNode(id){
	return document.getElementById(id).parentNode;
}
//-------------------------------------------------------------------------------
//浮动层
function visNew(id){
	showShj(getParentNode(id));		//显示层
	objOP = getParentNode(id).childNodes[1];
	if(navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0 || navigator.userAgent.indexOf("MSIE 8.0")>0){
				objOP = getParentNode(id).childNodes[0];
		}
	fadeEffect(objOP,0,40,4);
	hideScroll();	//去掉滚动条
	var elm = document.getElementById(id);	//浮动层
	var DDCW = document.documentElement;		//针对其它
	var bod = document.documentElement;		//针对其它
	if(navigator.userAgent.indexOf('Chrome')>0 || navigator.userAgent.indexOf('Safari')>0) bod = document.body;			//针对谷歌浏览器}
	var leftNum = (DDCW.clientWidth)/2 + $(bod).scrollLeft() - elm.offsetWidth/2;								//针对其它
	var topNum = (DDCW.clientHeight)/20 + $(bod).scrollTop() - elm.offsetHeight/2;					//针对其它
	var left = leftNum + 'px';
	var top = topNum + 100 + 'px';
	elm.style.left = left;
	elm.style.top = top;
	elm.style.visibility = 'visible';
	
}
//-------------------------------------------------------------------------------
//关闭vis弹出层
function visclose(elm){
	var obj=getParentNode(elm)
	dishiddenShj(obj);
	showScroll();//显示滚动条
	var elmobj = document.getElementById(elm);
	elmobj.style.visibility="hidden";
	var objOP = getParentNode(elm).childNodes[1];
		if(navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0 || navigator.userAgent.indexOf("MSIE 8.0")>0){
				objOP = getParentNode(elm).childNodes[0];
		}
	objOP.style.opacity = 0;
	objOP.style.filter = "alpha(opacity=0)";

}
//滚动条控制
function hideScroll(){
	var ST = document.documentElement.scrollTop;	//获取滚动条位置
	var SL = document.documentElement.scrollLeft;	//获取滚动条位置
	if(navigator.userAgent.indexOf('Chrome')>0 || navigator.userAgent.indexOf('Safari')>0){//针对谷歌浏览器
		ST = document.body.scrollTop;
		SL = document.body.scrollLeft;
	}
	document.documentElement.className = "hScroll";
	window.scrollTo(SL,ST);



}
function showScroll(){
	var SL = document.documentElement.scrollLeft;	//获取滚动条位置
	var ST = document.documentElement.scrollTop;	//获取滚动条位置
	if(navigator.userAgent.indexOf('Chrome')>0 || navigator.userAgent.indexOf('Safari')>0){//针对谷歌浏览器
		ST = document.body.scrollTop;
		SL = document.body.scrollLeft;
	}
	document.documentElement.className="sScroll";
	window.scrollTo(SL,ST);
}
function fadeEffect(element, start, end, speed){//透明度渐变：start:开始透明度 0-100；end:结束透明度 0-100；speed:速度1-100
	if(element.style.opacity == end / 100){element.style.opacity=0}
	var speed=speed||20;
	time = setInterval(function(){
		start = start < end ? Math.min(start + speed, end) : Math.max(start - speed, end);
		element.style.opacity =  start / 100;
		element.style.filter = "alpha(opacity=" + start + ")";
		if(start == end){
			clearInterval(time);
		}
	}, 20);
}

