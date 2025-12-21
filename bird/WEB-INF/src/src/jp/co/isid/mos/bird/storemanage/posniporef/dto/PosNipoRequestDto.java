/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.storemanage.posniporef.dto;

import java.util.List;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.storemanage.posniporef.common.PosNipoRefConstants;

/**
 * POS日報DTO(REQUEST)
 * @author xwatanabe
 */
public class PosNipoRequestDto implements CsvOutputDto {

    /** WINDOW_ID */
    private int windowId;
    
    //------------------------------------
    // 条件指定部分用
    //------------------------------------
    /** 会社コード  */
    private String companyCd;
    /** 期間 */
    private String kikan;
    /** 店舗コード */
    private String miseCd;
    /** 店舗リスト */
    private List miseList;
    /** オーナーコード */
    private String onerCd;
    /** 検索区分(店舗・オーナーコード・全店) */
    private String kbn;

    //------------------------------------
    // 前回検索時・検索条件
    //------------------------------------
    /** 前回検索時・会社コード */
    private String companyCdZen;
    /** 前回検索時・期間 */
    private String kikanZen;
    /** 前回検索時・店舗コード */
    private String miseCdZen;
    /** 前回検索時・オーナーコード */
    private String onerCdZen;
    /** 前回検索時・検索区分 */
    private String kbnZen;

    //------------------------------------
    // 条件確認部分用
    //------------------------------------
    /** 表示用：会社名称 */
    private String dispCompanyName;
    /** 表示用：期間(YYYY年MM月) */
    private String dispKikan;
    /** 表示用：店舗名称 */
    private String dispMiseName;
    /** 表示用：取得店舗数 */
    private String dispTenpoCnt;

    //------------------------------------
    // 結果部分用
    //------------------------------------
    /** 取得店舗数 */
    private int tenpoCnt;
    /** POS日報情報 */
    private List posNipoList;
    /** 表示タブインデックス(メインタブ) */
    private int tabIndex;
    /** 表示タブインデックス(サブタブ) */
    private int subTabIndex;
    /** データ存在フラグ */
    private boolean existDataFlg;
    /** 再検索フラグ */
    private boolean researchFlg;

    /**
     * クリアします。
     */
    public void clear(){
        companyCd       = null;
        kikan           = null;
        miseCd          = null;
        miseList        = null;
        onerCd          = null;
        kbn             = PosNipoRefConstants.KBN_MISE;         //検索区分を1(店舗)で初期化
        companyCdZen    = null;
        kikanZen        = null;
        miseCdZen       = null;
        onerCdZen       = null;
        kbnZen          = PosNipoRefConstants.KBN_MISE;         //検索区分を1(店舗)で初期化
        dispCompanyName = null;
        dispKikan       = null;
        dispMiseName    = null;
        dispTenpoCnt    = null;
        tenpoCnt        = 0;
        posNipoList     = null;
        tabIndex        = PosNipoRefConstants.TAB_INDEX_NIPO;       //表示タブ(日報)
        subTabIndex     = PosNipoRefConstants.SUB_TAB_INDEX_JITU;   //サブタブ(実際原価)
        existDataFlg    = false;
        researchFlg     = false;                                   //再検索フラグOFF
    }

    ////////////以下、セッター・ゲッター//////////////

    public String getKbn() {
        return kbn;
    }

    public void setKbn(String kbn) {
        this.kbn = kbn;
    }

    public String getMiseCd() {
        return miseCd;
    }

    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    public String getOnerCd() {
        return onerCd;
    }

    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getKikan() {
        return kikan;
    }

    public void setKikan(String kikan) {
        this.kikan = kikan;
    }

    public List getMiseList() {
        return miseList;
    }

    public void setMiseList(List miseList) {
        this.miseList = miseList;
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

    public String getDispCompanyName() {
        return dispCompanyName;
    }

    public void setDispCompanyName(String dispCompanyName) {
        this.dispCompanyName = dispCompanyName;
    }

    public String getDispKikan() {
        return dispKikan;
    }

    public void setDispKikan(String dispKikan) {
        this.dispKikan = dispKikan;
    }

    public String getDispMiseName() {
        return dispMiseName;
    }

    public void setDispMiseName(String dispMiseName) {
        this.dispMiseName = dispMiseName;
    }

    public String getDispTenpoCnt() {
        return dispTenpoCnt;
    }

    public void setDispTenpoCnt(String dispTenpoCnt) {
        this.dispTenpoCnt = dispTenpoCnt;
    }

    public List getPosNipoList() {
        return posNipoList;
    }

    public void setPosNipoList(List posNipoList) {
        this.posNipoList = posNipoList;
    }

    public int getPosNipoListSize() {
        if(posNipoList == null || posNipoList.size() == 0){
            return 0;
        }
        return posNipoList.size();
    }

    public int getTenpoCnt() {
        return tenpoCnt;
    }

    public void setTenpoCnt(int tenpoCnt) {
        this.tenpoCnt = tenpoCnt;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public String getCompanyCdZen() {
        return companyCdZen;
    }

    public void setCompanyCdZen(String companyCdZen) {
        this.companyCdZen = companyCdZen;
    }

    public String getKbnZen() {
        return kbnZen;
    }

    public void setKbnZen(String kbnZen) {
        this.kbnZen = kbnZen;
    }

    public String getKikanZen() {
        return kikanZen;
    }

    public void setKikanZen(String kikanZen) {
        this.kikanZen = kikanZen;
    }

    public String getMiseCdZen() {
        return miseCdZen;
    }

    public void setMiseCdZen(String miseCdZen) {
        this.miseCdZen = miseCdZen;
    }

    public String getOnerCdZen() {
        return onerCdZen;
    }

    public void setOnerCdZen(String onerCdZen) {
        this.onerCdZen = onerCdZen;
    }

    public int getSubTabIndex() {
        return subTabIndex;
    }

    public void setSubTabIndex(int subTabIndex) {
        this.subTabIndex = subTabIndex;
    }

    public boolean isExistDataFlg() {
        return existDataFlg;
    }

    public void setExistDataFlg(boolean existDataFlg) {
        this.existDataFlg = existDataFlg;
    }
}