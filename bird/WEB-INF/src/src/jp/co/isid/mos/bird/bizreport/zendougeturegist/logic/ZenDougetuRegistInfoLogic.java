package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic;
/**
 * 前年同月設定情報取得インターフェイス
 * @author inazawa
 * 
 */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;

public interface ZenDougetuRegistInfoLogic {
    /**
     * 前年同月設定情報取得
     * @param dto
     * @return 検索結果
     */
    public List search(ZenDougetuRegistDto dto);
}
