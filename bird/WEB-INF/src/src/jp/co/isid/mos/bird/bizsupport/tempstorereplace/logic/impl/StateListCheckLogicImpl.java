package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.MstStoreDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.TrnTempStoreDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.MstStoreInfo;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreInfo;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreStateList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.StateListCheckLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * 仮店舗置換え状況リストの入力チェックを検索する
 * @author Aspac
 */
public class StateListCheckLogicImpl implements StateListCheckLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L07";
    
    
    /**
     * 実店舗確定Dao
     */
    private TrnTempStoreDao trnTempStoreDao;
    
    
    /**
     * 実店舗Dao
     */
    private MstStoreDao mstStoreDao;
    
    
    
    /**
     * 実店舗確定Daoを取得します。
     * @return 実店舗確定Dao
     */
    public TrnTempStoreDao getTrnTempStoreDao() {
        return trnTempStoreDao;
    }
    /**
     * 実店舗確定Daoを設定します。
     * @param 実店舗確定Dao
     */
    public void setTrnTempStoreDao(TrnTempStoreDao trnTempStoreDao) {
        this.trnTempStoreDao = trnTempStoreDao;
    }

    
    
    
    
    /**
     * 実店舗Daoを取得します。
     * @return 実店舗Dao
     */
    public MstStoreDao getMstStoreDao() {
        return mstStoreDao;
    }
    /**
     * 実店舗Daoを設定します。
     * @param 実店舗Dao
     */
    public void setMstStoreDao(MstStoreDao mstStoreDao) {
        this.mstStoreDao = mstStoreDao;
    }
    
    
    
    /**
     * 仮店舗置換え状況リストの入力チェックを行う
     * 
     * @param listState 置換状況リスト
     * @return List
     * @exception ApplicationException
     */
    public void execute(List stateList, String sysdate, String companyCd) throws ApplicationException {

        
        //重複チェックリスト
        Map dplMap = new HashMap();
        
        //検索対象の仮コードリストを生成
        List kariList = new ArrayList();
        for (Iterator ite = stateList.iterator(); ite.hasNext();) {
            TrnTempStoreStateList stateInfo = (TrnTempStoreStateList) ite.next();
            kariList.add(stateInfo.getKariCd());
        }
        //検索対象の仮コード以外の仮コードに置換え対象となっている実店舗を取得リスト生成
        List miseCdListBT43 = new ArrayList();
        List mstListBT43 = getTrnTempStoreDao().getMiseCd(kariList);
        for (Iterator ite = mstListBT43.iterator(); ite.hasNext();) {
            TrnTempStoreInfo storeInfo = (TrnTempStoreInfo) ite.next();
            miseCdListBT43.add(storeInfo.getMiseCd());
        }
        
        //実店番リスト(BM01TENM & BT44MSJY) BT44には存在せず、BM01には存在することを確認
        List miseCdListBT44 = new ArrayList();
        List mstListBT44 = getMstStoreDao().getTempStoreReplaceAllowList(companyCd);
        for (Iterator ite = mstListBT44.iterator(); ite.hasNext();) {
            MstStoreInfo mstStoreInfo = (MstStoreInfo) ite.next();
            miseCdListBT44.add(mstStoreInfo.getMiseCd());
        }
        
        
        
        int listIndex = 0;
        for (Iterator ite = stateList.iterator(); ite.hasNext();) {
            TrnTempStoreStateList stateInfo = (TrnTempStoreStateList) ite.next();
            
            //置換え日(確定日)
            String kakuteiDt = stateInfo.getKakuteiDt();
            
            //実店舗(店舗コード)
            String jituMiseCd = stateInfo.getMiseCd();
            
            
            //置換え日フォーマットチェック
            DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
            if (!isNull(kakuteiDt) && !dateVerifier.validate(kakuteiDt.trim())) {
                throw new GenericMessageException("置換え日のフォーマットが不正です。","kakuteiDt",listIndex);
            }
            
            //置換え日が未入力　＆　実店舗が入力
            if(isNull(kakuteiDt) && !isNull(jituMiseCd)) {
                throw new NoInputException("置換え日","kakuteiDt",listIndex);
            }
            //置換え日が入力　＆　実店舗が未入力
            if(!isNull(kakuteiDt) && isNull(jituMiseCd)) {
                throw new NotSelectedException("実店舗","kakuteiDt",listIndex);
            }
           
            //日付けチェック
            //置換えデータが未入力のデータ、確定済みのデータは対象外
            if(!isNull(kakuteiDt) && !stateInfo.getSetFlg().equals("1")) {
                
                //予算設定開始日
                String yosanDt = stateInfo.getYosanDt();
                //予算設定開始日の翌月
                String yokuYosanDt = "";
                try {
                    yokuYosanDt = DateManager.getNextMonth(yosanDt,1);
                } catch (Exception e) {
                    throw new FtlSystemException(
                    "置換え更新データチェック中に、予算設定開始日の翌月取得ロジックでエラーが発生しました。");
                }
                //置換え日　<=　システム日付け(システム日より過去である場合エラー)
                if(kakuteiDt.compareTo(sysdate) <= 0) {
                    String yokuSysdate = "";
                    try {
                        yokuSysdate = DateManager.getNextDate(sysdate,1);
                    } catch (Exception e) {
                        throw new FtlSystemException(
                        "置換え更新データチェック中に、システム日翌日取得ロジックでエラーが発生しました。");
                    }
                    DateFormatter dateformat = new DateFormatter();
                    yokuSysdate = dateformat.format(yokuSysdate, DateFormatter.PATTERN_NORMAL, DateFormatter.PATTERN_SLASH, true);
                    String msg = "仮店舗置換え日は" + yokuSysdate + "以降の日付けを入力してください。";
                    throw new GenericMessageException(msg,"kakuteiDt",listIndex);
                }
                //置換え日　＞　予算設定開始日の翌月
                yokuYosanDt += "31";
                if(kakuteiDt.compareTo(yokuYosanDt) > 0) {
                    throw new NoInputException("仮店舗置換え日は「予算設定開始月」の翌月以内までの日付け","kakuteiDt",listIndex);
                }
                //クローズ店チェック
                if(stateInfo.getCloseDt().compareTo(sysdate) < 0) {
                    String msg = "実店舗コード「"+ jituMiseCd +"」はCLOSE店です。別の実店舗コードを選択してください。";
                    throw new NoInputException(msg,"kakuteiDt",listIndex);
                }
                
            }
            
            //実店舗チェック
            //置換えデータが未入力のデータ、確定済みのデータは対象外
            if(!isNull(jituMiseCd) && !stateInfo.getSetFlg().equals("1")) {
                
                //実店番重複チェック
                if(dplMap.containsKey(jituMiseCd)) {
                    int idx = Integer.parseInt(dplMap.get(jituMiseCd).toString());
                    String msg = "実店舗コード「"+ jituMiseCd +"」は重複して選択されています。別の実店舗コードを選択してください。";
                    throw new GenericMessageException(msg,"kakuteiDt",idx);
                }
                else {
                    dplMap.put(jituMiseCd,String.valueOf(listIndex));
                }
                //実店番確定済みチェック
                if(miseCdListBT43.contains(jituMiseCd)) {
                    String msg = "実店舗コード「"+ jituMiseCd +"」は既に使用されています。別の実店舗コードを選択してください。";
                    throw new GenericMessageException(msg,"kakuteiDt",listIndex);
                }
                
                //実店舗存在チェック(置換え許可チェック)
                if(miseCdListBT44.contains(jituMiseCd)) {
                    String msg = "実店舗コード「"+ jituMiseCd +"」は既存店として予算が設定されています。別の実店舗を選択して下さい。";
                    throw new GenericMessageException(msg,"kakuteiDt",listIndex);
                }
                
            }
            listIndex++;
        }
        
    }
    
    
    /**
     * Nullチェック
     */
    private boolean isNull(String str){
        if(str==null || str.equals("")) return true;
        return false;
    }
}
