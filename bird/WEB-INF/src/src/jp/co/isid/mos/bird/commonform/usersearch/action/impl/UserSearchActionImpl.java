package jp.co.isid.mos.bird.commonform.usersearch.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.action.UserSearchAction;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchConditionDto;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.commonform.usersearch.entity.CodCompany;
import jp.co.isid.mos.bird.commonform.usersearch.entity.UIGroupTogoUser;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetBumonLogic;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetCompanyLogic;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetCompanyNameLogic;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetRoleLogic;
import jp.co.isid.mos.bird.commonform.usersearch.logic.SearchUserLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

/**
 * ユーザー検索処理アクションクラス
 * 更新日：2006/06/28  使用停止チェックフラグ及びロールリスト用プルダウン追加対応@author inazawa
 * @author m.onodera
 */
public class UserSearchActionImpl implements UserSearchAction {

    /* アクションID */
    public static final String initialize_ACTION_ID    = "BCO003A01";
    public static final String select_ACTION_ID        = "BCO003A02";
    public static final String search_ACTION_ID        = "BCO003A03";
    public static final String cancel_ACTION_ID        = "BCO003A04";
    public static final String loadBumonList_ACTION_ID = "BCO003A05";
    public static final String listClear_ACTION_ID     = "BCO003A08";
    public static final String changePage_ACTION_ID    = "BCO003A06";

    private int selectPageNumber;
    private int maxPageCount;

    /* ユーザ情報(sesstion) */
    private BirdUserInfo birdUserInfor;
    /* ユーザー情報Dto */
    private UserSearchDto userSearchDto;
    /* UserSearchConditionDto */
    private UserSearchConditionDto userSearchConditionDto;
    /* 今回検索条件DTO */
    private UserSearchConditionDto userSearchRecentConditionDto;
    /* SearchUserLogic */
    private SearchUserLogic searchUserLogic;
    /* GetCompanyLogic */
    private GetCompanyLogic getCompanyLogic;
    /* GetBumonLogic */
    private GetBumonLogic getBumonLogic;
    /* GetRoleLogic ADD 2006/06/28*/
    private GetRoleLogic getRoleLogic;
    private GetCompanyNameLogic userSearchGetCompanyNameLogic;
    /* ユーザーID選択index */
    private int index;

    /**
     * ユーザ情報を取得します。
     * @return ユーザ情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfor;
    }
    /**
     * ユーザ情報を設定します。
     * @param birdUserInfor ユーザ情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfor) {
        this.birdUserInfor = birdUserInfor;
    }
    /**
     * ユーザー情報Dto設定処理
     * @return userSearchDto
     */
    public UserSearchDto getUserSearchDto() {
        return userSearchDto;
    }
    /**
     * ユーザー情報Dto設定処理
     * @param userSearchDto
     */
    public void setUserSearchDto(UserSearchDto userSearchDto) {
        this.userSearchDto = userSearchDto;
    }

