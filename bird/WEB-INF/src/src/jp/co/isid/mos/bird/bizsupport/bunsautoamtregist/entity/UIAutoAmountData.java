package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;

/**
 * 自動設定数量エンティティー
 * 
 * 2007/03/12 ADD T.Kinugawa 
 *     LAST_TMSPが1111-11-11で始まる場合、FIRST_TMSPを最終更新日として表示する。
 * 
 * @author xkinu
 *
 */
public class UIAutoAmountData {
    
    public static final String TABLE = "TM28SAUT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sokoCd_COLUMN = "SOKO_CD";
    
    public static final String sokoNameKj_COLUMN = "SOKO_NAME_KJ";
    
    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";
    
    public static final String shoNameKj_COLUMN = "SHO_NAME_KJ";
    
    public static final String shoNisugata_COLUMN = "SHO_NISUGATA";
    
    public static final String nisuName_COLUMN = "NISU_NAME";
    
    public static final String unitJ_COLUMN = "UNIT_J";
    
    public static final String amtWeek_COLUMN = "AMT_WEEK";
    
    public static final String amtDatd_COLUMN = "AMT_SATD";
    
    public static final String amtHold_COLUMN = "AMT_HOLD";
    
    public static final String strAmtWeek_COLUMN = "STR_AMT_WEEK";
    
    public static final String strAmtDatd_COLUMN = "STR_AMT_SATD";
    
    public static final String strAmtHold_COLUMN = "STR_AMT_HOLD";

    public static final String openDt_COLUMN = "OPEN_DT";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    public static final String mstVer_COLUMN = "MST_VER";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 管理会社企業名称
     */
    private String companyName;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店舗名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 倉庫コード
     */
    private String sokoCd;
    
    /**
     * 倉庫名称（漢字）
     */
    private String sokoNameKj;
    
    /**
     * 代表商品コード
     */
    private String shoCdDai;
    
    /**
     * 代表商品名称
     */
    private String shoNameKj;
    
    /**
     * 商品荷姿コード
     */
    private String shoNisugata;
    
    /**
     * 商品荷姿名称
     */
    private String nisuName;
    
    /**
     * 受注単位区分
     */
    private String unitJ;
    
    /**
     * 平日納品
     */
    private java.math.BigDecimal amtWeek;
    
    /**
     * 土曜納品
     */
    private java.math.BigDecimal amtSatd;
    
    /**
     * 日祝納品
     */
    private java.math.BigDecimal amtHold;
    
    /**
     * 平日納品
     */
    private String strAmtWeek;
    
    /**
     * 土曜納品
     */
    private  String strAmtSatd;
    
    /**
     * 日祝納品
     */
    private  String strAmtHold;
    /**
     * オープン日
     */
    private String openDt;
    /**
     * 登録ユーザー
     */
    private String firstUser = "";
    
    /**
     * 登録プログラム
     */
    private String firstPgm = "";
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser = "";
    
    /**
     * 修正プログラム
     */
    private String lastPgm = "";
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    /**
     * マスターヴァージョン
     */
    private Integer mstVer;
    /**
     * @return firstPgm を戻します。
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * @param firstPgm 設定する firstPgm。
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    /**
     * @return firstTmsp を戻します。
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * @param firstTmsp 設定する firstTmsp。
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    /**
     * @return firstUser を戻します。
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * @param firstUser 設定する firstUser。
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    /**
     * @return lastPgm を戻します。
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * @param lastPgm 設定する lastPgm。
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    /**
     * @return lastUser を戻します。
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * @param lastUser 設定する lastUser。
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    /**
     * @return lastTmsp を戻します。
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * @param lastTmsp 設定する lastTmsp。
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 管理会社企業名称を取得します。
     * @return 管理会社企業名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 管理会社企業名称を設定します。
     * @param companyName 管理会社企業名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称（漢字）を取得します。
     * @return 店舗名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称（漢字）を設定します。
     * @param miseNameKj 店舗名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 倉庫コードを取得します。
     * @return 倉庫コード
     */
    public String getSokoCd() {
        return sokoCd;
    }
    /**
     * 倉庫コードを設定します。
     * @param sokoCd 倉庫コード
     */
    public void setSokoCd(String sokoCd) {
        this.sokoCd = sokoCd;
    }
    
    /**
     * 倉庫名称（漢字）を取得します。
     * @return 倉庫名称（漢字）
     */
    public String getSokoNameKj() {
        return sokoNameKj;
    }
    /**
     * 倉庫名称（漢字）を設定します。
     * @param sokoNameKj 倉庫名称（漢字）
     */
    public void setSokoNameKj(String sokoNameKj) {
        this.sokoNameKj = sokoNameKj;
    }
    
    /**
     * 代表商品コードを取得します。
     * @return 代表商品コード
     */
    public String getShoCdDai() {
        return shoCdDai;
    }
    /**
     * 代表商品コードを設定します。
     * @param shoCdDai 代表商品コード
     */
    public void setShoCdDai(String shoCdDai) {
        this.shoCdDai = shoCdDai;
    }
    
    /**
     * 代表商品名称を取得します。
     * @return 代表商品名称
     */
    public String getShoNameKj() {
        return shoNameKj;
    }
    /**
     * 代表商品名称を設定します。
     * @param shoNameKj 代表商品名称
     */
    public void setShoNameKj(String shoNameKj) {
        this.shoNameKj = shoNameKj;
    }
    
