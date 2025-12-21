/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.commonform.functionsearch.dto.FunctionSearchDto;
import jp.co.isid.mos.bird.commonform.functionsearch.entity.UIFunction;
import jp.co.isid.mos.bird.commonform.rolesearch.dto.RoleSearchDto;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.action.RoleActConditionAction;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dto.RoleActConditionDto;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dto.RoleActRegistDto;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.SearchDataLogic;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.SearchRoleActerLogic;

/**
 * ロール別アクセス権限設定処理アクションクラス
 * 
 * @author itamoto
 */
public class RoleActConditionActionImpl implements RoleActConditionAction {

	/* アクションID */
	public static String initialize_ACTION_ID          = "BSA007A01";
	public static String changeFunctionType_ACTION_ID  = "BSA007A02";
	public static String addRole_ACTION_ID             = "BSA007A03";
	public static String addFunction_ACTION_ID         = "BSA007A03";
	public static String select_ACTION_ID              = "BSA007A05";

	/* 例外メッセージ */
	private String roleMsg = "ロール";
	private String menuMsg = "機能";
	private String maxMsg  = "は6件以上指定";

    /* DTO */
    /** メニュー情報DTO */
    private PullDownMenuDto pullDownMenuDto;
    /** ロール未設定情報Dto */
    private RoleActConditionDto roleActConditionDto;
	/** 機能情報Dto */
	private FunctionSearchDto functionSearchDto;
	/** ロール情報Dto */
	private RoleSearchDto roleSearchDto;
	/** ロールアクセス制御の検索Dto */
	private RoleActRegistDto roleActRegistDto;

    /* ロジック */
    /** ロール未設定情報Logic */
    private SearchDataLogic searchDataLogic;
    /** ロールアクセス制御の検索Logic */
    private SearchRoleActerLogic searchRoleActerLogic;
    

	/**
	 * 初期処理
	 * 
	 * @return 画面遷移情報
	 */
	public String initialize() {

        //メニューより遷移してきた時
		if (getPullDownMenuDto().isClearFlg()) {
			getPullDownMenuDto().setClearFlg(false);
			getRoleActConditionDto().clear();
			getRoleActRegistDto().clear();
		}

		Map map = new HashMap();
		map.put("init", new Boolean(getRoleActConditionDto().getInitFlg()));
		map.put("pull", getRoleActConditionDto().getSelectPullId());
		getRoleActRegistDto().clear();

        //=============
        // 初期処理
        //=============
		if (!getRoleActConditionDto().getInitFlg()) {

            //条件DTO初期化
            getRoleActConditionDto().clear();

            //メニューリスト取得・DTOにセット
            List menuList = (List) (getSearchDataLogic().execute(map)).get("BIRDMENU");
            getRoleActConditionDto().setUIBirdMenu(menuList);

            //選択プルダウン作成
			List changeList = new ArrayList();
			changeList.add(new SelectItem("0", "機能を選択して登録"));
			changeList.add(new SelectItem("1", "ロールを選択して登録"));
			getRoleActConditionDto().setSelectPull(changeList);
			getRoleActConditionDto().setSelectPullId("0");       //初期選択は「機能(0)」

            //初期処理フラグON(初期処理
			getRoleActConditionDto().setInitFlg(true);
		} 

        //===============
        // 遷移後の処理
        //===============
        else {
			
            //--------------------------------------
            // [ 機能を選択して登録 ]が選択された時
            //--------------------------------------
			if (getRoleActConditionDto().getSelectPullId().equals("0")) {
				if (functionSearchDto.getFunctionList() != null) {

                    //デフォルト機能一覧を条件DTOより取得
					List menuList = getRoleActConditionDto().getUIBirdMenu();
					
					for (Iterator j = functionSearchDto.getFunctionList().iterator(); j.hasNext();) {

                        //追加の機能エンティティを取得
						UIFunction entFunction = (UIFunction) j.next();

                        for (int i = 0; i < menuList.size(); i++ ) {
							UIFunction entBirdMenu = (UIFunction)menuList.get(i);
							if (entFunction.getMenuId().equals(entBirdMenu.getMenuId()) && 
                                    entFunction.getSubMenuId().equals(entBirdMenu.getSubMenuId())) {
								menuList.remove(i);
								break;
							}
						}
					}
					
                    //追加の機能エンティティをリストに追加
					for ( Iterator j = functionSearchDto.getFunctionList().iterator(); j.hasNext(); ){
						UIFunction entity = (UIFunction) j.next();
						entity.setCheckFlg( true );
						menuList.add( entity );
					}
                    getFunctionSearchDto().clear();
				}
			} 
            
            //----------------------------------------
            // [ ロールを選択して登録 ]が選択された時
            //----------------------------------------
            else if (getRoleActConditionDto().getSelectPullId().equals("1")) {
				if (roleSearchDto.getResultRoleList() != null) {

                    //デフォルトロール一覧を条件DTOより取得
                    List roleList = getRoleActConditionDto().getUIRole();

					for (Iterator j = roleSearchDto.getResultRoleList().iterator(); j.hasNext();) {

                        //追加のロールエンティティを取得
                        UIRole entRole = (UIRole) j.next();

                        for (int i = 0; i < roleList.size(); i++ ) {
							UIRole entBirdRole = (UIRole)roleList.get(i);
							if (entRole.getBunruiCd().equals(entBirdRole.getBunruiCd()) && 
                                    entRole.getRoleCd().equals(entBirdRole.getRoleCd())) {
								roleList.remove(i);
								break;
							}
						}
					}

                    //追加のロールエンティティをリストに追加
					for ( Iterator j = roleSearchDto.getResultRoleList().iterator(); j.hasNext(); ) {
						UIRole entity = (UIRole) j.next();
						entity.setCheckedRole( true );
						roleList.add( entity );
					}
				}
			}
		}
		return null;

	}


