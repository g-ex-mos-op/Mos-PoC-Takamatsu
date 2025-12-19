<!--
var timeOutId;
// プルダウンが表示されているかのフラグ
var showflag = 0;

function showFlag() {
showflag = 1;
}
function hideFlag() {
showflag = 0;
}
var tmpcolor;
// コマンドバースクリプト　プルダウン表示OFF
var selectedMenu;
var openFlg=false;

// onマウス時の色の設定
function overColor(menuObj) {
	if(menuObj != null) {
		if("menu_current_cell" != menuObj.className) {
			tmpcolor = menuObj.style.backgroundColor;
		    menuObj.className = "menu_selected_cell";
	    }
    }
    return menuObj;
}

// マウスが外れた際に色を戻す
function outColor(menuObj, menuCd) {
	if(menuObj != null) {
	    menuObj.className = menuCd;
	    menuObj.style.backgroundColor = "";
    }
    return menuObj;
}
// オブジェクトの検索
function MM_findObj(formObj, menuCd, tagName) { //v4.01
	if(formObj != null) {
	    var objs=formObj.getElementsByTagName(tagName);
	    for (i=0;i<objs.length;i++) {
	     if(objs[i].className.indexOf(menuCd) >=0) return objs[i];
	  	}
  	}
  return null;
}

/**
 * メニューの上にマウスが重なった時に実行する処理
 *
 * param:obj メニューID
 * param:menuCd メニューID
 */
function menuOver(menuObj, menuCd) {
	clearTimeout(timeOutId);
    if (menuCd == '') {
        return;
    }
	menuObj.style.cursor="pointer";
	if(menuCd==selectedMenu) {
		if(openFlg==true) {
			//”hideArea”を非表示にしてから、
			offHideArea()
			showFlag();
			timeOutId = setTimeout('hideFlag()', 1000);
		}
	}
	else {
		openFlg=false;
		hideFlag();
		outColor(MM_findObj(document.menubarForm, "menu_selected_cell", "th"), selectedMenu);
		//前回表示されていたサブメニュープルダウンを削除します。
		closeSelectBox();
	}
	selectedMenu=menuCd;
	overColor(menuObj);
}
/**
 * メニューからマウスが外れた時に実行する処理
 *
 * param:menuCd メニューID
 */
function menuOut(menuObj, menuCd) {
	if(0 == showflag) {
		outColor(MM_findObj(document.menubarForm, "menu_selected_cell", "th"), menuCd);
		//”hideArea”を表示します。
		onHideArea()
	}
}
/**
 * メニューをクリック時に実行する処理
 *
 * param:id メニューID
 */
function menuClick(cellIndex, menuObj, menuCd) {
	clearTimeout(timeOutId);
    if (menuCd == '') {
        return;
    }
	if(1 == showflag) {
		hideFlag();
		//前回表示されていたサブメニュープルダウンを削除します。
		closeSelectBox();
	}
	else if(menuCd == selectedMenu) {
		chagePullDownStatus(cellIndex, menuObj, menuCd);
	}
	else {
		//”hideArea”を非表示にしてから、
		offHideArea()
		showFlag();
		//サブメニュープルダウンを表示します。
		subMenuPullDown(cellIndex, menuObj, menuCd, 'visible');
		selectedMenu=menuCd;
		openFlg=true;
		hideFlag();
	}
}

/**
 * メニューからマウスが外れた時に実行する処理
 *
 * param:subMenuObj メニューコード
 * param:submenuViewId サブメニューVIEWID
 */
function subMenuOver(subMenuObj, menuCd) {
	clearTimeout(timeOutId);
	showFlag();
	subMenuObj.style.cursor="pointer";
	//サブメニュープルダウンを表示します。
	offHideArea()
	overColor(MM_findObj(document.menubarForm, menuCd, "th"));
	overColor(subMenuObj);
}
/**
 * メニューからマウスが外れた時に実行する処理
 *
 * param:menuCd メニューコード
 * param:submenuViewId サブメニューVIEWID
 */
function subMenuOut(subMenuObj, menuCd, submenuViewId) {
	outColor(subMenuObj, submenuViewId);
	hideFlag();
	timeOutId = setTimeout('closeSelectBox()', 1000);
}
/**
 * 前回表示されていたサブメニュープルダウンを削除します。
 */
function closeSelectBox() { //v3.0
	if(showflag==0) {
		subMenuPullDown(-1, null, selectedMenu, 'hidden');
		outColor(MM_findObj(document.menubarForm, "menu_selected_cell", "th"), selectedMenu);
		openFlg=false;
		//サブメニュープルダウンを非表示にしてから、
		//”hideArea”を表示します。
		onHideArea()
	}
}

