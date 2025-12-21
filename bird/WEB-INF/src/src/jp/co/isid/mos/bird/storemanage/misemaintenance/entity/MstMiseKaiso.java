package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MstMiseKaiso {
    
    public static final String TABLE = "BM18MKAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String kaisoKaisu_COLUMN = "KAISO_KAISU";
    
    public static final String kaisoHoshu_COLUMN = "KAISO_HOSHU";
    
    public static final String kaisoSta_COLUMN = "KAISO_STA";
    
    public static final String kaisoEnd_COLUMN = "KAISO_END";
    
    public static final String kaisoHiyo_COLUMN = "KAISO_HIYO";
    
    public static final String kaisoNaiyo_COLUMN = "KAISO_NAIYO";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 改装回数
     */
    private BigDecimal kaisoKaisu;
    
    /**
     * 改装区分
     */
    private String kaisoHoshu;
    
    /**
     * 開始日
     */
    private String kaisoSta;
    
    /**
     * 終了日
     */
    private String kaisoEnd;
    
    /**
     * 金額
     */
    private String kaisoHiyo;
    
    /**
     * 内容
     */
    private String kaisoNaiyo;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 行削除判別フラグ
     */
    private boolean delFlg;

    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * 改装回数を取得します。
     * @return 改装回数
     */
    public BigDecimal getKaisoKaisu() {
        return kaisoKaisu;
    }
    /**
     * 改装回数を設定します。
     * @param kaisoKaisu 改装回数
     */
    public void setKaisoKaisu(BigDecimal kaisoKaisu) {
        this.kaisoKaisu = kaisoKaisu;
    }
    
    /**
     * 改装区分を取得します。
     * @return 改装区分
     */
    public String getKaisoHoshu() {
        return convString(kaisoHoshu);
    }
    /**
     * 改装区分を設定します。
     * @param kaisoHoshu 改装区分
     */
    public void setKaisoHoshu(String kaisoHoshu) {
        this.kaisoHoshu = kaisoHoshu;
    }
    
    /**
     * 開始日を取得します。
     * @return 開始日
     */
    public String getKaisoSta() {
        return convString(kaisoSta);
    }
    /**
     * 開始日を設定します。
     * @param kaisoSta 開始日
     */
    public void setKaisoSta(String kaisoSta) {
        this.kaisoSta = kaisoSta;
    }
    
    /**
     * 終了日を取得します。
     * @return 終了日
     */
    public String getKaisoEnd() {
        return convString(kaisoEnd);
    }
    /**
     * 終了日を設定します。
     * @param kaisoEnd 終了日
     */
    public void setKaisoEnd(String kaisoEnd) {
        this.kaisoEnd = kaisoEnd;
    }
    
    /**
     * 金額を取得します。
     * @return 金額
     */
    public String getKaisoHiyo() {
        return convStringDec(kaisoHiyo);
    }
    /**
     * 金額を設定します。
     * @param kaisoHiyo 金額
     */
    public void setKaisoHiyo(String kaisoHiyo) {
        this.kaisoHiyo = kaisoHiyo;
    }
    
    /**
     * 内容を取得します。
     * @return 内容
     */
    public String getKaisoNaiyo() {
        return convString(kaisoNaiyo);
    }
    /**
     * 内容を設定します。
     * @param kaisoNaiyo 内容
     */
    public void setKaisoNaiyo(String kaisoNaiyo) {
        this.kaisoNaiyo = kaisoNaiyo;
    }
    
    /**
     * @return delFlg を戻します。
     */
    public boolean isDelFlg() {
        return delFlg;
    }
    /**
     * @param delFlg delFlg を設定。
     */
    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return convString(firstUser);
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
        return convString(firstPgm);
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return convString(lastUser);
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return convString(lastPgm);
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
    
    private String convStringDec(String str) {
        String ret = str;
        if (str == null) {
            ret = "0";
        }
        ret = ret.trim();
        return ret;
    }
}