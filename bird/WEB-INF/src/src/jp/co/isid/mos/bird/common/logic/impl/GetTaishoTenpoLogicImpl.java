package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.dao.UIMiseListDao;
import jp.co.isid.mos.bird.common.logic.GetTaishoTenpoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 対象店舗取得ロジック
 * 
 * @author xkinu
 *
 */
public class GetTaishoTenpoLogicImpl implements GetTaishoTenpoLogic {
    /**
     * DAO【対象店舗情報】
     */
    private UIMiseListDao uiMiseListDao;
    
    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        List listTaishoTenpo = null;
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //オーナーコード
        String onerCd = (String)params.get(PK_ONER_CD);
        //対象店舗検索ソートタイプ
        String selectType = (String)params.get(PK_SELECT_TYPE);
        //ソートタイプ
        String sortType = (String)params.get(PK_SORT_TYPE);
        if(SELECT_TYPE_CLOSE_IN.equals(selectType)) {
            if(SORT_TYPE_OPENCLOSEMISE.equals(sortType)) {
                listTaishoTenpo = getUiMiseListDao().getCloseIn(companyCd, onerCd, sysDate);
            }else if(SORT_TYPE_MISE.equals(sortType)) {
                listTaishoTenpo = getUiMiseListDao().getCloseInSortMise(companyCd, onerCd, sysDate);
            }else{
                listTaishoTenpo = getUiMiseListDao().getCloseIn(companyCd, onerCd, sysDate);
            }
        }
        else if (SELECT_TYPE_CLOSE_NOT_IN.equals(selectType)) {
            listTaishoTenpo = getUiMiseListDao().getCloseNotIn(companyCd, onerCd, sysDate);
        }
        else {
            listTaishoTenpo = getUiMiseListDao().getCloseIn(companyCd, onerCd, sysDate);
        }
        return listTaishoTenpo;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        String onerCd = (String)params.get(PK_ONER_CD);
        if(isNull(onerCd)) {
            throw new NotNullException("オーナーコード", "onerCd", 0);
        }
        //対象店舗検索タイプ
        String selectType = (String)params.get(PK_SELECT_TYPE);
        if(isNull(selectType)) {
            params.put(PK_SELECT_TYPE, SELECT_TYPE_CLOSE_IN);
        }
        //ソートタイプ
        String sortType = (String)params.get(PK_SORT_TYPE);
        if(isNull(sortType)) {
            params.put(PK_SORT_TYPE, SORT_TYPE_OPENCLOSEMISE);
        }
    }
    /**
     * @return uiMiseListDao を戻します。
     */
    public UIMiseListDao getUiMiseListDao() {
        return uiMiseListDao;
    }
    /**
     * @param uiMiseListDao 設定する uiMiseListDao。
     */
    public void setUiMiseListDao(UIMiseListDao uiMiseListDao) {
        this.uiMiseListDao = uiMiseListDao;
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
