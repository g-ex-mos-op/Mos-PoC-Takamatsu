/*
 * çÏê¨ì˙: 2006/11/30
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.logic.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetref.dao.TrnBudgetRegistDummyDao;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefResultDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.entity.TrnBudgetRegistDummy;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * CSVñæç◊É_ÉEÉìÉçÅ[ÉhÉçÉWÉbÉN
 * 
 * @author inazawa
 */
public class BudgetRefInfoMeisaiCsvLogicImpl implements CsvOutputLogic{
    /*FC_RCãÊï™ 1:FC2:íºâc*/
    private static final String FC = "1"; 
    private static final String RC = "2"; 
    /*Dao[ó\éZìoò^èÓïÒéÊìæ]*/
    private TrnBudgetRegistDummyDao trnBudgetRegistDummyDao;
    /** ÉçÉWÉbÉNIDíËã` */
    public static final String LOGIC_ID = "BBS023L05";
    
    
    public String getFileName(CsvOutputDto csvOutputDto) {
        String fileName = "YOSANMISE"+".csv";
        return fileName;
    }

    public List getOutputData(CsvOutputDto csvOutputDto) {
        BudgetRefResultDto budgetRefResultDto  = (BudgetRefResultDto)csvOutputDto;
        budgetRefResultDto.getBudgetRefCondDto();
        budgetRefResultDto.getBudgetRefYMDto();
        //èoóÕópÉäÉXÉg
        List outputList = new ArrayList();
        outputList = setHeader(budgetRefResultDto,outputList);
        //çÄñ⁄ÉZÉbÉg
        outputList.add(setKoumoku(budgetRefResultDto));
        //ñæç◊ÉZÉbÉg
        outputList = setMeisai(budgetRefResultDto,outputList);
        
        return outputList;
    }
    /**
     * ÉwÉbÉ_Å[çÏê¨
     * @return
     */
    private List setHeader(BudgetRefResultDto budgetRefResultDto,List outputList) {
        List header1 = new ArrayList();
        header1.add("âÔé–:");
        header1.add(budgetRefResultDto.getCompanyNm());
        outputList.add(header1);
        //ÉwÉbÉ_Å[ÇQçsñ⁄
        List header2 = new ArrayList();
        header2.add("îNìx:");
        header2.add(budgetRefResultDto.getNendo());
        outputList.add(header2);
        //ÉwÉbÉ_Å[ÇRçsñ⁄
        List header3 = new ArrayList();
        header3.add("ëŒè€ìXï‹:");
        header3.add("ëSìX");
        outputList.add(header3);
        //ÉwÉbÉ_Å[ÇSçsñ⁄(ãÛ)
        List space1 = new ArrayList();
        outputList.add(space1);
        return outputList;
    }

