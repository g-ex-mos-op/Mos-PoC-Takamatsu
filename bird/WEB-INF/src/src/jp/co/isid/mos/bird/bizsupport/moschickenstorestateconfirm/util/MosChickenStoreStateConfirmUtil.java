package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 店別予約状況一覧
 * static 処理保持クラス
 * 
 * 下記の機能を保持しています。
 * ■条件項目の対象日付生成処理
 * ■String用Null判断処理
 * 
 * @author xkinu
 */
public class MosChickenStoreStateConfirmUtil {
    /* 画面ID */
    public static final String SCREEN_ID = "BBS021";
    /* VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /* VIEWID：確認画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V02";
    /* ユーザータイプコード：本部 */
    public static final String USER_TYPE_CD_HONBU = "01";
    /* ユーザータイプコード：オーナー */
    public static final String USER_TYPE_CD_ONER = "02";
    /* ユーザータイプコード：店舗 */
    public static final String USER_TYPE_CD_MISE = "03";
    
    private MosChickenStoreStateConfirmUtil(){
        super();
    }
    /**
     * 条件項目の対象日付生成処理
     * 
     * @param targetCkanriNo
     * @param listKanriKikan
     * @return list 条件項目『対象日付』リスト
     */
    public static List createTargetDateList(String targetCkanriNo, List listKanriKikan){
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
                String setDate = targetFrom;
                //日付をリストへ格納していきます。
                for(int d=0; setDate.compareTo(targetTo) < 1; d++){
                    SelectItem entityCode = new SelectItem();
                    //Valueフィールド値設定
                    entityCode.setValue(setDate);
                    //ラベルフィールド値設定
                    entityCode.setLabel((String)dateCnvt.format(setDate, true));
                    listDate.add(d, entityCode);
                    try{
                        setDate = DateManager.getNextDate(setDate, 1);
                    }catch(Exception e){
                        throw new FtlSystemException("店別予約状況一覧 [static 処理保持クラス].条件項目の対象日付生成");
                    }
                }
                break;
            }
        }
        
        return listDate;
    }
    /**
     * 検索条件対象期間チェック処理
     * 
     * 対象期間の範囲を最大３日間に限定する。
     * 期間が４日以上の場合は「選択可能な期間は最大３日間です。」のメッセージの例外を発生させる。
     * @param fromDt
     * @param toDt
     */
    public static void checkParamDate(String fromDt, String toDt) {
        String maxDate = "";
        try{
            maxDate = DateManager.getNextDate(fromDt, 2);
        }catch(Exception e){
            throw new FtlSystemException("店別予約状況一覧 [static 処理保持クラス].検索条件対象期間チェック");
        }
        if(toDt.compareTo(fromDt) < 0 || toDt.compareTo(maxDate) >  0){
            throw new GenericMessageException("対象期間の選択可能な期間は最大３日間です。", "taishoDateFrom", 0);
        }
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
