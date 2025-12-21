/*
 * 作成日:2007/01/19
 */
package jp.co.isid.mos.bird.entry.ownerchange.action.impl;

import jp.co.isid.mos.bird.entry.ownerchange.action.OwnerChangeSetUpAction;
import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dto.OwnerChangeDto;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;
import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * オーナー間異動登録　対象者設定画面アクション
 * @author xkonishi
 *
 */
public class OwnerChangeSetUpActionImpl implements OwnerChangeSetUpAction {
    
    /**
     * アクションID定義
     */
    public static final String initialize_ACTION_ID = "BEN023A05";
    
    public static final String select_ACTION_ID = "BEN023A06";
    
    public static final String back_ACTION_ID = "BEN023A07";
    
    /**
     * オーナー間異動登録Dto
     */
    private OwnerChangeDto ownerChangeDto;
    
    /**
     * エントリー状況チェックロジック
     */
    private CheckEntryLogic checkEntryLogic;
    
    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * インデックス
     */
    private int index;

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
     * 対象者選択
     */
    public String select() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;
        }
        
        // DTO.スタッフ情報リストより、対象スタッフ情報を取得。
        MstStaffInfo mstStaffInfo = (MstStaffInfo) ownerChangeDto.getStaffInfoList().get(getIndex());
        // ロジック【エントリー状況チェック】を実行。
        checkEntryLogic.execute(birdDateInfo.getSysDate(), mstStaffInfo);
        
        ownerChangeDto.setMstStaffInfo(mstStaffInfo);
        
        // 初期化
        ownerChangeDto.setChangeOnerCd(null);
        ownerChangeDto.setChangeOnerName(null);
        ownerChangeDto.setMiseList(null);
        ownerChangeDto.setMoveDt(null);
        
        // 編集画面に遷移
        return OwnerChangeConstants.VIEW_ID_EDIT;
    }

    /**
     * 戻る
     */
    public String back() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;
        }

        // 条件画面に遷移。
        return OwnerChangeConstants.VIEW_ID_CONDITION;
    }

    /**
     * セッションキーチェック
     * @return セッションキーチェック結果
     */
    private boolean isValidSessionKey(){
         boolean result = makeSessionKey.isValidSessionKey(
                 ownerChangeDto.getNowSessionKey(), ownerChangeDto.getSessionKey());
         
         return result;
    }

    
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }

    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }

    public OwnerChangeDto getOwnerChangeDto() {
        return ownerChangeDto;
    }

    public void setOwnerChangeDto(OwnerChangeDto ownerChangeDto) {
        this.ownerChangeDto = ownerChangeDto;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}