package jp.co.isid.mos.bird.bizreport.netorderreport.dto;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.netorderreport.code.TaishoKikan;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;

/**
 * ネット注文帳票DTO
 * @author zzw
 *
 */
public class SearchDto  implements DownloadDto, CsvOutputDto, ExcelOutputDto{

	private String FILENAME = "ネット注文支部別TOP15";

	/**	対象期間情報リスト */
	private List taishoKikanList;
	/** 期間指定Fromリスト */
	private List kikanFromList;
	/** 期間指定Toリスト */
	private List kikanToList;
	/** 期間指定年月(yyyy/MM)リスト */
	private List kikanYMList;
	/** 対象期間情報 */
	private String taishoKikanCd = TaishoKikan.MONTH;
	/** 期間指定:対象年月 */
	private String kikanYM;
	/** 期間指定:期間指定From */
	private String kikanFrom;
	/** 期間指定:期間指定To */
	private String kikanTo;
    /** EXCLEのdataデータ */
    private List excelListData;
    /** EXCLEのdata_area_daiデータ */
    private List excelListAreaDai;
    /** ログインユーザー情報  */
    private BirdUserInfo birdUserInfo;
    /*** BIRD日付情報  */
    private BirdDateInfo birdDateInfo;
    /** excelダウンロードフラグ */
    private boolean downloadExcelFlg = false;
    /** pdfダウンロードフラグ */
    private boolean downloadPdfFlg = false;
    /** 画面の変化フラグ*/
    private boolean downloadFlg = false;
    /** 1回PDFダウンロードフラグ */
    private boolean downPdfOnceFlg = false;
    /** EXCELダウンロードファイルTEMP */
    private String excelFileNameTemp;
    /** PDFダウンロードファイルTEMP */
    private String pdfFileNameTemp;

    /**
     * 1回PDFダウンロードフラグを取得します
     * @return 1回PDFダウンロードフラグ
     */
	public boolean isDownPdfOnceFlg() {
		return downPdfOnceFlg;
	}

	/**
	 * 1回PDFダウンロードフラグを設定します
	 * @param downPdfOnceFlg 1回PDFダウンロードフラグ
	 */
	public void setDownPdfOnceFlg(boolean downPdfOnceFlg) {
		this.downPdfOnceFlg = downPdfOnceFlg;
	}

	/**
     * excelダウンロードフラグを取得します
     * @return excelダウンロードフラグ
     */
	public boolean isDownloadExcelFlg() {
		return downloadExcelFlg;
	}

	/**
	 * excelダウンロードフラグを設定します
	 * @param downloadExcelFlg excelダウンロードフラグ
	 */
	public void setDownloadExcelFlg(boolean downloadExcelFlg) {
		this.downloadExcelFlg = downloadExcelFlg;
	}

	/**
	 * pdfダウンロードフラグを取得します
	 * @return pdfダウンロードフラグ
	 */
	public boolean isDownloadPdfFlg() {
		return downloadPdfFlg;
	}

	/**
	 * pdfダウンロードフラグを設定します
	 * @param downloadPdfFlg pdfダウンロードフラグ
	 */
	public void setDownloadPdfFlg(boolean downloadPdfFlg) {
		this.downloadPdfFlg = downloadPdfFlg;
	}

	/**
	 * 画面の変化フラグを取得します
	 * @return 画面の変化フラグ
	 */
	public boolean isDownloadFlg() {
		//return isDownloadExcelFlg() || isDownloadPdfFlg();
		return downloadFlg;
	}

	/**
	 * 画面の変化フラグを設定します
	 * @param downloadFlg 画面の変化フラグ
	 */
	public void setDownloadFlg(boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
	}


	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo 設定する
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo 設定する
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}



    /**
     * 対象期間を取得する
     * @return 対象期間
     */
	public String getTaishoKikanCd() {
		return taishoKikanCd;
	}

//	ウィインドウ管理
	/**
	 * ウィインドウID
	 */
	private int windowId;

	/**
	 * MAXウィインドウID
	 */
	private int maxWindowId;

