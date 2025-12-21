/*
 * 作成日: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;

/**
 * PLデータの取得ロジック　インターフェース
 * @author itamoto
 */
public interface GetPLDataLogic {

    /**
     * PLデータを取得する
     * @param plRegistDto
     */
    public List execute(PlRegistDto plRegistDto);

	/**
     * 前月PLデータを取得する(P/LデータCSV一括取込用)
     * @param plRegistDto
     */
    public List executeByPlCsv(PlRegistDto plRegistDto);
}
