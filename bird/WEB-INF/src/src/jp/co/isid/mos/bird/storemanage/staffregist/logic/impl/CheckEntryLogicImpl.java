/*
 * 作成日:2007/02/19
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.storemanage.staffregist.dao.TrnEntryDataDao;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.TrnEntryData;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CheckEntryLogic;

/**
 * エントリー状況チェックロジック
 * @author inazawa
 *
 */
public class CheckEntryLogicImpl implements CheckEntryLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BSM004L08";
    
    /**
     * エントリー状況Dao
     */
    private TrnEntryDataDao trnEntryDataDao;
    
    /**
     * エントリー状況チェックロジック
     * @param システム日付
     * @param スタッフ情報
     */
    public void execute(String sysDate,String staffId) {

        // Dao【エントリー状況DAO．マスターライセンスエントリー状況取得】を実行。
        List mastarLicense = trnEntryDataDao.selectByMastarLicense(staffId, sysDate);

        // Dao【エントリー状況DAO．研修エントリー状況取得】を実行。
        List training = trnEntryDataDao.selectByTraining(staffId, sysDate);
        
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
            throw new CannotExecuteWithReasonException("マスターライセンス、または、研修にエントリー中の","活動状況を退職にすることは");
        }           
    }


    public TrnEntryDataDao getTrnEntryDataDao() {
        return trnEntryDataDao;
    }

    public void setTrnEntryDataDao(TrnEntryDataDao trnEntryDataDao) {
        this.trnEntryDataDao = trnEntryDataDao;
    }
}