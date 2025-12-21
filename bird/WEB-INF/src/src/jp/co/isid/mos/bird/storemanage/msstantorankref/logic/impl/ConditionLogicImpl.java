package jp.co.isid.mos.bird.storemanage.msstantorankref.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.msstantorankref.action.MssTantotenRankingRefAction;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dao.CodCompanyDao;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dto.MssTantotenRankingRefDto;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.ChangeCompanyLogic;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.ConditionLogic;

/**
 * ミステリーショッパーズ 担当店ランキング
 * 条件画面出力データ検索ロジック
 * 
 * @author xkinu
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssTantotenRankingRefAction.SCREEN_ID+"L01";
    /*【LOGIC】会社コード変更時ロジック */
    private ChangeCompanyLogic changeCompanyLogic;
    
    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws Exception
     */
    private void validate(MssTantotenRankingRefDto dto) throws Exception {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("ミステリーショッパーズ 担当店ランキング 画面DTO");
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
    public Map execute(MssTantotenRankingRefDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        //会社 情報格納処理
        settingCompany(dto);
        /* ロジック【会社コード変更時ロジック】実行 */
        getMssTantotenRankingChangeCompanyLogic().execute(dto);
        
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
    private void settingCompany(MssTantotenRankingRefDto dto) throws Exception {
        //ユーザーID
        String userId = dto.getUserId();
        // Dao【会社情報】実行
        List list = getMssTantotenRankingCodCompanyDao().select(userId);
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
    /**
     * 会社コード変更時ロジック取得処理
     * @return changeCompanyLogic を戻します。
     */
    public ChangeCompanyLogic getMssTantotenRankingChangeCompanyLogic() {
        return changeCompanyLogic;
    }
    /**
     * 会社コード変更時ロジック設定処理
     * @param changeCompanyLogic を設定。
     */
    public void setMssTantotenRankingChangeCompanyLogic(ChangeCompanyLogic logic) {
        this.changeCompanyLogic = logic;
    }
    /**
     * 会社検索DAO取得処理
     * @return codCompanyDao を戻します。
     */
    public CodCompanyDao getMssTantotenRankingCodCompanyDao() {
        return codCompanyDao;
    }
    /**
     * 会社検索DAO設定処理
     * @param codCompanyDao を設定。
     */
    public void setMssTantotenRankingCodCompanyDao(CodCompanyDao dao) {
        this.codCompanyDao = dao;
    }
}
