package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;

/**
 * モスチキン予約初期処理でのオーナー保有店舗取得インターフェイス
 * @author inazawa
 * 2006/09/19
 */

public interface CondMiseListLogicChickenRevLogic {
    public List execute(MosChickenRevInfoDto dto) throws Exception;

    public List execHonbu(MosChickenRevInfoDto dto) throws Exception;
}