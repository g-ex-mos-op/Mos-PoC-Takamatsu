package jp.co.isid.mos.bird.bizreport.zendougeturegist.action.impl;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.action.ZenDougetuRegistEditAction;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistCheckLogic;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistHolidayLogic;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
/**
 * 編集画面処理
 * @author inazawa
 * 2007/02/27
 */
public class ZenDougetuRegistEditActionImpl implements ZenDougetuRegistEditAction{
    
    /*LOGIC[エラーチェック]]**/
    private ZenDougetuRegistCheckLogic zenDougetuRegistCheckLogic; 
    /*アクションID*/
    public static final String execute_ACTION_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"A03";
    public static final String back_ACTION_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"A04";

    
    /*[前年同月設定登録DTO]**/
    private ZenDougetuRegistDto zenDougetuRegistDto;
    /*LOGIC[祝祭日取得取得ロジック]**/
    private ZenDougetuRegistHolidayLogic zenDougetuRegistHolidayLogic;

    /**
     * 初期処理
     */
    public String initialize() {
        return null;
    }
    /**
     * エラーチェック
     */
    public String execute() {
        //複数ウィンドウチェック
        /* セッションキー作成クラス生成 */
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        if(!makeSessionKey.isValidSessionKey( 
                getZenDougetuRegistDto().getSessionKey()
                ,  getZenDougetuRegistDto().getViewSessionKey())){
            return ZenDougetuRegistConstants.operationErr_VIEW_ID;
            
        }
        //エラーチェック
        getZenDougetuRegistCheckLogic().validate(getZenDougetuRegistDto());
        //祝祭日取得
        getZenDougetuRegistHolidayLogic().getHolidayInfo(getZenDougetuRegistDto());
        return ZenDougetuRegistConstants.CONFIRM_VIEWID;
    }
    /**
     * 戻る処理
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
        return ZenDougetuRegistConstants.COND_VIEWID;
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

    /** zenDougetuRegistCheckLogicを取得
     * @return zenDougetuRegistCheckLogic
     */
    public ZenDougetuRegistCheckLogic getZenDougetuRegistCheckLogic() {
        return zenDougetuRegistCheckLogic;
    }
    /**
     * zenDougetuRegistCheckLogicを設定
     * @param zenDougetuRegistCheckLogic
     */
    public void setZenDougetuRegistCheckLogic(
            ZenDougetuRegistCheckLogic zenDougetuRegistCheckLogic) {
        this.zenDougetuRegistCheckLogic = zenDougetuRegistCheckLogic;
    }
    /**
     * zenDougetuRegistHolidayLogicを取得
     * @return zenDougetuRegistHolidayLogic
     */
    public ZenDougetuRegistHolidayLogic getZenDougetuRegistHolidayLogic() {
        return zenDougetuRegistHolidayLogic;
    }
    /**
     * zenDougetuRegistHolidayLogicを設定
     * @param zenDougetuRegistHolidayLogic
     */
    public void setZenDougetuRegistHolidayLogic(
            ZenDougetuRegistHolidayLogic zenDougetuRegistHolidayLogic) {
        this.zenDougetuRegistHolidayLogic = zenDougetuRegistHolidayLogic;
    }





}
