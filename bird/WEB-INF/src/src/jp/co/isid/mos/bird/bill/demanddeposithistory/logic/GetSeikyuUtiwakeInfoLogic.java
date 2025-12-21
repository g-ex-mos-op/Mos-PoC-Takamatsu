/*
 * 作成日: 2006/08/22
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.logic;

import java.util.HashMap;

/**
 * 業務支援　ご請求ご入金履歴
 * 指定オーナーの売掛先一覧を取得。
 * @author xwatanabe
 *
 */
public interface GetSeikyuUtiwakeInfoLogic {
	
    /**
     * 指定オーナーの売掛先一覧を取得する。
     * @param  会社コード
     * @param  売掛先コード
     * @param  請求書ID
     * @return 請求書履歴のリスト
     */
    public HashMap execute(String companyCd, String urikakeCd, String seikyushoId);
   
}
