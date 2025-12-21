/*
 * 作成日: 2008/11/14
 */
package jp.co.isid.mos.bird.common.entity;

/**
 * パスワード管理ポリシー 定義保持用Entity
 * @author xnkusama
 */
public class CtlPasswordPolicy {
    //ポリシータイプ
    private int pswdProlicyType;
    //最高文字数
    private int maxCharCnt = 0;
    //最低文字数
    private int minCharCnt;
    //使用可能文字
    private long usableCharCodeFrom;
    private long usableCharCodeTo;
    //最低使用文字数
    private int minUseCharCnt = 0;
    //最低使用文字種数
    private int minUseCharTypeCnt = 0;
    //連続同一文字許可数
    private int continuousCharCnt = 0;
    //同一パスワード禁止世代数
    private int denyGenerationCnt = 0;
    //前回からの最低変更文字数
    private int mustChangeCharCnt = 0;
    //パスワード有効期間
    private int pswdAvailableTerm = 0;
    //有効期間後の動作
    
    //警告期間
    private int warningTerm = 0;
    //変更禁止期間
    private int denyChangeTerm = 0;
    //ロックまでの連続失敗回数
    private int failureToLockCnt = 0;
    
    public int getContinuousCharCnt() {
        return continuousCharCnt;
    }
    public void setContinuousCharCnt(int continuousCharCnt) {
        this.continuousCharCnt = continuousCharCnt;
    }
    public int getDenyChangeTerm() {
        return denyChangeTerm;
    }
    public void setDenyChangeTerm(int decyChangeTerm) {
        this.denyChangeTerm = decyChangeTerm;
    }
    public int getDenyGenerationCnt() {
        return denyGenerationCnt;
    }
    public void setDenyGenerationCnt(int denyGenerationCnt) {
        this.denyGenerationCnt = denyGenerationCnt;
    }
    public int getFailureToLockCnt() {
        return failureToLockCnt;
    }
    public void setFailureToLockCnt(int failureToLockCnt) {
        this.failureToLockCnt = failureToLockCnt;
    }
    public int getMinCharCnt() {
        return minCharCnt;
    }
    public void setMinCharCnt(int minCharCnt) {
        this.minCharCnt = minCharCnt;
    }
    public int getMinUseCharCnt() {
        return minUseCharCnt;
    }
    public void setMinUseCharCnt(int minUseCharCnt) {
        this.minUseCharCnt = minUseCharCnt;
    }
    public int getMinUseCharTypeCnt() {
        return minUseCharTypeCnt;
    }
    public void setMinUseCharTypeCnt(int minUseCharTypeCnt) {
        this.minUseCharTypeCnt = minUseCharTypeCnt;
    }
    public int getMustChangeCharCnt() {
        return mustChangeCharCnt;
    }
    public void setMustChangeCharCnt(int mustChangeCharCnt) {
        this.mustChangeCharCnt = mustChangeCharCnt;
    }
    public int getPswdAvailableTerm() {
        return pswdAvailableTerm;
    }
    public void setPswdAvailableTerm(int pswdAvailableTerm) {
        this.pswdAvailableTerm = pswdAvailableTerm;
    }
    public int getPswdProlicyType() {
        return pswdProlicyType;
    }
    public void setPswdProlicyType(int pswdProlicyType) {
        this.pswdProlicyType = pswdProlicyType;
    }
    public long getUsableCharCodeFrom() {
        return usableCharCodeFrom;
    }
    public void setUsableCharCodeFrom(long usableCharCodeFrom) {
        this.usableCharCodeFrom = usableCharCodeFrom;
    }
    public long getUsableCharCodeTo() {
        return usableCharCodeTo;
    }
    public void setUsableCharCodeTo(long usableCharCodeTo) {
        this.usableCharCodeTo = usableCharCodeTo;
    }
    public int getWarningTerm() {
        return warningTerm;
    }
    public void setWarningTerm(int warningTerm) {
        this.warningTerm = warningTerm;
    }
	/**
	 * @return maxCharCnt を戻します。
	 */
	public int getMaxCharCnt() {
		return maxCharCnt;
	}
	/**
	 * @param maxCharCnt を クラス変数maxCharCntへ設定します。
	 */
	public void setMaxCharCnt(int maxCharCnt) {
		this.maxCharCnt = maxCharCnt;
	}
    
}