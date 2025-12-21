package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.action.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dao.MstSibuInfoDao;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.action.GyotaibetuNipoRefOnerAction;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dto.GyotaibetuNipoRefResultDto;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIMiseListInfo;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoRefOnerLogic;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoSearchLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutputActionImpl;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 
 * @modifier xkinu 2013/01/31 海外売上集信対応
 * @modifier xkinu 2013/01/31 MOSCARD画面追加対応
 */
public class GyotaibetuNipoRefOnerActionImpl extends CsvOutput2ActionImpl implements GyotaibetuNipoRefOnerAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID   = ACTION_ID+"1"; 
    /** 実行アクションID */ 
    public static final String enforcement_ACTION_ID  = ACTION_ID+"2"; 
    /** 個店情報ポータルへ遷移アクションID */ 
    public static final String selectMise_ACTION_ID  = ACTION_ID+"3"; 
    /** アクション【ダウンロード】処理ID */ 
    public static final String downloadCsvOner_ACTION_ID  = ACTION_ID+"4"; 
    /** アクション【営業日報画面遷移】ID */ 
    public static final String dispEigyoNipo_ACTION_ID  = ACTION_ID+"5"; 
    /** アクション【宅配売上画面遷移】ID */ 
    public static final String dispTakuhai_ACTION_ID  = ACTION_ID+"6"; 
    /** アクション【MOSCARD画面遷移】ID */ 
    public static final String callMoscard_ACTION_ID  = ACTION_ID+"7";
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = ACTION_ID+"8";
   
	/** 別ウィンドウフラグ */
	private boolean newWindowFlg;
	
	/** 店コード */	
	private String miseCd;

    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;

    private GyotaibetuNipoRefResultDto gyotaibetuNipoRefResultDto;
    
    private MstSibuInfoDao mstSibuInfoDao;

    private GyotaibetuNipoSearchLogic gyotaibetuNipoSearchLogic;
    private GyotaibetuNipoRefOnerLogic gyotaibetuNipoRefOnerLogic;
    
    private CsvOutputActionImpl gyotaibetuNipoRefMiseCsvOutputAction;
    
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
            gyotaibetuNipoRefResultDto.setExistDataFlg(true);
        }
        //２． DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        if(gyotaibetuNipoRefResultDto.isExistDataFlg() 
        		&& gyotaibetuNipoRefResultDto.getEmptyListView())
        {
        	//0.日報共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】から処理0の値をキーに日報共通DTO【結果条件】を取得します。
        	NipoRefConditionParameterDto sessionResultParamDto = getGyotaibetuNipoRefMapResultDto().getParameterDto(windowId);
        	//2.DTO【Map結果情報】から処理0の値をキーにDTO【結果情報】を取得します。
        	GyotaibetuNipoRefResultDto resultDto = (GyotaibetuNipoRefResultDto) getGyotaibetuNipoRefMapResultDto().getResultDto(windowId);
        	//3.表示対象の日報共通DTO【結果条件】の条件と処理1の日報共通DTO【結果条件】の条件が一致した場合、
        	if(sessionResultParamDto != null && resultDto != null 
        			&& resultParamDto.equals(sessionResultParamDto) )
        	{
        		//DTO【結果情報】へ処理2のDTO【結果情報】を設定します。
        		setGyotaibetuNipoRefResultDto(resultDto);
        	}
        	else {
        		try {
		        	//4.処理3の条件に満たない場合は、セッションへ表示情報が存在しないと判断し再度検索処理として売上情報取得処理を実行します。
		            this.searchMiseList(resultParamDto);
		    		}
	    		catch (ApplicationException ex) {
	    			if(gyotaibetuNipoRefResultDto.getEmptyListView()) {
	    				//データが無い場合は
	    				gyotaibetuNipoRefResultDto.setExistDataFlg(false);
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
     * 検索処理の実行
     * 
     * @param parameterDto DTO【検索条件】
     * @modifier xkinu 2013/01/31 海外売上集信対応
     */
    private void searchMiseList(NipoRefConditionParameterDto parameterDto){
    	
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto, TaishoJoken.CODE_ONER);
    	//２．LOGIC【検索条件取得】を実行し、戻り値Map[検索条件]を取得します。
    	Map paramMap = getGyotaibetuNipoSearchLogic().getSearchData( parameterDto );
    	//３．Try開始 検索処理を行います。
		try{
            //1.LOGIC【オーナー売上情報取得】を実行し、List[[売上]]を取得します。
			List miseList = getGyotaibetuNipoRefOnerLogic().execute(paramMap);
	    			
    	    //2.LOGIC【オーナー売上情報取得】画面表示用を実行し、List[[表示用売上]]を取得します。
			//3.DTO【結果情報】.List[[検索結果情報]]へ処理2のList[[表示用売上]]を設定します。
			gyotaibetuNipoRefResultDto.setListView(
    	    		getGyotaibetuNipoRefOnerLogic().editViewDataList(miseList));
            
	        //4.下記の処理を実行し、対象店舗数を取得します。
	        int count = 0;
	        List countList = gyotaibetuNipoRefResultDto.getListView();
	        //count-1.処理2のList[[表示用売上]]で[表示用売上].店舗コードがnull出ない件数を集計します。
	    	for(int i = 0 ; i < countList.size() ; i++){
	    		UIMiseListInfo countData = (UIMiseListInfo) countList.get(i);
	    		if(countData.getMiseCd() != null){
	    			count++;
	    		}
	    	}
			//5.DTO【結果情報】.対象店舗数へ処理4の対象店舗数を設定します。
	    	gyotaibetuNipoRefResultDto.setTenpoCount(count);
	        //6.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
   	    	gyotaibetuNipoRefResultDto.setExistDataFlg(true);
	        //7.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
   	    	gyotaibetuNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
	        //8.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //9.日DTO【Map結果情報】.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        getGyotaibetuNipoRefMapResultDto().setParameterDto(parameterDto);
	    	//10.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGyotaibetuNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), gyotaibetuNipoRefResultDto);
	        //11.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	        searchParamDto.setResearchFlg(true);
            
   	    }
		//４．NoResultExceptionをキャッチし、下記の処理を行います。
		catch (NoResultException e){
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	    	//EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGyotaibetuNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), gyotaibetuNipoRefResultDto);
	        //EX-3.DTO【結果情報】.クリア処理を行います。
   	    	gyotaibetuNipoRefResultDto.clear();
        	//EX-4.NoResultExceptionを発生させます。
            //該当データがありません。のメッセージを表示します。
   	    	throw e;
        }
		//５．BatchProcessingExceptionをキャッチし、下記の処理を行います。
		catch (BatchProcessingException batchEx) {
	        //EX-1.DTO【結果情報】.クリア処理を行います。
		    	gyotaibetuNipoRefResultDto.clear();
	    	//EX-2.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getGyotaibetuNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), gyotaibetuNipoRefResultDto);
	    	//EX-3.キャッチしたBatchProcessingExceptionを発生させます。
	        throw batchEx;
		}
	    //６．処理終了
        
    }
    
    /**
     * 再検索処理の実行
     * @return
     */
    public String enforcement(){
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
        this.searchMiseList(searchParamDto);
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
        	//３．継承クラス.ダウンロード メイン処理を実行します。
        	super.downloadCsv();
        } catch (IOException e) {
        	//４．IOExceptionが発生した場合、FtlSystemExceptionを発生させます。
            throw new FtlSystemException(NipoRefConstants.MSG_CSV_DOWNLOAD, "", e);
        }
    }
    
    /**
     * 個店情報ポータルへ遷移
     */
    public String selectMise() {
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
     * 表示画面のコントロール
     * 営業日報タブへの切替
     *
     */
    public String dispEigyoNipo(){
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．宅配売上結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_E_ONER_MISE;
    }
    /**
     * 表示画面のコントロール
     * 宅配別タブへの切替
     */
    public String dispTakuhai(){
    	
		//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．宅配売上結果画面(店舗別)VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_T_ONER_MISE;
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
        //２．MOSCARD画面店舗一覧画面(オーナー用店舗別)VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_N_ONER_MISE;
	}

	public MstSibuInfoDao getMstSibuInfoDao() {
		return mstSibuInfoDao;
	}

	public void setMstSibuInfoDao(MstSibuInfoDao mstSibuInfoDao) {
		this.mstSibuInfoDao = mstSibuInfoDao;
	}

    /**
     * commonCodeDtoの設定
     * @return
     */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

	public boolean isNewWindowFlg() {
		return newWindowFlg;
	}

	public void setNewWindowFlg(boolean newWindowFlg) {
		this.newWindowFlg = newWindowFlg;
	}

	public String getMiseCd() {
		return miseCd;
	}

	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	public GyotaibetuNipoRefResultDto getGyotaibetuNipoRefResultDto() {
		return gyotaibetuNipoRefResultDto;
	}

	public void setGyotaibetuNipoRefResultDto(
			GyotaibetuNipoRefResultDto gyotaibetuNipoRefResultDto) {
		this.gyotaibetuNipoRefResultDto = gyotaibetuNipoRefResultDto;
	}

	public CsvOutputActionImpl getGyotaibetuNipoRefMiseCsvOutputAction() {
		return gyotaibetuNipoRefMiseCsvOutputAction;
	}

	public void setGyotaibetuNipoRefMiseCsvOutputAction(
			CsvOutputActionImpl gyotaibetuNipoRefMiseCsvOutputAction) {
		this.gyotaibetuNipoRefMiseCsvOutputAction = gyotaibetuNipoRefMiseCsvOutputAction;
	}
	public GyotaibetuNipoRefOnerLogic getGyotaibetuNipoRefOnerLogic() {
		return gyotaibetuNipoRefOnerLogic;
	}

	public void setGyotaibetuNipoRefOnerLogic(
			GyotaibetuNipoRefOnerLogic gyotaibetuNipoRefOnerLogic) {
		this.gyotaibetuNipoRefOnerLogic = gyotaibetuNipoRefOnerLogic;
	}

	public GyotaibetuNipoSearchLogic getGyotaibetuNipoSearchLogic() {
		return gyotaibetuNipoSearchLogic;
	}

	public void setGyotaibetuNipoSearchLogic(
			GyotaibetuNipoSearchLogic gyotaibetuNipoSearchLogic) {
		this.gyotaibetuNipoSearchLogic = gyotaibetuNipoSearchLogic;
	}

	public NipoRefConditionDto getNipoRefConditionDto() {
		return nipoRefConditionDto;
	}

	public void setNipoRefConditionDto(NipoRefConditionDto nipoRefConditionDto) {
		this.nipoRefConditionDto = nipoRefConditionDto;
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
    private NipoRefResultMapDto gyotaibetuNipoRefMapResultDto;
	/**
	 * DTO【Map結果情報】
	 * @return クラス変数gyotaibetuNipoRefMapResultDto を戻します。
	 */
	public NipoRefResultMapDto getGyotaibetuNipoRefMapResultDto() {
		return gyotaibetuNipoRefMapResultDto;
	}
	/**
	 * DTO【Map結果情報】
	 * @param mapResultDto を クラス変数gyotaibetuNipoRefMapResultDtoへ設定します。
	 */
	public void setGyotaibetuNipoRefMapResultDto(
			NipoRefResultMapDto mapResultDto) {
		this.gyotaibetuNipoRefMapResultDto = mapResultDto;
	}
}
