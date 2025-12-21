package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;

/**
 * 予約販売管理マスタ登録DTO
 * 
 * @author xkinu
 *
 */
public class MosChickenMstRegistDto {
    /**
     * ユーザーID
     */
    private String userId ="";
    /**
     * システム日付
     */
    private String sysDate = "";
    /**
     * アプリ日付
     */
    private String appDate = "";
    /**
     * 対象開始日
     */
    private String targetStartDt = "";
    
    /* 条件項目：[[キャンペーン情報リスト]] */
    private List listCamp;
    /* 検索条件：管理番号 */
    private String targetCkanriNo = "";
    /* 検索結果：[[管理対象期間リスト]] */
    private List listKanriKikan;
    /* 検索結果：[[管理対象メニューグループ]] */
    private List listKanriMenuGroup;
    /* 検索結果：[[管理対象メニュー]] */
    private List listKanriMenu;
    /* 処理対象：処理タイプ */
    private String modeType = MosChickenMstRegistUtil.MODETYPE_VIEW;
    /* 処理対象：メニューグループコード */
    private String targetMenuGroupCd = "";
    /* 処理対象：メニューコード */
    private String targetMenuCd = "";
    /* 処理対象：メニューコード */
    private String targetMenuName = "";
    /* 処理対象：変更対象メニューの開始インデックス */
    private String targetMGroupIndex = "-1";
    /* 処理対象：変更対象メニューの開始インデックス */
    private String menuStartIndex = "-1";
    private int newMenuGroupCounter = 0;
    /* 
     * 処理対象：処理確定直前アクション
     * タブのフォーカスや、入力項目のフォーカス制御用の判断値です。
     */
    private String compliteActionType="";
    /**
     * @return menuStartIndex を戻します。
     */
    public String getMenuStartIndex() {
        return menuStartIndex;
    }

    /**
     * @param menuStartIndex 設定する menuStartIndex。
     */
    public void setMenuStartIndex(String menuStartIndex) {
        this.menuStartIndex = menuStartIndex;
    }

    /**
     * @return listCamp を戻します。
     */
    public List getListCamp() {
        return listCamp;
    }

    /**
     * @param listCamp 設定する listCamp。
     */
    public void setListCamp(List listCamp) {
        this.listCamp = listCamp;
    }
    /**
     * @param listCamp 設定する listCamp。
     */
    public int getListCampSize() {
        if(this.listCamp == null){
            return 0;
        }
        return this.listCamp.size();
    }

    /**
     * @return listKanriKikan を戻します。
     */
    public List getListKanriKikan() {
        return listKanriKikan;
    }

    /**
     * @param listKanriKikan 設定する listKanriKikan。
     */
    public void setListKanriKikan(List listKanriKikan) {
        this.listKanriKikan = listKanriKikan;
    }

    /**
     * @return listKanriMenu を戻します。
     */
    public List getListKanriMenu() {
        return listKanriMenu;
    }

    /**
     * @param listKanriMenu 設定する listKanriMenu。
     */
    public void setListKanriMenu(List listKanriMenu) {
        this.listKanriMenu = listKanriMenu;
    }

    /**
     * @return listKanriMenuGroup を戻します。
     */
    public List getListKanriMenuGroup() {
        return listKanriMenuGroup;
    }

    /**
     * @param listKanriMenuGroup 設定する listKanriMenuGroup。
     */
    public void setListKanriMenuGroup(List listKanriMenuGroup) {
        this.listKanriMenuGroup = listKanriMenuGroup;
    }

    /**
     * @return targetMGroupIndex を戻します。
     */
    public String getTargetMGroupIndex() {
        return targetMGroupIndex;
    }

    /**
     * @param targetMGroupIndex 設定する targetMGroupIndex。
     */
    public void setTargetMGroupIndex(String targetMGroupIndex) {
        this.targetMGroupIndex = targetMGroupIndex;
    }

    /**
     * クリア処理
     *
     */
    public void clear() {
        setTargetMenuCd(null);
        setTargetMenuGroupCd(null);
        setListKanriKikan(null);
        setListKanriMenuGroup(null);
        setListKanriMenu(null);
        setTargetMGroupIndex("-1");
        setMenuStartIndex("-1");
        setCompliteActionType("init");
        setNewMenuGroupCounter(0);
        
    }

    /**
     * @return modeType を戻します。
     */
    public String getModeType() {
        return modeType;
    }

    /**
     * @param modeType 設定する modeType。
     */
    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    /**
     * @return sysDate を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * @param sysDate 設定する sysDate。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * @return targetStartDt を戻します。
     */
    public String getTargetStartDt() {
        return targetStartDt;
    }

    /**
     * @param targetStartDt 設定する targetStartDt。
     */
    public void setTargetStartDt(String targetStartDt) {
        this.targetStartDt = targetStartDt;
    }

    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId 設定する userId。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return appDate を戻します。
     */
    public String getAppDate() {
        return appDate;
    }

    /**
     * @param appDate 設定する appDate。
     */
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    /**
     * @return targetMenuCd を戻します。
     */
    public String getTargetMenuCd() {
        return targetMenuCd;
    }

    /**
     * @param targetMenuCd 設定する targetMenuCd。
     */
    public void setTargetMenuCd(String targetMenuCd) {
        this.targetMenuCd = targetMenuCd;
    }

    /**
     * @return targetMenuGroupCd を戻します。
     */
    public String getTargetMenuGroupCd() {
        return targetMenuGroupCd;
    }

    /**
     * @param targetMenuGroupCd 設定する targetMenuGroupCd。
     */
    public void setTargetMenuGroupCd(String targetMenuGroupCd) {
        this.targetMenuGroupCd = targetMenuGroupCd;
    }

    /**
     * @return targetCkanriNo を戻します。
     */
    public String getTargetCkanriNo() {
        return targetCkanriNo;
    }

    /**
     * @param targetCkanriNo 設定する targetCkanriNo。
     */
    public void setTargetCkanriNo(String targetCkanriNo) {
        this.targetCkanriNo = targetCkanriNo;
    }

    /**
     * @return compliteActionType を戻します。
     */
    public String getCompliteActionType() {
        return compliteActionType;
    }

    /**
     * @param compliteActionType 設定する compliteActionType。
     */
    public void setCompliteActionType(String compliteActionType) {
        this.compliteActionType = compliteActionType;
    }

    /**
     * @return newMenuGroupCounter を戻します。
     */
    public int getNewMenuGroupCounter() {
        return newMenuGroupCounter;
    }

    /**
     * @param newMenuGroupCounter 設定する newMenuGroupCounter。
     */
    public void setNewMenuGroupCounter(int newMenuGroupCounter) {
        this.newMenuGroupCounter = newMenuGroupCounter;
    }

    /**
     * @return targetMenuName を戻します。
     */
    public String getTargetMenuName() {
        return targetMenuName;
    }

    /**
     * @param targetMenuName 設定する targetMenuName。
     */
    public void setTargetMenuName(String targetMenuName) {
        this.targetMenuName = targetMenuName;
    }

}
