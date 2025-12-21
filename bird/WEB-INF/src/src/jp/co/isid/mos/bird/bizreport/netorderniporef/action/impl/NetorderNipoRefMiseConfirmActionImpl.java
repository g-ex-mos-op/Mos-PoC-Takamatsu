package jp.co.isid.mos.bird.bizreport.netorderniporef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.MstSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.netorderniporef.action.NetorderNipoRefMiseConfirmAction;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoCommon;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoConstants;
import jp.co.isid.mos.bird.bizreport.netorderniporef.dto.ResultDto;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.NetorderNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.netorderniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.text.converter.NumericConverter;

/**
 * ネット注文日報の結果画面(店舗別)アクション
 *
 */
public class NetorderNipoRefMiseConfirmActionImpl extends CsvOutput2ActionImpl implements NetorderNipoRefMiseConfirmAction {
    /** アクション【初期化処理】ID */
    public static final String initialize_ACTION_ID  = "BBR017A09";

    /** アクション【検索】ID */
    public static final String reSearch_ACTION_ID = "BBR017A10";

    /** CSVダウンロードアクションID */
    public static final String downloadCsv_ACTION_ID = "BBR017A11";

    /** 店舗リンクアクションID */
    public static final String selectedMise_ACTION_ID = "BBR017A12";

    /** 宅配売上日報タブリンクアクションID */
    public static final String goTakuhaiNipo_ACTION_ID = "BBR017A13";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR017A14";

    /** 対象支部リスト取得アクションID */
    public static final String reSearchTaishoSibuList_ACTION_ID = "BBR017A15";
    /** アクション【通貨切替】ID */
    public static final String changeCurrency_ACTION_ID = "BBR017A29";
    /** アクション【MOSCARD画面遷移】ID */
    public static final String callMoscard_ACTION_ID = "BBR017A32";
    /** アクション【SV検索ボタン】ID */
    public static final String callSvForm_ACTION_ID   = "BBR017A50";
    /**アクション【営業日報画面遷移】ID */
    public static final String goEigyoNipo_ACTION_ID   = "BBR017A52";

    /** SV選択画面 */
    private SvSearchDto newSvSearchDto;
    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;
    /** DTO【結果情報】 */
    private ResultDto netorderNipoRefResultDto;


    /** 検索条件取得ロジック */
    private NetorderNipoSearchLogic netorderNipoSearchLogic;

    /** LOGIC【店別売上予算情報取得】 */
    private UriageNipoInfoLogic uriageNipoInfoLogic;

    /** LOGIC【対象支部情報取得】 */
    private MstSibuInfoLogic mstSibuInfoLogic;

