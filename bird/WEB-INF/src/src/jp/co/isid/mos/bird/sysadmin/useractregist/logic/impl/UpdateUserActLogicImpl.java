/*
 * 作成日: 2006/02/27
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.sysadmin.useractregist.dao.CtlUserActDao;
import jp.co.isid.mos.bird.sysadmin.useractregist.entity.UIBirdMenu;
import jp.co.isid.mos.bird.sysadmin.useractregist.logic.UpdateUserActLogic;

/**
 * ユーザサクセス権限設定登録ロジック
 * 
 * @author xkhata
 * 更新日:2012/01/26 xkinu
 *        隠しメニュー登録処理時の不具合
 *        １トランザクション内で同一行の更新を同じ条件で更新処理を重複して実行されてしまうため
 *        「このデータは既に更新されている為、更新できません。」のエラーが発生
 *   1.メインメニュー登録処理を削除
 *   2.隠しメニュー登録処理の修正
 *     [[登録要点]](仕様変更：2012/01/26 裏メニュー表示不具合対応)
 *     ・変更対象の隠しメニューではなくても、以下の条件を１つでも満たす場合は登録処理を行います。
 *     　(1)隠しメニューの登録済み個別設定フラグが'9'の場合。
 *     　(2)隠しメニューの個別設定が新規で、元メニュー全体の個別設定フラグが'1'(使用可)の場合。
 *     ・隠しメニューの個別設定フラグの値は、固定で'1'(使用可)のみを登録します。
 *     
 * 更新日:2009/01/30 xkinu
 * [P]Project\[L]2008年度\[15]ポータル再構築の欠陥一覧としてあがった問題を対応しました。
 * ＜変更内容＞
 * 　メインメニューの使用フラグの値は、紐付くサブメニューの個別設定フラグの設定の値によって、
 * 　ロジック内で判断する。
 * 　＜判断内容＞
 * 　　■メインメニューの個別設定フラグが'1'(権限あり)に設定する場合。
 * 　　　あるメインメニューのサブメニューのどれか１つでも個別設定フラグ＝'1'の場合に設定します。
 * 　　■メインメニューの個別設定フラグが'9'(権限無し)に設定する場合。
 * 　　　あるメインメニューのサブメニュー全てが個別設定フラグ＝'9'の場合に設定します。
 * 　　　
 * 　設定ボタン押下後、個別設定フラグ設定可能のメニュー情報が表示されるが、
 * 　その中にメインメニューの情報も表示されている。
 * 　メインメニュー(MENU_ID="00")の情報は表示しないように変更しました。
 * ＜修正理由＞
 * 　オーナーユーザはサブメニューはメインメニューというものに紐付いているという仕様は理解していない為、
 * 　あるサブメニューに個別設定フラグを与えるためサブメニューにチェックをしても、紐付いているメインメニューのチェック
 *   が外れているとメインメニューが表示されない為にサブメニューが表示されないということが判断できない為。
 * ＜目的＞
 * 　権限がない状態のメインメニューのサブメニューに１つでも権限を付加した場合は、
 * 　ロジック内で自動的に紐付くメインメニューの権限を有りに登録するようにする。
 * 　登録者がメインメニューとサブメニューの使用を理解しなくてもよくなります。
 */
