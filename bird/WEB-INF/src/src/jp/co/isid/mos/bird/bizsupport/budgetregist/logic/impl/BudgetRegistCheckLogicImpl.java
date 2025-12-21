package jp.co.isid.mos.bird.bizsupport.budgetregist.logic.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetregist.common.BudgetRegistConstants;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dao.MstMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dao.TrnBudgetInfoDao;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.bizsupport.budgetregist.entity.MstMiseInfo;
import jp.co.isid.mos.bird.bizsupport.budgetregist.entity.TrnBudgetInfo;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.BudgetRegistCheckLogic;
import jp.co.isid.mos.bird.bizsupport.common.code.BudgetConstants;
import jp.co.isid.mos.bird.bizsupport.common.entity.CtlYosanControlDate;
import jp.co.isid.mos.bird.bizsupport.common.logic.YosanControlDateLogic;
import jp.co.isid.mos.bird.common.dao.MstNextNendoSibuDao;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstNextNendoSibu;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;


/**
 * 予算登録CSV取込エラーチェックロジック
 * 
 * @author Aspac
 */
public class BudgetRegistCheckLogicImpl implements BudgetRegistCheckLogic {

    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS022L03";

    /**
     * 予算更新DAO
     */
    private TrnBudgetInfoDao trnBudgetInfoDao;
    /**
     * 店情報DAO
     */
    private MstMiseInfoDao mstMiseInfoDao;
    /**
     * 支部マスタDAO
     */
    private MstSibuDao budgetRegistMstSibuDao;
    /**
     * 新年度支部マスタDAO
     */
    private MstNextNendoSibuDao budgetRegistMstNextNendoSibuDao;
    
    /**
     * 予算登録制御日付
     */
    private YosanControlDateLogic yosanControlDateLogic;
    
    /**
     * 予算更新DAOを取得します。 
     * @return
     */
    public TrnBudgetInfoDao getTrnBudgetInfoDao() {
        return trnBudgetInfoDao;
    }
    
