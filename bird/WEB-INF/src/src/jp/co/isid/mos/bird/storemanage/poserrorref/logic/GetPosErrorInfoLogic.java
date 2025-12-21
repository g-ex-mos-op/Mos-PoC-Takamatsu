/*
 * 作成日:2007/02/07
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.logic;

import java.util.List;

/**
 * POS集信エラー店舗一覧 集信エラー店舗情報取得ロジック
 * @author xkonishi
 *
 */
public interface GetPosErrorInfoLogic {
    
    /**
     * 集信エラー店舗情報取得
     * @param companyCd  会社コード
     * @param miseCd     店コード
     * @param onerCd     オーナーコード
     * @param userTypeCd ユーザータイプコード
     * @param shuDt      集信日
     * @return 集信エラー店舗情報リスト
     */
    public List execute(String companyCd, String miseCd, String onerCd, String userTypeCd, String shuDt);
}