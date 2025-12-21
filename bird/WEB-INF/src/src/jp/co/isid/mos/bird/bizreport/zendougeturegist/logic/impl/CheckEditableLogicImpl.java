package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.impl;
/**
 *  編集可否チェック処理
 * @author kusama
 * 2007/11/28
 */
import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dao.CtlYosanControlDateDao;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlYosanControlDate;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.CheckEditableLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

public class CheckEditableLogicImpl implements CheckEditableLogic{
    /*予算登録制御日付管理DAO*/
    private CtlYosanControlDateDao zendougetuRegistCtlYosanControlDateDao;
    
    /*LOGIC_ID**/
    public static final String LOGIC_ID = ZenDougetuRegistConstants.SUB_MENU_ID + "L07";
    /**
     * 編集可否チェック処理
     * @param 前年同月設定DTO
     */
    public boolean execute(ZenDougetuRegistDto dto) {
        String sTaishoKikanYM = dto.getTaishoNendo();
        //１．アプリ日付の前月を算出
        String sEditableYM = "";
        try {
            sEditableYM = DateManager.getPrevMonth(dto.getApplyDate().substring(0, 6), 1);
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", "", ex);
        }
        //２．パラメータ．対象期間が処理１の年月以降かチェック
        if (sTaishoKikanYM.compareTo(sEditableYM) < 0) {
            return false;
        }
        //３．【DAO】予算登録制御日付管理Dao．予算登録制御日付管理情報取得を実行
        List listCtlYosanDate = getZendougetuRegistCtlYosanControlDateDao()
                                    .getControlDate(dto.getCompanyCd(), 
                                                    DateManager.getCurrentYear(dto.getTaishoNendo()),
                                                    ZenDougetuRegistConstants.YOSAN_CONTROL_SHORI_KBN_KAMIKI_OKIKAE_TO);
        //４．処理３でレコードが取得できない場合、編集可能
        if (listCtlYosanDate == null || listCtlYosanDate.isEmpty()) {
            return true;
        }
        //５．処理３で取得した【Entity】予算登録制御日付管理．日付パラメータ ≧ 対象期間の場合
        CtlYosanControlDate  entityCtlYosanDate = (CtlYosanControlDate) listCtlYosanDate.get(0);
        if (entityCtlYosanDate.getShoriDt().compareTo(dto.getTaishoNendo()) >= 0) {
            return false;
        }
        return true;
    }
    
    public CtlYosanControlDateDao getZendougetuRegistCtlYosanControlDateDao() {
        return zendougetuRegistCtlYosanControlDateDao;
    }
    public void setZendougetuRegistCtlYosanControlDateDao(
            CtlYosanControlDateDao zendougetuRegistCtlYosanControlDateDao) {
        this.zendougetuRegistCtlYosanControlDateDao = zendougetuRegistCtlYosanControlDateDao;
    }

}
