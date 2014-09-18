// JavaScript Document
//table class="contentTable" 全部隔行变色
window.onload = function(){ //页面所有元素加载完毕
	var tables=document.getElementsByTagName('table');
	for(var i=0;i<tables.length;i++){
		if(tables[i].className=='contentTable'){
			var tbody =  tables[i].getElementsByTagName("tbody")[0];    //获取表格的第一个tbody元素
			var trs =   tbody.getElementsByTagName("tr");            //获取tbody元素下的所有tr元素
			for(var j=0;j < trs.length;j++){//循环tr元素
				if(j%2==0){        //取模. (取余数.比如 0%2=0 , 1%2=1 , 2%2=0 , 3%2=1)
					var Oclass=trs[j].className;
					if(Oclass!="bold")
					trs[j].className =  Oclass + " hover"; // 改变 符合条件的tr元素 的背景色.
				}
			}
		}
	}
}

