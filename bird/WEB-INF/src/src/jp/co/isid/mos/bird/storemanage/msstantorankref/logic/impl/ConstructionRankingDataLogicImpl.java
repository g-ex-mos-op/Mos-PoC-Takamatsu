package jp.co.isid.mos.bird.storemanage.msstantorankref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.msstantorankref.action.MssTantotenRankingRefAction;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.UIRankingData;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.UIRankingUnionData;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.ConstructionRankingDataLogic;

/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * CSVデータ構築ロジック
 * 
 * @author xkinu
 */
public class ConstructionRankingDataLogicImpl implements ConstructionRankingDataLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssTantotenRankingRefAction.SCREEN_ID+"L04";
    /**
     * 事前条件処理
     */
    private void validate(Map param) {
        if (isNull((String)param.get("nendo"))) {
            throw new NotNullException("年度", "nendo", 0);
        }
        if (isNull((String)param.get("kai"))) {
            throw new NotNullException("回数", "kai", 0);
        }
        if (param.get("rankingDatalist") == null) {
            throw new NotNullException("ランキングデータ");
        }
        if (param.get("kouseiHiDatalist") == null) {
            throw new NotNullException("構成比データ");
        }
    }
    /**
     *  (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List execute(Map param) throws Exception {
        //事前条件チェック処理
        validate(param);
        //CSVデータ構築 & リターン
        return construction(param);
    }
    /**
     * CSVデータ出力構築
     * @param param
     * @return
     */
    private List construction(Map param) {
        // ランキングデータデータ検索
        List rankingDatalist = (List)param.get("rankingDatalist");
         // ランキングデータデータ検索
        List lastRankingDatalist = (List)param.get("lastRankingDatalist");
        //今回ランキングデータ順位設定
        constructionRankNo(rankingDatalist);
        //前回ランキングデータ順位設定
        constructionRankNo(lastRankingDatalist);
        //今回・前回のデータを結合したList生成
        List rankingUnionDatalist = unionList(rankingDatalist, lastRankingDatalist);
        return rankingUnionDatalist;
    }
    /**
     * ランキングデータ順位設定
     * 
     * 評価データが同じ場合は同順位設定を行う。
     * 
     * @param param
     * @return
     */
    private void constructionRankNo(List rankingDatalist) {
        if(rankingDatalist == null){
            return;
        }
        UIRankingData entity = (UIRankingData)rankingDatalist.get(0);
        BigDecimal beforRank = new BigDecimal("1");
        BigDecimal beforPoint = entity.getHyoukaData();
        for(int i=0; i<rankingDatalist.size(); i++){
            entity = (UIRankingData)rankingDatalist.get(i);
            if("1".equals(entity.getSpcFlg())) {
                //特殊店フラグが'1'の場合ランクをnullに設定
                entity.setRank(null);
                entity.setZenRank(null);
            }else{
                if(beforPoint.compareTo(entity.getHyoukaData()) > 0) {
                    // 前行の評価値値が小さい場合ランクを１つ上げる
                    beforRank = new BigDecimal(String.valueOf(i+1));
                    beforPoint = entity.getHyoukaData();
                }
                //ランクを再設定
                entity.setRank(beforRank);
            }
       }
    }
    /**
     * ランキングデータ結合処理
     * 
     * 今回のデータと前回のデータの結合したListを作成します。
     * 
     * @param param
     * @return
     */
    private List unionList(List rankingDatalist, List lastRankingDatalist) {
        List rankingUnionDatalist = new ArrayList();
        UIRankingData thisEntity = (UIRankingData)rankingDatalist.get(0);
        for(int i=0; i<rankingDatalist.size(); i++){
            //結合結果エンティティ
            UIRankingUnionData unionEntity = new UIRankingUnionData();
            thisEntity = (UIRankingData)rankingDatalist.get(i);
            unionEntity.setNendo(thisEntity.getNendo());
            unionEntity.setKai(thisEntity.getKai());
            unionEntity.setCompanyCd(thisEntity.getCompanyCd());
            unionEntity.setCompanyName(thisEntity.getCompanyName());
            unionEntity.setSvCd(thisEntity.getSvCd());
            unionEntity.setSvNameKj(thisEntity.getSvNameKj());
            unionEntity.setOnerCd(thisEntity.getOnerCd());
            unionEntity.setOnerNameKj(thisEntity.getOnerNameKj());
            unionEntity.setMiseCd(thisEntity.getMiseCd());
            unionEntity.setMiseNameKj(thisEntity.getMiseNameKj());
            unionEntity.setRank(thisEntity.getRank());
            unionEntity.setSpcFlg(thisEntity.getSpcFlg());
            unionEntity.setZenRank(thisEntity.getZenRank());
            unionEntity.setHyoukaData(thisEntity.getHyoukaData());
            //前回値設定
            if(lastRankingDatalist != null && lastRankingDatalist.size() > 0){
                for(int s=0; s<lastRankingDatalist.size(); s++){
                    UIRankingData lastEntity = (UIRankingData)lastRankingDatalist.get(s);
                    if(thisEntity.getMiseCd().equals(lastEntity.getMiseCd())) {
                        unionEntity.setRankZennen(lastEntity.getRank());
                        unionEntity.setZenRankZennen(lastEntity.getZenRank());
                        unionEntity.setHyoukaDataZennen(lastEntity.getHyoukaData());
                        break;
                    }
                }
            }else{
                unionEntity.setRankZennen(null);
                unionEntity.setZenRankZennen(null);
                unionEntity.setHyoukaDataZennen(null);
            }
            rankingUnionDatalist.add(i, unionEntity);
        }
        return rankingUnionDatalist;
    }
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
