/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.entity;

/**
 * @author xyuchida
 *
 */
public interface CategoryRegistEntity {

    /**
     * 情報種別を取得します。
     * @return 情報種別
     */
    public String getInfoShu();

    /**
     * カテゴリIDを取得します。
     * @return カテゴリID
     */
    public String getCateId();

    /**
     * カテゴリ(サブカテゴリ)名を取得します。
     * @return カテゴリ名(サブカテゴリ)
     */
    public String getNewCateName();

    /**
     * 変更前カテゴリ(サブカテゴリ)名を取得します。
     * @return 変更前カテゴリ名(サブカテゴリ)
     */
    public String getOldCateName();

    /**
     * ソートキーを取得します。
     * @return ソートキー
     */
    public String getSortKey();
    
    /**
     * ソートキーを設定します。
     */
    public void setSortKey(String value);
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean isChkFlg();

    /**
     * 新規フラグを取得します。
     * @return 新規フラグ
     */
    public boolean isInsertFlg();

    /**
     * 新規フラグを設定します。
     * @param insertFlg 新規フラグ
     */
    public void setInsertFlg(boolean insertFlg);
}
