/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TenpoShubetu;
import jp.co.isid.mos.bird.analysis.common.menubetu.logic.impl.CsvJikanBetuLogicImpl;
import jp.co.isid.mos.bird.analysis.jikanbeturef.action.ViewAction;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.CsvDataKbn;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.ConditionLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchWeekLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchWeekShubetuLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl.CsvKikanShubetuLogicImpl;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl.CsvSuiiNipoLogicImpl;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl.CsvWeekLogicImpl;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl.CsvWeekShubetuLogicImpl;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
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
    public static final String initialize_ACTION_ID = JikanBetuRefUtil.ACTION_ID+"01";
    /* アクションID：店舗選択画面遷移 */
    public static final String callMiseForm_ACTION_ID = JikanBetuRefUtil.ACTION_ID+"02";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID = JikanBetuRefUtil.ACTION_ID+"03";
    /* アクションID：曜日別CSVダウンロード処理 */
    public static final String downloadWeek_ACTION_ID = JikanBetuRefUtil.ACTION_ID+"04";
    /* アクションID：日別推移CSVダウンロード処理 */
    public static final String downloadSuiiNipo_ACTION_ID =JikanBetuRefUtil.ACTION_ID+"05";
    /* アクションID：メニュー別時間帯別売上CSVダウンロード処理 */
    public static final String downloadJikanbetu_ACTION_ID =JikanBetuRefUtil.ACTION_ID+"06";
    /* アクションID：売上種別CSVダウンロード処理 */
    public static final String downloadShubetu_ACTION_ID = JikanBetuRefUtil.ACTION_ID+"07";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** 共通DTO【店舗選択画面】*/
    private MiseSearchDto miseSearchDto;
    /** DTO【自画面Session】 */
    private SessionDto jikanBetuRefSesDto;
    /** DTO【自画面Request】 */
    private RequestDto jikanBetuRefReqDto;
    /** DTO【自画面View】 */
    private RequestDto jikanBetuRefViewDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic jikanBetuRefConditionLogic;
    /** LOGIC【曜日別検索結果取得】*/
    private SearchWeekLogic jikanBetuRefSearchWeekLogic;
    /** LOGIC【曜日別種別売上検索結果取得】*/
    private SearchWeekShubetuLogic jikanBetuRefSearchWeekShubetuLogic;
    /** LOGIC【曜日別CSVダウンロード】*/
    private CsvWeekLogicImpl jikanBetuRefCsvWeekLogic;
    /** LOGIC【日別推移CSVダウンロード】*/
    private CsvSuiiNipoLogicImpl jikanBetuRefCsvSuiiNipoLogic;
    /** LOGIC【メニュー別時間帯別売上表CSVダウンロード】*/
    private CsvJikanBetuLogicImpl analysisMenuBetuCsvJikanBetuLogic;
    /** LOGIC【売上種別CSVダウンロード】*/
    private CsvWeekShubetuLogicImpl jikanBetuRefCsvWeekShubetuLogic;
    /** LOGIC【売上種別期間指定CSVダウンロード】*/
    private CsvKikanShubetuLogicImpl jikanBetuRefCsvKikanShubetuLogic;

	/**
	 * @return analysisMenuBetuCsvJikanBetuLogic を戻します。
	 */
	public CsvJikanBetuLogicImpl getAnalysisMenuBetuCsvJikanBetuLogic() {
		return analysisMenuBetuCsvJikanBetuLogic;
	}
	/**
	 * @param analysisMenuBetuCsvJikanBetuLogic を クラス変数analysisMenuBetuCsvJikanBetuLogicへ設定します。
	 */
	public void setAnalysisMenuBetuCsvJikanBetuLogic(
			CsvJikanBetuLogicImpl analysisMenuBetuCsvJikanBetuLogic) {
		this.analysisMenuBetuCsvJikanBetuLogic = analysisMenuBetuCsvJikanBetuLogic;
	}
	/**
	 * @return jikanBetuRefCsvSuiiNipoLogic を戻します。
	 */
	public CsvSuiiNipoLogicImpl getJikanBetuRefCsvSuiiNipoLogic() {
		return jikanBetuRefCsvSuiiNipoLogic;
	}
	/**
	 * @param jikanBetuRefCsvSuiiNipoLogic を クラス変数jikanBetuRefCsvSuiiNipoLogicへ設定します。
	 */
	public void setJikanBetuRefCsvSuiiNipoLogic(
			CsvSuiiNipoLogicImpl jikanBetuRefCsvSuiiNipoLogic) {
		this.jikanBetuRefCsvSuiiNipoLogic = jikanBetuRefCsvSuiiNipoLogic;
	}
	/**
	 * @return jikanBetuRefCsvWeekLogic を戻します。
	 */
	public CsvWeekLogicImpl getJikanBetuRefCsvWeekLogic() {
		return jikanBetuRefCsvWeekLogic;
	}
	/**
	 * @param jikanBetuRefCsvWeekLogic を クラス変数jikanBetuRefCsvWeekLogicへ設定します。
	 */
	public void setJikanBetuRefCsvWeekLogic(
			CsvWeekLogicImpl jikanBetuRefCsvWeekLogic) {
		this.jikanBetuRefCsvWeekLogic = jikanBetuRefCsvWeekLogic;
	}
	/**
	 * @return jikanBetuRefCsvWeekShubetuLogic を戻します。
	 */
	public CsvWeekShubetuLogicImpl getJikanBetuRefCsvWeekShubetuLogic() {
		return jikanBetuRefCsvWeekShubetuLogic;
	}
	/**
	 * @param jikanBetuRefCsvWeekShubetuLogic を クラス変数jikanBetuRefCsvWeekShubetuLogicへ設定します。
	 */
	public void setJikanBetuRefCsvWeekShubetuLogic(
			CsvWeekShubetuLogicImpl jikanBetuRefCsvWeekShubetuLogic) {
		this.jikanBetuRefCsvWeekShubetuLogic = jikanBetuRefCsvWeekShubetuLogic;
	}
    /**
     * @return jikanBetuRefCsvWeekShubetuLogic を戻します。
     */
    public CsvKikanShubetuLogicImpl getJikanBetuRefCsvKikanShubetuLogic() {
        return jikanBetuRefCsvKikanShubetuLogic;
    }
    /**
     * @param jikanBetuRefCsvWeekShubetuLogic を クラス変数jikanBetuRefCsvWeekShubetuLogicへ設定します。
     */
    public void setJikanBetuRefCsvKikanShubetuLogic(
            CsvKikanShubetuLogicImpl jikanBetuRefCsvKikanShubetuLogic) {
        this.jikanBetuRefCsvKikanShubetuLogic = jikanBetuRefCsvKikanShubetuLogic;
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.action.ViewAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //１.DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //２．BIRD情報をDTO【自画面Session】へ設定します。
            getJikanBetuRefSesDto().setBirdDateInfo(getBirdDateInfo());
            getJikanBetuRefSesDto().setBirdUserInfo(getBirdUserInfo());
            //３．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getJikanBetuRefConditionLogic().execute(getJikanBetuRefSesDto(), getJikanBetuRefReqDto());
            //４．自画面DTOの初期化処理を行います。
            // 1.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            // 2.DTO【自画面Request】.WindowIDへ処理2の新規WindowIDを設定します。
            getJikanBetuRefReqDto().initialaze(getJikanBetuRefSesDto());
        				
        }
        //２．店舗選択画面から遷移したきた場合。
        else if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【自画面Request用】．ウィンドウIDへDTO【店舗選択】.ウィンドウIDを設定します。
                setJikanBetuRefReqDto((RequestDto)getJikanBetuRefSesDto().getHoldReqDto(getMiseSearchDto().getWindowId()));
                
                //2．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //2-1．DTO【自画面Request】.表示対象へ共通DTO【店舗選択】.店舗コードを設定します。
                    getJikanBetuRefReqDto().setHyojiTaisho(selectedCd);
                }
                //3.遷移前の画面で、検索済みデータを表示していた場合は、
                //  そのデータをDTO【自画面Request用】へ設定します。
                setView();
            }
            //4．finally処理で下記の処理を行います。
            finally {
            	//4-1.DTO【自画面Session用】．共通画面遷移時のDTO保持オブジェクトへ
            	//    DTO【店舗選択】.ウィンドウIDでnullを設定し、店選択画面遷移時に保持したDTOを削除します。
                getJikanBetuRefSesDto().setHoldReqDto(getMiseSearchDto().getWindowId(), null);
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
            //２．BIRD情報をDTO【自画面Session】へ設定します。
            getJikanBetuRefSesDto().setBirdDateInfo(getBirdDateInfo());
            getJikanBetuRefSesDto().setBirdUserInfo(getBirdUserInfo());
            //２．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getJikanBetuRefConditionLogic().execute(getJikanBetuRefSesDto(), getJikanBetuRefReqDto());
            //３．自画面DTOの初期化処理を行います。
            // 1.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定します。
            // 2.DTO【自画面Request】.WindowIDへ処理2の新規WindowIDを設定します。
            getJikanBetuRefReqDto().initialaze(getJikanBetuRefSesDto());
            //４．DTO【BIRD間遷移時使用情報保持】の会社コードと店コードが設定されている場合、
            //    BIRD内遷移情報をDTO【自画面リクエスト】へ設定します。
            if(!CommonUtil.isNull(getCommonCodeDto().getCompanyCd())
            		&& !CommonUtil.isNull(getCommonCodeDto().getMiseCd())) {
            	//1.DTO【自画面Request】.会社コードへDTO【BIRD間遷移時使用情報保持】.会社コードを設定します。
                getJikanBetuRefReqDto().setCompanyCd(getCommonCodeDto().getCompanyCd());
                //2.DTO【自画面Request】.対象条件へ”MISE”を設定します。
                getJikanBetuRefReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                //3.DTO【自画面Request】.表示対象へDTO【BIRD間遷移時使用情報保持】.店コードを設定します。
                getJikanBetuRefReqDto().setHyojiTaisho(getCommonCodeDto().getMiseCd());
 
            }
        	//５.BIRD内遷移情報をクリアします。
        	getCommonCodeDto().clear();
        	//６．遷移先指定条件とその他の項目はデフォルト値で検索処理を行い、照会画面を表示します。
        	search(getJikanBetuRefReqDto());
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
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm() {
        int windowId = getJikanBetuRefReqDto().getWindowId();
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase(JikanBetuRefUtil.VIEW_ID);
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //３．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．共通DTO【店舗選択】ウィンドウIDへDTO【自画面Request用】ウィンドウIDを設定します。
        getMiseSearchDto().setWindowId(windowId);
        //５．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定します。
        List listCompany = new ArrayList();
        listCompany.add(getJikanBetuRefReqDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //６．現ウィンドウID の検索対象条件項目値の保管を行う。
        getJikanBetuRefSesDto().setHoldReqDto(windowId, getJikanBetuRefReqDto());
        //７．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
    }    
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.action.ViewAction#search()
	 */
	public String search() {
		//１．DTO【自画面Request】.別ウィンドウオープン判断フラグがtrueの場合、下記の処理を行います。
        if(getJikanBetuRefReqDto().isNewWindowFlg()) {
            //1.DTO【自画面Session】.ウィンドウID更新処理で新しいWindowIDを生成します。
            int windowId = getJikanBetuRefSesDto().createWindowId();
            //2.DTO【自画面Request】.ウィンドウIDへ処理1で生成した新しいWindowIDを設定します。
            getJikanBetuRefReqDto().setWindowId(windowId);
            //3.DTO【自画面Request】.別ウィンドウオープン判断フラグにfalseを設定します。
            getJikanBetuRefReqDto().setNewWindowFlg(false);
    	}
		search(getJikanBetuRefReqDto());
		//２．Nullをリターンします。
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
		int windowid = getJikanBetuRefReqDto().getWindowId();
        //１.DTO【自画面Request】へプルダウンリストを設定します。
        getJikanBetuRefReqDto().setPullDownLists();
        //２．セッションに検索データが存在しない場合、検索処理の実行を行います。
		if(getJikanBetuRefSesDto().isMustSearch(windowid)) {
			search(getJikanBetuRefReqDto());
		}
        //３．表示検索データが存在する場合、下記の処理を行う。
		if(getJikanBetuRefSesDto().isExistSearchedData(windowid)) {
			//DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
			getJikanBetuRefViewDto().getSearchedDataDto(windowid);
		}
	}
	/**
	 * 検索処理
	 * 
	 * @param logic
	 * @param sessionDto
	 * @param requestDto
	 */
	private void search(RequestDto requestDto) {
		List listSearchData = null;
		try {
			//DTO【自画面Request】.CSVモード判別値へfalseを設定します。
			requestDto.setCsvMode(false);
			//２．ロジック【曜日別情報の取得】を実行します。戻り値のList[[検索結果]]を取得します。
			if(CsvDataKbn.CODE_SHUBETU.equals(requestDto.getDataKbn())) {
				listSearchData = (List)getJikanBetuRefSearchWeekShubetuLogic().execute(requestDto);
			}
			else {
				listSearchData = (List)getJikanBetuRefSearchWeekLogic().execute(requestDto);
			}
		}
		//３．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行う。
		catch (NoResultException noReEx) {
		    //検索結果該当データなし対象情報を全てNullを設定します。
			requestDto.setNoResultSearchedData();
			throw noReEx;
		}
	    //４．DTO【自画面Session】へ処検索済み条件値を設定します。
		requestDto.setSearchedData(listSearchData);
	}
	/*
	 * 曜日別ダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.action.CsvAction#downloadWeek()
	 */
	public void downloadWeek() {
        //１．DTO【自画面Request】.データ区分へ『曜日別』の値を設定します。
		getJikanBetuRefReqDto().setDataKbn(CsvDataKbn.CODE_WEEK);
       	//２.【自画面共通】表示検索データ設定処理
		download(getJikanBetuRefCsvWeekLogic(), "曜日別");
	}
	/*
	 * 日別推移ダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.action.CsvAction#downloadSuiiNipo()
	 */
	public void downloadSuiiNipo() {
		//１．【自画面共通】表示検索データ設定処理
       	download(getJikanBetuRefCsvSuiiNipoLogic(), "日別推移");
	}
	/*
	 * メニュー別時間帯別売上表ダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.action.ViewAction#downloadJikanbetu()
	 */
	public void downloadJikanbetu() {
       	//１．DTO【自画面Request】.店舗種別へ対象条件が『全社』の値を設定します。
		if(UserType.HONBU.equals(getJikanBetuRefReqDto().getUserTypeCd())
				&& (getJikanBetuRefReqDto().getTaishoJoken().equals(TaishoJoken.CODE_ALL) 
                 || getJikanBetuRefReqDto().getTaishoJoken().equals(TaishoJoken.CODE_SIBU))) {
			getJikanBetuRefReqDto().setTenpoShubetu(TenpoShubetu.CODE_ZENNEN);
		}
		else {
			getJikanBetuRefReqDto().setTenpoShubetu(TenpoShubetu.CODE_ALL);
		}
       	//１．DTO【自画面Request】.対象期間へ『月別』の値を設定します。
		getJikanBetuRefReqDto().setTaishoKikan(TaishoKikan.MONTH);
       	//２.【自画面共通】表示検索データ設定処理
       	download(getAnalysisMenuBetuCsvJikanBetuLogic(), "メニュー別");
	}
	/*
	 * 曜日別売上種別ダウンロード処理
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.action.CsvAction#downloadShubetu()
	 */
	public void downloadShubetu() {
       	//１．DTO【自画面Request】.データ区分へ『曜日別』の値を設定します。
		getJikanBetuRefReqDto().setDataKbn(CsvDataKbn.CODE_SHUBETU);
       	//２.【自画面共通】表示検索データ設定処理
       	download(getJikanBetuRefCsvWeekShubetuLogic(), "売上種別");
	}
    /*
     * 曜日別売上種別（期間指定）ダウンロード処理
     *  (非 Javadoc)
     * @see jp.co.isid.mos.bird.analysis.jikanbeturef.action.CsvAction#downloadKikanShubetu()
     */
    public void downloadKikanShubetu() {
        //１．DTO【自画面Request】.データ区分へ『曜日別』の値を設定します。
        getJikanBetuRefReqDto().setDataKbn(CsvDataKbn.CODE_SHUBETU);
        //２.【自画面共通】表示検索データ設定処理
        download(getJikanBetuRefCsvKikanShubetuLogic(), "売上種別");
    }
	/**
	 * CSVダウンロード実行処理
	 * @param csvlogic
	 * @param csvKbnName
	 */
	private void download(CsvOutputLogic csvlogic, String csvKbnName) {
		try {
	    	//1.【自画面共通】表示検索データ設定処理
	        super.setCsvOutputLogic(csvlogic);
			super.downloadCsv();
		}
		catch (NoResultException noResultEx) {
			getJikanBetuRefReqDto().setNoResultSearchedData();
			throw noResultEx;
		}
        catch (IOException ioEx) {
        	throw new FtlSystemException("時間帯別売上", csvKbnName+"CSVダウンロードアクション", ioEx);
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
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 * 
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

	/**
	 * @return jikanBetuRefConditionLogic を戻します。
	 */
	public ConditionLogic getJikanBetuRefConditionLogic() {
		return jikanBetuRefConditionLogic;
	}
	/**
	 * @param logic を クラス変数jikanBetuRefConditionLogicへ設定します。
	 */
	public void setJikanBetuRefConditionLogic(ConditionLogic logic) {
		this.jikanBetuRefConditionLogic = logic;
	}
	/**
	 * @return jikanBetuRefReqDto を戻します。
	 */
	public RequestDto getJikanBetuRefReqDto() {
		return jikanBetuRefReqDto;
	}
	/**
	 * @param jikanBetuRefReqDto を クラス変数jikanBetuRefReqDtoへ設定します。
	 */
	public void setJikanBetuRefReqDto(RequestDto jikanBetuRefReqDto) {
		this.jikanBetuRefReqDto = jikanBetuRefReqDto;
	}
	/**
	 * @return jikanBetuRefSesDto を戻します。
	 */
	public SessionDto getJikanBetuRefSesDto() {
		return jikanBetuRefSesDto;
	}
	/**
	 * @param jikanBetuRefSesDto を クラス変数jikanBetuRefSesDtoへ設定します。
	 */
	public void setJikanBetuRefSesDto(SessionDto jikanBetuRefSesDto) {
		this.jikanBetuRefSesDto = jikanBetuRefSesDto;
	}
	/**
	 * @return jikanBetuRefViewDto を戻します。
	 */
	public RequestDto getJikanBetuRefViewDto() {
		return jikanBetuRefViewDto;
	}
	/**
	 * @param jikanBetuRefViewDto を クラス変数jikanBetuRefViewDtoへ設定します。
	 */
	public void setJikanBetuRefViewDto(RequestDto jikanBetuRefViewDto) {
		this.jikanBetuRefViewDto = jikanBetuRefViewDto;
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
	 * @return jikanBetuRefSearchWeekLogic を戻します。
	 */
	public SearchWeekLogic getJikanBetuRefSearchWeekLogic() {
		return jikanBetuRefSearchWeekLogic;
	}
	/**
	 * @param logic を クラス変数jikanBetuRefSearchWeekLogicへ設定します。
	 */
	public void setJikanBetuRefSearchWeekLogic(SearchWeekLogic logic) {
		this.jikanBetuRefSearchWeekLogic = logic;
	}
	/**
	 * @return jikanBetuRefSearchWeekShubetuLogic を戻します。
	 */
	public SearchWeekShubetuLogic getJikanBetuRefSearchWeekShubetuLogic() {
		return jikanBetuRefSearchWeekShubetuLogic;
	}
	/**
	 * @param logic を クラス変数jikanBetuRefSearchWeekShubetuLogicへ設定します。
	 */
	public void setJikanBetuRefSearchWeekShubetuLogic(SearchWeekShubetuLogic logic) {
		this.jikanBetuRefSearchWeekShubetuLogic = logic;
	}

}
