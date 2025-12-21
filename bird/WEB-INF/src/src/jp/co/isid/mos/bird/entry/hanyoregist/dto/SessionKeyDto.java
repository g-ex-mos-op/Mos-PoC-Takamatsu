/*
 * 作成日: 2006/5/1
 */
package jp.co.isid.mos.bird.entry.hanyoregist.dto;

/**
 * session key 保持用Dto<br>
 * @author itamoto
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
