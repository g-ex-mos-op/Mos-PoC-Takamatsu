/*
 * 作成日: 2006/07/19
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;

/**
 * 能力チェック対象者一覧取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetAbilityCheckStaffListLogic {
    /**
     * 対象者一覧を取得する
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public List execute(MlResultRegistDto dto) throws ApplicationException;
}