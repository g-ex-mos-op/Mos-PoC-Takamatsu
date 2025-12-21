package jp.co.isid.mos.bird.bizreport.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 屋号別日報の共通Dto
 * 『屋号別日報支部』画面と『屋号別日報店舗』画面の屋号情報を保持し、
 *  互いの画面への遷移時にここで保持した内容を受け渡すためのクラス。
 *  
 *  複数ページに対応するため、各条件はMap形式で保持し、【WindowId】をキーとして保持する。
 * 
 * @author xwatanabe
 */
public class YagoRefCommonCondDto {
    
    /** 支部コード */
    private Map sibuCd = new HashMap();
    
    /** 屋号コード */
    private Map yagoCd = new HashMap();
    
    /** 屋号タイプ */
    private Map yagoType = new HashMap();
    
    /** 支部名 */
    private Map sibuName = new HashMap();
    
    /** 屋号名 */
    private Map yagoName = new HashMap();
    
    /** 処理区分 */
    private Map linkKbn = new HashMap();
    
	/*********************************************************************
	 * ウィンドウ管理
	 *********************************************************************/
	/**
	 * ウィインドウID
	 */
	private int windowId;

	/**
	 * MAXウィインドウID
	 */
	private int maxWindowId;
	    
	/**
	 * 別ウィンドウチェック状態
	 */
	private String newWinFlg;
	
	/**
	 * 別ウィンドウチェック状態を取得する<br>
	 * @return 別ウィンドウチェック状態
	 */
	public boolean isNewWindowFlg() {
		return "ON".equals(newWinFlg) ? true : false;
	}

	public String getNewWinFlg() {
		return newWinFlg;
	}
	public void setNewWinFlg(String newWinFlg) {
		this.newWinFlg = newWinFlg;
	}

	/**
	 * ウィインドウIDを取得する<br>
	 * @return ウィインドウID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * ウィインドウIDを設定する<br>
	 * @param windowId ウィインドウID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * MAXウィインドウIDを取得する<br>
	 * @return MAXウィインドウID
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * MAXウィインドウIDを設定する<br>
	 * @param maxWindowId MAXウィインドウID
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	/**
	 * ウィンドウIDを生成する
	 */ 
	public void updateWindowId() {
		this.setWindowId(createWindowId());
	}

	/**
	 * MAXウィンドウIDを生成する
	 */ 
	public int createWindowId() {
		int newWindowId = getMaxWindowId() + 1;
		setMaxWindowId(newWindowId);
		return newWindowId;
	}
	
	
	
	public String getLinkKbn() {
		return (String)linkKbn.get(new Integer(getWindowId()));
	}

	public void setLinkKbn(String linkKbn) {
		this.linkKbn.put(new Integer(getWindowId()), linkKbn);
	}

	public String getSibuCd() {
		return (String)sibuCd.get(new Integer(getWindowId()));
	}

	public void setSibuCd(String sibuCd) {
		this.sibuCd.put(new Integer(getWindowId()), sibuCd);
	}

	public String getSibuName() {
		return (String)sibuName.get(new Integer(getWindowId()));
	}

	public void setSibuName(String sibuName) {
		this.sibuName.put(new Integer(getWindowId()), sibuName);
	}

	public String getYagoCd() {
		return (String)yagoCd.get(new Integer(getWindowId()));
	}

	public void setYagoCd(String yagoCd) {
		this.yagoCd.put(new Integer(getWindowId()), yagoCd);
	}

	public String getYagoName() {
		return (String)yagoName.get(new Integer(getWindowId()));
	}

	public void setYagoName(String yagoName) {
		this.yagoName.put(new Integer(getWindowId()), yagoName);
	}

	public String getYagoType() {
		return (String)yagoType.get(new Integer(getWindowId()));
	}

	public void setYagoType(String yagoType) {
		this.yagoType.put(new Integer(getWindowId()), yagoType);
	}
    
}