// コマンドバースクリプト　プルダウン表示ON
function subMenuPullDown(cellIndex, menuObj, menuCd, statusValue) { //v3.0
	var v,obj
    if ((obj=MM_findObj(document.pulldownForm, menuCd, "div"))!=null) {
    	v='';
    	if (obj.style) {
		    if("hidden" !=statusValue && menuObj!=null) {
		        var objTable = document.menubarForm.getElementsByTagName('table')[0];
		        var leftPoint = objTable.rows[0].cells[1].offsetLeft;

		        if(cellIndex == 0) {
		        	leftPoint = leftPoint + menuObj.offsetLeft;
		        }
		        if(cellIndex <= 4) {
		        	leftPoint = leftPoint + menuObj.offsetLeft + 1;
		        }
		        else {
		        	leftPoint = leftPoint + (660 - obj.offsetWidth);
		        }

		        if (lBrowser.isIE && lBrowser.agt.indexOf("rv:11.0") != -1) {
		        	obj.style.top = menuObj.offsetTop+26+"px";
		        } else {
		        	obj.style.top = menuObj.offsetTop+27+"px";
		        }
			    obj.style.left = leftPoint+"px";
		    }
    		obj=obj.style;
    		v=statusValue;
    	}
    	obj.visibility=v;
	}
}
/**
 * サブメニュープルダウン表示切り替え処理
 */
function chagePullDownStatus(cellIndex, menuObj, menuCd) { //v3.0
	var v,obj
    if ((obj=MM_findObj(document.pulldownForm, menuCd, "div"))!=null) {
    	v='';
    	if (obj.style.visibility == 'visible') {
    		subMenuPullDown(-1, null, menuCd, 'hidden');
    		openFlg=false;
    		//サブメニュープルダウンを非表示にしてから、
    		//”hideArea”を表示します。
			onHideArea()
    	}
    	else {
    		//”hideArea”を非表示にしてから、
    		//サブメニュープルダウンを表示します。
			offHideArea()
    		subMenuPullDown(cellIndex, menuObj, menuCd, 'visible');
    		openFlg=true;
    	}
		hideFlag();
	}
}
// コマンドバースクリプト　プルダウン表示ON時
// セレクトボックスを隠す処理
function onHideArea() { //v3.0
  changeDispHideArea('visible');
}
function offHideArea() { //v3.0
  changeDispHideArea('hidden');
}
function changeDispHideArea(statusValue) { //v3.0
  var i,p,v,obj;
    if(document.getElementsByTagName("div")!=null){
      var objs=document.getElementsByTagName("div");
      for(j=0;j<objs.length;j++){
        if(objs[j].className.indexOf("hideArea")>=0){
          obj=objs[j];
          obj.style.visibility=statusValue;
        }
     }
  }

}

function menuChange() {
    var menuDispPtn = document.menubarForm.elements['menubarForm:menuDispPtn'].value;
    if (menuDispPtn == 'true') {
        document.getElementById('menubarForm:referenceMenu', 0).style.display = 'block';
        document.getElementById('menubarForm:registMenu', 0).style.display = 'none';
        document.getElementById('menubarForm:linkToRefference', 0).style.display = 'none';
        document.getElementById('menubarForm:linkToRegit', 0).style.display = 'block';
        document.menubarForm.elements['menubarForm:menuDispPtn'].value = false;
    }
    else {
        document.getElementById('menubarForm:referenceMenu', 0).style.display = 'none';
        document.getElementById('menubarForm:registMenu', 0).style.display = 'block';
        document.getElementById('menubarForm:linkToRefference', 0).style.display = 'block';
        document.getElementById('menubarForm:linkToRegit', 0).style.display = 'none';
        document.menubarForm.elements['menubarForm:menuDispPtn'].value = true;
    }

    document.getElementById('headerForm:addMyMenuIFrame').contentWindow.document.changeMenuForm.elements['changeMenuForm:changeMenuPtn'].value
        = document.menubarForm.elements['menubarForm:menuDispPtn'].value;
    document.getElementById('headerForm:addMyMenuIFrame').contentWindow.document.changeMenuForm.elements['changeMenuForm:__link_clicked__'].value = "changeMenuForm:changeMenu";
    document.getElementById('headerForm:addMyMenuIFrame').contentWindow.document.changeMenuForm.submit();

}

//-->
