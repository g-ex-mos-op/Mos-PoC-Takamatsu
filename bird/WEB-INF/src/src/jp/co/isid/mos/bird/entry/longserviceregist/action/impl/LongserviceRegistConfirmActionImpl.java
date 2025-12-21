/*
 * 作成日: 2006/2/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.action.impl;

import jp.co.isid.mos.bird.entry.longserviceregist.action.LongserviceRegistConfirmAction;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.UpdateEntryLogic;

/**
 * 永年勤続マスタ登録　確認画面アクションクラス
 * @author itamoto
 */
public class LongserviceRegistConfirmActionImpl implements LongserviceRegistConfirmAction {
 
	/** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN017A21";
    public static final String regist_ACTION_ID = "BEN017A22";
    public static final String cancel_ACTION_ID = "BEN017A23";
    
    /** 永年勤続マスタ登録情報DTO */
    private LongserviceRegistDto longserviceRegistDto;
    
    /** 永年勤続マスタ情報更新ロジック */
    private UpdateEntryLogic updateUserLogic;

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {	
        // 一覧画面での更新ボタンによる実行の回避
        if(getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_INSERT ||
        		getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_UPDATE ||
        		getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_DELETE){
        	
        	return null;
        }
        
        return LongserviceRegistConstants.VIEW_ID_SELECT;
    }

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceRegistDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }

        // 一覧画面での更新ボタンによる実行の回避
        if(getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_INSERT ||
        		getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_UPDATE ||
        		getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_DELETE){
        
        	// 更新の実行
        	getUpdateUserLogic().execute(getLongserviceRegistDto());
        	
            // 永年勤続マスタ登録情報を削除
            getLongserviceRegistDto().clear();
            getLongserviceRegistDto().setEditMode(LongserviceRegistConstants.EDIT_MODE_INIT);
        }
                
    	return LongserviceRegistConstants.VIEW_ID_SELECT;
	}

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {

        if (getLongserviceRegistDto().isModeDelete()) {
            return LongserviceRegistConstants.VIEW_ID_SELECT;
        }
        else {
            return LongserviceRegistConstants.VIEW_ID_EDIT;
        }
	}
	public LongserviceRegistDto getLongserviceRegistDto() {
		return longserviceRegistDto;
	}
	public void setLongserviceRegistDto(LongserviceRegistDto longserviceRegistDto) {
		this.longserviceRegistDto = longserviceRegistDto;
	}

	public UpdateEntryLogic getUpdateUserLogic() {
		return updateUserLogic;
	}

	public void setUpdateUserLogic(UpdateEntryLogic updateUserLogic) {
		this.updateUserLogic = updateUserLogic;
	}
}

