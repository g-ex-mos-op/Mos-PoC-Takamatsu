package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * モスチキン管理マスタ登録
 * 
 * モスチキン管理対象メニュー画面表示用Entity
 * @author xkinu
 *
 */
public class UIKanriMenu {
    
    public static final String TABLE = "BM41CMNU";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String menuGroup_COLUMN = "MENU_GROUP";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    public static final String shokuCd_COLUMN = "SHOKU_CD";
    
    public static final String shokuNameKna_COLUMN = "SHOKU_NAME_KNA";
    
    public static final String convValue_COLUMN = "CONV_VALUE";
    
    public static final String strConvValue_COLUMN = "STR_CONV_VALUE";
    
    public static final String sumGroup_COLUMN = "SUM_GROUP";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * メニューグループコード
     */
    private String menuGroup;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    
    /**
     * 食包材コード
     */
    private String shokuCd;
    
    /**
     * 食包材名称
     */
    private String shokuNameKna;
    
    /**
     * 換算値
     */
    private BigDecimal convValue;
    /**
     * 換算値
     */
    private String strConvValue;
    
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
     * メニューグループコードを取得します。
     * @return メニューグループコード
     */
    public String getMenuGroup() {
        return menuGroup;
    }
    /**
     * メニューグループコードを設定します。
     * @param menuGroup メニューグループコード
     */
    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
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
     * @param menuNameKj メニュー名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
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
     * 換算値を取得します。
     * @return 換算値
     */
    public BigDecimal getConvValue() {
        return convValue;
    }
    /**
     * 換算値を設定します。
     * @param convValue 換算値
     */
    public void setConvValue(BigDecimal convValue) {
        this.convValue = convValue;
        this.strConvValue = String.valueOf(convValue);
    }
    /**
     * 換算値を取得します。
     * @return 換算値
     */
    public String getStrConvValue() {
        return strConvValue;
    }
    /**
     * 換算値を設定します。
     * @param strConvValue 換算値
     */
    public void setStrConvValue(String strConvValue) {
        this.strConvValue = strConvValue;
        try{
            this.convValue = new BigDecimal(strConvValue);
        }
        catch(Exception ex){
            
        }
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
