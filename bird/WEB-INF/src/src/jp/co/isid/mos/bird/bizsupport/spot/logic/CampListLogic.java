package jp.co.isid.mos.bird.bizsupport.spot.logic;


import java.util.List;



/**
 * 対象キャンペーン取得ロジック インターフェース
 *
 * @author xsong
 */
public interface CampListLogic {

	/**
     * 対象キャンペーンのプルダウンリストを取得します。
     * @param sysDate システム日付
     * @return 対象キャンペーンのプルダウンリスト 
     */
	public List getList(String val);
}