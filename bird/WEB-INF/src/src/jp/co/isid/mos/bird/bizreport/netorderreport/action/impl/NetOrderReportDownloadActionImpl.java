/**
 *
 */
package jp.co.isid.mos.bird.bizreport.netorderreport.action.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jodconverter.LocalConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.netorderreport.action.NetOrderReportDownloadAction;
import jp.co.isid.mos.bird.bizreport.netorderreport.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.netorderreport.dto.SearchDto;
import jp.co.isid.mos.bird.bizreport.netorderreport.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl.NetOrderReportDownloadLogicImpl;
import jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl.NetOrderReportExcelLogicImpl;
import jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl.NetOrderReportPdfLogicImpl;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.impl.ExcelOutputActionImpl;
import jp.co.isid.mos.bird.framework.action.impl.FileDownloadActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;

/**
 * ネット注文帳票画面
 * ACTION【照会画面アクション】インターフェース
 *
 */
public class NetOrderReportDownloadActionImpl implements  CommonAction, NetOrderReportDownloadAction {

	/** アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = "BBR019A01";
	/** アクションID：入力データ有効性チェック処理 */
    public static final String checkParamter_ACTION_ID = "BBR019A02";
    /** アクションID: EXCEL形式でダウンロード */
    public static final String downloadExcel_ACTION_ID = "BBR019A03";
    /** アクションID: PDF形式でダウンロード */
    public static final String downloadPdf_ACTION_ID = "BBR019A04";
	/** アクションID：条件入力画面へ戻る処理 */
    public static final String returnCondition_ACTION_ID = "BBR019A05";
    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	/** ネット注文帳票フォルダキー */
    private static final String KEY_TEMPLATE_FILE_PATH = "filePathNetOrderReport";
	/** ネット注文帳票フォルダキー */
    private static final String KEY_OFFICE_HOME_PATH = "officeHomePath";
    /** 排他ロックファイル名 */
    private static final String FILENAME_LOCK = "net_order.lock";

    /** LOGIC【条件部情報取得】 */
    private ConditionLogic conditionLogic;
    /** DTO【条件部情報】 */
    private SearchDto searchDto;
    /** ファイル名取得LOGIC */
    private NetOrderReportDownloadLogicImpl netorderReportDownloadLogic;
    /** ネット注文帳票Excelダウンロード */
    private NetOrderReportExcelLogicImpl excelOutputLogic;
	/** Excel出力Action */
	private ExcelOutputActionImpl excelAction = new ExcelOutputActionImpl();
	/** httpServletResponse */
	private HttpServletResponse httpServletResponse;
	/** PDFウンロードAction */
	private FileDownloadActionImpl fileDownloadAction ;
	/** PDFウンロードLOGIC */
	private NetOrderReportPdfLogicImpl netOrderReportPdfLogic;
	/** checkフラグ */
	private boolean checkFlg = true;
    /** LibreOffice Home Path */
    private static String officeHomePath;
    /** ロックファイル */
    private static String lockFileName;

	/**
     * 初期処理
     * @return null
     */
    public String initialize() {
        //１．プルダウンメニューから遷移された場合、下記の処理を行います。
    	if (getPullDownMenuDto().isClearFlg()) {
	        //1.BIRD共通DTO【メニュープルダウン情報】.クリアフラグへfalseを設定します。
	        getPullDownMenuDto().setClearFlg(false);
	    	searchDto.setBirdUserInfo(getBirdUserInfo());
	    	searchDto.setBirdDateInfo(getBirdDateInfo());
	    	searchDto.setDownloadFlg(false);
	    	searchDto.setDownloadExcelFlg(false);
	    	searchDto.setDownloadPdfFlg(false);
	    	//2.日報共通LOGIC【条件部情報取得】.実行を実行します。
	    	Map map = getConditionLogic().execute(getBirdUserInfo().getMstUser().getUserTypeCd(), getBirdUserInfo().getUserID(), getBirdDateInfo().getAppDate());
	    	int windowId=getSearchDto().initialize(map);
	        //【検索条件】.ウィンドウIDへ処理戻り値windowIdを設定します。
	    	getSearchDto().setWindowId(windowId);
	    	getSearchDto().copyDefaultParamerter(searchDto);
	    	officeHomePath = BirdContext.getProperty("fileProperty", KEY_OFFICE_HOME_PATH);
			lockFileName = BirdContext.getProperty("fileProperty", KEY_TEMPLATE_FILE_PATH) + FILE_SEPARATOR + FILENAME_LOCK;
    	}
    	return null;
    }

