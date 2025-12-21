/*
 * 作成日: 2006/03/20
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLAuthorInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.RegistPLDataLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * P/Lデータ登録ロジック
 * 
 * @author xyuchida
 */
public class RegistPLDataLogicImpl implements RegistPLDataLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L03";

    private static final String SCREENID_LUMP_TAKE_IN = "BBS002";

    /**
     * P/LデータDAO
     */
    private TrnPLInfoDao trnPLInfoDao;

    /**
     * P/Lデータ作成者情報DAO
     */
    private TrnPLAuthorInfoDao trnPLAuthorInfoDao;

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
     * P/Lデータ作成者情報DAOを取得します。
     * @return P/Lデータ作成者情報DAO
     */
    public TrnPLAuthorInfoDao getTrnPLAuthorInfoDao() {
        return trnPLAuthorInfoDao;
    }

    /**
     * P/Lデータ作成者情報DAOを設定します。
     * @param trnPLAuthorInfoDao P/Lデータ作成者情報DAO
     */
    public void setTrnPLAuthorInfoDao(TrnPLAuthorInfoDao trnPLAuthorInfoDao) {
        this.trnPLAuthorInfoDao = trnPLAuthorInfoDao;
    }

    /**
     * P/Lデータ登録
     * @param userId ユーザID
     * @param trnPLInfo P/Lデータエンティティ
     */
    public void execute(String userId, String sysDate, LumpTakeInDto lumpTakeInDto, TrnPLInfo trnPLInfo) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        /** P/Lデータ登録 */
        // 更新日設定
        trnPLInfo.setLastDt((new SimpleDateFormat("yyyyMMdd")).format(new Date()));

        // 共通情報設定
        trnPLInfo.setFirstUser(userId);
        trnPLInfo.setFirstPgm(SCREENID_LUMP_TAKE_IN);
        trnPLInfo.setFirstTmsp(currentTimestamp);
        trnPLInfo.setLastUser(userId);
        trnPLInfo.setLastPgm(SCREENID_LUMP_TAKE_IN);
        trnPLInfo.setLastTmsp(currentTimestamp);

        // エラーフラグ設定
        String errFlg = "0";
        if (trnPLInfo.getPlDataErrorInfo().isExistErrorAllZero()) {
            errFlg = "9";
        } else if (trnPLInfo.getPlDataErrorInfo().isExistError()) {
            errFlg = "1";
        }
        trnPLInfo.setErrFlg(errFlg);

        // 桁数オーバー項目0設定
        fillLengthOverItem(trnPLInfo);

        // 未設定数値項目0設定
        trnPLInfo.fillBlankItem();

        // delete
        getTrnPLInfoDao().deletePLInfo(trnPLInfo);

        // insert
        getTrnPLInfoDao().insertPLInfo(trnPLInfo);

        /** P/L作成者情報登録 */
        // P/L作成者エンティティ生成
        TrnPLAuthorInfo trnPLAuthorInfo = new TrnPLAuthorInfo();
        trnPLAuthorInfo.setCompanyCd(trnPLInfo.getCompanyCd());
        trnPLAuthorInfo.setOnerCd(trnPLInfo.getOnerCd());
        trnPLAuthorInfo.setAuthorName(trnPLInfo.getAuthorName());
        trnPLAuthorInfo.setAuthDt(trnPLInfo.getAuthDt());
        trnPLAuthorInfo.setAuthPhoneNum(trnPLInfo.getAuthPhoneNum());
        trnPLAuthorInfo.setAuthOther(trnPLInfo.getAuthOther());
        trnPLAuthorInfo.setKessanDt(lumpTakeInDto.getKessanDt());

        // 共通情報設定
        trnPLAuthorInfo.setFirstUser(userId);
        trnPLAuthorInfo.setFirstPgm(SCREENID_LUMP_TAKE_IN);
        trnPLAuthorInfo.setFirstTmsp(currentTimestamp);
        trnPLAuthorInfo.setLastUser(userId);
        trnPLAuthorInfo.setLastPgm(SCREENID_LUMP_TAKE_IN);
        trnPLAuthorInfo.setLastTmsp(currentTimestamp);

        // delete
        getTrnPLAuthorInfoDao().deleteAuthor(trnPLAuthorInfo);

        // insert
        getTrnPLAuthorInfoDao().insertAuthor(trnPLAuthorInfo);
    }

    private void fillLengthOverItem(TrnPLInfo trnPLInfo) {

        BigDecimal zero = BigDecimal.valueOf(0);
        PlDataErrorInfo plDataErrorInfo = trnPLInfo.getPlDataErrorInfo();

        trnPLInfo.setUriagedaka((plDataErrorInfo.isErrorItem(TrnPLInfo.uriagedaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getUriagedaka());
        trnPLInfo.setUriagegenka((plDataErrorInfo.isErrorItem(TrnPLInfo.uriagegenka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getUriagegenka());
        trnPLInfo.setUriageSoRieki((plDataErrorInfo.isErrorItem(TrnPLInfo.uriageSoRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getUriageSoRieki());
        trnPLInfo.setSalary((plDataErrorInfo.isErrorItem(TrnPLInfo.salary_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSalary());
        trnPLInfo.setYachin((plDataErrorInfo.isErrorItem(TrnPLInfo.yachin_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYachin());
        trnPLInfo.setSuikouHi((plDataErrorInfo.isErrorItem(TrnPLInfo.suikouHi_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSuikouHi());
        trnPLInfo.setRoyalty((plDataErrorInfo.isErrorItem(TrnPLInfo.royalty_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getRoyalty());
        trnPLInfo.setTesuryo((plDataErrorInfo.isErrorItem(TrnPLInfo.tesuryo_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTesuryo());
        trnPLInfo.setKoukoku((plDataErrorInfo.isErrorItem(TrnPLInfo.koukoku_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKoukoku());
        trnPLInfo.setShoumou((plDataErrorInfo.isErrorItem(TrnPLInfo.shoumou_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getShoumou());
        trnPLInfo.setHouteiFukuri((plDataErrorInfo.isErrorItem(TrnPLInfo.houteiFukuri_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getHouteiFukuri());
        trnPLInfo.setFukuriKousei((plDataErrorInfo.isErrorItem(TrnPLInfo.fukuriKousei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getFukuriKousei());
        trnPLInfo.setKousai((plDataErrorInfo.isErrorItem(TrnPLInfo.kousai_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKousai());
        trnPLInfo.setRyohi((plDataErrorInfo.isErrorItem(TrnPLInfo.ryohi_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getRyohi());
        trnPLInfo.setTusin((plDataErrorInfo.isErrorItem(TrnPLInfo.tusin_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTusin());
        trnPLInfo.setLease((plDataErrorInfo.isErrorItem(TrnPLInfo.lease_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getLease());
        trnPLInfo.setSharyo((plDataErrorInfo.isErrorItem(TrnPLInfo.sharyo_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSharyo());
        trnPLInfo.setSozei((plDataErrorInfo.isErrorItem(TrnPLInfo.sozei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSozei());
        trnPLInfo.setHoken((plDataErrorInfo.isErrorItem(TrnPLInfo.hoken_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getHoken());
        trnPLInfo.setUnchin((plDataErrorInfo.isErrorItem(TrnPLInfo.unchin_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getUnchin());
        trnPLInfo.setShuzen((plDataErrorInfo.isErrorItem(TrnPLInfo.shuzen_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getShuzen());
        trnPLInfo.setYobi((plDataErrorInfo.isErrorItem(TrnPLInfo.yobi_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYobi());
        trnPLInfo.setZappi((plDataErrorInfo.isErrorItem(TrnPLInfo.zappi_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getZappi());
        trnPLInfo.setKeihiShokei((plDataErrorInfo.isErrorItem(TrnPLInfo.keihiShokei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKeihiShokei());
        trnPLInfo.setShokyakuRieki((plDataErrorInfo.isErrorItem(TrnPLInfo.shokyakuRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getShokyakuRieki());
        trnPLInfo.setGenkaShokyaku((plDataErrorInfo.isErrorItem(TrnPLInfo.genkaShokyaku_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getGenkaShokyaku());
        trnPLInfo.setEigaiShueki((plDataErrorInfo.isErrorItem(TrnPLInfo.eigaiShueki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getEigaiShueki());
        trnPLInfo.setEigaiHiyo((plDataErrorInfo.isErrorItem(TrnPLInfo.eigaiHiyo_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getEigaiHiyo());
        trnPLInfo.setHonshahiHai((plDataErrorInfo.isErrorItem(TrnPLInfo.honshahiHai_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getHonshahiHai());
        trnPLInfo.setRieki((plDataErrorInfo.isErrorItem(TrnPLInfo.rieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getRieki());

        trnPLInfo.setUriage((plDataErrorInfo.isErrorItem(TrnPLInfo.uriage_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getUriage());
        trnPLInfo.setBuppan((plDataErrorInfo.isErrorItem(TrnPLInfo.buppan_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getBuppan());
        trnPLInfo.setUriUchiwake((plDataErrorInfo.isErrorItem(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getUriUchiwake());
        trnPLInfo.setElec((plDataErrorInfo.isErrorItem(TrnPLInfo.elec_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getElec());
        trnPLInfo.setGas((plDataErrorInfo.isErrorItem(TrnPLInfo.gas_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getGas());
        trnPLInfo.setWater((plDataErrorInfo.isErrorItem(TrnPLInfo.water_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getWater());
        trnPLInfo.setSonota((plDataErrorInfo.isErrorItem(TrnPLInfo.sonota_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSonota());
        trnPLInfo.setSuikouUchiwake((plDataErrorInfo.isErrorItem(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSuikouUchiwake());
        trnPLInfo.setGenzairyoKei((plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getGenzairyoKei());
        trnPLInfo.setGenzairyoShire((plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoShire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getGenzairyoShire());
        trnPLInfo.setGenzairyoZaiko((plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoZaiko_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getGenzairyoZaiko());
        trnPLInfo.setYasaiKei((plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYasaiKei());
        trnPLInfo.setYasaiShire((plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiShire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYasaiShire());
        trnPLInfo.setYasaiZaiko((plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiZaiko_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYasaiZaiko());
        trnPLInfo.setHouzaiKei((plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getHouzaiKei());
        trnPLInfo.setHouzaiShire((plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiShire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getHouzaiShire());
        trnPLInfo.setHouzaiZaiko((plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiZaiko_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getHouzaiZaiko());
        trnPLInfo.setBuppanKei((plDataErrorInfo.isErrorItem(TrnPLInfo.buppanKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getBuppanKei());
        trnPLInfo.setBuppanShire((plDataErrorInfo.isErrorItem(TrnPLInfo.buppanShire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getBuppanShire());
        trnPLInfo.setBuppanZaiko((plDataErrorInfo.isErrorItem(TrnPLInfo.buppanZaiko_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getBuppanZaiko());
        trnPLInfo.setTouSiireKei((plDataErrorInfo.isErrorItem(TrnPLInfo.touSiireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTouSiireKei());
        trnPLInfo.setTouZaikoKei((plDataErrorInfo.isErrorItem(TrnPLInfo.touZaikoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTouZaikoKei());
        trnPLInfo.setSashihikiKei((plDataErrorInfo.isErrorItem(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSashihikiKei());
        trnPLInfo.setYakuinSalary((plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinSalary_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYakuinSalary());
        trnPLInfo.setYakuinBonus((plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinBonus_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYakuinBonus());
        trnPLInfo.setYakuinRetire((plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinRetire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYakuinRetire());
        trnPLInfo.setYakuinKei((plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getYakuinKei());
        trnPLInfo.setSalarySalary((plDataErrorInfo.isErrorItem(TrnPLInfo.salarySalary_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSalarySalary());
        trnPLInfo.setSalaryBonus((plDataErrorInfo.isErrorItem(TrnPLInfo.salaryBonus_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSalaryBonus());
        trnPLInfo.setSalaryRetire((plDataErrorInfo.isErrorItem(TrnPLInfo.salaryRetire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSalaryRetire());
        trnPLInfo.setSalaryKei((plDataErrorInfo.isErrorItem(TrnPLInfo.salaryKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSalaryKei());
        trnPLInfo.setZakkyuSalary((plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuSalary_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getZakkyuSalary());
        trnPLInfo.setZakkyuBonus((plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuBonus_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getZakkyuBonus());
        trnPLInfo.setZakkyuRetire((plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuRetire_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getZakkyuRetire());
        trnPLInfo.setZakkyuKei((plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getZakkyuKei());
        trnPLInfo.setKyuryoKei((plDataErrorInfo.isErrorItem(TrnPLInfo.kyuryoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKyuryoKei());
        trnPLInfo.setBonusKei((plDataErrorInfo.isErrorItem(TrnPLInfo.bonusKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getBonusKei());
        trnPLInfo.setRetireKei((plDataErrorInfo.isErrorItem(TrnPLInfo.retireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getRetireKei());
        trnPLInfo.setSalaryUtiKei((plDataErrorInfo.isErrorItem(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getSalaryUtiKei());

        trnPLInfo.setKashiireUp((plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireUp_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKashiireUp());
        trnPLInfo.setKashiireDown((plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireDown_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKashiireDown());
        trnPLInfo.setKashiireZandaka((plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKashiireZandaka());
        trnPLInfo.setKappuUp((plDataErrorInfo.isErrorItem(TrnPLInfo.kappuUp_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKappuUp());
        trnPLInfo.setKappuDown((plDataErrorInfo.isErrorItem(TrnPLInfo.kappuDown_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKappuDown());
        trnPLInfo.setKappuZandaka((plDataErrorInfo.isErrorItem(TrnPLInfo.kappuZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getKappuZandaka());
        trnPLInfo.setLeaseUp((plDataErrorInfo.isErrorItem(TrnPLInfo.leaseUp_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getLeaseUp());
        trnPLInfo.setLeaseDown((plDataErrorInfo.isErrorItem(TrnPLInfo.leaseDown_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getLeaseDown());
        trnPLInfo.setLeaseZandaka((plDataErrorInfo.isErrorItem(TrnPLInfo.leaseZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getLeaseZandaka());
        trnPLInfo.setTouZoukaKei((plDataErrorInfo.isErrorItem(TrnPLInfo.touZoukaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTouZoukaKei());
        trnPLInfo.setTouGenshoKei((plDataErrorInfo.isErrorItem(TrnPLInfo.touGenshoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTouGenshoKei());
        trnPLInfo.setTouZandakaKei((plDataErrorInfo.isErrorItem(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_INVALID)) ? zero : trnPLInfo.getTouZandakaKei());
    }
}
