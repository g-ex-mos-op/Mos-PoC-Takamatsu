/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;

/**
 * 株式報酬制度データDto
 * @author yushuncheng
 *
 */
public class SeidoDataDto {

	/**
	 * 株式報酬制度リスト
	 */
	private List<UISeidoMst> seidoList;

	/************
     * 条件画面 *
     ************/
    /* 検索条件 */
    /**
     * 対象
     */
    private int condTarget;

	/**
	 * 株式報酬制度リストを取得します。
	 * @return uISeidoMst 株式報酬制度リスト
	 */
	public List<UISeidoMst> getSeidoList() {
		return seidoList;
	}

	/**
	 * 株式報酬制度リストを設定します。
	 * @param uISeidoMst 株式報酬制度リスト
	 */
	public void setSeidoList(List<UISeidoMst> seidoList) {
		this.seidoList = seidoList;
	}
    public boolean isEmptyListTarget() {
        return getSeidoList() == null || getSeidoList().isEmpty();
    }

	public int getCondTarget() {
        return condTarget;
    }
    public void setCondTarget(int condTarget) {
        this.condTarget = condTarget;
    }

}
