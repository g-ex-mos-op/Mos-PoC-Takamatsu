package jp.co.isid.mos.bird.bizreport.common.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.bizreport.common.logic.CompanyListLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 会社データ検索ロジック
 * 
 * @author xkinu
 */
public class CompanyListLogicImpl implements CompanyListLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR000L07";
    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;
    
    /**
     * 事前条件処理
     * 
     * @throws ApplicationException
     */
    private void validate(Map map) throws ApplicationException {
        boolean isForeingIn = false;
        if(map.containsKey(PK_FOREING_IN) && map.get(PK_FOREING_IN) != null){
	        	isForeingIn = ((Boolean)map.get(PK_FOREING_IN)).booleanValue();
        }
        else {
        	map.put(PK_FOREING_IN, new Boolean(isForeingIn));
        }
    }
    
    /**
     * 条件画面出力データ検索を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param map(2013/02から使用)
     * @exception ApplicationException
     */
    public List execute(Map map) throws ApplicationException {
        
        if(map == null) {
        	map = new HashMap();
        }
        validate(map);

        boolean isForeingIn = ((Boolean)map.get(PK_FOREING_IN)).booleanValue();
        // 会社データ取得
        List list = getCodCompanyDao().select(getBirdUserInfo(), isForeingIn);
        if (list == null || list.size() == 0) {
            throw new NotExistException("会社情報");
        }
        return list;
    }

    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * 会社情報取得Dao取得処理
     * @return codCompanyDao を戻します。
     */
    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }
    /**
     * 会社情報取得Dao設定処理
     * @param codCompanyDao を設定。
     */
    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }
}
