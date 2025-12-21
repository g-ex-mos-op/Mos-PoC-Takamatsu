<!--
var userTypeHONBU = "01";
var userTypeONER = "02";
var userTypeMISE = "03";
/**
 * 同期対象プルダウン表示切替処理
 */
function changeLinkPullDown(changeElement, linkPdwnName, defaultValueFlg) {
    //選択した対象条件の値を取得します。
    var selectedCode = changeElement.options[changeElement.selectedIndex].value;
    //対象条件別表示対象プルダウン設定処理
    changeDisplay("div", "pdwn_"+linkPdwnName+"_", selectedCode, defaultValueFlg);
    if(defaultValueFlg) {
    	var elementPullDown = getElement("select", linkPdwnName+"_", selectedCode);
    	elementPullDown.selectedIndex = 0;
    }
}
/**
 * 対象条件プルダウンリスト変更時同期対象プルダウン表示切替処理
 * (※本部ユーザー時のみ使用)
 * 対象条件プルダウンの値毎に
 * 表示対象プルダウンの内容を変更される。
 */
function changeTaishoJokenLinkPullDown(changeElement){
    //選択した対象条件の値を取得します。
    var selectedCode = changeElement.options[changeElement.selectedIndex].value;
	//店舗検索ボタンを非表示にします。
	inputForm.elements['suiiRefHonbuForm:miseCallBt'].style.display = "none";
    //対象条件別表示対象プルダウン設定処理
    changeDisplay("div", "pdwn_hyojiTaisho_", selectedCode, false);
    if("MISE"==selectedCode) {
    	//店舗検索ボタンを表示します。
    	inputForm.elements['suiiRefHonbuForm:miseCallBt'].style.display = "inline";
    }
}
/**
 * 別ウィンドウ処理
 */
function initWindow() {
	//別ウィンドウのチェック
	var isChecked = false;
	if(document.suiiRefForm.elements['suiiRefForm:newwin'] != null){
	    isChecked = document.suiiRefForm.elements['suiiRefForm:newwin'].checked;
		document.suiiRefForm.elements['suiiRefForm:newWindowFlg'].value=isChecked;
		if(isChecked) {
			document.suiiRefForm.target = "_blank";
			return;
		}
	}
	document.suiiRefForm.target = "_self";
}

/**
  * アクション呼出し
  * 実行ボタン押下、店舗選択画面遷移など
  */
function callAction(actionMode, focusTab) {
	if(inputForm != null && returnLoadingStatus()) {
		document.suiiRefForm.elements['suiiRefForm:newWindowFlg'].value = false;
		//条件パラメータ設定
		setConditionParam(focusTab);
		if(actionMode=="search") {
			initWindow();
			if(document.suiiRefForm.target != "_self") {
				document.suiiRefForm.elements['suiiRefForm:loadingFlg'].value = "OFF";
			}
		}
		else {
			document.suiiRefForm.target = "_self";
			if(actionMode=="downloadCsv") {
				document.suiiRefForm.elements['suiiRefForm:loadingFlg'].value = "OFF";
			}
		}
		document.suiiRefForm.elements['suiiRefForm:__link_clicked__'].value='suiiRefForm:'+actionMode;
		document.suiiRefForm.submit();
	}
}
//条件パラメータ設定
function setConditionParam(focusTab) {
	if(isHonbuUser() || isOnerUser()) {
		//会社設定
		settingValue("companyCd");
	}
	if(isHonbuUser()) {
		//店舗種別設定
		settingValue("tenpoShubetu");
		//対象期間設定
		settingValue("taishoKikan");
	}
	//期間指定設定
	if(isHonbuUser()) {
		settingValue("kikanSitei", "taishoKikan", "select");
	}
	else {
		//期間指定設定
		settingValue("kikanSitei", "", "select", "MONTH");
	}
	if(isHonbuUser() || isOnerUser()) {
		//対象条件設定
		settingValue("taishoJoken");
		//表示対象設定
		settingValue("hyojiTaisho", "taishoJoken", "select");
	}
	//前年データ種別
	if(isHonbuUser()) {
		settingValue("zennenDataShubetu", "tenpoShubetu", "select");
	}
	else {
		settingValue("zennenDataShubetu", "", "select", "ALL");
	}
	//フォーカスタブ指定時
	if(focusTab != null) {
		document.suiiRefForm.elements['suiiRefForm:focusTab'].value=focusTab;
	}
}
/**
 * 指定条件項目値設定処理
 */
