package jp.co.isid.mos.bird.analysis.jikanbeturef.entity;

import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;


/**
 * —j“ú•Ê—pAbstractƒGƒ“ƒeƒBƒeƒBƒNƒ‰ƒX
 * 
 * ì¬“ú:2008/09/25
 * @author xkinu
 *
 */
public abstract class UAWeek extends UIAnalysis {
    
    public static final String threeKbn_COLUMN = "THREE_KBN";
    
    public static final String threeName_COLUMN = "THREE_NAME";
    
    public static final String sevenKbn_COLUMN = "SEVEN_KBN";
    
    public static final String sevenName_COLUMN = "SEVEN_NAME";
    
    /**
     * O—j“ú‹æ•ª
     */
    private String threeKbn;
    
    /**
     * O—j“ú–¼Ì
     */
    private String threeName;
    
    /**
     * µ—j“ú‹æ•ª
     */
    private String sevenKbn;
    
    /**
     * µ—j“ú–¼Ì
     */
    private String sevenName;
	/**
	 * —j“ú–¼Ì
	 */
	private String weekName = "";
     
    /**
     * O—j“ú‹æ•ª‚ğæ“¾‚µ‚Ü‚·B
     * @return O—j“ú‹æ•ª
     */
    public String getThreeKbn() {
        return threeKbn;
    }
    /**
     * O—j“ú‹æ•ª‚ğİ’è‚µ‚Ü‚·B
     * @param threeKbn O—j“ú‹æ•ª
     */
    public void setThreeKbn(String threeKbn) {
        this.threeKbn = threeKbn;
    }
    
    /**
     * O—j“ú–¼Ì‚ğæ“¾‚µ‚Ü‚·B
     * @return O—j“ú–¼Ì
     */
    public String getThreeName() {
        return threeName;
    }
    /**
     * O—j“ú–¼Ì‚ğİ’è‚µ‚Ü‚·B
     * @param threeName O—j“ú–¼Ì
     */
    public void setThreeName(String threeName) {
        this.threeName = threeName;
    }
    
    /**
     * µ—j“ú‹æ•ª‚ğæ“¾‚µ‚Ü‚·B
     * @return µ—j“ú‹æ•ª
     */
    public String getSevenKbn() {
        return sevenKbn;
    }
    /**
     * µ—j“ú‹æ•ª‚ğİ’è‚µ‚Ü‚·B
     * @param sevenKbn µ—j“ú‹æ•ª
     */
    public void setSevenKbn(String sevenKbn) {
        this.sevenKbn = sevenKbn;
    }
    
    /**
     * µ—j“ú–¼Ì‚ğæ“¾‚µ‚Ü‚·B
     * @return µ—j“ú–¼Ì
     */
    public String getSevenName() {
        return sevenName;
    }
    /**
     * µ—j“ú–¼Ì‚ğİ’è‚µ‚Ü‚·B
     * @param sevenName µ—j“ú–¼Ì
     */
    public void setSevenName(String sevenName) {
        this.sevenName = sevenName;
    }
	/**
	 * @return weekName ‚ğ–ß‚µ‚Ü‚·B
	 */
	public String getWeekName() {
		return weekName;
	}
	/**
	 * @param weekName ‚ğ ƒNƒ‰ƒX•Ï”weekName‚Öİ’è‚µ‚Ü‚·B
	 */
	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
    
}
