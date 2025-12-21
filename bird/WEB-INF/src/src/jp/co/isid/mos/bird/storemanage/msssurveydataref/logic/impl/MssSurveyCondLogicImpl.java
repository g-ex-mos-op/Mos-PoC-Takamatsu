package jp.co.isid.mos.bird.storemanage.msssurveydataref.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.BirdMessageFormatter;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dao.CodCompanyDao;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dao.UIMssBatchDao;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssBatchErrDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyBatchDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyDataRefDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.UIMssBatch;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.logic.MssSurveyCondLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class MssSurveyCondLogicImpl implements MssSurveyCondLogic{
    private static final String PGM_ID_TORI = "BMSPTORI";
    private static final String PGM_ID_SIBU = "BMSPSIBU";
    private static final String[] PGM_ID = {PGM_ID_TORI, PGM_ID_SIBU};
    private UIMssBatchDao uIMssBatchDao;
    private CodCompanyDao mssSurveyCodCompanyDao;
    private static final String STAT_OK = "0";
    public static final String LOGIC_ID    = "BSM012L01";

    /**
     * 条件画面出力データ検索と検索結果のDTOへの設定を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param MssSuerveyDataRefDto dto 画面データ保持クラス
     * @exception Exception
     */
    public String execute(MssSuerveyDataRefDto dto) throws ApplicationException{
        //事前条件判断処理実行
        validate(dto);
        //会社 情報格納処理
        settingCompany(dto);
        /* ロジック【会社コード変更時ロジック】実行 */
        
        //検索データを戻す。
        return null;
    }
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws Exception
     */
    private void validate(MssSuerveyDataRefDto dto) throws ApplicationException {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("ミステリーショッパーズ サーベイデータ登録 画面DTO");
        }
    }
    public String batchStatus(MssSuerveyBatchDto dto){

        UIMssBatch uIMssBatch = getUIMssBatchDao().selectJobName(PGM_ID,"01"); 
        
        String statKbn = uIMssBatch.getStatKbn().trim();
        String errCd  = uIMssBatch.getErrCd().trim();
        //ステータス区分をそのまま処理(画面用ステータス）フラグへ設定
        dto.setShoriFlg(statKbn);
        if("9".equals(statKbn.trim())){
            S2Container container = SingletonS2ContainerFactory.getContainer();
            List listEntry = (List) container.getComponent("storemangeMssErrList");
            if(!"".equals(errCd.trim())){
                for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
                    MssBatchErrDto errEntity = (MssBatchErrDto) ite.next();
    
                    if (errCd.equals(errEntity.getErrCd())) {
                        String [] errMsg = {"msg1","msg2"};
                        errMsg[0] = errEntity.getErrMsg1();
                        errMsg[1] = errEntity.getErrMsg2();
                        dto.setErrMsg(BirdMessageFormatter.getMessage(errEntity.getBErrCd(),errMsg));
                        dto.setErrTitle(errEntity.getTitle());
                        break;
                    }
                }
            }else{
                dto.setErrTitle("エラー");
                String errTitile = "";
                if(dto.getShoriKbn().equals("0")){
                    errTitile = "「取込み」";
                }else if(dto.getShoriKbn().equals("1")){
                    errTitile = "「支部洗え替え」";
                }
                dto.setErrMsg(errTitile + "でエラーが発生しました。");
            }
        }

        return null;
    }
    /**
     * 会社プルダウン情報DTO格納処理
     * 
     * Dao【会社情報】実行
     * パラメーター：下記の値を保持したMapを引数とする。
     *               「userId」ユーザータイプコード
     * @param dto
     * @throws Exception
     */
    private void settingCompany(MssSuerveyDataRefDto dto) throws ApplicationException {
        //ユーザーID
        String userId = dto.getUserId();
        // Dao【会社情報】実行
        List list = getMssSurveyCodCompanyDao().select(userId);
        if (list == null || list.size() == 0) {
            throw new NotExistException("会社情報");
        }
        String initCompanyCd = ((CodCompany)(list.get(0))).getCompanyCd();
        // 会社コードリスト設定
        dto.setCompanyList(list);
        // 会社コード設定
        dto.setCompanyCd(initCompanyCd);
        // 会社名称設定
        dto.setCompanyName(dto.getCompanyName(initCompanyCd));
    }
    public String batchClearErr(){
        getUIMssBatchDao().updateMssBatch(PGM_ID_TORI, "01",STAT_OK,"");
        getUIMssBatchDao().updateMssBatch(PGM_ID_SIBU, "01",STAT_OK,"");
        return null;
    }

    public UIMssBatchDao getUIMssBatchDao() {
        return uIMssBatchDao;
    }

    public void setUIMssBatchDao(UIMssBatchDao mssBatchDao) {
        uIMssBatchDao = mssBatchDao;
    }
    public CodCompanyDao getMssSurveyCodCompanyDao() {
        return mssSurveyCodCompanyDao;
    }
    public void setMssSurveyCodCompanyDao(CodCompanyDao mssSurveyCodCompanyDao) {
        this.mssSurveyCodCompanyDao = mssSurveyCodCompanyDao;
    }


}
