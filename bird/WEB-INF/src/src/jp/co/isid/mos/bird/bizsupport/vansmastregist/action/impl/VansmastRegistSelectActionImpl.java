/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.action.impl;

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.action.VansmastRegistSelectAction;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.dto.VansmastRegistDto;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchVansSokoLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchVansShohinLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.SearchNisugataLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.logic.InputAreaAddLogic;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.common.VansmastRegistConstants;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * バンズ倉庫別登録　初期画面アクションクラス
 * @author narita
 */
public class VansmastRegistSelectActionImpl implements VansmastRegistSelectAction {
 
	/** アクションID定義 */
    public static final String initialize_ACTION_ID 	= "BBS029A01";
    public static final String select_ACTION_ID 		= "BBS029A02";
    
    /** バンズ倉庫別登録DTO */
    private VansmastRegistDto vansmastRegistDto;
    
    /** バンズ倉庫別登録 倉庫リスト取得ロジック */
    private SearchVansSokoLogic searchVansSokoLogic;
    
    /** バンズ倉庫別登録 代表商品リスト取得ロジック */
    private SearchVansShohinLogic searchVansShohinLogic;
    
    /** バンズ倉庫別登録 荷姿リスト取得ロジック */
    private SearchNisugataLogic searchNisugataLogic;
    
    /** バンズ倉庫別登録 入力欄追加ロジック */
    private InputAreaAddLogic inputAreaAddLogic;

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {	

		// コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // メニューDTO取得
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);

        // メニューから遷移された場合
        if (pullDownMenuDto.isClearFlg()) {
            // クリアフラグをOFFにする
            pullDownMenuDto.setClearFlg(false);

            // バンズ倉庫別登録クリア
            getVansmastRegistDto().clear();
            // ウィンドウID更新
            getVansmastRegistDto().updateWindowid();

            //複数ウィンドウ制御用のセッションキー生成
            String key = getVansmastRegistDto().getMkSession()._makeSessionKey();
            getVansmastRegistDto().setNowSessionKey(key);
            getVansmastRegistDto().setSessionKey(key);
            
            // 倉庫リスト取得
            getVansmastRegistDto().setUIVansSokoList( getSearchVansSokoLogic().execute() );
            // 荷姿リスト取得
            getVansmastRegistDto().setUINisugataList( getSearchNisugataLogic().execute() );
        }

        // 自画面へ遷移
        return null;
    }
    
    /**
     * 実行
     * @return 画面遷移情報
     */
    public String enforcement() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getVansmastRegistDto().isValidSessionKey()) {
            return VansmastRegistConstants.VIEW_ID_ERR;
        }
        
        String kanriMoto = getVansmastRegistDto().getKanriMoto();
        List sokoList = getVansmastRegistDto().getUIVansSokoList();
        
        // 選択した対象倉庫プルダウンの名称をDTOへセット
        getVansmastRegistDto().setKanriMotoKj(
        		getSearchVansSokoLogic().getKanriMotoKj(sokoList,kanriMoto));
        
        // 選択された対象倉庫の代表商品情報を検索
        List kanriList = getVansmastRegistDto().getUIVansSokoList();
        List shohinList = getSearchVansShohinLogic().execute(kanriMoto,kanriList);
        
        // 入力欄追加処理を実行
        getVansmastRegistDto().setUIVansShohinList(getInputAreaAddLogic().execute(
        		shohinList,VansmastRegistConstants.ADD_MODE_INIT, "",""));
        
        getVansmastRegistDto().setAddFlg(false);
        getVansmastRegistDto().setErrFlg(false);
        
    	return VansmastRegistConstants.VIEW_ID_EDIT;
	}

	public VansmastRegistDto getVansmastRegistDto() {
		return vansmastRegistDto;
	}

	public void setVansmastRegistDto(VansmastRegistDto vansmastRegistDto) {
		this.vansmastRegistDto = vansmastRegistDto;
	}

	public SearchVansSokoLogic getSearchVansSokoLogic() {
		return searchVansSokoLogic;
	}

	public void setSearchVansSokoLogic(SearchVansSokoLogic searchVansSokoLogic) {
		this.searchVansSokoLogic = searchVansSokoLogic;
	}

	public SearchVansShohinLogic getSearchVansShohinLogic() {
		return searchVansShohinLogic;
	}

	public void setSearchVansShohinLogic(SearchVansShohinLogic searchVansShohinLogic) {
		this.searchVansShohinLogic = searchVansShohinLogic;
	}

	public SearchNisugataLogic getSearchNisugataLogic() {
		return searchNisugataLogic;
	}

	public void setSearchNisugataLogic(SearchNisugataLogic searchNisugataLogic) {
		this.searchNisugataLogic = searchNisugataLogic;
	}

	public InputAreaAddLogic getInputAreaAddLogic() {
		return inputAreaAddLogic;
	}

	public void setInputAreaAddLogic(InputAreaAddLogic inputAreaAddLogic) {
		this.inputAreaAddLogic = inputAreaAddLogic;
	}
}

