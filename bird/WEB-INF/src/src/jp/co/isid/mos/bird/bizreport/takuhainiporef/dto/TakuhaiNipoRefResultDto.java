package jp.co.isid.mos.bird.bizreport.takuhainiporef.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultDto;

/**
 * 宅配売上日報 結果情報DTOクラス
 *
 * @author xjung
 */
public class TakuhaiNipoRefResultDto extends NipoRefResultDto{
	/**
	 * 支部(有無別)リスト
	 */
	private List umuRstList = new ArrayList(0);

	/**
	 * 支部(タイプ別)リスト
	 */
	private List typeRstList = new ArrayList(0);

	/**
	 * 店舗別リスト
	 */
	private List miseRstList = new ArrayList(0);

	/** 宅配種別区分名称 */
	private String takuKbnName = "";
    /**
     * 支部別宅配売上情報クリア
     */
    public void clearSibu() {
        super.clear();
        setUmuRstList(new ArrayList(0));
        setTypeRstList(new ArrayList(0));
        setTakuKbnName("");
    }

    /**
     * 店舗別宅配売上情報(オーナー用)クリア
     */
    public void clearMise() {
        super.clear();
        setMiseRstList(new ArrayList(0));
        setTakuKbnName("");
    }

    /**
     * 支部別リストデータ存在有無を判定する
     * @return true:データ有、false:データ無
     */
    public boolean getEmptyUmuRstList() {
        return umuRstList == null || umuRstList.isEmpty() ? true : false;
    }
    /**
     * 支部別リストデータ存在有無を判定する
     * @return true:データ有、false:データ無
     */
    public boolean getEmptyTypeRstList() {
        return typeRstList == null || typeRstList.isEmpty() ? true : false;
    }

    /**
     * 店舗別リストデータ存在有無を判定する
     * @return true:データ有、false:データ無
     */
    public boolean getEmptyMiseRstList() {
		return getMiseRstList() == null || getMiseRstList().isEmpty() ? true : false;
	}
	/**
	 * 支部(有無別)リストを取得する
	 * @return List 支部(有無別)リスト
	 */
	public List getUmuRstList() {
		return umuRstList;
	}

	/**
	 * 支部(有無別)リストを設定する
	 * @param umuRstList 支部(有無別)リスト
	 */
	public void setUmuRstList(List umuRstList) {
		this.umuRstList = umuRstList;
	}

	/**
	 * 支部(タイプ別)リストを取得する
	 * @return List 支部(タイプ別)リスト
	 */
	public List getTypeRstList() {
		return typeRstList;
	}

	/**
	 * 支部(タイプ別)リストを設定する
	 * @param typeRstList 支部(タイプ別)リスト
	 */
	public void setTypeRstList(List typeRstList) {
		this.typeRstList = typeRstList;
	}
	/**
	 * @return クラス変数miseRstList を戻します。
	 */
	public List getMiseRstList() {
		return miseRstList;
	}

	/**
	 * @param miseRstList を クラス変数miseRstListへ設定します。
	 */
	public void setMiseRstList(List miseRstList) {
		this.miseRstList = miseRstList;
	}

	/**
	 * @return クラス変数takuKbnName を戻します。
	 */
	public String getTakuKbnName() {
		return takuKbnName;
	}

	/**
	 * @param takuKbnName を クラス変数takuKbnNameへ設定します。
	 */
	public void setTakuKbnName(String takuKbnName) {
		this.takuKbnName = takuKbnName;
	}
}