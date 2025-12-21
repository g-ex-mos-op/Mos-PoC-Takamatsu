package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListCondDto;
/**
 * モスチキン予約キャンペーン名取得インターフェイス
 * @author inazawa
 * 2009/09/19
 */
public interface CondMosChickenTitleLogic {
    public List execute(MosChickenRefConfListCondDto dto) throws Exception;
}
