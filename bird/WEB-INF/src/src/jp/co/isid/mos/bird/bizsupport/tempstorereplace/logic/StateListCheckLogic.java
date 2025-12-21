package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 仮店舗置換え状況リストの入力チェックを行う
 * @author Aspac
 */
public interface StateListCheckLogic {
    
    /**
     * 仮店舗置換え状況リストを生成する
     * 
     * @param stateList
     * @return void
     * @exception ApplicationException
     */
    public void execute(List stateList, String sysdate, String companyCd) throws ApplicationException;
}