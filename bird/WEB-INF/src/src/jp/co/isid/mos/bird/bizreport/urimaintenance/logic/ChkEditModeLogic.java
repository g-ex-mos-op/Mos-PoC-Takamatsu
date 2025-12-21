package jp.co.isid.mos.bird.bizreport.urimaintenance.logic;

import java.util.List;


public interface ChkEditModeLogic {

    /**
     * 編集モードチェックを行う
     * @param listUri 売上修正情報リスト
     * @return boolean true:編集モード false:参照モード
     */
	public boolean execute(List listUri);
    
}