	/**
     * [機能][ロール]プルダウンの変更時の処理
	 * 
	 * @return 画面遷移情報
	 */
	public String changeFunctionType() {

        //既存の情報のクリア(機能)
		if (functionSearchDto.getFunctionList() != null) {
			functionSearchDto.clear();
			if(getRoleActConditionDto().getUIBirdMenu() !=null) {
				getRoleActConditionDto().getUIBirdMenu().clear();
			}
			List clearList = roleActConditionDto.getUIBirdMenu();
			getRoleActConditionDto().setUIBirdMenu(clearList);
		}

        //既存の情報のクリア(ロール)
        if (roleSearchDto.getResultRoleList() != null) {
			roleSearchDto.getResultRoleList().clear();
			if(getRoleActConditionDto().getUIRole() != null) {
				getRoleActConditionDto().getUIRole().clear();
			}
			List clearList = roleActConditionDto.getUIRole();
			getRoleActConditionDto().setUIRole(clearList);
		}

        //引数用のマップ作成
        Map map = new HashMap();
        map.put("init", new Boolean(getRoleActConditionDto().getInitFlg()));
        map.put("pull", getRoleActConditionDto().getSelectPullId());
        
        //---------------------
        //[機能]が選択された時
        //---------------------
		if (getRoleActConditionDto().getSelectPullId().equals("0")) {
            //デフォルト選択対象の機能一覧を取得
            List list = (List) getSearchDataLogic().execute(map).get("BIRDMENU");
            getRoleActConditionDto().setUIBirdMenu(list);
		} 
        //---------------------
        //[ロール]が選択された時
        //---------------------
        else {
            //デフォルト選択対象のロール一覧を取得
            List list = (List) getSearchDataLogic().execute(map).get("ROLE");
            getRoleActConditionDto().setUIRole(list);
		}

        //自画面に遷移
		return null;
	}

