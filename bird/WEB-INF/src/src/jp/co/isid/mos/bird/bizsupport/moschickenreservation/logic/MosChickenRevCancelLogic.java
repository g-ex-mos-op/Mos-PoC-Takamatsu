package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;

/**
 * モスチキン予約情報キャンセル/予約キャンセル取消インターフェイス
 * @author inazawa
 * 2006/09/19
 */

public interface MosChickenRevCancelLogic {
    /**
     * 予約キャンセル／キャンセル取消し
     */
    public void cancel(MosChickenRevInfoDto dto);

}
