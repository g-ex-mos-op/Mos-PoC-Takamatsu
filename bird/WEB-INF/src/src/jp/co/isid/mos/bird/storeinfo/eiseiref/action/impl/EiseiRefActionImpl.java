/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.FileDownloadActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction;
import jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1st;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.FileDownloadDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.RequestDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.ResultDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.SessionDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storeinfo.eiseiref.logic.SearchLogic;
import jp.co.isid.mos.bird.storeinfo.eiseiref.util.EiseiRefUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 店舗衛生結果アクション
 * 
 * 作成日:2012/12/05
 * @author xkinu
 *
 */
public class EiseiRefActionImpl extends FileDownloadActionImpl implements EiseiRefAction {
	
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = EiseiRefUtil.SCREEN_ID+"A01";
    /* アクションID：店舗選択画面遷移 */
    public static final String callMiseForm_ACTION_ID = EiseiRefUtil.SCREEN_ID+"A02";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = EiseiRefUtil.SCREEN_ID+"A03";
    /* アクションID：ダウンロード店舗衛生監査処理 */
    public static final String downloadBd32setb_ACTION_ID = EiseiRefUtil.SCREEN_ID+"A04";
    /* アクションID：ダウンロード店舗衛生情報処理 */
    public static final String downloadBd33shtb_ACTION_ID = EiseiRefUtil.SCREEN_ID+"A05";
    /* アクションID：ダウンロード衛生指導員訪店報告処理 */
    public static final String downloadBd34vstb_ACTION_ID = EiseiRefUtil.SCREEN_ID+"A06";

    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** 共通DTO【店舗選択】*/
    private MiseSearchDto miseSearchDto;
    /** DTO【Session情報】 */
    private SessionDto sessionDto;
    /** DTO【Request情報】 */
    private RequestDto requestDto;
    /** DTO【検索結果】 */
    private ResultDto resultDto;
    /** DTO【ダウンロード対象情報】 */
    private FileDownloadDto fileDownloadDto;
    /** LOGIC【条件項目情報取得】*/
    private ConditionLogic conditionLogic;
    /** LOGIC【検索結果取得】*/
    private SearchLogic searchLogic;
    
