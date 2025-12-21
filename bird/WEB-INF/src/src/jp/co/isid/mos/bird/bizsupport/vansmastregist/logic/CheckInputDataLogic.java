/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;

/**
 * 入力内容のチェックロジックインターフェース
 * @author narita
 */
public interface CheckInputDataLogic {

    /**
     * 入力内容のチェックを行う
     * @param inputList 代表商品リスト 
     * @param nisugataList 荷姿リスト
     * @return inputList 代表商品リスト 
     */
    public List execute(List inputList,List nisugataList);
}
