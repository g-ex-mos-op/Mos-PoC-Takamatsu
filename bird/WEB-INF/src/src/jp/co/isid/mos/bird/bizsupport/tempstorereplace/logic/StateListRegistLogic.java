package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 仮店舗置換え状況を更新する
 * @author Aspac
 */
public interface StateListRegistLogic {
    
    /**
     * 仮店舗置換え状況を更新する
     * 
     * @param List
     * @return void
     * @exception ApplicationException
     */
    public void execute(List stateList, String companyCd, String nendo, String userId) throws ApplicationException;
}