/*
 * 作成日:2012/08/10
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic;


import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;

/**
 * 売上修正確認の条件部情報取得ロジック インターフェース
 *
 * @author xkawa
 */
public interface ConditionLogic {

    /**
     * 条件部情報を取得する
     * @param sessionDto 売上修正確認セッションDto
     */
    public void execute (UriMainteViewSesDto sessionDto);
}