/*
 * 作成日: 2006/02/13
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.action.RoleActRegistAction;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dto.RoleActConditionDto;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dto.RoleActRegistDto;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.SearchDataLogic;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.SearchRoleActerLogic;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.UpdateRoleActerLogic;

/**
 * ロール別アクセス権限設定・編集画面(BSA007V02)
 * @author xkhata
 */
public class RoleActRegistActionImpl implements RoleActRegistAction{

	/* アクションID */
	public static String initialize_ACTION_ID = "BSA007A06";
	public static String update_ACTION_ID     = "BSA007A07";
	public static String back_ACTION_ID       = "BSA007A10";
	public static String changeTab_ACTION_ID  = "BSA007A11";

    //DTO
	/** ロールアクセス制御の検索Dto */
	private RoleActRegistDto roleActRegistDto;
	/** ロール未設定情報Dto */
	private RoleActConditionDto roleActConditionDto;

    //LOGIC
    /** ロールアクセス制御の検索Logic */
    private SearchRoleActerLogic searchRoleActerLogic;
    /** ロール未設定情報Logic */
    private SearchDataLogic searchDataLogic;
    /** 更新Logic */
    private UpdateRoleActerLogic updateRoleActerLogic;

    //メニュー情報・ユーザ情報等
    /** ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /** メニュー後の初期処理 */
    private PullDownMenuDto pullDownMenuDto;
    
    //その他
    /** 分類ID */
	private  String bunruiStr;
	/** メニューID */
	private String menuStr; 
	
	
	/**
	 * 初期処理
	 */
	public String initialize() {
        
        //-----------------
        // 機能の時
        //-----------------
		if(getRoleActConditionDto().getSelectPullId().equals("0") ) {
            
            //初期処理フラグがONの時(編集画面にて一度でも処理を行った時)
			if(getRoleActRegistDto().getInitFlg()) {

                //分類IDを取得・セット
                String bunruiStr = getBunruiStr();
				getRoleActRegistDto().setBunruiStr(bunruiStr);
			}
			//初期処理フラグがOFFの時
            else {
                //フラグONにする
				getRoleActRegistDto().setInitFlg(true);
			}
		}
        //-----------------
        // ロールの時
        //-----------------
        else {

            //初期処理フラグがONの時(編集画面にて一度でも処理を行った時)
			if(getRoleActRegistDto().getInitFlg()) {

                //メニューIDを取得・セット
                String menuStr = getMenuStr();
				getRoleActRegistDto().setMenuStr(menuStr);
			}
            //初期処理フラグがOFFの時
            else {
                //フラグONにする
				getRoleActRegistDto().setInitFlg(true);
			}
		}
        //自画面に遷移
		return null;
	}
	
