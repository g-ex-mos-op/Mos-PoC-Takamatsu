package jp.co.isid.mos.bird.bizadmin.svtantousibu.action.impl;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.action.SvTantouSibuRemoteSibuEditAction;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.RegistTantouMiseLogic;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * リモート閲覧支部登録 確認画面アクション
 *
 * @author xnkusama
 */
public class SvTantouSibuTantouMiseConfirmActionImpl implements SvTantouSibuRemoteSibuEditAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBA004A212";

    /** 戻るアクションID */
    public static final String back_ACTION_ID = "BEN004A22";

    /** 確認アクションID */
    public static final String confirm_ACTION_ID = "BEN004A23";

    /* LOGIC */
    private RegistTantouMiseLogic svTantouSibuRegistTantouMiseLogic;
    
    /* DTO */
    /** リモート閲覧支部・SV担当店登録DTO */
    private SvTantouSibuDto svTantouSibuDto;

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        //アップロードファイルがない場合は、エラーとする
        if (getSvTantouSibuDto().getUploadedFile() == null) {
            throw new NotSelectedException("CSVファイル", "");
        }
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
        
        //アップロードファイルクリア
        getSvTantouSibuDto().setUploadedFile(null);
        
        return SvTantouSibuCommon.VIEW_ID_INIT;
    }

    /**
     * 登録
     * @return
     */
    public String confirm() {
        //複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey( getSvTantouSibuDto().getViewSessionKey(), getSvTantouSibuDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        
        //更新ロジック実行
        getSvTantouSibuRegistTantouMiseLogic().execute(getSvTantouSibuDto());
        
        return SvTantouSibuCommon.VIEW_ID_INIT;
    }

    public SvTantouSibuDto getSvTantouSibuDto() {
        return svTantouSibuDto;
    }

    public void setSvTantouSibuDto(SvTantouSibuDto svTantouSibuDto) {
        this.svTantouSibuDto = svTantouSibuDto;
    }

    public RegistTantouMiseLogic getSvTantouSibuRegistTantouMiseLogic() {
        return svTantouSibuRegistTantouMiseLogic;
    }

    public void setSvTantouSibuRegistTantouMiseLogic(
            RegistTantouMiseLogic svTantouSibuRegistTantouMiseLogic) {
        this.svTantouSibuRegistTantouMiseLogic = svTantouSibuRegistTantouMiseLogic;
    }
}