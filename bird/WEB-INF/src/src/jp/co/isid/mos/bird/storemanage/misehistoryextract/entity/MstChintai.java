/*
 * ì¬“ú: 2016/02/19
 */
package jp.co.isid.mos.bird.storemanage.misehistoryextract.entity;

public class MstChintai {
	private String miseMDate; // “X‚l—LŒø“ú
	private String miseLeaseShu; // ’À‘İ“X•Üí•Ê
	private String miseLeaseStart; // ’À‘İ“X•ÜŠJn“ú
	private String miseLeaseEnd; // ’À‘İ“X•ÜI—¹“ú

	/**
	 * “X‚l—LŒø“ú‚ğæ“¾‚µ‚Ü‚·B
	 *
	 * @return “X‚l—LŒø“ú
	 */
	public String getMiseMDate() {
		return convString(miseMDate);
	}

	/**
	 * “X‚l—LŒø“ú‚ğİ’è‚µ‚Ü‚·B
	 *
	 * @param miseMDate
	 *            “X‚l—LŒø“ú
	 */
	public void setMiseMDate(String miseMDate) {
		this.miseMDate = miseMDate;
	}

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
	 * ’À‘İ“X•ÜŠJn“ú‚ğæ“¾‚µ‚Ü‚·B
	 *
	 * @return ’À‘İ“X•ÜŠJn“ú
	 */
	public String getMiseLeaseStart() {
		return convString(miseLeaseStart);
	}

	/**
	 * ’À‘İ“X•ÜŠJn“ú‚ğİ’è‚µ‚Ü‚·B
	 *
	 * @param miseLeaseStart
	 *            ’À‘İ“X•ÜŠJn“ú
	 */
	public void setMiseLeaseStart(String miseLeaseStart) {
		this.miseLeaseStart = miseLeaseStart;
	}

	/**
	 * ’À‘İ“X•ÜI—¹“ú‚ğæ“¾‚µ‚Ü‚·B
	 *
	 * @return ’À‘İ“X•ÜI—¹“ú
	 */
	public String getMiseLeaseEnd() {
		return convString(miseLeaseEnd);
	}

	/**
	 * ’À‘İ“X•ÜI—¹“ú‚ğİ’è‚µ‚Ü‚·B
	 *
	 * @param miseLeaseEnd
	 *            ’À‘İ“X•ÜI—¹“ú
	 */
	public void setMiseLeaseEnd(String miseLeaseEnd) {
		this.miseLeaseEnd = miseLeaseEnd;
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
