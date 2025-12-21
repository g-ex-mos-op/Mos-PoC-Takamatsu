package jp.co.isid.mos.bird.bizreport.takuhainiporef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.logic.MstSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiNipoRefSibuAction;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoCommon;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dto.TakuhaiNipoRefResultDto;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.SibuInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * 宅配売上日報の結果画面(支部別)アクション
 *
 * @author xjung
 * @modifier xkinu 2010/01/18 ADD
 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
 *        システムエラーが発生することがなくなる為。
 * @modifier xkinu 2013/01/31 MOSCARD画面追加対応
 * @modifier xkinu 2013/01/31 海外売上集信対応
 */
public class TakuhaiNipoRefSibuActionImpl extends CsvOutput2ActionImpl implements TakuhaiNipoRefSibuAction {

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBR003A11";
    /** アクション【SV検索ボタン】ID */
    public static final String callSvForm_ACTION_ID = "BBR003A12";
    /** 再検索アクションID */
    public static final String reSearch_ACTION_ID = "BBR003A13";

    /** サブタブ変更アクションID */
    public static final String changeSubTab_ACTION_ID = "BBR003A14";
    /** アクションID:アクション【CSVダウンロード(支部別)】 */
    public static final String downloadCsvSibu_ACTION_ID = "BBR003A15";
    /** アクションID:アクション【CSVダウンロード(店舗別)】 */
    public static final String downloadCsvMise_ACTION_ID = "BBR003A16";

    /** 支部リンクアクションID */
    public static final String selectedSibu_ACTION_ID = "BBR003A17";

    /** 営業日報タブリンクアクションID */
    public static final String goEigyoNipo_ACTION_ID = "BBR003A18";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR003A19";
    /** アクション【MOSCARD画面遷移】ID */
    public static final String callMoscard_ACTION_ID  = "BBR003A20";
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR003A41";

    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;
    /** LOGIC【CSVダウンロード(支部別)】 */
    private CsvOutputLogic takuhaiNipoRefSibuCsvOutputLogic;
    /** LOGIC【CSVダウンロード(店舗別)】 */
    private CsvOutputLogic takuhaiNipoRefMiseCsvOutputLogic;

	/** 宅配売上日報 結果情報DTO */
	private TakuhaiNipoRefResultDto takuhaiNipoRefResultDto;

	/** SV選択画面 */
    private SvSearchDto newSvSearchDto;

	/** 支部別宅配売上情報取得ロジック */
	private SibuInfoLogic sibuInfoLogic;

	/** 別ウィンドウフラグ */
	private boolean newWindowFlg;
    /** BIRDログインユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** LOGIC【支部マスタ情報】*/
    private MstSibuInfoLogic mstSibuInfoLogic;