function settingValue(setElementName, extendsElementName, tagName, targetFeeld) {
	var settingElement = document.suiiRefForm.elements['suiiRefForm:'+setElementName];
	if(settingElement != null) {
		settingElement.value="";
		var elementPullDown = null;
		if(extendsElementName != null && extendsElementName!="") {
			//同期しているプルダウンがいる場合、そのプルダウンの選択値を取得します。
			var elementExtends = inputForm.elements[inputFormName+':'+extendsElementName];
		    var taishoFeeld = elementExtends.options[elementExtends.selectedIndex].value;
		    elementPullDown = getElement(tagName, setElementName+"_", taishoFeeld);
			if("hyojiTaisho"== setElementName) {
				//ブロックコード設定処理
				var blockElement = document.suiiRefForm.elements['suiiRefForm:blockCd'];
				blockElement.value = "";
				if(isCompanyCdMos() && "SIBU"==taishoFeeld) {
					//モスフードサービスのみブロックコード有り
					var blockPullDown = getElement("select", "hyojiTaisho_", "BLOCK");
					if(blockPullDown != null){
						blockElement.value = blockPullDown.options[blockPullDown.selectedIndex].value;
					}
				}
			}
	    }
	    else if(targetFeeld !=null) {
	    	elementPullDown = getElement(tagName, setElementName+"_", targetFeeld);
	    }
	    else {
	    	elementPullDown = inputForm.elements[inputFormName+':'+setElementName];
	    }
		if(elementPullDown != null && elementPullDown.length>0) {
			settingElement.value = elementPullDown.options[elementPullDown.selectedIndex].value;
		}
		else if("hyojiTaisho"== setElementName) {
			if("MISE"==taishoFeeld) {
				settingElement.value = inputForm.miseCd.value;
			}
			if("ALL"==taishoFeeld && inputForm.onerCd !=null) {
				//オーナーユーザーで全店の場合
				settingElement.value = inputForm.onerCd.value;
			}
		}
	}
}
/**
 * エレメント取得処理
 */
function getElement(targetTag, firstCalssNm, targetCode) {
	var objsTag = inputForm.getElementsByTagName(targetTag);
	for (i = 0; i < objsTag.length; i++) {
		var classNm = objsTag[i].className;
		if(classNm.indexOf(firstCalssNm) >= 0){
			if(classNm.indexOf(targetCode) >= 0) {
				return objsTag[i];
			}
		}
	}
	return null;
}
/*
 * 条件フォーム用表示ステータス変更処理
 */
function changeDisplay(targetTag, firstCalssNm, lastClassNm) {
	var objs = inputForm.getElementsByTagName(targetTag);
    for (i = 0; i < objs.length; i++) {
		var classNm = objs[i].className;
		if(classNm.indexOf(firstCalssNm) >= 0){
			if("" != lastClassNm && classNm.indexOf(lastClassNm) >= 0) {
				objs[i].style.display     = "inline";
			}else{
				objs[i].style.display   = "none";
			}
		}
	}
}
/**
 * ウィンドウロードステータス取得処理
 *
 */
function returnLoadingStatus(){
	if(isLoadingFlgOFF()) {
		setLoadingFlg();
		return true;
	}
	return false;
}
/**
 * ウィンドウロードステータス取得処理
 *
 */
function isLoadingFlgOFF(){
	return (document.suiiRefForm.elements['suiiRefForm:loadingFlg'].value != "ON");
}

/**
 * 会社コードモスフード判別処理
 *
 */
function isCompanyCdMos(){
	var companyCd = document.suiiRefForm.elements['suiiRefForm:companyCd'].value;
	return ("00"==companyCd);
}

/**
 * ウィンドウロードフラグ設定処理
 *
 *
 */
function setLoadingFlg() {
	document.suiiRefForm.elements['suiiRefForm:loadingFlg'].value = "ON";
}
function isHonbuUser() {
	return userTypeHONBU == document.suiiRefForm.elements['suiiRefForm:userTypeCd'].value;
}
function isOnerUser() {
	return userTypeONER == document.suiiRefForm.elements['suiiRefForm:userTypeCd'].value;
}
function isMiseUser() {
	return userTypeMISE == document.suiiRefForm.elements['suiiRefForm:userTypeCd'].value;
}
/**
　* 宅配売上推移表タブ処理
  */
function callActionTakuhai(actionMode, taishoJoken) {
	if(isLoadingFlgOFF() && taishoJoken !=null) {
		if("JIGYOU"==taishoJoken || "SLAREA"==taishoJoken) {
			/**
			 * 宅配推移表タブの場合
			 * 下記の条件になる場合はalertを出す。（ok/cancel）
			 * MSG: 結果が表示されるまでに時間がかかります。よろしいですか？
			 * 対象条件	：事業本部 or 営業エリア
			 *
			 */
			 if(confirm(document.suiiRefForm.elements['suiiRefForm:jyokenMsg'].value)) {
			 	//はい
			 	callAction(actionMode);
			 	return;
			 }
			 else {
			 	//いいえ
			 	document.suiiRefForm.elements['suiiRefForm:loadingFlg'].value = "OFF";
			 	return;
			 }
		}
	 	//はい
	 	callAction(actionMode);
	}

}
//-->