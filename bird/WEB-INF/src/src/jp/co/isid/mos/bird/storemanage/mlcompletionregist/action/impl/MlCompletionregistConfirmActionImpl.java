/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.impl;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistConfirmAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl.RegistLogicImpl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * マスタライセンス研修修了登録
 * 確認画面アクション
 * 
 * @author xkinu
 */
public class MlCompletionregistConfirmActionImpl
    implements MlCompletionregistConfirmAction {
  
    /*【ロジック】*/
    private RegistLogicImpl registLogicImpl;
    /*【DTO】*/
    private MlCompletionregistDto miseMaintenanceDto;
    
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
    	
        return null;
    }
    

    /**
     * 実行処理
     * @return  
     */
    public String execute() {
		// TODO システム日付情報セット
        S2Container container = SingletonS2ContainerFactory.getContainer();
		HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
		BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession().getAttribute("birdDateInfo");
    	getMlCompletionregistDto().setBirdDateInfo(birdDateInfo);
    	//  
        getRegistLogic().execute(getMlCompletionregistDto());        
        // 確認画面-->画面遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_INIT);
        // 条件画面を表示更新する為にtrue
        getPullDownMenuDto().setClearFlg(true);
        return VIEWID_CONDITION;
    }
    
    /**
     * 戻る
     * @return 
     */
    public String back() {
        // 条件画面-->編集画面の遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_CONF_TO_EDIT);
        return VIEWID_EDIT;
    }
    
    /**
     * @return miseMaintenanceDto を戻します。
     */
    public MlCompletionregistDto getMlCompletionregistDto() {
        return miseMaintenanceDto;
    }
    /**
     * @param miseMaintenanceDto miseMaintenanceDto を設定。
     */
    public void setMlCompletionregistDto(MlCompletionregistDto miseMaintenanceDto) {
        this.miseMaintenanceDto = miseMaintenanceDto;
    }
    /**
     * @return registLogicImpl を戻します。
     */
    public RegistLogicImpl getRegistLogic() {
        return registLogicImpl;
    }
    /**
     * @param registLogicImpl を設定。
     */
    public void setRegistLogic(RegistLogicImpl logic) {
        this.registLogicImpl = logic;
    }

    /**
     * メニューDTO取得処理
     * @return PullDownMenuDto メニューDTOを戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) SingletonS2ContainerFactory.getContainer().getComponent(PullDownMenuDto.class);
    }
}