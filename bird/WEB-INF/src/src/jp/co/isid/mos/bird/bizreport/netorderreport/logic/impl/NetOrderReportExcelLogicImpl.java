package jp.co.isid.mos.bird.bizreport.netorderreport.logic.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.isid.mos.bird.bizreport.netorderreport.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.netorderreport.dao.NetOrderReportDataDao;
import jp.co.isid.mos.bird.bizreport.netorderreport.dto.NetOrderReportDownloadDto;
import jp.co.isid.mos.bird.bizreport.netorderreport.dto.SearchDto;
import jp.co.isid.mos.bird.bizreport.netorderreport.entity.NetOrderReport;
import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * ネット注文帳票Excelダウンロード
 * @author zzw
 *
 */
public class NetOrderReportExcelLogicImpl implements ExcelOutputLogic{

    /* ロジックID */
    public static final String LOGIC_ID = "BBR019L03";

	//ネット注文帳票DTO
	private SearchDto searchDto;

	//excelテンプレートを取るLOGIC
	private NetOrderReportDownloadLogicImpl netorderReportDownloadLogic;

	//excelテンプレートを取るDTO
	private NetOrderReportDownloadDto netorderReportDownloadDto;

	//ネット注文帳票情報の取得インターフェース
	private NetOrderReportDataDao netOrderReportDataDao;

	//sql検索時の開始日
	private String dateFrom = null;

	//sql検索時の終了日
	private String dateTo = null;

	/**
	 * ファイル名取得
	 */
	public String getFileName(ExcelOutputDto excelOutputDto) {
		SearchDto searchDto = (SearchDto) excelOutputDto;
		if(searchDto.isDownloadExcelFlg()) {
			//excel名
			return searchDto.getFileName(searchDto);
		}else if (searchDto.isDownloadPdfFlg()) {
			// 一時保存したExcelファイル名
			return searchDto.getExcelFileNameTemp();
		}else {
			return null;
		}
	}

	/**
	 * Workbook読込処理
	 */
	public Workbook loadWorkbook(ExcelOutputDto excelOutputDto) throws IOException {

		SearchDto searchDto = (SearchDto) excelOutputDto;
		String path=getNetorderReportDownloadLogic().getFileFullPath(searchDto);
		File file = new File(path);

        // ファイル存在チェック
        if (!file.exists()) {
        	//searchDto.setDownloadFlg(false);
        	searchDto.setDownloadExcelFlg(false);
        	searchDto.setDownloadPdfFlg(false);
            throw new FileNotFoundException("ファイル出力が失敗しました。");
        }

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        return workbook;
	}

	/**
	 * 出力データ作成処理
	 */
	public Workbook setOutputData(Workbook workbook, ExcelOutputDto excelOutputDto) {

		SearchDto dto=(SearchDto) excelOutputDto;

    	if (dto.isDownloadPdfFlg()) {     //PDFの場合
    		//シートを取得する(data､data_area_dai)
        	Sheet sheet = workbook.getSheet("data");

    		// データ取得用パラメータを設定する
    		setParamaterForGetData(dto);

        	// 出力データ取得
        	List excelListData = getNetOrderReportDataDao().selectNetOrderReport(dateFrom,dateTo, "data");
        	dto.setExcelListData(excelListData);

        	try {
        		// Excelのdataシート、ヘッダ部を作成
        		outputHeader(workbook,sheet,dto);
        		// Excelのdataシート、データ部を作成する
        		outputData(workbook, sheet, dto);
    		} catch (Exception ex) {
    			ex.printStackTrace();
    			dto.setDownloadExcelFlg(false);
    			dto.setDownloadPdfFlg(false);
    			throw new FileNotFoundException("ファイル出力が失敗しました。");
    		}

    		// EXCELファイルの自動計算フラグをTRUEにする
    		workbook.setForceFormulaRecalculation(true);
        	if (dto.isDownloadPdfFlg()) {
        		// PDF作成する前に、変換元EXCELファイルの再計算を実行する
    			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
    		}
    		return workbook;

    	} else {    		//EXCELの場合
    		//シートを取得する(data､data_area_dai)
        	Sheet sheet = workbook.getSheet("data");
        	Sheet sheet_areaDai = workbook.getSheet("data_area_dai");

    		// データ取得用パラメータを設定する
    		setParamaterForGetData(dto);

        	// 出力データ取得
        	List excelListData = getNetOrderReportDataDao().selectNetOrderReport(dateFrom,dateTo, "data");
        	List excelListArea = getNetOrderReportDataDao().selectNetOrderReport(dateFrom,dateTo, "data_area_dai");
        	dto.setExcelListData(excelListData);
        	dto.setExcelListAreaDai(excelListArea);

        	try {
        		// Excelのdataシート、ヘッダ部を作成
        		outputHeader(workbook,sheet,dto);
        		// Excelのdataシート、データ部を作成する
        		outputData(workbook, sheet, dto);

        		// Excelのdata_area_daiシート、ヘッダ部を作成
        		outputHeader(workbook,sheet_areaDai,dto);
        		// Excelのdata_area_daiシート、データ部を作成する
        		outputData(workbook, sheet_areaDai, dto);

    		} catch (Exception ex) {
    			ex.printStackTrace();
    			dto.setDownloadExcelFlg(false);
    			dto.setDownloadPdfFlg(false);
    			throw new FileNotFoundException("ファイル出力が失敗しました。");
    		}

    		// EXCELファイルの自動計算フラグをTRUEにする
    		workbook.setForceFormulaRecalculation(true);
        	if (dto.isDownloadPdfFlg()) {
        		// PDF作成する前に、変換元EXCELファイルの再計算を実行する
    			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
    		}
    		return workbook;
    	}

	}

