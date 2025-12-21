/**
 * 2007/04/18
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.MoveBlockLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;

/**
 * ブロックメンテナンス
 * 選択店舗新ブロック設定ロジック
 * 
 * 店舗ブロック情報の新ブロックのデータを指定値にします。
 * 
 * @author xkinu
 *
 */
public class MoveBlockLogicImpl implements MoveBlockLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BlockMaintenanceUtil.LOGIC_ID_SETMOVEBLOCK;
    
    /**
     * 事前条件処理
     * @param map
     * @throws MissingDataException
     * @throws NotSelectedException
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
        String blockCd = (String)params.get(PK_MOVE_BLOCK_CD);
        if(blockCd == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("移動先ブロックコード");
        }
        String blockName = (String)params.get(PK_MOVE_BLOCK_NAME);
        if(blockName == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("移動先ブロック名称");
        }
        //店舗が選択されているかのチェック
        boolean isExistData = false;
        for (int i=0; i<regList.size(); i++) {
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)regList.get(i);
        	if (entity.isSelect()) {
        		isExistData = true;
        		break;
        	}
        }
        if(!isExistData){
        	//MSG【E0409】店舗を選択してください。
        	throw new NotSelectedException("店舗");
        }
        
    }
	/**
	 * 実行処理
     * 
     * @param params
     * @return 
	 */
	public Map execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．変数.リターン値Mapを生成する。
        Map rparam = new HashMap();
        //３．変数.フォーカス対象インデックスを初期値0で生成する。
        int focusIndex = 0;
        //４．変数.フォーカス対象インデックス設定判断フラグをfalseで生成する。
        boolean indexSeted = false;
        
        List regList = (List)params.get(PK_LIST_REG);
        String blockCd = (String)params.get(PK_MOVE_BLOCK_CD);
        String blockName = (String)params.get(PK_MOVE_BLOCK_NAME);
        String changeFlg = (String)params.get(PK_MOVE_BLOCK_CHANGE_FLG);
        
        //５．パラメーター.[[登録対象データ]]の件数分下記の処理を行う。
        for (int i=0; i<regList.size(); i++) {
        	//５－１．[[登録対象データ]]から現行の[登録対象データ]を取得する。
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)regList.get(i);
        	if (entity.isSelect()) {
                //５－２．[登録対象データ].選択判断フラグがtrueの場合、以下の値を設定する。
        		//[登録対象データ].選択判断フラグ = false
        		entity.setSelect(false);
        		//[登録対象データ].移動対象ブロックコード = パラメーター.移動対象ブロックコード
        		entity.setMoveBlockCd(blockCd);
        		//[登録対象データ].移動対象ブロック名称 = パラメーター.移動対象ブロック名称
        		entity.setMoveBlockName(blockName);
        		//[登録対象データ].移動対象ブロック画面変更フラグ = パラメーター.移動対象ブロック画面変更フラグ
        		entity.setMoveBlockChangeFlg(changeFlg);
        		
            	if(entity.isDispFlg() && !indexSeted) {
            		//５－３．[登録対象データ].表示・非表示判断フラグ==trueで、
            		//       かつ変数.フォーカス対象インデックス設定判断フラグ==falseの場合に
            		//       以下の値を設定する。
            		//変数.フォーカス対象インデックス　＝　for文の現行インデックス
            		focusIndex = i;
            		//変数.フォーカス対象インデックス設定判断フラグ　＝　true
            		indexSeted = true;
            	}
        	}
        }
        //３．フォーカス対象インデックスをリターン値Mapへ保持する。
        rparam.put(RK_FORCUS_INDEX, new Integer(focusIndex));
        return rparam;
	}
}
