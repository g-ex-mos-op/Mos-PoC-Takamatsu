/*
 * 作成日: 2007/02/15
 */
package jp.co.isid.mos.bird.storemanage.posniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.storemanage.posniporef.dto.PosNipoSessionDto;
import jp.co.isid.mos.bird.storemanage.posniporef.entity.PosNipoInfo;
import jp.co.isid.mos.bird.storemanage.posniporef.util.HourTimeFormatter;

/**
 * POS日報CSVダウンロードロジック
 * @author xwatanabe
 */
public class PosNipoCSVLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM014L04";
    /** ファイル名 */
    public static String FILE_NAME = "";

    
    /**
     * CSV出力用のデータ取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        //出力用リスト
        List outputList = new ArrayList();

        //空行出力用リスト
        List karaList = new ArrayList();
        karaList.add("");

        //作業用リスト
        List tmpList;

        //DTOより情報取得
//---2007/03/26変更---
//        PosNipoRequestDto dto   = (PosNipoRequestDto)csvOutputDto;
//        String dispCompanyName = dto.getDispCompanyName();
//        String dispTenpoCnt    = dto.getDispTenpoCnt();
//        String dispKikan       = dto.getDispKikan();
//        String dispMiseName    = dto.getDispMiseName();
//        List posNipoList       = dto.getPosNipoList();

        PosNipoSessionDto dto   = (PosNipoSessionDto)csvOutputDto;
        int windowId = dto.getWindowId();
        String dispCompanyName = dto.getDispCompanyName(windowId);
        String dispTenpoCnt = dto.getDispTenpoCnt(windowId);
        String dispKikan = dto.getDispKikan(windowId);
        String dispMiseName = dto.getDispMiseName(windowId);
        List posNipoList = dto.getPosNipoList(windowId);
//---2007/03/26変更---

         //ファイル名
        FILE_NAME = "POSNIPO" + ".csv";

        //--------------
        // ヘッダ部
        //--------------
        //１行目
        tmpList = new ArrayList();
        tmpList.add("会社：");
        tmpList.add(dispCompanyName);
        tmpList.add("");
        tmpList.add("取得店舗数：");
        tmpList.add(dispTenpoCnt);
        outputList.add(tmpList);
        
        //２行目
        tmpList = new ArrayList();
        tmpList.add("期間：");
        tmpList.add(dispKikan);
        outputList.add(tmpList);
        
        //３行目
        tmpList = new ArrayList();
        tmpList.add("店舗：");
        tmpList.add(dispMiseName);
        outputList.add(tmpList);

        //空行
        outputList.add(karaList);

        //---------------------
        // データ部・項目名
        //---------------------
        tmpList = new ArrayList();
        tmpList.add("日付");
        tmpList.add("売上");
        tmpList.add("仕入：合計  金額");
        tmpList.add("仕入：仕入率");
        tmpList.add("仕入：食材");
        tmpList.add("仕入：野菜");
        tmpList.add("仕入：包材");
        tmpList.add("仕入：食包材合計");
        tmpList.add("仕入：物販");
        tmpList.add("仕入：消耗品");
        tmpList.add("実際原価：合計  金額");
        tmpList.add("実際原価：合計  原価率");
        tmpList.add("実際原価：食材  金額");
        tmpList.add("実際原価：食材  原価率");
        tmpList.add("実際原価：野菜  金額");
        tmpList.add("実際原価：野菜  原価率");
        tmpList.add("実際原価：包材  金額");
        tmpList.add("実際原価：包材  原価率");
        tmpList.add("実際原価：食包材計  金額");
        tmpList.add("実際原価：食包材計  原価率");
        tmpList.add("実際原価：物販計  金額");
        tmpList.add("実際原価：物販計  原価率");
        tmpList.add("標準原価：合計  金額");
        tmpList.add("標準原価：合計  原価率");
        tmpList.add("標準原価：食材  金額");
        tmpList.add("標準原価：食材  原価率");
        tmpList.add("標準原価：野菜  金額");
        tmpList.add("標準原価：野菜  原価率");
        tmpList.add("標準原価：包材  金額");
        tmpList.add("標準原価：包材  原価率");
        tmpList.add("標準原価：食包材計  金額");
        tmpList.add("標準原価：食包材計  原価率");
        tmpList.add("標準原価：物販計  金額");
        tmpList.add("標準原価：物販計  原価率");
        tmpList.add("人件費：人時売上高");
        tmpList.add("人件費：合計  金額");
        tmpList.add("人件費：合計  人件費率");
        tmpList.add("人件費：総労働時間");
        tmpList.add("人件費：Ｐ・Ａ  金額");
        tmpList.add("人件費：Ｐ・Ａ  人件費率");
        tmpList.add("人件費：Ｐ・Ａ  労働時間");
        tmpList.add("人件費：社員  金額");
        tmpList.add("人件費：社員  人件費率");
        tmpList.add("人件費：社員  労働時間");
        outputList.add(tmpList);

        //---------------------
        // データ部・データ
        //---------------------
        // 数値タイプの文字列フォーマッタ(定数) 2013/02 追加 海外売上集信対応
        NumericFormatter formatterUriage = new NumericFormatter(true, 0, true);
        for(int i=0 ; i < posNipoList.size(); i++) {

            PosNipoInfo posNipo = (PosNipoInfo)posNipoList.get(i);
            tmpList = new ArrayList();

            tmpList.add(formatDD(posNipo.getEigyoDt()));                    //日付
            tmpList.add(formatterUriage.format(posNipo.getUriage()));                     //売上
            tmpList.add(addComma(posNipo.getSiireKingakuSum()));            //仕入：金額合計
            tmpList.add(addComma(posNipo.getSiireRitu()));                  //仕入：率
            tmpList.add(addComma(posNipo.getSiireShoku()));
            tmpList.add(addComma(posNipo.getSiireYasai()));
            tmpList.add(addComma(posNipo.getSiireHouzai()));
            tmpList.add(addComma(posNipo.getSiireKingakuShokuHouzaiSum()));
            tmpList.add(addComma(posNipo.getSiireOther()));
            tmpList.add(addComma(posNipo.getSiireShoumou()));
            tmpList.add(addComma(posNipo.getJituKingakuSum()));             //実際原価：合計：金額
            tmpList.add(posNipo.getJituGenkarituSum());                     //実際原価：合計：率
            tmpList.add(addComma(posNipo.getJituShoku()));                  //実際原価：食材：金額
            tmpList.add(posNipo.getJituGenkarituShoku());                   //実際原価：食材：原価率
            tmpList.add(addComma(posNipo.getJituYasai()));                  //実際原価：野菜：金額
            tmpList.add(posNipo.getJituGenkarituYasai());                   //実際原価：野菜：原価率
            tmpList.add(addComma(posNipo.getJituHouzai()));                 //実際原価：包材：金額
            tmpList.add(posNipo.getJituGenkarituHouzai());                  //実際原価：包材：原価率
            tmpList.add(addComma(posNipo.getJituKingakuShokuHouzaiSum()));  //実際原価：食包材計：金額
            tmpList.add(posNipo.getJituGenkarituShokuHouzaiSum());          //実際原価：食包材計：原価率
            tmpList.add(addComma(posNipo.getJituOther()));                  //実際原価：物販計：金額
            tmpList.add(posNipo.getJituGenkarituOther());                   //実際原価：物販計：原価率
            tmpList.add(addComma(posNipo.getStdKingakuSum()));              //標準原価：合計：金額
            tmpList.add(posNipo.getStdGenkarituSum());                      //標準原価：合計：率
            tmpList.add(addComma(posNipo.getStdShoku()));                   //標準原価：食材：金額
            tmpList.add(posNipo.getStdGenkarituShoku());                    //標準原価：食材：原価率
            tmpList.add(addComma(posNipo.getStdYasai()));                   //標準原価：野菜：金額
            tmpList.add(posNipo.getStdGenkarituYasai());                    //標準原価：野菜：原価率
            tmpList.add(addComma(posNipo.getStdHouzai()));                  //標準原価：包材：金額
            tmpList.add(posNipo.getStdGenkarituHouzai());                   //標準原価：包材：原価率
            tmpList.add(addComma(posNipo.getStdKingakuShokuHouzaiSum()));   //標準原価：食包材計：金額
            tmpList.add(posNipo.getStdGenkarituShokuHouzaiSum());           //標準原価：食包材計：原価率
            tmpList.add(addComma(posNipo.getStdOther()));                   //標準原価：物販計：金額
            tmpList.add(posNipo.getStdGenkarituOther());                    //標準原価：物販計：原価率
            tmpList.add(addComma(posNipo.getJinjiUriage()));                //人件費：人時売上高
            tmpList.add(addComma(posNipo.getJinkenhiKingakuSum()));         //人件費：合計：金額
            tmpList.add(posNipo.getJinkenhirituSum());                      //人件費：合計：人件費率
            tmpList.add(formatHour(posNipo.getJinkenhiWh()));               //人件費：総労働時間
            tmpList.add(addComma(posNipo.getPaSal()));                      //人件費：Ｐ・Ａ：金額"
            tmpList.add(posNipo.getPaJinkenhiritu());                       //人件費：Ｐ・Ａ：人件費率
            tmpList.add(formatHour(posNipo.getPaWh()));                     //人件費：Ｐ・Ａ：労働時間
            tmpList.add(addComma(posNipo.getShainSal()));                   //人件費：社員：金額
            tmpList.add(posNipo.getShainJinkenhiritu());                    //人件費：社員：人件費率
            tmpList.add(formatHour(posNipo.getShainWh()));                  //人件費：社員：労働時間

            //行追加
            outputList.add(tmpList);
        }
        return outputList;
    }
        

    /**
     * validateメソッド
     */
    public void validate(CsvOutputDto csvOutputDto) {
        //自動生成されたメソッド・スタブ
    }

    /**
     * ファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        return FILE_NAME;
    }

    /**
     * 金額を表示用に編集
     * 数値にカンマを付け加える
     * @param  数値(金額)
     * @return カンマ編集済文字列
     */
    private String addComma(BigDecimal dec){
 
        //返却用
        String ret;
        NumericFormatter formatter = new NumericFormatter();
        ret = formatter.format(dec.toString(),"##,###,###,##0");
        return ret;
    }

    /** 日付のフォーマット。
     * @param  日付文字列(YYYYMM)
     * @return 日付文字列(DD日(曜))
    */
    private String formatDD(String date){

        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日('E')'");
        date = dateFormatter.format(date, true);
        
        return date;
    }

    /** 日付のフォーマット。
     * @param  日付文字列(YYYYMM)
     * @return 日付文字列(DD日(曜))
    */
    private String formatHour(BigDecimal dec){

        HourTimeFormatter dateFormatter = new HourTimeFormatter();
        String str = dateFormatter.format(dec);
        
        return str;
    }
}
