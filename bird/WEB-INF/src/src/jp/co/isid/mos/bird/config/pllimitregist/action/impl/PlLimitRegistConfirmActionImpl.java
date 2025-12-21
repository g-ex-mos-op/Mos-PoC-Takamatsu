/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.action.impl;

import jp.co.isid.mos.bird.config.pllimitregist.action.PlLimitRegistConfirmAction;
import jp.co.isid.mos.bird.config.pllimitregist.common.PlLimitRegistConstants;
import jp.co.isid.mos.bird.config.pllimitregist.dto.PlLimitRegistDto;
import jp.co.isid.mos.bird.config.pllimitregist.logic.RegistPLLimitLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 上下限設定確認画面アクション
 * 
 * @author xyuchida
 */
public class PlLimitRegistConfirmActionImpl implements PlLimitRegistConfirmAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BCF005A21";
    public static final String regist_ACTION_ID    = "BCF005A22";
    public static final String back_ACTION_ID    = "BCF005A23";

    /**
     * P/L上下限登録ロジック
     */
    private RegistPLLimitLogic registPLLimitLogic;
    /**
     * P/L入力上下限設定DTO
     */
    private PlLimitRegistDto plLimitRegistDto;
    /**
     * ログインユーザ情報
     */
    private BirdUserInfo birdUserInfo;

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
     * 登録
     * 
     * @return 画面遷移情報
     */
    public String regist() {

        // P/L上下限登録
        getRegistPLLimitLogic().execute(getPlLimitRegistDto().getPlLimitList(), getBirdUserInfo().getUserID());

        // 初期値設定
        getPlLimitRegistDto().setPlType(PlLimitRegistConstants.PL_TYPE_TENPO);

        // 条件設定画面へ遷移
        return PlLimitRegistConstants.VIEWID_CONDITION;
    }

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back() {
        // 上下限設定編集画面へ遷移
        return PlLimitRegistConstants.VIEWID_EDIT;
    }

    public RegistPLLimitLogic getRegistPLLimitLogic() {
        return registPLLimitLogic;
    }
    public void setRegistPLLimitLogic(RegistPLLimitLogic registPLLimitLogic) {
        this.registPLLimitLogic = registPLLimitLogic;
    }
    public PlLimitRegistDto getPlLimitRegistDto() {
        return plLimitRegistDto;
    }
    public void setPlLimitRegistDto(PlLimitRegistDto plLimitRegistDto) {
        this.plLimitRegistDto = plLimitRegistDto;
    }
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
}
