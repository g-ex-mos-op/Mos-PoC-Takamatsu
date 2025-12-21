/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.office.formregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenSakiBunshoInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.GetSubCategoryLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.office.formregist.action.FormConditionAction;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.CountFormLogic;
import jp.co.isid.mos.bird.office.formregist.logic.GetDefaultSubCategoryLogic;
import jp.co.isid.mos.bird.office.formregist.logic.GetFormInfoLogic;

/**
 * フォーム登録 条件画面アクション
 * @author xytamura
 */
public class FormConditionActionImpl implements CommonAction,
        FormConditionAction {

    /* アクションID */
    public static String initialize_ACTION_ID = "BOF001A01";

    public static String search_ACTION_ID = "BOF001A02";

    public static String registRank_ACTION_ID = "BOF001A04";

    public static String goInsert_ACTION_ID = "BOF001A03";

    public static String goRegist_ACTION_ID = "BOF001A05";

    public static String goDelete_ACTION_ID = "BOF001A06";

    public static String changePage_ACTION_ID = "BOF001A07";

    public static String changeTab_ACTION_ID = "BOF001A08";

    /* ビューID */

    private static final String VIEWID_EDIT = "BOF001V02";

    private static final String VIEWID_CONFIRM = "BOF001V03";

    private static final String VIEWID_RANK = "BOF001V04";

    /* 情報種別 */
    private static String INFO_SHU = "04";

    /* 年月出力月数 */
    //---2007/08/06 3年間に変更
//    private static int NENGETU_DISPLAY_MONTH = 6;
    private static int NENGETU_DISPLAY_MONTH = 36;


    /* カテゴリ一覧 */
    private List listCategory;

    /* DTO */
    private FormConditionDto formConditionDto;

//  add start xkhata
    private FormRegistFormDto formRegistFormDto;
// add end

    //    private PublicTargetDto publicTargetDto;

    private PublicTargetSearchDto publicTargetSearchDto;

    private PullDownMenuDto pullDownMenuDto;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    /* 選択サブカテゴリID */
    private String _selectedSubCateId;

    /* 選択ページ番号 */
    private int selectPageNumber;

    /* ロジック */
    // サブカテゴリ取得ロジック
    private GetSubCategoryLogic getSubCategoryLogic;

    // フォームの件数取得ロジック
    private CountFormLogic countFormLogic;

    // フォーム一覧情報の取得
    private GetFormInfoLogic getFormInfoLogic;

    // デフォルトサブカテゴリの取得
    private GetDefaultSubCategoryLogic getDefaultSubCategoryLogic;

    //
    private GetKoukaiTaishoLogic getKoukaiTaishoLogic;

    //【関連文書検索】
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    //自分を関連先として登録している文書の取得ロジック
    private GetKanrenSakiBunshoInfoLogic getKanrenSakiBunshoInfoLogic; 
    
    // カテゴリ取得ロジック
    private GetCategoryLogic getCategoryLogic;
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() throws ApplicationException {
        // メニューより遷移した場合は、セッションで保持している値をクリア
        if (getPullDownMenuDto().isClearFlg()) {
            clearSessionInfoFromMenu();
            getPullDownMenuDto().setClearFlg(false);
        }
        getFormConditionDto().setListKanrenSaki(null);
        DateFormatter formatter = new DateFormatter();

        // 共通ロジック【カテゴリの取得】を実行する.
        List listCategory = getGetCategoryLogic().getCategory(INFO_SHU);
        // カテゴリ情報をDTOへセット
        getFormConditionDto().setListCategory(listCategory);

        // 現在日付取得
        String sysDate = getBirdDateInfo().getSysDate();
        String sysMonth = formatter.format(sysDate,
                DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        // 現在月から過去６ヶ月分の年月リスト作成
        List listMonth = new ArrayList();
        for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysMonth, i);
            } catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理", ex.toString(), ex);
            }

            SelectItem item = new SelectItem(month, formatter.format(month,
                    DateFormatter.PATTERN_MONTH_SLASH,
                    DateFormatter.DATE_TYPE_YM));
            listMonth.add(item);
        }
        getFormConditionDto().setListNengetu(listMonth);

        return null;
    }

    /**
     * フォーム一覧検索処理
     * @return
     */
    public String search() {

        FormConditionDto dto = getFormConditionDto();
        dto.setCurrentPageNumber(1);

        // 検索結果をクリア
        dto.clearCondInsOrSearch();

// change start xkhata 20070829
        // デフォルトサブカテゴリの取得
//        String defSubCateId = getGetDefaultSubCategoryLogic().execute(dto);
        String defSubCateId = getGetDefaultSubCategoryLogic().execute(dto,getBirdUserInfo().getUserID());
// add end
        dto.setSelectedSubCateId(defSubCateId);
        
        if (defSubCateId == null) {
            throw new NoResultException();
        }


        // ロジック【フォーム情報の取得】
        List listForm = getGetFormInfoLogic().execute(
                    dto.getNengetu(),
                    dto.getCateId(), 
                    dto.getSelectedSubCateId(),
                    getBirdUserInfo().getUserID(), 
                    dto.getCurrentPageNumber(),
                    getBirdDateInfo().getSysDate());

        dto.setListForm(listForm);

        /* 共通ロジック【サブカテゴリの取得】を実行 */
        List listSubCategory = getGetSubCategoryLogic().getSubCategory(
                INFO_SHU, getFormConditionDto().getCateId());
        getFormConditionDto().setListSubCategory(listSubCategory);


        /* 指定条件でのフォーム件数取得 */
        int countForm = getCountFormLogic().execute(
                            dto.getNengetu(),
                            dto.getCateId(), 
                            dto.getSelectedSubCateId(),
                            getBirdUserInfo().getUserID(),
                            getBirdDateInfo().getSysDate());

        // 指定条件の件数を設定
        dto.setCount(countForm);

        // 選択カテゴリIDを保持
        dto.setSelectedCateId(dto.getCateId());
        // 選択年月を保持
        dto.setSelectedNengetu(dto.getNengetu());
        // 選択サブカテゴリをクリア
        dto.setSelectedSubCateId(null);
        dto.setSelectedSubCateId(defSubCateId);

        
        // 選択中のリストインデックス
        if (dto.getSelectedListIndex() < 0) {
            for(int i = 0; i < listForm.size(); i++){
                UIFormInfo entity = (UIFormInfo)listForm.get(i);
                if(defSubCateId.equals(entity.getSubCateId())){
                    dto.setSelectedListIndex(i);

                    break;
                }
            }
        }
        return null;
    }

    /**
     * 新規登録アクション
     * @return
     */
    public String goInsert() {
        // セッション保持データクリア
        clearSessionInfoFromMenu();
        getFormConditionDto().clearData();

// add start xkhata 
        getFormRegistFormDto().setAttachName(null);
        getFormRegistFormDto().setUploadFileName(null);
// add end
        
        getFormConditionDto().setRegMode(FormConditionDto.REG_MODE_INSERT);
        getFormConditionDto().setFlgCondToReg(true);


        getPublicTargetSearchDto().setInfoShu(INFO_SHU);
        getPublicTargetSearchDto().setRegDate(getBirdDateInfo().getSysDate());
        getPublicTargetSearchDto().setListTrnControlCompany(new ArrayList());
        getPublicTargetSearchDto().setListTrnControlGyotai(new ArrayList());
        getPublicTargetSearchDto().setListTrnControlGyotaiKobetu(new ArrayList());
        getPublicTargetSearchDto().setListTrnControlGyotaiTenpo(new ArrayList());
        getPublicTargetSearchDto().setListTrnControlShozoku(new ArrayList());
        
        getFormConditionDto().setListKanrenBunsho(new ArrayList());
        
        return VIEWID_EDIT;
    }

    /**
     * @param listCategory listCategory を設定。
     */
    public void setListCategory(List listCategory) {
        this.listCategory = listCategory;
    }

    /**
     * @return listCategory を戻します。
     */
    public List getListCategory() {
        return listCategory;
    }

    /**
     * 条件画面DTO設定処理
     * @param formConditionDto formConditionDto を設定。
     */
    public void setFormConditionDto(FormConditionDto formConditionDto) {
        this.formConditionDto = formConditionDto;
    }

    /**
     * 条件画面DTO取得処理
     * @return conditionDto を戻します。
     */
    public FormConditionDto getFormConditionDto() {
        return formConditionDto;
    }


    /**
     * タブ変更処理
     * @return
     */
    public String changeTab() {
        FormConditionDto dto = getFormConditionDto();
//        S2Container container = SingletonS2ContainerFactory.getContainer();
        // セッションからMstUser取得
        // 選択したタブのサブカテゴリIDを保持
        dto.setSelectedSubCateId(getSelectedSubCateId());
        // カテゴリID、年月を検索時のものに戻す
        dto.setCateId(dto.getSelectedCateId());
        // 選択中のリストインデックス
        dto.setSelectedListIndex(0);
        //        /* 指定条件でのフォーム件数取得 */
        //        CountFormLogic logicCountBunsho = (CountFormLogic) container
        //                .getComponent(CountFormLogicImpl.class);
        //        int countBunsho = logicCountBunsho.execute(dto.getNengetu(), dto
        //                .getCateId(), dto.getSelectedSubCateId(), getMstUser()
        //                .getRCompanyCd(), getMstUser().getBumonCd());

        /* 指定条件でのフォーム件数取得 */
//        CountFormLogic logicCountForm = (CountFormLogic) container
//                .getComponent(CountFormLogicImpl.class);
        int countForm = getCountFormLogic().execute(
                            dto.getNengetu(), 
                            dto.getCateId(), 
                            dto.getSelectedSubCateId(),
                            getBirdUserInfo().getUserID(),
                            getBirdDateInfo().getSysDate());

        dto.setCount(countForm);
        dto.setCurrentPageNumber(1);
        /* ロジック【フォーム一覧情報の取得】を実行 */
//---2007/08/06 DI機能を使用するように変更
//        GetFormInfoLogic logicGetForm = (GetFormInfoLogic) container
//                .getComponent(GetFormInfoLogic.class);
        
        // ページ番号
        dto.setCurrentPageNumber(1);
        dto.setNengetu(dto.getSelectedNengetu());
        // DTOで保持しているリストをクリア
        dto.setListForm(null);
        //        List listBunsho = logicGetForm.execute(dto.getNengetu(), dto
        //                .getCateId(), dto.getSelectedSubCateId(), dto
        //                .getCurrentPageNumber(), getMstUser().getRCompanyCd(),
        //                getMstUser().getBumonCd());

//---2007/08/06 DI機能を使用するように変更
//        List listForm = logicGetForm.execute(dto.getNengetu(), dto.getCateId(),
//                dto.getSelectedSubCateId(), dto.getCurrentPageNumber());
        List listForm = getGetFormInfoLogic().execute(
                            dto.getNengetu(),
                            dto.getCateId(), 
                            dto.getSelectedSubCateId(),
                            getBirdUserInfo().getUserID(), 
                            dto.getCurrentPageNumber(),
                            getBirdDateInfo().getSysDate());
        dto.setListForm(listForm);

        return null;
    }

    /**
     * 選択中サブカテゴリID設定処理
     * @param _selectedSubCateId _selectedSubCateId を設定。
     */
    public void setSelectedSubCateId(String _selectedSubCateId) {
        this._selectedSubCateId = _selectedSubCateId;
    }

    /**
     * 選択中サブカテゴリID取得処理
     * @return _selectedSubCateId を戻します。
     */
    public String getSelectedSubCateId() {
        return _selectedSubCateId;
    }

    /**
     * 削除ボタンアクション
     * @return
     */
    public String goDelete() {
        getFormConditionDto().setRegMode(FormConditionDto.REG_MODE_DELETE);
        getFormConditionDto().setFlgCondToReg(true);

        //xyamauchi add
        getFormRegistFormDto().setAttachName(null);
        getFormRegistFormDto().setUploadFileName(null);
        getFormRegistFormDto().clearFileName();
        //xyamauchi end
        
        // 公開対象情報取得

        String regDate = getFormConditionDto().getSelectedEntity().getRegDate();
        String seq = getFormConditionDto().getSelectedEntity().getSeq();

        PublicTargetDto publicDto = getKoukaiTaishoLogic.execute(INFO_SHU,
                regDate, seq);
        //データ反映
        getPublicTargetSearchDto().setInfoShu(INFO_SHU);
        getPublicTargetSearchDto().setRegDate(regDate);
        getPublicTargetSearchDto().setSeq(seq);
        getPublicTargetSearchDto().setListTrnControlCompany(
                publicDto.getListTrnControlCompany());
        getPublicTargetSearchDto().setListTrnControlShozoku(
                publicDto.getListTrnControlShozoku());
        getPublicTargetSearchDto().setListTrnControlGyotai(
                publicDto.getListTrnControlGyotai());
        getPublicTargetSearchDto().setListTrnControlGyotaiKobetu(
                publicDto.getListTrnControlGyotaiKobetu());
        getPublicTargetSearchDto().setListTrnControlGyotaiTenpo(
                publicDto.getListTrnControlGyotaiTenpo());

        // 関連文書情報取得
        List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU,
                regDate, seq);
        getFormConditionDto().setListKanrenBunsho(listKanren);

        //リンクファイルとして登録されているか
        List list =  getGetKanrenSakiBunshoInfoLogic().execute(INFO_SHU, regDate, seq);
        getFormConditionDto().setListKanrenSaki(list);

        return VIEWID_CONFIRM;
    }

    /**
     * 編集ボタンアクション 編集画面へ遷移
     * @return
     */
    public String goRegist() {
        getFormConditionDto().setRegMode(FormConditionDto.REG_MODE_UPDATE);
        getFormConditionDto().setFlgCondToReg(true);

// add start xkhata 
        getFormRegistFormDto().setAttachName(null);
        getFormRegistFormDto().setUploadFileName(null);
// add end

        // 公開対象情報取得
        String regDate = getFormConditionDto().getSelectedEntity().getRegDate();
        String seq = getFormConditionDto().getSelectedEntity().getSeq();

        PublicTargetDto publicDto = getKoukaiTaishoLogic.execute(INFO_SHU,
                regDate, seq);
        //データ反映
        getPublicTargetSearchDto().setInfoShu(INFO_SHU);
        getPublicTargetSearchDto().setRegDate(regDate);
        getPublicTargetSearchDto().setSeq(seq);
        getPublicTargetSearchDto().setListTrnControlCompany(
                publicDto.getListTrnControlCompany());
        getPublicTargetSearchDto().setListTrnControlShozoku(
                publicDto.getListTrnControlShozoku());
        getPublicTargetSearchDto().setListTrnControlGyotai(
                publicDto.getListTrnControlGyotai());
        getPublicTargetSearchDto().setListTrnControlGyotaiKobetu(
                publicDto.getListTrnControlGyotaiKobetu());
        getPublicTargetSearchDto().setListTrnControlGyotaiTenpo(
                publicDto.getListTrnControlGyotaiTenpo());

        // 関連文書情報取得
        List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU,
                regDate, seq);
        getFormConditionDto().setListKanrenBunsho(listKanren);

        return VIEWID_EDIT;
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {

//        S2Container container = SingletonS2ContainerFactory.getContainer();
        FormConditionDto dto = getFormConditionDto();

        /* ロジック【フォーム一覧情報の取得】を実行 */
//---2007/08/06 DI機能を使用するように変更
//        GetFormInfoLogic logicGetForm = (GetFormInfoLogic) container
//                .getComponent(GetFormInfoLogicImpl.class);
        // ページ番号
        dto.setCurrentPageNumber(getSelectPageNumber());
        // 選択カテゴリIDを保持
        dto.setSelectedCateId(dto.getCateId());
        // 選択年月を保持
        dto.setSelectedNengetu(dto.getNengetu());
        // 選択サブカテゴリ
        String selectedSubCateId = dto.getSelectedSubCateId();
        //        dto.setSelectedSubCateId(null);
        // 選択中のリストインデックス
        //        if (dto.getSelectedListIndex() < 0) {
        //            dto.setSelectedListIndex(0);
        //        }

//--- 2007/08/07 選択位置をクリア
        dto.setSelectedListIndex(0);
        
        // DTOで保持しているリストをクリア
        dto.setListForm(null);
        //        List listBunsho = logicGetForm.execute(dto.getNengetu(), dto
        //                .getCateId(), dto.getSelectedSubCateId(), dto
        //                .getCurrentPageNumber(), getMstUser().getRCompanyCd(),
        //                getMstUser().getBumonCd());

//---2007/08/06 DI機能を使用するように変更
//        List listForm = logicGetForm.execute(dto.getNengetu(), dto.getCateId(),
//                selectedSubCateId, dto.getCurrentPageNumber());
        List listForm = getGetFormInfoLogic().execute(
                            dto.getNengetu(),
                            dto.getCateId(), 
                            selectedSubCateId,
                            getBirdUserInfo().getUserID(), 
                            dto.getCurrentPageNumber(),
                            getBirdDateInfo().getSysDate());
        dto.setListForm(listForm);

        return null;
    }

    /**
     * @param selectPageNumber selectPageNumber を設定。
     */
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }

    /**
     * @return selectPageNumber を戻します。
     */
    public int getSelectPageNumber() {
        return selectPageNumber;
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
//        return (BirdDateInfo)SingletonS2ContainerFactory.getContainer().getComponent(BirdDateInfo.class);
        return birdDateInfo;
    }

