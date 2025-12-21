/*
 * 作成日:2007/01/19
 */
package jp.co.isid.mos.bird.entry.ownerchange.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.entry.ownerchange.action.OwnerChangeEditAction;
import jp.co.isid.mos.bird.entry.ownerchange.common.OwnerChangeConstants;
import jp.co.isid.mos.bird.entry.ownerchange.dto.OwnerChangeDto;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstMiseInfo;
import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckChangeOwnerLogic;
import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckRegistOwnerLogic;
import jp.co.isid.mos.bird.entry.ownerchange.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * オーナー間異動登録 編集画面アクション
 * @author xkonishi
 *
 */
public class OwnerChangeEditActionImpl implements OwnerChangeEditAction {

    /**
     * アクションID定義
     */
    public static final String initialize_ACTION_ID = "BEN023A08";
    
    public static final String confirm_ACTION_ID = "BEN023A09";
    
    public static final String ownerSearch_ACTION_ID = "BEN023A10";

    public static final String back_ACTION_ID = "BEN023A11";
    
    /**
     * オーナー間異動DTO
     */
    private OwnerChangeDto ownerChangeDto;
    
    /**
     * 変更後オーナーチェックロジック
     */
    private CheckChangeOwnerLogic checkChangeOwnerLogic;

    /**
     * 登録前オーナーチェック
     */
    private CheckRegistOwnerLogic checkRegistOwnerLogic;
    
    /**
     * 店舗情報取得ロジック
     */
    private GetMiseInfoLogic getMiseInfoLogic;

    /**
     * オーナー選択DTO
     */
    private OwnerSearchDto ownerSearchDto;
    
    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * セッションキー作成クラス
     */
    private MakeSessionKey makeSessionKey = new MakeSessionKey();

    
    /**
     * 初期処理
     */
    public String initialize() {
        // オーナー選択画面から遷移してきた場合
        if(ownerSearchDto.getReturnKind() != OwnerSearchDto.RETURNKIND_INIT){
            ownerSearchDto.setReturnKind(OwnerSearchDto.RETURNKIND_INIT);

            // ウインドウID反映
            ownerChangeDto.setWindowId(ownerSearchDto.getWindowId());

            // セッションキーチェック
            if(!isValidSessionKey()) {
                ownerSearchDto.setActionFlag(false);
                return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
            }

            // オーナー検索画面で、オーナーを決定した場合
            if (ownerSearchDto.isActionFlag()) {
                ownerSearchDto.setActionFlag(false);

                // DTOに変更後オーナーコード、変更後オーナー名をセット。
                ownerChangeDto.setChangeOnerCd(ownerSearchDto.getOnerCd());
                ownerChangeDto.setChangeOnerName(ownerSearchDto.getOnerNameKj());

                // ロジック【店舗一覧取得】を実行。
                List miseList = getMiseInfoLogic.execute(ownerChangeDto.getCompanyCd(),
                                                         ownerChangeDto.getChangeOnerCd(),
                                                         birdDateInfo.getSysDate());

                // ロジック【変更後オーナーチェック】を実行。
                boolean isEmpty = checkChangeOwnerLogic.execute(miseList);
                
                // オープンしている店が無い場合
                if (isEmpty) {
                    ownerChangeDto.setChangeOnerCd(null);
                    ownerChangeDto.setChangeOnerName(null);
                    ownerChangeDto.setMiseList(null);
                    throw new NotSelectedException(
                            "選択されたオーナーの保有店舗は全てクローズしています。別のオーナーを", "changeOwner", null);

                } else {
                    // DTOに店舗一覧をセット。
                    ownerChangeDto.setMiseList(miseList);
                }
            }
        }
        return null;
    }

    /**
     * 確認
     */
    public String confirm() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;            
        }
        
        // ロジック【登録前オーナーチェック】を実行。
        checkRegistOwnerLogic.execute(ownerChangeDto.getMstStaffInfo().getOnerCd(),
                                      ownerChangeDto.getChangeOnerCd(),
                                      ownerChangeDto.getMoveDt());
        // 変更後店舗名称取得
        List miseList = ownerChangeDto.getMiseList();
        String miseCd = ownerChangeDto.getChangeMiseCd();
        
        for(int i = 0; i < miseList.size(); i++) {
            MstMiseInfo mstMiseInfo = (MstMiseInfo) miseList.get(i);
            
            if (miseCd.equals(mstMiseInfo.getMiseCd())) {
                // DTOに変更後店名称をセット。
                ownerChangeDto.setChangeMiseName(mstMiseInfo.getMiseNameKj());
                break;
            }
        }
        // 確認画面に遷移
        return OwnerChangeConstants.VIEW_ID_CONFIRM;
    }
    
    /**
     * オーナー検索
     */
    public String ownerSearch() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;
        }
        
        // 初期化
        ownerSearchDto.clear();
        ownerSearchDto.setInitFlag(true);
        
        List companyCdList = new ArrayList();
        companyCdList.add(ownerChangeDto.getCompanyCd());
        
        // オーナー選択DTOにセット
        ownerSearchDto.setRCompanyCdList(companyCdList);
        ownerSearchDto.setNavigationCase(OwnerChangeConstants.VIEW_ID_EDIT);
        ownerSearchDto.setNeedReturnKind(true);
        ownerSearchDto.setWindowId(ownerChangeDto.getWindowId());
        
        // オーナー選択画面へ遷移
        return OwnerChangeConstants.VIEW_ID_OWNERSEARCH;
    }
    
    /**
     * 戻る
     */
    public String back() {
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return OwnerChangeConstants.VIEW_ID_OPERATION_ERR;
        }
        
        // 編集画面の入力項目を初期化
        ownerChangeDto.setChangeMiseCd(null);
        ownerChangeDto.setChangeMiseName(null);
        ownerChangeDto.setChangeOnerCd(null);
        ownerChangeDto.setChangeOnerName(null);
        
        // 対象者設定画面に遷移。
        return OwnerChangeConstants.VIEW_ID_SETUP;
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
    
    
    public CheckChangeOwnerLogic getCheckChangeOwnerLogic() {
        return checkChangeOwnerLogic;
    }

    public void setCheckChangeOwnerLogic(CheckChangeOwnerLogic checkChangeOwnerLogic) {
        this.checkChangeOwnerLogic = checkChangeOwnerLogic;
    }

    public CheckRegistOwnerLogic getCheckRegistOwnerLogic() {
        return checkRegistOwnerLogic;
    }

    public void setCheckRegistOwnerLogic(CheckRegistOwnerLogic checkRegistOwnerLogic) {
        this.checkRegistOwnerLogic = checkRegistOwnerLogic;
    }

    public GetMiseInfoLogic getGetMiseInfoLogic() {
        return getMiseInfoLogic;
    }

    public void setGetMiseInfoLogic(GetMiseInfoLogic getMiseInfoLogic) {
        this.getMiseInfoLogic = getMiseInfoLogic;
    }

    public OwnerChangeDto getOwnerChangeDto() {
        return ownerChangeDto;
    }

    public void setOwnerChangeDto(OwnerChangeDto ownerChangeDto) {
        this.ownerChangeDto = ownerChangeDto;
    }
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
}