package jp.co.isid.mos.bird.storemanage.misehistoryextract.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 店マスタ履歴抽出
 */
public class MiseHistoryExtractDto implements DownloadDto, CsvOutputDto, ExcelOutputDto {

	private String condMiseCd; // 店コード
	private List mstMiSeRiRekiJoho; // 店マスタ履歴情報
	private List tintaTenpoSyuBetuJoho; // 賃貸店舗種別情報
	private List mstChintai; // 賃貸店舗履歴情報

	/**
	 * @return condMiseCd を戻します。
	 */
	public String getCondMiseCd() {
		CodeFormatter formatter = new CodeFormatter(5);
		formatter.setFormatPattern("00000");
		return formatter.format(condMiseCd, true);
	}

	/**
	 * @return fileName を戻します。
	 */
	public String getFileName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = format.format(date);
		return "mise_rireki_" + strDate + ".xlsx";
	}

	/**
	 * @param condMiseCd
	 *            condMiseCd を設定。
	 */
	public void setCondMiseCd(String condMiseCd) {
		this.condMiseCd = condMiseCd;
	}

	/**
	 * @return mstMiSeRiRekiJoho を戻します。
	 */
	public List getMstMiSeRiRekiJoho() {
		return mstMiSeRiRekiJoho;
	}

	/**
	 * @param mstMiSeRiRekiJoho
	 *            mstMiSeRiRekiJohoを設定。
	 */
	public void setMstMiSeRiRekiJoho(List mstMiSeRiRekiJoho) {
		this.mstMiSeRiRekiJoho = mstMiSeRiRekiJoho;
	}

	/**
	 * @return tintaTenpoSyuBetuJoho を戻します。
	 */
	public List getTintaTenpoSyuBetuJoho() {
		return tintaTenpoSyuBetuJoho;
	}

	/**
	 * @param tintaTenpoSyuBetuJoho
	 *            tintaTenpoSyuBetuJohoを設定。
	 */
	public void setTintaTenpoSyuBetuJoho(List tintaTenpoSyuBetuJoho) {
		this.tintaTenpoSyuBetuJoho = tintaTenpoSyuBetuJoho;
	}

	/**
	 * @return mstChintai を戻します。
	 */
	public List getMstChintai() {
		return mstChintai;
	}

	/**
	 * @param mstChintai
	 *            mstChintaiを設定。
	 */
	public void setMstChintai(List mstChintai) {
		this.mstChintai = mstChintai;
	}
}