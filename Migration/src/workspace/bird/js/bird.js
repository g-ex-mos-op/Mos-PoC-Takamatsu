<!--
var lBrowser = {};
lBrowser.agt = navigator.userAgent.toLowerCase();
lBrowser.isW3C = document.getElementById ? true:false;
lBrowser.isIE = ((lBrowser.agt.indexOf("msie") != -1 || lBrowser.agt.indexOf("rv:11.0") != -1) && (lBrowser.agt.indexOf("opera") == -1) && (lBrowser.agt.indexOf("omniweb") == -1));
lBrowser.isNS6 = lBrowser.isW3C && (navigator.appName=="Netscape") ;
lBrowser.isOpera = lBrowser.agt.indexOf("opera") != -1;
lBrowser.isGecko = lBrowser.agt.indexOf("gecko") != -1;
lBrowser.isFireFox = lBrowser.agt.indexOf("firefox") != -1;
lBrowser.isSafari = lBrowser.agt.indexOf("safari") != -1;
lBrowser.ieTrueBody =function (){
  return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body;
};
//Firefoxの兼用ために、以下内容を追加する
//Firefox下のDOMに属性を追加する
if(lBrowser.isNS6){
	//firefox innerText define
	HTMLElement.prototype.__defineGetter__( "innerText",function(){
		return this.textContent;
	});
	HTMLElement.prototype.__defineSetter__( "innerText",function(sText){
		this.textContent=sText;
	});
}

if(! Array.indexOf) {
  Array.prototype.indexOf = function(o)
  {
    for(var i in this) {
      if(this[i] == o) {
        return i;
      }
    }
    return -1;
  }
}


function windowOpen(page, width, height, mode, targetName){
  var an = navigator.appName;
  var av = navigator.appVersion;
  var url = page;
  var newwin1 = "";

  if(targetName == null){
  	targetName = "_blank";
  }

  if(mode == 'NORMAL'){
    if (an.indexOf("Microsoft",0) != -1 && av.indexOf("4",0) != -1) {
      newwin1 = window.open(url,"targetName","toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,width="+width+",height="+height+",left=0,top=0");
    } else {
      if(an.indexOf("Netscape",0) != -1 && av.indexOf("4",0) != -1) {
        newwin1 = window.open(url,"targetName","toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,width="+width+",height="+height+"screenX=0,screenY=0");
      } else {
        newwin1 = window.open(url,"targetName","toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes,width="+width+",height="+height);
      }
    }
  }else{
    if (an.indexOf("Microsoft",0) != -1 && av.indexOf("4",0) != -1) {
      newwin1 = window.open(url,"targetName","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width="+width+",height="+height+",left=0,top=0");
    } else {
      if(an.indexOf("Netscape",0) != -1 && av.indexOf("4",0) != -1) {
        newwin1 = window.open(url,"targetName","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width="+width+",height="+height+"screenX=0,screenY=0");
      } else {
        newwin1 = window.open(url,"targetName","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width="+width+",height="+height);
      }
    }
  }
}
function bodyTabChange(checkTab,showArea) {
  var objs = document.getElementsByTagName(checkTab);
  var dispArea

  for (i = 0; i < objs.length; i++) {
    if (objs[i].className == showArea) {
      if(objs[i].style.display == "inline"){
        dispArea = objs[i].className;
      }
      objs[i].style.display = "inline";
    }else if (objs[i].className == 'static') {
      objs[i].style.display = "inline";
    }else if(objs[i].className.substring(0,16) == 'tab_change_area_'){
      objs[i].style.display = "none";
    }else{
      if(objs[i].className == 'body_active_cell' && showArea != dispArea){
        objs[i].className = 'body_non_active_cell';
      }else if(objs[i].className == 'body_non_active_cell' && objs[i].id == showArea){
        objs[i].className = 'body_active_cell';
      }
    }
  }
}
function bodyTabChange(checkTab,showArea,tabTag) {
  var objs = document.getElementsByTagName(checkTab);
  var tabObjs = document.getElementsByTagName(tabTag);
  var dispArea

//Data area Change
  for (i = 0; i < objs.length; i++) {
    if (objs[i].className == showArea) {
      if(objs[i].style.display == "inline"){
        dispArea = objs[i].className;
      }
      objs[i].style.display = "inline";
    }else if (objs[i].className == 'static') {
      objs[i].style.display = "inline";
    }else if(objs[i].className.substring(0,16) == 'tab_change_area_'){
      objs[i].style.display = "none";
    }else{
    }
  }
//Tab area Change
  for (t = 0; t < tabObjs.length; t++) {
    if(tabObjs[t].className == 'body_active_cell' && showArea != dispArea){
      tabObjs[t].className = 'body_non_active_cell';
    }else if(tabObjs[t].className == 'body_non_active_cell' && tabObjs[t].id == showArea){
      tabObjs[t].className = 'body_active_cell';
    }
  }
}

