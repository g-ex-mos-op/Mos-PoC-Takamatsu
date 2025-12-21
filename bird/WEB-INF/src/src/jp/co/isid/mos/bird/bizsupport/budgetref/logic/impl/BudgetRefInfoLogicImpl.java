/*
 * çÏê¨ì˙: 2006/11/30
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.logic.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.budgetref.dao.TrnBudgetRegistDummyDao;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefCondDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefYMDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.entity.TrnBudgetRegistDummy;
import jp.co.isid.mos.bird.bizsupport.budgetref.logic.BudgetRefInfoLogic;

/**
 * ó\éZìoò^éÊìæèàóùÉçÉWÉbÉN
 * 
 * @author inazawa
 */
public class BudgetRefInfoLogicImpl implements BudgetRefInfoLogic{
    /*Dao[ó\éZìoò^èÓïÒéÊìæ]*/
    private TrnBudgetRegistDummyDao trnBudgetRegistDummyDao;
    /*Dto[èåèâÊñ ópDTO]*/
    /*MAPópÉZÉbÉVÉáÉìÉLÅ[*/
    private static final String KARI_MISE= "KARI_MISE";
    private static final String JITU_MISE = "JITU_MISE";
    private static final String ALL_MISE = "ALL_MISE";
    private static final String KARIMISE_TOTAL = "KARIMISE_TOTAL";
    private static final String JITUMISE_TOTAL = "JITUMISE_TOTAL";
    private static final String MISE_TOTAL = "MISE_TOTAL";
    /** ÉçÉWÉbÉNIDíËã` */
    public static final String LOGIC_ID = "BBS023L03";
    
    
    /**
     * 
     */
    public Map execute(BudgetRefCondDto condDto,BudgetRefYMDto ymdDto) {
        Map listMap = new HashMap();
        
        listMap.put(JITU_MISE,(ArrayList) getTrnBudgetRegistDummyDao().getYosanJituStore(condDto.getCompanyCd(),ymdDto));
        listMap.put(KARI_MISE,(ArrayList) getTrnBudgetRegistDummyDao().getYosanKariStore(condDto.getCompanyCd(),ymdDto));
        listMap.put(ALL_MISE,(ArrayList) getTrnBudgetRegistDummyDao().getYosanStore(condDto.getCompanyCd(),ymdDto));
        
        List miseTotalList = (List)listMap.get(ALL_MISE);
        if(miseTotalList == null || miseTotalList.size() == 0){
            return null;
        }else{
            List listTukiBetuJituMise = getTukiBetuJituMiseTotal(condDto,ymdDto);
            List listTukiBetuKariMise = getTukiBetuKariMiseTotal(condDto,ymdDto);
            List listTukiBetuMise =     getTukiBetuMiseTotal(condDto,ymdDto);
            listMap.put(MISE_TOTAL,listTukiBetuMise);
            listMap.put(KARIMISE_TOTAL,listTukiBetuKariMise);
            listMap.put(JITUMISE_TOTAL,listTukiBetuJituMise);
            List jituMiseTotalList = (List)listMap.get(JITUMISE_TOTAL);
            List kariMiseTotalList = (List)listMap.get(KARIMISE_TOTAL);
            if(jituMiseTotalList == null || jituMiseTotalList.size() ==0){
                jituMiseTotalList.add(new TrnBudgetRegistDummy());
                listMap.remove(JITUMISE_TOTAL);
                listMap.put(JITUMISE_TOTAL,jituMiseTotalList);
            }
            if(kariMiseTotalList == null || kariMiseTotalList.size() ==0){
                kariMiseTotalList.add(new TrnBudgetRegistDummy());
                listMap.remove(KARIMISE_TOTAL);
                listMap.put(KARIMISE_TOTAL,kariMiseTotalList);
            }
        }
        return listMap;
    }
    /**
     * åéï çáåv
     * @param condDto
     * @param ymdDto
     * @return
     */
    private List getTukiBetuMiseTotal(BudgetRefCondDto condDto, BudgetRefYMDto ymdDto) {
        List listTukiBetuMise = new ArrayList();
        
        TrnBudgetRegistDummy trnBudgetRegistDummy = new TrnBudgetRegistDummy();
        TrnBudgetRegistDummy tukiBetuAllMiseTotal  = new TrnBudgetRegistDummy();
        for(int i=0;12>i;i++){
            trnBudgetRegistDummy = getTrnBudgetRegistDummyDao().getTukiBetuAllMiseTotal(condDto.getCompanyCd(),ymdDto,i);
            if(i == 0){
                if(trnBudgetRegistDummy.getYosanAll4() != null){
                    tukiBetuAllMiseTotal.setYosanAll4(trnBudgetRegistDummy.getYosanAll4());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll4("0");
                }
            }else if(i == 1){
                if(trnBudgetRegistDummy.getYosanAll5() != null){
                    tukiBetuAllMiseTotal.setYosanAll5(trnBudgetRegistDummy.getYosanAll5());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll5("0");
                }
            }else if(i == 2){
                if(trnBudgetRegistDummy.getYosanAll6() != null){
                    tukiBetuAllMiseTotal.setYosanAll6(trnBudgetRegistDummy.getYosanAll6());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll6("0");
                }
            }else if(i == 3){
                if(trnBudgetRegistDummy.getYosanAll7() != null){
                    tukiBetuAllMiseTotal.setYosanAll7(trnBudgetRegistDummy.getYosanAll7());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll7("0");
                }
            }else if(i == 4){
                if(trnBudgetRegistDummy.getYosanAll8() != null){
                    tukiBetuAllMiseTotal.setYosanAll8(trnBudgetRegistDummy.getYosanAll8());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll8("0");
                }
            }else if(i == 5){
                if(trnBudgetRegistDummy.getYosanAll9() != null){
                    tukiBetuAllMiseTotal.setYosanAll9(trnBudgetRegistDummy.getYosanAll9());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll9("0");
                }
            }else if(i == 6){
                if(trnBudgetRegistDummy.getYosanAll10() != null){
                    tukiBetuAllMiseTotal.setYosanAll10(trnBudgetRegistDummy.getYosanAll10());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll10("0");
                }
            }else if(i == 7){
                if(trnBudgetRegistDummy.getYosanAll11() != null){
                    tukiBetuAllMiseTotal.setYosanAll11(trnBudgetRegistDummy.getYosanAll11());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll11("0");
                }
            }else if(i == 8){
                if(trnBudgetRegistDummy.getYosanAll12() != null){
                    tukiBetuAllMiseTotal.setYosanAll12(trnBudgetRegistDummy.getYosanAll12());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll12("0");
                }
            }else if(i == 9){
                if(trnBudgetRegistDummy.getYosanAll1() != null){
                    tukiBetuAllMiseTotal.setYosanAll1(trnBudgetRegistDummy.getYosanAll1());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll1("0");
                }
            }else if(i == 10){
                if(trnBudgetRegistDummy.getYosanAll2() != null){
                    tukiBetuAllMiseTotal.setYosanAll2(trnBudgetRegistDummy.getYosanAll2());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll2("0");
                }
            }else if(i == 11){
                if(trnBudgetRegistDummy.getYosanAll3() != null){
                    tukiBetuAllMiseTotal.setYosanAll3(trnBudgetRegistDummy.getYosanAll3());
                }else{
                    tukiBetuAllMiseTotal.setYosanAll3("0");
                }
            }
        }
        listTukiBetuMise.add(tukiBetuAllMiseTotal);
        return listTukiBetuMise;
    }
    /**
     * åéï âºìXî‘çáåvéÊìæ
     * @param condDto
     * @param ymdDto
     * @return
     */
    private List getTukiBetuKariMiseTotal(BudgetRefCondDto condDto, BudgetRefYMDto ymdDto) {
        List listTukiBetuKariMise = new ArrayList();
        
        TrnBudgetRegistDummy trnBudgetRegistDummy = new TrnBudgetRegistDummy();
        TrnBudgetRegistDummy tukiBetuKariMiseTotal  = new TrnBudgetRegistDummy();
        for(int i=0;12>i;i++){
            trnBudgetRegistDummy = getTrnBudgetRegistDummyDao().getTukiBetuKariMiseTotal(condDto.getCompanyCd(),ymdDto,i);
            if(i == 0){
                if(trnBudgetRegistDummy.getYosanKarimise4() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise4(trnBudgetRegistDummy.getYosanKarimise4());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise4("0");
                }
            }else if(i == 1){
                if(trnBudgetRegistDummy.getYosanKarimise5() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise5(trnBudgetRegistDummy.getYosanKarimise5());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise5("0");
                }
            }else if(i == 2){
                if(trnBudgetRegistDummy.getYosanKarimise6() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise6(trnBudgetRegistDummy.getYosanKarimise6());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise6("0");
                }
            }else if(i == 3){
                if(trnBudgetRegistDummy.getYosanKarimise7() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise7(trnBudgetRegistDummy.getYosanKarimise7());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise7("0");
                }
            }else if(i == 4){
                if(trnBudgetRegistDummy.getYosanKarimise8() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise8(trnBudgetRegistDummy.getYosanKarimise8());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise8("0");
                }
            }else if(i == 5){
                if(trnBudgetRegistDummy.getYosanKarimise9() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise9(trnBudgetRegistDummy.getYosanKarimise9());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise9("0");
                }
            }else if(i == 6){
                if(trnBudgetRegistDummy.getYosanKarimise10() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise10(trnBudgetRegistDummy.getYosanKarimise10());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise10("0");
                }
            }else if(i == 7){
                if(trnBudgetRegistDummy.getYosanKarimise11() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise11(trnBudgetRegistDummy.getYosanKarimise11());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise11("0");
                }
            }else if(i == 8){
                if(trnBudgetRegistDummy.getYosanKarimise12() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise12(trnBudgetRegistDummy.getYosanKarimise12());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise12("0");
                }
            }else if(i == 9){
                if(trnBudgetRegistDummy.getYosanKarimise1() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise1(trnBudgetRegistDummy.getYosanKarimise1());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise1("0");
                }
            }else if(i == 10){
                if(trnBudgetRegistDummy.getYosanKarimise2() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise2(trnBudgetRegistDummy.getYosanKarimise2());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise2("0");
                }
            }else if(i == 11){
                if(trnBudgetRegistDummy.getYosanKarimise3() != null){
                    tukiBetuKariMiseTotal.setYosanKarimise3(trnBudgetRegistDummy.getYosanKarimise3());
                }else{
                    tukiBetuKariMiseTotal.setYosanKarimise3("0");
                }
            }
        }
        listTukiBetuKariMise.add(tukiBetuKariMiseTotal);
        return listTukiBetuKariMise;
    }
    
