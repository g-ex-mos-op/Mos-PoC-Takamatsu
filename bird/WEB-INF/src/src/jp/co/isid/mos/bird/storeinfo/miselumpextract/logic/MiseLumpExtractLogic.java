/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.logic;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.dto.MiseLumpExtractDto;

/**
 * CSVデータ取得ロジックインターフェース
 * @author xayumi
 */
public interface MiseLumpExtractLogic {

    public List execute(MiseLumpExtractDto dto);

}
