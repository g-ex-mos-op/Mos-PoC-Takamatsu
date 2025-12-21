/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.logic;

import java.util.HashMap;

/**
 * 業務支援　ご請求ご入金履歴画面
 * 請求入金情報の取得。
 * @author xwatanabe
 *
 */
public interface GetSeikyuNyukinInfoLogic {
	
    /**
     * 指定オーナーの売掛先一覧を取得する。
     * @param  会社コード
     * @param  オーナーコード
     * @param  検収日付
     * @return 請求書履歴のリスト
     */
    public HashMap execute(String companyCd, String onerCd, String kensuDt);
   
}
