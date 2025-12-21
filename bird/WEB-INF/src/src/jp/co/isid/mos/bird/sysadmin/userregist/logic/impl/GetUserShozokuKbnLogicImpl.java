/*
 * 作成日: 2008/11/17
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIGroupTogoMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserShozokuKbn;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstHanshaOnerDao;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetUserShozokuKbnLogic;

/**
 * ユーザ公開対象所属区分取得 ロジック
 * @author xnkusama
 */
public class GetUserShozokuKbnLogicImpl implements GetUserShozokuKbnLogic {

    /* ロジックＩＤ */
    public static final String LOGIC_ID = "BSA006L06";
    /* 店マスタ(UIGroupTogoMiseDao) */
    private UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao;

    /* ユーザ公開対象所属区分管理(MstUserShozokuDao） */
    private MstUserShozokuDao bizadminMstUserShozokuDao;
    /*販社オーナー*/
    private MstHanshaOnerDao mstHanshaOnerDao;

    /**
     * ユーザ公開対象所属区分
     * @param userRegistDto
     */
    public List execute(UserRegistDto dto) {
        List listShozokuKbn = new ArrayList();
        // １． パラメータ．ユーザタイプコード＝'01'（本部）の場合
        if (dto.getUserType().equals(UserType.HONBU)) {
        	listShozokuKbn = _honbuGetListUserShozokuKbn(dto);
        }
        // ２． パラメータ．ユーザタイプコード＝'02'（オーナー）の場合
        else if (dto.getUserType().equals(UserType.ONER)) {
        	listShozokuKbn = _onerGetListUserShozokuKbn(dto);
        }
        // ３． パラメータ.ユーザタイプコード が’03’(店舗)の場合
        else if (dto.getUserType().equals(UserType.TENPO)) {
        	listShozokuKbn = _tenpoGetListUserShozokuKbn(dto);
        }
        return listShozokuKbn;
    }
    /**
     * 本部ユーザー用
     * 登録ユーザー所属区分保持リスト取得処理
     * 
     * 
     * @param userTypeCd
     * @param rcompanyCd
     * @param miseCd
     * @return
     */
    private List _honbuGetListUserShozokuKbn(UserRegistDto sessionDto) {
		List listShozokuKbn = new ArrayList();
		List listHansha = null;
		UIUserOner userOner = sessionDto.getUserOner();
        if (CommonUtil.isNull(userOner.getOnerCd())) {
        	//販社オーナー情報テーブル検索
            listHansha = getMstHanshaOnerDao().getHanshaOner(
            		userOner.getCompanyCd(), userOner.getOnerCd());
        }
        //所属区分を決定します。
        if (listHansha == null || listHansha.isEmpty()) {
        	//オーナーコードが空(null or ブランク)の場合、
        	//又は販社オーナー情報(BM59HONR)に存在しないコードの場合は
        	//属性区分を本部に設定します。
            listShozokuKbn.add(UserShozokuKbn.HONBU);
        }
        else {
        	//販社オーナー情報(BM59HONR)に存在する場合は
        	//属性区分を販社に設定します。
            listShozokuKbn.add(UserShozokuKbn.HANSHA);
        }
        return listShozokuKbn;
    }

    /**
     * オーナーユーザー用
     * 登録ユーザー所属区分保持リスト取得処理
     * 
     * @param sessionDto
     * @return
     */
    private List _onerGetListUserShozokuKbn(UserRegistDto sessionDto) {
    	List listUserShozokuKbn = new ArrayList();
        List listHansha = null;
        List listOner = sessionDto.getUserOnerList();
        for (Iterator ite = listOner.iterator(); ite.hasNext();) {
            UIUserOner entity = (UIUserOner) ite.next();
            if(entity.isChecked()) {
                listHansha = getMstHanshaOnerDao().getHanshaOner(
                		entity.getCompanyCd(), entity.getOnerCd());
                if (listHansha != null && !listHansha.isEmpty()) {
                    break;
                }
            }
        }
        if (listHansha == null || listHansha.isEmpty()) {
        	listUserShozokuKbn.add(UserShozokuKbn.ONER);
        	listUserShozokuKbn.add(UserShozokuKbn.FC_TENPO);
        }
        else {
        	listUserShozokuKbn.add(UserShozokuKbn.HANSHA);
        	listUserShozokuKbn.add(UserShozokuKbn.HANSHA_TENPO);
        }
    	return listUserShozokuKbn;
    }
    /**
     * 店舗ユーザー用
     * ユーザー所属区分取得
     * @param sessionDto
     * @return
     */
    private List _tenpoGetListUserShozokuKbn(UserRegistDto sessionDto) {
    	List listUserShozokuKbn = new ArrayList();
        String hanshaOwner = getBizadminUIGroupTogoMiseDao().selectHansha(
        		sessionDto.getUserMise().getMiseCd()
        		, sessionDto.getUserMise().getCompanyCd());       
    
        if(hanshaOwner != null){
        	listUserShozokuKbn.add(UserShozokuKbn.HANSHA_TENPO);  
        } else {
            String aiteKbn = getBizadminMstUserShozokuDao().select(
            		sessionDto.getUserMise().getMiseCd()
            		, sessionDto.getUserMise().getCompanyCd());
            if (aiteKbn != null) {
                if (aiteKbn.equals("2")) {
                	listUserShozokuKbn.add(UserShozokuKbn.RC_TENPO);
                } else {
                	listUserShozokuKbn.add(UserShozokuKbn.FC_TENPO);
                }
            }               
        }
    	return listUserShozokuKbn;
    }
    /**
     * ユーザー所属Dao
     * @return
     */
    public MstUserShozokuDao getBizadminMstUserShozokuDao() {
        return bizadminMstUserShozokuDao;
    }
    /**
     * ユーザー所属Dao取得処理
     * @param mstUserShozokuDao
     */
    public void setBizadminMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
        this.bizadminMstUserShozokuDao = mstUserShozokuDao;
    }

    public MstHanshaOnerDao getMstHanshaOnerDao() {
        return mstHanshaOnerDao;
    }

    public void setMstHanshaOnerDao(MstHanshaOnerDao mstHanshaOnerDao) {
        this.mstHanshaOnerDao = mstHanshaOnerDao;
    }

	/**
	 * @param bizadminUIGroupTogoMiseDao を クラス変数bizadminUIGroupTogoMiseDaoへ設定します。
	 */
	public void setBizadminUIGroupTogoMiseDao(
			UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao) {
		this.bizadminUIGroupTogoMiseDao = bizadminUIGroupTogoMiseDao;
	}

	/**
	 * @return クラス変数bizadminUIGroupTogoMiseDao を戻します。
	 */
	public UIGroupTogoMiseDao getBizadminUIGroupTogoMiseDao() {
		return bizadminUIGroupTogoMiseDao;
	}

}