package jp.co.isid.mos.bird.entry.manageregist.action.impl;

import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.entry.manageregist.action.ManageRegistSelectAction;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;
import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.manageregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.manageregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.entry.manageregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 全国店長勉強会マスタ登録 条件アクション
 *
 * @author xjung
 */
public class ManageRegistSelectActionImpl implements ManageRegistSelectAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BEN020A01";

    /** 新規登録アクションID */
    public static final String insert_ACTION_ID = "BEN020A02";

    /** 編集アクションID */
    public static final String update_ACTION_ID = "BEN020A03";

    /** 削除アクションID */
    public static final String delete_ACTION_ID = "BEN020A04";

    /** 全国店長勉強会マスタ登録情報DTO */
    private ManageRegistDto manageRegistDto;

    /** 全国店長勉強会マスタ情報リスト取得ロジック */
    private SearchEntryListLogic searchEntryListLogic;

    /** 全国店長勉強会マスタ情報取得ロジック */
    private SearchEntryLogic searchEntryLogic;

    /** 全国店長勉強会マスタ情報チェックロジック */
    private CheckEntryLogic checkEntryLogic;

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // メニューDTO取得
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);

        // メニューから遷移された場合
        if (pullDownMenuDto.isClearFlg()) {
            // クリアフラグをOFFにする
            pullDownMenuDto.setClearFlg(false);

            // 全国店長勉強会マスタ登録情報削除
            getManageRegistDto().clear();

            // ウィンドウID更新
            getManageRegistDto().updateWindowid();

            //複数ウィンドウ制御用のセッションキー生成
            String key = getManageRegistDto().getMkSession()._makeSessionKey();
            getManageRegistDto().setNowSessionKey(key);
            getManageRegistDto().setSessionKey(key);

            // BIRDユーザー情報取得
            BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);

            // BIRD日付情報取得
            BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

            // ユーザID設定
            getManageRegistDto().setUserId(birdUserInfo.getUserID());
            // システム日付設定
            getManageRegistDto().setSysDate(birdDateInfo.getSysDate());

            // 編集モード設定
            getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_INIT);            
        }

        // 編集モードが初期又は戻るの場合
        if (getManageRegistDto().getEditMode() == ManageRegistConstants.EDIT_MODE_INIT
            || getManageRegistDto().getEditMode() == ManageRegistConstants.EDIT_MODE_RETURN) {
            // 全国店長勉強会マスタ情報リストを取得
            getManageRegistDto().setMstInfoList(
                getSearchEntryListLogic().execute(getManageRegistDto().getSysDate()));
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * 新規登録
     * @return 画面遷移情報
     */
    public String insert() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getManageRegistDto().isValidSessionKey()) {
            return ManageRegistConstants.VIEW_ID_ERR;
        }

        // 編集モード設定
        getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_INSERT);

        // 全国店長勉強会マスタ登録情報を削除
        getManageRegistDto().clear();
        
        // 編集画面へ遷移
        return ManageRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 編集
     * @return 画面遷移情報
     */
    public String update() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getManageRegistDto().isValidSessionKey()) {
            return ManageRegistConstants.VIEW_ID_ERR;
        }
 
        // 編集モード設定
        getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_UPDATE);

        // 選択された全国店長勉強会マスタ情報を取得
        getManageRegistDto().setUiEntryMst(
            (UIEntryMst) getManageRegistDto().getMstInfoList().get(
                getManageRegistDto().getSelectIndex()));

        // 全国店長勉強会マスタ情報取得
        this.getNationalMstInfo();

        // 編集画面へ遷移
        return ManageRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getManageRegistDto().isValidSessionKey()) {
            return ManageRegistConstants.VIEW_ID_ERR;
        }

        // 編集モード設定
        getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_DELETE);

        // 選択された全国店長勉強会マスタ情報を取得
        getManageRegistDto().setUiEntryMst(
            (UIEntryMst) getManageRegistDto().getMstInfoList().get(
                getManageRegistDto().getSelectIndex()));

        // 事前チェック
        getCheckEntryLogic().execute(getManageRegistDto());

        // 全国店長勉強会マスタ情報取得
        this.getNationalMstInfo();

        // 確認画面へ遷移
        return ManageRegistConstants.VIEW_ID_CONFIRM;
    }

    /**
     * 全国店長勉強会マスタ情報を取得する。 
     */    
    private void getNationalMstInfo() {
        // セレクションサイズ
        int selectionSize = getManageRegistDto().getEntryKbnSize()
                          + getManageRegistDto().getOptionalSize();
        
        // 全国店長勉強会マスタ情報取得
        Map mstInfo = getSearchEntryLogic().execute(
                        getManageRegistDto().getUiEntryMst().getEntryCd(),
                        getManageRegistDto().getUiEntryMst().getEntryYear(),
                        getManageRegistDto().getUiEntryMst().getEntryKai(),
                        getManageRegistDto().getDateSize(),
                        selectionSize);

        // 全国店長勉強会日付情報リスト設定
        getManageRegistDto().setDateInfoList(
            (List) mstInfo.get(ManageRegistConstants.MAP_DATE_LIST));

        // 全国店長勉強会セレクション情報リスト取得
        List selectionList = (List) mstInfo.get(ManageRegistConstants.MAP_SELECTION_LIST);

        // 申込区分リスト設定
        getManageRegistDto().setEntryKbnList(selectionList.subList
            (0, getManageRegistDto().getEntryKbnSize()));

        // オプショナルリスト設定
        getManageRegistDto().setOptionalList(selectionList.subList
            (getManageRegistDto().getEntryKbnSize(), selectionSize));
    }

    /**
     * 全国店長勉強会マスタ登録情報DTOを取得する。
     * @return 全国店長勉強会マスタ登録情報DTO
     */
    public ManageRegistDto getManageRegistDto() {
        return manageRegistDto;
    }

    /**
     * 全国店長勉強会マスタ登録情報DTOを設定する。
     * @param manageRegistDto 全国店長勉強会マスタ登録情報DTO
     */
    public void setManageRegistDto(ManageRegistDto manageRegistDto) {
        this.manageRegistDto = manageRegistDto;
    }

    /**
     * 全国店長勉強会マスタ情報リスト取得ロジックを取得する。
     * @return 全国店長勉強会マスタ情報リスト取得ロジック
     */
    public SearchEntryListLogic getSearchEntryListLogic() {
        return searchEntryListLogic;
    }

    /**
     * 全国店長勉強会マスタ情報リスト取得ロジックを設定する。
     * @param searchEntryListLogic 全国店長勉強会マスタ情報リスト取得ロジック
     */
    public void setSearchEntryListLogic(SearchEntryListLogic searchEntryListLogic) {
        this.searchEntryListLogic = searchEntryListLogic;
    }

    /**
     * 全国店長勉強会マスタ情報取得ロジックを取得する。
     * @return 全国店長勉強会マスタ情報取得ロジック
     */
    public SearchEntryLogic getSearchEntryLogic() {
        return searchEntryLogic;
    }

    /**
     * 全国店長勉強会マスタ情報取得ロジックを設定する。
     * @param searchEntryLogic 全国店長勉強会マスタ情報取得ロジック
     */
    public void setSearchEntryLogic(SearchEntryLogic searchEntryLogic) {
        this.searchEntryLogic = searchEntryLogic;
    }

    /**
     * 全国店長勉強会マスタ情報チェックロジックを取得する。
     * @return 全国店長勉強会マスタ情報チェックロジック
     */
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }

    /**
     * 全国店長勉強会マスタ情報チェックロジックを設定する。
     * @param checkEntryLogic 全国店長勉強会マスタ情報チェックロジック
     */
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }
}