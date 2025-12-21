/*
 * 作成日: 2006/04/25
 */
package jp.co.isid.mos.bird.entry.hanyoviewlist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoviewlist.dto.HanyoViewListDto;
import jp.co.isid.mos.bird.entry.hanyoviewlist.entity.UIHanyoEntryCsvInfo;
import jp.co.isid.mos.bird.entry.hanyoviewlist.logic.GetHanyoEntryListLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Converter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.MaskFormatter;

/**
 * 研修(出張/更新)申込状況確認 CSVロジック
 * @author Nakajima(ASPAC)
 */
public class HanyoViewListCsvLogicImpl implements CsvOutputLogic {

    private static final int OUTPUT_NUMBER = 12;

    //private UIPLRCDataInfoDao uiPlRcDataInfoDao;
    private GetHanyoEntryListLogic getHanyoEntryListLogic;

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {

	    // DTO
        HanyoViewListDto dto = (HanyoViewListDto) csvOutputDto;

        String fileName = "";
        if(dto.getEntryCd().equals("05")){
            //出張研修
            fileName = "TRAVELSHOKAI.csv";
        }else{
            //更新研修
            fileName = "KOUSINSHOKAI.csv";
        }

		return fileName;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        //郵便番号用フォーマッター
        MaskFormatter zipFormatter = new MaskFormatter("###-####");

        // DTO
        HanyoViewListDto dto = (HanyoViewListDto) csvOutputDto;

        // 検索処理
        dto.setHanyoEntryCsvList(
                getGetHanyoEntryListLogic()
                         .execute(dto.getSysDate(),
                                  dto.getEntryCd(),
                                  dto.getEntryYear(),
                                  dto.getEntryKai(),
                                  "CSV")
        );


        // CSV出力用List
        List listCSV = new ArrayList();

        try {
            // ヘッダ部
            makeHeader(dto, listCSV);


            for (Iterator ite = dto.getHanyoEntryCsvList().iterator(); ite.hasNext();) {
                //Listからentityへキャストする
                UIHanyoEntryCsvInfo uiHanyoEntryCsvInfo = (UIHanyoEntryCsvInfo) ite.next();
                //１行分のリスト作成
                List listData = new ArrayList();

                //オーナーコード
                if(uiHanyoEntryCsvInfo.getOnerCd() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getOnerCd().trim());
                }

                //オーナー名称
                if(uiHanyoEntryCsvInfo.getOnerNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getOnerNameKj().trim());
                }

//20070117 マスタライセンスPh4対応---start---
//                //支部コード
//                if(uiHanyoEntryCsvInfo.getSibuCd() == null){
//                    listData.add("");
//                }else{
//                    listData.add(uiHanyoEntryCsvInfo.getSibuCd().trim());
//                }
//
//                //支部名称
//                if(uiHanyoEntryCsvInfo.getSibuName()) == null){
//                    listData.add("");
//                }else{
//                    listData.add(uiHanyoEntryCsvInfo.getSibuName().trim()));
//                }

                //支部取込コード
                if(uiHanyoEntryCsvInfo.getSibuTorikomiCd() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getSibuTorikomiCd().trim());
                }

                //支部取込名称
                if(uiHanyoEntryCsvInfo.getSibuTorikomiName() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getSibuTorikomiName().trim());
                }
