/*
 * 作成日: 2006/3/20
 */
package jp.co.isid.mos.bird.bizsupport.plregist.logic.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.common.dao.TrnPLAuthorInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.logic.RenewPLDataLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.log.Logger;

/**
 * PLデータ更新処理ロジック
 * @author itamoto
 */
public class RenewPLDataLogicImpl implements RenewPLDataLogic {

    private static Logger logger_ = Logger.getLogger(RenewPLDataLogicImpl.class);
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS001L06";

    /**
     * PLデータ情報(TrnPLInfoDao)
     */
    private TrnPLInfoDao trnPLInfoDao;

    /**
     * PL作成者情報(TrnPLAuthorInfoDao)
     */
    private TrnPLAuthorInfoDao trnPLAuthorInfoDao;
    
	/**
	 * PLデータ情報(TrnPLInfoDao)の設定
	 * @return trnPLInfoDao を戻します。
	 */
	public TrnPLInfoDao getTrnPLInfoDao() {
		return trnPLInfoDao;
	}
	/**
	 * PLデータ情報(TrnPLInfoDao)の設定
	 * @param trnPLInfoDao trnPLInfoDao を設定。
	 */
	public void setTrnPLInfoDao(TrnPLInfoDao trnPLInfoDao) {
		this.trnPLInfoDao = trnPLInfoDao;
	}

	/**
	 * PL作成者情報(TrnPLAuthorInfoDao)の設定
	 * @return trnPLAuthorInfoDao を戻します。
	 */
	public TrnPLAuthorInfoDao getTrnPLAuthorInfoDao() {
		return trnPLAuthorInfoDao;
	}
	/**
	 * PL作成者情報(TrnPLAuthorInfoDao)の設定
	 * @param trnPLAuthorInfoDao trnPLAuthorInfoDao を設定。
	 */
	public void setTrnPLAuthorInfoDao(TrnPLAuthorInfoDao trnPLAuthorInfoDao) {
		this.trnPLAuthorInfoDao = trnPLAuthorInfoDao;
	}

	/**
     * PLデータを更新する
     * @param trnPLInfo
     * @param plRegistDto     
     * */
    public List execute(TrnPLInfo trnPLInfo, PlRegistDto plRegistDto) {

    	// P/Lデータ登録情報セット
    	trnPLInfo = setInfoTrnPLInfo(trnPLInfo, plRegistDto);

    	// １．Dao【PLデータ情報.PLデータの削除】を実行
    	// パラメータ：会社コード、店コード、PL種類、PL年月
    	trnPLInfoDao.deletePLInfo(trnPLInfo);

    	// ２．Dao【PLデータ情報.PLデータの挿入】を実行
    	// パラメータ：会社コード、店コード、PL種類、PL年月、システム日付
    	trnPLInfoDao.insertPLInfo(trnPLInfo);
    	
    	// P/L作成者情報セット
    	TrnPLAuthorInfo trnPLAuthorInfo = new TrnPLAuthorInfo();
    	trnPLAuthorInfo = setInfoTrnPLAuthorInfo(trnPLAuthorInfo, trnPLInfo, plRegistDto);

    	// ３．Dao【PL作成者情報.作成者情報の削除】を実行
    	// パラメータ：会社コード、オーナーコード、PL年月
    	trnPLAuthorInfoDao.deleteAuthor(trnPLAuthorInfo);
    	
    	// ４．Dao【PL作成者情報.作成者情報の挿入】を実行
    	// パラメータ：会社コード、オーナーコード、PL年月
    	trnPLAuthorInfoDao.insertAuthor(trnPLAuthorInfo);
    	
        return null;
    }
    
