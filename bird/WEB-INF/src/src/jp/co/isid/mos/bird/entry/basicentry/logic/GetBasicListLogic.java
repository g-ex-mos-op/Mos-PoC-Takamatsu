/*
 * 作成日: 2006/06/16
 */
package jp.co.isid.mos.bird.entry.basicentry.logic;

import java.util.List;

/**
 * ベーシック研修一覧取得ロジックインターフェース
 * @author kusama
 */
public interface GetBasicListLogic {

    public List execute(String sysdate, String sysnextdate, String entrycd, String onerCd, String userTypeCd);
    
}
