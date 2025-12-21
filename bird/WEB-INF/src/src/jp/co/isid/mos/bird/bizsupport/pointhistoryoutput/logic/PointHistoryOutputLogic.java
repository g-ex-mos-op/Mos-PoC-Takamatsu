/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dto.PointHistoryOutputDto;

/**
 * CSVデータ取得ロジックインターフェース
 * @author Yuichi Tamura(ISID-AO)
 */
public interface PointHistoryOutputLogic {

    public List execute(PointHistoryOutputDto dto);

}
