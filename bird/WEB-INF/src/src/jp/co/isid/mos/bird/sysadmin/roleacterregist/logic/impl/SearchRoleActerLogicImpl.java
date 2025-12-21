/*
 * 作成日: 2006/02/01
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.functionsearch.entity.UIFunction;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.CtlRoleActerDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.UIBirdMenuDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.UIRoleBunruiDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.UIRoleDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.CtlRoleActer;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.UIRoleBunrui;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.UIViewMenu;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.UIViewRole;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.SearchRoleActerLogic;

/**
 * @author xkhata
 *
 */
public class SearchRoleActerLogicImpl implements SearchRoleActerLogic{

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA007L02";

	private UIRoleDao uIRoleDao;
	private UIRoleBunruiDao uIRoleBunruiDao;
	private UIBirdMenuDao uIBirdMenuDao;
	private CtlRoleActerDao ctlRoleActerDao;
	
	
	/* UIRoleDao DI */
	public void setUIRoleDao(UIRoleDao uIRoleDao) {
		this.uIRoleDao = uIRoleDao;
	}
	
	public UIRoleDao getUIRoleDao() {
		return this.uIRoleDao;
	}
	
	/* UIRoleBunruiDao DI */
	public void setUIRoleBunruiDao(UIRoleBunruiDao uIRoleBunruiDao) {
		this.uIRoleBunruiDao = uIRoleBunruiDao;
	}
	
	public UIRoleBunruiDao getUIRoleBunruiDao() {
		return this.uIRoleBunruiDao;
	}
	
	/* UIBirdMenuDao Di */
	public void setUIBirdMenuDao(UIBirdMenuDao uIBirdMenuDao) {
		this.uIBirdMenuDao = uIBirdMenuDao;
	}
	
	public UIBirdMenuDao getUIBirdMenuDao() {
		return this.uIBirdMenuDao;
	}

	/* CtlRoleActerDao DI */
	public void setCtlRoleActerDao(CtlRoleActerDao ctlRoleActerDao) {
		this.ctlRoleActerDao = ctlRoleActerDao;
	}
	
	public CtlRoleActerDao getCtlRoleActerDao() {
		return this.ctlRoleActerDao;
	}
	
	/**
	 * 
	 * @param  Map "realsize" 選択された数
	 * 　　　　　　 "init"     初期化フラグ
	 *              "bunruiCd" 分類コード
	 *              "menulist" 選択されたメニューのリスト
	 * @return Map "
	 */
	public Map execute(Map listMap) throws ApplicationException {

        //返却用マップ
        Map retMap = new HashMap();

        List viewList = new ArrayList();
		int realSize = ((Integer)listMap.get("realsize")).intValue();
		
        //------------------------
        // 機能の時
        //------------------------
		if( listMap.get("pull").equals("0") ) {
			String bunruiCd = null;
			boolean initFlg = ((Boolean)listMap.get("init")).booleanValue();
			if (!initFlg) {
				// 全分類コード取得
				List bunruiList = getUIRoleBunruiDao().getRoleBunrui();
				UIRoleBunrui uiRoleBunrui = (UIRoleBunrui) bunruiList.iterator().next();
				bunruiCd = uiRoleBunrui.getBunruiCd();
				retMap.put("bunrui",bunruiList);
			} else {
				bunruiCd = (String)listMap.get("bunruiCd");
			}

            String[] menuSubMenuID = {"","","","",""};
								
			List menuList = (List)listMap.get("menulist");
			for (int i = 0; i < menuList.size(); i++ ) {
				UIFunction entity = (UIFunction)menuList.get(i);
				if (entity.getMenuId() != null) { 
					menuSubMenuID[i] = entity.getMenuId() + entity.getSubMenuId();
				}else {
					break;
				}
			}
			 
            //画面に表示する全リスト
			List accessList =getCtlRoleActerDao().getMenuRoleAll(
					bunruiCd, menuSubMenuID[0],menuSubMenuID[1],menuSubMenuID[2],menuSubMenuID[3],menuSubMenuID[4] ); 

            //アクセス権限のある機能のリスト
            List rolelist = getCtlRoleActerDao().getMenuRoleActer(
					bunruiCd, menuSubMenuID[0],menuSubMenuID[1],menuSubMenuID[2],menuSubMenuID[3],menuSubMenuID[4] );
			retMap.put("selectRole",bunruiCd);
			
            //既に使用可能なものにチェックをつける
			accessList = checkList(accessList, rolelist);

            //ソートする
            accessList = sortList(accessList, menuList, realSize ,(String)listMap.get("pull"));
			
            //表示用に加工する
			viewList = convList(accessList, realSize,(String)listMap.get("pull"));
		}
        //------------------------
        // ロールの時
        //------------------------
        else {
			String menuID = null;
			boolean initFlg = ((Boolean)listMap.get("init")).booleanValue();
			if (!initFlg) {
				// 全メニュー取得
				List menuList = getUIBirdMenuDao().getMainBirdMenu();
				UIFunction uiFunction = (UIFunction) menuList.iterator().next();
				menuID = uiFunction.getSubMenuId();
				retMap.put("birdmenu",menuList);
			} else {
				menuID = (String)listMap.get("menuId");
			}		
            retMap.put("selectMenu",menuID);


			//表示対象のロールIDを配列化する
            String[] roleId = {"","","","",""};
            List roleList = (List)listMap.get("rolelist");
			for(int i = 0; i < roleList.size(); i++ ) {
				UIRole entity = (UIRole)roleList.get(i);
				if(entity.getRoleCd() != null) {
					roleId[i] = entity.getRoleCd();
				} else {
					break;
				}
			}
			
            //画面に表示する全リスト
			List accessList = getCtlRoleActerDao().getRoleMenuAll(
					menuID, roleId[0], roleId[1], roleId[2], roleId[3], roleId[4]);

            //アクセス権限のある機能のリスト
            List accessRoleList = getCtlRoleActerDao().getRoleMenuActer(
					menuID, roleId[0], roleId[1], roleId[2], roleId[3], roleId[4]);

            //既に使用可能なものにチェックをつける
			accessList = checkList(accessList, accessRoleList);
			
            //ソートする
            accessList = sortList(accessList, roleList, realSize, (String)listMap.get("pull"));
            
            //表示用に加工する
			viewList = convList(accessList, realSize, (String)listMap.get("pull"));
		}
		
		retMap.put("view",viewList);
		return retMap;
	}
	
