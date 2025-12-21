package jp.co.isid.mos.bird.framework.logic;

/**
 * 遷移先ViewIDを取得ロジックインターフェース
 * @author xsong
 *
 */
public interface SelectViewIdLogic {

	/** 
	 * 遷移先ViewIDを取得する。
	 * @param id 画面ID
	 * @return DBから取得できた場合：DBの画面ID、取得できなかった場合：BPO001V01
	 */
	public String getSelectId(String id);
}
