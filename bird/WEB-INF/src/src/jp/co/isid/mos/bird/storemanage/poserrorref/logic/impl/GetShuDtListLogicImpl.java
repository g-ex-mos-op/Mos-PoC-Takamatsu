/*
 * 作成日:2007/02/08
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.logic.impl;

import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.poserrorref.logic.GetShuDtListLogic;

/**
 * POS集信エラー店舗一覧　集信日リスト取得ロジック
 * @author xkonishi
 *
 */
public class GetShuDtListLogicImpl implements GetShuDtListLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BSM015L01";
    
    /**
     * 集信日取得ロジック
     * @param basicDate  基準日
     * @param days       日数
     * @return shuDtList 集信日リスト
     */
    public List execute(String basicDate, int days) {
        List shuDtList = new ArrayList();
        DateFormatter dtFormatter = new DateFormatter();
        
        String dispDate= null;
        
        dispDate = dtFormatter.format(
                basicDate, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
        
        shuDtList.add(new SelectItem(basicDate, dispDate));
        
        for(int i = 0; i < days - 1; i++) {
            // リストの最後尾の要素を取得
            try {
                // 前日を取得
                basicDate = DateManager.getPrevDate(basicDate, 1);
            } catch (Exception e) {
                throw new FtlSystemException("POS集信エラー店舗一覧集信日取得ロジックの集信日生成");

            }
            
            dispDate = dtFormatter.format(
                    basicDate, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);

            // 集信日リストに追加
            shuDtList.add(new SelectItem(basicDate, dispDate));
        }
        return shuDtList;
    }
}