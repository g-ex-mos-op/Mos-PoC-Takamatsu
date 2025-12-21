package jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity;


public class MstCmenuInfo {
    
    public static final String TABLE = "BM41CMNU";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String menuGroup_COLUMN = "MENU_GROUP";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String shokuCd_COLUMN = "SHOKU_CD";
    
    public static final String convValue_COLUMN = "CONV_VALUE";
    
    public static final String sumGroup_COLUMN = "SUM_GROUP";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    

    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * メニューグループ
     */
    private String menuGroup;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * 食包材コード
     */
    private String shokuCd;
    
    /**
     * 換算値
     */
    private String convValue;
    
    /**
     * 集計グループ
     */
    private String sumGroup;
    
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
    private String firstTmsp;
    
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
    private String lastTmsp;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    /**
     * 予約数
     */
    private int reserveAmt;

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
     * メニューグループを取得します。
     * @return メニューグループ
     */
    public String getMenuGroup() {
        return menuGroup;
    }
    /**
     * メニューグループを設定します。
     * @param menuGroup メニューグループ
     */
    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }
    /**
     * 編集用予約数
     */
    private String strReserveAmt;

    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニューコードを設定します。
     * @param cateCd メニューコード
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    
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
     * 換算値を取得します。
     * @return 換算値
     */
    public String getConvValue() {
        return convValue;
    }
    /**
     * 換算値を設定します。
     * @param convValue 換算値
     */
    public void setConvValue(String convValue) {
        this.convValue = convValue;
    }
    
    /**
     * 集計グループを取得します。
     * @return 集計グループ
     */
    public String getSumGroup() {
        return sumGroup;
    }
    /**
     * 集計グループを設定します。
     * @param sumGroup 集計グループ
     */
    public void setSumGroup(String sumGroup) {
        this.sumGroup = sumGroup;
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
    public String getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(String firstTmsp) {
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
    public String getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(String lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuNameKj() {
        return menuNameKj;
    }
    /**
     * メニュー名称を設定します。
     * @param menuName10 メニュー名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
    }
    /**
     * 予約数を取得します。
     * @return 予約数
     */
    public int getReserveAmt() {
        return reserveAmt;
    }
    /**
     * 予約数を設定します。
     * @param reserverAmt 予約数
     */
    public void setReserveAmt(int reserveAmt) {
        this.reserveAmt = reserveAmt;
    }
    public String getStrReserveAmt() {
        return strReserveAmt;
    }
    public void setStrReserveAmt(String strReserveAmt) {
        this.strReserveAmt = strReserveAmt;
    }

}
