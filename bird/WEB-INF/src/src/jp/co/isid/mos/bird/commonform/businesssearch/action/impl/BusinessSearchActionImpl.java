/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.action.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.businesssearch.action.BusinessSearchAction;
import jp.co.isid.mos.bird.commonform.businesssearch.dto.BusinessSearchDto;
import jp.co.isid.mos.bird.commonform.businesssearch.dto.BusinessSearchListDto;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.CtlManagementCompany;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.commonform.businesssearch.logic.SearchBusinessListLogic;
import jp.co.isid.mos.bird.commonform.businesssearch.logic.SearchBusinessResultLogic;
import jp.co.isid.mos.bird.commonform.businesssearch.logic.SearchCompanyListLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xyuchida
 *  
 */
public class BusinessSearchActionImpl implements BusinessSearchAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BCO007A01";
    public static final String changeTab_ACTION_ID = "BCO007A02";
    public static final String select_ACTION_ID = "BCO007A03";
    public static final String cancel_ACTION_ID = "BCO007A04";

    /**
     * 初期処理フラグ
     */
    private boolean initialFlag;

    /**
     * 選択タブ管理会社企業コード
     */
    private String selectCompanyCd;

    /**
     * 業態選択DTO
     */
    private BusinessSearchDto businessSearchDto;

    /**
     * 業態リストDTO
     */
    private BusinessSearchListDto businessSearchListDto;

    /**
     * 管理会社リスト取得ロジック
     */
    private SearchCompanyListLogic searchCompanyListLogic;

    /**
     * 業態リスト取得ロジック
     */
    private SearchBusinessListLogic searchBusinessListLogic;

    /**
     * 結果業態リスト取得ロジック
     */
    private SearchBusinessResultLogic searchBusinessResultLogic;

    /**
     * 初期処理フラグを取得します。
     * 
     * @return 初期処理フラグ
     */
    public boolean isInitialFlag() {
        return initialFlag;
    }
    /**
     * 初期処理フラグを設定します。
     * 
     * @param initialFlag 初期処理フラグ
     */
    public void setInitialFlag(boolean initialFlag) {
        this.initialFlag = initialFlag;
    }

    /**
     * 選択タブ管理会社企業コードを取得します。
     * 
     * @return 選択タブ管理会社企業コード
     */
    public String getSelectCompanyCd() {
        return selectCompanyCd;
    }
    /**
     * 選択タブ管理会社企業コードを設定します。
     * 
     * @param selectCompanyCd 選択タブ管理会社企業コード
     */
    public void setSelectCompanyCd(String selectCompanyCd) {
        this.selectCompanyCd = selectCompanyCd;
    }

    /**
     * 業態選択DTOを取得します。
     * 
     * @return 業態選択DTO
     */
    public BusinessSearchDto getBusinessSearchDto() {
        return businessSearchDto;
    }
    /**
     * 業態選択DTOを設定します。
     * 
     * @param businessSearchDto 業態選択DTO
     */
    public void setBusinessSearchDto(BusinessSearchDto businessSearchDto) {
        this.businessSearchDto = businessSearchDto;
    }

    /**
     * 業態リストDTOを取得します。
     * 
     * @return 業態リストDTO
     */
    public BusinessSearchListDto getBusinessSearchListDto() {
        return businessSearchListDto;
    }
    /**
     * 業態リストDTOを設定します。
     * 
     * @param businessSearchListDto 業態リストDTO
     */
    public void setBusinessSearchListDto(
            BusinessSearchListDto businessSearchListDto) {
        this.businessSearchListDto = businessSearchListDto;
    }

    /**
     * 管理会社リスト取得ロジックを取得します。
     * 
     * @return 管理会社リスト取得ロジック
     */
    public SearchCompanyListLogic getSearchCompanyListLogic() {
        return searchCompanyListLogic;
    }
    /**
     * 管理会社リスト取得ロジックを設定します。
     * 
     * @param searchCompanyListLogic 管理会社リスト取得ロジック
     */
    public void setSearchCompanyListLogic(
            SearchCompanyListLogic searchCompanyListLogic) {
        this.searchCompanyListLogic = searchCompanyListLogic;
    }

    /**
     * 業態リスト取得ロジックを取得します。
     * 
     * @return 業態リスト取得ロジック
     */
    public SearchBusinessListLogic getSearchBusinessListLogic() {
        return searchBusinessListLogic;
    }
    /**
     * 業態リスト取得ロジックを設定します。
     * 
     * @param searchBusinessListLogic 業態リスト取得ロジック
     */
    public void setSearchBusinessListLogic(
            SearchBusinessListLogic searchBusinessListLogic) {
        this.searchBusinessListLogic = searchBusinessListLogic;
    }

    /**
     * 結果業態リスト取得ロジックを取得します。
     * 
     * @return 結果業態リスト取得ロジック
     */
    public SearchBusinessResultLogic getSearchBusinessResultLogic() {
        return searchBusinessResultLogic;
    }
    /**
     * 結果業態リスト取得ロジックを設定します。
     * 
     * @param searchBusinessResultLogic 結果業態リスト取得ロジック
     */
    public void setSearchBusinessResultLogic(
            SearchBusinessResultLogic searchBusinessResultLogic) {
        this.searchBusinessResultLogic = searchBusinessResultLogic;
    }

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        // 初期処理判定
        if (!isInitialFlag()) {

            // セッション情報クリア
            getBusinessSearchListDto().getResultGyotaiKbnSet().clear();

            // 管理会社リスト取得
            List companyList = getSearchCompanyListLogic().execute();
            if (companyList == null || companyList.size() <= 0) {
                throw new FtlSystemException("業態選択");
            }
            getBusinessSearchListDto().setCompanyList(companyList);

            // 先頭の管理会社企業コードを選択中管理会社企業コードとして保持
            CtlManagementCompany ctlManagementCompany = (CtlManagementCompany) companyList.get(0);
            getBusinessSearchListDto().setSelectedCompanyCd(ctlManagementCompany.getCompanyCd());

            // 業態リスト取得
            getBusinessSearchListDto().setGyotaiList(
                    getSearchBusinessListLogic().execute(ctlManagementCompany.getCompanyCd()));

            // パラメータ.業態リストが設定されている業態区分をチェック状態とする
            List resultGyotaiList = getBusinessSearchDto().getResultGyotaiList();
            if (resultGyotaiList != null) {
                for (Iterator it = resultGyotaiList.iterator(); it.hasNext();) {
                    getBusinessSearchListDto().getResultGyotaiKbnSet().add(((UIGyotai) it.next()).getGyotaiKbn());
                }
            }

            // 業態チェック状態読込
            loadCheckState();

            // 初期処理フラグ更新
            setInitialFlag(true);
        }

        return null;
    }

    /**
     * タブ切替
     * 
     * @return 画面遷移情報
     */
    public String changeTab() {

        // タブ切替判定
        if (!selectCompanyCd.equals(getBusinessSearchListDto().getSelectedCompanyCd())) {

            // 業態チェック状態保存
            saveCheckState();

            // 業態リスト取得
            getBusinessSearchListDto().setGyotaiList(
                    getSearchBusinessListLogic().execute(selectCompanyCd));

            // 業態チェック状態読込
            loadCheckState();

            // 今回選択された管理会社企業コードを選択中管理会社企業コードとして保持
            getBusinessSearchListDto().setSelectedCompanyCd(selectCompanyCd);
        }

        return null;
    }

    /**
     * 決定
     * 
     * @return 画面遷移情報
     */
    public String select() {

        // 業態チェック状態保存
        saveCheckState();

        // 業態選択を必須とする
        List gyotaiKbnList = Arrays.asList(getBusinessSearchListDto().getResultGyotaiKbnSet().toArray());
        if (gyotaiKbnList == null || gyotaiKbnList.size() <= 0) {
            throw new NotNullException("業態選択");
        }
        // 結果業態リスト取得
        getBusinessSearchDto().setResultGyotaiList(
                getSearchBusinessResultLogic().execute(gyotaiKbnList));

        // アクションフラグ設定
        getBusinessSearchDto().setActionFlg(true);
        getBusinessSearchDto().setReturnKind(BusinessSearchDto.RETURNKIND_SELECT);

        // 呼出元画面へ遷移
        return getBusinessSearchDto().getNavigationCase();
    }

    /**
     * 取消
     * 
     * @return 画面遷移情報
     */
    public String cancel() {

        // アクションフラグ設定
        getBusinessSearchDto().setActionFlg(false);
        getBusinessSearchDto().setReturnKind(BusinessSearchDto.RETURNKIND_CANCEL);

        // 呼出元画面へ遷移
        return getBusinessSearchDto().getNavigationCase();
    }

    /**
     * 業態チェック状態保存
     * 
     * @param roleSearchRoleListDto 業態リストDTO
     */
    private void saveCheckState() {

        for (Iterator it = getBusinessSearchListDto().getGyotaiList().iterator(); it.hasNext(); ) {
            UIGyotai uIGyotai = (UIGyotai) it.next();
            if (uIGyotai.isCheckedGyotai()) {
                getBusinessSearchListDto().getResultGyotaiKbnSet().add(uIGyotai.getGyotaiKbn());
            } else {
                getBusinessSearchListDto().getResultGyotaiKbnSet().remove(uIGyotai.getGyotaiKbn());
            }
        }
    }

    /**
     * 業態チェック状態読込
     * 
     * @param roleSearchRoleListDto 業態リストDTO
     */
    private void loadCheckState() {

        for (Iterator it = getBusinessSearchListDto().getGyotaiList().iterator(); it.hasNext();) {
            UIGyotai uIGyotai = (UIGyotai) it.next();
            if (getBusinessSearchListDto().getResultGyotaiKbnSet().contains(uIGyotai.getGyotaiKbn())) {
                uIGyotai.setCheckedGyotai(true);
            }
        }
    }
}