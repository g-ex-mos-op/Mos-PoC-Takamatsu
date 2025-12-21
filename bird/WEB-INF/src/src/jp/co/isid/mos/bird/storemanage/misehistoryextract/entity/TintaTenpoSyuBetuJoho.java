/*
 * ì¬“ú: 2016/02/19
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.entity;

public class TintaTenpoSyuBetuJoho {
	private String miseLeaseShu; // ’À‘İ“X•Üí•Ê
	private String miseLeaseName; // ’À‘İ“X•Ü–¼Ì
	private int maxLeaseShuCount; // Å‘åí•ÊŒ”

	/**
	 * ’À‘İ“X•Üí•Ê‚ğæ“¾‚µ‚Ü‚·B
	 *
	 * @return ’À‘İ“X•Üí•Ê
	 */
	public String getMiseLeaseShu() {
		return convString(miseLeaseShu);
	}

	/**
	 * ’À‘İ“X•Üí•Ê‚ğİ’è‚µ‚Ü‚·B
	 *
	 * @param miseLeaseShu
	 *            ’À‘İ“X•Üí•Ê
	 */
	public void setMiseLeaseShu(String miseLeaseShu) {
		this.miseLeaseShu = miseLeaseShu;
	}

	/**
	 * ’À‘İ“X•Ü–¼Ì‚ğæ“¾‚µ‚Ü‚·B
	 *
	 * @return ’À‘İ“X•Ü–¼Ì
	 */
	public String getMseLeaseName() {
		return convString(miseLeaseName);
	}

	/**
	 * ’À‘İ“X•Ü–¼Ì‚ğİ’è‚µ‚Ü‚·B
	 *
	 * @param miseLeaseName
	 *            ’À‘İ“X•Ü–¼Ì
	 */
	public void setMiseLeaseName(String miseLeaseName) {
		this.miseLeaseName = miseLeaseName;
	}

	/**
	 * Å‘åí•ÊŒ”‚ğæ“¾‚µ‚Ü‚·B
	 *
	 * @return Å‘åí•ÊŒ”
	 */
	public int getMaxLeaseShuCount() {
		return maxLeaseShuCount;
	}

	/**
	 * Å‘åí•ÊŒ”‚ğİ’è‚µ‚Ü‚·B
	 *
	 * @param maxLeaseShuCount
	 *            Å‘åí•ÊŒ”
	 */
	public void setMaxLeaseShuCount(int maxLeaseShuCount) {
		this.maxLeaseShuCount = maxLeaseShuCount;
	}

	private String convString(String str) {
		String ret = str;
		if (str == null) {
			ret = "";
		}
		ret = ret.trim();
		return ret;
	}
}
