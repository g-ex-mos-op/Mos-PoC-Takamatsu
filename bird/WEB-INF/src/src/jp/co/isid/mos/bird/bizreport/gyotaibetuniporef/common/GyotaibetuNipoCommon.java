package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common;

import jp.co.isid.mos.bird.common.util.CommonUtil;


/**
 * 屋号別売上日報 共通クラス
 * 
 */
public class GyotaibetuNipoCommon {

    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }
    
    /**
     * 渡された文字列がNullの場合、空白("”)に変換する
     * 渡された文字列がNull以外場合、Trim()する
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
    	if(str == null ||  str.trim().length() == 0){
    		return GyotaibetuNipoConstants.EMPTY;
    	}else{
    		return str.trim();
    	}
    }
    /**
     * [0]:屋号区分 [1]:屋号タイプ [2]:エリア区分 [3]:屋号名称
     * @param strlinkParams
     * @return
     */
    public static String[] getLinkParams(String strlinkParams) {
		String[] linkParams = new String[]{"","","",""};
		if(!CommonUtil.isNull(strlinkParams)) {
	    	//[0]:屋号区分 [1]:屋号タイプ [2]:エリア区分 [3]:屋号名称
			String[] params = strlinkParams.split(",");
			for(int p=0; p<linkParams.length; p++) {
				if(p>=params.length || params[p]==null) {
					continue;
				}
				linkParams[p] =params[p]; 
			}
		}
		return linkParams;
    }

}