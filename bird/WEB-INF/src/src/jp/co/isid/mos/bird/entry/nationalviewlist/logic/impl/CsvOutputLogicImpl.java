/*
 * 作成日: 2006/12/04
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.logic.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.entry.common.code.ConditionKbn;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.entry.nationalviewlist.dao.UICsvStatusListDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.dao.UINatiViewEntrySelectInfoDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListReqDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UICsvStatusList;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UINatiViewEntrySelectInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 【CSV出力情報取得】ロジック
 *
 * @author xkinu
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {

    /** ロジックID */
    public static final String LOGIC_ID = NationalViewListUtil.SCREEN_ID+"L03";
    /** DAO【CSV申込状況一覧】 */
    private UICsvStatusListDao nationalViewListUICsvStatusListDao;
    private UINatiViewEntrySelectInfoDao  natiViewEntrySelectInfoDao;

    /** オプショナル（宿泊）のセレクションインデックス */
    private static final Map<String, String> SHUKUHAKU_MAP = new Hashtable<String, String>();

    {
        {
            SHUKUHAKU_MAP.put("01", "");
            SHUKUHAKU_MAP.put("02", "");
            SHUKUHAKU_MAP.put("03", "");
            SHUKUHAKU_MAP.put("04", "");
            SHUKUHAKU_MAP.put("11", "");
            SHUKUHAKU_MAP.put("12", "");
            SHUKUHAKU_MAP.put("13", "");
            SHUKUHAKU_MAP.put("14", "");
            SHUKUHAKU_MAP.put("15", "");
            SHUKUHAKU_MAP.put("16", "");
        }
    };

    /** オプショナル（ツアー関連）のセレクションインデックス */
    private static final Map<String, String> TOUR_MAP = new Hashtable<String, String>();

    {
        {
            TOUR_MAP.put("05", "");
            TOUR_MAP.put("06", "");
            TOUR_MAP.put("07", "");
            TOUR_MAP.put("08", "");
            TOUR_MAP.put("09", "");
            TOUR_MAP.put("10", "");
        }
    };

    /** オプショナル（その他）のセレクションインデックス */
    private static final Map<String, String> SONOTA_MAP = new Hashtable<String, String>();

    {
        {
            SONOTA_MAP.put("21", "");
            SONOTA_MAP.put("22", "");
            SONOTA_MAP.put("23", "");
            SONOTA_MAP.put("24", "");
            SONOTA_MAP.put("25", "");
            SONOTA_MAP.put("26", "");
            SONOTA_MAP.put("27", "");
            SONOTA_MAP.put("28", "");
            SONOTA_MAP.put("29", "");
            SONOTA_MAP.put("30", "");
        }
    };


    /**
     * ロジック【申込状況検索】
     */
    private SearchLogic nationalViewListSearchLogic;

   /**
     * @return nationalViewListSearchLogic を戻します。
     */
    public SearchLogic getNationalViewListSearchLogic() {
        return nationalViewListSearchLogic;
    }

    /**
     * @param nationalViewListSearchLogic 設定する nationalViewListSearchLogic。
     */
    public void setNationalViewListSearchLogic(
            SearchLogic nationalViewListSearchLogic) {
        this.nationalViewListSearchLogic = nationalViewListSearchLogic;
    }

