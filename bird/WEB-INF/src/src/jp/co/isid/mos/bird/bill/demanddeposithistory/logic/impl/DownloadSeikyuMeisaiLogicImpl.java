/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bill.demanddeposithistory.dto.DemandDepositHistoryDto;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIBillRireki;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UITaxRateWake;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;


/**
 * 請求明細CSVダウンロードロジック
 * 請求明細情報をダウンロードする。
 *
 * @author xwatanabe
 */
public class DownloadSeikyuMeisaiLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS009L03";
    /** ファイル名 */
    public static final String FILE_NAME = "SEIKYU.csv";

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

        //----------------------------
        //DTOより表示対象リストの取得
        //----------------------------
        DemandDepositHistoryDto dto = (DemandDepositHistoryDto)csvOutputDto;
        List saisinTabList = dto.getSaisinTabList();
        List rirekiTabList = dto.getRirekiTabList();
// add 2023/02/07 USI範  begin
        List tenPercentList = dto.getTenPercentList();
        List gennZeiList = dto.getGennZeiList();
        List keikaSotiList = dto.getKeikaSotiList();
        List hiKazeiList = dto.getHiKazeiList();
// add 2023/02/07 USI範  end

        //----------------------------
        //最新タブ部分
        //----------------------------
        for(int i=0; i<saisinTabList.size(); i++) {

            UIBillRireki uiBillRireki = (UIBillRireki)saisinTabList.get(i);
//add 2023/04/12 USI金 begin
            String utiwakeKbn = uiBillRireki.getUtiwakeKbn();
//add 2023/04/12 USI金 end
// add 2023/02/07 USI範  begin
//modify 2023/04/14 USI金  begin
//            UITaxRateWake uIZniRizuWakeTen = (UITaxRateWake)tenPercentList.get(i);
//            UITaxRateWake uIZniRizuWakeGen = (UITaxRateWake)gennZeiList.get(i);
//            UITaxRateWake uIZniRizuWakeKei = (UITaxRateWake)keikaSotiList.get(i);
//            UITaxRateWake uIZniRizuWakeHi = (UITaxRateWake)hiKazeiList.get(i);
            UITaxRateWake uIZniRizuWakeTen = null;
            UITaxRateWake uIZniRizuWakeGen = null;
            UITaxRateWake uIZniRizuWakeKei = null;
            UITaxRateWake uIZniRizuWakeHi = null;
            if(!tenPercentList.isEmpty()) {
                uIZniRizuWakeTen = (UITaxRateWake)tenPercentList.get(i);
                uIZniRizuWakeGen = (UITaxRateWake)gennZeiList.get(i);
                uIZniRizuWakeKei = (UITaxRateWake)keikaSotiList.get(i);
                uIZniRizuWakeHi = (UITaxRateWake)hiKazeiList.get(i);
            }
//modify 2023/04/14 USI金  end
// add 2023/02/07 USI範  end
            tmpList = new ArrayList();
            tmpList.add(uiBillRireki.getUrikakeCd());             //売掛先コード
            tmpList.add(uiBillRireki.getOnerNameKj() + " 殿");    //売掛先名称
            outputList.add(tmpList);

            tmpList = new ArrayList();
            tmpList.add("日付");
            tmpList.add("前回ご請求額");
            tmpList.add("ご入金額");
            tmpList.add("繰越残高");
            tmpList.add("今回お買上額");
            tmpList.add("今回ご請求額");
            outputList.add(tmpList);

            tmpList = new ArrayList();
            tmpList.add(uiBillRireki.getSeikyuNyukinDt() + uiBillRireki.getHakkoDt());
            tmpList.add(addComma(uiBillRireki.getSeikyuZen()));
            tmpList.add(addComma(uiBillRireki.getNyukinGaku()));
            tmpList.add(addComma(uiBillRireki.getKuriZan()));
            tmpList.add(addComma(uiBillRireki.getKaiageGaku()));
            tmpList.add(addComma(uiBillRireki.getSeikyuKon()));
            outputList.add(tmpList);

            outputList.add(karaList);

            tmpList = new ArrayList();
            tmpList.add("＜今回お買上額内訳＞");
            outputList.add(tmpList);

            tmpList = new ArrayList();
// modify 2023/02/07 USI範  begin
//            tmpList.add("課税合計額");
//            tmpList.add("消費税額");
//            tmpList.add("税込・非課税額");
//modify 2023/04/12 USI金 begin
//            tmpList.add("税抜金額合計");
//            tmpList.add("消費税額");
//            tmpList.add("非課税額");
            if(utiwakeKbn == "0") {
                 tmpList.add("課税合計額");
                 tmpList.add("消費税額");
                 tmpList.add("税込・非課税額");
            }else if(utiwakeKbn == "1" && !tenPercentList.isEmpty()) {
            	 tmpList.add("税抜金額合計");
                 tmpList.add("消費税額");
                 tmpList.add("非課税額");
            }
//modify 2023/04/12 USI金 end
// modify 2023/02/07 USI範  end
            outputList.add(tmpList);

            tmpList = new ArrayList();
// modify 2023/02/07 USI範  begin
//            tmpList.add(addComma(uiBillRireki.getKazeiGaku()));
//            tmpList.add(addComma(uiBillRireki.getTaxKei()));
//            tmpList.add(addComma(uiBillRireki.getHizeiGaku()));
//modify 2023/04/12 USI金 begin
//            tmpList.add(uIZniRizuWakeTen.gettaxkbn1_kazei().add(uIZniRizuWakeGen.gettaxkbn2_kazei()).add(uIZniRizuWakeKei.gettaxkbn3_kazei()).add(uIZniRizuWakeTen.gettaxkbn1_uri()).add(uIZniRizuWakeGen.gettaxkbn2_uri()).add(uIZniRizuWakeKei.gettaxkbn3_uri()));
//            tmpList.add(uIZniRizuWakeTen.gettaxkbn1_inv().add(uIZniRizuWakeGen.gettaxkbn2_inv()).add(uIZniRizuWakeKei.gettaxkbn3_inv()));
//            tmpList.add(addComma(uIZniRizuWakeHi.gettaxkbn4_hizei()));
            if(utiwakeKbn == "0") {
            	 tmpList.add(addComma(uiBillRireki.getKazeiGaku()));
                 tmpList.add(addComma(uiBillRireki.getTaxKei()));
                 tmpList.add(addComma(uiBillRireki.getHizeiGaku()));
            }else if(utiwakeKbn == "1" && !tenPercentList.isEmpty()) {
            	tmpList.add(addComma(uIZniRizuWakeTen.gettaxkbn1_kazei().add(uIZniRizuWakeGen.gettaxkbn2_kazei()).add(uIZniRizuWakeKei.gettaxkbn3_kazei()).add(uIZniRizuWakeTen.gettaxkbn1_uri()).add(uIZniRizuWakeGen.gettaxkbn2_uri()).add(uIZniRizuWakeKei.gettaxkbn3_uri())));
                tmpList.add(addComma(uIZniRizuWakeTen.gettaxkbn1_inv().add(uIZniRizuWakeGen.gettaxkbn2_inv()).add(uIZniRizuWakeKei.gettaxkbn3_inv())));
                tmpList.add(addComma(uIZniRizuWakeHi.gettaxkbn4_hizei()));
            }
//modify 2023/04/12 USI金 end
// modify 2023/02/07 USI範  end
            outputList.add(tmpList);

            outputList.add(karaList);
            outputList.add(karaList);
        }

        //----------------------------
        //履歴タブ部分
        //----------------------------
        for(int i=0;i<rirekiTabList.size();i++) {

            List miseList = (List)rirekiTabList.get(i);

            for(int j=0; j<miseList.size(); j++) {

                UIBillRireki uiBillRireki = (UIBillRireki)miseList.get(j);

                if(j==0){
                    tmpList = new ArrayList();
                    tmpList.add(uiBillRireki.getUrikakeCd());             //売掛先コード
                    tmpList.add(uiBillRireki.getOnerNameKj() + " 殿");    //売掛先名称
                    outputList.add(tmpList);

                    tmpList = new ArrayList();
                    tmpList.add("種別");
                    tmpList.add("日付");
                    tmpList.add("繰越残高");
                    tmpList.add("今回お買上額");
                    tmpList.add("ご請求額");
                    tmpList.add("ご入金額");
                    tmpList.add("残高");
                    outputList.add(tmpList);
                }

                tmpList = new ArrayList();

                //種別
                if (uiBillRireki.getSeikyuNyukinKbn().equals("1")) {
                    tmpList.add("ご請求");
                } else if(uiBillRireki.getSeikyuNyukinKbn().equals("2")) {
                    tmpList.add("ご入金");
                }
                //日付(請求入金日付 + 発効日)
                tmpList.add(uiBillRireki.getSeikyuNyukinDt() + uiBillRireki.getHakkoDt());

                tmpList.add(addComma(uiBillRireki.getKuriZan()));       //繰越残高
                tmpList.add(addComma(uiBillRireki.getKaiageGaku()));    //買上額
                tmpList.add(addComma(uiBillRireki.getSeikyuKon()));     //請求額

                //ご入金額
                if (uiBillRireki.getSeikyuNyukinKbn().equals("1")) {
                    tmpList.add("0");
                } else if(uiBillRireki.getSeikyuNyukinKbn().equals("2")) {
                    tmpList.add(addComma(uiBillRireki.getNyukinGaku()));
                }

                //残高
                tmpList.add(addComma(uiBillRireki.getZandaka()));
                outputList.add(tmpList);
            }
            outputList.add(karaList);

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
    private String addComma(BigDecimal num){

        //返却用
        String ret;
        NumericFormatter formatter = new NumericFormatter();
        ret = formatter.format(num.toString(),"##,###,###,##0");
        return ret;
    }
}
