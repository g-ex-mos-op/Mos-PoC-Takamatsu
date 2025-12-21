package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.impl;
/**
 * 編集内容エラーチェック
 * @author inazawa
 * 2007/02/07
 */

import java.util.Iterator;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistCommon;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlZennenDouyouInfo;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistCheckLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

public class ZenDougetuRegistCheckLogicImpl implements ZenDougetuRegistCheckLogic{
    /*LOGIC_ID**/
    public static final String LOGIC_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"L04";
    /**
     * エラーチェック
     * @param 前年同月設定DTO
     */
    public void validate(ZenDougetuRegistDto dto) {
        int iCnt = 0;
        //String zenNendo = "";
        String sInputPossibleFrom = "";
        String sInputPossibleTo   = "";
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        try {
            //zenNendo = DateManager.getPrevMonth(dto.getTaishoNendo(),12);
            //入力可能年月From （＝入力対象年月の13ヶ月前）
            sInputPossibleFrom  = DateManager.getPrevMonth(dto.getTaishoNendo(), 13);
            //入力可能年月To   （＝入力対象年月の11ヶ月前）
            sInputPossibleTo    = DateManager.getPrevMonth(dto.getTaishoNendo(), 11);
            
        } catch (Exception e1) {
            throw new FtlSystemException("前年同月設定登録エラー処理", "", e1);
        }
        for (Iterator ite = dto.getListZenDougetu().iterator(); ite.hasNext();) {
            CtlZennenDouyouInfo entity = (CtlZennenDouyouInfo) ite.next();
            String zenEigyouDt = entity.getZennenDt();
            DateVerifier formmter = new DateVerifier();
            //必須チェックエラー
            if(ZenDougetuRegistCommon.isNull(zenEigyouDt)){
                throw new NotNullException("前年営業日","zennenDt",iCnt);
            }else{
                //営業日フォーマットエラー
                if(!formmter.validate(zenEigyouDt,DateVerifier.DATE_TYPE_YMD)){
                    throw new InvalidInputException("前年営業日","zennenDt",iCnt);
                }
//              2007/11/19 update start 前後1ヶ月も入力可能とする
//                //当年月以外の月入力エラー
//                if(!zenEigyouDt.substring(0,6).equals(zenNendo)){
//                    throw new GenericMessageException("前年営業日には"+zenNendo.substring(0,4)+"/"+ zenNendo.substring(4,6)+"の月内の日付を設定してください。","zennenDt",iCnt);
//                }
                if (zenEigyouDt.substring(0,6).compareTo(sInputPossibleFrom) < 0
                        || zenEigyouDt.substring(0,6).compareTo(sInputPossibleTo) > 0) 
                {
                    throw new GenericMessageException(
                                "前年営業日には" 
                                + dateFormatter.format(sInputPossibleFrom, true) 
                                + "～" 
                                + dateFormatter.format(sInputPossibleTo, true)
                                + "の日付を設定してください。", "zennenDt", iCnt);
                }
//              2007/11/19 update end
//              2007/11/16 delete start 重複登録を可能に変更                    
//                //重複エラー
//                for (int j = iCnt + 1; j < dto.getListZenDougetu().size(); j++) {
//                    CtlZennenDouyouInfo zenEntity = (CtlZennenDouyouInfo) dto.getListZenDougetu().get(j);
//                    if (!ZenDougetuRegistCommon.isNull(zenEntity.getZennenDt())&&
//                         zenEntity.getZennenDt().equals(entity.getZennenDt())&&
//                         !zenEntity.getEigyoDt().substring(4,8).equals(ZenDougetuRegistConstants.LEAP_DAY)){
//                        if(DateManager.isLeapYear(Integer.parseInt(dto.getTaishoNendo().substring(0,4)))&&
//                                dto.getTaishoNendo().substring(4,6).equals(ZenDougetuRegistConstants.LEAP_DAY.substring(0,2))){
//                            throw new GenericMessageException("2/29の前年営業日以外重複設定はできません。","zennenDt",j);
//                        }else{
//                            throw new GenericMessageException("前年営業日の重複設定は出来ません。","zennenDt",j);
//                        }
//                    }
//                }
//              2007/11/16 delete end
            }
            iCnt++;
        }
    }
}
