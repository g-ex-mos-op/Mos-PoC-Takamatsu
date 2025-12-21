/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.config.zenurikakuteiregist.util.ZenurikakuteiRegistUtil;


/**
 * 機能設定]【売上速報前月売上確定登録】
 * Request保持データDTO
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class RequestDto {
	/** セッションKey */
	private String sesstionKey;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private int windowId;
    
	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * ステータスプルダウンリスト取得処理
	 * @return
	 */
	public List getListStatus() {
		return ZenurikakuteiRegistUtil.creatListStatus();
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
}
