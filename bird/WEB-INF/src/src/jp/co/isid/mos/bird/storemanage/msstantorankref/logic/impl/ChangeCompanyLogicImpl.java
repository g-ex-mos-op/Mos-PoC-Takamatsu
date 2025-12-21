package jp.co.isid.mos.bird.storemanage.msstantorankref.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.msstantorankref.action.MssTantotenRankingRefAction;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dao.CodKaiDao;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dao.CodNendoDao;
import jp.co.isid.mos.bird.storemanage.msstantorankref.dto.MssTantotenRankingRefDto;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.CodKai;
import jp.co.isid.mos.bird.storemanage.msstantorankref.entity.CodNendo;
import jp.co.isid.mos.bird.storemanage.msstantorankref.logic.ChangeCompanyLogic;

/**
 * ミステリーショッパーズ 担当店ランキング
 * 会社コード変更時ロジック
 * 
 * @author xkinu
 */
public class ChangeCompanyLogicImpl implements ChangeCompanyLogic {
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssTantotenRankingRefAction.SCREEN_ID+"L02";
    
    /*【DAO】年度情報 */
    private CodNendoDao codNendoDao;
    /*【DAO】回情報 */
    private CodKaiDao codKaiDao;
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
        if (dto.getCompanyCd() == null) {
            throw new NotNullException("会社", "companyCd", 0);
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
         //年度 情報格納処理
        settingNendo(dto);
        //回数 情報格納処理
        settingKai(dto);
        
        if((dto.getNendoList() == null || dto.getNendoListSize() == 0)
                || (dto.getKaiList() == null || dto.getKaiListSize() == 0)) {
            throw new NoResultException();
        }
        //検索データを戻す。
        return null;
    }
    /**
     * 年度プルダウン情報DTO格納処理
     * 
     * DAO【支部リスト検索】実行
     * パラメーター：下記の値を保持したMapを引数とする。
     *               「companyCd」会社コード
     * @param dto
     * @throws Exception
     */
    private List settingNendo(MssTantotenRankingRefDto dto) throws Exception {
        List list = getMssTantotenRankingCodNendoDao().select(
                  dto.getUserId()
                , dto.getUsertypeCd()
                , dto.isLimit()
                , dto.getCompanyCd());
        dto.setNendoList(list);
        if(list != null && list.size() > 00){
            // 年度コード初期値
            String initCd = ((CodNendo)(list.get(0))).getNendo();
            // 年度コード設定
            dto.setNendo(initCd);
        }
        return list;
    }
    /**
     * 回数プルダウン情報DTO格納処理
     * 
     * DAO【支部リスト検索】実行
     * パラメーター：下記の値を保持したMapを引数とする。
     *               「companyCd」会社コード
     * @param dto
     * @throws Exception
     */
    private List settingKai(MssTantotenRankingRefDto dto) throws Exception {
        List list = getMssTantotenRankingCodKaiDao().select(
                  dto.getUserId()
                , dto.getUsertypeCd()
                , dto.isLimit()
                , dto.getCompanyCd());
        dto.setKaiList(list);
        if(list != null && list.size() > 00){
            // 回数コード初期値
            String initCd = ((CodKai)(list.get(0))).getKai();
            // 回数コード設定
            dto.setKai(initCd);
        }
        return list;
    }
    /**
     * 年度検索DAO取得処理
     * @return codNendoDao を戻します。
     */
    public CodNendoDao getMssTantotenRankingCodNendoDao() {
        return codNendoDao;
    }
    /**
     * 年度検索DAO設定処理
     * @param codNendoDao を設定。
     */
    public void setMssTantotenRankingCodNendoDao(CodNendoDao dao) {
        this.codNendoDao = dao;
    }
    /**
     * 回数検索DAO取得処理
     * @return codKaiDao を戻します。
     */
    public CodKaiDao getMssTantotenRankingCodKaiDao() {
        return codKaiDao;
    }
    /**
     * 回数検索DAO設定処理
     * @param codKaiDao を設定。
     */
    public void setMssTantotenRankingCodKaiDao(CodKaiDao dao) {
        this.codKaiDao = dao;
    }
    
}
