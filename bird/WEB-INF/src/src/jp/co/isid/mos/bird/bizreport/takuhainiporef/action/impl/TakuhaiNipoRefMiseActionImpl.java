package jp.co.isid.mos.bird.bizreport.takuhainiporef.action.impl;

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
import jp.co.isid.mos.bird.bizreport.common.logic.MstSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiNipoRefMiseAction;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoCommon;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dto.TakuhaiNipoRefResultDto;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.MiseInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 宅配売上日報の結果画面(店舗別)アクション
 *
 * @author xjung
 * @modifier xkinu 2010/01/18 ADD
 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
 *        システムエラーが発生することがなくなる為。
 * @modifier xkinu 2013/01/31 MOSCARD画面追加対応
 * @modifier xkinu 2013/01/31 海外売上集信対応
 */
public class TakuhaiNipoRefMiseActionImpl extends CsvOutput2ActionImpl implements TakuhaiNipoRefMiseAction {

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBR003A31";
    /** アクション【SV検索ボタン】ID */
    public static final String callSvForm_ACTION_ID = "BBR003A32";
    /** 再検索アクションID */
    public static final String reSearch_ACTION_ID = "BBR003A31";
    /** CSVダウンロードアクションID */
    public static final String downloadCsvMise_ACTION_ID = "BBR003A34";
    /** 店舗リンクアクションID */
    public static final String selectedSibu_ACTION_ID = "BBR003A35";
    /** 対象支部リスト取得アクションID */
    public static final String reSearchTaishoSibuList_ACTION_ID = "BBR003A36";
    /** 営業日報タブリンクアクションID */
    public static final String goEigyoNipo_ACTION_ID = "BBR003A37";
    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR003A38";
    /** アクション【MOSCARD画面遷移】ID */
    public static final String callMoscard_ACTION_ID  = "BBR003A39";
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR003A40";

    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;
	/** 宅配売上日報 結果情報DTO */
	private TakuhaiNipoRefResultDto takuhaiNipoRefResultDto;

    /** SV選択画面 */
    private SvSearchDto newSvSearchDto;

	/** 店舗別宅配売上情報取得ロジック */
	private MiseInfoLogic miseInfoLogic;

	/** 対象支部情報取得ロジック */
	private MstSibuInfoLogic mstSibuInfoLogic;

	/** 店コード */
	private String miseCd;