    /**
     * ユーザー検索用Dto設定処理
     * @return userSearchConditionDto
     */
    public UserSearchConditionDto getUserSearchConditionDto() {
        return userSearchConditionDto;
    }
    /**
     * ユーザー検索用Dto設定処理
     * @param userSearchConditionDto
     */
    public void setUserSearchConditionDto(
            UserSearchConditionDto userSearchConditionDto) {
        this.userSearchConditionDto = userSearchConditionDto;
    }
    /**
     * ユーザー検索用ロジック設定処理
     * @return searchUserLogic
     */
    public SearchUserLogic getSearchUserLogic() {
        return searchUserLogic;
    }
    /**
     * ユーザー検索用ロジック設定処理
     * @param searchUserLogic
     */
    public void setSearchUserLogic(SearchUserLogic searchUserLogic) {
        this.searchUserLogic = searchUserLogic;
    }
    /**
     * 会社一覧用ロジック設定処理
     * @return getCompanyLogic
     */
    public GetCompanyLogic getGetCompanyLogic() {
        return getCompanyLogic;
    }
    /**
     * 会社一覧用ロジック設定処理
     * @param getCompanyLogic
     */
    public void setGetCompanyLogic(GetCompanyLogic getCompanyLogic) {
        this.getCompanyLogic = getCompanyLogic;
    }
    /**
     * 部門一覧用ロジック設定処理
     * @return getSibuLogic
     */
    public GetBumonLogic getGetBumonLogic() {
        return getBumonLogic;
    }
    /**
     * 部門一覧用ロジック設定処理
     * @param getSibuLogic
     */
    public void setGetBumonLogic(GetBumonLogic getBumonLogic) {
        this.getBumonLogic = getBumonLogic;
    }
    /**
     * ロール一覧用ロジック設定処理
     * 作成日 2006/06/28
     * @return getRoleLogic
     * @author inazawa
     */
    public GetRoleLogic getGetRoleLogic() {
        return getRoleLogic;
    }
    /**
     * ロール一覧用ロジック設定処理
     * 作成日 2006/06/28
     * @param getRoleLogic
     * @author inazawa
     */
    public void setGetRoleLogic(GetRoleLogic getRoleLogic) {
        this.getRoleLogic = getRoleLogic;
    }
    /**
     * ユーザーID選択インデックス設定
     * @return index
     */
    public int getIndex() {
        return index;
    }
    /**
     * ユーザーID選択インデックス設定
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
    public int getSelectPageNumber() {
        return selectPageNumber;
    }
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }
    public int getMaxPageCount() {
        return maxPageCount;
    }
    public void setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
    }



    
    
    /**
     * ユーザー検索初期処理
     * @return 画面遷移情報
	 */
	public String initialize() {
        if (userSearchDto.isInitFlag()) {
            // ウインドウID採番
            userSearchConditionDto.updateWindowid();
            userSearchRecentConditionDto.updateWindowid();
            // 呼出元画面情報保持
            userSearchConditionDto.setParentViewWindowId(userSearchDto.getWindowId());
            userSearchConditionDto.setNavigationCase(userSearchDto.getNavigationCase());
            userSearchConditionDto.setNeedReturnKind(userSearchDto.isNeedReturnKind());

            // 検索用DTO初期化
            userSearchConditionDto.clear(true);
            
            // 画面表示用DTO初期化
            getUserSearchRecentConditionDto().clear(true);
//--- 2007/05/01
//            // 会社コードリスト取得
//            userSearchConditionDto.setCompanyList(getGetCompanyLogic().execute());
            // パラメータ.会社コードリスト有無判定
            List companyList = null;
            List rCompanyCdList = userSearchDto.getRCompanyCdList();
            if (rCompanyCdList == null || rCompanyCdList.isEmpty()) {
                // 会社リスト取得
                companyList = getGetCompanyLogic().execute();
            } else {    
                // 会社名リスト取得
                companyList = getUserSearchGetCompanyNameLogic().execute(rCompanyCdList);
                userSearchDto.setRCompanyCdList(null);
            }

            // 会社リスト設定
            userSearchConditionDto.setCompanyList(companyList);
//--- 2007/05/01
            // 初期表示用会社コード(先頭の会社コード)の設定
            CodCompany entity = (CodCompany) userSearchConditionDto.getCompanyList().get(0);
            String companyCd = entity.getCompanyCd();
            userSearchConditionDto.setKaisyaCd(companyCd);

            //初期表示用部門Listの設定
        	userSearchConditionDto.setBumonList(getGetBumonLogic().execute(userSearchConditionDto.getKaisyaCd()));

            //ロール表示用部門Listの設定
            userSearchConditionDto.setRoleList(getGetRoleLogic().execute());
            
            //画面表示用DTOに値をコピー
            userSearchRecentConditionDto.setCompanyList(userSearchConditionDto.getCompanyList());
            userSearchRecentConditionDto.setKaisyaCd(userSearchConditionDto.getKaisyaCd());
            userSearchRecentConditionDto.setBumonList(userSearchConditionDto.getBumonList());
            userSearchRecentConditionDto.setRoleList(userSearchConditionDto.getRoleList());
            
            // 初期処理フラグOFF
            userSearchDto.setInitFlag(false);
        }
        return null;
	}

