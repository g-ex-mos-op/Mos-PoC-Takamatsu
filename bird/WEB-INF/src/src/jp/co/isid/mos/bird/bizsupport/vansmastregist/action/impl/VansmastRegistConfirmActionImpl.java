/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.action.impl;

import java.sql.Timestamp;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.action.VansmastRegistConfirmAction;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dto.VansmastRegistDto;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.UpdateVansShohinLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * バンズ倉庫別登録　確認画面アクションクラス
 * @author narita
 */
public class VansmastRegistConfirmActionImpl implements VansmastRegistConfirmAction {
 
	/** アクションID定義 */
    public static final String initialize_ACTION_ID 	= "BBS029A21";
    public static final String regist_ACTION_ID 		= "BBS029A22";
    public static final String back_ACTION_ID 		= "BBS029A23";
    
    /** バンズ倉庫別登録DTO */
    private VansmastRegistDto vansmastRegistDto;
    
    /** バンズ倉庫別登録 入力内容チェックロジック */
    private UpdateVansShohinLogic updateVansShohinLogic;
    
	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {	
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
        return null;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
        getVansmastRegistDto().setAddFlg(false);
        getVansmastRegistDto().setErrFlg(false);
        
    	return VansmastRegistConstants.VIEW_ID_EDIT;
	}
    
    /**
     * 登録・終了
     * @return 画面遷移情報
     */
    public String regist() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // BIRDユーザー情報取得
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        // タイムスタンプ取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        
        // 代表商品情報の登録処理を実行
        getUpdateVansShohinLogic().execute(
        		getVansmastRegistDto().getUIVansShohinList(),
        		birdUserInfo,
        		currentTimestamp);
    	
    	// DTOのクリア
        getVansmastRegistDto().clear();
    	
    	return VansmastRegistConstants.VIEW_ID_SELECT;
	}

	public VansmastRegistDto getVansmastRegistDto() {
		return vansmastRegistDto;
	}

	public void setVansmastRegistDto(VansmastRegistDto vansmastRegistDto) {
		this.vansmastRegistDto = vansmastRegistDto;
	}

	public UpdateVansShohinLogic getUpdateVansShohinLogic() {
		return updateVansShohinLogic;
	}

	public void setUpdateVansShohinLogic(UpdateVansShohinLogic updateVansShohinLogic) {
		this.updateVansShohinLogic = updateVansShohinLogic;
	}
 
}

