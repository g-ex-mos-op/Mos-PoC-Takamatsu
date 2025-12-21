/*
 * 作成日: 2006/12/04
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao.UICsvAutoAmountDataDao;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto.BunsAutoAmtRegistReqDto;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UICsvAutoAmountData;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 【CSV出力情報取得】ロジック
 * 
 * @author xkinu
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    
    /** ロジックID */
    public static final String LOGIC_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"L03";
    /** DAO【バンズ自動設定情報一覧】 */
    private UICsvAutoAmountDataDao bunsAutoAmtRegistUICsvAutoAmountDataDao;
    /**
     * ロジック【検索】
     */
    private SearchLogic bunsAutoAmtRegistSearchLogic;
    
   /**
     * @return bunsAutoAmtRegistSearchLogic を戻します。
     */
    public SearchLogic getBunsAutoAmtRegistSearchLogic() {
        return bunsAutoAmtRegistSearchLogic;
    }

    /**
     * @param bunsAutoAmtRegistSearchLogic 設定する bunsAutoAmtRegistSearchLogic。
     */
    public void setBunsAutoAmtRegistSearchLogic(
            SearchLogic bunsAutoAmtRegistSearchLogic) {
        this.bunsAutoAmtRegistSearchLogic = bunsAutoAmtRegistSearchLogic;
    }

