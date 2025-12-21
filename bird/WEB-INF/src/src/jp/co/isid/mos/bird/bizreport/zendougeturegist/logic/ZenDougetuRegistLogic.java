package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic;
/**
 * 前年同月設定登録インターフェイス
 * @author inazawa
 * 2007/02/27
 */
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;

public interface ZenDougetuRegistLogic {
    /**
     * 前年同月設定登録処理
     * @param dto
     */
    public void regist(ZenDougetuRegistDto dto);

}
