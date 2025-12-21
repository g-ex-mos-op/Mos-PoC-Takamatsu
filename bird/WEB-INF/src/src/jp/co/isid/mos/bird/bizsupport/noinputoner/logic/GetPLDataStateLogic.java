/*
 * 作成日: 2006/03/24
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 過去26ヶ月のPLデータの入力状況を取得ロジック インターフェイス
 * @author xnkusama
 */
public interface GetPLDataStateLogic {
    /**
     * 過去26ヶ月のPLデータの入力状況を取得
     * @param String 管理会社企業コード
     * @param String 対象年月
     * @param String 支部コード
     * @param String 入力未入力情報
     * @return List  店PL情報
     * @exception ApplicationException
     */
    public List execute(String companyCd, String nengetu, String sibuCd, String kbnInput, boolean closeMiseFlg) throws ApplicationException;
}