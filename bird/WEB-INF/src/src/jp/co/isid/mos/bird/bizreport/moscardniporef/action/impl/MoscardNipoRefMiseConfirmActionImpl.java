package jp.co.isid.mos.bird.bizreport.moscardniporef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.MstSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefMiseConfirmAction;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoCommon;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dto.ResultDto;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * MOSCARDの結果画面(店舗別)アクション
 *
 * @author   xyamauchi
 */
public class MoscardNipoRefMiseConfirmActionImpl extends CsvOutput2ActionImpl implements MoscardNipoRefMiseConfirmAction {
    /** アクション【初期化処理】ID */
    public static final String initialize_ACTION_ID  = "BBR015A20";

    /** アクション【検索】ID */
    public static final String reSearch_ACTION_ID = "BBR015A21";

    /** CSVダウンロードアクションID */
    public static final String downloadCsv_ACTION_ID = "BBR015A22";

    /** 店舗リンクアクションID */
    public static final String selectedMise_ACTION_ID = "BBR015A23";

    /** アクション【営業日報画面遷移】ID */
    public static final String goEigyoNipo_ACTION_ID = "BBR015A24";

    /** 宅配売上日報タブリンクアクションID */
    public static final String goTakuhaiNipo_ACTION_ID = "BBR015A25";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR015A26";

    /** 対象支部リスト取得アクションID */
    public static final String reSearchTaishoSibuList_ACTION_ID = "BBR015A27";

    /** アクション【SV検索ボタン】ID */
    public static final String callSvForm_ACTION_ID   = "BBR015A28";

    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR015A29";

    /** SV選択画面 */
    private SvSearchDto newSvSearchDto;
    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;
    /** DTO【結果情報】 */
    private ResultDto moscardNipoRefResultDto;


    /** 検索条件取得ロジック */
    private SearchLogic moscardNipoSearchLogic;

    /** LOGIC【店別売上予算情報取得】 */
    private UriageNipoInfoLogic uriageNipoInfoLogic;

    /** LOGIC【対象支部情報取得】 */
    private MstSibuInfoLogic mstSibuInfoLogic;