    /**
     * çÄñ⁄ÇÃÉZÉbÉg
     * @return
     */
    private List setKoumoku(BudgetRefResultDto budgetRefResultDto) {
        List koumoku = new ArrayList();
        koumoku.add("ìXï‹ÉRÅ[Éh");
        koumoku.add("ìXï‹ñºèÃ");
        koumoku.add("ÇeÇbÅ^ÇqÇb");
        koumoku.add("ÇeÇbÅ^ÇqÇbãÊï™");
        koumoku.add("éxïîÉRÅ[Éh");
        koumoku.add("éxïîñºèÃ");
        koumoku.add("éxïîéÊçûÉRÅ[Éh");
        koumoku.add("éxïîéÊçûñºèÃ");
        koumoku.add("í ä˙ó\éZçáåv");
        koumoku.add("ÇSåéó\éZ");
        koumoku.add("ÇTåéó\éZ");
        koumoku.add("ÇUåéó\éZ");
        koumoku.add("ÇVåéó\éZ");
        koumoku.add("ÇWåéó\éZ");
        koumoku.add("ÇXåéó\éZ");
        koumoku.add("ÇPÇOåéó\éZ");
        koumoku.add("ÇPÇPåéó\éZ");
        koumoku.add("ÇPÇQåéó\éZ");
        koumoku.add("ÇPåéó\éZ");
        koumoku.add("ÇQåéó\éZ");
        koumoku.add("ÇRåéó\éZ");        
        return koumoku;
    }
    /**
     * ñæç◊ÉäÉXÉg
     * @param budgetRefResultDto
     * @return
     */
    private List setMeisai(BudgetRefResultDto budgetRefResultDto,List outPutList) {
        List resultList = getTrnBudgetRegistDummyDao().getMeisaiDL(budgetRefResultDto.getBudgetRefCondDto().getCompanyCd(),budgetRefResultDto.getBudgetRefYMDto());
        for(int i=0;resultList.size()>i;i++){
            TrnBudgetRegistDummy trnBudgetRegistDummy = (TrnBudgetRegistDummy)resultList.get(i);
            outPutList.add(setAllMiseCsv(trnBudgetRegistDummy,true));
        }
        List resultTotalList = budgetRefResultDto.getResultTotalList();
        outPutList.add(setAllMiseCsv((TrnBudgetRegistDummy)resultTotalList.get(0),false));
        return outPutList;

    }
    /**
     * çáåvèÓïÒÉZÉbÉg
     * @param trnBudgetRegistDummy
     * @return
     */
    private List setAllMiseCsv(TrnBudgetRegistDummy trnBudgetRegistDummy,boolean isTotal) {
        List allMiseList = new ArrayList();
        //ìXÉRÅ[Éh
        if(isTotal){
            allMiseList.add(trnBudgetRegistDummy.getMiseCd());
        }else{
            allMiseList.add("ëççáåv");
        }
        //ìXñºèÃ
        if(trnBudgetRegistDummy.getMiseNameKj() == null){
            allMiseList.add("");
        }else{
            allMiseList.add(trnBudgetRegistDummy.getMiseNameKj());
        }
        //RC/RCãÊï™
        if(isTotal){
            if(trnBudgetRegistDummy.getFcRc() == null){
                allMiseList.add("");
            }else{
                allMiseList.add(trnBudgetRegistDummy.getFcRc());
            }
        }else{
            allMiseList.add("");
        }
        //FC/RC
        if(isTotal){
            if(trnBudgetRegistDummy.getFcRc().equals(FC)){
                allMiseList.add("FC");
            }else if(trnBudgetRegistDummy.getFcRc().equals(RC)){
                allMiseList.add("RC");
            }else{
                allMiseList.add("");
            }
        }else{
            allMiseList.add("");
        }
        //éxïîÉRÅ[Éh
        if(isTotal){
            allMiseList.add(trnBudgetRegistDummy.getSibuCd());
        }else{
            allMiseList.add("");
        }
        //éxïîñºèÃ
        if(trnBudgetRegistDummy.getSibuName() != null){
            allMiseList.add(trnBudgetRegistDummy.getSibuName());
        }else{
            allMiseList.add("");
        }
        //éxïîéÊçûÇ›ÉRÅ[Éh
        if(isTotal){
            allMiseList.add(trnBudgetRegistDummy.getAreaDai());
        }else{
            allMiseList.add("");
        }
        //éxïîéÊçûÇ›ñºèÃ
        if(trnBudgetRegistDummy.getAreaDaiName() != null){
            allMiseList.add(trnBudgetRegistDummy.getAreaDaiName());
        }else{
            allMiseList.add("");            
        }
        //çáåv
        allMiseList.add(setAll(trnBudgetRegistDummy));
        //åéï ó\éZ
        allMiseList.add(trnBudgetRegistDummy.getYosanAll4());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll5());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll6());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll7());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll8());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll9());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll10());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll11());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll12());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll1());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll2());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll3());
        return allMiseList;
   }
    /**
     * ëççáåv
     * @param trnBudgetRegistDummy
     * @return
     */
    private String setAll(TrnBudgetRegistDummy trnBudgetRegistDummy) {
        BigDecimal yosan = new BigDecimal(0);
        BigDecimal yosanAll4 = new BigDecimal(0);
        BigDecimal yosanAll5 = new BigDecimal(0);
        BigDecimal yosanAll6 = new BigDecimal(0);
        BigDecimal yosanAll7 = new BigDecimal(0);
        BigDecimal yosanAll8 = new BigDecimal(0);
        BigDecimal yosanAll9 = new BigDecimal(0);
        BigDecimal yosanAll10 = new BigDecimal(0);
        BigDecimal yosanAll11 = new BigDecimal(0);
        BigDecimal yosanAll12 = new BigDecimal(0);
        BigDecimal yosanAll1 = new BigDecimal(0);
        BigDecimal yosanAll2 = new BigDecimal(0);
        BigDecimal yosanAll3 = new BigDecimal(0);
        if(trnBudgetRegistDummy.getYosanAll4() != null){
            yosanAll4 = new BigDecimal(trnBudgetRegistDummy.getYosanAll4());
        }else{
            yosanAll4 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll4("0");
        }
        yosan = yosan.add(yosanAll4);
        if(trnBudgetRegistDummy.getYosanAll5() != null){
            yosanAll5 = new BigDecimal(trnBudgetRegistDummy.getYosanAll5());
        }else{
            yosanAll5 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll5("0");
        }
        yosan = yosan.add(yosanAll5);
        if(trnBudgetRegistDummy.getYosanAll6() != null){
            yosanAll6 = new BigDecimal(trnBudgetRegistDummy.getYosanAll6());
        }else{
            yosanAll6 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll6("0");
        }
        yosan = yosan.add(yosanAll6);
        if(trnBudgetRegistDummy.getYosanAll7() != null){
            yosanAll7 = new BigDecimal(trnBudgetRegistDummy.getYosanAll7());
        }else{
            yosanAll7 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll7("0");
        }
        yosan = yosan.add(yosanAll7);
        if(trnBudgetRegistDummy.getYosanAll8() != null){
            yosanAll8 = new BigDecimal(trnBudgetRegistDummy.getYosanAll8());
        }else{
            yosanAll8 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll8("0");
        }
        yosan = yosan.add(yosanAll8);
        if(trnBudgetRegistDummy.getYosanAll9() != null){
            yosanAll9 = new BigDecimal(trnBudgetRegistDummy.getYosanAll9());
        }else{
            yosanAll9 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll9("0");
        }
        yosan = yosan.add(yosanAll9);
        if(trnBudgetRegistDummy.getYosanAll10() != null){
            yosanAll10 = new BigDecimal(trnBudgetRegistDummy.getYosanAll10());
        }else{
            yosanAll10 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll10("0");
        }
        yosan = yosan.add(yosanAll10);
        if(trnBudgetRegistDummy.getYosanAll11() != null){
            yosanAll11 = new BigDecimal(trnBudgetRegistDummy.getYosanAll11());
        }else{
            yosanAll11 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll11("0");
        }
        yosan = yosan.add(yosanAll11);
        if(trnBudgetRegistDummy.getYosanAll12() != null){
            yosanAll12 = new BigDecimal(trnBudgetRegistDummy.getYosanAll12());
        }else{
            yosanAll12 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll12("0");
        }
        yosan = yosan.add(yosanAll12);
        if(trnBudgetRegistDummy.getYosanAll1() != null){
            yosanAll1 = new BigDecimal(trnBudgetRegistDummy.getYosanAll1());
        }else{
            yosanAll1 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll1("0");
        }
        yosan = yosan.add(yosanAll1);
        if(trnBudgetRegistDummy.getYosanAll2() != null){
            yosanAll2 = new BigDecimal(trnBudgetRegistDummy.getYosanAll2());
        }else{
            yosanAll2 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll2("0");
        }
        yosan = yosan.add(yosanAll2);
        if(trnBudgetRegistDummy.getYosanAll3() != null){
            yosanAll3 = new BigDecimal(trnBudgetRegistDummy.getYosanAll3());
        }else{
            yosanAll3 = new BigDecimal(0);
            trnBudgetRegistDummy.setYosanAll3("0");
        }
        yosan = yosan.add(yosanAll3);
        return String.valueOf(yosan);
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // é©ìÆê∂ê¨Ç≥ÇÍÇΩÉÅÉ\ÉbÉhÅEÉXÉ^Éu
        
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
