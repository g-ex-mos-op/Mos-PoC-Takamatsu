/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * マスタライセンス研修修了登録
 * 画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface MlCompletionregistAction extends CommonAction {
	/** 画面ID：BSM006 */
	public static final String SCREEN_ID = "BSM006";
    /* モス会社コード */
    public static final String COMPANY_CD_MOS = "00";
    /* エントリーコード：ベーシック研修 '01' */
    public static final String ENTRY_CD_BASIC = "01";
    /* エントリーコード：出張研修 '05' */
    public static final String ENTRY_CD_TRIP = "05";
    /* エントリーコード：更新研修 '30' */
    public static final String ENTRY_CD_RENEWAL = "30";
    /* ビューID:条件画面 BSM006V01 */
    public static final String VIEWID_CONDITION = SCREEN_ID+"V01";
    /* ビューID:編集画面 BSM006V02 */
    public static final String VIEWID_EDIT      = SCREEN_ID+"V02";
    /* ビューID:確認画面 BSM006V03 */
    public static final String VIEWID_CONFIRM   = SCREEN_ID+"V03";

    /**
     * 実行
     * @return
     */
    public String execute();
    /**
     * 戻る
     * @return
     */
    public String back();
}