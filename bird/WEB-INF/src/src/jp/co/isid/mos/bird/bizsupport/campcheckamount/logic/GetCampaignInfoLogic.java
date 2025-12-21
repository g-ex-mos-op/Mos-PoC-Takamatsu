/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic;

import java.util.List;


/**
 *　キャンペーン情報取得ロジック
 *
 * @author xlee
 */
public interface GetCampaignInfoLogic {

    /**
     * キャンペーン情報取得
     * @param posStrDt POS受注開始日
     * @param cmpStrDt キャンペーン開始日
     * @parem posEndDt POS受注終了日
     * @return　キャンペーン情報
     */
    public List execute(String posStrDt, String cmpStrDt, String posEndDt);

    /**
     * キャンペーン情報取得
     * @param posStrDt POS受注開始日
     * @param cmpStrDt キャンペーン開始日
     * @parem posEndDt POS受注終了日
     * @return　キャンペーン情報
     */
    public String executeKoten(String posStrDt, String cmpStrDt,  String posEndDt);
}
