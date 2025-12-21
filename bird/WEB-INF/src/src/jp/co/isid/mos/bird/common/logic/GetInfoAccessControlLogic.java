/*
 * 作成日: 2006/02/13
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 公開対象制限ロジック インターフェイス
 * @author xnkusama
 */
public interface GetInfoAccessControlLogic {
    /**
     * サブカテゴリの取得
     * @param String infoShu
     * @param String birdUserInfo BIRDユーザー情報
     * @param List listMainData BT12～BT14で制限した情報
     * @exception ApplicationException
     */
    public List execute(String infoShu,
                         BirdUserInfo birdUserInfo,
                         List ListManin) throws ApplicationException;
}