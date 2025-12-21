package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListCondDto;

/**
 * モスチキン予約初期処理でのオーナー保有店舗取得インターフェイス
 * @author inazawa
 * 2006/09/19
 */

public interface CondMiseListLogicChickenRevLogic {
    public List execute(MosChickenRefConfListCondDto dto) throws Exception;

}