    /**
     * 部門リスト読込処理
     * @return 画面遷移情報
     */
    public String loadBumonList() {
        getUserSearchConditionDto().setBumonList(null);
        getUserSearchRecentConditionDto().setBumonList(getGetBumonLogic().execute(getUserSearchRecentConditionDto().getKaisyaCd()));
        userSearchConditionDto.setBumonList(getUserSearchRecentConditionDto().getBumonList());
        // 検索結果が残っているか判定
        userSearchConditionDto.setWindowId(getUserSearchRecentConditionDto().getWindowId());
        if (getUserSearchRecentConditionDto().getCount() > 0 && !userSearchConditionDto.isExistUserSearchList()) {
            // 保存しておいた検索条件で再検索
            List searchList = getSearchUserLogic().execute(userSearchConditionDto);
            userSearchConditionDto.setUserSearchList(searchList);
            userSearchConditionDto.setUserSearchListSize(searchList.size());
            getUserSearchRecentConditionDto().setUserSearchList(searchList);
            getUserSearchRecentConditionDto().setUserSearchListSize(searchList.size());
        }
        else {
            // 保持している検索結果を画面表示用DTOにセット
            getUserSearchRecentConditionDto().setUserSearchList(getUserSearchConditionDto().getUserSearchList());
            getUserSearchRecentConditionDto().setUserSearchListSize(getUserSearchConditionDto().getUserSearchListSize());
        }
        return null;
    }

    /**
     * ロールリスト読込処理
     * 作成日2006/06/28
     * @return 画面遷移情報
     * @author inazawa
     */
    public String loadRoleList() {
        getUserSearchConditionDto().setRoleList(null);
        getUserSearchRecentConditionDto().setRoleList(getGetRoleLogic().execute());
        userSearchConditionDto.setRoleList(getUserSearchRecentConditionDto().getRoleList());

        // 検索結果が残っているか判定
        userSearchConditionDto.setWindowId(getUserSearchRecentConditionDto().getWindowId());
        if (getUserSearchRecentConditionDto().getCount() > 0 && !userSearchConditionDto.isExistUserSearchList()) {
            // 保存しておいた検索条件で再検索
            List searchList = getSearchUserLogic().execute(userSearchConditionDto);
            userSearchConditionDto.setUserSearchList(searchList);
            userSearchConditionDto.setUserSearchListSize(searchList.size());
            getUserSearchRecentConditionDto().setUserSearchList(searchList);
            getUserSearchRecentConditionDto().setUserSearchListSize(searchList.size());
        }
        return null;
    }

    
    /**
     * ユーザー検索要求処理
     * 
     * 画面の検索ボタン押下時に呼ばれるメソッド
     * @return 画面遷移情報
	 */
	public String search() {

        // フォーム入力の検索条件を削除（会社コード、会社リスト、部門リストを除く）
        getUserSearchRecentConditionDto().setUserSearchList(null);
        getUserSearchRecentConditionDto().setUserSearchHonbuList(null);
        getUserSearchRecentConditionDto().setUserSearchOnerList(null);
        getUserSearchRecentConditionDto().setUserSearchMiseList(null);
        getUserSearchRecentConditionDto().setUserSearchListSize(0);
        // ウィンドウ情報セット
//        getUserSearchRecentConditionDto().setWindowId(userSearchConditionDto.getWindowId());
//        getUserSearchRecentConditionDto().setCurrentPageNumber(userSearchConditionDto.getCurrentPageNumber());
//        getUserSearchRecentConditionDto().setMaxPageCount(userSearchConditionDto.getMaxPageCount());
        
        // 入力チェック
        try {
            validate();
        } catch (ApplicationException e) {
            // 検索結果が残っているか判定
            if (getUserSearchRecentConditionDto().getCount() > 0 && !userSearchConditionDto.isExistUserSearchList()) {
                // 保存しておいた検索条件で再検索
                List searchList = getSearchUserLogic().execute(userSearchConditionDto);
                userSearchConditionDto.setUserSearchList(searchList);
                userSearchConditionDto.setUserSearchListSize(searchList.size());
            }
            throw e;
        }
        
        // ページ番号初期化
        getUserSearchRecentConditionDto().setCurrentPageNumber(1);
		userSearchConditionDto.setCurrentPageNumber(1);

        //選択ラジオボタンの初期化
		setIndex(0);
        
        // 検索
//        userSearchConditionDto.setUserSearchList(getSearchUserLogic().execute(getRecentConditionDto()));
        List searchList = getSearchUserLogic().execute(getUserSearchRecentConditionDto());
        getUserSearchRecentConditionDto().setUserSearchList(searchList);
        
        // 今回の検索条件を保存
        userSearchConditionDto.setWindowId(getUserSearchRecentConditionDto().getWindowId());
        userSearchConditionDto.setUserSearchList(searchList);
        userSearchConditionDto.setKaisyaCd(getUserSearchRecentConditionDto().getKaisyaCd());
        userSearchConditionDto.setBumonCd(getUserSearchRecentConditionDto().getBumonCd());
        userSearchConditionDto.setUserName(getUserSearchRecentConditionDto().getUserName());
        userSearchConditionDto.setOnerCd(getUserSearchRecentConditionDto().getOnerCd());
        userSearchConditionDto.setOnerNameKj(getUserSearchRecentConditionDto().getOnerNameKj());
        userSearchConditionDto.setMiseCd(getUserSearchRecentConditionDto().getMiseCd());
        userSearchConditionDto.setMiseNameKj(getUserSearchRecentConditionDto().getMiseNameKj());
        userSearchConditionDto.setRoleCd(getUserSearchRecentConditionDto().getRoleCd());
        userSearchConditionDto.setStopFlg(getUserSearchRecentConditionDto().isStopFlg());
        userSearchConditionDto.setUserIdCond(getUserSearchRecentConditionDto().getUserIdCond());
        
        if(searchList.isEmpty()){
        	userSearchConditionDto.setUserSearchListSize(0);
            throw new NoResultException("");
        }else{
            // データ件数セット
            getUserSearchRecentConditionDto().setUserSearchListSize(searchList.size());
            userSearchConditionDto.setUserSearchListSize(searchList.size());
            userSearchConditionDto.setCount(getUserSearchRecentConditionDto().getCount());
        }
        return null;
    }
	
	
    /**
     * ユーザー検索ページ切り替え処理
     * @return 画面遷移情報
	 */
    public String changePage() {
        // 選択されたページ番号を設定
        getUserSearchRecentConditionDto().setCurrentPageNumber(getSelectPageNumber());
        getUserSearchConditionDto().setCurrentPageNumber(getSelectPageNumber());
		//選択ラジオボタンの初期化
        setIndex(0);

        // 検索
        List listUser = getSearchUserLogic().execute(getUserSearchConditionDto());
        getUserSearchRecentConditionDto().setUserSearchList(listUser);
        getUserSearchConditionDto().setUserSearchList(listUser);
        
        // 自画面へ遷移
        return null;
    }

