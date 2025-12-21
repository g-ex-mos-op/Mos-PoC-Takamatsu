package jp.co.isid.mos.bird.bizsupport.spot.logic;


import java.util.List;



/**
 * スポット未受注店情報取得ロジック インターフェース
 *
 * @author xsong
 */
public interface TenpoDataLogic {

	/**
     * スポット未受注店情報を取得します。
     * @param cmpNo キャンペーンNo
     * @param sibu　支部コード
     * @return スポット未受注店情報List
     */
	public List execute(String cmpNo, String sibu);
}