    /**
     * 予算更新DAOを設定します。
     * @param trnBudgetInfoDao
     */
    public void setTrnBudgetInfoDao(TrnBudgetInfoDao trnBudgetInfoDao) {
        this.trnBudgetInfoDao = trnBudgetInfoDao;
    }
    /**
     * 店情報DAOを取得します。
     * @return 店情報DAO
     */
    public MstMiseInfoDao getMstMiseInfoDao() {
        return mstMiseInfoDao;
    }
    /**
     * 店情報DAOを設定します。
     * @param lumpTakeInPlYmVerifier 店情報DAO
     */
    public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
        this.mstMiseInfoDao = mstMiseInfoDao;
    }
    
    
    
    
    
    /**
     * 予算登録CSV取込ロジック
     * ※予算登録CSVのエラーチェック
     * ※エラー情報CSV生成
     * ※登録データ生成
     * 
     * @param budgetRegistDto 予算登録CSV取込DTO
     * @return 予算登録データ
     */
    public void execute(BudgetRegistDto budgetRegistDto) {
        
        /** 予算登録ファイル存在チェック **/
        
        //アップロードファイルチェック
        if (budgetRegistDto.getUploadedFile() == null
                || !budgetRegistDto.getUploadedFile().getName().toLowerCase().endsWith(".csv")) {
            throw new GenericMessageException("CSV形式で保存されたファイルを選択してください。", "uploadedFile");
        }
        
        
        /*************************************/
        /*** 予算登録ファイルCSVデータ読込 ***/
        /*************************************/
        
        String[][] csvArray = null;
        try {
            csvArray = CsvInputUtil.loadCSV(
                    budgetRegistDto.getUploadedFile().getInputStream(),
                    CsvInputUtil.OPTION_CSV_NO_DATA_HEAD_ROW);
                    //CsvInputUtil.OPTION_DEFAULT);
        } catch (FileNotFoundException e) {
            throw new GenericMessageException("CSV形式で保存されたファイルを選択してください。", "uploadedFile");            
        } catch (IOException e) {
            throw new InvalidInputException("CSVファイル");
        }
        
        
        /********************************/
        /*** 予算登録ファイルチェック ***/
        /********************************/
        
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        budgetRegistDto.setCurrentTimestamp(currentTimestamp);
        
        
        /** 予算登録リスト・エラー情報リスト **/
        
        List registInfoList = new ArrayList();
        List errorInfoList = new ArrayList();
        List rowItemList;
        
        
        
        /** ヘッダー部分　カラム部分チェック **/
                
        //登録データ件数チェック
        if (csvArray.length <= BudgetRegistConstants.CSV_CLM_LINE) {
            throw new GenericMessageException("予算データがありません。", "uploadedFile");
        }

        //項目数チェック       
        if (csvArray[BudgetRegistConstants.CSV_CLM_LINE - 1].length < BudgetRegistConstants.CSV_BUDGET_START_COL
                || csvArray[BudgetRegistConstants.CSV_CLM_LINE - 1].length > BudgetRegistConstants.CSV_MAX_ROW) {
            throw new GenericMessageException("項目数が不正です。", "uploadedFile");
        }
        
        //ヘッダー情報と項目行をエラーCSVデータに追加        
        rowItemList = new ArrayList();
        rowItemList.add(BudgetRegistConstants.CSV_HEADER_COMP_STR);
        rowItemList.add(convNull(budgetRegistDto.getCompanyName()));
        errorInfoList.add(rowItemList);
        
        rowItemList = new ArrayList();
        rowItemList.add(BudgetRegistConstants.CSV_HEADER_NEND_STR);
        rowItemList.add(convNull(budgetRegistDto.getCondYear()));
        errorInfoList.add(rowItemList);
        
        rowItemList = new ArrayList();
        rowItemList.add(BudgetRegistConstants.CSV_HEADER_MISE_STR);
        rowItemList.add(convNull(csvArray[2][1]));
        errorInfoList.add(rowItemList);
        
//        rowItemList = new ArrayList();
//        errorInfoList.add(rowItemList);
        
        rowItemList = new ArrayList();
        String[] clm = BudgetRegistConstants.CSV_CLM;
        for(int i=0; i<clm.length; i++ ) {
            rowItemList.add(clm[i]);
        }
        errorInfoList.add(rowItemList);
        
        //店舗情報取得
        List lstChkHavMise = new ArrayList();//マスタ存在チェック用リスト
        List lstChkDplMise = new ArrayList();//コード重複チェック用リスト
        List lstDplMise = new ArrayList();//コード重複リスト
        List mstMise = getMstMiseInfoDao().getMiseInfo(
                null,
                budgetRegistDto.getCondCompanyCd());
        
        if(mstMise == null || mstMise.size()==0) {
            throw new NoResultException("予算対象の店舗");
        }
        for (Iterator ite = mstMise.iterator(); ite.hasNext();) {
            MstMiseInfo miseInfo = (MstMiseInfo) ite.next();
            lstChkHavMise.add(miseInfo.getMiseCd());
        }
        Collections.sort(lstChkHavMise);
        Collections.sort(lstChkDplMise);
        
        //仮店番リスト取得
        List tempMiseCdList = createTempMiseCdList(budgetRegistDto.getCondYear(), budgetRegistDto.getCondCompanyCd());

        //支部マスタ取得
        List listSibu = getBudgetRegistMstSibuDao().getAllSibu(budgetRegistDto.getCondCompanyCd());
        //新年度支部マスタ取得
        List listNextNendoSibu = getBudgetRegistMstNextNendoSibuDao().getAllSibu(budgetRegistDto.getCondCompanyCd());
        
        /** データ部分チェック **/
        
        for (int i = BudgetRegistConstants.CSV_CLM_LINE; i < csvArray.length; i++) {
            
            rowItemList = new ArrayList();
            
            String tenpoShu = "";
            
            /***********************/
            /*****  店舗コード *****/
            /***********************/
            String rowMise = csvArray[i][BudgetRegistConstants.CSV_ROW_MISE -1];
           
            //必須チェック
            String rowMiseErr = "";
            if (rowMise == null || rowMise.trim().length() == 0) {
                rowMiseErr = BudgetRegistConstants.CSV_ERR_MUST;
            }
            
            int tenpoCount = 0;

            if(isNull(rowMiseErr)) {        
                //ゼロパディング実施
                rowMise = convCode(rowMise,BudgetRegistConstants.CODE_MISECD_LENGTH);
                //仮店番
                if (rowMise.startsWith(BudgetRegistConstants.CODE_MISECD_KARI_STR)) {
                    //フォーマットチェック
                    String revalue = rowMise.substring(1);
                    if (!getMiseKariCdVerifier().validate(revalue)) {
                        rowMiseErr = BudgetRegistConstants.CSV_ERR_FORMAT;
                    }
                    //仮店番存在チェック
                    if (tempMiseCdList.contains(rowMise)) {
                        rowMiseErr = BudgetRegistConstants.CSV_ERR_ISMST;
                    }
                //実店番    
                } else {
                    //店舗カウントセット                    
                    tenpoCount = BudgetRegistConstants.TENPO_CNT_COUNTABLE;

                    //フォーマットチェック
                    if (!getMiseCdVerifier().validate(rowMise)) {
                        rowMiseErr = BudgetRegistConstants.CSV_ERR_FORMAT;
                    }
                    //マスタ存在チェック
                    if(isNull(rowMiseErr) && Collections.binarySearch(lstChkHavMise,rowMise) < 0) {
                        rowMiseErr = BudgetRegistConstants.CSV_ERR_ISMST;
                    }
                }
                
                //重複チェック
                if(isNull(rowMiseErr) && Collections.binarySearch(lstChkDplMise,rowMise) >= 0) {
                    if(!lstDplMise.contains(rowMise)){
                        lstDplMise.add(rowMise);
                    }
                }else{
                    lstChkDplMise.add(rowMise);
                    Collections.sort(lstChkDplMise);
                }
                if(isNull(rowMiseErr)) {
                    //店舗種別を特定する
                    tenpoShu = createTenpoShu(rowMise, budgetRegistDto.getCondYear(), mstMise);
                }
            }
            if(!isNull(rowMiseErr)) {
                rowMise = rowMiseErr + BudgetRegistConstants.CSV_ERR_SEPARATOR  + rowMise;
                budgetRegistDto.setErrorFlg(true);                
            }
            rowItemList.add(rowMise);
            
            /***********************/
            /***** 店舗名称 ********/
            /***********************/
            String rowMiseName = csvArray[i][BudgetRegistConstants.CSV_ROW_MISENAME -1];
            if(isNull(rowMiseName)) rowMiseName = "";
            rowItemList.add(rowMiseName);
            
            /***********************/
            /***** FCRCコード ******/
            /***********************/
            String rowFcrcKbn = csvArray[i][BudgetRegistConstants.CSV_ROW_FCRC_KBN -1]; 
            String rowFcrcKbnErr = chkFCRC(rowFcrcKbn);
            if(!isNull(rowFcrcKbnErr)) {
                rowFcrcKbn = rowFcrcKbnErr + BudgetRegistConstants.CSV_ERR_SEPARATOR  + rowFcrcKbn;
                budgetRegistDto.setErrorFlg(true);                
            }
            rowItemList.add(rowFcrcKbn);
            
            /***********************/
            /***** FCRC ************/
            /***********************/
            String rowFcrc = csvArray[i][BudgetRegistConstants.CSV_ROW_FCRC -1];
            if(isNull(rowFcrc)) rowFcrc = "";
            rowItemList.add(rowFcrc);
                        
            /***********************/
            /***** 支部コード ******/
            /***********************/
            String rowSibu = csvArray[i][BudgetRegistConstants.CSV_ROW_SIBU -1];
            String rowSibuFullKeta = convCode(rowSibu,BudgetRegistConstants.CODE_SIBUCD_LENGHT);
            String rowSibuErr = chkSibuCd(rowSibuFullKeta, listSibu, listNextNendoSibu);
            if(!isNull(rowSibuErr)) {
                rowSibu = rowSibuErr + BudgetRegistConstants.CSV_ERR_SEPARATOR + rowSibu;
                budgetRegistDto.setErrorFlg(true);                
            }
            else {
                rowSibu = rowSibuFullKeta;
            }
            //rowSibu = convCode(rowSibu,BudgetRegistConstants.CODE_SIBUCD_LENGHT);
            rowItemList.add(rowSibu);
            
            /***********************/
            /***** 支部名称 ********/
            /***********************/
            String rowSibuName = csvArray[i][BudgetRegistConstants.CSV_ROW_SIBUNAME -1];
            if(isNull(rowSibuName)) rowSibuName = "";
            rowItemList.add(rowSibuName);
            
            /*************************/
            /***** 支部取込コード ****/
            /*************************/
            String rowAreaDai = csvArray[i][BudgetRegistConstants.CSV_ROW_AREADAI -1];
            String rowAreaDaiFullKeta = convCode(rowAreaDai,BudgetRegistConstants.CODE_AREADAICD_LENGHT);
            String rowAreaDaiErr = chkAreaDai(rowAreaDaiFullKeta, listSibu, listNextNendoSibu);
            if(!isNull(rowAreaDaiErr)) {
                rowAreaDai = rowAreaDaiErr + BudgetRegistConstants.CSV_ERR_SEPARATOR + rowAreaDai;
                budgetRegistDto.setErrorFlg(true);                
            }
            else {
                rowAreaDai = rowAreaDaiFullKeta;
            }
            rowItemList.add(rowAreaDai);          
            

            /**********************/
            /***** 支部取込 *******/
            /**********************/
            String rowAreaDaiName = csvArray[i][BudgetRegistConstants.CSV_ROW_AREADAINAME -1];
            if(isNull(rowAreaDaiName)) rowAreaDaiName = "";
            rowItemList.add(rowAreaDaiName);
            
            /*******************/
            /***** 予算合計 ****/
            /*******************/
            String rowYosanTotal = csvArray[i][BudgetRegistConstants.CSV_ROW_YOSANTOTAL -1];
            if(isNull(rowYosanTotal)) rowYosanTotal = "";
            rowItemList.add(rowYosanTotal);
            
            
            /*******************/
            /***** 予算 ********/
            /*******************/
//--- 2007/10/04 add 過去月予算登録対応            
            //上期置き換え処理済みかを判断
            String changeableStartYM = budgetRegistDto.getSysDateYearMonth();
            CtlYosanControlDate ctlYosan = getYosanControlDateLogic()
                                            .execute(budgetRegistDto.getCondCompanyCd(), budgetRegistDto.getCondYear(), BudgetConstants.YOSAN_CONTROL_SHORI_KBN_KAMIKI_OKIKAE);
            if (budgetRegistDto.isChangeKako()) {
                try {
                    changeableStartYM = DateManager.getPrevMonth(budgetRegistDto.getSysDateYearMonth(), 1);
                }
                catch (Exception ex) {
                    throw new FtlSystemException("日付", "", ex);
                }
                if (ctlYosan != null 
                        && (BudgetConstants.YOSAN_CONTROL_STATE_FLG_ZUMI.equals(ctlYosan.getStateFlg()) 
                                || BudgetConstants.YOSAN_CONTROL_STATE_FLG_IRAIZUMI.equals(ctlYosan.getStateFlg())))
                {
                    CtlYosanControlDate ctlYosanKamikiTo = getYosanControlDateLogic()
                                                    .execute(budgetRegistDto.getCondCompanyCd(), budgetRegistDto.getCondYear(), BudgetConstants.YOSAN_CONTROL_SHORI_KBN_KAMIKI_OKIKAE_TO);
                    if (ctlYosanKamikiTo.getShoriDt().compareTo(changeableStartYM) >= 0) {
                        changeableStartYM = budgetRegistDto.getSysDateYearMonth();
                    }
                }
            }
//---2007/10/04 add end            
            int zeroCnt = 0;
            int nullCnt = 0;
            int cntMonth = 0;
            for (int j = BudgetRegistConstants.CSV_ROW_YOSAN_START -1; j < BudgetRegistConstants.CSV_ROW_YOSAN_END && j < csvArray[i].length; j++) {
                
                TrnBudgetInfo registInfo = new TrnBudgetInfo();
                String error = "";
                String rowYosan = csvArray[i][j];

                //必須チェック 各予算(4月～3月)全てが未入力である場合
                if (isNull(rowYosan)) {
                    nullCnt++;
                    rowYosan = "";
                }

                //登録年月(yyyyMM)生成
                //対象が4月～12月では、画面選択した年
                //対象が1月～3月では、画面選択した翌年
                String year = budgetRegistDto.getCondYear();
                try {
                    //予算カラムの9番目以降は翌年1月～3月の予算
                    if(cntMonth >= 9) year = DateManager.getNextYear(year,1);
                }catch(Exception e){
                    throw new FtlSystemException(
                    "予算登録のエンティティ登録処理中に、年度算出ロジックでエラーが発生しました。", "", e);
                }

                //妥当性チェック(システム日付より過去の月の予算が設定されていた場合 --> エラー)
//--- 2007/10/04 過去月変更対応 update start                
//                String sysYearMonth = budgetRegistDto.getSysDateYearMonth();
                String yearMonth    = year+BudgetRegistConstants.DATE_MONTH[cntMonth];
//                if(!isNull(rowYosan) && sysYearMonth.compareTo(yearMonth) > 0){
                if(!isNull(rowYosan) && changeableStartYM.compareTo(yearMonth) > 0){
                    error = BudgetRegistConstants.CSV_ERR_VALID;
                }
//--- 2007/10/04 update end                
                //フォーマットチェック
                if(error.equals("") && !isNull(rowYosan)){
                    error = chkYosan(rowYosan);
                }


                if(error.equals("") && !isNull(rowYosan)) {

                    //整合性チェック 各予算(4月～3月)全てが"0"である場合
                    BigDecimal yosanValue = new BigDecimal(rowYosan);
                    if (yosanValue.longValue() == 0) {
                        zeroCnt++;
                    }

                    //登録エンティティを設定
                    registInfo.setYosanDt(year+BudgetRegistConstants.DATE_MONTH[cntMonth]);
                    registInfo.setCompanyCd(budgetRegistDto.getCondCompanyCd());
                    registInfo.setMiseCd(rowMise);
                    registInfo.setSibuCd(rowSibu);
                    registInfo.setAreaDai(rowAreaDai);
                    registInfo.setTenpoShu(tenpoShu);
                    registInfo.setFcRc(rowFcrcKbn);
                    registInfo.setYosan(yosanValue);
                    registInfo.setTenpoCount(new BigDecimal(tenpoCount));
                    registInfo.setSakujoKbn(BudgetRegistConstants.CODE_DELFLG_OFF);
                    registInfo.setLastDate(createLastDate(registInfo.getYosanDt(), budgetRegistDto.getSysdate()));
                    registInfo.setFirstUser(budgetRegistDto.getUserId());
                    registInfo.setFirstPgm("BBS022");
                    registInfo.setFirstTmsp(budgetRegistDto.getCurrentTimestamp());
                    registInfo.setLastUser(budgetRegistDto.getUserId());
                    registInfo.setLastPgm("BBS022");
                    registInfo.setLastTmsp(budgetRegistDto.getCurrentTimestamp());
                    
                    //エンティティをリストに追加
                    registInfoList.add(registInfo);
                    
                } else if (!error.equals("")) {
                    //エラー情報付加
                    rowYosan = error + BudgetRegistConstants.CSV_ERR_SEPARATOR + convNull(rowYosan);
                    budgetRegistDto.setErrorFlg(true);
                }
                
                rowItemList.add(rowYosan);
                cntMonth++;
            }
            //必須チェック 各予算(4月～3月)全てが未入力である場合
            if (nullCnt == cntMonth) {
                budgetRegistDto.setErrorFlg(true);
                rowItemList.set(BudgetRegistConstants.CSV_ROW_YOSAN_START -1,
                        BudgetRegistConstants.CSV_ERR_MUST + BudgetRegistConstants.CSV_ERR_SEPARATOR + rowItemList.get(BudgetRegistConstants.CSV_ROW_YOSAN_START -1));
            }
//--- 2007/06/14 delete start 全てゼロの登録を可にする
//---  全て未入力の場合はエラーとするが、そのチェックは上で行っているのでこの処理は不要
//            //整合性チェック 各予算(4月～3月)全てが"0"または未入力である場合
//            else if (zeroCnt + nullCnt == cntMonth) {
//                budgetRegistDto.setErrorFlg(true);
//                rowItemList.set(BudgetRegistConstants.CSV_ROW_YOSAN_START -1,
//                        BudgetRegistConstants.CSV_ERR_COMPL + BudgetRegistConstants.CSV_ERR_SEPARATOR + rowItemList.get(BudgetRegistConstants.CSV_ROW_YOSAN_START -1));
//            }
//--- 2007/06/14 delete end
            //予算チェックEND
            
            
            //エラー情報CSV追加
            errorInfoList.add(rowItemList);
        }
        
        //店舗コード重複チェックエラー記号を埋め込む
        for (int i=0; i<lstDplMise.size(); i++) {
            String dplMiseCd = (String)lstDplMise.get(i);
            for (int j=BudgetRegistConstants.CSV_DATA_LINE; j< errorInfoList.size(); j++) {
                List listRow = (List)errorInfoList.get(j);
                String miseErrCd = (String)listRow.get(BudgetRegistConstants.CSV_ROW_MISE -1 );
                int spIdx = 0;
                String miseCd = "";
                String errCd  = "";
                if(miseErrCd.indexOf(BudgetRegistConstants.CSV_ERR_SEPARATOR) >= 0) {
                    spIdx = miseErrCd.indexOf(BudgetRegistConstants.CSV_ERR_SEPARATOR);
                    miseCd = miseErrCd.substring(spIdx+1);
                    errCd  = miseErrCd.substring(0,spIdx);
                }else{
                    miseCd = miseErrCd;
                }
                //店舗コードが重複
                if(dplMiseCd.equals(miseCd)) {
                    errCd += BudgetRegistConstants.CSV_ERR_DUPL;
                    miseErrCd = errCd + BudgetRegistConstants.CSV_ERR_SEPARATOR + miseCd;
                    listRow.set(BudgetRegistConstants.CSV_ROW_MISE -1,miseErrCd);
                    budgetRegistDto.setErrorFlg(true);
                }
            }
        }
        
        // ※試験的にエラーにしてみる。
        //budgetRegistDto.setErrorFlg(true);
        
        budgetRegistDto.setListErrorInfo(errorInfoList);
        budgetRegistDto.setListBudget(registInfoList);
        
        return;
    }

    /**
     * FC/RC区分をチェックします。
     * ※必須チェック
     * ※フォーマットチェック
     * 
     * @param FC/RC区分
     * @return エラーコード
     */
    private String chkFCRC(String value) {
        
        String error = "";
        
        //必須チェック
        if (value == null || value.trim().length() ==0 ) {
            error = BudgetRegistConstants.CSV_ERR_MUST;
        }
        //フォーマットチェック
        else {
            if (!(value.equals(BudgetRegistConstants.CODE_FC)
                    || value.equals(BudgetRegistConstants.CODE_RC))) {
                error = BudgetRegistConstants.CSV_ERR_FORMAT;
            }
        }
        return error;
    }
    
    /**
     * 支部コードをチェックします。
     * ※必須チェック
     * ※フォーマットチェック
     * ※存在チェック
     * 
     * @param 支部コード
     * @return エラーコード
     */
    private String chkSibuCd(String value, List listSibu, List listNextNendoSibu) {
        
        String error = "";
        
        //必須チェック
        if (value == null || value.trim().length() ==0 ) {
            error = BudgetRegistConstants.CSV_ERR_MUST;
        }
        //フォーマットチェック
        else {
            if (!getSibuCdVerifier().validate(value)) {
                error = BudgetRegistConstants.CSV_ERR_FORMAT;
            }
        }
        //存在チェック
        if (isNull(error) && !chkExistSibu(value, listSibu, listNextNendoSibu)) {
            error = BudgetRegistConstants.CSV_ERR_ISMST;
        }
        
        return error;
    }
    
    /**
     * 支部取込コードをチェックします。
     * ※必須チェック
     * ※フォーマットチェック
     * ※存在チェック
     * 
     * @param 支部取込コード
     * @return エラーコード
     */
    private String chkAreaDai(String value, List listSibu, List listNextNendoSibu) {
        
        String error = "";
        
        //必須チェック
        if (value == null || value.trim().length() ==0 ) {
            error = BudgetRegistConstants.CSV_ERR_MUST;
        }
        //フォーマットチェック
        else {
            if (!getAreaDaiVerifier().validate(value)) {
                error = BudgetRegistConstants.CSV_ERR_FORMAT;
            }
        }
        //存在チェック
        if (isNull(error) && !chkExistSibu(value, listSibu, listNextNendoSibu)) {
            error = BudgetRegistConstants.CSV_ERR_ISMST;
        }
        return error;
    }
    
    
    /**
     * 予算をチェックします。
     * ※フォーマットチェック
     * 
     * @param 予算
     * @return エラーコード
     */
    private String chkYosan(String value) {
        
        String error = "";
        
        //フォーマットチェック
        if (value != null && value.trim().length() != 0
                && !getBudgetVerifier().validate(value)) {
            
            error = BudgetRegistConstants.CSV_ERR_FORMAT;
        }
        return error;
    }

    /**
     * 店舗種別を特定する
     * 店舗種別は画面の選択年度と対象店舗のオープン日付を比較し特定する
     * ※3:新店舗　　　　対象年度よりオープン日付の年度が前年以降
     * ※2:予算店舗　　　対象年度よりオープン日付の年度が前々年
     * ※1:前年対象店舗　対象年度よりオープン日付の年度が前々年以前
     * 
     * @param miseCd 店コード
     * @param targetNendo 対象年度
     * @param mstMise 店マスタ情報
     * @return 店舗種別
     *  
     */
    private String createTenpoShu(String miseCd, String targetNendo, List mstMise) {

        String tenpoShu = null;

        // 実店舗判定
        if (!miseCd.startsWith(BudgetRegistConstants.CODE_MISECD_KARI_STR)) {

            //オープン日付取得
            String openDt = null;
            for (Iterator ite = mstMise.iterator(); ite.hasNext();) {
                MstMiseInfo miseInfo = (MstMiseInfo) ite.next();
                if(miseInfo.getMiseCd().equals(miseCd)) {
                    openDt = miseInfo.getOpenDt();
                    break;
                }
            }

            if (openDt != null) {
                String openNendo = null;
                String prevNend = null;
                try {
                    // オープン日の年度取得
                    openNendo = DateManager.getCurrentYear(openDt);
                    // 対象年度の前年度取得
                    prevNend = DateManager.getPrevYear(targetNendo, 1);
                } catch (Exception e) {
                    throw new FtlSystemException("事業計画予算登録");
                }

                if (openNendo.compareTo(targetNendo) >= 0) {
                    // 対象年度以降にオープン予定の店舗は「新店」
                    tenpoShu = BudgetRegistConstants.CODE_TENPO_SHU_NEW;
                } else if (openNendo.equals(prevNend)) {
                    // 対象年度の前年度にオープンした店舗は「予算店」
                    tenpoShu = BudgetRegistConstants.CODE_TENPO_SHU_BUDGET;
                } else if (openNendo.compareTo(prevNend) < 0) {
                    // 対象年度の前々年度以前にオープンした店舗は「前年対象店」
                    tenpoShu = BudgetRegistConstants.CODE_TENPO_SHU_BEFORE;
                }
            }

        } else {
            // 仮店番は「新店」
            tenpoShu = BudgetRegistConstants.CODE_TENPO_SHU_NEW;
        }

        return tenpoShu;
    }

    /**
     * 店舗コードバリデータ
     */
    private CodeVerifier miseCdVerifier;
    /**
     * 店舗コードバリデータを取得します。
     * @return
     */
    public CodeVerifier getMiseCdVerifier() {
        return miseCdVerifier;
    }
    /**
     * 店舗コードバリデータを設定します。
     * @param
     */
    public void setMiseCdVerifier(CodeVerifier miseCdVerifier) {
        this.miseCdVerifier = miseCdVerifier;
    }
    
    /**
     * 仮店舗コードバリデータ
     * ※先頭文字『X』を除いた、４桁の数字文字を検証する
     */
    private CodeVerifier miseKariCdVerifier;
    /**
     * 仮舗コードバリデータを取得します。
     * @return
     */
    public CodeVerifier getMiseKariCdVerifier() {
        return miseKariCdVerifier;
    }
    /**
     * 仮舗コードバリデータを設定します。
     * @param
     */
    public void setMiseKariCdVerifier(CodeVerifier miseKariCdVerifier) {
        this.miseKariCdVerifier = miseKariCdVerifier;
    }
    
    /**
     * 支部コードバリデータ
     */
    private CodeVerifier sibuCdVerifier;
    /**
     * 支部コードバリデータを取得します。
     * @return
     */
    public CodeVerifier getSibuCdVerifier() {
        return sibuCdVerifier;
    }
    /**
     * 支部コードバリデータを設定します。
     * @param
     */
    public void setSibuCdVerifier(CodeVerifier sibuCdVerifier) {
        this.sibuCdVerifier = sibuCdVerifier;
    }

    /**
     * 支部取込コードバリデータ
     */
    private CodeVerifier areaDaiVerifier;
    /**
     * 支部取込コードバリデータを取得します。
     * @return
     */
    public CodeVerifier getAreaDaiVerifier() {
        return areaDaiVerifier;
    }
    /**
     * 支部取込コードバリデータを設定します。
     * @param
     */
    public void setAreaDaiVerifier(CodeVerifier areaDaiVerifier) {
        this.areaDaiVerifier = areaDaiVerifier;
    }
    
    /**
     * 予算バリデータ
     */
    private NumericVerifier budgetVerifier;
    /**
     * 予算バリデータを取得します。
     * @return
     */
    public NumericVerifier getBudgetVerifier() {
        return budgetVerifier;
    }
    /**
     * 予算バリデータを設定します。
     * @param
     */
    public void setBudgetVerifier(NumericVerifier budgetVerifier) {
        this.budgetVerifier = budgetVerifier;
    }

    /**
     * コードの桁数を調節する
     * ※引数Codeの先頭に"0"を付加しLengthの桁数に調節する
     * @return code 
     * @return length
     */
    private String convCode(String code, int length) {
        
        String reCode = "";
        
        if (isNull(code)) {
            return "";
        }
        
        if(code.length() < length) {
            int sp = length - code.length();
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < sp; i++ ) {
                sb.append('0');
            }
            sb.append(code);
            reCode = sb.toString();
        }
        else {
            reCode = code;
        }
        
        return reCode;
    }
    
    
    /**
     * Nullを空文字に変換
     * @param param
     * @return
     */
    private String convNull(String param) {
        if(param==null) return "";
        return param;
    }
    
    /**
     * Nullまたは空を判断
     * @return true:Null or 空
     */
    private boolean isNull(String value) {
        if(value==null || value.equals("")) return true;
        return false;
    }
    /**
     * 最終更新日付生成
     * @param yosanDt 予算対象年月
     * @param sysDate システム日付
     * @return 最終更新日付
     */
    private String createLastDate(String yosanDt, String sysDate) {

        String lastDate = null;

        // パラメータチェック
        if (yosanDt != null && yosanDt.length() == 6 && sysDate != null && sysDate.length() == 8) {
            String prevMonth = null;
            try {
                // 予算対象年月 - 10ヶ月
                prevMonth = DateManager.getPrevMonth(yosanDt, 10);
            } catch (Exception e) {
                throw new FtlSystemException("事業計画予算登録");
            }
            // 補正要否判定 予算対象年月 - 10ヶ月 > システム日付
            if (prevMonth.compareTo(sysDate) > 0) {
                // 予算対象年月 - 10ヶ月の1日
                lastDate = prevMonth + "01";
            } else {
                // 補正不要の場合、システム日付
                lastDate = sysDate;
            }
        }

        return lastDate;
    }

    /**
     * 仮店番リスト生成
     * @param nendo 年度
     * @param companyCd 会社コード
     * @return 仮店番リスト
     */
    private List createTempMiseCdList(String nendo, String companyCd) {

        // 仮店番リスト取得
        List tempMiseCdList = getTrnBudgetInfoDao().selectTempMiseCdList(nendo, companyCd);

        // 仮店番のみリストを作成
        List resultList = new ArrayList();
        for (Iterator it = tempMiseCdList.iterator(); it.hasNext();) {
            TrnBudgetInfo trnBudgetInfo = (TrnBudgetInfo) it.next();
            resultList.add(trnBudgetInfo.getMiseCd());
        }

        return resultList;
    }
    
    /**
     * 支部コード存在チェック
     * @param code
     * @return true:存在する
     */
    private boolean chkExistSibu(String code, List listSibu, List listNextNendoSibu) {
        //---2007/07/04 新支部マスタのみで存在チェックを行う
//        for (Iterator ite = listSibu.iterator(); ite.hasNext();) {
//            MstSibu mstSibu = (MstSibu) ite.next();
//            if (mstSibu.getSibuCd().equals(code)) {
//                return true;
//            }
//        }
        for (Iterator ite = listNextNendoSibu.iterator(); ite.hasNext();) {
            MstNextNendoSibu mstSibu = (MstNextNendoSibu) ite.next();
            if (mstSibu.getSibuCd().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public MstNextNendoSibuDao getBudgetRegistMstNextNendoSibuDao() {
        return budgetRegistMstNextNendoSibuDao;
    }

    public void setBudgetRegistMstNextNendoSibuDao(
            MstNextNendoSibuDao budgetRegistMstNextNendoSibuDao) {
        this.budgetRegistMstNextNendoSibuDao = budgetRegistMstNextNendoSibuDao;
    }

    public MstSibuDao getBudgetRegistMstSibuDao() {
        return budgetRegistMstSibuDao;
    }

    public void setBudgetRegistMstSibuDao(MstSibuDao budgetRegistMstSibuDao) {
        this.budgetRegistMstSibuDao = budgetRegistMstSibuDao;
    }

    public YosanControlDateLogic getYosanControlDateLogic() {
        return yosanControlDateLogic;
    }

    public void setYosanControlDateLogic(
            YosanControlDateLogic yosanControlDateLogicImpl) {
        this.yosanControlDateLogic = yosanControlDateLogicImpl;
    }
}
