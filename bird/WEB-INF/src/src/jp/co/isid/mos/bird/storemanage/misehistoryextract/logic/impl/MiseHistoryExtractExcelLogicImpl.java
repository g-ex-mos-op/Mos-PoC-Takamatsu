/*
 * 作成日: 2016/02/23
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.logic.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dao.MstMiSeRiRekiJohoDao;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dao.TintaTenpoSyuBetuJohoDao;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dto.MiseHistoryExtractDto;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.MstChintai;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.MstMiSeRiRekiJoho;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.TintaTenpoSyuBetuJoho;

/**
 * 店マスタ履歴抽出画面 Excelデータリストの作成
 */
public class MiseHistoryExtractExcelLogicImpl implements ExcelOutputLogic {
	 /* ロジックID */
    public static final String LOGIC_ID = "BSI009L02";

	private MstMiSeRiRekiJohoDao mstMiSeRiRekiJohoDao;
	private TintaTenpoSyuBetuJohoDao tintaTenpoSyuBetuJohoDao;
	private MstChintaiDao mstChintaiDao;
	private Map<String, Integer> maxLeaseShuCountMap = new HashMap();
	private Map<String, List> dataMap = new HashMap();
	/** テキストのセルスタイル */
	private CellStyle textStyle = null;
	/** 繰り返すテキストのセルスタイル */
	private CellStyle textLoopStyle = null;
	/** コードのセルスタイルMap */
	private Map<Integer, CellStyle> codeStyleHashMap = new HashMap<Integer, CellStyle>();
	/** 日付のセルスタイル */
	private CellStyle dateStyle = null;