    /** 店コード */
    private String miseCd;
    /** 別ウィンドウフラグ */
    private boolean newWindowFlg = false;

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
	        moscardNipoRefResultDto.setExistDataFlg(true);
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
	        	setMoscardNipoRefResultDto((ResultDto)getMoscardNipoRefMapResultDto().getResultDto(windowId));
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
        getListTaishoSibu(searchParamDto, true);
        //４． DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        if(moscardNipoRefResultDto.isExistDataFlg()
                && (moscardNipoRefResultDto.getUriageList() == null
                        || moscardNipoRefResultDto.getUriageList().isEmpty()))
        {
            //0.日報共通DTO【検索条件】.ウィンドウIDを取得します。
            int windowId = searchParamDto.getWindowId();
            //1.日報共通DTO【条件部情報】から処理0の値をキーに日報共通DTO【結果条件】を取得します。
            NipoRefConditionParameterDto sessionResultParamDto = getMoscardNipoRefMapResultDto().getParameterDto(windowId);
            //2.日報共通DTO【条件部情報】から処理0の値をキーにDTO【結果情報】を取得します。
            ResultDto resultDto = (ResultDto) getMoscardNipoRefMapResultDto().getResultDto(windowId);
            //3.表示対象の日報共通DTO【結果条件】の条件と処理1の日報共通DTO【結果条件】の条件が一致した場合、
            if(sessionResultParamDto != null && resultDto != null
                    && resultParamDto.equalsParams(sessionResultParamDto)
                    && resultDto.isSearchTypeMiseList())
            {
                //DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
                setMoscardNipoRefResultDto(resultDto);
            }
            else {
                try {
                    //4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
                    this.searchMiseUriageInfo(resultParamDto);
                }
                catch (ApplicationException ex) {
                   if((moscardNipoRefResultDto.getUriageList() == null
                               || moscardNipoRefResultDto.getUriageList().isEmpty())) {
                           //データが無い場合は
                           moscardNipoRefResultDto.setExistDataFlg(false);
                   }
                   throw ex;
                }

            }
        }
        //５．日報共通UTIL【共通処理】検索事前処理を実行します。
        //    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
        //６．nullをリターンします。(現行画面へ遷移)
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
				//SIBU-1.処理キーの値List[[対象支部]]をからDTO【検索条件】.支部コードの支部名称を取得します。
			List sibuList = getNipoRefConditionDto().getTaishoSibuList(taishoSibuKey);
            for (Iterator it = sibuList.iterator(); it.hasNext();) {
                SelectItem info = (SelectItem) it.next();
                if (resultParamDto.getSibuCd().equals(info.getValue())) {
                    //SIBU-2.DTO【検索条件】.対象支部名称へ処理SIBU-1の支部名称を設定します。
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
        getNewSvSearchDto().setNavigationCase(NipoRefConstants.VIEW_ID_M_MISE);
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
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
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
     * アクション【宅配売上画面遷移】
     */
    public String goTakuhaiNipo() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．遷移先のサブタブの位置を0に設定
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //３．宅配売上結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_T_MISE;
    }

    /**
     * アクション【屋号別売上画面遷移】
     */
    public String goYago() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．遷移先のサブタブの位置を0に設定
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //３．屋号別売上結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_MISE;
    }

    /** アクション【営業日報画面遷移】
     * @see jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefSibuConfirmAction#goEigyoNipo()
     */
    public String goEigyoNipo() {
        //１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
                getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．遷移先のサブタブの位置を0に設定
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //３．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_E_MISE;
    }

    /** アクション【ネット注文画面遷移】
     * @see jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefSibuConfirmAction#callNetorder()
     */
    public String callNetorder() {
    	// １．予算対象店、期別期報　を検索し結果が表示されている状態でネット注文タブを押下した場合、以下のエラーメッセージを表示する
    	//この検索条件ではネット注文はご利用できません。検索条件を変更してください。
    	if (TenpoShubetu.CODE_YOSAN.equals(resultParamDto.getTenpoShubetuCd())) {

			throw new GenericMessageException("この検索条件ではネット注文はご利用できません。検索条件を変更してください。");
		}
        //２．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
                getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //３．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_N_MISE;
    }

    /**
     * アクション【対象支部プルダウンリスト切替】
     * @return String 遷移先ビューID
     */
    public String reSearchTaishoSibuList() {
        //１．日報共通DTO【検索条件】を引数に、対象支部プルダウンリスト取得を実行します。
        getListTaishoSibu(searchParamDto, false);
        //２．DTO【結果情報】.データ存在フラグがfalseの場合NoResultExceptionを発生させます。
        if(!getMoscardNipoRefResultDto().isExistDataFlg()) {
        	throw new NoResultException();
        }
        //３．nullをリターンします。(現行画面へ遷移)
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
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto);
    	//２．DTO【結果条件】.集計区分＝SV指定(担当店一覧)の場合
        if(!parameterDto.isSvFlg()) {
            //1.DTO【検索条件】.支部コードの必須チェック。
            if (MoscardNipoCommon.isNull(parameterDto.getSibuCd())) {
            	//存在しない場合はNotNullExceptionを発生させます。
                throw new NotNullException(MoscardNipoConstants.MSG_SIBU_CD, "taishoSibu", 0);
            }
        }
        //３．Try開始 検索処理を行います。
        try {
            //1.LOGIC【検索条件取得】を実行し、Map[検索条件]を取得します。
            Map paramMap = getMoscardNipoSearchLogic().getSearchData(parameterDto);
            //2.変数String[区分],変数String[支部コード]を生成します。
            String kbnCd  = "";
            String sibuCd = "";
            //3.DTO【検索条件】.SV指定(担当店一覧)判断フラグがfalseの場合、
            if(!parameterDto.isSvFlg()){
	            //DTO【検索条件】.行CSSクラス名から判断した値を取得します。
                kbnCd  = MoscardNipoCommon.getSumKbn(parameterDto.getClassName());
                //変数String[支部コード]へDTO【検索条件】.支部コードを設定します。
                sibuCd = parameterDto.getSibuCd().trim();
            }

            //4.Map[検索条件]の支部コードへ変数String[支部コード]を設定します。
            paramMap.put(MoscardNipoConstants.MAP_SIBU_CD, sibuCd);
            //5.Map[検索条件]の区分へ変数String[区分]を設定します。
            paramMap.put(MoscardNipoConstants.MAP_KBN_CD , kbnCd);

            //6.LOGIC【店別売上予算情報取得】を実行し、Map[店別売上予算情報]を取得します。
            Map uriageNipoMap = getUriageNipoInfoLogic().execute(paramMap);
            //7.Map[店別売上予算情報]の売上リストを変数List[[売上]]とします。
            List uriageList = (List) uriageNipoMap.get(MoscardNipoConstants.MAP_URIAGE_LIST);
            //9.変数List[[売上]]が０件の場合、NoResultExceptionを発生させます。
            if (uriageList == null || uriageList.size() == 0) {
                throw new NoResultException(MoscardNipoConstants.EMPTY);
            }

            //SV情報セット
            String svCd   = (String) paramMap.get(MoscardNipoConstants.SV_CD);
            String svName = (String) paramMap.get(MoscardNipoConstants.SV_NAME);
            //11.DTO【検索条件】.SVコードへMap[検索条件]のSVコード(前ゼロ付加済)を再設定します。
            parameterDto.setSvCd(svCd);
            //12.DTO【検索条件】.SV名称へMap[検索条件]のSV名称を設定します。
            parameterDto.setSvName(svName);

            //13.DTO【結果情報】.List[[店別売上]]へ変数List[[売上]]を設定します。
	        moscardNipoRefResultDto.setUriageList(uriageList);
	        //15.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        moscardNipoRefResultDto.setExistDataFlg(true);
	        //16.DTO【結果情報】.検索タイプへTaishoJoken.CODE_MISEを設定する。
	        moscardNipoRefResultDto.setSearchListType(TaishoJoken.CODE_MISE);
	        //17.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        moscardNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
            //18.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            setNipoRefResultParameterDto(parameterDto);
	        //19.日報共通DTO【条件部情報】.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            getMoscardNipoRefMapResultDto().setParameterDto(parameterDto);
	    	//20.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getMoscardNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), moscardNipoRefResultDto);
	        //21.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
            searchParamDto.setResearchFlg(true);
	    }
        //４．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException noResult) {
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getMoscardNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), moscardNipoRefResultDto);
	        //EX-3.DTO【結果情報】.クリア処理を行います。
	        moscardNipoRefResultDto.clear();
	        //EX-4.DTO【検索条件】.SV名称へ""(空)を設定します。
	        parameterDto.setSvName("");
	    	//EX-5.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
	    	throw noResult;
	    }
        //５．BatchProcessingExceptionをキャッチし、下記の処理を行います。
        catch (BatchProcessingException batchEx) {
            //EX-1.DTO【結果情報】.クリア処理を行います。
            moscardNipoRefResultDto.clear();
            //EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
            //DTO【結果情報】を格納します。
            getMoscardNipoRefMapResultDto().setResultDto(
                    parameterDto.getWindowId(), moscardNipoRefResultDto);
            //EX-3.キャッチしたBatchProcessingExceptionを発生させます。
            throw batchEx;
        }
	    //５．処理終了
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
            moscardNipoRefResultDto.clear();
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
     *  MOSCARD条件部情報DTOを設定する
     * @param resultDto MOSCARD条件部情報DTO
     */
    public void setMoscardNipoRefResultDto(ResultDto resultDto) {
        this.moscardNipoRefResultDto = resultDto;
    }

    /**
     * DTO【結果情報】を取得する
     * @return ResultDto DTO【結果情報】
     */
    public ResultDto getMoscardNipoRefResultDto() {
        return moscardNipoRefResultDto;
    }

    /**
     * 検索条件取得ロジックを取得する
     * @return MoscardNipoSearchLogic 検索条件取得ロジック
     */
    public SearchLogic getMoscardNipoSearchLogic() {
        return moscardNipoSearchLogic;
    }

    /**
     *  検索条件取得ロジックを設定する
     * @param moscardNipoSearchLogic 検索条件取得ロジック
     */
    public void setMoscardNipoSearchLogic(SearchLogic moscardNipoSearchLogic) {
        this.moscardNipoSearchLogic = moscardNipoSearchLogic;
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
    private NipoRefResultMapDto moscardNipoRefMapResultDto;
	/**
	 * DTO【Map結果情報】
	 * @return クラス変数moscardNipoRefMapResultDto を戻します。
	 */
	public NipoRefResultMapDto getMoscardNipoRefMapResultDto() {
		return moscardNipoRefMapResultDto;
	}

	/**
	 * DTO【Map結果情報】
	 * @param mapResultDto を クラス変数moscardNipoRefMapResultDtoへ設定します。
	 */
	public void setMoscardNipoRefMapResultDto(
			NipoRefResultMapDto mapResultDto) {
		this.moscardNipoRefMapResultDto = mapResultDto;
	}

    // MOSCARD用会社リスト
    public List getMoscardCompanyList() {
        List moscardList = new ArrayList();
        try {
             // 営業日報用会社リストから会社モスフードサービス(00)を抜き出して MOSCARD用会社リストを作成
             for (int i = 0; i <getNipoRefConditionDto().getCompanyList().size(); i++) {
                 CodCompany company = (CodCompany)getNipoRefConditionDto().getCompanyList().get(i);
                 if (company.getCompanyCd().equals(CommonUtil.COMPANY_CD_MOS)) {
                      moscardList.add(company);
                      break;
                 }

             }
        } catch (NullPointerException e) {
            // TODO: handle exception

        }
        return moscardList;

    }
}