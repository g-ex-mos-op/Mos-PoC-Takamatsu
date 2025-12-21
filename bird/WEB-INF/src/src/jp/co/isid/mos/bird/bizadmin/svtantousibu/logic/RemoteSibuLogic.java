/*
 * 作成日: 2007/04/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic;

import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;

/**
 * リモート閲覧支部の取得ロジック
 * 
 * @author xnkusama
 */
public interface RemoteSibuLogic {

    public static final String RETURN_MAP_KEY_USER = "KEY_USER";
    public static final String RETURN_MAP_KEY_SIBULIST = "KEY_SIBU_LIST";
    public static final String RETURN_MAP_KEY_REMOTESIBU = "KEY_REMOTE_SIBU";
    
    /**
     * リモート閲覧支部の取得
     * @param SvTantouSibuDto
     * @return　Map
     * 　Mapのキー
     * 　　支部一覧　　　　：KEY_SIBU_LIST
     * 　　リモート閲覧支部：KEY_REMOTE_SIBU
     */
    public Map execute(SvTantouSibuDto dto);
}
