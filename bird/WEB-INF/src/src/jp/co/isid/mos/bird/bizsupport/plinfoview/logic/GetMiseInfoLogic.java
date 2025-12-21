package jp.co.isid.mos.bird.bizsupport.plinfoview.logic;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstMiseInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 条件画面の店舗ユーザーの店情報取得用のインターフェース
 * @author xkhata
 */
public interface GetMiseInfoLogic {
    public MstMiseInfo execute(String MiseCd, String companyCd ) throws ApplicationException;

}