	/**
	 * Excelファイルのヘッダ部を作成する
	 * @param book
	 * @param sheet
	 * @param dto
	 */
	 public void outputHeader(Workbook book, Sheet sheet,SearchDto dto) {
			// ヘッダの2行目:出力日：
			Row headerRow2 = sheet.getRow(1);
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy/M/d");
			addDataCell(headerRow2,10,format.format(date));

			// ヘッダの3行目:出力期間：
			Row headerRow3 = sheet.getRow(2);
			addDataCell(headerRow3,10,getExcelTaishoKikan(dto));
	 }

	 /**
	  * Excelファイルのデータ部を作成
	  * @param book
	  * @param sheet
	  * @param dto
	  */
	 public void outputData (Workbook book, Sheet sheet,SearchDto dto) {

		 int startRow = 4;
		 List exceList = null;
		 if(sheet.getSheetName().equals("data")) {
			 exceList = dto.getExcelListData();
		 }else if(sheet.getSheetName().equals("data_area_dai")) {
			 exceList = dto.getExcelListAreaDai();
		 }
		 if(exceList != null) {
			 for (Iterator ite = exceList.iterator(); ite.hasNext();) {
				//ネット注文帳票情報を作成する
				NetOrderReport entityData= (NetOrderReport) ite.next();
				// 基本情報(※表示必須)
				int startColumn = 9;
				Row row = sheet.getRow(startRow);
				//店情報
				addDataCell(row,startColumn++,entityData.getJigyouCd());
				addDataCell(row,startColumn++,entityData.getJigyouName());
				addDataCell(row,startColumn++,entityData.getSlareaCd());
				addDataCell(row,startColumn++,entityData.getSlareaName());
				addDataCell(row,startColumn++,entityData.getSibuCd());
				addDataCell(row,startColumn++,entityData.getSibuName());
				addDataCell(row,startColumn++,entityData.getAreaDai());
				addDataCell(row,startColumn++,entityData.getAreaName());
				addDataCell(row,startColumn++,entityData.getMiseCd());
				addDataCell(row,startColumn++,entityData.getMiseNameKj());
				addDataCell(row,startColumn++,entityData.getOnerCd());
				addDataCell(row,startColumn++,entityData.getOnerNameKj());
				//金額情報
				addIntDataCell(row,startColumn++,entityData.getOtherKin7());
				addIntDataCell(row,startColumn++,entityData.getOtherKin8());
				addIntDataCell(row,startColumn++,entityData.getEatKin());
				addIntDataCell(row,startColumn++,entityData.getTakeKin());
				addIntDataCell(row,startColumn++,entityData.getTelKin());
				addIntDataCell(row,startColumn++,entityData.getDriveKin());
				addIntDataCell(row,startColumn++,entityData.getOtherKin1());
				addIntDataCell(row,startColumn++,entityData.getOtherKin2());

				//件数情報
				addIntDataCell(row,startColumn++,entityData.getOtherKen7());
				addIntDataCell(row,startColumn++,entityData.getOtherKen8());
				addIntDataCell(row,startColumn++,entityData.getEatKen());
				addIntDataCell(row,startColumn++,entityData.getTakeKen());
				addIntDataCell(row,startColumn++,entityData.getTelKen());
				addIntDataCell(row,startColumn++,entityData.getDriveKen());
				addIntDataCell(row,startColumn++,entityData.getOtherKen1());
				addIntDataCell(row,startColumn++,entityData.getOtherKen2());

				//前年金額情報
				addIntDataCell(row,startColumn++,entityData.getZenOtherKin7());
				addIntDataCell(row,startColumn++,entityData.getZenOtherKin8());
				addIntDataCell(row,startColumn++,entityData.getZenEatKin());
				addIntDataCell(row,startColumn++,entityData.getZenTakeKin());
				addIntDataCell(row,startColumn++,entityData.getZenTelKin());
				addIntDataCell(row,startColumn++,entityData.getZenDriveKin());
				addIntDataCell(row,startColumn++,entityData.getZenOtherKin1());
				addIntDataCell(row,startColumn++,entityData.getZenOtherKin2());

				//前年件数情報
				addIntDataCell(row,startColumn++,entityData.getZenOtherKen7());
				addIntDataCell(row,startColumn++,entityData.getZenOtherKen8());
				addIntDataCell(row,startColumn++,entityData.getZenEatKen());
				addIntDataCell(row,startColumn++,entityData.getZenTakeKen());
				addIntDataCell(row,startColumn++,entityData.getZenTelKen());
				addIntDataCell(row,startColumn++,entityData.getZenDriveKen());
				addIntDataCell(row,startColumn++,entityData.getZenOtherKen1());
				addIntDataCell(row,startColumn++,entityData.getZenOtherKen2());

				startRow++;
			 }
		 }
	 }


