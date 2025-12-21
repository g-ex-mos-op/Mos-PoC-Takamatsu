package jp.co.isid.mos.bird.storemanage.msstantorankref.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.msstantorankref.action.MssTantotenRankingRefAction;
import jp.co.isid.mos.bird.storemanage.msstantorankref.action.impl.MssTantotenRankingRefActionImpl;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dao.UIKouseiHiDataDao;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dao.UILastNendoKaiDao;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dao.UIRankingDataDao;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.UILastNendoKai;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.SearchLogic;

/**
 * 検索ロジック
 * 
 * @author xkinu
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssTantotenRankingRefAction.SCREEN_ID+"L03";
    
    /** 対象オーナー：SV */
    private static final String SEARCH_SV = MssTantotenRankingRefActionImpl.SEARCH_SV;
    /** 対象オーナー：オーナー */
    private static final String SEARCH_ONER = MssTantotenRankingRefActionImpl.SEARCH_ONER;
    /** 対象オーナー：個店 */
    private static final String SEARCH_MISE = MssTantotenRankingRefActionImpl.SEARCH_MISE;
    /*【DAO】前回実施年度・回数取得*/
    private UILastNendoKaiDao uiLastNendoKaiDao;
    /*【DAO】ランキング情報 */
    private UIRankingDataDao uiRankingDataDao;
    /*【DAO】構成比情報*/
    private UIKouseiHiDataDao uiKouseiHiDataDao;
    /**
     * 事前条件処理
     */
    private void validate(Map param) {
        String searchType = (String)param.get("searchType");
        if (isNull((String)param.get("companyCd"))) {
            throw new NotNullException("会社", "companyCd", 0);
        }
        if (isNull(searchType)) {
            throw new NotSelectedException("SV、オーナーまたは、個店", "searchType", 0);
        }
        //対象条件が全社以外の場合表示対象コードチェック
        CodeVerifier codeVeri = new CodeVerifier();
        CodeFormatter cdf = new CodeFormatter(5, "00000");
        cdf.setFormatPattern("00000");
        CodeFormatter cdf8 = new CodeFormatter(8, "00000000");
        cdf8.setFormatPattern("00000000");
        if (searchType.equals(SEARCH_SV)) {
            String svCd = (String)param.get("svCd");
            if(isNull(svCd)){
                //【MSG】E0507「 ''を入力してください。'’」
                throw new NoInputException("SV", "honbuSvCd", 0);
            }else{
                //SVコードのデータ型チェック
                if(!codeVeri.validate(svCd)) {
                    throw new InvalidInputException("SV", "honbuSvCd", 0);
                }
                //前ゼロ付加を再設定。
                param.put("svCd", cdf8.format(svCd, true));  
            }
        }
        if (searchType.equals(SEARCH_ONER)) {
            String onerCd = (String)param.get("onerCd");
            if(isNull(onerCd)){
                //【MSG】E0507「 ''を入力してください。'’」
                throw new NoInputException("オーナー", "honbuOnerCd", 0);
            }
            if(!codeVeri.validate(onerCd)) {
                throw new InvalidInputException("オーナー", "honbuOnerCd", 0);
            }
            param.put("onerCd", cdf.format(onerCd, true));  
        }
        if (searchType.equals(SEARCH_MISE)) {
            String miseCd = (String)param.get("miseCd");
            if(isNull(miseCd)){
                //【MSG】E0507「 ''を入力してください。'’」
                throw new NoInputException("個店", "honbuMiseCd", 0);
            }
            if(!codeVeri.validate(miseCd)) {
                throw new InvalidInputException("個店", "honbuMiseCd", 0);
            }
            param.put("miseCd", cdf.format(miseCd, true));  
        }
    }
    /**
     * 出力データ検索を行う
     * 
     * @param BizReportRefDto dto 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(Map param) throws Exception {
        //事前条件チェック処理
        validate(param);

        Map returnMap = new HashMap();      
        List rankingDatalist = executeRankingData(param);
        List lastRankingDatalist = executeLastRankingData(param);
        List kouseiHiDatalist = executeKouseiHiData(param);
        List kouseiHiZenkokuDatalist = executeKouseiHiZenkokuData(param);
        // ランキングデータ設定
        returnMap.put("rankingDatalist", rankingDatalist);
        // ランキングデータ設定
        returnMap.put("lastRankingDatalist", lastRankingDatalist);
        // 構成比データ設定
        returnMap.put("kouseiHiDatalist", kouseiHiDatalist);
        // 構成比データ設定
        returnMap.put("kouseiHiZenkokuDatalist", kouseiHiZenkokuDatalist);
        
        return returnMap;
    }
    /**
     * 前回実施年度・回数検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeLastRankingData(Map param) throws Exception {
        //検索データを戻す。
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String companyCd = (String)param.get("companyCd");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        List list = getMssTantotenRankingUILastNendoKaiDao().select(
                  userId
                , userTypeCd
                , limitFlg
                , nendo
                , kai
                , companyCd
                 );
       if(list == null || list.size() == 0){
           return null;
       }
       UILastNendoKai entity = (UILastNendoKai)list.get(0);
       //前回の回数
       kai = entity.getKai();
       String searchType = (String)param.get("searchType");
       String svCd = (String)param.get("svCd");
       String onerCd = (String)param.get("onerCd");
       String miseCd = (String)param.get("miseCd");
       try{
           return executeRankingData(userId, userTypeCd, limitFlg, searchType, nendo, kai, companyCd, svCd, onerCd, miseCd);
       }catch(NoResultException noResultEx) {
           return null;
       }catch(Exception e){
           throw e;
       }
    }
    /**
     * ランキングデータデータ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeRankingData(Map param) throws Exception {
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String svCd = (String)param.get("svCd");
        String onerCd = (String)param.get("onerCd");
        String miseCd = (String)param.get("miseCd");
        return executeRankingData(userId, userTypeCd, limitFlg, searchType, nendo, kai, companyCd, svCd, onerCd, miseCd);
     }
    /**
     * ランキングデータデータ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeRankingData(
            String userId, String userTypeCd, boolean limitFlg
          , String searchType, String nendo,  String kai
          , String companyCd, String svCd, String onerCd, String miseCd) 
    throws Exception 
    {
        //検索データを戻す。
        List list = null;        
        list = getMssTantotenRankingUIRankingDataDao().select(
                userId
                , userTypeCd
                , limitFlg
                , searchType
                , nendo
                , kai
                , companyCd
                , svCd
                , onerCd
                , miseCd);
        if(list == null || list.size() == 0){
            throw new NoResultException();
        }
        return list;
    }
    /**
     * 構成比データ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeKouseiHiData(Map param) throws Exception {
        List list = null;
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
       String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String svCd = (String)param.get("svCd");
        
        list = getMssTantotenRankingUIKouseiHiDataDao().select(
                userId
                , userTypeCd
                , limitFlg
                , searchType
                , nendo
                , kai
                , companyCd
                , svCd
                , (String)param.get("onerCd")
                , (String)param.get("miseCd"));
        if(list == null || list.size() == 0){
            throw new NoResultException("構成比");
        }
        return list;
    }
    /**
     * 全国平均構成比データ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeKouseiHiZenkokuData(Map param) throws Exception {
        List list = null;
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        
        list = getMssTantotenRankingUIKouseiHiDataDao().selectZenkoku(
                userId
                , userTypeCd
                , limitFlg
                , nendo
                , kai
                , companyCd);
        if(list == null || list.size() == 0){
            throw new NoResultException("構成比");
        }
        return list;
    }

    /**
     * 前回実施年度・回数取得 検索Dao取得処理
     * 
     * @return uiLastNendoKaiDao を戻します。
     */
    public UILastNendoKaiDao getMssTantotenRankingUILastNendoKaiDao() {
        return uiLastNendoKaiDao;
    }
    /**
     * 前回実施年度・回数取得 検索Dao設定処理
     * 
     * @param uiLastNendoKaiDao を設定。
     */
    public void setMssTantotenRankingUILastNendoKaiDao(UILastNendoKaiDao dao) {
        this.uiLastNendoKaiDao = dao;
    }
    /**
     * ランキング情報検索Dao取得処理
     * 
     * @return uiRankingDataDao を戻します。
     */
    public UIRankingDataDao getMssTantotenRankingUIRankingDataDao() {
        return uiRankingDataDao;
    }
    /**
     * ランキング情報検索Dao設定処理
     * 
     * @param uiRankingDataDao を設定。
     */
    public void setMssTantotenRankingUIRankingDataDao(UIRankingDataDao dao) {
        this.uiRankingDataDao = dao;
    }
    /**
     * 構成比情報検索Dao取得処理
     * 
     * @return uiKouseiHiDataDao を戻します。
     */
    public UIKouseiHiDataDao getMssTantotenRankingUIKouseiHiDataDao() {
        return uiKouseiHiDataDao;
    }
    /**
     * 構成比情報検索Dao設定処理
     * 
     * @param uiKouseiHiDataDao を設定。
     */
    public void setMssTantotenRankingUIKouseiHiDataDao(UIKouseiHiDataDao dao) {
        this.uiKouseiHiDataDao = dao;
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
