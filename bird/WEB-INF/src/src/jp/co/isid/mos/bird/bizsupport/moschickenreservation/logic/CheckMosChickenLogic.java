package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;

/**
 * モスチキン予約エラーチェックロジックインターフェイス
 * @author inazawa
 * 2006/09/19
 */

public interface CheckMosChickenLogic {
    /*エラーチェックを行う*/
    public void validater(MosChickenRevInfoDto dto);
}
