package jp.co.isid.mos.bird.bizreport.zendougeturegist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.zendougeturegist.action.ZenDougetuRegistCondAction;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.common.ZenDougetuRegistConstants;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.dto.ZenDougetuRegistDto;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.CheckEditableLogic;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistEmptyDataLogic;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistHolidayLogic;
import jp.co.isid.mos.bird.bizreport.zendougeturegist.logic.ZenDougetuRegistInfoLogic;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 前年同月設定登録　条件画面
 * @author inazawa
 * 2007/02/27
 */
public  class ZenDougetuRegistCondActionImpl implements ZenDougetuRegistCondAction {
    /*アクションID*/
    public static final String initialize_ACTION_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"A01";
    public static final String execute_ACTION_ID = ZenDougetuRegistConstants.SUB_MENU_ID+"A02";

    /*編集可能なアプリ日付との差(月)*/
    //private static final int EDITABLE_MONTH_SA = 1;
    /*[プルダウンDTO]**/
    private PullDownMenuDto pullDownMenuDto;
    /*[前年同月設定登録DTO]**/
    private ZenDougetuRegistDto zenDougetuRegistDto;
    /*LOGIC[ユーザー所属会社]**/
    private GetUserCompanyLogic zenDougetuRegistGetUserCompanyLogic;
    /*LOGIC[前年同月設定取得ロジック]**/
    private ZenDougetuRegistInfoLogic zenDougetuRegistInfoLogic;
    /*LOGIC[空データ処理ロジック]**/
    private ZenDougetuRegistEmptyDataLogic zenDougetuRegistEmptyDataLogic;
    /*LOGIC[祝祭日取得取得ロジック]**/
    private ZenDougetuRegistHolidayLogic zenDougetuRegistHolidayLogic;
    /*編集可能チェック*/
    private CheckEditableLogic zenDougetuRegistCheckEditableLogic;

    
    
    /**
     * 初期画面
     * @return 遷移先 null：自画面遷移
     */
    public String initialize() {
        if(getPullDownMenuDto().isClearFlg()){
            getPullDownMenuDto().setClearFlg(false);
            
            // ウインドウID生成
            getZenDougetuRegistDto().updateWindowid();
            //クリア処理
            getZenDougetuRegistDto().clear();
            
            MakeSessionKey makeSessionKey = new MakeSessionKey();
            // セッションキーを生成し、DTOにセット。
            String sessionKey = makeSessionKey._makeSessionKey();
            getZenDougetuRegistDto().setSessioniKey(sessionKey);
            getZenDougetuRegistDto().setViewSessionKey(sessionKey);

            //ユーザーＩＤ，システム日付,アプリ日付を設定
            getZenDougetuRegistDto().setUserId(getBirdUserInfo().getUserID());
            getZenDougetuRegistDto().setSysDate(getBirdDateInfo().getSysDate());
            getZenDougetuRegistDto().setApplyDate(getBirdDateInfo().getAppDate());
            getZenDougetuRegistDto().setUsertypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            
            if(!getBirdUserInfo().getMstUser().getUserTypeCd().equals(ZenDougetuRegistConstants.HONBU_USER)){
                //本部以外のユーザーは使用権限のExceptionを発生
                throw new CannotAccessException();
            }
            //ユーザー所属会社取得
            setUserCompany();
            //対象期間取得
            setTaishouNendo();
        }
        
        return null;
    }
    /**
     * 対象年度作成
     */
    private void setTaishouNendo() {
        String kijyunNendo;
        try {
            //一番最初の年月を取得
            kijyunNendo = DateManager.getPrevYear(DateManager.getCurrentYear(getZenDougetuRegistDto().getApplyDate()),1) + ZenDougetuRegistConstants.APRIL;
        } catch (Exception e1) {
            throw new FtlSystemException("前年同月設定登録初期処理");
        }
        List taishoKikanList = new ArrayList();
        DateFormatter formatter = new DateFormatter();
        for(int i = 0 ; 36 > i ; i++) {
            String yyyyMm;
            try {
                yyyyMm = DateManager.getNextMonth(kijyunNendo,i);
            } catch (Exception e) {
                throw new FtlSystemException("前年同月設定登録初期処理");
            }
            taishoKikanList.add(new SelectItem(yyyyMm,formatter.format
                    (yyyyMm, DateFormatter.PATTERN_MONTH_SLASH,DateFormatter.DATE_TYPE_YM)));
        }
        
        //初期フォーカスを当月が選択されるようにする
        try {
            getZenDougetuRegistDto().setTaishoNendo(getZenDougetuRegistDto().getApplyDate().substring(0,6));
        } catch (Exception e) {
            throw new FtlSystemException("前年同月設定登録初期処理");
        }
        getZenDougetuRegistDto().setListTaishouNendo(taishoKikanList);
    }