	/**
	 * ファイル名取得
	 */
	public String getFileName(ExcelOutputDto paramExcelOutputDto) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = format.format(date);
		return "mise_rireki_" + strDate + ".xlsx";
	}

	/**
	 * Workbook読込処理
	 */
	public Workbook loadWorkbook(ExcelOutputDto paramExcelOutputDto) throws IOException {
		// 前回Bookのスタイルを初期化する
		this.textStyle = null;
		this.textLoopStyle = null;
		this.codeStyleHashMap = new HashMap<Integer, CellStyle>();
		this.dateStyle = null;
		return new XSSFWorkbook();
	}

	/**
	 * 出力データ作成処理
	 */
	public Workbook setOutputData(Workbook book, ExcelOutputDto excelOutputDto) {
		MiseHistoryExtractDto dto = (MiseHistoryExtractDto) excelOutputDto;

		// シートを作成
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = format.format(date);
		Sheet sheet = book.createSheet("mise_rireki_" + strDate);

		// 出力データ取得
		String miseCd = dto.getCondMiseCd();
		List mstMiSeRiRekiJoho = getMstMiSeRiRekiJohoDao().select(miseCd);
		List tintaTenpoSyuBetuJoho = getTintaTenpoSyuBetuJohoDao().select(miseCd);
		List mstChintai = getMstChintaiDao().select(miseCd);

		// 「該当データがありません」を表示
		if (mstMiSeRiRekiJoho == null || mstMiSeRiRekiJoho.size() == 0) {
			throw new NoResultException();
		}

		dto.setMstMiSeRiRekiJoho(mstMiSeRiRekiJoho);
		dto.setTintaTenpoSyuBetuJoho(tintaTenpoSyuBetuJoho);
		dto.setMstChintai(mstChintai);

		try {
			// ヘッダ部を作成
			outputHeader(book, sheet, dto);

			// データを格納
			this.dataMap.clear();
			for (Iterator iter = dto.getMstChintai().iterator(); iter.hasNext();) {
				MstChintai chintai = (MstChintai) iter.next();
				String key = chintai.getMiseMDate() + chintai.getMiseLeaseShu();
				if (this.dataMap.containsKey(key)) {
					List list = this.dataMap.get(key);
					list.add(chintai);
					this.dataMap.put(key, list);
				} else {
					List list = new ArrayList();
					list.add(chintai);
					this.dataMap.put(key, list);
				}
			}

			// データ部を作成
			outputData(book, sheet, dto);

		} catch (Exception ex) {
			throw new FtlSystemException("Excel作成");
		}

		return book;
	}

	/**
	 * ヘッダ部データを作成
	 */
	private void outputHeader(Workbook book, Sheet sheet, MiseHistoryExtractDto dto) {
		// ヘッダの1行目
		Row headerRow1 = sheet.createRow(0);
		createTextCell(book, headerRow1, 0, "ダウンロード対象：");
		createTextCell(book, headerRow1, 1, "店マスタ履歴");

		// ヘッダの2行目
		Row headerRow2 = sheet.createRow(1);
		createTextCell(book, headerRow2, 0, "店コード：");
		createCodeCell(book, headerRow2, 1, dto.getCondMiseCd(), 5);
		createTextCell(book, headerRow2, 2, this.getMiseName(dto));

		// ヘッダの3行目
		Row headerRow3 = sheet.createRow(2);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy/M/d");
		createTextCell(book, headerRow3, 0, "ダウンロード日付：");
		createDateCell(book, headerRow3, 1, format.format(date));

		// データのヘッダ部
		Row headerRow4 = sheet.createRow(4);
		List<String> listKoumoku = new ArrayList<String>();
		listKoumoku.add("店Ｍ有効日");
		listKoumoku.add("店コード");
		listKoumoku.add("店名称（漢字）");
		listKoumoku.add("店名称（カナ）");
		listKoumoku.add("オーナーコード");
		listKoumoku.add("オーナー名称");
		listKoumoku.add("支部コード");
		listKoumoku.add("支部名称");
		listKoumoku.add("支部取込コード");
		listKoumoku.add("支部取込コード名称");
		listKoumoku.add("店区分");
		listKoumoku.add("店区分名称");
		listKoumoku.add("業態区分");
		listKoumoku.add("業態区分名称");
		listKoumoku.add("担当SVコード");
		listKoumoku.add("担当SV名称");
		listKoumoku.add("店オープン日");
		listKoumoku.add("店クローズ日");
		listKoumoku.add("新店時店コード");
		listKoumoku.add("最新店コード");
		listKoumoku.add("県コード");
		listKoumoku.add("県名称");
		listKoumoku.add("郵便番号");
		listKoumoku.add("店住所1");
		listKoumoku.add("店住所2");
		listKoumoku.add("店住所3");
		listKoumoku.add("電話番号");
		listKoumoku.add("店舗タイプ区分");
		listKoumoku.add("店舗タイプ区分名称");
		listKoumoku.add("店舗形態区分");
		listKoumoku.add("店舗形態区分名称");
		listKoumoku.add("ロケーション区分");
		listKoumoku.add("ロケーション区分名称");
		listKoumoku.add("転貸");
		listKoumoku.add("転貸開始日");
		listKoumoku.add("転貸終了日");
		listKoumoku.add("転貸情報");
		listKoumoku.add("賃貸店舗経理コード");

		this.maxLeaseShuCountMap.clear();
		for (Iterator ite = dto.getTintaTenpoSyuBetuJoho().iterator(); ite.hasNext();) {
			TintaTenpoSyuBetuJoho entity = (TintaTenpoSyuBetuJoho) ite.next();
			this.maxLeaseShuCountMap.put(entity.getMiseLeaseShu(), entity.getMaxLeaseShuCount());
			for (int i = 0; i < entity.getMaxLeaseShuCount(); i++) {
				listKoumoku.add("賃貸店舗契約日（" + entity.getMseLeaseName() + "）");
				listKoumoku.add("契約終了予定日（" + entity.getMseLeaseName() + "）");
			}
		}
		createTextCell(book, headerRow4, 0, listKoumoku);

		// 列幅設定
		sheet.setColumnWidth(0, ("ダウンロード対象：".getBytes().length * 190));
		sheet.setColumnWidth(1, ("店マスタ履歴：".getBytes().length * 190));
		for (int col = 2; col < listKoumoku.size(); col++) {
			int length = listKoumoku.get(col).getBytes().length;
			sheet.setColumnWidth(col, length * 190);
		}
	}

	/**
	 * データ部を作成
	 */
	private void outputData(Workbook book, Sheet sheet, MiseHistoryExtractDto dto) {
		int startRow = 5;
		DateFormatter formart = new DateFormatter();
		for (Iterator ite = dto.getMstMiSeRiRekiJoho().iterator(); ite.hasNext();) {
			MstMiSeRiRekiJoho entity = (MstMiSeRiRekiJoho) ite.next();

			// 固定なデータ部を作成
			int startColumn = 0;
			Row row = sheet.createRow(startRow);
			createDateCell(book, row, startColumn++, formart.format(entity.getMiseMDate(), "yyyy/M/d", 1));
			createCodeCell(book, row, startColumn++, entity.getMiseCd(), 5);
			createTextCell(book, row, startColumn++, entity.getMiseNameKj());
			createTextCell(book, row, startColumn++, entity.getMiseNameKna());
			createCodeCell(book, row, startColumn++, entity.getOnerCd(), 5);
			createTextCell(book, row, startColumn++, entity.getOnerNameKj());
			createCodeCell(book, row, startColumn++, entity.getSibuCd(), 3);
			createTextCell(book, row, startColumn++, entity.getSibuName());
			createCodeCell(book, row, startColumn++, entity.getSibuToriCd(), 3);
			createTextCell(book, row, startColumn++, entity.getSibuToriName());
			createCodeCell(book, row, startColumn++, entity.getMiseKbn(), 3);
			createTextCell(book, row, startColumn++, entity.getAiteName());
			createCodeCell(book, row, startColumn++, entity.getGyotaiKbn(), 3);
			createTextCell(book, row, startColumn++, entity.getGyotaiKbnName());
			createCodeCell(book, row, startColumn++, entity.getSvCd(), 8);
			createTextCell(book, row, startColumn++, entity.getUserNameKj());
			createDateCell(book, row, startColumn++, formart.format(entity.getOpenDt(), "yyyy/M/d", 1));
			createDateCell(book, row, startColumn++, formart.format(entity.getCloseDt(), "yyyy/M/d", 1));
			createCodeCell(book, row, startColumn++, entity.getMiseCdShinten(), 5);
			createCodeCell(book, row, startColumn++, entity.getMiseCdSaishin(), 5);
			createCodeCell(book, row, startColumn++, entity.getKenCd(), 2);
			createTextCell(book, row, startColumn++, entity.getKenName());
			createCodeCell(book, row, startColumn++, entity.getMisePostNo(), 7);
			createTextCell(book, row, startColumn++, entity.getMiseAdrs1());
			createTextCell(book, row, startColumn++, entity.getMiseAdrs2());
			createTextCell(book, row, startColumn++, entity.getMiseAdrs3());
			createTextCell(book, row, startColumn++, entity.getMiseTel());
			createCodeCell(book, row, startColumn++, entity.getMTypeKbn(), 3);
			createTextCell(book, row, startColumn++, entity.getMTypeKbnName());
			createCodeCell(book, row, startColumn++, entity.getTMiseKeitai(), 3);
			createTextCell(book, row, startColumn++, entity.getMKeitaiName());
			createCodeCell(book, row, startColumn++, entity.getTLocateKbn(), 3);
			createTextCell(book, row, startColumn++, entity.getLocateName());
			createCodeCell(book, row, startColumn++, entity.getTentai(), 1);
			createDateCell(book, row, startColumn++, formart.format(entity.getTentaiStartDt(), "yyyy/M/d", 1));
			createDateCell(book, row, startColumn++, formart.format(entity.getTentaiEndDt(), "yyyy/M/d", 1));
			createTextCell(book, row, startColumn++, entity.getTentaiInfo());
			createCodeCell(book, row, startColumn++, entity.getMiseLeaseKCd(), 0);

			// 可変なデータ部を作成
			for (Iterator iter = dto.getTintaTenpoSyuBetuJoho().iterator(); iter.hasNext();) {
				TintaTenpoSyuBetuJoho joho = (TintaTenpoSyuBetuJoho) iter.next();
				int maxCount = joho.getMaxLeaseShuCount();
				List dataList = this.dataMap.get(entity.getMiseMDate() + joho.getMiseLeaseShu());

				for (int i = 0; i < maxCount; i++) {
					if (dataList != null && i < dataList.size()) {
						MstChintai data = (MstChintai) dataList.get(i);
						createDateCell(book, row, startColumn++,
								formart.format(data.getMiseLeaseStart(), "yyyy/M/d", 1));
						createDateCell(book, row, startColumn++, formart.format(data.getMiseLeaseEnd(), "yyyy/M/d", 1));
					} else {
						createTextCell(book, row, startColumn++, "");
						createTextCell(book, row, startColumn++, "");
					}
				}
			}

			startRow++;
		}
	}


	/**
	 * セルに文字列を設定
	 */
	private void createTextCell(Workbook book, Row row, int column, String value) {
		if (textStyle == null) {
			textStyle = book.createCellStyle();
			textStyle.setAlignment(CellStyle.ALIGN_LEFT);
		}
		Cell cell = row.createCell(column);
		cell.setCellStyle(textStyle);
		cell.setCellValue(value);
	}

	/**
	 * セルに文字列を設定
	 */
	private void createTextCell(Workbook book, Row row, int startCol, List<String> valueList) {
		if (textLoopStyle == null) {
			textLoopStyle = book.createCellStyle();
			textLoopStyle.setAlignment(CellStyle.ALIGN_LEFT);
		}
		for (int i = startCol; i < valueList.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(textLoopStyle);
			cell.setCellValue(valueList.get(i));
		}
	}

	/**
	 * セルにコード列を設定
	 */
	private void createCodeCell(Workbook book, Row row, int column, String value, int maxLenth) {
		CellStyle style = null;
		if (codeStyleHashMap.containsKey(maxLenth)) {
			style = codeStyleHashMap.get(maxLenth);
		} else {
			style = book.createCellStyle();
			codeStyleHashMap.put(maxLenth, style);
		}
		Cell cell = row.createCell(column);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		if (maxLenth != 0) {
			// コードの前にゼロを追加
			DataFormat format = book.createDataFormat();
			String zero = "";
			for (int i = 0; i < maxLenth; i++) {
				zero = zero + "0";
			}
			style.setDataFormat(format.getFormat(zero));
		}
		cell.setCellStyle(style);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/**
	 * セルに日期列を設定
	 */
	private void createDateCell(Workbook book, Row row, int column, String value) {
		if(dateStyle == null){
			dateStyle = book.createCellStyle();
			dateStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		}
		Cell cell = row.createCell(column);
		cell.setCellStyle(dateStyle);
		cell.setCellValue(value);
	}

	/**
	 * 店名称（漢字）を取得
	 */
	private String getMiseName(MiseHistoryExtractDto dto) {
		String miseName = "";
		for (Iterator ite = dto.getMstMiSeRiRekiJoho().iterator(); ite.hasNext();) {
			MstMiSeRiRekiJoho entity = (MstMiSeRiRekiJoho) ite.next();
			miseName = entity.getMiseNameKj();
			if (!miseName.isEmpty()) {
				break;
			}
		}

		return miseName;
	}

	/**
	 * @return mstMiSeRiRekiJohoDao を戻します。
	 */
	public MstMiSeRiRekiJohoDao getMstMiSeRiRekiJohoDao() {
		return mstMiSeRiRekiJohoDao;
	}

	/**
	 * @param MstMiSeRiRekiJohoDao
	 *            mstMiSeRiRekiJohoDao を設定。
	 */
	public void setMstMiSeRiRekiJohoDao(MstMiSeRiRekiJohoDao mstMiSeRiRekiJohoDao) {
		this.mstMiSeRiRekiJohoDao = mstMiSeRiRekiJohoDao;
	}

	/**
	 * @return tintaTenpoSyuBetuJohoDao を戻します。
	 */
	public TintaTenpoSyuBetuJohoDao getTintaTenpoSyuBetuJohoDao() {
		return tintaTenpoSyuBetuJohoDao;
	}

	/**
	 * @param TintaTenpoSyuBetuJohoDao
	 *            tintaTenpoSyuBetuJohoDao を設定。
	 */
	public void setTintaTenpoSyuBetuJohoDao(TintaTenpoSyuBetuJohoDao tintaTenpoSyuBetuJohoDao) {
		this.tintaTenpoSyuBetuJohoDao = tintaTenpoSyuBetuJohoDao;
	}

	/**
	 * @return mstChintaiDao を戻します。
	 */
	public MstChintaiDao getMstChintaiDao() {
		return mstChintaiDao;
	}

	/**
	 * @param MstChintaiDao
	 *            mstChintaiDao を設定。
	 */
	public void setMstChintaiDao(MstChintaiDao mstChintaiDao) {
		this.mstChintaiDao = mstChintaiDao;
	}
}