    /**
     * 商品荷姿コードを取得します。
     * @return 商品荷姿コード
     */
    public String getShoNisugata() {
        return shoNisugata;
    }
    /**
     * 商品荷姿コードを設定します。
     * @param shoNisugata 商品荷姿コード
     */
    public void setShoNisugata(String shoNisugata) {
        this.shoNisugata = shoNisugata;
    }
    
    /**
     * 商品荷姿名称を取得します。
     * @return 商品荷姿名称
     */
    public String getNisuName() {
        return nisuName;
    }
    /**
     * 商品荷姿名称を設定します。
     * @param nisuName 商品荷姿名称
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }
    
    /**
     * 平日納品を取得します。
     * @return 平日納品
     */
    public java.math.BigDecimal getAmtWeek() {
        return amtWeek;
    }
    /**
     * 平日納品を設定します。
     * @param amtWeek 平日納品
     */
    public void setAmtWeek(java.math.BigDecimal amtWeek) {
        this.amtWeek = amtWeek;
        strAmtWeek =  String.valueOf(amtWeek);
    }
    
    /**
     * 土曜納品を取得します。
     * @return 土曜納品
     */
    public java.math.BigDecimal getAmtSatd() {
        return amtSatd;
    }
    /**
     * 土曜納品を設定します。
     * @param amtSatd 土曜納品
     */
    public void setAmtSatd(java.math.BigDecimal amtSatd) {
        this.amtSatd = amtSatd;
        strAmtSatd =  String.valueOf(amtSatd);
    }
    
    /**
     * 日祝納品を取得します。
     * @return 日祝納品
     */
    public java.math.BigDecimal getAmtHold() {
        return amtHold;
    }
    /**
     * 日祝納品を設定します。
     * @param amtHold 日祝納品
     */
    public void setAmtHold(java.math.BigDecimal amtHold) {
        this.amtHold = amtHold;
        strAmtHold =  String.valueOf(amtHold);
    }
    
    /**
     * オープン日を取得します。
     * @return オープン日
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * オープン日を設定します。
     * @param openDt オープン日
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    /**
     * @return strAmtSatd を戻します。
     */
    public String getStrAmtSatd() {
        return strAmtSatd;
    }
    /**
     * @param strAmtSatd 設定する strAmtSatd。
     */
    public void setStrAmtSatd(String strAmt) {
        if(!BunsAutoAmtRegistUtil.isNull(strAmt)) {
            this.strAmtSatd = strAmt.trim();
        }
        this.strAmtSatd = strAmt;
        try{
            this.amtSatd = new BigDecimal(strAmt);
        }
        catch(Exception ex){
            
        }
    }
    /**
     * @return strAmtHold を戻します。
     */
    public String getStrAmtHold() {
        return strAmtHold;
    }
    /**
     * @param strAmtHold 設定する strAmtHold。
     */
    public void setStrAmtHold(String strAmt) {
        if(!BunsAutoAmtRegistUtil.isNull(strAmt)) {
            this.strAmtHold = strAmt.trim();
        }
        this.strAmtHold = strAmt;
        try{
            this.amtHold = new BigDecimal(strAmt);
        }
        catch(Exception ex){
            
        }
    }
    /**
     * @return strAmtWeek を戻します。
     */
    public String getStrAmtWeek() {
        return strAmtWeek;
    }
    /**
     * @param strAmt 設定する strAmtWeek。
     */
    public void setStrAmtWeek(String strAmt) {
        if(!BunsAutoAmtRegistUtil.isNull(strAmt)) {
            strAmtWeek = strAmt.trim();
        }
        this.strAmtWeek = strAmt;
        try{
            this.amtWeek = new BigDecimal(strAmt);
        }
        catch(Exception ex){
            
        }
    }
    /**
     * 最終更新日取得処理
     * 
     * @return
     */
    public String getLastDt() {
        if (getLastTmsp() != null) {
            String lasttmspYMD = String.valueOf(getLastTmsp()).substring(0,10);
//2007/03/12 ADD start T.Kinugawa
            if("1111-11-11".equals(lasttmspYMD)) {
                //LAST_TMSPが1111-11-11で始まる場合、FIRST_TMSPを最終更新日として表示する。
                lasttmspYMD = String.valueOf(getFirstTmsp()).substring(0,10);
            }
//2007/03/12 ADD end T.Kinugawa
            String lastDt = lasttmspYMD.replaceAll("-", "");
            return lastDt;
        }
        return "";
    }
    /**
     * @return unitJ を戻します。
     */
    public String getUnitJ() {
        return unitJ;
    }
    /**
     * @param unitJ 設定する unitJ。
     */
    public void setUnitJ(String unitJ) {
        this.unitJ = unitJ;
    }
    /**
     * @return mstVer を戻します。
     */
    public Integer getMstVer() {
        return mstVer;
    }
    /**
     * @param mstVer 設定する mstVer。
     */
    public void setMstVer(Integer mstVer) {
        this.mstVer = mstVer;
    }
    /**
     * マスターヴァージョンカウントアップ処理
     *
     */
    public void countUpMstVer() {
        if(mstVer == null) {
            mstVer = new Integer(0);
        }
        mstVer = new Integer(mstVer.intValue()+1);
    }
}
