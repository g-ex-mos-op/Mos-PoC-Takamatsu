/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.entity.MstRealTimeSchedule;

/**
 * 店舗別リアルタイム集信期間マスタ DAOクラス
 * 
 * 作成日:2010/11/10
 * @author xkinu
 *
 */
public interface MstRealTimeScheduleDao {
	/** エンティティ: 店舗別リアルタイム集信期間マスタ(BR82RTSM) */
	public static final Class BEAN = MstRealTimeSchedule.class;
	
	/** SQL実行時パラメータ定義：検索処理用(会社コード、店コード、集信開始日) */
	public static final String select_ARGS = "companyCd, miseCd, shuDtStart, sysDate";
	/** SQL実行時パラメータ定義：新規登録処理用(エンティティ) */
    public static String insert_ARGS = "entity";
	/** SQL実行時パラメータ定義：更新処理用(エンティティ) */
	public static String update_ARGS = "entity";
	
	/** SQL実行時パラメータ定義：削除処理用(エンティティ) */
	public static String delete_ARGS = "entity";
	/**
	 * 検索処理
	 * 
	 * @param companyCd[必須]
	 * @param miseCd
	 * @param shuDtStart
	 * @param sysDate
	 * @return List[[MstRealTimeSchedule]] 検索結果の店舗別リアルタイム集信期間マスタを戻します。
	 */
	public List select(
			  final String companyCd
			, final String miseCd
			, final String shuDtStart
			, final String sysDate);
	/**
	 * 新規登録SQL実行処理
	 * @param entity
	 */
	public int insert(final MstRealTimeSchedule entity);
	/**
	 * 更新SQL実行処理
	 * @param entity
	 */
	public int update(final MstRealTimeSchedule entity);
	/**
	 * 削除SQL実行処理
	 * @param entity
	 */
	public int delete(final MstRealTimeSchedule entity);
	/**
	 * 全店対象データ削除SQL実行処理
	 * @param entity
	 */
	public int deleteZenten();
}
