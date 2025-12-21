/*
 * 作成日: 2006/3/20
 */
package jp.co.isid.mos.bird.bizsupport.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;

/**
 * 前月PLデータの取得ロジック　インターフェース
 * @author itamoto
 */
public interface GetZenPLDataLogic {

    /**
     * 前月PLデータを取得する
     * @param plRegistDto
     */
    public List execute(PlRegistDto plRegistDto);
}