// bodyTabChange()のnameによる判定version
function bodyTabChangeByName(checkTab,showArea,tabTag) {
  var objs = document.getElementsByTagName(checkTab);
  var tabObjs = document.getElementsByTagName(tabTag);
  var dispArea

//Data area Change
  for (i = 0; i < objs.length; i++) {
    if (objs[i].className == showArea) {
      if(objs[i].style.display == "inline"){
        dispArea = objs[i].className;
      }
      objs[i].style.display = "inline";
    }else if (objs[i].className == 'static') {
      objs[i].style.display = "inline";
    }else if(objs[i].className.substring(0,16) == 'tab_change_area_'){
      objs[i].style.display = "none";
    }else{
    }
  }
//Tab area Change
  for (t = 0; t < tabObjs.length; t++) {
    if(tabObjs[t].className == 'body_active_cell' && showArea != dispArea){
      tabObjs[t].className = 'body_non_active_cell';
    }else if(tabObjs[t].className == 'body_non_active_cell' && tabObjs[t].name == showArea){
      tabObjs[t].className = 'body_active_cell';
    }
  }
}

function naviTabChange(checkTab,showArea) {
  var objs = document.getElementsByTagName(checkTab);
  var dispArea

//Tab & Data area Change
  for (i = 0; i < objs.length; i++) {
    if (objs[i].className == showArea) {
      if(objs[i].style.display == "inline"){
        dispArea = objs[i].className;
      }
      objs[i].style.display = "inline";
    }else if (objs[i].className == 'static') {
      objs[i].style.display = "inline";
    }else if(objs[i].className.substring(0,16) == 'tab_change_area_'){
      objs[i].style.display = "none";
    }else{
      if(objs[i].className == 'navi_active_cell' && showArea != dispArea){
        objs[i].className = 'navi_non_active_cell';
      }else if(objs[i].className == 'navi_non_active_cell' && objs[i].id == showArea){
        objs[i].className = 'navi_active_cell';
      }
    }
  }
}
function naviTabChange(checkTab,showArea,tabTag) {
  var objs = document.getElementsByTagName(checkTab);
  var tabObjs = document.getElementsByTagName(tabTag);
  var dispArea

//Data area Change
  for (i = 0; i < objs.length; i++) {
    if (objs[i].className == showArea) {
      if(objs[i].style.display == "inline"){
        dispArea = objs[i].className;
      }
      objs[i].style.display = "inline";
    }else if (objs[i].className == 'static') {
      objs[i].style.display = "inline";
    }else if(objs[i].className.substring(0,16) == 'tab_change_area_'){
      objs[i].style.display = "none";
    }else{
    }
  }
//Tab area Change
  for (t = 0; t < tabObjs.length; t++) {
    if(tabObjs[t].className == 'navi_active_cell' && showArea != dispArea){
      tabObjs[t].className = 'navi_non_active_cell';
    }else if(tabObjs[t].className == 'navi_non_active_cell' && tabObjs[t].id == showArea){
      tabObjs[t].className = 'navi_active_cell';
    }
  }
}

/*
 * エラーフォーカス処理
 */
