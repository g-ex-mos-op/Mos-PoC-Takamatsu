package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.TrnEntryStatusDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckStaffListLogic;

/**
 * 能力チェック対象者一覧取得ロジック 
 * @author xnkusama
 */
public class GetAbilityCheckStaffListLogicImpl implements GetAbilityCheckStaffListLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L02";
    
    TrnEntryStatusDao mlrrTrnEntryStatusDao;
    
    /**
     * 対象者一覧を取得する
     * @param MlResultRegistDto dto
     * @return List
     * @exception ApplicationException
     */
    public List execute(MlResultRegistDto dto) throws ApplicationException {
        List listEntryStaff;
        if (dto.isCondOnlyEntry()) {
            // エントリー者のみ表示の場合
            listEntryStaff = getMlrrTrnEntryStatusDao()
                                    .getEntryStaffList(dto.getEntryCd(),
                                                       dto.getEntryYear(),
                                                       dto.getEntryKai(),
                                                       dto.getCondCompanyCd(),
                                                       dto.getCondSibuCd(),
                                                       dto.getCondOnerCd(),
                                                       dto.getCondMiseCd(),
                                                       dto.getCondType());
        }
        else {
            // エントリーしていないスタッフも表示
            listEntryStaff = getMlrrTrnEntryStatusDao()
                                    .getAllStaffList(dto.getEntryCd(),
                                                     dto.getEntryYear(),
                                                     dto.getEntryKai(),
                                                     dto.getCondCompanyCd(),
                                                     dto.getCondSibuCd(),
                                                     dto.getCondOnerCd(),
                                                     dto.getCondMiseCd(),
                                                     dto.getCondType());
            // 取得したデータにエントリーコード、年、回の情報をセット
            if (listEntryStaff != null) {
                for (Iterator ite = listEntryStaff.iterator(); ite.hasNext();) {
                    TrnEntryStatus trnEntryStatus = (TrnEntryStatus) ite.next();
                    trnEntryStatus.setEntryCd(dto.getEntryCd());
                    trnEntryStatus.setEntryYear(dto.getEntryYear());
                    trnEntryStatus.setEntryKai(dto.getEntryKai());
                }
            }
        }
        return listEntryStaff;
    }

    public TrnEntryStatusDao getMlrrTrnEntryStatusDao() {
        return mlrrTrnEntryStatusDao;
    }

    public void setMlrrTrnEntryStatusDao(TrnEntryStatusDao mlrrTrnEntryStatusDao) {
        this.mlrrTrnEntryStatusDao = mlrrTrnEntryStatusDao;
    }

}
