/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIMLStaff;

/**
 * マスターライセンス（ベーシック・出張）研修受験スタッフ情報取得
 * 対象TABLE：BT30ENKJ 研修結果状況（全研修対象）
 *            BT24MLKJ マスターライセンス結果状況(ベーシック・出張・マスターライセンス）
 * @author xkinu
 */
public interface UIMLStaffDao {

    public static final Class BEAN = UIMLStaff.class;
    public static final String selectStaff_ARGS = "entryCd, entryYear, entryKai, companyCd, sibuCd";
    public static final String selectEntryStaff_ARGS = "entryCd, entryYear, entryKai, companyCd, sibuCd";
    public static final String insertBt30Enkj_ARGS = "entity";
    public static final String updateBt30Enkj_ARGS = "entity";
    public static final String deleteBt30Enkj_ARGS = "entity";
    public static final String insertBt24mlkj_ARGS = "entity";
    public static final String updateBt24mlkj_ARGS = "entity";

    /**
     * 研修受験スタッフ情報の検索
     * 
     * [TABLE]加盟店スタッフマスタから条件にあう
     * スタッフデータを全て取得されます。
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sibuCd
     * @return List 検索結果
     */
    public List selectStaff(
    		String entryCd, String entryYear, String entryKai
			, String companyCd, String sibuCd);
    /**
     * 研修受験スタッフ情報の検索
     * 
     * [TABLE]加盟店スタッフマスタと、
     * [TABLE]研修エントリー状況から条件にあう
     * スタッフデータを全て取得されます。
     * 
     * 条件項目『オプション』の
     * エントリー者のみ表示にチェックした場合に実行されます。
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sibuCd
     * @return List 検索結果
     */
    public List selectEntryStaff(
    		String entryCd, String entryYear, String entryKai
			, String companyCd, String sibuCd);
    
   /**
     * 研修結果状況(BT30ENKJ)情報の新規
     * 
     * @param entity
     * @return int 登録結果結果ステータス
     */
    public int insertBt30Enkj(UIMLStaff entity);

    /**
     * 研修結果状況(BT30ENKJ)情報の更新
     * 
     * @param entity
     * @return int 登録結果結果ステータス
     */
    public int updateBt30Enkj(UIMLStaff entity);

    /**
     * 研修結果状況(BT30ENKJ)情報の削除
     * 
     * @param entity
     * @return int 登録結果結果ステータス
     */
    public int deleteBt30Enkj(UIMLStaff entity);

    /**
     * マスターライセンス結果状況(BT24MLKJ)情報の新規
     * 
     * @param entity
     * @return int 登録結果結果ステータス
     */
    public int insertBt24mlkj(UIMLStaff entity);
    
    /**
     * マスターライセンス結果状況(BT24MLKJ)情報の更新
     * 
     * @param entity
     * @return int 登録結果結果ステータス
     */
    public int updateBt24mlkj(UIMLStaff entity);
    

 
}