	/**
	 * ネット注文帳票情報の取得インターフェースを取得します
	 * @return ネット注文帳票情報の取得インターフェース
	 */
	public NetOrderReportDataDao getNetOrderReportDataDao() {
		return netOrderReportDataDao;
	}

	/**
	 * ネット注文帳票情報の取得インターフェースを設定します
	 * @param netOrderReportDataDao ネット注文帳票情報の取得インターフェース
	 */
	public void setNetOrderReportDataDao(NetOrderReportDataDao netOrderReportDataDao) {
		this.netOrderReportDataDao = netOrderReportDataDao;
	}

	/**
	 * excelテンプレートを取るDTOを取得します
	 * @return excelテンプレートを取るDTO
	 */
	public NetOrderReportDownloadDto getNetorderReportDownloadDto() {
		return netorderReportDownloadDto;
	}

	/**
	 * excelテンプレートを取るDTOを設定します
	 * @param netorderReportDownloadDto excelテンプレートを取るDTO
	 */
	public void setDownloadDto(NetOrderReportDownloadDto netorderReportDownloadDto) {
		this.netorderReportDownloadDto = netorderReportDownloadDto;
	}

	/**
	 * excelテンプレートを取るLOGICを取得します
	 * @return excelテンプレートを取るLOGIC
	 */
	public NetOrderReportDownloadLogicImpl getNetorderReportDownloadLogic() {
		return netorderReportDownloadLogic;
	}

	/**
	 * excelテンプレートを取るLOGICを設定します
	 * @param netorderReportDownloadLogic excelテンプレートを取るLOGIC
	 */
	public void setNetorderReportDownloadLogic(NetOrderReportDownloadLogicImpl netorderReportDownloadLogic) {
		this.netorderReportDownloadLogic = netorderReportDownloadLogic;
	}

	/**
	 * ネット注文帳票DTOを取得します
	 * @return ネット注文帳票DTO
	 */
	 public SearchDto getSearchDto() {
		return searchDto;
	}

	 /**
	  * ネット注文帳票DTOを設定します
	  * @param searchDto ネット注文帳票DTO
	  */
	public void setSearchDto(SearchDto searchDto) {
		this.searchDto = searchDto;
	}

	/**
	 * excelにデータを書き込む
	 * @param row
	 * @param column
	 * @param value
	 */
	private void addDataCell(Row row, int column, String value) {
		 Cell cell = row.getCell(column);
		 cell.setCellValue(value);
	 }

	/**
	 * excelにint型のデータを書き込む
	 * @param row
	 * @param column
	 * @param value
	 */
	 private void addIntDataCell(Row row, int column, String value) {
		 Cell cell = row.getCell(column);
		 if(value.trim() != null && value != "") {
			 cell.setCellValue(Integer.parseInt(value));
		 }else {
			 cell.setCellValue(value);
		 }
	 }

	 /**
	  * excelファイルの出力期間を取得します
	  * @param dto
	  * @return 出力期間
	  */
	 private String getExcelTaishoKikan(SearchDto dto) {

		// 日付フォーマッタ
		DateFormatter df = new DateFormatter();
		// 対象期間情報
		StringBuffer kikan = new StringBuffer();

		 if(TaishoKikan.MONTH.equals(dto.getTaishoKikanCd())) {;
				kikan.append(dto.getKikanYM().substring(0, 4));
				kikan.append("年");
				kikan.append(Integer.parseInt(dto.getKikanYM().substring(4, 6)));
				kikan.append("月度");
		 }else if(TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {
				kikan.append(df.format(dto.getKikanFrom(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
				kikan.append("-");
				kikan.append(df.format(dto.getKikanTo(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
		 }
		 return kikan.toString();
	 }

		/**
		 * データ取得用パラメータを設定する
		 * @param paramDto
		 */
		private void setParamaterForGetData(SearchDto paramDto) {

			 boolean isMonth = TaishoKikan.MONTH.equals(paramDto.getTaishoKikanCd());
			 //対象期間は対象年月時,月初日、月末日取得処理
			 if(isMonth) {
				//月初日取得処理
				 dateFrom = paramDto.getKikanYM()+"01";
				//月末日取得処理
				 dateTo = paramDto.getKikanYM()+DateManager.getLastDayOfMonth(paramDto.getKikanYM());
				 //当月の場合、当月1日からアプリ日付まで
				 if (dateTo.compareTo(paramDto.getBirdDateInfo().getAppDate()) >= 0) {
					 dateTo = paramDto.getBirdDateInfo().getAppDate();
				 }
			 }else {
				 dateFrom = paramDto.getKikanFrom();
				 dateTo = paramDto.getKikanTo();
			 }
		}

}