    /** 店コード */
    private String miseCd;
    /** 別ウィンドウフラグ */
    private boolean newWindowFlg = false;
    /**
     * 表示形式
     * 2013/01/31 追加 海外売上集信対応
     */
    private NumericConverter netorderNipoNumericCnvt;
    /**
     * 為替相場表示形式
     * 2013/04/10 追加 海外売上集信対応
     */
    private NumericConverter netorderNipoRateCnvt;

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
        	netorderNipoRefResultDto.setExistDataFlg(true);
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
	        	ResultDto saveResultDto = (ResultDto)getNetorderNipoRefMapResultDto().getResultDto(windowId);
	        	if(saveResultDto != null) {
	        		setNetorderNipoRefResultDto(saveResultDto);
	        	}
                //SV-8．SVを選択後遷移してきた場合、下記の処理を行います。
                if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                    //SV-SELECT-1.日報共通DTO【検索条件】.SVコードへBIRD共通DTO【SV選択画面】.SVコードを設定します。
                    searchParamDto.setSvCd(getNewSvSearchDto().getSvCd());
                    //SV-SELECT-2.アクション【検索】を実行します。
                    return this.reSearch();
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
        //３．日報共通DTO【検索条件】を引数に、対象支部プルダウンリスト取得を実行します。
        getListTaishoSibu(searchParamDto, false);
        //４． DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        if(netorderNipoRefResultDto.isExistDataFlg()
        		&& (netorderNipoRefResultDto.getUriageList() == null
        				|| netorderNipoRefResultDto.getUriageList().isEmpty()))
        {
        	//0.日報共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】から処理0の値をキーに日報共通DTO【結果条件】を取得します。
        	NipoRefConditionParameterDto sessionResultParamDto = getNetorderNipoRefMapResultDto().getParameterDto(windowId);
        	//2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
        	ResultDto resultDto = (ResultDto) getNetorderNipoRefMapResultDto().getResultDto(windowId);
        	//3.表示対象の日報共通DTO【結果条件】の条件と処理1の日報共通DTO【結果条件】の条件が一致した場合、
        	if(sessionResultParamDto != null && resultDto != null
        			&& resultParamDto.equalsParams(sessionResultParamDto)
        			&& resultDto.isSearchTypeMiseList())
        	{
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setNetorderNipoRefResultDto(resultDto);
        	}
        	else {
        		try {
    	        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
                    this.searchMiseUriageInfo(resultParamDto);
        		}
        		catch (ApplicationException ex) {
        			if((netorderNipoRefResultDto.getUriageList() == null
            				|| netorderNipoRefResultDto.getUriageList().isEmpty())) {
        				//データが無い場合は
        				netorderNipoRefResultDto.setExistDataFlg(false);
        			}
        			throw ex;
        		}
        	}
        }
    	//５．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
    	//６．表示形式へ日報共通DTO【結果条件】.CodCompany[会社].表示形式を設定します。
    	getNetorderNipoNumericCnvt().setFormatPattern(resultParamDto.getCodCompany().getDispFormat());
    	getNetorderNipoRateCnvt().setFormatPattern("#,##0.00");
        //７．DTO【結果情報】.該当データ存在判断フラグがtrueで、
    	//    かつ日報共通DTO【結果条件】.CodCompany[会社].海外フラグがtrueの場合
    	//    下記の処理を行います。
        if(netorderNipoRefResultDto.isExistDataFlg() && resultParamDto.getCodCompany().isForeign()){
        	//1.DTO【結果情報】.換算フラグへDTO【結果条件】.換算フラグを設定します。
        	netorderNipoRefResultDto.setKansanFlg(resultParamDto.isKansan());
        	//2.日報共通DTO【結果条件】.換算フラグがtrue(換算済)の場合、下記の日本円換算処理を行います。
        	if(resultParamDto.isKansan()) {
	            //kansan-1.UTIL【ネット注文日報共通処理】.通貨換算処理を実行し、戻り値DTO【換算情報】を取得します。
		        ResultDto changeDto = NetorderNipoCommon.changeCurrency(
		        		resultParamDto, netorderNipoRefResultDto);
		        //kansan-2.DTO【結果情報】へ処理1の戻り値DTO【換算情報】を設定します。
		        setNetorderNipoRefResultDto(changeDto);
		        //kansan-3.表示形式へ日本円表示形式(#,##0)を設定します。
		        getNetorderNipoNumericCnvt().setFormatPattern(BizReportConstants.FORMAT_JPY);
        	}
        }
        //８．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * アクション【検索】
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
        //２．日報共通DTO【検索条件】を引数に、店別売上情報取得を実行します。
        this.searchMiseUriageInfo(searchParamDto);
        //３．日報共通DTO【条件情報部】.Map[対象支部リスト]に下記のキーが存在する場合、支部名称の設定を行います。
		//    キー：DTO【検索条件】.会社コード＋DTO【検索条件】.集計区分
        String companyCd = searchParamDto.getCompanyCd();
        String shukeiKbn = searchParamDto.getShukeiKbnCd();
        String taishoSibuKey = companyCd+shukeiKbn;
        boolean isExistSibu = getNipoRefConditionDto().getMapTaishoSibuList().containsKey(taishoSibuKey);
        if (!resultParamDto.isSvFlg() && isExistSibu) {
				//SIBU-1.処理３のキーの値List[[対象支部]]をからDTO【結果条件】.支部コードの支部名称を取得します。
			List sibuList = getNipoRefConditionDto().getTaishoSibuList(taishoSibuKey);
            for (Iterator it = sibuList.iterator(); it.hasNext();) {
                SelectItem info = (SelectItem) it.next();
                if (resultParamDto.getSibuCd().equals(info.getValue())) {
                    //SIBU-2.DTO【結果条件】.対象支部名称へ処理SIBU-1の支部名称を設定します。
                	resultParamDto.setSibuName(info.getLabel());
                    break;
                }
            }
        }
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
        //１．BIRD共通DTO【SV選択画面】.遷移元情報へ店舗別結果画面(本部用)VIEW_IDを設定します。
        getNewSvSearchDto().setNavigationCase(NipoRefConstants.VIEW_ID_N_MISE);
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
     * アクション【CSVダウンロード】
     */
    public void downloadCsvMise() {
        try {
        	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
        	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto
        			, resultParamDto.isSvFlg()?TaishoJoken.CODE_SV:TaishoJoken.CODE_SIBU);
        	//２．日報共通DTO【結果条件】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(resultParamDto);
        	//３．継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        } catch (IOException e) {
        	//４．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
    }

    /**
     * アクション【店舗リンク】
     */
    public String selectedMise() {
    	//１．S2Containerを取得し、BIRD共通DTO[画面遷移情報]を取得します。
        S2Container container = SingletonS2ContainerFactory.getContainer();
        CommonCodeDto commonCodeDto = (CommonCodeDto)
            container.getComponent(CommonCodeDto.class);
        //２．BIRD共通DTO[画面遷移情報]がnullの場合は、インスタンス化しオブジェクトを生成します。
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        //３．BIRD共通DTO[画面遷移情報].会社コードへ日報共通DTO【結果条件】.会社コードを設定します。
        commonCodeDto.setCompanyCd(resultParamDto.getCompanyCd());
        //４．BIRD共通DTO[画面遷移情報].店コードへクラス変数[店コード]を設定します。
        commonCodeDto.setMiseCd(getMiseCd());

        //５．個店ポータル画面VIEW_IDをリターンします。
        return CommonUtil.VIEW_ID_MISEREF;
    }

    /**
     * アクション【営業日報画面遷移】
     */
    public String goEigyoNipo() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．営業日報結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_E_MISE;
    }

    /**
     * アクション【宅配売上画面遷移】
     */
    public String goTakuhaiNipo() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．宅配売上結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_T_MISE;
    }

    /**
     * アクション【屋号別売上画面遷移】
     */
    public String goYago() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．屋号別売上結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_MISE;
    }

    /**
     * アクション【対象支部プルダウンリスト切替】
     * @return String 遷移先ビューID
     */
    public String reSearchTaishoSibuList() {
    	//１．結果条件の入力チェックを行います
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_SIBU);        //２．DTO【結果情報】.データ存在フラグがfalseの場合NoResultExceptionを発生させます。
        //１．DTO【結果情報】.データ存在フラグがfalseの場合NoResultExceptionを発生させます。
        if(!getNetorderNipoRefResultDto().isExistDataFlg()) {
        	throw new NoResultException();
        }
        searchParamDto.setSibuCd("");
        //２．nullをリターンします。(現行画面へ遷移)
        return null;
    }
	/*
	 * アクション【MOSCARD画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefMiseConfirmAction#callMoscard()
	 */
	public String callMoscard() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //１．MOSCARDはサブ区分を画面間で共有ないため、DTO【結果条件】.サブタブ区分へ初期値としてBizReportConstants.SUB_TAG_0を設定。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //２．MOSCARD店舗別画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_M_MISE;
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
     * 店舗別売上情報取得
     *
     * @param requestDto DTO【検索条件】
     *
	 * @modifier xkinu 2010/01/18 ADD
	 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
	 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
	 *        システムエラーが発生することがなくなる為。
	 * @modifier xkinu 2013/01/31 海外売上集信対応
     */
    private void searchMiseUriageInfo(NipoRefConditionParameterDto parameterDto) {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto
    			, parameterDto.isSvFlg()?TaishoJoken.CODE_SV:TaishoJoken.CODE_SIBU);
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
        		netorderNipoRefResultDto.clear();
                //EX-2.NotExistExceptionを発生させます。
        		throw new NotExistException("担当支部");
        	}
        }
    	//３．DTO【結果条件】.集計区分＝SV指定(担当店一覧)の場合
        if(!parameterDto.isSvFlg()) {
            //1.DTO【検索条件】.支部コードの必須チェック。
            if (NetorderNipoCommon.isNull(parameterDto.getSibuCd())) {
            	//存在しない場合はNotNullExceptionを発生させます。
                throw new NotNullException(NetorderNipoConstants.MSG_SIBU_CD, "taishoSibu", 0);
            }
        }
        //４．Try開始 検索処理を行います。
        try {
            //1.LOGIC【検索条件取得】を実行し、Map[検索条件]を取得します。
            Map paramMap = getNetorderNipoSearchLogic().getSearchData(parameterDto);
            //2.変数String[区分],変数String[支部コード]を生成します。
            String kbnCd  = "";
            String sibuCd = "";
            //3.DTO【検索条件】.SV指定(担当店一覧)判断フラグがfalseの場合、
            if(!parameterDto.isSvFlg()){
	            //DTO【検索条件】.行CSSクラス名から判断した値を取得します。
                kbnCd  = NetorderNipoCommon.getSumKbn(parameterDto.getClassName());
                //変数String[支部コード]へDTO【検索条件】.支部コードを設定します。
                sibuCd = parameterDto.getSibuCd().trim();
            }

            //4.Map[検索条件]の支部コードへ変数String[支部コード]を設定します。
            paramMap.put(NetorderNipoConstants.MAP_SIBU_CD, sibuCd);
            //5.Map[検索条件]の区分へ変数String[区分]を設定します。
            paramMap.put(NetorderNipoConstants.MAP_KBN_CD , kbnCd);

            //6.LOGIC【店別売上予算情報取得】を実行し、Map[店別売上予算情報]を取得します。
            Map uriageNipoMap = getUriageNipoInfoLogic().execute(paramMap);
            //7.Map[店別売上予算情報]の売上リストを変数List[[売上]]とします。
            List uriageList = (List) uriageNipoMap.get(NetorderNipoConstants.MAP_URIAGE_LIST);
            //8.Map[店別売上予算情報]の客数リストを変数List[[客数]]とします。
            List kyakusuList = (List) uriageNipoMap.get(NetorderNipoConstants.MAP_KYAKUSU_LIST);
            //9.変数List[[売上]]が０件の場合、NoResultExceptionを発生させます。
            if (uriageList == null || uriageList.size() == 0) {
                throw new NoResultException(NetorderNipoConstants.EMPTY);
            }
            //10.変数List[[客数]]が０件の場合、NoResultExceptionを発生させます。
            if (kyakusuList == null || kyakusuList.size() == 0) {
                throw new NoResultException(NetorderNipoConstants.EMPTY);
            }

            //SV情報セット
            String svCd   = (String) paramMap.get(NetorderNipoConstants.SV_CD);
            String svName = (String) paramMap.get(NetorderNipoConstants.SV_NAME);
            //11.DTO【検索条件】.SVコードへMap[検索条件]のSVコード(前ゼロ付加済)を再設定します。
            parameterDto.setSvCd(svCd);
            //12.DTO【検索条件】.SV名称へMap[検索条件]のSV名称を設定します。
            parameterDto.setSvName(svName);

            //13.DTO【結果情報】.List[[店別売上]]へ変数List[[売上]]を設定します。
            netorderNipoRefResultDto.setUriageList(uriageList);
	        //14.DTO【結果情報】.List[[店別客数]]へ変数List[[客数]]を設定します。
	        netorderNipoRefResultDto.setKyakusuList(kyakusuList);
	        //15.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        netorderNipoRefResultDto.setExistDataFlg(true);
	        //16.DTO【結果情報】.検索タイプへTaishoJoken.CODE_MISEを設定する。
	        netorderNipoRefResultDto.setSearchListType(TaishoJoken.CODE_MISE);
	        //17.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        netorderNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
            //18.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            setNipoRefResultParameterDto(parameterDto);
	        //19.DTO【Map結果情報】.日報共通DTO【結果条件】へDTO【検索条件】.ウィンドウIDをキーにDTO【検索条件】を設定します。
	        getNetorderNipoRefMapResultDto().setParameterDto(parameterDto);
	    	//20.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getNetorderNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), netorderNipoRefResultDto);
	        //21.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
            searchParamDto.setResearchFlg(true);
	    }
        //５．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException noResult) {
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //EX-2.DTO【結果情報】.クリア処理を行います。
	        netorderNipoRefResultDto.clear();
	        //EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getNetorderNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), netorderNipoRefResultDto);
	        //EX-4.DTO【検索条件】.SV名称へ""(空)を設定します。
	        parameterDto.setSvName("");
	    	//EX-5.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
	    	throw noResult;
	    }
	    //６．BatchProcessingExceptionをキャッチし、下記の処理を行います。
        catch (BatchProcessingException batchEx) {
	        //EX-1.DTO【結果情報】.クリア処理を行います。
        	netorderNipoRefResultDto.clear();
	        //EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getNetorderNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), netorderNipoRefResultDto);
	    	//EX-3.キャッチしたBatchProcessingExceptionを発生させます。
	        throw batchEx;
        }
	    //７．処理終了
    }
    /**
     * 対象(担当)支部リスト取得処理
     *
     * @param parameterDto DTO【検索条件】
     * @param isExThrow 例外発生フラグ true:Exceptionをスルーします。
     * @return
     */
    private void getListTaishoSibu(NipoRefConditionParameterDto parameterDto, boolean isExThrow) {
        String companyCd = parameterDto.getCompanyCd();
        String shukeiKbn = parameterDto.getShukeiKbnCd();
        String taishoSibuKey = companyCd+shukeiKbn;
        boolean isExistSibu = getNipoRefConditionDto().getMapTaishoSibuList().containsKey(taishoSibuKey);
        boolean limitFlg = getNipoRefConditionDto().getBirdUserInfo().isLimit();
        //１．変数List[[支部リスト]]を作成。
        List sibuList = null;
		//２．Try開始 対象支部リストを取得します。
    	try {
	        //1.日報共通DTO【条件情報部】.Map[対象支部リスト]に下記のキーが存在する場合、
			//    キー：DTO【検索条件】.会社コード＋DTO【検索条件】.集計区分
			if(isExistSibu) {
				//処理１の変数List[[支部リスト]]へその値を代入します。
				sibuList = getNipoRefConditionDto().getTaishoSibuList(taishoSibuKey);
			}
			//2.処理1のキーが存在しない場合、下記の処理を行います。
    		if(!isExistSibu) {
    	        String userId = getNipoRefConditionDto().getBirdUserInfo().getUserID();
	            //2-1．LOGIC【対象支部情報取得】を実行し、戻り値をList[[対象支部]]とします。
	            sibuList = getMstSibuInfoLogic().execute(
	            		companyCd, limitFlg, userId, shukeiKbn);
	    		//2-2．日報共通DTO【条件部情報】.Map[対象支部リスト]へ以下の条件で値を格納します。
	    		//    キー：DTO【検索条件】.会社コード＋DTO【検索条件】.集計区分
	    		//    値  ：処理１のList[[対象支部]]
	            getNipoRefConditionDto().getMapTaishoSibuList().put(taishoSibuKey, sibuList);
    		}
    	}
    	//３．処理２でNotExistExceptionが発生した場合、下記の処理を行います。
    	catch (NotExistException ex) {
            //EX-1.DTO【結果情報】.店舗別売上情報クリア処理を行います。
    		netorderNipoRefResultDto.clear();
            //EX-2.日報共通DTO【結果条件】.SV名称へ""(空)を設定します。
            resultParamDto.setSvName("");
            //EX-3.例外発生フラグがtrueの場合、下記の処理を行います。
    		if(isExThrow) {
    			//1.リモート制限フラグがtrueの場合、下記のExceptionを発生させます。
	            if(limitFlg) {
	            	throw new NotExistException("担当支部");
	            }
	            //2.キャッチしたNotExistExceptionをスルーします。
            	throw ex;
    		}
    	}
    	//４．Finally処理を行います。
    	finally {
    		//Final-1.DTO【検索条件】.List[[対象支部]]へ変数List[[支部リスト]]を設定します。
            parameterDto.setTaishoSibuList(sibuList);
    	}
    	//５。処理終了
    }

    //NETORDER用会社リスト
    public List getNetorderCompanyList() {
        List netorderList = new ArrayList();
        try {
             // ネット注文日報用会社リストから会社モスフードサービス(00)を抜き出してNETORDER用会社リストを作成
             for (int i = 0; i <getNipoRefConditionDto().getCompanyList().size(); i++) {
                 CodCompany company = (CodCompany)getNipoRefConditionDto().getCompanyList().get(i);
                 if (company.getCompanyCd().equals(CommonUtil.COMPANY_CD_MOS)) {
                	 netorderList.add(company);
                      break;
                 }

             }
        } catch (NullPointerException e) {

        }
        return netorderList;
    }

    //  NETORDER用店舗種別リスト
    public List getNetorderTenpoShubetuList() {
        List tenpoShubetuList = new ArrayList();
        List temp = new ArrayList();
        //全店、前年対象店、新店を抜き出してNETORDER用店舗種別リストを作成
        temp = TenpoShubetu.getPullDownList();
        for(int i=0;i<temp.toArray().length;i++)
        {
        	SelectItem item = (SelectItem)temp.get(i);
        	if (!TenpoShubetu.CODE_YOSAN.equals(item.getValue().toString()))
        	{
        		tenpoShubetuList.add(item);
        	}
        }
        return tenpoShubetuList;
    }

    //NETORDER用対象期間リスト
    public List getNetorderTaishoKikanList() {
        List taishoKikanList = new ArrayList();
        List temp = new ArrayList();
        //BIRDログインユーザー情報がNULLではない場合
        if (getBirdUserInfo() != null && getBirdUserInfo().getMstUser() != null){
	        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
	        //期日指定日報、当月月報、月別月報告、期間指定を抜き出してNETORDER用店舗種別リストを作成
	        temp = TaishoKikan.getPullDownList(userTypeCd);
	        for(int i=0;i<temp.toArray().length;i++)
	        {
	        	SelectItem item = (SelectItem)temp.get(i);
//	        	if (!TaishoKikan.KIBETU.equals(item.getValue().toString()))
//	        	{
	        		taishoKikanList.add(item);
//	        	}
	        }
        }
        return taishoKikanList;
    }

    /**
     * コンテナ取得処理
     * @return
     */
    private S2Container getS2Container() {
    	return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * CTRL【BIRDログインユーザー情報】取得処理
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
    	return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
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
     *  ネット注文日報条件部情報DTOを設定する
     * @param resultDto ネット注文日報条件部情報DTO
     */
    public void setNetorderNipoRefResultDto(ResultDto resultDto) {
        this.netorderNipoRefResultDto = resultDto;
    }

    /**
     * DTO【結果情報】を取得する
     * @return ResultDto DTO【結果情報】
     */
    public ResultDto getNetorderNipoRefResultDto() {
        return netorderNipoRefResultDto;
    }

    /**
     * 検索条件取得ロジックを取得する
     * @return NetorderNipoSearchLogic 検索条件取得ロジック
     */
    public NetorderNipoSearchLogic getNetorderNipoSearchLogic() {
        return netorderNipoSearchLogic;
    }

    /**
     *  検索条件取得ロジックを設定する
     * @param netorderNipoSearchLogic 検索条件取得ロジック
     */
    public void setNetorderNipoSearchLogic(NetorderNipoSearchLogic netorderNipoSearchLogic) {
        this.netorderNipoSearchLogic = netorderNipoSearchLogic;
    }

    /**
     * LOGIC【店別売上予算情報取得】を取得する
     * @return UriageNipoInfoLogic LOGIC【店別売上予算情報取得】
     */
    public UriageNipoInfoLogic getUriageNipoInfoLogic() {
        return this.uriageNipoInfoLogic;
    }

    /**
     *  LOGIC【店別売上予算情報取得】を設定する
     * @param uriageNipoInfoLogic LOGIC【店別売上予算情報取得】
     */
    public void setUriageNipoInfoLogic(UriageNipoInfoLogic uriageNipoInfoLogic) {
        this.uriageNipoInfoLogic = uriageNipoInfoLogic;
    }

    /**
     * LOGIC【対象支部情報取得】を取得する
     * @return MstSibuInfoLogic LOGIC【対象支部情報取得】
     */
    public MstSibuInfoLogic getMstSibuInfoLogic() {
        return mstSibuInfoLogic;
    }

    /**
     * LOGIC【対象支部情報取得】を設定する
     * @param mstSibuInfoLogic LOGIC【対象支部情報取得】
     */
    public void setMstSibuInfoLogic(MstSibuInfoLogic mstSibuInfoLogic) {
        this.mstSibuInfoLogic = mstSibuInfoLogic;
    }

    /**
     * 店コードを取得する
     * @return String 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }

    /**
     * 店コードを設定する
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
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
    /** DTO【Map結果情報】 */
    private NipoRefResultMapDto netorderNipoRefMapResultDto;
	/**
	 * DTO【Map結果情報】
	 * @return クラス変数netorderNipoRefMapResultDto を戻します。
	 */
	public NipoRefResultMapDto getNetorderNipoRefMapResultDto() {
		return netorderNipoRefMapResultDto;
	}

	/**
	 * DTO【Map結果情報】
	 * @param mapResultDto を クラス変数netorderNipoRefMapResultDtoへ設定します。
	 */
	public void setNetorderNipoRefMapResultDto(
			NipoRefResultMapDto mapResultDto) {
		this.netorderNipoRefMapResultDto = mapResultDto;
	}

	/**
	 * @return クラス変数netorderNipoNumericCnvt を戻します。
	 */
	public NumericConverter getNetorderNipoNumericCnvt() {
		return netorderNipoNumericCnvt;
	}

	/**
	 * @param netorderNipoNumericCnvt を クラス変数netorderNipoNumericCnvtへ設定します。
	 */
	public void setNetorderNipoNumericCnvt(
			NumericConverter netorderNipoNumericCnvt) {
		this.netorderNipoNumericCnvt = netorderNipoNumericCnvt;
	}

	/**
	 * @return クラス変数netorderNipoRateCnvt を戻します。
	 */
	public NumericConverter getNetorderNipoRateCnvt() {
		return netorderNipoRateCnvt;
	}

	/**
	 * @param netorderNipoRateCnvt を クラス変数netorderNipoRateCnvtへ設定します。
	 */
	public void setNetorderNipoRateCnvt(NumericConverter netorderNipoRateCnvt) {
		this.netorderNipoRateCnvt = netorderNipoRateCnvt;
	}
}