//20070117 マスタライセンスPh4対応--- end ---

                //店コード
                if(uiHanyoEntryCsvInfo.getMiseCd() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getMiseCd().trim());
                }

                //店名称
                if(uiHanyoEntryCsvInfo.getMiseNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getMiseNameKj().trim());
                }

                //責任社名
                if(uiHanyoEntryCsvInfo.getSekininshaName() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getSekininshaName().trim());
                }

                //職位(責任者)
                if(uiHanyoEntryCsvInfo.getSekininshaJob() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getSekininshaJob().trim());
                }

                //氏名漢字(姓)
                if(uiHanyoEntryCsvInfo.getStaffSiKj() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getStaffSiKj().trim());
                }

                //氏名漢字(名)
                if(uiHanyoEntryCsvInfo.getStaffNaKj() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getStaffNaKj().trim());
                }

                //氏名カナ(姓)
                if(uiHanyoEntryCsvInfo.getStaffSiKna() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getStaffSiKna().trim());
                }

                //氏名カナ(名)
                if(uiHanyoEntryCsvInfo.getStaffNaKna() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getStaffNaKna().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getSex() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getSex().trim());
                }

                //誕生日
                String birthday = uiHanyoEntryCsvInfo.getBirthday();
                DateFormatter dateFormatter = new DateFormatter();
                listData.add(birthday == null?"":dateFormatter.format(birthday
        				,DateFormatter.PATTERN_NORMAL
        				,DateFormatter.PATTERN_SLASH
        				,false));

                //職位
                if(uiHanyoEntryCsvInfo.getJob() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getJob().trim());
                }

                //結果先名称
                if(uiHanyoEntryCsvInfo.getKekkaSakiName() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getKekkaSakiName().trim());
                }

                //郵便番号
                if(uiHanyoEntryCsvInfo.getKekkaSakiZip() == null){
                    listData.add("");
                }else{
                    listData.add(zipFormatter.format(uiHanyoEntryCsvInfo.getKekkaSakiZip(), true));
                }

                //住所１
                if(uiHanyoEntryCsvInfo.getKekkaSakiAddress1() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getKekkaSakiAddress1().trim());
                }

                //住所２
                if(uiHanyoEntryCsvInfo.getKekkaSakiAddress2() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getKekkaSakiAddress2().trim());
                }

                //住所３
                if(uiHanyoEntryCsvInfo.getKekkaSakiAddress3() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getKekkaSakiAddress3().trim());
                }

                //社員歴年
                if(uiHanyoEntryCsvInfo.getEmpExpYear() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getEmpExpYear().trim());
                }

                //社員歴月
                if(uiHanyoEntryCsvInfo.getEmpExpMonth() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getEmpExpMonth().trim());
                }

                //パート歴年
                if(uiHanyoEntryCsvInfo.getPaExpYear() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getPaExpYear().trim());
                }

                //パート歴月
                if(uiHanyoEntryCsvInfo.getPaExpMonth() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getPaExpMonth().trim());
                }

                //区分
                if(uiHanyoEntryCsvInfo.getGuideKbn() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getGuideKbn().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getGuideName() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getGuideName().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getGuideZip() == null){
                    listData.add("");
                }else{
                    listData.add(zipFormatter.format(uiHanyoEntryCsvInfo.getGuideZip(), true));
                }

                //
                if(uiHanyoEntryCsvInfo.getGuideAdrs1() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getGuideAdrs1().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getGuideAdrs2() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getGuideAdrs2().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getGuideAdrs3() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getGuideAdrs3());
                }

                //
                if(uiHanyoEntryCsvInfo.getLastUser() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getLastUser().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getLastUserName() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getLastUserName().trim());
                }

                //
                if(uiHanyoEntryCsvInfo.getKeiyakuEnd() == null){
                    listData.add("");
                }else{
                    listData.add(uiHanyoEntryCsvInfo.getKeiyakuEnd().trim());
                }


                listCSV.add(listData);

            }

        }
        catch (Exception ex) {
        	throw new FtlSystemException("CSV作成");
        }
		return listCSV;
	}

    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
	}

    /* ヘッダ部 */
    private void makeHeader(HanyoViewListDto dto, List listCsv) {

        List listKaisaibi  = new ArrayList();
        List listKaisaimei = new ArrayList();
        List listKoumoku   = new ArrayList();
        List listSpace     = new ArrayList();

        try{
            // 開催日
            listKaisaibi.add("開催日：");
            listKaisaibi.add(Converter.dateToString("MM/dd", Converter.stringToDate("yyyyMMdd", dto.getFromDt()))
                    + "(" + Converter.dateToString("E", Converter.stringToDate("yyyyMMdd", dto.getFromDt())) + ")");

            // 開催名
            listKaisaimei.add("開催名：");
            listKaisaimei.add(dto.getEntryTitle());

            // 項目ヘッダー
            listKoumoku.add("オーナーコード");
            listKoumoku.add("オーナー名称");

//20070117 マスタライセンスPh4対応---start---
            //listKoumoku.add("支部コード");
            //listKoumoku.add("支部名称");
            listKoumoku.add("支部取込コード");
            listKoumoku.add("支部取込名称");
//20070117 マスタライセンスPh4対応--- end ---

            listKoumoku.add("店コード");
            listKoumoku.add("店名称");
            listKoumoku.add("申込責任者名称");
            listKoumoku.add("申込責任者職位");
            listKoumoku.add("スタッフ氏(漢字)");
            listKoumoku.add("スタッフ名(漢字)");
            listKoumoku.add("スタッフ氏(フリガナ)");
            listKoumoku.add("スタッフ名(フリガナ)");
            listKoumoku.add("性別");
            listKoumoku.add("生年月日");
            listKoumoku.add("職位");
            listKoumoku.add("結果報告先名");
            listKoumoku.add("結果報告先郵便番号");
            listKoumoku.add("結果報告先住所１");
            listKoumoku.add("結果報告先住所２");
            listKoumoku.add("結果報告先住所３");
            listKoumoku.add("店舗経験 社員歴年数");
            listKoumoku.add("店舗経験 社員歴月数");
            listKoumoku.add("店舗経験 パート・アルバイト歴年数");
            listKoumoku.add("店舗経験 パート・アルバイト歴月数");
            listKoumoku.add("受講案内送付先区分");
            listKoumoku.add("受講案内送付先名");
            listKoumoku.add("受講案内送付先郵便番号");
            listKoumoku.add("受講案内送付先住所１");
            listKoumoku.add("受講案内送付先住所２");
            listKoumoku.add("受講案内送付先住所３");
            listKoumoku.add("更新者ID");
            listKoumoku.add("更新者名称");
            listKoumoku.add("オーナー契約終了日");

            listCsv.add(listKaisaibi);
            listCsv.add(listKaisaimei);
            listCsv.add(listSpace);
            listCsv.add(listSpace);
            listCsv.add(listKoumoku);

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }

    }

	public GetHanyoEntryListLogic getGetHanyoEntryListLogic() {
		return getHanyoEntryListLogic;
	}
	public void setGetHanyoEntryListLogic(GetHanyoEntryListLogic getHanyoEntryListLogic) {
		this.getHanyoEntryListLogic = getHanyoEntryListLogic;
	}
}
