/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoregist.action.impl;

import jp.co.isid.mos.bird.entry.hanyoregist.action.HanyoRegistConfirmAction;
import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.UpdateUserLogic;

/**
 * 汎用研修マスタ登録　確認画面アクションクラス
 * @author itamoto
 */
public class HanyoRegistConfirmActionImpl implements HanyoRegistConfirmAction {

    /* アクションID */
    public static String initialize_ACTION_ID = "BEN004A09";
    public static String regist_ACTION_ID     = "BEN004A10";
    public static String cancel_ACTION_ID     = "BEN004A11";

    /* 汎用研修マスタ登録用DTO */
    private HanyoRegistDto hanyoRegistDto;
    /* セッションキー保持DTO */
    private SessionKeyDto hanyoRegistSessionKeyDto;
    
    /* エントリーマスタ管理の更新ロジック */
    private UpdateUserLogic updateUserLogic;

	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @return hanyoRegistDto を戻します。
	 */
	public HanyoRegistDto getHanyoRegistDto() {
		return hanyoRegistDto;
	}
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @param hanyoRegistDto hanyoRegistDto を設定。
	 */
	public void setHanyoRegistDto(HanyoRegistDto hanyoRegistDto) {
		this.hanyoRegistDto = hanyoRegistDto;
	}

	/**
	 * エントリーマスタ管理の更新ロジックの設定
	 * @return updateUserLogic を戻します。
	 */
	public UpdateUserLogic getUpdateUserLogic() {
		return updateUserLogic;
	}
	/**
	 * エントリーマスタ管理の更新ロジックの設定
	 * @param updateUserLogic updateUserLogic を設定。
	 */
	public void setUpdateUserLogic(UpdateUserLogic updateUserLogic) {
		this.updateUserLogic = updateUserLogic;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		// 初期処理
		if (hanyoRegistDto.isInitFlg()) {
			hanyoRegistDto.setInitFlg(false);
		}
        return null;
    }

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist() {
        // 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
        if (!HanyoRegistCommon.isValidSessionKey(getHanyoRegistSessionKeyDto().getSessionKey(), getHanyoRegistDto().getSessionKey())) {
            return HanyoRegistCommon.operationErr_VIEW_ID;
        }

    	// １．登録モードが’削除’の場合、メッセージダイアログを表示する。（OK/キャンセルボタン有り）
    	// 【Msg】この研修を削除します。現在までの申込の方のデータダウンロードは終了していますか？\n
    	// まだダウンロードが終了していない場合はキャンセルボタンを押してください。\n
    	// 申込状況確認画面から該当研修を選択し、データをダウンロードしてください。
    	// ① キャンセルボタンが押下された場合、処理中断。
    	// ２．ロジック【エントリーマスタ管理の更新】を実行する。
    	// パラメータ： ・ 登録モード( 0：新規  1：編集  2：削除)
    	// ・ [エントリーマスタ管理]
    	// ・ [[エントリー日付管理]]
    	// ・ エントリーコード（05：出張特別研修  30：更新研修）
    	// ・ システム日付
        getUpdateUserLogic().execute(getHanyoRegistDto());
        
        // 登録完了のフラグオン
        getHanyoRegistDto().setCondClearFlg(true);
        
    	return HanyoRegistCommon.SELECT_VIEW_ID;
	}

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
    	// １．確認画面で表示されたデータをクリアする。
		// ２．登録画面を表示する。
		// ３．確認画面に遷移する際、保持しておいたデータを登録画面にセットする。

        if (getHanyoRegistDto().isModeDelete()) {
            getHanyoRegistDto().setCondClearFlg(true);
            return HanyoRegistCommon.SELECT_VIEW_ID;
        }
        else {
            return HanyoRegistCommon.EDIT_VIEW_ID;
        }
	}
    public SessionKeyDto getHanyoRegistSessionKeyDto() {
        return hanyoRegistSessionKeyDto;
    }
    public void setHanyoRegistSessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.hanyoRegistSessionKeyDto = sessionKeyDto;
    }
}