    /**
     * ユーザー検索決定処理
     * @return 画面遷移情報
	 */
	public String select() {
        // ウィンドウIDを再セット
        userSearchConditionDto.setWindowId(getUserSearchRecentConditionDto().getWindowId());
        // 検索結果が残っているか判定
        if (userSearchConditionDto.getCount() > 0 && !userSearchConditionDto.isExistUserSearchList()) {
            // 保存しておいた検索条件で再検索
            List searchList = getSearchUserLogic().execute(userSearchConditionDto);
            userSearchConditionDto.setUserSearchList(searchList);
            userSearchConditionDto.setUserSearchListSize(searchList.size());
        }
        
        // 選択情報設定
        if (getUserSearchConditionDto().getUserSearchListSize() > 0) {
            UIGroupTogoUser user = (UIGroupTogoUser) getUserSearchConditionDto()
                    .getUserSearchList().get(getIndex());

            userSearchDto.setUserId(user.getUserId());
            userSearchDto.setUserName(user.getUserName());
            userSearchDto.setUserNameKana(user.getUserNameKana());
            // アクションフラグ設定
            userSearchDto.setActionFlg(true);

        } else {
            userSearchDto.setUserId(null);
        }
        // 検索用DTO初期化
        userSearchConditionDto.clear(true);

        // 遷移区分要否判定
        int returnKind = UserSearchDto.RETURNKIND_INIT;
        if (getUserSearchConditionDto().isNeedReturnKind()) {
            returnKind = UserSearchDto.RETURNKIND_SELECT;
            userSearchDto.setNeedReturnKind(false);
        }
        userSearchDto.setReturnKind(returnKind);
        // 呼出元画面情報戻し
        userSearchDto.setWindowId(getUserSearchConditionDto().getParentViewWindowId());
        userSearchDto.setNavigationCase(getUserSearchConditionDto().getNavigationCase());
        
        return userSearchDto.getNavigationCase();
	}

