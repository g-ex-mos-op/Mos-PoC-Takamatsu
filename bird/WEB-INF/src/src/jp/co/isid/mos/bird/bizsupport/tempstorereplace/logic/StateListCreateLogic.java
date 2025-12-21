package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 仮店舗置換え状況リストを生成する
 * @author Aspac
 */
public interface StateListCreateLogic {
    
    /**
     * 仮店舗置換え状況リストを生成する
     * 
     * @param companyCd
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd, String nendo, String targetCd, String code, String fixedKind, String sysdate) throws ApplicationException;
}