package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.impl;
/**
 * “o˜^ˆ—
 * @author inazawa
 * 2007/02/27
 */
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dao.CtlYosanControlDateDao;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dao.CtlYosanHirituDao;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dao.CtlZennenDouyouInfoDao;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlYosanControlDate;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlZennenDouyouInfo;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

public class ZenDougetuRegistLogicImpl implements ZenDougetuRegistLogic{
    /*DAO[‘O”N“¯ŒŽÝ’èDAO]**/
    private CtlZennenDouyouInfoDao ctlZennenDouyouInfoDao; 
    /*—\ŽZ“o˜^§Œä“ú•tŠÇ—DAO*/
    private CtlYosanControlDateDao zendougetuRegistCtlYosanControlDateDao;
    /*—\ŽZˆÂ•ª”ä—¦Dao*/
    private CtlYosanHirituDao zendougetuRegistCtlYosanHirituDao;
    
    /*—\ŽZ“o˜^§Œä“ú•tŠÇ—TBL ƒXƒe[ƒ^ƒXƒtƒ‰ƒOFˆ—ˆË—ŠÏ‚Ý*/
    private static final String STATE_FLG_IRAIZUMI = "1";
    /*LOGIC_ID**/
    public static final String LOGIC_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"L06";
    /**
     * “o˜^E•ÏXˆ—
     * @param ‘O”N“¯ŒŽÝ’èDTO
     */
    public void regist(ZenDougetuRegistDto dto) {
        for(int i=0; dto.getListZenDougetu().size()>i; i++){
            CtlZennenDouyouInfo entity = (CtlZennenDouyouInfo)dto.getListZenDougetu().get(i);
            entity.setLastUser(dto.getUserId());
            if(entity.getEmptyData().equals(ZenDougetuRegistConstants.EMPTY_NOT)){
                //ƒf[ƒ^‚ª‚ ‚Á‚½ê‡‚Í•ÏX
                update(entity);
            }else if(entity.getEmptyData().equals(ZenDougetuRegistConstants.EMPTY_DATA)){
                entity.setCompanyCd(dto.getCompanyCd());
                //ƒf[ƒ^‚ª–³‚©‚Á‚½ê‡‚Í“o˜^
                insert(entity);
            }
        }
        //2007/11/19 add start —\ŽZ“o˜^§Œä“ú•tŠÇ—TBL“o˜^ˆ—
        registCtlYosanDate(dto);
    }
    