	/**
	 * 初期処理
	 */
	public String initialize() {
    	//１．日報共通DTO【検索条件】.初期アクションフラグがtrueの場合、下記の処理を行います。
        if (searchParamDto.isInitActionFlg()) {
	    	//1.日報共通UTIL【共通処理】.画面表示デフォルトサブタグ区分取得処理を行います。
	        //2.処理1で取得したデフォルトサブタグ区分を日報共通DTO【結果条件】.サブタグ区分へ設定します。
	        resultParamDto.setSubTagKbn(
	        		NipoRefUtil.getDefaultSubTagKbn(resultParamDto.getSubTagKbn()));
	        //3.日報共通DTO【結果条件】.再検索(実行・再検索ボタン名称)制御フラグへ
	        //   日報共通DTO【検索条件】.再検索(実行・再検索ボタン名称)制御フラグ値を設定します。
	        resultParamDto.setResearchFlg(searchParamDto.isResearchFlg());
        	//4.日報共通DTO【検索条件】へ日報共通DTO【結果条件】を設定します。
        	setNipoRefSearchParameterDto(resultParamDto);
	        //5.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。(→処理３の処理を実行させるため）
            takuhaiNipoRefResultDto.setExistDataFlg(true);
	    }
        //２．SV選択画面から遷移してきた場合。2008/12/09追加 **************************/
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
        	//1.Try開始 SV選択画面からの値などを設定します。
        	try {
	            //SV-1．BIRD共通DTO【SV選択画面】.ウィンドウIDを取得します。
	        	int windowId = getNewSvSearchDto().getWindowId();
	        	//SV-2．処理SV-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【検索条件情報】を取得します。
	        	//SV-3．日報共通DTO【検索条件】へ処理SV-2のDTO【検索条件情報】を設定します。
	        	setNipoRefSearchParameterDto(
	        			(NipoRefConditionParameterDto) getNipoRefConditionDto().getSearchParameterDto(windowId));
	        	//SV-4．処理SV-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【結果条件情報】を取得します。
	        	//SV-5．日報共通DTO【結果条件】へ処理SV-4のDTO【結果条件情報】を設定します。
	        	setNipoRefResultParameterDto(
	        			(NipoRefConditionParameterDto) getNipoRefConditionDto().getResultParameterDto(windowId));
	        	//SV-6．処理SV-1のウィンドウIDをキーに日報共通DTO【条件部情報】からDTO【結果情報】を取得します。
	        	//SV-7．日報共通DTO【結果条件】へ処理SV-6のDTO【結果情報】を設定します。
	        	setTakuhaiNipoRefResultDto((TakuhaiNipoRefResultDto)getTakuhaiNipoRefMapResultDto().getResultDto(windowId));
	            //SV-6．SVを選択後遷移してきた場合。
	            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
	                //SV-SELECT-1．日報共通DTO【検索条件】.SVコードへBIRD共通DTO【SV選択画面】.SVコードを設定します。
	                searchParamDto.setSvCd(getNewSvSearchDto().getSvCd());
	            	//SV-SELECT-2．日報共通DTO【結果条件】へ日報共通DTO【条件条件】を設定します。
	            	resultParamDto = searchParamDto;
	            	//SV-SELECT-3．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
	                NipoRefUtil.callScreenInitialize(
	                		getNipoRefConditionDto(), searchParamDto, resultParamDto, false);
	                //SV-SELECT-4.店舗別画面VIEW_IDをリターンします。(屋号別売上店舗別画面へ遷移)
	                return NipoRefConstants.VIEW_ID_T_MISE;
	            }
        	}
        	//2.FINALLYで下記の処理を行います。
        	finally {
                //FINAL-1．BIRD共通DTO【SV選択画面】.遷移区分を初期値に戻す。
                getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
                //FINAL-2．BIRD共通DTO【SV選択画面】.クリア処理を実行します。
                getNewSvSearchDto().clear();
            }
        }
        //３． DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        boolean isDetail = TakuhaiNipoCommon.isDispTypeDetail(resultParamDto.getSubTagKbn());
        List listResult = isDetail?takuhaiNipoRefResultDto.getTypeRstList():takuhaiNipoRefResultDto.getUmuRstList();
        if (takuhaiNipoRefResultDto.isExistDataFlg()
        		&& (listResult == null 	|| listResult.isEmpty()) )
        {
        	//0.日報共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】から処理0の値をキーに日報共通DTO【結果条件】を取得します。
        	NipoRefConditionParameterDto sessionResultParamDto = getTakuhaiNipoRefMapResultDto().getParameterDto(windowId);
        	//2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
        	TakuhaiNipoRefResultDto resultDto = (TakuhaiNipoRefResultDto) getTakuhaiNipoRefMapResultDto().getResultDto(windowId);
        	//3.表示対象の日報共通DTO【結果条件】の条件と処理1の日報共通DTO【結果条件】の条件が一致した場合、
        	if(sessionResultParamDto != null && resultDto != null
        			&& resultParamDto.equalsParams(sessionResultParamDto)
        			&& resultDto.isSearchTypeSibuList())
        	{
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setTakuhaiNipoRefResultDto(resultDto);
            	listResult = isDetail?resultDto.getTypeRstList():resultDto.getUmuRstList();
        		if(listResult == null || listResult.isEmpty()) {
        			this.searchSibuInfo(resultParamDto);
        		}
        	}
        	else {
        		try {
		        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
		            this.searchSibuInfo(resultParamDto);
	    		}
	    		catch (ApplicationException ex) {
	    	        listResult = isDetail?takuhaiNipoRefResultDto.getTypeRstList():takuhaiNipoRefResultDto.getUmuRstList();
	    			if(listResult == null || listResult.isEmpty()) {
	    				//データが無い場合は
	    				takuhaiNipoRefResultDto.setExistDataFlg(false);
	    			}
	    			throw ex;
	    		}
        	}
        }
    	//４．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
        //５．nullをリターンします。(現行画面へ遷移)
		return null;
	}

	/**
	 * 再検索
	 */
	public String reSearch() {
        //１．クラス変数[別ウィンドウフラグ]がtrueの場合、下記の処理を行います。
        if (isNewWindowFlg()) {
            //1.日報共通DTO【条件部情報】.MAXウィンドウIDカウントアップし、MAXウィンドウIDを取得します。
            int newWindowId = getNipoRefConditionDto().updateWindowId();
            //2.日報共通DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.日報共通DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．日報共通DTO【検索条件】.集計区分で『SV指定(担当店一覧)』が選択された場合は、下記の処理を行います。
        if(searchParamDto.isSvFlg()){
        	//SV-SELECT-1．日報共通DTO【結果条件】へ日報共通DTO【条件条件】を設定します。
        	searchParamDto.setTaishoTenpoCd(TaishoTenpo.ALL);
        	resultParamDto = searchParamDto;
        	//SV-SELECT-2．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
            NipoRefUtil.callScreenInitialize(
            		getNipoRefConditionDto(), searchParamDto, resultParamDto, false);
            //SV-SELECT-3.店舗別画面VIEW_IDをリターンします。(店舗別画面へ遷移)
         return  NipoRefConstants.VIEW_ID_T_MISE;
        }
        //３.支部別売上情報を実行します。
        this.searchSibuInfo(searchParamDto);
        //４．日報共通DTO【結果条件】.サブタグ区分へ"売上タブ区分"を設定します。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //５．nullをリターンします。(現行画面へ遷移)
        return null;
	}

    /**
     * SV検索ボタン処理
     *
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        //１．BIRD共通DTO【SV選択画面】.遷移元情報へ支部別画面VIEW_IDを設定します。
        getNewSvSearchDto().setNavigationCase(NipoRefConstants.VIEW_ID_T_SIBU);
        //２．BIRD共通DTO【SV選択画面】.初期化フラグへtrueを設定します。
        getNewSvSearchDto().setInitFlag(true);
        //３．BIRD共通DTO【SV選択画面】.ウィンドウID　へ 日報共通DTO【検索条件】.ウィンドウIDを設定します。
        getNewSvSearchDto().setWindowId(searchParamDto.getWindowId());
        //４．List[[会社]]を作成し日報共通DTO【検索条件】.会社コードを設定します。
        List listCompany = new ArrayList();
        listCompany.add(searchParamDto.getCompanyCd());
        //５．BIRD共通DTO【SV選択画面】.List[[会社]]へ処理４のList[[会社]]を設定します。
        getNewSvSearchDto().setRCompanyCdList(listCompany);
        //６．日報共通DTO【条件部情報】.DTO【検索条件情報】へ日報共通DTO【検索条件】の保管を行う。
        getNipoRefConditionDto().setSearchParameterDto(searchParamDto);
        //７．日報共通DTO【条件部情報】.DTO【検索結果情報】へ日報共通DTO【結果条件】の保管を行う。
        getNipoRefConditionDto().setResultParameterDto(resultParamDto);
        //８．SV選択画面遷移VIEWIDをリターンする。
        return NipoRefConstants.VIEW_ID_SVSEARCH;
    }

    /**
     * サブタブ変更
     * @return String 遷移先ビューID
     */
    public String changeSubTab() {
        //１．nullをリターンします。(現行画面へ遷移)
        return null;
    }

	/**
	 * CSVダウンロード
	 */
	public void downloadCsvSibu() {
        try {
        	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
        	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_ALL);
            //２．LOGIC【CSVダウンロード(支部別)】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getTakuhaiNipoRefSibuCsvOutputLogic());
        	//３．継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        } catch (IOException e) {
        	//４．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
	}
    /**
     * アクション【CSVダウンロード(店舗別)】
     */
    public void downloadCsvMise() {
        try {
        	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
        	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_ALL);
            //２．LOGIC【CSVダウンロード(店舗別)】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getTakuhaiNipoRefMiseCsvOutputLogic());
        	//３．継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        } catch (IOException e) {
        	//５．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
   }

	/**
	 * 支部・種別リンク
	 */
	public String selectedSibu() {
		String linkParams = resultParamDto.getLinkParams();
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, true);
        resultParamDto.setLinkParams(linkParams);
        //２．店舗別結果画面(本部用)VIEW_IDをリターンします。(店舗別結果画面へ遷移)
        return NipoRefConstants.VIEW_ID_T_MISE;
	}

	/**
	 * アクション【営業日報画面遷移】
	 */
	public String goEigyoNipo() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．営業日報・支部別結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_E_SIBU;
	}

	/**
	 * 屋号別売上タブリンク
	 */
	public String goYago() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．屋号別売上日報・支部別結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_SIBU;
	}
	/*
	 * アクション【MOSCARD画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiuNipoRefSibuConfirmAction#callMoscard()
	 */
	public String callMoscard() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //１．MOSCARDはサブ区分を画面間で共有ないため、DTO【結果条件】.サブタブ区分へ初期値としてBizReportConstants.SUB_TAG_0を設定。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //２．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_M_SIBU;
	}
	/*
	 * アクション【ネット注文画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiuNipoRefSibuConfirmAction#callNetorder()
	 */
	public String callNetorder() {
		//１．予算対象店、期別期報　を検索し結果が表示されている状態でネット注文タブを押下した場合、以下のエラーメッセージを表示する
    	//この検索条件ではネット注文はご利用できません。検索条件を変更してください。
    	if (TenpoShubetu.CODE_YOSAN.equals(resultParamDto.getTenpoShubetuCd())) {

			throw new GenericMessageException("この検索条件ではネット注文はご利用できません。検索条件を変更してください。");
		}
		//２．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //３．ネット注文画面支部一覧画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_N_SIBU;
	}
    /**
     * 支部別宅配売上情報を取得する
     *
	 * @modifier xkinu 2010/01/18 ADD
	 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
	 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
	 *        システムエラーが発生することがなくなる為。
     */
    private void searchSibuInfo(NipoRefConditionParameterDto parameterDto) {
        // 宅配売上情報結果削除
        getTakuhaiNipoRefResultDto().clearSibu();
        //リモート制限ユーザログイン時は、下記の確認処理を行います。
        boolean limitFlg = getBirdUserInfo().isLimit();
        if(UserType.isHonbu(getBirdUserInfo().getMstUser().getUserTypeCd())
        		&& limitFlg) {
        	try {
            	//閲覧可能担当支部存在チェックを行います。
            	mstSibuInfoLogic.execute(
            			parameterDto.getCompanyCd()
            			, limitFlg, getBirdUserInfo().getUserID(), "SIBU_CD");
        	}
        	catch (NotExistException notExistEx){
        		throw new NotExistException("担当支部");
        	}
        }

        searchInfo(parameterDto);
    }
	/**
     * 支部別宅配売上情報を取得する
     * @param parameterDto DTO【検索条件】
     * @modifier xkinu 2013/01/31 海外売上集信対応
     */
    private void searchInfo(NipoRefConditionParameterDto parameterDto) {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto, TaishoJoken.CODE_ALL);
    	//２．Try開始 検索処理を行います。
	    try {
	        //1.LOGIC【支部売上情報取得】を実行し、Map[売上情報取得]を取得します。
	        Map resultMap = getSibuInfoLogic().execute(parameterDto, false);
	        //2.Map[売上情報取得]に対象店舗数が存在する場合、
	        //Map[売上情報取得]の対象店舗数の値をDTO【結果情報】.対象店舗数へ設定します。
	        if (resultMap.containsKey(TakuhaiNipoConstants.MAP_TENPO_COUNT)) {
	        	String scnt = (String) resultMap.get(TakuhaiNipoConstants.MAP_TENPO_COUNT);
	            takuhaiNipoRefResultDto.setTenpoCount(Integer.valueOf(scnt).intValue());
	        }
	        //3.Map[売上情報取得]の売上リストを変数List[[売上]]とします。
        	List listResult = (List) resultMap.get(TakuhaiNipoConstants.MAP_RST_LIST);
        	//4.DTO【検索条件】.サブタブ区分が”詳細別(売上)”か”詳細別(客数)”の場合は、
        	if(TakuhaiNipoCommon.isDispTypeDetail(parameterDto.getSubTagKbn())) {
        		//DTO【結果情報】.支部(タイプ別)リストへ処理2の変数List[[売上]]を設定します。
        		takuhaiNipoRefResultDto.setTypeRstList(listResult);
	        }
        	//5.DTO【検索条件】.サブタブ区分が”屋号別(売上)”か”屋号別(客数)”の場合は、
        	else {
        		///DTO【結果情報】.支部(有無別)リストへ処理2の変数List[[売上]]を設定します。
        		takuhaiNipoRefResultDto.setUmuRstList(listResult);
	        }
	        //6.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
			takuhaiNipoRefResultDto.setExistDataFlg(true);
	        //7.DTO【結果情報】.検索タイプへTaishoJoken.CODE_SIBUを設定する。
	        takuhaiNipoRefResultDto.setSearchListType(TaishoJoken.CODE_SIBU);
	        //8.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        takuhaiNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
	        //9.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //10.DTO【Map結果情報】.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            getTakuhaiNipoRefMapResultDto().setParameterDto(parameterDto);
	    	//11.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
	        //12.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	        searchParamDto.setResearchFlg(true);

		}
	    //３．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException e){
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //EX-2.DTO【結果情報】.クリア処理を行います。
		    takuhaiNipoRefResultDto.clearSibu();
	    	//EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
	    	//EX-4.キャッチしたNoResultExceptionを発生させます。
	        //該当データがありません。のメッセージを表示します。
			throw e;
	    }
		//４．BatchProcessingExceptionをキャッチし、下記の処理を行います。
		catch (BatchProcessingException batchEx) {
	        //EX-1.DTO【結果情報】.クリア処理を行います。
		    takuhaiNipoRefResultDto.clearSibu();
	    	//EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
	    	//EX-3.キャッチしたBatchProcessingExceptionを発生させます。
	        throw batchEx;
		}
	    //５．処理終了
    }
	/**
	 * 宅配売上情報結果DTOを取得する
	 * @return TakuhaiNipoRefResultDto 宅配売上情報結果DTO
	 */
	public TakuhaiNipoRefResultDto getTakuhaiNipoRefResultDto() {
		return takuhaiNipoRefResultDto;
	}

	/**
	 * 宅配売上情報結果DTOを設定する
	 * @param takuhaiNipoRefResultDto 宅配売上情報結果DTO
	 */
	public void setTakuhaiNipoRefResultDto(TakuhaiNipoRefResultDto takuhaiNipoRefResultDto) {
		this.takuhaiNipoRefResultDto = takuhaiNipoRefResultDto;
	}

    /**
     * 日報共通条件部情報DTOを取得する
     * @return NipoRefSearchDto 日報共通条件部情報DTO
     */
    public NipoRefConditionDto getNipoRefConditionDto() {
        return nipoRefConditionDto;
    }

    /**
     * 日報共通条件部情報DTOを設定する
     * @param nipoRefConditionDto 日報共通条件部情報DTO
     */
    public void setNipoRefConditionDto(NipoRefConditionDto nipoRefConditionDto) {
        this.nipoRefConditionDto = nipoRefConditionDto;
    }

	/**
	 * 支部別宅配売上情報取得ロジックを取得する
	 * @return SibuInfoLogic 支部別宅配売上情報取得ロジック
	 */
	public SibuInfoLogic getSibuInfoLogic() {
		return sibuInfoLogic;
	}

	/**
	 * 支部別宅配売上情報取得ロジックを設定する
	 * @param sibuInfoLogic 支部別宅配売上情報取得ロジック
	 */
	public void setSibuInfoLogic(SibuInfoLogic sibuInfoLogic) {
		this.sibuInfoLogic = sibuInfoLogic;
	}
	/**
	 * 別ウィンドウフラグを取得する
	 * @return boolean 別ウィンドウフラグ
	 */
    public boolean isNewWindowFlg() {
        return newWindowFlg;
    }

	/**
	 * 別ウィンドウフラグを設定する
	 * @param newWindowFlg 別ウィンドウフラグ
	 */
    public void setNewWindowFlg(boolean newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }

    public SvSearchDto getNewSvSearchDto() {
        return newSvSearchDto;
    }

    public void setNewSvSearchDto(SvSearchDto newSvSearchDto) {
        this.newSvSearchDto = newSvSearchDto;
    }

	/**
	 * @return クラス変数birdUserInfo を戻します。
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
	 * @return クラス変数mstSibuInfoLogic を戻します。
	 */
	public MstSibuInfoLogic getMstSibuInfoLogic() {
		return mstSibuInfoLogic;
	}

	/**
	 * @param mstSibuInfoLogic を クラス変数mstSibuInfoLogicへ設定します。
	 */
	public void setMstSibuInfoLogic(MstSibuInfoLogic mstSibuInfoLogic) {
		this.mstSibuInfoLogic = mstSibuInfoLogic;
	}

	/**
	 * @return クラス変数takuhaiNipoMiseCsvOutputLogic を戻します。
	 */
	public CsvOutputLogic getTakuhaiNipoRefMiseCsvOutputLogic() {
		return takuhaiNipoRefMiseCsvOutputLogic;
	}

	/**
	 * @param takuhaiNipoMiseCsvOutputLogic を クラス変数csvOutputLogicへ設定します。
	 */
	public void setTakuhaiNipoRefMiseCsvOutputLogic(
			CsvOutputLogic csvOutputLogic) {
		this.takuhaiNipoRefMiseCsvOutputLogic = csvOutputLogic;
	}

	/**
	 * @return クラス変数takuhaiNipoSibuCsvOutputLogic を戻します。
	 */
	public CsvOutputLogic getTakuhaiNipoRefSibuCsvOutputLogic() {
		return takuhaiNipoRefSibuCsvOutputLogic;
	}

	/**
	 * @param takuhaiNipoSibuCsvOutputLogic を クラス変数csvOutputLogicへ設定します。
	 */
	public void setTakuhaiNipoRefSibuCsvOutputLogic(
			CsvOutputLogic csvOutputLogic) {
		this.takuhaiNipoRefSibuCsvOutputLogic = csvOutputLogic;
	}
	/**
	 * DTO【結果条件】
	 * @return クラス変数resultParamDto を戻します。
	 */
	public NipoRefConditionParameterDto getNipoRefResultParameterDto() {
		return resultParamDto;
	}

	/**
	 * DTO【結果条件】
	 * @param parameterDto を クラス変数resultParamDtoへ設定します。
	 */
	public void setNipoRefResultParameterDto(
			NipoRefConditionParameterDto parameterDto) {
		this.resultParamDto = parameterDto;
	}

	/**
	 * DTO【検索条件】
	 * @return クラス変数searchParamDto を戻します。
	 */
	public NipoRefConditionParameterDto getNipoRefSearchParameterDto() {
		return searchParamDto;
	}

	/**
	 * DTO【検索条件】
	 * @param parameterDto を クラス変数searchParamDtoへ設定します。
	 */
	public void setNipoRefSearchParameterDto(
			NipoRefConditionParameterDto parameterDto) {
		this.searchParamDto = parameterDto;
	}
    /** DTO【MAP結果情報】 */
    private NipoRefResultMapDto takuhaiNipoRefMapResultDto;
	/**
	 * DTO【Map結果情報】
	 * @return クラス変数takuhaiNipoRefMapResultDto を戻します。
	 */
	public NipoRefResultMapDto getTakuhaiNipoRefMapResultDto() {
		return takuhaiNipoRefMapResultDto;
	}
	/**
	 * DTO【Map結果情報】
	 * @param mapResultDto を クラス変数takuhaiNipoRefMapResultDtoへ設定します。
	 */
	public void setTakuhaiNipoRefMapResultDto(
			NipoRefResultMapDto mapResultDto) {
		this.takuhaiNipoRefMapResultDto = mapResultDto;
	}

}