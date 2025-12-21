package jp.co.isid.mos.bird.storeinfo.miseinformationextract.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.action.impl.ExcelOutputActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.action.MiseInfoExtractAction;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.SvInfoDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dto.MiseInfoExtractDto;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.SvInfo;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic.TargetSiBuSearchLogic;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic.impl.MiseInfoMationExtractExcelLogicImpl;

/**
 * 店マスタ情報一括抽出アクション
 * @author   boukoumei
 */
public class MiseInfoExtractActionImpl extends CsvOutput2ActionImpl implements MiseInfoExtractAction{
    public static String initialize_ACTION_ID   = "BSI008A01";
    public static String downloadCsv_ACTION_ID  = "BSI008A02";
    public static String downloadExcel_ACTION_ID = "BSI008A03";

    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";
    /** VIEW_ID：店マスタ情報一括抽出 */
    public static final String VIEW_MISE_INFO_OUT   = "BSI008V01";

    /** SV選択画面 */
    private SvSearchDto newSvSearchDto;
    /** 支部取得LOGIC【対象支部情報取得】 */
    private TargetSiBuSearchLogic targetSiBuSearchLogic;
    private MiseInfoMationExtractExcelLogicImpl excelOutputLogic;
    /** 店マスタ情報一括抽出画面Dto */
    private MiseInfoExtractDto miseInfoExtractDto;
    /** 初期化画面時、対応項目初期値を設定する */
    private static String closeFlg = "outClose";
    private static boolean katekoriCheck2 = false;
    private static boolean katekoriCheck3 = false;
    private HttpServletResponse httpServletResponse;
	/** Action */
	private ExcelOutputActionImpl excelAction = new ExcelOutputActionImpl();
	/** dao */
	private SvInfoDao svInfoDao;

