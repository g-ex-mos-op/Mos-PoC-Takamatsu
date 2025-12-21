/*
 * 作成日: 2006/03/15
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.CtlPLLimitDao;
import jp.co.isid.mos.bird.bizsupport.common.util.PlLimitUtils;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnMonthUriageInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLLimit;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.CheckPLDataLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * P/Lデータチェックロジック
 * 
 * @author xyuchida
 */
public class CheckPLDataLogicImpl implements CheckPLDataLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L02";

    protected static final int MAX_LENGTH = 11;
    protected static final String LIMIT_FLG_MAX = "0";
    protected static final String LIMIT_FLG_MIN = "1";

    /**
     * P/LデータDAO
     */
    private TrnPLInfoDao trnPLInfoDao;

    /**
     * 売上情報DAO
     */
    private TrnMonthUriageInfoDao trnMonthUriageInfoDao;

    /**
     * P/Lデータ上下限値DAO
     */
    private CtlPLLimitDao ctlPLLimitDao;

    /**
     * P/LデータDAOを取得します。
     * @return P/LデータDAO
     */
    public TrnPLInfoDao getTrnPLInfoDao() {
        return trnPLInfoDao;
    }

    /**
     * P/LデータDAOを設定します。
     * @param trnPLInfoDao P/LデータDAO
     */
    public void setTrnPLInfoDao(TrnPLInfoDao trnPLInfoDao) {
        this.trnPLInfoDao = trnPLInfoDao;
    }

    /**
     * 売上情報DAODAOを取得します。
     * @return 売上情報DAO
     */
    public TrnMonthUriageInfoDao getTrnMonthUriageInfoDao() {
        return trnMonthUriageInfoDao;
    }

    /**
     * 売上情報DAOを設定します。
     * @param trnMonthUriageInfoDao 売上情報DAO
     */
    public void setTrnMonthUriageInfoDao(
            TrnMonthUriageInfoDao trnMonthUriageInfoDao) {
        this.trnMonthUriageInfoDao = trnMonthUriageInfoDao;
    }

    /**
     * P/Lデータ上下限値DAOを取得します。
     * @return P/Lデータ上下限値DAO
     */
    public CtlPLLimitDao getCtlPLLimitDao() {
        return ctlPLLimitDao;
    }

    /**
     * P/Lデータ上下限値DAOを設定します。
     * @param trnPLLimitDao P/Lデータ上下限値DAO
     */
    public void setCtlPLLimitDao(CtlPLLimitDao ctlPLLimitDao) {
        this.ctlPLLimitDao = ctlPLLimitDao;
    }

    /**
     * P/Lデータチェック
     * @param P/Lデータ
     */
    public void execute(TrnPLInfo trnPLInfo) {

        PlDataErrorInfo plDataErrorInfo = trnPLInfo.getPlDataErrorInfo();

        /** 店コードチェック */
        // 店舗P/Lのみチェックを行う
        if (trnPLInfo.getPlType().equals("1")) {
            String miseCd = trnPLInfo.getMiseCd();
            // 必須チェック
            if (miseCd == null) {
                plDataErrorInfo.add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_REQUIRED);
            }
            // 店存在チェック
            if (getTrnPLInfoDao().getExistMiseCount(trnPLInfo.getOnerCd(), miseCd) == 0) {
                plDataErrorInfo.add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_INVALID);
            }
            // 店クローズチェック
            if (getTrnPLInfoDao().getNotClosedMiseCount(trnPLInfo.getPlYm(), miseCd) == 0) {
                plDataErrorInfo.add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_CLOSED);
            }
        }

        // P/Lデータ上下限値リスト取得
        List plLimitList = getCtlPLLimitDao().selectPlLimitAll();

        /** 1.売上高 ～ 30.当月利益 */
        // 数値妥当性チェック改
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriagedaka_COLUMN, trnPLInfo.getUriagedaka(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriagegenka_COLUMN, trnPLInfo.getUriagegenka(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriageSoRieki_COLUMN, trnPLInfo.getUriageSoRieki(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salary_COLUMN, trnPLInfo.getSalary(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yachin_COLUMN, trnPLInfo.getYachin(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.suikouHi_COLUMN, trnPLInfo.getSuikouHi(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.royalty_COLUMN, trnPLInfo.getRoyalty(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.tesuryo_COLUMN, trnPLInfo.getTesuryo(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.koukoku_COLUMN, trnPLInfo.getKoukoku(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.shoumou_COLUMN, trnPLInfo.getShoumou(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houteiFukuri_COLUMN, trnPLInfo.getHouteiFukuri(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.fukuriKousei_COLUMN, trnPLInfo.getFukuriKousei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kousai_COLUMN, trnPLInfo.getKousai(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.ryohi_COLUMN, trnPLInfo.getRyohi(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.tusin_COLUMN, trnPLInfo.getTusin(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.lease_COLUMN, trnPLInfo.getLease(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sharyo_COLUMN, trnPLInfo.getSharyo(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sozei_COLUMN, trnPLInfo.getSozei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.hoken_COLUMN, trnPLInfo.getHoken(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.unchin_COLUMN, trnPLInfo.getUnchin(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.shuzen_COLUMN, trnPLInfo.getShuzen(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yobi_COLUMN, trnPLInfo.getYobi(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zappi_COLUMN, trnPLInfo.getZappi(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.keihiShokei_COLUMN, trnPLInfo.getKeihiShokei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.shokyakuRieki_COLUMN, trnPLInfo.getShokyakuRieki(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genkaShokyaku_COLUMN, trnPLInfo.getGenkaShokyaku(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.eigaiShueki_COLUMN, trnPLInfo.getEigaiShueki(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.eigaiHiyo_COLUMN, trnPLInfo.getEigaiHiyo(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.honshahiHai_COLUMN, trnPLInfo.getHonshahiHai(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
        checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.rieki_COLUMN, trnPLInfo.getRieki(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);

        // POS売上チェック
        // 店舗P/Lのみチェックを行う
        if (trnPLInfo.getPlType().equals("1")) {
            // 対象年月POS売上取得
            BigDecimal sales = getTrnMonthUriageInfoDao().getMonthUriage(trnPLInfo.getPlYm(), trnPLInfo.getMiseCd());
            if (sales == null) {
                sales = BigDecimal.valueOf(0);
            }
            // 売上高
//            checkSales(plDataErrorInfo, TrnPLInfo.uriagedaka_COLUMN, trnPLInfo.getUriagedaka(), sales);
            if (!PlLimitUtils.checkPosDiff(trnPLInfo.getUriagedaka(), sales, plLimitList)) {
                plDataErrorInfo.add(TrnPLInfo.uriagedaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_SALES);
            }
        }

        // 相関チェック
        // 3.売上総利益 = 1.売上高 - 2.売上原価
        if (trnPLInfo.getUriageSoRieki() != null
                && trnPLInfo.getUriagedaka() != null
                && trnPLInfo.getUriagegenka() != null) {
            if (trnPLInfo.getUriageSoRieki()
                    .compareTo(trnPLInfo.getUriagedaka()
                    .subtract(trnPLInfo.getUriagegenka())) != 0) {
                plDataErrorInfo.add(TrnPLInfo.uriageSoRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
        }

        // 24.経費小計 = 4.給料手当 ～ 23.雑費
        if (trnPLInfo.getKeihiShokei() != null
                && trnPLInfo.getSalary() != null
                && trnPLInfo.getYachin() != null
                && trnPLInfo.getSuikouHi() != null
                && trnPLInfo.getRoyalty() != null
                && trnPLInfo.getTesuryo() != null
                && trnPLInfo.getKoukoku() != null
                && trnPLInfo.getShoumou() != null
                && trnPLInfo.getHouteiFukuri() != null
                && trnPLInfo.getFukuriKousei() != null
                && trnPLInfo.getKousai() != null
                && trnPLInfo.getRyohi() != null
                && trnPLInfo.getTusin() != null
                && trnPLInfo.getLease() != null
                && trnPLInfo.getSharyo() != null
                && trnPLInfo.getSozei() != null
                && trnPLInfo.getHoken() != null
                && trnPLInfo.getUnchin() != null
                && trnPLInfo.getShuzen() != null
                && trnPLInfo.getYobi() != null
                && trnPLInfo.getZappi() != null) {
            if (trnPLInfo.getKeihiShokei()
                    .compareTo(trnPLInfo.getSalary()
                    .add(trnPLInfo.getYachin())
                    .add(trnPLInfo.getSuikouHi())
                    .add(trnPLInfo.getRoyalty())
                    .add(trnPLInfo.getTesuryo())
                    .add(trnPLInfo.getKoukoku())
                    .add(trnPLInfo.getShoumou())
                    .add(trnPLInfo.getHouteiFukuri())
                    .add(trnPLInfo.getFukuriKousei())
                    .add(trnPLInfo.getKousai())
                    .add(trnPLInfo.getRyohi())
                    .add(trnPLInfo.getTusin())
                    .add(trnPLInfo.getLease())
                    .add(trnPLInfo.getSharyo())
                    .add(trnPLInfo.getSozei())
                    .add(trnPLInfo.getHoken())
                    .add(trnPLInfo.getUnchin())
                    .add(trnPLInfo.getShuzen())
                    .add(trnPLInfo.getYobi())
                    .add(trnPLInfo.getZappi())) != 0) {
                plDataErrorInfo.add(TrnPLInfo.keihiShokei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
        }

        // 25.償却前利益 = 3.売上総利益 - 24.経費小計
        if (trnPLInfo.getShokyakuRieki() != null
                && trnPLInfo.getUriageSoRieki() != null
                && trnPLInfo.getKeihiShokei() != null) {
            if (trnPLInfo.getShokyakuRieki()
                    .compareTo(trnPLInfo.getUriageSoRieki()
                    .subtract(trnPLInfo.getKeihiShokei())) != 0) {
                plDataErrorInfo.add(TrnPLInfo.shokyakuRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
        }

        // 30.当月利益 = 25.償却前利益 - 26.減価償却費 + 27.営業外収益 - 28.営業外費用 - 29. 本社費配賦
        if (trnPLInfo.getRieki() != null
                && trnPLInfo.getShokyakuRieki() != null
                && trnPLInfo.getGenkaShokyaku() != null
                && trnPLInfo.getEigaiShueki() != null
                && trnPLInfo.getEigaiHiyo() != null
                && trnPLInfo.getHonshahiHai() != null) {
            if (trnPLInfo.getRieki()
                    .compareTo(trnPLInfo.getShokyakuRieki()
                    .subtract(trnPLInfo.getGenkaShokyaku())
                    .add(trnPLInfo.getEigaiShueki())
                    .subtract(trnPLInfo.getEigaiHiyo())
                    .subtract(trnPLInfo.getHonshahiHai())) != 0) {
                plDataErrorInfo.add(TrnPLInfo.rieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
        }

        /** 31.売上 ～ 33.計 */
        // チェック要否判定
        if (isNeededCheckUriagedaka(trnPLInfo)) {
            // 数値妥当性チェック
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriage_COLUMN, trnPLInfo.getUriage(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppan_COLUMN, trnPLInfo.getBuppan(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriUchiwake_COLUMN, trnPLInfo.getUriUchiwake(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);

            // 相関チェック
            // 33.売上高合計 = 31.売上 + 32.物販売上
            if (trnPLInfo.getUriUchiwake() != null
                    && trnPLInfo.getUriage() != null
                    && trnPLInfo.getBuppan() != null) {
                if (trnPLInfo.getUriUchiwake()
                        .compareTo(trnPLInfo.getUriage()
                        .add(trnPLInfo.getBuppan())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 33.売上高合計 = 1.売上高
            if (trnPLInfo.getUriUchiwake() != null
                    && trnPLInfo.getUriagedaka() != null) {
                if (trnPLInfo.getUriUchiwake()
                        .compareTo(trnPLInfo.getUriagedaka()) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_DETAIL);
                }
            }
        } else {
            plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_NOINPUT);
        }

        /** 34.電気代 ～ 38.計 */
        if (isNeededCheckSuikouHi(trnPLInfo)) {
            // 数値妥当性チェック
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.elec_COLUMN, trnPLInfo.getElec(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.gas_COLUMN, trnPLInfo.getGas(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.water_COLUMN, trnPLInfo.getWater(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sonota_COLUMN, trnPLInfo.getSonota(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.suikouUchiwake_COLUMN, trnPLInfo.getSuikouUchiwake(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);

            // 相関チェック
            // 38.水道光熱費合計 = 34.電気代 + 35.ガス代 + 36.水道代 + 37.その他
            if (trnPLInfo.getSuikouUchiwake() != null
                    && trnPLInfo.getElec() != null
                    && trnPLInfo.getGas() != null
                    && trnPLInfo.getWater() != null
                    && trnPLInfo.getSonota() != null) {
                if (trnPLInfo.getSuikouUchiwake()
                        .compareTo(trnPLInfo.getElec()
                        .add(trnPLInfo.getGas())
                        .add(trnPLInfo.getWater())
                        .add(trnPLInfo.getSonota())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 38.水道光熱費合計 = 6.水道光熱費
            if (trnPLInfo.getSuikouUchiwake() != null
                    && trnPLInfo.getSuikouHi() != null) {
                if (trnPLInfo.getSuikouUchiwake()
                        .compareTo(trnPLInfo.getSuikouHi()) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_DETAIL);
                }
            }
        } else {
            plDataErrorInfo.add(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_NOINPUT);
        }

        // 前月在庫情報取得
        TrnPLInfo prevTrnPLInfo = getPrevPlInfo(trnPLInfo);

        /** 39.原材料 ～ 43.計 */
        if (isNeededCheckUriagegenka(trnPLInfo)) {
            // 数値妥当性チェック
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genzairyoKei_COLUMN, trnPLInfo.getGenzairyoKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genzairyoShire_COLUMN, trnPLInfo.getGenzairyoShire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genzairyoZaiko_COLUMN, trnPLInfo.getGenzairyoZaiko(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yasaiKei_COLUMN, trnPLInfo.getYasaiKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yasaiShire_COLUMN, trnPLInfo.getYasaiShire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yasaiZaiko_COLUMN, trnPLInfo.getYasaiZaiko(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houzaiKei_COLUMN, trnPLInfo.getHouzaiKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houzaiShire_COLUMN, trnPLInfo.getHouzaiShire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houzaiZaiko_COLUMN, trnPLInfo.getHouzaiZaiko(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppanKei_COLUMN, trnPLInfo.getBuppanKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppanShire_COLUMN, trnPLInfo.getBuppanShire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppanZaiko_COLUMN, trnPLInfo.getBuppanZaiko(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touSiireKei_COLUMN, trnPLInfo.getTouSiireKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touZaikoKei_COLUMN, trnPLInfo.getTouZaikoKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sashihikiKei_COLUMN, trnPLInfo.getSashihikiKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);

            // 相関チェック
            // 39.原材料 (d)差引売上原価 = (a)前月在庫 + (b)当月仕入 - (c)当月在庫
            if (trnPLInfo.getGenzairyoKei() != null
                    && prevTrnPLInfo.getGenzairyoZaiko() != null
                    && trnPLInfo.getGenzairyoShire() != null
                    && trnPLInfo.getGenzairyoZaiko() != null) {
                if (trnPLInfo.getGenzairyoKei()
                        .compareTo(prevTrnPLInfo.getGenzairyoZaiko()
                        .add(trnPLInfo.getGenzairyoShire())
                        .subtract(trnPLInfo.getGenzairyoZaiko())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.genzairyoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 40.野菜 (d)差引売上原価 = (a)前月在庫 + (b)当月仕入 - (c)当月在庫
            if (trnPLInfo.getYasaiKei() != null
                    && prevTrnPLInfo.getYasaiZaiko() != null
                    && trnPLInfo.getYasaiShire() != null
                    && trnPLInfo.getYasaiZaiko() != null) {
                if (trnPLInfo.getYasaiKei()
                        .compareTo(prevTrnPLInfo.getYasaiZaiko()
                        .add(trnPLInfo.getYasaiShire())
                        .subtract(trnPLInfo.getYasaiZaiko())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.yasaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 41.包材 (d)差引売上原価 = (a)前月在庫 + (b)当月仕入 - (c)当月在庫
            if (trnPLInfo.getHouzaiKei() != null
                    && prevTrnPLInfo.getHouzaiZaiko() != null
                    && trnPLInfo.getHouzaiShire() != null
                    && trnPLInfo.getHouzaiZaiko() != null) {
                if (trnPLInfo.getHouzaiKei()
                        .compareTo(prevTrnPLInfo.getHouzaiZaiko()
                        .add(trnPLInfo.getHouzaiShire())
                        .subtract(trnPLInfo.getHouzaiZaiko())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.houzaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 42.物販 (d)差引売上原価 = (a)前月在庫 + (b)当月仕入 - (c)当月在庫
            if (trnPLInfo.getBuppanKei() != null
                    && prevTrnPLInfo.getBuppanZaiko() != null
                    && trnPLInfo.getBuppanShire() != null
                    && trnPLInfo.getBuppanZaiko() != null) {
                if (trnPLInfo.getBuppanKei()
                        .compareTo(prevTrnPLInfo.getBuppanZaiko()
                        .add(trnPLInfo.getBuppanShire())
                        .subtract(trnPLInfo.getBuppanZaiko())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.buppanKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 43.計 (b)当月仕入 = 39b + 40b + 41b + 42b
            if (trnPLInfo.getTouSiireKei() != null
                    && trnPLInfo.getGenzairyoShire() != null
                    && trnPLInfo.getYasaiShire() != null
                    && trnPLInfo.getHouzaiShire() != null
                    && trnPLInfo.getBuppanShire() != null) {
                if (trnPLInfo.getTouSiireKei()
                        .compareTo(trnPLInfo.getGenzairyoShire()
                        .add(trnPLInfo.getYasaiShire())
                        .add(trnPLInfo.getHouzaiShire())
                        .add(trnPLInfo.getBuppanShire())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.touSiireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 43.計 (c)当月在庫 = 39c + 40c + 41c + 42c
            if (trnPLInfo.getTouZaikoKei() != null
                    && trnPLInfo.getGenzairyoZaiko() != null
                    && trnPLInfo.getYasaiZaiko() != null
                    && trnPLInfo.getHouzaiZaiko() != null
                    && trnPLInfo.getBuppanZaiko() != null) {
                if (trnPLInfo.getTouZaikoKei()
                        .compareTo(trnPLInfo.getGenzairyoZaiko()
                        .add(trnPLInfo.getYasaiZaiko())
                        .add(trnPLInfo.getHouzaiZaiko())
                        .add(trnPLInfo.getBuppanZaiko())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.touZaikoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 43.計 (d)差引売上原価 = 39d + 40d + 41d + 42d
            if (trnPLInfo.getSashihikiKei() != null
                    && trnPLInfo.getGenzairyoKei() != null
                    && trnPLInfo.getYasaiKei() != null
                    && trnPLInfo.getHouzaiKei() != null
                    && trnPLInfo.getBuppanKei() != null) {
                if (trnPLInfo.getSashihikiKei()
                        .compareTo(trnPLInfo.getGenzairyoKei()
                        .add(trnPLInfo.getYasaiKei())
                        .add(trnPLInfo.getHouzaiKei())
                        .add(trnPLInfo.getBuppanKei())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 43.計 (d)差引売上原価 = 2.売上原価
            if (trnPLInfo.getSashihikiKei() != null
                    && trnPLInfo.getUriagegenka() != null) {
                if (trnPLInfo.getSashihikiKei()
                        .compareTo(trnPLInfo.getUriagegenka()) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_DETAIL);
                }
            }
        } else {
            plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_NOINPUT);
        }

        /** 44.役員報酬 ～ 47.計 */
        if (isNeededCheckSalary(trnPLInfo)) {
            // 数値妥当性チェック
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinSalary_COLUMN, trnPLInfo.getYakuinSalary(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinBonus_COLUMN, trnPLInfo.getYakuinBonus(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinRetire_COLUMN, trnPLInfo.getYakuinRetire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinKei_COLUMN, trnPLInfo.getYakuinKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salarySalary_COLUMN, trnPLInfo.getSalarySalary(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryBonus_COLUMN, trnPLInfo.getSalaryBonus(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryRetire_COLUMN, trnPLInfo.getSalaryRetire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryKei_COLUMN, trnPLInfo.getSalaryKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuSalary_COLUMN, trnPLInfo.getZakkyuSalary(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuBonus_COLUMN, trnPLInfo.getZakkyuBonus(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuRetire_COLUMN, trnPLInfo.getZakkyuRetire(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuKei_COLUMN, trnPLInfo.getZakkyuKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kyuryoKei_COLUMN, trnPLInfo.getKyuryoKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.bonusKei_COLUMN, trnPLInfo.getBonusKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.retireKei_COLUMN, trnPLInfo.getRetireKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryUtiKei_COLUMN, trnPLInfo.getSalaryUtiKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);

            // 相関チェック
            // 44.役員報酬 (d)計 = (a)給料 + (b)賞与 + (c)退職金
            if (trnPLInfo.getYakuinKei() != null
                    && trnPLInfo.getYakuinSalary() != null
                    && trnPLInfo.getYakuinBonus() != null
                    && trnPLInfo.getYakuinRetire() != null) {
                if (trnPLInfo.getYakuinKei()
                        .compareTo(trnPLInfo.getYakuinSalary()
                        .add(trnPLInfo.getYakuinBonus())
                        .add(trnPLInfo.getYakuinRetire())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.yakuinKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 45.給料手当 (d)計 = (a)給料 + (b)賞与 + (c)退職金
            if (trnPLInfo.getSalaryKei() != null
                    && trnPLInfo.getSalarySalary() != null
                    && trnPLInfo.getSalaryBonus() != null
                    && trnPLInfo.getSalaryRetire() != null) {
                if (trnPLInfo.getSalaryKei()
                        .compareTo(trnPLInfo.getSalarySalary()
                        .add(trnPLInfo.getSalaryBonus())
                        .add(trnPLInfo.getSalaryRetire())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.salaryKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 46.雑給 (d)計 = (a)給料 + (b)賞与 + (c)退職金
            if (trnPLInfo.getZakkyuKei() != null
                    && trnPLInfo.getZakkyuSalary() != null
                    && trnPLInfo.getZakkyuBonus() != null
                    && trnPLInfo.getZakkyuRetire() != null) {
                if (trnPLInfo.getZakkyuKei()
                        .compareTo(trnPLInfo.getZakkyuSalary()
                        .add(trnPLInfo.getZakkyuBonus())
                        .add(trnPLInfo.getZakkyuRetire())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.zakkyuKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 47.計 (a)給料 = 44a + 45a + 46a
            if (trnPLInfo.getKyuryoKei() != null
                    && trnPLInfo.getYakuinSalary() != null
                    && trnPLInfo.getSalarySalary() != null
                    && trnPLInfo.getZakkyuSalary() != null) {
                if (trnPLInfo.getKyuryoKei()
                        .compareTo(trnPLInfo.getYakuinSalary()
                        .add(trnPLInfo.getSalarySalary())
                        .add(trnPLInfo.getZakkyuSalary())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.kyuryoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 47.計 (b)賞与 = 44b + 45b + 46b
            if (trnPLInfo.getBonusKei() != null
                    && trnPLInfo.getYakuinBonus() != null
                    && trnPLInfo.getSalaryBonus() != null
                    && trnPLInfo.getZakkyuBonus() != null) {
                if (trnPLInfo.getBonusKei()
                        .compareTo(trnPLInfo.getYakuinBonus()
                        .add(trnPLInfo.getSalaryBonus())
                        .add(trnPLInfo.getZakkyuBonus())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.bonusKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 47.計 (c)退職金 = 44c + 45c + 46c
            if (trnPLInfo.getRetireKei() != null
                    && trnPLInfo.getYakuinRetire() != null
                    && trnPLInfo.getSalaryRetire() != null
                    && trnPLInfo.getZakkyuRetire() != null) {
                if (trnPLInfo.getRetireKei()
                        .compareTo(trnPLInfo.getYakuinRetire()
                        .add(trnPLInfo.getSalaryRetire())
                        .add(trnPLInfo.getZakkyuRetire())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.retireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 47.計 (d)計 = 44d + 45d + 46d
            if (trnPLInfo.getSalaryUtiKei() != null
                    && trnPLInfo.getYakuinKei() != null
                    && trnPLInfo.getSalaryKei() != null
                    && trnPLInfo.getZakkyuKei() != null) {
                if (trnPLInfo.getSalaryUtiKei()
                        .compareTo(trnPLInfo.getYakuinKei()
                        .add(trnPLInfo.getSalaryKei())
                        .add(trnPLInfo.getZakkyuKei())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                }
            }

            // 47.計 (d)計 = 4.給料手当
            if (trnPLInfo.getSalaryUtiKei() != null
                    && trnPLInfo.getSalary() != null) {
                if (trnPLInfo.getSalaryUtiKei()
                        .compareTo(trnPLInfo.getSalary()) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_DETAIL);
                }
            }
        } else {
            plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_NOINPUT);
        }

        /** 48.借入金 - 51.計 */
        if (isNeededCheckKariire(trnPLInfo)) {
            // 数値妥当性チェック
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kashiireUp_COLUMN, trnPLInfo.getKashiireUp(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kashiireDown_COLUMN, trnPLInfo.getKashiireDown(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kashiireZandaka_COLUMN, trnPLInfo.getKashiireZandaka(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kappuUp_COLUMN, trnPLInfo.getKappuUp(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kappuDown_COLUMN, trnPLInfo.getKappuDown(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kappuZandaka_COLUMN, trnPLInfo.getKappuZandaka(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.leaseUp_COLUMN, trnPLInfo.getLeaseUp(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.leaseDown_COLUMN, trnPLInfo.getLeaseDown(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.leaseZandaka_COLUMN, trnPLInfo.getLeaseZandaka(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touZoukaKei_COLUMN, trnPLInfo.getTouZoukaKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touGenshoKei_COLUMN, trnPLInfo.getTouGenshoKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);
            checkValidKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touZandakaKei_COLUMN, trnPLInfo.getTouZandakaKei(), trnPLInfo.getUriagedaka(), MAX_LENGTH, plLimitList);

            // 相関チェック
            // 48.借入金 (d)当月残高 = (a)前月残高 + (b)当月増加 - (c)当月減少
            if (trnPLInfo.getKashiireZandaka() != null
                    && prevTrnPLInfo.getKashiireZandaka() != null
                    && trnPLInfo.getKashiireUp() != null
                    && trnPLInfo.getKashiireDown() != null) {
                if (trnPLInfo.getKashiireZandaka()
                        .compareTo(prevTrnPLInfo.getKashiireZandaka()
                        .add(trnPLInfo.getKashiireUp())
                        .subtract(trnPLInfo.getKashiireDown())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.kashiireZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }

            // 49.割賦未払金 (d)当月残高 = (a)前月残高 + (b)当月増加 - (c)当月減少
            if (trnPLInfo.getKappuZandaka() != null
                    && prevTrnPLInfo.getKappuZandaka() != null
                    && trnPLInfo.getKappuUp() != null
                    && trnPLInfo.getKappuDown() != null) {
                if (trnPLInfo.getKappuZandaka()
                        .compareTo(prevTrnPLInfo.getKappuZandaka()
                        .add(trnPLInfo.getKappuUp())
                        .subtract(trnPLInfo.getKappuDown())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.kappuZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }

            // 50.リース未払金 (d)当月残高 = (a)前月残高 + (b)当月増加 - (c)当月減少
            if (trnPLInfo.getLeaseZandaka() != null
                    && prevTrnPLInfo.getLeaseZandaka() != null
                    && trnPLInfo.getLeaseUp() != null
                    && trnPLInfo.getLeaseDown() != null) {
                if (trnPLInfo.getLeaseZandaka()
                        .compareTo(prevTrnPLInfo.getLeaseZandaka()
                        .add(trnPLInfo.getLeaseUp())
                        .subtract(trnPLInfo.getLeaseDown())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.leaseZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }

            // 51.計 (d)当月残高 = (a)前月残高 + (b)当月増加 - (c)当月減少
            if (trnPLInfo.getTouZandakaKei() != null
                    && prevTrnPLInfo.getTouZandakaKei() != null
                    && trnPLInfo.getTouZoukaKei() != null
                    && trnPLInfo.getTouGenshoKei() != null) {
                if (trnPLInfo.getTouZandakaKei()
                        .compareTo(prevTrnPLInfo.getTouZandakaKei()
                        .add(trnPLInfo.getTouZoukaKei())
                        .subtract(trnPLInfo.getTouGenshoKei())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }

            // 51.計 (b)当月増加 = 48b + 49b + 50b
            if (trnPLInfo.getTouZoukaKei() != null
                    && trnPLInfo.getKashiireUp() != null
                    && trnPLInfo.getKappuUp() != null
                    && trnPLInfo.getLeaseUp() != null) {
                if (trnPLInfo.getTouZoukaKei()
                        .compareTo(trnPLInfo.getKashiireUp()
                        .add(trnPLInfo.getKappuUp())
                        .add(trnPLInfo.getLeaseUp())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.touZoukaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }

            // 51.計 (c)当月減少 = 48c + 49c + 50c
            if (trnPLInfo.getTouGenshoKei() != null
                    && trnPLInfo.getKashiireDown() != null
                    && trnPLInfo.getKappuDown() != null
                    && trnPLInfo.getLeaseDown() != null) {
                if (trnPLInfo.getTouGenshoKei()
                        .compareTo(trnPLInfo.getKashiireDown()
                        .add(trnPLInfo.getKappuDown())
                        .add(trnPLInfo.getLeaseDown())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.touGenshoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }

            // 51.計 (d)当月残高 = 48d + 49d + 50d
            if (trnPLInfo.getTouZandakaKei() != null
                    && trnPLInfo.getKashiireZandaka() != null
                    && trnPLInfo.getKappuZandaka() != null
                    && trnPLInfo.getLeaseZandaka() != null) {
                if (trnPLInfo.getTouZandakaKei()
                        .compareTo(trnPLInfo.getKashiireZandaka()
                        .add(trnPLInfo.getKappuZandaka())
                        .add(trnPLInfo.getLeaseZandaka())) != 0) {
                    plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                }
            }
        } else {
            plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_NOINPUT);
        }

        // All Zeroチェック
        checkAllZero(trnPLInfo);
    }

    /**
     * 売上高内訳チェック要否判定
     * 31.売上 ～ 33.計
     * @param trnPLInfo P/Lデータ
     * @return 売上高内訳チェック要否
     */
    protected boolean isNeededCheckUriagedaka(TrnPLInfo trnPLInfo) {

        boolean result = false;
        BigDecimal zero = BigDecimal.valueOf(0);

        // 1項目でも0以外の値が入力されている場合チェック対象とする
        if ((trnPLInfo.getUriage() != null && trnPLInfo.getUriage().compareTo(zero) != 0)
                || (trnPLInfo.getBuppan() != null && trnPLInfo.getBuppan().compareTo(zero) != 0)
                || (trnPLInfo.getUriUchiwake() != null && trnPLInfo.getUriUchiwake().compareTo(zero) != 0)) {
            result = true;
        }

        return result;
    }

    /**
     * 水道光熱費内訳チェック要否判定
     * 34.電気代 ～ 38.計
     * @param trnPLInfo P/Lデータ
     * @return 水道光熱費内訳チェック要否
     */
    protected boolean isNeededCheckSuikouHi(TrnPLInfo trnPLInfo) {

        boolean result = false;
        BigDecimal zero = BigDecimal.valueOf(0);

        // 1項目でも0以外の値が入力されている場合チェック対象とする
        if ((trnPLInfo.getElec() != null && trnPLInfo.getElec().compareTo(zero) != 0)
                || (trnPLInfo.getGas() != null && trnPLInfo.getGas().compareTo(zero) != 0)
                || (trnPLInfo.getWater() != null && trnPLInfo.getWater().compareTo(zero) != 0)
                || (trnPLInfo.getSonota() != null && trnPLInfo.getSonota().compareTo(zero) != 0)
                || (trnPLInfo.getSuikouUchiwake() != null && trnPLInfo.getSuikouUchiwake().compareTo(zero) != 0)) {
            result = true;
        }

        return result;
    }

    /**
     * 売上原価内訳チェック要否判定
     * 39.原材料 ～ 43.計
     * @param trnPLInfo P/Lデータ
     * @return 売上原価内訳チェック要否
     */
    protected boolean isNeededCheckUriagegenka(TrnPLInfo trnPLInfo) {

        boolean result = false;
        BigDecimal zero = BigDecimal.valueOf(0);

        // 1項目でも0以外の値が入力されている場合チェック対象とする
        if ((trnPLInfo.getGenzairyoKei() != null && trnPLInfo.getGenzairyoKei().compareTo(zero) != 0)
                || (trnPLInfo.getGenzairyoShire() != null && trnPLInfo.getGenzairyoShire().compareTo(zero) != 0)
                || (trnPLInfo.getGenzairyoZaiko() != null && trnPLInfo.getGenzairyoZaiko().compareTo(zero) != 0)
                || (trnPLInfo.getYasaiKei() != null && trnPLInfo.getYasaiKei().compareTo(zero) != 0)
                || (trnPLInfo.getYasaiShire() != null && trnPLInfo.getYasaiShire().compareTo(zero) != 0)
                || (trnPLInfo.getYasaiZaiko() != null && trnPLInfo.getYasaiZaiko().compareTo(zero) != 0)
                || (trnPLInfo.getHouzaiKei() != null && trnPLInfo.getHouzaiKei().compareTo(zero) != 0)
                || (trnPLInfo.getHouzaiShire() != null && trnPLInfo.getHouzaiShire().compareTo(zero) != 0)
                || (trnPLInfo.getHouzaiZaiko() != null && trnPLInfo.getHouzaiZaiko().compareTo(zero) != 0)
                || (trnPLInfo.getBuppanKei() != null && trnPLInfo.getBuppanKei().compareTo(zero) != 0)
                || (trnPLInfo.getBuppanShire() != null && trnPLInfo.getBuppanShire().compareTo(zero) != 0)
                || (trnPLInfo.getBuppanZaiko() != null && trnPLInfo.getBuppanZaiko().compareTo(zero) != 0)
                || (trnPLInfo.getTouSiireKei() != null && trnPLInfo.getTouSiireKei().compareTo(zero) != 0)
                || (trnPLInfo.getTouZaikoKei() != null && trnPLInfo.getTouZaikoKei().compareTo(zero) != 0)
                || (trnPLInfo.getSashihikiKei() != null && trnPLInfo.getSashihikiKei().compareTo(zero) != 0)) {
            result = true;
        }

        return result;
    }

    /**
     * 給料手当内訳チェック要否判定
     * 44.役員報酬 ～ 47.計
     * @param trnPLInfo P/Lデータ
     * @return 給料手当内訳チェック要否
     */
    protected boolean isNeededCheckSalary(TrnPLInfo trnPLInfo) {

        boolean result = false;
        BigDecimal zero = BigDecimal.valueOf(0);

        // 1項目でも0以外の値が入力されている場合チェック対象とする
        if ((trnPLInfo.getYakuinSalary() != null && trnPLInfo.getYakuinSalary().compareTo(zero) != 0)
                || (trnPLInfo.getYakuinBonus() != null && trnPLInfo.getYakuinBonus().compareTo(zero) != 0)
                || (trnPLInfo.getYakuinRetire() != null && trnPLInfo.getYakuinRetire().compareTo(zero) != 0)
                || (trnPLInfo.getYakuinKei() != null && trnPLInfo.getYakuinKei().compareTo(zero) != 0)
                || (trnPLInfo.getSalarySalary() != null && trnPLInfo.getSalarySalary().compareTo(zero) != 0)
                || (trnPLInfo.getSalaryBonus() != null && trnPLInfo.getSalaryBonus().compareTo(zero) != 0)
                || (trnPLInfo.getSalaryRetire() != null && trnPLInfo.getSalaryRetire().compareTo(zero) != 0)
                || (trnPLInfo.getSalaryKei() != null && trnPLInfo.getSalaryKei().compareTo(zero) != 0)
                || (trnPLInfo.getZakkyuSalary() != null && trnPLInfo.getZakkyuSalary().compareTo(zero) != 0)
                || (trnPLInfo.getZakkyuBonus() != null && trnPLInfo.getZakkyuBonus().compareTo(zero) != 0)
                || (trnPLInfo.getZakkyuRetire() != null && trnPLInfo.getZakkyuRetire().compareTo(zero) != 0)
                || (trnPLInfo.getZakkyuKei() != null && trnPLInfo.getZakkyuKei().compareTo(zero) != 0)
                || (trnPLInfo.getKyuryoKei() != null && trnPLInfo.getKyuryoKei().compareTo(zero) != 0)
                || (trnPLInfo.getBonusKei() != null && trnPLInfo.getBonusKei().compareTo(zero) != 0)
                || (trnPLInfo.getRetireKei() != null && trnPLInfo.getRetireKei().compareTo(zero) != 0)
                || (trnPLInfo.getSalaryUtiKei() != null && trnPLInfo.getSalaryUtiKei().compareTo(zero) != 0)) {
            result = true;
        }

        return result;
    }

    /**
     * 借入金内訳チェック要否判定
     * 48.借入金 - 51.計
     * @param trnPLInfo P/Lデータ
     * @return 借入金内訳チェック要否
     */
    protected boolean isNeededCheckKariire(TrnPLInfo trnPLInfo) {

        boolean result = false;
        BigDecimal zero = BigDecimal.valueOf(0);

        // 1項目でも0以外の値が入力されている場合チェック対象とする
        if ((trnPLInfo.getKashiireUp() != null && trnPLInfo.getKashiireUp().compareTo(zero) != 0)
                || (trnPLInfo.getKashiireDown() != null && trnPLInfo.getKashiireDown().compareTo(zero) != 0)
                || (trnPLInfo.getKashiireZandaka() != null && trnPLInfo.getKashiireZandaka().compareTo(zero) != 0)
                || (trnPLInfo.getKappuUp() != null && trnPLInfo.getKappuUp().compareTo(zero) != 0)
                || (trnPLInfo.getKappuDown() != null && trnPLInfo.getKappuDown().compareTo(zero) != 0)
                || (trnPLInfo.getKappuZandaka() != null && trnPLInfo.getKappuZandaka().compareTo(zero) != 0)
                || (trnPLInfo.getLeaseUp() != null && trnPLInfo.getLeaseUp().compareTo(zero) != 0)
                || (trnPLInfo.getLeaseDown() != null && trnPLInfo.getLeaseDown().compareTo(zero) != 0)
                || (trnPLInfo.getLeaseZandaka() != null && trnPLInfo.getLeaseZandaka().compareTo(zero) != 0)
                || (trnPLInfo.getTouZoukaKei() != null && trnPLInfo.getTouZoukaKei().compareTo(zero) != 0)
                || (trnPLInfo.getTouGenshoKei() != null && trnPLInfo.getTouGenshoKei().compareTo(zero) != 0)
                || (trnPLInfo.getTouZandakaKei() != null && trnPLInfo.getTouZandakaKei().compareTo(zero) != 0)) {
            result = true;
        }

        return result;
    }


    /**
     * 前月P/Lデータ取得
     * @param trnPLInfo P/Lデータ
     * @return 前月P/Lデータエンティティ
     */
    protected TrnPLInfo getPrevPlInfo(TrnPLInfo trnPLInfo) {

        // 前月(YYYYMM形式)取得
        String prevPlYm = null;
        try {
            prevPlYm = DateManager.getPrevMonth(trnPLInfo.getPlYm(), 1);
        } catch (Exception e) {
            // 無処理
        }

        TrnPLInfo prevTrnPLInfo = null;

        if (prevPlYm != null) {
            prevTrnPLInfo = getTrnPLInfoDao().getPLInfo(
                    trnPLInfo.getPlType(), prevPlYm, trnPLInfo.getMiseCd());
        }

        // レコードが存在しない場合、初期値設定
        if (prevTrnPLInfo == null) {

            prevTrnPLInfo = new TrnPLInfo();

            // 39.原材料 ～ 43.計
            prevTrnPLInfo.setGenzairyoZaiko(BigDecimal.valueOf(0));
            prevTrnPLInfo.setYasaiZaiko(BigDecimal.valueOf(0));
            prevTrnPLInfo.setHouzaiZaiko(BigDecimal.valueOf(0));
            prevTrnPLInfo.setBuppanZaiko(BigDecimal.valueOf(0));
            prevTrnPLInfo.setTouZaikoKei(BigDecimal.valueOf(0));

            // 48.借入金 ～ 51.計
            prevTrnPLInfo.setKashiireZandaka(BigDecimal.valueOf(0));
            prevTrnPLInfo.setKappuZandaka(BigDecimal.valueOf(0));
            prevTrnPLInfo.setLeaseZandaka(BigDecimal.valueOf(0));
            prevTrnPLInfo.setTouZandakaKei(BigDecimal.valueOf(0));
        }

        return prevTrnPLInfo;
    }

    /**
     * 必須チェック
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param itemCode 項目名
     * @param value チェック対象数値
     */
    protected void checkRequire(
            PlDataErrorInfo plDataErrorInfo, String itemCode, BigDecimal value) {

        if (value == null) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_REQUIRED);
        }
    }

    /**
     * 桁数チェック
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param length 最大桁数
     */
    protected void checkLength(
            PlDataErrorInfo plDataErrorInfo, String itemCode, BigDecimal value, int length) {

        if (value != null && value.abs().setScale(0).toString().length() > length) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_INVALID);
        }
    }

    /**
     * 上下限チェック
//     * @param plDataErrorInfo P/Lデータエラー情報
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param limitValueList P/Lデータ上下限値リスト
     */
    protected void checkLimit(
            PlDataErrorInfo plDataErrorInfo, String itemCode, BigDecimal value, List limitValueList) {

        // 上下限値取得
        BigDecimal max = getUpperLimit(limitValueList, itemCode);
        BigDecimal min = getLowerLimit(limitValueList, itemCode);

        if (value != null && (value.compareTo(min) < 0 || value.compareTo(max) > 0)) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_LIMIT);
        }
    }

    /**
     * 数値妥当性チェック
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param length 最大桁数
     * @param limitValueList P/Lデータ上下限値リスト
     */
    protected void checkValid(PlDataErrorInfo plDataErrorInfo, String itemCode, BigDecimal value, int length, List limitValueList) {

        // 必須チェック
        checkRequire(plDataErrorInfo, itemCode, value);
        if (!plDataErrorInfo.isErrorItem(itemCode, PlDataErrorInfo.ERRORCODE_NUM_REQUIRED)) {
            // 桁数チェック
            checkLength(plDataErrorInfo, itemCode, value, length);
            // 上下限チェック
            checkLimit(plDataErrorInfo, itemCode, value, limitValueList);
        }
    }

    /**
     * 上下限チェック改
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param plType P/Lタイプ
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param uriagedaka 売上高数値
     * @param limitValueList P/Lデータ上下限値リスト
     */
    protected void checkLimitKai(
            PlDataErrorInfo plDataErrorInfo,
            String plType,
            String itemCode,
            BigDecimal value,
            BigDecimal uriagedaka,
            List limitValueList) {
        if (!PlLimitUtils.checkLimit(plType, itemCode, value, uriagedaka, limitValueList)) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_LIMIT);
        }
    }

    /**
     * 数値妥当性チェック改
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param plType P/Lタイプ
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param uriagedaka 売上高数値
     * @param length 最大桁数
     * @param limitValueList P/Lデータ上下限値リスト
     */
    protected void checkValidKai(
            PlDataErrorInfo plDataErrorInfo,
            String plType,
            String itemCode,
            BigDecimal value,
            BigDecimal uriagedaka,
            int length,
            List limitValueList) {

        // 必須チェック
        checkRequire(plDataErrorInfo, itemCode, value);
        if (!plDataErrorInfo.isErrorItem(itemCode, PlDataErrorInfo.ERRORCODE_NUM_REQUIRED)) {
            // 桁数チェック
            checkLength(plDataErrorInfo, itemCode, value, length);
            // 上下限チェック
            checkLimitKai(plDataErrorInfo, plType, itemCode, value, uriagedaka, limitValueList);
        }
    }

    /**
     * POS売上チェック
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param sales POS売上
     */
    protected void checkSales(
            PlDataErrorInfo plDataErrorInfo, String itemCode, BigDecimal value, BigDecimal sales) {

        if (value != null && value.compareTo(sales) != 0) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_SALES);
        }
    }

    /**
     * 上限値取得
     * @param limitValueList P/Lデータ上下限値リスト
     * @param columnName カラム名
     * @return 上限値
     */
    protected BigDecimal getUpperLimit(List limitValueList, String columnName) {
        return getLimitValue(limitValueList, columnName, LIMIT_FLG_MAX);
    }

    /**
     * 下限値取得
     * @param limitValueList P/Lデータ上下限値リスト
     * @param columnName カラム名
     * @return 下限値
     */
    protected BigDecimal getLowerLimit(List limitValueList, String columnName) {
        return getLimitValue(limitValueList, columnName, LIMIT_FLG_MIN);
    }

    /**
     * 上下限値取得
     * @param limitValueList P/Lデータ上下限値リスト
     * @param columnName カラム名
     * @param limitFlg 上下限フラグ = 0:上限値 1:下限値
     * @return 上下限値
     */
    protected BigDecimal getLimitValue(List limitValueList, String columnName, String limitFlg) {
        BigDecimal result = null;
        for (Iterator it = limitValueList.iterator(); it.hasNext();) {
            TrnPLLimit trnPLLimit = (TrnPLLimit) it.next();
            if (columnName.equalsIgnoreCase(trnPLLimit.getColumnName())
                    && limitFlg.equals(trnPLLimit.getLimitFlg())) {
                result = trnPLLimit.getLimit();
                break;
            }
        }
        return result;
    }

    /**
     * All Zeroチェック
     * @param trnPLInfo P/Lデータ
     */
    protected void checkAllZero(TrnPLInfo trnPLInfo) {
        if (isInvalidNumber(trnPLInfo.getUriagedaka())
                && isInvalidNumber(trnPLInfo.getUriagegenka())
                && isInvalidNumber(trnPLInfo.getUriageSoRieki())
                && isInvalidNumber(trnPLInfo.getSalary())
                && isInvalidNumber(trnPLInfo.getYachin())
                && isInvalidNumber(trnPLInfo.getSuikouHi())
                && isInvalidNumber(trnPLInfo.getRoyalty())
                && isInvalidNumber(trnPLInfo.getTesuryo())
                && isInvalidNumber(trnPLInfo.getKoukoku())
                && isInvalidNumber(trnPLInfo.getShoumou())
                && isInvalidNumber(trnPLInfo.getHouteiFukuri())
                && isInvalidNumber(trnPLInfo.getFukuriKousei())
                && isInvalidNumber(trnPLInfo.getKousai())
                && isInvalidNumber(trnPLInfo.getRyohi())
                && isInvalidNumber(trnPLInfo.getTusin())
                && isInvalidNumber(trnPLInfo.getLease())
                && isInvalidNumber(trnPLInfo.getSharyo())
                && isInvalidNumber(trnPLInfo.getSozei())
                && isInvalidNumber(trnPLInfo.getHoken())
                && isInvalidNumber(trnPLInfo.getUnchin())
                && isInvalidNumber(trnPLInfo.getShuzen())
                && isInvalidNumber(trnPLInfo.getYobi())
                && isInvalidNumber(trnPLInfo.getZappi())
                && isInvalidNumber(trnPLInfo.getKeihiShokei())
                && isInvalidNumber(trnPLInfo.getShokyakuRieki())
                && isInvalidNumber(trnPLInfo.getGenkaShokyaku())
                && isInvalidNumber(trnPLInfo.getEigaiShueki())
                && isInvalidNumber(trnPLInfo.getEigaiHiyo())
                && isInvalidNumber(trnPLInfo.getHonshahiHai())
                && isInvalidNumber(trnPLInfo.getRieki())
                && isInvalidNumber(trnPLInfo.getUriage())
                && isInvalidNumber(trnPLInfo.getBuppan())
                && isInvalidNumber(trnPLInfo.getUriUchiwake())
                && isInvalidNumber(trnPLInfo.getElec())
                && isInvalidNumber(trnPLInfo.getGas())
                && isInvalidNumber(trnPLInfo.getWater())
                && isInvalidNumber(trnPLInfo.getSonota())
                && isInvalidNumber(trnPLInfo.getSuikouUchiwake())
                && isInvalidNumber(trnPLInfo.getGenzairyoKei())
                && isInvalidNumber(trnPLInfo.getGenzairyoShire())
                && isInvalidNumber(trnPLInfo.getGenzairyoZaiko())
                && isInvalidNumber(trnPLInfo.getYasaiKei())
                && isInvalidNumber(trnPLInfo.getYasaiShire())
                && isInvalidNumber(trnPLInfo.getYasaiZaiko())
                && isInvalidNumber(trnPLInfo.getHouzaiKei())
                && isInvalidNumber(trnPLInfo.getHouzaiShire())
                && isInvalidNumber(trnPLInfo.getHouzaiZaiko())
                && isInvalidNumber(trnPLInfo.getBuppanKei())
                && isInvalidNumber(trnPLInfo.getBuppanShire())
                && isInvalidNumber(trnPLInfo.getBuppanZaiko())
                && isInvalidNumber(trnPLInfo.getTouSiireKei())
                && isInvalidNumber(trnPLInfo.getTouZaikoKei())
                && isInvalidNumber(trnPLInfo.getSashihikiKei())
                && isInvalidNumber(trnPLInfo.getYakuinSalary())
                && isInvalidNumber(trnPLInfo.getYakuinBonus())
                && isInvalidNumber(trnPLInfo.getYakuinRetire())
                && isInvalidNumber(trnPLInfo.getYakuinKei())
                && isInvalidNumber(trnPLInfo.getSalarySalary())
                && isInvalidNumber(trnPLInfo.getSalaryBonus())
                && isInvalidNumber(trnPLInfo.getSalaryRetire())
                && isInvalidNumber(trnPLInfo.getSalaryKei())
                && isInvalidNumber(trnPLInfo.getZakkyuSalary())
                && isInvalidNumber(trnPLInfo.getZakkyuBonus())
                && isInvalidNumber(trnPLInfo.getZakkyuRetire())
                && isInvalidNumber(trnPLInfo.getZakkyuKei())
                && isInvalidNumber(trnPLInfo.getKyuryoKei())
                && isInvalidNumber(trnPLInfo.getBonusKei())
                && isInvalidNumber(trnPLInfo.getRetireKei())
                && isInvalidNumber(trnPLInfo.getSalaryUtiKei())
                && isInvalidNumber(trnPLInfo.getKashiireUp())
                && isInvalidNumber(trnPLInfo.getKashiireDown())
                && isInvalidNumber(trnPLInfo.getKashiireZandaka())
                && isInvalidNumber(trnPLInfo.getKappuUp())
                && isInvalidNumber(trnPLInfo.getKappuDown())
                && isInvalidNumber(trnPLInfo.getKappuZandaka())
                && isInvalidNumber(trnPLInfo.getLeaseUp())
                && isInvalidNumber(trnPLInfo.getLeaseDown())
                && isInvalidNumber(trnPLInfo.getLeaseZandaka())
                && isInvalidNumber(trnPLInfo.getTouZoukaKei())
                && isInvalidNumber(trnPLInfo.getTouGenshoKei())
                && isInvalidNumber(trnPLInfo.getTouZandakaKei())) {
            trnPLInfo.getPlDataErrorInfo().add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID);
        }
    }

    /**
     * Zero値(みなし)判定
     * @param value チェック対象数値
     * @return 判定結果
     */
    protected boolean isInvalidNumber(BigDecimal value) {
        boolean result = false;
        if (value == null
                || value.compareTo(BigDecimal.valueOf(0)) == 0
                || value.abs().setScale(0).toString().length() > MAX_LENGTH) {
            result = true;
        }
        return result;
    }
}
