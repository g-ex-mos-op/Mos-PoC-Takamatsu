/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.dto;

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
     * 期間指定(前月の年月)
     */ 
    private Map dataKbn = new HashMap();
    
    private Map kikanSiteiDay = new HashMap();

	/**
	 * 前月の年月取得処理
	 * 
	 * @return dataKbn を戻します。
	 */
	public String getDataKbn(int windowId) {
		return (String)dataKbn.get(new Integer(windowId));
	}

	/**
	 * 前月の年月設定処理
	 * 
	 * @param kbn を クラス変数dataKbnへ設定します。
	 */
	public void setDataKbn(int windowId, String kbn) {
		this.dataKbn.put(new Integer(windowId), kbn);
	}
    /**
     * @return kikanSiteiTo を戻します。
     */
    public String getKikanSiteiTo(int windowId) {
        return (String)kikanSiteiDay.get(new Integer(windowId));
    }

    /**
     * @param kikanSiteiTo を クラス変数kikanSiteiToへ設定します。
     */
    public void setKikanSiteiTo(int windowId, String kikanSitei) {
        this.kikanSiteiDay.put(new Integer(windowId), kikanSitei);
    }
}
