package jp.co.isid.mos.bird.entry.nationalregist.action.impl;

import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.entry.nationalregist.action.NationalRegistSelectAction;
import jp.co.isid.mos.bird.entry.nationalregist.common.NationalRegistConstants;
import jp.co.isid.mos.bird.entry.nationalregist.dto.NationalRegistDto;
import jp.co.isid.mos.bird.entry.nationalregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.nationalregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.nationalregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.entry.nationalregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 全国大会マスタ登録 条件アクション
 *
 * @author xjung
 */
public class NationalRegistSelectActionImpl implements NationalRegistSelectAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BEN014A01";

    /** 新規登録アクションID */
    public static final String insert_ACTION_ID = "BEN014A02";

    /** 編集アクションID */
    public static final String update_ACTION_ID = "BEN014A03";

    /** 削除アクションID */
    public static final String delete_ACTION_ID = "BEN014A04";

    /** 全国大会マスタ登録情報DTO */
    private NationalRegistDto nationalRegistDto;

    /** 全国大会マスタ情報リスト取得ロジック */
    private SearchEntryListLogic searchEntryListLogic;

    /** 全国大会マスタ情報取得ロジック */
    private SearchEntryLogic searchEntryLogic;

    /** 全国大会マスタ情報チェックロジック */
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

            // 全国大会マスタ登録情報削除
            getNationalRegistDto().clear();

            // ウィンドウID更新
            getNationalRegistDto().updateWindowid();

            //複数ウィンドウ制御用のセッションキー生成
            String key = getNationalRegistDto().getMkSession()._makeSessionKey();
            getNationalRegistDto().setNowSessionKey(key);
            getNationalRegistDto().setSessionKey(key);

            // BIRDユーザー情報取得
            BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);

            // BIRD日付情報取得
            BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

            // ユーザID設定
            getNationalRegistDto().setUserId(birdUserInfo.getUserID());
            // システム日付設定
            getNationalRegistDto().setSysDate(birdDateInfo.getSysDate());

            // 編集モード設定
            getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_INIT);            
        }

        // 編集モードが初期又は戻るの場合
        if (getNationalRegistDto().getEditMode() == NationalRegistConstants.EDIT_MODE_INIT
            || getNationalRegistDto().getEditMode() == NationalRegistConstants.EDIT_MODE_RETURN) {
            // 全国大会マスタ情報リストを取得
            getNationalRegistDto().setMstInfoList(
                getSearchEntryListLogic().execute(getNationalRegistDto().getSysDate()));
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
        if (!getNationalRegistDto().isValidSessionKey()) {
            return NationalRegistConstants.VIEW_ID_ERR;
        }

        // 編集モード設定
        getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_INSERT);

        // 全国大会マスタ登録情報を削除
        getNationalRegistDto().clear();
        
        // 編集画面へ遷移
        return NationalRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 編集
     * @return 画面遷移情報
     */
    public String update() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getNationalRegistDto().isValidSessionKey()) {
            return NationalRegistConstants.VIEW_ID_ERR;
        }
 
        // 編集モード設定
        getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_UPDATE);

        // 選択された全国大会マスタ情報を取得
        getNationalRegistDto().setUiEntryMst(
            (UIEntryMst) getNationalRegistDto().getMstInfoList().get(
                getNationalRegistDto().getSelectIndex()));

        // 全国大会マスタ情報取得
        this.getNationalMstInfo();

        // 編集画面へ遷移
        return NationalRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getNationalRegistDto().isValidSessionKey()) {
            return NationalRegistConstants.VIEW_ID_ERR;
        }

        // 編集モード設定
        getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_DELETE);

        // 選択された全国大会マスタ情報を取得
        getNationalRegistDto().setUiEntryMst(
            (UIEntryMst) getNationalRegistDto().getMstInfoList().get(
                getNationalRegistDto().getSelectIndex()));

        // 事前チェック
        getCheckEntryLogic().execute(getNationalRegistDto());

        // 全国大会マスタ情報取得
        this.getNationalMstInfo();

        // 確認画面へ遷移
        return NationalRegistConstants.VIEW_ID_CONFIRM;
    }

    /**
     * 全国大会マスタ情報を取得する。 
     */    
    private void getNationalMstInfo() {
        // セレクションサイズ
        int selectionSize = getNationalRegistDto().getEntryKbnSize()
                          + getNationalRegistDto().getOptionalSize();
        
        // 全国大会マスタ情報取得
        Map mstInfo = getSearchEntryLogic().execute(
                        getNationalRegistDto().getUiEntryMst().getEntryCd(),
                        getNationalRegistDto().getUiEntryMst().getEntryYear(),
                        getNationalRegistDto().getUiEntryMst().getEntryKai(),
                        getNationalRegistDto().getDateSize(),
                        selectionSize);

        // 全国大会日付情報リスト設定
        getNationalRegistDto().setDateInfoList(
            (List) mstInfo.get(NationalRegistConstants.MAP_DATE_LIST));

        // 全国大会セレクション情報リスト取得
        List selectionList = (List) mstInfo.get(NationalRegistConstants.MAP_SELECTION_LIST);

        // 申込区分リスト設定
        getNationalRegistDto().setEntryKbnList(selectionList.subList
            (0, getNationalRegistDto().getEntryKbnSize()));

        // オプショナルリスト設定
        getNationalRegistDto().setOptionalList(selectionList.subList
            (getNationalRegistDto().getEntryKbnSize(), selectionSize));
    }

    /**
     * 全国大会マスタ登録情報DTOを取得する。
     * @return 全国大会マスタ登録情報DTO
     */
    public NationalRegistDto getNationalRegistDto() {
        return nationalRegistDto;
    }

    /**
     * 全国大会マスタ登録情報DTOを設定する。
     * @param nationalRegistDto 全国大会マスタ登録情報DTO
     */
    public void setNationalRegistDto(NationalRegistDto nationalRegistDto) {
        this.nationalRegistDto = nationalRegistDto;
    }

    /**
     * 全国大会マスタ情報リスト取得ロジックを取得する。
     * @return 全国大会マスタ情報リスト取得ロジック
     */
    public SearchEntryListLogic getSearchEntryListLogic() {
        return searchEntryListLogic;
    }

    /**
     * 全国大会マスタ情報リスト取得ロジックを設定する。
     * @param searchEntryListLogic 全国大会マスタ情報リスト取得ロジック
     */
    public void setSearchEntryListLogic(SearchEntryListLogic searchEntryListLogic) {
        this.searchEntryListLogic = searchEntryListLogic;
    }

    /**
     * 全国大会マスタ情報取得ロジックを取得する。
     * @return 全国大会マスタ情報取得ロジック
     */
    public SearchEntryLogic getSearchEntryLogic() {
        return searchEntryLogic;
    }

    /**
     * 全国大会マスタ情報取得ロジックを設定する。
     * @param searchEntryLogic 全国大会マスタ情報取得ロジック
     */
    public void setSearchEntryLogic(SearchEntryLogic searchEntryLogic) {
        this.searchEntryLogic = searchEntryLogic;
    }

    /**
     * 全国大会マスタ情報チェックロジックを取得する。
     * @return 全国大会マスタ情報チェックロジック
     */
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }

    /**
     * 全国大会マスタ情報チェックロジックを設定する。
     * @param checkEntryLogic 全国大会マスタ情報チェックロジック
     */
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }
}