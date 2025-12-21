package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 予約・在庫状況情報（モスチキン）
 * 
 * @author xkinu
 *
 */
public class UIMCStatusInfo {
    
    public static final String TABLE = "BT69CZIN";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String title_COLUMN = "TITLE";
    
    public static final String targetFrom_COLUMN = "TARGET_FROM";
    
    public static final String targetTo_COLUMN = "TARGET_TO";
    
    public static final String defaultFrom_COLUMN = "DEFAULT_FROM";
    
    public static final String defaultTo_COLUMN = "DEFAULT_TO";
    
    public static final String yobiFrom_COLUMN = "YOBI_FROM";
    
    public static final String yobiTo_COLUMN = "YOBI_TO";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";

    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";
    
    public static final String shokuNameKna_COLUMN = "SHOKU_NAME_KNA";
    
    public static final String irime1_COLUMN = "IRIME1";
    
    public static final String zenjitsuZ_COLUMN = "ZENJITSU_Z";
    
    public static final String amtCase_COLUMN = "AMT_CASE";
    
    public static final String amtBara_COLUMN = "AMT_BARA";
    
    public static final String amtBl_COLUMN = "AMT_BL";
    
    public static final String shoAmtBara_COLUMN = "SHO_AMT_BARA";
    
    public static final String reserveConvAmt_COLUMN = "RESERVE_CONV_AMT";
    
    public static final String kanouAmt_COLUMN = "KANOU_AMT";
    
    public static final String tojitsuYz_COLUMN = "TOJITSU_YZ";
    
    public static final String tojitsuZz_COLUMN = "TOJITSU_ZZ";
    
    public static final String yokujitsuShoAmtBara_COLUMN = "YOKUJITSU_SHO_AMT_BARA";
    
    public static final String yokujitsuReserveConvAmt_COLUMN = "YOKUJITSU_RESERVE_CONV_AMT";
    
    public static final String yokujitsuZz_COLUMN = "YOKUJITSU_ZZ";
    
    public static final String strAmtCase_COLUMN = "STR_AMT_CASE";
    
    public static final String strAmtBara_COLUMN = "STR_AMT_BARA";
    
    public static final String strAmtBl_COLUMN = "STR_AMT_BL";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * タイトル
     */
    private String title;
    
    /**
     * 対象期間FROM
     */
    private String targetFrom;
    
    /**
     * 対象期間TO
     */
    private String targetTo;
    
    /**
     * デフォルト表示期間FROM
     */
    private String defaultFrom;
    
    /**
     * デフォルト表示期間TO
     */
    private String defaultTo;
    
    /**
     * 予備期間FROM
     */
    private String yobiFrom;
    
    /**
     * 予備期間TO
     */
    private String yobiTo;
    
    /**
     * 会社jコード
     */
    private String companyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 対象営業日
     */
    private String eigyoDt;
    
    /**
     * 食包材コード
     */
    private String shoCdDai;
    
    /**
     * 食包材名称
     */
    private String shokuNameKna;
    
    /**
     * いり目１
     */
    private BigDecimal irime1;
    
    /**
     * 前日在庫数量
     */
    private BigDecimal zenjitsuZ;
    
    /**
     * 前日在庫（C/S)
     */
    private BigDecimal amtCase;
    
    /**
     * 前日在庫(バラ）
     */
    private BigDecimal amtBara;
    
    /**
     * 当日貸借（バラ）
     */
    private BigDecimal amtBl;
    
    /**
     * 当日入荷
     */
    private BigDecimal shoAmtBara;
    
    /**
     * 当日予約
     */
    private BigDecimal reserveConvAmt;
    
    /**
     * 当日・翌日販売可能数
     */
    private BigDecimal kanouAmt;
    
    /**
     * 当日在庫残
     */
    private BigDecimal tojitsuZz;
    
    /**
     * 翌日入荷
     */
    private BigDecimal yokujitsuShoAmtBara;
    
    /**
     * 翌日予約
     */
    private BigDecimal yokujitsuReserveConvAmt;
    
    /**
     * 翌日在庫残
     */
    private BigDecimal yokujitsuZz;
    
    /**
     * 前日在庫（C/S)
     */
    private String strAmtCase;
    
    /**
     * 前日在庫(バラ）
     */
    private String strAmtBara;
    
    /**
     * 当日貸借（バラ）
     */
    private String strAmtBl;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    /**
     * 当日予約残
     */
    private BigDecimal tojitsuYz;
    
    /**
     * 管理番号を取得します。
     * @return 管理番号
     */
    public String getCkanriNo() {
        return ckanriNo;
    }
    /**
     * 管理番号を設定します。
     * @param ckanriNo 管理番号
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle() {
        return title;
    }
    /**
     * タイトルを設定します。
     * @param title タイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 対象期間FROMを取得します。
     * @return 対象期間FROM
     */
    public String getTargetFrom() {
        return targetFrom;
    }
    /**
     * 対象期間FROMを設定します。
     * @param targetFrom 対象期間FROM
     */
    public void setTargetFrom(String targetFrom) {
        this.targetFrom = targetFrom;
    }
    