    /**
     * —\ŽZ“o˜^§Œä“ú•tŠÇ—TBL“o˜^ˆ—
     * @—\ŽZˆÂ•ª”ä—¦TBL‚ÉƒŒƒR[ƒh‚ª‚È‚¢”NŒŽ‚Ìê‡‚ÍA“o˜^ˆ—‚ðs‚í‚È‚¢
     */
    private void registCtlYosanDate(ZenDougetuRegistDto dto) {
        String nendo = DateManager.getCurrentYear(dto.getTaishoNendo());
        String month = dto.getTaishoNendo().substring(4, 6);
        int shoriKbn = Integer.parseInt(month);
        String shoriKbnString = "";
        if (shoriKbn >= 1 && shoriKbn <= 3) {
            //‚PŒŽ‚©‚ç‚RŒŽ‚Ìê‡‚ÍAˆ—‹æ•ª‚ð13`15‚É•ÏŠ·
            shoriKbn = shoriKbn + 12;
        }
        shoriKbnString = String.valueOf(shoriKbn);
        if (shoriKbnString.length() == 1) {
            //‘Oƒ[ƒ•t‰Á
            shoriKbnString = "0" + shoriKbnString;
        }
        
        //‘ÎÛ”NŒŽ‚Å—\ŽZˆÂ•ª”ä—¦TBL‚ðŒŸõ‚µAƒŒƒR[ƒh‚ªŽæ“¾‚Å‚«‚È‚¢ê‡‚Íˆ—‚ðI—¹‚·‚é
        if (getZendougetuRegistCtlYosanHirituDao().count(dto.getCompanyCd(), dto.getTaishoNendo()) == 0) {
            return;
        }
        
        //—\ŽZ“o˜^§Œä“ú•tŠÇ—TBLŒŸõ
        List listCtlYodanDate = getZendougetuRegistCtlYosanControlDateDao().getControlDate(dto.getCompanyCd(), nendo, shoriKbnString);
        CtlYosanControlDate entity;
        if (listCtlYodanDate == null || listCtlYodanDate.isEmpty()) {
            //“o˜^Ï‚ÝƒŒƒR[ƒh‚ª‚È‚¢ê‡‚ÍV‹Kì¬
            entity = new CtlYosanControlDate();
            entity.setCompanyCd(dto.getCompanyCd());
            entity.setNendo(nendo);
            entity.setShoriKbn(shoriKbnString);
            entity.setLastTmsp(DateManager.getCurrentTimestamp());
        }
        else {
            //“o˜^Ï‚ÝƒŒƒR[ƒh‚ª‚ ‚éê‡‚ÍA‚»‚ê‚ðŽg—p‚·‚é
            entity = (CtlYosanControlDate) listCtlYodanDate.get(0);
        }
        entity.setShoriDt(dto.getTaishoNendo());
        entity.setStateFlg(STATE_FLG_IRAIZUMI);
        entity.setLastPgm(ZenDougetuRegistConstants.SUB_MENU_ID);
        entity.setLastUser(dto.getUserId());
        
        if (listCtlYodanDate == null || listCtlYodanDate.isEmpty()) {
            //Insert
            getZendougetuRegistCtlYosanControlDateDao().insert(entity);
        }
        else {
            //Update
            getZendougetuRegistCtlYosanControlDateDao().update(entity);
        }
    }
    /**
     * •ÏXˆ—
     * @param ‘O”N“¯ŒŽÝ’èDTO
     */
    private void update(CtlZennenDouyouInfo entity) {
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        entity.setLastTmsp(currentTimestamp);
        entity.setLastPgm(LOGIC_ID.substring(0, 6));
        entity.setUriageZen(entity.getUriageZen());
        getCtlZennenDouyouInfoDao().updateZenDougetuInfo(entity);
    }
    /**
     * “o˜^ˆ—
     * @param ‘O”N“¯ŒŽÝ’èDTO
     */
    private void insert(CtlZennenDouyouInfo entity) {
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        entity.setLastTmsp(currentTimestamp);
        entity.setLastPgm(LOGIC_ID.substring(0, 6));
        entity.setUriageZen(new BigDecimal(0));
        getCtlZennenDouyouInfoDao().insertZenDougetuInfo(entity);
    }
    /**
     * zennenDouyouInfoDao‚ðŽæ“¾
     * @return zennenDouyouInfoDao
     */
    public CtlZennenDouyouInfoDao getCtlZennenDouyouInfoDao() {
        return ctlZennenDouyouInfoDao;
    }
    /**
     * zennenDouyouInfoDao‚ðÝ’è
     * @param zennenDouyouInfoDao
     */
    public void setCtlZennenDouyouInfoDao(CtlZennenDouyouInfoDao zennenDouyouInfoDao) {
        ctlZennenDouyouInfoDao = zennenDouyouInfoDao;
    }

    public CtlYosanControlDateDao getZendougetuRegistCtlYosanControlDateDao() {
        return zendougetuRegistCtlYosanControlDateDao;
    }

    public void setZendougetuRegistCtlYosanControlDateDao(
            CtlYosanControlDateDao ctlYosanControlDateDao) {
        this.zendougetuRegistCtlYosanControlDateDao = ctlYosanControlDateDao;
    }

    public CtlYosanHirituDao getZendougetuRegistCtlYosanHirituDao() {
        return zendougetuRegistCtlYosanHirituDao;
    }

    public void setZendougetuRegistCtlYosanHirituDao(
            CtlYosanHirituDao zendougetuRegistCtlYosanHirituDao) {
        this.zendougetuRegistCtlYosanHirituDao = zendougetuRegistCtlYosanHirituDao;
    }

}