    /**
     * チェックOK場合、excelダウンロードフラグが'true'となる
     */
    public void checkDownloadExcel() {
    	// 入力パラメータチェック
    	checkParamater();
    	if(checkFlg) {
    		// チェックOK場合、ダウンロードフラグが'true'となる
    		getSearchDto().setDownloadExcelFlg(true);
    		getSearchDto().setDownloadFlg(true);
    	}
    }

    /**
     * EXCEL形式でダウンロード
     */
    public void downloadExcel()  {

    	// Excel出力内容を設定
    	ExcelOutputLogic logic = getExcelOutputLogic();
    	SearchDto searchDto = getSearchDto();
		excelAction.setExcelOutputDto(searchDto);
		excelAction.setExcelOutputLogic(logic);
		excelAction.setHttpServletResponse(httpServletResponse);

		// Excel出力
		excelAction.downloadExcel();

		//ロックファイルを削除する
		 unlock();

    }

	/**
	 * PDF形式でダウンロード
	 * @throws Exception
	 */
	public void checkDownloadPdf()  {
		checkParamater();
		if(checkFlg) {
			// チェックOK場合、ダウンロードフラグが'true'となる
			getSearchDto().setDownloadPdfFlg(true);
			getSearchDto().setDownloadFlg(true);
			getSearchDto().setDownPdfOnceFlg(true);
    	}
	}

	/**
	 * PDF形式でダウンロード
	 * @throws Exception
	 */
	public void  downloadPdf() {

		//1回だけダウンロードフラグが'false'となる
		getSearchDto().setDownPdfOnceFlg(false);
		getNetorderReportDownloadLogic().getTempFileName(getSearchDto());

    	// Excel出力内容を設定
    	ExcelOutputLogic logic = getExcelOutputLogic();
    	SearchDto searchDto = getSearchDto();
		excelAction.setExcelOutputDto(searchDto);
		excelAction.setExcelOutputLogic(logic);
		excelAction.setHttpServletResponse(httpServletResponse);

		// Excel保存
		excelAction.saveExcel();

		//excel転化pdf
		try {
			this.excelToPdf();
		} catch (OfficeException e) {
			// Excel転化Pdf失敗する
			e.printStackTrace();
			searchDto.setDownloadPdfFlg(false);
			// 一時保存したファイルを削除する
			new File(searchDto.getExcelFileNameTemp()).delete();
			new File(searchDto.getPdfFileNameTemp()).delete();
			//ロックファイルを削除する
			 unlock();

			throw new FileNotFoundException("ファイル出力が失敗しました。");
		}

		//pdfダウンロード
		fileDownloadAction.setDownloadLogic(getNetOrderReportPdfLogic());
		fileDownloadAction.setDownloadDto(searchDto);
		fileDownloadAction.download();

		// 一時保存したファイルを削除する
		new File(searchDto.getExcelFileNameTemp()).delete();
		new File(searchDto.getPdfFileNameTemp()).delete();

		//ロックファイルを削除する
		 unlock();

	}

	/**
	 * 戻る
	 */
	public String modoru() {

		//戻る場合、ダウンロードフラグが'false'となる
		searchDto.setDownloadExcelFlg(false);
		searchDto.setDownloadPdfFlg(false);
		searchDto.setDownloadFlg(false);
		return null;
	}

