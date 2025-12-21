/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.MstCompanySibuTorikomiDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.GetCompanySibuLogic;

/**
 * 公開対象支部選択情報保持リスト取得設定ロジック
 * @author xytamura
 */
public class GetCompanySibuLogicImpl implements GetCompanySibuLogic {
	/**ロジックID：*/
    public static final String LOGIC_ID = "BCO002L06";
    /**
     * 管理会社情報Dao
     */
    private CodCompanyDao publicTargetCodCompanyDao;
    /**
     * 支部取込情報Dao
     */
    private MstCompanySibuTorikomiDao mstCompanySibuTorikomiDao;

    /**
     * 公開対象支部(支部取込)選択情報保持リストの
     * 取得設定処理を行います。
     * 
     * @param conditionDto
     */
    public void execute(PublicTargetSearchConditionDto conditionDto) {
    	//DAO【保有店管理会社】検索を実行します。
    	conditionDto.setListCompanyCd(getPublicTargetCodCompanyDao().select());
    	Map mapCompanysSibu = new HashMap();
    	List listCompanysSibu = new ArrayList(0);
        List company = conditionDto.getListCompanyCd();
        for (int i = 0; i < company.size(); i++) {
        	//for-1.現行の会社コードを取得します。
            CodCompany ctlCompany = (CodCompany) company.get(i);
            String comapnyCd = ctlCompany.getCompanyCd();
            //for-2.処理for-1の会社コードをパラメータとして、
            //      DAO【支部取込情報】会社指定検索処理を実行し、List[[支部取込情報]]を取得します。
            List listAreaDai = getMstCompanySibuTorikomiDao().getSibuFromCompany(comapnyCd);
            //for-3.DTO【選択情報保持】.Map[会社別支部取込保持]へ
            //      処理for-2の戻り値List[[支部取込情報]]を会社コードをキーに格納します。
            mapCompanysSibu.put(comapnyCd,listAreaDai);
            listCompanysSibu.add(listAreaDai);
        }
        conditionDto.setMapCompanysSibu(mapCompanysSibu);
        conditionDto.setListCompanysSibu(listCompanysSibu);
    }

    /**
     * 支部取込情報Daoを設定します。
     * @param 支部取込情報Dao
     */
    public void setMstCompanySibuTorikomiDao(
            MstCompanySibuTorikomiDao mstCompanySibuTorikomiDao) {
        this.mstCompanySibuTorikomiDao = mstCompanySibuTorikomiDao;
    }
    
    /**
     * 支部取込情報Daoを取得します。
     * @return　支部取込情報Dao
     */
    public MstCompanySibuTorikomiDao getMstCompanySibuTorikomiDao() {
        return mstCompanySibuTorikomiDao;
    }

	/**
	 * @return クラス変数publicTargetCodCompanyDao を戻します。
	 */
	public CodCompanyDao getPublicTargetCodCompanyDao() {
		return publicTargetCodCompanyDao;
	}

	/**
	 * @param publicTargetCodCompanyDao を クラス変数publicTargetCodCompanyDaoへ設定します。
	 */
	public void setPublicTargetCodCompanyDao(CodCompanyDao publicTargetCodCompanyDao) {
		this.publicTargetCodCompanyDao = publicTargetCodCompanyDao;
	}


}