package jp.co.isid.mos.bird.bizsupport.budgetregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dao.MstMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.bizsupport.budgetregist.entity.MstMiseInfo;

/**
 * –‹ÆŒv‰æ—\Z“o˜^CSVæƒeƒ“ƒvƒŒ[ƒgƒ_ƒEƒ“ƒ[ƒhƒƒWƒbƒN
 * 
 * @author Aspac
 */
public class BudgetRegistTemplateCsvOutputLogicImpl implements CsvOutputLogic {

    /** ƒƒWƒbƒNID ’è‹` */
    public static final String LOGIC_ID = "BBS022L02";

    /**
     * “Xî•ñDAO
     */
    private MstMiseInfoDao mstMiseInfoDao;


    /**
     * “Xî•ñDAO‚ğæ“¾‚µ‚Ü‚·B
     * @return “Xî•ñDAO
     */
    public MstMiseInfoDao getMstMiseInfoDao() {
        return mstMiseInfoDao;
    }

    /**
     * “Xî•ñDAO‚ğİ’è‚µ‚Ü‚·B
     * @param lumpTakeInPlYmVerifier “Xî•ñDAO
     */
    public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
        this.mstMiseInfoDao = mstMiseInfoDao;
    }


    /**
     * ƒtƒ@ƒCƒ‹–¼æ“¾
     * @param csvOutputDto CSVo—Í—pDTO
     * @return ƒtƒ@ƒCƒ‹–¼
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        // ƒeƒ“ƒvƒŒ[ƒgCSVƒtƒ@ƒCƒ‹–¼
        return "YOSAN_TEMPLATE.csv";
    }

    
    /**
     * o—Íƒf[ƒ^æ“¾ˆ—
     * @param csvOutputDto CSVo—Í—pDTO
     * @return CSVo—Íƒf[ƒ^ƒŠƒXƒg
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        BudgetRegistDto budgetRegistDto = (BudgetRegistDto) csvOutputDto;
        
        List outputDataList = new ArrayList();
        List headerList1 = new ArrayList();
        List headerList2 = new ArrayList();
        List headerList3 = new ArrayList();
        List headerList4 = new ArrayList();
        List rowColmList = new ArrayList();

        
        /*******************************/
        /*** CSVƒwƒbƒ_î•ñ‚ğ¶¬‚·‚é ***/
        /*******************************/
        
        headerList1.add("‰ïĞF");
        headerList1.add(budgetRegistDto.getCompanyName());
        headerList2.add("”N“xF");
        headerList2.add(budgetRegistDto.getCondYear());
        headerList3.add("‘ÎÛ“X•ÜF");
        rowColmList.add("“X•ÜƒR[ƒh");
        rowColmList.add("“X•Ü–¼Ì");
        rowColmList.add("FC/RC‹æ•ª");
        rowColmList.add("FC/RC");
        rowColmList.add("x•”ƒR[ƒh");
        rowColmList.add("x•”–¼Ì");
        rowColmList.add("x•”æƒR[ƒh");
        rowColmList.add("x•”æ–¼Ì");
        rowColmList.add("—\Z‡Œv");
        rowColmList.add("‚SŒ—\Z");
        rowColmList.add("‚TŒ—\Z");
        rowColmList.add("‚UŒ—\Z");
        rowColmList.add("‚VŒ—\Z");
        rowColmList.add("‚WŒ—\Z");
        rowColmList.add("‚XŒ—\Z");
        rowColmList.add("‚P‚OŒ—\Z");
        rowColmList.add("‚P‚PŒ—\Z");
        rowColmList.add("‚P‚QŒ—\Z");
        rowColmList.add("‚PŒ—\Z");
        rowColmList.add("‚QŒ—\Z");
        rowColmList.add("‚RŒ—\Z");
        
        outputDataList.add(headerList1);
        outputDataList.add(headerList2);
        outputDataList.add(headerList3);
        //outputDataList.add(headerList4);
        outputDataList.add(rowColmList);
        
        
        /*******************************/
        /*** CSVƒf[ƒ^î•ñ‚ğ¶¬‚·‚é ***/
        /*******************************/
        
        // “X•Üî•ñæ“¾
        List miseList = getMstMiseInfoDao().getMiseInfo(
                budgetRegistDto.getSysdate(),
                budgetRegistDto.getCondCompanyCd());
        
        if(miseList == null || miseList.size()==0) {
            throw new NoResultException("—\Z‘ÎÛ‚Ì“X•Ü");
        }
        
        for (Iterator ite = miseList.iterator(); ite.hasNext();) {
            List rowItemList = new ArrayList();
            MstMiseInfo miseInfo = (MstMiseInfo) ite.next();
            rowItemList.add(miseInfo.getMiseCd());
            rowItemList.add(miseInfo.getMiseNameKj());
            rowItemList.add(miseInfo.getMiseKbn());
            rowItemList.add(miseInfo.getFcrc());
            rowItemList.add(miseInfo.getSibuCd());
            rowItemList.add(miseInfo.getSibuName());            
            rowItemList.add(miseInfo.getAreaDai());
            rowItemList.add(miseInfo.getSibuTorikomiName());
            outputDataList.add(rowItemList);
        }
        
        return outputDataList;
    }

    /**
     * –‘OğŒƒ`ƒFƒbƒNˆ—
     * @param csvOutputDto CSVo—Í—pDTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
    }
}