function focusElement(formName) {
	if(document.forms[formName] != null) {
		var targetForm = document.forms[formName];
		var elementName = document.focusElementForm.elements["FOCUS_ELEMENT"].value;
		var elementIndex = document.focusElementForm.elements["FOCUS_ELEMENT_INDEX"].value;
		if(elementName != "") {
			if(elementIndex == ""){
				elementIndex = 0;
			}
			if(targetForm.elements[elementName] != null){
				//指定エレメント名称時
		   	    if(targetForm.elements[elementName][elementIndex] != null) {
		   			targetForm.elements[elementName][elementIndex].focus();
		   			return;
		   		}
		   	}
   			var formAndElementName = formName+":"+elementName;
	   	    if(targetForm.elements[formAndElementName] != null) {
	   	    	//指定フォーム名称+":"+指定エレメント名称時
		   	    if(targetForm.elements[formAndElementName][elementIndex] != null) {
		   			targetForm.elements[formAndElementName][elementIndex].focus();
		   			return;
		   		}
	   		}
	   		/**
	   		 * forEachなどループでグルグルまわして生成されているエレメントの場合。
	   		 * 対象Formの全てのエレメントの名称から、指定されたエレメント名称、エレメントINDEXをキーに
	   		 * 同一のものが含まれえているエレメントへフォーカス処理を行う。
	   		 * 条件：ループ数 = エレメントINDEX
	   		 *       エレメント名称 = エレメント名称
	   		 * ID は フォームID:ループID_ループ数:個別ID の形
	   		 * NAME は フォームID:ループID_ループ数:個別NAME の形
	   		 */
	   		var targetName = targetElementName(formName, elementName, elementIndex);
	   		if(targetName != "") {
	   			if (targetForm.elements[targetName] != null &&
	   					targetForm.elements[targetName] != 'undefined' &&
	   					targetForm.elements[targetName].length > 1 &&
	   					targetForm.elements[targetName][0].type == 'radio') {
	   				targetForm.elements[targetName][0].focus();
	   			} else {
	   				targetForm.elements[targetName].focus();
	   			}
	   		}
		}
		else {
			setFocusToFirstTextElement(formName);
		}
	}
	else {

	}
}

/*
 * エラーフォーカス(disable 対応)処理
 * 20060829 Nakajima(ASPAC)
 * disableが掛かっているテキストにはフォーカスを当てないように対応
 */
function focusElementCustom(formName) {
	if(document.forms[formName] != null) {
		var targetForm = document.forms[formName];
		var elementName = document.focusElementForm.elements["FOCUS_ELEMENT"].value;
		var elementIndex = document.focusElementForm.elements["FOCUS_ELEMENT_INDEX"].value;
		if(elementName != "") {
			if(elementIndex == ""){
				elementIndex = 0;
			}
			if(targetForm.elements[elementName] != null){
				//指定エレメント名称時
		   	    if(targetForm.elements[elementName][elementIndex] != null) {
		   			targetForm.elements[elementName][elementIndex].focus();
		   			return;
		   		}
		   	}
   			var formAndElementName = formName+":"+elementName;
	   	    if(targetForm.elements[formAndElementName] != null) {
	   	    	//指定フォーム名称+":"+指定エレメント名称時
		   	    if(targetForm.elements[formAndElementName][elementIndex] != null) {
		   			targetForm.elements[formAndElementName][elementIndex].focus();
		   			return;
		   		}
	   		}
	   		/**
	   		 * forEachなどループでグルグルまわして生成されているエレメントの場合。
	   		 * 対象Formの全てのエレメントの名称から、指定されたエレメント名称、エレメントINDEXをキーに
	   		 * 同一のものが含まれえているエレメントへフォーカス処理を行う。
	   		 * 条件：ループ数 = エレメントINDEX
	   		 *       エレメント名称 = エレメント名称
	   		 * ID は フォームID:ループID_ループ数:個別ID の形
	   		 * NAME は フォームID:ループID_ループ数:個別NAME の形
	   		 */
	   		var targetName = targetElementName(formName, elementName, elementIndex);
	   		if(targetName != "") {
	   			targetForm.elements[targetName].focus();
	   		}
		}
		else {
			setFocusToFirstTextElementCustom(formName);
		}
	}
	else {

	}
}

/**
 * 対象パラメーター名称取得ファンクション
 *
 * forEachなどループでグルグルまわして生成されているエレメントのNameの取得を行う。
 * 対象Formの全てのエレメントの名称から、指定されたエレメント名称、エレメントINDEXをキーに
 *
 */
 function targetElementName(formName, keyName, keyIndex) {
  	var targetElm = targetElement(formName, keyName, keyIndex);
  	if(targetElm != null) {
	   return targetElm.name;
 	}
 	return "";
 }
