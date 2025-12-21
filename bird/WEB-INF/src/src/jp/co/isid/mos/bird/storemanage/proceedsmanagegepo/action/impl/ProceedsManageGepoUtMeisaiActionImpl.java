package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action.ProceedsManageGepoUtMeisaiAction;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestJokenDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.UriageTaxMeisaiRequestResultDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstMiseInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.UriageTaxSearchMeisaiLogic;

/**
 * 売上金管理月報売上と消費税の明細明細照会アクション
 * 2019/07/16
 * @author USI 張
 */

public class ProceedsManageGepoUtMeisaiActionImpl implements ProceedsManageGepoUtMeisaiAction{



    /** 明細アクションID */
    public static final String meisaiSearch_ACTION_ID = "BSM013A10";

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BSM013A11";

    /** 対象店舗プルダウン取得アクションID */
    public static final String searchMiseList_ACTION_ID = "BSM013A12";

    /** 再検索アクションID */
    public static final String search_ACTION_ID = "BSM013A13";

    /** 店選択アクションID */
    public static final String searchMiseCd_ACTION_ID = "BSM013A14";

    /** 明細一括ダウンロードアクションID */
    public static final String downloadCsv_ACTION_ID = "BSM013A15";

    /** 売上消費税明細検索アクションID */
    public static final String uriageTaxMeisaiSearch_ACTION_ID = "BSM013A16";

    /** 店選択情報DTO */
    private MiseSearchDto miseSearchDto;

    /** 売上金管理月報・条件部DTO */
    private ProceedsManageGepoDto proceedsManageGepoDto;

    /** 売上金管理月報明細・条件部DTO */
    private MeisaiRequestJokenDto meisaiRequestJokenDto;

    /** 対象店舗情報取得ロジック */
    private MstMiseInfoLogic mstMiseInfoLogic;

    /** ユーザー所属会社取得ロジック */
    private GetUserCompanyLogic getUserCompanyLogic;

    /** 売上消費税明細検索ロジック */
    private UriageTaxSearchMeisaiLogic uriageTaxSearchMeisaiLogic;

    /** 売上金管理月報売上消費税の明細・結果DTO */
    private UriageTaxMeisaiRequestResultDto uriageTaxMeisaiRequestResultDto;

