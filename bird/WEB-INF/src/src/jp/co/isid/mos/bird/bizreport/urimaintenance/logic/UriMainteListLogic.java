package jp.co.isid.mos.bird.bizreport.urimaintenance.logic;

import java.util.List;


public interface UriMainteListLogic {

    /**
     * 売上情報リストの取得を行う
     * @param companyCd　会社コード
     * @param miseCd　店舗コード
     * @param targetYM　対象年月
     * @param sysdate　システム日付
     * @return List　売上情報リスト
     */
	public List execute(String companyCd, String miseCd, String targetYM, String sysdate);
    
}
