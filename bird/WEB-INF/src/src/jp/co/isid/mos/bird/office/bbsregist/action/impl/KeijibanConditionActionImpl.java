/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.office.bbsregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetCategoryLogicImpl;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.office.bbsregist.action.KeijibanConditionAction;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;
import jp.co.isid.mos.bird.office.bbsregist.logic.CountKeijibanLogic;
import jp.co.isid.mos.bird.office.bbsregist.logic.GetKeijibanInfoLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 掲示板登録 条件画面アクション
 * @author xytamura
 */
public class KeijibanConditionActionImpl implements CommonAction,
        KeijibanConditionAction {

    /* アクションID */
    public static String initialize_ACTION_ID = "BOF003A01";

    public static String search_ACTION_ID = "BOF003A02";

    public static String changePage_ACTION_ID = "BOF003A03";

    public static String changeTab_ACTION_ID = "BOF003A04";
    
    public static String goReference_ACTION_ID = "BOF003A05";

    public static String goInsert_ACTION_ID = "BOF003A07";


    /* カテゴリ一覧 */
    private List listCategory;

    /* DTO */
    private KeijibanConditionDto keijibanConditionDto;
    private KeijibanRegistDto keijibanRegistDto;
    
    private PullDownMenuDto pullDownMenuDto;

    /* 選択されたサブカテゴリID */
    private String selectedCateId;
    /* 検索文字 */
    private String selectedKeyWord;
    /* 選択されたタイトルフラグ */
    private boolean selectedTitleFlg = true;
    /* 選択されたメッセージフラグ */ 
    private boolean selectedMsgFlg = true;
    /* 選択行 */ 
    private int selectedIndex;
    
    /* 選択ページ番号 */
    private int selectPageNumber;

    // 掲示板の件数取得ロジック
    private CountKeijibanLogic countKeijibanLogic;

    // 掲示板一覧情報の取得
    private GetKeijibanInfoLogic getKeijibanInfoLogic;


    /**
     * 初期アクション
     * @return
     */
    public String initialize() throws ApplicationException {
        // メニューより遷移した場合は、セッションで保持している値をクリア
        if (getPullDownMenuDto().isClearFlg()) {

            // 共通ロジック【カテゴリの取得】を実行する.
            S2Container container = SingletonS2ContainerFactory.getContainer();
            GetCategoryLogic logic = (GetCategoryLogic) container
                    .getComponent(GetCategoryLogicImpl.class);
            List listCategory = logic.getCategory(KeijibanConditionDto.INFO_SHU);
            // カテゴリ情報をDTOへセット
            createDefaultCategory(listCategory);
            getKeijibanConditionDto().setListCategory(listCategory);

            clearSessionInfoFromMenu();
            getPullDownMenuDto().setClearFlg(false);
            getKeijibanConditionDto().setKeywordSearchMode(false);
            search();
        }
        //登録画面→条件画面
        else if(getKeijibanRegistDto().isRegToCondFlg()){
            getKeijibanConditionDto().setKeywordSearchMode(false);
            clearSessionInfoFromMenu();
            search();
            getKeijibanRegistDto().setRegToCondFlg(false);
        } 
// add start xkhata
        // 削除画面 → 条件画面
        else if (getKeijibanRegistDto().isDelToCondFlg()) {
            getKeijibanConditionDto().setKeywordSearchMode(false);
            clearSessionInfoFromMenu();
            search();
            getKeijibanRegistDto().setDelToCondFlg(false);            
        }
// add end
        return null;
    }

    /**
     * 検索処理
     * @return
     */
    private String search() {
        KeijibanConditionDto dto = getKeijibanConditionDto();
        dto.setCurrentPageNumber(1);

        // 検索結果をクリア
        dto.clearCondInsOrSearch();

        dto.setKeyWord(getSelectedKeyWord());
        dto.setMsgFlg(getSelectedMsgFlg());
        dto.setTitleFlg(getSelectedTitleFlg());
        
        // ロジック【掲示板情報の取得】
        List listkeijiban = getGetKeijibanInfoLogic().execute(dto);
        dto.setListKeijiban(listkeijiban);

        // ロジック【掲示板件数の取得】
        int countForm = getCountFormLogic().execute(dto);

        // 指定条件の件数を設定
        dto.setCount(countForm);

        // 選択カテゴリIDを保持
        dto.setSelectedCateId(dto.getCateId());

        
        return null;
    }
    
    /**
     * 掲示板検索処理
     * @return
     */
    public String searchKeyWord(){
        if(getSelectedKeyWord() == null || getSelectedKeyWord().trim().length() == 0){
            throw new NotNullException("検索文字", "keyWord", null);
        }
        if(!getSelectedMsgFlg() && !getSelectedTitleFlg()){
            throw new NotSelectedException("タイトル、又はメッセージ", "keyWord", null);
        }
        getKeijibanConditionDto().setKeywordSearchMode(true);
        search();
        return null;
    }
    
    /**
     * 新規登録アクション
     * @return
     */
    public String goInsert() {

        getKeijibanRegistDto().setRegMode(KeijibanConditionDto.REG_MODE_INSERT);
        getKeijibanConditionDto().setFlgCondToReg(true);

        return KeijibanConditionDto.VIEWID_EDIT;
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
     * @param 条件画面DTO
     */
    public void setKeijibanConditionDto(KeijibanConditionDto keijibanConditionDto) {
        this.keijibanConditionDto = keijibanConditionDto;
    }

    /**
     * 条件画面DTO取得処理
     * @return 条件画面DTO
     */
    public KeijibanConditionDto getKeijibanConditionDto() {
        return keijibanConditionDto;
    }
        

    /**
     * 参照/編集画面DTOを取得します。
     * @return 参照/編集画面DTO 
     */
    public KeijibanRegistDto getKeijibanRegistDto() {
        return keijibanRegistDto;
    }

    /**
     * 参照/編集画面DTOを設定します。
     * @param 参照/編集画面DTO
     */
    public void setKeijibanRegistDto(KeijibanRegistDto keijibanRegistDto) {
        this.keijibanRegistDto = keijibanRegistDto;
    }

    /**
     * タブ変更処理
     * @return
     */
    public String changeTab() {
        KeijibanConditionDto dto = getKeijibanConditionDto();
        // セッションからMstUser取得
        // 選択したタブのサブカテゴリIDを保持
        // カテゴリID、年月を検索時のものに戻す
        dto.setCateId(getSelectedCateId());
        // 選択中のリストインデックス
        dto.setSelectedListIndex(0);
        dto.setListKeijiban(null);
        
        //ロジック【掲示板件数の取得】
        int countForm = getCountFormLogic().execute(dto);

        dto.setCount(countForm);
        // ページ番号
        dto.setCurrentPageNumber(1);
        // ロジック【掲示板情報の取得】
        List listKeijiban = getGetKeijibanInfoLogic().execute(dto);

        dto.setListKeijiban(listKeijiban);

        return null;
    }

    /**
     * 選択中カテゴリID設定処理
     * @param _selectedSubCateId _selectedSubCateId を設定。
     */
    public void setSelectedCateId(String selectedCateId) {
        this.selectedCateId = selectedCateId;
    }

    /**
     * 選択中カテゴリID取得処理
     * @return _selectedSubCateId を戻します。
     */
    public String getSelectedCateId() {
        return selectedCateId;
    }


    /**
     * 参照画面へ遷移
     * @return
     */
    public String goReference() {
        UIKeijiban entity = (UIKeijiban) getKeijibanConditionDto().getListKeijiban().get(getSelectedIndex());
        getKeijibanRegistDto().setRefEntity(entity);
        
        return KeijibanConditionDto.VIEWID_REF;
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {

        KeijibanConditionDto dto = getKeijibanConditionDto();
        // ページ番号
        dto.setCurrentPageNumber(getSelectPageNumber());
        // DTOで保持しているリストをクリア
        dto.setListKeijiban(null);
        /* ロジック【掲示板情報の取得】を実行 */
        List listKeijiban = getGetKeijibanInfoLogic().execute(dto);
        dto.setListKeijiban(listKeijiban);

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
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) SingletonS2ContainerFactory.getContainer()
                .getComponent(BirdDateInfo.class);
    }


    /**
     * 掲示板の件数取得ロジック設定処理
     * @param countBunshoLogic countBunshoLogic を設定。
     */
    public void setCountKeijibanLogic(CountKeijibanLogic countKeijibanLogic) {
        this.countKeijibanLogic = countKeijibanLogic;
    }

    /**
     * 掲示板の件数取得ロジック取得処理
     * @return countBunshoLogic を戻します。
     */
    public CountKeijibanLogic getCountFormLogic() {
        return countKeijibanLogic;
    }


    /**
     * セッション情報クリア処理
     */
    private void clearSessionInfoFromMenu() {
        getKeijibanConditionDto().setCateId("");
        getKeijibanConditionDto().clearCondInsOrSearch();
    }

    /**
     * @return getBunshoInfoLogic を戻します。
     */
    public GetKeijibanInfoLogic getGetKeijibanInfoLogic() {
        return getKeijibanInfoLogic;
    }

    /**
     * @param getBunshoInfoLogic getBunshoInfoLogic を設定。
     */
    public void setGetKeijibanInfoLogic(GetKeijibanInfoLogic getKeijibanInfoLogic) {
        this.getKeijibanInfoLogic = getKeijibanInfoLogic;
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
     * 業務系すべてのカテゴリーを作成
     * @param listCategory
     */
    private void createDefaultCategory(List listCategory){
        MstCategoryInfo entity = new MstCategoryInfo();
        entity.setCateName(KeijibanConditionDto.ALL_BIZ_CATE_NAME);
        listCategory.add(0, entity);
    }

    /**
     * selectedKeyWordを取得します。
     * @return selectedKeyWord 
     */
    public String getSelectedKeyWord() {
        return selectedKeyWord;
    }

    /**
     * selectedKeyWordを設定します。
     * @param selectedKeyWord
     */
    public void setSelectedKeyWord(String selectedKeyWord) {
        this.selectedKeyWord = selectedKeyWord;
    }

    /**
     * 選択されたメッセージフラグを取得します。
     * @return 選択されたメッセージフラグ 
     */
    public boolean getSelectedMsgFlg() {
        return selectedMsgFlg;
    }

    /**
     * 選択されたメッセージフラグを設定します。
     * @param 選択されたメッセージフラグ
     */
    public void setSelectedMsgFlg(boolean selectedMsgFlg) {
        this.selectedMsgFlg = selectedMsgFlg;
    }

    /**
     * 選択されたタイトルフラグを取得します。
     * @return selectedTitleFlg 
     */
    public boolean getSelectedTitleFlg() {
        return selectedTitleFlg;
    }

    /**
     * 選択されたタイトルフラグを設定します。
     * @param selectedTitleFlg
     */
    public void setSelectedTitleFlg(boolean selectedTitleFlg) {
        this.selectedTitleFlg = selectedTitleFlg;
    }

    /**
     * 選択行を取得します。
     * @return 選択行 
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * 選択行を設定します。
     * @param 選択行
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    
}