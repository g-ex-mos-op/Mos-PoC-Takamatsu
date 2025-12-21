package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic;
/**
 * 編集可否チェックインターフェイス
 * @author kusama
 * 2007/11/28
 */
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;

public interface CheckEditableLogic {
    /**
     * 編集可否チェック
     * @param dto
     */
    public boolean execute(ZenDougetuRegistDto dto);
}