    /**
     * P/Lデータ登録情報セット
     * @param trnPLInfo
     * @param plRegistDto
     * @return TrnPLInfo
     */
    private TrnPLInfo setInfoTrnPLInfo(TrnPLInfo trnPLInfo, PlRegistDto plRegistDto) {

    	// 未設定数値項目設定
    	trnPLInfo.fillBlankItem(BigDecimal.valueOf(0));

    	// 登録情報セット
    	if (trnPLInfo.getMemo() == null) {
    		trnPLInfo.setMemo("");
    	}
    	// 更新日セット
    	trnPLInfo.setLastDt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
//    	trnPLInfo.setLastDt(plRegistDto.getBirdDateInfo().getSysDate());
    	// エラーフラグ設定
    	trnPLInfo.setErrFlg(isInputPlData(trnPLInfo) ? "9" : "0");
    	
    	// 会計事務所NULL時
    	if (trnPLInfo.getAuthOther() == null) {
        	trnPLInfo.setAuthOther("");
    	}

    	// 共通情報設定
    	trnPLInfo.setFirstUser(plRegistDto.getBirdUserInfo().getUserID());
    	trnPLInfo.setFirstPgm(plRegistDto.VIEW_ID);
    	trnPLInfo.setFirstTmsp(DateManager.getCurrentTimestamp());
    	trnPLInfo.setLastUser(plRegistDto.getBirdUserInfo().getUserID());
    	trnPLInfo.setLastPgm(plRegistDto.VIEW_ID);
    	trnPLInfo.setLastTmsp(DateManager.getCurrentTimestamp());
    	return trnPLInfo;
    }

    /**
     * P/L作成者情報セット
     * @param trnPLAuthorInfo
     * @param trnPLInfo
     * @return TrnPLAuthorInfo
     */
    private TrnPLAuthorInfo setInfoTrnPLAuthorInfo(
			TrnPLAuthorInfo trnPLAuthorInfo, TrnPLInfo trnPLInfo,
			PlRegistDto plRegistDto) {
    	// P/L作成者情報
    	trnPLAuthorInfo.setCompanyCd(trnPLInfo.getCompanyCd());
        trnPLAuthorInfo.setOnerCd(trnPLInfo.getOnerCd());

        trnPLAuthorInfo.setAuthDt(trnPLInfo.getAuthDt());
    	trnPLAuthorInfo.setAuthorName(trnPLInfo.getAuthorName());
    	trnPLAuthorInfo.setAuthOther((trnPLInfo.getAuthOther() == null) ? ""
				: trnPLInfo.getAuthOther());
    	trnPLAuthorInfo.setAuthPhoneNum(trnPLInfo.getAuthPhoneNum());
    	trnPLAuthorInfo.setKessanDt(plRegistDto.getKessanDt());

        // 共通情報設定
        trnPLAuthorInfo.setFirstPgm(trnPLInfo.getLastPgm());
        trnPLAuthorInfo.setFirstTmsp(trnPLInfo.getLastTmsp());
        trnPLAuthorInfo.setFirstUser(trnPLInfo.getLastUser());
    	trnPLAuthorInfo.setLastPgm(trnPLInfo.getLastPgm());
    	trnPLAuthorInfo.setLastTmsp(trnPLInfo.getLastTmsp());
    	trnPLAuthorInfo.setLastUser(trnPLInfo.getLastUser());
    	return trnPLAuthorInfo;
    }
    
