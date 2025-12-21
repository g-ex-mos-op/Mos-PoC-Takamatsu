package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao.UIMCStatusInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.util.MosChickenStoreStateConfirmUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 店別予約状況一覧(モスチキン用画面）
 * 検索結果の取得ロジック
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"L03";
    /* パラメーターキー：管理番号 */
    public static final String PK_CKANRI_NO = "cKanriNo";
    /* パラメーターキー：会社コード */
    public static final String PK_COMPANY_CD = "companyCd";
    /* パラメーターキー：食包材コード */
    public static final String PK_SHOKU_CD = "shokuCd";
    /* パラメーターキー：支部コード */
    public static final String PK_SIBU_CD = "sibuCd";
    /* パラメーターキー：日付 */
    public static final String PK_DATE_FROM = "dateFrom";
    /* パラメーターキー：日付 */
    public static final String PK_DATE_TO = "dateTo";
    /* パラメーターキー：システム日付 */
    public static final String PK_SYSDATE = "sysDate";
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* DAO【予約状況情報】*/
    private UIMCStatusInfoDao mosChickenStoreStateUIMCStatusInfoDao;
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        String cKanriNo = (String)params.get(PK_CKANRI_NO);
        if(MosChickenStoreStateConfirmUtil.isNull(cKanriNo)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("管理番号");
        }
        String companyCd=(String)params.get(PK_COMPANY_CD);
        if(MosChickenStoreStateConfirmUtil.isNull(companyCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("会社コード");
        }
        String shokuCd = (String)params.get(PK_SHOKU_CD);
        if(MosChickenStoreStateConfirmUtil.isNull(shokuCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("食包材コード");
        }
        String sibuCd = (String)params.get(PK_SIBU_CD);
        if(MosChickenStoreStateConfirmUtil.isNull(sibuCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("支部コード");
        }
        String dateFrom = (String)params.get(PK_DATE_FROM);
        if(dateFrom == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("対象期間FROM", "taishoDateFrom", 0);
        }
        String dateTo = (String)params.get(PK_DATE_TO);
        if(dateTo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("対象期間TO", "taishoDateTo", 0);
        }
        if(dateFrom.compareTo(dateTo) > 0){
            //MSG【E0505】（が正しくありません。）
            throw new InvalidInputException("対象期間", "taishoDateFrom", 0);
        }
        //対象期間範囲チェック(最大3日間)
        MosChickenStoreStateConfirmUtil.checkParamDate(dateFrom, dateTo);
        
        String sysDate = (String)params.get(PK_SYSDATE);
        if(sysDate == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("システム日付");
        }
    }
    /**
     * 実行処理メソッド
     * 
     */
    public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．リターン値Listをインスタンス化する。
        List list = new ArrayList();
        String companyCd=(String)params.get(PK_COMPANY_CD);
        String cKanriNo = (String)params.get(PK_CKANRI_NO);
        String shokuCd = (String)params.get(PK_SHOKU_CD);
        String sibuCd = (String)params.get(PK_SIBU_CD);
        String dateFrom = (String)params.get(PK_DATE_FROM);
        String dateTo = (String)params.get(PK_DATE_TO);
        String sysDate = (String)params.get(PK_SYSDATE);
        //３．Dao【予約状況情報】の検索を実行する。
        list = getMosChickenStoreStateUIMCStatusInfoDao().select(
                companyCd, cKanriNo, shokuCd, sibuCd, dateFrom, dateTo, sysDate);
        //４．リターン値Listをリターンする。
        return list;
    }
    /**
     * @return mosChickenStoreStateUIMCStatusInfoDao を戻します。
     */
    public UIMCStatusInfoDao getMosChickenStoreStateUIMCStatusInfoDao() {
        return mosChickenStoreStateUIMCStatusInfoDao;
    }
    /**
     * @param mosChickenStoreStateUIMCStatusInfoDao 設定する mosChickenStoreStateUIMCStatusInfoDao。
     */
    public void setMosChickenStoreStateUIMCStatusInfoDao(
            UIMCStatusInfoDao mosChickenStoreStateUIMCStatusInfoDao) {
        this.mosChickenStoreStateUIMCStatusInfoDao = mosChickenStoreStateUIMCStatusInfoDao;
    }

}
