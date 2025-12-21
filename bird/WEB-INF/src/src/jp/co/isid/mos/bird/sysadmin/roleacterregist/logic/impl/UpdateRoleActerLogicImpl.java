/*
 * 作成日: 2006/02/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.functionsearch.entity.UIFunction;
import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.CtlRoleActerDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.CtlRoleActer;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.UIViewMenu;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.UIViewRole;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.UpdateRoleActerLogic;

/**
 * @author xkhata
 *
 */
public class UpdateRoleActerLogicImpl implements UpdateRoleActerLogic{

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA007L03";

	private CtlRoleActerDao ctlRoleActerDao;
	
	/**
	 * レコードのコンバート＋delete,insertを実行
	 * 
	 * @param Map
	 */
	public void execute(Map listMap) {
		
        //更新用エンティティ格納リスト
		List updateList = new ArrayList();
		//メインメニュー情報保持Ｍａｐを作成します。
        Map mapMainMenuRowNo = new HashMap();

        Map allMap = (Map)listMap.get("allMap");
		String userId = (String)listMap.get("user");
        /*
         * 機能のとき『メニューID + サブメニューID』のリスト取得
         * ロールの時『メニューID』のリスト取得
         */
		List initList = (List)listMap.get("initlist");

        //----------------
        //機能のとき
        //----------------
		if ( listMap.get("pullid").equals("0") ) {	
			List menuList = (List)listMap.get("menulist");	
			int realSize = menuList.size();

            //更新対象の[メニューID + サブメニューID]文字列の配列の作成
            String[] menuStr ={"","","","",""}; 
			for( int i = 0; i < realSize; i++ ) {
				UIFunction uif = (UIFunction)menuList.get(i);
				menuStr[i] = uif.getMenuId() + uif.getSubMenuId();
			}
			
		
			for ( int i = 0; i < initList.size(); i++ ) {
				List mapList = (List)allMap.get(initList.get(i));
				for ( int k = 0; k < mapList.size(); k++ ) {
					UIViewMenu ui = (UIViewMenu)mapList.get(k);
					for ( int j = 1; j <= realSize; j++ ) {

                        //更新用エンティティ
                        CtlRoleActer entity = new CtlRoleActer();
						entity.setRoleCd(ui.getRoleCd());
						entity.setFirstUser(userId);
						String menuId = "";
						String subMenuId = "";
						boolean checkFlg = ui.getCheckFlg1();
						boolean limitFlg = ui.getCheckFlg1Limit();
						switch(j) {
							case 1:
								menuId = ui.getMenuId1();
								subMenuId = ui.getSubMenuId1();
								checkFlg = ui.getCheckFlg1();
								limitFlg = ui.getCheckFlg1Limit();
                                break;
							case 2:
								menuId = ui.getMenuId2();
								subMenuId = ui.getSubMenuId2();
								checkFlg = ui.getCheckFlg2();
								limitFlg = ui.getCheckFlg2Limit();
                               break;
							case 3:
								menuId = ui.getMenuId3();
								subMenuId = ui.getSubMenuId3();
								checkFlg = ui.getCheckFlg3();
								limitFlg = ui.getCheckFlg3Limit();
                                break;
							case 4:
								menuId = ui.getMenuId4();
								subMenuId = ui.getSubMenuId4();
								checkFlg = ui.getCheckFlg4();
								limitFlg = ui.getCheckFlg4Limit();
                                break;
							case 5:
								menuId = ui.getMenuId5();
								subMenuId = ui.getSubMenuId5();
								checkFlg = ui.getCheckFlg5();
								limitFlg = ui.getCheckFlg5Limit();
                                break;
						}//end of switch(j)
						String key = entity.getRoleCd()+menuId;
						Integer[] iMainMenuInfo = (Integer[])mapMainMenuRowNo.get(key);
						if(iMainMenuInfo == null) {
							iMainMenuInfo = new Integer[]{new Integer(0),new Integer(0)};
						}
						//初期チェック数
						int enableCnt = iMainMenuInfo[0].intValue();
						//上限チェック数
						int extraCnt = iMainMenuInfo[1].intValue();
						
						if(checkFlg || limitFlg) {
							entity.setMenuId(menuId);
							entity.setSubMenuId(subMenuId);
							updateList.add(entity);
						}

				        //使用可能フラグ
				        if(checkFlg){
				            entity.setEnableFlg("1");
				            enableCnt++;
				        }else{
				            entity.setEnableFlg("0");
				        }

				        //上限拡張フラグ
				        if(limitFlg){
				            entity.setExtraFlg("1");
				            extraCnt++;
				        }else{
				            entity.setExtraFlg("0");
				        }
				        iMainMenuInfo[0] = new Integer(enableCnt);
				        iMainMenuInfo[1] = new Integer(extraCnt);
				        mapMainMenuRowNo.put(key, iMainMenuInfo);
					}
				}
			}

			for(int i = 0; i < initList.size(); i++ ) {
				getCtlRoleActerDao().deleteActerMenu(
						(String)initList.get(i),menuStr[0],menuStr[1],menuStr[2],menuStr[3],menuStr[4]);
			}
			
		}
		//----------------
        // ロールの時
        //----------------
        else {
			List roleList = (List)listMap.get("rolelist");
			int realSize = roleList.size();

            //更新対象のロールコード配列の作成
            String[] roleStr ={"","","","",""}; 
			for( int i = 0; i < realSize; i++ ) {
				UIRole ui = (UIRole)roleList.get(i);
				roleStr[i] = ui.getRoleCd();
			}
			for ( int i = 0; i < initList.size(); i++ ) {
				List mapList = (List)allMap.get(initList.get(i));
				for ( int k = 0; k < mapList.size(); k++ ) {
					UIViewRole ui = (UIViewRole)mapList.get(k);
					for ( int j = 1; j <= realSize; j++ ) {
                        
                        //更新用エンティティ
						CtlRoleActer entity = new CtlRoleActer();
						entity.setMenuId(ui.getMenuId());                //メニューID
						entity.setSubMenuId(ui.getSubMenuId());          //サブメニューID
						entity.setFirstUser(userId);                      //登録ユーザ
						String roleCd = "";
						boolean checkFlg = ui.getCheckFlg1();
						boolean limitFlg = ui.getCheckFlg1Limit();
						switch(j) {
							case 1:
								roleCd = ui.getRoleCd1();
								checkFlg = ui.getCheckFlg1();
								limitFlg = ui.getCheckFlg1Limit();
                                break;
							case 2:
								roleCd = ui.getRoleCd2();
								checkFlg = ui.getCheckFlg2();
								limitFlg = ui.getCheckFlg2Limit();
                               break;
							case 3:
								roleCd = ui.getRoleCd3();
								checkFlg = ui.getCheckFlg3();
								limitFlg = ui.getCheckFlg3Limit();
                                break;
							case 4:
								roleCd = ui.getRoleCd4();
								checkFlg = ui.getCheckFlg4();
								limitFlg = ui.getCheckFlg4Limit();
                                break;
							case 5:
								roleCd = ui.getRoleCd5();
								checkFlg = ui.getCheckFlg5();
								limitFlg = ui.getCheckFlg5Limit();
                                break;
						}//end of switch(j)
						String key = roleCd+entity.getMenuId();
						Integer[] iMainMenuInfo = (Integer[])mapMainMenuRowNo.get(key);
						if(iMainMenuInfo == null) {
							iMainMenuInfo = new Integer[]{new Integer(0),new Integer(0)};
						}
						//初期チェック数
						int enableCnt = iMainMenuInfo[0].intValue();
						//上限チェック数
						int extraCnt = iMainMenuInfo[1].intValue();
						
						if(checkFlg || limitFlg) {
							entity.setRoleCd(roleCd);
							updateList.add(entity);
						}

				        //使用可能フラグ
				        if(checkFlg){
				            entity.setEnableFlg("1");
				            enableCnt++;
				        }else{
				            entity.setEnableFlg("0");
				        }

				        //上限拡張フラグ
				        if(limitFlg){
				            entity.setExtraFlg("1");
				            extraCnt++;
				        }else{
				            entity.setExtraFlg("0");
				        }
				        iMainMenuInfo[0] = new Integer(enableCnt);
				        iMainMenuInfo[1] = new Integer(extraCnt);
				        mapMainMenuRowNo.put(key, iMainMenuInfo);
					}
				}
					
			}
				
			for(int i = 0; i < initList.size(); i++ ) {
				getCtlRoleActerDao().deleteActerRole(
						(String)initList.get(i),roleStr[0],roleStr[1],roleStr[2],roleStr[3],roleStr[4]);
			}
		}
        //ここまでロールの時

        //サブメニュー新規登録処理
		for(int i = 0; i < updateList.size(); i++ ) {
			CtlRoleActer ctl = (CtlRoleActer)updateList.get(i);
            getCtlRoleActerDao().insertRoleActer(
                    ctl.getRoleCd(), ctl.getMenuId(), ctl.getSubMenuId(), ctl.getEnableFlg(), ctl.getExtraFlg(), ctl.getFirstUser());
		}
		//メインメニューのロール登録処理
		//全メインメニューのロール情報を削除します。
		getCtlRoleActerDao().deleteAllMainMenu();
		//新規登録します。
		getCtlRoleActerDao().insertRemakeAllMainMenu(userId);        
	}
	/* CtlRoleActerDao DI */
	public void setCtlRoleActerDao(CtlRoleActerDao ctlRoleActerDao) {
		this.ctlRoleActerDao = ctlRoleActerDao;
	}
	
	public CtlRoleActerDao getCtlRoleActerDao() {
		return this.ctlRoleActerDao;
	}
	

}