/**
 * 対象パラメーター名称取得ファンクション
 *
 * forEachなどループでグルグルまわして生成されているエレメントのNameの取得を行う。
 * 対象Formの全てのエレメントの名称から、指定されたエレメント名称、エレメントINDEXをキーに
 *
 */
 function targetElement(formName, keyName, keyIndex) {
 	if(document.forms[formName] != null) {
 		var targetForm = document.forms[formName];
 		var i=0;
 		for (i=0; i<targetForm.elements.length; i++) {
			//チェック対象エレメント名称
			var targetName =targetForm.elements[i].name;
			if(targetName != null && targetName.length > 0) {
				if(targetName.indexOf(keyIndex+":"+keyName) >=0
				       || targetName.indexOf(formName+":"+keyName) >=0
				  )
				{
				   return targetForm.elements[i];
				}
			}
		}//end of for
	}
	return null;
}


// body area tab anchor style change (color etc)
function bodyAnchorStyleChange(showArea){
  var objs = document.getElementsByTagName('a');
  for (t = 0; t < objs.length; t++) {
    if(objs[t].className == 'body_non_selected_link' && objs[t].id == (showArea)){
      objs[t].className = 'body_selected_link';
    }else if(objs[t].className == 'body_selected_link' && objs[t].id == (showArea)){
      objs[t].className = 'body_selected_link';
    }else if(objs[t].className == 'body_selected_link'){
      objs[t].className = 'body_non_selected_link';
    }
  }
}

// bodyAnchorStyleChange()のnameによる判定version
function bodyAnchorStyleChangeByName(showArea){
  var objs = document.getElementsByTagName('a');
  for (t = 0; t < objs.length; t++) {
    if(objs[t].className == 'body_non_selected_link' && objs[t].name == (showArea)){
      objs[t].className = 'body_selected_link';
    }else if(objs[t].className == 'body_selected_link' && objs[t].name == (showArea)){
      objs[t].className = 'body_selected_link';
    }else if(objs[t].className == 'body_selected_link'){
      objs[t].className = 'body_non_selected_link';
    }
  }
}

// navi area tab anchor style change (color etc)
function naviAnchorStyleChange(showArea){
  var objs = document.getElementsByTagName('a');
  for (t = 0; t < objs.length; t++) {
    if(objs[t].className == 'navi_non_selected_link' && objs[t].id == (showArea)){
      objs[t].className = 'navi_selected_link';
    }else if(objs[t].className == 'navi_selected_link' && objs[t].id == (showArea)){
      objs[t].className = 'navi_selected_link';
    }else if(objs[t].className == 'navi_selected_link'){
      objs[t].className = 'navi_non_selected_link';
    }
  }
}

// set focus to first Text-Element
function setFocusToFirstTextElement(formName) {
	if (formName == null) {
		return;
	}
	var objForm = document.forms[formName];
	var type;
	for (i = 0; i < objForm.elements.length; i++) {
		type = objForm.elements[i].type;
		if ('text' == type) {
			if (objForm.elements[i].style.display!='none'){
				objForm.elements[i].focus();
				return;
			}
		}
	}
}

// set focus to first Text-Element (disable 対応)
// 20060829 Nakajima(ASPAC)
function setFocusToFirstTextElementCustom(formName) {
	if (formName == null) {
		return;
	}
	var objForm = document.forms[formName];
	var type;
	for (i = 0; i < objForm.elements.length; i++) {
		type = objForm.elements[i].type;
		if ('text' == type) {
			if (objForm.elements[i].style.display!='none'){
				if(objForm.elements[i].disabled == false){
					objForm.elements[i].focus();
					return;
				}
			}
		}
	}
}

//次の構成要素を取得
function getNextElement(field,tabIx) {
    var form = field.form;
    var returnNextElement;
    for(var e = 0; e < form.elements.length; e++) {
        if (tabIx == form.elements[e].tabIndex - 1) {
        	returnNextElement = form.elements[e];
            break;
    	}
    }

    return returnNextElement;
}

