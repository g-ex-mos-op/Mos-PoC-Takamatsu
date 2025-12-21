package jp.co.isid.mos.bird.bizreport.moscardniporef.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefOnerConfirmAction;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dto.ResultDto;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * MOSCARDの結果画面(オーナー)アクション
 *
 * @author   xyamauchi
 */
public class MoscardNipoRefOnerConfirmActionImpl extends CsvOutput2ActionImpl implements MoscardNipoRefOnerConfirmAction {
    /** アクションID:アクション【初期処理】 */
    public static final String initialize_ACTION_ID  = "BBR015A30";

    /** アクションID:アクション【検索】 */
    public static final String reSearch_ACTION_ID = "BBR015A31";

    /** アクションID:アクション【CSVダウンロード】 */
    public static final String downloadOnerCsv_ACTION_ID = "BBR015A32";
    
    /** アクションID:アクション【営業月報抽出CSVダウンロード】 */
    public static final String downloadGepoCsv_ACTION_ID = "BBR015A33";

    /** アクションID:アクション【個店ポータル画面遷移】 */
    public static final String selectedMise_ACTION_ID = "BBR015A34";

    /** アクション【営業日報画面遷移】ID */
    public static final String goEigyoNipo_ACTION_ID = "BBR015A35";
    
    /** 宅配売上日報タブリンクアクションID */
    public static final String goTakuhaiNipo_ACTION_ID = "BBR015A36";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR015A37";
    
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR015A38";
    
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

    /** 店コード */
    private String miseCd;

    /** 別ウィンドウフラグ */
    private boolean newWindowFlg;
    
    /** LOGIC【オーナー用CSVダウンロード】 */
    private CsvOutputLogic moscardNipoRefCsvOutputOnerLogic;
    
/** 2008/10/6 追加 start *****************************************************/    
    /** LOGIC【営業月報抽出CSVダウンロード】 */
    private CsvOutputLogic moscardNipoRefCsvOutputOnerGepoLogic;
    
/** 2008/10/6 end ************************************************************/
    
