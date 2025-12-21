package jp.co.isid.mos.bird.config.pllimitregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;

public class CtlPLLimit {
    
    public static final String TABLE = "BR49PLCH";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String plType_COLUMN = "PL_TYPE";
    
    public static final String columnName_COLUMN = "COLUMN_NAME";
    
    public static final String itemNo_COLUMN = "ITEM_NO";
    
    public static final String itemName_COLUMN = "ITEM_NAME";
    
    public static final String itemType_COLUMN = "ITEM_TYPE";
    
    public static final String limitType_COLUMN = "LIMIT_TYPE";
    
    public static final String limitMax_COLUMN = "LIMIT_MAX";
    
    public static final String limitMin_COLUMN = "LIMIT_MIN";
    
    public static final String sortKey_COLUMN = "SORT_KEY";
    
    public static final String koseihiFlg_COLUMN = "KOSEIHI_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * PLの種類
     */
    private String plType;
    
    /**
     * 対象カラム名称
     */
    private String columnName;
    
    /**
     * 対象項目番号
     */
    private String itemNo;
    
    /**
     * 対象項目表示名
     */
    private String itemName;
    
    /**
     * 項目タイプ
     */
    private String itemType;
    
    /**
     * 上下限タイプ
     */
    private String limitType;
    
    /**
     * 上限値
     */
    private String limitMax;
    
    /**
     * 下限値
     */
    private String limitMin;
    
    /**
     * ソートキー
     */
    private BigDecimal sortKey;
    
    /**
     * 構成比指定不可フラグ
     */
    private String koseihiFlg;
    
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
     * PLの種類を取得します。
     * @return PLの種類
     */
    public String getPlType() {
        return plType;
    }
    /**
     * PLの種類を設定します。
     * @param plType PLの種類
     */
    public void setPlType(String plType) {
        this.plType = plType;
    }
    
    /**
     * 対象カラム名称を取得します。
     * @return 対象カラム名称
     */
    public String getColumnName() {
        return columnName;
    }
    /**
     * 対象カラム名称を設定します。
     * @param columnName 対象カラム名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    /**
     * 対象項目番号を取得します。
     * @return 対象項目番号
     */
    public String getItemNo() {
        return itemNo;
    }
    /**
     * 対象項目番号を設定します。
     * @param itemNo 対象項目番号
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }
    
    /**
     * 対象項目表示名を取得します。
     * @return 対象項目表示名
     */
    public String getItemName() {
        return itemName;
    }
    /**
     * 対象項目表示名を設定します。
     * @param itemName 対象項目表示名
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    /**
     * 項目タイプを取得します。
     * @return 項目タイプ
     */
    public String getItemType() {
        return itemType;
    }
    /**
     * 項目タイプを設定します。
     * @param itemType 項目タイプ
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    /**
     * 上下限タイプを取得します。
     * @return 上下限タイプ
     */
    public String getLimitType() {
        return limitType;
    }
    /**
     * 上下限タイプを設定します。
     * @param limitType 上下限タイプ
     */
    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }
    
    /**
     * 上限値を取得します。
     * @return 上限値
     */
    public String getLimitMax() {
        return limitMax;
    }
    public String getLimitMaxWithType() {
        return getLimitType() + PlLimitRegistConstants.LIMIT_SEPARATOR + ((getLimitMax() == null) ? "" : getLimitMax());
    }
    /**
     * 上限値を設定します。
     * @param limitMax 上限値
     */
    public void setLimitMax(String limitMax) {
        this.limitMax = limitMax;
    }
    public void setLimitMaxWithType(String limitMax) {
        setLimitMax(limitMax);
    }
    
    /**
     * 下限値を取得します。
     * @return 下限値
     */
    public String getLimitMin() {
        return limitMin;
    }
    public String getLimitMinWithType() {
        return getLimitType() + PlLimitRegistConstants.LIMIT_SEPARATOR + ((getLimitMin() == null) ? "" : getLimitMin());
    }
    /**
     * 下限値を設定します。
     * @param limitMin 下限値
     */
    public void setLimitMin(String limitMin) {
        this.limitMin = limitMin;
    }
    public void setLimitMinWithType(String limitMin) {
        setLimitMin(limitMin);
    }
    
    /**
     * ソートキーを取得します。
     * @return ソートキー
     */
    public BigDecimal getSortKey() {
        return sortKey;
    }
    /**
     * ソートキーを設定します。
     * @param sortKey ソートキー
     */
    public void setSortKey(BigDecimal sortKey) {
        this.sortKey = sortKey;
    }
    
    /**
     * 構成比指定不可フラグを取得します。
     * @return 構成比指定不可フラグ
     */
    public String getKoseihiFlg() {
        return koseihiFlg;
    }
    /**
     * 構成比指定不可フラグを設定します。
     * @param koseihiFlg 構成比指定不可フラグ
     */
    public void setKoseihiFlg(String koseihiFlg) {
        this.koseihiFlg = koseihiFlg;
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
