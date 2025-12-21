/*
 * 作成日: 2007/04/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;

/**
 * SV担当店アップロードロジック
 * 
 * @author xnkusama
 */
public interface TantouUploadLogic {

    /**
     * SV担当店アップロード処理
     * @param SvTantouSibuDto 画面DTO
     * @return boolean
     */
    public boolean execute(SvTantouSibuDto dto);
}
