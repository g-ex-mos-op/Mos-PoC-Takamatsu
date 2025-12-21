/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * グラフ出力アクションInterface
 * @author xnkusama
 */
public interface GraphOutputAction {
//    /**
//     * CSV出力用ロジック設定処理
//     * @param downloadLogic
//     */
//    public void setGraphOutputLogic(final GraphOutputLogic graphLogic);
//
//    /**
//     * グラフ出力用DTO設定処理
//     * @param downloadDto
//     */
//    public void setGraphOutputDto(final GraphOutputDto graphOutputDto);
//
//    /**
//     * HttpSession設定処理
//     * @param httpSession
//     */
//    public void setSession(HttpSession httpSession);
//
//    /**
//     * HttpSession取得処理
//     * @return HttpSession
//     */
//    public HttpSession getHttpSession();

    public String viewGraph() throws ApplicationException;
}