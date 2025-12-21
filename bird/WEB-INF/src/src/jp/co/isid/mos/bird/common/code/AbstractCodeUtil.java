package jp.co.isid.mos.bird.common.code;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;
import jp.co.isid.mos.bird.common.entity.UILists;

/**
 * 対象期間コード定数クラス
 * 
 * @author xkinu
 */
public abstract class AbstractCodeUtil extends CodeUtil {
    /**
     * プルダウンリストを取得する 
     * @param 	codeTable	コードリスト
	 * @return	List		プルダウンリスト
     */
    public static List getPullDownList(String[][] codeTable) {
    	return CodeUtil.getPullDownList(codeTable);
    }
	/**
	 * プルダウンリストを取得する 
	 * 
	 * @param codeTable
	 * @return List		プルダウンリスト
	 */
    public static List getUIListPullDownList(String[][] codeTable) {
		List pullDownList = new ArrayList();
    	if(codeTable != null) {
			for(int i = 0; i < codeTable.length; i++) {
				UILists item = new UILists();
				item.setKey(codeTable[i][0]);
				item.setKeyName(codeTable[i][1]);
				pullDownList.add(item);
			}
    	}
		return pullDownList;
	}
	/**
	 * プルダウンリストを取得する
	 * 渡されたコード分のリストを作成する
	 * @param 	codeTable	コードリスト
	 * @param 	arrCode		コード配列
	 * @return	List		ﾌﾟﾙﾀﾞｳﾝﾘｽﾄ(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
	 */
    public static List getUIListPullDownList(String[][] codeTable, String[] arrCode) {
		List pullDownList = new ArrayList();

		if(arrCode == null) {
			return pullDownList;
		}

		for(int i= 0; i < arrCode.length; i++) {
			String code = arrCode[i];

			for(int j = 0; j < codeTable.length; j++) {
				if (codeTable[j][0].equals(code)) {
					UILists item = new UILists();
					item.setKey(codeTable[j][0]);
					item.setKeyName(codeTable[j][1]);
					pullDownList.add(item);
					break;
				}
			}
		}

		return pullDownList;
	}
	/**
	 * 対象コードの[UILists]の取得処理
	 * 
	 * @param code 対象コード
	 * @param listUILists List[[UILists]]
	 * @return UILists
	 */
    public static UILists getUILists(final String code, final List listUILists) {
    	if(code != null) {
			for(int i = 0; i < listUILists.size(); i++) {
				Object obj = listUILists.get(i);
				if (obj instanceof UILists) {
					UILists uiLists = (UILists) obj;
					if(uiLists.getKey().equals(code)) {
						return uiLists;
					}
					continue;
				}
				else {
					break;
				}
			}
    	}
		return null;
	}
}