	/**
	 * 既に登録済みのチェック生成
	 * @param accessList
	 * @param sortList
	 * @return List
	 */
	private List checkList( List accessList, List sortList) {

        //返却用リスト
        List checkList = new ArrayList();
		
		for (int i = 0; i < accessList.size(); i++ ) {
            
            //全表示リスト取得
			CtlRoleActer accessEntity = (CtlRoleActer)accessList.get(i);

            for (int j = 0; j < sortList.size(); j++ ) {

                //個別設定のエンティティ取得
                CtlRoleActer roleEntity = (CtlRoleActer)sortList.get(j);
				if(accessEntity.getRoleCd().equals(roleEntity.getRoleCd())) {
					if(accessEntity.getMenuId().equals(roleEntity.getMenuId())) {
                        if(accessEntity.getSubMenuId().equals(roleEntity.getSubMenuId())) {

                            //使用可能フラグ(初期)
                            if(roleEntity.getEnableFlg() != null && roleEntity.getEnableFlg().equals("1")){
                                accessEntity.setCheckFlg(true);
                            }
                            
                            //上限拡張フラグ(上限)
                            if(roleEntity.getExtraFlg() != null && roleEntity.getExtraFlg().equals("1")){
                                accessEntity.setCheckFlgLimit(true);
                            }
                        }
                    }
				}
			}
			checkList.add(accessEntity);
		}
		
		return checkList;
	}
	
	/**
	 * menuList順にソートする
	 * @param accessList
	 * @param menuList
	 * @return
	 */
	private List sortList( List accessList, List menuList, int realSize, String pullID) {
		List convList = new ArrayList();

		for ( int i = 0; i < accessList.size(); i = i + realSize ) {
			List sortList = new ArrayList();	
			for( int  j = i; j < i + realSize ; j++ ) {
				sortList.add( (CtlRoleActer)accessList.get(j) );
			}

			if(pullID.equals("0") ) {
				for( int k = 0; k < realSize; k++ ) {
					UIFunction uib = (UIFunction)menuList.get(k);
					for (int l = 0; l < sortList.size(); l++ ) {
						CtlRoleActer ctl = (CtlRoleActer)sortList.get(l);
						if( uib.getMenuId() != null ) {
							if (uib.getMenuId().equals(ctl.getMenuId())) {
								if(uib.getSubMenuId().equals(ctl.getSubMenuId())) {
									convList.add(ctl);
									break;
								}
							}
						}
					}
				}
			} else {
				for( int k = 0; k < realSize; k++ ) {
					UIRole uib = (UIRole)menuList.get(k);
					for (int l = 0; l < sortList.size(); l++ ) {
						CtlRoleActer ctl = (CtlRoleActer)sortList.get(l);
						if( uib.getRoleCd() != null ) {
							if(uib.getRoleCd().equals(ctl.getRoleCd())) {
								convList.add(ctl);
								break;
							}
						}
					}
				}
			}
		}
		
		return convList;
	}
	
