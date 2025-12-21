/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dto;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.SessionDto;

/**
 * 店舗衛生結果
 * DTO【RequestDto】
 * 
 * 作成日:2012/12/05
 * @author xkinu
 *
 */
public class RequestDto {
	/** ウィンドウID */
	private int windowId;
    /**
     * 会社コード：モス固定
     */
    private String companyCd = CommonUtil.COMPANY_CD_MOS;
    /**
     * 実績年度
     */
    private String nendo;
    /**
     * 対象店舗コード
     */
    private String miseCd;
	/**
	 * 対象店舗コード
	 * @return クラス変数miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * 対象店舗コード
	 * @param miseCd を クラス変数miseCdへ設定します。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * 実績年度
	 * @return クラス変数nendo を戻します。
	 */
	public String getNendo() {
		return nendo;
	}
	/**
	 * 実績年度
	 * @param nendo を クラス変数nendoへ設定します。
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	/**
	 * クリア処理
	 *
	 */
	public void reset() {
		setCompanyCd(CommonUtil.COMPANY_CD_MOS);
		setMiseCd(null);
		setNendo(null);
	}
	/**
	 * ウィンドウID
	 * @return クラス変数windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * ウィンドウID
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * @return クラス変数companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd を クラス変数companyCdへ設定します。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * 初期化処理
	 * 
	 * 1.ユーザタイプコード設定
	 * 2.ウィンドウIDの生成と設定
	 * 3.条件項目情報の設定
	 * 4.デフォルト値の設定
	 */
	public void initialaze(SessionDto sessonDto) {
		//１.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定する。
        int windowId = sessonDto.createWindowId();
        //２.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
        setWindowId(windowId);
	}
}
