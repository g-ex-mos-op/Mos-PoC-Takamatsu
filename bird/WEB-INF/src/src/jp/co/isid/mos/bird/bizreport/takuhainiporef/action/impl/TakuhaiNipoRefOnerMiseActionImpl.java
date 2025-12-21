package jp.co.isid.mos.bird.bizreport.takuhainiporef.action.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultMapDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiNipoRefOnerMiseAction;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dto.TakuhaiNipoRefResultDto;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.MiseInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 宅配売上日報の結果画面(オーナー)アクション
 * 
 * @author xjung
 * @modifier xkinu 2013/01/31 海外売上集信対応
 * @modifier xkinu 2013/01/31 MOSCARD画面追加対応
 */
public class TakuhaiNipoRefOnerMiseActionImpl extends CsvOutput2ActionImpl	implements TakuhaiNipoRefOnerMiseAction {

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBR003A21";

    /** 再検索アクションID */
    public static final String reSearch_ACTION_ID = "BBR003A22";

    /** CSVダウンロードアクションID */
    public static final String downloadCsvOner_ACTION_ID = "BBR003A23";

    /** 支部リンクアクションID */
    public static final String selectedSibu_ACTION_ID = "BBR003A24";

    /** 営業日報タブリンクアクションID */
    public static final String goEigyoNipo_ACTION_ID = "BBR003A25";

    /** 屋号別売上タブリンクアクションID */
    public static final String goYago_ACTION_ID = "BBR003A26";
    /** アクション【MOSCARD画面遷移】ID */ 
    public static final String callMoscard_ACTION_ID  = "BBR003A27";
    /** アクション【ネット注文画面遷移】ID */
    public static final String callNetorder_ACTION_ID = "BBR003A28";
    
    /** 日報共通DTO【条件部情報】 */
    private NipoRefConditionDto nipoRefConditionDto;
    /** 日報共通DTO【検索条件】*/
    private NipoRefConditionParameterDto searchParamDto;
    /** 日報共通DTO【結果条件】*/
    private NipoRefConditionParameterDto resultParamDto;

	/** 宅配売上日報 結果情報DTO */
	private TakuhaiNipoRefResultDto takuhaiNipoRefResultDto;

	/** 店舗別宅配売上情報取得ロジック */	
	private MiseInfoLogic miseInfoLogic;

	/** 店コード */	
	private String miseCd;

