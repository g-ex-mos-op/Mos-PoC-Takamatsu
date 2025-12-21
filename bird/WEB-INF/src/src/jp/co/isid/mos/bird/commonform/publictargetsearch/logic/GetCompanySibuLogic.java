package jp.co.isid.mos.bird.commonform.publictargetsearch.logic;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;

/**
 * 公開対象支部選択情報保持リスト取得設定ロジックインターフェース
 * @author xytamura
 */
public interface GetCompanySibuLogic {

    /**
     * 公開対象支部選択情報保持リスト取得設定処理を行います。
     * 
     * @param conditionDto
     */
    public void execute(PublicTargetSearchConditionDto conditionDto);

}