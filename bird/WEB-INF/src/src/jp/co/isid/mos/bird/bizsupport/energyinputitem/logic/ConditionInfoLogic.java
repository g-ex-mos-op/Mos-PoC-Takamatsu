/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyinputitem.logic;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;

/**
 * 条件項目の取得設定ロジック
 * 
 * @author xnkusama
 *
 */
public interface ConditionInfoLogic {

    /**
	 * 処理実行
	 * 
	 * @param dto
	 * @return
	 */
    public void execute(EnergyInputItemDto dto);

}
