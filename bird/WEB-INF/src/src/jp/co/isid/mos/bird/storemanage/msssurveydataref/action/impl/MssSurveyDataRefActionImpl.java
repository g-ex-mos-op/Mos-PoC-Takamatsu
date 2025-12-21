package jp.co.isid.mos.bird.storemanage.msssurveydataref.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.framework.action.BatchExecuteAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.action.impl.FileUploadActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.action.MssSurveyDataRefAction;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyBatchDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyDataRefDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.dto.MssSuerveyUploadDto;
import jp.co.isid.mos.bird.storemanage.msssurveydataref.logic.MssSurveyCondLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
public class MssSurveyDataRefActionImpl implements MssSurveyDataRefAction{

    /*VIewID*/
    public static final String mssSurveyDataRefSelect_VIEW_ID =  "BSM012V01";
    public static final String mssSurveyDataBottom_VIEW_ID = "BSM012V02";
    /* アクションID */
    public static final String initialize_ACTION_ID =  "BSM012A01";
    public static final String executeStatus_ACTION_ID = "BSM012A02";
    public static final String clearErr_ACTION_ID =    "BSM012A03";
    public static final String execute_ACTION_ID =     "BSM012A04";
    public static final String upload_ACTION_ID =      "BSM012A05";
    public static final String back_ACTION_ID =        "BSM012A06";
    public static final String batchKick_ACTION_ID =   "BSM012A07";
    private static final int BATCH_KICK = 1;
    private static final int UOLOAD_KICK = 2;
    private static final String HONBU_USER = "01";
    /*フレームワークアクション*/
    private BatchExecuteAction mssBatchExecuteAction;
    private  FileUploadAction mssFileUploadActionImpl;
    private MssSurveyCondLogic mssSurveyCondLogicImpl;
    /*Dtoの設定*/
    private  MssSuerveyDataRefDto mssSuerveyDataRefDto;
    private  MssSuerveyUploadDto  mssSuerveyUploadDto;
    private  MssSuerveyBatchDto   mssSuerveyBatchDto;

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() throws Exception{
        //Dtoへシステム日付設定
       getMssSuerveyDataRefDto().setSysDate(getMssSuerveyDataRefDto().getBirdDateInfo().getSysDate());
       getMssSuerveyDataRefDto().setKijunNendo(getMssSuerveyDataRefDto().getSysDate().substring(0,4));
       String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
       getMssSuerveyDataRefDto().setUserType(userTypeCd);
       getMssSuerveyDataRefDto().setUserId(getBirdUserInfo().getMstUser().getUser_id());


       if(getPullDownMenuDto().isClearFlg()){
           getMssSuerveyDataRefDto().clear();
           getMssSuerveyUploadDto().clear();
           getMssSuerveyBatchDto().clear();
           getPullDownMenuDto().setClearFlg(false);    
           getMssSuerveyDataRefDto().setSelectFlg(0);
           getMssSuerveyUploadDto().setUpdateFlg(0);
           getMssSuerveyUploadDto().setRegistFlg(1);
           getMssSuerveyDataRefDto().setSelectFlg(BATCH_KICK);
       }
       List taishoNendo = new ArrayList();
       for(int nend = Integer.parseInt(DateManager.getCurrentYear(getMssSuerveyDataRefDto().getSysDate()));nend>=2006;nend--){
           String strNendo = String.valueOf(nend);
           SelectItem taishoSelect = new SelectItem(strNendo);
           taishoNendo.add( taishoSelect );
   
       }
       getMssSuerveyDataRefDto().setTaishoNendo(taishoNendo);
       getMssSuerveyDataRefDto().setReReadFlg(false);
          
       if(!getMssSuerveyDataRefDto().getUserType().equals(HONBU_USER)){
           //本部以外のユーザーは使用権限のExceptionを発生
           throw new CannotAccessException();
       }
       getMssSurveyCondLogicImpl().execute(getMssSuerveyDataRefDto());
       return null;
    }
    public String executeStatus() {
        
        getMssSurveyCondLogicImpl().batchStatus(getMssSuerveyBatchDto());
        getMssSuerveyDataRefDto().setReReadFlg(true);
        return null;
    }
    /**
     * 実行ボタン押下時
     * @return
     */
    public String execute(){
        getMssSuerveyBatchDto().setCompanyCd(mssSuerveyDataRefDto.getCompanyCd());
        mssSuerveyDataRefDto.setUpdateFlg(0);
        getMssBatchExecuteAction().executeBatch();
        return null;
    }
    public String clearErr(){
        getMssSurveyCondLogicImpl().batchClearErr();
        getMssSuerveyUploadDto().clear();
        return null;
    }
    
    
    /**
     * セッションクリア処
     */
    public String back() {
        getMssSuerveyUploadDto().setRegistFlg(1);
        return null;
    }
    /**
     * アップロードアクション
     */
    public String upload() {
        getMssSuerveyUploadDto().setShoriKbn(getMssSuerveyBatchDto().getShoriKbn());
        getMssFileUploadActionImpl().upload();
        return null;
    }
    

    /**
     * バッチアクション
     */
    public String batchKick() {
        getMssSuerveyDataRefDto().setSelectFlg(UOLOAD_KICK);
        getMssSuerveyBatchDto().setUserId(getMssSuerveyDataRefDto().getUserId());
        getMssBatchExecuteAction().executeBatch();
        return null;
    }

    /**
     * mssSuerveyDataRefDtoをゲット
     * @return mssSuerveyDataRefDto
     */
    public MssSuerveyDataRefDto getMssSuerveyDataRefDto() {
        return mssSuerveyDataRefDto;
    }
    /**
     * mssSuerveyDataRefDtoをセット
     * @param mssSuerveyDataRefDto
     */
    public void setMssSuerveyDataRefDto(MssSuerveyDataRefDto mssSuerveyDataRefDto) {
        this.mssSuerveyDataRefDto = mssSuerveyDataRefDto;
    }
    /**
     * pullDownMenuDtoの設定
     * @return pullDownMenuDto を戻します。
     */
    private PullDownMenuDto getPullDownMenuDto() {
           return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    public MssSuerveyUploadDto getMssSuerveyUploadDto() {
        return mssSuerveyUploadDto;
    }
    public void setMssSuerveyUploadDto(MssSuerveyUploadDto mssSuerveyUploadDto) {
        this.mssSuerveyUploadDto = mssSuerveyUploadDto;
    }
    public MssSuerveyBatchDto getMssSuerveyBatchDto() {
        return mssSuerveyBatchDto;
    }
    public void setMssSuerveyBatchDto(MssSuerveyBatchDto mssSuerveyBatchDto) {
        this.mssSuerveyBatchDto = mssSuerveyBatchDto;
    }
    public BatchExecuteAction getMssBatchExecuteAction() {
        return mssBatchExecuteAction;
    }
    public void setMssBatchExecuteAction(
            BatchExecuteAction mssBatchExecuteAction) {
        this.mssBatchExecuteAction = mssBatchExecuteAction;
    }
    public FileUploadAction getMssFileUploadActionImpl() {
        return mssFileUploadActionImpl;
    }
    public void setMssFileUploadActionImpl(
            FileUploadActionImpl mssFileUploadActionImpl) {
        this.mssFileUploadActionImpl = mssFileUploadActionImpl;
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    public MssSurveyCondLogic getMssSurveyCondLogicImpl() {
        return mssSurveyCondLogicImpl;
    }
    public void setMssSurveyCondLogicImpl(
            MssSurveyCondLogic mssSurveyCondLogicImpl) {
        this.mssSurveyCondLogicImpl = mssSurveyCondLogicImpl;
    }
  

}
