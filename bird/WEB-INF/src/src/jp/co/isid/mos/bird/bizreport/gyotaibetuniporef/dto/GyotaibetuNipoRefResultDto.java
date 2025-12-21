package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultDto;

/**
 * 宅配売上日報 結果情報DTO
 *
 * @author xjung
 */
public class GyotaibetuNipoRefResultDto extends NipoRefResultDto {
	/**
	 * List[[検索結果情報]]
	 */
    private List listView = new ArrayList(0);
    
    private String yagoName = "";
    /**
	 * @return クラス変数yagoName を戻します。
	 */
	public String getYagoName() {
		return yagoName;
	}

	/**
	 * @param yagoName を クラス変数yagoNameへ設定します。
	 */
	public void setYagoName(String yagoName) {
		this.yagoName = yagoName;
	}

	/**
     * 宅配売上日報 情報クリア
     *
     */
    public void clear() {
    	super.clear();
		setListView(null);
		setYagoName("");

    }

    /**
     * List[[検索結果情報]]存在有無を判定する<br>
     * @return true:データ有、false:データ無
     */
    public boolean getEmptyListView() {
		return getListView() == null || getListView().isEmpty() ? true : false;
	}

	/**
	 * List[[検索結果情報]]
	 * @return クラス変数listView を戻します。
	 */
	public List getListView() {
		return listView;
	}

	/**
	 * List[[検索結果情報]]
	 * @param listView を クラス変数listViewへ設定します。
	 */
	public void setListView(List listView) {
		this.listView = listView;
	}
}