    /**
     * ユーザー検索取消(戻る)処理
     * @return 画面遷移情報
	 * @see jp.co.isid.mos.bird.commonform.usersearch.action.UserSearchAction#cancel()
	 */
	public String cancel() {
        // 検索用DTO初期化
        userSearchConditionDto.clear(true);
        // アクションフラグfalse
        userSearchDto.setActionFlg(false);

        // 遷移区分要否判定
        int returnKind = UserSearchDto.RETURNKIND_INIT;
        if (getUserSearchConditionDto().isNeedReturnKind()) {
            returnKind = UserSearchDto.RETURNKIND_CANCEL;
            userSearchDto.setNeedReturnKind(false);
        }
        userSearchDto.setReturnKind(returnKind);
        // ウインドウID、画面遷移情報戻し
        userSearchDto.setWindowId(getUserSearchConditionDto().getParentViewWindowId());
        userSearchDto.setNavigationCase(getUserSearchConditionDto().getNavigationCase());
        
        return userSearchDto.getNavigationCase();
	}
	
    /**
     * ユーザー検索クリア処理
     * @return 画面遷移情報
	 * @see jp.co.isid.mos.bird.commonform.usersearch.action.UserSearchAction#cancel()
	 */
	public String listClear() {
        // 検索用DTO初期化
        userSearchConditionDto.clear(false);
        return null;
	}

    /**
     * 入力チェック
     */
    private void validate() {
    	MetaCharVerifier sqlVerifier = new MetaCharVerifier();
        // 会社コード必須チェック
        if (userSearchRecentConditionDto.getKaisyaCd() == null
                || userSearchRecentConditionDto.getKaisyaCd().equals("")) {
        	NotNullException notNullEx = new NotNullException("会社コード", "", true);
        	notNullEx.setHtmlElementName("rCompanyCd");
            throw notNullEx;
        }

        // ユーザー名文字数
        String userName = userSearchRecentConditionDto.getUserName();
        if (userName != null) {
       		//バイト数、SQLメタ文字が含まれているかチェックを行う
       		if(userName.getBytes().length > 40 || !sqlVerifier.validate(userName)) {
       			throw new InvalidInputException(
       					"ユーザー名", "userName", null);
      		}
        }

        // オーナーコード桁数
        String onerCd = userSearchRecentConditionDto.getOnerCd();
        if (onerCd != null && onerCd.getBytes().length > 5) {
            throw new InvalidInputException(
            		"オーナーコード", "onerCd", null);
        }

        // オーナー名文字数
        String onerNameKj = userSearchRecentConditionDto.getOnerNameKj();
        if (onerNameKj != null) {
       		if(onerNameKj.getBytes().length > 40 || !sqlVerifier.validate(onerNameKj)) {
       			throw new InvalidInputException(
       					"オーナー名", "onerNameKj", null);
       		}
        }

        // 店コード桁数
        String miseCd = userSearchRecentConditionDto.getMiseCd();
        if (miseCd != null) {
       		if(miseCd.getBytes().length > 5) {
	            throw new InvalidInputException("店コード", "miseCd", null);
       		}
        }

        // 店舗名文字数
        String miseNameKj = userSearchRecentConditionDto.getMiseNameKj();
        if (miseNameKj != null) {
       		if(miseNameKj.getBytes().length > 40 || !sqlVerifier.validate(miseNameKj)) {
       			throw new InvalidInputException("店舗名", "miseNameKj", null);
       		}
        }

        // ユーザID、コードチェック
        String userIdCond = userSearchRecentConditionDto.getUserIdCond();
        CodeVerifier codeVerifier = new CodeVerifier(8, true);      //8桁・アルファベット許可
        codeVerifier.setNullable(true);                             //nullを許可
        if (!codeVerifier.validate(userIdCond)) {
            throw new InvalidInputException("ユーザID", "userIdCond", null);
        }
    }

    /**
     * 今回検索条件DTO取得処理
     * @return 今回検索条件DTO
     */
    public UserSearchConditionDto getUserSearchRecentConditionDto() {
        return userSearchRecentConditionDto;
    }
    /**
     * 今回検索条件DTO設定処理
     * @param recentConditionDto 今回検索条件DTO
     */
    public void setUserSearchRecentConditionDto(UserSearchConditionDto recentConditionDto) {
        this.userSearchRecentConditionDto = recentConditionDto;
    }
    public GetCompanyNameLogic getUserSearchGetCompanyNameLogic() {
        return userSearchGetCompanyNameLogic;
    }
    public void setUserSearchGetCompanyNameLogic(
            GetCompanyNameLogic userSearchGetCompanyNameLogic) {
        this.userSearchGetCompanyNameLogic = userSearchGetCompanyNameLogic;
    }
}