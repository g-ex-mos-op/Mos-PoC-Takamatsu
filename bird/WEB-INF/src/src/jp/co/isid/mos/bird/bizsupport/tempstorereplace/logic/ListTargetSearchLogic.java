package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 対象条件プルダウンリストを生成する
 * @author Aspac
 */
public interface ListTargetSearchLogic {
    
    /**
     * 対象条件プルダウンリストを生成する
     * 
     * @param companyCd
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException;
}