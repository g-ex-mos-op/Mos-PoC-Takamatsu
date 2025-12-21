package jp.co.isid.mos.bird.analysis.posdata.logic;

import java.util.List;
import java.util.Map;

public interface PosDataFormLogic {

    /**
     * POS集信データの取得。
     * @param posDataList
     * @return List
     */
    public List execute(Map paramMap);
    
    /**
     * ダウンロードリストの作成。
     * @param posDataList
     * @return List
     */
    public List addDawnloadList(List posDataList);
    
    /**
     * 条件部情報を取得する
     * @param userType	ユーザタイプ
     * @param appDate	システム日付
     * @return Map     条件部情報
     */
    public Map execute (String userType,String appDate);
    
    /**
     * オーナーコードを取得する
     * @param onerList	オーナーリスト
     * @param companyCd 会社コード
     * @return Map     オーナーコード
     */
    public String getOnerId(List onerList,String companyCd);
}