    /**
     * P/Lデータ入力が未入力かどうか
     * @param trnPLInfo
     * @return
     */
    private boolean isInputPlData(TrnPLInfo trnPLInfo) {
    	if (isNoInput(trnPLInfo.getUriagedaka())
    			&& isNoInput(trnPLInfo.getUriagedaka())
				&& isNoInput(trnPLInfo.getUriagegenka())
				&& isNoInput(trnPLInfo.getUriageSoRieki())
				&& isNoInput(trnPLInfo.getSalary())
				&& isNoInput(trnPLInfo.getYachin())
				&& isNoInput(trnPLInfo.getSuikouHi())
				&& isNoInput(trnPLInfo.getRoyalty())
				&& isNoInput(trnPLInfo.getTesuryo())
				&& isNoInput(trnPLInfo.getKoukoku())
				&& isNoInput(trnPLInfo.getShoumou())
				&& isNoInput(trnPLInfo.getHouteiFukuri())
				&& isNoInput(trnPLInfo.getFukuriKousei())
				&& isNoInput(trnPLInfo.getKousai())
				&& isNoInput(trnPLInfo.getRyohi())
				&& isNoInput(trnPLInfo.getTusin())
				&& isNoInput(trnPLInfo.getLease())
				&& isNoInput(trnPLInfo.getSharyo())
				&& isNoInput(trnPLInfo.getSozei())
				&& isNoInput(trnPLInfo.getHoken())
				&& isNoInput(trnPLInfo.getUnchin())
				&& isNoInput(trnPLInfo.getShuzen())
				&& isNoInput(trnPLInfo.getYobi())
				&& isNoInput(trnPLInfo.getZappi())
				&& isNoInput(trnPLInfo.getKeihiShokei())
				&& isNoInput(trnPLInfo.getShokyakuRieki())
				&& isNoInput(trnPLInfo.getGenkaShokyaku())
				&& isNoInput(trnPLInfo.getEigaiShueki())
				&& isNoInput(trnPLInfo.getEigaiHiyo())
				&& isNoInput(trnPLInfo.getHonshahiHai())
				&& isNoInput(trnPLInfo.getRieki())
				&& isNoInput(trnPLInfo.getUriage())
				&& isNoInput(trnPLInfo.getBuppan())
				&& isNoInput(trnPLInfo.getUriUchiwake())
				&& isNoInput(trnPLInfo.getElec())
				&& isNoInput(trnPLInfo.getGas())
				&& isNoInput(trnPLInfo.getWater())
				&& isNoInput(trnPLInfo.getSonota())
				&& isNoInput(trnPLInfo.getSuikouUchiwake())
				&& isNoInput(trnPLInfo.getGenzairyoKei())
				&& isNoInput(trnPLInfo.getGenzairyoShire())
				&& isNoInput(trnPLInfo.getGenzairyoZaiko())
				&& isNoInput(trnPLInfo.getYasaiKei())
				&& isNoInput(trnPLInfo.getYasaiShire())
				&& isNoInput(trnPLInfo.getYasaiZaiko())
				&& isNoInput(trnPLInfo.getHouzaiKei())
				&& isNoInput(trnPLInfo.getHouzaiShire())
				&& isNoInput(trnPLInfo.getHouzaiZaiko())
				&& isNoInput(trnPLInfo.getBuppanKei())
				&& isNoInput(trnPLInfo.getBuppanShire())
				&& isNoInput(trnPLInfo.getBuppanZaiko())
				&& isNoInput(trnPLInfo.getTouSiireKei())
				&& isNoInput(trnPLInfo.getTouZaikoKei())
				&& isNoInput(trnPLInfo.getSashihikiKei())
				&& isNoInput(trnPLInfo.getYakuinSalary())
				&& isNoInput(trnPLInfo.getYakuinBonus())
				&& isNoInput(trnPLInfo.getYakuinRetire())
				&& isNoInput(trnPLInfo.getYakuinKei())
				&& isNoInput(trnPLInfo.getSalarySalary())
				&& isNoInput(trnPLInfo.getSalaryBonus())
				&& isNoInput(trnPLInfo.getSalaryRetire())
				&& isNoInput(trnPLInfo.getSalaryKei())
				&& isNoInput(trnPLInfo.getZakkyuSalary())
				&& isNoInput(trnPLInfo.getZakkyuBonus())
				&& isNoInput(trnPLInfo.getZakkyuRetire())
				&& isNoInput(trnPLInfo.getZakkyuKei())
				&& isNoInput(trnPLInfo.getKyuryoKei())
				&& isNoInput(trnPLInfo.getBonusKei())
				&& isNoInput(trnPLInfo.getRetireKei())
				&& isNoInput(trnPLInfo.getSalaryUtiKei())
				&& isNoInput(trnPLInfo.getKashiireUp())
				&& isNoInput(trnPLInfo.getKashiireDown())
				&& isNoInput(trnPLInfo.getKashiireZandaka())
				&& isNoInput(trnPLInfo.getKappuUp())
				&& isNoInput(trnPLInfo.getKappuDown())
				&& isNoInput(trnPLInfo.getKappuZandaka())
				&& isNoInput(trnPLInfo.getLeaseUp())
				&& isNoInput(trnPLInfo.getLeaseDown())
				&& isNoInput(trnPLInfo.getLeaseZandaka())
				&& isNoInput(trnPLInfo.getTouZoukaKei())
				&& isNoInput(trnPLInfo.getTouGenshoKei())
				&& isNoInput(trnPLInfo.getTouZandakaKei())) {
    		return true;
    	}
    	return false;
    }

    /**
     * 未入力チェック
     * @param val
     * @return
     */
    private boolean isNoInput(BigDecimal val) {
    	if (isNull(val)) {
    		return true;
    	}
    	if (isZero(val)) {
    		return true;
    	}
    	return false;
    }
    
    /**
	 * nullチェック
	 */
    private boolean isNull(BigDecimal val) {
        if (val == null) {
            return true;
        }
        return false;
    }
    /**
	 * Zeroチェック
	 */
    private boolean isZero(BigDecimal val) {
		if (val != null && val.compareTo(BigDecimal.valueOf(0)) == 0) {
			return true;
		}
		return false;
	}
}
