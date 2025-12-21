/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.common.logic;

import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;

/**
 * PL作成者情報の取得Logic インターフェース
 * @author itamoto
 */
public interface GetPLAuthorLogic {

    /**
     * 作成者情報を取得する
     * @param onerCd
     */
    public TrnPLAuthorInfo execute(String onerCd);
}
