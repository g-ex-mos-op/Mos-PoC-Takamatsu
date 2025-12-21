package jp.co.isid.mos.bird.bizreport.campsuiiref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.ChangeCampLogic;
import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.dao.CodBlockDao;
import jp.co.isid.mos.bird.bizreport.common.entity.CodBlock;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.CompanyListLogic;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * 条件項目の取得設定
 * @author xnkusama
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR012L03";

    /* DAO */
    private CodBlockDao codBlockDao;
    private CodHyojiTaishoDao commonCampaignCodHyojiTaishoDao;
    
    /* LOGIC */
    private CompanyListLogic companyListLogic;
    private ChangeCampLogic campsuiirefChangeCampLogic;
    
    
    /**
     * 条件項目の取得設定
     * @param requestDto
     * @param sessionDto
     */
    public void execute(RequestSuiiDto requestDto) {
        SessionSuiiDto sessionDto = (SessionSuiiDto) requestDto.getSelfSessionDto();

        // １．事前条件処理
        validate(requestDto, sessionDto);
        // ２．会社 情報格納処理
        doSetCompanyInfo(requestDto, sessionDto);
        // ３．キャンペーン対象期間マスタ情報格納処理
        getCampsuiirefChangeCampLogic().execute(requestDto);
        // ４．ブロック情報設定
        makeBlockList(requestDto);
    }

    /**
     * ３．会社 情報格納処理
     */
    private void doSetCompanyInfo(RequestSuiiDto requestDto, SessionSuiiDto sessionDto) {
        // ３－１．業績管理共通ロジック【会社情報 データ検索ロジック】実行。戻り値List[[会社情報]]を取得する。
        List listCompany = getCompanyListLogic().execute(null);
        if (listCompany == null || listCompany.isEmpty()) {
            throw new NotExistException("会社データ");
        }
        // ３－２．DTO【Session】．会社へList[[会社情報]]を設定する。
        sessionDto.setListCompany(listCompany);
        if (CommonUtil.isNull(requestDto.getCompanyCd())) {
            // ３－３．DTO【Request】．会社コードへList[[会社情報]]のインデックス　0（ゼロ）の会社コードを設定する。
            requestDto.setCompanyCd(((CodCompany) listCompany.get(0)).getCompanyCd());
        }
        else {
            // ３－４．パラメータ．会社コードが入力されている場合は、下記の処理を行う。
            requestDto.setCompanyCd(requestDto.getCompanyCd());
        }
    }
    
    /**
     * ブロックコードプルダウン用リスト作成
     * @param requestDto
     */
    private void makeBlockList(RequestSuiiDto requestDto) {
        SessionSuiiDto sessionDto = (SessionSuiiDto) requestDto.getSelfSessionDto();
        // ブロックテーブルから一覧取得
        List listBlock = null;
        listBlock = getCodBlockDao().select();
        // 先頭に「全ブロック」を追加
        CodBlock codBlock = new CodBlock();
        codBlock.setBlockCd("");
        codBlock.setBlockName("全ブロック");
        listBlock.add(0, codBlock);
        
        sessionDto.setListBlock(listBlock);
    }
    /**
     * 事前条件処理
     * @param requestDto
     * @param sessionDto
     */
    private void validate(RequestSuiiDto requestDto, SessionSuiiDto sessionDto) {
        if (requestDto == null) {
            throw new MissingDataException("Request用DTOデータ");
        }
        if (sessionDto == null) {
            throw new MissingDataException("Session用DTOデータ");
        }
    }

    public CompanyListLogic getCompanyListLogic() {
        return companyListLogic;
    }

    public void setCompanyListLogic(CompanyListLogic companyListLogic) {
        this.companyListLogic = companyListLogic;
    }

    public ChangeCampLogic getCampsuiirefChangeCampLogic() {
        return campsuiirefChangeCampLogic;
    }

    public void setCampsuiirefChangeCampLogic(
            ChangeCampLogic campsuiirefChangeCampLogic) {
        this.campsuiirefChangeCampLogic = campsuiirefChangeCampLogic;
    }

    public CodBlockDao getCodBlockDao() {
        return codBlockDao;
    }

    public void setCodBlockDao(CodBlockDao codBlockDao) {
        this.codBlockDao = codBlockDao;
    }

    public CodHyojiTaishoDao getCommonCampaignCodHyojiTaishoDao() {
        return commonCampaignCodHyojiTaishoDao;
    }

    public void setCommonCampaignCodHyojiTaishoDao(
            CodHyojiTaishoDao commonCampaignCodHyojiTaishoDao) {
        this.commonCampaignCodHyojiTaishoDao = commonCampaignCodHyojiTaishoDao;
    }

}
