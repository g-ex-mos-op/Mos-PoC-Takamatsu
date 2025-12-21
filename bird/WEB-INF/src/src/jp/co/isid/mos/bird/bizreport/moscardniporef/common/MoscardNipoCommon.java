package jp.co.isid.mos.bird.bizreport.moscardniporef.common;


/**
 * 営業日報 共通クラス
 * 
 * @author xyamauchi
 */
public class MoscardNipoCommon {

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
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
        return (str == null || str.trim().length() == 0) ?
                MoscardNipoConstants.EMPTY : str.trim();
    }

    /**
     * 合計区分を取得する
     * @param trClass 行CSSクラス名
     * @return String 合計区分
     */
    public static String getSumKbn(String trClass) {
        
        if (trClass == null || MoscardNipoConstants.NO_CLASS.equals(trClass)) {
			return MoscardNipoConstants.LINK_SIBU;
	    } else if (MoscardNipoConstants.TR_AREA_SUM.equals(trClass)) {
	    	return MoscardNipoConstants.LINK_SLAREA;
	    } else if (MoscardNipoConstants.TR_JIGYOU_SUM.equals(trClass)) {
	    	return MoscardNipoConstants.LINK_JIGYOU;
	    } else if (MoscardNipoConstants.TR_HONBU_SUM.equals(trClass)) {
	    	return MoscardNipoConstants.LINK_HONBU;	    
        } else if (MoscardNipoConstants.TR_GYOTAI_SUM.equals(trClass)) {
            return MoscardNipoConstants.LINK_GYOTAI;
        }  else if (MoscardNipoConstants.TR_TOTAL_SUM.equals(trClass)) {
	    	return MoscardNipoConstants.LINK_ALL;
	    }
		return MoscardNipoConstants.EMPTY;
    }
}