	/**
	 * 表示用にコンバートする
	 * @param accessList
	 * @param realSize
	 * @param pullID
	 * @return
	 */
	private List convList(List accessList, int realSize, String pullID) {
		List viewList = new ArrayList();
		
		for (int i = 0; i < accessList.size(); i = i + realSize ) {
			CtlRoleActer ent1;
			CtlRoleActer ent2;
			CtlRoleActer ent3;
			CtlRoleActer ent4;
			CtlRoleActer ent5;
			
			switch (realSize) {
				case 1:
					ent1 = (CtlRoleActer)accessList.get(i);
					
					if(pullID.equals("0") ) {
						viewList.add(new UIViewMenu( ent1.getRoleCd()
													,ent1.getRoleName()
													,ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													)		
									);
					} else {
						viewList.add(new UIViewRole( ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getSubMenuName()
													,ent1.getRoleCd()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													)
									);
					}
					break;
				case 2:
					ent1 = (CtlRoleActer)accessList.get(i);
					ent2 = (CtlRoleActer)accessList.get(i + 1);
					
					if(pullID.equals("0") ) {
						viewList.add(new UIViewMenu( ent1.getRoleCd()
													,ent1.getRoleName()
													,ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getMenuId()
													,ent2.getSubMenuId()
													,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
									)		
						);
					} else {
						viewList.add(new UIViewRole( ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getSubMenuName()
													,ent1.getRoleCd()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getRoleCd()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													)
									);
					}
					break;
				case 3:
					ent1 = (CtlRoleActer)accessList.get(i);
					ent2 = (CtlRoleActer)accessList.get(i + 1);
					ent3 = (CtlRoleActer)accessList.get(i + 2);
					
					if(pullID.equals("0") ) {
						viewList.add(new UIViewMenu( ent1.getRoleCd()
													,ent1.getRoleName()
													,ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getMenuId()
													,ent2.getSubMenuId()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													,ent3.getMenuId()
													,ent3.getSubMenuId()
													,ent3.getCheckFlg()
                                                    ,ent3.getCheckFlgLimit()
													)
									);
					} else {
						viewList.add(new UIViewRole( ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getSubMenuName()
													,ent1.getRoleCd()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getRoleCd()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													,ent3.getRoleCd()
                                                    ,ent3.getCheckFlg()
                                                    ,ent3.getCheckFlgLimit()
													)
									);
					}
					break;
				case 4:
					ent1 = (CtlRoleActer)accessList.get(i);
					ent2 = (CtlRoleActer)accessList.get(i + 1);
					ent3 = (CtlRoleActer)accessList.get(i + 2);
					ent4 = (CtlRoleActer)accessList.get(i + 3);
					
					if(pullID.equals("0") ) {
						viewList.add(new UIViewMenu( ent1.getRoleCd()
													,ent1.getRoleName()
													,ent1.getMenuId()
													,ent1.getSubMenuId()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getMenuId()
													,ent2.getSubMenuId()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													,ent3.getMenuId()
													,ent3.getSubMenuId()
													,ent3.getCheckFlg()
                                                    ,ent3.getCheckFlgLimit()
													,ent4.getMenuId()
													,ent4.getSubMenuId()
													,ent4.getCheckFlg()
                                                    ,ent4.getCheckFlgLimit()
													)
									);
					} else {
						viewList.add(new UIViewRole( ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getSubMenuName()
													,ent1.getRoleCd()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getRoleCd()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													,ent3.getRoleCd()
                                                    ,ent3.getCheckFlg()
                                                    ,ent3.getCheckFlgLimit()
													,ent4.getRoleCd()
                                                    ,ent4.getCheckFlg()
                                                    ,ent4.getCheckFlgLimit()
													)
									);
					}
					break;
				case 5:
					ent1 = (CtlRoleActer)accessList.get(i);
					ent2 = (CtlRoleActer)accessList.get(i + 1);
					ent3 = (CtlRoleActer)accessList.get(i + 2);
					ent4 = (CtlRoleActer)accessList.get(i + 3);
					ent5 = (CtlRoleActer)accessList.get(i + 4);
					
					if(pullID.equals("0") ) {
						viewList.add(new UIViewMenu( ent1.getRoleCd()
													,ent1.getRoleName()
													,ent1.getMenuId()
													,ent1.getSubMenuId()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getMenuId()
													,ent2.getSubMenuId()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													,ent3.getMenuId()
													,ent3.getSubMenuId()
                                                    ,ent3.getCheckFlg()
                                                    ,ent3.getCheckFlgLimit()
													,ent4.getMenuId()
													,ent4.getSubMenuId()
													,ent4.getCheckFlg()
                                                    ,ent4.getCheckFlgLimit()
													,ent5.getMenuId()
													,ent5.getSubMenuId()
													,ent5.getCheckFlg()
                                                    ,ent5.getCheckFlgLimit()
													)
									);
					} else {
						viewList.add(new UIViewRole( ent1.getMenuId()
													,ent1.getSubMenuId()
													,ent1.getSubMenuName()
													,ent1.getRoleCd()
                                                    ,ent1.getCheckFlg()
                                                    ,ent1.getCheckFlgLimit()
													,ent2.getRoleCd()
                                                    ,ent2.getCheckFlg()
                                                    ,ent2.getCheckFlgLimit()
													,ent3.getRoleCd()
                                                    ,ent3.getCheckFlg()
                                                    ,ent3.getCheckFlgLimit()
													,ent4.getRoleCd()
                                                    ,ent4.getCheckFlg()
                                                    ,ent4.getCheckFlgLimit()
													,ent5.getRoleCd()
                                                    ,ent5.getCheckFlg()
                                                    ,ent5.getCheckFlgLimit()
													)
									);
					}
					break;
			}
		}
		return viewList;
	}
}
