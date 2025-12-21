package jp.co.isid.mos.bird.storemanage.msschousadataref.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dao.CodCompanyDao;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dao.MssMstUserMiseDao;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.ConditionLogic;

/**
 * ミステリーショッパーズ 店舗別評価
 * 条件画面出力データ検索ロジック
 * 
 * @author inazawa
 */
public class ConditionLogicImpl implements ConditionLogic {
    public static final String LOGIC_ID =    "BSM011L01";
    /*【DAO】会社コード*/
    private CodCompanyDao mssChousaCodCompanyNameDao;
    private MssMstUserMiseDao mssMstUserMiseDao;

    

    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws Exception
     */
    private void validate(MssChousaDataRefDto dto) throws Exception {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("ミステリーショッパーズ 店舗別評価 画面DTO");
        }
    }
    
    /**
     * 条件画面出力データ検索と検索結果のDTOへの設定を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param BizReportRefDto dto 画面データ保持クラス
     * @exception Exception
     */
    public Map execute(MssChousaDataRefDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        //会社 情報格納処理
        settingCompany(dto);
        /* ロジック【会社コード変更時ロジック】実行 */
        
        //検索データを戻す。
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
    private void settingCompany(MssChousaDataRefDto dto) throws Exception {
        //ユーザーID
        String userId = dto.getUserId();
        // Dao【会社情報】実行
        List list = getMssChousaCodCompanyNameDao().select(userId);
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



    public CodCompanyDao getMssChousaCodCompanyNameDao() {
        return mssChousaCodCompanyNameDao;
    }

    public void setMssChousaCodCompanyNameDao(CodCompanyDao mssChousaCodCompanyNameDao) {
        this.mssChousaCodCompanyNameDao = mssChousaCodCompanyNameDao;
    }

    public MssMstUserMiseDao getMssMstUserMiseDao() {
        return mssMstUserMiseDao;
    }

    public void setMssMstUserMiseDao(MssMstUserMiseDao mssMstUserMiseDao) {
        this.mssMstUserMiseDao = mssMstUserMiseDao;
    }


    
}
