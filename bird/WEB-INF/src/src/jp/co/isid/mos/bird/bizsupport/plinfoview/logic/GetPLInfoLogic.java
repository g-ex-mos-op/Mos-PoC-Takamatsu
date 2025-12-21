/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLDataInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * PL情報取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetPLInfoLogic {
    /**
     * PL情報を取得する
     * @param PlInfoViewDto
     * @param String sysDt
     * @param String appDt
     * @param String userId
     * @return UIPLDataInfo
     * @exception ApplicationException
     */
    public UIPLDataInfo execute(PlInfoViewDto dto, String sysDt, String appDt, String userId) throws ApplicationException;
}