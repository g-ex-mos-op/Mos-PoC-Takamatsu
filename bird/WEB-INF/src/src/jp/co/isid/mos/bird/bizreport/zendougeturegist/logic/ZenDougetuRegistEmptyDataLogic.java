package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;

/**
 * 空データ取得処理ロジック　インターフェイス
 * @author inazawa
 * 2007/03/02
 */
public interface ZenDougetuRegistEmptyDataLogic {
    /**
     * 空データ取得
     * @param dto
     * @return 修理されたリスト
     */
    public List getEmptyData(ZenDougetuRegistDto dto);
}
