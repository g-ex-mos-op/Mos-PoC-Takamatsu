package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutputActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action.ProceedsManageGepoMeisaiAction;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestJokenDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestResultDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstMiseInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.SearchMeisaiLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 売上金管理月報明細照会アクション
 *
 * @author xjung
 */
public class ProceedsManageGepoMeisaiActionImpl extends CsvOutputActionImpl implements ProceedsManageGepoMeisaiAction {

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

    /** 店選択情報DTO */
    private MiseSearchDto miseSearchDto;

    /** 売上金管理月報・条件部DTO */
    private ProceedsManageGepoDto proceedsManageGepoDto;

    /** 売上金管理月報明細・条件部DTO */
    private MeisaiRequestJokenDto meisaiRequestJokenDto;

    /** 売上金管理月報明細・結果DTO */
    private MeisaiRequestResultDto meisaiRequestResultDto;

    /** 対象店舗情報取得ロジック */ 
    private MstMiseInfoLogic mstMiseInfoLogic;

    /** ユーザー所属会社取得ロジック */
    private GetUserCompanyLogic getUserCompanyLogic;

    /** 明細検索ロジック */ 
    private SearchMeisaiLogic searchMeisaiLogic;
    
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
		            setMeisaiRequestResultDto(
		            		getProceedsManageGepoDto().getCommonTransitionMeisaiResultWindowId(windowsId));

                    // 明細検索
		            
		            if( getMeisaiRequestResultDto() != null
		             &&	getMeisaiRequestResultDto().getCompanyCd() != null
		             && !getMeisaiRequestResultDto().getCompanyCd().equals("")){
                        // 検索フラグ設定
                        getMeisaiRequestJokenDto().setSearchFlg(false);
                        // 明細検索実行(前回の検索を再実行)
                        getSearchMeisaiLogic().execute(
                                getProceedsManageGepoDto()
                              , getMeisaiRequestResultDto()
                              , getMeisaiRequestResultDto()
                        );
                        // 検索フラグ設定
                        getMeisaiRequestJokenDto().setSearchFlg(true);
		            }
				}
				
			}finally{
				
				// 共通画面遷移時保持明細条件 クリア
				getProceedsManageGepoDto().setCommonTransitionMeisaiJokenWindowId(windowsId,null);			
				// 共通画面遷移時保持明細結果 クリア
				getProceedsManageGepoDto().setCommonTransitionMeisaiResultWindowId(windowsId,null);
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
     * 明細
     * @return String 遷移先ビューID
	 */
	public String meisaiSearch() {
		
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
            getSearchMeisaiLogic().execute(
            		getProceedsManageGepoDto()
            	  , getMeisaiRequestJokenDto()
            	  , getMeisaiRequestResultDto()
            		);
            // 検索フラグ設定
            getMeisaiRequestJokenDto().setSearchFlg(true);
        }catch(ApplicationException ex){
        	// エラー格納
        	getMeisaiRequestJokenDto().setAppEx(ex);
		}
        
        // 明細照会画面へ遷移
		return ProceedsConstants.VIEW_ID_MAISAI_SCH;
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
        
        // 結果Dtoを取得
        setMeisaiRequestResultDto(getProceedsManageGepoDto().getCommonTransitionMeisaiResultWindowId(getMeisaiRequestJokenDto().getWindowId()));
        
        try {
            // 会社コードがnullまたは空ではない場合
            if( getMeisaiRequestResultDto() != null
                && !CommonUtil.isNull(getMeisaiRequestResultDto().getCompanyCd())){
                // 検索フラグ設定
                getMeisaiRequestJokenDto().setSearchFlg(false);
                // 明細検索実行
                getSearchMeisaiLogic().execute(
                        getProceedsManageGepoDto()
                      , getMeisaiRequestResultDto()
                      , getMeisaiRequestResultDto()
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
     * 再検索
     * @return String 遷移先ビューID
     */
    public String search() {

        // 検索フラグ設定
        getMeisaiRequestJokenDto().setSearchFlg(false);
        
        // 明細検索実行
        getSearchMeisaiLogic().execute(
        		getProceedsManageGepoDto()
        	  , getMeisaiRequestJokenDto()
        	  , getMeisaiRequestResultDto()
        		);

        // タブNoを設定
        getMeisaiRequestJokenDto().setTabNo(ProceedsConstants.MEISAI_TAB_1);
        // 検索フラグ設定
        getMeisaiRequestJokenDto().setSearchFlg(true);

        // 自画面へ遷移
        return null;
    }


	/**
	 * 店舗検索
	 */
	public String searchMiseCd() {
        // 遷移区分要否フラグ設定
        getMiseSearchDto().setNeedReturnKind(true);
        //遷移元情報を設定
        getMiseSearchDto().setNavigationCase(ProceedsConstants.VIEW_ID_MAISAI_SCH);
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
	 * 売上金管理月報明細照会・結果部DTOクラスを取得する
	 * @return meisaiRequestResultDto 売上金管理月報明細照会・結果部DTOクラス
	 */
	public MeisaiRequestResultDto getMeisaiRequestResultDto() {
		return meisaiRequestResultDto;
	}

	/**
	 * 売上金管理月報明細照会・結果部DTOクラスを設定する
	 * @param meisaiRequestResultDto 売上金管理月報明細照会・結果部DTOクラス
	 */
	public void setMeisaiRequestResultDto(
			MeisaiRequestResultDto meisaiRequestResultDto) {
		this.meisaiRequestResultDto = meisaiRequestResultDto;
	}

	/**
	 * 明細検索ロジックを取得する
	 * @return searchMeisaiLogic 明細検索ロジック
	 */
	public SearchMeisaiLogic getSearchMeisaiLogic() {
		return searchMeisaiLogic;
	}

	/**
	 * 明細検索ロジックを設定する
	 * @param searchMeisaiLogic 明細検索ロジック
	 */
	public void setSearchMeisaiLogic(SearchMeisaiLogic searchMeisaiLogic) {
		this.searchMeisaiLogic = searchMeisaiLogic;
	}
}