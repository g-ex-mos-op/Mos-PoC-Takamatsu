package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto;

import java.util.List;

/**
 * 売上金管理月報明細照会・結果部DTOクラス
 *
 * @author xogawa
 */
public class MeisaiRequestResultDto extends MeisaiRequestDto {

	/**
	 * タブ別情報
	 */
	private List tabDataList;

	/**
	 * 会社名
	 */
	private String companyNm;
	
	/**
	 * タブ別情報を取得する
	 * @return tabDataList タブ別情報
	 */
	public List getTabDataList() {
		return tabDataList;
	}

	/**
	 * タブ別情報を設定する
	 * @param tabDataList タブ別情報
	 */
	public void setTabDataList(List tabDataList) {
		this.tabDataList = tabDataList;
	}
	
	/**
	 * タブ別情報サイズを取得する
	 * @return int タブ別情報サイズ
	 */
	public int getTabDataListLength() {
		if(tabDataList == null || tabDataList.isEmpty()){
			return 0;
		}
		return tabDataList.size();
	}

	/**
	 * タブ別情報サイズを設定する
	 * @param int タブ別情報サイズ（ダミー）
	 */
	public void setTabDataListLength(int tabDataListLength) {
		// ダミー
	}

	/**
	 * 会社名を取得する
	 * @return companyNm 会社名
	 */
	public String getCompanyNm() {
		return companyNm;
	}

	/**
	 * 会社名を設定する
	 * @param companyNm 会社名
	 */
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
}
