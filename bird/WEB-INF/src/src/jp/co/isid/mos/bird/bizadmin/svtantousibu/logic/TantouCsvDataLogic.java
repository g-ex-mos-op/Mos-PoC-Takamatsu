/*
 * 作成日: 2007/04/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic;

import java.util.List;

/**
 * SV担当店ダウンロードロジック
 * 
 * @author xnkusama
 */
public interface TantouCsvDataLogic {

    /**
     * リモート閲覧支部の取得
     * @param 会社コード
     * @param 支部コード
     * @param 未設定フラグ
     * @param システム日付
     * @return　List
     */
    public List execute(String companyCd, String sibuCd, String flgMisettei, String sysDate);
}
