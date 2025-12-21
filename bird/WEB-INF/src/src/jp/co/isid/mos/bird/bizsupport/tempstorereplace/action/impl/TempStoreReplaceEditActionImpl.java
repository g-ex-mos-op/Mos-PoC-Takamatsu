package jp.co.isid.mos.bird.bizsupport.tempstorereplace.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.action.TempStoreReplaceEditAction;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dto.TempStoreReplaceDto;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreStateList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.StateListCheckLogic;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;



/**
 * 仮店舗置換えメンテナンス編集Action
 * 
 * @author Aspac
 */
public class TempStoreReplaceEditActionImpl implements TempStoreReplaceEditAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BBS025A02";
    
    /** ViewID定義 */
    public static final String condition_VIEW_ID    = "BBS025V01";//検索画面(前画面)
    public static final String stateEdit_VIEW_ID    = "BBS025V02";//編集画面
    public static final String stateConfirm_VIEW_ID = "BBS025V03";//確認画面(次画面)
    
    private static final String VIEWID_MISESEARCH = "BCO008V01";//店舗検索画面

    /**
     * selectIndex
     */
    private int selectIndex;
    
    /**
     * selectIndexを取得します。
     * @return selectIndex
     */
    public int getSelectIndex() {
        return selectIndex;
    }
    /**
     * selectIndexを設定します。
     * @param selectIndex
     */
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    
    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;
    /**
     * 仮店舗置換えメンテナンスDTO
     */
    private TempStoreReplaceDto tempStoreReplaceDto;
    private TempStoreReplaceDto tempStoreReplaceRequestDto;
    /**
     * 店舗情報DTO【共通】
     */
    private MiseSearchDto miseSearchDto;
    

    
    /**
     * 店舗情報取得ロジック
     */
    private GetMiseLogic getMiseLogic;
    
    /**
     * 仮店舗置換え編集チェックロジック
     */
    private StateListCheckLogic stateListCheckLogic;
    
    
    /**
     * 仮店舗置換えメンテナンスを取得します。
     * @return 仮店舗置換えメンテナンス
     */
    public TempStoreReplaceDto getTempStoreReplaceDto() {
        return tempStoreReplaceDto;
    }
    /**
     * 仮店舗置換えメンテナンスを設定します。
     * @param 仮店舗置換えメンテナンス
     */
    public void setTempStoreReplaceDto(TempStoreReplaceDto tempStoreReplaceDto) {
        this.tempStoreReplaceDto = tempStoreReplaceDto;
    }

    /**
     * メニューDTOを取得します。
     * @return
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * メニューDTOを設定します
     * @param pullDownMenuDto
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    
    

    /**
     * 店舗情報DTO【共通】を取得します。
     * @return 店舗情報DTO【共通】
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * 店舗情報DTO【共通】を設定します。
     * @param 店舗情報DTO【共通】
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }


    
    /**
     * 店舗情報ロジックを取得します。
     * @return 店舗情報ロジック
     */
    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }
    /**
     * 店舗情報ロジックを設定します。
     * @param 店舗情報ロジック
     */
    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }
    

    /**
     * 仮店舗置換え編集チェックロジックを取得します。
     * @return 仮店舗置換え編集チェックロジック
     */
    public StateListCheckLogic getStateListCheckLogic() {
        return stateListCheckLogic;
    }
    /**
     * 仮店舗置換え編集チェックロジックを設定します。
     * @param 仮店舗置換え編集チェックロジック
     */
    public void setStateListCheckLogic(StateListCheckLogic stateListCheckLogic) {
        this.stateListCheckLogic = stateListCheckLogic;
    }
    
    
    
    /**
     * 初期表示
     * @return
     */
    public String initialize() {
        
        // 店検索画面からの戻り(店決定・戻るボタン)
        if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
            getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
            getTempStoreReplaceRequestDto().setSelectIndex(getTempStoreReplaceDto().getSelectIndex());
        }
        // 店が選択された場合
        if (getMiseSearchDto().isActionFlg()) {
            // アクションフラグ設定
            getMiseSearchDto().setActionFlg(false);
            
            //店舗情報を取得
            String miseCd = getMiseSearchDto().getMiseCd();
            MstMise mstMise = getGetMiseLogic().execute(getTempStoreReplaceDto().getCondCompanyCd(),miseCd);
            String miseName = mstMise.getMiseNameKj();
            String openDt = mstMise.getOpenDt();
            String closeDt = mstMise.getCloseDt();
            
            String sysdate = getTempStoreReplaceDto().getSysdate();
            
            //置換日(確定日)初期設定
            String okikaeDt = "";
            try {
                if(isNull(openDt)) {
                    String pre7Dt = DateManager.getPrevDate(openDt,7);
                    //オープン日の７日前よりシステム日付けが過去
                    if(pre7Dt.compareTo(sysdate) > 0) {
                        okikaeDt = pre7Dt;
                    }
                    //オープン日の７日前よりシステム日付けが未来
                    else {
                        //システム日付けの翌日を設定
                        okikaeDt = DateManager.getNextDate(sysdate,1);
                    }
                }
                else {
                    okikaeDt = DateManager.getNextDate(sysdate,1);
                }
            } catch (Exception e) {
                throw new FtlSystemException(
                "編集画面表示処理中に置換え日設定でエラーが発生しました。");
            }
            
            // 取得した店舗情報をDTOに設定
            List list = getTempStoreReplaceDto().getListState();
            TrnTempStoreStateList stateInfo = (TrnTempStoreStateList)list.get(getTempStoreReplaceRequestDto().getSelectIndex());
            stateInfo.setMiseCd(miseCd);
            stateInfo.setMiseNameKj(miseName);
            stateInfo.setOpenDt(openDt);
            stateInfo.setCloseDt(closeDt);
            stateInfo.setKakuteiDt(okikaeDt);
        }
        return null;
    }

    
    /**
     * 戻るボタンAction
     * @return
     */
    public String back(){
                
        getTempStoreReplaceDto().setProcessFlg(true);
        return condition_VIEW_ID;
    }
    
    
    /**
     * 店舗情報検索
     * @return
     */
    public String searchStore(){
        
        getMiseSearchDto().clear();
        getMiseSearchDto().setActionFlg(true);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNavigationCase("BBS025V02");
        getMiseSearchDto().setNeedReturnKind(true);
        getTempStoreReplaceDto().setSelectIndex(getSelectIndex());
        
        return VIEWID_MISESEARCH;
        
    }
    
    
    /**
     * 確認ボタンAction
     * @return
     */
    public String confirm() {

        //仮店舗置換え編集チェック
        getStateListCheckLogic().execute(
                getTempStoreReplaceDto().getListState(),
                getTempStoreReplaceDto().getSysdate(),
                getTempStoreReplaceDto().getCondCompanyCd());
        
        return stateConfirm_VIEW_ID;

    }
    
    
    /**
     * Nullあるいは空を判断する
     * @param str
     * @return true:Nullまたは空
     */
    private boolean isNull(String str) {
        if(str==null || str.equals("")) return true;
        return false;
    }
    public TempStoreReplaceDto getTempStoreReplaceRequestDto() {
        return tempStoreReplaceRequestDto;
    }
    public void setTempStoreReplaceRequestDto(
            TempStoreReplaceDto tempStoreReplaceRequestDto) {
        this.tempStoreReplaceRequestDto = tempStoreReplaceRequestDto;
    }

}
