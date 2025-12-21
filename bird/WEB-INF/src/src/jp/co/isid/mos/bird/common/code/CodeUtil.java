package jp.co.isid.mos.bird.common.code;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * コードの共通クラス
 * 
 * @author xjung
 */
public class CodeUtil {

	/**
	 * コードリストよりコードの名称を取得する
	 * @param code			コード
	 * @param codeTable	コードリスト
	 * @return String      コード名称
	 */
	protected static String getName(String code, String[][] codeTable) {
		for(int i = 0; i < codeTable.length; i++) {
			if(codeTable[i][0].equals(code)) {
				return codeTable[i][1];
			}			
		}		
		return  "";
	}
	
	/**
	 * プルダウンリストを取得する 
	 * @param 	codeTable	コードリスト
	 * @return	List		プルダウンリスト
	 */
	protected static List getPullDownList(String[][] codeTable) {
		List pullDownList = new ArrayList();
		for(int i = 0; i < codeTable.length; i++) {
			SelectItem item = new SelectItem(codeTable[i][0], codeTable[i][1]);
			pullDownList.add(item);
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
	protected static List getPullDownList(String[][] codeTable, String[] arrCode) {
		List pullDownList = new ArrayList();

		if(arrCode == null) {
			return pullDownList;
		}

		for(int i= 0; i < arrCode.length; i++) {
			String code = arrCode[i];

			for(int j = 0; j < codeTable.length; j++) {
				if (codeTable[j][0].equals(code)) {
					SelectItem item = new SelectItem(codeTable[j][0], codeTable[j][1]);
					pullDownList.add(item);
				}
			}
		}

		return pullDownList;
	}
}