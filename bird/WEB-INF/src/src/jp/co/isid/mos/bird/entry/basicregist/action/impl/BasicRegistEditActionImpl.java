/*
 * 作成日: 2006/05/30
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.action.impl;

import jp.co.isid.mos.bird.entry.basicregist.action.BasicRegistEditAction;
import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.logic.CheckEntryLogic;

/**
 * ベーシック研修マスタ登録編集アクション
 * 
 * @author xyuchida
 */
public class BasicRegistEditActionImpl implements BasicRegistEditAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN001A11";
    public static final String confirm_ACTION_ID = "BEN001A12";
    public static final String back_ACTION_ID = "BEN001A13";

    private BasicRegistDto basicRegistDto;
    private CheckEntryLogic checkEntryLogic;

    public BasicRegistDto getBasicRegistDto() {
        return basicRegistDto;
    }
    public void setBasicRegistDto(BasicRegistDto basicRegistDto) {
        this.basicRegistDto = basicRegistDto;
    }
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        // 自画面へ遷移
        return null;
    }

    /**
     * 確認
     * @return 画面遷移情報
     */
    public String confirm() {

        // 入力値チェック
        getCheckEntryLogic().execute(getBasicRegistDto());

        // 確認画面へ遷移
        return BasicRegistConstants.VIEWID_CONFIRM;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // 条件画面へ遷移
        return BasicRegistConstants.VIEWID_SELECT;
    }
}
