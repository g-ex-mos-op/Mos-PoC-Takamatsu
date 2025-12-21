package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefOnerConfirmAction;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dto.ResultDto;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.GroupEigyoNipoSearchLogic;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.UriageNipoInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
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
 * 営業日報の結果画面(オーナー)アクション
 *
 * @author   xjung
 * @modifier xkinu 2013/01/31 MOSCARD対応 MOSCARD画面へのタブを追加
 * @modifier xkinu 2013/01/31 海外売上集信対応 
 */
public class GroupEigyoNipoRefOnerConfirmActionImpl extends CsvOutput2ActionImpl implements GroupEigyoNipoRefOnerConfirmAction {
    /** アクションID:アクション【初期処理】 */
    public static final String initialize_ACTION_ID  = "BBR001A16";

    /** アクションID:アクション【検索】 */
    public static final String reSearch_ACTION_ID = "BBR001A17";

    /** アクションID:アクション【CSVダウンロード】 */
    public static final String downloadOnerCsv_ACTION_ID = "BBR001A18";
    
    /** アクションID:アクション【営業月報抽出CSVダウンロード】 */
    public static final String downloadGepoCsv_ACTION_ID = "BBR001A23";

    /** アクションID:アクション【個店ポータル画面遷移】 */
    public static final String selectedMise_ACTION_ID = "BBR001A19";

    /** 宅配売上日報タブリンクアクションID */
    public static final String goTakuhaiNipo_ACTION_ID = "BBR001A20";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR001A21";
    
    /** アクション【MOSCARD画面遷移】ID */
    public static final String callMoscard_ACTION_ID = "BBR001A33";
    
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR001A53";
    
    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;
    /** DTO【結果情報】 */
    private ResultDto groupEigyoNipoRefResultDto;

    /** 検索条件取得ロジック */ 
    private GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic;

    /** LOGIC【店別売上予算情報取得】 */ 
    private UriageNipoInfoLogic uriageNipoInfoLogic;

    /** 店コード */
    private String miseCd;

    /** 別ウィンドウフラグ */
    private boolean newWindowFlg;
    
