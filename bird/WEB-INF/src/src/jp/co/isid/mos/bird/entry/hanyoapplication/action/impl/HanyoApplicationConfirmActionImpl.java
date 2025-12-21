/*
 * 作成日: 2006/6/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.action.impl;

import jp.co.isid.mos.bird.entry.hanyoapplication.action.HanyoApplicationConfirmAction;
import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchEntryInfoLogic;

/**
 * 研修マスタ登録　確認画面アクションクラス
 * @author kusama
 */
public class HanyoApplicationConfirmActionImpl implements HanyoApplicationConfirmAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BEN005A21";
    public static String cancel_ACTION_ID       = "BEN005A22";
    public static String regist_ACTION_ID       = "BEN005A23";

    /* DTO */
    // 汎用研修マスタ登録用DTO
    private HanyoApplicationDto hanyoApplicationDto;
    // セッションキー保持DTO
    private SessionKeyDto hanyoRegistSessionKeyDto;
    
    /* LOGIC */
    private SearchEntryInfoLogic hanyoAppSearchEntryInfoLogic;
    
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @return hanyoRegistDto を戻します。
	 */
	public HanyoApplicationDto getHanyoApplicationDto() {
		return hanyoApplicationDto;
	}
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @param hanyoRegistDto hanyoRegistDto を設定。
	 */
	public void setHanyoApplicationDto(HanyoApplicationDto hanyoApplicationDto) {
		this.hanyoApplicationDto = hanyoApplicationDto;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        return null;
    }
    
    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
        getHanyoAppSearchEntryInfoLogic().execute(getHanyoApplicationDto());
    	return HanyoApplicationCommon.VIEW_ID_EDIT;
	}

    /**
     * 終了
     */
    public String regist() {
        getHanyoApplicationDto().setCondClearFlg(true);
        return HanyoApplicationCommon.VIEW_ID_SELECT;
    }
    
//    private S2Container getS2Container() {
//        return SingletonS2ContainerFactory.getContainer(); 
//    }
    public SessionKeyDto getHanyoRegistSessionKeyDto() {
        return hanyoRegistSessionKeyDto;
    }
    public void setHanyoRegistSessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.hanyoRegistSessionKeyDto = sessionKeyDto;
    }
    public SearchEntryInfoLogic getHanyoAppSearchEntryInfoLogic() {
        return hanyoAppSearchEntryInfoLogic;
    }
    public void setHanyoAppSearchEntryInfoLogic(
            SearchEntryInfoLogic hanyoAppSearchEntryInfoLogic) {
        this.hanyoAppSearchEntryInfoLogic = hanyoAppSearchEntryInfoLogic;
    }
}