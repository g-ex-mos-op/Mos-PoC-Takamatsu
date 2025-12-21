package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserCompanyLogic;

/**
 * 業務支援共通ユーザー所属会社取得
 * @author inazawa
 */
public class GetUserCompanyLogicImpl implements GetUserCompanyLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = "BBS001L08";
    
    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;
    
    /**
     * 条件画面出力データ検索と検索結果のDTOへの設定を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param BizReportRefDto dto 画面データ保持クラス
     * @exception Exception
     */
    public List execute(String userId){
        //会社 情報格納処理
        return settingCompany(userId);
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
    private List settingCompany(String userId){
        // Dao【会社情報】実行
        
        List list = getCodCompanyDao().select(userId);
        return list;
    }
    /**
     * codCompanyDaoを取得
     * @return codCompanyDao
     */
    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }
    /**
     * codCompanyDaoを設定
     * @param codCompanyDao
     */
    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }
}
