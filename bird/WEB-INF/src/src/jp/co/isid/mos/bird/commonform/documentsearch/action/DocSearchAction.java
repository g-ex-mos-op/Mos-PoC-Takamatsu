/*
 * ì¬“ú: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.documentsearch.action;

/**
 * @author m.onodera
 *
 */
public interface DocSearchAction {

	public String initialize();
	public String search();
	public String select();
    public String cancel();
	public String changePage();
}
