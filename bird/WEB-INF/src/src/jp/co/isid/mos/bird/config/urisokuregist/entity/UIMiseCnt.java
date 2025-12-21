/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;

import java.math.BigDecimal;

/**
 * 作成日:2008/09/01
 * @author xkinu
 *
 */
public class UIMiseCnt extends TrnUsrMiseCnt {
    /**
     * 店舗数
     */
    private String miseCnt;

	/**
	 * @return miseCnt を戻します。
	 */
	public String getMiseCnt() {
		return miseCnt;
	}

	/**
	 * @param miseCnt を クラス変数miseCntへ設定します。
	 */
	public void setMiseCnt(String miseCnt) {
		this.miseCnt = miseCnt;
	}
	/**
	 * @param miseCntAll miseCntAllへ設定します。
	 */
	public void setMiseCntAll(BigDecimal miseCntAll) {
		super.setMiseCntAll(miseCntAll);
		if(miseCntAll != null) {
			setMiseCnt(miseCntAll.toString());
		}
	}
}
