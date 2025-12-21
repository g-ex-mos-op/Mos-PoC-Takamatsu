package jp.co.isid.mos.bird.bizadmin.svtantousibu.action.impl;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.action.SvTantouSibuRemoteSibuEditAction;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * リモート閲覧支部登録 編集画面アクション
 *
 * @author xnkusama
 */
public class SvTantouSibuRemoteSibuEditActionImpl implements SvTantouSibuRemoteSibuEditAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBA004A11";

    /** 戻るアクションID */
    public static final String back_ACTION_ID = "BEN004A12";

    /** 確認アクションID */
    public static final String confirm_ACTION_ID = "BEN004A13";

    /* LOGIC */
    
    /* DTO */
    /** リモート閲覧支部・SV担当店登録DTO */
    private SvTantouSibuDto svTantouSibuDto;

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        return null;
    }

    /**
     * 戻る
     * @return
     */
    public String back() {
        //複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey( getSvTantouSibuDto().getViewSessionKey(), getSvTantouSibuDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        
        return SvTantouSibuCommon.VIEW_ID_INIT;
    }

    /**
     * 確認
     * @return
     */
    public String confirm() {
        //複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey( getSvTantouSibuDto().getViewSessionKey(), getSvTantouSibuDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        
        return SvTantouSibuCommon.VIEW_ID_REMOTESIBU_CONFIRM;
    }

    public SvTantouSibuDto getSvTantouSibuDto() {
        return svTantouSibuDto;
    }

    public void setSvTantouSibuDto(SvTantouSibuDto svTantouSibuDto) {
        this.svTantouSibuDto = svTantouSibuDto;
    }
}