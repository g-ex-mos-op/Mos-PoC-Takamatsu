package jp.co.isid.mos.bird.bizreport.urimaintenance.util;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;



/**
 * 売上修正情報チェックロジック
 * @author Aspac
 *
 */
public class CheckInputData{
    /**
     * 売上在高のチェックを行う
     * @return
     */
    public static void execute(List listUri, int index, UriMainteHeader header) {
        
        //売上金タブチェック
        if(index==UriMaintenanceConstants.TAB_INDEX_URIAGEKIN) {
            checkUriagekin(listUri, header);
        }
        
        //前受売掛タブチェック
        else if(index==UriMaintenanceConstants.TAB_INDEX_URIKAKEKIN) {
            checkUrikakekin(listUri, header);
        }
        
        //販売タブチェック
        else if(index==UriMaintenanceConstants.TAB_INDEX_HANBAI) {
            checkHanbai(listUri, header);
        }
        
        //値引きタブチェック
        else if(index==UriMaintenanceConstants.TAB_INDEX_NEBIKI) {
            checkNebiki(listUri, header);
        }
        
        //計算結果チェック
        else if(index==UriMaintenanceConstants.TAB_INDEX_CHK_CALCULATE) {
            checkCalculate(listUri, header);
        }
        
    }
    
    
    
    
    
    /**
     * 売上金タブチェック処理
     * @param listUri
     */
    private static void checkUriagekin(List listUri, UriMainteHeader header) {
        
        int index = 0;
        for (int i=0; i<listUri.size()-1; i++) {
            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(i);
            
            checkRequireAndPositiveNumeric(entity.getU01Uriage(), 7, header.getUriage(), "u01Uriage", index);               //売上高
            checkRequireAndPositiveNumeric(entity.getU06Tax(), 7, header.getTax(), "u06Tax", index);                        //消費税
            checkRequireAndPositiveNumeric(entity.getU68AridakaJitu(), 7, header.getAridakaJitu(), "u68AridakaJitu", index); //実現金在高
            checkRequireAndPositiveNumeric(entity.getU74Kyakusu(), 4, header.getKyakusu(), "u74Kyakusu", index);            //客数
            checkRequireAndPositiveNumeric(entity.getU71CanKen(), 4, header.getCanKen(), "u71CanKen", index);               //取消件数
            checkRequireAndPositiveNumeric(entity.getU72CanKin(), 7, header.getCanKin(), "u72CanKin", index);               //取消金額
            
            index++;
        }
        
    }
    
