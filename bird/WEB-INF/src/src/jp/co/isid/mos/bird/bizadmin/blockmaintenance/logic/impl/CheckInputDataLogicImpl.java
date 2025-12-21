/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * ブロックメンテナンス
 * 入力値のチェック ロジック
 * 
 * @author xkinu
 *
 */
public class CheckInputDataLogicImpl implements CheckInputDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BlockMaintenanceUtil.LOGIC_ID_CHECKDATA;

    /**
     * 事前条件処理
     * @param map
     * @throws MissingDataException
     */
    private void validate(final Map params) {
        List regList = (List)params.get(PK_LIST_REG);
        if(regList == null || regList.size() == 0){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("登録対象データ");
        }
        
    }
	/**
	 * 実行処理
     * 
     * @param params
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.CheckInputDataLogic#execute(java.util.Map)
	 */
	public void execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        
        List regList = (List)params.get(PK_LIST_REG);
        //店舗が選択されているかのチェック
        boolean setExistNewBlock = false;
        for (int i=0; i<regList.size(); i++) {
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)regList.get(i);
        	if (!BlockMaintenanceUtil.isNull(entity.getMoveBlockCd())) {
        		setExistNewBlock = true;
        		break;
        	}
        }
        if(!setExistNewBlock){
        	//MSG【E0302】”新ブロック”が設定されていません。
        	throw new MissingConfigException("新ブロック");
        }

	}

}
