package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.TrnTempStoreStateDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreStateList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.StateListCreateLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 仮店舗置換え状況を検索する
 * @author Aspac
 */
public class StateListCreateLogicImpl implements StateListCreateLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L06";
    
    
    /**
     * 仮店舗置換え状況Dao
     */
    private TrnTempStoreStateDao trnTempStoreStateDao;
    
    
    
    /**
     * 仮店舗置換え状況Daoを取得します。
     * @return 仮店舗置換え状況Dao
     */
    public TrnTempStoreStateDao getTrnTempStoreStateDao() {
        return trnTempStoreStateDao;
    }
    /**
     * 仮店舗置換え状況Daoを設定します。
     * @param 仮店舗置換え状況Dao
     */
    public void setTrnTempStoreStateDao(TrnTempStoreStateDao trnTempStoreStateDao) {
        this.trnTempStoreStateDao = trnTempStoreStateDao;
    }




    /**
     * 仮店舗置換え状況リストを生成する
     * 
     * @param companyCd
     * @param nendo
     * @param targetCd
     * @param code 事業コード or 支部コード
     * @param fixedKind   0:確定を含まない　1:含む
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd, String nendo, String targetCd, String code, String fixedKind, String sysdate) throws ApplicationException {

        
        //予算対象開始日・予算対象終了日
        String nextn = "";
        try {
            nextn = DateManager.getNextYear(nendo,1);
        } catch (Exception e) {
            ;
        }
        String fromDt = nendo + "04";
        String toDt   = nextn + "03";
        
        List listStateKakutei = new ArrayList();
        List listStateMikakutei = new ArrayList();
        List listStateMitoroku = new ArrayList();
        List listState = new ArrayList();
        
        
        //確定を含む
        if(fixedKind.equals("1")) {
            //確定済みリスト
            listStateKakutei = getTrnTempStoreStateDao().getTempStoreStateKakutei(companyCd, fromDt, toDt, nendo, targetCd, code);
        }
        //未確定リスト
        listStateMikakutei = getTrnTempStoreStateDao().getTempStoreStateMikakutei(companyCd, fromDt, toDt, nendo, targetCd, code);
        
        
        //未登録リスト
        //※レスポンス改善のため置換え状況リストと予算情報リストを別に取得しマージする
        listStateMitoroku  = getTrnTempStoreStateDao().getTempStoreStateMitoroku(companyCd, fromDt, toDt, nendo, targetCd, code);
        List mitorokuYosanList = getTrnTempStoreStateDao().getYosanInfo(companyCd, fromDt, toDt);
        
        //置換え状況リストと予算情報リストをマージする
        for (Iterator ite1 = listStateMitoroku.iterator(); ite1.hasNext();) {
            TrnTempStoreStateList mitorokuinfo = (TrnTempStoreStateList) ite1.next();
            String mitorokuKariCd = mitorokuinfo.getKariCd();
            for (Iterator ite2 = mitorokuYosanList.iterator(); ite2.hasNext();) {
                TrnTempStoreStateList yosaninfo = (TrnTempStoreStateList) ite2.next();
                if(mitorokuKariCd.equals(yosaninfo.getKariCd())) {
                    mitorokuinfo.setYosanDt(yosaninfo.getYosanDt());
                    mitorokuinfo.setYosan(yosaninfo.getYosan());
                }
            }
        }
        
        
        String preDate = "";
        try {
            String sysYYYYMM = getYearMonth(sysdate);
            preDate = DateManager.getPrevMonth(sysYYYYMM,1);
            
            
            //未登録リストデータ調整
            for (Iterator ite = listStateMitoroku.iterator(); ite.hasNext();) {
                TrnTempStoreStateList stateInfo = (TrnTempStoreStateList) ite.next();
                
                //予算設定開始月が当月含む過去２ヶ月の日付けより過去の場合登録不可フラグを設定する
                if(preDate.compareTo(stateInfo.getYosanDt()) > 0 ){
                    stateInfo.setAbleUpdateFlg("0");
                }
            }
        
        } catch (Exception e) {
            throw new FtlSystemException(
            "システム日付の前月取得ロジックでエラーが発生しました。");
        }
        
        
        //未登録、未確定、確定リストをージする
        listState.addAll(listStateKakutei);
        listState.addAll(listStateMikakutei);
        listState.addAll(listStateMitoroku);
        
        if(listState == null || listState.size() <= 0 ){
            throw new NoResultException();
        }
        
        //支部コード・仮店舗コードの昇順でソート
        Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                int ret;
                TrnTempStoreStateList entity1 = (TrnTempStoreStateList) o1;
                TrnTempStoreStateList entity2 = (TrnTempStoreStateList) o2;
                if((ret = entity1.getSibuCd().compareTo(entity2.getSibuCd())) == 0){
                    ret = entity1.getKariCd().compareTo(entity2.getKariCd());
                }
                return ret;
            }
        };
        Collections.sort(listState, comparator);
        
        return listState;
    }
    
    
    /**
     * 年月を取得
     */
    private String getYearMonth(String sysdate) throws ParseException {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyyMM");
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormatter.format(dateFormatter.parse(sysdate));
    }
    
}
