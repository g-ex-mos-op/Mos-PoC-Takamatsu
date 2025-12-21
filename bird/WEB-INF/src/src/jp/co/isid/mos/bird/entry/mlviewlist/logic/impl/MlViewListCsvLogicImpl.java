/*
 * 作成日: 2006/04/25
 */
package jp.co.isid.mos.bird.entry.mlviewlist.logic.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlviewlist.dto.MlViewListDto;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlEntryCsvInfo;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.GetMlEntryListLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.MaskFormatter;

/**
 * マスタライセンス申込状況確認 CSVロジック
 * @author Aspac
 * 修正：2006/09/12
 * 　　　担当者、送付先の住所を１～３に分割して出力するよう変更
 */
public class MlViewListCsvLogicImpl implements CsvOutputLogic {
    /* ロジックID */
    public static final String LOGIC_ID = "BEN009L03";

    //能力、筆記、面接の受験ステータス
    private static final String STATUS_JYUKEN   = "0";
    private static final String STATUS_MENJYO   = "1";
    private static final String STATUS_FUKA     = "2";
    private static final String STATUS_MIJYUKEN = "3";

    //CSVファイル名称
    private static final String CSV_FILE_NAME = "LISENCESTATE.csv";

    //
    private GetMlEntryListLogic getMlEntryListLogic;

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return CSV_FILE_NAME;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

        // DTO
        MlViewListDto dto = (MlViewListDto) csvOutputDto;

        // 検索処理
        dto.setEntryList(
                getGetMlEntryListLogic()
                         .execute("CSV",//CSV出力のため"CSV"指定
                                  dto.getSysDate(),
                                  dto.getEntryCd(),
                                  dto.getEntryYear(),
                                  dto.getEntryKai(),
                                  dto.getSearchSelectFlg(),
                                  dto.getSearchSibuCd(),
                                  dto.getSearchOnerCd(),
                                  dto.getSearchMiseCd())
        );


        // CSV出力用List
        List listCSV = new ArrayList();

