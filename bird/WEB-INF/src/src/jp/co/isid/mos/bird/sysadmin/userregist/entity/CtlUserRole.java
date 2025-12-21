package jp.co.isid.mos.bird.sysadmin.userregist.entity;

import java.sql.Timestamp;

/**
 * ロール情報Entity
 * @author xylee
 */
public class CtlUserRole {
    
	    public static final String TABLE = "BR04USRL";
	    
	    public static final String userId_COLUMN = "USER_ID";
	   
	    public static final String roleCd_COLUMN = "ROLE_CD";
	    
	    public static final String batFlg_COLUMN = "BAT_FLG";
	    
	    public static final String firstUser_COLUMN = "FIRST_USER";
	    
	    public static final String firstPgm_COLUMN = "FIRST_PGM";
	    
	    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
	    
	    public static final String lastUser_COLUMN = "LAST_USER";
	    
	    public static final String lastPgm_COLUMN = "LAST_PGM";
	    
	    public static final String lastTmsp_COLUMN = "LAST_TMSP";
	    
	    /**
	     * ユーザID
	     */
	    private String userId;
	    
	    /**
	     * ロールコード
	     */
	    private String roleCd;
	    
	    /**
	     * バットフラグ
	     */
	    private String batFlg;
	    
	    /**
	     * 登録ユーザー
	     */
	    private String firstUser;
	    
	    /**
	     * 登録プログラム
	     */
	    private String firstPgm;
	    
	    /**
	     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
	     */
	    private Timestamp firstTmsp;
	    
	    /**
	     * 修正ユーザー
	     */
	    private String lastUser;
	    
	    /**
	     * 修正プログラム
	     */
	    private String lastPgm;
	    
	    /**
	     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
	     */
	    private Timestamp lastTmsp;
	    
	    /**
		 * @return userId を戻します。
		 */
		public String getUserId() {
			return userId;
		}
		/**
		 * @param userId userId を設定。
		 */
		public void setUserId(String userId) {
			this.userId = userId;
		}
		/**
		 * @return batFlg を戻します。
		 */
		public String getBatFlg() {
			return batFlg;
		}
		/**
		 * @param batFlag batFlag を設定。
		 */
		public void setBatFlg(String batFlg) {
			this.batFlg = batFlg;
		}
		/**
		 * @return roleCd を戻します。
		 */
		public String getRoleCd() {
			return roleCd;
		}
		/**
		 * @param roleCd roleCd を設定。
		 */
		public void setRoleCd(String roleCd) {
			this.roleCd = roleCd;
		}
	    /**
	     * 登録ユーザーを取得します。
	     * @return 登録ユーザー
	     */
	    public String getFirstUser() {
	        return firstUser;
	    }
	    /**
	     * 登録ユーザーを設定します。
	     * @param firstUser 登録ユーザー
	     */
	    public void setFirstUser(String firstUser) {
	        this.firstUser = firstUser;
	    }
	    
	    /**
	     * 登録プログラムを取得します。
	     * @return 登録プログラム
	     */
	    public String getFirstPgm() {
	        return firstPgm;
	    }
	    /**
	     * 登録プログラムを設定します。
	     * @param firstPgm 登録プログラム
	     */
	    public void setFirstPgm(String firstPgm) {
	        this.firstPgm = firstPgm;
	    }
	    
	    /**
	     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
	     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
	     */
	    public Timestamp getFirstTmsp() {
	        return firstTmsp;
	    }
	    /**
	     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
	     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
	     */
	    public void setFirstTmsp(Timestamp firstTmsp) {
	        this.firstTmsp = firstTmsp;
	    }
	    
	    /**
	     * 修正ユーザーを取得します。
	     * @return 修正ユーザー
	     */
	    public String getLastUser() {
	        return lastUser;
	    }
	    /**
	     * 修正ユーザーを設定します。
	     * @param lastUser 修正ユーザー
	     */
	    public void setLastUser(String lastUser) {
	        this.lastUser = lastUser;
	    }
	    
	    /**
	     * 修正プログラムを取得します。
	     * @return 修正プログラム
	     */
	    public String getLastPgm() {
	        return lastPgm;
	    }
	    /**
	     * 修正プログラムを設定します。
	     * @param lastPgm 修正プログラム
	     */
	    public void setLastPgm(String lastPgm) {
	        this.lastPgm = lastPgm;
	    }
	    
	    /**
	     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
	     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
	     */
	    public Timestamp getLastTmsp() {
	        return lastTmsp;
	    }
	    /**
	     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
	     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
	     */
	    public void setLastTmsp(Timestamp lastTmsp) {
	        this.lastTmsp = lastTmsp;
	    }
	    
}
