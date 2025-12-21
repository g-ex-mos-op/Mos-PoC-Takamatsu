package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 年度プルダウンリストを生成する
 * @author Aspac
 */
public interface ListNendoLogic {
    
    /**
     * 年度プルダウンリストを生成する
     * 
     * @return List
     * @exception ApplicationException
     */
    public List execute() throws ApplicationException;
}