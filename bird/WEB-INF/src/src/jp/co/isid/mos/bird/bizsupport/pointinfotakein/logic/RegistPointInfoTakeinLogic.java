/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;

/**
 * ポイント情報登録ロジックインターフェース
 * @author yushuncheng
 *
 */
public interface RegistPointInfoTakeinLogic {

    /**
     * ポイント取込情報登録
     * @param PointInfoTakeinDto 画面DTO
     */
    public void execute(PointInfoTakeinDto dto);

}
