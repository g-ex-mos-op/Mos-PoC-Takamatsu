/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.action.impl;

import jp.co.isid.mos.bird.entry.longserviceregist.action.LongserviceRegistEditAction;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.CheckEntryLogic;

/**
 * 汎用研修マスタ登録　編集画面アクションクラス
 * @author narita
 */
public class LongserviceRegistEditActionImpl implements LongserviceRegistEditAction {

	/** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN017A11";
    public static final String confirm_ACTION_ID = "BEN017A12";
    public static final String cancel_ACTION_ID = "BEN017A13";
    
    /** 永年勤続マスタ登録情報DTO */
    private LongserviceRegistDto longserviceRegistDto;
    
    /** 永年勤続マスタ情報チェックロジック */
    private CheckEntryLogic checkEntryLogic;

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		// 初期処理
        return null;
    }

    /**
     * 確認
     * @return 画面遷移情報
     */
    public String confirm() {

    	// 登録内容チェックを行う
    	getCheckEntryLogic().execute(getLongserviceRegistDto());
    	
    	// 確認画面を表示する。
    	return LongserviceRegistConstants.VIEW_ID_CONFIRM;
	}

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {

    	// 登録画面で編集・入力されたデータをクリアする。
    	//getLongserviceRegistDto().clear();
    	
    	// エディットモードを設定
    	//getLongserviceRegistDto().setEditMode(LongserviceRegistConstants.EDIT_MODE_RETURN);
    	
    	// 初期画面を表示する
    	return LongserviceRegistConstants.VIEW_ID_SELECT;
	}

	public LongserviceRegistDto getLongserviceRegistDto() {
		return longserviceRegistDto;
	}
	public void setLongserviceRegistDto(LongserviceRegistDto longserviceRegistDto) {
		this.longserviceRegistDto = longserviceRegistDto;
	}
	

	/**
	 * 登録内容チェックロジックの設定
	 * @return checkEntryLogic を戻します。
	 */
	public CheckEntryLogic getCheckEntryLogic() {
		return checkEntryLogic;
	}
	/**
	 * 登録内容チェックロジックの設定
	 * @param checkEntryLogic checkEntryLogic を設定。
	 */
	public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
		this.checkEntryLogic = checkEntryLogic;
	}
}

