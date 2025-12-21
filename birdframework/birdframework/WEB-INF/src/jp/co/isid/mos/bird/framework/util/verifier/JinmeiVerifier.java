/*
 * DefaultJapaneseVerifier.java
 * 
 * Created by xytamura
 * Copy from 2ndGenesis on 2005/11/14
 *
 */
package jp.co.isid.mos.bird.framework.util.verifier;

/**
 * 日本語文字列用バリデータ。
 * JIS非漢字、第一水準、第二水準、及び半角文字列を許可します。
 */
public class JinmeiVerifier extends DefaultJapaneseVerifier {

    /* 人名に使用する文字　*/
    public static final String GAIJI_FOR_NAME = 
         "纊褜鍈銈蓜俉炻昱棈鋹曻彅丨仡仼伀伃伹佖侒侊侚侔俍偀倢俿倞偆偰偂傔僴僘兊兤冝冾凬刕劜劦勀勛匀匇匤卲厓厲叝﨎咜咊咩哿喆坙坥垬埈埇﨏塚增墲夋奓奛奝奣妤妺孖寀甯寘寬尞岦岺峵崧嵓﨑嵂嵭嶸嶹巐弡弴彧德"
     +   "忞恝悅悊惞惕愠惲愑愷愰憘戓抦揵摠撝擎敎昀昕昻昉昮昞昤晥晗晙晴晳暙暠暲暿曺朎朗杦枻桒柀栁桄棏﨓楨﨔榘槢樰橫橆橳橾櫢櫤毖氿汜沆汯泚洄涇浯涖涬淏淸淲淼渹湜渧渼溿澈澵濵瀅瀇瀨炅炫焏焄煜煆煇凞燁燾犱"
     +   "犾猤猪獷玽珉珖珣珒琇珵琦琪琩琮瑢璉璟甁畯皂皜皞皛皦益睆劯砡硎硤硺礰礼神祥禔福禛竑竧靖竫箞精絈絜綷綠緖繒罇羡羽茁荢荿菇菶葈蒴蕓蕙蕫﨟薰蘒﨡蠇裵訒訷詹誧誾諟諸諶譓譿賰賴贒赶﨣軏﨤逸遧郞都鄕鄧釚"
     +   "釗釞釭釮釤釥鈆鈐鈊鈺鉀鈼鉎鉙鉑鈹鉧銧鉷鉸鋧鋗鋙鋐﨧鋕鋠鋓錥錡鋻﨨錞鋿錝錂鍰鍗鎤鏆鏞鏸鐱鑅鑈閒隆﨩隝隯霳霻靃靍靏靑靕顗顥飯飼餧館馞驎髙髜魵魲鮏鮱鮻鰀鵰鵫鶴鸙黑";


    /**
     * @see jp.co.isid.mos.birdframework.util.JinmeVerifier
     */
    protected boolean isKanji(final String source) {
        if (source.equals("\n")) {
            return true;
        }

        if (source.equals("\r")) {
            return true;
        }

        if(isGijiForName(source)){
            return true;
        }

        
        /* from http://home.att.ne.jp/apple/wizard/java/ShiftJIS.html
        JISX0208   非漢字           0x8140～0x84BE
                   第一水準         0x889F～0x9872        
                   第二水準         0x989F～0x9FFC, 0xE040～0xEAA4
        
        Windows start-program-アクセサリ-システムツール-文字コード表も参照 */

        //String special = "～∥－￠￡￢";
        //String special = "～∥－";
        //if (special.indexOf(source.charAt(0)) >= 0) {
        //return true;
        //}
        byte[] bytes;
        try {
            bytes = source.getBytes("Shift_JIS");
        }
        catch (java.io.UnsupportedEncodingException e) {
            if(isGijiForName(source)){
                return true;
            }
            return false;
        }
        if (bytes.length != 2) {
            return false;
        }

        int high = bytes[0];
        int low = bytes[1];
        if (high < 0) {
            high = 256 + high;
        }
        if (low < 0) {
            low = 256 + low;
        }

        long value = low + (long) high * 256;

//      //2006/05/01 JIS非漢字、第一水準漢字及び第二水準漢字のみ許可
        if (0x8140 <= value && value <= 0x84BE
            || 0x889F <= value && value <= 0x9872
            || 0x989F <= value && value <= 0x9FFC
            || 0xE040 <= value && value <= 0xEAA4) {
            return true;
        }

//        //JISX0208 第一水準漢字及び第二水準漢字のみ許可
//        if ((0x889F <= value && value <= 0x9872)
//            || (0x989F <= value && value <= 0x9FFC)
//            || (0xE040 <= value && value <= 0xEAA4)) {
//            return true;
//        }
        
        return false;
    }
    
    /**
     * 人名に使用される漢字か判定する
     * @param source
     * @return true:人名漢字
     */
    private boolean isGijiForName(final String source) {
       return GAIJI_FOR_NAME.indexOf(source) != -1;
    }
}