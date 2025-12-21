package jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.MstMiSeJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.TintaTenpoSyuBetuJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dto.MiseInfoExtractDto;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.MstChintai;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.MstMiSeJoho;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.TintaTenpoSyuBetuJoho;

/**
 * 店マスタ情報一括抽出Excelダウンロード
 * @author boukoumei
 */
public class MiseInfoMationExtractExcelLogicImpl implements ExcelOutputLogic{
    /** ロジックID */
    public static final String LOGIC_ID = "BSI008L03";
	private static final String OUTCLOSE = "outClose";
	/** コードフォマート用*/
	private static final String FORMAT_1 = "0";
	private static final String FORMAT_2 = "00";
	private static final String FORMAT_3 = "000";
	private static final String FORMAT_5 = "00000";
	private static final String FORMAT_7 = "0000000";
	private static final String FORMAT_8 = "00000000";

	/** 出力データ取得用の条件参数*/
	/** 出力区分*/
	private String outPutDiv = null;
	/** クローズ店フラグ*/
	private String inClose = null;
	/** 集計区分*/
	private String shukeKubu = null;
	/** svCd*/
	private String svCd = null;
	/** 支部コード*/
	private String siBuCd = null;

	/** 店マスタ情報の取得*/
	private MstMiSeJohoDao mstMiSeJohoDao;
	/** 賃貸店舗種別情報の取得*/
	private TintaTenpoSyuBetuJohoDao tintaTenpoSyuBetuJohoDao;
	/** 賃貸店舗履歴情報の取得*/
	private MstChintaiDao mstChintaiDao;
	/** 賃貸店舗種別コードリスト*/
	private List<String> tintaTenpoSyuBetuCdList = new ArrayList<String>();
	/** 賃貸店舗種別情報Map*/
	private Map<String,Integer> tintaTenpoSyuBetuMap = new TreeMap<String,Integer>();
	/** 賃貸店舗種別名称Map*/
	private Map<String,String> tintaTenpoSyuBetuNameMap = new TreeMap<String,String>();
	/** 賃貸店舗履歴Map*/
	private Map<String,List<MstChintai>> mstChintaiMap = new TreeMap<String,List<MstChintai>>();

	/** テキストのセルスタイル */
	private CellStyle textStyle = null;

	/** 繰り返すテキストのセルスタイル */
	private CellStyle textLoopStyle = null;

	/** コードのセルスタイルMap */
	private Map<String, CellStyle> codeStyleHashMap = new HashMap<String, CellStyle>();

	/** 日付のセルスタイル */
	private CellStyle dateStyle = null;


