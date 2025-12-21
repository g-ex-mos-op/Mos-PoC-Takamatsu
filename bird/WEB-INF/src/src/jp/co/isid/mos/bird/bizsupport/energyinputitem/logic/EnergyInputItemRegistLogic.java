/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyinputitem.logic;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.dto.EnergyInputItemDto;

/**
 * 管理状況データの更新ロジック
 * 
 * @author xnkusama
 *
 */
public interface EnergyInputItemRegistLogic {

    /**
	 * 処理実行
	 * 
	 * @param dto
	 * @return
	 */
    public void execute(EnergyInputItemDto dto);

}