public class UpdateUserActLogicImpl implements UpdateUserActLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA012L02";

    private static final String MAIN_MENU_ID = "00";
    private static final String HIDDEN_MENU = "1";
    /* DAO */
	private CtlUserActDao ctlUserActDao;

    /** 使用可 */
    private static final String STATUS_ON = "1";
    /** 個別設定フラグ：使用不可 */
    private static final String STATUS_OFF = "9";
    
    /**
     * 実行処理
     * 
	 * 更新日:2012/01/26 xkinu
	 *        隠しメニュー登録処理時の不具合
	 *        １トランザクション内で同一行の更新を同じ条件で更新処理を重複して実行されてしまうため
	 *        「このデータは既に更新されている為、更新できません。」のエラーが発生
	 *   1.メインメニュー登録処理を削除
	 *   2.隠しメニュー登録処理の修正
	 *   
	 * 更新日:2009/01/30 xkinu
	 * [P]Project\[L]2008年度\[15]ポータル再構築の欠陥一覧としてあがった問題を対応しました。
     */
	public void execute(Map listMap) throws ApplicationException {

        //メニュー情報リスト取得
        List actList = (List)listMap.get("menuInfo");
		String userId = (String)listMap.get("userId");
		//サブメニュー(機能名)情報保持Mapを作成します。
        Map mapSubMenu = new HashMap();
		//隠しメニュー個別設定フラグ情報保持Mapを作成します。
        Map mapHiddenMenuCstmzFlg = new HashMap();
        List listHiddenMenu = new ArrayList(0);

        for ( int i = 0; i < actList.size(); i++ ) {
            UIBirdMenu uiEntity = (UIBirdMenu)actList.get(i);
            String menuId = uiEntity.getMenuId();
            if(!MAIN_MENU_ID.equals(menuId) ){
                String menuAndSubMenuId = menuId+uiEntity.getSubMenuId();
            	if(!HIDDEN_MENU.equals(uiEntity.getHiddenFlg())) {
                    boolean isUpdateData = (uiEntity.isCheckFlgBefor() != uiEntity.getCheckFlg());
		        	if(isUpdateData) {
			        	//変更前と変更後が違う場合は、[登録対象].カスタマイズフラグへ
	            		//チェックがONの場合は”1”をOFFの場合は”0”(ゼロ)を設定します。
		            	uiEntity.setCustomizeFlg(uiEntity.getCheckFlg()?STATUS_ON:STATUS_OFF);
			        }
	        		mapSubMenu.put(menuAndSubMenuId, uiEntity);
            	}//end of if(!HIDDEN_MENU.equals(uiEntity.getHiddenFlg()))
            	else {
            		listHiddenMenu.add(uiEntity);
            	}
            }//end of if(!MAIN_MENU_ID.equals(menuId) )
        }//end for ( int i = 0; i < actList.size(); i++ )

        //隠しメニュー個別設定フラグ判定処理
        for ( int i = 0; i < listHiddenMenu.size(); i++ ) {
            UIBirdMenu uiEntity = (UIBirdMenu)listHiddenMenu.get(i);
    		String menuAndSubMenuId = uiEntity.getMenuId()+uiEntity.getSubMenuId();
            String motoMenuAndSubMenuId = uiEntity.getMotoMenuId()+uiEntity.getMotoSubMenuId();            		
    		//元メニューから個別設定フラグの値を判別します。
    		if(mapSubMenu.containsKey(motoMenuAndSubMenuId)) {
    			UIBirdMenu uiMotoMenuEntity = (UIBirdMenu)mapSubMenu.get(motoMenuAndSubMenuId);
    			settingCustmazeFlg(mapHiddenMenuCstmzFlg, menuAndSubMenuId, uiMotoMenuEntity);
    		}
        }//end for ( int i = 0; i < actList.size(); i++ )
        /*
         * 隠しメニュー個別設定フラグ設定処理
         * 
         * [[登録要点]](仕様変更：2012/01/26 裏メニュー表示不具合対応)
         * ・変更対象の隠しメニューではなくても、以下の条件を１つでも満たす場合は登録処理を行います。
         * 　(1)隠しメニューの登録済み個別設定フラグが'9'の場合。
         * 　(2)隠しメニューの個別設定が新規で、元メニュー全体の個別設定フラグが'1'(使用可)の場合。
         * ・隠しメニューの個別設定フラグの値は、固定で'1'(使用可)のみを登録します。
         */
        for ( int i = 0; i < listHiddenMenu.size(); i++ ) {
            UIBirdMenu uiEntity = (UIBirdMenu)listHiddenMenu.get(i);
    		String menuAndSubMenuId = uiEntity.getMenuId()+uiEntity.getSubMenuId();
    		//元メニューから個別設定フラグの値を判別します。
    		if(mapHiddenMenuCstmzFlg.containsKey(menuAndSubMenuId)) {
    			String customizeFlgNew = (String)mapHiddenMenuCstmzFlg.get(menuAndSubMenuId);
    			if(CommonUtil.isNull(uiEntity.getFirstUser()) && customizeFlgNew == null) {
    				//個別設定登録済みデータが無く、個別設定がnullの場合は何もしない。
    				continue;
    			}
    			else if(STATUS_ON.equals(uiEntity.getCustomizeFlg())) {
    				//既に使用可能に設定されている場合は何もしない。
    				continue;
    			}
    			else {
    				//チェックフラグの前後の値を変えます。(変更対象にする）
   					uiEntity.setCheckFlg(uiEntity.isCheckFlgBefor()?false:true);
   					//個別設定フラグの値は、固定で'1'(使用可)を設定します。
    				uiEntity.setCustomizeFlg(STATUS_ON);
    			}
    		}
        }//end for ( int i = 0; i < actList.size(); i++ )
		//登録処理
		for ( int i = 0; i < actList.size(); i++ ) {
			UIBirdMenu uiEntity = (UIBirdMenu)actList.get(i);
			String menuId = uiEntity.getMenuId();
			if(MAIN_MENU_ID.equals(menuId) ){
				//メインメニューの登録は行わない(仕様変更：カテゴリ表示対応2012/01/20から)
				continue;
			}
			if(uiEntity.isCheckFlgBefor() != uiEntity.getCheckFlg()
					&& uiEntity.getCustomizeFlg() != null) {
				String menuAndSubMenuId = menuId+uiEntity.getSubMenuId();
				if(HIDDEN_MENU.equals(uiEntity.getHiddenFlg())
						&& !mapHiddenMenuCstmzFlg.containsKey(menuAndSubMenuId)
						) {
					//既に他の画面のデータで登録済みの場合は、同一キーであるため
					//「?このデータは既に更新されている為、更新できません。」のエラーが発生するため
					//更新処理を行わない。
					continue;
				}
				uiEntity.setLastUser(userId);
				uiEntity.setLastPgm(LOGIC_ID.substring(0, 7));
				if(CommonUtil.isNull(uiEntity.getFirstUser())) {
					uiEntity.setFirstUser(userId);
					uiEntity.setFirstPgm(LOGIC_ID.substring(0, 7));
					uiEntity.setFirstTmsp(DateManager.getCurrentTimestamp());
					//新規登録処理を行います。
					getCtlUserActDao().insert(uiEntity);
				}
				else {
					//更新登録処理を行います。
					getCtlUserActDao().update(uiEntity);
				}
				if(HIDDEN_MENU.equals(uiEntity.getHiddenFlg())) {
					mapHiddenMenuCstmzFlg.remove(menuAndSubMenuId);
				}
			}
		}
	}
	/**
	 * 
	 * @param mapCstmzFlg
	 * @param mapKey
	 * @param uiEntity
	 */
	private void settingCustmazeFlg(Map mapCstmzFlg, String mapKey, UIBirdMenu uiEntity) {
		boolean isUpdateData = (uiEntity.isCheckFlgBefor() != uiEntity.getCheckFlg());
    	//個別設定フラグの値を設定します。
    	String customizeFlg = uiEntity.getCustomizeFlg();
    	if(isUpdateData && customizeFlg == null) {
    		customizeFlg = STATUS_ON.equals(uiEntity.getEnableFlg())?STATUS_ON:STATUS_OFF;
    	}
    	//既にMapへ登録されている場合は、値の優劣により設定値を変更します。
    	//【優劣内訳】(左側の値が設定されている場合は上書きできない)
    	//     使用可能＞データ無し(null)＞使用不可
    	//　・使用可能の場合、無条件でMapへ”使用可能”を設定する。
    	//　・データ無し(null)の場合、Map値が未設定か、又は”使用可能”以外の場合設定する。
    	//　・使用不可の場合、Map値が未設定の場合のみMapへ”使用不可”を設定する。
    	if(mapCstmzFlg.containsKey(mapKey) ) {
    		String putedCstmzFlg = (String) mapCstmzFlg.get(mapKey);
    		if(STATUS_ON.equals(putedCstmzFlg) || STATUS_OFF.equals(customizeFlg)) {
    			customizeFlg = putedCstmzFlg;
    		}
    	}
    	mapCstmzFlg.put(mapKey, customizeFlg);
	}
	/* ユーザ機能アクセス制限の更新Dao設定処理 */
	public void setCtlUserActDao(CtlUserActDao ctlUserActDao) {
		this.ctlUserActDao = ctlUserActDao;
	}
	
	/* ユーザ機能アクセス制限の更新Dao取得処理 */
	public CtlUserActDao getCtlUserActDao() {
		return this.ctlUserActDao;
	}


}
