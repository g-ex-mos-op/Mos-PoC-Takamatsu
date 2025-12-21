package jp.co.isid.mos.bird.bizsupport.tempstorereplace.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.action.TempStoreReplaceConfirmAction;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dto.TempStoreReplaceDto;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.StateListRegistLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;



/**
 * 仮店舗置換えメンテナンス確認Action
 * 
 * @author Aspac
 */
public class TempStoreReplaceConfirmActionImpl implements TempStoreReplaceConfirmAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BBS025A03";
    
    /** ViewID定義 */
    public static final String condition_VIEW_ID    = "BBS025V01";//検索画面(次画面)
    public static final String stateEdit_VIEW_ID    = "BBS025V02";//編集画面(前画面)
    public static final String stateConfirm_VIEW_ID = "BBS025V03";//確認画面

    
    /**
     * ユーザ関連情報
     */
    private BirdUserInfo birdUserInfo;
    
    
    /**
     * S2コンテナを取得します。
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザ関連情報を取得します。
     * @return birdUserInfo
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRDユーザ関連情報を設定します。
     * @param birdUserInfo
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    
    
    
    /**
     * 仮店舗置換えDTO
     */
    private TempStoreReplaceDto tempStoreReplaceDto;

    /**
     * 仮店舗置換えDTOを取得します。
     * @return 仮店舗置換えDTO 
     */
    public TempStoreReplaceDto getTempStoreReplaceDto() {
        return tempStoreReplaceDto;
    }
    /**
     * 仮店舗置換えDTOを設定します。
     * @param 仮店舗置換えDTO 
     */
    public void setTempStoreReplaceDto(TempStoreReplaceDto tempStoreReplaceDto) {
        this.tempStoreReplaceDto = tempStoreReplaceDto;
    }
    
    
    

    /**
     * 仮店舗置換え状況更新ロジック
     */
    private StateListRegistLogic stateListRegistLogic;
       
    /**
     * 仮店舗置換え状況更新ロジックを取得します。
     * @return 仮店舗置換え状況更新ロジック 
     */
    public StateListRegistLogic getStateListRegistLogic() {
        return stateListRegistLogic;
    }
    /**
     * 仮店舗置換え状況更新ロジックを設定します。
     * @param 仮店舗置換え状況更新ロジック 
     */
    public void setStateListRegistLogic(StateListRegistLogic stateListRegistLogic) {
        this.stateListRegistLogic = stateListRegistLogic;
    }
    
    
    
    
    /**
     * 初期表示
     * @return
     */
    public String initialize() {
        return null;
    }

    
    /**
     * 戻るボタンAction
     * @return
     */
    public String back(){
                
        return stateEdit_VIEW_ID;
    }
    
    
    
    /**
     * 登録ボタンAction
     * @return
     */
    public String entry() {
        
        String userid = getBirdUserInfo().getUserID();
        
        List stateList = getTempStoreReplaceDto().getListState();
        String companyCd = getTempStoreReplaceDto().getCondCompanyCd();
        String nendo = getTempStoreReplaceDto().getCondNendo();
        
        getStateListRegistLogic().execute(stateList, companyCd, nendo, userid);
        getTempStoreReplaceDto().reset();
        
        return condition_VIEW_ID;

    }

    
}
