package jp.co.isid.mos.bird.commonform.publictargetsearch.entity;

public class CodShozoku {
    
    public static final String TABLE = "BC07SHZK";
    
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    
    public static final String shozokuName_COLUMN = "SHOZOKU_NAME";
    
    public static final String userShozoku_COLUMN = "USER_SHOZOKU_KBN";
    
    
    /**
     * 所属区分
     */
    private String shozokuKbn;
    
    /**
     * 所属区分名称
     */
    private String shozokuName;
    
    /**
     * ユーザ所属
     */
    private String userShozoku;
    
    /**
     * 選択フラグ
     */
    private boolean sentakuFlg;
    
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
     * @param shozokuName 所属区分名称
     */
    public void setShozokuName(String shozokuName) {
        this.shozokuName = shozokuName;
    }
    
    /**
     * ユーザ所属を取得します。
     * @return ユーザ所属
     */
    public String getUserShozoku() {
        return userShozoku;
    }
    /**
     * ユーザ所属を設定します。
     * @param userShozoku ユーザ所属
     */
    public void setUserShozoku(String userShozoku) {
        this.userShozoku = userShozoku;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean getSentakuFlg() {
        return sentakuFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param sentakuFlg 選択フラグ
     */
    public void setSentakuFlg(boolean sentakuFlg) {
        this.sentakuFlg = sentakuFlg;
    }
    
}
