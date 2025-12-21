package jp.co.isid.mos.bird.config.summenuregist.action.impl;

import jp.co.isid.mos.bird.config.summenuregist.action.SumMenuRegistConfirmAction;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistRequestDto;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * メニュー集約マスタ設定 確認画面アクション
 * @author xnkusama
 */
public class SumMenuRegistConfirmActionImpl implements
        SumMenuRegistConfirmAction {

    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A21";
    public static final String back_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A22";
    public static final String regist_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A23";

    /* DTO */
    private SumMenuRegistDto sumMenuRegistDto;
    private SumMenuRegistRequestDto sumMenuRegistReqDto;
    /* LOGIC */
    private SumMenuRegistLogic sumMenuRegistRegistLogic;
    /* OTHER */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    
    /**
     * 戻る
     */
    public String back() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //セッションキー作成・保持
        createSessionKey();
        // 編集画面へ遷移
        return SumMenuRegistConst.VIEW_ID_EDIT;
    }

    /**
     * 登録・終了
     */
    public String regist() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //セッションキー作成・保持
        createSessionKey();
        // 
        getSumMenuRegistRegistLogic().execute(getSumMenuRegistDto());
        
        return SumMenuRegistConst.VIEW_ID_CONDITION;
    }

    /**
     * 初期処理
     */
    public String initialize() {
        //セッションキー作成・保持
        createSessionKey();
        // 処理なし
        return null;
    }

    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String key = sessionKey._makeSessionKey();
        getSumMenuRegistReqDto().setSessionKey(key);
        getSumMenuRegistDto().setSessionKey(key);
    }
    
    public SumMenuRegistLogic getSumMenuRegistRegistLogic() {
        return sumMenuRegistRegistLogic;
    }

    public void setSumMenuRegistRegistLogic(
            SumMenuRegistLogic sumMenuRegistRegistLogic) {
        this.sumMenuRegistRegistLogic = sumMenuRegistRegistLogic;
    }

    public SumMenuRegistDto getSumMenuRegistDto() {
        return sumMenuRegistDto;
    }

    public void setSumMenuRegistDto(SumMenuRegistDto sumMenuRegistDto) {
        this.sumMenuRegistDto = sumMenuRegistDto;
    }

    public SumMenuRegistRequestDto getSumMenuRegistReqDto() {
        return sumMenuRegistReqDto;
    }

    public void setSumMenuRegistReqDto(SumMenuRegistRequestDto sumMenuRegistReqDto) {
        this.sumMenuRegistReqDto = sumMenuRegistReqDto;
    }

}