    /**
     * アクション【初期処理】
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
        //２。DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
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
        			&& resultParamDto.equalsParams(sessionResultParamDto)) {
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setMoscardNipoRefResultDto(resultDto);
        	}
        	else {
                try {
    	        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
                    this.searchOnerUriageInfo(resultParamDto);
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
    	//３．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
        //４．nullをリターンします。(現行画面へ遷移)
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
            //2.DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．店舗別売上情報取得を実行します。
        this.searchOnerUriageInfo(searchParamDto);
        //３．日報共通DTO【結果条件】.サブタグ区分へ"売上タブ区分"を設定します。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);

        //４．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * アクション【CSVダウンロード】
     */
    public void downloadCsvOner() {
        try {
        	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
        	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto);
        	//２．日報共通DTO【結果条件】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(resultParamDto);
        	//３．LOGIC【オーナー用CSVダウンロード】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getMoscardNipoRefCsvOutputOnerLogic());
        	//４．継承クラス.ダウンロード メイン処理を実行します。
        	super.downloadCsv();
        } catch (IOException e) {
        	//５．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
    }
    /**
     * アクション【営業月報抽出CSVダウンロード】
     */
    public void downloadCsvGepo() {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), searchParamDto);
    	//２．Try開始　ダウンロード処理を行います。
        try {
        	//1.DTO【MOSCARDCSV出力用】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(searchParamDto);
        	//2,LOGIC【営業月報抽出CSVダウンロード】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getMoscardNipoRefCsvOutputOnerGepoLogic());
        	//3.継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        }
        //３．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException noResult) {
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へ日報共通DTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(searchParamDto);
	        //EX-2.DTO【Map結果情報】.DTO【結果情報】へ日報共通DTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getMoscardNipoRefMapResultDto().setResultDto(
	        		searchParamDto.getWindowId(), moscardNipoRefResultDto);
	        //EX-3.DTO【結果情報】.クリア処理を行います。
	        moscardNipoRefResultDto.clear();
	    	//EX-4.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
	    	throw noResult;
        }
	    //４．IOExceptionをキャッチし、下記の処理を行います。
	    catch (IOException e) {
        	//EX1.FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
    }
    /**
     * アクション【個店ポータル画面遷移】
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
        //３．宅配売上結果画面(オーナー用店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_T_ONER_MISE;
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
        //３．屋号別売上結果画面(オーナー用店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_ONER_MISE;
    }
 	/**  アクション【営業日報画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefOnerConfirmAction#goEigyoNipo()
	 */
	public String goEigyoNipo() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．遷移先のサブタブの位置を0に設定
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //３．MOSCARD画面店舗一覧画面(オーナー用店舗別)VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_E_ONER_MISE;
	}
	/**  アクション【ネット注文日報画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.moscardniporef.action.MoscardNipoRefOnerConfirmAction#callNetorder()
	 */
	public String callNetorder() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．ネット注文画面店舗一覧画面(オーナー用店舗別)VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_N_ONER_MISE;
	}
    
    /**
     * 店舗別売上情報を取得する
     * 
     * @param requestDto DTO【検索条件】
     * @modifier xkinu 2013/01/31 海外売上集信対応 
     */
    private void searchOnerUriageInfo(NipoRefConditionParameterDto parameterDto) {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto);
        //２．Try開始 検索処理を行います。
        try {
	        //1.LOGIC【検索条件取得】を実行し、Map[検索条件]を取得します。
	        Map paramMap = getMoscardNipoSearchLogic().getSearchData(parameterDto);
	 
	        //2.LOGIC【店別売上予算情報取得】を実行し、Map[店別売上予算情報]を取得します。
	        Map resultMap = getUriageNipoInfoLogic().execute(paramMap);
	        //3.Map[店別売上予算情報]の売上リストを変数List[[売上]]とします。
	        List uriageList = (List) resultMap.get(MoscardNipoConstants.MAP_URIAGE_LIST);
	        //5.変数List[[売上]]が０件の場合、NoResultExceptionを発生させます。
	        if ( uriageList == null || uriageList.size() == 0) {
	            throw new NoResultException(MoscardNipoConstants.EMPTY);
	        }
	        //7.DTO【結果情報】.List[[店別売上]]へ変数List[[売上]]を設定します。
	        moscardNipoRefResultDto.setUriageList(uriageList);
	        //9.DTO【結果情報】.対象店舗数へDTO【Map結果情報】から対象店舗数(オーナー)キーで取得した値intを設定します。
	        moscardNipoRefResultDto.setTenpoCount(
	            ((Integer) resultMap.get(MoscardNipoConstants.MAP_ONER_TENPO_COUNT)).intValue());
	        //10.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        moscardNipoRefResultDto.setExistDataFlg(true);
	        //11.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        moscardNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
	        //12.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
            //13.日報共通DTO【条件部情報】.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            getMoscardNipoRefMapResultDto().setParameterDto(parameterDto);
	        //14.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getMoscardNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), moscardNipoRefResultDto);
	        //15.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	        searchParamDto.setResearchFlg(true);
	    }
        //３．NoResultExceptionをキャッチし、下記の処理を行います。
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
	    	//EX-4.NoResultExceptionを発生させます。
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
	    //４．処理終了
    }
   
    /**
     * 日報共通条件部情報DTOを取得する
     * @return NipoRefConditionDto 日報共通条件部情報DTO
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
     *  DTO【結果情報】を設定する
     * @param moscardNipoRefResultDto DTO【結果情報】
     */
    public void setMoscardNipoRefResultDto(ResultDto moscardNipoRefResultDto) {
        this.moscardNipoRefResultDto = moscardNipoRefResultDto;
    }
 
    /**
     * MOSCARD結果情報DTOを取得する
     * @return ResultDto MOSCARD結果情報DTO
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
     * @param searchLogic 検索条件取得ロジック
     */
    public void setMoscardNipoSearchLogic(SearchLogic searchLogic) {
        this.moscardNipoSearchLogic = searchLogic;
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

	/**
	 * LOGIC【営業月報抽出CSVダウンロード】
	 * @return クラス変数moscardNipoRefCsvOutputOnerGepoLogic を戻します。
	 */
	public CsvOutputLogic getMoscardNipoRefCsvOutputOnerGepoLogic() {
		return moscardNipoRefCsvOutputOnerGepoLogic;
	}

	/**
	 * LOGIC【営業月報抽出CSVダウンロード】
	 * @param csvOutputLogic を クラス変数moscardNipoRefCsvOutputOnerGepoLogicへ設定します。
	 */
	public void setMoscardNipoRefCsvOutputOnerGepoLogic(
			CsvOutputLogic csvOutputLogic) {
		this.moscardNipoRefCsvOutputOnerGepoLogic = csvOutputLogic;
	}

	/**
	 * @return クラス変数moscardNipoRefCsvOutputOnerLogic を戻します。
	 */
	public CsvOutputLogic getMoscardNipoRefCsvOutputOnerLogic() {
		return moscardNipoRefCsvOutputOnerLogic;
	}

	/**
	 * @param csvOutputLogic を クラス変数moscardNipoRefCsvOutputOnerLogicへ設定します。
	 */
	public void setMoscardNipoRefCsvOutputOnerLogic(
			CsvOutputLogic csvOutputLogic) {
		this.moscardNipoRefCsvOutputOnerLogic = csvOutputLogic;
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