package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dto.MosChickenStateConfirmDto;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.UIMCStatusInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 予約・在庫状況確認
 * static 処理保持クラス
 * 
 * 下記の機能を保持しています。
 * ■条件項目の対象日付生成処理
 * ■入力項目の妥当性チェック処理
 * ■String用Null判断処理
 * 
 * @author xkinu
 *
 */
public class MosChickenStateConfirmUtil {
    /* 画面ID */
    public static final String SCREEN_ID = "BBS018";
    /* VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /* VIEWID：編集画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V02";
    public static final String VIEW_ID_ONERSEARCH   = "BCO006V01";//オーナー選択
    /* ユーザータイプコード：本部 */
    public static final String USER_TYPE_CD_HONBU = "01";
    /* ユーザータイプコード：オーナー */
    public static final String USER_TYPE_CD_ONER = "02";
    /* ユーザータイプコード：店舗 */
    public static final String USER_TYPE_CD_MISE = "03";
    /** 画面遷移区分：初期値 [-1] */
    public static final int SCENECHANGE_KBN_INIT = -1;
    /** 画面遷移区分：現行画面からの遷移時 [0] */
    public static final int SCENECHANGE_KBN_SELF = 0;
    /** 画面遷移区分：編集画面への遷移時 [1] */
    public static final int SCENECHANGE_KBN_VIEW = 1;
    /** 画面遷移区分：オーナー検索画面への遷移時 [10] */
    public static final int SCENECHANGE_KBN_ONERSEARCH = 10;
    //複数Window対応
    // VIEW_ID(操作エラー)
    public static final String operationErr_VIEW_ID = "operation.Err";
    
    private MosChickenStateConfirmUtil(){
        super();
    }
    /**
     * 指定管理番号条件項目生成処理
     * 
     * @param targetCkanriNo
     * @param listKanriKikan
     * @return list 条件項目『対象日付』リスト
     */
    public static List createListJokenData(String targetCkanriNo, List listKanriKikan){
        List listData = null;
        if(targetCkanriNo == null || (listKanriKikan == null || listKanriKikan.size() == 0)){
            return listData;
        }
        listData = new ArrayList();
        listData.add(0, createTargetDateList(targetCkanriNo, listKanriKikan));
        return listData;
    }
    /**
     * 条件項目の対象日付生成処理
     * 
     * @param targetCkanriNo
     * @param listKanriKikan
     * @return list 条件項目『対象日付』リスト
     */
    private static List createTargetDateList(String targetCkanriNo, List listKanriKikan){
        List listDate = null;
        //１．事前条件判断処理
        if(targetCkanriNo == null || (listKanriKikan == null || listKanriKikan.size() == 0)){
            return listDate;
        }
        //２．日付コンバーターをインスタンス化する。
        DateFormatter dateCnvt = new DateFormatter();
        String targetFrom = "";
        String targetTo = "";
        listDate = new ArrayList();
        for(int i=0; i<listKanriKikan.size(); i++) {
            MstKanriKikan entity = (MstKanriKikan)listKanriKikan.get(i);
            if(targetCkanriNo.equals(entity.getCkanriNo())){
                targetFrom = entity.getTargetFrom();
                targetTo = entity.getTargetTo();
                //日付リストをインスタンス化します。
                listDate = new ArrayList();
                String setDate = targetTo;
                //日付をリストへ格納していきます。
                for(int d=0; setDate.compareTo(targetFrom) >= 0; d++){
                    SelectItem entityCode = new SelectItem();
                    //Valueフィールド値設定
                    entityCode.setValue(setDate);
                    //ラベルフィールド値設定
                    entityCode.setLabel((String)dateCnvt.format(setDate, true));
                    listDate.add(d, entityCode);
                    try{
                        setDate = DateManager.getPrevDate(setDate, 1);
                    }catch(Exception e){
                        throw new FtlSystemException("予約・在庫状況確認 [static 処理保持クラス].条件項目の対象日付生成");
                    }
                }
                break;
            }
        }
        
        return listDate;
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * 入力項目の妥当性チェック処理
     * 
     * @param dto
     */
    public static String checkOnerCd(String onerCd){
        if(MosChickenStateConfirmUtil.isNull(onerCd)){
            //MSG【E0507】(を入力してください。)
            throw new NoInputException("オーナーコード", "onerCd", 0);
        }
        //対象条件が全社以外の場合表示対象コードチェック
        CodeVerifier codeVeri = new CodeVerifier(true);
        //コードフォーマットチェック
        if(!codeVeri.validate(onerCd) || onerCd.length() > 5) {
            throw new InvalidInputException("オーナーコード", "onerCd", 0);
        }
//        CodeFormatter cdf = new CodeFormatter(5, "00000");
//        cdf.setFormatPattern("00000");
//        onerCd = cdf.format(onerCd, true);
        return onerCd;                     
    }
    /**
     * 入力項目の妥当性チェック処理
     * 
     * @param dto
     */
    public static void checkInputParam(MosChickenStateConfirmDto dto){
        List listStatusInfo = dto.getListSearchData();
        if(listStatusInfo != null){
            for(int i=0; i<listStatusInfo.size(); i++){
                UIMCStatusInfo entity = (UIMCStatusInfo)listStatusInfo.get(i);
                String strAmtCase = entity.getStrAmtCase();
                entity.setStrAmtCase(checkingAmt(strAmtCase, "strAmtCase", "前日在庫(C/S)", i));
                String strAmtBara = entity.getStrAmtBara();
                entity.setStrAmtBara(checkingAmt(strAmtBara, "strAmtBara", "前日在庫(バラ)", i));
                String strAmtBl   = entity.getStrAmtBl();
                entity.setStrAmtBl(checkingAmt(strAmtBl, "strAmtBl", "当日貸借(バラ)", i));
               
            }
        }
        
    }
    /**
     * 数値チェック処理
     * 
     * チェック１．空(orNull)でないか？　空(orNull)の場合は0(ゼロ)を設定する。
     * チェック２．ゼロより大きいか？
     * チェック３．マイナス値でなく、半角数字、11桁以内。
     * 
     * @param strAmt
     * @param htmlLabel
     * @param htmlName
     * @param htmlIndex
     */
    private static String checkingAmt(String strAmt, String htmlName, String htmlLabel, int htmlIndex){
        NumericVerifier numVer = new NumericVerifier(true, 11, 0);
        if(MosChickenStateConfirmUtil.isNull(strAmt)){
            strAmt = "0";
        }
        if(!numVer.validate(strAmt)){
            // MSG【E0505】
            throw new InvalidInputException(htmlLabel, htmlName, htmlIndex);
        }
        try{
            BigDecimal decAmt = new BigDecimal(strAmt);
            if(!"strAmtBl".equals(htmlName) && decAmt.compareTo(new BigDecimal("0")) < 0){
                //当日貸借(バラ)以外の項目はマイナス不可
                //MSG【E0606】
                throw new NotRelevantException(htmlLabel, "0以上", htmlName, htmlIndex);
            }
            if(decAmt.compareTo(new BigDecimal("99999")) > 0 || decAmt.compareTo(new BigDecimal("-99999")) < 0){
                //MSG【E0507】を入力してください。
                throw new NoInputException(htmlLabel+"は整数5桁以内の数値", htmlName, htmlIndex);
            }
        }
        catch(NumberFormatException numEx){
        }
        return strAmt;
        
    }
}
