/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.storemanage.posniporef.dto;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.storemanage.posniporef.common.PosNipoRefConstants;

/**
 * POS日報Dto
 * @author xwatanabe
 */
public class PosNipoRefDto extends CommonDto implements CsvOutputDto {

    /** ユーザタイプ */
    private String userTypeCd;
    /** オーナーコードMAP */
    private Map onerMap;
    
    //------------------------------------
    // 条件指定部分用
    //------------------------------------
    /** 会社コード  */
    private String companyCd;
    /** 会社コード  */
    private String companyName;
    /** 会社リスト */
    private List companyList;

    /** 期間 */
    private String kikan;
    /** 期間リスト */
    private List kikanList;
    
    /** 店舗コード */
    private String miseCd;
    /** 店舗名称 */
    private String miseName;
    /** 店舗リスト */
    private List miseList;
    
    /** オーナーコード */
    private String onerCd;
    /** オーナー名 */
    private String onerName;

    /** 検索区分(店舗・オーナーコード・全店) */
    private String kbn;
    /** 取得店舗数 */
    private int tenpoCnt;


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
    /** 再検索フラグ */
    private boolean researchFlg;

    
    //------------------------------------
    // 各タブ部分用
    //------------------------------------
    /** 表示タブインデックス */
    private int tabIndex;
    /** POS日報情報 */
    private List posNipoList;

    /**
     * クリアします。
     */
    public void clear(){
        companyCd       = null;
        companyName     = null;
        companyList     = null;
        kikan           = null;
        kikanList       = null;
        miseCd          = null;
        miseName        = null;
        miseList        = null;
        onerCd          = null;
        onerName        = null;
        kbn             = PosNipoRefConstants.KBN_MISE;     //検索区分を0(店舗)で初期化
        tenpoCnt        = 0;
        dispCompanyName = null;
        dispKikan       = null;
        dispMiseName    = null;
        dispTenpoCnt    = null;
        researchFlg     = false;                            //再検索フラグOFF
        tabIndex        = 1;
        posNipoList     = null;
    }

    /**
     * 結果部分の内容をクリアします。
     */
    public void resultClear(){

        dispCompanyName = null;
        dispKikan       = null;
        dispMiseName    = null;
        dispTenpoCnt    = null;
        tenpoCnt        = 0;
        researchFlg     = false;
        posNipoList     = null;
    }
    
    
    ////////////以下、セッター・ゲッター//////////////

    public List getCompanyList() {
        return companyList;
    }
    
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }


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

    /**
     * @return dispMiseName を戻します。
     */
    public String getDispMiseName() {
        return dispMiseName;
    }

    /**
     * @param dispMiseName 設定する dispMiseName。
     */
    public void setDispMiseName(String dispMiseName) {
        this.dispMiseName = dispMiseName;
    }

    /**
     * @return userTypeCd を戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }

    /**
     * @param userTypeCd 設定する userTypeCd。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    /**
     * @return kikan を戻します。
     */
    public String getKikan() {
        return kikan;
    }

    /**
     * @param kikan 設定する kikan。
     */
    public void setKikan(String kikan) {
        this.kikan = kikan;
    }

    /**
     * @return kikanList を戻します。
     */
    public List getKikanList() {
        return kikanList;
    }

    /**
     * @param kikanList 設定する kikanList。
     */
    public void setKikanList(List list) {
        this.kikanList = list;
    }

    /**
     * @return miseList を戻します。
     */
    public List getMiseList() {
        return miseList;
    }

    /**
     * @param miseList 設定する miseList。
     */
    public void setMiseList(List miseList) {
        this.miseList = miseList;
    }

    /**
     * @return miseName を戻します。
     */
    public String getMiseName() {
        return miseName;
    }

    /**
     * @param miseName 設定する miseName。
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }

    /**
     * @return onerName を戻します。
     */
    public String getOnerName() {
        return onerName;
    }

    /**
     * @param onerName 設定する onerName。
     */
    public void setOnerName(String onerName) {
        this.onerName = onerName;
    }

    /**
     * @return tenpoCnt を戻します。
     */
    public int getTenpoCnt() {
        return tenpoCnt;
    }

    /**
     * @param tenpoCnt 設定する tenpoCnt。
     */
    public void setTenpoCnt(int tenpoCnt) {
        this.tenpoCnt = tenpoCnt;
    }

    /**
     * @return tabIndex を戻します。
     */
    public int getTabIndex() {
        return tabIndex;
    }

    /**
     * @param tabIndex 設定する tabIndex。
     */
    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    
    /**
     * POS日報リストを戻します。
     */
    public List getPosNipoList() {
        return posNipoList;
    }
    /**
     * POS日報リストを設定する。
     */
    public void setPosNipoList(List posNipoList) {
        this.posNipoList = posNipoList;
    }
    /**
     * POS日報リストサイズを取得する。
     */
    public int getPosNipoListSize() {
        if(posNipoList == null){
            return 0;
        }else{
            return posNipoList.size();
        }
    }

    /**
     * @return dispCompanyName を戻します。
     */
    public String getDispCompanyName() {
        return dispCompanyName;
    }

    /**
     * @param dispCompanyName 設定する dispCompanyName。
     */
    public void setDispCompanyName(String dispCompanyName) {
        this.dispCompanyName = dispCompanyName;
    }

    /**
     * @return dispKikan を戻します。
     */
    public String getDispKikan() {
        return dispKikan;
    }

    /**
     * @param dispKikan 設定する dispKikan。
     */
    public void setDispKikan(String dispKikan) {
        this.dispKikan = dispKikan;
    }

    /**
     * @return dispTenpoCnt を戻します。
     */
    public String getDispTenpoCnt() {
        return dispTenpoCnt;
    }

    /**
     * @param dispTenpoCnt 設定する dispTenpoCnt。
     */
    public void setDispTenpoCnt(String dispTenpoCnt) {
        this.dispTenpoCnt = dispTenpoCnt;
    }


    /**
     * @return researchFlg を戻します。
     */
    public boolean isResearchFlg() {
        return researchFlg;
    }


    /**
     * @param researchFlg 設定する researchFlg。
     */
    public void setResearchFlg(boolean researchFlg) {
        this.researchFlg = researchFlg;
    }

    /**
     * @return onerMap を戻します。
     */
    public Map getOnerMap() {
        return onerMap;
    }

    /**
     * @param onerMap 設定する onerMap。
     */
    public void setOnerMap(Map onerMap) {
        this.onerMap = onerMap;
    }

    /**
     * @return companyName を戻します。
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName 設定する companyName。
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}