/**
     * ファイル名取得処理
     */
    public String getFileName(CsvOutputDto csvOutputDto) {

        // DTO
        NationalViewListReqDto dto = (NationalViewListReqDto) csvOutputDto;
        UIStatusInfo entity = dto.getEntityStatusInfo();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        String kbn = dto.getTargetKbn();
        String kbnKigou = "";
        //１．パラメーター.DTO【申込状況確認】.区分＝’参加’の場合以下の名前をリターンする。
        if (ConditionKbn.VALUE_ENTRY.equals(kbn)) {
            kbnKigou = "_SANKA";
        }
        //２．パラメーター.DTO【申込状況確認】.区分＝’不参加’の場合以下の名前をリターンする。
        else if (ConditionKbn.VALUE_NOTENTRY.equals(kbn)) {
            kbnKigou = "_FUSANKA";
        }
        //３．パラメーター.DTO【申込状況確認】.区分＝’未登録’の場合以下の名前をリターンする。
        else if (ConditionKbn.VALUE_MITOUROKU.equals(kbn)) {
            kbnKigou = "_MITOUROKU";
        }
        else {

        }
        String fileName = "NATIONAL"+ kbnKigou + entryYear + entryKai + ".csv";

        return fileName;

    }

    /**
     * CSV出力メイン処理
     *
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        //DTO
        NationalViewListReqDto dto = (NationalViewListReqDto) csvOutputDto;
        //ボタン押下時の条件項目値を現行ウィンドウIDをキーに保持を行う。
//        dto.holdJokenParam();

        String userId = dto.getUserId();
        boolean limit = dto.isLimit();
        String sysDate = dto.getSysDate();
        UIStatusInfo entityCourse = dto.getEntityStatusInfo();
        String entryCd = entityCourse.getEntryCd();
        String entryYear = entityCourse.getEntryYear();
        String entryKai = entityCourse.getEntryKai();
        String companyCd = dto.getTargetCompanyCd();
        String taishouJoken = dto.getCsvTaishoJoken();
        String sibuCd = dto.getCsvSibuCd();
        String svCd = dto.getCsvSvCd();

        //区分
        String attendKbn = dto.getTargetKbn();
        //１．変数[[CSV申込状況一覧]]を生成する。
        List listCsvOutputData = null;
        //２．パラメーター.DTO【申込状況確認】.区分＝’未登録’の場合、下記の処理を行う。
        if(ConditionKbn.VALUE_MITOUROKU.equals(attendKbn)){
            //２－１．DAO【CSV申込状況一覧】.未登録オーナー検索を実行し、処理１の変数[[CSV申込状況一覧]]へ代入する。
            listCsvOutputData = getNationalViewListUICsvStatusListDao().selectMitouroku(
                    userId, limit, entryCd, entryYear, entryKai, sysDate
                , companyCd, taishouJoken, sibuCd, svCd);
        }
        //３．パラメーター.DTO【申込状況確認】.区分＝’参加’or ’不参加’の場合、下記の処理を行う。
        else{
            //３－１．DAO【CSV申込状況一覧】.検索を実行し、処理１の変数[[CSV申込状況一覧]]へ代入する。
            listCsvOutputData = getNationalViewListUICsvStatusListDao().select(
                    userId, limit, entryCd, entryYear, entryKai, sysDate
                , companyCd, taishouJoken, sibuCd, svCd, attendKbn);
        }
        //４．該当データが存在しない場合、下記のExceptionを発生させる。
        if (listCsvOutputData == null || listCsvOutputData.size() == 0) {
            //MSG【E0102】’該当データーがありません。’
             throw new NoResultException();
        }
        //５．[[CSV出力データ]]リストをインスタンスかする。
        List listCSV = new ArrayList();

        //６．CSVレイアウト　ヘッダ部を作成する。
        settingHeader(listCSV, dto);

        List natiEntrySelectInfo2 = getNatiViewEntrySelectInfoDao()
				.getNatiViewEntrySelectInfo(entryCd, entryYear, entryKai,"2");
        //７．CSVレイアウト　データ部タイトルを作成する。
        List listDataTitle = makeDataTitle(natiEntrySelectInfo2);
        listCSV.add(listDataTitle);

        try {

            //８.変数[[CSV申込状況一覧]]を基に件数分の[CSV出力データ]を生成し、[[CSV出力データ]]リストへ格納する。
            for (Iterator ite = listCsvOutputData.iterator(); ite.hasNext();) {
                //Listからentityへキャストする
                UICsvStatusList entity = (UICsvStatusList) ite.next();
                //１行分の[CSV出力データ]リスト作成
                List listData = make1RowData(entity);
                //上記で作成した[CSV出力データ]を[[CSV出力データ]]リストへ格納
                listCSV.add(listData);
            }

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成 申込状況確認：結果データ設定処理");
        }
        //１０．[[CSV出力データ]]リストをリターンする。
        return listCSV;
    }

    /**
     * CSVレイアウトヘッダ部を作成する。
     *
     * @param dto
     * @param listCsv
     */
    private void settingHeader(List listCsv, NationalViewListReqDto dto) {

        List header1 = new ArrayList();
        List header2 = new ArrayList();
        List header3 = new ArrayList();
        List header4 = new ArrayList();
        String attendKbn = dto.getTargetKbn();

        try{
            DateFormatter datefm = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "yyyy/MM/dd'('E')'");
            // 一行目（開催日、本部申込開始日、オーナー申込開始日）
            header1.add("開催日：");
            String kaisaiFrom = datefm.format(dto.getEntityStatusInfo().getKaisaiFromDt(), true);
            String kaisaiTo = datefm.format(dto.getEntityStatusInfo().getKaisaiToDt(), true);
            header1.add(convertMoji(kaisaiFrom + "　～　"+ kaisaiTo));

            header1.add("");
            header1.add("区分：");
            header1.add(ConditionKbn.getName(attendKbn, ConditionKbn.CODE_TABLE_SANKA));

            header1.add("");
            header1.add("本部申込開始日：");
            datefm.setFormatPattern(DateFormatter.PATTERN_SLASH);
            header1.add(datefm.format(dto.getEntityStatusInfo().getHonbuFromDt(), true));

            header1.add("");
            header1.add("オーナー申込開始日：");
            header1.add(datefm.format(dto.getEntityStatusInfo().getOnerFromDt(), true));


            // ニ行目（説明会名称、本部申込終了日、オーナー申込終了日）
            header2.add("イベント名称：");
            header2.add(dto.getEntityStatusInfo().getEntryTitle());

            header2.add("");
            header2.add("");
            header2.add("");

            header2.add("");
            header2.add("本部申込終了日：");
            header2.add(datefm.format(dto.getEntityStatusInfo().getHonbuToDt(), true));

            header2.add("");
            header2.add("オーナー申込終了日：");
            header2.add(datefm.format(dto.getEntityStatusInfo().getOnerToDt(), true));


            //三行目（注意書き）
            header3.add("※支部コード、支部名称はオーナーマスタに登録されている支部が表示されます。");


            //四行目（空欄）
            header4.add("");

            listCsv.add(header1);
            listCsv.add(header2);
            listCsv.add(header3);
            listCsv.add(header4);
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成 申込状況確認：ヘッダ部作成処理");
        }
    }

    /**
     * CSVレイアウトデータ部タイトルを作成する。
     *
     * @return
     */
    private List makeDataTitle(List selectionList2) {
        List list = new ArrayList();
        //五行目（データのヘッダ）
        list.add("オーナーコード");
        list.add("オーナー名称");
        list.add("SEQ_NO");
        list.add("支部コード");
        list.add("支部名称");
        list.add("店舗コード");
        list.add("店舗名称");
        list.add("参加者氏（漢字）");
        list.add("参加者名（漢字）");
        list.add("参加者氏（フリガナ）");
        list.add("参加者名（フリガナ）");
        list.add("性別");
        list.add("生年月日");
        list.add("年齢");
        list.add("職位");
        list.add("申込区分");

//        list.add("オプショナル（宿泊関連）１");
//        list.add("オプショナル（宿泊関連）２");
//        list.add("オプショナル（宿泊関連）３");
//        list.add("オプショナル（宿泊関連）４");
//        list.add("オプショナル（ツアー関連）１");
//        list.add("オプショナル（ツアー関連）２");
//        list.add("オプショナル（ツアー関連）３");
//        list.add("オプショナル（ツアー関連）４");
//        list.add("オプショナル（ツアー関連）５");
//        list.add("オプショナル（ツアー関連）６");


        //宿泊関連
        int index = 1;
        for (int i = 0; i < selectionList2.size(); i++) {
            UINatiViewEntrySelectInfo natiEntrySelectInfo = (UINatiViewEntrySelectInfo) selectionList2.get(i);
            String optionIndex = natiEntrySelectInfo.getSelectionIndex();
            String optionNm = natiEntrySelectInfo.getSelectionName();

            if (SHUKUHAKU_MAP.containsKey(optionIndex)) {
                optionNm = "オプショナル（宿泊関連）" + String.valueOf(index) + "【" + optionNm + "】";
                list.add(optionNm);
                index++;
            }
        }
        //ツアー関連
        index = 1;
        for (int i = 0; i < selectionList2.size(); i++) {
            UINatiViewEntrySelectInfo natiEntrySelectInfo = (UINatiViewEntrySelectInfo) selectionList2.get(i);
            String optionIndex = natiEntrySelectInfo.getSelectionIndex();
            String optionNm = natiEntrySelectInfo.getSelectionName();

            if (TOUR_MAP.containsKey(optionIndex)) {
                optionNm = "オプショナル（ツアー関連）" + String.valueOf(index) + "【" + optionNm + "】";
                list.add(optionNm);
                index++;
            }
        }
        //その他
        index = 1;
        for (int i = 0; i < selectionList2.size(); i++) {
            UINatiViewEntrySelectInfo natiEntrySelectInfo = (UINatiViewEntrySelectInfo) selectionList2.get(i);
            String optionIndex = natiEntrySelectInfo.getSelectionIndex();
            String optionNm = natiEntrySelectInfo.getSelectionName();

            if (SONOTA_MAP.containsKey(optionIndex)) {
                optionNm = "オプショナル（その他）" + String.valueOf(index) + "【" + optionNm + "】";
                list.add(optionNm);
                index++;
            }
        }

//        list.add("備考");
        list.add("備考1");
        list.add("備考2");
        list.add("更新者ID");
        list.add("更新者名称");
        list.add("オーナー契約終了日");
        return list;
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /**
     * DAO【CSV申込状況一覧】取得処理
     *
     * @return csvDataDao を戻します。
     */
    public UICsvStatusListDao getNationalViewListUICsvStatusListDao() {
        return nationalViewListUICsvStatusListDao;
    }

    /**
     * DAO【CSV申込状況一覧】設定処理
     *
     * @param csvDataDao 設定する dao。
     */
    public void setNationalViewListUICsvStatusListDao(
            UICsvStatusListDao nationalViewListUICsvStatusListDao) {
        this.nationalViewListUICsvStatusListDao = nationalViewListUICsvStatusListDao;
    }

	public UINatiViewEntrySelectInfoDao getNatiViewEntrySelectInfoDao() {
		return natiViewEntrySelectInfoDao;
	}

	public void setNatiViewEntrySelectInfoDao(UINatiViewEntrySelectInfoDao natiViewEntrySelectInfoDao) {
		this.natiViewEntrySelectInfoDao = natiViewEntrySelectInfoDao;
	}
    /**
     * １行分のリスト作成取得処理
     *
     * @param entity
     * @return
     */
    private List make1RowData(UICsvStatusList entity) {
        List listData = new ArrayList();
        //オーナーコード
        listData.add(convertMoji(entity.getOnerCd()));
        //オーナー名称
        listData.add(convertMoji(entity.getOnerNameKj()));

        //SEQ_NO
        listData.add(convertMoji(entity.getSeqNo()));

        //支部コード
        listData.add(convertMoji(entity.getSibuCd()));
        //支部名称
        listData.add(convertMoji(entity.getSibuName()));

        //店舗コード
        listData.add(convertMoji(entity.getMiseCd()));
        //店舗名称
        listData.add(convertMoji(entity.getMiseNameKj()));

        //氏名漢字(姓)
        listData.add(convertMoji(entity.getLNameKj()));
        //氏名漢字(名)
        listData.add(convertMoji(entity.getFNameKj()));
        //氏名カナ(姓)
        listData.add(convertMoji(entity.getLNameKna()));
        //氏名カナ(名)
        listData.add(convertMoji(entity.getFNameKna()));

        //性別
        listData.add(convertMoji(entity.getSex()));
        //生年月日
        String birthday = entity.getBirthday();
        DateFormatter dateFormatter = new DateFormatter();
        listData.add(birthday == null?"":dateFormatter.format(birthday
				,DateFormatter.PATTERN_NORMAL
				,DateFormatter.PATTERN_SLASH
				,false));
        //年齢
        //listData.add(convertMoji(entity.getAge()));
        listData.add(convertMoji(getNewAge(birthday)));
        //職位
        listData.add(convertMoji(entity.getJobType()));

        //申込区分
//        listData.add(convertMoji(entity.getProposeKbn()));
        //申込区分名
        listData.add(convertMoji(entity.getProposeName()));
        //オプショナル（宿泊関連）
        //オプショナル１
        listData.add(convertMoji(entity.getOptional1()));
        //オプショナル２
        listData.add(convertMoji(entity.getOptional2()));
        //オプショナル３
        listData.add(convertMoji(entity.getOptional3()));
        //オプショナル４
        listData.add(convertMoji(entity.getOptional4()));
        //オプショナル１１
        listData.add(convertMoji(entity.getOptional11()));
        //オプショナル１２
        listData.add(convertMoji(entity.getOptional12()));
        //オプショナル１３
        listData.add(convertMoji(entity.getOptional13()));
        //オプショナル１４
        listData.add(convertMoji(entity.getOptional14()));
        //オプショナル１５
        listData.add(convertMoji(entity.getOptional15()));
        //オプショナル１６
        listData.add(convertMoji(entity.getOptional16()));

        //オプショナル（ツアー関連）
        //オプショナル５
        listData.add(convertMoji(entity.getOptional5()));
        //オプショナル６
        listData.add(convertMoji(entity.getOptional6()));
        //オプショナル７
        listData.add(convertMoji(entity.getOptional7()));
        //オプショナル８
        listData.add(convertMoji(entity.getOptional8()));
        //オプショナル９
        listData.add(convertMoji(entity.getOptional9()));
        //オプショナル１０
        listData.add(convertMoji(entity.getOptional10()));

        //オプショナル（その他）
        //オプショナル２１
        listData.add(convertMoji(entity.getOptional21()));
        //オプショナル２２
        listData.add(convertMoji(entity.getOptional22()));
        //オプショナル２３
        listData.add(convertMoji(entity.getOptional23()));
        //オプショナル２４
        listData.add(convertMoji(entity.getOptional24()));
        //オプショナル２５
        listData.add(convertMoji(entity.getOptional25()));
        //オプショナル２６
        listData.add(convertMoji(entity.getOptional26()));
        //オプショナル２７
        listData.add(convertMoji(entity.getOptional27()));
        //オプショナル２８
        listData.add(convertMoji(entity.getOptional28()));
        //オプショナル２９
        listData.add(convertMoji(entity.getOptional29()));
        //オプショナル３０
        listData.add(convertMoji(entity.getOptional30()));
        //備考1
        listData.add(convertMoji(entity.getNote1()));
        //備考2
        listData.add(convertMoji(entity.getNote()));
        //更新者ID
        listData.add(convertMoji(entity.getLastUser()));
        //更新者名称
        listData.add(convertMoji(entity.getUserNameKj()));

        //オーナー契約終了日
        listData.add(convertMoji(entity.getKeiyakuEnd()));

        return listData;
    }
    /**
     * 指定文字列をユニコードへ変換し取得します。
     *
     * @param moji
     * @return
     */
    private String convertMoji(String moji){
        if(moji == null){
            return "";
        }
        return moji.trim();
    }
    /**
     * 年齢再計算する
     * @param birthday
     * @return
     */
    private String getNewAge(String birthday){
    	 // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報を取得
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        String sysDate = birdDateInfo.getSysDate();
        String age = null;
        age = String.valueOf(NationalEntryUtil.chgToAgeFrDate(birthday,sysDate));
        if ("0".equals(age)) {
        	age = "";
        }
        return age;
    }

}