//Enterキー押下をTabキー押下に変換
//・sLastFocusID, sFirstFocusIDを指定することで、タブキーでのループ移動が可能
//・sLastFocusID, sFirstFocusIDを指定しない場合は、nullを設定して下さい
//[パラメータ]
// formName : 対象のフォーム名
// sLastFocusID  : formName内でフォーカスが最後（tabIndexが最大）のオブジェクトID
// sFirstFocusID : formName内でフォーカスが最初（tabIndexが１）のオブジェクトID
//
function enterTAB(e, formName, sLastFocusID, sFirstFocusID){

	var event = e || event;
	var isMsie = (document.all) ? true : false;
	var key;
    var srcobj;
	if (isMsie) { // IE
        key = event.keyCode;
        srcobj=event.srcElement;
    }
    else { // IE以外
        key = event.which;
        srcobj=event.target;
    }

	if(key == 13
			&& srcobj.type != 'button'
			&& srcobj.type != 'submit'
			&& srcobj.type != 'reset'
			&& srcobj.type != 'textarea'
			&& srcobj.type != '') {

		var f = document.forms[formName];
        eventid = srcobj.id;

		if (sLastFocusID != null
				&& sFirstFocusID != null
				&& eventid == formName + ':' + sLastFocusID) {
			if(isMsie)
				document.all(formName + ':' + sFirstFocusID).focus();
			else
				f.elements[formName + ':' + sFirstFocusID].focus();
			return false;
		}

        if(isMsie){ // IE
			try {
				window.event.keyCode = 0x09;
			}
			catch(e) {
				event.returnValue = false;
			}
		} else { // IE以外
            var el;
            var stepLook = 6;
            for (var i=0; i < stepLook; i++) {
            	el = getNextElement(srcobj,srcobj.tabIndex + i);
            	if (el) {
            		break;
            	}
            }

            if(!el)
                return false;
            else
                el.focus();
            return false;
        }
	}

	return true;
}

//Enterキーを押された時に自動実行されるボタンの設定
//フォーカスがテキスト、ラジオボタン、チェックボックスにある時のみ動作します
//[パラメータ]
//   formName : 対象のフォーム名
//   btnID    : 実行させるボタンのID
function setDefaultSubmitButton(formName, btnID) {
	var theEvent = window.event || arguments.callee.caller.arguments[0];
	var srcobj;
	var key;
	var isMsie = (document.all) ? true : false;
	if (isMsie) { // IE
        key = theEvent.keyCode;
        srcobj=theEvent.srcElement;
    }
    else { // IE以外
        key = theEvent.which;
        srcobj=theEvent.target;
    }
	if (key == 13) {
		if (srcobj.type == 'text' ||
				srcobj.type == 'radio' ||
				srcobj.type == 'checkbox' ||
				srcobj.type == 'password'){

			var iDorName = formName + ":" + btnID;
			var targetForm = document.forms[formName];
			var execBtn = targetForm.elements[iDorName];
			if(execBtn == null) {
				execBtn = targetForm.elements[btnID];
			}
			execBtn.click();
			return false;
		} else if (srcobj.type == 'file') {
			theEvent.srcobj.click();
			return false;
		}
	}
	return true;
}



