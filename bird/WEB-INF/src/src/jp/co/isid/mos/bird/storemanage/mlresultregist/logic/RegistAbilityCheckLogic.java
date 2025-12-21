/*
 * 作成日: 2006/07/28
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * 能力チェック結果登録ロジック
 * 
 * @author xyuchida
 */
public interface RegistAbilityCheckLogic {

    /**
     * 能力チェック結果登録
     * 
     * @param mlResultRegistDto
     */
    public void execute(MlResultRegistDto mlResultRegistDto);
}
