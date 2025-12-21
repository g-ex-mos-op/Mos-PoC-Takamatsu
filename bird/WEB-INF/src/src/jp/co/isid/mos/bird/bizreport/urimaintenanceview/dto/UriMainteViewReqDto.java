/*
 * 作成日:2007/02/27
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;

/**
 * 売上修正確認リクエストDTO
 * @author xwatanabe
 */
public class UriMainteViewReqDto {

    /** WINDOW_ID */
    private int windowId;
    
    //------------------------------------
    // 条件指定部分用
    //------------------------------------
    /** 会社コード  */
    private String companyCd;
    /** 修正日 */
    private String syuseiDate;

    //------------------------------------
    // 前回検索時・検索条件
    //------------------------------------
    /** 前回検索時・会社コード */
    private String companyCdZen;
    /** 前回検索時・修正日 */
    private String syuseiDateZen;

    //------------------------------------
    // 結果部分用
    //------------------------------------
    /** 売上修正履歴 */
    private List uriageSyuseiRirekiList;
    /** 表示タブインデックス(メインタブ) */
    private int tabIndexMain;
    /** 表示タブインデックス(サブタブ・前受売掛) */
    private int tabIndexSub1;
    /** 表示タブインデックス(サブタブ・販売) */
    private int tabIndexSub2;
    /** 表示タブインデックス(サブタブ・値引き) */
    private int tabIndexSub3;
    /** データ存在フラグ */
    private boolean existDataFlg;
    /** 再検索フラグ */
    private boolean researchFlg;
    /** ヘッダー */
    private UriMainteHeader header;

    /**
     * クリアします。
     */
    public void clear(){
        companyCd       = null;
        syuseiDate      = null;
        companyCdZen    = null;
        syuseiDateZen   = null;
        tabIndexMain    = UriMainteViewConstants.TAB_INDEX_URIAGE;  //表示タブ(売上金)
        tabIndexSub1    = UriMainteViewConstants.TAB_INDEX_SUB_MAEUKE;   //サブタブ
        tabIndexSub2    = UriMainteViewConstants.TAB_INDEX_SUB_HANBAI;   //サブタブ
        tabIndexSub3    = UriMainteViewConstants.TAB_INDEX_SUB_NEBIKI;   //サブタブ
        existDataFlg    = false;
        researchFlg     = false;                                   //再検索フラグOFF
        uriageSyuseiRirekiList = null;
    }

    ////////////以下、セッター・ゲッター//////////////

    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getSyuseiDate() {
        return syuseiDate;
    }
    public void setSyuseiDate(String syuseiDate) {
        this.syuseiDate = syuseiDate;
    }

    public String getCompanyCdZen() {
        return companyCdZen;
    }
    public void setCompanyCdZen(String companyCdZen) {
        this.companyCdZen = companyCdZen;
    }

    public String getSyuseiDateZen() {
        return syuseiDateZen;
    }
    public void setSyuseiDateZen(String syuseiDateZen) {
        this.syuseiDateZen = syuseiDateZen;
    }

    public List getUriageSyuseiRirekiList() {
        return uriageSyuseiRirekiList;
    }
    public void setUriageSyuseiRirekiList(List uriageSyuseiRirekiList) {
        this.uriageSyuseiRirekiList = uriageSyuseiRirekiList;
    }

    public int getTabIndexMain() {
        return tabIndexMain;
    }
    public void setTabIndexMain(int tabIndexMain) {
        this.tabIndexMain = tabIndexMain;
    }
    
    public int getTabIndexSub1() {
        return tabIndexSub1;
    }
    public void setTabIndexSub1(int tabIndexSub1) {
        this.tabIndexSub1 = tabIndexSub1;
    }
    
    public int getTabIndexSub2() {
        return tabIndexSub2;
    }
    public void setTabIndexSub2(int tabIndexSub2) {
        this.tabIndexSub2 = tabIndexSub2;
    }

    public int getTabIndexSub3() {
        return tabIndexSub3;
    }
    public void setTabIndexSub3(int tabIndexSub3) {
        this.tabIndexSub3 = tabIndexSub3;
    }
    
    public boolean isExistDataFlg() {
        return existDataFlg;
    }
    public void setExistDataFlg(boolean existDataFlg) {
        this.existDataFlg = existDataFlg;
    }
    
    public boolean isResearchFlg() {
        return researchFlg;
    }
    public void setResearchFlg(boolean researchFlg) {
        this.researchFlg = researchFlg;
    }
    
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    /**
     * @return header を戻します。
     */
    public UriMainteHeader getHeader() {
        return header;
    }

    /**
     * @param header 設定する header。
     */
    public void setHeader(UriMainteHeader header) {
        this.header = header;
    }
}