//    /**
//     * MstUser取得処理
//     * @return
//     */
//    private MstUser getMstUser() {
//        BirdUserInfo info = (BirdUserInfo)SingletonS2ContainerFactory.getContainer().getComponent(BirdUserInfo.class);
//        return info.getMstUser();
//    }

    /**
     * サブカテゴリ取得ロジック設定ロジック
     * @param getSubCategoryLogic getSubCategoryLogic を設定。
     */
    public void setGetSubCategoryLogic(GetSubCategoryLogic getSubCategoryLogic) {
        this.getSubCategoryLogic = getSubCategoryLogic;
    }

    /**
     * サブカテゴリ取得ロジック取得ロジック
     * @return getSubCategoryLogic を戻します。
     */
    public GetSubCategoryLogic getGetSubCategoryLogic() {
        return getSubCategoryLogic;
    }

    /**
     * フォームの件数取得ロジック設定処理
     * @param countBunshoLogic countBunshoLogic を設定。
     */
    public void setCountFormLogic(CountFormLogic countFormLogic) {
        this.countFormLogic = countFormLogic;
    }

    /**
     * フォームの件数取得ロジック取得処理
     * @return countBunshoLogic を戻します。
     */
    public CountFormLogic getCountFormLogic() {
        return countFormLogic;
    }

    /**
     * 順位設定アクション
     * @return
     */
    public String registRank() {
        return VIEWID_RANK;
    }

    /**
     * セッション情報クリア処理
     */
    private void clearSessionInfoFromMenu() {
        getFormConditionDto().setCateId("");
        getFormConditionDto().setNengetu("");
        getFormConditionDto().clearCondInsOrSearch();
        getPublicTargetSearchDto().clearData();
    }

    /**
     * @return getBunshoInfoLogic を戻します。
     */
    public GetFormInfoLogic getGetFormInfoLogic() {
        return getFormInfoLogic;
    }

    /**
     * @param getBunshoInfoLogic getBunshoInfoLogic を設定。
     */
    public void setGetFormInfoLogic(GetFormInfoLogic getFormInfoLogic) {
        this.getFormInfoLogic = getFormInfoLogic;
    }

    /**
     * @return getDefaultSubCategoryLogic を戻します。
     */
    public GetDefaultSubCategoryLogic getGetDefaultSubCategoryLogic() {
        return getDefaultSubCategoryLogic;
    }

    /**
     * @param getDefaultSubCategoryLogic getDefaultSubCategoryLogic を設定。
     */
    public void setGetDefaultSubCategoryLogic(
            GetDefaultSubCategoryLogic getDefaultSubCategoryLogic) {
        this.getDefaultSubCategoryLogic = getDefaultSubCategoryLogic;
    }

    /**
     * @return getKoukaiTaishoLogic を戻します。
     */
    public GetKoukaiTaishoLogic getGetKoukaiTaishoLogic() {
        return getKoukaiTaishoLogic;
    }

    /**
     * @param getKoukaiTaishoLogic getKoukaiTaishoLogic を設定。
     */
    public void setGetKoukaiTaishoLogic(
            GetKoukaiTaishoLogic getKoukaiTaishoLogic) {
        this.getKoukaiTaishoLogic = getKoukaiTaishoLogic;
    }

    //    /**
    //     * @return publicTargetDto を戻します。
    //     */
    //    public PublicTargetDto getPublicTargetDto() {
    //        return publicTargetDto;
    //    }
    //
    //    /**
    //     * @param publicTargetDto publicTargetDto を設定。
    //     */
    //    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
    //        this.publicTargetDto = publicTargetDto;
    //    }

    /**
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }

    /**
     * @return publicTargetSearchDto を戻します。
     */
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
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
     * 自分を関連先として登録している文書の取得ロジックを取得します。
     * @return 自分を関連先として登録している文書の取得ロジック 
     */
    public GetKanrenSakiBunshoInfoLogic getGetKanrenSakiBunshoInfoLogic() {
        return getKanrenSakiBunshoInfoLogic;
    }

    /**
     * 自分を関連先として登録している文書の取得ロジックを設定します。
     * @param 自分を関連先として登録している文書の取得ロジック
     */
    public void setGetKanrenSakiBunshoInfoLogic(
            GetKanrenSakiBunshoInfoLogic getKanrenSakiBunshoInfoLogic) {
        this.getKanrenSakiBunshoInfoLogic = getKanrenSakiBunshoInfoLogic;
    }

    public FormRegistFormDto getFormRegistFormDto() {
        return formRegistFormDto;
    }

    public void setFormRegistFormDto(FormRegistFormDto formRegistFormDto) {
        this.formRegistFormDto = formRegistFormDto;
    }

    public GetCategoryLogic getGetCategoryLogic() {
        return getCategoryLogic;
    }

    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
        this.getCategoryLogic = getCategoryLogic;
    }

}