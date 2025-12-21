package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;
/**
 * モスチキン予約新規予約用マスタ登録商品取得インターフェイス
 * @author inazawa
 * 2006/09/19
 */
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;

public interface MosChickenRevNewInfoLogic {
    public List execute (MosChickenRevInfoDto dto);
}
