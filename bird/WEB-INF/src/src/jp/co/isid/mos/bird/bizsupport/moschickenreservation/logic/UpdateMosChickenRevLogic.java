package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
/**
 * モスチキン予約情報登録・更新インターフェイス
 * @author inazawa
 * 2006/09/19
 */

public interface UpdateMosChickenRevLogic {
    /**
     * 登録
     */
    public void insert(MosChickenRevInfoDto dto);
    /**
     * 更新
     */
    public void update(MosChickenRevInfoDto dto);

}
