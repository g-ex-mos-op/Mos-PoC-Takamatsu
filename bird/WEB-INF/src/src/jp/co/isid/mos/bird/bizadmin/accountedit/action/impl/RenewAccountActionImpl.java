/*
 * 作成日: 2006/03/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.action.impl;

import jp.co.isid.mos.bird.bizadmin.accountedit.action.RenewAccountAction;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccountEditRequestDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.RenewEditAccountLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * @author aspac
 * 
 * 更新日:2009/12/14 xkinu ロック解除機能追加
 * 
 */
public class RenewAccountActionImpl implements RenewAccountAction {

    public static final String initialize_ACTION_ID = "BBA001A21";
    public static final String update_ACTION_ID = "BBA001A22";
    public static final String back_ACTION_ID = "BBA001A23";

    private BirdDateInfo birdDateInfo;
	private BirdUserInfo birdUserInfo;
	/** DTO【画面ロール情報保持】*/
    private GamenRoleDto gamenRoleDto;
    
	private AccounteditDto accounteditDto;
	/** DTO【リクエスト用】*/
	private AccountEditRequestDto accounteditReqDto;
    /** LOGIC【アカウント登録】*/
	private RenewEditAccountLogic accounteditRenewEditAccountLogic;
    /* セッションキー */
    private MakeSessionKey sessionKey = new MakeSessionKey();

    /**
     * 初期化処理
     */
	public String initialize() {        
		return null;
	}
	
	public String update(){
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(accounteditDto.getSessionKeyNow(), accounteditReqDto.getSessionKey())) {
            return CommonUtil.operationErr_VIEW_ID;
        }     
        //DTOへシステム日付をセット
        accounteditDto.setSysDt(getBirdDateInfo().getSysDate());
		accounteditRenewEditAccountLogic.execute(accounteditDto,gamenRoleDto);
		
        String returnViewId = "BBA001V01";
        if (!CommonUtil.isNull(accounteditDto.getReturnViewId())) {
            returnViewId = accounteditDto.getReturnViewId();
        }

		return returnViewId;
	}
	
	public String back(){        
		return accounteditDto.getPageId();
	}
    
	/**
	 * @return accounteditDto を戻します。
	 */
	public AccounteditDto getAccounteditDto() {
		return accounteditDto;
	}
	/**
	 * @param accounteditDto accounteditDto を設定。
	 */
	public void setAccounteditDto(AccounteditDto accounteditDto) {
		this.accounteditDto = accounteditDto;
	}

	/**
	 * @return accounteditRenewEditAccountLogic を戻します。
	 */
	public RenewEditAccountLogic getAccounteditRenewEditAccountLogic() {
		return accounteditRenewEditAccountLogic;
	}
	/**
	 * @param accounteditRenewEditAccountLogic accounteditRenewEditAccountLogic を設定。
	 */
	public void setAccounteditRenewEditAccountLogic(
			RenewEditAccountLogic renewEditAccountLogic) {
		this.accounteditRenewEditAccountLogic = renewEditAccountLogic;
	}

    /**
     * @return gamenRoleDto getGamenRoleDto を設定。
     */
    public GamenRoleDto getGamenRoleDto() {
        return gamenRoleDto;
    }

    /**
     * @param gamenRoleDto getGamenRoleDto を設定。
     */
    public void setGamenRoleDto(GamenRoleDto gamenRoleDto) {
        this.gamenRoleDto = gamenRoleDto;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return クラス変数accountEditRequestDto を戻します。
	 */
	public AccountEditRequestDto getAccounteditReqDto() {
		return accounteditReqDto;
	}

	/**
	 * @param accounteditReqDto を クラス変数accountEditRequestDtoへ設定します。
	 */
	public void setAccounteditReqDto(AccountEditRequestDto accountEditRequestDto) {
		this.accounteditReqDto = accountEditRequestDto;
	}


}
