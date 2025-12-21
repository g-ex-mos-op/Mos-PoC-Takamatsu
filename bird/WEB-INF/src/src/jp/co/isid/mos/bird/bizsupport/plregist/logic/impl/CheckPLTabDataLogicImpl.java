/*
 * 作成日: 2006/3/22
 */
package jp.co.isid.mos.bird.bizsupport.plregist.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.log.Logger;

import jp.co.isid.mos.bird.bizsupport.common.dao.CtlPLLimitDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.util.PlLimitUtils;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLLimit;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.TrnPLInfoDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.logic.CheckPLTabDataLogic;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * タブ内容チェック
 * @author itamoto
 */
public class CheckPLTabDataLogicImpl implements CheckPLTabDataLogic {

    private static Logger logger_ = Logger.getLogger(CheckPLTabDataLogicImpl.class);
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS001L04";
    
    
    protected static final int MAX_LENGTH = 11;
    protected static final String LIMIT_FLG_MAX = "0";
    protected static final String LIMIT_FLG_MIN = "1";

    
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
     * タブ内容の整合性チェックを行う。
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto     
     * */
    public List execute(TrnPLInfo trnPLInfo, TrnZenPLInfo trnZenPLInfo,
			PlRegistDto plRegistDto, TrnPLInfoDto trnPLInfoDto) {

        PlDataErrorInfo plDataErrorInfo = trnPLInfo.getPlDataErrorInfo();
        

        // 初回入力では整合性チェックを行わない
        if(checkFirstRecord(plRegistDto,trnPLInfoDto)) {
            return null;
        }
        
        // P/Lデータ上下限値リスト取得
        List limitValueList = getCtlPLLimitDao().selectPlLimitAll();
        
        if(plRegistDto.isErrSwitchFlg()) {
        
        	// １．パラメータ.タブID=1の場合
        	// 月次損益計算書内容チェック
        	if (plRegistDto.getSelectedTab() == 1) {
        		checkMonthlyIncomeStatement(trnPLInfo, trnZenPLInfo, plRegistDto,
    					plDataErrorInfo, limitValueList, trnPLInfoDto);
        	}
    
        	// ２．パラメータ.タブID=2の場合
        	// 内訳内容チェック
        	if (plRegistDto.getSelectedTab() == 2) {
        		checkItems(trnPLInfo, trnZenPLInfo, plRegistDto, plDataErrorInfo,
    					limitValueList, trnPLInfoDto);
        	}
        	
        	// ３．パラメータ.タブID=3の場合
        	// 借入金（メモ含む）内容チェック
           	if (plRegistDto.getSelectedTab() == 3) {
           		checkDebt(trnPLInfo, trnZenPLInfo, plRegistDto, plDataErrorInfo,
    					limitValueList, trnPLInfoDto);
           	}
        }
        else {
            // １．パラメータ.タブID=1の場合
            // 月次損益計算書内容チェック
            checkMonthlyIncomeStatement(trnPLInfo, trnZenPLInfo, plRegistDto,
                    plDataErrorInfo, limitValueList, trnPLInfoDto);
    
            // ２．パラメータ.タブID=2の場合
            // 内訳内容チェック
            checkItems(trnPLInfo, trnZenPLInfo, plRegistDto, plDataErrorInfo,
                    limitValueList, trnPLInfoDto);
            
            // ３．パラメータ.タブID=3の場合
            // 借入金（メモ含む）内容チェック
            checkDebt(trnPLInfo, trnZenPLInfo, plRegistDto, plDataErrorInfo,
                    limitValueList, trnPLInfoDto);
        }
        
        return null;
    }

    
    /**
     * 月次損益計算書内容チェック
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto
     */
    private void checkMonthlyIncomeStatement(TrnPLInfo trnPLInfo,
			TrnZenPLInfo trnZenPLInfo, PlRegistDto plRegistDto,
			PlDataErrorInfo plDataErrorInfo, List limitValueList,
			TrnPLInfoDto trnPLInfoDto) {
        
    	// 1　売上高
    	// 2　売上原価
    	// 3　売上総利益
    	// 4　給料手当
    	// 5　家賃地代
    	// 6　水道光熱費
    	// 7　ロイヤルティ
    	// 8　支払手数料
    	// 9　広告費
    	// 10　消耗品費
    	// 11　法定福利費
    	// 12　福利厚生費
    	// 13　交際費
    	// 14　旅費交通費
    	// 15　通信費
    	// 16　賃借リース料
    	// 17　車両費
    	// 18　租税公課
    	// 19　保険料
    	// 20　運賃
    	// 21　修繕費
    	// 22　(予備欄)
    	// 23　雑費
    	// 24　経費小計
    	// 25　償却前利益
    	// 26　減価償却費
    	// 27　営業外収益
    	// 28　営業外費用
    	// 29　本社費配賦
    	// 30　当月利益

    	// ①数値の妥当性チェック
    	// ■1～30までの項目が、11桁以下の数字であること11桁以上の場合は【E0505】
    	//   （チェック対象項目名）を発生させる。
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.uriagedaka_COLUMN, trnPLInfoDto.getUriagedaka(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.uriagegenka_COLUMN, trnPLInfoDto.getUriagegenka(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.uriageSoRieki_COLUMN, trnPLInfoDto.getUriageSoRieki(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.salary_COLUMN, trnPLInfoDto.getSalary(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yachin_COLUMN, trnPLInfoDto.getYachin(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.suikouHi_COLUMN, trnPLInfoDto.getSuikouHi(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.royalty_COLUMN, trnPLInfoDto.getRoyalty(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.tesuryo_COLUMN, trnPLInfoDto.getTesuryo(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.koukoku_COLUMN, trnPLInfoDto.getKoukoku(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.shoumou_COLUMN, trnPLInfoDto.getShoumou(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.houteiFukuri_COLUMN, trnPLInfoDto.getHouteiFukuri(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.fukuriKousei_COLUMN, trnPLInfoDto.getFukuriKousei(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kousai_COLUMN, trnPLInfoDto.getKousai(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.ryohi_COLUMN, trnPLInfoDto.getRyohi(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.tusin_COLUMN, trnPLInfoDto.getTusin(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.lease_COLUMN, trnPLInfoDto.getLease(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.sharyo_COLUMN, trnPLInfoDto.getSharyo(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.sozei_COLUMN, trnPLInfoDto.getSozei(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.hoken_COLUMN, trnPLInfoDto.getHoken(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.unchin_COLUMN, trnPLInfoDto.getUnchin(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.shuzen_COLUMN, trnPLInfoDto.getShuzen(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yobi_COLUMN, trnPLInfoDto.getYobi(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.zappi_COLUMN, trnPLInfoDto.getZappi(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.keihiShokei_COLUMN, trnPLInfoDto.getKeihiShokei(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.shokyakuRieki_COLUMN, trnPLInfoDto.getShokyakuRieki(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.genkaShokyaku_COLUMN, trnPLInfoDto.getGenkaShokyaku(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.eigaiShueki_COLUMN, trnPLInfoDto.getEigaiShueki(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.eigaiHiyo_COLUMN, trnPLInfoDto.getEigaiHiyo(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.honshahiHai_COLUMN, trnPLInfoDto.getHonshahiHai(), MAX_LENGTH);
    	checkNumericAndLength(plDataErrorInfo, TrnPLInfo.rieki_COLUMN, trnPLInfoDto.getRieki(), MAX_LENGTH);

//        if (plDataErrorInfo.isExistError()) {
//        	throw new InvalidInputException("赤字箇所の桁数");
//        }
    	
    	// ■1～30までの項目は必須項目である空白がある場合は【E0506】（チェック対象項目名）を発生させる。
        checkRequire(plDataErrorInfo, TrnPLInfo.uriagedaka_COLUMN, trnPLInfoDto.getUriagedaka());
        checkRequire(plDataErrorInfo, TrnPLInfo.uriagegenka_COLUMN, trnPLInfoDto.getUriagegenka());
        checkRequire(plDataErrorInfo, TrnPLInfo.uriageSoRieki_COLUMN, trnPLInfoDto.getUriageSoRieki());
        checkRequire(plDataErrorInfo, TrnPLInfo.salary_COLUMN, trnPLInfoDto.getSalary());
        checkRequire(plDataErrorInfo, TrnPLInfo.yachin_COLUMN, trnPLInfoDto.getYachin());
        checkRequire(plDataErrorInfo, TrnPLInfo.suikouHi_COLUMN, trnPLInfoDto.getSuikouHi());
        checkRequire(plDataErrorInfo, TrnPLInfo.royalty_COLUMN, trnPLInfoDto.getRoyalty());
        checkRequire(plDataErrorInfo, TrnPLInfo.tesuryo_COLUMN, trnPLInfoDto.getTesuryo());
        checkRequire(plDataErrorInfo, TrnPLInfo.koukoku_COLUMN, trnPLInfoDto.getKoukoku());
        checkRequire(plDataErrorInfo, TrnPLInfo.shoumou_COLUMN, trnPLInfoDto.getShoumou());
        checkRequire(plDataErrorInfo, TrnPLInfo.houteiFukuri_COLUMN, trnPLInfoDto.getHouteiFukuri());
        checkRequire(plDataErrorInfo, TrnPLInfo.fukuriKousei_COLUMN, trnPLInfoDto.getFukuriKousei());
        checkRequire(plDataErrorInfo, TrnPLInfo.kousai_COLUMN, trnPLInfoDto.getKousai());
        checkRequire(plDataErrorInfo, TrnPLInfo.ryohi_COLUMN, trnPLInfoDto.getRyohi());
        checkRequire(plDataErrorInfo, TrnPLInfo.tusin_COLUMN, trnPLInfoDto.getTusin());
        checkRequire(plDataErrorInfo, TrnPLInfo.lease_COLUMN, trnPLInfoDto.getLease());
        checkRequire(plDataErrorInfo, TrnPLInfo.sharyo_COLUMN, trnPLInfoDto.getSharyo());
        checkRequire(plDataErrorInfo, TrnPLInfo.sozei_COLUMN, trnPLInfoDto.getSozei());
        checkRequire(plDataErrorInfo, TrnPLInfo.hoken_COLUMN, trnPLInfoDto.getHoken());
        checkRequire(plDataErrorInfo, TrnPLInfo.unchin_COLUMN, trnPLInfoDto.getUnchin());
        checkRequire(plDataErrorInfo, TrnPLInfo.shuzen_COLUMN, trnPLInfoDto.getShuzen());
        checkRequire(plDataErrorInfo, TrnPLInfo.yobi_COLUMN, trnPLInfoDto.getYobi());
        checkRequire(plDataErrorInfo, TrnPLInfo.zappi_COLUMN, trnPLInfoDto.getZappi());
        checkRequire(plDataErrorInfo, TrnPLInfo.keihiShokei_COLUMN, trnPLInfoDto.getKeihiShokei());
        checkRequire(plDataErrorInfo, TrnPLInfo.shokyakuRieki_COLUMN, trnPLInfoDto.getShokyakuRieki());
        checkRequire(plDataErrorInfo, TrnPLInfo.genkaShokyaku_COLUMN, trnPLInfoDto.getGenkaShokyaku());
        checkRequire(plDataErrorInfo, TrnPLInfo.eigaiShueki_COLUMN, trnPLInfoDto.getEigaiShueki());
        checkRequire(plDataErrorInfo, TrnPLInfo.eigaiHiyo_COLUMN, trnPLInfoDto.getEigaiHiyo());
        checkRequire(plDataErrorInfo, TrnPLInfo.honshahiHai_COLUMN, trnPLInfoDto.getHonshahiHai());
        checkRequire(plDataErrorInfo, TrnPLInfo.rieki_COLUMN, trnPLInfoDto.getRieki());

//        if (plDataErrorInfo.isExistError()) {
//        	throw new NotNullException("赤字箇所");
//        }

    	// ■3=1-2成立しない場合は【E0607】（“計算結果が不整合“、“売上総利益“）を発生させる。
        // 3.売上総利益 = 1.売上高 - 2.売上原価
        boolean uriageSoRiekiFlg = false;
//        if (!plDataErrorInfo.isErrorItem(TrnPLInfo.uriagedaka_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.uriagegenka_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.uriageSoRieki_COLUMN)) {
          // 「オーナー：36387 ヴィアン様」はチェック処理無し #5277  2021.12.17 fukasawa
/*        
            if (convDecimal(trnPLInfoDto.getUriageSoRieki())
                    .compareTo(convDecimal(trnPLInfoDto.getUriagedaka())
                    .subtract(convDecimal(trnPLInfoDto.getUriagegenka()))) != 0) {
*/
        if (convDecimal(trnPLInfoDto.getUriageSoRieki())
                .compareTo(convDecimal(trnPLInfoDto.getUriagedaka())
                .subtract(convDecimal(trnPLInfoDto.getUriagegenka()))) != 0 
                && !trnPLInfo.getOnerCd().equals("36387")) {
                plDataErrorInfo.add(TrnPLInfo.uriageSoRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                uriageSoRiekiFlg = true;
            }
//        } else {
//            plDataErrorInfo.add(TrnPLInfo.uriageSoRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//            uriageSoRiekiFlg = true;
//        }

        // ★参考値設定 3.売上総利益 整合性エラー時
        if (uriageSoRiekiFlg) {
        	plRegistDto.setUriageSoRiekiReference(convDecimal(trnPLInfoDto.getUriagedaka())
                    .subtract(convDecimal(trnPLInfoDto.getUriagegenka())));
        } else {
        	plRegistDto.setUriageSoRiekiReference(null);
        }
        
//        if (plDataErrorInfo.isExistError()) {
//        	throw new GenericCommentException("計算結果が不整合", "売上総利益");
//        }
        
        // ■24=4+5+6+・・・+23成立しない場合は【E0607】（“計算結果が不整合“、“経費小計“）を発生させる。
        // 24.経費小計 = 4.給料手当て ～ 23.雑費
        boolean keihiShokeiFlg = false;
//        if (!plDataErrorInfo.isErrorItem(TrnPLInfo.salary_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.yachin_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.suikouHi_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.royalty_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.tesuryo_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.koukoku_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.shoumou_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.houteiFukuri_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.fukuriKousei_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.kousai_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.ryohi_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.tusin_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.lease_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.sharyo_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.sozei_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.hoken_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.unchin_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.shuzen_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.yobi_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.zappi_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.keihiShokei_COLUMN)) {
	        if (convDecimal(trnPLInfoDto.getKeihiShokei())
	                .compareTo(convDecimal(trnPLInfoDto.getSalary())
	                .add(convDecimal(trnPLInfoDto.getYachin()))
	                .add(convDecimal(trnPLInfoDto.getSuikouHi()))
	                .add(convDecimal(trnPLInfoDto.getRoyalty()))
	                .add(convDecimal(trnPLInfoDto.getTesuryo()))
	                .add(convDecimal(trnPLInfoDto.getKoukoku()))
	                .add(convDecimal(trnPLInfoDto.getShoumou()))
	                .add(convDecimal(trnPLInfoDto.getHouteiFukuri()))
	                .add(convDecimal(trnPLInfoDto.getFukuriKousei()))
	                .add(convDecimal(trnPLInfoDto.getKousai()))
	                .add(convDecimal(trnPLInfoDto.getRyohi()))
	                .add(convDecimal(trnPLInfoDto.getTusin()))
	                .add(convDecimal(trnPLInfoDto.getLease()))
	                .add(convDecimal(trnPLInfoDto.getSharyo()))
	                .add(convDecimal(trnPLInfoDto.getSozei()))
	                .add(convDecimal(trnPLInfoDto.getHoken()))
	                .add(convDecimal(trnPLInfoDto.getUnchin()))
	                .add(convDecimal(trnPLInfoDto.getShuzen()))
	                .add(convDecimal(trnPLInfoDto.getYobi()))
	                .add(convDecimal(trnPLInfoDto.getZappi()))) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.keihiShokei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            keihiShokeiFlg = true;
	        }
//        } else {
//            plDataErrorInfo.add(TrnPLInfo.keihiShokei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//            keihiShokeiFlg = true;
//        }

        // ★参考値設定 24.経費小計 整合性エラー時
        if (keihiShokeiFlg) {
        	plRegistDto.setKeihiShokeiReference(convDecimal(trnPLInfoDto.getSalary())
	                .add(convDecimal(trnPLInfoDto.getYachin()))
	                .add(convDecimal(trnPLInfoDto.getSuikouHi()))
	                .add(convDecimal(trnPLInfoDto.getRoyalty()))
	                .add(convDecimal(trnPLInfoDto.getTesuryo()))
	                .add(convDecimal(trnPLInfoDto.getKoukoku()))
	                .add(convDecimal(trnPLInfoDto.getShoumou()))
	                .add(convDecimal(trnPLInfoDto.getHouteiFukuri()))
	                .add(convDecimal(trnPLInfoDto.getFukuriKousei()))
	                .add(convDecimal(trnPLInfoDto.getKousai()))
	                .add(convDecimal(trnPLInfoDto.getRyohi()))
	                .add(convDecimal(trnPLInfoDto.getTusin()))
	                .add(convDecimal(trnPLInfoDto.getLease()))
	                .add(convDecimal(trnPLInfoDto.getSharyo()))
	                .add(convDecimal(trnPLInfoDto.getSozei()))
	                .add(convDecimal(trnPLInfoDto.getHoken()))
	                .add(convDecimal(trnPLInfoDto.getUnchin()))
	                .add(convDecimal(trnPLInfoDto.getShuzen()))
	                .add(convDecimal(trnPLInfoDto.getYobi()))
	                .add(convDecimal(trnPLInfoDto.getZappi())));
        } else {
        	plRegistDto.setKeihiShokeiReference(null);
        }

//        if (plDataErrorInfo.isExistError()) {
//        	throw new GenericCommentException("計算結果が不整合", "経費小計");
//        }

        // ■25=3-24成立しない場合は【E0607】（“計算結果が不整合“、“償却前利益“）を発生させる。
        // 25.償却前利益 = 3.売上総利益 - 24.経費小計
        boolean shokyakuRiekiFlg = false;
        
        BigDecimal keihiShokei = null;
        if (plRegistDto.getKeihiShokeiReference() == null) {
        	keihiShokei = convDecimal(trnPLInfoDto.getKeihiShokei());
        } else {
        	keihiShokei = plRegistDto.getKeihiShokeiReference();
        }
        BigDecimal uriageSoRieki = null;
        if (plRegistDto.getUriageSoRiekiReference() == null) {
        	uriageSoRieki = convDecimal(trnPLInfoDto.getUriageSoRieki());
        } else {
        	uriageSoRieki = plRegistDto.getUriageSoRiekiReference();
        }
        
//        if (!plDataErrorInfo.isErrorItem(TrnPLInfo.shokyakuRieki_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.uriageSoRieki_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.keihiShokei_COLUMN)) {
	        if (convDecimal(trnPLInfoDto.getShokyakuRieki())
	                .compareTo(uriageSoRieki
	                .subtract(keihiShokei)) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.shokyakuRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            shokyakuRiekiFlg = true;
	        }
//        } else {
//            plDataErrorInfo.add(TrnPLInfo.shokyakuRieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//            shokyakuRiekiFlg = true;
//        }

        // ★参考値設定 25.償却前利益 整合性エラー時
        if (shokyakuRiekiFlg) {
        	plRegistDto.setShokyakuRiekiReference(uriageSoRieki
	                .subtract(keihiShokei));
        } else {
        	plRegistDto.setShokyakuRiekiReference(null);
        }

//        if (plDataErrorInfo.isExistError()) {
//        	throw new GenericCommentException("計算結果が不整合", "償却前利益");
//        }
        
        // ■30＝25-26+27-28-29成立しない場合は【E0607】（“計算結果が不整合“、“当月利益“）を発生させる。
        // 30.当月利益 = 25.償却前利益 - 26.減価償却費 + 27.営業外収益 - 28.営業外費用 - 29. 本社費配賦
        boolean riekiFlg = false;
        
        BigDecimal shokyakuRieki = null;
        if (plRegistDto.getShokyakuRiekiReference() == null) {
        	shokyakuRieki = convDecimal(trnPLInfoDto.getShokyakuRieki());
        } else {
        	shokyakuRieki = plRegistDto.getShokyakuRiekiReference();
        }
        
//        if (!plDataErrorInfo.isErrorItem(TrnPLInfo.shokyakuRieki_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.genkaShokyaku_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.eigaiShueki_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.eigaiHiyo_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.honshahiHai_COLUMN)
//                && !plDataErrorInfo.isErrorItem(TrnPLInfo.rieki_COLUMN)) {
	        if (convDecimal(trnPLInfoDto.getRieki())
	                .compareTo(shokyakuRieki
	                .subtract(convDecimal(trnPLInfoDto.getGenkaShokyaku()))
	                .add(convDecimal(trnPLInfoDto.getEigaiShueki()))
	                .subtract(convDecimal(trnPLInfoDto.getEigaiHiyo()))
	                .subtract(convDecimal(trnPLInfoDto.getHonshahiHai()))) != 0) {
	            plDataErrorInfo.add(TrnPLInfo.rieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
	            riekiFlg = true;
	        }
//        } else {
//            plDataErrorInfo.add(TrnPLInfo.rieki_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//            riekiFlg = true;
//        }
        
        // ★参考値設定 30.当月利益 整合性エラー時
        if (riekiFlg) {
        	plRegistDto.setRiekiReference(shokyakuRieki
	                .subtract(convDecimal(trnPLInfoDto.getGenkaShokyaku()))
	                .add(convDecimal(trnPLInfoDto.getEigaiShueki()))
	                .subtract(convDecimal(trnPLInfoDto.getEigaiHiyo()))
	                .subtract(convDecimal(trnPLInfoDto.getHonshahiHai())));
        } else {
        	plRegistDto.setRiekiReference(null);
        }
        
        
//        if (plDataErrorInfo.isExistError()) {
//        	throw new GenericCommentException("計算結果が不整合", "当月利益");
//        }

    	// ②1～30までの項目の上下限値チェック
    	// パラメータ.PLデータの各カラムがパラメータ.PL上下限値の範囲内であるかをチェック
    	// 範囲外の場合は、【E0607】（”値が範囲外”、各カラム名）を発生させる。

        /**
         * 上下限チェック改
         * @param plDataErrorInfo P/Lデータエラー情報
         * @param plType P/Lタイプ
         * @param itemCode 項目名
         * @param value チェック対象数値
         * @param uriagedaka 売上高数値
         * @param limitValueList P/Lデータ上下限値リスト
         */
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriagedaka_COLUMN, trnPLInfoDto.getUriagedaka(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriagegenka_COLUMN, trnPLInfoDto.getUriagegenka(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriageSoRieki_COLUMN, trnPLInfoDto.getUriageSoRieki(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salary_COLUMN, trnPLInfoDto.getSalary(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yachin_COLUMN, trnPLInfoDto.getYachin(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.suikouHi_COLUMN, trnPLInfoDto.getSuikouHi(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.royalty_COLUMN, trnPLInfoDto.getRoyalty(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.tesuryo_COLUMN, trnPLInfoDto.getTesuryo(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.koukoku_COLUMN, trnPLInfoDto.getKoukoku(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.shoumou_COLUMN, trnPLInfoDto.getShoumou(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houteiFukuri_COLUMN, trnPLInfoDto.getHouteiFukuri(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.fukuriKousei_COLUMN, trnPLInfoDto.getFukuriKousei(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kousai_COLUMN, trnPLInfoDto.getKousai(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.ryohi_COLUMN, trnPLInfoDto.getRyohi(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.tusin_COLUMN, trnPLInfoDto.getTusin(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.lease_COLUMN, trnPLInfoDto.getLease(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sharyo_COLUMN, trnPLInfoDto.getSharyo(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sozei_COLUMN, trnPLInfoDto.getSozei(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.hoken_COLUMN, trnPLInfoDto.getHoken(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.unchin_COLUMN, trnPLInfoDto.getUnchin(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.shuzen_COLUMN, trnPLInfoDto.getShuzen(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yobi_COLUMN, trnPLInfoDto.getYobi(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zappi_COLUMN, trnPLInfoDto.getZappi(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.keihiShokei_COLUMN, trnPLInfoDto.getKeihiShokei(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.shokyakuRieki_COLUMN, trnPLInfoDto.getShokyakuRieki(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genkaShokyaku_COLUMN, trnPLInfoDto.getGenkaShokyaku(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.eigaiShueki_COLUMN, trnPLInfoDto.getEigaiShueki(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.eigaiHiyo_COLUMN, trnPLInfoDto.getEigaiHiyo(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.honshahiHai_COLUMN, trnPLInfoDto.getHonshahiHai(), trnPLInfoDto.getUriagedaka(), limitValueList);
        checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.rieki_COLUMN, trnPLInfoDto.getRieki(), trnPLInfoDto.getUriagedaka(), limitValueList);
        
//        if (plDataErrorInfo.isExistError()) {
//        	throw new GenericCommentException("値が範囲外", "各カラム名");
//        }

    	// ③POS売上チェック
    	// 3 売上が、[[POS前月売上.POS前月売上]]と一致しているかチェック成立しない場合は、ワーニングとしておく
        if (!trnPLInfo.getPlType().equals("0")) {
            
            // POS売上取得
            BigDecimal sales = BigDecimal.valueOf(0);
            if(plRegistDto.getTrnPosZenUriage()!=null) {
                sales = plRegistDto.getTrnPosZenUriage().getUriage();
            }
            if (!PlLimitUtils.checkPosDiff(convDecimal(trnPLInfoDto.getUriagedaka()), sales, limitValueList)) {
                plDataErrorInfo.add(TrnPLInfo.uriagedaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_SALES);
            }
            
        }
	}

    /**
     * 内訳内容チェック
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto
     */
    private void checkItems(TrnPLInfo trnPLInfo, TrnZenPLInfo trnZenPLInfo,
			PlRegistDto plRegistDto, PlDataErrorInfo plDataErrorInfo,
			List limitValueList, TrnPLInfoDto trnPLInfoDto) {
    	// 31　売上
    	// 32　物販売上
    	// 33　計
    	// 34　電気代
    	// 35　ガス代
    	// 36　水道代
    	// 37　その他
    	// 38　計
    	// 39a　原材料（a）前月在庫
    	// 39b　原材料（b）当月仕入
    	// 39c　原材料（c）当月在庫
    	// 39d　原材料（d）差引売上原価
    	// 40a　野菜（a）前月在庫
    	// 40b　野菜（b）当月仕入
    	// 40c　野菜（c）当月在庫
    	// 40d　野菜（d）差引売上原価
    	// 41a　包材（a）前月在庫
    	// 41b　包材（b）当月仕入
    	// 41c　包材（c）当月在庫
    	// 41d　包材（d）差引売上原価
    	// 42a　物販（a）前月在庫
    	// 42b　物販（b）当月仕入
    	// 42c　物販（c）当月在庫
    	// 42d　物販（d）差引売上原価
    	// 43a　計（a）前月在庫
    	// 43b　計（b）当月仕入
    	// 43c　計（c）当月在庫
    	// 43d　計（d）差引売上原価
    	// 44a　役員報酬（a）給料
    	// 44b　役員報酬（b）賞与
    	// 44c　役員報酬（c）退職金
    	// 44d　役員報酬（d）計
    	// 45a　給料手当（a）給料
    	// 45b　給料手当（b）賞与
    	// 45c　給料手当（c）退職金
    	// 45d　給料手当（d）計
    	// 46a　雑給（a）給料
    	// 46b　雑給（b）賞与
    	// 46c　雑給（c）退職金
    	// 46d　雑給（d）計
    	// 47a　計（a）給料
    	// 47b　計（b）賞与
    	// 47c　計（c）退職金
    	// 47d　計（d）計

    	// 前提条件
    	// 31～33の項目に１つでも入力されていたら、31～33もチェック対象とする
    	boolean checkFlg31to33 = false;
    	if ((!isNull(trnPLInfoDto.getUriage()) && !isZero(trnPLInfoDto.getUriage()))
    	    	|| !(isNull(trnPLInfoDto.getBuppan()) && !isZero(trnPLInfoDto.getBuppan()))
    	    	|| !(isNull(trnPLInfoDto.getUriUchiwake()) && !isZero(trnPLInfoDto.getUriUchiwake()))) {
    		checkFlg31to33 = true;
    	}
    	
    	// 34～38の項目に１つでも入力されていたら、34～38もチェック対象とする
    	boolean checkFlg34to38 = false;
    	if ((!isNull(trnPLInfoDto.getElec()) && !isZero(trnPLInfoDto.getElec()))
    	    	|| (!isNull(trnPLInfoDto.getGas()) && !isZero(trnPLInfoDto.getGas()))
    	    	|| (!isNull(trnPLInfoDto.getWater()) && !isZero(trnPLInfoDto.getWater()))
    	    	|| (!isNull(trnPLInfoDto.getOther()) && !isZero(trnPLInfoDto.getOther()))
    	    	|| (!isNull(trnPLInfoDto.getSuikouUchiwake()) && !isZero(trnPLInfoDto.getSuikouUchiwake()))) {
    		checkFlg34to38 = true;
    	}
    	
    	// 39～43の項目に１つでも入力されていたら、39～43もチェック対象とする
    	boolean checkFlg39to43 = false;
    	if ((!isNull(trnPLInfoDto.getGenzairyoKei()) && !isZero(trnPLInfoDto.getGenzairyoKei()))
    	    	|| (!isNull(trnPLInfoDto.getGenzairyoShire()) && !isZero(trnPLInfoDto.getGenzairyoShire()))
    	    	|| (!isNull(trnPLInfoDto.getGenzairyoZaiko()) && !isZero(trnPLInfoDto.getGenzairyoZaiko()))
    	    	|| (!isNull(trnPLInfoDto.getYasaiKei()) && !isZero(trnPLInfoDto.getYasaiKei()))
    	    	|| (!isNull(trnPLInfoDto.getYasaiShire()) && !isZero(trnPLInfoDto.getYasaiShire()))
    	    	|| (!isNull(trnPLInfoDto.getYasaiZaiko()) && !isZero(trnPLInfoDto.getYasaiZaiko()))
    	    	|| (!isNull(trnPLInfoDto.getHouzaiKei()) && !isZero(trnPLInfoDto.getHouzaiKei()))
    	    	|| (!isNull(trnPLInfoDto.getHouzaiShire()) && !isZero(trnPLInfoDto.getHouzaiShire()))
    	    	|| (!isNull(trnPLInfoDto.getHouzaiZaiko()) && !isZero(trnPLInfoDto.getHouzaiZaiko()))
    	    	|| (!isNull(trnPLInfoDto.getBuppanKei()) && !isZero(trnPLInfoDto.getBuppanKei()))
    	    	|| (!isNull(trnPLInfoDto.getBuppanShire()) && !isZero(trnPLInfoDto.getBuppanShire()))
    	    	|| (!isNull(trnPLInfoDto.getBuppanZaiko()) && !isZero(trnPLInfoDto.getBuppanZaiko()))
    	    	|| (!isNull(trnPLInfoDto.getTouSiireKei()) && !isZero(trnPLInfoDto.getTouSiireKei()))
    	    	|| (!isNull(trnPLInfoDto.getTouZaikoKei()) && !isZero(trnPLInfoDto.getTouZaikoKei()))
    	    	|| (!isNull(trnPLInfoDto.getSashihikiKei()) && !isZero(trnPLInfoDto.getSashihikiKei()))) {
    		checkFlg39to43 = true;
    	}
    	
    	// 44～47の項目に１つでも入力されていたら、44～47もチェック対象とする
    	boolean checkFlg44to47 = false;
    	if ((!isNull(trnPLInfoDto.getYakuinSalary()) && !isZero(trnPLInfoDto.getYakuinSalary()))
    	    	|| (!isNull(trnPLInfoDto.getYakuinBonus()) && !isZero(trnPLInfoDto.getYakuinBonus()))
    	    	|| (!isNull(trnPLInfoDto.getYakuinRetire()) && !isZero(trnPLInfoDto.getYakuinRetire()))
    	    	|| (!isNull(trnPLInfoDto.getYakuinKei()) && !isZero(trnPLInfoDto.getYakuinKei()))
    	    	|| (!isNull(trnPLInfoDto.getSalarySalary()) && !isZero(trnPLInfoDto.getSalarySalary()))
    	    	|| (!isNull(trnPLInfoDto.getSalaryBonus()) && !isZero(trnPLInfoDto.getSalaryBonus()))
    	    	|| (!isNull(trnPLInfoDto.getSalaryRetire()) && !isZero(trnPLInfoDto.getSalaryRetire()))
    	    	|| (!isNull(trnPLInfoDto.getSalaryKei()) && !isZero(trnPLInfoDto.getSalaryKei()))
    	    	|| (!isNull(trnPLInfoDto.getZakkyuSalary()) && !isZero(trnPLInfoDto.getZakkyuSalary()))
    	    	|| (!isNull(trnPLInfoDto.getZakkyuBonus()) && !isZero(trnPLInfoDto.getZakkyuBonus()))
    	    	|| (!isNull(trnPLInfoDto.getZakkyuRetire()) && !isZero(trnPLInfoDto.getZakkyuRetire()))
    	    	|| (!isNull(trnPLInfoDto.getZakkyuKei()) && !isZero(trnPLInfoDto.getZakkyuKei()))
    	    	|| (!isNull(trnPLInfoDto.getKyuryoKei()) && !isZero(trnPLInfoDto.getKyuryoKei()))
    	    	|| (!isNull(trnPLInfoDto.getBonusKei()) && !isZero(trnPLInfoDto.getBonusKei()))
    	    	|| (!isNull(trnPLInfoDto.getRetireKei()) && !isZero(trnPLInfoDto.getRetireKei()))
    	    	|| (!isNull(trnPLInfoDto.getSalaryUtiKei()) && !isZero(trnPLInfoDto.getSalaryUtiKei()))) {
    		checkFlg44to47 = true;
    	}
    	
    	// ①前提条件に当てはまるもので数値の妥当性チェック
    	// ■31～47までの項目が、11桁以下の数字であること11桁以上の場合は【E0505】（チェック対象項目名）を発生させる。
    	// 31～33の項目
    	if (checkFlg31to33) {
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.uriage_COLUMN, trnPLInfoDto.getUriage(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.buppan_COLUMN, trnPLInfoDto.getBuppan(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.uriUchiwake_COLUMN, trnPLInfoDto.getUriUchiwake(), MAX_LENGTH);
    	}
        
    	// 34～38の項目
    	if (checkFlg34to38) {
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.elec_COLUMN, trnPLInfoDto.getElec(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.gas_COLUMN, trnPLInfoDto.getGas(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.water_COLUMN, trnPLInfoDto.getWater(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.sonota_COLUMN, trnPLInfoDto.getOther(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.suikouUchiwake_COLUMN, trnPLInfoDto.getSuikouUchiwake(), MAX_LENGTH);
    	}

    	// 39～43の項目
    	if (checkFlg39to43) {
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.genzairyoKei_COLUMN, trnPLInfoDto.getGenzairyoKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.genzairyoShire_COLUMN, trnPLInfoDto.getGenzairyoShire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.genzairyoZaiko_COLUMN, trnPLInfoDto.getGenzairyoZaiko(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yasaiKei_COLUMN, trnPLInfoDto.getYasaiKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yasaiShire_COLUMN, trnPLInfoDto.getYasaiShire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yasaiZaiko_COLUMN, trnPLInfoDto.getYasaiZaiko(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.houzaiKei_COLUMN, trnPLInfoDto.getHouzaiKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.houzaiShire_COLUMN, trnPLInfoDto.getHouzaiShire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.houzaiZaiko_COLUMN, trnPLInfoDto.getHouzaiZaiko(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.buppanKei_COLUMN, trnPLInfoDto.getBuppanKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.buppanShire_COLUMN, trnPLInfoDto.getBuppanShire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.buppanZaiko_COLUMN, trnPLInfoDto.getBuppanZaiko(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.touSiireKei_COLUMN, trnPLInfoDto.getTouSiireKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.touZaikoKei_COLUMN, trnPLInfoDto.getTouZaikoKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.sashihikiKei_COLUMN, trnPLInfoDto.getSashihikiKei(), MAX_LENGTH);
    	}
        
    	// 44～47の項目
    	if (checkFlg44to47) {
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yakuinSalary_COLUMN, trnPLInfoDto.getYakuinSalary(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yakuinBonus_COLUMN, trnPLInfoDto.getYakuinBonus(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yakuinRetire_COLUMN, trnPLInfoDto.getYakuinRetire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.yakuinKei_COLUMN, trnPLInfoDto.getYakuinKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.salarySalary_COLUMN, trnPLInfoDto.getSalarySalary(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.salaryBonus_COLUMN, trnPLInfoDto.getSalaryBonus(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.salaryRetire_COLUMN, trnPLInfoDto.getSalaryRetire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.salaryKei_COLUMN, trnPLInfoDto.getSalaryKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.zakkyuSalary_COLUMN, trnPLInfoDto.getZakkyuSalary(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.zakkyuBonus_COLUMN, trnPLInfoDto.getZakkyuBonus(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.zakkyuRetire_COLUMN, trnPLInfoDto.getZakkyuRetire(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.zakkyuKei_COLUMN, trnPLInfoDto.getZakkyuKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kyuryoKei_COLUMN, trnPLInfoDto.getKyuryoKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.bonusKei_COLUMN, trnPLInfoDto.getBonusKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.retireKei_COLUMN, trnPLInfoDto.getRetireKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.salaryUtiKei_COLUMN, trnPLInfoDto.getSalaryUtiKei(), MAX_LENGTH);
    	}
    	
//        if (plDataErrorInfo.isExistError()) {
//        	throw new InvalidInputException("赤字箇所の桁数");
//        }

    	// ■31～47までの項目は必須項目空白がある場合は【E0506】（チェック対象項目名）を発生させる。
    	// 31～33の項目
    	if (checkFlg31to33) {
	        checkRequire(plDataErrorInfo, TrnPLInfo.uriage_COLUMN, trnPLInfoDto.getUriage());
	        checkRequire(plDataErrorInfo, TrnPLInfo.buppan_COLUMN, trnPLInfoDto.getBuppan());
	        checkRequire(plDataErrorInfo, TrnPLInfo.uriUchiwake_COLUMN, trnPLInfoDto.getUriUchiwake());
    	}
        
    	// 34～38の項目
    	if (checkFlg34to38) {
	        checkRequire(plDataErrorInfo, TrnPLInfo.elec_COLUMN, trnPLInfoDto.getElec());
	        checkRequire(plDataErrorInfo, TrnPLInfo.gas_COLUMN, trnPLInfoDto.getGas());
	        checkRequire(plDataErrorInfo, TrnPLInfo.water_COLUMN, trnPLInfoDto.getWater());
	        checkRequire(plDataErrorInfo, TrnPLInfo.sonota_COLUMN, trnPLInfoDto.getOther());
	        checkRequire(plDataErrorInfo, TrnPLInfo.suikouUchiwake_COLUMN, trnPLInfoDto.getSuikouUchiwake());
    	}
        
    	// 39～43の項目
    	if (checkFlg39to43) {
	        checkRequire(plDataErrorInfo, TrnPLInfo.genzairyoKei_COLUMN, trnPLInfoDto.getGenzairyoKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.genzairyoShire_COLUMN, trnPLInfoDto.getGenzairyoShire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.genzairyoZaiko_COLUMN, trnPLInfoDto.getGenzairyoZaiko());
	        checkRequire(plDataErrorInfo, TrnPLInfo.yasaiKei_COLUMN, trnPLInfoDto.getYasaiKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.yasaiShire_COLUMN, trnPLInfoDto.getYasaiShire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.yasaiZaiko_COLUMN, trnPLInfoDto.getYasaiZaiko());
	        checkRequire(plDataErrorInfo, TrnPLInfo.houzaiKei_COLUMN, trnPLInfoDto.getHouzaiKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.houzaiShire_COLUMN, trnPLInfoDto.getHouzaiShire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.houzaiZaiko_COLUMN, trnPLInfoDto.getHouzaiZaiko());
	        checkRequire(plDataErrorInfo, TrnPLInfo.buppanKei_COLUMN, trnPLInfoDto.getBuppanKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.buppanShire_COLUMN, trnPLInfoDto.getBuppanShire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.buppanZaiko_COLUMN, trnPLInfoDto.getBuppanZaiko());
	        checkRequire(plDataErrorInfo, TrnPLInfo.touSiireKei_COLUMN, trnPLInfoDto.getTouSiireKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.touZaikoKei_COLUMN, trnPLInfoDto.getTouZaikoKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.sashihikiKei_COLUMN, trnPLInfoDto.getSashihikiKei());
    	}
        
    	// 44～47の項目
    	if (checkFlg44to47) {
	        checkRequire(plDataErrorInfo, TrnPLInfo.yakuinSalary_COLUMN, trnPLInfoDto.getYakuinSalary());
	        checkRequire(plDataErrorInfo, TrnPLInfo.yakuinBonus_COLUMN, trnPLInfoDto.getYakuinBonus());
	        checkRequire(plDataErrorInfo, TrnPLInfo.yakuinRetire_COLUMN, trnPLInfoDto.getYakuinRetire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.yakuinKei_COLUMN, trnPLInfoDto.getYakuinKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.salarySalary_COLUMN, trnPLInfoDto.getSalarySalary());
	        checkRequire(plDataErrorInfo, TrnPLInfo.salaryBonus_COLUMN, trnPLInfoDto.getSalaryBonus());
	        checkRequire(plDataErrorInfo, TrnPLInfo.salaryRetire_COLUMN, trnPLInfoDto.getSalaryRetire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.salaryKei_COLUMN, trnPLInfoDto.getSalaryKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.zakkyuSalary_COLUMN, trnPLInfoDto.getZakkyuSalary());
	        checkRequire(plDataErrorInfo, TrnPLInfo.zakkyuBonus_COLUMN, trnPLInfoDto.getZakkyuBonus());
	        checkRequire(plDataErrorInfo, TrnPLInfo.zakkyuRetire_COLUMN, trnPLInfoDto.getZakkyuRetire());
	        checkRequire(plDataErrorInfo, TrnPLInfo.zakkyuKei_COLUMN, trnPLInfoDto.getZakkyuKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.kyuryoKei_COLUMN, trnPLInfoDto.getKyuryoKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.bonusKei_COLUMN, trnPLInfoDto.getBonusKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.retireKei_COLUMN, trnPLInfoDto.getRetireKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.salaryUtiKei_COLUMN, trnPLInfoDto.getSalaryUtiKei());
    	}
    	
//        if (plDataErrorInfo.isExistError()) {
//        	throw new NotNullException("赤字箇所");
//        }

        
        boolean uriageDataRefFlg = false;
        
    	// ■33=31+32成立しない場合は【E0607】（“計算結果が不整合“、“33　計“）を発生させる。
    	// 31～33の項目
    	if (checkFlg31to33) {
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.uriage_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.buppan_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.uriUchiwake_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getUriUchiwake())
		                .compareTo(convDecimal(trnPLInfoDto.getUriage())
		                .add(convDecimal(trnPLInfoDto.getBuppan()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    uriageDataRefFlg = true;
		        }
            } else {
	            plDataErrorInfo.add(TrnPLInfo.uriUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
            
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "33　計");
//	        }
    	}
        // ★参考値設定 33.売上高計算結果 整合性エラー時
        if (uriageDataRefFlg) {
            plRegistDto.setUriageDakaReference(
                    convDecimal(trnPLInfoDto.getUriage())
                    .add(convDecimal(trnPLInfoDto.getBuppan())));
        } else {
            if(plRegistDto.isErrSwitchFlg()) {
                plRegistDto.setUriageDakaReference(null);
            }
        }
        
        
        
        boolean suidokonetsuhiRefFlg = false;
        
    	// 34～38の項目
    	if (checkFlg34to38) {
            
	    	// ■38=34+35+36+37成立しない場合は【E0607】（“計算結果が不整合“、“38　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.elec_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.gas_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.water_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.sonota_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.suikouUchiwake_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getSuikouUchiwake())
		                .compareTo(convDecimal(trnPLInfoDto.getElec())
		                .add(convDecimal(trnPLInfoDto.getGas()))
						.add(convDecimal(trnPLInfoDto.getWater()))
						.add(convDecimal(trnPLInfoDto.getOther()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    suidokonetsuhiRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.suikouUchiwake_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
            
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "38　計");
//	        }
    	}
        // ★参考値設定 38.水道光熱費計算結果 整合性エラー時
        if (suidokonetsuhiRefFlg) {
            plRegistDto.setSuidokonetsuhiReference(
                    convDecimal(trnPLInfoDto.getElec())
                    .add(convDecimal(trnPLInfoDto.getGas()))
                    .add(convDecimal(trnPLInfoDto.getWater()))
                    .add(convDecimal(trnPLInfoDto.getOther())));
        } else {
            if(plRegistDto.isErrSwitchFlg()) {
                plRegistDto.setSuidokonetsuhiReference(null);
            }
        }
        
	    
    	// 39～43の項目
    	if (checkFlg39to43) {
            
            
            boolean uriageGenkaTogetsuShiireRefFlg = false;
            
	        // ■43b=39b+40b+41b+42b成立しない場合は【E0607】（“計算結果が不整合“、“当月仕入　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.touSiireKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.buppanShire_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getTouSiireKei())
		                .compareTo(convDecimal(trnPLInfoDto.getGenzairyoShire())
		                .add(convDecimal(trnPLInfoDto.getYasaiShire()))
						.add(convDecimal(trnPLInfoDto.getHouzaiShire()))
						.add(convDecimal(trnPLInfoDto.getBuppanShire()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.touSiireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    uriageGenkaTogetsuShiireRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.touSiireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "当月仕入　計");
//	        }
            // ★参考値設定 43b.売上原価(当月仕入)計算結果 整合性エラー時
            if (uriageGenkaTogetsuShiireRefFlg) {
                plRegistDto.setUriageGenkaTogetsuShiireReference(
                        convDecimal(trnPLInfoDto.getGenzairyoShire())
                        .add(convDecimal(trnPLInfoDto.getYasaiShire()))
                        .add(convDecimal(trnPLInfoDto.getHouzaiShire()))
                        .add(convDecimal(trnPLInfoDto.getBuppanShire())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setUriageGenkaTogetsuShiireReference(null);
                }
            }
            
            
            
            boolean uriageGenkaTogetsuZaikoRefFlg = false;
            
	        // ■43c=39c+40c+41c+42c成立しない場合は【E0607】（“計算結果が不整合“、“当月在庫　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.touZaikoKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoZaiko_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiZaiko_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiZaiko_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.buppanZaiko_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getTouZaikoKei())
		                .compareTo(convDecimal(trnPLInfoDto.getGenzairyoZaiko())
		                .add(convDecimal(trnPLInfoDto.getYasaiZaiko()))
						.add(convDecimal(trnPLInfoDto.getHouzaiZaiko()))
						.add(convDecimal(trnPLInfoDto.getBuppanZaiko()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.touZaikoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    uriageGenkaTogetsuZaikoRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.touZaikoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "当月在庫　計");
//	        }
            // ★参考値設定 43b.売上原価(当月仕入)計算結果 整合性エラー時
            if (uriageGenkaTogetsuZaikoRefFlg) {
                plRegistDto.setUriageGenkaTogetsuZaikoReference(
                        convDecimal(trnPLInfoDto.getGenzairyoZaiko())
                        .add(convDecimal(trnPLInfoDto.getYasaiZaiko()))
                        .add(convDecimal(trnPLInfoDto.getHouzaiZaiko()))
                        .add(convDecimal(trnPLInfoDto.getBuppanZaiko())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setUriageGenkaTogetsuZaikoReference(null);
                }
            }
            
            
            
//            boolean uriageGenkaSashihikiUriageRefFlg = false;
//            
//	        // ■43d=39d+40d+41d+42d成立しない場合は【E0607】（“計算結果が不整合“、“差引売上原価　計“）を発生させる。
//            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.sashihikiKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.buppanKei_COLUMN)) {
//		        if (convDecimal(trnPLInfoDto.getSashihikiKei())
//		                .compareTo(convDecimal(trnPLInfoDto.getGenzairyoKei())
//		                .add(convDecimal(trnPLInfoDto.getYasaiKei()))
//						.add(convDecimal(trnPLInfoDto.getHouzaiKei()))
//						.add(convDecimal(trnPLInfoDto.getBuppanKei()))) != 0) {
//		            plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//                    uriageGenkaSashihikiUriageRefFlg = true;
//				}
//            } else {
//	            plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//            }
////	        if (plDataErrorInfo.isExistError()) {
////	        	throw new GenericCommentException("計算結果が不整合", "差引売上原価　計");
////	        }
//            // ★参考値設定 43d.売上原価(差引売上原価)計算結果 整合性エラー時
//            if (uriageGenkaSashihikiUriageRefFlg) {
//                plRegistDto.setUriageGenkaSashihikiUriageReference(
//                        convDecimal(trnPLInfoDto.getGenzairyoKei())
//                        .add(convDecimal(trnPLInfoDto.getYasaiKei()))
//                        .add(convDecimal(trnPLInfoDto.getHouzaiKei()))
//                        .add(convDecimal(trnPLInfoDto.getBuppanKei())));
//            } else {
//                plRegistDto.setUriageGenkaSashihikiUriageReference(null);
//            }
            
	        
            
            boolean sashihikiGenkaGenzairyoRefFlg = false;
            
	        // ■39d=39a+39b-39c成立しない場合は【E0607】（“計算結果が不整合“、“原材料 差引売上原価“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.genzairyoZaiko_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getGenzairyoKei())
		                .compareTo(trnZenPLInfo.getGenzairyoZaiko()
		                .add(convDecimal(trnPLInfoDto.getGenzairyoShire()))
						.subtract(convDecimal(trnPLInfoDto.getGenzairyoZaiko()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.genzairyoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    sashihikiGenkaGenzairyoRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.genzairyoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "原材料 差引売上原価");
//	        }
            // ★参考値設定 39d.差引売上原価(原材料)計算結果 整合性エラー時
            if (sashihikiGenkaGenzairyoRefFlg) {
                plRegistDto.setSashihikiGenkaGenzairyoReference(
                        trnZenPLInfo.getGenzairyoZaiko()
                        .add(convDecimal(trnPLInfoDto.getGenzairyoShire()))
                        .subtract(convDecimal(trnPLInfoDto.getGenzairyoZaiko())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setSashihikiGenkaGenzairyoReference(null);
                }
            }
            
            
            boolean sashihikiGenkaYasaiRefFlg = false;
	
	        // ■40d=40a+40b-40c成立しない場合は【E0607】（“計算結果が不整合“、“野菜　差引売上原価“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yasaiZaiko_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getYasaiKei())
		                .compareTo(trnZenPLInfo.getYasaiZaiko()
		                .add(convDecimal(trnPLInfoDto.getYasaiShire()))
						.subtract(convDecimal(trnPLInfoDto.getYasaiZaiko()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.yasaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    sashihikiGenkaYasaiRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.yasaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "野菜　差引売上原価");
//	        }
            // ★参考値設定 40d.差引売上原価(野菜)計算結果 整合性エラー時
            if (sashihikiGenkaYasaiRefFlg) {
                plRegistDto.setSashihikiGenkaYasaiReference(
                        trnZenPLInfo.getYasaiZaiko()
                        .add(convDecimal(trnPLInfoDto.getYasaiShire()))
                        .subtract(convDecimal(trnPLInfoDto.getYasaiZaiko())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setSashihikiGenkaYasaiReference(null);
                }
            }
            
            
            boolean sashihikiGenkaHozaiRefFlg = false;
            
	        // ■41d=41a+41b-41c成立しない場合は【E0607】（“計算結果が不整合“、“包材　差引売上原価“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.houzaiZaiko_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getHouzaiKei())
		                .compareTo(trnZenPLInfo.getHouzaiZaiko()
		                .add(convDecimal(trnPLInfoDto.getHouzaiShire()))
						.subtract(convDecimal(trnPLInfoDto.getHouzaiZaiko()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.houzaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    sashihikiGenkaHozaiRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.houzaiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "包材　差引売上原価");
//	        }
            // ★参考値設定 41d.差引売上原価(包材)計算結果 整合性エラー時
            if (sashihikiGenkaHozaiRefFlg) {
                plRegistDto.setSashihikiGenkaHouzaiReference(
                        trnZenPLInfo.getHouzaiZaiko()
                        .add(convDecimal(trnPLInfoDto.getHouzaiShire()))
                        .subtract(convDecimal(trnPLInfoDto.getHouzaiZaiko())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setSashihikiGenkaHouzaiReference(null);
                }
            }
            
            
            boolean sashihikiGenkaBuppanRefFlg = false;
	
	        // ■42d=42a+42b-42c成立しない場合は【E0607】（“計算結果が不整合“、“物販　差引売上原価“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.buppanKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.buppanShire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.buppanZaiko_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getBuppanKei())
		                .compareTo(trnZenPLInfo.getBuppanZaiko()
		                .add(convDecimal(trnPLInfoDto.getBuppanShire()))
						.subtract(convDecimal(trnPLInfoDto.getBuppanZaiko()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.buppanKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    sashihikiGenkaBuppanRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.buppanKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "物販　差引売上原価");
//	        }
            // ★参考値設定 42d.差引売上原価(物販)計算結果 整合性エラー時
            if (sashihikiGenkaBuppanRefFlg) {
                plRegistDto.setSashihikiGenkaBuppanReference(
                        trnZenPLInfo.getBuppanZaiko()
                        .add(convDecimal(trnPLInfoDto.getBuppanShire()))
                        .subtract(convDecimal(trnPLInfoDto.getBuppanZaiko())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setSashihikiGenkaBuppanReference(null);
                }
            }
            
            
            boolean sashihikiGenkaKeiRefFlg = false;        
	
	        // ■43d=43a+43b-43c成立しない場合は【E0607】（“計算結果が不整合“、“計　差引売上原価“）を発生させる。
            if (!uriageGenkaTogetsuShiireRefFlg
                 && !uriageGenkaTogetsuZaikoRefFlg
                 && !sashihikiGenkaGenzairyoRefFlg
                 && !sashihikiGenkaYasaiRefFlg
                 && !sashihikiGenkaHozaiRefFlg
                 && !sashihikiGenkaBuppanRefFlg) {
		        if (convDecimal(trnPLInfoDto.getSashihikiKei())
		                .compareTo(trnZenPLInfo.getTouZaikoKei()
		                .add(convDecimal(trnPLInfoDto.getTouSiireKei()))
						.subtract(convDecimal(trnPLInfoDto.getTouZaikoKei()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    sashihikiGenkaKeiRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.sashihikiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "計　差引売上原価");
//	        }
            // ★参考値設定 43d.差引売上原価計 計算結果 整合性エラー時
            if (sashihikiGenkaKeiRefFlg) {
                plRegistDto.setSashihikiGenkaKeiReference(
                        trnZenPLInfo.getTouZaikoKei()
                        .add(convDecimal(trnPLInfoDto.getTouSiireKei()))
                        .subtract(convDecimal(trnPLInfoDto.getTouZaikoKei())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setSashihikiGenkaKeiReference(null);
                }
            }
    	}
        
        
        
    	// 44～47の項目
    	if (checkFlg44to47) {
            
            boolean kyuryoTeateKyuryoRefFlg = false;
            
	        // ■47a=44a+45a+46a成立しない場合は【E0607】（“計算結果が不整合“、“給料　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.kyuryoKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinSalary_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salarySalary_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuSalary_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getKyuryoKei())
		                .compareTo(convDecimal(trnPLInfoDto.getYakuinSalary())
		                .add(convDecimal(trnPLInfoDto.getSalarySalary()))
						.add(convDecimal(trnPLInfoDto.getZakkyuSalary()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.kyuryoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateKyuryoRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.kyuryoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "給料　計");
//	        }
            // ★参考値設定 47a.給料手当(給料) 計算結果 整合性エラー時
            if (kyuryoTeateKyuryoRefFlg) {
                plRegistDto.setKyuryoTeateKyuryoReference(
                        convDecimal(trnPLInfoDto.getYakuinSalary())
                        .add(convDecimal(trnPLInfoDto.getSalarySalary()))
                        .add(convDecimal(trnPLInfoDto.getZakkyuSalary())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateKyuryoReference(null);
                }
            }
            
            
            boolean kyuryoTeateShoyoRefFlg = false;
	
	        // ■47b=44b+45b+46b成立しない場合は【E0607】（“計算結果が不整合“、“賞与　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.bonusKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinBonus_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salaryBonus_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuBonus_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getBonusKei())
		                .compareTo(convDecimal(trnPLInfoDto.getYakuinBonus())
		                .add(convDecimal(trnPLInfoDto.getSalaryBonus()))
						.add(convDecimal(trnPLInfoDto.getZakkyuBonus()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.bonusKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateShoyoRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.bonusKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "賞与　計");
//	        }
            // ★参考値設定 47b.給料手当(賞与) 計算結果 整合性エラー時
            if (kyuryoTeateShoyoRefFlg) {
                plRegistDto.setKyuryoTeateShoyoReference(
                        convDecimal(trnPLInfoDto.getYakuinBonus())
                        .add(convDecimal(trnPLInfoDto.getSalaryBonus()))
                        .add(convDecimal(trnPLInfoDto.getZakkyuBonus())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateShoyoReference(null);
                }
            }
            
            
            boolean kyuryoTeateTaishokuRefFlg = false;
	        
	        // ■47c=44c+45c+46c成立しない場合は【E0607】（“計算結果が不整合“、“退職金　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.retireKei_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinRetire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salaryRetire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuRetire_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getRetireKei())
		                .compareTo(convDecimal(trnPLInfoDto.getYakuinRetire())
		                .add(convDecimal(trnPLInfoDto.getSalaryRetire()))
						.add(convDecimal(trnPLInfoDto.getZakkyuRetire()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.retireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateTaishokuRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.retireKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "退職金　計");
//	        }
            // ★参考値設定 47c.給料手当(退職金) 計算結果 整合性エラー時
            if (kyuryoTeateTaishokuRefFlg) {
                plRegistDto.setKyuryoTeateTaishokuReference(
                        convDecimal(trnPLInfoDto.getYakuinRetire())
                        .add(convDecimal(trnPLInfoDto.getSalaryRetire()))
                        .add(convDecimal(trnPLInfoDto.getZakkyuRetire())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateTaishokuReference(null);
                }
            }
            
            
//            boolean kyuryoTeateKeiRefFlg = false;
//	
//	        // ■47d=44d+45d+46d成立しない場合は【E0607】（“計算結果が不整合“、“計　計“）を発生させる。
//            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.salaryUtiKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salaryKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuKei_COLUMN)) {
//		        if (convDecimal(trnPLInfoDto.getSalaryUtiKei())
//		                .compareTo(convDecimal(trnPLInfoDto.getYakuinKei())
//		                .add(convDecimal(trnPLInfoDto.getSalaryKei()))
//						.add(convDecimal(trnPLInfoDto.getZakkyuKei()))) != 0) {
//		            plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//                    kyuryoTeateKeiRefFlg = true;
//				}
//            } else {
//	            plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
//            }
////	        if (plDataErrorInfo.isExistError()) {
////	        	throw new GenericCommentException("計算結果が不整合", "計　計");
////	        }
//            // ★参考値設定 47d.給料手当(計) 計算結果 整合性エラー時
//            if (kyuryoTeateKeiRefFlg) {
//                plRegistDto.setKyuryoTeateKeiReference(
//                        convDecimal(trnPLInfoDto.getYakuinKei())
//                        .add(convDecimal(trnPLInfoDto.getSalaryKei()))
//                        .add(convDecimal(trnPLInfoDto.getZakkyuKei())));
//            } else {
//                plRegistDto.setKyuryoTeateKeiReference(null);
//            }
            
            
            boolean kyuryoTeateKeiYakuinRefFlg = false;
            
	        // ■44d=44a+44b+44c成立しない場合は【E0607】（“計算結果が不整合“、“役員報酬　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinSalary_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinBonus_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinRetire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.yakuinKei_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getYakuinKei())
		                .compareTo(convDecimal(trnPLInfoDto.getYakuinSalary())
		                .add(convDecimal(trnPLInfoDto.getYakuinBonus()))
						.add(convDecimal(trnPLInfoDto.getYakuinRetire()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.yakuinKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateKeiYakuinRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.yakuinKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }            
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "役員報酬　計");
//	        }
            // ★参考値設定 44d.給料手当(役員報酬)計 計算結果 整合性エラー時
            if (kyuryoTeateKeiYakuinRefFlg) {
                plRegistDto.setKyuryoTeateKeiYakuinReference(
                        convDecimal(trnPLInfoDto.getYakuinSalary())
                        .add(convDecimal(trnPLInfoDto.getYakuinBonus()))
                        .add(convDecimal(trnPLInfoDto.getYakuinRetire())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateKeiYakuinReference(null);
                }
            }
            
            
            boolean kyuryoTeateKeiKyuryoRefFlg = false;
	        
	        // ■45d=45a+45b+45c成立しない場合は【E0607】（“計算結果が不整合“、“給料手当　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.salarySalary_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salaryBonus_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salaryRetire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.salaryKei_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getSalaryKei())
		                .compareTo(convDecimal(trnPLInfoDto.getSalarySalary())
		                .add(convDecimal(trnPLInfoDto.getSalaryBonus()))
						.add(convDecimal(trnPLInfoDto.getSalaryRetire()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.salaryKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateKeiKyuryoRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.salaryKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "給料手当　計");
//	        }
            // ★参考値設定 45d.給料手当(給料手当)計 計算結果 整合性エラー時
            if (kyuryoTeateKeiKyuryoRefFlg) {
                plRegistDto.setKyuryoTeateKeiKyuryoReference(
                        convDecimal(trnPLInfoDto.getSalarySalary())
                        .add(convDecimal(trnPLInfoDto.getSalaryBonus()))
                        .add(convDecimal(trnPLInfoDto.getSalaryRetire())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateKeiKyuryoReference(null);
                }
            }
            
            
            boolean kyuryoTeateKeiZatsuKyuRefFlg = false;
	
	        // ■46d=46a+46b+46c成立しない場合は【E0607】（“計算結果が不整合“、“雑給　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuSalary_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuBonus_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuRetire_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.zakkyuKei_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getZakkyuKei())
		                .compareTo(convDecimal(trnPLInfoDto.getZakkyuSalary())
		                .add(convDecimal(trnPLInfoDto.getZakkyuBonus()))
						.add(convDecimal(trnPLInfoDto.getZakkyuRetire()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.zakkyuKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateKeiZatsuKyuRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.zakkyuKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "雑給　計");
//	        }
            // ★参考値設定 46d.給料手当(雑給)計 計算結果 整合性エラー時
            if (kyuryoTeateKeiZatsuKyuRefFlg) {
                plRegistDto.setKyuryoTeateKeiZatsuKyuReference(
                        convDecimal(trnPLInfoDto.getZakkyuSalary())
                        .add(convDecimal(trnPLInfoDto.getZakkyuBonus()))
                        .add(convDecimal(trnPLInfoDto.getZakkyuRetire())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateKeiZatsuKyuReference(null);
                }
            }
            
            
            
            boolean kyuryoTeateKeiKubunRefFlg = false;
	
	        // ■47d=47a+47b+47c成立しない場合は【E0607】（“計算結果が不整合“、“計　計“）を発生させる。
            if (!kyuryoTeateKyuryoRefFlg
                 && !kyuryoTeateShoyoRefFlg
                 && !kyuryoTeateTaishokuRefFlg
                 && !kyuryoTeateKeiYakuinRefFlg
                 && !kyuryoTeateKeiKyuryoRefFlg
                 && !kyuryoTeateKeiZatsuKyuRefFlg) {
		        if (convDecimal(trnPLInfoDto.getSalaryUtiKei())
		                .compareTo(convDecimal(trnPLInfoDto.getKyuryoKei())
		                .add(convDecimal(trnPLInfoDto.getBonusKei()))
						.add(convDecimal(trnPLInfoDto.getRetireKei()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
                    kyuryoTeateKeiKubunRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.salaryUtiKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "計　計");
//	        }
            // ★参考値設定 47d.給料手当計 計算結果 整合性エラー時
            if (kyuryoTeateKeiKubunRefFlg) {
                plRegistDto.setKyuryoTeateKeiKubunReference(
                        convDecimal(trnPLInfoDto.getKyuryoKei())
                        .add(convDecimal(trnPLInfoDto.getBonusKei()))
                        .add(convDecimal(trnPLInfoDto.getRetireKei())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKyuryoTeateKeiKubunReference(null);
                }
            }
            
            
    	}
    	
    	// ②前提条件で当てはまるもので31～47までの項目の上下限値チェック
    	// パラメータ.PLデータの各カラムがパラメータ.PL上下限値の範囲内であるかをチェック
    	// 範囲外の場合は、【E0607】（”値が範囲外”、各カラム名）を発生させる。
    	// 31～33の項目
        if (checkFlg31to33) {
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriage_COLUMN, trnPLInfoDto.getUriage(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppan_COLUMN, trnPLInfoDto.getBuppan(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.uriUchiwake_COLUMN, trnPLInfoDto.getUriUchiwake(), trnPLInfoDto.getUriagedaka(), limitValueList);
        }
        
        // 34～38の項目
        if (checkFlg34to38) {
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.elec_COLUMN, trnPLInfoDto.getElec(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.gas_COLUMN, trnPLInfoDto.getGas(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.water_COLUMN, trnPLInfoDto.getWater(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sonota_COLUMN, trnPLInfoDto.getOther(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.suikouUchiwake_COLUMN, trnPLInfoDto.getSuikouUchiwake(), trnPLInfoDto.getUriagedaka(), limitValueList);
        }
        
        // 39～43の項目
        if (checkFlg39to43) {
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genzairyoKei_COLUMN, trnPLInfoDto.getGenzairyoKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genzairyoShire_COLUMN, trnPLInfoDto.getGenzairyoShire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.genzairyoZaiko_COLUMN, trnPLInfoDto.getGenzairyoZaiko(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yasaiKei_COLUMN, trnPLInfoDto.getYasaiKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yasaiShire_COLUMN, trnPLInfoDto.getYasaiShire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yasaiZaiko_COLUMN, trnPLInfoDto.getYasaiZaiko(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houzaiKei_COLUMN, trnPLInfoDto.getHouzaiKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houzaiShire_COLUMN, trnPLInfoDto.getHouzaiShire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.houzaiZaiko_COLUMN, trnPLInfoDto.getHouzaiZaiko(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppanKei_COLUMN, trnPLInfoDto.getBuppanKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppanShire_COLUMN, trnPLInfoDto.getBuppanShire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.buppanZaiko_COLUMN, trnPLInfoDto.getBuppanZaiko(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touSiireKei_COLUMN, trnPLInfoDto.getTouSiireKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touZaikoKei_COLUMN, trnPLInfoDto.getTouZaikoKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.sashihikiKei_COLUMN, trnPLInfoDto.getSashihikiKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
        }        
        
        // 44～47の項目
        if (checkFlg44to47) {
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinSalary_COLUMN, trnPLInfoDto.getYakuinSalary(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinBonus_COLUMN, trnPLInfoDto.getYakuinBonus(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinRetire_COLUMN, trnPLInfoDto.getYakuinRetire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.yakuinKei_COLUMN, trnPLInfoDto.getYakuinKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salarySalary_COLUMN, trnPLInfoDto.getSalarySalary(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryBonus_COLUMN, trnPLInfoDto.getSalaryBonus(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryRetire_COLUMN, trnPLInfoDto.getSalaryRetire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryKei_COLUMN, trnPLInfoDto.getSalaryKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuSalary_COLUMN, trnPLInfoDto.getZakkyuSalary(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuBonus_COLUMN, trnPLInfoDto.getZakkyuBonus(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuRetire_COLUMN, trnPLInfoDto.getZakkyuRetire(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.zakkyuKei_COLUMN, trnPLInfoDto.getZakkyuKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kyuryoKei_COLUMN, trnPLInfoDto.getKyuryoKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.bonusKei_COLUMN, trnPLInfoDto.getBonusKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.retireKei_COLUMN, trnPLInfoDto.getRetireKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.salaryUtiKei_COLUMN, trnPLInfoDto.getSalaryUtiKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
        }    	
//        if (plDataErrorInfo.isExistError()) {
//        	throw new GenericCommentException("値が範囲外", "各カラム名");
//        }
    }
    
    /**
     * 借入金（メモ含む）内容チェック
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto
     */
    private void checkDebt(TrnPLInfo trnPLInfo, TrnZenPLInfo trnZenPLInfo,
			PlRegistDto plRegistDto, PlDataErrorInfo plDataErrorInfo,
			List limitValueList, TrnPLInfoDto trnPLInfoDto) {
    	// 48a　借入金　（a）前月残高
    	// 48b　借入金　（b）当月増加
    	// 48c　借入金　（c）当月減少
    	// 48d　借入金　（d）当月残高
    	// 49a　割賦未払金　（a）前月残高
    	// 49b　割賦未払金　（b）当月増加
    	// 49c　割賦未払金　（c）当月減少
    	// 49d　割賦未払金　（d）当月残高
    	// 50a　リース未払金　（a）前月残高
    	// 50b　リース未払金　（b）当月増加
    	// 50c　リース未払金　（c）当月減少
    	// 50d　リース未払金　（d）当月残高
    	// 51a　計　（a）前月残高
    	// 51b　計　（b）当月増加
    	// 51c　計　（c）当月減少
    	// 51d　計　（d）当月残高

    	// 前提条件
    	// 48～51の項目に１つでも入力されていたら、48～51もチェック対象とする
    	boolean checkFlg48to51 = false;
    	if ((!isNull(trnPLInfoDto.getKashiireUp()) && !isZero(trnPLInfoDto.getKashiireUp()))
    			|| (!isNull(trnPLInfoDto.getKashiireDown()) && !isZero(trnPLInfoDto.getKashiireDown()))
    			|| (!isNull(trnPLInfoDto.getKashiireZandaka()) && !isZero(trnPLInfoDto.getKashiireZandaka()))
    	    	|| (!isNull(trnPLInfoDto.getKappuUp()) && !isZero(trnPLInfoDto.getKappuUp()))
    	    	|| (!isNull(trnPLInfoDto.getKappuDown()) && !isZero(trnPLInfoDto.getKappuDown()))
    	    	|| (!isNull(trnPLInfoDto.getKappuZandaka()) && !isZero(trnPLInfoDto.getKappuZandaka()))
    	    	|| (!isNull(trnPLInfoDto.getLeaseUp()) && !isZero(trnPLInfoDto.getLeaseUp()))
    	    	|| (!isNull(trnPLInfoDto.getLeaseDown()) && !isZero(trnPLInfoDto.getLeaseDown()))
    	    	|| (!isNull(trnPLInfoDto.getLeaseZandaka()) && !isZero(trnPLInfoDto.getLeaseZandaka()))
    	    	|| (!isNull(trnPLInfoDto.getTouZoukaKei()) && !isZero(trnPLInfoDto.getTouZoukaKei()))
    	    	|| (!isNull(trnPLInfoDto.getTouGenshoKei()) && !isZero(trnPLInfoDto.getTouGenshoKei()))
    	    	|| (!isNull(trnPLInfoDto.getTouZandakaKei()) && !isZero(trnPLInfoDto.getTouZandakaKei()))) {
    		checkFlg48to51 = true;
    	}
    	
        
    	if (checkFlg48to51) {
    		
	    	// ①前提条件に当てはまるもので数値の妥当性チェック
	    	// 48～51までの項目の桁数が11桁以内かチェック　　11桁以上の場合は【E0505】（チェック対象項目名）を発生させる。
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kashiireUp_COLUMN, trnPLInfoDto.getKashiireUp(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kashiireDown_COLUMN, trnPLInfoDto.getKashiireDown(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kashiireZandaka_COLUMN, trnPLInfoDto.getKashiireZandaka(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kappuUp_COLUMN, trnPLInfoDto.getKappuUp(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kappuDown_COLUMN, trnPLInfoDto.getKappuDown(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.kappuZandaka_COLUMN, trnPLInfoDto.getKappuZandaka(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.leaseUp_COLUMN, trnPLInfoDto.getLeaseUp(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.leaseDown_COLUMN, trnPLInfoDto.getLeaseDown(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.leaseZandaka_COLUMN, trnPLInfoDto.getLeaseZandaka(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.touZoukaKei_COLUMN, trnPLInfoDto.getTouZoukaKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.touGenshoKei_COLUMN, trnPLInfoDto.getTouGenshoKei(), MAX_LENGTH);
    		checkNumericAndLength(plDataErrorInfo, TrnPLInfo.touZandakaKei_COLUMN, trnPLInfoDto.getTouZandakaKei(), MAX_LENGTH);
	        // メモ文字列長チェック
	        if (trnPLInfo.getMemo() != null
					&& trnPLInfo.getMemo().getBytes().length > 100) {
				plDataErrorInfo.add(TrnPLInfo.memo_COLUMN,
						PlDataErrorInfo.ERRORCODE_INVALID);
			}
	    	
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new InvalidInputException("赤字箇所の桁数");
//	        }
	
	    	// ■48～51までの項目は必須項目空白がある場合は【E0506】（チェック対象項目名）を発生させる。
	        checkRequire(plDataErrorInfo, TrnPLInfo.kashiireUp_COLUMN, trnPLInfoDto.getKashiireUp());
	        checkRequire(plDataErrorInfo, TrnPLInfo.kashiireDown_COLUMN, trnPLInfoDto.getKashiireDown());
	        checkRequire(plDataErrorInfo, TrnPLInfo.kashiireZandaka_COLUMN, trnPLInfoDto.getKashiireZandaka());
	        checkRequire(plDataErrorInfo, TrnPLInfo.kappuUp_COLUMN, trnPLInfoDto.getKappuUp());
	        checkRequire(plDataErrorInfo, TrnPLInfo.kappuDown_COLUMN, trnPLInfoDto.getKappuDown());
	        checkRequire(plDataErrorInfo, TrnPLInfo.kappuZandaka_COLUMN, trnPLInfoDto.getKappuZandaka());
	        checkRequire(plDataErrorInfo, TrnPLInfo.leaseUp_COLUMN, trnPLInfoDto.getLeaseUp());
	        checkRequire(plDataErrorInfo, TrnPLInfo.leaseDown_COLUMN, trnPLInfoDto.getLeaseDown());
	        checkRequire(plDataErrorInfo, TrnPLInfo.leaseZandaka_COLUMN, trnPLInfoDto.getLeaseZandaka());
	        checkRequire(plDataErrorInfo, TrnPLInfo.touZoukaKei_COLUMN, trnPLInfoDto.getTouZoukaKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.touGenshoKei_COLUMN, trnPLInfoDto.getTouGenshoKei());
	        checkRequire(plDataErrorInfo, TrnPLInfo.touZandakaKei_COLUMN, trnPLInfoDto.getTouZandakaKei());
	        
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new NotNullException("赤字箇所");
//	        }
	
            
            boolean kariireZandakaRefFlg = false;
	
	    	// ■48d=48a+48b-48c成立しない場合は【E0607】（“計算結果が不整合“、“借入金　当月残高“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireUp_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireDown_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireZandaka_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getKashiireZandaka())
		                .compareTo(trnZenPLInfo.getKashiireZandaka()
		                .add(convDecimal(trnPLInfoDto.getKashiireUp()))
						.subtract(convDecimal(trnPLInfoDto.getKashiireDown()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.kashiireZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                    kariireZandakaRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.kashiireZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "借入金　当月残高");
//	        }
            // ★参考値設定 48d.借入金計 計算結果 整合性エラー時
            if (kariireZandakaRefFlg) {
                plRegistDto.setKariireZandakaReference(
                        trnZenPLInfo.getKashiireZandaka()
                        .add(convDecimal(trnPLInfoDto.getKashiireUp()))
                        .subtract(convDecimal(trnPLInfoDto.getKashiireDown())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKariireZandakaReference(null);
                }
            }
            
            
            boolean wappuZandakaRefFlg = false;
	
	    	// ■49d=49a+49b-49c成立しない場合は【E0607】（“計算結果が不整合“、“割賦未払金　当月残高“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.kappuUp_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.kappuDown_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.kappuZandaka_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getKappuZandaka())
		                .compareTo(trnZenPLInfo.getKappuZandaka()
		                .add(convDecimal(trnPLInfoDto.getKappuUp()))
						.subtract(convDecimal(trnPLInfoDto.getKappuDown()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.kappuZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                    wappuZandakaRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.kappuZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "割賦未払金　当月残高");
//	        }
            // ★参考値設定 49d.割賦未払い金計 計算結果 整合性エラー時
            if (wappuZandakaRefFlg) {
                plRegistDto.setWappuZandakaReference(
                        trnZenPLInfo.getKappuZandaka()
                        .add(convDecimal(trnPLInfoDto.getKappuUp()))
                        .subtract(convDecimal(trnPLInfoDto.getKappuDown())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setWappuZandakaReference(null);
                }
            }
            
	
            boolean leaseZandakaRefFlg = false;
            
	        // ■50d=50a+50b-50c成立しない場合は【E0607】（“計算結果が不整合“、“リース未払金　当月残高“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.leaseUp_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.leaseDown_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.leaseZandaka_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getLeaseZandaka())
		                .compareTo(trnZenPLInfo.getLeaseZandaka()
		                .add(convDecimal(trnPLInfoDto.getLeaseUp()))
						.subtract(convDecimal(trnPLInfoDto.getLeaseDown()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.leaseZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                    leaseZandakaRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.leaseZandaka_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
            }
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "リース未払金　当月残高");
//	        }
            // ★参考値設定 50d.リース未払い金計 計算結果 整合性エラー時
            if (leaseZandakaRefFlg) {
                plRegistDto.setLeaseZandakaReference(
                        trnZenPLInfo.getLeaseZandaka()
                        .add(convDecimal(trnPLInfoDto.getLeaseUp()))
                        .subtract(convDecimal(trnPLInfoDto.getLeaseDown())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setLeaseZandakaReference(null);
                }
            }
            
	
            
            
//	        // ■51d=51a+51b-51c成立しない場合は【E0607】（“計算結果が不整合“、“計　当月残高“）を発生させる。
//            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.touZoukaKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.touGenshoKei_COLUMN)
//                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.touZandakaKei_COLUMN)) {
//		        if (convDecimal(trnPLInfoDto.getTouZandakaKei())
//		                .compareTo(trnZenPLInfo.getTouZandakaKei()
//		                .add(convDecimal(trnPLInfoDto.getTouZoukaKei()))
//						.subtract(convDecimal(trnPLInfoDto.getTouGenshoKei()))) != 0) {
//		            plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
//				}
//            } else {
//	            plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
//            }
//            
////	        if (plDataErrorInfo.isExistError()) {
////	        	throw new GenericCommentException("計算結果が不整合", "計　当月残高");
////	        }
	
            
            
            boolean tougetsuZoukaRefFlg = false;
            
	        // ■51b=48b+49b+50b成立しない場合は【E0607】（“計算結果が不整合“、“当月増加　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireUp_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.kappuUp_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.leaseUp_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.touZoukaKei_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getTouZoukaKei())
		                .compareTo(convDecimal(trnPLInfoDto.getKashiireUp())
		                .add(convDecimal(trnPLInfoDto.getKappuUp()))
						.add(convDecimal(trnPLInfoDto.getLeaseUp()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.touZoukaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                    tougetsuZoukaRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.touZoukaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "前月残高　計");
//	        }
            // ★参考値設定 51b.当月増加金計 計算結果 整合性エラー時
            if (tougetsuZoukaRefFlg) {
                plRegistDto.setTougetsuZoukaReference(
                        convDecimal(trnPLInfoDto.getKashiireUp())
                        .add(convDecimal(trnPLInfoDto.getKappuUp()))
                        .add(convDecimal(trnPLInfoDto.getLeaseUp())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setTougetsuZoukaReference(null);
                }
            }
            
	
            boolean tougetsuGensyoRefFlg = false;
            
	    	// ■51c=48c+49c+50c成立しない場合は【E0607】（“計算結果が不整合“、“当月減少　計“）を発生させる。
            if (!plDataErrorInfo.isErrorItem(TrnPLInfo.kashiireDown_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.kappuDown_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.leaseDown_COLUMN)
                    && !plDataErrorInfo.isErrorItem(TrnPLInfo.touGenshoKei_COLUMN)) {
		        if (convDecimal(trnPLInfoDto.getTouGenshoKei())
		                .compareTo(convDecimal(trnPLInfoDto.getKashiireDown())
		                .add(convDecimal(trnPLInfoDto.getKappuDown()))
						.add(convDecimal(trnPLInfoDto.getLeaseDown()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.touGenshoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                    tougetsuGensyoRefFlg = true;
				}
            } else {
	            plDataErrorInfo.add(TrnPLInfo.touGenshoKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
            }
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "前月残高　計");
//	        }
            // ★参考値設定 51c.当月減少金計 計算結果 整合性エラー時
            if (tougetsuGensyoRefFlg) {
                plRegistDto.setTougetsuGensyoReference(
                        convDecimal(trnPLInfoDto.getKashiireDown())
                        .add(convDecimal(trnPLInfoDto.getKappuDown()))
                        .add(convDecimal(trnPLInfoDto.getLeaseDown())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setTougetsuGensyoReference(null);
                }
            }
            
	
            
            boolean kariireRefFlg = false;
            
	    	// ■51d=48d+49d+50d成立しない場合は【E0607】（“計算結果が不整合“、“当月残高　計“）を発生させる。
            if (!kariireZandakaRefFlg
                 && !wappuZandakaRefFlg
                 && !leaseZandakaRefFlg
                 && !tougetsuZoukaRefFlg
                 && !tougetsuGensyoRefFlg) {
		        if (convDecimal(trnPLInfoDto.getTouZandakaKei())
		                .compareTo(convDecimal(trnPLInfoDto.getKashiireZandaka())
		                .add(convDecimal(trnPLInfoDto.getKappuZandaka()))
						.add(convDecimal(trnPLInfoDto.getLeaseZandaka()))) != 0) {
		            plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
                    kariireRefFlg = true;
		        }
	        } else {
	            plDataErrorInfo.add(TrnPLInfo.touZandakaKei_COLUMN, PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN);
			}
//            if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("計算結果が不整合", "前月残高　計");
//	        }
            // ★参考値設定 51d.借入金計 計算結果 整合性エラー時
            if (kariireRefFlg) {
                plRegistDto.setKariireReference(
                        convDecimal(trnPLInfoDto.getKashiireZandaka())
                        .add(convDecimal(trnPLInfoDto.getKappuZandaka()))
                        .add(convDecimal(trnPLInfoDto.getLeaseZandaka())));
            } else {
                if(plRegistDto.isErrSwitchFlg()) {
                    plRegistDto.setKariireReference(null);
                }
            }
            
            
            
	    	// ②前提条件で当てはまるもので48～51までの項目の上下限値チェック
	    	// パラメータ.PLデータの各カラムがパラメータ.PL上下限値の範囲内であるかをチェック　範囲外の場合は、【E0607】（”値が範囲外”、各カラム名）を発生させる。
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kashiireUp_COLUMN, trnPLInfoDto.getKashiireUp(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kashiireDown_COLUMN, trnPLInfoDto.getKashiireDown(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kashiireZandaka_COLUMN, trnPLInfoDto.getKashiireZandaka(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kappuUp_COLUMN, trnPLInfoDto.getKappuUp(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kappuDown_COLUMN, trnPLInfoDto.getKappuDown(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.kappuZandaka_COLUMN, trnPLInfoDto.getKappuZandaka(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.leaseUp_COLUMN, trnPLInfoDto.getLeaseUp(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.leaseDown_COLUMN, trnPLInfoDto.getLeaseDown(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.leaseZandaka_COLUMN, trnPLInfoDto.getLeaseZandaka(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touZoukaKei_COLUMN, trnPLInfoDto.getTouZoukaKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touGenshoKei_COLUMN, trnPLInfoDto.getTouGenshoKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
            checkLimitKai(plDataErrorInfo, trnPLInfo.getPlType(), TrnPLInfo.touZandakaKei_COLUMN, trnPLInfoDto.getTouZandakaKei(), trnPLInfoDto.getUriagedaka(), limitValueList);
	
//	        if (plDataErrorInfo.isExistError()) {
//	        	throw new GenericCommentException("値が範囲外", "各カラム名");
//	        }
    	}
    	else {
	        // メモ文字列長チェック
    		if (trnPLInfo.getMemo() != null
    				&& trnPLInfo.getMemo().getBytes().length > 100) {
    			plDataErrorInfo.add(TrnPLInfo.memo_COLUMN,
    					PlDataErrorInfo.ERRORCODE_INVALID);
    		}

//    		if (plDataErrorInfo.isExistError()) {
//    			throw new InvalidInputException("メモ・通信");
//    		}
    	}
    }
    
    /**
	 * 必須チェック
	 * @param plDataErrorInfo P/Lデータエラー情報
	 * @param itemCode 項目名
	 * @param value チェック対象数値
	 */
    protected void checkRequire(PlDataErrorInfo plDataErrorInfo,
			String itemCode, String value) {

        if (value == null || value.equals("")) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_REQUIRED);
        }
    }

    /**
     * 数値妥当性＆桁数チェック
     * @param plDataErrorInfo P/Lデータエラー情報
     * @param itemCode 項目名
     * @param value チェック対象数値
     * @param length 最大桁数
     */
    protected void checkNumericAndLength(PlDataErrorInfo plDataErrorInfo,
			String itemCode, String value, int length) {

    	if (!isNumericFormat(value)) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_INVALID);
    	}
    	
        if (value != null && value.length() > length) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_INVALID);
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
            String value,
            String uriagedaka,
            List limitValueList) {
        if (!PlLimitUtils.checkLimit(plType, itemCode, value, uriagedaka, limitValueList)) {
            plDataErrorInfo.add(itemCode, PlDataErrorInfo.ERRORCODE_NUM_LIMIT);
        }
    }

    /**
    
    
    /**
	 * POS売上チェック
	 * 
	 * @param plDataErrorInfo
	 *            P/Lデータエラー情報
	 * @param itemCode
	 *            項目名
	 * @param value
	 *            チェック対象数値
	 * @param sales
	 *            POS売上
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
     * 初回入力状態チェック
     * ※条件1: 月次損益タブの売上高がPOS売上げと一致
     * ※条件2: 月次損益タブの売上高以外の項目が全て0
     * ※条件3: 内訳タブの全項目が全て0
     * ※条件4: 借入金タブの全項目が全て0
     * 
     * @param PlRegistDto P/Lデータ入力用Dto
     * @param TrnPLInfoDto P/LデータDto
     * @return true:初回入力
     */
    private boolean checkFirstRecord(PlRegistDto plRegistDto, TrnPLInfoDto trnPLInfoDto){
        
        trnPLInfoDto.setDefaultFlg(false);
        
        // 1.月次損益タブの売上高がPOS売上げと一致
        if ( plRegistDto.getTrnPosZenUriage() != null ) {
            if (!trnPLInfoDto.getUriagedaka()
                    .equals(String.valueOf(plRegistDto.getTrnPosZenUriage().getUriage()))) 
                return false;
        }
        
        // 2.月次損益タブの売上高以外の項目が全て0
        if (!isNull(trnPLInfoDto.getUriagegenka()) && !isZero(trnPLInfoDto.getUriagegenka())) return false;
        if (!isNull(trnPLInfoDto.getUriageSoRieki()) && !isZero(trnPLInfoDto.getUriageSoRieki())) return false;
        if (!isNull(trnPLInfoDto.getSalary()) && !isZero(trnPLInfoDto.getSalary())) return false;
        if (!isNull(trnPLInfoDto.getYachin()) && !isZero(trnPLInfoDto.getYachin())) return false;
        if (!isNull(trnPLInfoDto.getSuikouHi()) && !isZero(trnPLInfoDto.getSuikouHi())) return false;
        if (!isNull(trnPLInfoDto.getRoyalty()) && !isZero(trnPLInfoDto.getRoyalty())) return false;
        if (!isNull(trnPLInfoDto.getTesuryo()) && !isZero(trnPLInfoDto.getTesuryo())) return false;
        if (!isNull(trnPLInfoDto.getKoukoku()) && !isZero(trnPLInfoDto.getKoukoku())) return false;
        if (!isNull(trnPLInfoDto.getShoumou()) && !isZero(trnPLInfoDto.getShoumou())) return false;
        if (!isNull(trnPLInfoDto.getHouteiFukuri()) && !isZero(trnPLInfoDto.getHouteiFukuri())) return false;
        if (!isNull(trnPLInfoDto.getFukuriKousei()) && !isZero(trnPLInfoDto.getFukuriKousei())) return false;
        if (!isNull(trnPLInfoDto.getKousai()) && !isZero(trnPLInfoDto.getKousai())) return false;
        if (!isNull(trnPLInfoDto.getRyohi()) && !isZero(trnPLInfoDto.getRyohi())) return false;
        if (!isNull(trnPLInfoDto.getTusin()) && !isZero(trnPLInfoDto.getTusin())) return false;
        if (!isNull(trnPLInfoDto.getLease()) && !isZero(trnPLInfoDto.getLease())) return false;
        if (!isNull(trnPLInfoDto.getSharyo()) && !isZero(trnPLInfoDto.getSharyo())) return false;
        if (!isNull(trnPLInfoDto.getSozei()) && !isZero(trnPLInfoDto.getSozei())) return false;
        if (!isNull(trnPLInfoDto.getHoken()) && !isZero(trnPLInfoDto.getHoken())) return false;
        if (!isNull(trnPLInfoDto.getUnchin()) && !isZero(trnPLInfoDto.getUnchin())) return false;
        if (!isNull(trnPLInfoDto.getShuzen()) && !isZero(trnPLInfoDto.getShuzen())) return false;
        if (!isNull(trnPLInfoDto.getYobi()) && !isZero(trnPLInfoDto.getYobi())) return false;
        if (!isNull(trnPLInfoDto.getZappi()) && !isZero(trnPLInfoDto.getZappi())) return false;
        if (!isNull(trnPLInfoDto.getKeihiShokei()) && !isZero(trnPLInfoDto.getKeihiShokei())) return false;
        if (!isNull(trnPLInfoDto.getShokyakuRieki()) && !isZero(trnPLInfoDto.getShokyakuRieki())) return false;
        if (!isNull(trnPLInfoDto.getGenkaShokyaku()) && !isZero(trnPLInfoDto.getGenkaShokyaku())) return false;
        if (!isNull(trnPLInfoDto.getEigaiShueki()) && !isZero(trnPLInfoDto.getEigaiShueki())) return false;
        if (!isNull(trnPLInfoDto.getEigaiHiyo()) && !isZero(trnPLInfoDto.getEigaiHiyo())) return false;
        if (!isNull(trnPLInfoDto.getHonshahiHai()) && !isZero(trnPLInfoDto.getHonshahiHai())) return false;
        if (!isNull(trnPLInfoDto.getRieki()) && !isZero(trnPLInfoDto.getRieki())) return false;
        
        // 3.内訳タブの全項目がすべて0
        if (!isNull(trnPLInfoDto.getUriage()) && !isZero(trnPLInfoDto.getUriage())) return false;
        if (!isNull(trnPLInfoDto.getBuppan()) && !isZero(trnPLInfoDto.getBuppan())) return false;
        if (!isNull(trnPLInfoDto.getUriUchiwake()) && !isZero(trnPLInfoDto.getUriUchiwake())) return false;
        if (!isNull(trnPLInfoDto.getElec()) && !isZero(trnPLInfoDto.getElec())) return false;
        if (!isNull(trnPLInfoDto.getGas()) && !isZero(trnPLInfoDto.getGas())) return false;
        if (!isNull(trnPLInfoDto.getWater()) && !isZero(trnPLInfoDto.getWater())) return false;
        if (!isNull(trnPLInfoDto.getOther()) && !isZero(trnPLInfoDto.getOther())) return false;
        if (!isNull(trnPLInfoDto.getSuikouUchiwake()) && !isZero(trnPLInfoDto.getSuikouUchiwake())) return false;
        if (!isNull(trnPLInfoDto.getGenzairyoKei()) && !isZero(trnPLInfoDto.getGenzairyoKei())) return false;
        if (!isNull(trnPLInfoDto.getGenzairyoShire()) && !isZero(trnPLInfoDto.getGenzairyoShire())) return false;
        if (!isNull(trnPLInfoDto.getGenzairyoZaiko()) && !isZero(trnPLInfoDto.getGenzairyoZaiko())) return false;
        if (!isNull(trnPLInfoDto.getYasaiKei()) && !isZero(trnPLInfoDto.getYasaiKei())) return false;
        if (!isNull(trnPLInfoDto.getYasaiShire()) && !isZero(trnPLInfoDto.getYasaiShire())) return false;
        if (!isNull(trnPLInfoDto.getYasaiZaiko()) && !isZero(trnPLInfoDto.getYasaiZaiko())) return false;
        if (!isNull(trnPLInfoDto.getHouzaiKei()) && !isZero(trnPLInfoDto.getHouzaiKei())) return false;
        if (!isNull(trnPLInfoDto.getHouzaiShire()) && !isZero(trnPLInfoDto.getHouzaiShire())) return false;
        if (!isNull(trnPLInfoDto.getHouzaiZaiko()) && !isZero(trnPLInfoDto.getHouzaiZaiko())) return false;
        if (!isNull(trnPLInfoDto.getBuppanKei()) && !isZero(trnPLInfoDto.getBuppanKei())) return false;
        if (!isNull(trnPLInfoDto.getBuppanShire()) && !isZero(trnPLInfoDto.getBuppanShire())) return false;
        if (!isNull(trnPLInfoDto.getBuppanZaiko()) && !isZero(trnPLInfoDto.getBuppanZaiko())) return false;
        if (!isNull(trnPLInfoDto.getTouSiireKei()) && !isZero(trnPLInfoDto.getTouSiireKei())) return false;
        if (!isNull(trnPLInfoDto.getTouZaikoKei()) && !isZero(trnPLInfoDto.getTouZaikoKei())) return false;
        if (!isNull(trnPLInfoDto.getSashihikiKei()) && !isZero(trnPLInfoDto.getSashihikiKei())) return false;
        if (!isNull(trnPLInfoDto.getYakuinSalary()) && !isZero(trnPLInfoDto.getYakuinSalary())) return false;
        if (!isNull(trnPLInfoDto.getYakuinBonus()) && !isZero(trnPLInfoDto.getYakuinBonus())) return false;
        if (!isNull(trnPLInfoDto.getYakuinRetire()) && !isZero(trnPLInfoDto.getYakuinRetire())) return false;
        if (!isNull(trnPLInfoDto.getYakuinKei()) && !isZero(trnPLInfoDto.getYakuinKei())) return false;
        if (!isNull(trnPLInfoDto.getSalarySalary()) && !isZero(trnPLInfoDto.getSalarySalary())) return false;
        if (!isNull(trnPLInfoDto.getSalaryBonus()) && !isZero(trnPLInfoDto.getSalaryBonus())) return false;
        if (!isNull(trnPLInfoDto.getSalaryRetire()) && !isZero(trnPLInfoDto.getSalaryRetire())) return false;
        if (!isNull(trnPLInfoDto.getSalaryKei()) && !isZero(trnPLInfoDto.getSalaryKei())) return false;
        if (!isNull(trnPLInfoDto.getZakkyuSalary()) && !isZero(trnPLInfoDto.getZakkyuSalary())) return false;
        if (!isNull(trnPLInfoDto.getZakkyuBonus()) && !isZero(trnPLInfoDto.getZakkyuBonus())) return false;
        if (!isNull(trnPLInfoDto.getZakkyuRetire()) && !isZero(trnPLInfoDto.getZakkyuRetire())) return false;
        if (!isNull(trnPLInfoDto.getZakkyuKei()) && !isZero(trnPLInfoDto.getZakkyuKei())) return false;
        if (!isNull(trnPLInfoDto.getKyuryoKei()) && !isZero(trnPLInfoDto.getKyuryoKei())) return false;
        if (!isNull(trnPLInfoDto.getBonusKei()) && !isZero(trnPLInfoDto.getBonusKei())) return false;
        if (!isNull(trnPLInfoDto.getRetireKei()) && !isZero(trnPLInfoDto.getRetireKei())) return false;
        if (!isNull(trnPLInfoDto.getSalaryUtiKei()) && !isZero(trnPLInfoDto.getSalaryUtiKei())) return false;
        
        // 4.借入金タブの全項目が全て0
        if (!isNull(trnPLInfoDto.getKashiireUp()) && !isZero(trnPLInfoDto.getKashiireUp())) return false;
        if (!isNull(trnPLInfoDto.getKashiireDown()) && !isZero(trnPLInfoDto.getKashiireDown())) return false;
        if (!isNull(trnPLInfoDto.getKashiireZandaka()) && !isZero(trnPLInfoDto.getKashiireZandaka())) return false;
        if (!isNull(trnPLInfoDto.getKappuUp()) && !isZero(trnPLInfoDto.getKappuUp())) return false;
        if (!isNull(trnPLInfoDto.getKappuDown()) && !isZero(trnPLInfoDto.getKappuDown())) return false;
        if (!isNull(trnPLInfoDto.getKappuZandaka()) && !isZero(trnPLInfoDto.getKappuZandaka())) return false;
        if (!isNull(trnPLInfoDto.getLeaseUp()) && !isZero(trnPLInfoDto.getLeaseUp())) return false;
        if (!isNull(trnPLInfoDto.getLeaseDown()) && !isZero(trnPLInfoDto.getLeaseDown())) return false;
        if (!isNull(trnPLInfoDto.getLeaseZandaka()) && !isZero(trnPLInfoDto.getLeaseZandaka())) return false;
        if (!isNull(trnPLInfoDto.getTouZoukaKei()) && !isZero(trnPLInfoDto.getTouZoukaKei())) return false;
        if (!isNull(trnPLInfoDto.getTouGenshoKei()) && !isZero(trnPLInfoDto.getTouGenshoKei())) return false;
        if (!isNull(trnPLInfoDto.getTouZandakaKei()) && !isZero(trnPLInfoDto.getTouZandakaKei())) return false;
        if (!isNull(trnPLInfoDto.getMemo()) && !trnPLInfoDto.getMemo().equals("")) return false;
        
        trnPLInfoDto.setDefaultFlg(true);
        
        return true;
    }
    
    
    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
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
    private boolean isZero(String val) {
		if (val != null && isNumericFormat(val)
				&& new BigDecimal(val).compareTo(BigDecimal.valueOf(0)) == 0) {
			return true;
		}
		return false;
	}

    /**
     * 数値チェック
     */
    private boolean isNumericFormat(String val) {
    	NumericVerifier numericVerifier = new NumericVerifier();
		if (!isNull(val)
				&& !numericVerifier.validate(val)) {
			return false;
		}
		return true;
    }
    
    /**
     * String から BigDecimalへ変換
     * @return パラメータ値がnull、または数値以外の場合は「0」を戻す
     */
    private BigDecimal convDecimal(String st) {
    	return (st == null || !isNumericFormat(st)) ? new BigDecimal("0")
				: new BigDecimal(st);
    }
}
