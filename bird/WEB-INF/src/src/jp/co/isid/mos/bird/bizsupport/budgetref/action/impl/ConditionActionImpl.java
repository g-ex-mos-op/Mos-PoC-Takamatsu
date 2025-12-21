/*
 * 作成日: 2006/
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.action.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.budgetref.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefCondDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefResultDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.dto.BudgetRefYMDto;
import jp.co.isid.mos.bird.bizsupport.budgetref.entity.TrnBudgetRegistDummy;
import jp.co.isid.mos.bird.bizsupport.budgetref.logic.BudgetRefInfoLogic;
import jp.co.isid.mos.bird.bizsupport.budgetref.logic.CondNendoLogic;
import jp.co.isid.mos.bird.bizsupport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 事業計画予算確認条件アクション
 * 
 * @author inazawa
 */
public class ConditionActionImpl implements ConditionAction {
    
    /*アクションID*/
    public static final String initialize_ACTION_ID = "BBS023A01";
    public static final String execute_ACTION_ID = "BBS023A02";
    /*SQL実行結果MAP用セッションキー*/
    private static final String KARI_MISE= "KARI_MISE";
    private static final String JITU_MISE = "JITU_MISE";
    private static final String ALL_MISE = "ALL_MISE";
    private static final String KARIMISE_TOTAL = "KARIMISE_TOTAL";
    private static final String JITUMISE_TOTAL = "JITUMISE_TOTAL";
    private static final String MISE_TOTAL = "MISE_TOTAL";
    /*合計MAP*/
    private static final String ALL = "ALL";
    private static final String FIRST_ALL = "FIRST_ALL";
    private static final String SECOND_ALL = "SECOND_ALL";
    
    /*Dto[事業計画予算確認]*/
    private BudgetRefCondDto budgetRefCondDto;
    /*DTO[指定年月指定]*/
    private BudgetRefYMDto budgetRefYMDDto;
    /*DTO[結果格納DTO]*/
    private BudgetRefResultDto budgetRefResultDto;
    /*LOGIC[条件画面対象年度]*/
    private CondNendoLogic budgetRefCondNendoLogic;
    /*LOGIC[ユーザー所得会社取得処理]*/
    private GetUserCompanyLogic budgetGetUserCompanyLogic;
    /*LOGIC[予算登録支部別]*/
    private BudgetRefInfoLogic budgetRefInfoLogic; 
    /*ユーザーが本部ユーザー*/
    private static final String HONBU_USER = "01";
    /*再検索判断 初回：0、2回目以降：1*/
    private static final String SEARCH = "0";
    private static final String RE_SEARCH = "1";
    
    
    
