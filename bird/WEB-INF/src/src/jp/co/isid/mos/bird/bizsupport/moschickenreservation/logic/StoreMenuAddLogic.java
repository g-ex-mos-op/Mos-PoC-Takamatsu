package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;

/**
 * 商品追加インターフェイス
 * @author inazawa
 * 2006/09/19
 */


public interface StoreMenuAddLogic {
    public List exec(MosChickenRevInfoDto dto);
}
