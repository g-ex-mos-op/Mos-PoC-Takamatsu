package jp.co.isid.mos.bird.bizreport.takuhainiporef.common;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * 宅配売上日報 共通クラス
 * 
 * @author xjung
 */
public class TakuhaiNipoCommon {

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
        	TakuhaiNipoConstants.EMPTY : str.trim();
    }

    /**
     * 渡されたBigDecimalがNullの場合、"0"に変換する
     * @param num BigDecimal
     * @return パラメータがNULLの場合BigDecimal("0")
     */
    public static BigDecimal setBicEmpty(BigDecimal num) {
        return num == null ? new BigDecimal(TakuhaiNipoConstants.ZERO) : num;
    }

    /**
     * リンク区分を取得する
     * @param trClass 行CSSクラス名
     * @return String リンク区分
     */
    public static String getLinkKbnCd(String trClass, String shukeiKbn) {
        
        /* 2008/12/09追加 xayumi SV対応
         * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
            return TaishoJoken.CODE_SV;
            
        } else if (trClass == null || NipoRefConstants.CSS_TR_CLASS.equals(trClass)) {
			return TaishoJoken.CODE_SIBU;
	    } else if (NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(trClass)) {
	    	return TaishoJoken.CODE_SLAREA;
	    } else if (NipoRefConstants.CSS_TR_CLASS_JIGYOU.equals(trClass)) {
	    	return TaishoJoken.CODE_JIGYOU;
	    } else if (NipoRefConstants.CSS_TR_CLASS_HONBU.equals(trClass)) {
	    	return TaishoJoken.CODE_HONBU;
	    } else if (NipoRefConstants.CSS_TR_CLASS_TOTAL.equals(trClass)) {
	    	return TaishoJoken.CODE_ALL;
	    }
		return TakuhaiNipoConstants.EMPTY;
    }

    /**
     * 合計行支部名称を取得する
     * @param str 合計行名称
     * @return String 合計行名称 + "計" 
     */
    public static String concatSumTitle(String str) {
		StringBuffer sb = new StringBuffer();
		// 合計行名称
		sb.append(setEmpty(str));
		//「計」
		sb.append((TakuhaiNipoConstants.STR_SUM));
		return sb.toString();
    }


    /**
     * TdCssクラスを取得する
     * @param num       比(前年比又は構成比)
     * @param rClass    行Cssクラス
     * @return String   TdCssクラス
     */
    public static String getHiTdCssClassName(BigDecimal num, String rClass) {
        if (num.compareTo(new BigDecimal(100)) < 0) {
            return NipoRefConstants.CSS_TR_CLASS.equals(rClass)
                ? TakuhaiNipoConstants.TD_NUM_RED_GREEN : TakuhaiNipoConstants.TD_NUM_RED; 
        }

        return NipoRefConstants.CSS_TR_CLASS.equals(rClass)
            ? TakuhaiNipoConstants.TD_NUM_GREEN : TakuhaiNipoConstants.TD_NUM;   
        
    }
    /**
     * リクエストタブが詳細タブか否か判断処理
     * @return
     */
    public static boolean isDispTypeDetail(String subTabKbn) {
    	return (BizReportConstants.SUB_TAG_2.equals(subTabKbn) || BizReportConstants.SUB_TAG_3.equals(subTabKbn));
    }
    /**
     * [0]:宅配区分 [1]:宅配区分名称 [2]宅配区分タイプ(有無orタイプ)
     * @param strlinkParams
     * @return
     */
    public static String[] getLinkParams(String strlinkParams) {
		String[] linkParams = new String[]{"","", ""};
		if(!CommonUtil.isNull(strlinkParams)) {
	    	//[0]:宅配区分 [1]:宅配区分名称 [2]宅配区分タイプ(有無orタイプ)
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