package jp.co.isid.mos.bird.config.summenuregist.action.impl;

import jp.co.isid.mos.bird.commonform.menusearch.dto.MenuSearchDto;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;
import jp.co.isid.mos.bird.config.summenuregist.action.SumMenuRegistCondAction;
import jp.co.isid.mos.bird.config.summenuregist.common.SumMenuRegistConst;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistDto;
import jp.co.isid.mos.bird.config.summenuregist.dto.SumMenuRegistRequestDto;
import jp.co.isid.mos.bird.config.summenuregist.logic.CheckExistDataLogic;
import jp.co.isid.mos.bird.config.summenuregist.logic.SumMenuRegistLogic;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * メニュー集約マスタ設定 条件画面アクション
 * @author xnkusama
 *
 */
public class SumMenuRegistCondActionImpl implements SumMenuRegistCondAction {
    /**
     * アクションID定義 
     */
    public static final String initialize_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A01";
    public static final String newEdit_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A02";
    public static final String downloadCsv_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A03";
    public static final String edit_ACTION_ID = SumMenuRegistConst.GAMEN_ID + "A04";

    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;
    /* ACTION */
    private CsvOutputAction sumMenuRegistCsvAction;
    /* LOGIC */
    private SumMenuRegistLogic sumMenuRegistGetSumMenuLogic;
    private SumMenuRegistLogic sumMenuRegistGetKoMenuLogic;
    private CheckExistDataLogic sumMenuRegistCheckExistDataLogic;
    
    /* DTO */
    private SumMenuRegistDto sumMenuRegistDto;
    private SumMenuRegistRequestDto sumMenuRegistReqDto;
    private MenuSearchDto menuSearchDto;
    
    /*PARAM*/
    private String sumMenuCd;
    
    /* OTHER */
    private MakeSessionKey sessionKey = new MakeSessionKey();
    
    public String initialize() {

        //DTO初期化処理
        initDto();
        //セッションキー作成・保持
        createSessionKey();
        
        //集約メニュー一覧取得
        getSumMenuRegistGetSumMenuLogic().execute(getSumMenuRegistDto());
        
        //メニューからの遷移
        if (getPullDownMenuDto().isClearFlg()) {
            getPullDownMenuDto().setClearFlg(false);
        }
        //メニュー検索からの遷移
        else if (MenuSearchUtil.ACTION_KIND_INIT != getMenuSearchDto().getActionKind()) {
            //TODO メニュー検索からの戻りの時にSessionKeyが引き付けない
            //2画面メニュー検索の状態でメニューを選択した時に両方編集画面へ遷移できてしまうので考慮が必要
            
            //メニュー検索画面でメニューが選択された場合
            if (MenuSearchUtil.ACTION_KIND_SELECT == getMenuSearchDto().getActionKind()) {
                //【ロジック】登録済み情報チェックを実行
                getSumMenuRegistCheckExistDataLogic().execute(getSumMenuRegistDto(), 
                                                              getMenuSearchDto().getMenuCd(),
                                                              null,
                                                              CheckExistDataLogic.MODE_NEW);
                //セッションDTO．集約先メニューコード ← メニュー検索DTO．メニューコード
                getSumMenuRegistDto().setSumMenuCd(getMenuSearchDto().getMenuCd());
                //【ロジック】子メニュー一覧取得 実行
                getSumMenuRegistGetKoMenuLogic().execute(getSumMenuRegistDto());
                //メニュー検索DTOクリア
                getMenuSearchDto().setActionKind(MenuSearchUtil.ACTION_KIND_NULL);
                //編集画面へ遷移
                return SumMenuRegistConst.VIEW_ID_EDIT;
            }
        }
        
        return null;
    }
    
