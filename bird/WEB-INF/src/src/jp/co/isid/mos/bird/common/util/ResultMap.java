/**
 * 
 */
package jp.co.isid.mos.bird.common.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 作成日:2013/02/04
 * @author xkinu
 *
 */
public class ResultMap extends LinkedHashMap {
	private static final int MAX_ENTRIES = 5;

	/* (非 Javadoc)
	 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
	 */
	protected boolean removeEldestEntry(Entry eldest) {
		// TODO 自動生成されたメソッド・スタブ
		return size() > MAX_ENTRIES;
	}
}