    /**
     * 前受売掛タブチェック処理
     * @param listUri
     */
    private static void checkUrikakekin(List listUri, UriMainteHeader header) {
        
        int index = 0;
        
        for (int i=0; i<listUri.size()-1; i++) {
            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(i);
                   
            checkRequireAndPositiveNumeric(entity.getU15KaikeiKen2(), 4, header.getKkbnName2()+UriMaintenanceConstants.LIST_KENSU_CLM, "u15KaikeiKen2", index);      //会計区分2件数
            checkRequireAndPositiveNumeric(entity.getU16KaikeiKin2(), 7, header.getKkbnName2()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u16KaikeiKin2", index);   //会計区分2金額
            checkRequireAndPositiveNumeric(entity.getU17KaikeiKen3(), 4, header.getKkbnName3()+UriMaintenanceConstants.LIST_KENSU_CLM, "u17KaikeiKen3", index);      //会計区分3件数
            checkRequireAndPositiveNumeric(entity.getU18KaikeiKin3(), 7, header.getKkbnName3()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u18KaikeiKin3", index);   //会計区分3金額
            checkRequireAndPositiveNumeric(entity.getU19KaikeiKen4(), 4, header.getKkbnName4()+UriMaintenanceConstants.LIST_KENSU_CLM, "u19KaikeiKen4", index);      //会計区分4件数
            checkRequireAndPositiveNumeric(entity.getU20KaikeiKin4(), 7, header.getKkbnName4()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u20KaikeiKin4", index);   //会計区分4金額
            checkRequireAndPositiveNumeric(entity.getU21KaikeiKen5(), 4, header.getKkbnName5()+UriMaintenanceConstants.LIST_KENSU_CLM, "u21KaikeiKen5", index);      //会計区分5件数
            checkRequireAndPositiveNumeric(entity.getU22KaikeiKin5(), 7, header.getKkbnName5()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u22KaikeiKin5", index);   //会計区分5金額
            checkRequireAndPositiveNumeric(entity.getU23KaikeiKen6(), 4, header.getKkbnName6()+UriMaintenanceConstants.LIST_KENSU_CLM, "u23KaikeiKen6", index);      //会計区分6件数
            checkRequireAndPositiveNumeric(entity.getU24KaikeiKin6(), 7, header.getKkbnName6()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u24KaikeiKin6", index);   //会計区分6金額
            
            checkRequireAndPositiveNumeric(entity.getU25KaikeiKen7(), 4, header.getKkbnName7()+UriMaintenanceConstants.LIST_KENSU_CLM, "u25KaikeiKen7", index);      //会計区分7件数
            checkRequireAndPositiveNumeric(entity.getU26KaikeiKin7(), 7, header.getKkbnName7()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u26KaikeiKin7", index);   //会計区分7金額
            checkRequireAndPositiveNumeric(entity.getU27KaikeiKen8(), 4, header.getKkbnName8()+UriMaintenanceConstants.LIST_KENSU_CLM, "u27KaikeiKen8", index);      //会計区分8件数
            checkRequireAndPositiveNumeric(entity.getU28KaikeiKin8(), 7, header.getKkbnName8()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u28KaikeiKin8", index);   //会計区分8金額
            checkRequireAndPositiveNumeric(entity.getU29KaikeiKen9(), 4, header.getKkbnName9()+UriMaintenanceConstants.LIST_KENSU_CLM, "u29KaikeiKen9", index);      //会計区分9件数
            checkRequireAndPositiveNumeric(entity.getU30KaikeiKin9(), 7, header.getKkbnName9()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u30KaikeiKin9", index);   //会計区分9金額
            checkRequireAndPositiveNumeric(entity.getU31KaikeiKen10(), 4, header.getKkbnName10()+UriMaintenanceConstants.LIST_KENSU_CLM, "u31KaikeiKen10", index);   //会計区分10件数
            checkRequireAndPositiveNumeric(entity.getU32KaikeiKin10(), 7, header.getKkbnName10()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u32KaikeiKin10", index);//会計区分10金額
            checkRequireAndPositiveNumeric(entity.getU33KaikeiKen11(), 4, header.getKkbnName11()+UriMaintenanceConstants.LIST_KENSU_CLM, "u33KaikeiKen11", index);   //会計区分11件数
            checkRequireAndPositiveNumeric(entity.getU34KaikeiKin11(), 7, header.getKkbnName11()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u34KaikeiKin11", index);//会計区分11金額
            
            index++;
        }
        
    }

    /**
     * 販売タブチェック処理
     * @param listUri
     */
    private static void checkHanbai(List listUri, UriMainteHeader header) {
        
        int index = 0;
        for (int i=0; i<listUri.size()-1; i++) {
            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(i);
            
            checkRequireAndPositiveNumeric(entity.getU35TieketKen1(), 4, header.getTcktName1()+UriMaintenanceConstants.LIST_KENSU_CLM, "u35TieketKen1", index);       //チケット販売1件数
            checkRequireAndPositiveNumeric(entity.getU36TieketKin1(), 7, header.getTcktName1()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u36TieketKin1", index);    //チケット販売1金額
            checkRequireAndPositiveNumeric(entity.getU37TieketKen2(), 4, header.getTcktName2()+UriMaintenanceConstants.LIST_KENSU_CLM, "u37TieketKen2", index);       //チケット販売2件数
            checkRequireAndPositiveNumeric(entity.getU38TieketKin2(), 7, header.getTcktName2()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u38TieketKin2", index);    //チケット販売2金額
            checkRequireAndPositiveNumeric(entity.getU39TieketKen3(), 4, header.getTcktName3()+UriMaintenanceConstants.LIST_KENSU_CLM, "u39TieketKen3", index);       //チケット販売3件数
            checkRequireAndPositiveNumeric(entity.getU40TieketKin3(), 7, header.getTcktName3()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u40TieketKin3", index);    //チケット販売3金額
            checkRequireAndPositiveNumeric(entity.getU41TieketKen4(), 4, header.getTcktName4()+UriMaintenanceConstants.LIST_KENSU_CLM, "u41TieketKen4", index);       //チケット販売4件数
            checkRequireAndPositiveNumeric(entity.getU42TieketKin4(), 7, header.getTcktName4()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u42TieketKin4", index);    //チケット販売4金額
            checkRequireAndPositiveNumeric(entity.getU43TieketKen5(), 4, header.getTcktName5()+UriMaintenanceConstants.LIST_KENSU_CLM, "u43TieketKen5", index);       //チケット販売5件数
            checkRequireAndPositiveNumeric(entity.getU44TieketKin5(), 7, header.getTcktName5()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u44TieketKin5", index);    //チケット販売5金額
             
            checkRequireAndPositiveNumeric(entity.getU45TieketKen6(),  4, header.getTcktName6()+UriMaintenanceConstants.LIST_KENSU_CLM, "u45TieketKen6", index);      //チケット販売1件数
            checkRequireAndPositiveNumeric(entity.getU46TieketKin6(),  7, header.getTcktName6()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u46TieketKin6", index);   //チケット販売1金額
            checkRequireAndPositiveNumeric(entity.getU47TieketKen7(),  4, header.getTcktName7()+UriMaintenanceConstants.LIST_KENSU_CLM, "u47TieketKen7", index);      //チケット販売2件数
            checkRequireAndPositiveNumeric(entity.getU48TieketKin7(),  7, header.getTcktName7()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u48TieketKin7", index);   //チケット販売2金額
            checkRequireAndPositiveNumeric(entity.getU49TieketKen8(),  4, header.getTcktName8()+UriMaintenanceConstants.LIST_KENSU_CLM, "u49TieketKen8", index);      //チケット販売3件数
            checkRequireAndPositiveNumeric(entity.getU50TieketKin8(),  7, header.getTcktName8()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u50TieketKin8", index);   //チケット販売3金額
            checkRequireAndPositiveNumeric(entity.getU51TieketKen9(),  4, header.getTcktName9()+UriMaintenanceConstants.LIST_KENSU_CLM, "u51TieketKen9", index);      //チケット販売4件数
            checkRequireAndPositiveNumeric(entity.getU52TieketKin9(),  7, header.getTcktName9()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u52TieketKin9", index);   //チケット販売4金額
            checkRequireAndPositiveNumeric(entity.getU53TieketKen10(), 4, header.getTcktName10()+UriMaintenanceConstants.LIST_KENSU_CLM, "u53TieketKen10",index);     //チケット販売5件数
            checkRequireAndPositiveNumeric(entity.getU54TieketKin10(), 7, header.getTcktName10()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u54TieketKin10",index);  //チケット販売5金額
            
            checkRequireAndPositiveNumeric(entity.getU55TieketKen11(), 4, header.getTcktName11()+UriMaintenanceConstants.LIST_KENSU_CLM, "u55TieketKen11", index);    //チケット販売1件数
            checkRequireAndPositiveNumeric(entity.getU56TieketKin11(), 7, header.getTcktName11()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u56TieketKin11", index); //チケット販売1金額
            checkRequireAndPositiveNumeric(entity.getU57TieketKen12(), 4, header.getTcktName12()+UriMaintenanceConstants.LIST_KENSU_CLM, "u57TieketKen12", index);    //チケット販売2件数
            checkRequireAndPositiveNumeric(entity.getU58TieketKin12(), 7, header.getTcktName12()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u58TieketKin12", index); //チケット販売2金額
            checkRequireAndPositiveNumeric(entity.getU59TieketKen13(), 4, header.getTcktName13()+UriMaintenanceConstants.LIST_KENSU_CLM, "u59TieketKen13", index);    //チケット販売3件数
            checkRequireAndPositiveNumeric(entity.getU60TieketKin13(), 7, header.getTcktName13()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u60TieketKin13", index); //チケット販売3金額
            checkRequireAndPositiveNumeric(entity.getU61TieketKen14(), 4, header.getTcktName14()+UriMaintenanceConstants.LIST_KENSU_CLM, "u61TieketKen14", index);    //チケット販売4件数
            checkRequireAndPositiveNumeric(entity.getU62TieketKin14(), 7, header.getTcktName14()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u62TieketKin14", index); //チケット販売4金額
            checkRequireAndPositiveNumeric(entity.getU63TieketKen15(), 4, header.getTcktName15()+UriMaintenanceConstants.LIST_KENSU_CLM, "u63TieketKen15", index);    //チケット販売5件数
            checkRequireAndPositiveNumeric(entity.getU64TieketKin15(), 7, header.getTcktName15()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u64TieketKin15", index); //チケット販売5金額
            
            index++;
        }
    }
    
    
    /**
     * 値引きタブチェック処理
     * @param listUri
     */
    private static void checkNebiki(List listUri, UriMainteHeader header) {
        
        int index = 0;
        for (int i=0; i<listUri.size()-1; i++) {
            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(i);
            
            checkRequireAndPositiveNumeric(entity.getU01NebikiKen1(), 4, header.getNkbnName1()+UriMaintenanceConstants.LIST_KENSU_CLM, "u01NebikiKen1", index);    //値引1件数
            checkRequireAndPositiveNumeric(entity.getU02NebikiKin1(), 7, header.getNkbnName1()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u02NebikiKin1", index); //値引1金額
            checkRequireAndPositiveNumeric(entity.getU03NebikiTax1(), 7, header.getNkbnName1()+UriMaintenanceConstants.LIST_TAX_CLM, "u03NebikiTax1", index);      //値引1税額
            checkRequireAndPositiveNumeric(entity.getU04NebikiKen2(), 4, header.getNkbnName2()+UriMaintenanceConstants.LIST_KENSU_CLM, "u04NebikiKen2", index);    //値引2件数
            checkRequireAndPositiveNumeric(entity.getU05NebikiKin2(), 7, header.getNkbnName2()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u05NebikiKin2", index); //値引2金額
            checkRequireAndPositiveNumeric(entity.getU06NebikiTax2(), 7, header.getNkbnName2()+UriMaintenanceConstants.LIST_TAX_CLM, "u06NebikiTax2", index);      //値引2税額
            checkRequireAndPositiveNumeric(entity.getU07NebikiKen3(), 4, header.getNkbnName3()+UriMaintenanceConstants.LIST_KENSU_CLM, "u07NebikiKen3", index);    //値引3件数
            checkRequireAndPositiveNumeric(entity.getU08NebikiKin3(), 7, header.getNkbnName3()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u08NebikiKin3", index); //値引3金額
            checkRequireAndPositiveNumeric(entity.getU09NebikiTax3(), 7, header.getNkbnName3()+UriMaintenanceConstants.LIST_TAX_CLM, "u09NebikiTax3", index);      //値引3税額
            
            checkRequireAndPositiveNumeric(entity.getU10NebikiKen4(), 4, header.getNkbnName4()+UriMaintenanceConstants.LIST_KENSU_CLM, "u10NebikiKen4", index);    //値引4件数
            checkRequireAndPositiveNumeric(entity.getU11NebikiKin4(), 7, header.getNkbnName4()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u11NebikiKin4", index); //値引4金額
            checkRequireAndPositiveNumeric(entity.getU12NebikiTax4(), 7, header.getNkbnName4()+UriMaintenanceConstants.LIST_TAX_CLM, "u12NebikiTax4", index);      //値引4税額
            checkRequireAndPositiveNumeric(entity.getU13NebikiKen5(), 4, header.getNkbnName5()+UriMaintenanceConstants.LIST_KENSU_CLM, "u13NebikiKen5", index);    //値引5件数
            checkRequireAndPositiveNumeric(entity.getU14NebikiKin5(), 7, header.getNkbnName5()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u14NebikiKin5", index); //値引5金額
            checkRequireAndPositiveNumeric(entity.getU15NebikiTax5(), 7, header.getNkbnName5()+UriMaintenanceConstants.LIST_TAX_CLM, "u15NebikiTax5", index);      //値引5税額
            checkRequireAndPositiveNumeric(entity.getU16NebikiKen6(), 4, header.getNkbnName6()+UriMaintenanceConstants.LIST_KENSU_CLM, "u16NebikiKen6", index);    //値引6件数
            checkRequireAndPositiveNumeric(entity.getU17NebikiKin6(), 7, header.getNkbnName6()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u17NebikiKin6", index); //値引6金額
            checkRequireAndPositiveNumeric(entity.getU18NebikiTax6(), 7, header.getNkbnName6()+UriMaintenanceConstants.LIST_TAX_CLM, "u18NebikiTax6", index);      //値引6税額
            
            checkRequireAndPositiveNumeric(entity.getU19NebikiKen7(), 4, header.getNkbnName7()+UriMaintenanceConstants.LIST_KENSU_CLM, "u19NebikiKen7", index);    //値引7件数
            checkRequireAndPositiveNumeric(entity.getU20NebikiKin7(), 7, header.getNkbnName7()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u20NebikiKin7", index); //値引7金額
            checkRequireAndPositiveNumeric(entity.getU21NebikiTax7(), 7, header.getNkbnName7()+UriMaintenanceConstants.LIST_TAX_CLM, "u21NebikiTax7", index);      //値引7税額
            checkRequireAndPositiveNumeric(entity.getU22NebikiKen8(), 4, header.getNkbnName8()+UriMaintenanceConstants.LIST_KENSU_CLM, "u22NebikiKen8", index);    //値引8件数
            checkRequireAndPositiveNumeric(entity.getU23NebikiKin8(), 7, header.getNkbnName8()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u23NebikiKin8", index); //値引8金額
            checkRequireAndPositiveNumeric(entity.getU24NebikiTax8(), 7, header.getNkbnName8()+UriMaintenanceConstants.LIST_TAX_CLM, "u24NebikiTax8", index);      //値引8税額
            checkRequireAndPositiveNumeric(entity.getU25NebikiKen9(), 4, header.getNkbnName9()+UriMaintenanceConstants.LIST_KENSU_CLM, "u25NebikiKen9", index);    //値引9件数
            checkRequireAndPositiveNumeric(entity.getU26NebikiKin9(), 7, header.getNkbnName9()+UriMaintenanceConstants.LIST_KINGAKUK_CLM, "u26NebikiKin9", index); //値引9金額
            checkRequireAndPositiveNumeric(entity.getU27NebikiTax9(), 7, header.getNkbnName9()+UriMaintenanceConstants.LIST_TAX_CLM, "u27NebikiTax9", index);      //値引9税額
            
            index++;
        }
    }

    
    /**
     * 計算結果チェック処理
     * @param listUri
     */
    private static void checkCalculate(List listUri, UriMainteHeader header) {
        
        int index = 0;
        for (int i=0; i<listUri.size()-1; i++) {
            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(i);
            
            checkGenkinKingakuPositiveNumericAndLength(entity.getU14GenkinKin(), 7, header.getGenkinKin(), "u01Uriage", index); //現金金額
            checkGenkinKafusokuNumericAndLength(entity.getGenkinKafusoku(), 7, header.getGenkinKabusoku(), "u01Uriage", index);  //現金過不足
            
            index++;
        }
        
    }
    
    
    /**
     * 整数チェック
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param elm チェック対象名称
     */
    private static void checkNumericAndLength(String value, int length, String elm, int index, String msg) {
        
        //マイナス不許可、桁数指定、小数点以下桁数0
        NumericVerifier numericVerifier = new NumericVerifier(false, length, 0);
        try {
	        if (!CommonUtil.isNull(value) && !numericVerifier.validate(value)) {
	            throw new GenericMessageException(msg, elm, index);
	        }
        }
        finally{
        	numericVerifier = null;
        }
    }
    /**
     * 正の整数チェック（現金金額チェック）
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param strElm チェック対象名称
     */
    private static void checkGenkinKingakuPositiveNumericAndLength(String value, int length, String strElm, String elm, int index) {
         String msg = strElm+"は正の整数で"+String.valueOf(length)+"桁までとなっています。内訳を確認してください。";
    	 checkNumericAndLength(value, length, elm, index, msg);
    }

    
    /**
     * 整数チェック（現金過不足）
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param strElm チェック対象名称
     */
    private static void checkGenkinKafusokuNumericAndLength(String value, int length, String strElm, String elm, int index) {
    	String msg = strElm+"は整数"+String.valueOf(length)+"桁まで入力してください。";
        //マイナス許可、桁数指定、小数点以下桁数0
        NumericVerifier numericVerifier = new NumericVerifier(true, length, 0);
        try {
	        if (!CommonUtil.isNull(value) && !numericVerifier.validate(value)) {
	            throw new GenericMessageException(msg, elm, index);
	        }
        }
        finally{
        	numericVerifier = null;
        }
	}
    /**
     * 正の整数チェック
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param strElm チェック対象名称
     */
    private static void checkPositiveNumericAndLength(String value, int length, String strElm, String elm, int index) {
    	String msg = strElm+"は正の整数"+String.valueOf(length)+"桁まで入力してください。";
    	checkNumericAndLength(value, length, elm, index, msg);
    }
    
    
    /**
     * 必須チェック＆正の整数チェック
     * @param value
     * @param length
     * @param strElm
     */
    private static void checkRequireAndPositiveNumeric(String value, int length, String strElm, String elm, int index) {
        checkRequire(value, strElm, elm, index);
        checkPositiveNumericAndLength(value, length, strElm, elm, index);
    }
    
    
    /**
     * 必須チェック
     * @param value チェック対象数値
     * @param strElm チェック対象名称
     */
    private static void checkRequire(String value, String strElm, String elm, int index) {
        if (value == null || value.equals("")) {
            throw new GenericMessageException(strElm+"は必須です。", elm, index);
        }
    }

    
}
