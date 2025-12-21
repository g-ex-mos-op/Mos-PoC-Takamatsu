package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.action.MssOnerPointRankRefAction;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.CodKaiDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.CodNendoDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dao.CodSibuDao;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.dto.MssOnerPointRankCsvDto;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodKai;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodNendo;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodSibu;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.logic.ChangeCompanyLogic;

/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 会社コード変更時ロジック
 * 
 * @author xkinu
 */
public class ChangeCompanyLogicImpl implements ChangeCompanyLogic {
    
    /** ロジックID定義 */
    public static final String LOGIC_ID = MssOnerPointRankRefAction.SCREEN_ID+"L02";
    /*【DAO】支部コード*/
    private CodSibuDao codSobiDao;
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
    private void validate(MssOnerPointRankCsvDto dto) throws Exception {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("ミステリーショッパーズ オーナー別獲得ポイントランク 画面DTO");
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
    public Map execute(MssOnerPointRankCsvDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        //支部 情報格納処理
        settingSibu(dto);
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
     * 支部プルダウン情報DTO格納処理
     * 
     * DAO【支部リスト検索】実行
     * パラメーター：下記の値を保持したMapを引数とする。
     *               「companyCd」会社コード
     * @param dto
     * @throws Exception
     */
    private List settingSibu(MssOnerPointRankCsvDto dto) throws Exception {
        List list = getMssOnerPointRankCodSibuDao().select(dto.getUserId()
                , dto.getUsertypeCd()
                , dto.isLimit()
                , dto.getCompanyCd());
        if(list == null || list.size() == 0){
            throw new NoResultException();
        }
        dto.setSibuList(list);
        // 支部コード初期値
        String initCd = ((CodSibu)(list.get(0))).getSibuCd();
        // 支部コード設定
        dto.setSibuCd(initCd);
        // 支部名称設定
        dto.setSibuName(dto.getSibuName(initCd));
        return list;
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
    private List settingNendo(MssOnerPointRankCsvDto dto) throws Exception {
        List list = getMssOnerPointRankCodNendoDao().select(
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
    private List settingKai(MssOnerPointRankCsvDto dto) throws Exception {
        List list = getMssOnerPointRankCodKaiDao().select(dto.getUserId()
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
     * 支部検索DAO取得処理
     * @return codSobiDao を戻します。
     */
    public CodSibuDao getMssOnerPointRankCodSibuDao() {
        return codSobiDao;
    }
    /**
     * 支部検索DAO設定処理
     * @param codSobiDao を設定。
     */
    public void setMssOnerPointRankCodSibuDao(CodSibuDao dao) {
        this.codSobiDao = dao;
    }
    /**
     * 年度検索DAO取得処理
     * @return codNendoDao を戻します。
     */
    public CodNendoDao getMssOnerPointRankCodNendoDao() {
        return codNendoDao;
    }
    /**
     * 年度検索DAO設定処理
     * @param codNendoDao を設定。
     */
    public void setMssOnerPointRankCodNendoDao(CodNendoDao dao) {
        this.codNendoDao = dao;
    }
    /**
     * 回数検索DAO取得処理
     * @return codKaiDao を戻します。
     */
    public CodKaiDao getMssOnerPointRankCodKaiDao() {
        return codKaiDao;
    }
    /**
     * 回数検索DAO設定処理
     * @param codKaiDao を設定。
     */
    public void setMssOnerPointRankCodKaiDao(CodKaiDao dao) {
        this.codKaiDao = dao;
    }
}
