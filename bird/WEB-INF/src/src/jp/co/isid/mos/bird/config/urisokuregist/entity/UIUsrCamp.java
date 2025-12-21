/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;

/**
 * 売上速報対象キャンペーン
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class UIUsrCamp extends TrnUsrCamp {
    
    public static final String seqNo_COLUMN = "SEQ_NO";

    public static final String campTitle_COLUMN = "CAMP_TITLE";

    public static final String campFrom_COLUMN = "CAMP_FROM";

    public static final String campTo_COLUMN = "CAMP_TO";

    public static final String menuCnt_COLUMN = "MENU_CNT";

    public static final String menuSeqNo_COLUMN = "MENU_SEQ_NO";

    public static final String menuCd_COLUMN = "MENU_CD";

    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";

    /**
     * 行番号
     */
    private BigDecimal seqNo;
    /**
     * キャンペーンタイトル
     */
    private String campTitle;
    /**
     * キャンペーン期間開始日
     */
    private String campFrom;
    /**
     * キャンペーン期間終了日
     */
    private String campTo;
    /**
     * キャンペーン対象メニュー数
     */
    private BigDecimal menuCnt;
    /**
     * キャンペーン対象メニュー行番号
     */
    private String menuSeqNo;
    /**
     * メニューコード
     */
    private String menuCd;
    /**
     * メニュー名称
     */
    private String menuNameKj;

    /**
     * 対象データ行番号
     */
    private String taishoSeqNo;
	/**
	 * @return campFrom を戻します。
	 */
	public String getCampFrom() {
		return campFrom;
	}

	/**
	 * @param campFrom campFromへ設定します。
	 */
	public void setCampFrom(String campFrom) {
		this.campFrom = campFrom;
	}

	/**
	 * @return campTitle を戻します。
	 */
	public String getCampTitle() {
		return campTitle;
	}

	/**
	 * @param campTitle campTitleへ設定します。
	 */
	public void setCampTitle(String campTitle) {
		this.campTitle = campTitle;
	}

	/**
	 * @return campTo を戻します。
	 */
	public String getCampTo() {
		return campTo;
	}

	/**
	 * @param campTo campToへ設定します。
	 */
	public void setCampTo(String campTo) {
		this.campTo = campTo;
	}

	/**
	 * @return menuCd を戻します。
	 */
	public String getMenuCd() {
		return menuCd;
	}

	/**
	 * @param menuCd menuCdへ設定します。
	 */
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	/**
	 * @return menuCnt を戻します。
	 */
	public BigDecimal getMenuCnt() {
		return menuCnt;
	}

	/**
	 * @param menuCnt menuCntへ設定します。
	 */
	public void setMenuCnt(BigDecimal menuCnt) {
		this.menuCnt = menuCnt;
	}

	/**
	 * @return menuNameKj を戻します。
	 */
	public String getMenuNameKj() {
		return menuNameKj;
	}

	/**
	 * @param menuNameKj menuNameKjへ設定します。
	 */
	public void setMenuNameKj(String menuNameKj) {
		this.menuNameKj = menuNameKj;
	}

	/**
	 * @return menuSeqNo を戻します。
	 */
	public String getMenuSeqNo() {
		return menuSeqNo;
	}

	/**
	 * @param menuSeqNo menuSeqNoへ設定します。
	 */
	public void setMenuSeqNo(String menuSeqNo) {
		this.menuSeqNo = menuSeqNo;
	}

	/**
	 * @return seqNo を戻します。
	 */
	public BigDecimal getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo seqNoへ設定します。
	 */
	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * @return jisseki を戻します。
	 */
	public boolean isJisseki() {
		return getJissekiFlg().equals(UrisokuRegistUtil.SELECTED);
	}

	/**
	 * @param flg jissekiへ設定します。
	 */
	public void setJisseki(boolean flg) {
		setJissekiFlg(flg?UrisokuRegistUtil.SELECTED:"0");
	}

	/**
	 * @return title を戻します。
	 */
	public boolean isTitle() {
		return getTitleFlg().equals(UrisokuRegistUtil.SELECTED);
	}

	/**
	 * @param flg を titleFlgへ設定します。
	 */
	public void setTitle(boolean flg) {
		setTitleFlg(flg?UrisokuRegistUtil.SELECTED:"0");
	}

	/**
	 * @return taishoSeqNo を戻します。
	 */
	public String getTaishoSeqNo() {
		return taishoSeqNo;
	}

	/**
	 * @param taishoSeqNo を クラス変数taishoSeqNoへ設定します。
	 */
	public void setTaishoSeqNo(String taishoSeqNo) {
		this.taishoSeqNo = taishoSeqNo;
	}

}
