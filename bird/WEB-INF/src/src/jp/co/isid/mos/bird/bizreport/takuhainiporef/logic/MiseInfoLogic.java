package jp.co.isid.mos.bird.bizreport.takuhainiporef.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

/**
 * 店舗別の宅配売上情報を取得ロジック インターフェース
 *
 * @author xjung
 */
public interface MiseInfoLogic {

    /**
     * 店舗別の宅配売上情報を取得する
     * @param takuhaiNipoRefDto 条件部情報DTO
	 * @return Map 店舗別宅配売上情報<br>
	 * 本部ユーザの時：店舗別宅配売上情報リスト<br>
	 * オーナーユーザの時：店舗別宅配売上情報リスト、対象店舗数
	 */
	public Map execute(NipoRefConditionParameterDto takuhaiNipoRefDto);
}