	/** 別ウィンドウフラグ */
	private boolean newWindowFlg;
    /** BIRDログインユーザー情報 */
    private BirdUserInfo birdUserInfo;

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
	        	TakuhaiNipoRefResultDto saveResultDto = (TakuhaiNipoRefResultDto)getTakuhaiNipoRefMapResultDto().getResultDto(windowId);
	        	if(saveResultDto != null) {
	        		setTakuhaiNipoRefResultDto(saveResultDto);
	        	}
                //SV-8．SVを選択後遷移してきた場合、下記の処理を行います。
                if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                    //SV-SELECT-1．日報共通DTO【検索条件】.SVコードへBIRD共通DTO【SV選択画面】.SVコードを設定します。
                    searchParamDto.setSvCd(getNewSvSearchDto().getSvCd());
                    //SV-SELECT-2．店別売上情報取得処理を実行します。
                    this.searchMiseInfo(searchParamDto);
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
        if(takuhaiNipoRefResultDto.isExistDataFlg()
        		&& takuhaiNipoRefResultDto.getEmptyMiseRstList())
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
        			&& resultDto.isSearchTypeMiseList())
        	{
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setTakuhaiNipoRefResultDto(resultDto);
        	}
        	else {
        		try {
		        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
		            this.searchMiseInfo(resultParamDto);
	    		}
	    		catch (ApplicationException ex) {
	    			if(takuhaiNipoRefResultDto.getEmptyMiseRstList()) {
	    				//データが無い場合は
	    				takuhaiNipoRefResultDto.setExistDataFlg(false);
	    			}
	    			throw ex;
	    		}
        	}
        }
        //５．検索結果が存在する場合、
        if(takuhaiNipoRefResultDto.isExistDataFlg()) {
        	String[] linkParams = TakuhaiNipoCommon.getLinkParams(resultParamDto.getLinkParams());
        	//DTO【結果情報】宅配区分名称へ日報共通DTO【結果条件】リンクパラメータから名称を取得し設定します。
           	takuhaiNipoRefResultDto.setTakuKbnName(linkParams[1]);
        }
    	//６．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
        //７．nullをリターンします。(現行画面へ遷移)
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
        //２．日報共通DTO【検索条件】を引数に、店別売上情報取得を実行します。
        this.searchMiseInfo(searchParamDto);
        //３．日報共通DTO【結果条件】.サブタグ区分へ"売上タブ区分"を設定します。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //４．日報共通DTO【条件情報部】.Map[対象支部リスト]に下記のキーが存在する場合、支部名称の設定を行います。
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

        //７．nullをリターンします。(現行画面へ遷移)
        return null;
	}

    /**
     * SV検索ボタン処理
     *
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        //１．BIRD共通DTO【SV選択画面】.遷移元情報へ店舗別画面VIEW_IDを設定します。
        getNewSvSearchDto().setNavigationCase(NipoRefConstants.VIEW_ID_T_MISE);
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
     * アクション【ダウンロード】処理
     * @return
     */
    public void downloadCsvMise(){
        try {
        	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
        	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_SIBU);
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
	 * 店舗リンク
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
	 * 営業日報タブリンク
	 */
	public String goEigyoNipo() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．営業日報・店舗別結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_E_MISE;
	}

	/**
	 * 屋号別売上タブリンク
	 */
	public String goYago() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．屋号別売上日報・店舗別結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_MISE;
	}
	/*
	 * アクション【MOSCARD画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiuNipoRefMiseConfirmAction#callMoscard()
	 */
	public String callMoscard() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //１．MOSCARDはサブ区分を画面間で共有ないため、DTO【結果条件】.サブタブ区分へ初期値としてBizReportConstants.SUB_TAG_0を設定。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //２．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_M_MISE;
	}
	/*
	 * アクション【ネット注文画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiuNipoRefMiseConfirmAction#callNetorder()
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
		return NipoRefConstants.VIEW_ID_N_MISE;
	}

    /**
     * 対象支部プルダウンリスト取得
     */
    public String reSearchTaishoSibuList() {
    	//１．結果条件の入力チェックを行います
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_SIBU);        //２．DTO【結果情報】.データ存在フラグがfalseの場合NoResultExceptionを発生させます。
        if(!getTakuhaiNipoRefResultDto().isExistDataFlg()) {
        	//NoResultExceptionを発生させます。
        	throw new NoResultException();
        }
        //３．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * 店舗別宅配売上情報を取得する
     *
	 * @modifier xkinu 2010/01/18 ADD
	 *        リモート制限ユーザログイン時閲覧可能担当支部存在チェックを行う処理を追加しました。
	 *        これによって、リモート閲覧支部が設定されていない場合に検索ボタンを実行した場合に、
	 *        システムエラーが発生することがなくなる為。
	 * @modifier xkinu 2013/01/31 海外売上集信対応
     */
    private void searchMiseInfo(NipoRefConditionParameterDto parameterDto) {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto, TaishoJoken.CODE_SIBU);
    	//２．DTO【結果条件】.集計区分＝SV指定(担当店一覧)以外の場合
        if(!parameterDto.isSvFlg()){
            //1.DTO【検索条件】.支部コードの必須チェック。
            if (CommonUtil.isNull(parameterDto.getSibuCd())) {
            	//存在しない場合はNotNullExceptionを発生させます。
                throw new NotNullException(TakuhaiNipoConstants.MSG_SIBU_CD, "taishoSibu", 0);
            }
        }
        //４．Try開始 検索処理を行います。
        try {
            //1.LOGIC【売上情報取得】を実行し、Map[売上情報取得]を取得します。
            Map resultMap = getMiseInfoLogic().execute(parameterDto);
            //2.Map[売上情報取得]の売上リストを変数List[[売上]]とします。
            List uriageList = (List) resultMap.get(TakuhaiNipoConstants.MAP_MISE_RST_LIST);
            //SV情報セット
            String svCd   = (String) resultMap.get(TakuhaiNipoConstants.MAP_SV_CD);
            String svName = (String) resultMap.get(TakuhaiNipoConstants.MAP_SV_NAME);
            //3.DTO【検索条件】.SVコードへMap[検索条件]のSVコード(前ゼロ付加済)を再設定します。
            parameterDto.setSvCd(svCd);
            //4.DTO【検索条件】.SV名称へMap[検索条件]のSV名称を設定します。
            parameterDto.setSvName(svName);

            //5.DTO【結果情報】.List[[店別売上]]へ変数List[[売上]]を設定します。
	        takuhaiNipoRefResultDto.setMiseRstList(uriageList);
	        //6.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        takuhaiNipoRefResultDto.setExistDataFlg(true);
	        //7.DTO【結果情報】.検索タイプへTaishoJoken.CODE_MISEを設定する。
	        takuhaiNipoRefResultDto.setSearchListType(TaishoJoken.CODE_MISE);
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
		//５．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException noResult) {
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
            //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            setNipoRefResultParameterDto(parameterDto);
	        //EX-2.DTO【結果情報】.店舗別売上情報クリア処理を行います。
	    	takuhaiNipoRefResultDto.clearMise();
	    	//EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
	        //EX-4.日報共通DTO【結果条件】.SV名称へ""(空)を設定します。
	        resultParamDto.setSvName("");
	    	//EX-5.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
	    	throw noResult;
	    }
		//６．BatchProcessingExceptionをキャッチし、下記の処理を行います。
		catch (BatchProcessingException batchEx) {
	        //EX-1.DTO【結果情報】.クリア処理を行います。
		    takuhaiNipoRefResultDto.clearMise();
	    	//EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
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
            takuhaiNipoRefResultDto.clearMise();
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
	 * 宅配売上日報 結果情報DTOを取得する
	 * @return TakuhaiNipoRefResultDto 宅配売上日報 結果情報DTO
	 */
	public TakuhaiNipoRefResultDto getTakuhaiNipoRefResultDto() {
		return takuhaiNipoRefResultDto;
	}

	/**
	 * 宅配売上日報 結果情報DTOを設定する
	 * @param takuhaiNipoRefResultDto 宅配売上日報 結果情報DTO
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
	 * 店舗別宅配売上情報取得ロジックを取得する
	 * @return MiseInfoLogic 店舗別宅配売上情報取得ロジック
	 */
	public MiseInfoLogic getMiseInfoLogic() {
		return miseInfoLogic;
	}

	/**
	 * 店舗別宅配売上情報取得ロジックを設定する
	 * @param miseInfoLogic 店舗別宅配売上情報取得ロジック
	 */
	public void setMiseInfoLogic(MiseInfoLogic miseInfoLogic) {
		this.miseInfoLogic = miseInfoLogic;
	}

	/**
	 * 対象支部情報取得ロジックを取得する
	 * @return MstSibuInfoLogic 対象支部情報取得ロジック
	 */
	public MstSibuInfoLogic getMstSibuInfoLogic() {
		return mstSibuInfoLogic;
	}

	/**
	 * 対象支部情報取得ロジックを設定する
	 * @param mstSibuInfoLogic 対象支部情報取得ロジック
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