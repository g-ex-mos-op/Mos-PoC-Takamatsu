/*
 * 作成日: 2006/07/05
 * 更新：2008/06/09 サービス実施状況欄変更 （PK-011050-005）
 * 　　　　　　　　　　・宅配-「実施状況」削除
 * 　　　　　　　　　　・宅配-「変更日」--> 「適用日」に変更
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.MstEventStatusDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dto.MiseLumpExtractDto;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.GasAircon;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstChintai;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstEventStatus;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstStaff;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.TrnURL;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.UITakuhaiMijissi;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.logic.MiseLumpExtractLogic;

/**
 * 個店情報一括抽出画面 CVSデータリストの作成
 * @author xayumi
 *
 * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
 * 更新日:2015/05/14 xytamura 宅配未実施店一覧を当月宅配実績無し店舗一覧に名称変更
 */
public class MiseLumpExtractCsvLogicImpl implements CsvOutputLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BSI003L02";
    /* イベント分類：宅配 */
    private static final String EVENT_BNRUI_TAKUHAI = "02";

    private MiseLumpExtractLogic miseLumpExtractLogic;
    private MstEventStatusDao mstEventStatusDao;

    /** 緊急連絡先情報 (0) */
    public static final int MST_MISEYOBI_TEL  = 0;
    /** 地図情報（ＵＲＬ) (1) */
    public static final int TRN_URL           = 1;
    /** メモ1～５ (2) */
    public static final int MST_MISEYOBI_MEMO = 2;
    /** 賃貸店舗情報 (3) */
    public static final int MST_CHINTAI       = 3;
    /** インセンティブ (4) */
    //public static final int MST_INSENTIVE     = 4;
    /** ＨＤＣ (5) */
    //public static final int MST_HDC           = 5;
    /** サービス実施状 (4) */
    public static final int MST_EVENTSTATUS   = 4;
    /** マスタライセンス情報 (5) */
    public static final int MST_STAFF         = 5;
    /** 当月宅配実績無し店舗一覧 (6) */
    public static final int MST_TAKUHAI_MIJISSI = 6;
    /** 店舗ガス種別 (7) 011/07/11 店舗ガス種別追加対応 */
    public static final int UI_GAS_AIRCON     = 7;

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {

	    // DTO
        MiseLumpExtractDto dto = (MiseLumpExtractDto) csvOutputDto;

        int index = dto.getIndex();

        String fileName = "";

        if(index==MST_MISEYOBI_TEL){
            fileName = "KinkyuRenraku.csv";

        } else if(index==TRN_URL){
            fileName = "MapInfo.csv";

        } else if(index==MST_MISEYOBI_MEMO){
            fileName = "Memo.csv";

        } else if(index==MST_CHINTAI){
            fileName = "Chintai.csv";

        }
//        else if(index==MST_INSENTIVE){
//            fileName = "Insentive.csv";
//
//        } else if(index==MST_HDC){
//            fileName = "HDC.csv";
//
//        }
        else if(index==MST_EVENTSTATUS){
            fileName = "Service.csv";

        } else if(index==MST_STAFF){
            fileName = "Masterlicense.csv";
        } else if(index==MST_TAKUHAI_MIJISSI){
            fileName = "TakuhaiJissekinashi.csv";
        }
        //2011/07/11 店舗ガス種別追加対応
        else if(index == UI_GAS_AIRCON) {
        	fileName = "GasAirconditioner.csv";
        }

		return fileName;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

        // DTO
        MiseLumpExtractDto dto = (MiseLumpExtractDto) csvOutputDto;

        /** ロジック【個店情報の検索】実施 ****/
        List csvList = getMiseLumpExtractLogic().execute(dto);

        if (csvList == null || csvList.size() == 0) {
            throw new NoResultException();
        }

        dto.setCsvList(csvList);

        int index = dto.getIndex();

        // CSV出力用List
        List listCSV = new ArrayList();

        try {
            // ヘッダ部
            makeHeader(dto, listCSV);

            if(index==MST_MISEYOBI_TEL){
                /** 店舗拡張マスタ(MstMiseYobi) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    MstMiseYobi entity = (MstMiseYobi) ite.next();
                    //１行分のリスト作成
                    List listData = new ArrayList();
                    listData.add(entity.getSibuCd());
                    listData.add(entity.getSibuName());
                    listData.add(entity.getBlockName());
                    listData.add(entity.getMiseCd());
                    listData.add(entity.getMiseNameKj());
                    listData.add(entity.getEmergenceName1());
                    listData.add(entity.getEmergenceTel1());
                    listData.add(entity.getEmergenceName2());
                    listData.add(entity.getEmergenceTel2());
                    listData.add(entity.getEmergenceName3());
                    listData.add(entity.getEmergenceTel3());

                    listCSV.add(listData);
                }

            } else if(index==TRN_URL){
                /** 地図URL情報(TrnURL) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    TrnURL entity = (TrnURL) ite.next();
                    //１行分のリスト作成
                    List listData = new ArrayList();
                    listData.add(entity.getSibuCd());
                    listData.add(entity.getSibuName());
                    listData.add(entity.getBlockName());
                    listData.add(entity.getMiseCd());
                    listData.add(entity.getMiseNameKj());
                    listData.add(entity.getUrl());

                    listCSV.add(listData);
                }

            } else if(index==MST_MISEYOBI_MEMO){
                /** 店舗拡張マスタ(MstMiseYobi) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    MstMiseYobi entity = (MstMiseYobi) ite.next();
                    //１行分のリスト作成
                    List listData = new ArrayList();
                    listData.add(entity.getSibuCd());
                    listData.add(entity.getSibuName());
                    listData.add(entity.getBlockName());
                    listData.add(entity.getMiseCd());
                    listData.add(entity.getMiseNameKj());
                    listData.add(entity.getYobiFree1());
                    listData.add(entity.getYobiFree2());
                    listData.add(entity.getYobiFree3());
                    listData.add(entity.getYobiFree4());
                    listData.add(entity.getYobiFree5());

                    listCSV.add(listData);
                }

            } else if(index==MST_CHINTAI){
                /** 賃貸店舗履歴(MstChintai) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    MstChintai entity = (MstChintai) ite.next();
                    //１行分のリスト作成
                    List listData = new ArrayList();
                    listData.add(entity.getSibuCd());
                    listData.add(entity.getSibuName());
                    listData.add(entity.getBlockName());
                    listData.add(entity.getMiseCd());
                    listData.add(entity.getMiseNameKj());
                    listData.add(entity.getMiseLeaseName());
                    listData.add(formatYMD(entity.getMiseLeaseStart()));
                    listData.add(formatYMD(entity.getMiseLeaseEnd()));

                    listCSV.add(listData);
                }

            }
//            else if(index==MST_INSENTIVE){
//                /** インセンティブ履歴(MstInsentive) */
//                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
//                    //Listからentityへキャストする
//                    MstInsentive entity = (MstInsentive) ite.next();
//                    //１行分のリスト作成
//                    List listData = new ArrayList();
//                    listData.add(entity.getSibuCd());
//                    listData.add(entity.getSibuName());
//                    listData.add(entity.getBlockName());
//                    listData.add(entity.getMiseCd());
//                    listData.add(entity.getMiseNameKj());
//                    listData.add(entity.getInsentiveName1());
//                    listData.add(formatYMD(entity.getInsentive1Sta()));
//                    listData.add(formatYMD(entity.getInsentive1End()));
//                    listData.add(entity.getInsentive1Note());
//
//                    listCSV.add(listData);
//                }

//            }
//        else if(index==MST_HDC){
//                /** HDC受賞履歴(MstHDC) */
//                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
//                    //Listからentityへキャストする
//                    MstHDC entity = (MstHDC) ite.next();
//                    //１行分のリスト作成
//                    List listData = new ArrayList();
//                    listData.add(entity.getSibuCd());
//                    listData.add(entity.getSibuName());
//                    listData.add(entity.getBlockName());
//                    listData.add(entity.getMiseCd());
//                    listData.add(entity.getMiseNameKj());
//                    listData.add(formatYM(entity.getHdcDt()));
//                    listData.add(entity.getHdcNaiyou());
//
//                    listCSV.add(listData);
//                }

//            }
            else if(index==MST_EVENTSTATUS){
                makeEventStatus(dto, listCSV);

            } else if(index==MST_STAFF){
                /** マスターライセンス結果状況(MstStaff) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    MstStaff entity = (MstStaff) ite.next();
                    //１行分のリスト作成
                    List listData = new ArrayList();
                    listData.add(entity.getSibuCd());
                    listData.add(entity.getSibuName());
                    listData.add(entity.getOnerCd());
                    listData.add(entity.getOnerNameKj());
                    listData.add(entity.getMiseCd1());
                    listData.add(entity.getMiseNameKj());
                    listData.add(formatYM(entity.getLicenseDt()));
                    listData.add(entity.getLicenseNo());
                    listData.add(entity.getTotalLastYear());
                    listData.add(entity.getTotalLastKai());
                    listData.add(entity.getExamNo());
                    listData.add(entity.getCsvStaffName());
                    listData.add(entity.getCsvTotalResult());
                    listData.add(entity.getCsvSub1Result());
                    listData.add(entity.getCsvSub2Result());
                    listData.add(entity.getCsvSub3Result());
                    listData.add(entity.getCsvCourseStatus());
                    listData.add(entity.getCsvSituationKbn());

                    listCSV.add(listData);
                }

            } else if (index == MST_TAKUHAI_MIJISSI) {
                /** 宅配未実施店 */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    UITakuhaiMijissi entity = (UITakuhaiMijissi) ite.next();
                    List listData = new ArrayList();
                    listData.add(entity.getCompanyCd());
                    listData.add(entity.getMiseCd());
                    listData.add(entity.getMiseNameKj());
                    listData.add(entity.getTakuhaiKbnName());
                    listData.add(entity.getEventStartDt());
                    listData.add(entity.getEventEndDt());
                    listData.add(entity.getHenkoDt());

                    listCSV.add(listData);
                }
            }
            //2011/07/11 店舗ガス種別追加対応
            else if (index == UI_GAS_AIRCON) {
                /** 店舗ガス種別 */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    GasAircon entity = (GasAircon) ite.next();
                    List listData = new ArrayList();
                    listData.add(entity.getSibuCd());
                    listData.add(entity.getSibuName());
                    listData.add(entity.getBlockName());
                    listData.add(entity.getMiseCd());
                    listData.add(entity.getMiseNameKj());
                    listData.add(entity.getGasShuName()==null?"":entity.getGasShuName());
                    listData.add(entity.getAirConShuName()==null?"":entity.getAirConShuName());

                    listCSV.add(listData);
                }
            }

        }
        catch (Exception ex) {
        	throw new FtlSystemException("CSV作成");
        }
		return listCSV;
	}


    private void makeEventStatus(MiseLumpExtractDto dto, List listCSV){
        /** イベント実施状況(MstEventStatus) */
        List listEvnt    = null;
        String miseCd    = "";
        String bunruiCd  = "";
        for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
            //Listからentityへキャストする
            MstEventStatus entity = (MstEventStatus) ite.next();

            if(!miseCd.equals(entity.getMiseCd())){
                miseCd     = entity.getMiseCd();

                if(listEvnt!=null){
                    listCSV.add(listEvnt);
                }

                listEvnt = new ArrayList();
                listEvnt.add(entity.getSibuCd());
                listEvnt.add(entity.getSibuName());
                listEvnt.add(entity.getBlockName());
                listEvnt.add(entity.getMiseCd());
                listEvnt.add(entity.getMiseNameKj());
            }

            if(!bunruiCd.equals(entity.getEventBnrui())){
                bunruiCd = entity.getEventBnrui();
                listEvnt.add(entity.getEventBnruiName());
            }

            listEvnt.add(entity.getEventName());
            //イベント分類が０２（宅配）以外の時のみ出力
            if (!EVENT_BNRUI_TAKUHAI.equals(entity.getEventBnrui())) {
                listEvnt.add(entity.getEventStatusName());
            }
            listEvnt.add(formatYMD(entity.getEventStartDt()));
            listEvnt.add(formatYMD(entity.getEventEndDt()));
            listEvnt.add(entity.getEventPatternName());
            listEvnt.add(formatYMD(entity.getHenkoDt()));
            listEvnt.add(entity.getNotes());
        }

        if(listEvnt!=null){
            listCSV.add(listEvnt);
        }
    }


    /* ヘッダ部 */
    private void makeHeader(MiseLumpExtractDto dto, List listCsv) {

        List listCompany = new ArrayList();
        List listSibu    = new ArrayList();
        List listClose   = new ArrayList();
        List listKoumoku = new ArrayList();
        List listKoumoku2= new ArrayList();
        List listSpace   = new ArrayList();

        try{
            int index = dto.getIndex();

            // 会社
            listCompany.add("会社：");
            listCompany.add(dto.getCompanyName());
            // 支部
            listSibu.add("支部：");
            listSibu.add( dto.getSibuName());
            // クローズ店
            listClose.add("クローズ店：");
            listClose.add(dto.getCloseName());

            // 項目ヘッダー
            if(index!=MST_STAFF && index != MST_TAKUHAI_MIJISSI){
                listKoumoku.add("支部コード");
                listKoumoku.add("支部名");
                listKoumoku.add("ブロック名");
                listKoumoku.add("店コード");
                listKoumoku.add("店舗名");
            }


            if(index==MST_MISEYOBI_TEL){
                listKoumoku.add("緊急連絡先名1");
                listKoumoku.add("緊急連絡先1");
                listKoumoku.add("緊急連絡先名2");
                listKoumoku.add("緊急連絡先2");
                listKoumoku.add("緊急連絡先名3");
                listKoumoku.add("緊急連絡先3");

            } else if(index==TRN_URL){
                listKoumoku.add("公式ホームページURL");

            } else if(index==MST_MISEYOBI_MEMO){
                listKoumoku.add("子ども１１０番情報");
                listKoumoku.add("休業情報");
                listKoumoku.add("メモ３");
                listKoumoku.add("メモ４");
                listKoumoku.add("メモ５");

            } else if(index==MST_CHINTAI){
                listKoumoku.add("賃貸店舗");
                listKoumoku.add("賃貸店舗契約日");
                listKoumoku.add("契約終了予定日");

            }
//            else if(index==MST_INSENTIVE){
//                listKoumoku.add("インセンティブ名称");
//                listKoumoku.add("開始日");
//                listKoumoku.add("終了日");
//                listKoumoku.add("備考");
//
//            }
//            else if(index==MST_HDC){
//                listKoumoku.add("HDC店舗賞年月");
//                listKoumoku.add("HDC店舗賞内容");
//
//            }
            else if(index==MST_EVENTSTATUS){
                List maxList = dto.getListEvntMax();

                for (Iterator ite = maxList.iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    MstEventStatus entity = (MstEventStatus) ite.next();

                    int k = Integer.parseInt(entity.getEventMaxCnt());

                    listKoumoku.add("イベント分類");
                    for(int i =0 ; i < k; i++) {
                        listKoumoku.add("項目");
                        //イベント分類が０２（宅配）以外の時のみ出力
                        if (!EVENT_BNRUI_TAKUHAI.equals(entity.getEventBnrui())) {
                            listKoumoku.add("実施状況");
                        }
                        listKoumoku.add("開始日");
                        listKoumoku.add("終了日");
                        listKoumoku.add("種別");
                        listKoumoku.add("適用日");
                        listKoumoku.add("備考");
                    }
                }

            } else if(index==MST_STAFF){
                listKoumoku.add("支部コード");
                listKoumoku.add("支部名");
                listKoumoku.add("オーナーコード");
                listKoumoku.add("オーナー名");
                listKoumoku.add("店コード");
                listKoumoku.add("店舗名");
                listKoumoku.add("ライセンス取得年度");
                listKoumoku.add("ライセンス番号");
                listKoumoku.add("受験年度");
                listKoumoku.add("受験回");
                listKoumoku.add("受験番号");
                listKoumoku.add("受験者氏名");
                listKoumoku.add("総合結果");
                listKoumoku.add("能力チェック");
                listKoumoku.add("筆記テスト");
                listKoumoku.add("面接");
                listKoumoku.add("研修修了");
                listKoumoku.add("活動状況");
            } else if (index == MST_TAKUHAI_MIJISSI) {
                listKoumoku.add("");
                listKoumoku.add("");
                listKoumoku.add("");
                listKoumoku.add("宅配実施状況　現状");
                listKoumoku2.add("会社");
                listKoumoku2.add("店コード");
                listKoumoku2.add("店名称");
                listKoumoku2.add("宅配区分");
                listKoumoku2.add("開始日");
                listKoumoku2.add("終了日");
                listKoumoku2.add("変更日");
            }
            else if (index == UI_GAS_AIRCON) {
            	listKoumoku.add("ガス種別");
            	listKoumoku.add("エアコン種別");
            }

            listCsv.add(listCompany);
            listCsv.add(listSibu);
            listCsv.add(listClose);
            listCsv.add(listSpace);
            listCsv.add(listKoumoku);
            if (!listKoumoku2.isEmpty()) {
                listCsv.add(listKoumoku2);
            }

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }

    }

    /**
     * 日付フォーマット処理(yyyy/MM/dd)
     * @param  date        変換前の日付文字列
     * @return formatDate　変換後の日付文字列
     */
    private String formatYMD(String date){

        DateFormatter daFom = new DateFormatter();

        String formatDate = "";

        try {
            formatDate =
                daFom.format(
                    date,
                    DateFormatter.PATTERN_SLASH,
                    DateFormatter.DATE_TYPE_YMD);

        } catch (Exception e) {
            throw new FtlSystemException("CSV作成:日付フォーマット処理");
        }

        return formatDate;
    }

    /**
     * 日付フォーマット処理(yyyy/MM)
     * @param  date  変換前の日付文字列
     * @return formatDate　変換後の日付文字列
     */
    private String formatYM(String date){

        DateFormatter daFom = new DateFormatter();

        String formatDate = "";

        try {
            formatDate =
                daFom.format(
                    date,
                    DateFormatter.PATTERN_MONTH_SLASH,
                    DateFormatter.DATE_TYPE_YM);

        } catch (Exception e) {
            throw new FtlSystemException("CSV作成:日付フォーマット処理");
        }

        return formatDate;
    }

    /**
     * 妥当性チェック
     */
    public void validate(CsvOutputDto csvOutputDto) {
    }


	public MiseLumpExtractLogic getMiseLumpExtractLogic() {
		return miseLumpExtractLogic;
	}
	public void setMiseLumpExtractLogic(MiseLumpExtractLogic getHanyoEntryListLogic) {
		this.miseLumpExtractLogic = getHanyoEntryListLogic;
	}


    /**
     * イベント実施状況(MstEventStatus)Daoの設定
     * @return mstEventStatusDao を戻します。
     */
    public MstEventStatusDao getMstEventStatusDao() {
        return mstEventStatusDao;
    }
    /**
     * イベント実施状況(MstEventStatus)Daoの設定
     * @param MstEventStatusDao mstEventStatusDao を設定。
     */
    public void setMstEventStatusDao(MstEventStatusDao mstEventStatusDao) {
        this.mstEventStatusDao = mstEventStatusDao;
    }
}
