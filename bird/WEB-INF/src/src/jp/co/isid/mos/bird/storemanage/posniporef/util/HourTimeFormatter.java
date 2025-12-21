package jp.co.isid.mos.bird.storemanage.posniporef.util;

import java.math.BigDecimal;
import jp.co.isid.mos.bird.framework.util.Converter;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;

/** 
 * @author ISID MOS PROJECT
 */
public class HourTimeFormatter extends DefaultFormatter {

    /**
     * 時間単位の数値を [HHHH:mm] 形式の文字列に変換する。
     * mmの小数点1位は切上げ処理をする。
     * @param  target     対象文字列
     * @return フォーマット済文字列
     */
    public String format(BigDecimal target) {

        //返却用
        String retStr  = "";

        //文字列
        String strHour;
        String strMin;

        //数値
        BigDecimal bdHour;
        BigDecimal bdMin;

        try{
            //整数部分のみ切取り
            bdHour = target.setScale(0, BigDecimal.ROUND_FLOOR);
            
            //少数部分のみ切取り
            bdMin  = target.subtract(bdHour);

            //60を乗算して(時間→分)単位にする
            bdMin = bdMin.multiply(new BigDecimal(60));
            
            //小数点1位以下切捨て
            bdMin = bdMin.setScale(1, BigDecimal.ROUND_FLOOR);

            //小数点以下切り上げ
            bdMin = bdMin.setScale(0, BigDecimal.ROUND_CEILING);

            
            //切り上げで60分になった時対応
            if (bdMin.intValue() == 60) {
                //時間に＋１する
                bdHour = bdHour.add(new BigDecimal(1));
                strHour = Converter.decToString(bdHour);
                
                //分を00にセット
                strMin  = "00";                                         
            }else{

                //時間
                strHour = Converter.decToString(bdHour);

                //分
                strMin = Converter.decToString(bdMin);
                if(strMin.length() == 1){
                    strMin = "0" + strMin; 
                }
            }

            //文字列を連結して返却
            retStr = strHour + ":" + strMin;
            
        }catch(Exception e){

            retStr = target.toString();
        }

        return retStr;
    }

    /**
     * [HHHH:mm] 形式の文字列を時間単位の数値(BigDecimal)に変換する。
     * @param  対象文字列
     * @return 時間単位の数値
     */
    public BigDecimal parseTimeValue(String time) {

//        StringTokenizer tokenizer = new StringTokenizer(time, ":");
//        if (tokenizer.countTokens() != 2) {
//            return null;
//        }
//      String[] array = (String[]) Collections.list(tokenizer).toArray();

        String[] array = time.split(":");
        if(array == null || array.length != 2){
            return null;
        }
        
        BigDecimal hour = null;
        BigDecimal minute = null;

        try {
            hour = new BigDecimal(array[0]);
            minute = new BigDecimal(array[1]);
        } catch (NumberFormatException e) {
            return null;
        }

        BigDecimal convertMinute = minute.divide(BigDecimal.valueOf(60), 2, BigDecimal.ROUND_DOWN);
        return hour.add(convertMinute);
    }
    
}
