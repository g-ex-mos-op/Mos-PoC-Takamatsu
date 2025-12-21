package jp.co.isid.mos.bird.storeinfo.miseref.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstTODlJyohoDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dto.TOHistoryCsvOutputDto;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstTODlJyoho;

public class DlMiseTOJyohoLogicImpl implements CsvOutputLogic {

	private static final String LOGIC_ID = "BSI001L05";

	private MstTODlJyohoDao mstTODlJyohoDao;
	private Date date = new Date();

	public MstTODlJyohoDao getMstTODlJyohoDao() {
		return mstTODlJyohoDao;
	}

	public void setMstTODlJyohoDao(MstTODlJyohoDao mstTODlJyohoDao) {
		this.mstTODlJyohoDao = mstTODlJyohoDao;
	}

	@Override
	public String getFileName(CsvOutputDto paramCsvOutputDto) {
		// TODO 自動生成されたメソッド・スタブ
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String strDate = format.format(date);
		strDate = strDate.split(" ")[0].replace("/", "");
		return "takeover_rireki_" + strDate + ".csv";
	}

	@Override
	public List getOutputData(CsvOutputDto paramCsvOutputDto) {
		// TODO 自動生成されたメソッド・スタブ
		TOHistoryCsvOutputDto csvOutputDto = (TOHistoryCsvOutputDto)paramCsvOutputDto;
		String companyCd = csvOutputDto.getCondCompanyCd();
		String miseCd = csvOutputDto.getCondMiseCd();

		// 出力データ取得
		List outputData = getMstTODlJyohoDao().selectTODlJyoho(companyCd, miseCd);

		if (outputData == null || outputData.size() == 0) {
			throw new NoResultException();
		}

		// CSV出力用リスト
		List listCsv = new ArrayList();

		// ヘッダ部
		List listHeader1 = new ArrayList();
		listHeader1.add("店コード：");
		MstTODlJyoho jyoho = (MstTODlJyoho)outputData.get(0);
		String miseNm = jyoho.getMiseName();
		listHeader1.add(convertNull(miseCd));
		listHeader1.add(convertNull(miseNm));
		listCsv.add(listHeader1);

		List listHeader2 = new ArrayList();
		listHeader2.add("ダウンロード日付：");
		listHeader2.add(new SimpleDateFormat("yyyy/MM/dd").format(date));
		listCsv.add(listHeader2);

		listCsv.add(new ArrayList());

		// データ部のヘッダ部
		List listRowHeader = new ArrayList();
		listRowHeader.add("引継ぎオープン日");
		listRowHeader.add("オープン日");
		listRowHeader.add("クローズ日");
		listRowHeader.add("店コード");
		listRowHeader.add("店名称");
		listRowHeader.add("新店時店コード");
		listRowHeader.add("最新店コード");
		listRowHeader.add("引継ぎ元店コード");
		listRowHeader.add("引継ぎ先店コード");
		listRowHeader.add("オーナーコード");
		listRowHeader.add("オーナー名称");
		listCsv.add(listRowHeader);

		// データ部
		for (Iterator it = outputData.iterator(); it.hasNext();) {
			List listRowData = new ArrayList();
			MstTODlJyoho hisData = (MstTODlJyoho)it.next();
			listRowData.add(convertStringDate(convertNull(hisData.getHikitugi_date())));
			listRowData.add(convertStringDate(convertNull(hisData.getOpen_date())));
			listRowData.add(convertStringDate(convertNull(hisData.getClose_date())));
			listRowData.add(convertNull(hisData.getMiseCd()));
			listRowData.add(convertNull(hisData.getMiseName()));
			listRowData.add(convertNull(hisData.getMiseCdShinten()));
			listRowData.add(convertNull(hisData.getMiseCdSaishin()));
			listRowData.add(convertNull(hisData.getMiseCdMoto()));
			listRowData.add(convertNull(hisData.getMiseCdSaki()));
			listRowData.add(convertNull(hisData.getOnerCd()));
			listRowData.add(convertNull(hisData.getOnerName()));

			listCsv.add(listRowData);
		}

		return listCsv;
	}

	@Override
	public void validate(CsvOutputDto paramCsvOutputDto) {
		//コードバリデーター
        CodeVerifier codeVerifier = new CodeVerifier(5,false);
        TOHistoryCsvOutputDto csvOutputDto = (TOHistoryCsvOutputDto)paramCsvOutputDto;
		String companyCd = csvOutputDto.getCondCompanyCd();
		String miseCd = csvOutputDto.getCondMiseCd();

        if (companyCd == null || "".equals(companyCd)) {
        	throw new NotNullException("会社コード");
        }
        if (!codeVerifier.validate(companyCd)) {
        	throw new InvalidInputException("会社コード");
        }

        if (miseCd == null || "".equals(miseCd)) {
        	throw new NotNullException("店コード", "condMiseCd", null);
        }
        if (!codeVerifier.validate(miseCd)) {
        	throw new InvalidInputException("店コード", "condMiseCd", null);
        }
	}

	private String convertStringDate(String strDate) {

		DateFormatter format = new DateFormatter();

		return format.format(strDate, "yyyy/MM/dd", DateFormatter.DATE_TYPE_YMD);
	}

	private String convertNull(String str) {
		return str == null ? "" : str;
	}
}