// bodyTabChangeByName()に、bodyAnchorStyleChangeByName()の機能、印刷時全タブ分のデータが印刷できる機能を追加。
// パラメーター数は呼び出し側で指定した分だけ使える。それ以外はデフォルトの値が設定される。
// [プロパティ]
// index 				: 表示領域のname属性の可変部分
// areaPreName 			: 表示領域のname属性の文字部分
// areaTagName    		: 表示領域、非表示領域のタブ名
// tabTagName   		: タブ部分のタグ名
// tabActiveClass		: 表示タブのクラス名
// tabNonActiveClass	: 非表示タブのクラス名
// anchorPreName		: タブのname属性の文字部分
// anchorTagName		: タブのタグ名
// selectedClass		: 選択されたタブのクラス名
// nonSelectedClass		: 選択されていないタブのクラス名
function changeTabArea() {
	// パラメータ初期化
	var index = 0;
	var areaPreName = 'tab_change_area_';
	var areaTagName = 'div';
	var tabTagName = 'td';
	var tabActiveClass = 'body_active_cell';
	var tabNonActiveClass = 'body_non_active_cell';
	var anchorPreName = 'body_anchor_area_';
	var anchorTagName = 'a';
	var selectedClass = 'body_selected_link';
	var nonSelectedClass = 'body_non_selected_link';

	// パラメータが指定されている場合、その値に上書き
	if (arguments.length > 0) {
		index = arguments[0];
	}
	if (arguments.length > 1) {
		areaPreName = arguments[1];
	}
	if (arguments.length > 2) {
		areaTagName = arguments[2];
	}
	if (arguments.length > 3) {
		tabTagName = arguments[3];
	}
	if (arguments.length > 4) {
		tabActiveClass = arguments[4];
	}
	if (arguments.length > 5) {
		tabNonActiveClass = arguments[5];
	}
	if (arguments.length > 6) {
		anchorPreName = arguments[6];
	}
	if (arguments.length > 7) {
		anchorTagName = arguments[7];
	}
	if (arguments.length > 8) {
		selectedClass = arguments[8];
	}
	if (arguments.length > 9) {
		nonSelectedClass = arguments[9];
	}

	var objs = document.getElementsByTagName(areaTagName);
	var tabObjs = document.getElementsByTagName(tabTagName);
	var tabColor = document.getElementsByTagName(anchorTagName);
	var dispArea;
	var areaName = areaPreName + index;
	var tabName = anchorPreName + index;
	// 表示領域切替
	for (i = 0; i < objs.length; i++) {
  		var arrayClass = objs[i].className.split(" ");
	  	if(objs[i].className.indexOf(areaPreName)>=0){
			// nameが表示領域と一致する場合
	    	if (arrayClass.indexOf(areaName) >=0) {
	    		objs[i].className = objs[i].className.replace("invisible_tab","visible_tab");
	    	} else {
	      	// nameが定義されてあり、areaPreNameの文字列が含まれている場合
	    		if (objs[i].className.indexOf('invisible_tab') ==-1) {
	      			objs[i].className = objs[i].className.replace("visible_tab","invisible_tab");
	      		}
	    	}
    	}
  	}
	// タブ切替
  	for (i = 0; i < tabObjs.length; i++) {
  		var arrayClass = tabObjs[i].className.split(" ");
	  	if(tabObjs[i].className.indexOf(areaPreName)>=0){
			if (arrayClass.indexOf(areaName) >=0) {
		  		// 表示タブ設定
	      		tabObjs[i].className = tabObjs[i].className.replace(tabNonActiveClass, tabActiveClass);
	    	}
	    	else {
				// 非表示タブ設定
	    		tabObjs[i].className = tabObjs[i].className.replace(tabActiveClass, tabNonActiveClass);
	    	}
    	}
  	}//end of タブ切替
}
/**
 * スクロールロード処理
 * divIdのエレメントは不可視状態では高さが判断できません。
 * なので、可視状態してからこの処理を実行してください。
 * 注)第1・第2引数は必須です！
 *
 * 第1引数：divId (必須)
 * 第2引数：maxHeight(必須)
 * 第3引数：tableIndex 第1引数のid内の何番目のテーブルなのか判断します。
 */
function loadScroll() {
	var divId = arguments[0];
	var maxHeight = null;
	var tableIndex = 0;
	if(arguments.length > 1) {
		maxHeight = arguments[1]
	}
	// パラメータが指定されている場合、その値に上書き
	if (arguments.length > 2) {
		tableIndex = arguments[2];
	}

	var scrollDiv = document.getElementById(divId);
    if (scrollDiv == null) {
    	var fms = document.forms;
    	for (var i=0;i<fms.length;i++) {
    		var divIdT = fms[i].name+":"+divId;
    		scrollDiv = document.getElementById(divIdT);
    		if (scrollDiv != null) {
    			break;
    		}
    	}
    }
    //スクロール状態変更処理
    changeScrollStatus(scrollDiv, maxHeight, tableIndex);
}
/**
 * スクロールロード処理
 * divIdのエレメントは不可視状態では高さが判断できません。
 * なので、可視状態してからこの処理を実行してください。
 * 注)第1引数は必須です！
 *
 * 第1引数：divId (必須)
 * 第2引数：maxHeight
 * 第3引数：tableIndex 第1引数のid内の何番目のテーブルなのか判断します。
 */
function loadScrolls() {
　　var divId = arguments[0];
　　var maxHeight = null;
	var tableIndex = 0;
	if(arguments.length > 1) {
		maxHeight = arguments[1]
	}
	// パラメータが指定されている場合、その値に上書き
	if (arguments.length > 2) {
		tableIndex = arguments[2];
	}
	var elementScrollArea = document.getElementById(divId);
	if(elementScrollArea == null) {
		var fms = document.forms;
    	for (var i=0;i<fms.length;i++) {
    		var divIdT = fms[i].name+":"+divId;
    		elementScrollArea = document.getElementById(divIdT);
    		if (elementScrollArea != null) {
    			break;
    		}
    	}
	}
    var selectDivs = document.getElementsByTagName("div");
    for(i=0; i<selectDivs.length; i++) {
    	var scrollDiv = selectDivs[i];
    	if(scrollDiv.className.indexOf("scroll_standby") >= 0
    	     || scrollDiv.className.indexOf("scroll_complete") >= 0 ){
		    //スクロール状態変更処理
		    changeScrollStatus(scrollDiv, maxHeight, tableIndex);
	    }
    }
}
/**
 * スクロール状態変更処理
 *
 * DIVエレメントは不可視状態では高さが判断できません。
 * なので、可視状態してからこの処理を実行してください。
 * 注)第1・第3引数は必須です！
 *
 * 第1引数：scrollDiv  (必須)
 * 第2引数：maxHeight
 * 第3引数：tableIndex (必須) 第1引数のid内の何番目のテーブルなのか判断します。
 */