    /**
     * 対象期間TOを取得します。
     * @return 対象期間TO
     */
    public String getTargetTo() {
        return targetTo;
    }
    /**
     * 対象期間TOを設定します。
     * @param targetTo 対象期間TO
     */
    public void setTargetTo(String targetTo) {
        this.targetTo = targetTo;
    }
    
    /**
     * デフォルト表示期間FROMを取得します。
     * @return デフォルト表示期間FROM
     */
    public String getDefaultFrom() {
        return defaultFrom;
    }
    /**
     * デフォルト表示期間FROMを設定します。
     * @param defaultFrom デフォルト表示期間FROM
     */
    public void setDefaultFrom(String defaultFrom) {
        this.defaultFrom = defaultFrom;
    }
    
    /**
     * デフォルト表示期間TOを取得します。
     * @return デフォルト表示期間TO
     */
    public String getDefaultTo() {
        return defaultTo;
    }
    /**
     * デフォルト表示期間TOを設定します。
     * @param defaultTo デフォルト表示期間TO
     */
    public void setDefaultTo(String defaultTo) {
        this.defaultTo = defaultTo;
    }
    
    /**
     * 予備期間FROMを取得します。
     * @return 予備期間FROM
     */
    public String getYobiFrom() {
        return yobiFrom;
    }
    /**
     * 予備期間FROMを設定します。
     * @param yobiFrom 予備期間FROM
     */
    public void setYobiFrom(String yobiFrom) {
        this.yobiFrom = yobiFrom;
    }
    
    /**
     * 予備期間TOを取得します。
     * @return 予備期間TO
     */
    public String getYobiTo() {
        return yobiTo;
    }
    /**
     * 予備期間TOを設定します。
     * @param yobiTo 予備期間TO
     */
    public void setYobiTo(String yobiTo) {
        this.yobiTo = yobiTo;
    }
    
    /**
     * 会社jコードを取得します。
     * @return 会社jコード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社jコードを設定します。
     * @param companyCd 会社jコード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 食包材コードを取得します。
     * @return 食包材コード
     */
    public String getShoCdDai() {
        return shoCdDai;
    }
    /**
     * 食包材コードを設定します。
     * @param shoCdDai 食包材コード
     */
    public void setShoCdDai(String shoCdDai) {
        this.shoCdDai = shoCdDai;
    }
    
    /**
     * 食包材名称を取得します。
     * @return 食包材名称
     */
    public String getShokuNameKna() {
        return shokuNameKna;
    }
    /**
     * 食包材名称を設定します。
     * @param shokuNameKna 食包材名称
     */
    public void setShokuNameKna(String shokuNameKna) {
        this.shokuNameKna = shokuNameKna;
    }
    
    /**
     * 前日在庫（C/S)を取得します。
     * @return 前日在庫（C/S)
     */
    public BigDecimal getAmtCase() {
        return amtCase;
    }
    /**
     * 前日在庫（C/S)を設定します。
     * @param amtCase 前日在庫（C/S)
     */
    public void setAmtCase(BigDecimal amtCase) {
        this.amtCase = amtCase;
        this.strAmtCase = String.valueOf(amtCase);
    }
    
    /**
     * 前日在庫(バラ）を取得します。
     * @return 前日在庫(バラ）
     */
    public BigDecimal getAmtBara() {
        return amtBara;
    }
    /**
     * 前日在庫(バラ）を設定します。
     * @param amtBara 前日在庫(バラ）
     */
    public void setAmtBara(BigDecimal amtBara) {
        this.amtBara = amtBara;
        this.strAmtBara = String.valueOf(amtBara);
    }
    
    /**
     * 当日貸借（バラ）を取得します。
     * @return 当日貸借（バラ）
     */
    public BigDecimal getAmtBl() {
        return amtBl;
    }
    /**
     * 当日貸借（バラ）を設定します。
     * @param amtBl 当日貸借（バラ）
     */
    public void setAmtBl(BigDecimal amtBl) {
        this.amtBl = amtBl;
        this.strAmtBl = String.valueOf(amtBl);
    }
    
    /**
     * 当日入荷を取得します。
     * @return 当日入荷
     */
    public BigDecimal getShoAmtBara() {
        return shoAmtBara;
    }
    /**
     * 当日入荷を設定します。
     * @param shoAmt 当日入荷
     */
    public void setShoAmtBara(BigDecimal shoAmtBara) {
        this.shoAmtBara = shoAmtBara;
    }
    
    /**
     * 当日予約を取得します。
     * @return 当日予約
     */
    public BigDecimal getReserveConvAmt() {
        return reserveConvAmt;
    }
    /**
     * 当日予約を設定します。
     * @param reserveConvAmt 当日予約
     */
    public void setReserveConvAmt(BigDecimal reserveConvAmt) {
        this.reserveConvAmt = reserveConvAmt;
    }
    
