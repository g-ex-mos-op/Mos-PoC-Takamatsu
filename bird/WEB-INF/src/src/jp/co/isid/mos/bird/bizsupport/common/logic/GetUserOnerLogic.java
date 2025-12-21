/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;

/**
 * ユーザー対応オーナーの検索logic interface
 * @author itamoto
 */
public interface GetUserOnerLogic {

    /**
     * ユーザ対応オーナー店舗情報を取得する
     * @param userId
     * @param plRegistDto
     */
    public List execute(String userId, PlRegistDto plRegistDto);

    /**
     * ユーザー対応店舗情報の取得する
     * @param PlRegistDto
     */
    public List execute(PlRegistDto plRegistDto);
    
    /**
     * 店クローズ日を取得設定する。
     * @param plRegistDto
     * @param miseCd
     */
    public void getMiseCloseDt(PlRegistDto plRegistDto, String miseCd);
}
