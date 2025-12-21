package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MstShokuhouzai {
    
    public static final String TABLE = "PM06SHOK";
    
    public static final String shokuCd_COLUMN = "SHOKU_CD";
    
    public static final String mstDt_COLUMN = "MST_DT";
    
    public static final String shokuNameKna_COLUMN = "SHOKU_NAME_KNA";
    
    public static final String hacchuDt_COLUMN = "HACCHU_DT";
    
    public static final String irime1Cd_COLUMN = "IRIME1_CD";
    
    public static final String irime1_COLUMN = "IRIME1";
    
    public static final String irime1Tanka_COLUMN = "IRIME1_TANKA";
    
    public static final String irime2Cd_COLUMN = "IRIME2_CD";
    
    public static final String irime2_COLUMN = "IRIME2";
    
    public static final String irime2Tanka_COLUMN = "IRIME2_TANKA";
    
    public static final String irime3Cd_COLUMN = "IRIME3_CD";
    
    public static final String budomariKeisu_COLUMN = "BUDOMARI_KEISU";
    
    public static final String hacchuFlg_COLUMN = "HACCHU_FLG";
    
    public static final String hacchuKbn_COLUMN = "HACCHU_KBN";
    
    public static final String shokuKbn_COLUMN = "SHOKU_KBN";
    
    public static final String shiireTankaFlg_COLUMN = "SHIIRE_TANKA_FLG";
    
    public static final String hacchuBnrCd_COLUMN = "HACCHU_BNR_CD";
    
    public static final String hacBnrNameKj_COLUMN = "HAC_BNR_NAME_KJ";

    public static final String hacTanniKbn_COLUMN = "HAC_TANNI_KBN";
    
    public static final String hacHissuFlg_COLUMN = "HAC_HISSU_FLG";
    
    public static final String kdRyakuKna_COLUMN = "KD_RYAKU_KNA";
    
    public static final String shiyoFlg_COLUMN = "SHIYO_FLG";
    
    public static final String kobetsuFlg_COLUMN = "KOBETSU_FLG";
    
    public static final String shuseiFlg_COLUMN = "SHUSEI_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 食包材コード
     */
    private String shokuCd;
    
    /**
     * マスタ更新日
     */
    private String mstDt;
    
    /**
     * 食包材名称
     */
    private String shokuNameKna;
    
    /**
     * 発注可能日
     */
    private String hacchuDt;
    
    /**
     * 入目１単位コード
     */
    private String irime1Cd;
    
    /**
     * 入目１
     */
    private BigDecimal irime1;
    
    /**
     * 入目１仕入単位
     */
    private BigDecimal irime1Tanka;
    
    /**
     * 入目２単位コード
     */
    private String irime2Cd;
    
    /**
     * 入目２
     */
    private BigDecimal irime2;
    
    /**
     * 入目２仕入単位
     */
    private BigDecimal irime2Tanka;
    
    /**
     * 入目３単位コード
     */
    private String irime3Cd;
    
    /**
     * 標準歩留係数
     */
    private String budomariKeisu;
    
    /**
     * 発注可能フラグ
     */
    private String hacchuFlg;
    
    /**
     * 発注区分
     */
    private String hacchuKbn;
    
    /**
     * 食包材区分
     */
    private String shokuKbn;
    
    /**
     * 仕入れ単価変更フラグ
     */
    private String shiireTankaFlg;
    
    /**
     * 発注分類コード
     */
    private String hacchuBnrCd;
    /**
     * 発注分類名称
     */
    private String hacBnrNameKj;
    
    /**
     * 発注短幾分
     */
    private String hacTanniKbn;
    
    /**
     * 発注必須フラグ
     */
    private String hacHissuFlg;
    
    /**
     * KD略称
     */
    private String kdRyakuKna;
    
    /**
     * 使用可能フラグ
     */
    private String shiyoFlg;
    
    /**
     * 個別設定フラグ
     */
    private String kobetsuFlg;
    
    /**
     * 修正フラグ
     */
    private String shuseiFlg;
    
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
     * 食包材コードを取得します。
     * @return 食包材コード
     */
    public String getShokuCd() {
        return shokuCd;
    }
    /**
     * 食包材コードを設定します。
     * @param shokuCd 食包材コード
     */
    public void setShokuCd(String shokuCd) {
        this.shokuCd = shokuCd;
    }
    
    /**
     * マスタ更新日を取得します。
     * @return マスタ更新日
     */
    public String getMstDt() {
        return mstDt;
    }
    /**
     * マスタ更新日を設定します。
     * @param mstDt マスタ更新日
     */
    public void setMstDt(String mstDt) {
        this.mstDt = mstDt;
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
     * 発注可能日を取得します。
     * @return 発注可能日
     */
    public String getHacchuDt() {
        return hacchuDt;
    }
    /**
     * 発注可能日を設定します。
     * @param hacchuDt 発注可能日
     */
    public void setHacchuDt(String hacchuDt) {
        this.hacchuDt = hacchuDt;
    }
    
    /**
     * 入目１単位コードを取得します。
     * @return 入目１単位コード
     */
    public String getIrime1Cd() {
        return irime1Cd;
    }
    /**
     * 入目１単位コードを設定します。
     * @param irime1Cd 入目１単位コード
     */
    public void setIrime1Cd(String irime1Cd) {
        this.irime1Cd = irime1Cd;
    }
    
    /**
     * 入目１を取得します。
     * @return 入目１
     */
    public BigDecimal getIrime1() {
        return irime1;
    }
    /**
     * 入目１を設定します。
     * @param irime1 入目１
     */
    public void setIrime1(BigDecimal irime1) {
        this.irime1 = irime1;
    }
    
    /**
     * 入目１仕入単位を取得します。
     * @return 入目１仕入単位
     */
    public BigDecimal getIrime1Tanka() {
        return irime1Tanka;
    }
    /**
     * 入目１仕入単位を設定します。
     * @param irime1Tanka 入目１仕入単位
     */
    public void setIrime1Tanka(BigDecimal irime1Tanka) {
        this.irime1Tanka = irime1Tanka;
    }
    
    /**
     * 入目２単位コードを取得します。
     * @return 入目２単位コード
     */
    public String getIrime2Cd() {
        return irime2Cd;
    }
    /**
     * 入目２単位コードを設定します。
     * @param irime2Cd 入目２単位コード
     */
    public void setIrime2Cd(String irime2Cd) {
        this.irime2Cd = irime2Cd;
    }
    
    /**
     * 入目２を取得します。
     * @return 入目２
     */
    public BigDecimal getIrime2() {
        return irime2;
    }
    /**
     * 入目２を設定します。
     * @param irime2 入目２
     */
    public void setIrime2(BigDecimal irime2) {
        this.irime2 = irime2;
    }
    
    /**
     * 入目２仕入単位を取得します。
     * @return 入目２仕入単位
     */
    public BigDecimal getIrime2Tanka() {
        return irime2Tanka;
    }
    /**
     * 入目２仕入単位を設定します。
     * @param irime2Tanka 入目２仕入単位
     */
    public void setIrime2Tanka(BigDecimal irime2Tanka) {
        this.irime2Tanka = irime2Tanka;
    }
    
    /**
     * 入目３単位コードを取得します。
     * @return 入目３単位コード
     */
    public String getIrime3Cd() {
        return irime3Cd;
    }
    /**
     * 入目３単位コードを設定します。
     * @param irime3Cd 入目３単位コード
     */
    public void setIrime3Cd(String irime3Cd) {
        this.irime3Cd = irime3Cd;
    }
    
    /**
     * 標準歩留係数を取得します。
     * @return 標準歩留係数
     */
    public String getBudomariKeisu() {
        return budomariKeisu;
    }
    /**
     * 標準歩留係数を設定します。
     * @param budomariKeisu 標準歩留係数
     */
    public void setBudomariKeisu(String budomariKeisu) {
        this.budomariKeisu = budomariKeisu;
    }
    
    /**
     * 発注可能フラグを取得します。
     * @return 発注可能フラグ
     */
    public String getHacchuFlg() {
        return hacchuFlg;
    }
    /**
     * 発注可能フラグを設定します。
     * @param hacchuFlg 発注可能フラグ
     */
    public void setHacchuFlg(String hacchuFlg) {
        this.hacchuFlg = hacchuFlg;
    }
    
    /**
     * 発注区分を取得します。
     * @return 発注区分
     */
    public String getHacchuKbn() {
        return hacchuKbn;
    }
    /**
     * 発注区分を設定します。
     * @param hacchuKbn 発注区分
     */
    public void setHacchuKbn(String hacchuKbn) {
        this.hacchuKbn = hacchuKbn;
    }
    
    /**
     * 食包材区分を取得します。
     * @return 食包材区分
     */
    public String getShokuKbn() {
        return shokuKbn;
    }
    /**
     * 食包材区分を設定します。
     * @param shokuKbn 食包材区分
     */
    public void setShokuKbn(String shokuKbn) {
        this.shokuKbn = shokuKbn;
    }
    
    /**
     * 仕入れ単価変更フラグを取得します。
     * @return 仕入れ単価変更フラグ
     */
    public String getShiireTankaFlg() {
        return shiireTankaFlg;
    }
    /**
     * 仕入れ単価変更フラグを設定します。
     * @param shiireTankaFlg 仕入れ単価変更フラグ
     */
    public void setShiireTankaFlg(String shiireTankaFlg) {
        this.shiireTankaFlg = shiireTankaFlg;
    }
    
    /**
     * 発注分類コードを取得します。
     * @return 発注分類コード
     */
    public String getHacchuBnrCd() {
        return hacchuBnrCd;
    }
    /**
     * 発注分類コードを設定します。
     * @param hacchuBnrCd 発注分類コード
     */
    public void setHacchuBnrCd(String hacchuBnrCd) {
        this.hacchuBnrCd = hacchuBnrCd;
    }
    
    /**
     * 発注短幾分を取得します。
     * @return 発注短幾分
     */
    public String getHacTanniKbn() {
        return hacTanniKbn;
    }
    /**
     * 発注短幾分を設定します。
     * @param hacTanniKbn 発注短幾分
     */
    public void setHacTanniKbn(String hacTanniKbn) {
        this.hacTanniKbn = hacTanniKbn;
    }
    
    /**
     * 発注必須フラグを取得します。
     * @return 発注必須フラグ
     */
    public String getHacHissuFlg() {
        return hacHissuFlg;
    }
    /**
     * 発注必須フラグを設定します。
     * @param hacHissuFlg 発注必須フラグ
     */
    public void setHacHissuFlg(String hacHissuFlg) {
        this.hacHissuFlg = hacHissuFlg;
    }
    
    /**
     * KD略称を取得します。
     * @return KD略称
     */
    public String getKdRyakuKna() {
        return kdRyakuKna;
    }
    /**
     * KD略称を設定します。
     * @param kdRyakuKna KD略称
     */
    public void setKdRyakuKna(String kdRyakuKna) {
        this.kdRyakuKna = kdRyakuKna;
    }
    
    /**
     * 使用可能フラグを取得します。
     * @return 使用可能フラグ
     */
    public String getShiyoFlg() {
        return shiyoFlg;
    }
    /**
     * 使用可能フラグを設定します。
     * @param shiyoFlg 使用可能フラグ
     */
    public void setShiyoFlg(String shiyoFlg) {
        this.shiyoFlg = shiyoFlg;
    }
    
    /**
     * 個別設定フラグを取得します。
     * @return 個別設定フラグ
     */
    public String getKobetsuFlg() {
        return kobetsuFlg;
    }
    /**
     * 個別設定フラグを設定します。
     * @param kobetsuFlg 個別設定フラグ
     */
    public void setKobetsuFlg(String kobetsuFlg) {
        this.kobetsuFlg = kobetsuFlg;
    }
    
    /**
     * 修正フラグを取得します。
     * @return 修正フラグ
     */
    public String getShuseiFlg() {
        return shuseiFlg;
    }
    /**
     * 修正フラグを設定します。
     * @param shuseiFlg 修正フラグ
     */
    public void setShuseiFlg(String shuseiFlg) {
        this.shuseiFlg = shuseiFlg;
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
     * @return hacBnrNameKj を戻します。
     */
    public String getHacBnrNameKj() {
        return hacBnrNameKj;
    }
    /**
     * @param hacBnrNameKj 設定する hacBnrNameKj。
     */
    public void setHacBnrNameKj(String hacBnrNameKj) {
        this.hacBnrNameKj = hacBnrNameKj;
    }
    
}
