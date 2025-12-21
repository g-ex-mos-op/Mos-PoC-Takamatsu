/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.action.MlRegistSelectAction;
import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryMaster;
import jp.co.isid.mos.bird.entry.mlregist.logic.CreateEntryMasterLogic;
import jp.co.isid.mos.bird.entry.mlregist.logic.SearchEntryMasterListLogic;
import jp.co.isid.mos.bird.entry.mlregist.logic.SearchEntryMasterLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * マスタライセンスマスタ登録条件アクション
 * 
 * @author xyuchida
 */
public class MlRegistSelectActionImpl implements MlRegistSelectAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN007A01";
    public static final String insert_ACTION_ID = "BEN007A02";
    public static final String update_ACTION_ID = "BEN007A03";
    public static final String delete_ACTION_ID = "BEN007A04";
    public static final String changePage_ACTION_ID = "BEN007A05";

    // DTO
    private MlRegistDto mlRegistDto;
    // Logic
    private SearchEntryMasterListLogic searchEntryMasterListLogic;
    private SearchEntryMasterLogic searchEntryMasterLogic;
    private CreateEntryMasterLogic createEntryMasterLogic;
    // 画面制御
    private int SelectPageNumber;

    public MlRegistDto getMlRegistDto() {
        return mlRegistDto;
    }
    public void setMlRegistDto(MlRegistDto mlRegistDto) {
        this.mlRegistDto = mlRegistDto;
    }

    public SearchEntryMasterListLogic getSearchEntryMasterListLogic() {
        return searchEntryMasterListLogic;
    }
    public void setSearchEntryMasterListLogic(
            SearchEntryMasterListLogic searchEntryMasterListLogic) {
        this.searchEntryMasterListLogic = searchEntryMasterListLogic;
    }
    public SearchEntryMasterLogic getSearchEntryMasterLogic() {
        return searchEntryMasterLogic;
    }
    public void setSearchEntryMasterLogic(
            SearchEntryMasterLogic searchEntryMasterLogic) {
        this.searchEntryMasterLogic = searchEntryMasterLogic;
    }
    public CreateEntryMasterLogic getCreateEntryMasterLogic() {
        return createEntryMasterLogic;
    }
    public void setCreateEntryMasterLogic(
            CreateEntryMasterLogic createEntryMasterLogic) {
        this.createEntryMasterLogic = createEntryMasterLogic;
    }

    public int getSelectPageNumber() {
        return SelectPageNumber;
    }
    public void setSelectPageNumber(int selectPageNumber) {
        SelectPageNumber = selectPageNumber;
    }

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();

        // 画面初期表示処理
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        if (pullDownMenuDto.isClearFlg()) {

            MlRegistDto mlRegistDto = (MlRegistDto) container.getComponent(MlRegistDto.class);

            // ユーザID取得
            mlRegistDto.setUserId(((BirdUserInfo) container.getComponent(BirdUserInfo.class)).getUserID());
            // システム日付取得
            mlRegistDto.setSysDate(((BirdDateInfo) container.getComponent(BirdDateInfo.class)).getSysDate());

            // エントリーコード設定
            mlRegistDto.setEntryCd(MlRegistConstants.ENTRY_CD_ML);

            // ページNo初期化
            mlRegistDto.setCurrentPageNumber(1);
            mlRegistDto.setMaxPageCount(MlRegistConstants.MAX_PAGE_COUNT);

            // ベーシック研修マスタ取得
            SearchEntryMasterListLogic searchEntryMasterLogic = (SearchEntryMasterListLogic) container.getComponent(SearchEntryMasterListLogic.class);
            searchEntryMasterLogic.execute(mlRegistDto);

            // インデックス初期化
            mlRegistDto.setSelectIndex(0);

            pullDownMenuDto.setClearFlg(false);
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * 新規
     * @return 画面遷移情報
     */
    public String insert() {

        // 編集モードを新規に設定
        getMlRegistDto().setEditMode(MlRegistConstants.EDIT_MODE_INSERT);

        // 新規エントリ生成
        getCreateEntryMasterLogic().execute(getMlRegistDto());

        // 編集画面へ遷移
        return MlRegistConstants.VIEWID_EDIT;
    }

    /**
     * 編集
     * @return 画面遷移情報
     */
    public String update() {

        // 選択状態チェック
        validateSelect();

        // 編集モードを更新に設定
        getMlRegistDto().setEditMode(MlRegistConstants.EDIT_MODE_UPDATE);

        // 選択されたエントリーを編集対象とする
        getSearchEntryMasterLogic().execute(getMlRegistDto());

        // 編集前申込開始日取得
        String applyOnerFromDt = getMlRegistDto().getUiEntryDateApplyOner().getFromDt();
        String applyHeadFromDt = getMlRegistDto().getUiEntryDateApplyHead().getFromDt();
        // オーナー、本部の申込開始日の早い日付を取得する
        getMlRegistDto().setApplyFromDt(
                (applyHeadFromDt == null || (applyOnerFromDt != null && applyOnerFromDt.compareTo(applyHeadFromDt) < 0)) ? applyOnerFromDt : applyHeadFromDt);

        // 編集画面へ遷移
        return MlRegistConstants.VIEWID_EDIT;
    }

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete() {

        // 選択状態チェック
        validateSelect();

        // 削除不可判定
        if (!canDelete((UIEntryMaster) getMlRegistDto().getMlEntryList().get(getMlRegistDto().getSelectIndex()))) {
            throw new CannotExecuteWithReasonException("既に申込期間に入っている", "削除");
        }

        // 編集モードを削除に設定
        getMlRegistDto().setEditMode(MlRegistConstants.EDIT_MODE_DELETE);

        // 選択されたエントリーを編集対象とする
        getSearchEntryMasterLogic().execute(getMlRegistDto());

        // 確認画面へ遷移
        return MlRegistConstants.VIEWID_CONFIRM;
    }

    /**
     * ページ切替
     * @return 画面遷移情報
     */
    public String changePage() {

        // ページNo設定
        getMlRegistDto().setCurrentPageNumber(getSelectPageNumber());

        // ベーシック研修マスタ再取得
        getSearchEntryMasterListLogic().execute(getMlRegistDto());

        // インデックス初期化
        getMlRegistDto().setSelectIndex(0);

        // 自画面へ遷移
        return null;
    }

    private boolean canDelete(UIEntryMaster uiEntryMaster) {
        // 申込(オーナー)FROMが現在日付より後であれば削除可能
        return uiEntryMaster.getApplyFromDt().compareTo(getMlRegistDto().getSysDate()) > 0;
    }

    private void validateSelect() {
        // エントリーリストチェック
        List mlEntryList = getMlRegistDto().getMlEntryList();
        if (mlEntryList == null || mlEntryList.isEmpty()) {
            throw new NotExistException("編集可能なエントリー");
        }
        // 選択状態インデックスチェック
        int selectIndex = getMlRegistDto().getSelectIndex();
        if (selectIndex < 0 || selectIndex >= mlEntryList.size()) {
            throw new InvalidInputException("選択状態");
        }
    }
}
