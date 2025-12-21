/*
 * 作成日: 2006/3/22
 */
package jp.co.isid.mos.bird.bizsupport.plregist.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.framework.log.Logger;

import jp.co.isid.mos.bird.bizsupport.common.dao.CtlPLLimitDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.util.PlLimitUtils;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.logic.CheckPLDataTabBetweenTabLogic;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;

/**
 * タブ間の整合性チェック
 * @author itamoto
 */
public class CheckPLDataTabBetweenTabLogicImpl implements CheckPLDataTabBetweenTabLogic {

    private static Logger logger_ = Logger.getLogger(CheckPLTabDataLogicImpl.class);
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS001L05";
    
    /**
     * P/Lデータ上下限値DAO
     */
    private CtlPLLimitDao ctlPLLimitDao;
    
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
     * タブ間の整合性チェックを行う。
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto     
     * */
    public List execute(TrnPLInfo trnPLInfo, TrnZenPLInfo trnZenPLInfo,
			PlRegistDto plRegistDto) {

        PlDataErrorInfo plDataErrorInfo = trnPLInfo.getPlDataErrorInfo();
//        plDataErrorInfo.clear();
        
        // P/Lデータ上下限値リスト取得
        List limitValueList = getCtlPLLimitDao().selectPlLimitAll();

        // POS売上チェック
        if (!trnPLInfo.getPlType().equals("0")) {

            // POS売上取得
            BigDecimal sales = BigDecimal.valueOf(0);
            if(plRegistDto.getTrnPosZenUriage()!=null) {
                sales = plRegistDto.getTrnPosZenUriage().getUriage();
            }
            if (!PlLimitUtils.checkPosDiff(trnPLInfo.getUriagedaka(), sales, limitValueList)) {
                plDataErrorInfo.add(TrnPLInfo.uriagedaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_SALES);
            }
        }
        
    	// ３１～３３の項目に１つでも入力されていたら、３１～３３もチェック対象とする
    	boolean checkFlg31to33 = true;
//    	boolean checkFlg31to33 = false;
    	if ((!isNull(trnPLInfo.getUriage()) && !isZero(trnPLInfo.getUriage()))
    	    	|| (!isNull(trnPLInfo.getBuppan()) && !isZero(trnPLInfo.getBuppan()))
    	    	|| (!isNull(trnPLInfo.getUriUchiwake()) && !isZero(trnPLInfo.getUriUchiwake()))) {
    		checkFlg31to33 = true;
    	}

    	// ■33=31+32成り立たないときは、[[PLエラー情報]]の項目【数値制御】に×をセットする
    	// 成り立たないときは、[[PL色情報]]の項目３１～３３にfalseをセットする
    	if (checkFlg31to33) {
	        if (trnPLInfo.getUriUchiwake()
	                .compareTo(trnPLInfo.getUriage()
	                .add(trnPLInfo.getBuppan())) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            plDataErrorInfo.add(TrnPLInfo.uriage_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            plDataErrorInfo.add(TrnPLInfo.buppan_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	        }
	        if (plDataErrorInfo.isExistError()) {
	        	// タブ位置指定
	        	plRegistDto.setSelectedTab(2);
//	        	throw new GenericCommentException("計算結果が不整合", "33　計");
	        }
    	}
    	
    	// ■33=1成り立たないときは、[[PLエラー情報]]の項目【数値制御】に×をセットする
    	// 成り立たないときは、[[PL色情報]]の項目【売上高】、【計】にfalseをセットする    	
    	if (checkFlg31to33) {
    		// 「オーナー：36387 ヴィアン様」はチェック処理無し #5277  2021.12.17 fukasawa
//    		if (trnPLInfo.getUriagedaka().compareTo(trnPLInfo.getUriUchiwake()) != 0) {
    		if (trnPLInfo.getUriagedaka().compareTo(trnPLInfo.getUriUchiwake()) != 0 && !trnPLInfo.getOnerCd().equals("36387")) {
    		plDataErrorInfo.add(TrnPLInfo.uriagedaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	        	// タブ位置指定
	        	plRegistDto.setSelectedTab(2);
//	        	throw new GenericCommentException("計算結果が不整合", "1 売上高 = 33 計");
    		}
    	}
    	
    	// ３４～３８の項目に１つでも入力されていたら、３４～３８もチェック対象とする
    	boolean checkFlg34to38 = true;
//    	boolean checkFlg34to38 = false;
    	if ((!isNull(trnPLInfo.getElec()) && !isZero(trnPLInfo.getElec()))
    	    	|| (!isNull(trnPLInfo.getGas()) && !isZero(trnPLInfo.getGas()))
    	    	|| (!isNull(trnPLInfo.getWater()) && !isZero(trnPLInfo.getWater()))
    	    	|| (!isNull(trnPLInfo.getSonota()) && !isZero(trnPLInfo.getSonota()))
    	    	|| (!isNull(trnPLInfo.getSuikouUchiwake()) && !isZero(trnPLInfo.getSuikouUchiwake()))) {
    		checkFlg34to38 = true;
    	}
    	
    	// ■38=6成り立たないときは、[[PLエラー情報]]の項目【数値制御】に×をセットする
    	// 成り立たないときは、[[PL色情報]]の項目３４～３８にfalseをセットする
    	if (checkFlg34to38) {
    		if (trnPLInfo.getSuikouUchiwake().compareTo(trnPLInfo.getSuikouHi()) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            plDataErrorInfo.add(TrnPLInfo.suikouHi_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	        	// タブ位置指定
	        	plRegistDto.setSelectedTab(2);
//	        	throw new GenericCommentException("計算結果が不整合", "6 水道光熱費 = 38 計");
    		}
    	}

    	// ３９～４３の項目に１つでも入力されていたら、３９～４３もチェック対象とする
    	boolean checkFlg39to43 = true;
//    	boolean checkFlg39to43 = false;
    	if ((!isNull(trnPLInfo.getGenzairyoKei()) && !isZero(trnPLInfo.getGenzairyoKei()))
    	    	|| (!isNull(trnPLInfo.getGenzairyoShire()) && !isZero(trnPLInfo.getGenzairyoShire()))
    	    	|| (!isNull(trnPLInfo.getGenzairyoZaiko()) && !isZero(trnPLInfo.getGenzairyoZaiko()))
    	    	|| (!isNull(trnPLInfo.getYasaiKei()) && !isZero(trnPLInfo.getYasaiKei()))
    	    	|| (!isNull(trnPLInfo.getYasaiShire()) && !isZero(trnPLInfo.getYasaiShire()))
    	    	|| (!isNull(trnPLInfo.getYasaiZaiko()) && !isZero(trnPLInfo.getYasaiZaiko()))
    	    	|| (!isNull(trnPLInfo.getHouzaiKei()) && !isZero(trnPLInfo.getHouzaiKei()))
    	    	|| (!isNull(trnPLInfo.getHouzaiShire()) && !isZero(trnPLInfo.getHouzaiShire()))
    	    	|| (!isNull(trnPLInfo.getHouzaiZaiko()) && !isZero(trnPLInfo.getHouzaiZaiko()))
    	    	|| (!isNull(trnPLInfo.getBuppanKei()) && !isZero(trnPLInfo.getBuppanKei()))
    	    	|| (!isNull(trnPLInfo.getBuppanShire()) && !isZero(trnPLInfo.getBuppanShire()))
    	    	|| (!isNull(trnPLInfo.getBuppanZaiko()) && !isZero(trnPLInfo.getBuppanZaiko()))
    	    	|| (!isNull(trnPLInfo.getTouSiireKei()) && !isZero(trnPLInfo.getTouSiireKei()))
    	    	|| (!isNull(trnPLInfo.getTouZaikoKei()) && !isZero(trnPLInfo.getTouZaikoKei()))
    	    	|| (!isNull(trnPLInfo.getSashihikiKei()) && !isZero(trnPLInfo.getSashihikiKei()))) {
    		checkFlg39to43 = true;
    	}

    	// ■43d=2成り立たないときは、[[PLエラー情報]]の項目【数値制御】に×をセットする
    	// 成り立たないときは、[[PL色情報]]の項目【計（d）差引売上原価】、【売上原価】にfalseをセットする
    	if (checkFlg39to43) {
    		if (trnPLInfo.getSashihikiKei().compareTo(trnPLInfo.getUriagegenka()) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            plDataErrorInfo.add(TrnPLInfo.uriagegenka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	        	// タブ位置指定
	        	plRegistDto.setSelectedTab(2);
//	        	throw new GenericCommentException("計算結果が不整合", "2 売上原価 = 43 差引売上原価 計");
    		}
    	}
    	
    	// ４４～４７の項目に１つでも入力されていたら、４４～４７もチェック対象とする
    	boolean checkFlg44to47 = true;
//    	boolean checkFlg44to47 = false;
    	if ((!isNull(trnPLInfo.getYakuinSalary()) && !isZero(trnPLInfo.getYakuinSalary()))
    	    	|| (!isNull(trnPLInfo.getYakuinBonus()) && !isZero(trnPLInfo.getYakuinBonus()))
    	    	|| (!isNull(trnPLInfo.getYakuinRetire()) && !isZero(trnPLInfo.getYakuinRetire()))
    	    	|| (!isNull(trnPLInfo.getYakuinKei()) && !isZero(trnPLInfo.getYakuinKei()))
    	    	|| (!isNull(trnPLInfo.getSalarySalary()) && !isZero(trnPLInfo.getSalarySalary()))
    	    	|| (!isNull(trnPLInfo.getSalaryBonus()) && !isZero(trnPLInfo.getSalaryBonus()))
    	    	|| (!isNull(trnPLInfo.getSalaryRetire()) && !isZero(trnPLInfo.getSalaryRetire()))
    	    	|| (!isNull(trnPLInfo.getSalaryKei()) && !isZero(trnPLInfo.getSalaryKei()))
    	    	|| (!isNull(trnPLInfo.getZakkyuSalary()) && !isZero(trnPLInfo.getZakkyuSalary()))
    	    	|| (!isNull(trnPLInfo.getZakkyuBonus()) && !isZero(trnPLInfo.getZakkyuBonus()))
    	    	|| (!isNull(trnPLInfo.getZakkyuRetire()) && !isZero(trnPLInfo.getZakkyuRetire()))
    	    	|| (!isNull(trnPLInfo.getZakkyuKei()) && !isZero(trnPLInfo.getZakkyuKei()))
    	    	|| (!isNull(trnPLInfo.getKyuryoKei()) && !isZero(trnPLInfo.getKyuryoKei()))
    	    	|| (!isNull(trnPLInfo.getBonusKei()) && !isZero(trnPLInfo.getBonusKei()))
    	    	|| (!isNull(trnPLInfo.getRetireKei()) && !isZero(trnPLInfo.getRetireKei()))
    	    	|| (!isNull(trnPLInfo.getSalaryUtiKei()) && !isZero(trnPLInfo.getSalaryUtiKei()))) {
    		checkFlg44to47 = true;
    	}

    	// ■47d=4成り立たないときは、[[PLエラー情報]]の項目【数値制御】に×をセットする
    	// 成り立たないときは、[[PL色情報]]の項目【計（d）計】、【給料手当】にfalseをセットする
    	if (checkFlg44to47) {
    		if (trnPLInfo.getSalaryUtiKei().compareTo(trnPLInfo.getSalary()) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            plDataErrorInfo.add(TrnPLInfo.salary_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	        	// タブ位置指定
	        	plRegistDto.setSelectedTab(2);
//	        	throw new GenericCommentException("計算結果が不整合", "4 給料手当 = 47 計 計");
    		}
    	}
    	
    	if (plDataErrorInfo.isExistError()) {
        	plRegistDto.setSelectedTab(2);
        	throw new GenericCommentException("計算結果が不整合");
    	}
        return null;
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
