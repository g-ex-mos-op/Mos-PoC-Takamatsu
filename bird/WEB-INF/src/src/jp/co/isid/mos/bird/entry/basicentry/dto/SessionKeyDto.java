/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.basicentry.dto;

/**
 * session key 保持用Dto<br>
 * @author kusama
 */
public class SessionKeyDto {
    
    /* セッションKey */
    private String sessionKey;

	/**
	 * セッションKeyの設定
	 * @return sessionKey を戻します。
	 */
	public String getSessionKey() {
		return sessionKey;
	}
	/**
	 * セッションKeyの設定
	 * @param sessionKey sessionKey を設定。
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
