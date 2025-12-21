
/*
 * 作成日: 2008/02/19
 */
package jp.co.isid.mos.bird.communication.docform.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.action.impl.OutLinkActionImpl;
import jp.co.isid.mos.bird.common.dto.PortalSearchInfoDto;
import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.communication.docform.action.DocFormAction;
import jp.co.isid.mos.bird.communication.docform.code.SearchField;
import jp.co.isid.mos.bird.communication.docform.common.DocFormCont;
import jp.co.isid.mos.bird.communication.docform.dto.DocFormDto;
import jp.co.isid.mos.bird.communication.docform.dto.FullTextSearchDocDto;
import jp.co.isid.mos.bird.communication.docform.dto.FullTextSearchFormDto;
import jp.co.isid.mos.bird.communication.docform.dto.RequestDto;
import jp.co.isid.mos.bird.communication.docform.entity.UIViewDocFormInfo;
import jp.co.isid.mos.bird.communication.docform.entity.UIViewShozokuInfo;
import jp.co.isid.mos.bird.communication.docform.logic.GetDocFormInfoLogic;
import jp.co.isid.mos.bird.communication.docform.logic.GetDocFormShozokuLogic;
import jp.co.isid.mos.bird.communication.docform.logic.InitializeLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

/**
 * 文書＆フォーム画面アクション
 * @author xnkusama
 *
 * 更新日：2008/12/10 xkinu FullTextSearch対応追加
 */
public class DocFormActionImpl implements CommonAction, DocFormAction {

    /* アクションID */
    public static final String initialize_ACTION_ID   = "BCM004A01";
    public static final String searchByCate_ACTION_ID = "BCM004A02";
    public static final String searchByTitle_ACTION_ID= "BCM004A03";
    public static final String changeTab_ACTION_ID    = "BCM004A04";
    public static final String changePage_ACTION_ID   = "BCM004A05";
    public static final String viewDetail_ACTION_ID   = "BCM004A06";
    public static final String changeInfoShu_ACTION_ID= "BCM004A07";

    /* 検索モード */
    private static final int SEARCH_MODE_CATEGORY = 1;
    private static final int SEARCH_MODE_TITLE = 2;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    /************
     * ロジック *
     ************/
    //LOGIC【初期化処理】
    private InitializeLogic docformInitializeLogic;
    //文書・フォーム検索ロジック
    private GetDocFormInfoLogic docformGetDocFormInfoLogic;
    //関連文書検索
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    private GetDocFormShozokuLogic getDocShozokuLogic;
    /*********
     *  DTO  *
     *********/
    private PullDownMenuDto pullDownMenuDto;
    /** BIRD共通DTO【画面間Request】*/
    private PortalSearchInfoDto portalSearchInfoDto;

    private DocFormDto docFormDto;
    private RequestDto docformReqDto;


    /** FullTextSearchDTO：文書用 */
    private FullTextSearchDto fullTextSearchDocDto;
    /** FullTextSearchDTO：フォーム用 */
    private FullTextSearchDto fullTextSearchFormDto;