    /**
     * 当日・翌日販売可能数を取得します。
     * @return 当日・翌日販売可能数
     */
    public BigDecimal getKanouAmt() {
        return kanouAmt;
    }
    /**
     * 当日・翌日販売可能数を設定します。
     * @param kanouAmt 当日・翌日販売可能数
     */
    public void setKanouAmt(BigDecimal kanouAmt) {
        this.kanouAmt = kanouAmt;
    }
    
    /**
     * 当日在庫残を取得します。
     * @return 当日在庫残
     */
    public BigDecimal getTojitsuZz() {
        return tojitsuZz;
    }
    /**
     * 当日在庫残を設定します。
     * @param tojitsuZz 当日在庫残
     */
    public void setTojitsuZz(BigDecimal tojitsuZz) {
        this.tojitsuZz = tojitsuZz;
    }
    
    /**
     * 翌日入荷を取得します。
     * @return 翌日入荷
     */
    public BigDecimal getYokujitsuShoAmtBara() {
        return yokujitsuShoAmtBara;
    }
    /**
     * 翌日入荷を設定します。
     * @param yokujitsuShoAmtBara 翌日入荷
     */
    public void setYokujitsuShoAmtBara(BigDecimal yokujitsuShoAmtBara) {
        this.yokujitsuShoAmtBara = yokujitsuShoAmtBara;
    }
    
    /**
     * 翌日予約を取得します。
     * @return 翌日予約
     */
    public BigDecimal getYokujitsuReserveConvAmt() {
        return yokujitsuReserveConvAmt;
    }
    /**
     * 翌日予約を設定します。
     * @param yokujitsuReserveConvAmt 翌日予約
     */
    public void setYokujitsuReserveConvAmt(BigDecimal yokujitsuReserveConvAmt) {
        this.yokujitsuReserveConvAmt = yokujitsuReserveConvAmt;
    }
    
    /**
     * 翌日在庫残を取得します。
     * @return 翌日在庫残
     */
    public BigDecimal getYokujitsuZz() {
        return yokujitsuZz;
    }
    /**
     * 翌日在庫残を設定します。
     * @param yokujitsuZz 翌日在庫残
     */
    public void setYokujitsuZz(BigDecimal yokujitsuZz) {
        this.yokujitsuZz = yokujitsuZz;
    }
    
    /**
     * 前日在庫（C/S)を取得します。
     * @return 前日在庫（C/S)
     */
    public String getStrAmtCase() {
        return strAmtCase;
    }
    /**
     * 前日在庫（C/S)を設定します。
     * @param strAmtCase 前日在庫（C/S)
     */
    public void setStrAmtCase(String strAmtCase) {
        this.strAmtCase = strAmtCase;
        try{
            this.amtCase = new BigDecimal(strAmtCase);
        }
        catch(Exception ex){
            
        }
    }
    
    /**
     * 前日在庫(バラ）を取得します。
     * @return 前日在庫(バラ）
     */
    public String getStrAmtBara() {
        return strAmtBara;
    }
    /**
     * 前日在庫(バラ）を設定します。
     * @param strAmtBara 前日在庫(バラ）
     */
    public void setStrAmtBara(String strAmtBara) {
        this.strAmtBara = strAmtBara;
        try{
            this.amtBara = new BigDecimal(strAmtBara);
        }
        catch(Exception ex){
            
        }
    }
    
    /**
     * 当日貸借（バラ）を取得します。
     * @return 当日貸借（バラ）
     */
    public String getStrAmtBl() {
        return strAmtBl;
    }
    /**
     * 当日貸借（バラ）を設定します。
     * @param strAmtBl 当日貸借（バラ）
     */
    public void setStrAmtBl(String strAmtBl) {
        this.strAmtBl = strAmtBl;
        try{
            this.amtBl = new BigDecimal(strAmtBl);
        }
        catch(Exception ex){
            
        }
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    /**
     * @return eigyoDt を戻します。
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * @param eigyoDt 設定する eigyoDt。
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    /**
     * @return tojitsuYz を戻します。
     */
    public BigDecimal getTojitsuYz() {
        return tojitsuYz;
    }
    /**
     * @param tojitsuYz 設定する tojitsuYz。
     */
    public void setTojitsuYz(BigDecimal tojitsuYz) {
        this.tojitsuYz = tojitsuYz;
    }
    /**
     * @return irime1 を戻します。
     */
    public BigDecimal getIrime1() {
        return irime1;
    }
    /**
     * @param irime1 設定する irime1。
     */
    public void setIrime1(BigDecimal irime1) {
        this.irime1 = irime1;
    }
    /**
     * @return zenjitsuZ を戻します。
     */
    public BigDecimal getZenjitsuZ() {
        return zenjitsuZ;
    }
    /**
     * @param zenjitsuZ 設定する zenjitsuZ。
     */
    public void setZenjitsuZ(BigDecimal zenjitsuZ) {
        this.zenjitsuZ = zenjitsuZ;
    }
    
}