        try {
            // ヘッダ部
            makeHeader(dto, listCSV);

            for (Iterator ite = dto.getEntryList().iterator(); ite.hasNext();) {

                UIMlEntryCsvInfo uiMlEntryCsvInfo = (UIMlEntryCsvInfo) ite.next();

                //１行分のリスト作成
                List listData = new ArrayList();

                //受験番号フォーマット用
                CodeFormatter codeFormatter = new CodeFormatter(5, "");
                codeFormatter.setFormatPattern("00000");
                //郵便番号用フォーマッター
                MaskFormatter zipFormatter = new MaskFormatter("###-####");

                listData.add(uiMlEntryCsvInfo.getOnerCd());
                listData.add(uiMlEntryCsvInfo.getOnerNameKj());
                listData.add(uiMlEntryCsvInfo.getSibuCd());
                listData.add(uiMlEntryCsvInfo.getSibuName());
                listData.add(uiMlEntryCsvInfo.getMiseCd());
                listData.add(uiMlEntryCsvInfo.getMiseNameKj());
                listData.add(uiMlEntryCsvInfo.getEntryName());
                listData.add(uiMlEntryCsvInfo.getTotalLastYear());
                listData.add(uiMlEntryCsvInfo.getTotalLastKai());
                listData.add(uiMlEntryCsvInfo.getEntryCount());
                listData.add(codeFormatter.format(uiMlEntryCsvInfo.getExamNo(), true));
                listData.add(uiMlEntryCsvInfo.getJob());
                listData.add(uiMlEntryCsvInfo.getNameTanto());
                listData.add(uiMlEntryCsvInfo.getTelTanto());
                //2006/08/30「000-0000」フォーマット
                //listData.add(uiMlEntryCsvInfo.getZipTanto());
                listData.add(zipFormatter.format(uiMlEntryCsvInfo.getZipTanto(), true));
                listData.add(uiMlEntryCsvInfo.getAddressTanto1());
                listData.add(uiMlEntryCsvInfo.getAddressTanto2());
                listData.add(uiMlEntryCsvInfo.getAddressTanto3());
                listData.add(uiMlEntryCsvInfo.getSoufuNameTanto());
                listData.add(uiMlEntryCsvInfo.getNameHokoku());
                listData.add(uiMlEntryCsvInfo.getTelHokoku());
                //2006/08/30「000-0000」フォーマット
                //listData.add(uiMlEntryCsvInfo.getZipHokoku());
                listData.add(zipFormatter.format(uiMlEntryCsvInfo.getZipHokoku(), true));
                listData.add(uiMlEntryCsvInfo.getAddressHokoku1());
                listData.add(uiMlEntryCsvInfo.getAddressHokoku2());
                listData.add(uiMlEntryCsvInfo.getAddressHokoku3());
                listData.add(uiMlEntryCsvInfo.getSoufuNameHokoku());
                listData.add(uiMlEntryCsvInfo.getStaffLNameKj());
                listData.add(uiMlEntryCsvInfo.getStaffFNameKj());
                listData.add(uiMlEntryCsvInfo.getStaffLNameKna());
                listData.add(uiMlEntryCsvInfo.getStaffFNameKna());
                listData.add(uiMlEntryCsvInfo.getSex());

                DateFormatter dateFormatter = new DateFormatter();
                listData.add(uiMlEntryCsvInfo.getBirthday() == null ? ""
						: dateFormatter.format(uiMlEntryCsvInfo.getBirthday(), DateFormatter.PATTERN_NORMAL,
								DateFormatter.PATTERN_SLASH, false));
                listData.add(uiMlEntryCsvInfo.getEmpExpYear());
                listData.add(uiMlEntryCsvInfo.getEmpExpMonth());
                listData.add(uiMlEntryCsvInfo.getPaExpYear());
                listData.add(uiMlEntryCsvInfo.getPaExpMonth());
                listData.add(uiMlEntryCsvInfo.getCourseStatusName());
                listData.add(uiMlEntryCsvInfo.getCompleCourseDt());
                listData.add(uiMlEntryCsvInfo.getCompleCourseCd());
                listData.add(uiMlEntryCsvInfo.getCompleCourseName());
                listData.add(uiMlEntryCsvInfo.getEntry_placeCd());
                listData.add(uiMlEntryCsvInfo.getNote());
                listData.add(getStatusName(uiMlEntryCsvInfo.getAbilityChk()));
                listData.add(getStatusName(uiMlEntryCsvInfo.getExamChk()));
                listData.add(getStatusName(uiMlEntryCsvInfo.getInterviewChk()));
                listData.add(uiMlEntryCsvInfo.getLastUser());
                listData.add(uiMlEntryCsvInfo.getLastTmsp());
                listData.add(uiMlEntryCsvInfo.getKeiyakuEnd());

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


    /**
     * CSVヘッダー作成
     * @param dto
     * @param listCsv
     */
    private void makeHeader(MlViewListDto dto, List listCsv) {

        List listCompany = new ArrayList();
        List listSearch  = new ArrayList();
        List listYear    = new ArrayList();
        List listSpace   = new ArrayList();
        List listKoumoku = new ArrayList();

        try{

            //会社
            listCompany.add("会社：");
            listCompany.add("モスフードサービス");

            UIMlEntryCsvInfo entityCsvInfo = (UIMlEntryCsvInfo) dto.getEntryList().get(0);
            //対象条件
            if(dto.getSearchSelectFlg().equals("0")){
                listSearch.add("対象条件 支部：");
                listSearch.add(dto.getSearchSibuCd() + " " + entityCsvInfo.getSibuName());
            }
            else if(dto.getSearchSelectFlg().equals("1")){
                listSearch.add("対象条件 オーナー：");
                listSearch.add(dto.getSearchOnerCd() + " " + entityCsvInfo.getOnerNameKj());
            }
            else if(dto.getSearchSelectFlg().equals("2")){
                listSearch.add("対象条件 店舗：");
                listSearch.add(dto.getSearchMiseCd() + " " + entityCsvInfo.getMiseNameKj());
            }
            else if(dto.getSearchSelectFlg().equals("3")){
                listSearch.add("対象条件：");
                listSearch.add("全オーナー");
            }

            //年度指定
            listYear.add("年度指定：");
            listYear.add(dto.getEntryYear());

            // 項目ヘッダー
            listKoumoku.add("オーナーコード");
            listKoumoku.add("オーナー名称");
            listKoumoku.add("支部取込コード");
            listKoumoku.add("支部取込名称");
            listKoumoku.add("店コード");
            listKoumoku.add("店名称");
            listKoumoku.add("エントリー");
            listKoumoku.add("初回エントリー年");
            listKoumoku.add("初回エントリー回");
            listKoumoku.add("総受験回数");
            listKoumoku.add("受験番号");
            listKoumoku.add("職位");
            listKoumoku.add("担当者氏名");
            listKoumoku.add("担当者電話番号");
            listKoumoku.add("担当者郵便番号");
            listKoumoku.add("担当者住所１");
            listKoumoku.add("担当者住所２");
            listKoumoku.add("担当者住所３");
            listKoumoku.add("担当者送付先名");
            listKoumoku.add("結果報告先担当者氏名");
            listKoumoku.add("結果報告先電話番号");
            listKoumoku.add("結果報告先郵便番号");
            listKoumoku.add("結果報告先住所１");
            listKoumoku.add("結果報告先住所２");
            listKoumoku.add("結果報告先住所３");
            listKoumoku.add("結果報告先送付先名");
            listKoumoku.add("スタッフ氏（漢字）");
            listKoumoku.add("スタッフ名（漢字）");
            listKoumoku.add("スタッフ（カナ）");
            listKoumoku.add("スタッフ名（カナ）");
            listKoumoku.add("性別");
            listKoumoku.add("生年月日");
            listKoumoku.add("店舗経験　社員歴年数");
            listKoumoku.add("店舗経験　社員歴月数");
            listKoumoku.add("店舗経験　アルバイト歴年数");
            listKoumoku.add("店舗経験　アルバイト歴月数");
            listKoumoku.add("修了(予定)コース状況");
            listKoumoku.add("修了(予定)コース修了年月");
            listKoumoku.add("修了(予定)コースコード");
            listKoumoku.add("修了(予定)コース名称");
            listKoumoku.add("受験希望地コード");
            listKoumoku.add("備考");
            listKoumoku.add("今回能力");
            listKoumoku.add("今回筆記");
            listKoumoku.add("今回面接");
            listKoumoku.add("更新者");
            listKoumoku.add("更新日");
            listKoumoku.add("オーナー契約終了日");

            listCsv.add(listCompany);
            listCsv.add(listSearch);
            listCsv.add(listYear);
            listCsv.add(listSpace);
            listCsv.add(listKoumoku);

        } catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }
    }

    /**
     * 能力、筆記、面接の申込状況名称取得処理
     * @return
     */
    private String getStatusName(String status) {
        String statusName = "";

        if (STATUS_JYUKEN.equals(status)) {
            statusName = "受験";
        }
        else if (STATUS_MENJYO.equals(status)) {
            statusName = "免除";
        }
        else if (STATUS_FUKA.equals(status)) {
            statusName = "受験不可";
        }
        else if (STATUS_MIJYUKEN.equals(status)) {
            statusName = "未受験";
        }
        return statusName;
    }

	public GetMlEntryListLogic getGetMlEntryListLogic() {
		return getMlEntryListLogic;
	}
	public void setGetMlEntryListLogic(GetMlEntryListLogic getMlEntryListLogic) {
		this.getMlEntryListLogic = getMlEntryListLogic;
	}
}