	/**
     * アクション【初期化処理】
     */
    public String initialize() {

    	 //１．プルダウンメニューから遷移された場合、下記の処理を行います。
        if (getPullDownMenuDto().isClearFlg()) {
        	getPullDownMenuDto().setClearFlg(false);

        	closeFlg="outClose";
        	katekoriCheck2 = false;
        	katekoriCheck3 = false;
        	miseInfoExtractDto.setSvCd("");
        	miseInfoExtractDto.setShukeiKbnCd("SIBU_CD");
        	miseInfoExtractDto.setShukeiKbnList(ShukeiKbn.getPullDownList());
        	// 支部コードリストを取得する
            getListTaishoSibu(miseInfoExtractDto,false);
        }
        /*２．SV選択画面から遷移してきた場合。2008/12/09追加 **************************/
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
        	//１.Try開始 SV選択画面からの値などを設定します。
        	try {
	            //SV-1．BIRD共通DTO【SV選択画面】.ウィンドウIDを取得します。
	        	int windowId = getNewSvSearchDto().getWindowId();
	            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
	                //SV-SELECT-1．日報共通DTO【検索条件】.SVコードへBIRD共通DTO【SV選択画面】.SVコードを設定します。
	            	miseInfoExtractDto.setSibuCd("");
	            	miseInfoExtractDto.setSvCd(getNewSvSearchDto().getSvCd());

	                //SV-SELECT-4.店舗別画面VIEW_IDをリターンします。(店マスタ情報一括抽出画面へ遷移)
	                return VIEW_MISE_INFO_OUT;
	            }
        	}
        	//２.FINALLYで下記の処理を行います。
        	finally {
                //FINAL-1．BIRD共通DTO【SV選択画面】.遷移区分を初期値に戻す。
                getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
                //FINAL-2．BIRD共通DTO【SV選択画面】.クリア処理を実行します。
                getNewSvSearchDto().clear();
            }
        }

        // ３．画面更新後、前回選択値を設定する
        miseInfoExtractDto.setCloseFlg(closeFlg);
        miseInfoExtractDto.setKatekoriCheck2(katekoriCheck2);
        miseInfoExtractDto.setKatekoriCheck3(katekoriCheck3);

        // ４．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * アクション【SV検索ボタン】処理
     *
     * @return SV検索フォームViewID
     */
    public String callSvForm()
    {
        //１．BIRD共通DTO【SV選択画面】.遷移元情報へ店舗別結果画面(本部用)VIEW_IDを設定します。
        getNewSvSearchDto().setNavigationCase(VIEW_MISE_INFO_OUT);
        //２．BIRD共通DTO【SV選択画面】.初期化フラグへtrueを設定します。
        getNewSvSearchDto().setInitFlag(true);
        //３．BIRD共通DTO【SV選択画面】.ウィンドウID　へ 日報共通DTO【検索条件】.ウィンドウIDを設定します。
        getNewSvSearchDto().setWindowId(miseInfoExtractDto.getWindowId());
        //４．List[[会社]]を作成し日報共通DTO【検索条件】.会社コードを設定します。
        List listCompany = new ArrayList();
        listCompany.add(miseInfoExtractDto.getCompanyCd());
        //５．BIRD共通DTO【SV選択画面】.List[[会社]]へ処理４のList[[会社]]を設定します。
        getNewSvSearchDto().setRCompanyCdList(listCompany);
    	//６．画面更新前、前回選択値を保存する
    	setViewSelDate();
        miseInfoExtractDto.setSvCd(newSvSearchDto.getSvCd());
        //７．SV選択画面遷移VIEWIDをリターンする。
        return VIEW_ID_SVSEARCH;
    }

    /**
     * アクション【ダウンロードCSV】
     */
    public void downloadCsv() throws IOException{
    	// SV名称を取得する
    	getSvName();

    	// 出力用画面からのパラメータを取得し、ＤＴＯに設定する
		S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");;
        miseInfoExtractDto.setShukeiKbnCd((String)request.getParameter("miseInfoExtractCondition:shukeiKbnCd"));
        miseInfoExtractDto.setSibuCd((String)request.getParameter("miseInfoExtractCondition:sibuCd"));
        miseInfoExtractDto.setSvCd((String)request.getParameter("miseInfoExtractCondition:svCd"));
        miseInfoExtractDto.setKatekoriCheck2(Boolean.valueOf(request.getParameter("miseInfoExtractCondition:katekoriCh2")));
        miseInfoExtractDto.setKatekoriCheck3(Boolean.valueOf(request.getParameter("miseInfoExtractCondition:katekoriCh3")));

    	// 画面更新前、前回選択値を保存する
    	setViewSelDate();

        // CSVダウンロード
        try {
        	//３．継承クラス.ダウンロード メイン処理を実行します。
            super.downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
    }

    /**
     * アクション【ダウンロードExcel】
     */
    public void downloadExcel() throws IOException{
    	// 入力パラメータをチェックする
    	checkParamater();

    	// SV名称を取得する
    	getSvName();

		// Excel出力内容を設定
    	ExcelOutputLogic logic = getExcelOutputLogic();
		MiseInfoExtractDto dto = getMiseInfoExtractDto();
		excelAction.setExcelOutputDto(dto);
		excelAction.setExcelOutputLogic(logic);
		excelAction.setHttpServletResponse(httpServletResponse);

    	// 画面更新前、前回選択値を保存する
    	setViewSelDate();

		// Excel出力
		excelAction.downloadExcel();
    }

    /**
     * アクション【対象支部プルダウンリスト切替】
     * @return String 遷移先ビューID
     */
    public String reSearchTaishoSibuList() {
    	// １．画面更新前、前回選択値を保存する
    	setViewSelDate();
    	// ２．結果条件の入力チェックを行います
    	getListTaishoSibu(miseInfoExtractDto,false);
        miseInfoExtractDto.setSibuCd("");
        // ３．nullをリターンします。(現行画面へ遷移)
        return null;
    }

    /**
     * 対象(担当)支部リスト取得処理
     *
     * @param parameterDto DTO【検索条件】
     * @param isExThrow 例外発生フラグ true:Exceptionをスルーします。
     * @return
     */
    private void getListTaishoSibu(MiseInfoExtractDto parameterDto, boolean isExThrow) {
        String shukeiKbn = parameterDto.getShukeiKbnCd();
        //１．変数List[[支部リスト]]を作成。
        List sibuList = null;
		//２．対象支部リストを取得します。
    	sibuList = getTargetSiBuSearchLogic().execute(shukeiKbn);
		//List[[対象支部]]へ変数List[[支部リスト]]を設定します。
        parameterDto.setTaishoSibuList(sibuList);
        miseInfoExtractDto.setSibuCd("");
    }

    /**
     * 画面更新時、前回選択値を保存する
     */
    private void setViewSelDate(){
    	closeFlg = miseInfoExtractDto.getCloseFlg();
    	katekoriCheck2 = miseInfoExtractDto.getKatekoriCheck2();
    	katekoriCheck3 = miseInfoExtractDto.getKatekoriCheck3();
    }

	/**
	 * 入力パラメータのチェックを実行する
	 */
	private void checkParamater() {
		if (CommonUtil.isNull(miseInfoExtractDto.getShukeiKbnCd())) {
			throw new NotNullException("集計区分");
		}

		/* 集計区分で『SV指定(担当店一覧)』が選択された場合 */
		if (miseInfoExtractDto.isSvFlg()) {
			if (CommonUtil.isNull(miseInfoExtractDto.getSvCd())) {
				throw new NotNullException("SVコード");
			}
		}

		// 支部(店舗一覧)検索の場合
		else if (TaishoJoken.CODE_SIBU.equals(miseInfoExtractDto.getTaishoJoken())) {
			if (!miseInfoExtractDto.isSvFlg()) {
				// 支部コードの必須チェックを行います。
				if (CommonUtil.isNull(miseInfoExtractDto.getSibuCd())) {
					throw new NotNullException("対象支部");
				}
			}
		}
	}

	/**
	 * SVコードにより、SV名称を取得する
	 */
	private void getSvName()
	{
		if(miseInfoExtractDto.getSvCd() != null && !miseInfoExtractDto.getSvCd().isEmpty())
		{
	        // SV名称を取得
			SvInfo svInfoEntity = getSvInfoDao().selectSvInfo(miseInfoExtractDto.getSvCd());
	        if(svInfoEntity!= null){
	        	miseInfoExtractDto.setSvName(svInfoEntity.getSvNameKj());
	        }
		}
	}

    /**
     * SV選択画面を取得します。
     * @return SV選択画面
     */
    public SvSearchDto getNewSvSearchDto() {
        return newSvSearchDto;
    }

    /**
     * SV選択画面を設定します。
     * @param newSvSearchDto SV選択画面
     */
    public void setNewSvSearchDto(SvSearchDto newSvSearchDto) {
        this.newSvSearchDto = newSvSearchDto;
    }

    /**
     * BIRD共通DTO【メニュープルダウン情報】取得処理
     * @return
     */
    private PullDownMenuDto getPullDownMenuDto() {
    	return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
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
     * CTRL【BIRD日付情報】取得処理
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * LOGIC【対象支部情報取得】を取得する
     * @return TargetSiBuSearchLogic LOGIC【対象支部情報取得】
     */
    public TargetSiBuSearchLogic getTargetSiBuSearchLogic() {
        return targetSiBuSearchLogic;
    }

    /**
     * LOGIC【対象支部情報取得】を設定する
     * @param targetSiBuSearchLogic LOGIC【対象支部情報取得】
     */
    public void setTargetSiBuSearchLogic(TargetSiBuSearchLogic targetSiBuSearchLogic) {
        this.targetSiBuSearchLogic = targetSiBuSearchLogic;
    }

    /**
     * 店マスタ情報一括抽出画面Dtoを取得します。
     * @return 店マスタ情報一括抽出画面Dto
     */
    public MiseInfoExtractDto getMiseInfoExtractDto() {
        return miseInfoExtractDto;
    }

    /**
     * 店マスタ情報一括抽出画面Dtoを設定します。
     * @param miseInfoExtractDto 店マスタ情報一括抽出画面Dto
     */
    public void setMiseInfoExtractDto(MiseInfoExtractDto miseInfoExtractDto) {
        this.miseInfoExtractDto = miseInfoExtractDto;
    }

    /**
	 * @return excelOutputLogic
	 */
	public MiseInfoMationExtractExcelLogicImpl getExcelOutputLogic() {
		return excelOutputLogic;
	}

	/**
	 * @param excelOutputLogic セットする excelOutputLogic
	 */
	public void setExcelOutputLogic(MiseInfoMationExtractExcelLogicImpl excelOutputLogic) {
		this.excelOutputLogic = excelOutputLogic;
	}

    /**
	 * @return httpServletResponse
	 */
	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	/**
	 * @param httpServletResponse セットする httpServletResponse
	 */
	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	/**
	 * @return svInfoDao
	 */
	public SvInfoDao getSvInfoDao() {
		return svInfoDao;
	}

	/**
	 * @param svInfoDao セットする svInfoDao
	 */
	public void setSvInfoDao(SvInfoDao svInfoDao) {
		this.svInfoDao = svInfoDao;
	}
}