    /**
     * 実行アクション
     * @return
     */
    public String edit() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //セッションキー作成・保持
        createSessionKey();
        //DTOクリア
        clearDto();
        //選択された行のメニューコードをセッションDTO．集約メニューコードにセット
        getSumMenuRegistDto().setSumMenuCd(getSumMenuCd());
        //【ロジック】子メニュー一覧取得 実行
        getSumMenuRegistGetKoMenuLogic().execute(getSumMenuRegistDto());
        
        //編集画面へ遷移
        return SumMenuRegistConst.VIEW_ID_EDIT;
    }
    
    /**
     * 新規登録
     */
    public String newEdit() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        //DTOクリア
        clearDto();
        //メニュー検索DTOへ情報をセット
        getMenuSearchDto().initialize();
        getMenuSearchDto().setRequesterViewId(SumMenuRegistConst.VIEW_ID_CONDITION);
        //メニュー選択画面へ遷移
        return SumMenuRegistConst.VIEW_ID_MENU_SEARCH;
    }
    
    /**
     * CSVダウンロード
     */
    public String downloadCsv() {
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(getSumMenuRegistReqDto().getSessionKey(), getSumMenuRegistDto().getSessionKey())) {
            return SumMenuRegistConst.operationErr_VIEW_ID;
        }
        try {
            getSumMenuRegistCsvAction().downloadCsv();
        }
        catch (ApplicationException aex) {
            throw aex;
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV", null, ex);
        }
        return null;
    }
    
    /**
     * DTO初期化処理
     */
    private void clearDto() {
        getSumMenuRegistDto().setListKoMenu(null);
    }
    
    /**
     * セッションキー作成・保持
     */
    private void createSessionKey() {
        String key = sessionKey._makeSessionKey();
        getSumMenuRegistReqDto().setSessionKey(key);
        getSumMenuRegistDto().setSessionKey(key);
    }
    /**
     * DTO初期化処理
     * @return
     */
    private void initDto() {
        getSumMenuRegistDto().setUserId(getBirdUserInfo().getUserID());
        getSumMenuRegistDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
        getSumMenuRegistDto().setSysDate(getBirdDateInfo().getSysDate());
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
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

    public SumMenuRegistLogic getSumMenuRegistGetSumMenuLogic() {
        return sumMenuRegistGetSumMenuLogic;
    }

    public void setSumMenuRegistGetSumMenuLogic(
            SumMenuRegistLogic sumMenuRegistGetSumMenu) {
        this.sumMenuRegistGetSumMenuLogic = sumMenuRegistGetSumMenu;
    }

    public String getSumMenuCd() {
        return sumMenuCd;
    }

    public void setSumMenuCd(String sumMenuCd) {
        this.sumMenuCd = sumMenuCd;
    }

    public SumMenuRegistLogic getSumMenuRegistGetKoMenuLogic() {
        return sumMenuRegistGetKoMenuLogic;
    }

    public void setSumMenuRegistGetKoMenuLogic(
            SumMenuRegistLogic sumMenuRegistGetKoMenuLogic) {
        this.sumMenuRegistGetKoMenuLogic = sumMenuRegistGetKoMenuLogic;
    }

    public MenuSearchDto getMenuSearchDto() {
        return menuSearchDto;
    }

    public void setMenuSearchDto(MenuSearchDto menuSearchDto) {
        this.menuSearchDto = menuSearchDto;
    }

    public CheckExistDataLogic getSumMenuRegistCheckExistDataLogic() {
        return sumMenuRegistCheckExistDataLogic;
    }

    public void setSumMenuRegistCheckExistDataLogic(
            CheckExistDataLogic sumMenuRegistCheckExistDataLogic) {
        this.sumMenuRegistCheckExistDataLogic = sumMenuRegistCheckExistDataLogic;
    }

    public CsvOutputAction getSumMenuRegistCsvAction() {
        return sumMenuRegistCsvAction;
    }

    public void setSumMenuRegistCsvAction(CsvOutputAction sumMenuRegistCsvAction) {
        this.sumMenuRegistCsvAction = sumMenuRegistCsvAction;
    }

}