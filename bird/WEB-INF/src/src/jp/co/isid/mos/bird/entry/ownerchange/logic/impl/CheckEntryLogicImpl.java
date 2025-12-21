/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.ownerchange.dao.TrnEntryDataDao;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;
import jp.co.isid.mos.bird.entry.ownerchange.entity.TrnEntryData;
import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;

/**
 * エントリー状況チェックロジック
 * @author xkonishi
 *
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L05";
    
    /**
     * エントリー状況Dao
     */
    private TrnEntryDataDao trnEntryDataDao;
    
    /**
     * エントリー状況チェックロジック
     * @param システム日付
     * @param スタッフ情報
     */
    public void execute(String sysDate, MstStaffInfo mstStaffInfo) {

        // Dao【エントリー状況DAO．マスターライセンスエントリー状況取得】を実行。
        List mastarLicense = trnEntryDataDao.selectByMastarLicense(mstStaffInfo.getStaffId(), sysDate);

        // Dao【エントリー状況DAO．研修エントリー状況取得】を実行。
        List training = trnEntryDataDao.selectByTraining( mstStaffInfo.getStaffId(), sysDate);
        
        // 対象スタッフがエントリー中であった場合
        if( !mastarLicense.isEmpty() || !training.isEmpty()) {
            
            mastarLicense.addAll(training);
        
            String entryName = "";
            
            // エントリー中のタイトル名を取得
            for(int i = 0; i < mastarLicense.size(); i++) {
                
                TrnEntryData trnEntryData = (TrnEntryData) mastarLicense.get(i);

                if(entryName.length() != 0) {
                    entryName += "、";
                } 
                
                entryName += trnEntryData.getEntryTitle();
                
            }
            throw new CannotExecuteException("対象スタッフは、" + entryName + "にエントリー中の為、選択");
        }           
    }


    public TrnEntryDataDao getTrnEntryDataDao() {
        return trnEntryDataDao;
    }

    public void setTrnEntryDataDao(TrnEntryDataDao trnEntryDataDao) {
        this.trnEntryDataDao = trnEntryDataDao;
    }
}