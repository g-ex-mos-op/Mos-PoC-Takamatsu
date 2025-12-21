/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.office.formdownload.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.office.formdownload.action.FormDownloadAction;
import jp.co.isid.mos.bird.office.formdownload.dto.FormDownloadDto;
import jp.co.isid.mos.bird.office.formdownload.entity.UIFormDownLoad;
import jp.co.isid.mos.bird.office.formdownload.entity.UIViewShozokuInfo;
import jp.co.isid.mos.bird.office.formdownload.logic.GetFormInfoLogic;
import jp.co.isid.mos.bird.office.formdownload.logic.GetFormShozokuLogic;
import jp.co.isid.mos.bird.office.formdownload.logic.GetUserFormCategoryLogic;

/**
 * フォームダウンロード画面アクション
 * @author xytamura
 */
public class FormDownloadActionImpl implements FormDownloadAction {

    /* アクションID */
    public static String initialize_ACTION_ID = "BOF002A01";

    public static String searchByCate_ACTION_ID = "BOF002A02";

    public static String searchByTitle_ACTION_ID = "BOF002A03";

    public static String changeTab_ACTION_ID = "BOF002A04";

    public static String changePage_ACTION_ID = "BOF002A05";

    public static String viewDetail_ACTION_ID = "BOF002A06";

    /* 検索モード */
    private static final int SEARCH_MODE_CATEGORY = 1;

    private static final int SEARCH_MODE_TITLE = 2;

    // /* 情報種別 */
    // private static final String INFO_SHU = "04";

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    // 検索条件：カテゴリID
    private String condCateId;

    // 検索条件：サブカテゴリ（カテゴリ検索時のみ）
    private String condSubCateId;

    // 選択ページ番号
    private int selectPageNumber = 0;

    /* 【ロジック】 */
    // カテゴリ一覧の取得
    private GetUserFormCategoryLogic getUserCategoryLogic;

    // フォームダウンロードの取得
    private GetFormInfoLogic getFormInfoLogic;

    // 関連文書検索
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;

    /* 【DTO】 */
    private FormDownloadDto formDownloadDto;

    private PullDownMenuDto pullDownMenuDto;

    /* 情報種別("04"固定) */
    private String infoShu;
    
    private GetFormShozokuLogic getFormShozokuLogic;

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        // 初期処理（メニューから遷移された場合のみ）
        if (getPullDownMenuDto().isClearFlg()) {
            // ロジック【カテゴリ一覧の取得】
            // List listCategory = getGetCategoryLogic().getCategory(getInfoShu());
            List listCategory = getGetUserCategoryLogic().execute(getInfoShu(),
                    getBirdDateInfo().getSysDate(), getBirdUserInfo());
            getFormDownloadDto().setListNaviCate(listCategory);
            
            getFormDownloadDto().clearDto();
            getFormDownloadDto().clearCondDto();
            getFormDownloadDto().setViewCateName("");
            getPullDownMenuDto().setClearFlg(false);
        }

