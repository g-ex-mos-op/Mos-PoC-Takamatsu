package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;

/**
 * 祝祭日取得処理ロジック　インターフェイス
 * @author inazawa
 * 2007/03/02
 */
public interface ZenDougetuRegistHolidayLogic {
    /**
     * 
     * @param dto
     * @return 祝祭日情報
     */
    public List getHolidayInfo(ZenDougetuRegistDto dto);

}
