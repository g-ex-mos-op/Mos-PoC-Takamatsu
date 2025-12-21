package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.action.MssOnerPointRankRefAction;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.action.impl.MssOnerPointRankRefActionImpl;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.MiseInfoDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.SibuInfoDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.UIOnerMiseCntDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.UIPointDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.UISubTotalPointDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.UITotalPointDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.MiseInfo;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.SibuInfo;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.SearchLogic;

/**
 * 検索ロジック
 * 
 * @author xkinu
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssOnerPointRankRefAction.SCREEN_ID+"L04";
    
    /** 対象オーナー：支部 */
    private static final String SEARCH_SIBU = MssOnerPointRankRefActionImpl.SEARCH_SIBU;
    /** 対象オーナー：オーナー */
    private static final String SEARCH_ONER = MssOnerPointRankRefActionImpl.SEARCH_ONER;
    /** 対象オーナー：個店 */
    private static final String SEARCH_MISE = MssOnerPointRankRefActionImpl.SEARCH_MISE;
    /*【DAO】対象支部情報検索Dao */
    private SibuInfoDao sibuInfoDao;
    /*【DAO】対象店舗情報検索Dao */
    private MiseInfoDao miseInfoDao;
    /*【DAO】総合得点情報検索Dao */
    private UITotalPointDao uiTotalPointDao;
    /*【DAO】中分類総合得点情報検索Dao */
    private UISubTotalPointDao uiSubTotalPointDao;
    /*【DAO】項目別評価値情報検索Dao */
    private UIPointDao uiPointDao;
    /*【DAO】各オーナー保有店舗数情報検索Dao */
    private UIOnerMiseCntDao uiOnerMiseCntDao;
    /**
     * 事前条件処理
     */
    private void validate(Map param) {
        String searchType = (String)param.get("searchType");
        if (isNull((String)param.get("companyCd"))) {
            throw new NotNullException("会社", "companyCd", 0);
        }
        if (isNull(searchType)) {
            throw new NotNullException("支部", "searchType", 0);
        }
        //対象条件が全社以外の場合表示対象コードチェック
        CodeVerifier codeVeri = new CodeVerifier();
        CodeFormatter cdf = new CodeFormatter(5, "00000");
        cdf.setFormatPattern("00000");
        CodeFormatter cdf8 = new CodeFormatter(8, "00000000");
        cdf8.setFormatPattern("00000000");
        if (searchType.equals(SEARCH_SIBU)) {
            if(isNull((String)param.get("sibuCd"))){
                throw new NotNullException("支部", "sibuCd", 0);
            }
            String svCd = (String)param.get("svCd");
            if(!isNull(svCd)) {
                //SVコードのデータ型チェック
                if(!codeVeri.validate(svCd)) {
                    throw new InvalidInputException("SV", "svCd", 0);
                }
                //前ゼロ付加を再設定。
                param.put("svCd", cdf8.format(svCd, true));  
            }
        }
        if (searchType.equals(SEARCH_ONER)) {
            String onerCd = (String)param.get("onerCd");
            if(isNull(onerCd)){
                throw new NotNullException("オーナー", "onerCd", 0);
            }
            if(!codeVeri.validate(onerCd)) {
                throw new InvalidInputException("オーナー", "onerCd", 0);
            }
            param.put("onerCd", cdf.format(onerCd, true));  
        }
        if (searchType.equals(SEARCH_MISE)) {
            String miseCd = (String)param.get("miseCd");
            if(isNull(miseCd)){
                throw new NotNullException("個店", "miseCd", 0);
            }
            if(!codeVeri.validate(miseCd)) {
                throw new InvalidInputException("個店", "miseCd", 0);
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
        String searchType = (String)param.get("searchType");
        List sibuInfo = executeSibuInfo(param);
        SibuInfo entitySibu = (SibuInfo)(sibuInfo.get(0));
        param.put("sibuCds", executeSibus(sibuInfo));
        //対象店舗コードを設定
        param.put("miseCds", executeMises(param));
        List totalPointlist = executeTotalPoint(param);
        List subTotalPointlist = executeSubTotalPoint(param);
        List pointlist = executePoint(param);
        List onerMiseCntlist = executeOnerMiseCnt(param);
        
        if(SEARCH_ONER.equals(searchType)) {
            // オーナー支部検索
            returnMap.put("onerSibulist", sibuInfo);
        }
        //対象支部コードを設定
        returnMap.put("sibuCd", entitySibu.getSibuCd());
        returnMap.put("sibuName", entitySibu.getSibuName());
        // 総合得点データ検索
        returnMap.put("totalPointlist", totalPointlist);
        // 中分類平均データ検索
        returnMap.put("subTotalPointlist", subTotalPointlist);
        // 各項目評価データ検索
        returnMap.put("pointlist", pointlist);
        // オーナー保有店数
        returnMap.put("onerMiseCntlist", onerMiseCntlist);
        
        return returnMap;
    }
    /**
     * 対象対象支部検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeSibuInfo(Map param) throws Exception {
        //検索データを戻す。
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String sibuCd = (String)param.get("sibuCd");
        List list = getMssOnerPointRankSibuInfoDao().select(
                  userId
                , userTypeCd
                , limitFlg
                , searchType
                , nendo
                , kai
                , companyCd
                , sibuCd
                , (String)param.get("onerCd")
                , (String)param.get("miseCd"));
        if(list == null || list.size() == 0){
            throw new NoResultException();
        }
        return list;
    }
    /**
     * 対象対象支部検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeMiseInfo(Map param) throws Exception {
        //検索データを戻す。
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String companyCd = (String)param.get("companyCd");
        String[] sibuCd = (String[])param.get("sibuCds");
        List list = getMssOnerPointRankMiseInfoDao().select(
                  userId
                , userTypeCd
                , limitFlg
                , searchType
                , companyCd
                , sibuCd
                , (String)param.get("svCd"));
        if(list == null || list.size() == 0){
            throw new NoResultException();
        }
        return list;
    }
    /**
     * 対象支部の配列取得処理
     * 
     * @param List sibulist 対象支部情報保持クラス
     * @exception Exception
     */
    private String[] executeSibus(List sibulist) throws Exception {
        //検索処理実行
        if(sibulist == null) {
            return null;
        }
        String sibus[] = new String[sibulist.size()];
        for(int i=0; i<sibulist.size(); i++){
            SibuInfo entity = (SibuInfo)(sibulist.get(i));
            sibus[i] = entity.getSibuCd();
        }
        return sibus;
    }
    /**
     * 対象店舗の配列取得処理
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private String[] executeMises(Map param) throws Exception {
        //検索処理実行
        List listMiseInfo = executeMiseInfo(param);
        String mises[] = new String[listMiseInfo.size()];
        for(int i=0; i<listMiseInfo.size(); i++){
            MiseInfo entity = (MiseInfo)(listMiseInfo.get(i));
            mises[i] = entity.getMiseCd();
        }
        return mises;
    }
    /**
     * 総合得点データ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeTotalPoint(Map param) throws Exception {
        //検索データを戻す。
        List list = null;
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String svCd = (String)param.get("svCd");
        String[] miseCd = (String[])param.get("miseCds");
        if(SEARCH_SIBU.equals(searchType) && svCd != null && svCd.trim().length()> 0){
            list = getMssOnerPointRankUITotalPointDao().selectSv(
                    userId
                    , userTypeCd
                    , limitFlg
                    , nendo
                    , kai
                    , companyCd
                    , (String)param.get("sibuCd")
                    , svCd
                    , miseCd);
        }else {
            list = getMssOnerPointRankUITotalPointDao().select(
                    userId
                    , userTypeCd
                    , limitFlg
                    , searchType
                    , nendo
                    , kai
                    , companyCd
                    , (String[])param.get("sibuCds")
                    , svCd
                    , miseCd);
        }
        if(list == null || list.size() == 0){
            throw new NoResultException();
        }
        return list;
    }
    /**
     * 中分類総合得点データ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executeSubTotalPoint(Map param) throws Exception {
        List list = null;
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String sibuCd = (String)param.get("sibuCd");
        String svCd = (String)param.get("svCd");
        String[] miseCd = (String[])param.get("miseCds");
         
        if(SEARCH_SIBU.equals(searchType) && svCd != null && svCd.trim().length()> 0){
            list = getMssOnerPointRankUISubTotalPointDao().selectSv(
                    userId
                    , userTypeCd
                    , limitFlg
                    , nendo
                    , kai
                    , companyCd
                    , sibuCd
                    , svCd
                    , miseCd);
        }else {
            list = getMssOnerPointRankUISubTotalPointDao().select(
                    userId
                    , userTypeCd
                    , limitFlg
                    , searchType
                    , nendo
                    , kai
                    , companyCd
                    , (String[])param.get("sibuCds")
                    , (String)param.get("onerCd")
                    , miseCd);
        }
        if(list == null || list.size() == 0){
            throw new NoResultException("中分類総合得点");
        }
        return list;
    }
    /**
     * 各項目評価データ検索を行う
     * 
     * @param Map param 条件データ保持クラス
     * @exception Exception
     */
    private List executePoint(Map param) throws Exception {
        List list = null;
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String sibuCd = (String)param.get("sibuCd");
        String svCd = (String)param.get("svCd");
        String[] miseCd = (String[])param.get("miseCds");
         
        if(SEARCH_SIBU.equals(searchType) && svCd != null && svCd.trim().length()> 0){
            list = getMssOnerPointRankUIPointDao().selectSv(
                    userId
                    , userTypeCd
                    , limitFlg
                    , nendo
                    , kai
                    , companyCd
                    , sibuCd
                    , svCd
                    , miseCd);
        }else {
            
            list = getMssOnerPointRankUIPointDao().select(
                    userId
                    , userTypeCd
                    , limitFlg
                    , searchType
                    , nendo
                    , kai
                    , companyCd
                    , (String[])param.get("sibuCds")
                    , (String)param.get("onerCd")
                    , miseCd);
        }
        if(list == null || list.size() == 0){
            throw new NoResultException("各項目評価");
        }
       return list;
    }
    /**
     * オーナー保有店舗数
     * @param param
     * @return
     * @throws Exception
     */
    private List executeOnerMiseCnt(Map param) throws Exception {
        String userId = (String)param.get("userId");
        String userTypeCd = (String)param.get("userTypeCd");
        boolean limitFlg = ((Boolean)param.get("limit")).booleanValue();
        String searchType = (String)param.get("searchType");
        String nendo = (String)param.get("nendo");
        String kai = (String)param.get("kai");
        String companyCd = (String)param.get("companyCd");
        String[] sibuCd = (String[])param.get("sibuCds");
        String svCd = (String)param.get("svCd");
        //検索データを戻す。
        List list =  getMssOnerPointRankUIOnerMiseCntDao().select(
                  userId
                , userTypeCd
                , limitFlg
                , searchType
                , nendo
                , kai
                , companyCd
                , sibuCd
                , svCd
                , (String)param.get("onerCd")
                , (String[])param.get("miseCds")
                );
        if(list == null || list.size() == 0){
            throw new NoResultException("オーナー保有店舗数");
        }
        return list;
    }

    /**
     * 対象支部検索Dao取得処理
     * 
     * @return sibuInfoDao を戻します。
     */
    public SibuInfoDao getMssOnerPointRankSibuInfoDao() {
        return sibuInfoDao;
    }
    /**
     * 対象支部検索Dao設定処理
     * 
     * @param uiSuiiDao を設定。
     */
    public void setMssOnerPointRankSibuInfoDao(SibuInfoDao dao) {
        this.sibuInfoDao = dao;
    }
    /**
    /**
     * 対象店舗情報検索Dao取得処理
     * 
     * @return miseInfoDao を戻します。
     */
    public MiseInfoDao getMssOnerPointRankMiseInfoDao() {
        return miseInfoDao;
    }
    /**
     * 対象店舗情報検索Dao設定処理
     * 
     * @param miseInfoDao を設定。
     */
    public void setMssOnerPointRankMiseInfoDao(MiseInfoDao dao) {
        this.miseInfoDao = dao;
    }
    /**
     * 総合得点情報検索Dao取得処理
     * 
     * @return uiTotalPointDao を戻します。
     */
    public UITotalPointDao getMssOnerPointRankUITotalPointDao() {
        return uiTotalPointDao;
    }
    /**
     * 総合得点情報検索Dao設定処理
     * 
     * @param uiTotalPointDao を設定。
     */
    public void setMssOnerPointRankUITotalPointDao(UITotalPointDao dao) {
        this.uiTotalPointDao = dao;
    }
    /**
     * 中分類総合得点情報検索Dao取得処理
     * 
     * @return uiSubTotalPointDao を戻します。
     */
    public UISubTotalPointDao getMssOnerPointRankUISubTotalPointDao() {
        return uiSubTotalPointDao;
    }
    /**
     * 中分類総合得点情報検索Dao設定処理
     * 
     * @param uiSubTotalPointDao を設定。
     */
    public void setMssOnerPointRankUISubTotalPointDao(UISubTotalPointDao dao) {
        this.uiSubTotalPointDao = dao;
    }
    /**
     * 項目別評価情報検索Dao取得処理
     * 
     * @return uiPointDao を戻します。
     */
    public UIPointDao getMssOnerPointRankUIPointDao() {
        return uiPointDao;
    }
    /**
     * 各オーナー保有店舗数情報検索Dao設定処理
     * 
     * @param uiOnerMiseCntDao を設定。
     */
    public void setMssOnerPointRankUIOnerMiseCntDao(UIOnerMiseCntDao dao) {
        this.uiOnerMiseCntDao = dao;
    }
    /**
     * 各オーナー保有店舗数情報検索Dao取得処理
     * 
     * @return uiOnerMiseCntDao を戻します。
     */
    public UIOnerMiseCntDao getMssOnerPointRankUIOnerMiseCntDao() {
        return uiOnerMiseCntDao;
    }
    /**
     * 項目別評価情報検索Dao設定処理
     * 
     * @param uiPointDao を設定。
     */
    public void setMssOnerPointRankUIPointDao(UIPointDao dao) {
        this.uiPointDao = dao;
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
