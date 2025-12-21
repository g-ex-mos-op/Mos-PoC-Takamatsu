package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;
import java.util.List;

import  jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
/**
 * モスチキン予約情報取得インターフェイス
 * @author inazawa
 * 2006/09/19
 */
public interface MosChickenRevInfoLogic {
    public List execute(MosChickenRevInfoDto dto);
}
