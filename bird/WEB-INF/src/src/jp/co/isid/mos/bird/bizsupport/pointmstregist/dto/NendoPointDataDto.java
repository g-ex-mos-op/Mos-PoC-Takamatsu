/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;

/**
 * 年度付与ポイント
 * @author yushuncheng
 *
 */
public class NendoPointDataDto {

	/**
	 * データ追加フラグ
	 */
	private boolean addFlag;

	 /**
     * 対象年度
     */
    private String nendo;

    /**
	 * 付与ポイントリスト
	 */
    private List<UIPointMst> pointList;


	/**
	 * データ追加フラグを取得します。
	 * @return addFlag データ追加フラグ
	 */
	public boolean isAddFlag() {
		return addFlag;
	}

	/**
	 * データ追加フラグを設定します。
	 * @param addFlag データ追加フラグ
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	/**
	 * 対象年度を取得します。
	 * @return 対象年度
	 */
	public String getNendo() {
		return nendo;
	}

	/**
	 * 対象年度を設定します。
	 * @param nendo 対象年度
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}

	/**
	 * 付与ポイントデータListを取得します。
	 * @return pointList 付与ポイントデータList
	 */
	public List<UIPointMst> getPointList() {
		return pointList;
	}

	/**
	 *  付与ポイントデータListを設定します。
	 * @param pointList 付与ポイントデータList
	 */
	public void setPointList(List<UIPointMst> pointList) {
		this.pointList = pointList;
	}

}