    /** LOGIC【オーナー用CSVダウンロード】 */
    private CsvOutputLogic groupEigyoNipoOnerCsvOutputLogic;
    
/** 2008/10/6 追加 start *****************************************************/    
    /** LOGIC【営業月報抽出CSVダウンロード】 */
    private CsvOutputLogic groupEigyoNipoOnerGepoCsvOutputLogic;
    
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
	        groupEigyoNipoRefResultDto.setExistDataFlg(true);
        }
        //２。DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
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
        			&& resultParamDto.equalsParams(sessionResultParamDto)) {
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setGroupEigyoNipoRefResultDto(resultDto);
        	}
        	else {
        		try {
    	        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
                    this.searchOnerUriageInfo(resultParamDto);
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
        	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), resultParamDto, TaishoJoken.CODE_ONER);
        	//２．日報共通DTO【結果条件】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(resultParamDto);
        	//３．LOGIC【オーナー用CSVダウンロード】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getGroupEigyoNipoOnerCsvOutputLogic());
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
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), searchParamDto, TaishoJoken.CODE_ONER);
    	//２．Try開始　ダウンロード処理を行います。
        try {
        	//1.DTO【営業日報CSV出力用】を引数にCSV出力用DTO設定処理を行います。
        	setCsvOutputDto(searchParamDto);
        	//2,LOGIC【営業月報抽出CSVダウンロード】を引数に CSV出力用ロジック設定処理を行います。
        	setCsvOutputLogic(getGroupEigyoNipoOnerGepoCsvOutputLogic());
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
	        getGroupEigyoNipoRefMapResultDto().setResultDto(
	        		searchParamDto.getWindowId(), groupEigyoNipoRefResultDto);
	        //EX-3.DTO【結果情報】.クリア処理を行います。
	        groupEigyoNipoRefResultDto.clear();
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
        //２．宅配売上結果画面(オーナー用店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_T_ONER_MISE;
    }

    /**
     * アクション【屋号別売上画面遷移】
     */
    public String goYago() {
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．屋号別売上結果画面(オーナー用店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_ONER_MISE;
    }
 	/*
	 *  アクション【MOSCARD画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefOnerConfirmAction#callMoscard()
	 */
	public String callMoscard() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //１．MOSCARDはサブ区分を画面間で共有ないため、DTO【結果条件】.サブタブ区分へ初期値としてBizReportConstants.SUB_TAG_0を設定。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //２．MOSCARD画面店舗一覧画面(オーナー用店舗別)VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_M_ONER_MISE;
	}
	
	/*
	 *  アクション【ネット注文画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.groupeigyoniporef.action.GroupEigyoNipoRefOnerConfirmAction#callNetorder()
	 */
	public String callNetorder() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //１．ネット注文画面店舗一覧画面(オーナー用店舗別)VIEW_IDをリターンします。
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
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto, TaishoJoken.CODE_ONER);
        //２．Try開始 検索処理を行います。
        try {
	        //1.LOGIC【検索条件取得】を実行し、Map[検索条件]を取得します。
	        Map paramMap = getGroupEigyoNipoSearchLogic().getSearchData(parameterDto);
	 
	        //2.LOGIC【店別売上予算情報取得】を実行し、Map[店別売上予算情報]を取得します。
	        Map resultMap = getUriageNipoInfoLogic().execute(paramMap);
	        //3.Map[店別売上予算情報]の売上リストを変数List[[売上]]とします。
	        List uriageList = (List) resultMap.get(EigyoNipoConstants.MAP_URIAGE_LIST);
	        //4.Map[店別売上予算情報]の客数リストを変数List[[客数]]とします。
	        List kyakusuList = (List) resultMap.get(EigyoNipoConstants.MAP_KYAKUSU_LIST);
	        //5.変数List[[売上]]が０件の場合、NoResultExceptionを発生させます。
	        if ( uriageList == null || uriageList.size() == 0) {
	            throw new NoResultException(EigyoNipoConstants.EMPTY);
	        }
	        //6.変数List[[客数]]が０件の場合、NoResultExceptionを発生させます。
	        if ( kyakusuList == null || kyakusuList.size() == 0) {
	            throw new NoResultException(EigyoNipoConstants.EMPTY);
	        }
	        //7.DTO【結果情報】.List[[店別売上]]へ変数List[[売上]]を設定します。
	        groupEigyoNipoRefResultDto.setUriageList(uriageList);
	        //8.DTO【結果情報】.List[[店別客数]]へ変数List[[客数]]を設定します。
	        groupEigyoNipoRefResultDto.setKyakusuList(kyakusuList);
	        //9.DTO【結果情報】.対象店舗数へDTO【Map結果情報】から対象店舗数(オーナー)キーで取得した値intを設定します。
	        groupEigyoNipoRefResultDto.setTenpoCount(
	            ((Integer) resultMap.get(EigyoNipoConstants.MAP_ONER_TENPO_COUNT)).intValue());
	        //10.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        groupEigyoNipoRefResultDto.setExistDataFlg(true);
	        //11.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        groupEigyoNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
	        //12.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //13.DTO【Map結果情報】.日報共通DTO【結果条件】へDTO【検索条件】.ウィンドウIDをキーにDTO【検索条件】を設定します。
	        getGroupEigyoNipoRefMapResultDto().setParameterDto(parameterDto);
	        //14.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGroupEigyoNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), groupEigyoNipoRefResultDto);
	        //15.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	        searchParamDto.setResearchFlg(true);
	    }
        //３．NoResultExceptionをキャッチし、下記の処理を行います。
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
	    //４．BatchProcessingExceptionをキャッチし、下記の処理を行います。
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
	    //５．処理終了
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
     * @param groupEigyoNipoRefResultDto DTO【結果情報】
     */
    public void setGroupEigyoNipoRefResultDto(ResultDto groupEigyoNipoRefResultDto) {
        this.groupEigyoNipoRefResultDto = groupEigyoNipoRefResultDto;
    }
 
    /**
     * 営業日報結果情報DTOを取得する
     * @return ResultDto 営業日報結果情報DTO
     */
    public ResultDto getGroupEigyoNipoRefResultDto() {
        return groupEigyoNipoRefResultDto;
    }

    /**
     * 検索条件取得ロジックを取得する
     * @return GroupEigyoNipoSearchLogic 検索条件取得ロジック
     */
    public GroupEigyoNipoSearchLogic getGroupEigyoNipoSearchLogic() {
        return groupEigyoNipoSearchLogic;
    }

    /**
     *  検索条件取得ロジックを設定する
     * @param groupEigyoNipoSearchLogic 検索条件取得ロジック
     */
    public void setGroupEigyoNipoSearchLogic(GroupEigyoNipoSearchLogic groupEigyoNipoSearchLogic) {
        this.groupEigyoNipoSearchLogic = groupEigyoNipoSearchLogic;
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
     * LOGIC【営業月報抽出CSVダウンロード】
     * @return
     */
    public CsvOutputLogic getGroupEigyoNipoOnerGepoCsvOutputLogic() {
        return groupEigyoNipoOnerGepoCsvOutputLogic;
    }
    /**
     * LOGIC【営業月報抽出CSVダウンロード】
     * @param logic
     */
    public void setGroupEigyoNipoOnerGepoCsvOutputLogic(
    		CsvOutputLogic logic) {
        this.groupEigyoNipoOnerGepoCsvOutputLogic = logic;
    }
	/**
	 * LOGIC【オーナー用CSVダウンロード】
	 * @return クラス変数groupEigyoNipoOnerCsvOutputLogic を戻します。
	 */
	public CsvOutputLogic getGroupEigyoNipoOnerCsvOutputLogic() {
		return groupEigyoNipoOnerCsvOutputLogic;
	}

	/**
	 * LOGIC【オーナー用CSVダウンロード】
	 * @param logic を クラス変数groupEigyoNipoOnerCsvOutputLogicへ設定します。
	 */
	public void setGroupEigyoNipoOnerCsvOutputLogic(CsvOutputLogic logic) {
		this.groupEigyoNipoOnerCsvOutputLogic = logic;
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
}