package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 事業プルダウンリストを生成する
 * @author Aspac
 */
public interface ListJigyoLogic {
    
    /**
     * 事業プルダウンリストを生成する
     * 
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException;
}