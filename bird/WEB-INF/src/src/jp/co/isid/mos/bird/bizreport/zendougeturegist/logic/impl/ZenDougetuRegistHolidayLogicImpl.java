package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistCommon;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlZennenDouyouInfo;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistHolidayLogic;
import jp.co.isid.mos.bird.common.entity.CtlHolidayInfo;
import jp.co.isid.mos.bird.common.logic.GetHolidayLogic;

/**
 * 祝祭日取得処理ロジック
 * @author inazawa
 * 2007/03/02
 */
public class ZenDougetuRegistHolidayLogicImpl implements ZenDougetuRegistHolidayLogic{
    
    /*LOGIC[共通：祝日取得ロジック]**/
    private  GetHolidayLogic zenDougetuRegistGetHolidayLogic;
    /*LOGIC_ID**/
    public static final String LOGIC_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"L03";

    /**
     * 祝祭日取得処理
     * @param 前年同月設定DTO
     * @return 生成されたリスト
     */
    public List getHolidayInfo(ZenDougetuRegistDto dto) {
            List listZenDougetu = dto.getListZenDougetu();
            int iCnt = 0;
            for (Iterator ite = listZenDougetu.iterator(); ite.hasNext();) {
                CtlZennenDouyouInfo entity    = (CtlZennenDouyouInfo) ite.next();
                //当年日付
                List holidayList = getZenDougetuRegistGetHolidayLogic().getHoliday(entity.getEigyoDt());
                if(ZenDougetuRegistCommon.isNull(entity.getEigyoDt())){
                    entity.setEigyoDt("");
                }
                if(holidayList != null && holidayList.size()>0){
                    //祝日だった場合
                    CtlHolidayInfo uIHolidayInfo = (CtlHolidayInfo)holidayList.get(0);
                    entity.setShukuJitu(true);
                    entity.setShukuNm(uIHolidayInfo.getShukuName());
                }else{
                    entity.setShukuJitu(false);
                }
                //前年日付
                List zenHolidayList = getZenDougetuRegistGetHolidayLogic().getHoliday(entity.getZennenDt());
                if(ZenDougetuRegistCommon.isNull(entity.getZennenDt())){
                    entity.setZennenDt("");
                }
                if(zenHolidayList != null && zenHolidayList.size()>0){
                    //祝日だった場合
                    CtlHolidayInfo uIHolidayInfo = (CtlHolidayInfo)zenHolidayList.get(0);
                    entity.setZenShukuNm(uIHolidayInfo.getShukuName());
                }else{
                    entity.setZenShukuNm("");
                }
                
                listZenDougetu.set(iCnt,entity);
                iCnt++;
            }
             
            return  listZenDougetu;
    
    }
    /**
     * zenDougetuRegistGetHolidayLogicを取得
     * @return zenDougetuRegistGetHolidayLogic
     */
    public GetHolidayLogic getZenDougetuRegistGetHolidayLogic() {
        return zenDougetuRegistGetHolidayLogic;
    }
    /**
     * zenDougetuRegistGetHolidayLogicを設定
     * @param zenDougetuRegistGetHolidayLogic
     */
    public void setZenDougetuRegistGetHolidayLogic(
            GetHolidayLogic zenDougetuRegistGetHolidayLogic) {
        this.zenDougetuRegistGetHolidayLogic = zenDougetuRegistGetHolidayLogic;
    }
}

