package jp.co.isid.mos.bird.bizreport.posreportref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.dao.TrnPosReportRefDao;
import jp.co.isid.mos.bird.bizreport.posreportref.dto.PosReportRefDto;
import jp.co.isid.mos.bird.bizreport.posreportref.entity.TrnPosReportRef;
import jp.co.isid.mos.bird.bizreport.posreportref.logic.SearchMiseListLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 店一覧検索ロジック
 * 
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public class SearchMiseListLogicImpl implements SearchMiseListLogic {
    /*LOGIC_ID**/
    public static final String LOGIC_ID = "BBR006L02";
    
    /**DAO[POS速報]**/
    private TrnPosReportRefDao trnPosReportRefDao;
    
    /**
     * 店一覧POS情報
     * 
     * @param birdUserInfo BIRDユーザー情報
     * @param posReportRefDto
     * @return 検索結果
     */
    public List execute(
    	   BirdUserInfo birdUserInfo
   		 , PosReportRefDto requestDto) 
    {
    	//１．新規でList[[POS速報]]をインスタンス化します。
    	List listPosSokuho = new ArrayList(0);
    	String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
        if (UserType.isHonbu(userTypeCd)) {
            //２．ログインユーザーが”本部”の場合、DAO[POS速報].本部用店舗一覧検索を実行し、
        	//    処理１のList[[POS速報]]へ戻り値を代入します。
        	listPosSokuho = getTrnPosReportRefDao().selectHonbuMiseList(
	        		birdUserInfo, requestDto);
        }
        else if (UserType.isOner(userTypeCd)) {
            //３．ログインユーザーが”オーナー”の場合、DAO[POS速報].オーナー用店舗一覧検索を実行し、
        	//    処理１のList[[POS速報]]へ戻り値を代入します。
        	listPosSokuho = getTrnPosReportRefDao().selectOnerMiseList(
        		birdUserInfo, requestDto);
        }
        //４．処理１のList[[POS速報]]のサイズが0より大きい場合、合計行作成処理を行います。
        if (listPosSokuho.size() > 0) {
        	BigDecimal decUriage = new BigDecimal("0");
        	BigDecimal decKyakusu = new BigDecimal("0");
        	BigDecimal decEatKin = new BigDecimal("0");
        	BigDecimal decEatKen = new BigDecimal("0");
        	BigDecimal decTakeKin = new BigDecimal("0");
        	BigDecimal decTakeKen = new BigDecimal("0");
        	BigDecimal decTelKin = new BigDecimal("0");
        	BigDecimal decTelKen = new BigDecimal("0");
        	BigDecimal decDriveKin = new BigDecimal("0");
        	BigDecimal decDriveKen = new BigDecimal("0");
        	BigDecimal decOtherKin = new BigDecimal("0");
        	BigDecimal decOtherKen = new BigDecimal("0");
        	for (int i = 0; i < listPosSokuho.size(); i++) {
        		TrnPosReportRef posData = (TrnPosReportRef)listPosSokuho.get(i);
        		decUriage   = decUriage.add(new BigDecimal(posData.getAllKin()));
            	decKyakusu  = decKyakusu.add(new BigDecimal(posData.getAllKen()));
            	decEatKin   = decEatKin.add(new BigDecimal(posData.getEatKin()));
            	decEatKen   = decEatKen.add(new BigDecimal(posData.getEatKen()));
            	decTakeKin  = decTakeKin.add(new BigDecimal(posData.getTakeKin()));
            	decTakeKen  = decTakeKen.add(new BigDecimal(posData.getTakeKen()));
            	decTelKin   = decTelKin.add(new BigDecimal(posData.getTelKin()));
            	decTelKen   = decTelKen.add(new BigDecimal(posData.getTelKen()));
            	decDriveKin = decDriveKin.add(new BigDecimal(posData.getDriveKin()));
            	decDriveKen = decDriveKen.add(new BigDecimal(posData.getDriveKen()));
            	decOtherKin = decOtherKin.add(new BigDecimal(posData.getOtherKin()));
            	decOtherKen = decOtherKen.add(new BigDecimal(posData.getOtherKen()));
        	}
        	//合計行用にEntity[POS速報]を新しくインスタンス化します。
        	TrnPosReportRef total = new TrnPosReportRef();
        	total.setMiseCd("TOTAL");
        	total.setMiseNameKj("総合計");
        	total.setShuSysTime("");
        	total.setAllKin(decUriage.toString());
        	total.setAllKen(decKyakusu.toString());
        	total.setEatKin(decEatKin.toString());
        	total.setEatKen(decEatKen.toString());
        	total.setTakeKin(decTakeKin.toString());
        	total.setTakeKen(decTakeKen.toString());
        	total.setTelKin(decTelKin.toString());
        	total.setTelKen(decTelKen.toString());
        	total.setDriveKin(decDriveKin.toString());
        	total.setDriveKen(decDriveKen.toString());
        	total.setOtherKin(decOtherKin.toString());
        	total.setOtherKen(decOtherKen.toString());
        	listPosSokuho.add(total);
        }
        return listPosSokuho;
    }
    /**
     * trnPosReportRefDaoを取得
     * @return trnPosReportRefDao
     */
    public TrnPosReportRefDao getTrnPosReportRefDao() {
        return trnPosReportRefDao;
    }

    /**
     * trnPosReportRefDaoを設定
     * @param trnPosReportRefDao
     */
    public void setTrnPosReportRefDao(TrnPosReportRefDao trnPosReportRefDao) {
        this.trnPosReportRefDao = trnPosReportRefDao;
    }
}
