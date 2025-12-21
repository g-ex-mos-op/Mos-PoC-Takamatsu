package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
/**
 * モスチキン予約キャンペーン名取得インターフェイス
 * @author inazawa
 * 2009/09/19
 */
public interface CondMosChickenTitleLogic {
    public List execute(MosChickenRevInfoDto dto) throws Exception;
}
