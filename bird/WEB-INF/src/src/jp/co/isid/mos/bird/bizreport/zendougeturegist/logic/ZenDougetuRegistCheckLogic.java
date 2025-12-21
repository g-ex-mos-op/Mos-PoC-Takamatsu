package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic;
/**
 * 編集内容エラーチェックインターフェイス
 * @author inazawa
 * 2007/02/07
 */
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;

public interface ZenDougetuRegistCheckLogic {
    /**
     * 編集内容エラーチェック処理
     * @param dto
     */
    public void validate(ZenDougetuRegistDto dto);
}
