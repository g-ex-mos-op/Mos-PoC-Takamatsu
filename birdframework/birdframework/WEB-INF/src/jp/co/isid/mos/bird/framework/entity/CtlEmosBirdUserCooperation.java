package jp.co.isid.mos.bird.framework.entity;

import java.io.Serializable;


public class CtlEmosBirdUserCooperation implements Serializable {
    
    public static final long serialVersionUID = 1l;
    
    public static final String TABLE = "BR08UCIF";
    
    public static final String emosUserId_COLUMN = "E_USER_ID";
    
    public static final String birdUserId_COLUMN = "USER_ID";
    
    
    private String emosUserId;
    private String birdUserId;
    
    /**
     * e-mosslesユーザーＩＤを取得します。
     * @return ユーザーＩＤ
     */
    public String getEmosUserId() {
        return emosUserId;
    }
    /**
     * e-mosslesユーザーＩＤを設定します。
     * @param userId ユーザーＩＤ
     */
    public void setEmosUserId(String userId) {
        this.emosUserId = userId;
    }
    
    /**
     * BIRDユーザーＩＤ
     * @return ユーザーＩＤ
     */
    public String getBirdUserId() {
        return birdUserId;
    }
    /**
     * BIRDユーザーＩＤを設定します。
     * @param String ユーザーＩＤ
     */
    public void setBirdUserId(String userId) {
        this.birdUserId = userId;
    }
    
}
