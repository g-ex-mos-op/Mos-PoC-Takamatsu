/**
 * 
 */
package jp.co.isid.mos.bird.framework.dto;

/**
 * 画面排他制御DTO
 * 
 * @author xyuchida
 */
public interface ExclusiveWindowDto {

    /**
     * @return lastSessionKey を戻します。
     */
    public String getLastSessionKey();

    /**
     * @param lastSessionKey 設定する lastSessionKey。
     */
    public void setLastSessionKey(String lastSessionKey);

    /**
     * @return sessionKey を戻します。
     */
    public String getSessionKey();

    /**
     * @param sessionKey 設定する sessionKey。
     */
    public void setSessionKey(String sessionKey);
}
