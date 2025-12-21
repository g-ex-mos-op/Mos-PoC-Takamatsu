package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dao.CtlZennenDouyouInfoDao;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistInfoLogic;
/**
 * 条件画面処理
 * @author inazawa
 * 0007/022/27 
 */
public class ZenDougetuRegistInfoLogicImpl implements ZenDougetuRegistInfoLogic{
    /*LOGIC_ID**/
    public static final String LOGIC_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"L01";
    /*DAO[前年同月設定DAO]**/
    private CtlZennenDouyouInfoDao ctlZennenDouyouInfoDao; 
    /**
     * 検索処理
     * @param 前年同月設定DTO
     * @return 検索結果リスト
     */
    public List search(ZenDougetuRegistDto dto) {
        String taishoNendo = dto.getTaishoNendo()+"%";
        return getCtlZennenDouyouInfoDao().getZenDougetuInfo(dto.getCompanyCd(),taishoNendo);
    }
    /**
     * zennenDouyouInfoDaoを取得
     * @return zennenDouyouInfoDao
     */
    public CtlZennenDouyouInfoDao getCtlZennenDouyouInfoDao() {
        return ctlZennenDouyouInfoDao;
    }
    /**
     * zennenDouyouInfoDaoを設定
     * @param zennenDouyouInfoDao
     */
    public void setCtlZennenDouyouInfoDao(CtlZennenDouyouInfoDao zennenDouyouInfoDao) {
        ctlZennenDouyouInfoDao = zennenDouyouInfoDao;
    }

}
