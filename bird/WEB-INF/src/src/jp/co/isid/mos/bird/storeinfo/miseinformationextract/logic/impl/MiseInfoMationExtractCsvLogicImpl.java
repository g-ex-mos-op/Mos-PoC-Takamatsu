package jp.co.isid.mos.bird.storeinfo.miseinformationextract.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.MstMiSeJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao.TintaTenpoSyuBetuJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.dto.MiseInfoExtractDto;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.MstChintai;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.MstMiSeJoho;
import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.TintaTenpoSyuBetuJoho;

/**
 * 店マスタ情報一括抽出用CVSデータリストの作成ロジック
 *
 * @author xayumi
 */
public class MiseInfoMationExtractCsvLogicImpl implements CsvOutputLogic {
    /* ロジックID */
    public static final String LOGIC_ID = "BSI008L02";
	private static final String OUTCLOSE = "outClose";
	/* 【DAO】 */
	/** 店マスタ情報の取得*/
	private MstMiSeJohoDao mstMiSeJohoDao;
	/** 賃貸店舗種別情報の取得*/
	private TintaTenpoSyuBetuJohoDao tintaTenpoSyuBetuJohoDao;
	/** 賃貸店舗履歴情報の取得*/
	private MstChintaiDao mstChintaiDao;

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
	/** 賃貸店舗種別コードリスト*/
	private List<String> tintaTenpoSyuBetuCdList = new ArrayList<String>();
	/** 賃貸店舗種別情報Map*/
	private Map<String,Integer> tintaTenpoSyuBetuMap = new TreeMap<String,Integer>();
	/** 賃貸店舗種別名称Map*/
	private Map<String,String> tintaTenpoSyuBetuNameMap = new TreeMap<String,String>();
	/** 賃貸店舗履歴Map*/
	private Map<String,List<MstChintai>> mstChintaiMap = new TreeMap<String,List<MstChintai>>();
	/**
	 * ＣＳＶファイル名取得
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		String fileName = "mise_list_";
		Date date = new Date();
		// ＣＳＶファイル名を作成する
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String time = format.format(date);
		fileName = fileName.concat(time).concat(".csv");
		return fileName;
	}

	/**
	 * 出力データを取得する
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

		MiseInfoExtractDto dto = (MiseInfoExtractDto) csvOutputDto;

		// データ取得用パラメータを設定する
		setParamaterForGetData(dto);

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
        dto.setCsvList(masterMiseJohoList);

        // CSV出力データリスト
        List listCSV = new ArrayList();
        // CSVファイル出力用の全部データを作成する
        listCSV = setCsvListForOutput(dto);

		return listCSV;
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
	 * CSVファイル出力用の全部データを作成する
	 * @param dto
	 * @return
	 */
	private List setCsvListForOutput(MiseInfoExtractDto dto ){
		// CSV出力用List
		List listCSV = new ArrayList();
		try {
			// CSVファイルのヘッダ部を作成する
			makeHeader(dto, listCSV);
			// CSVファイルのTitleを作成する
			makeTitle(dto, listCSV);
			// CSVファイルのデータ部を作成する
			makeData(dto, listCSV);
		} catch (Exception ex) {
			throw new FtlSystemException("CSV作成");
		}
		return listCSV;
	}


