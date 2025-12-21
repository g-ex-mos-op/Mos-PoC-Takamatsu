/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.config.pllimitregist.action.PlLimitRegistConditionAction;
import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.dto.PlLimitRegistDto;
import jp.co.isid.mos.bird.config.pllimitregist.logic.ClearLimitValueLogic;
import jp.co.isid.mos.bird.config.pllimitregist.logic.GetPLLimitLogic;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 条件設定画面アクション
 * 
 * @author xyuchida
 */
public class PlLimitRegistConditionActionImpl implements PlLimitRegistConditionAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BCF005A01";
    public static final String execute_ACTION_ID    = "BCF005A02";

    /**
     * P/L上下限取得ロジック
     */
    private GetPLLimitLogic getPLLimitLogic;
    /**
     * P/L上下限MAX/MIN値クリアロジック
     */
    private ClearLimitValueLogic clearLimitValueLogic;
    /**
     * P/L入力上下限設定DTO
     */
    private PlLimitRegistDto plLimitRegistDto;
    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        if (getPullDownMenuDto().isClearFlg()) {
            // 初期値設定
            getPlLimitRegistDto().setPlType(PlLimitRegistConstants.PL_TYPE_TENPO);
            getPlLimitRegistDto().setPlLimitList(null);

            getPullDownMenuDto().setClearFlg(false);
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * 実行
     * 
     * @return 画面遷移情報
     */
    public String execute() {

        // P/L上下限取得
        List plLimitList = getGetPLLimitLogic().execute(getPlLimitRegistDto().getPlType());
        // P/L上下限MAX/MIN値クリア
        getClearLimitValueLogic().execute(plLimitList);

        getPlLimitRegistDto().setPlLimitList(plLimitList);

        // 上下限設定編集画面へ遷移
        return PlLimitRegistConstants.VIEWID_EDIT;
    }

    public GetPLLimitLogic getGetPLLimitLogic() {
        return getPLLimitLogic;
    }

    public void setGetPLLimitLogic(GetPLLimitLogic getPLLimitLogic) {
        this.getPLLimitLogic = getPLLimitLogic;
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

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
}
