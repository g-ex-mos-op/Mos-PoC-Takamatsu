/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.suiiref.entity;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;

/**
 * UITabData[タブ情報]
 * 
 * 作成日:2013/04/11
 * @author xkinu
 *
 */
public class UITabData {
	/** タブコード */
    public static final String code_COLUMN      = "CODE";
    /** 表示対象コード */
    public static final String htCd_COLUMN      = "HT_CD";
    /** 表示対象名称 */
    public static final String htName_COLUMN      = "HT_NAME";
    /** 対象(当年)店舗数 */
    public static final String miseCnt_COLUMN      = "MISE_CNT";
    /** 全店舗数(当年or前年or予算有り) */
    public static final String miseCntAll_COLUMN      = "MISE_CNT_ALL";

	/** タブコード */
    private String code;
	/** 表示対象コード */
    private String htCd;
	/** 表示対象名称 */
    private String htName;
    /** 対象(当年)店舗数 */
    private int miseCnt = 0;
    /** 全店舗数(当年or前年or予算のみ) */
    private int miseCntAll = 0;
    /** ラベル */
    private String label;
    /** 未来判断値 */
    private boolean isFuture = false;
	/**
	 * タブラベル
	 * @return クラス変数label を戻します。
	 */
	public String getLabel() {
		if ( SuiiRefUtil.SUII_TYPE_GEPO.equals(getCode()) ) {
			return "月次";
		}
		return label;
	}
	/**
	 * タブラベル
	 * @param label を クラス変数labelへ設定します。
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * タブコード
	 * @return クラス変数code を戻します。
	 */
	public String getCode() {
		return code;
	}
	/**
	 * タブコード
	 * @param code を クラス変数codeへ設定します。
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 対象(当年)店舗数
	 * @return クラス変数miseCnt を戻します。
	 */
	public int getMiseCnt() {
		return miseCnt;
	}
	/**
	 * 対象(当年)店舗数
	 * @param miseCnt を クラス変数miseCntへ設定します。
	 */
	public void setMiseCnt(int miseCnt) {
		this.miseCnt = miseCnt;
	}
	/**
	 * 未来判断値
	 * @return クラス変数isFuture を戻します。
	 */
	public boolean isFuture() {
		return isFuture;
	}
	/**
	 * 未来判断値
	 * @param isFuture を クラス変数isFutureへ設定します。
	 */
	public void setFuture(boolean isFuture) {
		this.isFuture = isFuture;
	}
	/**
	 * 全店舗数(当年or前年or予算のみ)
	 * @return クラス変数miseCntAll を戻します。
	 */
	public int getMiseCntAll() {
		return miseCntAll;
	}
	/**
	 * 全店舗数(当年or前年or予算のみ)
	 * @param miseCntAll を クラス変数miseCntAllへ設定します。
	 */
	public void setMiseCntAll(int miseCntAll) {
		this.miseCntAll = miseCntAll;
	}
	/**
	 * 表示対象コード
	 * @return クラス変数htCd を戻します。
	 */
	public String getHtCd() {
		return htCd;
	}
	/**
	 * 表示対象コード
	 * @param htCd を クラス変数htCdへ設定します。
	 */
	public void setHtCd(String htCd) {
		this.htCd = htCd;
	}
	/**
	 * 表示対象名称
	 * @return クラス変数htName を戻します。
	 */
	public String getHtName() {
		return htName;
	}
	/**
	 * 表示対象名称
	 * @param htName を クラス変数htNameへ設定します。
	 */
	public void setHtName(String htName) {
		this.htName = htName;
	}
    /**
     * List[[検索結果]]
     */
    private List listResult = new ArrayList(0);
    /**
     * List[[検索結果]]
	 * @return クラス変数listResult を戻します。
	 */
	public List getListResult() {
		return listResult;
	}

	/**
	 * List[[検索結果]]
	 * @param listResult を クラス変数listResultへ設定します。
	 */
	public void setListResult(List listResult) {
		this.listResult = listResult;
	}
	/**
	 * 結果データ月次判断処理
	 * @return
	 */
	public boolean isSuiiTypeGepo() {
		return SuiiRefUtil.SUII_TYPE_GEPO.equals(getCode());
	}
 
}
