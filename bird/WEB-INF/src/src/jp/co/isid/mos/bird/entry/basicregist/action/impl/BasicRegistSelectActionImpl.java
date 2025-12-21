/*
 * 作成日: 2006/05/30
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.action.impl;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.entry.basicregist.action.BasicRegistSelectAction;
import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.entry.basicregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.entry.common.logic.SearchDefaultCourseListLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ベーシック研修マスタ登録条件アクション
 * 
 * @author xyuchida
 */
public class BasicRegistSelectActionImpl implements BasicRegistSelectAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN001A01";
    public static final String insert_ACTION_ID = "BEN001A02";
    public static final String update_ACTION_ID = "BEN001A03";
    public static final String delete_ACTION_ID = "BEN001A04";
    public static final String changePage_ACTION_ID = "BEN001A05";

    private BasicRegistDto basicRegistDto;
    private PullDownMenuDto pullDownMenuDto;
    private SearchEntryListLogic searchEntryListLogic;
    private SearchEntryLogic searchEntryLogic;
    private SearchDefaultCourseListLogic searchDefaultCourseListLogic;
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private int selectPageNumber;

    public BasicRegistDto getBasicRegistDto() {
        return basicRegistDto;
    }
    public void setBasicRegistDto(BasicRegistDto basicRegistDto) {
        this.basicRegistDto = basicRegistDto;
    }
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }


    public SearchEntryListLogic getSearchEntryListLogic() {
        return searchEntryListLogic;
    }
    public void setSearchEntryListLogic(
            SearchEntryListLogic searchEntryListLogic) {
        this.searchEntryListLogic = searchEntryListLogic;
    }
    public SearchEntryLogic getSearchEntryLogic() {
        return searchEntryLogic;
    }
    public void setSearchEntryLogic(SearchEntryLogic searchEntryLogic) {
        this.searchEntryLogic = searchEntryLogic;
    }
    public SearchDefaultCourseListLogic getSearchDefaultCourseListLogic() {
        return searchDefaultCourseListLogic;
    }
    public void setSearchDefaultCourseListLogic(
            SearchDefaultCourseListLogic searchDefaultCourseListLogic) {
        this.searchDefaultCourseListLogic = searchDefaultCourseListLogic;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public int getSelectPageNumber() {
        return selectPageNumber;
    }
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
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

            BasicRegistDto basicRegistDto = (BasicRegistDto) container.getComponent(BasicRegistDto.class);

            // ユーザID取得
            BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
            basicRegistDto.setUserId(birdUserInfo.getUserID());
            // システム日付取得
            BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
            basicRegistDto.setSysDate(birdDateInfo.getSysDate());

            // ページNo初期化
            basicRegistDto.setCurrentPageNumber(1);
            basicRegistDto.setMaxPageCount(BasicRegistConstants.MAX_PAGE_COUNT);

            // ベーシック研修マスタ取得
            SearchEntryListLogic searchEntryListLogic = (SearchEntryListLogic) container.getComponent(SearchEntryListLogic.class);
            searchEntryListLogic.execute(basicRegistDto);

            // インデックス初期化
            basicRegistDto.setSelectIndex(0);

            // デフォルトコースリスト取得
            basicRegistDto.setCourseList(getSearchDefaultCourseListLogic().execute(BasicRegistConstants.ENTRYCODE_BASIC));

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
        getBasicRegistDto().setEditMode(BasicRegistConstants.EDIT_MODE_INSERT);

        String entryCd = BasicRegistConstants.ENTRYCODE_BASIC;

        // 新規ベーシック研修マスタを生成
        // エントリーマスタ管理
        UIEntryMst uiEntryMst = new UIEntryMst();
        uiEntryMst.setEntryCd(entryCd);
        uiEntryMst.setEntryPlace("");
        uiEntryMst.setPlaceLimit(BigDecimal.valueOf(0));
        uiEntryMst.setSpareFlg1("");
        uiEntryMst.setSpareFlg2("");
        uiEntryMst.setSakujoFlg(BasicRegistConstants.SAKUJO_FLG_OFF);
        getBasicRegistDto().setUiEntryMst(uiEntryMst);

        // エントリー日付管理
        // ベーシック研修(開催日)
        UIEntryDate uiEntryDateBasic = new UIEntryDate();
        uiEntryDateBasic.setEntryCd(entryCd);
        uiEntryDateBasic.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_KANRI);
        uiEntryDateBasic.setDayKbn(BasicRegistConstants.DAY_KBN_BASIC);
        getBasicRegistDto().setUiEntryDateBasic(uiEntryDateBasic);
        // 臨店自習コース(その他)
        UIEntryDate uiEntryDateVisit = new UIEntryDate();
        uiEntryDateVisit.setEntryCd(entryCd);
        uiEntryDateVisit.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_KANRI);
        uiEntryDateVisit.setDayKbn(BasicRegistConstants.DAY_KBN_VISIT);
        getBasicRegistDto().setUiEntryDateVisit(uiEntryDateVisit);
        // 表示(本部)
        UIEntryDate uiEntryDateDisplayhead = new UIEntryDate();
        uiEntryDateDisplayhead.setEntryCd(entryCd);
        uiEntryDateDisplayhead.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateDisplayhead.setDayKbn(BasicRegistConstants.DAY_KBN_DISPLAY);
        getBasicRegistDto().setUiEntryDateDisplayHead(uiEntryDateDisplayhead);
        // 表示(オーナー)
        UIEntryDate uiEntryDateDisplayoner = new UIEntryDate();
        uiEntryDateDisplayoner.setEntryCd(entryCd);
        uiEntryDateDisplayoner.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_OWNER);
        uiEntryDateDisplayoner.setDayKbn(BasicRegistConstants.DAY_KBN_DISPLAY);
        getBasicRegistDto().setUiEntryDateDisplayOner(uiEntryDateDisplayoner);
        // 申込(本部)
        UIEntryDate uiEntryDateApplyhead = new UIEntryDate();
        uiEntryDateApplyhead.setEntryCd(entryCd);
        uiEntryDateApplyhead.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateApplyhead.setDayKbn(BasicRegistConstants.DAY_KBN_APPLY);
        getBasicRegistDto().setUiEntryDateApplyHead(uiEntryDateApplyhead);
        // 申込(オーナー)
        UIEntryDate uiEntryDateApplyoner = new UIEntryDate();
        uiEntryDateApplyoner.setEntryCd(entryCd);
        uiEntryDateApplyoner.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_OWNER);
        uiEntryDateApplyoner.setDayKbn(BasicRegistConstants.DAY_KBN_APPLY);
        getBasicRegistDto().setUiEntryDateApplyOner(uiEntryDateApplyoner);
        // 結果登録
        UIEntryDate uiEntryDateResult = new UIEntryDate();
        uiEntryDateResult.setEntryCd(entryCd);
        uiEntryDateResult.setUsertypeCd(BasicRegistConstants.USERTYPE_CODE_HONBU);
        uiEntryDateResult.setDayKbn(BasicRegistConstants.DAY_KBN_RESULT);
        getBasicRegistDto().setUiEntryDateResult(uiEntryDateResult);

        // エントリーコース管理
        UIEntryCourse uiEntryCourse = new UIEntryCourse();
        uiEntryCourse.setEntryCd(entryCd);
        getBasicRegistDto().setUiEntryCourse(uiEntryCourse);

        // 編集画面へ遷移
        return BasicRegistConstants.VIEWID_EDIT;
    }

    /**
     * 更新
     * @return 画面遷移情報
     */
    public String update() {

        // 編集モードを更新に設定
        getBasicRegistDto().setEditMode(BasicRegistConstants.EDIT_MODE_UPDATE);

        // 選択されたエントリー情報取得
        getSearchEntryLogic().execute(getBasicRegistDto());

        // 編集前申込開始日取得
        String applyOnerFromDt = getBasicRegistDto().getUiEntryDateApplyOner().getFromDt();
        String applyHeadFromDt = getBasicRegistDto().getUiEntryDateApplyHead().getFromDt();
        // オーナー、本部の申込開始日の早い日付を取得する
        getBasicRegistDto().setApplyFromDt(
                (applyHeadFromDt == null || (applyOnerFromDt != null && applyOnerFromDt.compareTo(applyHeadFromDt) < 0)) ? applyOnerFromDt : applyHeadFromDt);

        // 編集画面へ遷移
        return BasicRegistConstants.VIEWID_EDIT;
    }

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete() {

        // 削除不可判定
        if (!canDelete((UIEntryMst) getBasicRegistDto().getBasicEntryList().get(getBasicRegistDto().getSelectIndex()))) {
            throw new CannotExecuteWithReasonException("既に申込期間に入っている", "削除");
        }

        // 編集モードを削除に設定
        getBasicRegistDto().setEditMode(BasicRegistConstants.EDIT_MODE_DELETE);

        // 選択されたエントリー情報取得
        getSearchEntryLogic().execute(getBasicRegistDto());

        // 確認画面へ遷移
        return BasicRegistConstants.VIEWID_CONFIRM;
    }

    /**
     * ページ切替
     * @return 画面遷移情報
     */
    public String changePage() {

        // ページNo設定
        getBasicRegistDto().setCurrentPageNumber(getSelectPageNumber());

        // ベーシック研修マスタ再取得
        getSearchEntryListLogic().execute(getBasicRegistDto());

        // インデックス初期化
        getBasicRegistDto().setSelectIndex(0);

        // 自画面へ遷移
        return null;
    }

    private boolean canDelete(UIEntryMst uiEntryMst) {
        // 申込(オーナー)FROMが現在日付より後であれば削除可能
        return uiEntryMst.getApplyOnerFromDt().compareTo(getBirdDateInfo().getSysDate()) > 0;
    }
}
