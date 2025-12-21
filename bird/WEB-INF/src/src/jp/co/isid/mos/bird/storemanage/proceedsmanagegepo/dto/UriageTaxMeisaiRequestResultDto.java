package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UriageTaxMeisaiInfo;

/**
 * 売上消費税明細・DTOクラス
 * 2019/07/16
 * @author USI 張
 */
public class UriageTaxMeisaiRequestResultDto extends MeisaiRequestDto{


	/**
	 * データ一覧
	 */
	private List<UriageTaxMeisaiInfo> uriageTaxMeisaiList;

	/**
	 * 対象店舗の名前
	 */
	private String taishoTenpo;

	/**
	 * 会社の名前
	 */
	private String companyNm;

	/**
	 * データ一覧を取得する
	 * @return uriageTaxMeisaiList データ一覧
	 */
	public List<UriageTaxMeisaiInfo> getUriageTaxMeisaiList() {
		return uriageTaxMeisaiList;
	}

	/**
	 * データ一覧を設定する
	 * @return uriageTaxMeisaiList データ一覧
	 */
	public void setUriageTaxMeisaiList(List<UriageTaxMeisaiInfo> uriageTaxMeisaiList) {
		this.uriageTaxMeisaiList = uriageTaxMeisaiList;
	}

	/**
	 * 対象店舗の名前を取得する
	 * @return taishoTenpo 対象店舗の名前
	 */
	public String getTaishoTenpo() {
		return taishoTenpo;
	}

	/**
	 * 対象店舗の名前を設定する
	 * @param taishoTenpo 会社の名前
	 */
	public void setTaishoTenpo(String taishoTenpo) {
		this.taishoTenpo = taishoTenpo;
	}

	/**
	 * 会社の名前を取得する
	 * @return companyNm 会社の名前
	 */
	public String getCompanyNm() {
		return companyNm;
	}

	/**
	 * 会社の名前を設定する
	 * @param companyNm 会社の名前
	 */
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
}
