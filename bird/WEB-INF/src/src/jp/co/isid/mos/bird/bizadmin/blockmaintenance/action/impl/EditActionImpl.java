/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto.RequestDto;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto.SessionDto;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.CodBlock;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.MoveBlockLogic;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.ResetBlockLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ブロックメンテナンス
 * 編集画面用アクション
 * 
 * @author xkinu
 *
 */
public class EditActionImpl implements EditAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"1";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"2";
    /* アクションID：確認処理 */
    public static final String confirm_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"3";
    /* アクションID：ブロック移動処理 */
    public static final String moveBlock_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"4";
    /* アクションID：ブロックリセット処理 */
    public static final String resetBlock_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"5";
    /* アクションID：対象ブロックプルダウン変更処理 */
    public static final String changeDispBlock_ACTION_ID 
	= BlockMaintenanceUtil.ACTION_ID_EDIT+"6";
    
    /* DTO【自画面SessionDto】 */
    private SessionDto blockMainteSesDto;
    /* DTO【自画面RequestDto】 */
    private RequestDto blockMainteReqDto;
    
    /* LOGIC【入力値のチェック】ロジック */
    private CheckInputDataLogic blockMainteCheckInputDataLogic;
    /* LOGIC【選択店舗新ブロック設定】ロジック */
    private MoveBlockLogic blockMainteMoveBlockLogic;
    /* LOGIC【新ブロックリセット】ロジック */
    private ResetBlockLogic blockMainteResetBlockLogic;
    /* セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();

	/**
	 * 初期処理 アクション
     * 
     * @return null 画面ID
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#initialize()
	 */
	public String initialize() {
		return null;
	}

	/**
	 * 戻る アクション
     * 
     * @return 初期画面ID
     * 
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#back()
	 */
	public String back() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //２．DTO【Request用】の検索対象条件項目の値をDTO【自画面Session用】から設定する。
        //会社を設定
        getBlockMainteReqDto().setTargetCompanyCd(getBlockMainteSesDto().getTargetCompanyCd());
        //対象支部を設定
        getBlockMainteReqDto().setTargetSibuCd(getBlockMainteSesDto().getTargetSibuCd());
        //３．DTO【Request用】ウィンドウIDをキーにDTO【自画面Session用】.検索結果を削除する。
        getBlockMainteSesDto().setListSearchData(null);
        //４．初期画面VIEWIDをリターンする。
        return BlockMaintenanceUtil.VIEW_ID_CONDITION;
	}
	/**
	 * 対象ブロックプルダウン変更 アクション
     * 
     * @return null 画面ID
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#changeDispBlock()
	 */
	public String changeDispBlock() {
    	//１． 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //２．指定ブロックの表示フラグをONにする。
        boolean existFlg = BlockMaintenanceUtil.changeDispFlg(
        		  getBlockMainteSesDto().getListSearchData()
        		, getBlockMainteReqDto().getDispBlockCd());
        if(!existFlg) {
        	//表示する店舗が存在し無い場合MSG【E0103】を表示する。
        	//throw new NotExistException("該当データ");
            throw new NoResultException();
        }
		return null;
	}
	/**
	 * 選択した店舗の移動アクション
	 * 
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#blockMove()
	 */
	public String moveBlock() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //２．ロジック【選択店舗新ブロック設定】を実行する。
        List regList = getBlockMainteSesDto().getListSearchData();
        String blockCd = getBlockMainteReqDto().getMoveBlockCd();
        CodBlock eBlock = BlockMaintenanceUtil.getCodBlock(
        		getBlockMainteSesDto().getListBlock(), blockCd);
        String blockName = eBlock.getBlockName();
        String changeFlg = eBlock.getChangeFlg();
        Map params = new HashMap();
        //パラメーター：BIRDユーザー情報
        params.put(MoveBlockLogic.PK_USERINFO, getBirdUserInfo());
        //パラメーター：登録対象データ
        params.put(MoveBlockLogic.PK_LIST_REG, regList);
        //パラメーター：移動先ブロックコード
        params.put(MoveBlockLogic.PK_MOVE_BLOCK_CD, blockCd);
        //パラメーター：移動先ブロック名称
        params.put(MoveBlockLogic.PK_MOVE_BLOCK_NAME, blockName);
        //パラメーター：移動先ブロック画面変更フラグ
        params.put(MoveBlockLogic.PK_MOVE_BLOCK_CHANGE_FLG, changeFlg);
        Map rparam = getBlockMainteMoveBlockLogic().execute(params);
        //フォーカス対象インデックスを設定。
        Integer focusIndes = (Integer) rparam.get(MoveBlockLogic.RK_FORCUS_INDEX);
        getBlockMainteReqDto().setFocusIndex(focusIndes.intValue());
        //フォーカス最終行インデックスを設定する。
        getBlockMainteReqDto().setFocusInitIndex(
        		BlockMaintenanceUtil.getFocusInitIndex(regList));
		return null;
	}
	/**
	 * 移動をリセット
	 * 
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#blockMoveReset()
	 */
	public String resetBlock() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //２．ロジック【新ブロックリセット】を実行する。
        List regList = getBlockMainteSesDto().getListSearchData();
        Map params = new HashMap();
        //パラメーター：BIRDユーザー情報
        params.put(ResetBlockLogic.PK_USERINFO, getBirdUserInfo());
        //パラメーター：登録対象データ
        params.put(ResetBlockLogic.PK_LIST_REG, regList);
        getBlockMainteResetBlockLogic().execute(params);
        
		return null;
	}

	/**
	 * 確認アクション
	 * 
	 * @return 確認画面VIEW_ID
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#confirm()
	 */
	public String confirm() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        List regList = getBlockMainteSesDto().getListSearchData();
        //１．ロジック【入力値のチェック】を実行する。
        Map params = new HashMap();
        params.put(CheckInputDataLogic.PK_LIST_REG, regList);
        getBlockMainteCheckInputDataLogic().execute(params);
        
		return BlockMaintenanceUtil.VIEW_ID_CONFIRM;
	}
    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @return
     */
    private boolean isValidSessionKey(){
        return mkSession.isValidSessionKey( 
                     getBlockMainteSesDto().getNowSessionKey()
                  ,  getBlockMainteSesDto().getSessionKey(getBlockMainteReqDto().getWindowId()) );
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
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
	/**
	 * @return blockMainteResetBlockLogic を戻します。
	 */
	public ResetBlockLogic getBlockMainteResetBlockLogic() {
		return blockMainteResetBlockLogic;
	}

	/**
	 * @param blockMainteResetBlockLogic 設定する blockMainteResetBlockLogic。
	 */
	public void setBlockMainteResetBlockLogic(
			ResetBlockLogic blockMainteResetBlockLogic) {
		this.blockMainteResetBlockLogic = blockMainteResetBlockLogic;
	}

	/**
	 * @return blockMainteSesDto を戻します。
	 */
	public SessionDto getBlockMainteSesDto() {
		return blockMainteSesDto;
	}

	/**
	 * @param blockMainteSesDto 設定する blockMainteSesDto。
	 */
	public void setBlockMainteSesDto(SessionDto blockMainteSesDto) {
		this.blockMainteSesDto = blockMainteSesDto;
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

	/**
	 * @return blockMainteCheckInputDataLogic を戻します。
	 */
	public CheckInputDataLogic getBlockMainteCheckInputDataLogic() {
		return blockMainteCheckInputDataLogic;
	}

	/**
	 * @param blockMainteCheckInputDataLogic 設定する blockMainteCheckInputDataLogic。
	 */
	public void setBlockMainteCheckInputDataLogic(
			CheckInputDataLogic blockMainteCheckInputDataLogic) {
		this.blockMainteCheckInputDataLogic = blockMainteCheckInputDataLogic;
	}

	/**
	 * @return blockMainteReqDto を戻します。
	 */
	public RequestDto getBlockMainteReqDto() {
		return blockMainteReqDto;
	}

	/**
	 * @param blockMainteReqDto 設定する blockMainteReqDto。
	 */
	public void setBlockMainteReqDto(RequestDto blockMainteReqDto) {
		this.blockMainteReqDto = blockMainteReqDto;
	}

}
