/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.jikanbeturef.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.ConditionLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 条件項目の取得設定ロジック
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_CONDITION;
    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;
    /** LOGIC【条件項目会社情報設定】*/
    private SetCompanyLogic jikanBetuRefSetCompanyLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto)
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto) {
		//１．事前条件処理
		validate(sessionDto, requestDto);
		//２．会社コードプルダウンリスト設定
		List listCompany = getCodCompanyDao().select(sessionDto.getBirdUserInfo().getUserID());
		sessionDto.setListCompany(listCompany);
		CodCompany com = (CodCompany)(sessionDto.getListCompany().get(0));
		requestDto.setCompanyCd(com.getCompanyCd());
		//３．会社変更ロジック実行
		getJikanBetuRefSetCompanyLogic().execute(sessionDto, requestDto);
        //４．条件項目のプルダウンリストList[[対象期間]]を作成します。
		List listTaishoKikan = TaishoKikan.getUIListPullDownList();
        //５．処理４のプルダウンリストList[[対象期間]]のインデックス0の[対象期間].List[[リストデータ]]へ
		//    システム日付の当月含めて過去14ヶ月分のyyyyMMのリストを設定します。
        //６．DTO【自画面Session】.List[[対象期間]]へ処理４のプルダウンリストList[[対象期間]]を設定します。
        //５．処理４のプルダウンリストList[[対象期間]]の各インデックスの[対象期間].List[[リストデータ]]へ日付の値を設定します。
        for(int i=0; i<listTaishoKikan.size(); i++) {
            UILists taishoKikan = (UILists)listTaishoKikan.get(i);
            if(TaishoKikan.MONTH.equals(taishoKikan.getKey())) {
                //月次．システム日付の当月含めて過去14ヶ月分のyyyyMMのリストを設定します。
                taishoKikan.setListData(JikanBetuRefUtil.creatListNengetu(sessionDto.getBirdDateInfo().getAppDate(), 14));
                continue;
            }
            if(TaishoKikan.KIKAN.equals(taishoKikan.getKey())) {
                //期間指定．営業日(アプリ日付)から、営業日(アプリ日付)の年月から12ヶ月前の年月の1日からの分を
                //yyyyMMddの型でリストに設定します。
                taishoKikan.setListData(JikanBetuRefUtil.creatListNengappi(sessionDto.getBirdDateInfo().getAppDate()));
                continue;
            }
        }
		sessionDto.setListTaishoKikan(listTaishoKikan);
		requestDto.setListTaishoKikan(listTaishoKikan);
        
        //７．DTO【自画面Request】.対象期間へデフォルト値として『月別』の値を設定します。
		requestDto.setTaishoKikan(TaishoKikan.MONTH);
        //８．DTO【自画面Request】.期間指定へデフォルト値として今月の値を設定します。
        int defaultTaishoKikanIndex = TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())? 0:1;
        UILists taishoKikan = ((UILists)listTaishoKikan.get(defaultTaishoKikanIndex));
        SelectItem sitem = (SelectItem)(taishoKikan.getListData().get(TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())? 0:1));
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
	 * @return jikanBetuRefSetCompanyLogic を戻します。
	 */
	public SetCompanyLogic getJikanBetuRefSetCompanyLogic() {
		return jikanBetuRefSetCompanyLogic;
	}
	/**
	 * @param logic を クラス変数jikanBetuRefSetCompanyLogicへ設定します。
	 */
	public void setJikanBetuRefSetCompanyLogic(SetCompanyLogic logic) {
		this.jikanBetuRefSetCompanyLogic = logic;
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
