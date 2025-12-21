/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto;

import java.util.List;


/**
 * テナント入金明細登録画面
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class RequestDto {
	/** セッションKey */
	private String sesstionKey;
	
    /** DTO【自画面Session】*/
    private SessionDto tenantPayPdfRegistSesDto;
	/**
	 * リクエスト対象VIEWID
	 */
	private String viewId;

	/** ウィンドウID */
    private int windowId;
    
    /**
     * [条件項目]対象年月リスト
     */
    private List listTaishoYm;
    /**
     * [条件項目]店コード
     */ 
    private String miseCd;
    /**
     * [条件項目]対象年月
     */ 
    private String taishoYm;
    /**
     * [条件項目]回数
     */ 
    private String kaisuu;
    
    private int downloadIndex=-1;
    private int selectedIndex=-1;
	/**
	 * @return selectedIndex を戻します。
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex を クラス変数selectedIndexへ設定します。
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
    /**
     * 初期画面プルダウン設定処理
     *
     */
    public void setPullDownData() {
    	if(getSessionDto() != null) {
    		listTaishoYm = getSessionDto().getListTaishoYm();
    	}
    }
	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * クリア処理
	 *
	 */
	public void clear() {
		setMiseCd(null);
		setTaishoYm(null);
		setKaisuu(null);
	}
	/**
	 * @return tenantPayPdfRegistSesDto を戻します。
	 */
	public SessionDto getTenantPayPdfRegistSesDto() {
		return tenantPayPdfRegistSesDto;
	}
	/**
	 * @param tenantPayPdfRegistSesDto を クラス変数tenantPayPdfRegistSesDtoへ設定します。
	 */
	public void setTenantPayPdfRegistSesDto(SessionDto tenantPayPdfRegistSesDto) {
		this.tenantPayPdfRegistSesDto = tenantPayPdfRegistSesDto;
	}
	/**
	 * ログインユーザーID取得処理
	 * @return
	 */
	public String getUserId() {
		return getTenantPayPdfRegistSesDto().getBirdUserInfo().getUserID();
	}
	public SessionDto getSessionDto() {
		return tenantPayPdfRegistSesDto;
	}
	/**
	 * @return kaisuu を戻します。
	 */
	public String getKaisuu() {
		return kaisuu;
	}
	/**
	 * @param kaisuu を クラス変数kaisuuへ設定します。
	 */
	public void setKaisuu(String kaisuu) {
		this.kaisuu = kaisuu;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd を クラス変数miseCdへ設定します。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return taishoYm を戻します。
	 */
	public String getTaishoYm() {
		return taishoYm;
	}
	/**
	 * @param taishoYm を クラス変数taishoYmへ設定します。
	 */
	public void setTaishoYm(String taishoYm) {
		this.taishoYm = taishoYm;
	}
	/**
	 * @return listTaishoYm を戻します。
	 */
	public List getListTaishoYm() {
		return listTaishoYm;
	}
	/**
	 * @param listTaishoYm を クラス変数listTaishoYmへ設定します。
	 */
	public void setListTaishoYm(List listTaishoYm) {
		this.listTaishoYm = listTaishoYm;
	}
	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}
	/**
	 * @param viewId を クラス変数viewIdへ設定します。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	/**
	 * @return sesstionKey を戻します。
	 */
	public String getSesstionKey() {
		return sesstionKey;
	}
	/**
	 * @param sesstionKey を クラス変数sesstionKeyへ設定します。
	 */
	public void setSesstionKey(String sesstionKey) {
		this.sesstionKey = sesstionKey;
	}
	/**
	 * @return downloadIndex を戻します。
	 */
	public int getDownloadIndex() {
		return downloadIndex;
	}
	/**
	 * @param downloadIndex を クラス変数downloadIndexへ設定します。
	 */
	public void setDownloadIndex(int downloadIndex) {
		this.downloadIndex = downloadIndex;
	}
}
