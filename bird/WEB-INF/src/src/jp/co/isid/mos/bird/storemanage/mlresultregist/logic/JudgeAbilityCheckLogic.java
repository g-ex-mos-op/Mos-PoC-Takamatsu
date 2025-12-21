/*
 * 作成日: 2006/07/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * 能力チェック合否判定ロジック
 * 
 * @author xyuchida
 */
public interface JudgeAbilityCheckLogic {

    /**
     * 能力チェック合否判定
     * 
     * @param mlResultRegistDto
     */
    public void execute(MlResultRegistDto mlResultRegistDto);
}
