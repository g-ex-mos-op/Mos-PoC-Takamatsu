/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.ConditionLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SetCompanyLogic;
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
    public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_CONDITION;
    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;
    /** LOGIC【条件項目会社情報設定】*/
    private SetCompanyLogic menuBetuRefSetCompanyLogic;

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
		getMenuBetuRefSetCompanyLogic().execute(sessionDto, requestDto);
        //４．条件項目のプルダウンリストList[[対象期間]]を作成します。
		List listTaishoKikan = TaishoKikan.getUIListPullDownList();
		String appDate = sessionDto.getBirdDateInfo().getAppDate();
		//５．処理４のプルダウンリストList[[対象期間]]の各インデックスの[対象期間].List[[リストデータ]]へ日付の値を設定します。
		for(int i=0; i<listTaishoKikan.size(); i++) {
			UILists taishoKikan = (UILists)listTaishoKikan.get(i);
			if(TaishoKikan.MONTH.equals(taishoKikan.getKey())) {
		        //月次．システム日付の当月含めて過去14ヶ月分のyyyyMMのリストを設定します。
				taishoKikan.setListData(MenuBetuRefUtil.creatListNengetu(appDate, 14));
				continue;
			}
			if(TaishoKikan.DAY.equals(taishoKikan.getKey())) {
		        //期日指定．営業日(アプリ日付)から、営業日(アプリ日付)の年月から12ヶ月前の年月の1日からの分を
				//yyyyMMddの型でリストに設定します。
				taishoKikan.setListData(MenuBetuRefUtil.creatListNengappi(appDate, 12));
				continue;
			}
			if(TaishoKikan.KIKAN.equals(taishoKikan.getKey())) {
		        //期間指定．営業日(アプリ日付)から、営業日(アプリ日付)の年月以前の25ヶ月前の年月の1日からの分を
				//yyyyMMddの型でリストに設定します。
				taishoKikan.setListData(MenuBetuRefUtil.creatListNengappi(appDate, 25));
				continue;
			}
		}
        //６．DTO【自画面Session】.List[[対象期間]]へ処理４のプルダウンリストList[[対象期間]]を設定します。
		sessionDto.setListTaishoKikan(listTaishoKikan);
		requestDto.setListTaishoKikan(listTaishoKikan);
        //７．DTO【自画面Request】.対象期間へデフォルト値として『期日指定』の値を設定します。
		requestDto.setTaishoKikan(TaishoKikan.DAY);
        //８．DTO【自画面Request】.期間指定へデフォルト値として処理７で設定したデフォルト対象期間別に、
		//    前月or前日の値を設定します。
		int defaultTaishoKikanIndex = TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())? 0:1;
		UILists taishoKikan = ((UILists)listTaishoKikan.get(defaultTaishoKikanIndex));
        SelectItem sitem = (SelectItem)(taishoKikan.getListData().get(TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())? 1:0));
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
	 * @return menuBetuRefSetCompanyLogic を戻します。
	 */
	public SetCompanyLogic getMenuBetuRefSetCompanyLogic() {
		return menuBetuRefSetCompanyLogic;
	}
	/**
	 * @param logic を クラス変数menuBetuRefSetCompanyLogicへ設定します。
	 */
	public void setMenuBetuRefSetCompanyLogic(SetCompanyLogic logic) {
		this.menuBetuRefSetCompanyLogic = logic;
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
