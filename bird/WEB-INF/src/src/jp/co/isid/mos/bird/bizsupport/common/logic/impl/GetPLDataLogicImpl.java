/*
 * 作成日: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.dao.TrnPosZenUriageDao;
import jp.co.isid.mos.bird.bizsupport.common.dao.TrnZenPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLDataLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.log.Logger;

/**
 * PLデータの取得ロジック
 * @author itamoto
 */
public class GetPLDataLogicImpl implements GetPLDataLogic {

    private static Logger logger_ = Logger.getLogger(GetPLDataLogicImpl.class);

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS001L03";

    /* MSTUserMiseInfoDao */
    private TrnPLInfoDao trnPLInfoDao;
    /* TrnZenPLInfoDao */
    private TrnZenPLInfoDao trnZenPLInfoDao;
    /* TrnPosZenUriageDao */
    private TrnPosZenUriageDao trnPosZenUriageDao;
    
	/**
	 * PLデータの取得Daoの設定
	 * @return trnPLInfoDao を戻します。
	 */
	public TrnPLInfoDao getTrnPLInfoDao() {
		return trnPLInfoDao;
	}
	/**
	 * PLデータの取得Daoの設定
	 * @param trnPLInfoDao trnPLInfoDao を設定。
	 */
	public void setTrnPLInfoDao(TrnPLInfoDao trnPLInfoDao) {
		this.trnPLInfoDao = trnPLInfoDao;
	}

	/**
	 * 前月PLデータ情報Daoの設定
	 * @return trnZenPLInfoDao を戻します。
	 */
	public TrnZenPLInfoDao getTrnZenPLInfoDao() {
		return trnZenPLInfoDao;
	}
	/**
	 * 前月PLデータ情報Daoの設定
	 * @param trnZenPLInfoDao trnZenPLInfoDao を設定。
	 */
	public void setTrnZenPLInfoDao(TrnZenPLInfoDao trnZenPLInfoDao) {
		this.trnZenPLInfoDao = trnZenPLInfoDao;
	}
	
	/**
	 * POS前月売上取得Daoの設定
	 * @return trnPosZenUriageDao を戻します。
	 */
	public TrnPosZenUriageDao getTrnPosZenUriageDao() {
		return trnPosZenUriageDao;
	}
	/**
	 * POS前月売上取得Daoの設定
	 * @param trnPosZenUriageDao trnPosZenUriageDao を設定。
	 */
	public void setTrnPosZenUriageDao(TrnPosZenUriageDao trnPosZenUriageDao) {
		this.trnPosZenUriageDao = trnPosZenUriageDao;
	}

	/**
     * PLデータを取得する
     * @param plRegistDto
     */
    public List execute(PlRegistDto plRegistDto) {
    	
    	// １．Dao【PLデータ情報.PLデータの取得】を実行する。
    	// パラメータ：店コード、対象年月、PL種類
    	String miseCd = null;
    	String plYm = null;
		String plType = null;
		
		// 店舗P/L選択の場合
		if (plRegistDto.getPlFlg() == 0) {
			miseCd = plRegistDto.getStorePlMiseCd();
			plType = "1";
		}
		// 本社P/L選択の場合
		if (plRegistDto.getPlFlg() == 1) {
			miseCd = plRegistDto.getHeadOfficePlOnerCd();
			plType = "0";
		}
		plYm = plRegistDto.getTargetYM();		
		plRegistDto.setTrnPLInfo(trnPLInfoDao.getPLInfo(miseCd, plYm, plType));
    	
        // 対象年月取得
        DateFormatter formatter = new DateFormatter();
        String month;
		try {
			month = DateManager.getPrevMonth(plYm, 1);
		} catch (Exception e) {
            throw new FtlSystemException("P/Lデータ取得対象年月 - 1");
		}
        String prePlYm = formatter.format(month,
				DateFormatter.PATTERN_MONTH,
				DateFormatter.DATE_TYPE_YM); 
		
    	// ２．Dao【前月PLデータ情報.前月PLデータの取得】を実行する。
    	// パラメータ：店コード、対象年月、PL種類
		plRegistDto.setTrnZenPLInfo(trnZenPLInfoDao.getZenPLInfo(miseCd, prePlYm,
				plType));
    	// A.データが存在した場合
		if (plRegistDto.getTrnZenPLInfo() != null) {
			// [前月PLデータ情報]を各項目にセットする
		} 
    	// B.データが存在しない場合
		else {
			// 各項目を「0」で設定する。
		}
		
		// ３．Dao【POS前月売上.POS前月売上の取得】を実行する。
    	// パラメータ：店コード、対象年月、PL種類
        String formatPlYm = formatter.format(plYm,
				DateFormatter.PATTERN_MONTH,
				DateFormatter.DATE_TYPE_YM); 
		
		plRegistDto.setTrnPosZenUriage(trnPosZenUriageDao.getPosZenUriage(formatPlYm,
				miseCd));
		
		return null;
    }

	/**
     * 前月PLデータを取得する(P/LデータCSV一括取込用)
     * @param plRegistDto
     */
    public List executeByPlCsv(PlRegistDto plRegistDto) {
    	
    	// １．Dao【PLデータ情報.PLデータの取得】を実行する。
    	// パラメータ：店コード、対象年月、PL種類
    	String miseCd = null;
    	String plYm = null;
		String plType = null;
		
		plYm = plRegistDto.getTrnPLInfo().getPlYm();		
		miseCd = plRegistDto.getTrnPLInfo().getMiseCd();
		plType = plRegistDto.getTrnPLInfo().getPlType();
    	
    	// ２．Dao【前月PLデータ情報.前月PLデータの取得】を実行する。
    	// パラメータ：店コード、対象年月、PL種類
		plRegistDto.setTrnZenPLInfo(trnZenPLInfoDao.getZenPLInfo(miseCd, plYm,
				plType));
    	// A.データが存在した場合
		if (plRegistDto.getTrnZenPLInfo() != null) {
			// [前月PLデータ情報]を各項目にセットする
		} 
    	// B.データが存在しない場合
		else {
			// 各項目を「0」で設定する。
		}
		return null;
    }
}
