package jp.co.isid.mos.bird.bizsupport.spot.logic;


import jp.co.isid.mos.bird.bizsupport.spot.dto.SpotResultDataDto;



/**
 * キャンペーン情報取得ロジック インターフェース
 *
 * @author xsong
 */
public interface CampResultDataLogic {

	/**
     * キャンペーン情報を取得します。
     * @param cmpNo キャンペーンNo
     * @param sibu　支部コード
     * @return  キャンペーン情報
     */
	public SpotResultDataDto execute(String cmpNo, String sibu);
}