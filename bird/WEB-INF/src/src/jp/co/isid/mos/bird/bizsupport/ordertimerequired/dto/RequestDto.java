/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.TaishoKikan;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * リクエストDTO
 * 
 * 作成日:2009/10/15
 * @author xkinu
 *
 */
public class RequestDto implements CsvOutputDto {
	private String msg = "";
	private Exception exceptionObj;
	/**
	 * リクエスト対象VIEWID
	 */
	private String viewId;

	/** ウィンドウID */
    private int windowId;
    /**
     * [条件項目]店舗リスト
     */
    private List listMise = new ArrayList(0);
    /**
     * [条件項目]対象年月リスト
     */
    private List listKikan = new ArrayList(0);
    
    /** [条件項目]会社コード（MOSフードサービス固定) */
    private String companyCd=CommonUtil.COMPANY_CD_MOS;
    /**
     * [条件項目]店コード
     */ 
    private String miseCd;
    /**
     * [条件項目]対象期間
     */ 
    private String taishoKikan;
    /**
     * [条件項目]対象期間From
     */ 
    private String kikanFrom;
    /**
     * [条件項目]対象期間To
     */ 
    private String kikanTo;
    
    /**
     * [表示項目]店名称
     */ 
    private String miseNameKj;
    
    private List ListOutputData = new ArrayList(0);
    
    public void clear() {
    	setMiseCd(null);
    	setTaishoKikan(null);
    	setKikanFrom(null);
    	setKikanTo(null);
    	setMiseNameKj(null);
    	
    	setMsg(null);
    	setExceptionObj(null);
    }
	/**
	 * @return listKikan を戻します。
	 */
	public List getListKikan() {
		return listKikan;
	}
	/**
	 * @param listKikan を クラス変数listTaishoKikanへ設定します。
	 */
	public void setListKikan(List listTaishoKikan) {
		this.listKikan = listTaishoKikan;
	}
	/** 店コード取得処理 */
	public String getMiseCd() {
		return miseCd;
	}
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan() {
		return taishoKikan;
	}
	/**
	 * @param taishoKikan を クラス変数taishoKikanへ設定します。
	 */
	public void setTaishoKikan(String taishoKikan) {
		this.taishoKikan = taishoKikan;
	}
	/**
	 * @return kikanFrom を戻します。
	 */
	public String getKikanFrom() {
		return kikanFrom;
	}
	/**
	 * @param kikanFrom を クラス変数kikanFromへ設定します。
	 */
	public void setKikanFrom(String kikanFrom) {
		this.kikanFrom = kikanFrom;
	}
	/**
	 * @return kikanTo を戻します。
	 */
	public String getKikanTo() {
		return kikanTo;
	}
	/**
	 * @param kikanTo を クラス変数kikanToへ設定します。
	 */
	public void setKikanTo(String kikanTo) {
		this.kikanTo = kikanTo;
	}
	public String getViewId() {
		return viewId;
	}
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	public int getWindowId() {
		return windowId;
	}
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * @return msg を戻します。
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg 設定する msg。
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return exceptionObj を戻します。
	 */
	public Exception getExceptionObj() {
		return exceptionObj;
	}
	/**
	 * @param exceptionObj 設定する exceptionObj。
	 */
	public void setExceptionObj(Exception exceptionObj) {
		this.exceptionObj = exceptionObj;
	}
	/**
	 * @return companyCd を戻します。
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
	 * @return listMise を戻します。
	 */
	public List getListMise() {
		return listMise;
	}
	/**
	 * @param listMise を クラス変数listMiseへ設定します。
	 */
	public void setListMise(List listMise) {
		this.listMise = listMise;
	}
	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}
	/**
	 * @param miseNameKj を クラス変数miseNameKjへ設定します。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}
	/**
	 * @return listOutputData を戻します。
	 */
	public List getListOutputData() {
		return ListOutputData;
	}
	/**
	 * @param listOutputData を クラス変数listOutputDataへ設定します。
	 */
	public void setListOutputData(List listOutputData) {
		ListOutputData = listOutputData;
	}
	/**
	 * 対象期間プルダウン用リスト取得処理
	 * @return List 対象期間プルダウン用リスト
	 */
	public List getListTaishoKikan() {
		return TaishoKikan.getPullDownList(TaishoKikan.CT_DAY);
	}
}
