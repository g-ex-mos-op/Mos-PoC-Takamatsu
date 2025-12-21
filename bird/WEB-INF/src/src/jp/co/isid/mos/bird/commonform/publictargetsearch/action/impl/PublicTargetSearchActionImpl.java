/*
 * 作成日: 2006/01/16
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.action.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.publictargetsearch.action.PublicTargetSearchAction;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodShozoku;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstCompanySibuTorikomi;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.MstGyotiUneiCompany;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.CreateKokaiTargetLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.GetCompanySibuLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.KobetuSetLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.SearchGyotaiLogic;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.SearchKokaiLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 公開対象選択のアクション
 * 
 * @author xytamura
 */
public class PublicTargetSearchActionImpl implements CommonAction,
        PublicTargetSearchAction {

    public static final String selectCompany_ACTION_ID = "BCO002A06";

    public static final String cancel_ACTION_ID = "BCO002A02";

    public static final String clear_ACTION_ID = "BCO002A03";

    public static final String select_ACTION_ID = "BCO002A01";

    public static final String kogetuSet_ACTION_ID = "BCO002A10";

    /**
     * 個別設定画面ページキー
     */
    private static final String KOBETUSET_PAGE = "BCO002V02";

    /**
     * 公開対象選択画面が使用するDto
     */
    private PublicTargetSearchConditionDto conditionDto;

    /**
     * 公開対象選択パラメータ受け渡し用のDto
     */
    private PublicTargetSearchDto publicTargetSearchDto;

    /**
     * 公開対象の検索ロジック
     */
    private SearchKokaiLogic searchKokaiLogic;

    /**
     * 業態の検索ロジック
     */
    private SearchGyotaiLogic searchGyotaiLogic;

    /**
     * 個別設定情報の取得ロジック
     */
    private KobetuSetLogic kobetuSetLogic;

    /**
     * 公開対象作成ロジック
     */
    private CreateKokaiTargetLogic createKokaiTargetLogic;

    /**
     * 支部取得ロジック
     */
    private GetCompanySibuLogic getCompanySibuLogic;

    private String selectedGyotaiCompanyCd;
    private String selectedGyotiKbn;

    private int selectedIndex;

    // private List companyCondList;
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    private boolean flgCompanySelect = false;
    
    /**
     * 初期処理
     * 
     * @see jp.co.isid.mos.bird.framework.action.HtmlInitAction#init()
     */
    public String initialize() {
        if (getPublicTargetSearchDto().isInitFlag()) {
            getPublicTargetSearchConditionDto().clearData();
            getPublicTargetSearchConditionDto().setBirdUserInfo(getBirdUserInfo());
            
            PublicTargetSearchConditionDto dto = getSearchKokaiLogic().execute(
                    getPublicTargetSearchConditionDto(),
                    getPublicTargetSearchDto());

            setPublicTargetSearchConditionDto(dto);
            
            getPublicTargetSearchDto().setInitFlag(false);
        }
        if (!flgCompanySelect) {
            getPublicTargetSearchConditionDto().setSelectedCompanyIndex(-1);
        }
        flgCompanySelect = false;

        return null;
    }

    /**
     * 会社が選択された時の処理
     * 
     * @return
     */
    public String selectCompany() {
        flgCompanySelect = true;
        
        PublicTargetSearchConditionDto dto = getSearchGyotaiLogic().execute(
                getPublicTargetSearchConditionDto());
        setPublicTargetSearchConditionDto(dto);

        return null;
    }

    /**
     * 戻る
     * 
     * @return
     */
    public String cancel() {
        publicTargetSearchDto.setActionFlg(false);
        // publicTargetSearchDto.clearData();
        return publicTargetSearchDto.getNavigationCase();
    }

    /**
     * クリア
     * 
     * @return
     */
    public String clear() {

        PublicTargetSearchConditionDto dto = getPublicTargetSearchConditionDto();
        clearShozoku(dto.getListCodShozoku());
        clearCompany(dto.getListCtlUserCompany());
        clearGyotai(dto.getListMstGyotiUneiCompany());
        return null;
    }

    /**
     * 所属をクリア
     * 
     * @param shozoku
     *            所属
     */
    private void clearShozoku(List shozoku) {
        for (int i = 0; i < shozoku.size(); i++) {
            CodShozoku entity = (CodShozoku) shozoku.get(i);
            entity.setSentakuFlg(false);
        }
    }

    /**
     * 会社をクリア
     * 
     * @param company
     *            会社
     */
    private void clearCompany(List company) {
        for (int i = 0; i < company.size(); i++) {
            CtlCompany entity = (CtlCompany) company.get(i);
            entity.setSentakuFlg(false);
        }

    }

    /**
     * 業態と個別設定をクリア
     * 
     * @param gyotai
     *            業態
     */
    private void clearGyotai(List gyotai) {
        for (int i = 0; i < gyotai.size(); i++) {
            MstGyotiUneiCompany entity = (MstGyotiUneiCompany) gyotai.get(i);
            entity.setSentakuFlg(false);
            if (entity.getKobetuSetMise() != null) {
                entity.getKobetuSetMise().clear();
            }
            if (entity.getKobetuSetSibu() != null) {
                entity.getKobetuSetSibu().clear();
            }
        }
    }

    /**
     * 決定
     * 
     * @return
     */
    public String select() {
        checkCampany();
        checkGyotai();
        checkShozoku();
        checkCompanySibu();
        getPublicTargetSearchDto().setActionFlg(true);
        publicTargetSearchDto = getCreateKokaiTargetLogic()
                .execute(getPublicTargetSearchConditionDto(),
                        getPublicTargetSearchDto());
        return getPublicTargetSearchDto().getNavigationCase();
    }

    /**
     * 会社が選択されているかチェックします。
     * 
     * @return
     */
    private void checkCampany() {
        List campany = getPublicTargetSearchConditionDto()
                .getListCtlUserCompany();
        if (campany == null) {
            throw new NotNullException("会社選択");
        }
        for (int i = 0; i < campany.size(); i++) {
            CtlCompany entity = (CtlCompany) campany.get(i);
            if (entity.getSentakuFlg()) {
                return;
            }
        }
        throw new NotNullException("会社選択");
    }

    /**
     * 所属が選択されているかチェックします。
     * 
     * @return
     */
    private void checkShozoku() {
        List shozoku = getPublicTargetSearchConditionDto().getListCodShozoku();
        if (shozoku == null) {
            throw new NotNullException("所属選択");
        }
        for (int i = 0; i < shozoku.size(); i++) {
            CodShozoku entity = (CodShozoku) shozoku.get(i);
            if (entity.getSentakuFlg()) {
                return;
            }
        }
        throw new NotNullException("所属選択");
    }

    /**
	 * 業態が選択されているかチェックします。
	 * 
	 * @return
	 */
	private void checkGyotai() {
	    // 業態選択の場合
	    if (PublicTargetSearchConditionDto.MODE_GYOTAI
	            .equals(conditionDto.getSelectMode())) {
	        List gyotai = conditionDto
	                .getListMstGyotiUneiCompany();
	        if (gyotai == null) {
	            throw new NotNullException("業態選択");
	        }
	        for (int i = 0; i < gyotai.size(); i++) {
	            MstGyotiUneiCompany entity = (MstGyotiUneiCompany) gyotai
	                    .get(i);
	            if (entity.getSentakuFlg()) {
	                return;
	            }
	        }
	        throw new NotNullException("業態選択");
	
	        // 支部選択の場合
	    }
	}

    /**
     * 会社と支部の整合性チェックを行います。
     */
    private void checkCompanySibu() {
    	if (PublicTargetSearchConditionDto.MODE_SIBU
	            .equals(conditionDto.getSelectMode())) {
	        // 会社と支部の整合性チェック用Mapの作成
	        Map mapCompanysAreaDai = conditionDto.getMapCompanysSibu();
	        if (mapCompanysAreaDai == null 
	        		&& mapCompanysAreaDai.isEmpty())
	        {
	            throw new NotNullException("支部選択");
	        }
	        // 会社と支部の整合性チェック用Mapの作成
	        Map companyCheck = new HashMap();
	        List company = conditionDto.getListCtlUserCompany();
	        for (int i = 0; i < company.size(); i++) {
	            CtlCompany ctlCompany = (CtlCompany) company.get(i);
	            if (ctlCompany.getSentakuFlg()) {
	                companyCheck.put(ctlCompany.getRCompanyCd(), "");
	            }
	        }
	        boolean selected = false;
	        for(Iterator key = mapCompanysAreaDai.keySet().iterator(); key.hasNext();) {
	            List listAreaDai = (List)mapCompanysAreaDai.get((String)key.next());
	            for(int i=0; i < listAreaDai.size(); i++) {
	                MstCompanySibuTorikomi entity = (MstCompanySibuTorikomi)listAreaDai.get(i);
		            if (entity.getSentakuFlg()) {
		            	selected = true;
		                //会社と支部(支部取込：AREA_DAI)の整合性チェック
		                if (companyCheck.containsKey(entity.getCompanyCd())) {
		                    break;
		                } else {
		                    throw new GenericCommentException("会社と支部が不整合");
		                }
		            }
	            }
	        }
	        if(selected == false) {
	        	throw new NotNullException("支部選択");
	        }
    	}
    }

    
    /**
     * 個別設定
     * 
     * @return
     */
    public String kogetuSet() {
        conditionDto.setSelectedGyotaiCompanyCd(selectedGyotaiCompanyCd);
        conditionDto.setSelectedGyotiKbn(selectedGyotiKbn);
        conditionDto.setSelectedIndex(selectedIndex);

        // 【個別設定情報の検索】
        PublicTargetSearchConditionDto dto = getKobetuSetLogic().execute(
                conditionDto);

        // 選択された業態をセット
        MstGyotiUneiCompany mstGyotiUneiCompany = (MstGyotiUneiCompany) conditionDto
                .getListMstGyotiUneiCompany().get(selectedIndex);

        dto.setSelectedMstGyotiUneiCompany(mstGyotiUneiCompany);
        return KOBETUSET_PAGE;

    }

    /**
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchConditionDto getPublicTargetSearchConditionDto() {
        conditionDto.setUserid(getBirdUserInfo().getUserID());
        return conditionDto;
    }

    /**
     * @param publicTargetSearchDto
     *            publicTargetSearchDto を設定。
     */
    public void setPublicTargetSearchConditionDto(
            PublicTargetSearchConditionDto conditionDto) {
        this.conditionDto = conditionDto;
    }

    /**
     * 公開対象の検索ロジックを取得します。
     * 
     * @return 公開対象の検索ロジック
     */
    public SearchKokaiLogic getSearchKokaiLogic() {
        return searchKokaiLogic;
    }

    /**
     * 公開対象の検索ロジックをセットします。
     * 
     * @param searchKokaiLogic
     *            公開対象の検索ロジック
     */
    public void setSearchKokaiLogic(SearchKokaiLogic searchKokaiLogic) {
        this.searchKokaiLogic = searchKokaiLogic;
    }

    /**
     * 個別設定情報の取得ロジックを取得します。
     * 
     * @return 個別設定情報の取得ロジック
     */
    public KobetuSetLogic getKobetuSetLogic() {
        return kobetuSetLogic;
    }

    /**
     * 個別設定情報を取得ロジックをセットします。
     * 
     * @param 個別設定情報の取得ロジック
     */
    public void setKobetuSetLogic(KobetuSetLogic kobetuSetLogic) {
        this.kobetuSetLogic = kobetuSetLogic;
    }

    // /**
    // *
    // * @return companyCondList を戻します。
    // */
    // public List getCompanyCondList() {
    // return companyCondList;
    // }
    //
    // /**
    // * @param companyCondList companyCondList を設定。
    // */
    // public void setCompanyCondList(List companyCondList) {
    // this.companyCondList = companyCondList;
    // }

    // /**
    // * @param selectedCompanyCd selectedCompanyCd を設定。
    // */
    // public void setSelectedCompanyCd(String selectedCompanyCd) {
    // this.selectedCompanyCd = selectedCompanyCd;
    // }

    /**
     * 選択された業態区分を設定
     * 
     * @param selectedGyotiKbn
     *            業態区分
     */
    public void setSelectedGyotiKbn(String selectedGyotiKbn) {
        this.selectedGyotiKbn = selectedGyotiKbn;
    }

    /**
     * 選択された業態のインデックスを設定
     * 
     * @param selectedIndex
     *            選択された業態のインデックス
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    /**
     * 公開対象選択パラメータ受け渡し用のDtoを取得
     * 
     * @return 公開対象選択パラメータ受け渡し用のDto
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }

    /**
     * 公開対象選択パラメータ受け渡し用のDtoを設定
     * 
     * @param publicTargetSearchDto
     *            公開対象選択パラメータ受け渡し用のDto
     */
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
    }

    /**
     * 公開対象作成ロジックを取得
     * 
     * @return createKokaiTargetLogic 公開対象作成ロジック
     */
    public CreateKokaiTargetLogic getCreateKokaiTargetLogic() {
        return createKokaiTargetLogic;
    }

    /**
     * 公開対象作成ロジックを設定
     * 
     * @param createKokaiTargetLogic
     *            公開対象作成ロジック
     */
    public void setCreateKokaiTargetLogic(
            CreateKokaiTargetLogic createKokaiTargetLogic) {
        this.createKokaiTargetLogic = createKokaiTargetLogic;
    }

    /**
     * 業態の検索ロジックを取得します。
     * 
     * @return 業態の検索ロジック
     */
    public SearchGyotaiLogic getSearchGyotaiLogic() {
        return searchGyotaiLogic;
    }

    /**
     * 業態の検索ロジックを設定します。
     * 
     * @param searchGyotaiLogic
     *            業態の検索ロジック
     */
    public void setSearchGyotaiLogic(SearchGyotaiLogic searchGyotaiLogic) {
        this.searchGyotaiLogic = searchGyotaiLogic;
    }

    /**
     * 支部取得ロジックを取得します。
     * 
     * @return 支部取得ロジック
     */
    public GetCompanySibuLogic getGetCompanySibuLogic() {
        return getCompanySibuLogic;
    }

    /**
     * 支部取得ロジックを設定します。
     * 
     * @param 支部取得ロジック
     */
    public void setGetCompanySibuLogic(GetCompanySibuLogic getCompanySibuLogic) {
        this.getCompanySibuLogic = getCompanySibuLogic;
    }

    /**
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * @param birdUserInfo
     *            birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
	 * 切り替え
	 */
	public String kirikae() {
	    //『公開対象業態選択』の場合
	    if (PublicTargetSearchConditionDto.MODE_GYOTAI
	            .equals(conditionDto.getSelectMode())) {
	        //LOGIC【業態の検索＆設定】を実行します。
	        getSearchGyotaiLogic().execute(conditionDto);          
	    }
	    //『公開対象支部選択』の場合
	    else if (PublicTargetSearchConditionDto.MODE_SIBU
	            .equals(conditionDto.getSelectMode())) {
	    	//LOGIC【公開対象支部選択情報保持リスト取得＆設定】を実行します。
	        getGetCompanySibuLogic().execute(conditionDto);
	    }
	    return null;
	}

    public String getSelectedGyotaiCompanyCd() {
        return selectedGyotaiCompanyCd;
    }

    public void setSelectedGyotaiCompanyCd(String selectedGyotaiCompanyCd) {
        this.selectedGyotaiCompanyCd = selectedGyotaiCompanyCd;
    }

}