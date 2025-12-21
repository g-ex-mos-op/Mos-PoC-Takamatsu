/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dto;

import java.util.List;

/**
 * 推移表用セッションDTO
 * 
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class SuiiSessionDto {
	
	/** プルダウン値保持リスト：年度 */
	private List listNendo = null;
	private List listTaishoJoken = null;
	/** プルダウン値保持リスト：支部 */
	private List listSibuCd = null;
	private List listSlareaCd = null;
	private SuiiRequestDto holdRequestDto = null;
	/**
	 * @return クラス変数listSibuCd を戻します。
	 */
	public List getListSibuCd() {
		return listSibuCd;
	}
	/**
	 * @param listSibuCd を クラス変数listSibuCdへ設定します。
	 */
	public void setListSibuCd(List listSibuCd) {
		this.listSibuCd = listSibuCd;
	}
	/**
	 * @return クラス変数listSlareaCd を戻します。
	 */
	public List getListSlareaCd() {
		return listSlareaCd;
	}
	/**
	 * @param listSlareaCd を クラス変数listSlareaCdへ設定します。
	 */
	public void setListSlareaCd(List listSlareaCd) {
		this.listSlareaCd = listSlareaCd;
	}
	/**
	 * @return クラス変数listNendo を戻します。
	 */
	public List getListNendo() {
		return listNendo;
	}
	/**
	 * @param listNendo を クラス変数listNendoへ設定します。
	 */
	public void setListNendo(List listNendo) {
		this.listNendo = listNendo;
	}
	/**
	 * @return クラス変数listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}
	/**
	 * @param listTaishoJoken を クラス変数listTaishoJokenへ設定します。
	 */
	public void setListTaishoJoken(List listTaishoJoken) {
		this.listTaishoJoken = listTaishoJoken;
	}
	/**
	 * @return クラス変数holdRequestDto を戻します。
	 */
	public SuiiRequestDto getHoldRequestDto() {
		return holdRequestDto;
	}
	/**
	 * @param holdRequestDto を クラス変数holdRequestDtoへ設定します。
	 */
	public void setHoldRequestDto(SuiiRequestDto holdRequestDto) {
		this.holdRequestDto = holdRequestDto;
	}
	
}