	/**
	 * 選択された情報を取得
     * [設定]ボタン押下時の処理
	 * 
	 * @return 画面遷移情報
	 */
	public String select() {

        //チェックが付いている行のみのリスト取得
        List checkList = check();
		int realSize = checkList.size();

        //引数マップ作成
        Map listMap = new HashMap();            
		listMap.put("pull",getRoleActConditionDto().getSelectPullId());
		listMap.put("menulist",checkList);
		listMap.put("rolelist",checkList);
		listMap.put("init",new Boolean( getRoleActRegistDto().getInitFlg()) );
		listMap.put("bunruiCd",getRoleActRegistDto().getBunruiStr());
		listMap.put("menuId",getRoleActRegistDto().getMenuStr());
		listMap.put("realsize",new Integer(realSize));
		
        //ロジック実行
		Map resultMap = getSearchRoleActerLogic().execute(listMap);
		
        //機能の時
		if(getRoleActConditionDto().getSelectPullId().equals("0") ){
			if (!getRoleActRegistDto().getInitFlg()) {
				getRoleActRegistDto().setUIRoleBunrui((List)resultMap.get("bunrui"));
			}
			getRoleActRegistDto().setBunruiStr((String)resultMap.get("selectRole"));
		} 
        //ロールの時
        else {
			if(!getRoleActRegistDto().getInitFlg()) {

                //メインメニューリストをDTOにセット
				getRoleActRegistDto().setUIBirdMenu((List)resultMap.get("birdmenu"));
			}
            //対象のメニューIDをセット
			getRoleActRegistDto().setMenuStr((String)resultMap.get("selectMenu"));
		}
		
		List viewList = (List)resultMap.get("view");
				
		boolean[] booleanFlg = new boolean[5]; 
		
		for( int i = 0; i < 5; i++ ) {
			if( i < realSize) {
				booleanFlg[i] = true;
			}else {
				booleanFlg[i] = false;
			}
		}
		
		getRoleActRegistDto().setViewFlg1(booleanFlg[0]);
		getRoleActRegistDto().setViewFlg2(booleanFlg[1]);
		getRoleActRegistDto().setViewFlg3(booleanFlg[2]);
		getRoleActRegistDto().setViewFlg4(booleanFlg[3]);
		getRoleActRegistDto().setViewFlg5(booleanFlg[4]);

        //機能の時
		if(getRoleActConditionDto().getSelectPullId().equals("0") ) {
			getRoleActRegistDto().setUiAllBirdMenuMap(new HashMap());
			getRoleActRegistDto().getUiAllBirdMenuMap().put(
					getRoleActRegistDto().getBunruiStr(),viewList);
			List strList = new ArrayList();
			strList.add (getRoleActRegistDto().getBunruiStr());
			getRoleActRegistDto().setInitFlgList(strList);
			
			getRoleActRegistDto().setUIBirdMenu(checkList);
		}
        //ロールの時
        else {
            
            //全ロールマップ作成・DTOにセット
            Map allRoleMap = new HashMap();
            allRoleMap.put(getRoleActRegistDto().getMenuStr(), viewList);
            getRoleActRegistDto().setUIAllRoleMap(allRoleMap);

            //初期表示メニューIDリストを作成・DTOにセット
			List strList = new ArrayList();
			strList.add(getRoleActRegistDto().getMenuStr());
			getRoleActRegistDto().setInitFlgList(strList);
			
            //初期画面にて選択した(チェックをつけた)ロールIDのリストをDTOにセット
			getRoleActRegistDto().setUIRole(checkList);

		}
		getRoleActRegistDto().setUIViewList(viewList);

        //次の画面へ遷移
		return "BSA007V02";
	}

