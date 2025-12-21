/**
 * 2007/04/18
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.MoveBlockLogic;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.ResetBlockLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * ブロックメンテナンス
 * 新ブロックリセットロジック
 * 
 * 店舗ブロック情報の新ブロックのデータを全て空にします。
 * 
 * @author xkinu
 *
 */
public class ResetBlockLogicImpl implements ResetBlockLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BlockMaintenanceUtil.LOGIC_ID_RESETBLOCK;
    /** ロジック【選択店舗新ブロック設定】*/
    private MoveBlockLogic blockMainteMoveBlockLogic;
    
    /**
     * 事前条件処理
     * @param map
     * @throws MissingDataException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
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
     * @return listNewBlock 
	 */
	public void execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．変数.対象データ存在判断値を生成する。
        boolean isExistData = false;
        //パラメーター.[[登録対象データ]]
        List regList = (List)params.get(PK_LIST_REG);
        //３．パラメーター.[[登録対象データ]]の件数分下記の処理を行う。
        for (int i=0; i<regList.size(); i++) {
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)regList.get(i);
        	//1．[登録対象データ].新ブロックコードがNull又は空で無い場合
        	//        下記の処理を行う。
        	if (!BlockMaintenanceUtil.isNull(entity.getMoveBlockCd())) {
        		//1-1.[登録対象データ].選択フラグへ"1"を設定する。
        		entity.setSelectFlg(UIMiseBlockInfo.SELECTED);
        		isExistData = true;
        	}
        }
        //４．変数.対象データ存在判断値がfalseの場合。
        if (!isExistData) {
        	//リセット対象データが存在しない為、ここで処理終了。
        	return;
        }
        //５．パラメーターMapへ
        //ロジック【選択店舗新ブロック設定】のパラメーターキー：移動対象ブロックコード をKeyに
        //空（ブランク）を設定する。
        params.put(MoveBlockLogic.PK_MOVE_BLOCK_CD, "");
        //６．パラメーターMapへ
        //ロジック【選択店舗新ブロック設定】のパラメーターキー：移動対象ブロック名称 をKeyに
        //空（ブランク）を設定する。
        params.put(MoveBlockLogic.PK_MOVE_BLOCK_NAME, "");
        params.put(MoveBlockLogic.PK_MOVE_BLOCK_CHANGE_FLG, "0");
        
        //７．ロジック【選択店舗新ブロック設定】の検索を実行する。
        ///   パラメーター：パラメーターMap
        getBlockMainteMoveBlockLogic().execute(params);
    }
	/**
	 * @return blockMainteMoveBlockLogic を戻します。
	 */
	public MoveBlockLogic getBlockMainteMoveBlockLogic() {
		return blockMainteMoveBlockLogic;
	}
	/**
	 * @param blockMainteMoveBlockLogic 設定する blockMainteMoveBlockLogic。
	 */
	public void setBlockMainteMoveBlockLogic(
			MoveBlockLogic blockMainteMoveBlockLogic) {
		this.blockMainteMoveBlockLogic = blockMainteMoveBlockLogic;
	}

}