function changeScrollStatus(scrollDiv, maxHeight, tableIndex) {
	if(scrollDiv==null) {
		return;
	}
	var elTables =scrollDiv.getElementsByTagName("table");
    var dataTable = null;
	if(elTables.length > 0) {
		dataTable = elTables[tableIndex];
	}
    var divHeight = scrollDiv.offsetHeight;

    if (maxHeight == null) {
    	if (dataTable != null) {
    		if(dataTable.offsetHeight <= divHeight) {
    			//スクロールの高さよりもデータ部が低い場合はスクロールを非表示にします。
		    	scrollDiv.className = scrollDiv.className.replace("scroll_complete","scroll_standby");
				/* 2016/11/19追加　画面スクロールバー表示しない場合、classname＝”scroll_padding_data**”divの様式設定する*/
		    	var par=scrollDiv.parentNode;
				if (par.className.indexOf("scroll_padding_data")>=0){
					par.className = "scroll_padding_data";
				}
	    	}
	    	else {
		    	scrollDiv.className = scrollDiv.className.replace("scroll_standby","scroll_complete");
				/* 2016/11/19追加　画面スクロールバー表示する場合、classname＝”scroll_padding_data**”divの様式設定しない*/
		    	var par=scrollDiv.parentNode;
				if (par.className.indexOf("scroll_padding_data")>=0){
					par.className = "scroll_padding_data_no";
				}
	    	}
    	}
    	return;
    }
    if (divHeight > maxHeight) {
    	scrollDiv.style.height = maxHeight+"px";
    	scrollDiv.className = scrollDiv.className.replace("scroll_standby","scroll_complete");
    	scrollDiv.className = scrollDiv.className.replace("scroll_hidden","scroll_complete");
		/* 2016/11/19追加　画面スクロールバー表示する場合、classname＝”scroll_padding_data**”divの様式設定しない*/
    	var par=scrollDiv.parentNode;
		if (par.className.indexOf("scroll_padding_data")>=0){
			par.className = "scroll_padding_data_no";
		}
    	return;
    }
    if ((dataTable != null && dataTable.offsetHeight > maxHeight)) {
    	scrollDiv.style.height = maxHeight+"px";
    	scrollDiv.className = scrollDiv.className.replace("scroll_standby","scroll_complete");
    	scrollDiv.className = scrollDiv.className.replace("scroll_hidden","scroll_complete");
		/* 2016/11/19追加　画面スクロールバー表示する場合、classname＝”scroll_padding_data**”divの様式設定しない*/
    	var par=scrollDiv.parentNode;
		if (par.className.indexOf("scroll_padding_data")>=0){
			par.className = "scroll_padding_data_no";
		}
    	return;
    }
    if (divHeight <= maxHeight || (dataTable != null && dataTable.offsetHeight <= maxHeight)) {
    	scrollDiv.className = scrollDiv.className.replace("scroll_complete","scroll_standby");
    	scrollDiv.className = scrollDiv.className.replace("scroll_area_100","scroll_standby");
		/* 2016/11/19追加　画面スクロールバー表示しない場合、classname＝”scroll_padding_data**”divの様式設定する*/
    	var par=scrollDiv.parentNode;
		if (par.className.indexOf("scroll_padding_data")>=0){
			par.className = "scroll_padding_data";
		}
    	scrollDiv.style.height = "auto";
    }
}

/**
 * OnBlurイベント追加処理
 *
 * 画面でテキストボックスに動的にOnBlurイベントを追加する
 * 追加対象：classNameに"imenotuse"を含む Or styleに"ime-mode:disabled"を含む
 *
 *
 * @returns
 */

