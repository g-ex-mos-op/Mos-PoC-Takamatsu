package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dto.TempStoreReplaceDto;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreStateList;

/**
 * 仮店舗置換え状況ダウンロードロジック
 * 
 * @author Aspac
 */
public class StateListCsvOutputLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS025L05";

    

    /**
     * ファイル名取得
     * @param csvOutputDto CSV出力用DTO
     * @return ファイル名
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        // テンプレートCSVファイル名
        return "YOSANKARIOKI.csv";
    }

    
    /**
     * 出力データ取得処理
     * @param csvOutputDto CSV出力用DTO
     * @return CSV出力データリスト
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        TempStoreReplaceDto tempStoreReplaceDto = (TempStoreReplaceDto) csvOutputDto;
        
        
        List outputDataList = new ArrayList();
        List headerList1 = new ArrayList();
        List headerList2 = new ArrayList();
        List headerList3 = new ArrayList();
        List headerList4 = new ArrayList();
        List rowColmList = new ArrayList();

        
        /*******************************/
        /*** CSVヘッダ情報を生成する ***/
        /*******************************/
        
        headerList1.add("会社：");
        headerList1.add(tempStoreReplaceDto.getCompanyName());
        headerList2.add("年度：");
        headerList2.add(tempStoreReplaceDto.getCondNendo());
        headerList3.add("対象店舗：");
        headerList3.add(tempStoreReplaceDto.getTargetStoreShu());
        
        rowColmList.add("状況");
        rowColmList.add("支部コード");
        rowColmList.add("支部名称");
        rowColmList.add("仮店舗種別");
        rowColmList.add("仮店舗コード");
        rowColmList.add("予算設定開始月");
        rowColmList.add("予算額");
        rowColmList.add("置換え日");
        rowColmList.add("売上発生日");
        rowColmList.add("オープン日");
        rowColmList.add("店舗種別");
        rowColmList.add("支部コード");
        rowColmList.add("支部名称");
        rowColmList.add("店舗コード");
        rowColmList.add("店舗名称");
        rowColmList.add("登録ユーザ");
        rowColmList.add("登録ユーザ名称");
        rowColmList.add("登録日");
        
        outputDataList.add(headerList1);
        outputDataList.add(headerList2);
        outputDataList.add(headerList3);
        outputDataList.add(headerList4);
        outputDataList.add(rowColmList);
        
        
        /*******************************/
        /*** CSVデータ情報を生成する ***/
        /*******************************/
        
        //仮店舗置換え状況リスト
        List listState = tempStoreReplaceDto.getListState();
        
        //フォーマット設定
        DateFormatter dateformat = new DateFormatter();
        NumericFormatter numformat = new NumericFormatter();
        
        
        for (Iterator ite = listState.iterator(); ite.hasNext();) {
            List rowItemList = new ArrayList();
            TrnTempStoreStateList stateInfo = (TrnTempStoreStateList) ite.next();
            
            //状況
            String state = "";
            if(stateInfo.getSetFlg().equals("1")){
                state = "確定";
            }
            else {
                //予算設定開始月が当月含む過去２ヶ月の日付けより過去の場合
                if(stateInfo.getAbleUpdateFlg().equals("0")) {
                    state = "対象期間外";
                }
            }
            rowItemList.add(state);
            //支部コード
            rowItemList.add(stateInfo.getSibuCd());
            //支部名称
            rowItemList.add(convNull(stateInfo.getSibuName()));
            //FCRC
            String fcrc = "";
            if(stateInfo.getFcRc().equals("1")) fcrc = "FC";
            else if(stateInfo.getFcRc().equals("2")) fcrc = "RC";
            rowItemList.add(fcrc);
            //仮店舗コード
            rowItemList.add(stateInfo.getKariCd());
            //予算設定開始月
            rowItemList.add(convNull(dateformat.format(stateInfo.getYosanDt(), DateFormatter.PATTERN_MONTH, DateFormatter.PATTERN_MONTH_SLASH, true)));
            //予算額
            rowItemList.add(convNull(numformat.format(stateInfo.getYosan())));
            //置換え日
            rowItemList.add(convNull(dateformat.format(stateInfo.getKakuteiDt(), DateFormatter.PATTERN_NORMAL, DateFormatter.PATTERN_SLASH, true)));
            //売上げ発生日
            rowItemList.add(convNull(dateformat.format(stateInfo.getUriageDt(), DateFormatter.PATTERN_NORMAL, DateFormatter.PATTERN_SLASH, true)));
            //オープン日
            rowItemList.add(convNull(dateformat.format(stateInfo.getOpenDt(), DateFormatter.PATTERN_NORMAL, DateFormatter.PATTERN_SLASH, true)));
            //店舗種別(BM01店区分) 
            String miseKbn = "";
            if(stateInfo.getMiseKbn()!=null && !stateInfo.getMiseKbn().equals("")) { 
                if(stateInfo.getMiseKbn().substring(1,2).equals("1")) miseKbn = "FC"; 
                else if(stateInfo.getMiseKbn().substring(1,2).equals("2")) miseKbn = "RC";
            }
            rowItemList.add(miseKbn);
            //実店舗支部コード
            rowItemList.add(stateInfo.getJituSibuCd());
            //実店舗支部名称
            rowItemList.add(convNull(stateInfo.getJituSibuName()));
            //実店舗コード
            rowItemList.add(stateInfo.getMiseCd());
            //実店舗名称
            rowItemList.add(convNull(stateInfo.getMiseNameKj()));
            //登録ユーザ
            rowItemList.add(convNull(stateInfo.getFirstUser()));
            //登録ユーザ名称
            rowItemList.add(convNull(stateInfo.getUserNameKj()));
            //登録日
            String torokuDt = "";
            if(stateInfo.getFirstTmsp()!=null) {
                torokuDt = stateInfo.getFirstTmsp().toString().substring(0,10);
                torokuDt = convNull(dateformat.format(torokuDt, DateFormatter.PATTERN_CROSS, DateFormatter.PATTERN_SLASH, true));
            }
            rowItemList.add(torokuDt);
            
            outputDataList.add(rowItemList);
        }
        
        return outputDataList;
    }

    /**
     * 事前条件チェック処理
     * @param csvOutputDto CSV出力用DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
    }
    
    
    /**
     * Nullを空に変換する
     * @param str
     */
    private String convNull(String str) {
        if(str == null) return "";
        return str;
    }
    
}
