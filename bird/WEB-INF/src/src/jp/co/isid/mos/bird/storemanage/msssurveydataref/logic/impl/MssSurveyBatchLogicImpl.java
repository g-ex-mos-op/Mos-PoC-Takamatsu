/*
 * 作成日: 2006/08/15
 *
 */
package jp.co.isid.mos.bird.storemanage.msssurveydataref.logic.impl;
import java.io.File;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.BatchExecuteDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.BatchProcessingException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.logic.BatchExecuteLogic;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dao.UIMssBatchDao;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyBatchDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.UIMssBatch;


/**
 * バッチ起動処理
 * @author inazawa
 */
public class MssSurveyBatchLogicImpl implements BatchExecuteLogic {

    /*LOGICID*/
    public static final String LOGIC_ID = "BSM012L02";
    
    private static final String PGM_ID_TORI = "BMSPTORI";
    private static final String PGM_ID_SIBU = "BMSPSIBU";
    private static final String[] PGM_ID = {PGM_ID_TORI, PGM_ID_SIBU};
    private static final String STAT_BATCH = "1";
    private static final String STAT_ERR = "9";
    private static final String TORI_BATCH = "0";
    private static final String SIBU_BATCH = "1";

    private  UIMssBatchDao UIMssBatchDao;
    /**
     * チェック処理を実装します。最初に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public void validate(BatchExecuteDto batchExecuteDto)throws ApplicationException {
        MssSuerveyBatchDto dto = (MssSuerveyBatchDto) batchExecuteDto;
        //取り込みと支部洗換えの結合ステータス取得
        UIMssBatch uIMssBatch = getUIMssBatchDao().selectJobName(PGM_ID,"01"); 
        String statKbn = uIMssBatch.getStatKbn().trim();
        if(statKbn.equals(STAT_BATCH)){
            throw new BatchProcessingException("しばらくお待ち下さい。");
        }else if(statKbn.equals(STAT_ERR)){
            throw new GenericCommentException("処理エラー中","エラー解除を行ってから再度実行して下さい。");

        }
        if(dto.getShoriKbn().equals(TORI_BATCH)){
            String path = BirdContext.getProperty("fileProperty", "upLoadPathMss");
            File dir = new File(path);
            File pdfFiles[] = dir.listFiles();
            if(pdfFiles == null){
                throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFile", null);
            }else{
                if(pdfFiles.length == 0){
                    throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFile", null);
                }
            }
        }
    }
    /**
     * 実行するジョブをリターンするように実装します。
     * diconファイルからセットされた値を使用したい場合はBatchExecuteDto#getExecuteJobName()の
     * 戻り値をリターンするように実装します。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     * @return 実行するジョブ
     */
    public String[] getExecuteBatch(BatchExecuteDto batchExecuteDto) {
        MssSuerveyBatchDto dto = (MssSuerveyBatchDto) batchExecuteDto;
        
        String userID = dto.getUserId();
       
        String[] jobName = null;       
        if(dto.getShoriKbn().equals(TORI_BATCH)){
            //取り込み処理の場合"BIRD_MSP_TORI","COMPNAY_CD", "USERID", "NENDO","KAI"
            jobName = new String[]{"BIRD_MSP_TORI",dto.getCompanyCd(),userID, dto.getNendo(),dto.getKai()};
        }else if(dto.getShoriKbn().equals(SIBU_BATCH)){
            //支部洗え替えの場合"BIRD_MSP_SIBU","COMPNAY_CD", "USERID", "NENDO","KAI"
            jobName = new String[]{"BIRD_MSP_SIBU",dto.getCompanyCd(),userID, dto.getNendo()};
        }
        return jobName;
    }

    /**
     * 事前処理を実装します。バッチ起動前に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     * @return 画面が使用するＤＴＯ
     */
    public BatchExecuteDto preProcessing(BatchExecuteDto batchExecuteDto) {
        MssSuerveyBatchDto dto = (MssSuerveyBatchDto) batchExecuteDto;
        
        return dto;
    }

    /**
     * 正常終了時の事後処理を実装します。バッチ起動後に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public void postProcessing(BatchExecuteDto batchExecuteDto) {
        MssSuerveyBatchDto dto = (MssSuerveyBatchDto) batchExecuteDto;
        dto.setBatchDur(dto.getShoriKbn());
        dto.setSuccessMsg("登録処理を起動しました");
        dto.setPageKey("BSM012V01");
        
    }

    /**
     * 異常終了時の事後処理を実装します。バッチ起動後に呼び出されます。
     * dtoがnullの場合は処理をせずreturnを返す
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public void errProcessing(BatchExecuteDto batchExecuteDto) {
        if(batchExecuteDto == null){
            return;
        }
        MssSuerveyBatchDto dto = (MssSuerveyBatchDto) batchExecuteDto;
        dto.setPageKey("BSM012V01");
        
        //取り込みと支部洗換えの結合ステータス取得
        UIMssBatch uIMssBatch = getUIMssBatchDao().selectJobName(PGM_ID,"01");        
        String statKbn = uIMssBatch.getStatKbn().trim();
        
        dto.setSuccessMsg("エラーが発生しました。解除を行ってから再度実行して下さい");
        dto.setShoriKbn(statKbn);
    }
    
   /** 遷移先を取得します。
    * @param batchExecuteDto 画面が使用するＤＴＯ
    */
    public String getPageKey(BatchExecuteDto batchExecuteDto) {
        return "BSM012V01";
    }

public UIMssBatchDao getUIMssBatchDao() {
    return UIMssBatchDao;
}

public void setUIMssBatchDao(UIMssBatchDao UIMssBatchDao) {
    this.UIMssBatchDao = UIMssBatchDao;
}



}