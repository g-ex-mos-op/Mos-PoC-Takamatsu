package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

public class TrnControlShozoku {
    
    public static final String TABLE = "BT13IASZ";
    
    public static final String infoShu_COLUMN = "INFO_SHU";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    
    public static final String shozokouName_COLUMN = "SHOZOKU_NAME";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 情報種別
     */
    private String infoShu;
    
    /**
     * 情報登録日
     */
    private String regDate;
    
    /**
     * 情報シーケンス番号
     */
    private String seq;
    
    /**
     * 所属区分
     */
    private String shozokuKbn;
    
    /**
     * 所属区分名称
     */
    private String shozokuName;
    
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
     * 情報種別を取得します。
     * @return 情報種別
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * 情報種別を設定します。
     * @param infoShu 情報種別
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }
    
    /**
     * 情報登録日を取得します。
     * @return 情報登録日
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * 情報登録日を設定します。
     * @param regDate 情報登録日
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    
    /**
     * 情報シーケンス番号を取得します。
     * @return 情報シーケンス番号
     */
    public String getSeq() {
        return seq;
    }
    /**
     * 情報シーケンス番号を設定します。
     * @param seq 情報シーケンス番号
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * 所属区分を取得します。
     * @return 所属区分
     */
    public String getShozokuKbn() {
        return shozokuKbn;
    }
    /**
     * 所属区分を設定します。
     * @param shozokuKbn 所属区分
     */
    public void setShozokuKbn(String shozokuKbn) {
        this.shozokuKbn = shozokuKbn;
    }
    
    /**
     * 所属区分名称を取得します。
     * @return 所属区分名称
     */
    public String getShozokuName() {
        return shozokuName;
    }
    /**
     * 所属区分名称を設定します。
     * @param shozokouName 所属区分名称
     */
    public void setShozokuName(String shozokuName) {
        this.shozokuName = shozokuName;
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
    
}