	/* 初期化処理
	 * @see jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //0.DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //1.ウィンドウIDの設定
            requestDto.initialaze(sessionDto);
            //2．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            conditionLogic.execute(
            		getBirdUserInfo(), getBirdDateInfo(), sessionDto, requestDto);
        }
        //２．店舗選択画面から遷移したきた場合。
        else if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【店舗選択】.ウィンドウIDをキーににDTO【Session情報】からDTO【Request情報】を取得しアクション.DTO【Request情報】へ設定します。
                setEiseiRefRequestDto((RequestDto)sessionDto.getRequestDto(getMiseSearchDto().getWindowId()));
                //2．DTO【店舗選択】.ウィンドウIDをキーににDTO【Session情報】からDTO【Result情報】を取得しアクション.DTO【Result情報】へ設定します。
                setEiseiRefResultDto((ResultDto)sessionDto.getResultDto(getMiseSearchDto().getWindowId()));
                
                //3．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //1．DTO【Request情報】.店コードへ共通DTO【店舗選択】.店コードを設定します。
                    requestDto.setMiseCd(selectedCd);
                }
            }
            //4．finally処理で下記の処理を行います。
            finally {
            	//4-1.DTO【店舗選択】.遷移区分を初期値に戻します。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //4-2.DTO【店舗選択】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        /*
         *３．個店ポータル画面から遷移してきた場合
         */
        else if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
        	//１.DTO【BIRD間遷移時使用情報保持】.BIRD内遷移フラグをリセット(faiseを設定)
            getCommonCodeDto().setUseCommonDto(false);
            //２．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            conditionLogic.execute(getBirdUserInfo(), getBirdDateInfo(), sessionDto, requestDto);
            //３．DTO【BIRD間遷移時使用情報保持】の会社コードと店コードが設定されている場合、
            //    BIRD内遷移情報をDTO【Request情報】へ設定します。
            if(!CommonUtil.isNull(getCommonCodeDto().getCompanyCd())
            		&& !CommonUtil.isNull(getCommonCodeDto().getMiseCd())) {
            	//1.DTO【Request情報】.会社コードへDTO【BIRD間遷移時使用情報保持】.会社コードを設定します。
                requestDto.setCompanyCd(getCommonCodeDto().getCompanyCd());
                //2.DTO【Request情報】.対象店舗へDTO【BIRD間遷移時使用情報保持】.店コードを設定します。
                requestDto.setMiseCd(getCommonCodeDto().getMiseCd());
                if(sessionDto.getListNendo().isEmpty()) {
                	requestDto.setNendo("");
                }
                else {
	                //3.DTO【Request情報】.実施年度へ当年度を設定します。(仕様番号:SP030102)
	                requestDto.setNendo(((SelectItem)sessionDto.getListNendo().get(0)).getValue().toString());
                }
            }
        	//４.DTO【BIRD間遷移時使用情報保持】をクリアします。
        	getCommonCodeDto().clear();
            //５.メソッド.検索処理を実行します。
            search(requestDto);
        }
        //４．DTO【検索結果】.該当データ判断値がtrueの場合は、下記の処理を行います。
        if(resultDto.isExistsData()) {
        	//1.DTO【検索結果】.List[[検索結果]]が0件の場合は、下記の処理を実行します。
        	if(resultDto.getListData().isEmpty()) {
        		//0.デフォルトフォーカスタブとしてアクション.DTO【検索結果】.タブキーを取得します。
        		String defaultTab = resultDto.getTabKey();
        		//1.メソッド.検索処理を実行します。
        		search(resultDto);
        		//2.処理0のデフォルトフォーカスタブをアクション.DTO【検索結果】.タブキーへ設定します。
        		resultDto.setTabKey(defaultTab);
        	}
        	//2.DTO【ダウンロード対象情報】へDTO【検索結果】の会社コード、店コード、実施年度を設定します。
        	fileDownloadDto.settingDefaultParam(resultDto);
        }
        //５．Nullをリターンします。
		return null;
	}
	/**
	 * 検索処理
	 * @param reqDto
	 */
	private void search(RequestDto reqDto) {
        //１.DTO【Session情報】.WindowID作成処理を行い、取得した新規WindowIDをDTO【Request情報】.WindowIDへ設定します。
		requestDto.initialaze(sessionDto);
		try {
	        //２．LOGIC【検索結果取得】を実行し、戻り値DTO【検索結果】を取得します。
	        //３．処理２のDTO【検索結果】をアクション.DTO【検索結果】へ設定します。
	    	setEiseiRefResultDto(searchLogic.execute(getBirdUserInfo(), getBirdDateInfo(), reqDto));
		}
		catch(NoResultException noResultEx) {
			//４．処理２でNoResultExceptionが発生した場合は、下記の処理を行います。
			resultDto.reset();
			throw noResultEx;
		}
	}
	/* 実行・再検索処理
	 * @see jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction#search()
	 */
	public String search() {
        search(requestDto);
		return null;
	}

	/* 店検索処理
	 * @see jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction#callMiseForm()
	 */
	public String callMiseForm() {
        int windowId = requestDto.getWindowId();
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase(EiseiRefUtil.VIEW_ID);
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //３．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．共通DTO【店舗選択】ウィンドウIDへDTO【Request情報】ウィンドウIDを設定します。
        getMiseSearchDto().setWindowId(windowId);
        //５．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定します。
        List listCompany = new ArrayList();
        listCompany.add(CommonUtil.COMPANY_CD_MOS);//会社コード:モスフードサービス固定
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //６．DTO【Session情報】.Map[Request情報]へDTO【Request情報】.ウィンドウID をキーにDTO【Request情報】を設定します。
        sessionDto.setRequestDto(windowId, requestDto);
        //７．DTO【Session情報】.Map[検索結果]へDTO【Request情報】.ウィンドウID をキーにDTO【検索結果】を設定します。
        sessionDto.setResultDto(windowId, resultDto);
        //８．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
	}
    /**
     * ダウンロード店舗衛生監査処理
     * @see jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction#downloadBd32setb()
     * @return 画面遷移情報
     */
    public void downloadBd32setb() {
    	fileDownloadDto.setTabKey(Tab1st.TAB_1);
    	super.download();
    }
    /**
     * ダウンロード店舗衛生情報処理
     * @see jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction#downloadBd33shtb()
     * @return 画面遷移情報
     */
    public void downloadBd33shtb() {
    	fileDownloadDto.setTabKey(Tab1st.TAB_2);
    	super.download();
    }
    /**
     * ダウンロード衛生指導員訪店報告処理
     * @see jp.co.isid.mos.bird.storeinfo.eiseiref.action.EiseiRefAction#downloadBd34vstb()
     * @return 画面遷移情報
     */
    public void downloadBd34vstb() {
    	fileDownloadDto.setTabKey(Tab1st.TAB_4);
    	super.download();
    }

	/**
	 * @return クラス変数requestDto を戻します。
	 */
	public RequestDto getEiseiRefRequestDto() {
		return requestDto;
	}

	/**
	 * @param requestDto を クラス変数requestDtoへ設定します。
	 */
	public void setEiseiRefRequestDto(RequestDto requestDto) {
		this.requestDto = requestDto;
	}

	/**
	 * @return クラス変数sessionDto を戻します。
	 */
	public SessionDto getEiseiRefSessionDto() {
		return sessionDto;
	}

	/**
	 * @param sessionDto を クラス変数sessionDtoへ設定します。
	 */
	public void setEiseiRefSessionDto(SessionDto sessionDto) {
		this.sessionDto = sessionDto;
	}

	/**
	 * @return クラス変数miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}

	/**
	 * @param miseSearchDto を クラス変数miseSearchDtoへ設定します。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * DTO【BIRD間遷移時使用情報保持】
     * 個店ポータルからの遷移時保持DTO取得処理
     * @return
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) getS2Container().getComponent(CommonCodeDto.class);
    }

	/**
	 * @return クラス変数pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param dto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto dto) {
		this.pullDownMenuDto = dto;
	}

	/**
	 *  DTO【検索結果】
	 * @return クラス変数eiseirefResultDto を戻します。
	 */
	public ResultDto getEiseiRefResultDto() {
		return resultDto;
	}

	/**
	 *  DTO【検索結果】
	 * @param dto を クラス変数eiseirefResultDtoへ設定します。
	 */
	public void setEiseiRefResultDto(ResultDto dto) {
		this.resultDto = dto;
	}
	/**
	 * @return クラス変数conditionLogic を戻します。
	 */
	public ConditionLogic getEiseiRefConditionLogic() {
		return conditionLogic;
	}

	/**
	 * @param logic を クラス変数conditionLogicへ設定します。
	 */
	public void setEiseiRefConditionLogic(ConditionLogic logic) {
		this.conditionLogic = logic;
	}

	/**
	 * @return クラス変数searchLogic を戻します。
	 */
	public SearchLogic getEiseiRefSearchLogic() {
		return searchLogic;
	}

	/**
	 * @param logic を クラス変数searchLogicへ設定します。
	 */
	public void setEiseiRefSearchLogic(SearchLogic logic) {
		this.searchLogic = logic;
	}

	/**
	 * @return クラス変数fileDownloadDto を戻します。
	 */
	public FileDownloadDto getEiseiRefFileDownloadDto() {
		return fileDownloadDto;
	}

	/**
	 * @param dto を クラス変数fileDownloadDtoへ設定します。
	 */
	public void setEiseiRefFileDownloadDto(FileDownloadDto dto) {
		this.fileDownloadDto = dto;
	}

}
