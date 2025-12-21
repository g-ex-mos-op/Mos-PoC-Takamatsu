/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dto;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * ダウンロード情報
 * 
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class FileDownloadDto extends RequestDto implements DownloadDto {
    /**
     * ダウンロードファイル種類
     * 
     * 値:jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1stのコード
     */
    private String tabKey;
    private String downloadFileName;
	/**
     * 値:jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1stのコード
	 * @return クラス変数tabKey を戻します。
	 */
	public String getTabKey() {
		return tabKey;
	}
	/**
	 * @param tabKey を クラス変数tabKeyへ設定します。
	 */
	public void setTabKey(String tabKey) {
		this.tabKey = tabKey;
	}
	/**
	 * 
	 * @param resultDto
	 */
	public void settingDefaultParam(ResultDto resultDto) {
		super.setCompanyCd(resultDto.getCompanyCd());
		super.setMiseCd(resultDto.getMiseCd());
		super.setNendo(resultDto.getNendo());
	}
	/**
	 * @return クラス変数downloadFileName を戻します。
	 */
	public String getDownloadFileName() {
		return downloadFileName;
	}
	/**
	 * @param downloadFileName を クラス変数downloadFileNameへ設定します。
	 */
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
}
