package jp.co.isid.mos.bird.storemanage.msschousadataref.entity;

import java.util.List;

/**
 * タブデータ保持Entityクラス
 * 
 * @author xkinu
 *
 */
public class UITabData {
    
    public static final String TABLE = "";
    
    public static final String tabCode_COLUMN = "TAB_CODE";
    
    public static final String tabName_COLUMN = "TAB_NAME";
    
    public static final String id_COLUMN = "ID";
    
    public static final String titleAreaId_COLUMN = "TITLE_AREA_ID";
    public static final String dataAreaId_COLUMN = "DATA_AREA_ID";
    
    public static final String anchorId_COLUMN = "ANCHOR_ID";

    public static final String dataCnt_COLUMN = "DATA_CNT";
    
    /**
     * タブINDEX
     */
    private int tabIndex = 0;
    /**
     * タブコード
     */
    private String tabCode = "";
    /**
     * タブ名称
     * 　例）タブコードが#0 第#0回
     */
    private String tabName = "&nbsp;";
    
    /**
     * タブエリアID
     */
    private String id;
    
    /**
     * タブ対象データテーブルヘッダーエリアID
     */
    private String titleAreaId;
    
    /**
     * タブ対象データエリアID
     */
    private String dataAreaId;
    
    /**
     * タブアンカーID
     */
    private String anchorId;
    /**
     * データ件数
     */
    private int dataCnt;
    /**
     * タブ対象データ
     */
    private List listData;
    
    /**
     * タブINDEXを取得します。
     * @return タブINDEX
     */
    public int getTabIndex() {
        return tabIndex;
    }
    /**
     * タブINDEXを設定します。
     * @param tabIndex タブINDEX
     */
    public void setTabIndex(int index) {
        this.tabIndex = index;
    }
    
    /**
     * タブコードを取得します。
     * @return タブコード
     */
    public String getTabCode() {
        if(tabCode == null) {
            return "";
        }
        return tabCode;
    }
    /**
     * タブコードを設定します。
     * @param tabCode タブコード
     */
    public void setTabCode(String tabCode) {
        this.tabCode = tabCode;
    }
    /**
     * タブ名称を取得します。
     * @return タブ名称
     */
    public String getTabName() {
        if(tabName == null) {
            return "&nbsp;";
        }
        return tabName;
    }
    /**
     * タブ名称を設定します。
     * @param tabName タブ名称
     */
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
    
    /**
     * タブ対象データテーブルヘッダーエリアIDを取得します。
     * @return タブエリアID
     */
    public String getTitleAreaId() {
        if(titleAreaId == null) {
            return "tab_title_area_"+getTabIndex();
        }
        return titleAreaId;
    }
    /**
     * タブ対象データテーブルヘッダーエリアIDを設定します。
     * @param id タブエリアID
     */
    public void setTitleAreaId(String id) {
        this.titleAreaId = id;
    }
    
    /**
     * タブ対象データテーブルヘッダーエリアIDを取得します。
     * @return タブエリアID
     */
    public String getDataAreaId() {
        if(dataAreaId == null) {
            return "tab_data_area_"+getTabIndex();
        }
        return dataAreaId;
    }
    /**
     * タブ対象データテーブルヘッダーエリアIDを設定します。
     * @param dataAreaId タブエリアID
     */
    public void setDataAreaId(String id) {
        this.dataAreaId = id;
    }
    
    /**
     * タブエリアIDを取得します。
     * @return タブエリアID
     */
    public String getId() {
        if(id == null) {
            return "tab_area_"+getTabIndex();
        }
        return id;
    }
    /**
     * タブエリアIDを設定します。
     * @param id タブエリアID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * タブアンカーIDを取得します。
     * @return タブアンカーID
     */
    public String getAnchorId() {
        if(anchorId == null) {
            return "tab_anchor_area_"+getTabIndex();
        }
        return anchorId;
    }
    /**
     * タブアンカーIDを設定します。
     * @param anchorId タブアンカーID
     */
    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }
    
    /**
     * タブアンカーHREFを取得します。
     * @return 売上予算
     */
    public String getAnchorHref() {
        return "javascript:changeTaB('"+getId()+"','"+getTitleAreaId()+"', '"+getAnchorId()+"','"+getDataAreaId()+"');";
    }
    /**
     * データ件数を取得します。
     * @return データ件数
     */
    public int getDataCnt() {
        return dataCnt;
    }
    /**
     * データ件数を設定します。
     * @param dataCnt データ件数
     */
    public void setDataCnt(int index) {
        this.dataCnt = index;
    }
    /**
     * データを取得します。
     * @return データ
     */
    public List getListData() {
        return listData;
    }
    /**
     * データ設定します。
     * @param listData データ
     */
    public void setListData(List list) {
        this.listData = list;
    }
   
}
