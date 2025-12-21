/*
 * 作成日: 2006/5/26
 */
package jp.co.isid.mos.bird.entry.basicviewlist.logic;

import java.util.List;

/**
 * ベーシック研修一覧取得ロジックインターフェース
 * @author Nakajima
 */
public interface GetBasicListLogic {

    public List execute(String sysdate, String sysnextdate, String entrycd);
    
}
