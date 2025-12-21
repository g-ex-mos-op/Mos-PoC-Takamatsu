/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic;

import java.util.List;

/**
 * お買上明細報取得ロジック
 *
 * @author xlee
 */
public interface GetPDFListLogic {

    /**
     *　お買上情報を
     * @param　オーナーコード
     * @param　frDate　検収日付：FROM
     * @return toDate　検収日付：TO
     */
    public List execute(String onerCd,
    		            String frDate,
    		            String toDate,
// add 2023/02/23 USI範 begin
    		            String kinGaku,
    		            String kinGakuTo);
// add 2023/02/23 USI範 end
}