	/**
     * 初期処理
     * @return
     */
    public String initialize() {
        //１．初期処理（メニューから遷移された場合のみ）
        if (getPullDownMenuDto().isClearFlg()) {
            //１．メニューから遷移フラグを反転
            getPullDownMenuDto().setClearFlg(false);
        	//２．LOGIC【初期化処理】を実行します。
            getDocformInitializeLogic().execute(birdDateInfo, birdUserInfo, getDocFormDto());

    		//２．DTO【リクエスト元情報保持DTO】.アクション種類が”初期化”の場合、
    		//　　総合検索画面から呼び出されたと判断し、下記の処理を行います。
            if(CommonUtil.ACTION_KIND_INIT == getPortalSearchInfoDto().getActionKind()) {
                //２－１.DTO【リクエスト元情報保持DTO】.アクション種類へ”稼動中”を設定します。
    			getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_EXEC);
                try{
                    // 必須チェック
                    if (CommonUtil.isNull(getPortalSearchInfoDto().getSearchWord())) {
                        throw new NoInputException("検索文字","searchWord", 0);
                    }
        			//２－２．検索対象文字列をDTO【自画面】全文検索へ設定します。
        			getDocformReqDto().setSearchWord(getPortalSearchInfoDto().getSearchWord());
                	//３．検索処理を実行します。
                	searchByButton();
                }
                finally {
                    //６．DTO【リクエスト元情報保持DTO】.アクション種類へ”非稼動”を設定します。
        			getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_NULL);
                }
            }
            else if((((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())
            		&& getCommonCodeDto().getParam(OutLinkActionImpl.CommonCodeDto_PKEY_PARAM) != null)) ||
            		getBirdUserInfo().isPmossles()) {
                // DTOクリア
               	getDocFormDto().clearDto();

                String[] params = ((String)getCommonCodeDto().getParam(OutLinkActionImpl.CommonCodeDto_PKEY_PARAM)).split("&");
            	//1.BIRD内遷移フラグをリセット
            	getCommonCodeDto().clear();

                //第１パラメータ：情報種別
                String infoShu = params[0].substring(params[0].indexOf("=") + 1);
                //第２パラメータ：カテゴリID
                String cateId = params[1].substring(params[1].indexOf("=") + 1);
                //第３パラメータ：サブカテゴリID（指定なしの可能性あり）
                String subCateId = null;
                if (params.length >= 3) {
                    subCateId = params[2].substring(params[2].indexOf("=") + 1);
                }

                getDocformReqDto().setSearchField(infoShu);
                getDocformReqDto().setCateSearchField(infoShu);
                getDocformReqDto().setSearchCateId(cateId);
                getDocformReqDto().setPortalLinkSubCateId(subCateId);
                getDocformReqDto().setCurrentPageNumber(1);

                doSearchByCate();
            }
        }
        //２．nullをリターンします。
        return null;
    }

    /**
     * 検索ボタン押下時処理
     * @return
     */
    public String searchByButton() {

        // DTOクリア
       	getDocFormDto().clearDto();
        // 必須チェック
        String searchWord = getDocformReqDto().getSearchWord();
        String title = getDocformReqDto().getSearchTitle();
        if (isNull(searchWord) && isNull(title)) {
            throw new NoInputException("全文検索又はタイトル","searchWord", 0);
        }
        MetaCharVerifier verifier = new MetaCharVerifier();
        if (!isNull(searchWord) && !verifier.validate(searchWord)) {
            throw new InvalidInputException("全文検索","searchWord", 0);
        }
        if (!isNull(title) && !verifier.validate(title)) {
            throw new InvalidInputException("タイトル","searchTitle", 0);
        }
        getDocformReqDto().setSearchField(getDocformReqDto().getButtonSearchField());
        if (SearchField.FIELD_DOCFORM.equals(getDocformReqDto().getSearchField())) {
        	getDocformReqDto().setTargetTab(SearchField.FIELD_DOCFORM);
        }
        else if (SearchField.FIELD_DOC.equals(getDocformReqDto().getSearchField())) {
        	getDocformReqDto().setTargetTab(SearchField.FIELD_DOC);
        }
        else if (SearchField.FIELD_FORM.equals(getDocformReqDto().getSearchField())) {
        	getDocformReqDto().setTargetTab(SearchField.FIELD_FORM);
        }
        getDocformReqDto().setCurrentPageNumber(1);
        doSearchByTitle(true);

        // ・検索対象が文書・フォームで、結果がどちらか片方しかない場合は、検索タイプを置き換える
        // ・結果表示している情報種別を設定する
         if (SearchField.FIELD_DOCFORM.equals(getDocformReqDto().getSearchField())) {
            if (!(getDocFormDto().isExistBunshoData() && getDocFormDto().isExistFormData())) {
                if (getDocFormDto().isExistBunshoData()) {
                    getDocFormDto().setTargetTab(SearchField.FIELD_DOC);
                }
                else {
                    getDocFormDto().setTargetTab(SearchField.FIELD_FORM);
                }
            }
        }
        return null;
    }
    /**
     * タイトルによる検索
     * @param mode 検索ボタンでの処理かどうか （true：検索ボタンからの処理）
     */
    private void doSearchByTitle(boolean mode) {
        Map mapSearchData
        = getDocformGetDocFormInfoLogic()
                .execute(getDocformReqDto()
                        , getBirdDateInfo(), getBirdUserInfo()
                        , (FullTextSearchDocDto)getFullTextSearchDocDto()
                        , (FullTextSearchFormDto)getFullTextSearchFormDto());

        doAfter(mapSearchData);

        // 文書、フォームのデータ有無をDTOにセット
        if (mode) {
        	if(mapSearchData.containsKey(DocFormCont.RESULT_MAP_KEY_EXIST_BUNSHO)) {
        		getDocFormDto().setExistBunshoData(((Boolean) mapSearchData.get(DocFormCont.RESULT_MAP_KEY_EXIST_BUNSHO)).booleanValue());
        	}
        	if(mapSearchData.containsKey(DocFormCont.RESULT_MAP_KEY_EXIST_FORM)) {
        		getDocFormDto().setExistFormData(((Boolean) mapSearchData.get(DocFormCont.RESULT_MAP_KEY_EXIST_FORM)).booleanValue());
        	}
        }

        mapSearchData.clear();
        mapSearchData = null;

        //検索モード設定
        getDocFormDto().setSearchMode(SEARCH_MODE_TITLE);

        //1件目の詳細を表示
        doViewDetail();

        getDocFormDto().setViewCateName("");

    }

    /**
     * カテゴリによる文書一覧検索処理
     * @return
     */
    public String searchByCate() {
        // DTOクリア
       	getDocFormDto().clearDto();
        getDocformReqDto().setSearchField(getDocformReqDto().getCateSearchField());
        getDocformReqDto().setCurrentPageNumber(1);
        doSearchByCate();

        return null;
    }

    /**
     * カテゴリによる検索
     */
    private void doSearchByCate() {
        Map mapSearchData = getDocformGetDocFormInfoLogic().execute(
                                getDocformReqDto()
                                , getBirdDateInfo(), getBirdUserInfo()
                                , (FullTextSearchDocDto)getFullTextSearchDocDto()
                                , (FullTextSearchFormDto)getFullTextSearchFormDto());

        doAfter(mapSearchData);
        //検索モード設定
        getDocFormDto().setSearchMode(SEARCH_MODE_CATEGORY);
        //1件目の詳細を表示
        doViewDetail();
        String searchField = getDocFormDto().getSearchedField();
        String viewCateName = null;
        List listCate = getDocFormDto().getListCategory(searchField);
        for( int i = 0; i < listCate.size(); i++ ) {
            MstCategoryInfo entity = (MstCategoryInfo) listCate.get(i);
            if ( getDocFormDto().getSearchedCateId().equals( entity.getCateId())) {
                viewCateName = "【" + entity.getCateName() + "】";
                break;
            }
        }
        getDocFormDto().setViewCateName(viewCateName);
    }
    /**
     * 検索処理後の情報設定
     */
    private void doAfter(Map mapSearchData) {
        getDocFormDto().setSearchedField(getDocformReqDto().getSearchField());
        getDocFormDto().setSearchedCateId(getDocformReqDto().getSearchCateId());
        getDocFormDto().setSearchedWord(getDocformReqDto().getSearchWord());
        getDocFormDto().setSearchedTitle(getDocformReqDto().getSearchTitle());
        getDocFormDto().setTargetTab(getDocformReqDto().getTargetTab());
        getDocFormDto().setCurrentPageNumber(getDocformReqDto().getCurrentPageNumber());

        // 表示用データ
        getDocFormDto().setListDocForm((List) mapSearchData.get(DocFormCont.RESULT_MAP_KEY_DOC_FORM));

        // 表示サブカテゴリ
        if(isNull(getDocFormDto().getTargetTab())) {
            getDocFormDto().setTargetTab((String)mapSearchData.get(DocFormCont.RESULT_MAP_KEY_DEFAULT_TAB));
	        if (!isNull(getDocFormDto().getSearchedCateId())){
	            getDocFormDto().setListSubCate((List) mapSearchData.get(DocFormCont.RESULT_MAP_KEY_SUB_CATEGORY_LIST));
	        }
        }
        // 検索結果件数をセット
        getDocFormDto().setCount(((Integer) mapSearchData.get(DocFormCont.RESULT_MAP_KEY_KENSU)).intValue());

        // １件目の詳細を表示
        getDocFormDto().setIndexEntityViewDetail(0);
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {
        String searchField = getDocformReqDto().getSearchField();
    	//カテゴリID
        String cateId = getDocformReqDto().getSearchCateId();
        //タイトル
        String searchWord = getDocformReqDto().getSearchWord();
        //タイトル
        String searchTitle = getDocformReqDto().getSearchTitle();
    	//対象タブ
        String targetTab = getDocformReqDto().getTargetTab();

        getDocformReqDto().setSearchField(getDocFormDto().getSearchedField());
        getDocformReqDto().setSearchCateId(getDocFormDto().getSearchedCateId());
        getDocformReqDto().setSearchWord(getDocFormDto().getSearchedWord());
        getDocformReqDto().setSearchTitle(getDocFormDto().getSearchedTitle());
        getDocformReqDto().setTargetTab(getDocFormDto().getTargetTab());
        try {
	        if (isNull(getDocformReqDto().getSearchCateId())) {
	            //タイトルによる検索
	            doSearchByTitle(false);
	        }
	        else {
	            //カテゴリによる検索
	            doSearchByCate();
	        }
        }
        finally {
            getDocformReqDto().setSearchField(searchField);
            getDocformReqDto().setSearchCateId(cateId);
            getDocformReqDto().setSearchWord(searchWord);
            getDocformReqDto().setSearchTitle(searchTitle);
            getDocformReqDto().setTargetTab(targetTab);
        }
        return null;
    }

    /**
     * タブ変更処理
     * @return
     */
    public String changeTab() {
        String searchField = getDocformReqDto().getSearchField();
    	//カテゴリID
        String cateId = getDocformReqDto().getSearchCateId();
        //タイトル
        String searchWord = getDocformReqDto().getSearchWord();
        //タイトル
        String searchTitle = getDocformReqDto().getSearchTitle();
        getDocformReqDto().setSearchField(getDocFormDto().getSearchedField());
        getDocformReqDto().setSearchCateId(getDocFormDto().getSearchedCateId());
        getDocformReqDto().setSearchWord(getDocFormDto().getSearchedWord());
        getDocformReqDto().setSearchTitle(getDocFormDto().getSearchedTitle());
        getDocformReqDto().setCurrentPageNumber(1);
        try {
	        // ロジック【照会文書の取得】を実行
	        Map mapSearchData
	                = getDocformGetDocFormInfoLogic().execute(
	                		getDocformReqDto()
	                        , getBirdDateInfo(), getBirdUserInfo()
	                        , (FullTextSearchDocDto)getFullTextSearchDocDto()
	                        , (FullTextSearchFormDto)getFullTextSearchFormDto());

	        doAfter(mapSearchData);
	        //詳細表示
	        doViewDetail();
        }
        finally {
            getDocformReqDto().setSearchField(searchField);
            getDocformReqDto().setSearchCateId(cateId);
            getDocformReqDto().setSearchWord(searchWord);
            getDocformReqDto().setSearchTitle(searchTitle);
        }
        return null;
    }

    /**
     * 文書選択
     * @return
     */
    public String viewDetail() {
        doViewDetail();
        return null;
    }

    // 詳細表示
    private void doViewDetail() {
        int intSelectIndex = getDocFormDto().getIndexEntityViewDetail();
        UIViewDocFormInfo entity = (UIViewDocFormInfo) getDocFormDto().getListDocForm().get(intSelectIndex);
        getDocFormDto().setEntityViewDetail(entity);
        // 関連ファイルプルダウンの選択位置をクリア
        getDocFormDto().setViewSelectedAttachFileIndex(0);

        //String infoShu = "03";
        String regDate = entity.getRegDate();
        String seq = entity.getSeq();
        Map paramMap = new HashMap();
        paramMap.put("infoShu",entity.getInfoShu());
        paramMap.put("regDate",regDate);
        paramMap.put("seq",seq);

        List shozokuList = getDocShozokuLogic.execute(paramMap);

        String viewShozoku = new String();

        for ( int i = 0; i < shozokuList.size(); i++ ) {
            UIViewShozokuInfo shozoku = (UIViewShozokuInfo)shozokuList.get(i);
            viewShozoku += shozoku.getShozokuName().trim();

            if ( i != shozokuList.size() - 1 ) {
                viewShozoku +=  ",";
            }
        }
        getDocFormDto().setViewShozoku(viewShozoku);
    }
	/**
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 *
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

    //
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * @return documentDownloadDto を戻します。
     */
    public DocFormDto getDocFormDto() {
        return docFormDto;
    }
    /**
     * @param documentDownloadDto documentDownloadDto を設定。
     */
    public void setDocFormDto(DocFormDto docFormDto) {
        this.docFormDto = docFormDto;
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
    public GetDocFormShozokuLogic getGetDocShozokuLogic() {
        return getDocShozokuLogic;
    }

    public void setGetDocShozokuLogic(GetDocFormShozokuLogic getDocShozokuLogic) {
        this.getDocShozokuLogic = getDocShozokuLogic;
    }
    public GetDocFormInfoLogic getDocformGetDocFormInfoLogic() {
        return docformGetDocFormInfoLogic;
    }

    public void setDocformGetDocFormInfoLogic(
            GetDocFormInfoLogic docformGetDocFormInfoLogic) {
        this.docformGetDocFormInfoLogic = docformGetDocFormInfoLogic;
    }

    /**
	 * @return portalSearchInfoDto を戻します。
	 */
	public PortalSearchInfoDto getPortalSearchInfoDto() {
		return portalSearchInfoDto;
	}

	/**
	 * @param portalSearchInfoDto を クラス変数portalSearchInfoDtoへ設定します。
	 */
	public void setPortalSearchInfoDto(PortalSearchInfoDto portalSearchInfoDto) {
		this.portalSearchInfoDto = portalSearchInfoDto;
	}
	/**
	 * @return fullTextSearchDocDto を戻します。
	 */
	public FullTextSearchDto getFullTextSearchDocDto() {
		return fullTextSearchDocDto;
	}

	/**
	 * @param fullTextSearchDocDto を クラス変数fullTextSearchDocDtoへ設定します。
	 */
	public void setFullTextSearchDocDto(FullTextSearchDto fullTextSearchDocDto) {
		this.fullTextSearchDocDto = fullTextSearchDocDto;
	}

	/**
	 * @return fullTextSearchFormDto を戻します。
	 */
	public FullTextSearchDto getFullTextSearchFormDto() {
		return fullTextSearchFormDto;
	}

	/**
	 * @param fullTextSearchFormDto を クラス変数fullTextSearchFormDtoへ設定します。
	 */
	public void setFullTextSearchFormDto(FullTextSearchDto fullTextSearchFormDto) {
		this.fullTextSearchFormDto = fullTextSearchFormDto;
	}
	/**
	 * @return docformReqDto を戻します。
	 */
	public RequestDto getDocformReqDto() {
		return docformReqDto;
	}

	/**
	 * @param docformReqDto を クラス変数docFormReqDtoへ設定します。
	 */
	public void setDocformReqDto(RequestDto docFormReqDto) {
		this.docformReqDto = docFormReqDto;
	}

	/**
	 * @return クラス変数docformInitializeLogic を戻します。
	 */
	public InitializeLogic getDocformInitializeLogic() {
		return docformInitializeLogic;
	}

	/**
	 * @param docformInitializeLogic を クラス変数docformInitializeLogicへ設定します。
	 */
	public void setDocformInitializeLogic(InitializeLogic docformInitializeLogic) {
		this.docformInitializeLogic = docformInitializeLogic;
	}

}