    /**
     * 初期処理
     * @throws Exception 
     */
    public String initialize() throws Exception {
        if (getPullDownMenuDto().isClearFlg()) {
            getBudgetRefCondDto().clear();
            getBudgetRefResultDto().clear();
            getPullDownMenuDto().setClearFlg(false);    
            //ユーザーIDの設定
            getBudgetRefCondDto().setUserId(getBirdUserInfo().getMstUser().getUser_id());
            //ユーザータイプの設定
            getBudgetRefCondDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            //システム日付の設定
            getBudgetRefCondDto().setSysDate(getBirdDateInfo().getSysDate());
            if(!getBudgetRefCondDto().getUserTypeCd().equals(HONBU_USER)){
                //本部以外のユーザーは使用権限のExceptionを発生
                throw new CannotAccessException();
            }
            List listCompany = getBudgetGetUserCompanyLogic().execute(getBudgetRefCondDto().getUserId());
            if (listCompany == null || listCompany.size() == 0) {
                throw new NotExistException("会社情報");
            }
            getBudgetRefCondDto().setListCompany(listCompany);
            List taishouNendo = getBudgetRefCondNendoLogic().execute(); 
            if(taishouNendo == null || taishouNendo.size() == 0){
                throw new  NotExistException("該当データ");
            }
            List nendoList = new ArrayList();
            for(int i=0;taishouNendo.size()>i;i++){
                TrnBudgetRegistDummy trnBudgetRegistDummy = (TrnBudgetRegistDummy)taishouNendo.get(i); 
                nendoList.add(DateManager.getCurrentYear(trnBudgetRegistDummy.getNendo()));
            }
            List taishouNendoList = new ArrayList();
            TrnBudgetRegistDummy trnBudgetRegistDummy = new TrnBudgetRegistDummy();
            for(int i=0;nendoList.size()>i;i++){
                String currentYear =  (String)nendoList.get(i);
                String currentYearNext = "";
                if(nendoList.size()-1!=i){
                    currentYearNext =  (String)nendoList.get(i+1);
                }
                if(!currentYear.equals(currentYearNext)){
                    trnBudgetRegistDummy = new TrnBudgetRegistDummy();
                    trnBudgetRegistDummy.setNendo(currentYear);
                    taishouNendoList.add(trnBudgetRegistDummy);
                }
            }
            getBudgetRefCondDto().setListTaishoNendo(taishouNendoList);
            getBudgetRefCondDto().setReExec(SEARCH);
        }
        return null;
    }
    /**
     * 検索実行
     * @return
     */
    public String execute() {
        ArrayList monthList = new ArrayList();
        for(int i=4;16>i;i++){
            if(13>i){
                if(10>i){
                    monthList.add((String)getBudgetRefCondDto().getNendo()+"0"+String.valueOf(i));
                }else{
                    monthList.add((String)getBudgetRefCondDto().getNendo()+String.valueOf(i));
                }
            }else{
                monthList.add((Integer.parseInt(getBudgetRefCondDto().getNendo())+1)+"0"+String.valueOf(i-12));
            }
        }
        setMonth(monthList);
        Map resultMap = getBudgetRefInfoLogic().execute(getBudgetRefCondDto(),getBudgetRefYMDDto());
        if(resultMap == null || resultMap.isEmpty()){
            getBudgetRefResultDto().setResultList(null);
            getBudgetRefCondDto().setReExec(null);
            throw new NotExistException("該当データ");
        }
        List resultList= setList(resultMap);
        getBudgetRefResultDto().setResultList(resultList);
        for(int i=0;getBudgetRefCondDto().getListCompany().size()>i;i++){
            CodCompany codCompany = (CodCompany)getBudgetRefCondDto().getListCompany().get(i);
            if(codCompany.getCompanyCd().equals(getBudgetRefCondDto().getCompanyCd())){
                getBudgetRefCondDto().setCompanyNm(codCompany.getCompanyName());
                break;
            }
        }
        getBudgetRefResultDto().setCompanyNm(getBudgetRefCondDto().getCompanyNm());
        getBudgetRefResultDto().setNendo(getBudgetRefCondDto().getNendo());
        getBudgetRefResultDto().setResultTotalList((List)resultMap.get(MISE_TOTAL));
        getBudgetRefCondDto().setReExec(RE_SEARCH);
        getBudgetRefResultDto().setBudgetRefYMDto(getBudgetRefYMDDto());
        getBudgetRefResultDto().setBudgetRefCondDto(getBudgetRefCondDto());
        return null;
    }
    /**
     * 結果をひとつのリストにまとめる
     * @param resultMap
     * @return
     */
    private List setList(Map resultMap) {
        List resultList = new ArrayList();
        List kariMiseList = (List)resultMap.get(KARI_MISE);
        List jituMiseList = (List)resultMap.get(JITU_MISE);
        List allMiseList = (List)resultMap.get(ALL_MISE);
        List tukiBetuKariMiseList = (List)resultMap.get(KARIMISE_TOTAL);
        List tukiBetuJituMiseList = (List)resultMap.get(JITUMISE_TOTAL);
        List tukiBetuAllMiseList = (List)resultMap.get(MISE_TOTAL);
        TrnBudgetRegistDummy kariMise = null;
        TrnBudgetRegistDummy jituMise = null;
        TrnBudgetRegistDummy allMise = null;
        TrnBudgetRegistDummy trnBudgetRegistDummy = null;
        for(int i=0; kariMiseList.size()>i ;i++){
            trnBudgetRegistDummy = new TrnBudgetRegistDummy();
            kariMise =(TrnBudgetRegistDummy) kariMiseList.get(i);
            jituMise =(TrnBudgetRegistDummy) jituMiseList.get(i);
            allMise =(TrnBudgetRegistDummy)  allMiseList.get(i);
            trnBudgetRegistDummy.setSibuCd(jituMise.getSibuCd());
            trnBudgetRegistDummy.setSibuName(jituMise.getSibuName());
            trnBudgetRegistDummy = setMonthJituMiseYosan(jituMise,trnBudgetRegistDummy);
            trnBudgetRegistDummy = setMonthKariMiseYosan(kariMise,trnBudgetRegistDummy);
            trnBudgetRegistDummy = setMonthAllMiseYosan(allMise,trnBudgetRegistDummy);
            resultList.add(trnBudgetRegistDummy);
        }
        
        trnBudgetRegistDummy = new TrnBudgetRegistDummy();
        trnBudgetRegistDummy.setSibuCd("総合計");
        trnBudgetRegistDummy = setMonthJituMiseYosan((TrnBudgetRegistDummy)tukiBetuJituMiseList.get(0),trnBudgetRegistDummy);
        trnBudgetRegistDummy = setMonthKariMiseYosan((TrnBudgetRegistDummy)tukiBetuKariMiseList.get(0),trnBudgetRegistDummy);
        trnBudgetRegistDummy = setMonthAllMiseYosan((TrnBudgetRegistDummy)tukiBetuAllMiseList.get(0),trnBudgetRegistDummy);
        resultList.add(trnBudgetRegistDummy);
        
        
        return resultList;
    }
    /**
     * 実店番登録
     * @param jituMise
     * @return
     */
    private TrnBudgetRegistDummy setMonthJituMiseYosan(TrnBudgetRegistDummy jituMise,TrnBudgetRegistDummy trnBudgetRegistDummy) {
        NumericFormatter numericFormatter = new NumericFormatter(); 
        trnBudgetRegistDummy.setJitutenbanKbn("実店番");
        Map total = setAddJituYosan(jituMise);
        if(isNull(total.get(ALL))){
            trnBudgetRegistDummy.setYosanJitumise(numericFormatter.format(total.get(ALL)));
        }else{
            trnBudgetRegistDummy.setYosanJitumise("0");
        }
        if(isNull(jituMise.getYosanJitumise4())){
            trnBudgetRegistDummy.setYosanJitumise4(numericFormatter.format(jituMise.getYosanJitumise4()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise4("0");
        }
        if(isNull(jituMise.getYosanJitumise5())){
            trnBudgetRegistDummy.setYosanJitumise5(numericFormatter.format(jituMise.getYosanJitumise5()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise5("0");
        }
        if(isNull(jituMise.getYosanJitumise6())){
            trnBudgetRegistDummy.setYosanJitumise6(numericFormatter.format(jituMise.getYosanJitumise6()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise6("0");
        }
        if(isNull(jituMise.getYosanJitumise7())){
            trnBudgetRegistDummy.setYosanJitumise7(numericFormatter.format(jituMise.getYosanJitumise7()));            
        }else{
            trnBudgetRegistDummy.setYosanJitumise7("0");
        }
        if(isNull(jituMise.getYosanJitumise8())){
            trnBudgetRegistDummy.setYosanJitumise8(numericFormatter.format(jituMise.getYosanJitumise8()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise8("0");
        }
        if(isNull(jituMise.getYosanJitumise9())){
            trnBudgetRegistDummy.setYosanJitumise9(numericFormatter.format(jituMise.getYosanJitumise9()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise9("0");
        }
        if(isNull(jituMise.getYosanJitumise10())){
            trnBudgetRegistDummy.setYosanJitumise10(numericFormatter.format(jituMise.getYosanJitumise10()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise10("0");
        }
        if(isNull(jituMise.getYosanJitumise11())){
            trnBudgetRegistDummy.setYosanJitumise11(numericFormatter.format(jituMise.getYosanJitumise11()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise11("0");
        }
        if(isNull(jituMise.getYosanJitumise12())){
            trnBudgetRegistDummy.setYosanJitumise12(numericFormatter.format(jituMise.getYosanJitumise12()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise12("0");
        }
        if(isNull(jituMise.getYosanJitumise1())){
            trnBudgetRegistDummy.setYosanJitumise1(numericFormatter.format(jituMise.getYosanJitumise1()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise1("0");
        }
        if(isNull(jituMise.getYosanJitumise2())){
            trnBudgetRegistDummy.setYosanJitumise2(numericFormatter.format(jituMise.getYosanJitumise2()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise2("0");
        }
        if(isNull(jituMise.getYosanJitumise3())){
            trnBudgetRegistDummy.setYosanJitumise3(numericFormatter.format(jituMise.getYosanJitumise3()));
        }else{
            trnBudgetRegistDummy.setYosanJitumise3("0");
        }
        if(isNull(total.get(FIRST_ALL))){
            trnBudgetRegistDummy.setYosanJitumiseFirst(numericFormatter.format(total.get(FIRST_ALL)));
        }else{
            trnBudgetRegistDummy.setYosanJitumiseFirst("0");
        }
        if(isNull(total.get(SECOND_ALL))){
            trnBudgetRegistDummy.setYosanJitumiseSecond(numericFormatter.format(total.get(SECOND_ALL)));
        }else{
            trnBudgetRegistDummy.setYosanJitumiseSecond("0");
        }

        return trnBudgetRegistDummy;
    }
    /**
     * 実店番の支部毎予算の合計
     * @param jituMise
     * @return
     */
    private Map setAddJituYosan(TrnBudgetRegistDummy jituMise) {
        BigDecimal jitu = new BigDecimal(0);
        BigDecimal jituFirst  = new BigDecimal(0);
        BigDecimal jituSecond = new BigDecimal(0);
        BigDecimal jitu4 = new BigDecimal(0);
        BigDecimal jitu5 = new BigDecimal(0);
        BigDecimal jitu6 = new BigDecimal(0);
        BigDecimal jitu7 = new BigDecimal(0);
        BigDecimal jitu8 = new BigDecimal(0);
        BigDecimal jitu9 = new BigDecimal(0);
        BigDecimal jitu10= new BigDecimal(0);
        BigDecimal jitu11= new BigDecimal(0);
        BigDecimal jitu12= new BigDecimal(0);
        BigDecimal jitu1 = new BigDecimal(0);
        BigDecimal jitu2 = new BigDecimal(0);
        BigDecimal jitu3 = new BigDecimal(0);
        Map total = new HashMap();
        if(isNull(jituMise.getYosanJitumise4())){
            jitu4 = new BigDecimal(jituMise.getYosanJitumise4());
        }else{
            jitu4 = new BigDecimal(0);
            jituMise.setYosanKarimise4("0");
        }
        jituFirst = jituFirst.add(jitu4);
        if(isNull(jituMise.getYosanJitumise5())){
            jitu5 = new BigDecimal(jituMise.getYosanJitumise5());
        }else{
            jitu5 = new BigDecimal(0);
            jituMise.setYosanKarimise5("0");
        }
        jituFirst = jituFirst.add(jitu5);
        if(isNull(jituMise.getYosanJitumise6())){
            jitu6 = new BigDecimal(jituMise.getYosanJitumise6());
        }else{
            jitu6 = new BigDecimal(0);
            jituMise.setYosanKarimise6("0");
        }
        jituFirst = jituFirst.add(jitu6);
        if(isNull(jituMise.getYosanJitumise7())){
            jitu7 = new BigDecimal(jituMise.getYosanJitumise7());
        }else{
            jitu7 = new BigDecimal(0);
            jituMise.setYosanKarimise7("0");
        }
        jituFirst = jituFirst.add(jitu7);
        if(isNull(jituMise.getYosanJitumise8())){
            jitu8 = new BigDecimal(jituMise.getYosanJitumise8());
        }else{
            jitu8 = new BigDecimal(0);
            jituMise.setYosanKarimise8("0");
        }
        jituFirst = jituFirst.add(jitu8);
        if(isNull(jituMise.getYosanJitumise9())){
            jitu9 = new BigDecimal(jituMise.getYosanJitumise9());
        }else{
            jitu9 = new BigDecimal(0);
            jituMise.setYosanKarimise9("0");
        }
        jituFirst = jituFirst.add(jitu9);
        if(isNull(jituMise.getYosanJitumise10())){
            jitu10 = new BigDecimal(jituMise.getYosanJitumise10());
        }else{
            jitu10 = new BigDecimal(0);
            jituMise.setYosanKarimise10("0");
        }
        jituSecond = jituSecond.add(jitu10);
        if(isNull(jituMise.getYosanJitumise11())){
            jitu11 = new BigDecimal(jituMise.getYosanJitumise11());
        }else{
            jitu11 = new BigDecimal(0);
            jituMise.setYosanKarimise11("0");
        }
        jituSecond = jituSecond.add(jitu11);
        if(isNull(jituMise.getYosanJitumise12())){
            jitu12 = new BigDecimal(jituMise.getYosanJitumise12());
        }else{
            jitu12 = new BigDecimal(0);
            jituMise.setYosanKarimise12("0");
        }
        jituSecond = jituSecond.add(jitu12);
        if(isNull(jituMise.getYosanJitumise1())){
            jitu1 = new BigDecimal(jituMise.getYosanJitumise1());
        }else{
            jitu1 = new BigDecimal(0);
            jituMise.setYosanKarimise1("0");
        }
        jituSecond = jituSecond.add(jitu1);
        if(isNull(jituMise.getYosanJitumise2())){
            jitu2 = new BigDecimal(jituMise.getYosanJitumise2());
        }else{
            jitu2 = new BigDecimal(0);
            jituMise.setYosanKarimise2("0");
        }
        jituSecond = jituSecond.add(jitu2);
        if(isNull(jituMise.getYosanJitumise3())){
            jitu3 = new BigDecimal(jituMise.getYosanJitumise3());
        }else{
            jitu3 = new BigDecimal(0);
            jituMise.setYosanKarimise3("0");
        }
        jituSecond = jituSecond.add(jitu3);
        
        jitu = jituFirst.add(jituSecond);
        total.put(FIRST_ALL,String.valueOf(jituFirst));
        total.put(SECOND_ALL,String.valueOf(jituSecond));
        total.put(ALL,String.valueOf(jitu));
        return total;
    }
    /**
     * 仮店番登録
     * @param kariMise
     * @return
     */
    private TrnBudgetRegistDummy setMonthKariMiseYosan(TrnBudgetRegistDummy kariMise,TrnBudgetRegistDummy trnBudgetRegistDummy) {
        NumericFormatter numericFormatter = new NumericFormatter(); 
        trnBudgetRegistDummy.setKaritenbanKbn("仮店番");
        Map total = setAddKariYosan(kariMise);
        if(isNull(total.get(ALL))){
            trnBudgetRegistDummy.setYosanKarimise(numericFormatter.format(total.get(ALL)));
        }else{
            trnBudgetRegistDummy.setYosanKarimise("0");
        }
        if(isNull(kariMise.getYosanKarimise4())){
            trnBudgetRegistDummy.setYosanKarimise4(numericFormatter.format(kariMise.getYosanKarimise4()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise4("0");
        }
        if(isNull(kariMise.getYosanKarimise5())){
            trnBudgetRegistDummy.setYosanKarimise5(numericFormatter.format(kariMise.getYosanKarimise5()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise5("0");
        }
        if(isNull(kariMise.getYosanKarimise6())){
            trnBudgetRegistDummy.setYosanKarimise6(numericFormatter.format(kariMise.getYosanKarimise6()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise6("0");
        }
        if(isNull(kariMise.getYosanKarimise7())){
            trnBudgetRegistDummy.setYosanKarimise7(numericFormatter.format(kariMise.getYosanKarimise7()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise7("0");
        }
        if(isNull(kariMise.getYosanKarimise8())){
            trnBudgetRegistDummy.setYosanKarimise8(numericFormatter.format(kariMise.getYosanKarimise8()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise8("0");
        }
        if(isNull(kariMise.getYosanKarimise9())){
            trnBudgetRegistDummy.setYosanKarimise9(numericFormatter.format(kariMise.getYosanKarimise9()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise9("0");
        }
        if(isNull(kariMise.getYosanKarimise10())){
            trnBudgetRegistDummy.setYosanKarimise10(numericFormatter.format(kariMise.getYosanKarimise10()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise10("0");
        }
        if(isNull(kariMise.getYosanKarimise11())){
            trnBudgetRegistDummy.setYosanKarimise11(numericFormatter.format(kariMise.getYosanKarimise11()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise11("0");
        }
        if(isNull(kariMise.getYosanKarimise12())){
            trnBudgetRegistDummy.setYosanKarimise12(numericFormatter.format(kariMise.getYosanKarimise12()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise12("0");
        }
        if(isNull(kariMise.getYosanKarimise1())){
            trnBudgetRegistDummy.setYosanKarimise1(numericFormatter.format(kariMise.getYosanKarimise1()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise1("0");
        }
        if(isNull(kariMise.getYosanKarimise2())){
            trnBudgetRegistDummy.setYosanKarimise2(numericFormatter.format(kariMise.getYosanKarimise2()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise2("0");
        }
        if(isNull(kariMise.getYosanKarimise3())){
            trnBudgetRegistDummy.setYosanKarimise3(numericFormatter.format(kariMise.getYosanKarimise3()));
        }else{
            trnBudgetRegistDummy.setYosanKarimise3("0");
        }
        if(isNull(total.get(FIRST_ALL))){
            trnBudgetRegistDummy.setYosanKarimiseFirst(numericFormatter.format(total.get(FIRST_ALL)));
        }else{
            trnBudgetRegistDummy.setYosanKarimiseFirst("0");
        }
        if(isNull(total.get(SECOND_ALL))){
            trnBudgetRegistDummy.setYosanKarimiseSecond(numericFormatter.format(total.get(SECOND_ALL)));
        }else{
            trnBudgetRegistDummy.setYosanKarimiseSecond("0");
        }
        return trnBudgetRegistDummy;
    }
    /**
     * 仮店番の支部毎予算
     * @param jituMise
     * @return
     */
    private Map setAddKariYosan(TrnBudgetRegistDummy kariMise) {
        Map total = new HashMap();
        BigDecimal kari = new BigDecimal(0);
        BigDecimal kariFirst = new BigDecimal(0);
        BigDecimal kariSecond = new BigDecimal(0);
        BigDecimal kari4 = new BigDecimal(0);
        BigDecimal kari5 = new BigDecimal(0);
        BigDecimal kari6 = new BigDecimal(0);
        BigDecimal kari7 = new BigDecimal(0);
        BigDecimal kari8 = new BigDecimal(0);
        BigDecimal kari9 = new BigDecimal(0);
        BigDecimal kari10 = new BigDecimal(0);
        BigDecimal kari11 = new BigDecimal(0);
        BigDecimal kari12 = new BigDecimal(0);
        BigDecimal kari1 = new BigDecimal(0);
        BigDecimal kari2 = new BigDecimal(0);
        BigDecimal kari3 = new BigDecimal(0);
        if(isNull(kariMise.getYosanKarimise4())){
            kari4 =  new BigDecimal(kariMise.getYosanKarimise4());
        }else{
            kari4 =  new BigDecimal(0);
            kariMise.setYosanKarimise4("0");
        }
        kariFirst = kariFirst.add(kari4);
        if(isNull(kariMise.getYosanKarimise5())){
            kari5 =  new BigDecimal(kariMise.getYosanKarimise5());
        }else{
            kari5 =  new BigDecimal(0);
            kariMise.setYosanKarimise5("0");
        }
        kariFirst = kariFirst.add(kari5);
        if(isNull(kariMise.getYosanKarimise6())){
            kari6 =  new BigDecimal(kariMise.getYosanKarimise6());
        }else{
            kari6 =  new BigDecimal(0);
            kariMise.setYosanKarimise6("0");
        }
        kariFirst = kariFirst.add(kari6); 
        if(isNull(kariMise.getYosanKarimise7())){
            kari7 =  new BigDecimal(kariMise.getYosanKarimise7());
        }else{
            kari7 =  new BigDecimal(0);
            kariMise.setYosanKarimise7("0");
        }
        kariFirst = kariFirst.add(kari7); 
        if(isNull(kariMise.getYosanKarimise8())){
            kari8 =  new BigDecimal(kariMise.getYosanKarimise8());
        }else{
            kari8 =  new BigDecimal(0);
            kariMise.setYosanKarimise8("0");
        }
        kariFirst = kariFirst.add(kari8); 
        if(isNull(kariMise.getYosanKarimise9())){
            kari9 =  new BigDecimal(kariMise.getYosanKarimise9());
        }else{
            kari9 =  new BigDecimal(0);
            kariMise.setYosanKarimise9("0");
        }
        kariFirst = kariFirst.add(kari9); 
        if(isNull(kariMise.getYosanKarimise10())){
            kari10 =  new BigDecimal(kariMise.getYosanKarimise10());
        }else{
            kari10 =  new BigDecimal(0);
            kariMise.setYosanKarimise10("0");
        }
        kariSecond = kariSecond.add(kari10); 
        if(isNull(kariMise.getYosanKarimise11())){
            kari11 =  new BigDecimal(kariMise.getYosanKarimise11());
        }else{
            kari11 =  new BigDecimal(0);
            kariMise.setYosanKarimise11("0");
        }
        kariSecond = kariSecond.add(kari11); 
        if(isNull(kariMise.getYosanKarimise12())){
            kari12 =  new BigDecimal(kariMise.getYosanKarimise12());
        }else{
            kari12 =  new BigDecimal(0);
            kariMise.setYosanKarimise12("0");
        }
        kariSecond = kariSecond.add(kari12); 
        if(isNull(kariMise.getYosanKarimise1())){
            kari1 =  new BigDecimal(kariMise.getYosanKarimise1());
        }else{
            kari1 =  new BigDecimal(0);
            kariMise.setYosanKarimise1("0");
        }
        kariSecond = kariSecond.add(kari1); 
        if(isNull(kariMise.getYosanKarimise2())){
            kari2 =  new BigDecimal(kariMise.getYosanKarimise2());
        }else{
            kari2 =  new BigDecimal(0);
            kariMise.setYosanKarimise2("0");
        }
        kariSecond = kariSecond.add(kari2); 
        if(isNull(kariMise.getYosanKarimise3())){
            kari3 =  new BigDecimal(kariMise.getYosanKarimise3());
        }else{
            kari3 =  new BigDecimal(0);
            kariMise.setYosanKarimise3("0");
        }
        kariSecond = kariSecond.add(kari3); 

        
        kari = kariFirst.add(kariSecond);
        total.put(FIRST_ALL,String.valueOf(kariFirst));
        total.put(SECOND_ALL,String.valueOf(kariSecond));
        total.put(ALL,String.valueOf(kari));

        return  total;
    }

    /**
     * 全店舗登録
     * @param allMise
     * @return
     */
    private TrnBudgetRegistDummy setMonthAllMiseYosan(TrnBudgetRegistDummy allMise,TrnBudgetRegistDummy trnBudgetRegistDummy) {
        NumericFormatter numericFormatter = new NumericFormatter(); 
        Map total = setAddAllYosan(allMise);
        trnBudgetRegistDummy.setMiseKbn("合計");
        if(isNull(allMise.getYosanAll4())){
            trnBudgetRegistDummy.setYosanAll4(numericFormatter.format(allMise.getYosanAll4()));
        }else{
            trnBudgetRegistDummy.setYosanAll4("0");
        }
        if(isNull(allMise.getYosanAll5())){
            trnBudgetRegistDummy.setYosanAll5(numericFormatter.format(allMise.getYosanAll5()));
        }else{
            trnBudgetRegistDummy.setYosanAll5("0");
        }
        if(isNull(allMise.getYosanAll6())){
            trnBudgetRegistDummy.setYosanAll6(numericFormatter.format(allMise.getYosanAll6()));
        }else{
            trnBudgetRegistDummy.setYosanAll6("0");
        }
        if(isNull(allMise.getYosanAll7())){
            trnBudgetRegistDummy.setYosanAll7(numericFormatter.format(allMise.getYosanAll7()));
        }else{
            trnBudgetRegistDummy.setYosanAll7("0");
        }
        if(isNull(allMise.getYosanAll8())){
            trnBudgetRegistDummy.setYosanAll8(numericFormatter.format(allMise.getYosanAll8()));
        }else{
            trnBudgetRegistDummy.setYosanAll8("0");
        }
        if(isNull(allMise.getYosanAll9())){
            trnBudgetRegistDummy.setYosanAll9(numericFormatter.format(allMise.getYosanAll9()));
        }else{
            trnBudgetRegistDummy.setYosanAll9("0");
        }
        if(isNull(allMise.getYosanAll10())){
            trnBudgetRegistDummy.setYosanAll10(numericFormatter.format(allMise.getYosanAll10()));
        }else{
            trnBudgetRegistDummy.setYosanAll10("0");
        }
        if(isNull(allMise.getYosanAll11())){
            trnBudgetRegistDummy.setYosanAll11(numericFormatter.format(allMise.getYosanAll11()));
        }else{
            trnBudgetRegistDummy.setYosanAll11("0");
        }
        if(isNull(allMise.getYosanAll12())){
            trnBudgetRegistDummy.setYosanAll12(numericFormatter.format(allMise.getYosanAll12()));
        }else{
            trnBudgetRegistDummy.setYosanAll12("0");
        }
        if(isNull(allMise.getYosanAll1())){
            trnBudgetRegistDummy.setYosanAll1(numericFormatter.format(allMise.getYosanAll1()));
        }else{
            trnBudgetRegistDummy.setYosanAll1("0");
        }
        if(isNull(allMise.getYosanAll2())){
            trnBudgetRegistDummy.setYosanAll2(numericFormatter.format(allMise.getYosanAll2()));
        }else{
            trnBudgetRegistDummy.setYosanAll2("0");
        }
        if(isNull(allMise.getYosanAll3())){
            trnBudgetRegistDummy.setYosanAll3(numericFormatter.format(allMise.getYosanAll3()));
        }else{
            trnBudgetRegistDummy.setYosanAll3("0");
        }
        if(isNull(total.get(ALL))){
            trnBudgetRegistDummy.setYosanAll(numericFormatter.format(total.get(ALL)));
        }else{
            trnBudgetRegistDummy.setYosanAll("0");   
        }
        if(isNull(total.get(FIRST_ALL))){
            trnBudgetRegistDummy.setYosanFirst(numericFormatter.format(total.get(FIRST_ALL)));
        }else{
            trnBudgetRegistDummy.setYosanFirst("0");
        }
        if(isNull(total.get(SECOND_ALL))){
            trnBudgetRegistDummy.setYosanSecond(numericFormatter.format(total.get(SECOND_ALL)));
        }else{
            trnBudgetRegistDummy.setYosanSecond("0");
        }
        return trnBudgetRegistDummy;
    }
    /**
     * 全店舗の支部毎予算
     * @param jituMise
     * @return
     */
    private Map setAddAllYosan(TrnBudgetRegistDummy allMise) {
        BigDecimal all = new BigDecimal(0);
        BigDecimal allFirst = new BigDecimal(0);
        BigDecimal allSecond = new BigDecimal(0);
        Map total = new HashMap();
        BigDecimal all4 = new BigDecimal(0);
        BigDecimal all5 = new BigDecimal(0);
        BigDecimal all6 = new BigDecimal(0);
        BigDecimal all7 = new BigDecimal(0);
        BigDecimal all8 = new BigDecimal(0);
        BigDecimal all9 = new BigDecimal(0);
        BigDecimal all10= new BigDecimal(0);
        BigDecimal all11= new BigDecimal(0);
        BigDecimal all12= new BigDecimal(0);
        BigDecimal all1 = new BigDecimal(0);
        BigDecimal all2 = new BigDecimal(0);
        BigDecimal all3 = new BigDecimal(0);
  
        if(isNull(allMise.getYosanAll4())){
            all4 =  new BigDecimal(allMise.getYosanAll4());
        }else{
            all4 =  new BigDecimal(0); 
            allMise.setYosanAll4("0");
        }
        allFirst = allFirst.add(all4);
        if(isNull(allMise.getYosanAll5())){
            all5 =  new BigDecimal(allMise.getYosanAll5());
        }else{
            all5 =  new BigDecimal(0); 
            allMise.setYosanAll5("0");
        }
        allFirst = allFirst.add(all5);
        if(isNull(allMise.getYosanAll6())){
            all6 =  new BigDecimal(allMise.getYosanAll6());
        }else{
            all6 =  new BigDecimal(0); 
            allMise.setYosanAll6("0");
        }
        allFirst = allFirst.add(all6);
        if(isNull(allMise.getYosanAll7())){
            all7 =  new BigDecimal(allMise.getYosanAll7());
        }else{
            all7 =  new BigDecimal(0); 
            allMise.setYosanAll7("0");
        }
        allFirst = allFirst.add(all7);
        if(isNull(allMise.getYosanAll8())){
            all8 =  new BigDecimal(allMise.getYosanAll8());
        }else{
            all8 =  new BigDecimal(0); 
            allMise.setYosanAll8("0");
        }
        allFirst = allFirst.add(all8);
        if(isNull(allMise.getYosanAll9())){
            all9 =  new BigDecimal(allMise.getYosanAll9());
        }else{
            all9 =  new BigDecimal(0); 
            allMise.setYosanAll9("0");
        }
        allFirst = allFirst.add(all9);
        if(isNull(allMise.getYosanAll10())){
            all10 =  new BigDecimal(allMise.getYosanAll10());
        }else{
            all10 =  new BigDecimal(0); 
            allMise.setYosanAll10("0");
        }
        allSecond = allSecond.add(all10);
        if(isNull(allMise.getYosanAll11())){
            all11 =  new BigDecimal(allMise.getYosanAll11());
        }else{
            all11 =  new BigDecimal(0); 
            allMise.setYosanAll11("0");
        }
        allSecond = allSecond.add(all11);
        if(isNull(allMise.getYosanAll12())){
            all12 =  new BigDecimal(allMise.getYosanAll12());
        }else{
            all12 =  new BigDecimal(0); 
            allMise.setYosanAll12("0");
        }
        allSecond = allSecond.add(all12);
        if(isNull(allMise.getYosanAll1())){
            all1 =  new BigDecimal(allMise.getYosanAll1());
        }else{
            all1 =  new BigDecimal(0); 
            allMise.setYosanAll1("0");
        }
        allSecond = allSecond.add(all1);
        if(isNull(allMise.getYosanAll2())){
            all2 =  new BigDecimal(allMise.getYosanAll2());
        }else{
            all2 =  new BigDecimal(0); 
            allMise.setYosanAll2("0");
        }
        allSecond = allSecond.add(all2);
        if(isNull(allMise.getYosanAll3())){
            all3 =  new BigDecimal(allMise.getYosanAll3());
        }else{
            all3 =  new BigDecimal(0); 
            allMise.setYosanAll3("0");
        }
        allSecond = allSecond.add(all3);

        all = allFirst.add(allSecond);
        total.put(FIRST_ALL,String.valueOf(allFirst));
        total.put(SECOND_ALL,String.valueOf(allSecond));
        total.put(ALL,String.valueOf(all));
        return total;
    }
    /**
     * 各月をDTOにセット
     * @param monthList
     */
    private void setMonth(ArrayList monthList) {
        for(int i=0; monthList.size()>i ;i++){
            if(i==0){
                getBudgetRefYMDDto().setYosanYm4((String)monthList.get(i));
            }else if(i==1){
                getBudgetRefYMDDto().setYosanYm5((String)monthList.get(i));
            }else if(i==2){
                getBudgetRefYMDDto().setYosanYm6((String)monthList.get(i));
            }else if(i==3){
                getBudgetRefYMDDto().setYosanYm7((String)monthList.get(i));
            }else if(i==4){
                getBudgetRefYMDDto().setYosanYm8((String)monthList.get(i));
            }else if(i==5){
                getBudgetRefYMDDto().setYosanYm9((String)monthList.get(i));
            }else if(i==6){
                getBudgetRefYMDDto().setYosanYm10((String)monthList.get(i));
            }else if(i==7){
                getBudgetRefYMDDto().setYosanYm11((String)monthList.get(i));
            }else if(i==8){
                getBudgetRefYMDDto().setYosanYm12((String)monthList.get(i));
            }else if(i==9){
                getBudgetRefYMDDto().setYosanYm1((String)monthList.get(i));
            }else if(i==10){
                getBudgetRefYMDDto().setYosanYm2((String)monthList.get(i));
            }else if(i==11){
                getBudgetRefYMDDto().setYosanYm3((String)monthList.get(i));
            }
        }
    }
    /**
     * pullDownMenuDtoの設定
     * @return pullDownMenuDto を戻します。
     */
    private PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    /**
     * コンテナーの取得
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * budgetRefDtoの取得
     * @return budgetRefDto
     */
    public BudgetRefCondDto getBudgetRefCondDto() {
        return budgetRefCondDto;
    }
    /**
     * budgetRefDtoの設定
     * @param budgetRefDto
     */
    public void setBudgetRefCondDto(BudgetRefCondDto budgetRefCondDto) {
        this.budgetRefCondDto = budgetRefCondDto;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * budgetRefCondNendoLogicを取得
     * @return budgetRefCondNendoLogic
     */
    public CondNendoLogic getBudgetRefCondNendoLogic() {
        return budgetRefCondNendoLogic;
    }
    /**
     * budgetRefCondNendoLogicを設定
     * @param budgetRefCondNendoLogic
     */
    public void setBudgetRefCondNendoLogic(CondNendoLogic budgetRefCondNendoLogic) {
        this.budgetRefCondNendoLogic = budgetRefCondNendoLogic;
    }
    /**
     * budgetGetUserCompanyLogicを取得
     * @return budgetGetUserCompanyLogic
     */
    public GetUserCompanyLogic getBudgetGetUserCompanyLogic() {
        return budgetGetUserCompanyLogic;
    }
    /**
     * budgetGetUserCompanyLogicを設定
     * @param budgetGetUserCompanyLogic
     */
    public void setBudgetGetUserCompanyLogic(
            GetUserCompanyLogic budgetGetUserCompanyLogic) {
        this.budgetGetUserCompanyLogic = budgetGetUserCompanyLogic;
    }
    /**
     * budgetRefInfoLogicを取得します
     * @return budgetRefInfoLogic
     */
    public BudgetRefInfoLogic getBudgetRefInfoLogic() {
        return budgetRefInfoLogic;
    }
    /**
     * budgetRefInfoLogicを設定します
     * @param budgetRefInfoLogic
     */
    public void setBudgetRefInfoLogic(BudgetRefInfoLogic budgetRefInfoLogic) {
        this.budgetRefInfoLogic = budgetRefInfoLogic;
    }
    /**
     * budgetRefYMDDtoを取得
     * @return budgetRefYMDDto
     */
    public BudgetRefYMDto getBudgetRefYMDDto() {
        return budgetRefYMDDto;
    }
    /**
     * budgetRefYMDDtoをセット
     * @param budgetRefYMDDto
     */
    public void setBudgetRefYMDDto(BudgetRefYMDto budgetRefYMDDto) {
        this.budgetRefYMDDto = budgetRefYMDDto;
    }
    /**
     * budgetRefResultDtoを取得
     * @return budgetRefResultDto
     */
    public BudgetRefResultDto getBudgetRefResultDto() {
        return budgetRefResultDto;
    }
    /**
     * budgetRefResultDtoをセット
     * @param budgetRefResultDto
     */
    public void setBudgetRefResultDto(BudgetRefResultDto budgetRefResultDto) {
        this.budgetRefResultDto = budgetRefResultDto;
    }
    /**
     * null、空文字判定
     * @param object
     * @return
     */
    private boolean isNull(Object object) {
        if(object == null || "".equals(object.toString().trim())){
            return false; 
        }
        return  true;
    }

}
