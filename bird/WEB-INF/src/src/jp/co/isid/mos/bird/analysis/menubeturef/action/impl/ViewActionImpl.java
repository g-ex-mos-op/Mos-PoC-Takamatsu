/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.logic.impl.CsvJikanBetuLogicImpl;
import jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.ConditionLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchAbcLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.impl.CsvAbcLogicImpl;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.impl.CsvShokuzaiMeyasuLogicImpl;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.impl.CsvUriageShubetuLogicImpl;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 【データ分析】メニュー別売上画面
 * 照会アクション
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class ViewActionImpl extends CsvOutput2ActionImpl implements ViewAction {
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = MenuBetuRefUtil.ACTION_ID+"01";
    /* アクションID：店舗選択画面遷移 */
    public static final String callMiseForm_ACTION_ID = MenuBetuRefUtil.ACTION_ID+"02";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = MenuBetuRefUtil.ACTION_ID+"03";
    /* アクションID：ABC分析表CSVダウンロード処理 */
    public static final String downloadAbc_ACTION_ID = MenuBetuRefUtil.ACTION_ID+"04";
    /* アクションID：売上種別CSVダウンロード処理 */
    public static final String downloadShubetu_ACTION_ID = MenuBetuRefUtil.ACTION_ID+"05";
    /* アクションID：メニュー別時間帯別売上CSVダウンロード処理 */
    public static final String downloadJikanbetu_ACTION_ID =MenuBetuRefUtil.ACTION_ID+"06";
    /* アクションID：食材準備目安表CSVダウンロード処理 */
    public static final String downloadMeyasu_ACTION_ID =MenuBetuRefUtil.ACTION_ID+"07";
    /* アクションID：ABC分析表【集約】CSVダウンロード処理 */
    public static final String downloadAbcSMenu_ACTION_ID = MenuBetuRefUtil.ACTION_ID+"08";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** 共通DTO【店舗選択】*/
    private MiseSearchDto miseSearchDto;
    /** DTO【自画面Session】 */
    private SessionDto menuBetuRefSesDto;
    /** DTO【自画面Request】 */
    private RequestDto menuBetuRefReqDto;
    /** DTO【自画面View】 */
    private RequestDto menuBetuRefViewDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic menuBetuRefConditionLogic;
    /** LOGIC【検索結果取得】*/
    private SearchAbcLogic menuBetuRefSearchAbcLogic;

    /** LOGIC【ABC分析表CSVダウンロード】*/
    private CsvAbcLogicImpl menuBetuRefCsvAbcLogic;
    /** LOGIC【売上種別CSVダウンロード】*/
    private CsvUriageShubetuLogicImpl menuBetuRefCsvUriageShubetuLogic;
    /** LOGIC【メニュー別時間帯別売上表CSVダウンロード】*/
    private CsvJikanBetuLogicImpl analysisMenuBetuCsvJikanBetuLogic;
    /** LOGIC【食材準備目安表CSVダウンロード】*/
    private CsvShokuzaiMeyasuLogicImpl menuBetuRefCsvShokuzaiMeyasuLogic;

	/*
	 * 初期化処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //１.DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //２．BIRD情報をDTO【自画面Session】へ設定します。
            getMenuBetuRefSesDto().setBirdDateInfo(getBirdDateInfo());
            getMenuBetuRefSesDto().setBirdUserInfo(getBirdUserInfo());
            //３．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getMenuBetuRefConditionLogic().execute(getMenuBetuRefSesDto(), getMenuBetuRefReqDto());
            //４．自画面DTOの初期化処理を行います。
            // 1.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            // 2.DTO【自画面Request】.WindowIDへ処理2の新規WindowIDを設定します。
            getMenuBetuRefReqDto().initialaze(getMenuBetuRefSesDto());
        				
        }
        //２．店舗選択画面から遷移したきた場合。
        else if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【自画面Request用】．ウィンドウIDへDTO【店舗選択】.ウィンドウIDを設定します。
                setMenuBetuRefReqDto((RequestDto)getMenuBetuRefSesDto().getHoldReqDto(getMiseSearchDto().getWindowId()));
                
                //2．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //2-1．DTO【自画面Request】.表示対象へ共通DTO【店舗選択】.店舗コードを設定します。
                    getMenuBetuRefReqDto().setHyojiTaisho(selectedCd);
                }
                //3.遷移前の画面で、検索済みデータを表示していた場合は、
                //  そのデータをDTO【自画面Request用】へ設定します。
                setView();
            }
            //4．finally処理で下記の処理を行います。
            finally {
            	//4-1.DTO【自画面Session用】．共通画面遷移時のDTO保持オブジェクトへ
            	//    DTO【店舗選択】.ウィンドウIDでnullを設定し、店選択画面遷移時に保持したDTOを削除します。
                getMenuBetuRefSesDto().setHoldReqDto(getMiseSearchDto().getWindowId(), null);
                //4-2．DTO【店舗選択】.遷移区分を初期値に戻します。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //4-3．DTO【店舗選択】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        /*
         *３．個店ポータル画面から遷移してきた場合
         */
        else if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
        	//１.BIRD内遷移フラグをリセット
            getCommonCodeDto().setUseCommonDto(false);
            // BirdUserInfo、BirdDateInfoをDTOへ設定
            getMenuBetuRefSesDto().setBirdDateInfo(getBirdDateInfo());
            getMenuBetuRefSesDto().setBirdUserInfo(getBirdUserInfo());
            //２．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getMenuBetuRefConditionLogic().execute(getMenuBetuRefSesDto(), getMenuBetuRefReqDto());
            //３．自画面DTOの初期化処理を行います。
            // 1.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            // 2.DTO【自画面Request】.WindowIDへ処理2の新規WindowIDを設定します。
            getMenuBetuRefReqDto().initialaze(getMenuBetuRefSesDto());
            //４．DTO【BIRD間遷移時使用情報保持】の会社コードと店コードが設定されている場合、
            //    BIRD内遷移情報をDTO【自画面リクエスト】へ設定します。
            if(!CommonUtil.isNull(getCommonCodeDto().getCompanyCd())
            		&& !CommonUtil.isNull(getCommonCodeDto().getMiseCd())) {
            	//1.DTO【自画面Request】.会社コードへDTO【BIRD間遷移時使用情報保持】.会社コードを設定します。
                getMenuBetuRefReqDto().setCompanyCd(getCommonCodeDto().getCompanyCd());
                //2.DTO【自画面Request】.対象条件へ”MISE”を設定します。
                getMenuBetuRefReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                //3.DTO【自画面Request】.表示対象へDTO【BIRD間遷移時使用情報保持】.店コードを設定します。
                getMenuBetuRefReqDto().setHyojiTaisho(getCommonCodeDto().getMiseCd());
 
            }
        	//５.BIRD内遷移情報をクリアします。
        	getCommonCodeDto().clear();
        	//６．遷移先指定条件とその他の項目はデフォルト値で検索処理を行い、照会画面を表示します。
        	search(getMenuBetuRefReqDto());
        	//７.【自画面共通】表示検索データ設定処理
        	setView();
        }
        //４．処理１以外の場合下記の処理を行います。
        else {
        	//1.【自画面共通】表示検索データ設定処理
        	setView();
        }
        //５．Nullをリターンします。
        return null;
	}
    /**
     * 店舗選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行します上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm() {
        int windowId = getMenuBetuRefReqDto().getWindowId();
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase(MenuBetuRefUtil.VIEW_ID);
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //３．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．共通DTO【店舗選択】ウィンドウIDへDTO【自画面Request用】ウィンドウIDを設定します。
        getMiseSearchDto().setWindowId(windowId);
        //５．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定します。
        List listCompany = new ArrayList();
        listCompany.add(getMenuBetuRefReqDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //６．現ウィンドウID の検索対象条件項目値の保管を行います。
        getMenuBetuRefSesDto().setHoldReqDto(windowId, getMenuBetuRefReqDto());
        //７．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
    }    
	/*
	 * 実行or再検索
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#search()
	 */
	public String search() {
		//１．DTO【自画面Request】.別ウィンドウオープン判断フラグがtrueの場合、下記の処理を行います。
        if(getMenuBetuRefReqDto().isNewWindowFlg()) {
            //1.DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getMenuBetuRefSesDto().createWindowId();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getMenuBetuRefReqDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
            getMenuBetuRefReqDto().setNewWindowFlg(false);
    	}
		search(getMenuBetuRefReqDto());
		return null;
	}
	/**
	 * 表示検索データ設定処理
	 * 
	 * @param logic 検索ロジックオブジェクト
	 * @param sessionDto DTO【自画面セッション】
	 * @param requestDto DTO【自画面リクエスト】
	 * @param viewDto DTO【自画面照会情報保持】
	 */
	private void setView() {
		int windowid = getMenuBetuRefReqDto().getWindowId();
        //１．DTO【自画面Request】へプルダウンリストを設定します。
        getMenuBetuRefReqDto().setPullDownLists();
        //２．セッションに検索データが存在しない場合、検索処理の実行を行います。
		if(getMenuBetuRefSesDto().isMustSearch(windowid)) {
			search(getMenuBetuRefReqDto());
		}
        //３．表示検索データが存在する場合、下記の処理を行います。
		if(getMenuBetuRefSesDto().isExistSearchedData(windowid)) {
			//DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
			getMenuBetuRefViewDto().getSearchedDataDto(windowid);
		}
	}
	/**
	 * 検索処理
	 * 
	 * @param requestDto
	 */
	private void search(RequestDto requestDto) {
		List listSearchData = null;
		try {
			getMenuBetuRefReqDto().setCsvDataKbn("");
			//２．ロジック【メニュー別売上情報の取得】を実行します。戻り値のList[[検索結果]]を取得します。
			listSearchData = (List)getMenuBetuRefSearchAbcLogic().execute(requestDto);
		}
		//３．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行います。
		catch (NoResultException noReEx) {
		    //検索結果該当データなし対象情報を全てNullを設定します。
			requestDto.setNoResultSearchedData();
			throw noReEx;
		}
	    //４．DTO【自画面Session】へ処検索済み条件値を設定します。
		requestDto.setSearchedData(listSearchData);
	}

	/*
	 * ABC分析表【単品】CSVダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#downloadAbc()
	 */
	public void downloadAbc() {
		getMenuBetuRefReqDto().setCsvDataKbn(RowType.CD_MENU);
    	//１．スーパークラス.CSV出力用ロジックへLOGIC【ABC分析表CSVダウンロード】を設定します。
    	download(getMenuBetuRefCsvAbcLogic(), "ABC分析表【単品】");
	}
	/*
	 * ABC分析表【集約】CSVダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#downloadAbcSMenu()
	 */
	public void downloadAbcSMenu() {
		getMenuBetuRefReqDto().setCsvDataKbn(RowType.CD_SUM_MENU);
    	//１．スーパークラス.CSV出力用ロジックへLOGIC【ABC分析表CSVダウンロード】を設定します。
    	download(getMenuBetuRefCsvAbcLogic(), "ABC分析表【集約】");
	}
	/*
	 * 売上種別CSVダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#downloadShubetu()
	 */
	public void downloadShubetu() {
    	//1.【自画面共通】表示検索データ設定処理
    	download(getMenuBetuRefCsvUriageShubetuLogic(), "メニュー別種別個数");
	}
	/*
	 * メニュー別時間帯別売上表CSVダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#downloadJikanbetu()
	 */
	public void downloadJikanbetu() {
       	//1.【自画面共通】表示検索データ設定処理
       	download(getAnalysisMenuBetuCsvJikanBetuLogic(), "メニュー別時間帯別売上表");
	}
	/*
	 * 食材準備目安表CSVダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#downloadMeyasu()
	 */
	public void downloadMeyasu() {
    	//1.【自画面共通】表示検索データ設定処理
    	download(getMenuBetuRefCsvShokuzaiMeyasuLogic(), "食材準備目安表");
	}
	private void download(CsvOutputLogic csvlogic, String csvKbnName) {
		try {
	    	//1.【自画面共通】表示検索データ設定処理
	        super.setCsvOutputLogic(csvlogic);
	        //２．スーパークラス.ダウンロード メイン処理を実行します。
			super.downloadCsv();
		}
		catch (NoResultException noResultEx) {
			getMenuBetuRefReqDto().setNoResultSearchedData();
			throw noResultEx;
		}
        catch (IOException ioEx) {
        	throw new FtlSystemException("メニュー別売上", csvKbnName+"CSVダウンロードアクション", ioEx);
        }
	}
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	/**
	 * @return menuBetuRefReqDto を戻します。
	 */
	public RequestDto getMenuBetuRefReqDto() {
		return menuBetuRefReqDto;
	}

	/**
	 * @param menuBetuRefReqDto を クラス変数menuBetuRefReqDtoへ設定します。
	 */
	public void setMenuBetuRefReqDto(RequestDto menuBetuRefReqDto) {
		this.menuBetuRefReqDto = menuBetuRefReqDto;
	}

	/**
	 * @return menuBetuRefSesDto を戻します。
	 */
	public SessionDto getMenuBetuRefSesDto() {
		return menuBetuRefSesDto;
	}

	/**
	 * @param menuBetuRefSesDto を クラス変数menuBetuRefSesDtoへ設定します。
	 */
	public void setMenuBetuRefSesDto(SessionDto menuBetuRefSesDto) {
		this.menuBetuRefSesDto = menuBetuRefSesDto;
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

	/**
	 * @return menuBetuRefViewDto を戻します。
	 */
	public RequestDto getMenuBetuRefViewDto() {
		return menuBetuRefViewDto;
	}

	/**
	 * @param menuBetuRefViewDto を クラス変数menuBetuRefViewDtoへ設定します。
	 */
	public void setMenuBetuRefViewDto(RequestDto menuBetuRefViewDto) {
		this.menuBetuRefViewDto = menuBetuRefViewDto;
	}
	/**
	 * @return menuBetuRefConditionLogic を戻します。
	 */
	public ConditionLogic getMenuBetuRefConditionLogic() {
		return menuBetuRefConditionLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefConditionLogicへ設定します。
	 */
	public void setMenuBetuRefConditionLogic(ConditionLogic logic) {
		this.menuBetuRefConditionLogic = logic;
	}
	/**
	 * @return menuBetuRefSearchAbcLogic を戻します。
	 */
	public SearchAbcLogic getMenuBetuRefSearchAbcLogic() {
		return menuBetuRefSearchAbcLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefSearchAbcLogicへ設定します。
	 */
	public void setMenuBetuRefSearchAbcLogic(SearchAbcLogic logic) {
		this.menuBetuRefSearchAbcLogic = logic;
	}
	/**
	 * @return miseSearchDto を戻します。
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
	 * @return menuBetuRefCsvShokuzaiMeyasuLogic を戻します。
	 */
	public CsvShokuzaiMeyasuLogicImpl getMenuBetuRefCsvShokuzaiMeyasuLogic() {
		return menuBetuRefCsvShokuzaiMeyasuLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefCsvShokuzaiMeyasuLogicへ設定します。
	 */
	public void setMenuBetuRefCsvShokuzaiMeyasuLogic(
			CsvShokuzaiMeyasuLogicImpl logic) {
		this.menuBetuRefCsvShokuzaiMeyasuLogic = logic;
	}
	/**
	 * @return menuBetuRefCsvUriageShubetuLogic を戻します。
	 */
	public CsvUriageShubetuLogicImpl getMenuBetuRefCsvUriageShubetuLogic() {
		return menuBetuRefCsvUriageShubetuLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefCsvUriageShubetuLogicへ設定します。
	 */
	public void setMenuBetuRefCsvUriageShubetuLogic(
			CsvUriageShubetuLogicImpl logic) {
		this.menuBetuRefCsvUriageShubetuLogic = logic;
	}
	/**
	 * @return menuBetuRefCsvAbcLogic を戻します。
	 */
	public CsvAbcLogicImpl getMenuBetuRefCsvAbcLogic() {
		return menuBetuRefCsvAbcLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefCsvAbcLogicへ設定します。
	 */
	public void setMenuBetuRefCsvAbcLogic(
			CsvAbcLogicImpl logic) {
		this.menuBetuRefCsvAbcLogic = logic;
	}
	/**
	 * @return analysisMenuBetuCsvJikanBetuLogic を戻します。
	 */
	public CsvJikanBetuLogicImpl getAnalysisMenuBetuCsvJikanBetuLogic() {
		return analysisMenuBetuCsvJikanBetuLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefCsvJikanBetuLogicへ設定します。
	 */
	public void setAnalysisMenuBetuCsvJikanBetuLogic(
			CsvJikanBetuLogicImpl logic) {
		this.analysisMenuBetuCsvJikanBetuLogic = logic;
	}

}
