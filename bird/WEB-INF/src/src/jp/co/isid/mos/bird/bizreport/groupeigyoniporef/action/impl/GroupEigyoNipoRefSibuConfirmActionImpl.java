package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.logic.MstSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefSibuConfirmAction;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoCommon;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dto.ResultDto;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnUriageInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnYosanInfo;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.GroupEigyoNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageInfoLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.ViewSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.ViewUriYosanLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.YosanInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.text.converter.NumericConverter;

/**
 * 営業日報の結果画面(支部別)アクション
 *
 * @author   xkhata
 * @modifier xjung 2006/10/03 総合営業日報タブ連携対応
 * @modifier xkinu 2010/01/18 ADD
 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
 *        システムエラーが発生することがなくなる為。
 * @modifier xkinu 2013/01/24 海外売上集信対応
 * @modifier xkinu 2013/01/24 MOSCARD対応 MOSCARD画面へのタブを追加
 */
public class GroupEigyoNipoRefSibuConfirmActionImpl extends CsvOutput2ActionImpl implements GroupEigyoNipoRefSibuConfirmAction {
    /** 初期アクションID */
    public static final String initialize_ACTION_ID  = "BBR001A03";

    /** 再検索アクションID */
    public static final String reSearch_ACTION_ID = "BBR001A04";

    /** アクションID:アクション【CSVダウンロード(支部別)】 */
    public static final String downloadCsvSibu_ACTION_ID = "BBR001A05";
    /** アクションID:アクション【CSVダウンロード(店舗別)】 */
    public static final String downloadCsvMise_ACTION_ID = "BBR001A22";

    /** 支部リンクアクションID */
    public static final String selectedSibu_ACTION_ID = "BBR001A06";

    /** 宅配売上日報タブリンクアクションID */
    public static final String goTakuhaiNipo_ACTION_ID = "BBR001A07";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR001A08";

    /** アクション【通貨切替】ID */
    public static final String changeCurrency_ACTION_ID = "BBR001A28";

    /** アクション【MOSCARD画面遷移】ID */
    public static final String callMoscard_ACTION_ID = "BBR001A31";
    /** アクション【SV検索ボタン】ID */
    public static final String callSvForm_ACTION_ID   = "BBR001A50";
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR001A51";

    /** LOGIC【支部マスタ情報】*/
    private MstSibuInfoLogic mstSibuInfoLogic;
    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;

    /** LOGIC【CSVダウンロード(支部別)】 */
    private CsvOutputLogic groupEigyoNipoSibuCsvOutputLogic;

    /** LOGIC【CSVダウンロード(店舗別)】 */
    private CsvOutputLogic groupEigyoNipoMiseCsvOutputLogic;

    /** DTO【結果情報】 */
    private ResultDto groupEigyoNipoRefResultDto;

    /** SV選択画面 */
    private SvSearchDto newSvSearchDto;

    /** LOGIC【検索条件取得】 */
    private GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic;

    /** LOGIC【売上情報取得】 */
    private UriageInfoLogic uriageInfoLogic;

    /** LOGIC【予算情報取得】 */
    private YosanInfoLogic yosanInfoLogic;

    /** LOGIC【表示支部取得】 */
    private ViewSibuInfoLogic viewSibuInfoLogic;

    /** 表示売上LOGIC【予算情報取得】 */
    private ViewUriYosanLogic viewUriYosanLogic;