    /**
     * ユーザー所属会社取得
     */
    private void setUserCompany() {
        List listUserRCompany = getZenDougetuRegistGetUserCompanyLogic().execute(getZenDougetuRegistDto().getUserId(),null);
        getZenDougetuRegistDto().setListUserRCompany(listUserRCompany);
        if(listUserRCompany != null && listUserRCompany.size()>0){
            getZenDougetuRegistDto().setListUserRCompany(listUserRCompany);
            MstUserCompany mstUserCompany = (MstUserCompany)listUserRCompany.get(0);
            getZenDougetuRegistDto().setCompanyCd(mstUserCompany.getCompanyCd());
        }else{
            //データが無い場合は空リスト生成
            getZenDougetuRegistDto().setListUserRCompany(new ArrayList());
        }
    }
    /**
     * 検索処理
     * @return 遷移先VIEW_ID
     */
    public String execute() {
        //複数ウィンドウチェック
        MakeSessionKey makeSessionKey = new MakeSessionKey();
        if(!makeSessionKey.isValidSessionKey( 
                getZenDougetuRegistDto().getSessionKey()
                ,  getZenDougetuRegistDto().getViewSessionKey())){
            return ZenDougetuRegistConstants.operationErr_VIEW_ID;
            
        }
        //対象日付が過去か未来か
        String viewId = "";
        //--2007/11/28 update start 上期置換え済みの年月は、編集不可にする
//        //--2007/11/19 update start 前月も編集可にする
////        if(getZenDougetuRegistDto().getTaishoNendo().compareTo(getZenDougetuRegistDto().getApplyDate().substring(0,6)) >= 0){
//        String editableMonthFrom = "";
//        try {
//            editableMonthFrom = DateManager.getPrevMonth(getZenDougetuRegistDto().getApplyDate().substring(0,6), EDITABLE_MONTH_SA);
//        }
//        catch (Exception ex) {
//            throw new FtlSystemException("日付", "", ex);
//        }
//        if(getZenDougetuRegistDto().getTaishoNendo().compareTo(editableMonthFrom) >= 0) 
//        {
//        //--2007/11/19 update end
//            //未来なら編集画面へ
//            viewId = ZenDougetuRegistConstants.EDIT_VIEWID; 
//            getZenDougetuRegistDto().setPastYearFlg(ZenDougetuRegistConstants.DATE_FUTURE);
//        }else{
//            //過去なら確認画面へ
//            viewId = ZenDougetuRegistConstants.CONFIRM_VIEWID; 
//            getZenDougetuRegistDto().setPastYearFlg(ZenDougetuRegistConstants.DATE_PAST);
//        }
        boolean isEditable = getZenDougetuRegistCheckEditableLogic().execute(getZenDougetuRegistDto());
        if (isEditable) {
            viewId = ZenDougetuRegistConstants.EDIT_VIEWID;
            getZenDougetuRegistDto().setPastYearFlg(ZenDougetuRegistConstants.DATE_FUTURE);
        }
        else {
            viewId = ZenDougetuRegistConstants.CONFIRM_VIEWID;
            getZenDougetuRegistDto().setPastYearFlg(ZenDougetuRegistConstants.DATE_PAST);
        }
        //--2007/11/28 update end 
        //前年同月設定情報取得
        getZenDougetuRegistDto().setListZenDougetu(getZenDougetuRegistInfoLogic().search(getZenDougetuRegistDto()));
        //空データ処理
        getZenDougetuRegistDto().setListZenDougetu(getZenDougetuRegistEmptyDataLogic().getEmptyData(getZenDougetuRegistDto()));
        //祝祭日取得ロジック
        getZenDougetuRegistDto().setListZenDougetu(getZenDougetuRegistHolidayLogic().getHolidayInfo(getZenDougetuRegistDto()));
        return viewId;
    }



    /**
     * pullDownMenuDtoを取得
     * @return pullDownMenuDto
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }


    /**
     * pullDownMenuDtoを設定
     * @param pullDownMenuDto
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
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
    /**
     * コンテナーの取得
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }

    /**
     * zenDougetuRegistGetUserCompanyLogicを取得
     * @return zenDougetuRegistGetUserCompanyLogic
     */
    public GetUserCompanyLogic getZenDougetuRegistGetUserCompanyLogic() {
        return zenDougetuRegistGetUserCompanyLogic;
    }

    /**
     * zenDougetuRegistGetUserCompanyLogicを設定
     * @param zenDougetuRegistGetUserCompanyLogic
     */
    public void setZenDougetuRegistGetUserCompanyLogic(
            GetUserCompanyLogic zenDougetuRegistGetUserCompanyLogic) {
        this.zenDougetuRegistGetUserCompanyLogic = zenDougetuRegistGetUserCompanyLogic;
    }
    /**
     * zenDougetuRegistInfoLogicを取得
     * @return zenDougetuRegistInfoLogic
     */
    public ZenDougetuRegistInfoLogic getZenDougetuRegistInfoLogic() {
        return zenDougetuRegistInfoLogic;
    }
    /**
     * zenDougetuRegistInfoLogicを設定
     * @param zenDougetuRegistInfoLogic
     */
    public void setZenDougetuRegistInfoLogic(
            ZenDougetuRegistInfoLogic zenDougetuRegistInfoLogic) {
        this.zenDougetuRegistInfoLogic = zenDougetuRegistInfoLogic;
    }
    /**
     * zenDougetuRegistEmptyDataLogicを取得
     * @return zenDougetuRegistEmptyDataLogic
     */
    public ZenDougetuRegistEmptyDataLogic getZenDougetuRegistEmptyDataLogic() {
        return zenDougetuRegistEmptyDataLogic;
    }
    /**
     * zenDougetuRegistEmptyDataLogicを設定
     * @param zenDougetuRegistEmptyDataLogic
     */
    public void setZenDougetuRegistEmptyDataLogic(
            ZenDougetuRegistEmptyDataLogic zenDougetuRegistEmptyDataLogic) {
        this.zenDougetuRegistEmptyDataLogic = zenDougetuRegistEmptyDataLogic;
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
    public CheckEditableLogic getZenDougetuRegistCheckEditableLogic() {
        return zenDougetuRegistCheckEditableLogic;
    }
    public void setZenDougetuRegistCheckEditableLogic(
            CheckEditableLogic zenDougetuRegistCheckEditableLogic) {
        this.zenDougetuRegistCheckEditableLogic = zenDougetuRegistCheckEditableLogic;
    }

}