    /**
     * åéï é¿ìXî‘çáåvéÊìæ
     * @param condDto
     * @param ymdDto
     * @return
     */
    private List getTukiBetuJituMiseTotal(BudgetRefCondDto condDto,BudgetRefYMDto ymdDto) {
        List listTukiBetuJituMise = new ArrayList();
        
        TrnBudgetRegistDummy trnBudgetRegistDummy = new TrnBudgetRegistDummy();
        TrnBudgetRegistDummy tukiBetuJituMiseTotal = new TrnBudgetRegistDummy();
        for(int i=0;12>i;i++){
            trnBudgetRegistDummy = getTrnBudgetRegistDummyDao().getTukiBetuJituMiseTotal(condDto.getCompanyCd(),ymdDto,i);
            if(i == 0){
                if(trnBudgetRegistDummy.getYosanJitumise4() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise4(trnBudgetRegistDummy.getYosanJitumise4());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise4("0");
                }
            }else if(i == 1){
                if(trnBudgetRegistDummy.getYosanJitumise5() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise5(trnBudgetRegistDummy.getYosanJitumise5());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise5("0");
                }
            }else if(i == 2){
                if(trnBudgetRegistDummy.getYosanJitumise6() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise6(trnBudgetRegistDummy.getYosanJitumise6());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise6("0");
                }
            }else if(i == 3){
                if(trnBudgetRegistDummy.getYosanJitumise7() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise7(trnBudgetRegistDummy.getYosanJitumise7());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise7("0");
                }
            }else if(i == 4){
                if(trnBudgetRegistDummy.getYosanJitumise8() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise8(trnBudgetRegistDummy.getYosanJitumise8());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise8("0");
                }
            }else if(i == 5){
                if(trnBudgetRegistDummy.getYosanJitumise9() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise9(trnBudgetRegistDummy.getYosanJitumise9());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise9("0");
                }
            }else if(i == 6){
                if(trnBudgetRegistDummy.getYosanJitumise10() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise10(trnBudgetRegistDummy.getYosanJitumise10());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise10("0");
                }
            }else if(i == 7){
                if(trnBudgetRegistDummy.getYosanJitumise11() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise11(trnBudgetRegistDummy.getYosanJitumise11());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise11("0");
                }
            }else if(i == 8){
                if(trnBudgetRegistDummy.getYosanJitumise12() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise12(trnBudgetRegistDummy.getYosanJitumise12());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise12("0");
                }
            }else if(i == 9){
                if(trnBudgetRegistDummy.getYosanJitumise1() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise1(trnBudgetRegistDummy.getYosanJitumise1());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise1("0");
                }
            }else if(i == 10){
                if(trnBudgetRegistDummy.getYosanJitumise2() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise2(trnBudgetRegistDummy.getYosanJitumise2());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise2("0");
                }
            }else if(i == 11){
                if(trnBudgetRegistDummy.getYosanJitumise3() != null){
                    tukiBetuJituMiseTotal.setYosanJitumise3(trnBudgetRegistDummy.getYosanJitumise3());
                }else{
                    tukiBetuJituMiseTotal.setYosanJitumise3("0");
                }
            }
        }
        listTukiBetuJituMise.add(tukiBetuJituMiseTotal);

        return listTukiBetuJituMise;
    }


    /**
     * trnBudgetRegistDummyDaoÇéÊìæ
     * @return trnBudgetRegistDummyDao
     */
    public TrnBudgetRegistDummyDao getTrnBudgetRegistDummyDao() {
        return trnBudgetRegistDummyDao;
    }

    /**
     * trnBudgetRegistDummyDaoÇê›íË
     * @param trnBudgetRegistDummyDao
     */
    public void setTrnBudgetRegistDummyDao(
            TrnBudgetRegistDummyDao trnBudgetRegistDummyDao) {
        this.trnBudgetRegistDummyDao = trnBudgetRegistDummyDao;
    }






    
}
