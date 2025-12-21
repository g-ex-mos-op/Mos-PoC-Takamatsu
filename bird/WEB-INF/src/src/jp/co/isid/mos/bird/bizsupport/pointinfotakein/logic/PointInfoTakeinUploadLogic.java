/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;

/**
 * ポイント情報取込ロジックインターフェース
 * @author yushuncheng
 *
 */
public interface PointInfoTakeinUploadLogic {

    /**
     * ポイント情報アップロード処理
     * @param PointInfoTakeinDto 画面DTO
     * @return boolean
     */
    public boolean execute(PointInfoTakeinDto dto);

}
