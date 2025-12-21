/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.logic.impl;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.rankref.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.rankref.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.rankref.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.rankref.logic.ConditionLogic;
import jp.co.isid.mos.bird.analysis.rankref.util.RankRefUtil;
import jp.co.isid.mos.bird.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 条件項目の取得設定ロジック
 * 
 * 作成日:2008/10/21
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = RankRefUtil.LOGIC_ID_CONDITION;
    /*DAO【管理会社情報取得】*/
    private CodCompanyDao codCompanyDao;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto)
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto) {
		//１．事前条件処理
		validate(sessionDto, requestDto);
		//２．BIRD共通DAO【管理会社情報取得】.検索を実行し、戻り値List[[会社コード]]を取得します。
		List listCompany = getCodCompanyDao().select(sessionDto.getBirdUserInfo().getUserID());
		//２－１．DTO【自画面Session】.List[[対象期間]]へ処理２のList[[会社コード]]を設定します。
		sessionDto.setListCompany(listCompany);
		//３．デフォルト値としてインデックス0番目の会社コードを設定します。
		CodCompany com = (CodCompany)(sessionDto.getListCompany().get(0));
		requestDto.setCompanyCd(com.getCompanyCd());
        //４．条件項目のプルダウンリストList[[期間指定]]を作成します。
		List listKikanSitei = TaishoKikan.getUIListPullDownList();
        //４－１．処理４のプルダウンリストList[[期間指定]]のインデックス0の[期間指定].List[[リストデータ]]へ
		//    アプリ日付の当月含めて過去36ヶ月分のyyyyMMのリストを設定します。
		((UILists)listKikanSitei.get(0)).setListData(RankRefUtil.creatListNengetu(sessionDto.getBirdDateInfo().getAppDate(), 36));
        //４－２．処理４のプルダウンリストList[[期間指定]]のインデックス0の[期間指定].List[[リストデータ]]へ
		//    アプリ日付の当月含めて過去36ヶ月分のyyyyMMのリストを設定します。
		((UILists)listKikanSitei.get(1)).setListData(RankRefUtil.creatListNendo(sessionDto.getBirdDateInfo().getAppDate(), 3));
        //５．DTO【自画面Session】.List[[対象期間]]へ処理４のプルダウンリストList[[対象期間]]を設定します。
		sessionDto.setListKikanSitei(listKikanSitei);
        //６．DTO【自画面Request】.List[[対象期間]]へ処理４のプルダウンリストList[[対象期間]]を設定します。
		requestDto.setListKikanSitei(listKikanSitei);
        //７．DTO【自画面Request】.対象期間へデフォルト値として『月別』の値を設定します。
		requestDto.setTaishoKikan(TaishoKikan.MONTH);
        //８．DTO【自画面Request】.期間指定へデフォルト値としてto の値を設定します。
		UILists taishoKikanMonth = ((UILists)listKikanSitei.get(0));
        SelectItem sitem = (SelectItem)(taishoKikanMonth.getListData().get(0));
        requestDto.setKikanSitei((String)sitem.getValue());
 	}
    /**
     * 事前条件処理
     * 
     * @param sessionDto
     * @param requestDto
     */
    private void validate(SessionDto sessionDto, RequestDto requestDto)
    {
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        if (sessionDto.getBirdDateInfo() == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        if (sessionDto.getBirdUserInfo() == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
    }
	/**
	 * @return codCompanyDao を戻します。
	 */
	public CodCompanyDao getCodCompanyDao() {
		return codCompanyDao;
	}
	/**
	 * @param codCompanyDao を クラス変数codCompanyDaoへ設定します。
	 */
	public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
		this.codCompanyDao = codCompanyDao;
	}
}
