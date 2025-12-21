/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.urimaintenance.util;

import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;

/**
 * 作成日:2012/08/02
 * @author xkinu
 *
 */
public class ListCopy {
    /**
     * 売上修正Entityのコピーを行う
     * @return
     */
    public static UIUriMainteWorkInfo copyReviseData(final UIUriMainteWorkInfo entity) {
        
        UIUriMainteWorkInfo entityTmp = new UIUriMainteWorkInfo();
        
        entityTmp.setU01Uriage(entity.getU01Uriage());
        entityTmp.setU02MenuUri(entity.getU02MenuUri());
        entityTmp.setU03BuppanUri(entity.getU03BuppanUri());
        entityTmp.setU04Nebiki(entity.getU04Nebiki());
        entityTmp.setU05Nebikigo(entity.getU05Nebikigo());
        entityTmp.setU06Tax(entity.getU06Tax());
        entityTmp.setU07MinusKen(entity.getU07MinusKen());
        entityTmp.setU08MinusKin(entity.getU08MinusKin());
        entityTmp.setU09NebikiKen(entity.getU09NebikiKen());
        entityTmp.setU10NebikiKin(entity.getU10NebikiKin());
        entityTmp.setU11PNebikiKen(entity.getU11PNebikiKen());
        entityTmp.setU12PNebikiKin(entity.getU12PNebikiKin());
        entityTmp.setU13GenkinKen(entity.getU13GenkinKen());
        entityTmp.setU14GenkinKin(entity.getU14GenkinKin());
        entityTmp.setU15KaikeiKen2(entity.getU15KaikeiKen2());
        entityTmp.setU16KaikeiKin2(entity.getU16KaikeiKin2());
        entityTmp.setU17KaikeiKen3(entity.getU17KaikeiKen3());
        entityTmp.setU18KaikeiKin3(entity.getU18KaikeiKin3());
        entityTmp.setU19KaikeiKen4(entity.getU19KaikeiKen4());
        entityTmp.setU20KaikeiKin4(entity.getU20KaikeiKin4());
        entityTmp.setU21KaikeiKen5(entity.getU21KaikeiKen5());
        entityTmp.setU22KaikeiKin5(entity.getU22KaikeiKin5());
        entityTmp.setU23KaikeiKen6(entity.getU23KaikeiKen6());
        entityTmp.setU24KaikeiKin6(entity.getU24KaikeiKin6());
        entityTmp.setU25KaikeiKen7(entity.getU25KaikeiKen7());
        entityTmp.setU26KaikeiKin7(entity.getU26KaikeiKin7());
        entityTmp.setU27KaikeiKen8(entity.getU27KaikeiKen8());
        entityTmp.setU28KaikeiKin8(entity.getU28KaikeiKin8());
        entityTmp.setU29KaikeiKen9(entity.getU29KaikeiKen9());
        entityTmp.setU30KaikeiKin9(entity.getU30KaikeiKin9());
        entityTmp.setU31KaikeiKen10(entity.getU31KaikeiKen10());
        entityTmp.setU32KaikeiKin10(entity.getU32KaikeiKin10());
        entityTmp.setU33KaikeiKen11(entity.getU33KaikeiKen11());
        entityTmp.setU34KaikeiKin11(entity.getU34KaikeiKin11());
        entityTmp.setU35TieketKen1(entity.getU35TieketKen1());
        entityTmp.setU36TieketKin1(entity.getU36TieketKin1());
        entityTmp.setU37TieketKen2(entity.getU37TieketKen2());
        entityTmp.setU38TieketKin2(entity.getU38TieketKin2());
        entityTmp.setU39TieketKen3(entity.getU39TieketKen3());
        entityTmp.setU40TieketKin3(entity.getU40TieketKin3());
        entityTmp.setU41TieketKen4(entity.getU41TieketKen4());
        entityTmp.setU42TieketKin4(entity.getU42TieketKin4());
        entityTmp.setU43TieketKen5(entity.getU43TieketKen5());
        entityTmp.setU44TieketKin5(entity.getU44TieketKin5());
        entityTmp.setU45TieketKen6(entity.getU45TieketKen6());
        entityTmp.setU46TieketKin6(entity.getU46TieketKin6());
        entityTmp.setU47TieketKen7(entity.getU47TieketKen7());
        entityTmp.setU48TieketKin7(entity.getU48TieketKin7());
        entityTmp.setU49TieketKen8(entity.getU49TieketKen8());
        entityTmp.setU50TieketKin8(entity.getU50TieketKin8());
        entityTmp.setU51TieketKen9(entity.getU51TieketKen9());
        entityTmp.setU52TieketKin9(entity.getU52TieketKin9());
        entityTmp.setU53TieketKen10(entity.getU53TieketKen10());
        entityTmp.setU54TieketKin10(entity.getU54TieketKin10());
        entityTmp.setU55TieketKen11(entity.getU55TieketKen11());
        entityTmp.setU56TieketKin11(entity.getU56TieketKin11());
        entityTmp.setU57TieketKen12(entity.getU57TieketKen12());
        entityTmp.setU58TieketKin12(entity.getU58TieketKin12());
        entityTmp.setU59TieketKen13(entity.getU59TieketKen13());
        entityTmp.setU60TieketKin13(entity.getU60TieketKin13());
        entityTmp.setU61TieketKen14(entity.getU61TieketKen14());
        entityTmp.setU62TieketKin14(entity.getU62TieketKin14());
        entityTmp.setU63TieketKen15(entity.getU63TieketKen15());
        entityTmp.setU64TieketKin15(entity.getU64TieketKin15());
        entityTmp.setU65Nyukin(entity.getU65Nyukin());
        entityTmp.setU66Shukin(entity.getU66Shukin());
        entityTmp.setU67AridakaCal(entity.getU67AridakaCal());
        entityTmp.setU68AridakaJitu(entity.getU68AridakaJitu());
        entityTmp.setU69Kajou(entity.getU69Kajou());
        entityTmp.setU70Fusoku(entity.getU70Fusoku());
        entityTmp.setU71CanKen(entity.getU71CanKen());
        entityTmp.setU72CanKin(entity.getU72CanKin());
        entityTmp.setU73ChengeCnt(entity.getU73ChengeCnt());
        entityTmp.setU74Kyakusu(entity.getU74Kyakusu());
        entityTmp.setU75AllcanKen(entity.getU75AllcanKen());
        entityTmp.setU76AllcanKin(entity.getU76AllcanKin());
        entityTmp.setDataKbn(entity.getDataKbn());
        entityTmp.setU01NebikiKen1(entity.getU01NebikiKen1());
        entityTmp.setU02NebikiKin1(entity.getU02NebikiKin1());
        entityTmp.setU03NebikiTax1(entity.getU03NebikiTax1());
        entityTmp.setU04NebikiKen2(entity.getU04NebikiKen2());
        entityTmp.setU05NebikiKin2(entity.getU05NebikiKin2());
        entityTmp.setU06NebikiTax2(entity.getU06NebikiTax2());
        entityTmp.setU07NebikiKen3(entity.getU07NebikiKen3());
        entityTmp.setU08NebikiKin3(entity.getU08NebikiKin3());
        entityTmp.setU09NebikiTax3(entity.getU09NebikiTax3());
        entityTmp.setU10NebikiKen4(entity.getU10NebikiKen4());
        entityTmp.setU11NebikiKin4(entity.getU11NebikiKin4());
        entityTmp.setU12NebikiTax4(entity.getU12NebikiTax4());
        entityTmp.setU13NebikiKen5(entity.getU13NebikiKen5());
        entityTmp.setU14NebikiKin5(entity.getU14NebikiKin5());
        entityTmp.setU15NebikiTax5(entity.getU15NebikiTax5());
        entityTmp.setU16NebikiKen6(entity.getU16NebikiKen6());
        entityTmp.setU17NebikiKin6(entity.getU17NebikiKin6());
        entityTmp.setU18NebikiTax6(entity.getU18NebikiTax6());
        entityTmp.setU19NebikiKen7(entity.getU19NebikiKen7());
        entityTmp.setU20NebikiKin7(entity.getU20NebikiKin7());
        entityTmp.setU21NebikiTax7(entity.getU21NebikiTax7());
        entityTmp.setU22NebikiKen8(entity.getU22NebikiKen8());
        entityTmp.setU23NebikiKin8(entity.getU23NebikiKin8());
        entityTmp.setU24NebikiTax8(entity.getU24NebikiTax8());
        entityTmp.setU25NebikiKen9(entity.getU25NebikiKen9());
        entityTmp.setU26NebikiKin9(entity.getU26NebikiKin9());
        entityTmp.setU27NebikiTax9(entity.getU27NebikiTax9());
        entityTmp.setGenkinKafusoku(entity.getGenkinKafusoku());
        
        entityTmp.setUpNo96(entity.getUpNo96());
        entityTmp.setUpNo97(entity.getUpNo97());
        
        return entityTmp;

    }

}