	/**
	 * チェックの付いた行のみのリストを返却する。
     * チェックが１つ以上５つ以下でない場合は例外をスローする。
	 * 
	 * @author xkhata
	 * @return List チェックの付いていた行のリスト
	 */
	private List check() {
		// 選択情報チェックボックス １つ以上５つ以下選択
		boolean flg = false;
		int count = 0;

        List checkList = new ArrayList();

        //「機能」が選択された場合
		if (getRoleActConditionDto().getSelectPullId().equals("0")) {
			for (Iterator i = getRoleActConditionDto().getUIBirdMenu().iterator(); i.hasNext();) {
				UIFunction entity = (UIFunction) i.next();
				if (entity.isCheckFlg()) {
					flg = true;
					count++;
					checkList.add(entity);
				}
			}
		} 
        //「ロール」が選択された場合
        else {
			for (Iterator i = getRoleActConditionDto().getUIRole().iterator(); i.hasNext();) {
				UIRole entity = (UIRole) i.next();
				if (entity.isCheckedRole()) {
					flg = true;
					count++;
					checkList.add(entity);
				}
			}
		}

        //１つ以上あるかチェック
        if (!flg) {
			if ( getRoleActConditionDto().getSelectPullId().equals("0")) {
				throw new NotExistException(menuMsg, "");
			} else {
				throw new NotExistException(roleMsg, "");
			}
		}

        //５つ以下かチェック
		if (count >= 6) {
			if ( getRoleActConditionDto().getSelectPullId().equals("0")) {
				throw new CannotExecuteException("対象" + menuMsg + maxMsg, "");
			} else {
				throw new CannotExecuteException("対象" + roleMsg + maxMsg, "");
			}
		}

        //チェックが付いていた行のリストを返却
		return checkList;
	}

    /**
     * [機能選択]ボタン押下時の処理(機能の追加)
     * 
     * @return 画面遷移情報
     */
    public String addFunction() {

        //DTO作成 or 初期化
        if ( functionSearchDto != null ) {
            functionSearchDto.clear();
        } else {
            functionSearchDto = new FunctionSearchDto();
        }
        //戻り先(自画面)の画面IDをセット
        functionSearchDto.setNavigationCase("BSA007V01");

        //機能選択初期処理起動フラグON
        functionSearchDto.setInitFlag(true); 

        //機能選択画面へ遷移
        return "BCO005V01";
    }

    /**
     * [ロール選択]ボタン押下時の処理(ロールの追加)
     * 
     * @return 画面遷移情報
     */
    public String addRole() {

        //DTO作成
        if ( roleSearchDto == null ) {
            roleSearchDto = new RoleSearchDto();
        }
        //戻り先(自画面)の画面IDをセット
        roleSearchDto.setNavigationCase("BSA007V01");

        //ロール選択画面へ遷移
        return "BCO004V01";
    }

    ////////////////////////////以下・セッター、ゲッター/////////////////////////////////////

    /**
     * 機能情報Dto取得処理
     * 
     * @return functionSearchDto
     */
    public FunctionSearchDto getFunctionSearchDto() {
        return functionSearchDto;
    }

    /**
     * 機能情報Dto設定処理
     * 
     * @param functionSearchDto
     */
    public void setFunctionSearchDto(FunctionSearchDto functionSearchDto) {
        this.functionSearchDto = functionSearchDto;
    }

    /**
     * ロール情報Dto取得処理
     * 
     * @return RoleSearchDto
     */
    public RoleSearchDto getRoleSearchDto() {
        return this.roleSearchDto;
    }

    /**
     * ロール情報Dto設定処理
     * 
     * @param RoleSearchDto
     */
    public void setRoleSearchDto(RoleSearchDto roleSearchDto) {
        this.roleSearchDto = roleSearchDto;
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
     * ロールアクセス制御の検索Logic取得処理
     * 
     * @return SearchRoleActerLogic
     */
    public SearchRoleActerLogic getSearchRoleActerLogic() {
        return this.searchRoleActerLogic;
    }

    /**
     * ロールアクセス制御の検索Logic設定処理
     * 
     * @param SearchRoleActerLogic
     */
    public void setSearchRoleActerLogic(
            SearchRoleActerLogic searchRoleActerLogic) {
        this.searchRoleActerLogic = searchRoleActerLogic;
    }

    /**
     * ロールアクセス制御の検索Dto取得処理
     * 
     * @return RoleActRegistDto
     */
    public RoleActRegistDto getRoleActRegistDto() {
        return this.roleActRegistDto;
    }

    /**
     * ロールアクセス制御の検索Dto設定処理
     * 
     * @param RoleActRegistDto
     */
    public void setRoleActRegistDto(RoleActRegistDto roleActRegistDto) {
        this.roleActRegistDto = roleActRegistDto;
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

