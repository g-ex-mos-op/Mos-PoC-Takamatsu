/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.logic;

import java.util.List;


/**
 * 売掛先一覧取得ロジック
 * 
 * @author xwatanabe
 */
public interface GetUrikakeListLogic {

    /**
     * 売掛先リスト取得
     * 指定した「会社コード」「オーナーコード」に対応する売掛先のリストを取得・返却する
     * @param  会社コード
     * @param  オーナーコード
     * @return 売掛先一覧のリスト
     */
    public List execute(String companyCd,String onerCd);
}
