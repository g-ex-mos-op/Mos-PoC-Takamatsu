package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dao.TrnPosReportRefMenuDao;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.entity.TrnPosReportRefMenu;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.PosReportRefMenuInfoLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
    /**
     * 店別メニュー別POS情報取得ロジック
     * @author inazawa
     * 2007/02/07
     */

public class PosReportRefMenuInfoLogicImpl implements PosReportRefMenuInfoLogic{
    
    /*LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L04";

    /*DAO[店別メニュー別POS情報]**/
    TrnPosReportRefMenuDao trnPosReportRefMenuDao;
    /**
     * 店別メニュー別POS情報取得
     * @param posReportRefDto
     * @return 店別メニュー別POS情報
     */
    public List execute(PosReportRefDto posReportRefDto) {
    	List listMenuInfo = getTrnPosReportRefMenuDao().getPosReportRefMenu(
    			posReportRefDto.getCompanyCd(),posReportRefDto.getMiseCd(),posReportRefDto.getLatestDate());
    	//全メニュー売上合計
    	BigDecimal decTotalUriage = new BigDecimal("0");
    	for(int i=0; i<listMenuInfo.size(); i++) {
    		decTotalUriage = decTotalUriage.add(
    				new BigDecimal(
    					((TrnPosReportRefMenu)listMenuInfo.get(i)).getUriageKingaku()));
    	}
    	for(int i=0; i<listMenuInfo.size(); i++) {
    		TrnPosReportRefMenu eMenu = (TrnPosReportRefMenu)listMenuInfo.get(i);
    		//金額構成比の算出
    		eMenu.setKingakuKouseiHi(Calculator.percentage(
    				new BigDecimal(eMenu.getUriageKingaku())
    			,   decTotalUriage, 2));
    	}
    	
        return listMenuInfo;
    }
    
    /**
     * trnPosReportRefMenuDaoを設定
     * @return trnPosReportRefMenuDao
     */
    public TrnPosReportRefMenuDao getTrnPosReportRefMenuDao() {
        return trnPosReportRefMenuDao;
    }
    /**
     * trnPosReportRefMenuDaoを取得
     * @param trnPosReportRefMenuDao
     */
    public void setTrnPosReportRefMenuDao(TrnPosReportRefMenuDao trnPosReportRefMenuDao) {
        this.trnPosReportRefMenuDao = trnPosReportRefMenuDao;
    }
}
