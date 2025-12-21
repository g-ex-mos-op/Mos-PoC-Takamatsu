/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.MyMenuDao;
import jp.co.isid.mos.bird.framework.dao.TrnMyMenuDao;
import jp.co.isid.mos.bird.framework.entity.MyMenu;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.logic.RegMyMenuLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

/**
 * ユーザマイメニュー登録ロジック
 * 
 * 作成日:2008/12/18
 * @author xkinu
 *
 */
public class RegMyMenuLogicImpl implements RegMyMenuLogic {
    /** ロジックID */
    public static final String LOGIC_ID = PortalTopUtil.SCREEN_ID+"L06";
    
    /**
     * ユーザマイメニュー情報DAO
     */
	private MyMenuDao portalTopMyMenuDao;
    /**
     * ユーザマイメニュー情報DAO
     */
	private TrnMyMenuDao portalTopTrnMyMenuDao;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.portal.top.logic.RegMyMenuLogic#add(jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.portal.top.dto.PortalTopDto)
	 */
	public List add(BirdUserInfo birdUserInfo, PortalTopDto portalTopDto) {
		List listMyMenu=birdUserInfo.getListMyMenu();
		String userId = birdUserInfo.getUserID();
		String targetViewId = portalTopDto.getTargetMyMenuViewId();
		int registStatus = -1;
		for(int i=0; i<listMyMenu.size(); i++) {
			MyMenu entity = (MyMenu)listMyMenu.get(i);
			if(entity.getViewId().equals(targetViewId) ) {
                if (entity.getLastTmsp() != null) {
                    registStatus = 1;
    				if(PortalTopUtil.SAKUJO_ON.equals(entity.getSakujoFlg())) {
    					entity.setSakujoFlg(PortalTopUtil.SAKUJO_OFF);
    				}
                    entity.setLastPgm(PortalTopUtil.SCREEN_ID+"L");
                    entity.setLastUser(userId);
                    registStatus = getPortalTopTrnMyMenuDao().update(entity);
                }
                break;
			}
		}
		if(registStatus!=1) {
			//１．Entity[ユーザマイメニュー情報]を新しく生成します。
			MyMenu entity = new MyMenu();
	        //２．タイムスタンプを生成する。
	        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
	        //３．カラムに値を設定します。
			entity.setUserId(userId);
			entity.setViewId(targetViewId);
			entity.setSakujoFlg(PortalTopUtil.SAKUJO_OFF);
			entity.setFirstPgm(PortalTopUtil.SCREEN_ID+"L");
			entity.setFirstTmsp(currentTimestamp);
			entity.setFirstUser(userId);
			entity.setLastPgm(PortalTopUtil.SCREEN_ID+"L");
			entity.setLastTmsp(currentTimestamp);
			entity.setLastUser(userId);
			//４．DAO【ユーザマイメニュー情報】新規登録処理を実行します。
			getPortalTopTrnMyMenuDao().insert(entity);
		}
		// TODO 自動生成されたメソッド・スタブ
		return getListMyMenu(birdUserInfo);
	}

	/**
	 * 削除処理
	 * @see jp.co.isid.mos.bird.portal.top.logic.RegMyMenuLogic#delete(jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.portal.top.dto.PortalTopDto)
	 */
	public List delete(BirdUserInfo birdUserInfo, PortalTopDto portalTopDto) {
		List listMyMenu=birdUserInfo.getListMyMenu();
		String targetViewId = portalTopDto.getTargetMyMenuViewId();
		for(int i=0; i<listMyMenu.size(); i++) {
			MyMenu entity = (MyMenu)listMyMenu.get(i);
			if(entity.getViewId().equals(targetViewId)) {
				//１．ユーザーID
				String userId = birdUserInfo.getUserID();
		        //２．タイムスタンプを生成する。
		        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
		        //３．カラムに値を設定します。
				entity.setSakujoFlg(PortalTopUtil.SAKUJO_ON);//削除:1を設定します。
				entity.setLastPgm(PortalTopUtil.SCREEN_ID+"L");
				entity.setLastUser(userId);
				
				if(entity.getFirstTmsp() == null) {
					//新規登録処理
					//    (デフォルトにあってユーザ別にまだデータが存在しない場合)
					entity.setFirstPgm(PortalTopUtil.SCREEN_ID+"L");
					entity.setFirstTmsp(currentTimestamp);
					entity.setFirstUser(userId);
					entity.setLastTmsp(currentTimestamp);
					//５．DAO【ユーザマイメニュー情報】新規登録処理を実行します。
					getPortalTopTrnMyMenuDao().insert(entity);
				}
				else {
					//４．DAO【ユーザマイメニュー情報】更新登録処理を実行します。
					getPortalTopTrnMyMenuDao().update(entity);
				}
				break;
			}
		}
		// TODO 自動生成されたメソッド・スタブ
		return getListMyMenu(birdUserInfo);
	}
	private List getListMyMenu(BirdUserInfo birdUserInfo) {
		String userId = birdUserInfo.getUserID();
		// TODO 自動生成されたメソッド・スタブ
		return getPortalTopMenuDao().select(userId);
		
	}
	/**
	 * @return portalTopTrnMyMenuDao を戻します。
	 */
	public TrnMyMenuDao getPortalTopTrnMyMenuDao() {
		return portalTopTrnMyMenuDao;
	}

	/**
	 * @param portalTopTrnMyMenuDao を クラス変数portalTopMyMenuDaoへ設定します。
	 */
	public void setPortalTopTrnMyMenuDao(TrnMyMenuDao portalTopMyMenuDao) {
		this.portalTopTrnMyMenuDao = portalTopMyMenuDao;
	}

	/**
	 * @return portalTopMyMenuDao を戻します。
	 */
	public MyMenuDao getPortalTopMenuDao() {
		return portalTopMyMenuDao;
	}

	/**
	 * @param portalTopMyMenuDao を クラス変数portalTopMyMenuDaoへ設定します。
	 */
	public void setPortalTopMyMenuDao(MyMenuDao portalTopMyMenuDao) {
		this.portalTopMyMenuDao = portalTopMyMenuDao;
	}

}