function preventOnload() {
	var inputs = document.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		var typeVal = inputs[i].type;
		var reId = inputs[i].id;
		if (typeVal == "text") {
			var classPro = inputs[i].attributes["class"];
			if (classPro != null && classPro != "null" && classPro != "undefined" && classPro !="") {
				var classVal = classPro.value;
				// 空白を削除
				classVal = classVal.replace(/ /g, "");

				// classNameに"imenotuse"を含む
				if (classVal.indexOf("imenotuse") >= 0) {
					addevent(inputs[i],'blur',objOnBlur);
				}
			}

			if (lBrowser.isIE) {
				var styleValIE = inputs[i].style.imeMode.toLowerCase();
				if (styleValIE == "disabled") {
					addevent(inputs[i],'blur',objOnBlur);
				}
			} else {
				var stylePro = inputs[i].attributes["style"];
				if (stylePro != null && stylePro != "null" && stylePro != "undefined") {
					var styleValNIE = stylePro.value.toLowerCase();

					// 空白を削除
					styleValNIE = styleValNIE.replace(/ /g, "");
					// styleに"ime-mode:disabled"を含む
					if (styleValNIE.indexOf("ime-mode:disabled") >= 0) {
						addevent(inputs[i],'blur',objOnBlur);
					}
				}
			}
		}
	}
}


/**
 * OnBlurイベント:全角文字を半角文字に変換する
 * 変換対象以外の文字を入力する場合、エラー発生
 *
 * @returns
 */
function objOnBlur(e){
	e = e || window.event;
	var _this = e.srcElement||e.target;
	var reValues = _this.value;
	// 空欄の場合、変換しない
	if (reValues == "") {
		return;
	}
	// 全角文字を半角文字に変換する
	_this.value = zenkakuTohankaku(reValues);

	var result = _this.value;
	for(var i = 0; i < result.length; i++) {
		var cCode = result.charCodeAt(i);
		if((cCode < 32  || cCode > 126)) {
			alert('入力した内容が正しくありません。');
			var idVal = _this.id;
			if(lBrowser.isFireFox){
				setTimeout(function(){document.getElementById(idVal).focus();}, 0);
			}else{
				_this.focus();
			}
			_this.value = "";
			return;
		}
	}
}

/**
 * 全角文字を半角文字に変換する
 * 変換対象：ａ～ｚ Ａ～Ｚ ０～９ ｀ ～ ！ ＠ ＃ ＄ ％ ＾ ＆ ＊ （ ） － ＿ ＝ ＋ ｛ ［ ｝ ］ ｜ ￥ ： ； ”  ＜ ， ＞ ． ？ ／ 全角スペース
 *
 * @param str
 * @returns
 */
function zenkakuTohankaku(str) {

	var result="";
	for(var i=0;i<str.length;i++){
		var cCode = str.charCodeAt(i);
		// 全角英数字を半角英数字に変換する
		if(cCode >= 65281  && cCode < 65374){
			cCode = cCode - 65248;
		}

		// 全角スペースの場合
		if(cCode == 12288){
			cCode = 32;
		}

		//￥の場合
		if(cCode == 65509){
			cCode = 92;
		}

		//”の場合
		if(cCode == 8221){
			cCode = 34;
		}

		//’の場合
		if(cCode == 8217){
			cCode = 39
		}
		//～の場合
		if(lBrowser.isSafari){
			if(cCode == 12316){
				cCode = 126;
			}
			if(cCode == 165){
				cCode = 92;
			}
		}else{
			if(cCode == 65374){
				cCode =126
			}
		}
		result += String.fromCharCode(cCode);
	}

	return result;
}

/**
 * format処理
 * 文字列前後のスペース[全角／半角]を削除します。
 *
 */
function formatOnload() {
	var linkElements = document.getElementsByTagName("a");
	if (!lBrowser.isIE) {
		for (var i = 0; i < linkElements.length; i++) {
			var elementValue = linkElements[i].innerHTML;
			if (elementValue != null && elementValue != "") {
				linkElements[i].innerHTML = elementValue.replace(/^[\s|　]+|[\s|　]+$/g,'');
			}
		}
	}
}

var addevent = function (object, evt, func) {
	if(window.addEventListener) {
		object.addEventListener(evt, func, false);
	} else if (window.attachEvent) {
		object.attachEvent("on" + evt, func);
	}
};


addevent(window,'load',preventOnload);

addevent(window,'load',formatOnload);

//-->