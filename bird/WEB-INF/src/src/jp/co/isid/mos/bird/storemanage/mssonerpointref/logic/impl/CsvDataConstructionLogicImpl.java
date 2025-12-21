package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.storemanage.mssonerpointref.action.MssOnerPointRankRefAction;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.UIOnerMiseCnt;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.UIPoint;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.UISubTotalPoint;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.UITotalPoint;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.CsvDataConstructionLogic;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.SearchLogic;

/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * CSVデータ構築ロジック
 * 
 * @author xkinu
 */
public class CsvDataConstructionLogicImpl implements CsvDataConstructionLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssOnerPointRankRefAction.SCREEN_ID+"L03";
    /*【ロジック】データ検索ロジック*/
    private SearchLogic searchLogic;
    /**
     *  (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List execute(Map paramJoken) throws Exception {
         // 検索結果List
        Map mapdata = (Map)getMssOnerPointRankSearchLogic().execute(paramJoken);
        //CSVデータ構築 & リターン
        return construction(paramJoken, mapdata);
    }
    /**
     * データ検索ロジック取得処理
     * @return searchLogic を戻します。
     */
    public SearchLogic getMssOnerPointRankSearchLogic() {
        return searchLogic;
    }
    /**
     * データ検索ロジック設定処理
     * @param searchLogic を設定。
     */
    public void setMssOnerPointRankSearchLogic(SearchLogic logic) {
        this.searchLogic = logic;
    }
    /**
     * CSVデータ出力構築
     * @param param
     * @return
     */
    private List construction(Map paramJoken, Map param) {
        /* データ構築開始 */
        List listCSV = new ArrayList();
        //1.ヘッダー構築
        constructionHeader(paramJoken, param, listCSV);
        //2.空白行
        listCSV.add(new ArrayList());       
        //3.カラム箇所ヘッダー設定
        listCSV.add(constructionTitle(param));
        //4.データ設定
        constructionData(param, listCSV);
        
        return listCSV;
    }
    /**
     * 条件項目ヘッダ作成
     * 
     *  1行目：会社
     *  2行目：対象支部 
     *  3行目：対象SV
     *  4行目：実施年度＋実施回数
     *  
     * @param param
     * @return
     */
    private void constructionHeader(Map paramJoken, Map param, List listCSV) {
        // 中分類総合得点
        List totalPointlist = (List)param.get("totalPointlist");
        UITotalPoint entity = (UITotalPoint)totalPointlist.get(0);
        List listHeaderRowCompany = new ArrayList();
        List listHeaderRowSibu = new ArrayList();
        List listHeaderRowSv = new ArrayList();
        List listHeaderRowNendoKai = new ArrayList();
        //条件項目ヘッダ設定
        //会社情報
        listHeaderRowCompany.add("会社");
        listHeaderRowCompany.add(paramJoken.get("companyCd")+"  "+ paramJoken.get("companyName"));
        listCSV.add(listHeaderRowCompany);
        //会社情報
        listHeaderRowSibu.add("対象支部");
        listHeaderRowSibu.add(param.get("sibuCd")+"  "+ param.get("sibuName"));
        listCSV.add(listHeaderRowSibu);
        //会社情報
        listHeaderRowSv.add("対象SV");
        if(entity.getSvCd() != null){
            listHeaderRowSv.add(entity.getSvCd()+"  "+ entity.getSvNameKj());
        }
        listCSV.add(listHeaderRowSv);
        //会社情報
        listHeaderRowNendoKai.add("実施年度");
        listHeaderRowNendoKai.add(paramJoken.get("nendo")+"年度");
        listHeaderRowNendoKai.add("第"+ paramJoken.get("kai")+"回");
        listCSV.add(listHeaderRowNendoKai);
    }
    /**
     * CSV一覧データタイトル構築
     * @param param
     * @return
     */
    private List constructionTitle(Map param) {
        // オーナー別名称データ検索
        List onersNamelist = (List)param.get("onerMiseCntlist");
        //カラム名称設定
        List listHeader = new ArrayList();
        for(int i=0; i < onersNamelist.size(); i++){
            UIOnerMiseCnt entityName = (UIOnerMiseCnt)onersNamelist.get(i);
            if(i==0){
                listHeader.add("");
                listHeader.add("項目NO");
                listHeader.add("項目名称");
                listHeader.add("上限値");
                listHeader.add("全国平均");
                listHeader.add("支部平均");
            }
            listHeader.add(entityName.getOnerNameKj().trim()+"("+entityName.getOnerCd()+")");                
        }
        return listHeader;
    }
    /**
     * CSV一覧データタイトル構築
     * 
     * 検索対象がオーナーの場合に対象となる支部が複数存在した場合、
     * 支部平均の得点は非表示にする。
     * 
     * @param param
     * @return
     */
    private void constructionData(Map param, List listCSV) {
        // オーナー保有店数構築
        listCSV.add(constructionDataMiseCnt(param));
        //オーナー保有支部
        List onerSibulist = (List)param.get("onerSibulist");
        boolean isSibus = false;
        if(onerSibulist != null && onerSibulist.size() > 1){
            isSibus = true;
        }
        // 総合得点データ構築
        listCSV.add(constructionDataTotalPoint(param, isSibus));
        // 中分類平均データ構築
        constructionDataSubTotalPoint(param, listCSV, isSibus);
        // 各項目評価データ構築
        constructionDataPoint(param, listCSV, isSibus);
    }
    /**
     * CSV一覧データ保有店舗数表示箇所構築
     * @param param
     * @return
     */
    private List constructionDataMiseCnt(Map param) {
        // オーナー保有店数
        List onerMiseCntlist = (List)param.get("onerMiseCntlist");
        //カラム名称設定
        List listMiseCnt = new ArrayList();
        for(int i=0; i < onerMiseCntlist.size(); i++){
            UIOnerMiseCnt entity = (UIOnerMiseCnt)onerMiseCntlist.get(i);
            if(i==0){
                listMiseCnt.add("");
                listMiseCnt.add("");
                listMiseCnt.add("");
                listMiseCnt.add("");
                listMiseCnt.add(entity.getZenCnt()+"店");
                listMiseCnt.add(entity.getSibuCnt()+"店");
            }
            listMiseCnt.add(entity.getMiseCnt()+"店");                
        }
        return listMiseCnt;
    }
    /**
     * CSV一覧データ 総合得点 表示箇所構築
     * 
     * 対象支部が複数存在する場合は支部平均を空で表示
     * @param param
     * @param isSibus 複数支部フラグ
     * @return
     */
    private List constructionDataTotalPoint(Map param, boolean isSibus) {
        // 総合得点
        List totalPointlist = (List)param.get("totalPointlist");
        //カラム名称設定
        List listTotoalPoint = new ArrayList();
        for(int i=0; i < totalPointlist.size(); i++){
            UITotalPoint entity = (UITotalPoint)totalPointlist.get(i);
            if(i==0){
                listTotoalPoint.add(entity.getBnrLName());
                listTotoalPoint.add("");
                listTotoalPoint.add("");
                listTotoalPoint.add(entity.getLimitU100());
                listTotoalPoint.add(entity.getZenAvg100());
                if(!isSibus){ 
                    listTotoalPoint.add(entity.getSibuAvg100());
                }else{
                    //対象支部が複数存在する場合は支部平均を空で表示
                    listTotoalPoint.add("");
                }
            }
            listTotoalPoint.add(entity.getTokuten100());                
        }
        return listTotoalPoint;
    }
    /**
     * CSV一覧データ 中分類総合得点 表示箇所構築
     * 
     * 対象支部が複数存在する場合は支部平均を空で表示
     * @param param
     * @param listCSV CSV出力データ保持オブジェクト
     * @param isSibus 複数支部フラグ
     * @return
     */
    private void constructionDataSubTotalPoint(Map param, List listCSV, boolean isSibus) {
        // 中分類総合得点
        List subTotalPointlist = (List)param.get("subTotalPointlist");
        //カラム名称設定
        List listSubTotoalPoint = null;
        String lastBnrL = "";
        String lastBnrM = "";
        for(int i=0; i < subTotalPointlist.size(); i++){
            UISubTotalPoint entity = (UISubTotalPoint)subTotalPointlist.get(i);
            String thisBnrL = entity.getBnrL();
            String thisBnrM = entity.getBnrM();
            if(!lastBnrL.equals(thisBnrL) || !lastBnrM.equals(thisBnrM)){
                if(listSubTotoalPoint != null){
                    listCSV.add(listSubTotoalPoint);
                }
                lastBnrL = thisBnrL;
                lastBnrM = thisBnrM;
                listSubTotoalPoint = new ArrayList();
                listSubTotoalPoint.add(entity.getBnrLName().trim()+"("+entity.getBnrMName().trim()+")");
                listSubTotoalPoint.add("");
                listSubTotoalPoint.add("");
                listSubTotoalPoint.add(entity.getLimitU100());
                listSubTotoalPoint.add(entity.getZenAvg100());
                if(!isSibus){ 
                    listSubTotoalPoint.add(entity.getSibuAvg100());
                }else{
                    //対象支部が複数存在する場合は支部平均を空で表示
                    listSubTotoalPoint.add("");
                }
            }
            listSubTotoalPoint.add(entity.getTokuten100());                
        }
        if(listSubTotoalPoint != null) {
            listCSV.add(listSubTotoalPoint);
        }
    }
    /**
     * CSV一覧データ 各項目評価データ 表示箇所構築
     * 
     * 対象支部が複数存在する場合は支部平均を空で表示
     * @param param
     * @param listCSV CSV出力データ保持オブジェクト
     * @param isSibus 複数支部フラグ
     * @return
     */
    private void constructionDataPoint(Map param, List listCSV, boolean isSibus) {
       // 各項目評価データ
        List pointlist = (List)param.get("pointlist");
        //カラム名称設定
        List listPoint = null;
        String lastBnrL = "";
        String lastBnrM = "";
        String lastKoumokuNo = "";
        String lastKoumokuSub = "";
        for(int i=0; i < pointlist.size(); i++){
            UIPoint entity = (UIPoint)pointlist.get(i);
            String thisBnrL = entity.getBnrL();
            String thisBnrM = entity.getBnrM();
            String thisKoumokuNo = entity.getKoumokuNo();
            String thisKoumokuSub = entity.getKoumokuSub();
            if(!lastBnrL.equals(thisBnrL) || !lastBnrM.equals(thisBnrM)
                    || !lastKoumokuNo.equals(thisKoumokuNo) || !lastKoumokuSub.equals(thisKoumokuSub)
                    ){
                if(listPoint != null){
                    listCSV.add(listPoint);
                }
                listPoint = new ArrayList();
                if(!lastBnrL.equals(thisBnrL) || !lastBnrM.equals(thisBnrM)){
                    listPoint.add(entity.getBnrLName().trim()+"("+entity.getBnrMName().trim()+")");
                }else{
                    listPoint.add("");
                }
                listPoint.add(entity.getKoumokuNo());
                listPoint.add(entity.getKoumokuName().trim());
                listPoint.add(entity.getLimitU100());
                listPoint.add(entity.getZenAvg100());
                if(!isSibus){ 
                    listPoint.add(entity.getSibuAvg100());
                }else{
                    //対象支部が複数存在する場合は支部平均を空で表示
                    listPoint.add("");
                }
                lastBnrL = thisBnrL;
                lastBnrM = thisBnrM;
                lastKoumokuNo = thisKoumokuNo;
                lastKoumokuSub = thisKoumokuSub;
            }
            listPoint.add(entity.getTokuten100());                
        }
        if(listPoint != null) {
            listCSV.add(listPoint);
        }
    }
}
