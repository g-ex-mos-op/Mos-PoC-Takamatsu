/**
 * 
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.dto;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * CSVダウンロードDTO
 * 
 * 作成日:2010/02/24
 * @author xkinu
 *
 */
public class CsvDownloadDto implements CsvOutputDto {
    /* システム日付 */
    private String sysDate;

	/**
	 * @return クラス変数sysDate を戻します。
	 */
	public String getSysDate() {
		return sysDate;
	}

	/**
	 * @param sysDate を クラス変数sysDateへ設定します。
	 */
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
}
