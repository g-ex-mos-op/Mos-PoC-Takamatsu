/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.logic;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.dto.CalenderInfoDto;

/**
 * カレンダー情報取得ロジックインターフェース
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public interface GetCalenderInfoLogic {
    /**
     * カレンダー情報取得処理
     * 
     * @param companyCd   対象会社コード
     * @param targetMonth 対象年月
     * @return ロジック内でインスタンス化したDTO【カレンダー情報】を戻します。
     */
    public CalenderInfoDto execute(BirdUserInfo userInfo, String targetMonth);

}
