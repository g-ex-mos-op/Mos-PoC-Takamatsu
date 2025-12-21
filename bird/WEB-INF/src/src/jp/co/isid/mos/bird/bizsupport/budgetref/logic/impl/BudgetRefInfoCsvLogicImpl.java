/*
 * ì¬“ú: 2006/11/30
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.logic.impl;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefResultDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.entity.TrnBudgetRegistDummy;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;


/**
 * CSVƒ_ƒEƒ“ƒ[ƒhƒƒWƒbƒN
 * 
 * @author inazawa
 */
public class BudgetRefInfoCsvLogicImpl implements CsvOutputLogic{

    /** ƒƒWƒbƒNID’è‹` */
    public static final String LOGIC_ID = "BBS023L04";
    
    
    /**
     * ƒtƒ@ƒCƒ‹–¼æ“¾
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
//        BudgetRefResultDto budgetRefResultDto  = (BudgetRefResultDto)csvOutputDto;
        String fileName = "YOSANSIBU"+".csv";
        
        return fileName;
    }

    public List getOutputData(CsvOutputDto csvOutputDto) {
        BudgetRefResultDto budgetRefResultDto  = (BudgetRefResultDto)csvOutputDto;
        //o—Í—pƒŠƒXƒg
        List outPutList = new ArrayList();
        outPutList = setHeader(budgetRefResultDto,outPutList);
        //€–ÚƒZƒbƒg
        outPutList.add(setKoumoku(budgetRefResultDto));
        //–¾×ƒZƒbƒg
        outPutList = setMeisai(budgetRefResultDto,outPutList);
        
        return outPutList;
    }
    /**
     * –¾×ƒŠƒXƒg
     * @param budgetRefResultDto
     * @return
     */
    private List setMeisai(BudgetRefResultDto budgetRefResultDto,List outPutList) {
        List resultList = budgetRefResultDto.getResultList();
        for(int i=0;resultList.size()>i;i++){
            TrnBudgetRegistDummy trnBudgetRegistDummy = (TrnBudgetRegistDummy)resultList.get(i);
            outPutList.add(setJituMiseCsv(trnBudgetRegistDummy));
            outPutList.add(setKariMiseCsv(trnBudgetRegistDummy));
            outPutList.add(setAllMiseCsv(trnBudgetRegistDummy));
        }
        return outPutList;
    }
    /**
     * ‡Œvî•ñƒZƒbƒg
     * @param trnBudgetRegistDummy
     * @return
     */
    private List setAllMiseCsv(TrnBudgetRegistDummy trnBudgetRegistDummy) {
        List allMiseList = new ArrayList();
        allMiseList.add(trnBudgetRegistDummy.getSibuCd());
        if(trnBudgetRegistDummy.getSibuName() != null){
            allMiseList.add(trnBudgetRegistDummy.getSibuName());
        }else{
            allMiseList.add("");
        }
        allMiseList.add(trnBudgetRegistDummy.getMiseKbn());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll4());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll5());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll6());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll7());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll8());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll9());
        allMiseList.add(trnBudgetRegistDummy.getYosanFirst());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll10());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll11());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll12());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll1());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll2());
        allMiseList.add(trnBudgetRegistDummy.getYosanAll3());
        allMiseList.add(trnBudgetRegistDummy.getYosanSecond());
        return allMiseList;
   }

    /**
     * ‰¼“X”ÔƒŠƒXƒg
     * @param trnBudgetRegistDummy
     * @return
     */
    private List setKariMiseCsv(TrnBudgetRegistDummy trnBudgetRegistDummy) {
        List kariMiseList = new ArrayList();
        kariMiseList.add(trnBudgetRegistDummy.getSibuCd());
        if(trnBudgetRegistDummy.getSibuName() != null){
            kariMiseList.add(trnBudgetRegistDummy.getSibuName());
        }else{
            kariMiseList.add("");
        }
        kariMiseList.add(trnBudgetRegistDummy.getKaritenbanKbn());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise4());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise5());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise6());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise7());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise8());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise9());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimiseFirst());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise10());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise11());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise12());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise1());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise2());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimise3());
        kariMiseList.add(trnBudgetRegistDummy.getYosanKarimiseSecond());
        return kariMiseList;
    }

    /**
     * À“X”Ôî•ñ‚ğƒZƒbƒg
     * @param trnBudgetRegistDummy
     * @return
     */
    private List setJituMiseCsv(TrnBudgetRegistDummy trnBudgetRegistDummy) {
        List jituMiseList = new ArrayList();
        jituMiseList.add(trnBudgetRegistDummy.getSibuCd());
        if(trnBudgetRegistDummy.getSibuName() != null){
            jituMiseList.add(trnBudgetRegistDummy.getSibuName());
        }else{
            jituMiseList.add("");
        }
        jituMiseList.add(trnBudgetRegistDummy.getJitutenbanKbn());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise4());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise5());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise6());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise7());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise8());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise9());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumiseFirst());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise10());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise11());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise12());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise1());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise2());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumise3());
        jituMiseList.add(trnBudgetRegistDummy.getYosanJitumiseSecond());
        return jituMiseList;
    }

    /**
     * ƒwƒbƒ_[ì¬
     * @return
     */
    private List setHeader(BudgetRefResultDto budgetRefResultDto,List  outputList) {
        List header1 = new ArrayList();
        header1.add("‰ïĞ:");
        header1.add(budgetRefResultDto.getCompanyNm());
        outputList.add(header1);
        //ƒwƒbƒ_[‚Qs–Ú
        List header2 = new ArrayList();
        header2.add("‘ÎÛ”N“x:");
        header2.add(budgetRefResultDto.getNendo());
        outputList.add(header2);
        //ƒwƒbƒ_[‚Rs–Ú(‹ó)
        List space1 = new ArrayList();
        outputList.add(space1);
        return outputList;
    }

    /**
     * €–Ú‚ÌƒZƒbƒg
     * @return
     */
    private List setKoumoku(BudgetRefResultDto budgetRefResultDto) {
        List koumoku = new ArrayList();
        koumoku.add("x•”ƒR[ƒh");
        koumoku.add("x•”–¼Ì");
        koumoku.add("");
        koumoku.add("’ÊŠú—\Z‡Œv");
        koumoku.add("‚SŒ—\Z");
        koumoku.add("‚TŒ—\Z");
        koumoku.add("‚UŒ—\Z");
        koumoku.add("‚VŒ—\Z");
        koumoku.add("‚WŒ—\Z");
        koumoku.add("‚XŒ—\Z");
        koumoku.add("ã”¼Šú‡Œv");
        koumoku.add("‚P‚OŒ—\Z");
        koumoku.add("‚P‚PŒ—\Z");
        koumoku.add("‚P‚QŒ—\Z");
        koumoku.add("‚PŒ—\Z");
        koumoku.add("‚QŒ—\Z");
        koumoku.add("‚RŒ—\Z");        
        koumoku.add("‰º”¼Šú‡Œv");
        return koumoku;
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // ©“®¶¬‚³‚ê‚½ƒƒ\ƒbƒhEƒXƒ^ƒu
        
    }

    
}
