/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.longserviceregist.action.impl;

import jp.co.isid.mos.bird.entry.longserviceregist.action.LongserviceRegistSelectAction;
import jp.co.isid.mos.bird.entry.longserviceregist.dto.LongserviceRegistDto;
import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.SearchEntryLogic;

import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.longserviceregist.logic.SearchEntryListLogic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 全国大会マスタ登録 条件アクション
 *
 * @author xjung
 */
public class LongserviceRegistSelectActionImpl implements LongserviceRegistSelectAction {

	/** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN017A01";
    public static final String insert_ACTION_ID = "BEN017A02";
    public static final String update_ACTION_ID = "BEN017A03";
    public static final String delete_ACTION_ID = "BEN017A04";
    
    /** 永年勤続マスタ登録情報DTO */
    private LongserviceRegistDto longserviceRegistDto;

    /** 永年勤続マスタ情報リスト取得ロジック */
    private SearchEntryListLogic searchEntryListLogic;

    /** 永年勤続マスタ情報取得ロジック */
    private SearchEntryLogic searchEntryLogic;

    /** 永年勤続マスタ情報チェックロジック */
    private CheckEntryLogic checkEntryLogic;

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

            // 永年勤続マスタ登録情報削除
            getLongserviceRegistDto().clear();

            // ウィンドウID更新
            getLongserviceRegistDto().updateWindowid();

            //複数ウィンドウ制御用のセッションキー生成
            String key = getLongserviceRegistDto().getMkSession()._makeSessionKey();
            getLongserviceRegistDto().setNowSessionKey(key);
            getLongserviceRegistDto().setSessionKey(key);

            // BIRDユーザー情報取得
            BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);

            // BIRD日付情報取得
            BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

            // ユーザID設定
            getLongserviceRegistDto().setUserId(birdUserInfo.getUserID());
            // システム日付設定
            getLongserviceRegistDto().setSysDate(birdDateInfo.getSysDate());

            // 編集モード設定
            getLongserviceRegistDto().setEditMode(LongserviceRegistConstants.EDIT_MODE_INIT);            
        }

        // 編集モードが戻る以外の場合
        //if (getLongserviceRegistDto().getEditMode() != LongserviceRegistConstants.EDIT_MODE_RETURN) {
            // 永年勤続マスタ情報リストを取得
            getLongserviceRegistDto().setMstInfoList(
                getSearchEntryListLogic().execute(getLongserviceRegistDto().getSysDate()));

            if(getLongserviceRegistDto().getMstInfoList().size() <= 0){
            	// 永年勤続マスタ情報リストが存在しない
            	getLongserviceRegistDto().setEmptyMstInfoList(false);
            	// 編集・削除可能データの存在フラグの設定 存在しない：false
            	getLongserviceRegistDto().setButtonFlg(false);
            }else{
            	// 永年勤続マスタ情報リストが存在する
            	getLongserviceRegistDto().setEmptyMstInfoList(true);
            	// 編集・削除可能データの存在フラグの設定
            	getLongserviceRegistDto().setButtonFlg(
            			getSearchEntryListLogic().getButtonFlg(getLongserviceRegistDto()));
            }
        //}

        // 編集モードが初期の場合
        if (getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_INIT || 
        		getLongserviceRegistDto().getEditMode() == LongserviceRegistConstants.EDIT_MODE_INSERT) {
     	
        	int defSelectIndex = getSearchEntryLogic().getDefSelectIndex(
        			getLongserviceRegistDto().getMstInfoList(),
        			getLongserviceRegistDto().getSysNendo());
        	// 選択ラジオボタンインデックス設定
            getLongserviceRegistDto().setSelectIndex(defSelectIndex);
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * 新規登録
     * @return 画面遷移情報
     */
    public String insert() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceRegistDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }

        // 編集モード設定
        getLongserviceRegistDto().setEditMode(LongserviceRegistConstants.EDIT_MODE_INSERT);
        
        // 永年勤続マスタ登録情報を削除
        getLongserviceRegistDto().clear();
        
        // 編集画面へ遷移
        return LongserviceRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 編集
     * @return 画面遷移情報
     */
    public String update() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceRegistDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }
 
        // 編集モード設定
        getLongserviceRegistDto().setEditMode(LongserviceRegistConstants.EDIT_MODE_UPDATE);

        // 選択された永年勤続マスタ情報を取得
        getLongserviceRegistDto().setUiEntryMst(
            (UIEntryMst) getLongserviceRegistDto().getMstInfoList().get(
                getLongserviceRegistDto().getSelectIndex()));
        
        // 永年勤続マスタ日付情報を検索       
        getLongserviceRegistDto().setDateInfoList(
                getSearchEntryLogic().execute(getLongserviceRegistDto()));
        
        // リードオンリーフラグの設定
        getLongserviceRegistDto().setReadOnlyFlg(getLongserviceRegistDto().isReadOnly());
        
        // 編集画面へ遷移
        return LongserviceRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete(){
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceRegistDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }

        // 編集モード設定
        getLongserviceRegistDto().setEditMode(LongserviceRegistConstants.EDIT_MODE_DELETE);

        // 選択された永年勤続マスタ情報を取得
        getLongserviceRegistDto().setUiEntryMst(
            (UIEntryMst) getLongserviceRegistDto().getMstInfoList().get(
                getLongserviceRegistDto().getSelectIndex()));
        
        // 削除可能チェック
        if(!getLongserviceRegistDto().isDeleteCheck()){
        	throw new CannotExecuteException(LongserviceRegistConstants.MSG_DELETE);
        }

        // 確認画面へ遷移
        return LongserviceRegistConstants.VIEW_ID_CONFIRM;
    }

    /**
     * 永年勤続マスタ情報リスト取得ロジックを取得する。
     * @return 永年勤続マスタ情報リスト取得ロジック
     */
    public SearchEntryListLogic getSearchEntryListLogic() {
        return searchEntryListLogic;
    }

    /**
     * 永年勤続マスタ情報リスト取得ロジックを設定する。
     * @param searchEntryListLogic 永年勤続マスタ情報リスト取得ロジック
     */
    public void setSearchEntryListLogic(SearchEntryListLogic searchEntryListLogic) {
        this.searchEntryListLogic = searchEntryListLogic;
    }

    /**
     * 永年勤続マスタ情報取得ロジックを取得する。
     * @return 永年勤続マスタ情報取得ロジック
     */
    public SearchEntryLogic getSearchEntryLogic() {
        return searchEntryLogic;
    }

    /**
     * 永年勤続マスタ情報取得ロジックを設定する。
     * @param searchEntryLogic 永年勤続マスタ情報取得ロジック
     */
    public void setSearchEntryLogic(SearchEntryLogic searchEntryLogic) {
        this.searchEntryLogic = searchEntryLogic;
    }

    /**
     * 永年勤続マスタ情報チェックロジックを取得する。
     * @return 永年勤続マスタ情報チェックロジック
     */
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }

    /**
     * 永年勤続マスタ情報チェックロジックを設定する。
     * @param checkEntryLogic 永年勤続マスタ情報チェックロジック
     */
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }

	public LongserviceRegistDto getLongserviceRegistDto() {
		return longserviceRegistDto;
	}

	public void setLongserviceRegistDto(LongserviceRegistDto longserviceRegistDto) {
		this.longserviceRegistDto = longserviceRegistDto;
	}
}