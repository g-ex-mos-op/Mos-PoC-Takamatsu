package jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistCommon;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.entity.CtlZennenDouyouInfo;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistEmptyDataLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 空データ取得処理ロジック　インターフェイス
 * @author inazawa
 * 2007/03/02
 */
public class ZenDougetuRegistEmptyDataLogicImpl implements ZenDougetuRegistEmptyDataLogic{
    /*LOGIC_ID**/
    public static final String LOGIC_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"L02";
    /**
     * 空データ取得
     * @param 前年同月設定DTO
     * @return 生成されたリスト
     */
    public List getEmptyData(ZenDougetuRegistDto dto) {
       List listZenDougetu = dto.getListZenDougetu();
       //選択された年月リスト生成
       List kijyunList = setKijyunList(dto);
       //データがあるかないか
       if(listZenDougetu != null && listZenDougetu.size()>0){
           //ある場合でも選択された年月の一日から末日まで登録されているか
           listZenDougetu = setEigyouDt(dto,kijyunList);
       }else{
           //データが無い場合
           listZenDougetu = setZenEigyouDt(dto,kijyunList);
       }
 
       
       return  listZenDougetu;
    }
    /**
     * 既存データチェック
     * @param 前年同月設定DTO
     * @return 生成されたリスト
     */
    private List setEigyouDt(ZenDougetuRegistDto dto,List kijyunList) {
        List zenDougetuList = dto.getListZenDougetu();
        if(zenDougetuList.size()!=kijyunList.size()){
            //日付が途中で抜けている場合
            zenDougetuList = setEmpty(dto,kijyunList);
        }else{
            //日付がそろっている場合
            zenDougetuList = setEmptyFlg(zenDougetuList);
        }
        
        
        return zenDougetuList;
    }
    /**
     * Entityにデータありのフラグを設定する
     * @param 前年同月設定DTO
     * @return 生成されたリスト
     */
    private List setEmptyFlg(List zenDougetuList) {
        for(int i=0; zenDougetuList.size()>i; i++){
            CtlZennenDouyouInfo entity = (CtlZennenDouyouInfo)zenDougetuList.get(i);
            entity.setEmptyData(ZenDougetuRegistConstants.EMPTY_NOT);
        }
        return zenDougetuList;
    }
    /**
     * 抜けている日付の設定を行う
     * @param 前年同月設定DTO
     * @param 基準リスト
     * @return 生成されたリスト
     */
    private List setEmpty(ZenDougetuRegistDto dto, List kijyunList) {
        List listZenDougetu = dto.getListZenDougetu();
        String matubi = DateManager.getLastDayOfMonth(dto.getTaishoNendo());
        CtlZennenDouyouInfo lastEntity    = (CtlZennenDouyouInfo)listZenDougetu.get(listZenDougetu.size()-1);
        //末日が抜けてる場合を設定
        if(!matubi.equals(lastEntity.getEigyoDt().substring(6,8))){
            CtlZennenDouyouInfo newEntity    = new CtlZennenDouyouInfo();
            newEntity.setEmptyData(ZenDougetuRegistConstants.EMPTY_DATA);
            newEntity.setEigyoDt(dto.getTaishoNendo()+matubi);
            newEntity.setUriageZen(new BigDecimal(0));
            listZenDougetu.add(listZenDougetu.size(),newEntity);
        }
        //末日以外の日が抜けてる場合の設定
        for (int i=0;listZenDougetu.size()>i; i++) {
            for(int j=0;kijyunList.size()>j;j++){
                CtlZennenDouyouInfo entity    = (CtlZennenDouyouInfo)listZenDougetu.get(j);
                CtlZennenDouyouInfo kijyunEntity    = (CtlZennenDouyouInfo)kijyunList.get(j);
                if(!entity.getEigyoDt().equals(kijyunEntity.getEigyoDt())){
                    kijyunEntity.setEmptyData(ZenDougetuRegistConstants.EMPTY_DATA);
                    kijyunEntity.setUriageZen(new BigDecimal(0));
                    listZenDougetu.add(j,kijyunEntity);
                    break;
                }
            }
            
        }
        //日にちがある場合、空データフラグをゼロにする
        for (int i=0;listZenDougetu.size()>i; i++) {
            CtlZennenDouyouInfo entity    = (CtlZennenDouyouInfo)listZenDougetu.get(i);
            if(ZenDougetuRegistCommon.isNull(entity.getEmptyData())){
                entity.setEmptyData(ZenDougetuRegistConstants.EMPTY_NOT);
            }
        }
        return listZenDougetu;
    }
    /**
     * 基準年月リスト作成
     * @param 前年同月設定DTO
     * @return 基準年月リスト
     */
    private List setKijyunList(ZenDougetuRegistDto dto) {
        int matubi = Integer.parseInt(DateManager.getLastDayOfMonth(dto.getTaishoNendo()));
        List listZenDougetu = new ArrayList();
        for(int i=1; matubi>i-1; i++){
            CtlZennenDouyouInfo entity = new CtlZennenDouyouInfo();
            if(10>i){
                entity.setEigyoDt(dto.getTaishoNendo()+"0"+i);
            }else{
                entity.setEigyoDt(dto.getTaishoNendo()+i);
            }
            listZenDougetu.add(entity);
        }
        return listZenDougetu;
    }
    /**
     * 新規はデフォルトで当年営業日に対象年月の末日までのリストを作成
     * @param 前年同月設定DTO
     * @param 基準年月リスト
     * @return 当年営業日リスト
     */
    private List setZenEigyouDt(ZenDougetuRegistDto dto,List kijyunList) {
        List listZenDougetu = new ArrayList();
        for(int i=0; kijyunList.size()>i; i++){
            listZenDougetu.add(setTouEigyoDate(kijyunList,i));
        }
        return listZenDougetu;
    }
    /**
     * 新規リストの一行生成処理
     * @param 基準年月リストの
     * @param 日にち
     * @return 前年同月設定Entity 
     */
    private CtlZennenDouyouInfo setTouEigyoDate(List kijyunList,int i){
        CtlZennenDouyouInfo entity = (CtlZennenDouyouInfo)kijyunList.get(i);
        entity.setZennenDt("");
        entity.setEigyoDt(entity.getEigyoDt());
        entity.setEmptyData(ZenDougetuRegistConstants.EMPTY_DATA);
        return entity;
    }
}