	/**
	 * タブの変更
	 * @return null
	 */
	public String changeTab() {	

        //引数用マップ
        Map listMap = new HashMap();

        //------------------------
        // 機能の時
        //------------------------
        if(getRoleActConditionDto().getSelectPullId().equals("0") ) {
			List initList = (List)getRoleActRegistDto().getInitFlgList();
			if( !initList.contains(getBunruiStr()))  {
				listMap.put("pull",getRoleActConditionDto().getSelectPullId());
				listMap.put("menulist",getRoleActRegistDto().getUIBirdMenu());
				listMap.put("init",new Boolean( getRoleActRegistDto().getInitFlg()) );
				listMap.put("bunruiCd",getBunruiStr());
				listMap.put("realsize",new Integer(getRoleActRegistDto().getUIBirdMenu().size()));
				
				getRoleActRegistDto().getUiAllBirdMenuMap().put(
						getRoleActRegistDto().getBunruiStr(),getRoleActRegistDto().getUIViewList());
				
                //ロジック実行
				Map resultMap = getSearchRoleActerLogic().execute(listMap);
	
				List viewList =(List)resultMap.get("view");
				
				List strList = getRoleActRegistDto().getInitFlgList();
				strList.add(getBunruiStr());
				getRoleActRegistDto().setInitFlgList(strList);
				
				getRoleActRegistDto().getUiAllBirdMenuMap().put(getBunruiStr(),viewList);
				getRoleActRegistDto().setUIViewList(viewList);
								
				getRoleActRegistDto().setBunruiStr(getBunruiStr());
				
			}else {
				getRoleActRegistDto().getUiAllBirdMenuMap().put(
						getRoleActRegistDto().getBunruiStr(),getRoleActRegistDto().getUIViewList());
				List viewList = (List)getRoleActRegistDto().getUiAllBirdMenuMap().get(getBunruiStr());
				getRoleActRegistDto().setUIViewList(viewList);
				getRoleActRegistDto().setBunruiStr(getBunruiStr());
			}
		}
        //------------------------
        // ロールの時
        //------------------------
        else {
            //既に表示したメニューIDのリスト
			List initList = (List)getRoleActRegistDto().getInitFlgList();

            //今回表示対象のメニューID
            String menuId = getMenuStr();
            
            
            //まだ表示していない『メニューID』の時
            if( !initList.contains(menuId)) {

                System.out.println("pull：[" + getRoleActConditionDto().getSelectPullId() + "]");
                System.out.println("rolelist：[" + getRoleActRegistDto().getUIRole() + "]");
                System.out.println("init：[" + new Boolean( getRoleActRegistDto().getInitFlg()) + "]");
                System.out.println("menuId：[" + menuId + "]");
                System.out.println("realsize：[" + new Integer(getRoleActRegistDto().getUIRole().size()) + "]");

                //引数用マップ作成
                listMap.put("pull",getRoleActConditionDto().getSelectPullId());
				listMap.put("rolelist",getRoleActRegistDto().getUIRole());
				listMap.put("init",new Boolean( getRoleActRegistDto().getInitFlg()) );
				listMap.put("menuId", menuId);
				listMap.put("realsize",new Integer(getRoleActRegistDto().getUIRole().size()));
				
                //ロジック実行
				Map resultMap = getSearchRoleActerLogic().execute(listMap);
				
                //表示する『機能リスト』
				List viewList = (List)resultMap.get("view");

                //DTOにセット
                getRoleActRegistDto().setUIViewList(viewList);      //取得した『機能リスト』
                getRoleActRegistDto().setMenuStr(menuId);           //表示対象の『メニューID』

                //既に表示したメニューIDのリストに今回のメニューIDを追加
				List strList = getRoleActRegistDto().getInitFlgList();
				strList.add(menuId);
				getRoleActRegistDto().setInitFlgList(strList);

                //全ロールのマップに今回取得した『機能リスト』を追加
                Map allRoleMap = getRoleActRegistDto().getUIAllRoleMap();
                allRoleMap.put(menuId, viewList);

			}
            //既に表示した『メニューID』の時
            else  {

                //表示対象の『機能リスト』取得・DTOにセット
                List viewList = (List)getRoleActRegistDto().getUIAllRoleMap().get(menuId);
                getRoleActRegistDto().setUIViewList(viewList);


                //全ロールのマップに再度セット？
                Map allRoleMap = getRoleActRegistDto().getUIAllRoleMap();
                allRoleMap.put(menuId, viewList);

                //表示対象の『メニューID』をセット
				getRoleActRegistDto().setMenuStr(getMenuStr());
			}
		}
		
        //自画面に遷移
		return null;
	}
	
