package jp.co.isid.mos.bird.bizadmin.accountlist.action.impl;

import jp.co.isid.mos.bird.bizadmin.accountlist.action.AccountListAction;
import jp.co.isid.mos.bird.bizadmin.accountlist.code.AccountListConst;
import jp.co.isid.mos.bird.bizadmin.accountlist.dto.AccountListDto;
import jp.co.isid.mos.bird.bizadmin.accountlist.logic.AccountListLogic;
import jp.co.isid.mos.bird.bizadmin.common.code.BizAdminConst;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * 利用者情報照会／変更アクション
 * @author xnkusama
 *
 */
public class AccountListActionImpl implements AccountListAction {
    /**ACTION_ID*/
    public static final String initialize_ACTION_ID = "BBA005A01";
    public static final String goAccountEdit_ACTION_ID = "BBA005A02";

    /**DTO*/
    private PullDownMenuDto pullDownMenuDto;
    
    private AccountListDto accountListDto;
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private CommonCodeDto commonCodeDto;
    
    /**LOGIC*/
    private AccountListLogic accountlistAccountListLogic;
    
    /**
     * 初期処理
     */
    public String initialize() {
        //2)．メニューから遷移された場合、下記の処理を行います。
        if (getPullDownMenuDto().isClearFlg()) {
            getPullDownMenuDto().setClearFlg(false);
        }
        getAccountListDto().setBirdDateInfo(getBirdDateInfo());
        getAccountListDto().setBirdUserInfo(getBirdUserInfo());
        
        getAccountlistAccountListLogic().execute(getAccountListDto());
        
        return null;
    }
    
    /**
     * アカウント情報変更リンク
     */
    public String goAccountEdit() {
        getCommonCodeDto().setParam(BizAdminConst.ACCOUNT_EDIT_USER_ID_PARAM_KEY, getAccountListDto().getEditUserId());
        getCommonCodeDto().setNavigationCase(AccountListConst.VIEW_ID_INIT);
        getCommonCodeDto().setUseCommonDto(true);
        return BizAdminConst.ACCOUNT_EDIT_INIT_VIEW_ID;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public AccountListLogic getAccountlistAccountListLogic() {
        return accountlistAccountListLogic;
    }

    public void setAccountlistAccountListLogic(
            AccountListLogic accountlistAccountListLogic) {
        this.accountlistAccountListLogic = accountlistAccountListLogic;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public AccountListDto getAccountListDto() {
        return accountListDto;
    }

    public void setAccountListDto(AccountListDto accountListDto) {
        this.accountListDto = accountListDto;
    }

    public CommonCodeDto getCommonCodeDto() {
        return commonCodeDto;
    }

    public void setCommonCodeDto(CommonCodeDto commonCodeDto) {
        this.commonCodeDto = commonCodeDto;
    }

	/**
	 * @return クラス変数pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

}