        return null;
    }

    /**
     * タイトルによるフォーム一覧検索処理
     * @return
     */
    public String searchByTitle() {
        // DTOクリア
        if (getSelectPageNumber() == 0) {
            getFormDownloadDto().clearDto();
        }
        // 必須チェック
        String title = getFormDownloadDto().getCondTitle();
        if (title == null || "".equals(title.trim())) {
            throw new NotNullException("タイトル", "titleName", null);
        }

        Map mapBunshoInfo = getGetFormInfoLogic().execute(
                                getInfoShu(), 
                                "", 
                                "",
                                title, 
                                getBirdDateInfo().getSysDate(), 
                                getBirdUserInfo(),
                                getFormDownloadDto().getCurrentPageNumber());

        doGetDocInfoLogicAfter(mapBunshoInfo);
        
        mapBunshoInfo.clear();
        mapBunshoInfo = null;
        
        // 検索モード設定
        getFormDownloadDto().setSearchMode(SEARCH_MODE_TITLE);
        doViewDetail();
        
// add start xkhata
        getFormDownloadDto().setViewCateName("");
// add end
        return null;
    }

    /**
     * カテゴリによるフォーム一覧検索処理
     * @return
     */
    public String searchByCate() {
        // DTOクリア
        if (getSelectPageNumber() == 0) {
            getFormDownloadDto().clearDto();
        }
        getFormDownloadDto().setCondTitle(null);
        // 選択されたカテゴリIDをDTOへセット
        getFormDownloadDto().setCondCateId(getCondCateId());

        // ロジック【フォームダウンロードの取得】を実行
        Map mapBunshoInfo = getGetFormInfoLogic().execute(
                                getInfoShu(),
                                getFormDownloadDto().getCondCateId(),
                                getFormDownloadDto().getCondSubCateId(),
                                "",
                                getBirdDateInfo().getSysDate(), 
                                getBirdUserInfo(),
                                getFormDownloadDto().getCurrentPageNumber());

        doGetDocInfoLogicAfter(mapBunshoInfo);
        // 検索モード設定
        getFormDownloadDto().setSearchMode(SEARCH_MODE_CATEGORY);

        doViewDetail();

// add start xkhata
        String viewCateName = new String();
        
        for ( int i = 0; i < getFormDownloadDto().getListNaviCate().size(); i++ ) {
            MstCategoryInfo entity = (MstCategoryInfo)getFormDownloadDto().getListNaviCate().get(i);
            if ( getCondCateId().equals( entity.getCateId())) {
                viewCateName = "【" + entity.getCateName() + "】";
                break;
            }
            
        }
        
        getFormDownloadDto().setViewCateName(viewCateName);
// add end
        return null;
    }

    /*
     * 検索処理後の情報設定
     */
    private void doGetDocInfoLogicAfter(Map mapBunshoInfo) {

        // 表示用データ
        getFormDownloadDto().setListForm((List) mapBunshoInfo.get("FORM_LIST"));
        // 表示サブカテゴリ
        if (getFormDownloadDto().getCondSubCateId() == null
                || getFormDownloadDto().getCondSubCateId().equals("")) {
            getFormDownloadDto().setCondSubCateId(
                    (String) mapBunshoInfo.get("DEF_SUB_CATE"));
            getFormDownloadDto().setListSubCate(
                    (List) mapBunshoInfo.get("SUB_CATE_LIST"));
        }
        getFormDownloadDto().setCount(
                ((Integer) mapBunshoInfo.get("TOTAL_COUNT")).intValue());

        // １件目の詳細を表示
        getFormDownloadDto().setIndexEntityViewDetail(0);
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {
        // ページ番号
        getFormDownloadDto().setCurrentPageNumber(getSelectPageNumber());

        // if (isNull(getFormDownloadDto().getCondCateId())) {
        if (formDownloadDto.getSearchMode() == 2) {

            // タイトルによる検索
            searchByTitle();
        } else {
            // カテゴリによる検索
            setCondCateId(getFormDownloadDto().getCondCateId());
            searchByCate();
        }

        return null;
    }

    /**
     * タブ変更処理
     * @return
     */
    public String changeTab() {
        // ページクリア
        getFormDownloadDto().setCurrentPageNumber(1);
        setSelectPageNumber(0);
        getFormDownloadDto().setCondSubCateId(getCondSubCateId());
        // ロジック【フォームダウンロード情報の取得】を実行
        Map mapBunshoInfo = getGetFormInfoLogic().execute(getInfoShu(),
                getFormDownloadDto().getCondCateId(), getCondSubCateId(),
                getFormDownloadDto().getCondTitle(),
                getBirdDateInfo().getSysDate(), getBirdUserInfo(),
                getFormDownloadDto().getCurrentPageNumber());

        doGetDocInfoLogicAfter(mapBunshoInfo);

        doViewDetail();

        return null;
    }

    /**
     * フォーム選択
     * @return
     */
    public String viewDetail() {
        doViewDetail();
        return null;
    }

    private void doViewDetail() {
        int intSelectIndex = getFormDownloadDto().getIndexEntityViewDetail();
        UIFormDownLoad entity = (UIFormDownLoad) getFormDownloadDto()
                .getListForm().get(intSelectIndex);
        getFormDownloadDto().setEntityViewDetail(entity);
// add start xkhata
        getFormDownloadDto().setSelectIndex(intSelectIndex);
//---2007/08/07 関連ファイルプルダウンの選択位置をクリア        
        getFormDownloadDto().setViewSelectAttachFileIndex(0);
        
        String infoShu = "04";
        String regDate = entity.getRegDate();
        String seq = entity.getSeq();

        Map paramMap = new HashMap();
        paramMap.put("infoShu",infoShu);
        paramMap.put("regDate",regDate);
        paramMap.put("seq",seq);

        List shozokuList = getFormShozokuLogic.execute(paramMap);
        
        String viewShozoku = new String();
        
        for ( int i = 0; i < shozokuList.size(); i++ ) {
            UIViewShozokuInfo shozoku = (UIViewShozokuInfo)shozokuList.get(i);
            viewShozoku += shozoku.getShozokuName().trim();
            
            if ( i != shozokuList.size() - 1 ) {
                viewShozoku +=  ",";
            }
        }
        getFormDownloadDto().setViewShozoku(viewShozoku);
        
// add end
        // // 関連文書情報取得
        // List listKanren = getGetKanrenBunshoInfoLogic().execute(getInfoShu(),
        // entity.getRegDate(), entity.getSeq());
        // getFormDownloadDto().setListKanrenBunsho(listKanren);
    }
    
    /**
     * @return documentDownloadDto を戻します。
     */
    public FormDownloadDto getFormDownloadDto() {
        return formDownloadDto;
    }

    /**
     * @param documentDownloadDto documentDownloadDto を設定。
     */
    public void setFormDownloadDto(FormDownloadDto formDownloadDto) {
        this.formDownloadDto = formDownloadDto;
    }

    /**
     * カテゴリ一覧の取得ロジックを取得します。
     * @return カテゴリ一覧の取得ロジック
     */
    public GetUserFormCategoryLogic getGetUserCategoryLogic() {
        return getUserCategoryLogic;
    }

    /**
     * カテゴリ一覧の取得ロジックを設定します。
     * @param カテゴリ一覧の取得ロジック
     */
    public void setGetUserCategoryLogic(
            GetUserFormCategoryLogic getUserCategoryLogic) {
        this.getUserCategoryLogic = getUserCategoryLogic;
    }

    // /**
    // * @return getCategoryLogic を戻します。
    // */
    // public GetCategoryLogic getGetCategoryLogic() {
    // return getCategoryLogic;
    // }
    // /**
    // * @param getCategoryLogic getCategoryLogic を設定。
    // */
    // public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
    // this.getCategoryLogic = getCategoryLogic;
    // }
    /**
     * @param infoShu infoShu を設定。
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }

    /**
     * @return infoShu を戻します。
     */
    public String getInfoShu() {
        return infoShu;
    }

    /**
     * @return getDocInfoLogic を戻します。
     */
    public GetFormInfoLogic getGetFormInfoLogic() {
        return getFormInfoLogic;
    }

    /**
     * @param getDocInfoLogic getDocInfoLogic を設定。
     */
    public void setGetFormInfoLogic(GetFormInfoLogic getFormInfoLogic) {
        this.getFormInfoLogic = getFormInfoLogic;
    }

    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo birdDateInfo を設定。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * BIRDユーザー情報設定処理
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    // /**
    // * @return getDefaultSubCategoryLogic を戻します。
    // */
    // public GetDefaultSubCategoryLogic getGetDefaultSubCategoryLogic() {
    // return getDefaultSubCategoryLogic;
    // }
    // /**
    // * @param getDefaultSubCategoryLogic getDefaultSubCategoryLogic を設定。
    // */
    // public void setGetDefaultSubCategoryLogic(
    // GetDefaultSubCategoryLogic getDefaultSubCategoryLogic) {
    // this.getDefaultSubCategoryLogic = getDefaultSubCategoryLogic;
    // }
    /**
     * @return condCateId を戻します。
     */
    public String getCondCateId() {
        return condCateId;
    }

    /**
     * @param condCateId condCateId を設定。
     */
    public void setCondCateId(String condCateId) {
        this.condCateId = condCateId;
    }

    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * @return condSubCateId を戻します。
     */
    public String getCondSubCateId() {
        return condSubCateId;
    }

    /**
     * @param condSubCateId condSubCateId を設定。
     */
    public void setCondSubCateId(String condSubCateId) {
        this.condSubCateId = condSubCateId;
    }

    /**
     * @return getKanrenBunshoInfoLogic を戻します。
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }

    /**
     * @param getKanrenBunshoInfoLogic getKanrenBunshoInfoLogic を設定。
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }

    /**
     * @return selectPageNumber を戻します。
     */
    public int getSelectPageNumber() {
        return selectPageNumber;
    }

    /**
     * @param selectPageNumber selectPageNumber を設定。
     */
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }

    public GetFormShozokuLogic getGetFormShozokuLogic() {
        return getFormShozokuLogic;
    }

    public void setGetFormShozokuLogic(GetFormShozokuLogic getFormShozokuLogic) {
        this.getFormShozokuLogic = getFormShozokuLogic;
    }
}