	/**
	 * ファイル名取得
	 */
	public String getFileName(ExcelOutputDto paramExcelOutputDto) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = format.format(date);
		return "mise_list_" + strDate + ".xlsx";
	}

	/**
	 * 出力データ作成処理
	 */
	public Workbook setOutputData(Workbook book, ExcelOutputDto excelOutputDto) {
		MiseInfoExtractDto dto = (MiseInfoExtractDto) excelOutputDto;
		// シートを作成
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = format.format(date);
		Sheet sheet = book.createSheet("mise_list_" + strDate);

		// 出力データ取得
		// データ取得用パラメータを設定する
		setParamaterForGetData(dto);

		// 出力データ取得
		// 店マスタ情報を取得する
		List masterMiseJohoList = mstMiSeJohoDao.selectMiSeJoho(outPutDiv, inClose, shukeKubu, svCd, siBuCd);
		// 賃貸店舗種別情報を取得す
		List tintaTenpoSyuBetuJohoList = tintaTenpoSyuBetuJohoDao.select(inClose, shukeKubu, svCd, siBuCd);
		// 賃貸店舗履歴を取得する
		List chinTaiHisJohoList = mstChintaiDao.selectChintai(inClose, shukeKubu, svCd, siBuCd);

		// データが存在しないの場合
		if(masterMiseJohoList == null || masterMiseJohoList.size()== 0) {
			throw new NoResultException();
		}
		// 関連マップを作成する
		setMap(tintaTenpoSyuBetuJohoList,chinTaiHisJohoList);
		dto.setExcleList(masterMiseJohoList);

		try {
			// Excelヘッダ部を作成
			outputHeader(book, sheet, dto);
			// ExcelのTitleを作成する
			outputTitle(book, sheet, dto);
			// Excelデータ部を作成する
			outputData(book, sheet, dto);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FtlSystemException("Excel作成");
		}

		return book;
	}

	/**
	 * データ取得用パラメータを設定する
	 * @param csvOutputDto
	 * @param outPutDiv 出力区分
	 * @param inClose クローズ店フラグ
	 * @param shukeKubu 集計区分
	 * @param svCd svCd
	 * @param siBuCd 支部コード
	 */
	private void setParamaterForGetData(MiseInfoExtractDto dto) {
		// 出力区分を設定する
		// [基本情報]にチェックを入れる場合
		if (dto.getKatekoriCheck1() && !dto.getKatekoriCheck2() && !dto.getKatekoriCheck3()) {
			outPutDiv = "1";
		} else if (dto.getKatekoriCheck1() && dto.getKatekoriCheck2() && !dto.getKatekoriCheck3()) {
			// [基本情報+日付・店舗継承]にチェックを入れる場合
			outPutDiv = "2";
		} else if (dto.getKatekoriCheck1() && !dto.getKatekoriCheck2() && dto.getKatekoriCheck3()) {
			// [基本情報+特性・営業時間]にチェックを入れる場合
			outPutDiv = "3";
		} else if (dto.getKatekoriCheck1() && dto.getKatekoriCheck2() && dto.getKatekoriCheck3()) {
			// 全てのカテゴリにチェックを入れる場合
			outPutDiv = "4";
		}
		// クローズ店フラグ
		if (OUTCLOSE.equals(dto.getCloseFlg())) {
			inClose = null;
		} else {
			inClose = OUTCLOSE;
		}
		// 集計区分
		shukeKubu = dto.getShukeiKbnCd();

		siBuCd = null;
		if (shukeKubu.equals("SV_CD")) {
			// svCd
			svCd = dto.getSvCd();
		} else {
			svCd = null;
			// 支部コード(画面．支部コード＝「全て」の場合、支部コード＝NULL)
			if (!"All".equals(dto.getSibuCd())) {
				siBuCd = dto.getSibuCd();
			}
		}
	}

	/**
	 * 関連マップを作成する
	 * @param list1 賃貸店舗種別情報
	 * @param list2 賃貸店舗履歴
	 */
	private void setMap(List list1, List list2)
	{
		// 前回データを削除する
		tintaTenpoSyuBetuCdList.clear();
		tintaTenpoSyuBetuMap.clear();
		tintaTenpoSyuBetuNameMap.clear();
		mstChintaiMap.clear();

		// 賃貸店舗種別情報関連マップを作成する
		for (Iterator ite = list1.iterator(); ite.hasNext();) {
        	TintaTenpoSyuBetuJoho entity = (TintaTenpoSyuBetuJoho) ite.next();
        	String key = entity.getMiseLeaseShu();
        	tintaTenpoSyuBetuCdList.add(key);
        	if(!tintaTenpoSyuBetuMap.containsKey(key)){
        		tintaTenpoSyuBetuNameMap.put(key, entity.getMiseLeaseName());
        		tintaTenpoSyuBetuMap.put(key, Integer.valueOf(entity.getMaxLeaseShuCount()));
        	}
        }

		// 賃貸店舗履歴関連マップを作成する
		for (Iterator ite = list2.iterator(); ite.hasNext();) {
			List<MstChintai> entityList = new ArrayList<MstChintai>();
			MstChintai entity = (MstChintai) ite.next();
        	String key = entity.getMiseCd().concat(entity.getMiseLeaseShu());
        	if(!mstChintaiMap.containsKey(key)){
        		entityList.add(entity);
        		mstChintaiMap.put(key, entityList);
        	} else{
        		entityList = mstChintaiMap.get(key);
        		entityList.add(entity);
        		mstChintaiMap.put(key, entityList);
        	}
        }
	}

	/**
	 * Excelファイルのヘッダ部を作成する
	 * @param dto
	 * @param listCsv
	 */
    private void outputHeader(Workbook book, Sheet sheet,MiseInfoExtractDto dto) {
		// ヘッダの1行目:ダウンロード対象：店マスタ情報一覧
		Row headerRow1 = sheet.createRow(0);
		createTextCell(book, headerRow1, 0, "ダウンロード対象：");
		createTextCell(book, headerRow1, 1, "店マスタ情報一覧");

		// ヘッダの2行目:クローズ店：含まない
		Row headerRow2 = sheet.createRow(1);
		createTextCell(book, headerRow2, 0, "クローズ店：");
		createTextCell(book, headerRow2, 1, dto.getCloseName());

		// ヘッダ3行目:集計区分：
		Row headerRow3 = sheet.createRow(2);
		createTextCell(book, headerRow3, 0, "集計区分：");
		createTextCell(book, headerRow3, 1, ShukeiKbn.getName(dto.getShukeiKbnCd()));
		if(!"SV_CD".equals(dto.getShukeiKbnCd())){
			createTextCell(book, headerRow3, 2, "対象支部：");
	        if("All".equals(dto.getSibuCd())){
	    		createTextCell(book, headerRow3, 3, "すべて");
	        } else {
	    		createTextCell(book, headerRow3, 3, dto.getSibuName());
	        }
		} else{
			createTextCell(book, headerRow3, 2, "担当SV：");
			createTextCell(book, headerRow3, 3, dto.getSvCd()+" "+ dto.getSvName());
		}

        // ヘッダ4行目:選択カテゴリ：
        Row headerRow4 = sheet.createRow(3);
        createTextCell(book, headerRow4, 0, "選択カテゴリ：");
        if(dto.getKatekoriCheck2() && dto.getKatekoriCheck3()){
        	createTextCell(book, headerRow4, 1, "すべて");
        } else{
        	if(dto.getKatekoriCheck2()){
        		createTextCell(book, headerRow4, 1, "基本情報、 住所情報");
        	}else if(dto.getKatekoriCheck3()){
        		createTextCell(book, headerRow4, 1, "基本情報、 付属情報");
        	}else{
        		createTextCell(book, headerRow4, 1, "基本情報");
        	}
        }

		// ヘッダ5行目:ダウンロード日付：
		Row headerRow5 = sheet.createRow(4);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy/M/d");
		createTextCell(book, headerRow5, 0, "ダウンロード日付：");
		createDateCell(book, headerRow5, 1, format.format(date));

		// 空行を追加する
        Row headerRow6 = sheet.createRow(5);
        createTextCell(book, headerRow6, 0, "");
    }

    /**
     * ExcelファイルのTitleを作成する
     * @param dto
     * @param listCsv
     */
	private void outputTitle(Workbook book, Sheet sheet,MiseInfoExtractDto dto) {
		Row headerRow9 = sheet.createRow(7);
		Row infoRow = sheet.createRow(6);
		List listTitle = new ArrayList();
		List infoList = new ArrayList();

		// 基本情報(※表示必須)
		infoList.add("基本情報");
		listTitle.add("店コード");
		listTitle.add("店名称（漢字）");
		infoList.add("");
		listTitle.add("店名称（カナ）");
		infoList.add("");
		listTitle.add("オーナーコード");
		infoList.add("");
		listTitle.add("オーナー名称");
		infoList.add("");
		listTitle.add("支部コード");
		infoList.add("");
		listTitle.add("支部名称");
		infoList.add("");
		listTitle.add("支部取込コード");
		infoList.add("");
		listTitle.add("支部取込コード名称");
		infoList.add("");
		listTitle.add("店区分");
		infoList.add("");
		listTitle.add("店区分名称");
		infoList.add("");
		listTitle.add("業態区分");
		infoList.add("");
		listTitle.add("業態区分名称");
		infoList.add("");
		listTitle.add("担当SVコード");
		infoList.add("");
		listTitle.add("担当SV名称");
		infoList.add("");
		listTitle.add("店オープン日");
		infoList.add("");
		listTitle.add("店クローズ日");
		infoList.add("");
		listTitle.add("新店時店コード");
		infoList.add("");
		listTitle.add("最新店コード");
		infoList.add("");
		// 住所情報(※カテゴリ「日付店舗継承」を選択する場合、表示可能)
		if (dto.getKatekoriCheck2()) {
			infoList.add("住所情報");
			listTitle.add("県コード");
			listTitle.add("県名称");
			infoList.add("");
			listTitle.add("郵便番号");
			infoList.add("");
			listTitle.add("店住所1");
			infoList.add("");
			listTitle.add("店住所2");
			infoList.add("");
			listTitle.add("店住所3");
			infoList.add("");
			listTitle.add("電話番号");
			infoList.add("");
		}
		// 付属情報(※カテゴリ「特性・営業時間」を選択する場合、表示可能)
		if (dto.getKatekoriCheck3()) {
			infoList.add("付属情報");
			listTitle.add("店舗タイプ区分");
			listTitle.add("店舗タイプ区分名称");
			infoList.add("");
			listTitle.add("店舗形態区分");
			infoList.add("");
			listTitle.add("店舗形態区分名称");
			infoList.add("");
			listTitle.add("ロケーション区分");
			infoList.add("");
			listTitle.add("ロケーション区分名称");
			infoList.add("");
			listTitle.add("転貸");
			infoList.add("");
			listTitle.add("転貸開始日");
			infoList.add("");
			listTitle.add("転貸終了日");
			infoList.add("");
			listTitle.add("転貸情報");
			infoList.add("");
			listTitle.add("賃貸店舗経理コード");
			infoList.add("");

			// 賃貸店舗履歴部分を追加する
			for (int cdIndex = 0; cdIndex < tintaTenpoSyuBetuCdList.size(); cdIndex++) {
				String syuBetuCd = tintaTenpoSyuBetuCdList.get(cdIndex);
				String syuBetuName = tintaTenpoSyuBetuNameMap.get(syuBetuCd);
				for (int index = 0; index < tintaTenpoSyuBetuMap.get(syuBetuCd); index++) {
					listTitle.add("賃貸店舗契約日（" + syuBetuName.trim() + "）");
					infoList.add("");
					listTitle.add("契約終了予定日（" + syuBetuName.trim() + "）");
					infoList.add("");
				}
			}
		}

		createTextCell(book, infoRow, 0, infoList);
		createTextCell(book, headerRow9, 0, listTitle);
	}

	/**
	 *Excelファイルのデータ部を作成
	 */
	private void outputData(Workbook book, Sheet sheet, MiseInfoExtractDto dto) {
		int startRow = 8;
		DateFormatter formart = new DateFormatter();
		for (Iterator ite = dto.getExcleList().iterator(); ite.hasNext();) {
			// 店固定情報を作成する
			MstMiSeJoho entityData = (MstMiSeJoho) ite.next();

			// 基本情報(※表示必須)
			int startColumn = 0;
			Row row = sheet.createRow(startRow);
			createCodeCell(book, row, startColumn++,entityData.getMiseCd(), FORMAT_5);           // 店コード
			createTextCell(book, row, startColumn++,entityData.getMiseNameKj());      			 // 店名称（漢字）
			createTextCell(book, row, startColumn++,entityData.getMiseNameKna());        	     // 店名称（カナ）
        	createCodeCell(book, row, startColumn++,entityData.getOnerCd(), FORMAT_5);           // オーナーコード
        	createTextCell(book, row, startColumn++,entityData.getOnerNameKj());      			 // オーナー名称
        	createCodeCell(book, row, startColumn++,entityData.getSibuCd(), FORMAT_3);           // 支部コード
        	createTextCell(book, row, startColumn++,entityData.getSibuName());                   // 支部名称
        	createCodeCell(book, row, startColumn++,entityData.getSibuToriCd(), FORMAT_3);       // 支部取込コード
        	createTextCell(book, row, startColumn++,entityData.getSibuToriName());               // 支部取込コード名称
        	createCodeCell(book, row, startColumn++,entityData.getMiseKbn(), FORMAT_3);          // 店区分
        	createTextCell(book, row, startColumn++,entityData.getAiteName());                   // 店区分名称
        	createCodeCell(book, row, startColumn++,entityData.getGyotaiKbn(), FORMAT_3);        // 業態区分
        	createTextCell(book, row, startColumn++,entityData.getGyotaiKbnName());              // 業態区分名称
        	createCodeCell(book, row, startColumn++,entityData.getSvCd(), FORMAT_8);       	     // 担当SVコード
        	createTextCell(book, row, startColumn++,entityData.getUserNameKj());                 // 担当SV名称
        	// 店オープン日
        	createDateCell(book, row, startColumn++,formart.format(entityData.getOpenDt(), "yyyy/M/d", 1));
        	// 店クローズ日
        	createDateCell(book, row, startColumn++,formart.format(entityData.getCloseDt(), "yyyy/M/d", 1));
        	createCodeCell(book, row, startColumn++,entityData.getMiseCdShinten(), FORMAT_5);    // 新店時店コード
        	createCodeCell(book, row, startColumn++,entityData.getMiseCdSaishin(), FORMAT_5);  	 // 最新店コード
        	// 住所情報(※カテゴリ「日付店舗継承」を選択する場合、表示可能)
        	if(dto.getKatekoriCheck2()){
        		createCodeCell(book, row, startColumn++,entityData.getKenCd(), FORMAT_2);  	  	// 県コード
        		createTextCell(book, row, startColumn++,entityData.getKenName());               // 県名称
        		createCodeCell(book, row, startColumn++,entityData.getMisePostNo(),FORMAT_7);  	// 郵便番号
        		createTextCell(book, row, startColumn++,entityData.getMiseAdrs1());         	// 店住所1
        		createTextCell(book, row, startColumn++,entityData.getMiseAdrs2());  	        // 店住所2
        		createTextCell(book, row, startColumn++,entityData.getMiseAdrs3());  	        // 店住所3
        		createTextCell(book, row, startColumn++,entityData.getMiseTel());            	// 電話番号
        	}

        	// 付属情報(※カテゴリ「特性・営業時間」を選択する場合、表示可能)
        	if(dto.getKatekoriCheck3()){
        		createCodeCell(book, row, startColumn++,entityData.getMTypeKbn(), FORMAT_3);  		    // 店舗タイプ区分
        		createTextCell(book, row, startColumn++,entityData.getMTypeKbnName());  	            // 店舗タイプ区分名称
        		createCodeCell(book, row, startColumn++,entityData.getTMiseKeitai(), FORMAT_3);  		// 店舗形態区分
        		createTextCell(book, row, startColumn++,entityData.getMKeitaiName());  		            // 店舗形態区分名称
        		createCodeCell(book, row, startColumn++,entityData.getTLocateKbn(), FORMAT_3);  		// ロケーション区分
        		createTextCell(book, row, startColumn++,entityData.getLocateName());  		            // ロケーション区分名称
        		createCodeCell(book, row, startColumn++,entityData.getTentai(), FORMAT_1); 		     	// 転貸
        		// 転貸開始日
        		createDateCell(book, row, startColumn++,formart.format(entityData.getTentaiStartDt(), "yyyy/M/d", 1));
            	// 転貸終了日
        		createDateCell(book, row, startColumn++,formart.format(entityData.getTentaiEndDt(), "yyyy/M/d", 1));
            	createTextCell(book, row, startColumn++,entityData.getTentaiInfo());  		// 転貸情報
        		createCodeCell(book, row, startColumn++,entityData.getMiseLeaseKCd(),"");  	// 賃貸店舗経理コード

    			// 可変な部分、賃貸店舗履歴データを作成する
    			for (int cdIndex = 0; cdIndex < tintaTenpoSyuBetuCdList.size(); cdIndex++) {
    				String syuBetuCd = tintaTenpoSyuBetuCdList.get(cdIndex);
    				if (mstChintaiMap.containsKey(entityData.getMiseCd().concat(syuBetuCd))) {
    					List<MstChintai> listForExcel = new ArrayList<MstChintai>();
    					listForExcel = mstChintaiMap.get(entityData.getMiseCd().concat(syuBetuCd));
    					if (listForExcel.size() < tintaTenpoSyuBetuMap.get(syuBetuCd)) {
    						for (int i = 0; i < tintaTenpoSyuBetuMap.get(syuBetuCd); i++) {
    							if (i < listForExcel.size()) {
    								MstChintai entity = listForExcel.get(i);
    								createDateCell(book, row, startColumn++,formart.format(entity.getMiseLeaseStart(), "yyyy/M/d", 1));
    								createDateCell(book, row, startColumn++,formart.format(entity.getMiseLeaseEnd(), "yyyy/M/d", 1));
    							} else {
    								startColumn = startColumn+2;
    							}
    						}
    					} else {
    						for (int i = 0; i < listForExcel.size(); i++) {
    							MstChintai entity = listForExcel.get(i);
    							createDateCell(book, row, startColumn++,formart.format(entity.getMiseLeaseStart(), "yyyy/M/d", 1));
    							createDateCell(book, row, startColumn++,formart.format(entity.getMiseLeaseEnd(), "yyyy/M/d", 1));
    						}
    					}
    				} else {
    					Integer countNull = tintaTenpoSyuBetuMap.get(syuBetuCd);
    					for (int j = 0; j < countNull; j++) {
    						startColumn = startColumn+2;
    					}
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
	private void createCodeCell(Workbook book, Row row, int column, String value, String formatType) {
		CellStyle style = null;
		if (codeStyleHashMap.containsKey(formatType)) {
			style = codeStyleHashMap.get(formatType);
		} else {
			style = book.createCellStyle();
			codeStyleHashMap.put(formatType, style);
		}
		DataFormat format = book.createDataFormat();
		Cell cell = row.createCell(column);
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		if(!formatType.isEmpty()){
			style.setDataFormat(format.getFormat(formatType));
		}
		cell.setCellStyle(style);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/**
	 * セルに日期列を設定
	 */
	private void createDateCell(Workbook book, Row row, int column, String value) {
        DataFormat format = book.createDataFormat();
		Cell cell = row.createCell(column);
		if (dateStyle == null) {
			dateStyle = book.createCellStyle();
			dateStyle.setAlignment(CellStyle.ALIGN_RIGHT);
			dateStyle.setDataFormat(format.getFormat("yyyy/m/d"));
		}
		cell.setCellStyle(dateStyle);
		cell.setCellValue(value);
	}

	/**
	 * 店名称（漢字）を取得
	 */
	private String getMiseName(MiseInfoExtractDto dto) {
		String miseName = "";
		return miseName;
	}

	/**
	 * @return mstMiSeJohoDao
	 */
	public MstMiSeJohoDao getMstMiSeJohoDao() {
		return mstMiSeJohoDao;
	}

	/**
	 * @param mstMiSeJohoDao
	 *            セットする mstMiSeJohoDao
	 */
	public void setMstMiSeJohoDao(MstMiSeJohoDao mstMiSeJohoDao) {
		this.mstMiSeJohoDao = mstMiSeJohoDao;
	}
	/**
	 * @return tintaTenpoSyuBetuJohoDao
	 */
	public TintaTenpoSyuBetuJohoDao getTintaTenpoSyuBetuJohoDao() {
		return tintaTenpoSyuBetuJohoDao;
	}

	/**
	 * @param tintaTenpoSyuBetuJohoDao セットする tintaTenpoSyuBetuJohoDao
	 */
	public void setTintaTenpoSyuBetuJohoDao(TintaTenpoSyuBetuJohoDao tintaTenpoSyuBetuJohoDao) {
		this.tintaTenpoSyuBetuJohoDao = tintaTenpoSyuBetuJohoDao;
	}

	/**
	 * @return mstChintaiDao
	 */
	public MstChintaiDao getMstChintaiDao() {
		return mstChintaiDao;
	}

	/**
	 * @param mstChintaiDao セットする mstChintaiDao
	 */
	public void setMstChintaiDao(MstChintaiDao mstChintaiDao) {
		this.mstChintaiDao = mstChintaiDao;
	}

	/**
	 * Workbook読込処理
	 */
	public Workbook loadWorkbook(ExcelOutputDto paramExcelOutputDto) throws IOException {
		// 前回Bookのスタイルを初期化する
		this.textStyle = null;
		this.textLoopStyle = null;
		this.codeStyleHashMap = new HashMap<String, CellStyle>();
		this.dateStyle = null;

		return new XSSFWorkbook();
	}
}