	/** 別ウィンドウフラグ */
	private boolean newWindowFlg;
 
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
        //２．DTO【結果情報】.データ存在フラグがtrueで、売上情報が空の場合表示されていた画面の情報を復帰させます。
        if(takuhaiNipoRefResultDto.isExistDataFlg() 
        		&& takuhaiNipoRefResultDto.getEmptyMiseRstList())
        {
        	//0.日報共通DTO【検索条件】.ウィンドウIDを取得します。
        	int windowId = searchParamDto.getWindowId();
        	//1.DTO【Map結果情報】.日報共通DTO【結果条件】から処理0の値をキーに日報共通DTO【結果条件】を取得します。
        	NipoRefConditionParameterDto sessionResultParamDto = getTakuhaiNipoRefMapResultDto().getParameterDto(windowId);
        	//2.DTO【Map結果情報】.日報共通DTO【結果条件】から処理0の値をキーにDTO【結果情報】を取得します。
        	TakuhaiNipoRefResultDto resultDto = (TakuhaiNipoRefResultDto) getTakuhaiNipoRefMapResultDto().getResultDto(windowId);
        	//3.表示対象の日報共通DTO【結果条件】の条件と処理1の日報共通DTO【結果条件】の条件が一致した場合、
        	if(sessionResultParamDto != null && resultDto != null 
        			&& resultParamDto.equalsParams(sessionResultParamDto) )
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
    	//３．日報共通UTIL【共通処理】検索事前処理を実行します。
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
            //2.DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．店舗別売上情報取得を実行します。
        this.searchMiseInfo(searchParamDto);
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
     * 店舗別宅配売上情報を取得する
     * @param parameterDto DTO【検索条件】
     * @modifier xkinu 2013/01/31 海外売上集信対応
     */
    private void searchMiseInfo(NipoRefConditionParameterDto parameterDto) {
    	//１．日報共通UTIL【共通処理】検索事前処理を実行します。
    	//    引数:日報共通DTO【条件部情報】、日報共通DTO【結果条件】
    	NipoRefUtil.searchInitialize(getNipoRefConditionDto(), parameterDto, TaishoJoken.CODE_ONER);
    	//２．Try開始 検索処理を行います。
	    try {
            //1.LOGIC【売上情報取得】を実行し、Map[売上情報取得]を取得します。
	        Map resultMap = getMiseInfoLogic().execute(parameterDto);
	        //2.Map[売上情報取得]の対象店舗数の値をDTO【結果情報】.対象店舗数へ設定します。
        	String scnt = (String) resultMap.get(TakuhaiNipoConstants.MAP_TENPO_COUNT);
            takuhaiNipoRefResultDto.setTenpoCount(Integer.valueOf(scnt).intValue());          
            //3.Map[売上情報取得]の店舗別結果リストの値を
            //  DTO【結果情報】.List[[店舗別リスト(オーナー用)]]へ設定します。
        	takuhaiNipoRefResultDto.setMiseRstList(
	                (List) resultMap.get(TakuhaiNipoConstants.MAP_MISE_RST_LIST));          
	        //4.DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
			takuhaiNipoRefResultDto.setExistDataFlg(true);
	        //5.DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
	        takuhaiNipoRefResultDto.setKansanFlg(parameterDto.isKansan());
	        //6.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //7.DTO【Map結果情報】.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
            getTakuhaiNipoRefMapResultDto().setParameterDto(parameterDto);
	    	//8.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
	        //9.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	        searchParamDto.setResearchFlg(true);
		}
	    //３．NoResultExceptionをキャッチし、下記の処理を行います。
	    catch (NoResultException e){
	        //EX-0.日報共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	searchParamDto.setResearchFlg(true);
	        //EX-1.日報共通DTO【結果条件】へDTO【検索条件】を設定します。
	        setNipoRefResultParameterDto(parameterDto);
	        //EX-2.DTO【結果情報】.クリア処理を行います。
		    takuhaiNipoRefResultDto.clearMise();
	    	//EX-3.DTO【Map結果情報】.DTO【結果情報】へDTO【検索条件】.ウィンドウIDをキーに、
	        //DTO【結果情報】を格納します。
	        getTakuhaiNipoRefMapResultDto().setResultDto(
	        		parameterDto.getWindowId(), takuhaiNipoRefResultDto);
	    	//EX-4.NoResultExceptionを発生させます。
	        //該当データがありません。のメッセージを表示します。
			throw e;
	    }
		//４．BatchProcessingExceptionをキャッチし、下記の処理を行います。
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
	    //５．処理終了
    }

	/**
	 * 営業日報タブリンク
	 */
	public String goEigyoNipo() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．営業日報・オーナー用結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_E_ONER_MISE;
	}
	
	/**
	 * 屋号別売上タブリンク
	 */
	public String goYago() {
    	//１．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //２．屋号別売上日報・オーナー用結果画面VIEW_IDをリターンします。
        return NipoRefConstants.VIEW_ID_Y_ONER_MISE;
	}
	/* 
	 * アクション【MOSCARD画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiuNipoRefOnerConfirmAction#callMoscard()
	 */
	public String callMoscard() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //１．MOSCARDはサブ区分を画面間で共有ないため、DTO【結果条件】.サブタブ区分へ初期値としてBizReportConstants.SUB_TAG_0を設定。
        resultParamDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
        //２．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_M_ONER_MISE;
	}
	/* 
	 * アクション【ネット注文画面遷移】
	 * @see jp.co.isid.mos.bird.bizreport.takuhainiporef.action.TakuhaiuNipoRefOnerConfirmAction#callNetorder()
	 */
	public String callNetorder() {
		//０．日報共通UTIL【共通処理】他画面遷移時事前処理を実行します。
        NipoRefUtil.callScreenInitialize(
        		getNipoRefConditionDto(), searchParamDto, resultParamDto, isNewWindowFlg());
        //1．MOSCARD画面支部一覧画面VIEW_IDをリターンします。
		return NipoRefConstants.VIEW_ID_N_ONER_MISE;
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