//	ウィンドウ管理getter/setter
	/**
	 * ウィンドウIDを取得する
	 * @return ウィインドウID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * ウィンドウIDを設定する
	 * @param windowId ウィンドウID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * MAXウィンドウIDを取得する
	 * @return MAXウィンドウID
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * MAXウィンドウIDを設定する
	 * @param maxWindowId MAXウィンドウID
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}


	/**
	 * ウィンドウIDを生成する
	 */
	public int updateWindowId() {
		int windowId = createWindowId();
		this.setWindowId(windowId);
		return windowId;
	}

	/**
	 * MAXウィンドウIDを生成する
     * @return int  MAXウィンドウID
	 */
	public int createWindowId() {
		int newWindowId = getMaxWindowId() + 1;
		setMaxWindowId(newWindowId);
		return newWindowId;
	}
    /**
     * 対象期間を設定する
     * @param taishoKikanCd 対象期間
     */
	public void setTaishoKikanCd(String taishoKikanCd) {
		this.taishoKikanCd = taishoKikanCd;
	}

    /**
     * 期間指定:対象年月を取得する
     * @return 期間指定:対象年月
     */
	public String getKikanYM() {
		return kikanYM;
	}

    /**
     * 期間指定:対象年月を設定する
     * @param kikanYM 期間指定:対象年月
     */
	public void setKikanYM(String kikanYM) {
		this.kikanYM = kikanYM;
	}

    /**
     * 期間指定:期間指定Fromを取得する
     * @return 期間指定:期間指定From
     */
	public String getKikanFrom() {
		return kikanFrom;
	}

    /**
     * 期間指定:期間指定Fromを設定する
     * @param kikanFrom 期間指定:期間指定From
     */
	public void setKikanFrom(String kikanFrom) {
		this.kikanFrom = kikanFrom;
	}

    /**
     * 期間指定:期間指定Toを取得する
     * @return 期間指定:期間指定To
     */
	public String getKikanTo() {
		return kikanTo;
	}

    /**
     * 期間指定:期間指定Toを設定する
     * @param kikanTo 期間指定:期間指定To
     */
	public void setKikanTo(String kikanTo) {
		this.kikanTo = kikanTo;
	}

    /**
     * 期間指定年月(yyyy/MM)リストを取得する
     * @return List 期間指定年月(yyyy/MM)リスト
     */
	public List getKikanYMList() {
		return kikanYMList;
	}

    /**
     * 期間指定年月(yyyy/MM)リストを設定する
     * @param kikanYMList 期間指定年月(yyyy/MM)リスト
     */
	public void setKikanYMList(List kikanYMList) {
		this.kikanYMList = kikanYMList;
	}

    /**
     * 対象期間リストを取得する
     * @return List 対象期間リスト
     */
	public List getTaishoKikanList() {
		return taishoKikanList;
	}

    /**
     * 対象期間リストを設定する
     * @param taishoKikanList 対象期間リスト
     */
	public void setTaishoKikanList(List taishoKikanList) {
		this.taishoKikanList = taishoKikanList;
	}

    /**
     * 期間指定Fromリストを取得する
     * @return List 期間指定Fromリスト
     */
	public List getKikanFromList() {
		return kikanFromList;
	}

    /**
     * 期間指定Fromリストを設定する
     * @param kikanFromList 期間指定Fromリスト
     */
	public void setKikanFromList(List kikanFromList) {
		this.kikanFromList = kikanFromList;
	}

    /**
     * 期間指定Toリストを取得する
     * @return List 期間指定Toリスト
     */
	public List getKikanToList() {
		return kikanToList;
	}

    /**
     * 期間指定Toリストを設定する
     * @param kikanToList 期間指定Toリスト
     */
	public void setKikanToList(List kikanToList) {
		this.kikanToList = kikanToList;
	}

	   /**
     * EXCLEデータを取得します。
     * @return EXCLEデータ
     */
    public List getExcelListData() {
        return excelListData;
    }

    /**
     * EXCLEデータを設定します。
     * @param excleList EXCLEデータ
     */
    public void setExcelListData(List excelListData) {
        this.excelListData = excelListData;
    }

    /**
     * EXCLEデータを取得します。
     * @return EXCLEデータ
     */
    public List getExcelListAreaDai() {
        return excelListAreaDai;
    }

    /**
     * EXCLEデータを設定します。
     * @param excleList EXCLEデータ
     */
    public void setExcelListAreaDai(List excelListAreaDai) {
        this.excelListAreaDai = excelListAreaDai;
    }

	public int initialize(Map mapData) {
    	//新ウィンドウIDを生成します。
    	int newWindowId = createWindowId();
		// 対象期間リスト
		setTaishoKikanList((List) mapData.get(BizReportConstants.TAISHO_KIKAN_LIST));
		// 期間指定年月日FROMリスト
		setKikanFromList((List) mapData.get(BizReportConstants.KIKAN_YMD_LIST));
		// 期間指定年月日TOリスト
		setKikanToList((List) mapData.get(BizReportConstants.KIKAN_YMD_LIST));
		// 期間指定年月リスト
		setKikanYMList((List) mapData.get(BizReportConstants.KIKAN_YM_LIST));
        setWindowId(newWindowId);
        //新ウィンドウIDをリターンします。
        return newWindowId;
	}

	public void copyDefaultParamerter(SearchDto paramDto) {
		//対象期間
		SelectItem item = (SelectItem)getTaishoKikanList().get(0);
		paramDto.setTaishoKikanCd((String)item.getValue());
		item = (SelectItem)getKikanFromList().get(0);
		paramDto.setKikanFrom((String)item.getValue());
		item = (SelectItem)getKikanToList().get(0);
		paramDto.setKikanTo((String)item.getValue());
		item = (SelectItem)getKikanYMList().get(0);
		paramDto.setKikanYM((String)item.getValue());
	}

	/**
	 * ファイル名取得
	 */
	public String getFileName(SearchDto searchDto) {
		String fileName = FILENAME;
		try {
			if(searchDto.isDownloadExcelFlg()) {
				fileName = new String(fileName.getBytes(),"iso-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(TaishoKikan.MONTH.equals(getTaishoKikanCd())) {
			fileName = fileName +"_"+ getKikanYM() ;
		}else if(TaishoKikan.DAYS.equals(getTaishoKikanCd())) {
			fileName = fileName +"_"+ getKikanFrom() + "-" + getKikanTo() ;
		}

		if(searchDto.isDownloadExcelFlg()) {
			fileName = fileName+ ".xlsx";
		}else if(searchDto.isDownloadPdfFlg()) {
			fileName = fileName +".pdf";
		}
		return fileName;
	}

	@Override
	public String getFileName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getExcelFileNameTemp() {
		return excelFileNameTemp;
	}

	public void setExcelFileNameTemp(String excelFileNameTemp) {
		this.excelFileNameTemp = excelFileNameTemp;
	}

	public String getPdfFileNameTemp() {
		return pdfFileNameTemp;
	}

	public void setPdfFileNameTemp(String pdfFileNameTemp) {
		this.pdfFileNameTemp = pdfFileNameTemp;
	}



}
