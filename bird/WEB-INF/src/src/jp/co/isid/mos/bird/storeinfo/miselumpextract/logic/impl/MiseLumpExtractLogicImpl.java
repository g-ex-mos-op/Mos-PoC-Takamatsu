/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.GasAirconDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.MstEventStatusDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.MstMiseYobiDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.MstStaffDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.TrnURLDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dao.UITakuhaiMijissiDao;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dto.MiseLumpExtractDto;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstEventStatus;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstInsentive;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstStaff;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.TrnURL;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.logic.MiseLumpExtractLogic;

/**
 * 個店情報一括抽出画面  個店情報の検索ロジック
 * @author xayumi
 *
 * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
 */
public class MiseLumpExtractLogicImpl implements MiseLumpExtractLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BSI003L03";

    private MstChintaiDao     mstChintaiDao;
    private MstEventStatusDao mstEventStatusDao;
//    private MstHDCDao         mstHDCDao;
//    private MstInsentiveDao   mstInsentiveDao;
    private MstMiseYobiDao    mstMiseYobiDao;
    private MstStaffDao       mstStaffDao;
    private TrnURLDao         trnURLDao;
    private UITakuhaiMijissiDao uiTakuhaiMijissiDao;
    /** DAO【店舗ガス種別】2011/07/11 店舗ガス種別追加対応 */
    private GasAirconDao      miseLumpExtractGasAirconDao;

	/**
     * 個店情報の検索
     * @return 検索結果
     *
     * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
     */
    public List execute(MiseLumpExtractDto dto) {

        String companyCd = dto.getCompanyCd();
        String sibuCd    = dto.getSibuCd();
        String closeFlg  = dto.getCloseFlg();
        if(closeFlg.equals("inClose")){
            //「含む」が選択された場合、null。
            closeFlg = null;
        }

        List csvList = new ArrayList();

        int index = dto.getIndex();

        if(index==MiseLumpExtractCsvLogicImpl.MST_MISEYOBI_TEL){
            /* 店舗拡張マスタ(MstMiseYobi) */
            csvList = removeEmptyTel(getMstMiseYobiDao().selectMiseYobi(companyCd, sibuCd, closeFlg));

        } else if(index==MiseLumpExtractCsvLogicImpl.TRN_URL){
            /* 地図URL情報(TrnURL) */
            csvList = removeEmptyMap(getTrnURLDao().selectMapURL(companyCd, sibuCd, closeFlg));

        } else if(index==MiseLumpExtractCsvLogicImpl.MST_MISEYOBI_MEMO){
            /* 店舗拡張マスタ(MstMiseYobi) */
            csvList = removeEmptyMemo(getMstMiseYobiDao().selectMiseYobi(companyCd, sibuCd, closeFlg));

        } else if(index==MiseLumpExtractCsvLogicImpl.MST_CHINTAI){
            /* 賃貸店舗履歴(MstChintai) */
            csvList = getMstChintaiDao().selectChintai(companyCd, sibuCd, closeFlg);

        }
//        else if(index==MiseLumpExtractCsvLogicImpl.MST_INSENTIVE){
//            /* インセンティブ履歴(MstInsentive) */
//            csvList =
//                makeInsentiveData(
//                    getMstInsentiveDao().selectInsentiveRireki(companyCd, sibuCd, closeFlg));
//
//        } else if(index==MiseLumpExtractCsvLogicImpl.MST_HDC){
//            /* HDC受賞履歴(MstHDC) */
//            csvList = getMstHDCDao().selectHDC(companyCd, sibuCd, closeFlg);
//
//        }
        else if(index==MiseLumpExtractCsvLogicImpl.MST_EVENTSTATUS){
            /* イベント実施状況(MstEventStatus) */
            csvList = makeEventStatus(dto, companyCd, sibuCd, closeFlg);

        } else if(index==MiseLumpExtractCsvLogicImpl.MST_STAFF){
            /* マスターライセンス結果状況(MstStaff) */
            csvList = makeStaff(companyCd, sibuCd, closeFlg);
        } else if(index==MiseLumpExtractCsvLogicImpl.MST_TAKUHAI_MIJISSI){
            /* 宅配未実施店 */
            csvList = makeTakuhaiMijissi(companyCd, sibuCd, closeFlg, dto.getSysDate());
        }
        else if (index == MiseLumpExtractCsvLogicImpl.UI_GAS_AIRCON) {
        	/* 店舗ガス種別 */
        	csvList = getMiseLumpExtractGasAirconDao().select(companyCd, sibuCd, closeFlg);
        }
        return csvList;
    }


    /**
     * マスターライセンス結果状況CSVリストを作成する。
     * @param  companyCd  会社コード
     * @param  sibuCd     支部コード
     * @param  closeFlg   店クローズフラグ（含む／含まない）
     * @return csvList    マスターライセンス結果状況CSVリスト
     */
    private List makeStaff(String companyCd, String sibuCd, String closeFlg){
        List csvList = new ArrayList();

        /** ① ロジック【店リストの検索】実行 */
        List miseList =
            getMstStaffDao().selectMise(companyCd, sibuCd, closeFlg);
        /** ② ロジック【マスターライセンス結果状況一覧の検索】実行 */
        List staffList =
            getMstStaffDao().selectStaff(companyCd, sibuCd, closeFlg);

        for (Iterator ite = staffList.iterator(); ite.hasNext();) {
            csvList.add(ite.next());
        }

        // 店リスト①と、リスト②をマージする。
        // ※マスタライセンス保持者のいない店舗もＣＳＶに表示させる為、
        //   店舗情報のみの空データをマージさせる。
        boolean isExist = false;
        for (Iterator ite = miseList.iterator(); ite.hasNext();) {
            MstStaff eMise = (MstStaff) ite.next();

            for (Iterator i = staffList.iterator(); i.hasNext();) {
                MstStaff eStaff = (MstStaff) i.next();

                if(eMise.getMiseCd1().equals(eStaff.getMiseCd1())){
                    isExist = true;
                    break;
                }
            }
            if(!isExist){
                csvList.add(eMise);
            }
            isExist = false;
        }

        // 支部コード、オーナーコード、店コード、
        // 受験年度、受験回をキーにソート
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String val1 = ((MstStaff) obj1).getSibuCd()
                            + ((MstStaff) obj1).getOnerCd()
                            + ((MstStaff) obj1).getMiseCd1()
                            + ((MstStaff) obj1).getTotalLastYear()
                            + ((MstStaff) obj1).getTotalLastKai();

                String val2 = ((MstStaff) obj2).getSibuCd()
                            + ((MstStaff) obj2).getOnerCd()
                            + ((MstStaff) obj2).getMiseCd1()
                            + ((MstStaff) obj2).getTotalLastYear()
                            + ((MstStaff) obj2).getTotalLastKai();

                return val1.compareTo(val2);
            }
        };
        Collections.sort(csvList, sortComparator);

        return csvList;

    }

    /**
     * イベント実施状況CSVリストを作成する。
     * @param  dto        dto
     * @param  companyCd  会社コード
     * @param  sibuCd     支部コード
     * @param  closeFlg   店クローズフラグ（含む／含まない）
     * @return csvList    イベント実施状況CSVリスト
     */
    private List makeEventStatus(MiseLumpExtractDto dto, String companyCd, String sibuCd, String closeFlg){

        /** ロジック【店リストの検索】実行 */
        List miseList =
            getMstEventStatusDao().selectEventMise(companyCd, sibuCd, closeFlg);

        /** ロジック【イベント分類毎の、イベント種別の数のMAXを取得】実行 */
        List maxList =
            getMstEventStatusDao().selectCount(companyCd, sibuCd, closeFlg);

        dto.setListEvntMax(maxList);


        int maxCnt   = 0;
        int evntCnt  = 0;
        String bunruiCd = "";
        List evntList   = null;
        List csvList    = new ArrayList();
        MstEventStatus eNew = null;

        //店リスト
        for (Iterator ite = miseList.iterator(); ite.hasNext();) {
            MstEventStatus eMise = (MstEventStatus) ite.next();

            /** ロジック【イベント実施状況の検索】実行 */
            evntList = getMstEventStatusDao().selectEvent(companyCd, eMise.getMiseCd());

            //空白行チェック処理
            if(!chkEmptyEvent(evntList)){
                continue;
            }

            //一店舗分のイベント実施状況データ
            for (Iterator i = evntList.iterator(); i.hasNext();) {
                MstEventStatus eEvnt = (MstEventStatus) i.next();

                if(!bunruiCd.equals(eEvnt.getEventBnrui())){

                    if(!bunruiCd.equals("")){
                        //MAX値取得
                        for (Iterator k = maxList.iterator(); k.hasNext();) {
                            MstEventStatus eMax = (MstEventStatus) k.next();
                            if(bunruiCd.equals(eMax.getEventBnrui())){
                                maxCnt = Integer.parseInt(eMax.getEventMaxCnt());
                                break;
                            }
                        }

                        //店舗情報のみの空データを追加する。
                        for(int k = 0 ; k < maxCnt-evntCnt; k++){
                            eNew = new MstEventStatus();
                            eNew.setCompanyCd(eMise.getCompanyCd());
                            eNew.setMiseCd(eMise.getMiseCd());
                            eNew.setMiseNameKj(eMise.getMiseNameKj());
                            eNew.setSibuCd(eMise.getSibuCd());
                            eNew.setSibuName(eMise.getSibuName());
                            eNew.setBlockName(eMise.getBlockName());
                            eNew.setGyotaiKbn(eMise.getGyotaiKbn());
                            eNew.setEventBnrui(bunruiCd);
                            eNew.setEventSortCd("ZZ");

                            csvList.add(eNew);
                        }
                        maxCnt   = 0;
                        evntCnt  = 0;
                    }

                    bunruiCd = eEvnt.getEventBnrui();
                }

                eNew = new MstEventStatus();
                eNew.setCompanyCd(eMise.getCompanyCd());
                eNew.setMiseCd(eMise.getMiseCd());
                eNew.setMiseNameKj(eMise.getMiseNameKj());
                eNew.setSibuCd(eMise.getSibuCd());
                eNew.setSibuName(eMise.getSibuName());
                eNew.setBlockName(eMise.getBlockName());
                eNew.setGyotaiKbn(eMise.getGyotaiKbn());

                eNew.setEventBnrui(eEvnt.getEventBnrui());
                eNew.setEventBnruiName(eEvnt.getEventBnruiName());
                eNew.setEventCd(eEvnt.getEventCd());
                eNew.setEventName(eEvnt.getEventName());
                eNew.setEventStatus(eEvnt.getEventStatus());
                eNew.setEventStartDt(eEvnt.getEventStartDt());
                eNew.setEventEndDt(eEvnt.getEventEndDt());
                eNew.setEventSortCd(eEvnt.getEventSortCd());
                eNew.setEventPatternCd(eEvnt.getEventPatternCd());
                eNew.setEventPatternName(eEvnt.getEventPatternName());
                eNew.setHenkoDt(eEvnt.getHenkoDt());
                eNew.setNotes(eEvnt.getNotes());

                csvList.add(eNew);

                evntCnt++;
            }
        }

        return csvList;
    }

    /**
     * 宅配未実施店CSVリストを作成する。
     * @param  companyCd  会社コード
     * @param  sibuCd     支部コード
     * @param  closeFlg   店クローズフラグ（含む／含まない）
     * @return csvList    宅配未実施店CSVリスト
     */
    private List makeTakuhaiMijissi(String companyCd, String sibuCd, String closeFlg, String sysDate){
        String targetYM = targetYM = sysDate.substring(0, 6);
        String closeDt = null;
        if (closeFlg != null) {
            closeDt = sysDate;
        }
        //宅配未実施店情報の取得
        return getUiTakuhaiMijissiDao().select(companyCd, sibuCd, targetYM, closeDt);
    }
    /**
     * 空白行チェック処理
     * @param listEventStatus
     * @return boo
     */
    private boolean chkEmptyEvent(List listEventStatus){
        boolean emptyFlg = false;

        if (listEventStatus != null) {
            for (Iterator ite = listEventStatus.iterator(); ite.hasNext();) {
                MstEventStatus entity = (MstEventStatus) ite.next();

                if(!entity.getEventStatus().equals("")
                  || !entity.getEventStartDt().equals("")
                  || !entity.getEventEndDt().equals("")
                  || !entity.getEventPatternCd().equals("")
                  || !entity.getHenkoDt().equals("")
                  || !entity.getNotes().equals("")){

                    emptyFlg = true;
                }
            }
        }

        return emptyFlg;
    }


    /**
     * 空白行削除処理
     * @param listTrnURL
     * @return
     */
    private List removeEmptyMap(List listTrnURL){

        if (listTrnURL != null) {
            for (Iterator ite = listTrnURL.iterator(); ite.hasNext();) {
                TrnURL mstTrnURL = (TrnURL) ite.next();
                //地図が空白の場合、remove。
                if(mstTrnURL.getUrl().equals("")){
                    ite.remove();
                }
            }
        }

        return listTrnURL;
    }

    /**
     * 空白行削除処理
     * @param listMiseyobi
     * @return
     */
    private List removeEmptyTel(List listMiseyobi){

        if (listMiseyobi != null) {
            for (Iterator ite = listMiseyobi.iterator(); ite.hasNext();) {
                MstMiseYobi mstMiseYobi = (MstMiseYobi) ite.next();
                //緊急連絡先１～３・緊急連絡先名１～３全ての項目が空白の場合、remove。
                if(mstMiseYobi.getEmergenceTel1().equals("")
                        && mstMiseYobi.getEmergenceTel2().equals("")
                        && mstMiseYobi.getEmergenceTel3().equals("")
                        && mstMiseYobi.getEmergenceName1().equals("")
                        && mstMiseYobi.getEmergenceName2().equals("")
                        && mstMiseYobi.getEmergenceName3().equals("")){
                    ite.remove();
                }
            }
        }

        return listMiseyobi;
    }

    /**
     * 空白行削除処理
     * @param listMiseyobi
     * @return
     */
    private List removeEmptyMemo(List listMiseyobi){

        if (listMiseyobi != null) {
            for (Iterator ite = listMiseyobi.iterator(); ite.hasNext();) {
                MstMiseYobi mstMiseYobi = (MstMiseYobi) ite.next();
                //メモ１～５全ての項目が空白の場合、remove。
                if(mstMiseYobi.getYobiFree1().equals("")
                        && mstMiseYobi.getYobiFree2().equals("")
                        && mstMiseYobi.getYobiFree3().equals("")
                        && mstMiseYobi.getYobiFree4().equals("")
                        && mstMiseYobi.getYobiFree5().equals("")){
                    ite.remove();
                }
            }
        }

        return listMiseyobi;
    }

    private List makeInsentiveData(List listInsentive) {
        List list = new ArrayList();

        // インセンティブ１～５を分解し、1レコード１インセンティブ情報にする
        for (Iterator ite = listInsentive.iterator(); ite.hasNext();) {
            MstInsentive mstInsentive = (MstInsentive) ite.next();

            if (!isNull(mstInsentive.getInsentive1())) {
                MstInsentive mstInsentive1 = new MstInsentive();
                mstInsentive1.setCompanyCd(mstInsentive.getCompanyCd());
                mstInsentive1.setMiseCd(mstInsentive.getMiseCd());
                mstInsentive1.setMiseNameKj(mstInsentive.getMiseNameKj());
                mstInsentive1.setSibuCd(mstInsentive.getSibuCd());
                mstInsentive1.setSibuName(mstInsentive.getSibuName());
                mstInsentive1.setBlockCd(mstInsentive.getBlockCd());
                mstInsentive1.setBlockName(mstInsentive.getBlockName());
                mstInsentive1.setInsentive1(mstInsentive.getInsentive1());
                mstInsentive1.setInsentiveName1(mstInsentive.getInsentiveName1());
                mstInsentive1.setInsentive1Sta(mstInsentive.getInsentive1Sta());
                mstInsentive1.setInsentive1End(mstInsentive.getInsentive1End());
                mstInsentive1.setInsentive1Note(mstInsentive.getInsentive1Note());
                list.add(mstInsentive1);
            }

            if (!isNull(mstInsentive.getInsentive2())) {
                MstInsentive mstInsentive2 = new MstInsentive();
                mstInsentive2.setCompanyCd(mstInsentive.getCompanyCd());
                mstInsentive2.setMiseCd(mstInsentive.getMiseCd());
                mstInsentive2.setMiseNameKj(mstInsentive.getMiseNameKj());
                mstInsentive2.setSibuCd(mstInsentive.getSibuCd());
                mstInsentive2.setSibuName(mstInsentive.getSibuName());
                mstInsentive2.setBlockCd(mstInsentive.getBlockCd());
                mstInsentive2.setBlockName(mstInsentive.getBlockName());
                mstInsentive2.setInsentive1(mstInsentive.getInsentive2());
                mstInsentive2.setInsentiveName1(mstInsentive.getInsentiveName2());
                mstInsentive2.setInsentive1Sta(mstInsentive.getInsentive2Sta());
                mstInsentive2.setInsentive1End(mstInsentive.getInsentive2End());
                mstInsentive2.setInsentive1Note(mstInsentive.getInsentive2Note());
                list.add(mstInsentive2);
            }

            if (!isNull(mstInsentive.getInsentive3())) {
                MstInsentive mstInsentive3 = new MstInsentive();
                mstInsentive3.setCompanyCd(mstInsentive.getCompanyCd());
                mstInsentive3.setMiseCd(mstInsentive.getMiseCd());
                mstInsentive3.setMiseNameKj(mstInsentive.getMiseNameKj());
                mstInsentive3.setSibuCd(mstInsentive.getSibuCd());
                mstInsentive3.setSibuName(mstInsentive.getSibuName());
                mstInsentive3.setBlockCd(mstInsentive.getBlockCd());
                mstInsentive3.setBlockName(mstInsentive.getBlockName());
                mstInsentive3.setInsentive1(mstInsentive.getInsentive3());
                mstInsentive3.setInsentiveName1(mstInsentive.getInsentiveName3());
                mstInsentive3.setInsentive1Sta(mstInsentive.getInsentive3Sta());
                mstInsentive3.setInsentive1End(mstInsentive.getInsentive3End());
                mstInsentive3.setInsentive1Note(mstInsentive.getInsentive3Note());
                list.add(mstInsentive3);
            }

            if (!isNull(mstInsentive.getInsentive4())) {
                MstInsentive mstInsentive4 = new MstInsentive();
                mstInsentive4.setCompanyCd(mstInsentive.getCompanyCd());
                mstInsentive4.setMiseCd(mstInsentive.getMiseCd());
                mstInsentive4.setMiseNameKj(mstInsentive.getMiseNameKj());
                mstInsentive4.setSibuCd(mstInsentive.getSibuCd());
                mstInsentive4.setSibuName(mstInsentive.getSibuName());
                mstInsentive4.setBlockCd(mstInsentive.getBlockCd());
                mstInsentive4.setBlockName(mstInsentive.getBlockName());
                mstInsentive4.setInsentive1(mstInsentive.getInsentive4());
                mstInsentive4.setInsentiveName1(mstInsentive.getInsentiveName4());
                mstInsentive4.setInsentive1Sta(mstInsentive.getInsentive4Sta());
                mstInsentive4.setInsentive1End(mstInsentive.getInsentive4End());
                mstInsentive4.setInsentive1Note(mstInsentive.getInsentive4Note());
                list.add(mstInsentive4);
            }

            if (!isNull(mstInsentive.getInsentive5())) {
                MstInsentive mstInsentive5 = new MstInsentive();
                mstInsentive5.setCompanyCd(mstInsentive.getCompanyCd());
                mstInsentive5.setMiseCd(mstInsentive.getMiseCd());
                mstInsentive5.setMiseNameKj(mstInsentive.getMiseNameKj());
                mstInsentive5.setSibuCd(mstInsentive.getSibuCd());
                mstInsentive5.setSibuName(mstInsentive.getSibuName());
                mstInsentive5.setBlockCd(mstInsentive.getBlockCd());
                mstInsentive5.setBlockName(mstInsentive.getBlockName());
                mstInsentive5.setInsentive1(mstInsentive.getInsentive5());
                mstInsentive5.setInsentiveName1(mstInsentive.getInsentiveName5());
                mstInsentive5.setInsentive1Sta(mstInsentive.getInsentive5Sta());
                mstInsentive5.setInsentive1End(mstInsentive.getInsentive5End());
                mstInsentive5.setInsentive1Note(mstInsentive.getInsentive5Note());
                list.add(mstInsentive5);
            }

        }

        // 支部コード、ブロックコード、店コード、
        // インセンティブコード、開始日をキーにソート
        list = sortList(list);

        // 支部コード、ブロックコード、店コード、
        // インセンティブコード、開始日をキーに重複を削除
        String oldKey = "";
        String newKey = "";
        for (Iterator ite = list.iterator(); ite.hasNext();) {
            MstInsentive mstInsentive = (MstInsentive) ite.next();
            newKey =
                mstInsentive.getSibuCd()
                + mstInsentive.getBlockCd()
                + mstInsentive.getMiseCd()
                + mstInsentive.getInsentive1()
                + mstInsentive.getInsentive1Sta();

            if (oldKey.equals(newKey)) {
                ite.remove();
            }
            else {
                oldKey = newKey;
            }
        }

        // 支部コード、ブロックコード、店コード、
        // インセンティブコード、開始日をキーにソート
        list = sortList(list);

        return list;
    }


    /**
     * 支部コード、ブロックコード、店コード、
     * インセンティブコード、開始日をキーにソート
     * @param list インセンティブ履歴リスト
     */
    private List sortList(List list){

        // 支部コード、ブロックコード、店コード、
        // インセンティブコード、開始日をキーにソート
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String val1 = ((MstInsentive) obj1).getSibuCd()
                            + ((MstInsentive) obj1).getBlockCd()
                            + ((MstInsentive) obj1).getMiseCd()
                            + ((MstInsentive) obj1).getInsentive1()
                            + ((MstInsentive) obj1).getInsentive1Sta();

                String val2 = ((MstInsentive) obj2).getSibuCd()
                            + ((MstInsentive) obj2).getBlockCd()
                            + ((MstInsentive) obj2).getMiseCd()
                            + ((MstInsentive) obj2).getInsentive1()
                            + ((MstInsentive) obj2).getInsentive1Sta();

                return val1.compareTo(val2);
            }
        };
        Collections.sort(list, sortComparator);

        return list;
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * 賃貸店舗履歴(MstChintai)Daoの設定
     * @return mstChintaiDao を戻します。
     */
    public MstChintaiDao getMstChintaiDao() {
        return mstChintaiDao;
    }
    /**
     * 賃貸店舗履歴(MstChintai)Daoの設定
     * @param MstChintaiDao mstChintaiDao を設定。
     */
    public void setMstChintaiDao(MstChintaiDao mstChintaiDao) {
        this.mstChintaiDao = mstChintaiDao;
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
    /**
     * HDC受賞履歴(MstHDC)Daoの設定
     * @return mstHDCDao を戻します。
     */
//    public MstHDCDao getMstHDCDao() {
//        return mstHDCDao;
//    }
    /**
     * HDC受賞履歴(MstHDC)Daoの設定
     * @param MstHDCDao mstHDCDao を設定。
     */
//    public void setMstHDCDao(MstHDCDao mstHDCDao) {
//        this.mstHDCDao = mstHDCDao;
//    }
    /**
     * インセンティブ履歴(MstInsentive)Daoの設定
     * @return mstInsentiveDao を戻します。
     */
//    public MstInsentiveDao getMstInsentiveDao() {
//        return mstInsentiveDao;
//    }
    /**
     * インセンティブ履歴(MstInsentive)Daoの設定
     * @param MstInsentiveDao mstInsentiveDao を設定。
     */
//    public void setMstInsentiveDao(MstInsentiveDao mstInsentiveDao) {
//        this.mstInsentiveDao = mstInsentiveDao;
//    }
    /**
     * 店舗拡張マスタ(MstMiseYobi)Daoの設定
     * @return mstMiseYobiDao を戻します。
     */
    public MstMiseYobiDao getMstMiseYobiDao() {
        return mstMiseYobiDao;
    }
    /**
     * 店舗拡張マスタ(MstMiseYobi)Daoの設定
     * @param MstMiseYobiDao mstMiseYobiDao を設定。
     */
    public void setMstMiseYobiDao(MstMiseYobiDao mstMiseYobiDao) {
        this.mstMiseYobiDao = mstMiseYobiDao;
    }
    /**
     * マスターライセンス結果状況(MstStaff)Daoの設定
     * @return mstStaffDao を戻します。
     */
    public MstStaffDao getMstStaffDao() {
        return mstStaffDao;
    }
    /**
     * マスターライセンス結果状況(MstStaff)Daoの設定
     * @param MstStaffDao mstStaffDao を設定。
     */
    public void setMstStaffDao(MstStaffDao mstStaffDao) {
        this.mstStaffDao = mstStaffDao;
    }
    /**
     * 地図URL情報(TrnURL)Daoの設定
     * @return trnURLDao を戻します。
     */
    public TrnURLDao getTrnURLDao() {
        return trnURLDao;
    }
    /**
     * 地図URL情報(TrnURL)Daoの設定
     * @param TrnURLDao trnURLDao を設定。
     */
    public void setTrnURLDao(TrnURLDao trnURLDao) {
        this.trnURLDao = trnURLDao;
    }


    public UITakuhaiMijissiDao getUiTakuhaiMijissiDao() {
        return uiTakuhaiMijissiDao;
    }


    public void setUiTakuhaiMijissiDao(UITakuhaiMijissiDao uiTakuhaiMijissiDao) {
        this.uiTakuhaiMijissiDao = uiTakuhaiMijissiDao;
    }
    /**
	 * @return クラス変数miseLumpExtractGasAirconDao を戻します。
	 */
	public GasAirconDao getMiseLumpExtractGasAirconDao() {
		return miseLumpExtractGasAirconDao;
	}


	/**
	 * @param miseLumpExtractGasAirconDao を クラス変数miseLumpExtractGasAirconDaoへ設定します。
	 */
	public void setMiseLumpExtractGasAirconDao(
			GasAirconDao miseLumpExtractGasAirconDao) {
		this.miseLumpExtractGasAirconDao = miseLumpExtractGasAirconDao;
	}

}
