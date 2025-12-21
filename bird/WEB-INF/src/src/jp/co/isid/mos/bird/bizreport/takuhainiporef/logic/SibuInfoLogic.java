package jp.co.isid.mos.bird.bizreport.takuhainiporef.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;

/**
 * 支部別の宅配売上情報を取得ロジック インターフェース
 *
 * @author xjung
 */
public interface SibuInfoLogic {

    /**
     * 支部別の宅配売上情報を取得する<br>
     * @param dto           検索条件Dto
     * @param isDownLoadFlg CSVダウンロードアクションフラグ
     * @return Map 支部別宅配売上情報(対象店舗数、有無別リスト、タイプ別リスト)
	 */
	public Map execute(NipoRefConditionParameterDto dto, boolean isDownLoadFlg);
}