	/**
	 * 対象期間・期間指定入力チェック
	 */
	public void checkParamater() {

		SearchDto dto = getSearchDto();
        if (CommonUtil.isNull(dto.getTaishoKikanCd())) {
        	checkFlg = false;
            throw new NotNullException("対象期間");
        }
        boolean isDays = TaishoKikan.DAYS.equals(dto.getTaishoKikanCd());
        String kikanFrom =dto.getKikanFrom();
        String kikanTo = dto.getKikanTo();
        String prekiKanTo= this.getPreDate(kikanTo);

        //期間指定の場合
        if(isDays) {
        	// 期間指定FROM > 期間指定TOの場合
        	if(!this.validateDateFromTo(kikanFrom, kikanTo)) {
        		checkFlg = false;
        		throw new ConstraintsViolationException("期間指定はFROM <= TOで", EigyoNipoConstants.EMPTY);
        	}
        	// 指定可能な最長期間を1年間とする
        	if(this.validateDateFromTo2(kikanFrom,prekiKanTo)) {
        		checkFlg = false;
        		throw new GenericMessageException("期間指定は最長1年間までとしてください。");
        	}
        }

        //排他チェック
        checkLock();

	}

    /**
     * DTO【条件部情報】を取得する
     * @return searchDto DTO【条件部情報】
     */
	public SearchDto getSearchDto() {
		return searchDto;
	}

    /**
     * DTO【条件部情報】を設定する
     * @param searchDto DTO【条件部情報】
     */
	public void setSearchDto(SearchDto searchDto) {
		this.searchDto = searchDto;
	}

   /**
    *
    * @return
    */
   private S2Container getS2Container() {
       return SingletonS2ContainerFactory.getContainer();
   }

   /**
    * CTRL【BIRDユーザー情報】取得処理<p/>
    * @return birdUserInfo を戻します。
    */
   private BirdUserInfo getBirdUserInfo() {
       return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
   }
   /**
    * CTRL【BIRD日付情報】取得処理<p/>
    * @return birdDateInfo を戻します。
    */
   private BirdDateInfo getBirdDateInfo() {
       return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
   }

   /**
    * LOGIC【条件部情報取得】を取得する
    * @return LOGIC【条件部情報取得】
    */
   public ConditionLogic getConditionLogic() {
	   return conditionLogic;
   }
   /**
    * LOGIC【条件部情報取得】を設定する
    * @param conditionLogic LOGIC【条件部情報取得】
    */
   public void setConditionLogic(ConditionLogic conditionLogic) {
	   this.conditionLogic = conditionLogic;
   	}

   /**
    * ネット注文帳票Excelダウンロードを取得する
    * @return ネット注文帳票Excelダウンロード
    */
   public NetOrderReportExcelLogicImpl getExcelOutputLogic() {
		return excelOutputLogic;
	}

