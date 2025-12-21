/*
 * 作成日: 2006/04/25
 */
package jp.co.isid.mos.bird.entry.basicviewlist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.basicviewlist.dto.BasicViewListDto;
import jp.co.isid.mos.bird.entry.basicviewlist.entity.UIBasicEntryCsvInfo;
import jp.co.isid.mos.bird.entry.basicviewlist.logic.GetBasicEntryListLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Converter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.MaskFormatter;

/**
 * ベーシック研修状況確認 CSVロジック
 * @author Nakajima(ASPAC)
 *
 * 更新日:2012/11/26 ASPAC xkinu trim対応
 */
public class BasicViewListCsvLogicImpl implements CsvOutputLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN003L03";

    //private UIPLRCDataInfoDao uiPlRcDataInfoDao;
    private GetBasicEntryListLogic getBasicEntryListLogic;

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {

        String fileName = "BASICSHOKAI.csv";

		return fileName;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

        // DTO
        BasicViewListDto dto = (BasicViewListDto) csvOutputDto;

        // 検索処理
        dto.setBasicEntryCsvList(
                getGetBasicEntryListLogic()
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

            //郵便番号用フォーマッター
            MaskFormatter zipFormatter = new MaskFormatter("###-####");

            for (Iterator ite = dto.getBasicEntryCsvList().iterator(); ite.hasNext();) {
                //Listからentityへキャストする
                UIBasicEntryCsvInfo entity = (UIBasicEntryCsvInfo) ite.next();
                //１行分のリスト作成
                List listData = new ArrayList();
                listData.add(rtrim(entity.getOnerCd()));
                listData.add(rtrim(entity.getOnerNameKj()));

//--20070116 マスタライセンスPh4対応---start---
                //listData.add(entity.getSibuCd());
                //listData.add(rtrim(entity.getSibuName()));

                listData.add(rtrim(entity.getSibuTorikomiCd()));
                listData.add(rtrim(entity.getSibuTorikomiName()));
//--20070116 マスタライセンスPh4対応--- end ---

                listData.add(rtrim(entity.getMiseCd()));
                listData.add(convertMS932toUnicode(entity.getMiseNameKj()));
                listData.add(convertMS932toUnicode(entity.getSekininshaName()));
                listData.add(convertMS932toUnicode(entity.getSekininshaJob()));
                listData.add(convertMS932toUnicode(entity.getStaffSiKj()));
                listData.add(convertMS932toUnicode(entity.getStaffNaKj()));
                listData.add(convertMS932toUnicode(entity.getStaffSiKna()));
                listData.add(convertMS932toUnicode(entity.getStaffNaKna()));
                listData.add(convertMS932toUnicode(entity.getSex()));
                String birthday = entity.getBirthday();
                DateFormatter dateFormatter = new DateFormatter();
                listData.add(birthday == null?"":dateFormatter.format(birthday
        				,DateFormatter.PATTERN_NORMAL
        				,DateFormatter.PATTERN_SLASH
        				,false));
                listData.add(convertMS932toUnicode(entity.getJob()));
                listData.add(convertMS932toUnicode(entity.getKekkaSakiName()));
                listData.add(zipFormatter.format(entity.getKekkaSakiZip(), true));
                listData.add(convertMS932toUnicode(entity.getKekkaSakiAddress1()));
                listData.add(convertMS932toUnicode(entity.getKekkaSakiAddress2()));
                listData.add(convertMS932toUnicode(entity.getKekkaSakiAddress3()));
                listData.add(rtrim(entity.getEmpExpYear()));
                listData.add(rtrim(entity.getEmpExpMonth()));
                listData.add(rtrim(entity.getPaExpYear()));
                listData.add(rtrim(entity.getPaExpMonth()));
                listData.add(convertMS932toUnicode(entity.getGuideKbn()));
                listData.add(convertMS932toUnicode(entity.getGuideName()));
                listData.add(zipFormatter.format(entity.getGuideZip(), true));
                listData.add(convertMS932toUnicode(entity.getGuideAdrs1()));
                listData.add(convertMS932toUnicode(entity.getGuideAdrs2()));
                listData.add(convertMS932toUnicode(entity.getGuideAdrs3()));
                listData.add(convertMS932toUnicode(entity.getOtherFlg1()));
                listData.add(convertMS932toUnicode(entity.getOtherFlg2()));
                listData.add(convertMS932toUnicode(entity.getOtherFlg3()));
                listData.add(convertMS932toUnicode(entity.getBossName()));
                listData.add(convertMS932toUnicode(entity.getBossGroup()));
                listData.add(convertMS932toUnicode(entity.getBossJobType()));
                listData.add(convertMS932toUnicode(entity.getBossComment()));
                listData.add(convertMS932toUnicode(entity.getLastUser()));
                listData.add(convertMS932toUnicode(entity.getLastUserName()));
                listData.add(rtrim(entity.getKeiyakuEnd()));

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
    private void makeHeader(BasicViewListDto dto, List listCsv) {

        List listKaisaibi      = new ArrayList();
        List listRintenKaisibi = new ArrayList();
        List listKoumoku       = new ArrayList();
        List listSpace         = new ArrayList();

        try{

            // 開催日
            listKaisaibi.add("開催日：");
            listKaisaibi.add(Converter.dateToString("MM/dd", Converter.stringToDate("yyyyMMdd", dto.getBasicDtFrom()))
                     + "(" + Converter.dateToString("E", Converter.stringToDate("yyyyMMdd", dto.getBasicDtFrom())) + ")");

            // 臨店実習開始日
            listRintenKaisibi.add("臨店実習開始日：");
            listRintenKaisibi.add(Converter.dateToString("MM/dd", Converter.stringToDate("yyyyMMdd", dto.getRintenDtFrom()))
                    + "(" + Converter.dateToString("E", Converter.stringToDate("yyyyMMdd", dto.getRintenDtFrom())) + ")");

            // 項目ヘッダー
            listKoumoku.add("オーナーコード");
            listKoumoku.add("オーナー名称");

//--20070116 マスタライセンスPh4対応---start---
            //listKoumoku.add("支部コード");
            //listKoumoku.add("支部名称");

            listKoumoku.add("支部取込コード");
            listKoumoku.add("支部取込名称");
//--20070116 マスタライセンスPh4対応--- end ---

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
            listKoumoku.add("臨店実習コース");
            listKoumoku.add("宿泊先タイプ");
            listKoumoku.add("面接");
            listKoumoku.add("上司名称");
            listKoumoku.add("上司所属");
            listKoumoku.add("上司職位");
            listKoumoku.add("上司コメント");
            listKoumoku.add("更新者ID");
            listKoumoku.add("更新者名称");
            listKoumoku.add("オーナー契約終了日");

            listCsv.add(listKaisaibi);
            listCsv.add(listRintenKaisibi);
            listCsv.add(listSpace);
            listCsv.add(listSpace);
            listCsv.add(listKoumoku);

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }

    }
    /**
     * スペーストリム処理後、Windowsクライアント(MS932)からの入力文字列をUnicode文字列に変換した値を戻します。
     * nullの場合は空("")を戻します。
     *
     * 作成日:2012/11/26 ASAPC xkinu
     * @return String トリムされた値を戻します。
     */
    private String convertMS932toUnicode(String value) {
   		return rtrim(value);
    }
    /**
     * 全半角スペーストリム処理
     *
     * nullの場合は空("")を戻します。
     * 作成日:2012/11/26 ASAPC xkinu
     * @return String トリムされた値を戻します。
     */
    private String rtrim(String value) {
    	if(value == null) {
        	return "";
    	}
		return CommonUtil.rAllSpaceTrim(value);
    }
	public GetBasicEntryListLogic getGetBasicEntryListLogic() {
		return getBasicEntryListLogic;
	}
	public void setGetBasicEntryListLogic(GetBasicEntryListLogic getBasicEntryListLogic) {
		this.getBasicEntryListLogic = getBasicEntryListLogic;
	}
}
