/*
 * 作成日: 2006/6/05
 */
package jp.co.isid.mos.bird.entry.basicentry.action.impl;


import jp.co.isid.mos.bird.entry.basicentry.action.BasicEntryConfirmAction;
import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.basicentry.logic.SearchEntryInfoLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * 研修マスタ登録　確認画面アクションクラス
 * @author kusama
 */
public class BasicEntryConfirmActionImpl implements BasicEntryConfirmAction {

    private static Logger logger_ = Logger.getLogger(BasicEntryConfirmActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID   = "BEN002A21";
    public static String cancel_ACTION_ID       = "BEN002A22";
    public static String regist_ACTION_ID       = "BEN002A23";

    /* DTO */
    // 汎用研修マスタ登録用DTO
    private BasicEntryDto basicEntryDto;
    // セッションキー保持DTO
    private SessionKeyDto hanyoRegistSessionKeyDto;
    
    /* LOGIC */
    private SearchEntryInfoLogic basicEntrySearchEntryInfoLogic;
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @return hanyoRegistDto を戻します。
	 */
	public BasicEntryDto getBasicEntryDto() {
		return basicEntryDto;
	}
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @param hanyoRegistDto hanyoRegistDto を設定。
	 */
	public void setBasicEntryDto(BasicEntryDto hanyoApplicationDto) {
		this.basicEntryDto = hanyoApplicationDto;
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
        // 選択された研修の情報を検索する
        getBasicEntrySearchEntryInfoLogic().execute(getBasicEntryDto());
        
        
    	return BasicEntryCommon.VIEW_ID_EDIT;
	}

    /**
     * 終了
     */
    public String regist() {
        getBasicEntryDto().setCondClearFlg(true);
        return BasicEntryCommon.VIEW_ID_SELECT;
    }
    
    public SessionKeyDto getHanyoRegistSessionKeyDto() {
        return hanyoRegistSessionKeyDto;
    }
    public void setHanyoRegistSessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.hanyoRegistSessionKeyDto = sessionKeyDto;
    }
    public SearchEntryInfoLogic getBasicEntrySearchEntryInfoLogic() {
        return basicEntrySearchEntryInfoLogic;
    }
    public void setBasicEntrySearchEntryInfoLogic(
            SearchEntryInfoLogic basicEntrySearchEntryInfoLogic) {
        this.basicEntrySearchEntryInfoLogic = basicEntrySearchEntryInfoLogic;
    }
}