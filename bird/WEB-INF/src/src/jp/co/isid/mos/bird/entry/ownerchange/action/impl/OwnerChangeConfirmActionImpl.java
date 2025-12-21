/*
 * 作成日:2007/01/19
 */
package jp.co.isid.mos.bird.entry.ownerchange.action.impl;

import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.entry.ownerchange.action.OwnerChangeConfirmAction;
import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dto.OwnerChangeDto;
import jp.co.isid.mos.bird.entry.ownerchange.logic.RenewStaffInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * オーナー間異動登録　確認画面アクション
 * @author xkonishi
 *
 */
public class OwnerChangeConfirmActionImpl implements OwnerChangeConfirmAction {

    /**
     * アクションID定義
     */
    public static final String initialize_ACTION_ID = "BEN023A12";
    
    public static final String regist_ACTION_ID = "BEN023A13";
    
    public static final String back_ACTION_ID = "BEN023A14";
    
    /**
     * 加盟店スタッフマスタ更新ロジック
     */
    private RenewStaffInfoLogic renewStaffInfoLogic;
    /**
     * オーナー間異動登録DTO
     */
    private OwnerChangeDto ownerChangeDto;
    
    /**
     * ユーザー情報
     */
    private BirdUserInfo birdUserInfo;

    /**
     * セッションキー作成クラス
     */
    private MakeSessionKey makeSessionKey = new MakeSessionKey();
    
    
    /**
     * 初期処理
     */
    public String initialize() {

        return null;
    }

    /**
     * 登録
     */
    public String regist() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        
        // ロジック【加盟店スタッフマスタ更新】実行。
        renewStaffInfoLogic.execute(ownerChangeDto.getChangeOnerCd(),
                                    ownerChangeDto.getChangeMiseCd(),
                                    ownerChangeDto.getMoveDt(),
                                    birdUserInfo.getUserID(),
                                    ownerChangeDto.getMstStaffInfo());
        
        // 会社リストの先頭の情報を取得。
        MstUserCompany mstUserCompany = (MstUserCompany) ownerChangeDto.getCompanyList()
                                        .get(OwnerChangeConstants.FIRST_ELEMENT);
        
        // 初期化
        ownerChangeDto.setCompanyCd(mstUserCompany.getCompanyCd());
        ownerChangeDto.setKbn(OwnerChangeConstants.KBN_MISE);
        ownerChangeDto.setMiseCd(null);
        ownerChangeDto.setOnerCd(null);
        
        // 条件画面に遷移
        return OwnerChangeConstants.VIEW_ID_CONDITION;
    }

    /**
     * 戻る
     */
    public String back() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        // 編集画面に遷移
        return OwnerChangeConstants.VIEW_ID_EDIT;
    }

    /**
     * セッションキーチェック
     * @return セッションキーチェック結果
     */
    private boolean isValidSessionKey() {
         boolean result = makeSessionKey.isValidSessionKey(
                 ownerChangeDto.getNowSessionKey(), ownerChangeDto.getSessionKey());
         
         return result;
    }

    
    public OwnerChangeDto getOwnerChangeDto() {
        return ownerChangeDto;
    }

    public void setOwnerChangeDto(OwnerChangeDto ownerChangeDto) {
        this.ownerChangeDto = ownerChangeDto;
    }

    public RenewStaffInfoLogic getRenewStaffInfoLogic() {
        return renewStaffInfoLogic;
    }

    public void setRenewStaffInfoLogic(RenewStaffInfoLogic renewStaffInfoLogic) {
        this.renewStaffInfoLogic = renewStaffInfoLogic;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
}