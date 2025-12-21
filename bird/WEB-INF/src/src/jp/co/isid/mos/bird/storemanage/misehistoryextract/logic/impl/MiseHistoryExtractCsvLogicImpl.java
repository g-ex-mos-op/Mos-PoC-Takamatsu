/*
 * 作成日: 2016/02/19
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dao.MstMiSeRiRekiJohoDao;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dao.TintaTenpoSyuBetuJohoDao;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.dto.MiseHistoryExtractDto;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.MstChintai;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.MstMiSeRiRekiJoho;
import jp.co.isid.mos.bird.storemanage.misehistoryextract.entity.TintaTenpoSyuBetuJoho;

/**
 * 店マスタ履歴抽出画面 Csvデータリストの作成
 */
public class MiseHistoryExtractCsvLogicImpl implements CsvOutputLogic {
	 /* ロジックID */
    public static final String LOGIC_ID = "BSI009L01";

	private MstMiSeRiRekiJohoDao mstMiSeRiRekiJohoDao;
	private TintaTenpoSyuBetuJohoDao tintaTenpoSyuBetuJohoDao;
	private MstChintaiDao mstChintaiDao;
	private Map<String, Integer> maxLeaseShuCountMap = new HashMap();
	private Map<String, List> dataMap = new HashMap();

	/**
	 * ファイル名取得
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		// ファイル名を作成
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String fileName = "mise_rireki_" + format.format(date) + ".csv";
		return fileName;
	}

	/**
	 * CSV出力内容取得
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

		// 出力データ取得
		MiseHistoryExtractDto dto = (MiseHistoryExtractDto) csvOutputDto;
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

		// CSV出力内容を作成
		List listCSV = new ArrayList();

		try {
			// ヘッダ部
			makeHeader(dto, listCSV);

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

			// データ部
			DateFormatter formart = new DateFormatter();
			for (Iterator ite = dto.getMstMiSeRiRekiJoho().iterator(); ite.hasNext();) {
				MstMiSeRiRekiJoho entity = (MstMiSeRiRekiJoho) ite.next();

				// 固定なデータ部を作成
				List<String> listData = new ArrayList<String>();
				listData.add(formart.format(entity.getMiseMDate(), "yyyy/MM/dd", 1));
				listData.add(entity.getMiseCd());
				listData.add(entity.getMiseNameKj());
				listData.add(entity.getMiseNameKna());
				listData.add(entity.getOnerCd());
				listData.add(entity.getOnerNameKj());
				listData.add(entity.getSibuCd());
				listData.add(entity.getSibuName());
				listData.add(entity.getSibuToriCd());
				listData.add(entity.getSibuToriName());
				listData.add(entity.getMiseKbn());
				listData.add(entity.getAiteName());
				listData.add(entity.getGyotaiKbn());
				listData.add(entity.getGyotaiKbnName());
				listData.add(entity.getSvCd());
				listData.add(entity.getUserNameKj());
				listData.add(formart.format(entity.getOpenDt(), "yyyy/MM/dd", 1));
				listData.add(formart.format(entity.getCloseDt(), "yyyy/MM/dd", 1));
				listData.add(entity.getMiseCdShinten());
				listData.add(entity.getMiseCdSaishin());
				listData.add(entity.getKenCd());
				listData.add(entity.getKenName());
				listData.add(entity.getMisePostNo());
				listData.add(entity.getMiseAdrs1());
				listData.add(entity.getMiseAdrs2());
				listData.add(entity.getMiseAdrs3());
				listData.add(entity.getMiseTel());
				listData.add(entity.getMTypeKbn());
				listData.add(entity.getMTypeKbnName());
				listData.add(entity.getTMiseKeitai());
				listData.add(entity.getMKeitaiName());
				listData.add(entity.getTLocateKbn());
				listData.add(entity.getLocateName());
				listData.add(entity.getTentai());
				listData.add(formart.format(entity.getTentaiStartDt(), "yyyy/MM/dd", 1));
				listData.add(formart.format(entity.getTentaiEndDt(), "yyyy/MM/dd", 1));
				listData.add(entity.getTentaiInfo());
				listData.add(entity.getMiseLeaseKCd());

				// 可変なデータ部を作成
				for (Iterator iter = dto.getTintaTenpoSyuBetuJoho().iterator(); iter.hasNext();) {
					TintaTenpoSyuBetuJoho joho = (TintaTenpoSyuBetuJoho) iter.next();
					int maxCount = joho.getMaxLeaseShuCount();
					List dataList = this.dataMap.get(entity.getMiseMDate() + joho.getMiseLeaseShu());

					for (int i = 0; i < maxCount; i++) {
						if (dataList != null && i < dataList.size()) {
							MstChintai data = (MstChintai) dataList.get(i);
							listData.add(formart.format(data.getMiseLeaseStart(), "yyyy/MM/dd", 1));
							listData.add(formart.format(data.getMiseLeaseEnd(), "yyyy/MM/dd", 1));
						} else {
							listData.add("");
							listData.add("");
						}
					}
				}

				listCSV.add(listData);
			}
		} catch (Exception ex) {
			throw new FtlSystemException("CSV作成");
		}

		return listCSV;
	}

	/**
	 * ヘッダ部データを作成
	 */
	private void makeHeader(MiseHistoryExtractDto dto, List listCsv) {
		List<String> listDownloadTarget = new ArrayList<String>();
		List<String> listMise = new ArrayList<String>();
		List<String> listDownloadDate = new ArrayList<String>();
		List<String> listNullRow = new ArrayList<String>();
		List<String> listKoumoku = new ArrayList<String>();

		// ダウンロード対象
		listDownloadTarget.add("ダウンロード対象：");
		listDownloadTarget.add("店マスタ履歴");

		// 店コード
		listMise.add("店コード：");
		listMise.add(dto.getCondMiseCd());
		listMise.add(this.getMiseName(dto));

		// ダウンロード日付
		listDownloadDate.add("ダウンロード日付：");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		listDownloadDate.add(format.format(date));

		// 項目ヘッダー
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

		// ヘッダを作成
		listCsv.add(listDownloadTarget);
		listCsv.add(listMise);
		listCsv.add(listDownloadDate);
		listCsv.add(listNullRow);
		listCsv.add(listKoumoku);
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
	 * 妥当性チェック
	 */
	public void validate(CsvOutputDto csvOutputDto) {
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
