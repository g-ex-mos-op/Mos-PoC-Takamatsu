package jp.co.isid.mos.bird.common.entity;

/**
 * 照会用関連文書情報エンティティ
 * 
 * 作成日:2009/12/09
 * @author xkinu
 *
 */
public class UIViewKanrenBunsho extends UIDocSearch {
    
    public static final String TABLE = "BT11RLFL";
    //関連させたドキュメントの情報種別
    public static final String relInfoShu_COLUMN = "MOTO_INFO_SHU";
    //関連させたドキュメントの情報種別
    public static final String relRegDate_COLUMN = "MOTO_REG_DATE";
    //関連させたドキュメントの情報種別
    public static final String relSeq_COLUMN = "MOTO_SEQ";
    //関連ファイルのカテゴリーソートキー
    public static final String sortKeyCate_COLUMN = "SORT_KEY_CATE";
    //関連ファイルのサブカテゴリーソートキー
    public static final String sortKeySubCate_COLUMN = "SORT_KEY_SUB_CATE";

    /**
     * 関連先情報種別
     */
    private String motoInfoShu;
    
    /**
     * 関連先登録日
     */
    private String motoRegDate;
    
    /**
     * 関連先シーケンス番号
     */
    private String motoSeq;
    /**
     * 関連ファイルのカテゴリーソートキー
     */
    private String sortKeyCate;
    /**
     * 関連ファイルのサブカテゴリーソートキー
     */
    private String sortKeySubCate;
    /**
     * 関連先情報種別を取得します。
     * @return 関連先情報種別
     */
    public String getMotoInfoShu() {
        return motoInfoShu;
    }
    /**
     * 関連先情報種別を設定します。
     * @param motoInfoShu 関連先情報種別
     */
    public void setMotoInfoShu(String relInfoShu) {
        this.motoInfoShu = relInfoShu;
    }
    
    /**
     * 関連先登録日を取得します。
     * @return 関連先登録日
     */
    public String getMotoRegDate() {
        return motoRegDate;
    }
    /**
     * 関連先登録日を設定します。
     * @param motoRegDate 関連先登録日
     */
    public void setMotoRegDate(String relRegDate) {
        this.motoRegDate = relRegDate;
    }
    
    /**
     * 関連先シーケンス番号を取得します。
     * @return 関連先シーケンス番号
     */
    public String getMotoSeq() {
        return motoSeq;
    }
    /**
     * 関連先シーケンス番号を設定します。
     * @param motoSeq 関連先シーケンス番号
     */
    public void setMotoSeq(String relSeq) {
        this.motoSeq = relSeq;
    }
	/**
	 * @return クラス変数sortKeyCate を戻します。
	 */
	public String getSortKeyCate() {
		return sortKeyCate;
	}
	/**
	 * @param sortKeyCate を クラス変数sortKeyCateへ設定します。
	 */
	public void setSortKeyCate(String sortKeyCate) {
		this.sortKeyCate = sortKeyCate;
	}
	/**
	 * @return クラス変数sortKeySubCate を戻します。
	 */
	public String getSortKeySubCate() {
		return sortKeySubCate;
	}
	/**
	 * @param sortKeySubCate を クラス変数sortKeySubCateへ設定します。
	 */
	public void setSortKeySubCate(String sortKeySubCate) {
		this.sortKeySubCate = sortKeySubCate;
	}
}