    /**
     * 初期処理
     */
	public String initialize() {

		// 店選択画面から遷移した場合
		if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT){

			int windowsId = Integer.parseInt(getMiseSearchDto().getReturnWindowId());

			try{
				// 売上金管理月報明細照会・条件DTOをを取得
				setMeisaiRequestJokenDto(
						getProceedsManageGepoDto().getCommonTransitionMeisaiJokenWindowId(windowsId));

				// 店舗が選択されているか選択がキャンセルされた場合
				if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT
				|| getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_CANCEL) {

					// 店が選択された場合
		            if (getMiseSearchDto().isActionFlg()) {
		            	// アクションフラグ設定
		                getMiseSearchDto().setActionFlg(false);
		                // 取得した「店コード」をDTOにセット
		            	getMeisaiRequestJokenDto().setTaishoTenpoCd(getMiseSearchDto().getMiseCd());
		            }

		            // 売上金管理月報明細照会・結果DTO
		            setUriageTaxMeisaiRequestResultDto(
		            		getProceedsManageGepoDto().getCommonTransitionUriageTaxMeisaiResult(windowsId));

		            // 明細検索
		            if( getUriageTaxMeisaiRequestResultDto() != null
		             &&	getUriageTaxMeisaiRequestResultDto().getCompanyCd() != null
		             && !getUriageTaxMeisaiRequestResultDto().getCompanyCd().equals("")){
                        // 検索フラグ設定
                        getMeisaiRequestJokenDto().setSearchFlg(false);
                        // 明細検索実行(前回の検索を再実行)
                        getUriageTaxSearchMeisaiLogic().uriageTaxExecute(
                                getProceedsManageGepoDto()
                              , getUriageTaxMeisaiRequestResultDto()
                              , getUriageTaxMeisaiRequestResultDto()
                        );
                        // 検索フラグ設定
                        getMeisaiRequestJokenDto().setSearchFlg(true);
		            }
				}

			}finally{
				// 共通画面遷移時保持明細条件 クリア
				getProceedsManageGepoDto().setCommonTransitionMeisaiJokenWindowId(windowsId,null);
				// 共通画面遷移時保持明細結果 クリア
				getProceedsManageGepoDto().setCommonTransitionUriageTaxMeisaiResult(windowsId,null);
				// 店舗選択DTO クリア
				getMiseSearchDto().clear();

			}

	    }

		// 会社リストを設定
		getMeisaiRequestJokenDto().setCompanyList(
				getProceedsManageGepoDto().getCompanyListWindowId(getMeisaiRequestJokenDto().getWindowId())
				);
		// 対象店舗情報リストを設定
		getMeisaiRequestJokenDto().setTaishoTenpoList(
				getProceedsManageGepoDto().getTaishoTenpoListWindowId(getMeisaiRequestJokenDto().getWindowId())
				);

		// エラーチェック
		if(getMeisaiRequestJokenDto().getAppEx() != null){
			throw getMeisaiRequestJokenDto().getAppEx();
		}

		// 自画面へ遷移
		return null;
	}


	/**
	* 売上と消費税の明細検索
	* @return String 遷移先ビューID
	*/
    public String uriageTaxMeisaiSearch() {
		if(getProceedsManageGepoDto() == null){
            throw new NotNullException("売上金管理月報DTO");
        }
        // 会社コードを設定
        getMeisaiRequestJokenDto().setCompanyCd(getProceedsManageGepoDto().getOldCompanyCd());
        // 対象年月日を設定
        getMeisaiRequestJokenDto().setTaishoYM(getProceedsManageGepoDto().getOldTaishoYM());
        // 対象店舗コードを設定
        getMeisaiRequestJokenDto().setTaishoTenpoCd(getProceedsManageGepoDto().getOldTaishoTenpoCd());
        // 対象店舗名称を設定
        getMeisaiRequestJokenDto().setTaishoTenpo(getProceedsManageGepoDto().getTaishoTenpo());
        // ウィンドウID生成
        int windowId = getProceedsManageGepoDto().createWindowId();
        // ウィンドウIDを設定
        getMeisaiRequestJokenDto().setWindowId(windowId);

        // 管理会社企業情報リストを設定
        getProceedsManageGepoDto().setCompanyListWindowId(windowId,getProceedsManageGepoDto().getCompanyList());
        // 対象店舗情報リストを設定
        getProceedsManageGepoDto().setTaishoTenpoListWindowId(windowId,getProceedsManageGepoDto().getTaishoTenpoList());

        // タブNoを設定
        getMeisaiRequestJokenDto().setTabNo(ProceedsConstants.MEISAI_TAB_1);

        // 明細用対象年月日を設定
        getProceedsManageGepoDto().setMeisaiTaishoYMList(
        		delYearMonthList(getProceedsManageGepoDto().getTaishoYMList()
        						,ProceedsConstants.MEISAI_MIN_MONTH
        		)
        );

        try{
            // 検索フラグ設定
            getMeisaiRequestJokenDto().setSearchFlg(false);
            // 明細検索実行
            getUriageTaxSearchMeisaiLogic().uriageTaxExecute(
            		getProceedsManageGepoDto()
            	  , getMeisaiRequestJokenDto()
            	  , getUriageTaxMeisaiRequestResultDto()
            		);
            // 検索フラグ設定
            getMeisaiRequestJokenDto().setSearchFlg(true);
        }catch(ApplicationException ex){
        	// エラー格納
        	getMeisaiRequestJokenDto().setAppEx(ex);
		}
        System.out.println(getMeisaiRequestJokenDto().getSearchFlg());
        // 明細照会画面へ遷移
		return ProceedsConstants.VIEW_ID_UTMAISAI_SCH;

	}


    /**
	 * 対象店舗プルダウン取得
	 */
	public String searchMiseList() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();

        // 日付関連情報
        BirdDateInfo birdDateInfo =
            (BirdDateInfo) container.getComponent(BirdDateInfo.class);

		// 対象店舗情報リストにnullを設定
		getProceedsManageGepoDto().setTaishoTenpoListWindowId(getMeisaiRequestJokenDto().getWindowId(),null);
        getMeisaiRequestJokenDto().setTaishoTenpoList(null);

        try {
            // 会社コードがnullまたは空ではない場合
            if( getUriageTaxMeisaiRequestResultDto() != null
                && !CommonUtil.isNull(getUriageTaxMeisaiRequestResultDto().getCompanyCd())){
                // 検索フラグ設定
                getMeisaiRequestJokenDto().setSearchFlg(false);
                // 明細検索実行
                getUriageTaxSearchMeisaiLogic().uriageTaxExecute(
                        getProceedsManageGepoDto()
                      , getUriageTaxMeisaiRequestResultDto()
                      , getUriageTaxMeisaiRequestResultDto()
                );
                // 検索フラグ設定
                getMeisaiRequestJokenDto().setSearchFlg(true);
            }
        } finally {
        	List taishoTenpoList = getMstMiseInfoLogic().execute(
                                    getMeisaiRequestJokenDto().getCompanyCd()
                                    , getProceedsManageGepoDto().getOnerCd()
                                    , birdDateInfo.getAppDate()
                                    );

            // 対象店舗プルダウンの先頭行へ『全店』を追加する。
            MstMiseInfo entity = new MstMiseInfo();
            entity.setMiseCd(ProceedsConstants.ZENTEN_CD);
            entity.setMiseNameKj(ProceedsConstants.ZENTEN_NAME);
            taishoTenpoList.add(0, entity);

            // 対象店舗情報リストを設定
            getProceedsManageGepoDto().setTaishoTenpoListWindowId(
                    getMeisaiRequestJokenDto().getWindowId()
                    // 対象店舗を取得
                  , taishoTenpoList
            );
            getMeisaiRequestJokenDto().setTaishoTenpoList(taishoTenpoList);
        }
        // 自画面へ遷移
        return null;
	}

		/**
		 * 指定月以降年月リスト作成処理
		 * @param yyyyMMList 年月リスト
		 * @param date 指定月
		 * @return List 指定月以降を格納したリスト
		 */
		private List delYearMonthList(List yyyyMMList,String date) {
	        // 格納用年月リスト
	        List delYearMonthList = new ArrayList();

	        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatter.PATTERN_MONTH);

	        // 指定月以降を格納
	        try {
	        	for(int i = 0; i < yyyyMMList.size() ; i++){
	            	Date yyyyMM = sdf.parse( (String)( (SelectItem)(yyyyMMList.get(i)) ).getValue() );
	            	if(yyyyMM.before(sdf.parse(date))){
	            		// 何もしない
	            	}else{
	            		delYearMonthList.add(yyyyMMList.get(i));
	            	}
	        	}
	        } catch (ParseException e) {
	            // 必要なし
	        }

	    	return delYearMonthList;
	    }

		/**
		 * 店舗検索
		 */
		public String searchMiseCd() {
	        // 遷移区分要否フラグ設定
	        getMiseSearchDto().setNeedReturnKind(true);
	        //遷移元情報を設定
	        getMiseSearchDto().setNavigationCase(ProceedsConstants.VIEW_ID_UTMAISAI_SCH);
	        //初期化
	        getMiseSearchDto().setInitialFlag(true);

	        //遷移元ウィンドウIDを設定
	        getMiseSearchDto().setReturnWindowId(
	            Integer.toString(getMeisaiRequestJokenDto().getWindowId()));

	        // 会社コードリストをセット
	        List listCompany = new ArrayList();
	        listCompany.add(getMeisaiRequestJokenDto().getCompanyCd());
	        getMiseSearchDto().setRCompanyCdList(listCompany);

	        // 共通画面遷移時保持明細条件を設定
	        getProceedsManageGepoDto().setCommonTransitionMeisaiJokenWindowId(
	        		getMeisaiRequestJokenDto().getWindowId(),getMeisaiRequestJokenDto());

	        //店検索画面へ遷移
	        return ProceedsConstants.VIEW_ID_MISE_SCH;
		}

		/**
		 * 売上と消費税の明細検索ロジックを取得する
		 * @return uriageTaxSearchMeisaiLogic 売上と消費税の明細検索ロジック
		 */
		public UriageTaxSearchMeisaiLogic getUriageTaxSearchMeisaiLogic() {
			return uriageTaxSearchMeisaiLogic;
		}

		/**
		 * 売上と消費税の明細検索ロジックを設定する
		 * @param uriageTaxSearchMeisaiLogic 売上と消費税の明細検索ロジック
		 */
		public void setUriageTaxSearchMeisaiLogic(UriageTaxSearchMeisaiLogic uriageTaxSearchMeisaiLogic) {
			this.uriageTaxSearchMeisaiLogic = uriageTaxSearchMeisaiLogic;
		}

		/**
		 * 売上金管理月報売上と消費税の明細照会・結果部DTOクラスを取得する
		 * @return uriageTaxMeisaiRequestResultDto 売上金管理月報売上と消費税の明細照会・結果部DTOクラス
		 */
		public UriageTaxMeisaiRequestResultDto getUriageTaxMeisaiRequestResultDto() {
			return uriageTaxMeisaiRequestResultDto;
		}

		/**
		 * 売上金管理月報売上と消費税の明細照会・結果部DTOクラスを設定する
		 * @param uriageTaxMeisaiRequestResultDto 売上金管理月報売上と消費税の明細照会・結果部DTOクラス
		 */
		public void setUriageTaxMeisaiRequestResultDto(UriageTaxMeisaiRequestResultDto uriageTaxMeisaiRequestResultDto) {
			this.uriageTaxMeisaiRequestResultDto = uriageTaxMeisaiRequestResultDto;
		}

		/**
	     * 店検索情報DTOを取得する
	     * @return 店検索情報DTO
	     */
	    public MiseSearchDto getMiseSearchDto() {
	        return miseSearchDto;
	    }

	    /**
	     * 店検索情報DTOを設定する
	     * @param miseSearchDto 店検索情報DTO
	     */
	    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
	        this.miseSearchDto = miseSearchDto;
	    }

	    /**
	     * 売上金管理月報・条件部DTOを取得する
	     * @return 売上金管理月報・条件部DTO
	     */
	    public ProceedsManageGepoDto getProceedsManageGepoDto() {
	        return proceedsManageGepoDto;
	    }

	    /**
	     * 売上金管理月報・条件部DTOを設定する
	     * @param proceedsManageGepoDto 売上金管理月報・条件部DTO
	     */
	    public void setProceedsManageGepoDto(ProceedsManageGepoDto proceedsManageGepoDto) {
	        this.proceedsManageGepoDto = proceedsManageGepoDto;
	    }

	    /**
	     * 対象店舗情報取得ロジックを取得する
	     * @return 対象店舗情報取得ロジック
	     */
	    public MstMiseInfoLogic getMstMiseInfoLogic() {
	        return mstMiseInfoLogic;
	    }

	    /**
	     * 対象店舗情報取得ロジックを設定する
	     * @param mstMiseInfoLogic 対象店舗情報取得ロジック
	     */
	    public void setMstMiseInfoLogic(MstMiseInfoLogic mstMiseInfoLogic) {
	        this.mstMiseInfoLogic = mstMiseInfoLogic;
	    }

	    public GetUserCompanyLogic getGetUserCompanyLogic() {
	        return getUserCompanyLogic;
	    }

	    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
	        this.getUserCompanyLogic = getUserCompanyLogic;
	    }

		/**
		 * 売上金管理月報明細照会・条件部DTOを取得する
		 * @return meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTO
		 */
		public MeisaiRequestJokenDto getMeisaiRequestJokenDto() {
			return meisaiRequestJokenDto;
		}

		/**
		 * 売上金管理月報明細照会・条件部DTOを設定する
		 * @param meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTO
		 */
		public void setMeisaiRequestJokenDto(MeisaiRequestJokenDto meisaiRequestJokenDto) {
			this.meisaiRequestJokenDto = meisaiRequestJokenDto;
		}
		 /**
	     * 売上と消費税の再検索
	     * @return String 遷移先ビューID
	     */
	     public String uriageTaxResearch() {

	        // 検索フラグ設定
	        getMeisaiRequestJokenDto().setSearchFlg(false);

	        // 売上と消費税の明細検索実行
	        getUriageTaxSearchMeisaiLogic().uriageTaxExecute(
	        		getProceedsManageGepoDto()
	        	  , getMeisaiRequestJokenDto()
	        	  , getUriageTaxMeisaiRequestResultDto()
	        		);
	        // 検索フラグ設定
	        getMeisaiRequestJokenDto().setSearchFlg(true);
	        // タブNoを設定
	        getMeisaiRequestJokenDto().setTabNo(ProceedsConstants.MEISAI_TAB_1);
	        // 検索フラグ設定
	        getMeisaiRequestJokenDto().setSearchFlg(true);
	        System.out.println(getMeisaiRequestJokenDto().getSearchFlg());
	        // 自画面へ遷移
	        return null;
	    }
}
