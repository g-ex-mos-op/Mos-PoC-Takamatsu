/*
 * 作成日:2012/08/09
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.action.impl;

import jp.co.isid.mos.bird.bizreport.urimaintenanceview.action.KaikeiKbnViewAction;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewMeisaiJokenDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewMeisaiResultDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewReqDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.SearchMeisaiLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 売上修正確認詳細 画面アクション
 *
 * @author xkawa
 */
public class KaikeiKbnViewActionImpl implements KaikeiKbnViewAction {

    /** 明細アクションID */
    public static final String meisaiSearch_ACTION_ID = "BSM013A10";	

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BSM013A11";

    /** 再検索アクションID */
    public static final String search_ACTION_ID = "BSM013A12";

    /** 売上修正確認明細画面 */
    protected static final String VIEW_ID = "BBR009V02";
    
    /** 売上修正確認セッションDTO */
    private UriMainteViewSesDto uriMainteViewSesDto;
    /** 売上修正確認リクエストDTO */
    private UriMainteViewReqDto uriMainteViewReqDto;
    
    /** 売上修正確認明細・条件部DTO */
    private UriMainteViewMeisaiJokenDto uriMainteViewMeisaiJokenDto;

    /** 売上修正確認明細・結果DTO */
    private UriMainteViewMeisaiResultDto uriMainteViewMeisaiResultDto;
    
    /** 明細検索ロジック */ 
    private SearchMeisaiLogic searchMeisaiLogic;
    
    /**
     * 初期処理
     */
	public String initialize() {

		// エラーチェック
		if(getUriMainteViewMeisaiJokenDto().getAppEx() != null){
			throw getUriMainteViewMeisaiJokenDto().getAppEx();
		}
		
		// 自画面へ遷移
		return null;
	}

	/**
     * 明細
     * @return String 遷移先ビューID
	 */
	public String view() {
		
        if(getUriMainteViewSesDto() == null){
            throw new NotNullException("売上修正DTO");
        }

        // 会社コードを設定
        getUriMainteViewMeisaiJokenDto().setCompanyCd(getUriMainteViewSesDto().getCompanyCdZen(getUriMainteViewReqDto().getWindowId()));        
        // 対象年月日を設定
        getUriMainteViewMeisaiJokenDto().setSyuseiDate(getUriMainteViewSesDto().getSyuseiDateZen(getUriMainteViewReqDto().getWindowId()));     
        // ウィンドウID生成
        int windowId = getUriMainteViewSesDto().createWindowId();
        // ウィンドウIDを設定
        getUriMainteViewMeisaiJokenDto().setWindowId(windowId);
        
        try{
            // 検索フラグ設定
            getUriMainteViewMeisaiJokenDto().setSearchFlg(false);
            // 明細検索実行
            getSearchMeisaiLogic().execute(
                    getUriMainteViewSesDto()
            	  , getUriMainteViewMeisaiJokenDto()
            	  , getUriMainteViewMeisaiResultDto()
            		);
            // 検索フラグ設定
            getUriMainteViewMeisaiJokenDto().setSearchFlg(true);
        }catch(ApplicationException ex){
        	// エラー格納
        	getUriMainteViewMeisaiJokenDto().setAppEx(ex);
		}
        
        // 明細照会画面へ遷移
		return VIEW_ID;
	}

 
    /**
     * 再検索
     * @return String 遷移先ビューID
     */
    public String search() {

        // 検索フラグ設定
        getUriMainteViewMeisaiJokenDto().setSearchFlg(false);
        
        // 明細検索実行
        getSearchMeisaiLogic().execute(
                getUriMainteViewSesDto()
        	  , getUriMainteViewMeisaiJokenDto()
        	  , getUriMainteViewMeisaiResultDto()
        		);

        // 検索フラグ設定
        getUriMainteViewMeisaiJokenDto().setSearchFlg(true);

        // 自画面へ遷移
        return null;
    }


    /**
     * 売上修正確認セッションDTOを取得する
     * @return 売上修正確認セッションDTO
     */   
    public UriMainteViewSesDto getUriMainteViewSesDto() {
        return uriMainteViewSesDto;
    }

    /**
     * 売上修正確認セッションDTOを設定する
     * @param proceedsManageGepoDto 売上修正確認セッションDTO
     */
    public void setUriMainteViewSesDto(UriMainteViewSesDto uriMainteViewSesDto) {
        this.uriMainteViewSesDto = uriMainteViewSesDto;
    }

	/**
     * 売上修正確認リクエストDTOを戻します。
     * @return uriMainteViewReqDto 売上修正確認リクエストDTO
     */
    public UriMainteViewReqDto getUriMainteViewReqDto() {
        return uriMainteViewReqDto;
    }

    /**
     * 売上修正確認リクエストDTOを設定します。
     * @param uriMainteViewReqDto 売上修正確認リクエストDTO
     */
    public void setUriMainteViewReqDto(UriMainteViewReqDto uriMainteViewReqDto) {
        this.uriMainteViewReqDto = uriMainteViewReqDto;
    }

    /**
	 * 売上修正確認明細・条件部DTOを取得する
	 * @return meisaiRequestJokenDto 売上修正確認明細・条件部DTO
	 */
	public UriMainteViewMeisaiJokenDto getUriMainteViewMeisaiJokenDto() {
		return uriMainteViewMeisaiJokenDto;
	}

	/**
	 * 売上修正確認明細・条件部DTOを設定する
	 * @param meisaiRequestJokenDto 売上修正確認明細・条件部DTO
	 */
	public void setUriMainteViewMeisaiJokenDto(UriMainteViewMeisaiJokenDto meisaiRequestJokenDto) {
		this.uriMainteViewMeisaiJokenDto = meisaiRequestJokenDto;
	}

	/**
	 * 売上修正確認明細・結果部DTOクラスを取得する
	 * @return meisaiRequestResultDto 売上修正確認明細・結果部DTOクラス
	 */
	public UriMainteViewMeisaiResultDto getUriMainteViewMeisaiResultDto() {
		return uriMainteViewMeisaiResultDto;
	}

	/**
	 * 売上修正確認明細・結果部DTOクラスを設定する
	 * @param meisaiRequestResultDto 売上修正確認明細・結果部DTOクラス
	 */
	public void setUriMainteViewMeisaiResultDto(
			UriMainteViewMeisaiResultDto meisaiRequestResultDto) {
		this.uriMainteViewMeisaiResultDto = meisaiRequestResultDto;
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