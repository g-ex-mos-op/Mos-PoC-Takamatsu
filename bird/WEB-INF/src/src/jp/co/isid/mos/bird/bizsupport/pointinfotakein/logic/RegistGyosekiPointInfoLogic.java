/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;

/**
 * 業績ポイント登録ロジックインターフェース
 * @author yushuncheng
 *
 */
public interface RegistGyosekiPointInfoLogic {

    /**
     * 業績ポイント情報登録
     * @param PointInfoTakeinDto 画面DTO
     * @return
     */
    public boolean execute(PointInfoTakeinDto dto);

}
