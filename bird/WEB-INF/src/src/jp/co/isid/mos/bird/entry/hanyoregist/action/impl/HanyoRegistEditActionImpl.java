/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoregist.action.impl;

import jp.co.isid.mos.bird.entry.hanyoregist.action.HanyoRegistEditAction;
import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.CheckEntryLogic;

/**
 * 汎用研修マスタ登録　編集画面アクションクラス
 * @author itamoto
 */
public class HanyoRegistEditActionImpl implements HanyoRegistEditAction {

    /* アクションID */
    public static String initialize_ACTION_ID = "BEN004A06";
    public static String confirm_ACTION_ID    = "BEN004A07";
    public static String cancel_ACTION_ID     = "BEN004A08";

    /* 汎用研修マスタ登録用DTO */
    private HanyoRegistDto hanyoRegistDto;
    
    /* 登録内容チェックロジック */
    private CheckEntryLogic checkEntryLogic;
//    
//    /* ログイン情報 */
//    private BirdUserInfo birdUserInfo;
//    /* 日付情報 */
//    private BirdDateInfo birdDateInfo;

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
     * 確認
     * @return 画面遷移情報
     */
    public String confirm() {
    	// １．ロジック【登録内容チェック】を実行する。
    	// パラメータ：登録画面モード（0：登録画面上で入力不可項目なし　1：登録画面上で入力不可項目有り）
    	// 開催日FROM／開催日TO／開催名／開催場所／申込定員／会場定員
    	// 本部(受付開始)／本部(受付終了)／本部(表示開始)／本部(表示終了)
    	// オーナー(受付開始)／オーナー(受付終了)／オーナー(表示開始)／オーナー(表示終了)／システム日付
    	getCheckEntryLogic().execute(getHanyoRegistDto());
    	
    	// ２．確認画面を表示する。
    	// ３．登録画面で編集・入力されたデータを保持しておく。
    	return HanyoRegistCommon.CONFIRM_VIEW_ID;
	}

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
    	// １．登録画面で編集・入力されたデータをクリアする。
		// ２．初期画面を表示する。
        getHanyoRegistDto().setCondClearFlg(true);
    	return HanyoRegistCommon.SELECT_VIEW_ID;
	}

//    /**
//	 * nullチェック
//	 */
//    private boolean isNull(String val) {
//        if (val == null) {
//            return true;
//        }
//        if ("".equals(val.trim())) {
//            return true;
//        }
//        return false;
//    }
}