	/**
	 * 更新処理
	 * @return
	 */
	public String update() {
		Map listMap = new HashMap();
		if(getRoleActConditionDto().getSelectPullId().equals("0")) {
			getRoleActRegistDto().getUIAllRoleMap().put(
					getRoleActRegistDto().getBunruiStr(),getRoleActRegistDto().getUIViewList());
			
			listMap.put("pullid",getRoleActConditionDto().getSelectPullId());
			listMap.put("allMap",getRoleActRegistDto().getUiAllBirdMenuMap());
			listMap.put("menulist",getRoleActRegistDto().getUIBirdMenu());
			listMap.put("initlist",getRoleActRegistDto().getInitFlgList());
			listMap.put("user",getBirdUserInfo().getUserID());

		}else  {
			getRoleActRegistDto().getUIAllRoleMap().put(
					getRoleActRegistDto().getMenuStr(), getRoleActRegistDto().getUIViewList());
			listMap.put("pullid",getRoleActConditionDto().getSelectPullId());
			listMap.put("allMap",getRoleActRegistDto().getUIAllRoleMap());
			listMap.put("rolelist",getRoleActRegistDto().getUIRole());
			listMap.put("initlist",getRoleActRegistDto().getInitFlgList());
			listMap.put("user",getBirdUserInfo().getUserID());
			
		//	getUpdateRoleActerLogic().execute(listMap);
		}
		
		getUpdateRoleActerLogic().execute(listMap);
		
		getPullDownMenuDto().setClearFlg(true);
		
		return "BSA007V01";
	}
	
	/**
	 * 戻るボタン
	 * @return
	 */
	public String back() {
		
		getPullDownMenuDto().setClearFlg(true);
		
		return "BSA007V01";
	}
    
    /////////////////////////////////////以下、セッター・ゲッター/////////////////////////////////
    
    /**
     * ロールアクセス制御の検索Dto取得処理
     * @return RoleActRegistDto
     */
    public RoleActRegistDto getRoleActRegistDto() {
        return this.roleActRegistDto;
    }
    
    /**
     * ロールアクセス制御の検索Dto設定処理
     * @param RoleActRegistDto
     */
    public void setRoleActRegistDto(RoleActRegistDto roleActRegistDto) {
        this.roleActRegistDto = roleActRegistDto;
    }
    
    /**
     * ロール未設定情報取得処理
     * 
     * @return RoleActConditioi
     */
    public RoleActConditionDto getRoleActConditionDto() {
        return this.roleActConditionDto;
    }

    /**
     * ロール未設定情報設定処理
     * 
     * @param RoleActCondition
     */
    public void setRoleActConditionDto(RoleActConditionDto roleActCondition) {
        this.roleActConditionDto = roleActCondition;
    }
    
    /**
     * 分類コード取得処理
     * @return String 
     * 
     */
    public String getBunruiStr() {
        return this.bunruiStr;
    }
    
    /**
     * メニューID設定処理
     * @param String
     */
    public void setMenuStr(String menuStr) {
        this.menuStr = menuStr;
    }
    
    /**
     * メニューID取得処理
     * @return String 
     * 
     */
    public String getMenuStr() {
        return this.menuStr;
    }
    
    /**
     * 分類コード設定処理
     * @param String
     */
    public void setBunruiStr(String bunruiStr) {
        this.bunruiStr = bunruiStr;
    }
    /**
     * ロールアクセス制御の検索Logic取得処理
     * @return SearchRoleActerLogic
     */
    public SearchRoleActerLogic getSearchRoleActerLogic() {
        return this.searchRoleActerLogic;
    }
    
    /**
     * ロールアクセス制御の検索Logic設定処理
     * @param SearchRoleActerLogic
     */
    public void setSearchRoleActerLogic(SearchRoleActerLogic searchRoleActerLogic) {
        this.searchRoleActerLogic = searchRoleActerLogic;
    }
    
    /**
     * ロール未設定情報
     * 
     * @return SearchDataLogic
     */
    public SearchDataLogic getSearchDataLogic() {
        return this.searchDataLogic;
    }

    /**
     * ロール未設定情報
     * 
     * @param SearchDataLogic
     */
    public void setSearchDataLogic(SearchDataLogic searchDataLogic) {
        this.searchDataLogic = searchDataLogic;
    }

    /**
     * update情報取得
     * @return
     */
    public UpdateRoleActerLogic getUpdateRoleActerLogic() {
        return this.updateRoleActerLogic;
    }
    
    public void setUpdateRoleActerLogic(UpdateRoleActerLogic updateRoleActerLogic) {
        this.updateRoleActerLogic = updateRoleActerLogic;
    }
    
    /**
     * BIRDログイン情報の設定
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * BIRDログイン情報の設定
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    
    /**
     * メニュー後の初期処理判定
     * 
     * @return
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

}
