/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.action.impl;

import jp.co.isid.mos.bird.config.pllimitregist.action.PlLimitRegistEditAction;
import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.dto.PlLimitRegistDto;
import jp.co.isid.mos.bird.config.pllimitregist.logic.CheckPLLimitLogic;
import jp.co.isid.mos.bird.config.pllimitregist.logic.ClearLimitValueLogic;

/**
 * 上下限設定編集画面アクション
 * 
 * @author xyuchida
 */
public class PlLimitRegistEditActionImpl implements PlLimitRegistEditAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BCF005A11";
    public static final String confirm_ACTION_ID    = "BCF005A12";
    public static final String back_ACTION_ID    = "BCF005A13";

    /**
     * P/L上下限チェックロジック
     */
    private CheckPLLimitLogic checkPLLimitLogic;
    /**
     * P/L上下限MAX/MIN値クリアロジック
     */
    private ClearLimitValueLogic clearLimitValueLogic;
    /**
     * P/L入力上下限設定DTO
     */
    private PlLimitRegistDto plLimitRegistDto;

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        // 自画面へ遷移
        return null;
    }

    /**
     * 確認
     * 
     * @return 画面遷移情報
     */
    public String confirm() {

        // P/L上下限MAX/MIN値クリア
        getClearLimitValueLogic().execute(getPlLimitRegistDto().getPlLimitList());
        // P/L上下限チェック
        getCheckPLLimitLogic().execute(getPlLimitRegistDto().getPlLimitList());

        // 上下限設定確認画面へ遷移
        return PlLimitRegistConstants.VIEWID_CONFIRM;
    }

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back() {
        // 条件設定画面へ遷移
        return PlLimitRegistConstants.VIEWID_CONDITION;
    }

    public CheckPLLimitLogic getCheckPLLimitLogic() {
        return checkPLLimitLogic;
    }

    public void setCheckPLLimitLogic(CheckPLLimitLogic checkPLLimitLogic) {
        this.checkPLLimitLogic = checkPLLimitLogic;
    }

    public ClearLimitValueLogic getClearLimitValueLogic() {
        return clearLimitValueLogic;
    }

    public void setClearLimitValueLogic(ClearLimitValueLogic clearLimitValueLogic) {
        this.clearLimitValueLogic = clearLimitValueLogic;
    }

    public PlLimitRegistDto getPlLimitRegistDto() {
        return plLimitRegistDto;
    }

    public void setPlLimitRegistDto(PlLimitRegistDto plLimitRegistDto) {
        this.plLimitRegistDto = plLimitRegistDto;
    }
}