    /** 別ウィンドウフラグ */
    private boolean newWindowFlg = false;
    /**
     * 表示形式
     * 2013/01/31 追加 海外売上集信対応
     */
    private NumericConverter groupEigyoNipoNumericCnvt;
    /**
     * 為替相場表示形式
     * 2013/04/10 追加 海外売上集信対応
     */
    private NumericConverter groupEigyoNipoRateCnvt;
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
     * アクション【初期化処理】
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
	        groupEigyoNipoRefResultDto.setExistDataFlg(true);
        }
        //２．SV選択画面から遷移してきた場合。2008/12/09追加 **************************/
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            //0．SVを選択後遷移してきた場合、
            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                //SV-SELECT-1.店舗別画面VIEW_IDをリターンします。(営業日報店舗別画面へ遷移)
                return NipoRefConstants.VIEW_ID_E_MISE;
            }
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
	        	setGroupEigyoNipoRefResultDto((ResultDto)getGroupEigyoNipoRefMapResultDto().getResultDto(windowId));
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
        if(groupEigyoNipoRefResultDto.isExistDataFlg()
        		&& (groupEigyoNipoRefResultDto.getUriageList() == null
        				|| groupEigyoNipoRefResultDto.getUriageList().isEmpty()))
        {
        	//0.日報共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】から処理0の値をキーに日報共通DTO【結果条件】を取得します。
        	NipoRefConditionParameterDto sessionResultParamDto = getGroupEigyoNipoRefMapResultDto().getParameterDto(windowId);
        	//2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
        	ResultDto resultDto = (ResultDto) getGroupEigyoNipoRefMapResultDto().getResultDto(windowId);
        	//3.表示対象の日報共通DTO【結果条件】の条件と処理1の日報共通DTO【結果条件】の条件が一致した場合、
        	if(sessionResultParamDto != null && resultDto != null
        			&& resultParamDto.equalsParams(sessionResultParamDto)
        			&& resultDto.isSearchTypeSibuList())
        	{
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setGroupEigyoNipoRefResultDto(resultDto);
        	}
        	else {
        		try {
		        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
		            this.searchSibuUriageInfo(resultParamDto);
        		}
        		catch (ApplicationException ex) {
        			if((groupEigyoNipoRefResultDto.getUriageList() == null
            				|| groupEigyoNipoRefResultDto.getUriageList().isEmpty())) {
        				//データが無い場合は
        				groupEigyoNipoRefResultDto.setExistDataFlg(false);
        			}
        			throw ex;
        		}
        	}
        }
    	//４．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
    	//５．表示形式へ日報共通DTO【結果条件】.CodCompany[会社].表示形式を設定します。
    	getGroupEigyoNipoNumericCnvt().setFormatPattern(resultParamDto.getCodCompany().getDispFormat());
    	getGroupEigyoNipoRateCnvt().setFormatPattern("#,##0.00");
        //６．DTO【結果情報】.該当データ存在判断フラグがtrueで、
    	//    かつ日報共通DTO【結果条件】.CodCompany[会社].海外フラグがtrueの場合
    	//    下記の処理を行います。
        if(groupEigyoNipoRefResultDto.isExistDataFlg() && resultParamDto.getCodCompany().isForeign()){
        	//1.DTO【結果情報】.換算フラグへDTO【結果条件】.換算フラグを設定します。
        	groupEigyoNipoRefResultDto.setKansanFlg(resultParamDto.isKansan());
        	//2.日報共通DTO【結果条件】.換算フラグがtrue(換算済)の場合、下記の日本円換算処理を行います。
        	if(resultParamDto.isKansan()) {
	            //kansan-1.UTIL【営業日報共通処理】.通貨換算処理を実行し、戻り値DTO【換算情報】を取得します。
		        ResultDto changeDto = EigyoNipoCommon.changeCurrency(
		        		resultParamDto, groupEigyoNipoRefResultDto);
		        //kansan-2.DTO【結果情報】へ処理1の戻り値DTO【換算情報】を設定します。
		        setGroupEigyoNipoRefResultDto(changeDto);
		        //kansan-3.表示形式へ日本円表示形式(#,##0)を設定します。
		        getGroupEigyoNipoNumericCnvt().setFormatPattern(BizReportConstants.FORMAT_JPY);
        	}
        }
        //７．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * アクション【検索】
     */
    public String reSearch() {
        //１．日報共通DTO【検索条件】.集計区分で『SV指定(担当店一覧)』が選択された場合は、下記の処理を行います。
        if(searchParamDto.isSvFlg()){
        	//SV-SELECT-1．日報共通DTO【結果条件】へ日報共通DTO【条件条件】を設定します。
        	searchParamDto.setTaishoTenpoCd(TaishoTenpo.ALL);
        	resultParamDto = searchParamDto;
        	//SV-SELECT-2．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
            NipoRefUtil.callScreenInitialize(
            		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
            //SV-SELECT-3.営業日報店舗別画面VIEW_IDをリターンします。(店舗別画面へ遷移)
            return NipoRefConstants.VIEW_ID_E_MISE;
        }
        //２．クラス変数[別ウィンドウフラグ]がtrueの場合、下記の処理を行います。
        if (isNewWindowFlg()) {
            //1.日報共通DTO【条件部情報】.MAXウィンドウIDカウントアップし、MAXウィンドウIDを取得します。
            int newWindowId = getNipoRefConditionDto().updateWindowId();
            //2.日報共通DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.日報共通DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //３．支部別営業日報売上情報を実行します。
        this.searchSibuUriageInfo(searchParamDto);
        //４．日報共通DTO【結果条件】.サブタブ区分へ”売上”区分を設定します。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //５．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * アクション【SV検索ボタン】処理
     *
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        //１．BIRD共通DTO【SV選択画面】.遷移元情報へ営業日報支部別画面VIEW_IDを設定します。
        getNewSvSearchDto().setNavigationCase(NipoRefConstants.VIEW_ID_E_SIBU);
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
     * アクション【CSVダウンロード(支部別)】
     */
    public void downloadCsvSibu() {
        try {
        	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
        	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_ALL);
        	//２．日報共通DTO【結果条件】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(resultParamDto);
        	//３．LOGIC【CSVダウンロード(支部別)】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getGroupEigyoNipoSibuCsvOutputLogic());
        	//４．継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        } catch (IOException e) {
        	//５．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
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
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto
        			, resultParamDto.getClassName());
            //２．日報共通DTO【結果条件】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(resultParamDto);
        	//３．LOGIC【CSVダウンロード(店舗別)】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getGroupEigyoNipoMiseCsvOutputLogic());
        	//４．継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        } catch (IOException e) {
        	//５．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
   }

    /**
     * アクション【支部リンク】
     * 別ウィンドウが発生します。
     */
    public String selectedSibu() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, true);
        //２．店舗別結果画面(本部用)VIEW_IDをリターンします。(店舗別結果画面へ遷移)
        return NipoRefConstants.VIEW_ID_E_MISE;
    }
	/**
	 * アクション【通貨切替】
	 * @see jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefSibuConfirmAction#changeCurrency()
	 */
	public String changeCurrency() {
        //１．nullをリターンします。(現行画面へ遷移)
		return null;
	}

    /**
     * アクション【宅配売上画面遷移】
     */
    public String goTakuhaiNipo() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．宅配売上日報・支部別結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_T_SIBU;
    }

    /**
     * アクション【屋号別売上画面遷移】
     */
    public String goYago() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．屋号別売上・支部別結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_SIBU;
    }

	/*
	 * アクション【MOSCARD画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefSibuConfirmAction#callMoscard()
	 */
	public String callMoscard() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．MOSCARDはサブ区分を画面間で共有ないため、DTO【結果条件】.サブタブ区分へ初期値としてBizReportConstants.SUB_TAG_0を設定。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //３．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_M_SIBU;
	}

	/*
	 * アクション【ネット注文画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefSibuConfirmAction#callNetorder()
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
     * 支部別営業日報売上情報を取得する
     *
     * 更新日:2010/01/18 ADD xkinu
     *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
     *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
     *        システムエラーが発生することがなくなる為。
     *
     * @param parameterDto DTO【検索条件】
     */
    private void searchSibuUriageInfo(NipoRefConditionParameterDto parameterDto){
       	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
       	//    引数:日報共通DTO【条件部情報】、DTO【結果条件】
       	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto, TaishoJoken.CODE_ALL);
        //２．日報共通DTO【条件部情報】.ユーザータイプ＝”本部”で、
        //    かつ日報共通DTO【条件部情報】.CTRL【BIRDログインユーザー情報】.リモート制限がtrueの場合、
        //    下記の確認処理を行います。
        boolean limitFlg = getNipoRefConditionDto().getBirdUserInfo().isLimit();
        if(UserType.isHonbu(getNipoRefConditionDto().getUserTypeCd())
        		&& limitFlg) {
        	try {
            	//1.LOGIC【支部マスタ情報】を実行し、閲覧可能担当支部存在チェックを行います。
            	mstSibuInfoLogic.execute(
            			parameterDto.getCompanyCd()
            			, limitFlg, getNipoRefConditionDto().getBirdUserInfo().getUserID(), "SIBU_CD");
        	}
            //2.処理1でNotExistExceptionが発生した場合下記の処理を行います。
        	catch (NotExistException notExistEx){
    	        //EX-1.DTO【結果情報】.クリア処理を行います。
    	        groupEigyoNipoRefResultDto.clear();
                //EX-2.NotExistExceptionを発生させます。
        		throw new NotExistException("担当支部");
        	}
        }
        //３．Try開始 検索処理を行います。
        try {
	        //1.LOGIC【検索条件取得】を実行し、Map[検索条件]を取得します。
	        Map paramMap =  getGroupEigyoNipoSearchLogic().getSearchData(parameterDto);

	        //2.LOGIC【表示支部情報】を実行し、変数List[[支部情報]]を取得します。
	        List sibuList = getViewSibuInfoLogic().execute(paramMap);
	        //3.処理2List[[支部情報]]が0件の場合は、NoResultExceptionを発生させます。
	        if (sibuList == null || sibuList.size() == 0) {
	        	throw new NoResultException(EigyoNipoConstants.EMPTY);
	        }
	        //4.変数Map[売上予算情報]を生成します。
	        Map uriYosanMap = new HashMap();
	        //5.変数List[[売上情報]]を生成します。
	        List uriageList = new ArrayList();
	        //6.変数List[[前年同月営業日補正用売上情報]]を生成します。
	        List hoseiUriList = new ArrayList();
	        //7.Map[検索情報].前年データ種別＝”前年同月営業日補正”の場合、下記の処理を行います。
	        if (parameterDto.isSelectHosei()) {
	        	//補正処理1.Map[検索情報].前年データ種別へNipoZennenDataShubetu.CODE_DOGETUを設定します。
	            paramMap.put(EigyoNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_DOGETU);
	            //補正処理2.LOGIC【売上情報取得】を実行し、戻り値を変数List[[売上情報]]へ設定します。
	            uriageList = getUriageInfoLogic().execute(paramMap);
	            //補正処理3.Map[検索情報].前年データ種別へNipoZennenDataShubetu.CODE_HOSEIを設定します。
	            paramMap.put(EigyoNipoConstants.MAP_DATASHU, NipoZennenDataShubetu.CODE_HOSEI);
	            //補正処理4.LOGIC【売上情報取得】を実行し、戻り値を変数List[[前年同月営業日補正用売上情報]]へ設定します。
	            hoseiUriList = getUriageInfoLogic().execute(paramMap);
	            //補正処理5.変数Map[売上予算情報]へ"hoseiyou"をキーに変数List[[前年同月営業日補正用売上情報]]を設定します。
	            uriYosanMap.put(EigyoNipoConstants.MAP_HOSE_URI_LIST, hoseiUriList);
	        }else{
	        	//8.LOGIC【売上情報取得】を実行し、戻り値を変数List[[売上情報]]へ設定します。
	        	uriageList = getUriageInfoLogic().execute(paramMap);
	        }

	        //9.LOGIC【予算情報取得】を実行し、戻り値を変数List[[予算情報]]とします。
	        List yosanList = getYosanInfoLogic().execute(paramMap);
	        //10.変数List[[予算情報]]が0件の場合、下記の処理を行います。
	        if (yosanList == null || yosanList.size() == 0) {
	            //1.変数List[[売上情報]]が0件の場合NoResultExceptionを発生させます。
	        	if(uriageList.size() == 0) {
	                throw new NoResultException(EigyoNipoConstants.EMPTY);
	            }
	        	//2.変数List[[予算情報]]を新たにインスタンス化します。
	            yosanList = new ArrayList();
	            //3.変数List[[売上情報]]の件数分、下記の処理を行います。
	            for (int i = 0; i < uriageList.size(); i++) {
	            	//y-row-1. 変数List[[売上情報]]から現行インデックスのTrnUriageInfo[売上情報]を取得します。
	                TrnUriageInfo uriage = (TrnUriageInfo)uriageList.get(i);
	                //y-row-2. 変数.TrnYosanInfo[予算]を生成します。
	                TrnYosanInfo yosan = new TrnYosanInfo();
	                //y-row-3. 変数.TrnYosanInfo[予算].会社コードへTrnUriageInfo[売上情報].会社コードを設定します。
	                yosan.setCompanyCd(uriage.getCompanyCd());
	                //y-row-4. 変数.TrnYosanInfo[予算].支部コードへTrnUriageInfo[売上情報].支部コードを設定します。
	                yosan.setSibuCd(uriage.getSibuCd());
	                //y-row-5. 変数.TrnYosanInfo[予算].予算へ0(ゼロ)を設定します。
	                yosan.setYosan(new BigDecimal(0));
	                //y-row-6. 変数List[[予算情報]]へ変数.TrnYosanInfo[予算]を追加します。
	                yosanList.add(yosan);
	            }
	        }
	        //11.変数Map[売上予算情報]へ支部情報リストキーで、変数List[[支部情報]]を格納します。
	        uriYosanMap.put(EigyoNipoConstants.MAP_SIBU_LIST, sibuList);
	        //12.変数Map[売上予算情報]へ売上情報リストキーで、変数List[[売上情報]]を格納します。
	        uriYosanMap.put(EigyoNipoConstants.MAP_URIAGE_LIST, uriageList);
	        //13.変数Map[売上予算情報]へ予算情報リストキーで、変数List[[予算情報]]を格納します。
	        uriYosanMap.put(EigyoNipoConstants.MAP_YOSAN_LIST, yosanList);
	        //14.変数Map[売上予算情報]へ集計区分キーで、DTO【検索条件】.集計区分を格納します。
	        uriYosanMap.put(EigyoNipoConstants.MAP_SHUKEI_KBN, parameterDto.getShukeiKbnCd());
	        //15.変数Map[売上予算情報]へパラメータキーで、変数Map[検索条件]を格納します。
	        uriYosanMap.put(EigyoNipoConstants.MAP_PARAMS, paramMap);
	        //16.LOGIC【表示売上予算情報取得】を実行し、戻り値を変数Map[表示売上予算情報]とします。
	        Map resultMap = getViewUriYosanLogic().execute(uriYosanMap);
	        //17.変数Map[表示売上予算情報]から売上リストキーでList[[画面表示用売上]]を取得します。
	        List listUriage = (List)resultMap.get(EigyoNipoConstants.MAP_URIAGE_LIST);
	        //18.List[[画面表示用売上]]が0件の場合、NoResultExceptionを発生させます。
	        if(listUriage.size() == 0) {
	            throw new NoResultException(EigyoNipoConstants.EMPTY);
	        }
	        //19.DTO【結果情報】.List[[売上情報]]へ処理16のList[[画面表示用売上]]を設定します。
	        groupEigyoNipoRefResultDto.setUriageList(listUriage);
	        //20.DTO【結果情報】.List[[売上平均情報]]へ変数Map[表示売上予算情報]から売上平均リストキーの値Listを設定します。
	        groupEigyoNipoRefResultDto.setUriAvgList((List)resultMap.get(EigyoNipoConstants.MAP_RST_URI_AVE));
	        //21.DTO【結果情報】.List[[客数情報]]へ変数Map[表示売上予算情報]から客数リストキーの値Listを設定します。
	        groupEigyoNipoRefResultDto.setKyakusuList((List)resultMap.get(EigyoNipoConstants.MAP_KYAKUSU_LIST));
	        //22.DTO【結果情報】.List[[客数平均情報]]へ変数Map[表示売上予算情報]から客数平均リストキーの値Listを設定します。
	        groupEigyoNipoRefResultDto.setKyakuAvgList((List)resultMap.get(EigyoNipoConstants.MAP_RST_KYAKUSU_AVE));

	        //23.DTO【結果情報】.対象店舗数へ変数Map[表示売上予算情報]から対象店舗数キーの値intを設定します。
	        groupEigyoNipoRefResultDto.setTenpoCount(
	            ((BigDecimal) resultMap.get(EigyoNipoConstants.MAP_TENPO_COUNT)).intValue());
	        //24.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        groupEigyoNipoRefResultDto.setExistDataFlg(true);
	        //25.DTO【結果情報】.検索タイプへTaishoJoken.CODE_SIBUを設定する。
	        groupEigyoNipoRefResultDto.setSearchListType(TaishoJoken.CODE_SIBU);
	        //26.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        groupEigyoNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
	        //27.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //28.DTO【Map結果情報】.日報共通DTO【結果条件】へDTO【検索条件】.ウィンドウIDをキーにDTO【検索条件】を設定します。
	        getGroupEigyoNipoRefMapResultDto().setParameterDto(parameterDto);
	        //29.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGroupEigyoNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), groupEigyoNipoRefResultDto);
	        //30.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	        searchParamDto.setResearchFlg(true);

        }
        //４．NoResultExceptionをキャッチし、下記の処理を行います。
        catch (NoResultException noResult) {
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
        	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //EX-2.DTO【結果情報】.クリア処理を行います。
	        groupEigyoNipoRefResultDto.clear();
	        //EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGroupEigyoNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), groupEigyoNipoRefResultDto);
        	//EX-4.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
        	throw noResult;
        }
        //５．BatchProcessingExceptionをキャッチし、下記の処理を行います。
        catch (BatchProcessingException batchEx) {
	        //EX-1.DTO【結果情報】.クリア処理を行います。
	        groupEigyoNipoRefResultDto.clear();
	        //EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGroupEigyoNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), groupEigyoNipoRefResultDto);
	    	//EX-3.キャッチしたBatchProcessingExceptionを発生させます。
	        throw batchEx;
        }
        //６．処理終了
    }
    /**
     * 日報共通DTO【条件部情報】を取得する
     * @return NipoRefConditionDto 日報共通DTO【条件部情報】
     */
    public NipoRefConditionDto getNipoRefConditionDto() {
        return nipoRefConditionDto;
    }

    /**
     * 日報共通DTO【条件部情報】を設定する
     * @param nipoRefConditionDto 日報共通DTO【条件部情報】
     */
    public void setNipoRefConditionDto(NipoRefConditionDto nipoRefConditionDto) {
        this.nipoRefConditionDto = nipoRefConditionDto;
    }
    /**
     * DTO【結果情報】を設定する
     * @param resultDtoDTO【結果情報】
     */
    public void setGroupEigyoNipoRefResultDto(ResultDto resultDto) {
        this.groupEigyoNipoRefResultDto = resultDto;
    }

    /**
     * DTO【結果情報】を取得する
     * @return ResultDto DTO【結果情報】
     */
    public ResultDto getGroupEigyoNipoRefResultDto() {
        return groupEigyoNipoRefResultDto;
    }


    /**
     * LOGIC【検索条件取得】を取得する
     * @return GroupEigyoNipoSearchLogic LOGIC【検索条件取得】
     */
    public GroupEigyoNipoSearchLogic getGroupEigyoNipoSearchLogic() {
        return groupEigyoNipoSearchLogic;
    }

    /**
     *  LOGIC【検索条件取得】を設定する
     * @param groupEigyoNipoSearchLogic LOGIC【検索条件取得】
     */
    public void setGroupEigyoNipoSearchLogic(GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic) {
        this.groupEigyoNipoSearchLogic = groupEigyoNipoSearchLogic;
    }

    /**
     * LOGIC【売上情報取得】を取得する
     * @return UriageInfoLogic LOGIC【売上情報取得】
     */
    public UriageInfoLogic getUriageInfoLogic() {
        return this.uriageInfoLogic;
    }

    /**
     *  LOGIC【売上情報取得】を設定する
     * @param uriageInfoLogic LOGIC【売上情報取得】
     */
    public void setUriageInfoLogic(UriageInfoLogic uriageInfoLogic) {
        this.uriageInfoLogic = uriageInfoLogic;
    }

    /**
     * LOGIC【予算情報取得】を取得する
     * @return YosanInfoLogic LOGIC【予算情報取得】
     */
    public YosanInfoLogic getYosanInfoLogic() {
        return this.yosanInfoLogic;
    }

    /**
     *  LOGIC【予算情報取得】を設定する
     * @param yosanInfoLogic LOGIC【予算情報取得】
     */
    public void setYosanInfoLogic(YosanInfoLogic yosanInfoLogic ) {
        this.yosanInfoLogic = yosanInfoLogic;
    }

    /**
     * LOGIC【表示支部取得】を取得する
     * @return ViewSibuInfoLogic LOGIC【表示支部取得】
     */
    public ViewSibuInfoLogic getViewSibuInfoLogic() {
        return this.viewSibuInfoLogic;
    }

    /**
     *  LOGIC【表示支部取得】を設定する
     * @param viewSibuInfoLogic LOGIC【表示支部取得】
     */
    public void setViewSibuInfoLogic( ViewSibuInfoLogic viewSibuInfoLogic) {
        this.viewSibuInfoLogic = viewSibuInfoLogic;
    }

    /**
     * 表示売上LOGIC【予算情報取得】を取得する
     * @return ViewUriYosanLogic 表示売上LOGIC【予算情報取得】
     */
    public ViewUriYosanLogic getViewUriYosanLogic() {
        return this.viewUriYosanLogic;
    }

    /**
     *  表示売上LOGIC【予算情報取得】を設定する
     * @param viewUriYosanLogic 表示売上LOGIC【予算情報取得】
     */
    public void setViewUriYosanLogic( ViewUriYosanLogic viewUriYosanLogic) {

        this.viewUriYosanLogic = viewUriYosanLogic;
    }

    public SvSearchDto getNewSvSearchDto() {
        return newSvSearchDto;
    }

    public void setNewSvSearchDto(SvSearchDto newSvSearchDto) {
        this.newSvSearchDto = newSvSearchDto;
    }


	/**
	 * @return クラス変数groupEigyoNipoMiseCsvOutputLogic を戻します。
	 */
	public CsvOutputLogic getGroupEigyoNipoMiseCsvOutputLogic() {
		return groupEigyoNipoMiseCsvOutputLogic;
	}

	/**
	 * @param groupEigyoNipoMiseCsvOutputLogic を クラス変数groupEigyoNipoMiseCsvOutputLogicへ設定します。
	 */
	public void setGroupEigyoNipoMiseCsvOutputLogic(
			CsvOutputLogic groupEigyoNipoMiseCsvOutputLogic) {
		this.groupEigyoNipoMiseCsvOutputLogic = groupEigyoNipoMiseCsvOutputLogic;
	}

	/**
	 * @return クラス変数groupEigyoNipoSibuCsvOutputLogic を戻します。
	 */
	public CsvOutputLogic getGroupEigyoNipoSibuCsvOutputLogic() {
		return groupEigyoNipoSibuCsvOutputLogic;
	}

	/**
	 * @param groupEigyoNipoSibuCsvOutputLogic を クラス変数groupEigyoNipoSibuCsvOutputLogicへ設定します。
	 */
	public void setGroupEigyoNipoSibuCsvOutputLogic(
			CsvOutputLogic groupEigyoNipoSibuCsvOutputLogic) {
		this.groupEigyoNipoSibuCsvOutputLogic = groupEigyoNipoSibuCsvOutputLogic;
	}
	/**
	 * 日報共通DTO【結果条件】
	 * @return クラス変数resultParamDto を戻します。
	 */
	public NipoRefConditionParameterDto getNipoRefResultParameterDto() {
		return resultParamDto;
	}

	/**
	 * 日報共通DTO【結果条件】
	 * @param parameterDto を クラス変数resultParamDtoへ設定します。
	 */
	public void setNipoRefResultParameterDto(
			NipoRefConditionParameterDto parameterDto) {
		this.resultParamDto = parameterDto;
	}

	/**
	 * 日報共通DTO【検索条件】
	 * @return クラス変数searchParamDto を戻します。
	 */
	public NipoRefConditionParameterDto getNipoRefSearchParameterDto() {
		return searchParamDto;
	}

	/**
	 * 日報共通DTO【検索条件】
	 * @param parameterDto を クラス変数searchParamDtoへ設定します。
	 */
	public void setNipoRefSearchParameterDto(
			NipoRefConditionParameterDto parameterDto) {
		this.searchParamDto = parameterDto;
	}

	/**
	 * @return クラス変数newWindowFlg を戻します。
	 */
	public boolean isNewWindowFlg() {
		return newWindowFlg;
	}

	/**
	 * @param newWindowFlg を クラス変数newWindowFlgへ設定します。
	 */
	public void setNewWindowFlg(boolean newWindowFlg) {
		this.newWindowFlg = newWindowFlg;
	}
    /** DTO【Map結果情報】 */
    private NipoRefResultMapDto groupEigyoNipoRefMapResultDto;
	/**
	 * DTO【Map結果情報】
	 * @return クラス変数groupEigyoNipoRefMapResultDto を戻します。
	 */
	public NipoRefResultMapDto getGroupEigyoNipoRefMapResultDto() {
		return groupEigyoNipoRefMapResultDto;
	}

	/**
	 * DTO【Map結果情報】
	 * @param mapResultDto を クラス変数groupEigyoNipoRefMapResultDtoへ設定します。
	 */
	public void setGroupEigyoNipoRefMapResultDto(
			NipoRefResultMapDto mapResultDto) {
		this.groupEigyoNipoRefMapResultDto = mapResultDto;
	}

	/**
	 * @return クラス変数groupEigyoNipoNumericCnvt を戻します。
	 */
	public NumericConverter getGroupEigyoNipoNumericCnvt() {
		return groupEigyoNipoNumericCnvt;
	}

	/**
	 * @param groupEigyoNipoNumericCnvt を クラス変数groupEigyoNipoNumericCnvtへ設定します。
	 */
	public void setGroupEigyoNipoNumericCnvt(
			NumericConverter groupEigyoNipoNumericCnvt) {
		this.groupEigyoNipoNumericCnvt = groupEigyoNipoNumericCnvt;
	}

	/**
	 * @return クラス変数groupEigyoNipoRateCnvt を戻します。
	 */
	public NumericConverter getGroupEigyoNipoRateCnvt() {
		return groupEigyoNipoRateCnvt;
	}

	/**
	 * @param groupEigyoNipoRateCnvt を クラス変数groupEigyoNipoRateCnvtへ設定します。
	 */
	public void setGroupEigyoNipoRateCnvt(NumericConverter groupEigyoNipoRateCnvt) {
		this.groupEigyoNipoRateCnvt = groupEigyoNipoRateCnvt;
	}
}