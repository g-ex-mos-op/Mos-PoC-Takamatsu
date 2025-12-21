/*
 * 作成日: 2006/07/19
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;

/**
 * マスターライセンス能力チェック結果取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetAbilityCheckCategoryResultLogic {
    /**
     * マスターライセンス能力チェック結果取得
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public TrnAbilityCheckResult execute(MlResultRegistDto dto) throws ApplicationException;
}