/**
     * ファイル名取得処理
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        
        // DTO
        BunsAutoAmtRegistReqDto dto = (BunsAutoAmtRegistReqDto) csvOutputDto;
        String kbn = dto.getTargetKbn();
        String kbnKigou = "";
        //１．パラメーター.DTO【自画面リクエスト用】.区分＝’全店一覧’の場合以下の名前をリターンする。
        if ("all".equals(kbn)) {
            kbnKigou = "ALL";
        }
        //２．パラメーター.DTO【自画面リクエスト用】.区分＝’未設定店舗一覧’の場合以下の名前をリターンする。
        else if ("unset".equals(kbn)) {
            kbnKigou = "UNSET";
        }
        else {
            
        }
        String fileName = "AUTOBUNS "+ kbnKigou + ".csv";
        
        return fileName;
        
    }
    
    /**
     * CSV出力メイン処理
     *  
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {     
        //DTO
        BunsAutoAmtRegistReqDto dto = (BunsAutoAmtRegistReqDto) csvOutputDto;
        //ボタン押下時の条件項目値を現行ウィンドウIDをキーに保持を行う。
//        dto.holdJokenParam();
        
        String userId = dto.getBunsAutoAmtRegistDto().getBirdUserInfo().getUserID();
        boolean limit = dto.getBunsAutoAmtRegistDto().getBirdUserInfo().isLimit();
        String sysDate = dto.getBunsAutoAmtRegistDto().getBirdDateInfo().getSysDate();
        String companyCd = dto.getBunsAutoAmtRegistDto().getTargetCompanyCd();
        String taishoJoken = dto.getTargetTaishoJoken();
        String onerCd = "";
        String miseCd = "";
        if(dto.getBunsAutoAmtRegistDto().isHonbuUser()) {
            taishoJoken = ConditionTaishoJoken.VALUE_ALL;
        }
        else if(dto.getBunsAutoAmtRegistDto().isOnerUser()) {
            taishoJoken = ConditionTaishoJoken.VALUE_ONER;
            onerCd = ((UIUserOner)(dto.getBunsAutoAmtRegistDto().getBirdUserInfo().getUserOner().get(0))).getOnerCd();
        }
        else if(dto.getBunsAutoAmtRegistDto().isOnerUser()) {
            taishoJoken = ConditionTaishoJoken.VALUE_MISE;
            miseCd = ((UIUserMise)(dto.getBunsAutoAmtRegistDto().getBirdUserInfo().getUserMise().get(0))).getMiseCd();
        }
        //区分
        String kbn = dto.getTargetKbn();
        //１．変数[[バンズ自動設定情報一覧]]を生成する。
        List listCsvOutputData = null;
        //２．パラメーター.DTO【自画面リクエスト用】.区分＝’全店ダウンロード’の場合、下記の処理を行う。
        if("all".equals(kbn)){
            //２－１．DAO【バンズ自動設定情報一覧】.未登録オーナー検索を実行し、処理１の変数[[バンズ自動設定情報一覧]]へ代入する。
            listCsvOutputData = getBunsAutoAmtRegistUICsvAutoAmountDataDao().selectAll(
                    userId, limit, sysDate, companyCd, taishoJoken, onerCd, miseCd, null);
        }
        //３．パラメーター.DTO【自画面リクエスト用】.区分＝’未設定店舗一覧’の場合、下記の処理を行う。
        else{
            //３－１．DAO【バンズ自動設定情報一覧】.検索を実行し、処理１の変数[[バンズ自動設定情報一覧]]へ代入する。
            listCsvOutputData = getBunsAutoAmtRegistUICsvAutoAmountDataDao().selectUnset(
                    userId, limit, sysDate, companyCd, taishoJoken, onerCd, miseCd, null);
        }
        //４．該当データが存在しない場合、下記のExceptionを発生させる。
        if (listCsvOutputData == null || listCsvOutputData.size() == 0) {
            //MSG【E0102】’該当データーがありません。’
            throw new NoResultException();
        }
        //５．[[CSV出力データ]]リストをインスタンスかする。
        List listCSV = new ArrayList();
        
        //６．CSVレイアウト　ヘッダ部を作成する。
        settingHeader(listCSV, dto);
        
        //７．CSVレイアウト　データ部タイトルを作成する。
        List listDataTitle = makeDataTitle(kbn);
        listCSV.add(listDataTitle);
        
        try {
            List listData = new ArrayList();
            //８.変数[[バンズ自動設定情報一覧]]を基に件数分の[CSV出力データ]を生成し、[[CSV出力データ]]リストへ格納する。
            for (Iterator ite = listCsvOutputData.iterator(); ite.hasNext();) {
                //Listからentityへキャストする
                UICsvAutoAmountData entity = (UICsvAutoAmountData) ite.next();
                //１行分の[CSV出力データ]リスト作成
                listData = make1RowData(kbn, entity);
                //上記で作成した[CSV出力データ]を[[CSV出力データ]]リストへ格納
                listCSV.add(listData);
                //2008/02/13---add start WAS高負荷対応
                entity = null;
                ite.remove();
                //2008/02/13---add end   WAS高負荷対応
            }
            listData = null;
            listCsvOutputData = null;
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成 バンズ自動設定数量：結果データ設定処理");
        }
        //１０．[[CSV出力データ]]リストをリターンする。
        return listCSV;
    }
    
    /**
     * CSVレイアウトヘッダ部を作成する。
     * 
     * @param dto
     * @param listCsv
     */
    private void settingHeader(List listCsv, BunsAutoAmtRegistReqDto dto) {
        
        List header1 = new ArrayList();
        List header2 = new ArrayList();
        List header3 = new ArrayList();
        List header4 = new ArrayList();
        String kbn = dto.getTargetKbn();
        
        try{
            DateFormatter datefm = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "yyyy/MM/dd");
            // 一行目
            header1.add(convertMoji("バンズ自動設定"));
            //２．パラメーター.DTO【自画面リクエスト用】.区分＝’全店ダウンロード’の場合、下記の処理を行う。
            if("all".equals(kbn)){
                header1.add(convertMoji("全店一覧"));
            }
            else {
                header1.add(convertMoji("未設定店舗一覧"));
            }
            
            // ニ行目
            header2.add("作成日時：");
            header2.add(datefm.format(dto.getBunsAutoAmtRegistDto().getBirdDateInfo().getSysDate(), true));
            
            
            //三行目（空欄）
            header3.add("");
            //四行目（空欄）
            header4.add("");
            
            listCsv.add(header1);
            listCsv.add(header2);
            listCsv.add(header3);
            listCsv.add(header4);
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成 バンズ自動設定数量：ヘッダ部作成処理");
        }
    }
        
    /**
     * CSVレイアウトデータ部タイトルを作成する。
     * 
     * @return
     */
    private List makeDataTitle(String kbn) {
        List list = new ArrayList();
        //五行目（データのヘッダ）
        list.add("支部コード");
        list.add("支部名称");
        list.add("オーナーコード");
        list.add("オーナー名称");
        list.add("店舗コード");
        list.add("店舗名称");
        list.add("倉庫コード");
        list.add("倉庫名称");
        if("all".equals(kbn)) {
            list.add("商品コード");
            list.add("商品名称");
            list.add("荷姿");
            list.add("平日納品");
            list.add("土曜納品");
            list.add("日祝納品");
        }
        list.add("オープン日");
        return list;
    }
    
    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ
        
    }
    /**
     * １行分のリスト作成取得処理
     * 
     * @param entity
     * @return
     */
    private List make1RowData(String kbn, UICsvAutoAmountData entity) {
        List listData = new ArrayList();
        //支部コード
        listData.add(convertMoji(entity.getSibuCd()));                
        //支部名称
        listData.add(convertMoji(entity.getSibuName()));
        //オーナーコード
        listData.add(convertMoji(entity.getOnerCd()));               
        //オーナー名称
        listData.add(convertMoji(entity.getOnerNameKj()));
        //店舗コード
        listData.add(convertMoji(entity.getMiseCd()));               
        //店舗名称
        listData.add(convertMoji(entity.getMiseNameKj()));
        //倉庫コード
        listData.add(convertMoji(entity.getSokoCd()));               
        //倉庫名称
        listData.add(convertMoji(entity.getSokoNameKj()));
        
        if("all".equals(kbn)) {
            //商品コード
            listData.add(convertMoji(entity.getShoCdDai()));               
            //商品名称
            listData.add(convertMoji(entity.getShoNameKj()));
            //荷姿名称
            listData.add(convertMoji(entity.getNisuName()));
            
            //平日納品
            listData.add(entity.getAmtWeek());
            //土曜納品
            listData.add(entity.getAmtSatd());
            //日祝納品
            listData.add(entity.getAmtHold());
        }
        DateFormatter datefm = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "yyyy/MM/dd");
        //オープン日
        listData.add(convertMoji(datefm.format(entity.getOpenDt(), true)));                
                
        return listData;
    }
    
    /**
     * 指定文字列をユニコードへ変換し取得します。
     * 
     * @param moji
     * @return
     */
    private String convertMoji(String moji){
        if(moji == null){
            return "";
        }
//        return MojiConverter.convertMS932toUnicode(moji).trim();
        return moji.trim();
    }

    public UICsvAutoAmountDataDao getBunsAutoAmtRegistUICsvAutoAmountDataDao() {
        return bunsAutoAmtRegistUICsvAutoAmountDataDao;
    }

    public void setBunsAutoAmtRegistUICsvAutoAmountDataDao(
            UICsvAutoAmountDataDao bunsAutoAmtRegistUICsvAutoAmountDataDao) {
        this.bunsAutoAmtRegistUICsvAutoAmountDataDao = bunsAutoAmtRegistUICsvAutoAmountDataDao;
    }
}