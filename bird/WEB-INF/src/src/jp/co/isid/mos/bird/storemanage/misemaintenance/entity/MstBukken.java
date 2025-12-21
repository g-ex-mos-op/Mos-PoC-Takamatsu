package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MstBukken {
    
    public static final String TABLE = "BM24BUKJ";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";

    public static final String keiyakuUpdate_COLUMN = "KEIYAKU_UPDATE";
    
    public static final String keiyakuYachin_COLUMN = "KEIYAKU_YACHIN";
    
    public static final String keiyakuOner_COLUMN = "KEIYAKU_ONER";
    
    public static final String yachinZeiKbn_COLUMN = "YACHIN_ZEI_KBN";
    
    public static final String yachinZeiKbnName_COLUMN = "YACHIN_ZEI_KBN_NAME";
    
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
     * 物件契約：更新日
     */
    private String keiyakuUpdate;
    
    /**
     * 物件契約：家賃
     */
    private String keiyakuYachin;
    
    /**
     * 物件契約：家主
     */
    private String keiyakuOner;
    
    /**
     * 家賃税区分
     */
    private String yachinZeiKbn;
    
    /**
     * 家賃税区分名称
     */
    private String yachinZeiKbnName;
    
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
     * 物件契約：更新日を取得します。
     * @return 物件契約：更新日
     */
    public String getKeiyakuUpdate() {
        return convString(keiyakuUpdate);
    }
    /**
     * 物件契約：更新日を設定します。
     * @param keiyakuUpdate 物件契約：更新日
     */
    public void setKeiyakuUpdate(String keiyakuUpdate) {
        this.keiyakuUpdate = keiyakuUpdate;
    }
    
    /**
     * 物件契約：家賃を取得します。
     * @return 物件契約：家賃
     */
    public String getKeiyakuYachin() {
        return convStringDec(keiyakuYachin);
    }
    /**
     * 物件契約：家賃を設定します。
     * @param keiyakuYachin 物件契約：家賃
     */
    public void setKeiyakuYachin(String keiyakuYachin) {
        this.keiyakuYachin = keiyakuYachin;
    }
    
    /**
     * 物件契約：家主を取得します。
     * @return 物件契約：家主
     */
    public String getKeiyakuOner() {
        return convString(keiyakuOner);
    }
    /**
     * 物件契約：家主を設定します。
     * @param keiyakuOner 物件契約：家主
     */
    public void setKeiyakuOner(String keiyakuOner) {
        this.keiyakuOner = keiyakuOner;
    }
    
    /**
     * 家賃税区分を取得します。
     * @return 家賃税区分
     */
    public String getYachinZeiKbn() {
        return convString(yachinZeiKbn);
    }
    /**
     * 家賃税区分を設定します。
     * @param yachinZeiKbn 家賃税区分
     */
    public void setYachinZeiKbn(String yachinZeiKbn) {
        this.yachinZeiKbn = yachinZeiKbn;
    }
    
    /**
     * 家賃税区分名称を取得します。
     * @return 家賃税区分名称
     */
    public String getYachinZeiKbnName() {
        return convString(yachinZeiKbnName);
    }
    /**
     * 家賃税区分名称を設定します。
     * @param yachinZeiKbnName 家賃税区分名称
     */
    public void setYachinZeiKbnName(String yachinZeiKbnName) {
        this.yachinZeiKbnName = yachinZeiKbnName;
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
        return lastUser;
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
        return lastPgm;
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