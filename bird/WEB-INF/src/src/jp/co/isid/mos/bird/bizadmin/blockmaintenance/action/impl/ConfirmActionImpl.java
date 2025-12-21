/**
 * 
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.ConfirmAction;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto.RequestDto;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto.SessionDto;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.RegistLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ブロックメンテナンス
 * 確認画面用アクション
 * 
 * @author xkinu
 *
 */
public class ConfirmActionImpl implements ConfirmAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"1";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"2";
    /* アクションID：登録処理 */
    public static final String regist_ACTION_ID 
    	= BlockMaintenanceUtil.ACTION_ID_EDIT+"3";
    
    /* DTO【プルダウンメニュー】 */
    private PullDownMenuDto pullDownMenuDto;
    /* DTO【自画面SessionDto】 */
    private SessionDto blockMainteSesDto;
    /* DTO【自画面RequestDto】 */
    private RequestDto blockMainteReqDto;
    
    /* LOGIC【データ登録】ロジック */
    private RegistLogic blockMainteRegistLogic;
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
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}    	
        //２．編集画面VIEWIDをリターンする。
        return BlockMaintenanceUtil.VIEW_ID_EDIT;
	}

	/**
	 * 確認アクション
	 * 
	 * @return 確認画面VIEW_ID
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.EditAction#confirm()
	 */
	public String regist() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //２．ロジック【入力値のチェック】を実行する。
        Map params = new HashMap();
        params.put(RegistLogic.PK_USERINFO, getBirdUserInfo());
        params.put(RegistLogic.PK_DATEINFO, getBirdDateInfo());
        params.put(RegistLogic.PK_LIST_REG, getBlockMainteSesDto().getListConfirmData());
        getBlockMainteRegistLogic().execute(params);
        
        //３．DTO【プルダウンメニュー】.クリアフラグをtrueに設定する。
        getPullDownMenuDto().setClearFlg(true);
        
        //４．初期画面VIEWIDをリターンする。
		return BlockMaintenanceUtil.VIEW_ID_CONDITION;
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
	 * @return blockMainteRegistLogic を戻します。
	 */
	public RegistLogic getBlockMainteRegistLogic() {
		return blockMainteRegistLogic;
	}

	/**
	 * @param blockMainteRegistLogic 設定する blockMainteRegistLogic。
	 */
	public void setBlockMainteRegistLogic(RegistLogic blockMainteRegistLogic) {
		this.blockMainteRegistLogic = blockMainteRegistLogic;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
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

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto 設定する pullDownMenuDto。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

}
