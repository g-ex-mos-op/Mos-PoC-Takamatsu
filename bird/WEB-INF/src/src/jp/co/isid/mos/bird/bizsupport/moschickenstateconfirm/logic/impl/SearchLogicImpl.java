package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dao.UIMCStatusInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * 検索結果の取得ロジック
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStateConfirmUtil.SCREEN_ID+"L02";
    /* パラメーターキー：管理番号 */
    public static final String PK_CKANRI_NO = "cKanriNo";
    /* パラメーターキー：会社コード */
    public static final String PK_COMPANY_CD = "companyCd";
    /* パラメーターキー：店舗コード */
    public static final String PK_MISE_CD = "miseCd";
    /* パラメーターキー：日付 */
    public static final String PK_DATE = "date";
//add 2019/08/12 USI張 begin
    /* パラメーターキー：対象食包材 */
    public static final String PK_SHOKU_CD = "shokuCd";
//add 2019/08/12 USI張 end
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* DAO【予約・在庫状況情報】*/
    private UIMCStatusInfoDao mosChickenStateUIMCStatusInfoDao;
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        String cKanriNo = (String)params.get(PK_CKANRI_NO);
        if(MosChickenStateConfirmUtil.isNull(cKanriNo)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("管理番号");
        }
        String companyCd=(String)params.get(PK_COMPANY_CD);
        if(MosChickenStateConfirmUtil.isNull(companyCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("会社コード");
        }
        String miseCd = (String)params.get(PK_MISE_CD);
        if(MosChickenStateConfirmUtil.isNull(miseCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("店舗コード");
        }
        String dateYmd = (String)params.get(PK_DATE);
        if(dateYmd == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("日付");
        }
//add 2019/08/12 USI張 begin
        String shokuCd = (String)params.get(PK_SHOKU_CD);
        if(shokuCd == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("対象食包材");
        }
//add 2019/08/12 USI張 end
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
        String miseCd = (String)params.get(PK_MISE_CD);
//add 2019/08/12 USI張 begin
        String shokuCd = (String)params.get(PK_SHOKU_CD);
//add 2019/08/12 USI張 end
        //当日
        String thisYmd = (String)params.get(PK_DATE);
        String nextYmd = "";
        String lastYmd = "";
        try{
            nextYmd = DateManager.getNextDate(thisYmd, 1);
            lastYmd = DateManager.getPrevDate(thisYmd, 1);
        }
        catch(Exception e){
            throw new FtlSystemException("予約・在庫状況確認　検索結果の取得ロジック"
                    , "", e);
            
        }
        //３．Dao【予約・在庫状況情報】の検索を実行する。
//modify 2019/08/12 USI張 begin
//        list = getMosChickenStateUIMCStatusInfoDao().select(companyCd, cKanriNo, miseCd, lastYmd, thisYmd, nextYmd);
        list = getMosChickenStateUIMCStatusInfoDao().select(companyCd, cKanriNo, miseCd, shokuCd , lastYmd, thisYmd, nextYmd);
//modify 2019/08/12 USI張 end
        //４．リターン値Listをリターンする。
        return list;
    }

    /**
     * @return mosChickenStateUIMCStatusInfoDao を戻します。
     */
    public UIMCStatusInfoDao getMosChickenStateUIMCStatusInfoDao() {
        return mosChickenStateUIMCStatusInfoDao;
    }

    /**
     * @param mosChickenStateUIMCStatusInfoDao 設定する mosChickenStateUIMCStatusInfoDao。
     */
    public void setMosChickenStateUIMCStatusInfoDao(
            UIMCStatusInfoDao mosChickenStateUIMCStatusInfoDao) {
        this.mosChickenStateUIMCStatusInfoDao = mosChickenStateUIMCStatusInfoDao;
    }

}
