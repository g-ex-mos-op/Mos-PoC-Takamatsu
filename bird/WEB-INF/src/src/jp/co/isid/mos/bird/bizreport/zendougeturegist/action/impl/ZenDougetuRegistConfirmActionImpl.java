package jp.co.isid.mos.bird.bizreport.zendougeturegist.action.impl;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.action.ZenDougetuRegistConfirmAction;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
/**
 * 確認画面処理
 * @author inazawa
 * 2007/02/27
 */
public class ZenDougetuRegistConfirmActionImpl implements ZenDougetuRegistConfirmAction{
    /*LOGIC[登録処理]]**/
    private ZenDougetuRegistLogic zenDougetuRegistLogic; 
    /*[前年同月設定登録DTO]**/
    private ZenDougetuRegistDto zenDougetuRegistDto;
    /*アクションID*/
    public static final String regist_ACTION_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"A05";
    public static final String back_ACTION_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"A06";

    /**
     * 初期処理
     * @return 遷移先VIEW_ID　nullなら自画面遷移
     */
    public String initialize() {
        //複数ウィンドウチェック
        /* セッションキー作成クラス生成 */
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        if(!makeSessionKey.isValidSessionKey( 
                getZenDougetuRegistDto().getSessionKey()
                ,  getZenDougetuRegistDto().getViewSessionKey())){
            return ZenDougetuRegistConstants.operationErr_VIEW_ID;
        }
        return null;
    }
    /**
     * 登録処理
     * @return 遷移先VIEW_ID　nullなら自画面遷移
     */
    public String regist() {
        //複数ウィンドウチェック
        /* セッションキー作成クラス生成 */
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        if(!makeSessionKey.isValidSessionKey( 
                getZenDougetuRegistDto().getSessionKey()
                ,  getZenDougetuRegistDto().getViewSessionKey())){
            return ZenDougetuRegistConstants.operationErr_VIEW_ID;
            
        }
        //登録・変更処理
        getZenDougetuRegistLogic().regist(getZenDougetuRegistDto());
        getZenDougetuRegistDto().setCompanyCd(null);
        try {
            getZenDougetuRegistDto().setTaishoNendo(getZenDougetuRegistDto().getApplyDate().substring(0,6));
        } catch (Exception e) {
            throw new FtlSystemException("前年同月設定登録後処理");
        }
        return ZenDougetuRegistConstants.COND_VIEWID;
    }
    /**
     * 戻る処理
     * @return 遷移先VIEW_ID　nullなら自画面遷移
     */
    public String back() {
        //複数ウィンドウチェック
        /* セッションキー作成クラス生成 */
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        if(!makeSessionKey.isValidSessionKey( 
                getZenDougetuRegistDto().getSessionKey()
                ,  getZenDougetuRegistDto().getViewSessionKey())){
            return ZenDougetuRegistConstants.operationErr_VIEW_ID;
            
        }

        //対象日付が過去か未来か
        String viewId = "";
        if(getZenDougetuRegistDto().getPastYearFlg().equals(ZenDougetuRegistConstants.DATE_FUTURE)){
            //未来なら編集画面へ
            viewId = ZenDougetuRegistConstants.EDIT_VIEWID; 
        }else{
            //過去なら条件画面へ
            viewId = ZenDougetuRegistConstants.COND_VIEWID; 
        }
        return viewId;
    }
    /**
     * zenDougetuRegistLogicを取得
     * @return zenDougetuRegistLogic
     */
    public ZenDougetuRegistLogic getZenDougetuRegistLogic() {
        return zenDougetuRegistLogic;
    }
    /**
     * zenDougetuRegistLogicを設定
     * @param zenDougetuRegistLogic
     */
    public void setZenDougetuRegistLogic(ZenDougetuRegistLogic zenDougetuRegistLogic) {
        this.zenDougetuRegistLogic = zenDougetuRegistLogic;
    }
    /**
     * zenDougetuRegistDtoを取得
     * @return zenDougetuRegistDto
     */
    public ZenDougetuRegistDto getZenDougetuRegistDto() {
        return zenDougetuRegistDto;
    }

    /**
     * zenDougetuRegistDtoを設定
     * @param zenDougetuRegistDto
     */
    public void setZenDougetuRegistDto(ZenDougetuRegistDto zenDougetuRegistDto) {
        this.zenDougetuRegistDto = zenDougetuRegistDto;
    }

}
