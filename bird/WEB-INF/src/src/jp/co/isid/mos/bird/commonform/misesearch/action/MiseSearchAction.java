/*
 * ì¬“ú: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.action;

/**
 * @author xyuchida
 *
 */
public interface MiseSearchAction {

	public String initialize();
	public String select();
	public String search();
    public String cancel();
	public String loadSibuList();
}
