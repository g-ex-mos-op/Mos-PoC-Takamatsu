/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.SessionDto;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 会社条件項目情報設定ロジック
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class SetCompanyLogicImpl implements SetCompanyLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_SET_COMPANY;
	/** LOGIC【表示対象取得】*/
	private GetHyojiTaishoLogic getHyojiTaishoLogic;
	/*
	 *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.logic.SetCompanyLogic#execute(jp.co.isid.mos.bird.analysis.menubeturef.dto.SessionDto, jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto)
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto) {
		//１．事前条件処理
		validate(sessionDto, requestDto);
		//２．「全社(全店)」「支部」「個店(店舗)」対象条件プルダウンリスト情報を作成します。
		String[] codes = new String[]{TaishoJoken.CODE_ALL,TaishoJoken.CODE_SIBU,TaishoJoken.CODE_MISE};
		List listTaishoJoken =TaishoJoken.getPullDownList(sessionDto.getUserTypeCd()
		, requestDto.getCompanyCd(), sessionDto.getBirdUserInfo().isLimit(),codes);
		//３．DTO【自画面Session】.List[[対象条件]]へ対象条件プルダウンリスト情報を設定します。
		sessionDto.setListTaishoJoken(listTaishoJoken);
    	//４.DTO【自画面Session】.List[[対象条件]]の件数分下記の処理を行います。
    	for (int i=0; i<listTaishoJoken.size(); i++) {
    		//for-1.現行インデックスの[対象条件]から集計区分を取得します。
    		UILists taishoJokenData = (UILists)listTaishoJoken.get(i);
    		String taishoJoken = taishoJokenData.getKey();
    		if(UserType.HONBU.equals(sessionDto.getUserTypeCd()) && TaishoJoken.CODE_MISE.equals(taishoJoken)) {
    			continue;
    		}
    		//for-2. ロジック【表示対象データ検索ロジック】実行し、List[[表示対象]]を取得する。
    		Map params = new HashMap();
    		params.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, sessionDto.getBirdDateInfo().getAppDate());
    		params.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, requestDto.getCompanyCd());
    		params.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, taishoJoken);
	        Map logigMap = getGetHyojiTaishoLogic().execute(params);
	        List listHyojiTaisho = (List)logigMap.get(GetHyojiTaishoLogicImpl.RK_LIST);
       		if (listHyojiTaisho != null && listHyojiTaisho.size() > 0) {
		        //DTO【自画面Session】へ対象条件コードをキーにList[[表示対象]]を格納する。
       			taishoJokenData.setListData(listHyojiTaisho);
                if (TaishoJoken.CODE_SIBU.equals(taishoJoken)) {
                    // 支部の場合はブロック情報を格納する
                    if(UserType.HONBU.equals(sessionDto.getUserTypeCd()) && logigMap.get("listBlock") != null) {
                        requestDto.setBlockList((List)logigMap.get("listBlock"));
                        sessionDto.setBlockList((List)logigMap.get("listBlock"));
                    }
                }
	        }
    	}
    	requestDto.setListTaishoJoken(listTaishoJoken);
        //５．DTO【自画面Request】.対象条件へデフォルト値として『個店(or 店舗)』の値を設定します。
		requestDto.setTaishoJoken(TaishoJoken.CODE_MISE);
	}
    /**
     * 事前条件処理
     * 
     * @param dto
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
        if (CommonUtil.isNull(requestDto.getCompanyCd())) {
            throw new NotNullException("会社コード");
        }
    }
	/**
	 * @return getHyojiTaishoLogic を戻します。
	 */
	public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
		return getHyojiTaishoLogic;
	}
	/**
	 * @param logic を クラス変数getHyojiTaishoLogicへ設定します。
	 */
	public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic logic) {
		this.getHyojiTaishoLogic = logic;
	}

}
