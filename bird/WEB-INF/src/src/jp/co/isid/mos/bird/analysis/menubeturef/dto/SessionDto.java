/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.dto;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuSesDto;

/**
 * メニュー別売上画面
 * SessionDto
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class SessionDto extends MenuBetuSesDto {
    /**
     * 期間指定終了日
     */
    private Map kikanSiteiTo = new HashMap();
    /**
     * 期間指定(前月の年月)
     */ 
    private Map kikanSiteiLastMonth = new HashMap();

	/**
	 * 前月の年月取得処理
	 * 
	 * @return lastMonth を戻します。
	 */
	public String getKikanSiteiLastMonth(int windowId) {
		return (String)kikanSiteiLastMonth.get(new Integer(windowId));
	}

	/**
	 * 前月の年月設定処理
	 * 
	 * @param lastMonth を クラス変数kikanSiteiLastMonthへ設定します。
	 */
	public void setKikanSiteiLastMonth(int windowId, String lastMonth) {
		this.kikanSiteiLastMonth.put(new Integer(windowId), lastMonth);
	}

	/**
	 * @return kikanSiteiTo を戻します。
	 */
	public String getKikanSiteiTo(int windowId) {
		return (String)kikanSiteiTo.get(new Integer(windowId));
	}

	/**
	 * @param kikanSiteiTo を クラス変数kikanSiteiToへ設定します。
	 */
	public void setKikanSiteiTo(int windowId, String kikanSiteiTo) {
		this.kikanSiteiTo.put(new Integer(windowId), kikanSiteiTo);
	}
}