   /**
    * ネット注文帳票Excelダウンロードを設定する
    * @param excelOutputLogic ネット注文帳票Excelダウンロード
    */
	public void setExcelOutputLogic(NetOrderReportExcelLogicImpl excelOutputLogic) {
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
     * BIRD共通DTO【メニュープルダウン情報】取得処理
     * @return
     */
    private PullDownMenuDto getPullDownMenuDto() {
    	return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }

    /**
     * PDFウンロードLOGICを取得する
     * @return PDFウンロードLOGIC
     */
	public NetOrderReportPdfLogicImpl getNetOrderReportPdfLogic() {
		return netOrderReportPdfLogic;
	}

	/**
	 * PDFウンロードLOGICを設定する
	 * @param netOrderReportPdfLogic PDFウンロードLOGIC
	 */
	public void setNetOrderReportPdfLogic(NetOrderReportPdfLogicImpl netOrderReportPdfLogic) {
		this.netOrderReportPdfLogic = netOrderReportPdfLogic;
	}

	/**
	 * PDFウンロードActionを取得する
	 * @return PDFウンロードAction
	 */
	public FileDownloadActionImpl getFileDownloadAction() {
		return fileDownloadAction;
	}

	/**
	 * PDFウンロードActionを設定する
	 * @param fileDownloadAction PDFウンロードAction
	 */
	public void setFileDownloadAction(FileDownloadActionImpl fileDownloadAction) {
		this.fileDownloadAction = fileDownloadAction;
	}

	/**
	 * ファイル名取得LOGICを取得する
	 * @return ファイル名取得LOGIC
	 */
	public NetOrderReportDownloadLogicImpl getNetorderReportDownloadLogic() {
		return netorderReportDownloadLogic;
	}

	/**
	 * ファイル名取得LOGICを設定する
	 * @param netorderReportDownloadLogic ファイル名取得LOGIC
	 */
	public void setNetorderReportDownloadLogic(NetOrderReportDownloadLogicImpl netorderReportDownloadLogic) {
		this.netorderReportDownloadLogic = netorderReportDownloadLogic;
	}

	/**
	 * Excel転化Pdf
	 * @throws OfficeException
	 */
	private void excelToPdf() throws OfficeException {

		// pdf出力した時、作成したexcelファイル
		String saveFilePath = searchDto.getExcelFileNameTemp();
		// 転化後pdfファイル
		String pdfFilePath = searchDto.getPdfFileNameTemp();
		LocalOfficeManager officeManager = LocalOfficeManager.builder().officeHome(officeHomePath).build();
		LocalConverter converter = LocalConverter.make(officeManager);

		officeManager.start();
		try {
		   File excelFile = new File(saveFilePath);
		   File pdfFile = new File(pdfFilePath);
		   converter.convert(excelFile).to(pdfFile).execute();

		} finally {
		   officeManager.stop();
		}
	}

	 /**
     * 期間指定FROM、期間指定TOの整合性チェックをする
     * @param fromDt	期間指定From
     * @param toDt		期間指定To
     * @return boolean true：正常(FromDt ≦toDt)、flase:エラー
     */
    private boolean validateDateFromTo(String fromDt, String toDt) {

        if (Integer.parseInt(fromDt) - Integer.parseInt(toDt) > 0 ) {
        	return false;
        }
        return true;
    }

    /**
     * 期間指定TO一年前の時間をとる
     * @param fromTo
     * @return
     */
    private String getPreDate(String fromTo) {

    	String preDate=null;
    	try {
    		SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
			Date date = format.parse(fromTo);
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    cal.add(Calendar.YEAR,-1);
		     preDate= format.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return preDate;
    }

    /**
     * 期間指定FROM、一年前の期間指定TOの整合性チェックをする
     * @param fromDt
     * @param toDt
     * @return
     */
    private  boolean validateDateFromTo2(String fromDt, String toDt) {

        if (Integer.parseInt(fromDt) - Integer.parseInt(toDt) > 0 ) {
        	return false;
        }
        return true;
    }

	/**
	 * 排他処理チェック
	 */
	private void checkLock() {

		try {
			// ファイルチャンネル取得
	        File file = new File(lockFileName);

	        if (file.exists()) {
	        	if ((System.currentTimeMillis() - file.lastModified()) > 1000L*60*5 ) {
	        		// 5分以上経過していた場合、不正にファイルが残ってしまっているものと判断し、ファイル削除する。再作成。
	        		file.delete();
	        	} else {
		        	// EXCEPTIONの場合、ロックされていると処理中断
					checkFlg = false;
					throw new GenericMessageException("現在、他のユーザーにて帳票作成中です。しばらく経ってから再度実行してください。");
	        	}
	        }
	        // ロックファイル生成し、排他処理開始
	        file.createNewFile();

		} catch (Exception e) {
			checkFlg = false;
			throw new GenericMessageException("現在、他のユーザーにて帳票作成中です。しばらく経ってから再度実行してください。");
	    }

	}

    /**
     * ロックファイルを削除して、解除する
     * @return
     */    private void unlock() {
		// ファイルチャンネル取得
        File file = new File(lockFileName);

        if (file.exists()) {
        	file.delete();
        }
    }

}