	/**
	 * CSVファイルのヘッダ部を作成する
	 * @param dto
	 * @param listCsv
	 */
    private void makeHeader(MiseInfoExtractDto dto, List listCsv) {
        List listHeader1 = new ArrayList();
        List listHeader2 = new ArrayList();
        List listHeader3 = new ArrayList();
        List listHeader4 = new ArrayList();
        List listHeader5 = new ArrayList();
        List ListBlackRow = new ArrayList();

        // ダウンロード対象：店マスタ情報一覧
        listHeader1.add("ダウンロード対象：");
        listHeader1.add("店マスタ情報一覧");
        listCsv.add(listHeader1);

        // クローズ店：含まない
        listHeader2.add("クローズ店：");
        listHeader2.add(dto.getCloseName());
        listCsv.add(listHeader2);

        // 集計区分：
        listHeader3.add("集計区分：");
        listHeader3.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));
		if(!"SV_CD".equals(dto.getShukeiKbnCd())){
			listHeader3.add("対象支部：");
	        if("All".equals(dto.getSibuCd())){
	        	listHeader3.add("すべて");
	        } else {
	        	listHeader3.add(dto.getSibuName());
	        }
		} else{
			listHeader3.add("担当SV：");
			listHeader3.add(dto.getSvCd()+" "+ dto.getSvName());
		}
        listCsv.add(listHeader3);

        // 選択カテゴリ：
        listHeader4.add("選択カテゴリ：");
        if(dto.getKatekoriCheck2() && dto.getKatekoriCheck3()){
        	listHeader4.add("すべて");
        } else{
        	if(dto.getKatekoriCheck2()){
        		listHeader4.add("基本情報、住所情報 ");
        	}else if(dto.getKatekoriCheck3()){
        		listHeader4.add("基本情報、付属情報 ");
        	} else{
        		listHeader4.add("基本情報 ");
        	}
        }
        listCsv.add(listHeader4);

        // ダウンロード日付：
        listHeader5.add("ダウンロード日付：");
		// ダウンロード日付を設定する
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        listHeader5.add(format.format(date));
        listCsv.add(listHeader5);

        // 空行を追加する
        listCsv.add(ListBlackRow);
    }

    /**
     * CSVファイルのTitleを作成する
     * @param dto
     * @param listCsv
     */
	private void makeTitle(MiseInfoExtractDto dto, List listCsv) {
		List infoList = new ArrayList();
		List listTitle = new ArrayList();

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

		listCsv.add(infoList);
		listCsv.add(listTitle);
	}

	/**
	 * CSVファイルのデータ部を作成する
	 * @param dto
	 * @param listCsv
	 */
    private void makeData(MiseInfoExtractDto dto, List listCsv) {
    	DateFormatter dateFormart = new DateFormatter();

        for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
        	List listData = new ArrayList();

        	// 店固定情報を作成する
        	MstMiSeJoho entityData = (MstMiSeJoho) ite.next();
        	// 基本情報(※表示必須)
        	listData.add(entityData.getMiseCd());           // 店コード
        	listData.add(entityData.getMiseNameKj());       // 店名称（漢字）
        	listData.add(entityData.getMiseNameKna());      // 店名称（カナ）
        	listData.add(entityData.getOnerCd());       	// オーナーコード
        	listData.add(entityData.getOnerNameKj());       // オーナー名称
        	listData.add(entityData.getSibuCd());       	// 支部コード
        	listData.add(entityData.getSibuName());         // 支部名称
        	listData.add(entityData.getSibuToriCd());       // 支部取込コード
        	listData.add(entityData.getSibuToriName());     // 支部取込コード名称
        	listData.add(entityData.getMiseKbn());       	// 店区分
        	listData.add(entityData.getAiteName());       	// 店区分名称
        	listData.add(entityData.getGyotaiKbn());       	// 業態区分
        	listData.add(entityData.getGyotaiKbnName()); 	// 業態区分名称
        	listData.add(entityData.getSvCd());       		// 担当SVコード
        	listData.add(entityData.getUserNameKj());       // 担当SV名称
        	// 店オープン日
        	listData.add(dateFormart.format(entityData.getOpenDt(), "yyyy/MM/dd", 1));
        	// 店クローズ日
        	listData.add(dateFormart.format(entityData.getCloseDt(), "yyyy/MM/dd", 1));
        	listData.add(entityData.getMiseCdShinten());  	// 新店時店コード
        	listData.add(entityData.getMiseCdSaishin());  	// 最新店コード
        	// 住所情報(※カテゴリ「日付店舗継承」を選択する場合、表示可能)
        	if(dto.getKatekoriCheck2()){
        		listData.add(entityData.getKenCd());  	    // 県コード
        		listData.add(entityData.getKenName());      // 県名称
        		listData.add(entityData.getMisePostNo());  	// 郵便番号
        		listData.add(entityData.getMiseAdrs1());  	// 店住所1
        		listData.add(entityData.getMiseAdrs2());  	// 店住所2
        		listData.add(entityData.getMiseAdrs3());  	// 店住所3
        		listData.add(entityData.getMiseTel());  	// 電話番号
        	}

        	// 付属情報(※カテゴリ「特性・営業時間」を選択する場合、表示可能)
        	if(dto.getKatekoriCheck3()){
        		listData.add(entityData.getMTypeKbn());  		// 店舗タイプ区分
        		listData.add(entityData.getMTypeKbnName());  	// 店舗タイプ区分名称
        		listData.add(entityData.getTMiseKeitai());  	// 店舗形態区分
        		listData.add(entityData.getMKeitaiName());  	// 店舗形態区分名称
        		listData.add(entityData.getTLocateKbn());  		// ロケーション区分
        		listData.add(entityData.getLocateName());  		// ロケーション区分名称
        		listData.add(entityData.getTentai()); 		 	// 転貸
        		// 転貸開始日
            	listData.add(dateFormart.format(entityData.getTentaiStartDt(), "yyyy/MM/dd", 1));
            	// 転貸終了日
            	listData.add(dateFormart.format(entityData.getTentaiEndDt(), "yyyy/MM/dd", 1));
        		listData.add(entityData.getTentaiInfo());  		// 転貸情報
        		listData.add(entityData.getMiseLeaseKCd());  	// 賃貸店舗経理コード

    			// 賃貸店舗履歴データを作成する
    			for (int cdIndex = 0; cdIndex < tintaTenpoSyuBetuCdList.size(); cdIndex++) {
    				String syuBetuCd = tintaTenpoSyuBetuCdList.get(cdIndex);
    				if (mstChintaiMap.containsKey(entityData.getMiseCd().concat(syuBetuCd))) {
    					List<MstChintai> listForCsv = new ArrayList<MstChintai>();
    					listForCsv = mstChintaiMap.get(entityData.getMiseCd().concat(syuBetuCd));
    					if (listForCsv.size() < tintaTenpoSyuBetuMap.get(syuBetuCd)) {
    						for (int i = 0; i < tintaTenpoSyuBetuMap.get(syuBetuCd); i++) {
    							if (i < listForCsv.size()) {
    								MstChintai entity = listForCsv.get(i);
    								listData.add(dateFormart.format(entity.getMiseLeaseStart(), "yyyy/MM/dd", 1));
    								listData.add(dateFormart.format(entity.getMiseLeaseEnd(), "yyyy/MM/dd", 1));
    							} else {
    								listData.add("");
    								listData.add("");
    							}
    						}
    					} else {
    						for (int i = 0; i < listForCsv.size(); i++) {
    							MstChintai entity = listForCsv.get(i);
    							listData.add(dateFormart.format(entity.getMiseLeaseStart(), "yyyy/MM/dd", 1));
    							listData.add(dateFormart.format(entity.getMiseLeaseEnd(), "yyyy/MM/dd", 1));
    						}
    					}
    				} else {
    					Integer countNull = tintaTenpoSyuBetuMap.get(syuBetuCd);
    					for (int j = 0; j < countNull; j++) {
    						listData.add("");
    						listData.add("");
    					}
    				}
    			}
        	}

			listCsv.add(listData);
		}
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

	@Override
	public void validate(CsvOutputDto paramCsvOutputDto) {
		MiseInfoExtractDto dto = (MiseInfoExtractDto) paramCsvOutputDto;
		if (CommonUtil.isNull(dto.getShukeiKbnCd())) {
			throw new NotNullException("集計区分");
		}

		/* 集計区分で『SV指定(担当店一覧)』が選択された場合 */
		if (dto.isSvFlg()) {
			if (CommonUtil.isNull(dto.getSvCd())) {
				throw new NotNullException("SVコード");
			}
		}
		// 支部(店舗一覧)検索の場合
		else if (TaishoJoken.CODE_SIBU.equals(dto.getTaishoJoken())) {
			if (!dto.isSvFlg()) {
				// 支部コードの必須チェックを行います。
				if (CommonUtil.isNull(dto.getSibuCd())) {
					throw new NotNullException("対象支部");
				}
			}
		}
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

}
