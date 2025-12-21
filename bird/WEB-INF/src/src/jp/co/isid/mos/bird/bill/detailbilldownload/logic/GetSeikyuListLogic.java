/*
 * 作成日: 2006/08/09
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.logic;

import java.util.List;

/**
 * 請求書一覧の取得ロジック
 * 
 * @author xwatanabe
 */
public interface GetSeikyuListLogic {

    /**
     * 請求書リスト取得
     * @param  会社コード
     * @param  オーナーコード
     * @param  売掛先コード
     * @param  検収日付
     * @return 請求書一覧のリスト
     */
    public List execute(String companyCd,String onerCd,String urikakeCd,String kensyDate);
}
