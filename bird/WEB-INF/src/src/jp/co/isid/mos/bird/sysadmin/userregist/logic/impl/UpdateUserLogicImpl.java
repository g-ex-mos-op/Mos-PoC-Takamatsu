/*
 * 作成日: 2006/2/20
 * 更新日: 2006/07/04
 * 更新日: 2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserGyotaiDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserOnerDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIGroupTogoMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserOner;
import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIGroupTogoMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.sysadmin.userregist.dao.CtlUserRoleDao;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodRoleType;
import jp.co.isid.mos.bird.sysadmin.userregist.entity.CtlUserRole;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetUserShozokuKbnLogic;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.UpdateUserLogic;

/**
 * ユーザ情報の更新処理ロジック
 * @author itamoto
 */
public class UpdateUserLogicImpl implements UpdateUserLogic {

    /* ロジックＩＤ */
    public static final String LOGIC_ID = "BSA006L03";

    /* UIUserDao */
    private UIUserDao bizadminUIUserDao;

    /* ユーザ所属会社(MstUserCompanyDao） */
    private MstUserCompanyDao bizadminMstUserCompanyDao;

    /* ユーザ管理業態(MstUserGyotaiDao） */
    private MstUserGyotaiDao bizadminMstUserGyotaiDao;

    /* ユーザ公開対象所属区分管理(MstUserShozokuDao） */
    private MstUserShozokuDao bizadminMstUserShozokuDao;

    /* ユーザ対応店舗(MstUserMiseDao) */
    private MstUserMiseDao bizadminMstUserMiseDao;

    /* ユーザ対応オーナー(MstUserOnerDao) */
    private MstUserOnerDao bizadminMstUserOnerDao;

    /* 店マスタ(UIGroupTogoMiseDao) */
    private UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao;

    /* ロール設定(CtlUserRoleDao) */
    private CtlUserRoleDao userRegistCtlUserRoleDao;
    
    private UpdatePasswordLogic updatePasswordLogic;
    /* GetUserShozokuKbnLogicImpl */
    private GetUserShozokuKbnLogic getUserShozokuKbnLogic;

    private static final String RC = "2";

    private static final String FC = "1";

    /**
     * BIRDユーザの新規登録
     * @param userRegistDto
     */
    public List execute(UserRegistDto userRegistDto) {
    	Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // １．BIRDユーザ新規登録(BR01USER)
        insertBirdUser(userRegistDto, currentTimestamp);

        // ３．ユーザ所属会社新規登録(BM03USCP)
        insertUserCompany(userRegistDto, currentTimestamp);

        // ４．ユーザ管理業態新規登録(BM05USGT)
        insertBM05USGT(userRegistDto, currentTimestamp);

        // ５．ユーザ公開対象所属区分管理新規登録(BC13SHKM)
        insertUserShozoku(userRegistDto, currentTimestamp);

        // ６．ユーザ対応店舗新規登録(BM07UTEN)
        insertUserMise(userRegistDto, currentTimestamp);

        // ７．ユーザ対応オーナ新規登録(BM06UONR)
        insertUserOner(userRegistDto, currentTimestamp);

        // ８．全ユーザ新規登録(BM04USRL)
        insertUserRole(userRegistDto, currentTimestamp);
        
        // ９．パスワード登録
        insertPassword(userRegistDto);

        return null;
    }

    /**
     * パスワード登録
     * @param userRegistDto
     */
    private void insertPassword(UserRegistDto userRegistDto) {
        // 【共通ロジック】パスワード更新を実行
        getUpdatePasswordLogic().execute(userRegistDto.getUIUser().getUserId(), 
                                            userRegistDto.getUserPassWord(), 
                                            UpdatePasswordLogic.HENKO_KBN_INSERT, 
                                            userRegistDto.getBirdUserInfo().getUserID(),
                                            userRegistDto.VIEW_ID);
    }
    
    /**
     * BIRDユーザ新規登録
     * @param userRegistDto
     */
    private void insertBirdUser(UserRegistDto userRegistDto, Timestamp currentTimestamp) {
		String loginUserId = userRegistDto.getBirdUserInfo().getUserID();
    	UIUser uIUser = userRegistDto.getUIUser();
    	String userTypeCd=userRegistDto.getUIUser().getUsertypeCd();
        // 2006/07/04 ADD START パスワード追加対応 INAZAWA
        // 新規．[BIRDユーザ].パスワード ← パラメータ.初期パスワード
        // 2006/07/04 ADD END パスワード追加対応 INAZAWA
    	uIUser.setUserPswd(userRegistDto.getUserPassWord().getBytes());
        // 新規．[BIRDユーザ].パスワード更新日 ← ""
    	uIUser.setPswdupdtDt(userRegistDto.getBirdDateInfo().getSysDate());
        // 新規．[BIRDユーザ].前回パスワード ← ""
    	uIUser.setLastPswd("".getBytes());
        //本部の場合
        if (UserType.isHonbu(userTypeCd)) {
        	uIUser.setSekyuFlg("2");
        }
        else {
        	//支部制限フラグOFFを設定します。
        	uIUser.setLimitKbn("0");
        }
        // 日付フォーマット(YYYYMMDD型)
        DateFormatter dateFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        uIUser.setSeikyuDt(dateFormatter.format(userRegistDto.getUIUser()
                .getSeikyuDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
        uIUser.setSeikyuUpdtDt(dateFormatter.format(userRegistDto.getUIUser()
                .getSeikyuUpdtDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
        uIUser.setAppliedDt(dateFormatter.format(userRegistDto.getUIUser()
                .getAppliedDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));

        uIUser.setFirstUser(loginUserId);
        uIUser.setFirstPgm(userRegistDto.VIEW_ID);
        uIUser.setFirstTmsp(currentTimestamp);
        uIUser.setLastUser(loginUserId);
        uIUser.setLastPgm(userRegistDto.VIEW_ID);
        uIUser.setLastTmsp(currentTimestamp);

        bizadminUIUserDao.insert(uIUser);
    }
    /**
     * ユーザ所属会社新規登録
     * @param userRegistDto
     */
    private void insertUserCompany(UserRegistDto userRegistDto, Timestamp currentTimestamp) {
    	UIUser uIUser = userRegistDto.getUIUser();
        MstUserCompany newMstUserCompany = userRegistDto.getUserCompany();
        newMstUserCompany.setUserId(uIUser.getUserId());
        newMstUserCompany.setFirstUser(userRegistDto.getBirdUserInfo().getUserID());
        newMstUserCompany.setFirstPgm(userRegistDto.VIEW_ID);
        newMstUserCompany.setFirstTmsp(currentTimestamp);
        newMstUserCompany.setLastUser(userRegistDto.getBirdUserInfo().getUserID());
		newMstUserCompany.setLastPgm(userRegistDto.VIEW_ID);
		newMstUserCompany.setLastTmsp(currentTimestamp);

        if (UserType.isHonbu(uIUser.getUsertypeCd())) {
        	String onerCd = userRegistDto.getUserOner().getOnerCd();
            // 新規．[ユーザ所属会社].属性区分 
        	// オーナーコードが指定されている場合は、'2'(契約)
        	// オーナーが指定されていない場合は'1'(所属)を設定します。
        	newMstUserCompany.setZokuseiKbn(onerCd==null?"1":"2");
        	newMstUserCompany.setRCompanyCd(userRegistDto.getUserCompany().getRCompanyCd());
        	//DAO【ユーザー所属会社】新規登録を行います。
        	bizadminMstUserCompanyDao.insert(newMstUserCompany);
        }
        if (UserType.isOner(uIUser.getUsertypeCd())) {
        	for(int o=0; o<userRegistDto.getUserOnerList().size(); o++) {
        		UIUserOner eOner = (UIUserOner)userRegistDto.getUserOnerList().get(o);
        		if(eOner.isChecked()) {
        			//新規．[ユーザ所属会社].属性区分 ←'2'(契約)
        			newMstUserCompany.setZokuseiKbn("2");
        			newMstUserCompany.setRCompanyCd(eOner.getCompanyCd());
	            	//DAO【ユーザー所属会社】新規登録を行います。
	            	bizadminMstUserCompanyDao.insert(newMstUserCompany);
        		}
        	}
        }
        if (UserType.isTenpo(uIUser.getUsertypeCd())) {
            // 属性区分決定
            String zokuseiKbn = "";
            List list = getBizadminUIGroupTogoMiseDao().selectMise(
            		userRegistDto.getUserMise().getMiseCd()
            		, userRegistDto.getUserCompany().getRCompanyCd());
            if (list != null && list.size() > 0) {
                UIGroupTogoMise entity = (UIGroupTogoMise) list.get(0);
                String miseKbn = entity.getMiseKbn();
                if (miseKbn != null && miseKbn.length() >= 2) {
                    if (RC.equals(miseKbn.substring(1, 2))) {
                        zokuseiKbn = "1";//RC→所属
                    } else if(FC.equals(miseKbn.substring(1, 2))) {
                        zokuseiKbn = "2";//FC→契約
                    }
                }
            }
        	newMstUserCompany.setZokuseiKbn(zokuseiKbn);
        	newMstUserCompany.setRCompanyCd(userRegistDto.getUserCompany().getRCompanyCd());
        	//DAO【ユーザー所属会社】新規登録を行います。
        	bizadminMstUserCompanyDao.insert(newMstUserCompany);
       }
    }

    /**
     * ユーザ管理業態新規登録
     * @param sessionDto
     */
    private void insertBM05USGT(UserRegistDto userRegistDto, Timestamp currentTimestamp)
    {
    	if(UserType.isTenpo(userRegistDto.getUIUser().getUsertypeCd())) {
            UIGroupTogoMise eMstGTMise = null;
            //直営店舗ユーザの場合にe-mosslesへ連携しない
            List list = getBizadminUIGroupTogoMiseDao().selectMise(
            		userRegistDto.getUserMise().getMiseCd(), userRegistDto.getUserMise().getCompanyCd());
            eMstGTMise = (UIGroupTogoMise)list.get(0);
            //登録用List[[ユーザ管理業態]]を作成します。
            List gyoutaiList = new ArrayList(0);
            UIUserGyotai uiUserGyotai = new UIUserGyotai();
        	// 選択した店舗の業態を登録用List[[ユーザ管理業態]]へ設定します。
            uiUserGyotai.setGyotaiKbn(eMstGTMise.getGyotaiKbn());
            gyoutaiList.add(uiUserGyotai);
            //DTO【自画面Session】へ登録用List[[ユーザ管理業態]]を設定します。
            userRegistDto.setUserGyotaiList(gyoutaiList);
    	}
    	if(userRegistDto.getUserGyotaiList() != null){
    		String loginUserId = userRegistDto.getBirdUserInfo().getUserID();
	        /* 新しいデータを入力 */
	        UIUserGyotai gyotai = new UIUserGyotai();
	
	        gyotai.setFirstPgm(userRegistDto.VIEW_ID);
	        gyotai.setFirstUser(loginUserId);
	        gyotai.setFirstTmsp(currentTimestamp);
	        gyotai.setLastPgm(userRegistDto.VIEW_ID);
	        gyotai.setLastUser(loginUserId);
	        gyotai.setLastTmsp(currentTimestamp);
	        for (int j = 0; j < userRegistDto.getUserGyotaiList().size(); j++) {
                if (userRegistDto.getUserGyotaiList().get(j) instanceof UIGyotai) {
                    UIGyotai unit = (UIGyotai) userRegistDto.getUserGyotaiList().get(j);
                    gyotai.setGyotaiKbn(unit.getGyotaiKbn());
                }
                if (userRegistDto.getUserGyotaiList().get(j) instanceof UIUserGyotai) {
                    UIUserGyotai unit = (UIUserGyotai) userRegistDto.getUserGyotaiList().get(j);
                    gyotai.setGyotaiKbn(unit.getGyotaiKbn());
                }
                gyotai.setUserId(userRegistDto.getUIUser().getUserId());
                bizadminMstUserGyotaiDao.insert(gyotai);
            }
        }
    }

    /**
     * ユーザ公開対象所属区分管理新規登録
     * @param userRegistDto
     */
    private void insertUserShozoku(UserRegistDto userRegistDto, Timestamp currentTimestamp) {
    	UIUser uIUser = userRegistDto.getUIUser();
		String loginUserId = userRegistDto.getBirdUserInfo().getUserID();
        MstUserShozoku mstUserShozoku = new MstUserShozoku();
        mstUserShozoku.setFirstUser(loginUserId);
        mstUserShozoku.setFirstPgm(userRegistDto.VIEW_ID);
        mstUserShozoku.setFirstTmsp(currentTimestamp);
        mstUserShozoku.setLastUser(loginUserId);
        mstUserShozoku.setLastPgm(userRegistDto.VIEW_ID);
        mstUserShozoku.setLastTmsp(currentTimestamp);
        mstUserShozoku.setUserId(userRegistDto.getUIUser().getUserId());
        // ①．新規．[ユーザ公開対象所属区分管理]を作成する
        // １． パラメータ．ユーザタイプコード＝'01'（本部）の場合
        if (UserType.isHonbu(uIUser.getUsertypeCd())) {
            // Ⅰ． [ユーザ公開対象所属区分管理] を以下の内容で新規作成する。
            // ・ 新規．[ユーザ公開対象所属区分管理].ユーザID ← パラメータ.ユーザID
            // ・ 新規．[ユーザ公開対象所属区分管理].所属区分 ← 1’
            mstUserShozoku.setShozokuKbn("1");
            bizadminMstUserShozokuDao.insert(mstUserShozoku);
        }
        // ２． パラメータ．ユーザタイプコード＝'02'（オーナー）の場合
        if (UserType.isOner(uIUser.getUsertypeCd())) {
            List listShozokuKbn = getGetUserShozokuKbnLogic().execute(userRegistDto);
            if (listShozokuKbn != null) {
                for (Iterator ite = listShozokuKbn.iterator(); ite.hasNext();) {
                    /*所属区分Entity*/                 
                    mstUserShozoku.setShozokuKbn(ite.next().toString());                    
                    bizadminMstUserShozokuDao.insert(mstUserShozoku);
                }
            }
        }

        // ３． パラメータ.ユーザタイプコード が’03’(店舗)の場合
        if (UserType.isTenpo(uIUser.getUsertypeCd())) {
            List listShozokuKbn = getGetUserShozokuKbnLogic().execute(userRegistDto);
            if (listShozokuKbn != null) {
                for (Iterator ite = listShozokuKbn.iterator(); ite.hasNext();) {
                    mstUserShozoku.setShozokuKbn(ite.next().toString());
                    bizadminMstUserShozokuDao.insert(mstUserShozoku);
                }
            }
        }
    }

    /**
     * ユーザ対応店舗新規登録
     * @param userRegistDto
     */
    private void insertUserMise(UserRegistDto userRegistDto, Timestamp currentTimestamp) {
        // ６．パラメータ．[[店マスタ]]が存在する場合、ユーザ対応店舗を新規登録する。
        if (userRegistDto.getUserMise() != null) {
	    	String loginUserId = userRegistDto.getBirdUserInfo().getUserID();
	        MstUserMise mstUserMise = userRegistDto.getUserMise();
	        mstUserMise.setCompanyCd(userRegistDto.getUserCompany().getRCompanyCd());
	        mstUserMise.setUserId(userRegistDto.getUIUser().getUserId());
	        mstUserMise.setFirstUser(loginUserId);
	        mstUserMise.setFirstPgm(userRegistDto.VIEW_ID);
	        mstUserMise.setFirstTmsp(currentTimestamp);
	        mstUserMise.setLastUser(loginUserId);
	        mstUserMise.setLastPgm(userRegistDto.VIEW_ID);
	        mstUserMise.setLastTmsp(currentTimestamp);
            bizadminMstUserMiseDao.insert(mstUserMise);
        }
    }

    /**
     * ユーザ対応オーナー新規登録
     * @param userRegistDto
     */
    private void insertUserOner(UserRegistDto userRegistDto, Timestamp currentTimestamp) {
        // ７．パラメータ．.[[オーナーマスタ]]が存在する場合、ユーザ対応オーナーを新規登録する。
        if (userRegistDto.getUserOnerList() != null) {
	    	String loginUserId = userRegistDto.getBirdUserInfo().getUserID();
	        MstUserOner mstUserOner = new MstUserOner();
	        mstUserOner.setFirstUser(loginUserId);
	        mstUserOner.setFirstPgm(userRegistDto.VIEW_ID);
	        mstUserOner.setFirstTmsp(currentTimestamp);
	        mstUserOner.setLastUser(loginUserId);
	        mstUserOner.setLastPgm(userRegistDto.VIEW_ID);
	        mstUserOner.setLastTmsp(currentTimestamp);

            // ①．新規．[[ユーザ対応オーナー]]を作成する。パラメータ.[[オーナーマスタ]]の件数分下記の処理を行う。
            for (Iterator i = userRegistDto.getUserOnerList().iterator(); i.hasNext();) {
            	UIUserOner entity = (UIUserOner) i.next();
            	if(entity.isChecked()) {
	                // 1． [ユーザ対応オーナー] を以下の内容で新規作成する。
	                // ・ 新規．[ユーザ対応オーナー].ユーザID ← パラメータ.ユーザID
	                // ・ 新規．[ユーザ対応オーナー].管理会社企業コード ← パラメータ.[[オーナーマスタ]].管理会社企業コード
	                // ・ 新規．[ユーザ対応オーナー].オーナーコード ← パラメータ.[[オーナーマスタ]].店コード
	                mstUserOner.setUserId(userRegistDto.getUIUser().getUserId());
	                mstUserOner.setCompanyCd(entity.getCompanyCd());
	                mstUserOner.setOnerCd(entity.getOnerCd());
	
	                // ②．Dao【ユーザ対応オーナー．ユーザ対応オーナーの新規登録】を実行する。
	                // パラメータ：７.①で作成した新規．[[ユーザ対応オーナー]]
	                bizadminMstUserOnerDao.insert(mstUserOner);
            	}
            }
        }
    }

    /**
     * ロール設定処理（全ユーザー）
     * @param userRegistDto
     */
    private void insertUserRole(UserRegistDto userRegistDto, Timestamp currentTimestamp) {
    	String loginUserId = userRegistDto.getBirdUserInfo().getUserID();
        CtlUserRole ctlUserRole = new CtlUserRole();
        List roleList = userRegistDto.getRoleCode();

        // 新規．[ロール設定].ユーザID ← パラメータ.ユーザID
        ctlUserRole.setUserId(userRegistDto.getUIUser().getUserId());

        // 新規．[ロール設定].ユーザ名称 ← パラメータ.ユーザ名称
        ctlUserRole.setRoleCd("001");

        // 新規．[ロール設定].ユーザ名称(カナ) ← パラメータ.ユーザ名称(カナ)
        ctlUserRole.setBatFlg("0");

        ctlUserRole.setFirstUser(loginUserId);
        ctlUserRole.setFirstPgm(userRegistDto.VIEW_ID);
        ctlUserRole.setFirstTmsp(currentTimestamp);
        ctlUserRole.setLastUser(loginUserId);
        ctlUserRole.setLastPgm(userRegistDto.VIEW_ID);
        ctlUserRole.setLastTmsp(currentTimestamp);
        
        //まずは共通ロール001をinsertする。
        userRegistCtlUserRoleDao.insertUserRole(ctlUserRole);
        
/*************************** 2007/02/05 add start xamaruyama ***************************/
        //ここで各契約タイプに応じたロールをinsertする。

        //ユーザタイプが本部以外の場合、以下のfor文に入る。
        if (!UserType.isHonbu(userRegistDto.getUIUser().getUsertypeCd())) {
            for (int i = 0; i < roleList.size(); i++) {
                CodRoleType entity = (CodRoleType) roleList.get(i);
                ctlUserRole.setRoleCd(entity.getRoleCd());
                userRegistCtlUserRoleDao.insertUserRole(ctlUserRole);
            }
        }
    }
/*************************** 2007/01/31 add end xamaruyama ***************************/
    /**
     * BIRDユーザ情報取得Daoを取得します。
     * @return bizadminUIUserDao を戻します。
     */
    public UIUserDao getBizadminUIUserDao() {
        return bizadminUIUserDao;
    }

    /**
     * BIRDユーザ情報取得Daoを設定します。
     * @param bizadminUIUserDao bizadminUIUserDao を設定。
     */
    public void setBizadminUIUserDao(UIUserDao uIUserDao) {
        this.bizadminUIUserDao = uIUserDao;
    }

    /**
     * ユーザ所属会社Daoの設定
     * @return bizadminMstUserCompanyDao を戻します。
     */
    public MstUserCompanyDao getBizadminMstUserCompanyDao() {
        return bizadminMstUserCompanyDao;
    }

    /**
     * ユーザ所属会社Daoの設定
     * @param bizadminMstUserCompanyDao bizadminMstUserCompanyDao を設定。
     */
    public void setBizadminMstUserCompanyDao(MstUserCompanyDao mstUserCompanyDao) {
        this.bizadminMstUserCompanyDao = mstUserCompanyDao;
    }

    /**
     * ロール設定Daoの設定
     * @return userRegistCtlUserRoleDao を戻します。
     */
    public CtlUserRoleDao getUserRegistCtlUserRoleDao() {
        return userRegistCtlUserRoleDao;
    }

    /**
     * ロール設定Daoの設定
     * @param bizadminMstUserCompanyDao bizadminMstUserCompanyDao を設定。
     */
    public void setUserRegistCtlUserRoleDao(CtlUserRoleDao ctlUserRoleDao) {
        this.userRegistCtlUserRoleDao = ctlUserRoleDao;
    }

    /**
     * ユーザ管理業態(MstUserGyotaiDao）の設定
     * @return bizadminMstUserGyotaiDao を戻します。
     */
    public MstUserGyotaiDao getBizadminMstUserGyotaiDao() {
        return bizadminMstUserGyotaiDao;
    }

    /**
     * ユーザ管理業態(MstUserGyotaiDao）の設定
     * @param bizadminMstUserGyotaiDao bizadminMstUserGyotaiDao を設定。
     */
    public void setBizadminMstUserGyotaiDao(MstUserGyotaiDao mstUserGyotaiDao) {
        this.bizadminMstUserGyotaiDao = mstUserGyotaiDao;
    }

    /**
     * ユーザ公開対象所属区分管理(MstUserShozokuDao）の設定
     * @return bizadminMstUserShozokuDao を戻します。
     */
    public MstUserShozokuDao getBizadminMstUserShozokuDao() {
        return bizadminMstUserShozokuDao;
    }

    /**
     * ユーザ公開対象所属区分管理(MstUserShozokuDao）の設定
     * @param bizadminMstUserShozokuDao bizadminMstUserShozokuDao を設定。
     */
    public void setBizadminMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
        this.bizadminMstUserShozokuDao = mstUserShozokuDao;
    }

    /**
     * ユーザ対応オーナー(MstUserOnerDao)の設定
     * @return bizadminMstUserOnerDao を戻します。
     */
    public MstUserOnerDao getBizadminMstUserOnerDao() {
        return bizadminMstUserOnerDao;
    }

    /**
     * ユーザ対応オーナー(MstUserOnerDao)の設定
     * @param bizadminMstUserOnerDao bizadminMstUserOnerDao を設定。
     */
    public void setBizadminMstUserOnerDao(MstUserOnerDao mstUserOnerDao) {
        this.bizadminMstUserOnerDao = mstUserOnerDao;
    }

    /**
     * ユーザ対応店舗(MstUserMiseDao）の設定
     * @return bizadminMstUserMiseDao を戻します。
     */
    public MstUserMiseDao getBizadminMstUserMiseDao() {
        return bizadminMstUserMiseDao;
    }

    /**
     * ユーザ対応店舗(MstUserMiseDao）の設定
     * @param bizadminMstUserMiseDao bizadminMstUserMiseDao を設定。
     */
    public void setBizadminMstUserMiseDao(MstUserMiseDao mstUserMiseDao) {
        this.bizadminMstUserMiseDao = mstUserMiseDao;
    }

    /**
     * 店マスタ(UIGroupTogoMiseDao)の取得
     * @return 店マスタ(UIGroupTogoMiseDao)
     */
    public UIGroupTogoMiseDao getBizadminUIGroupTogoMiseDao() {
        return bizadminUIGroupTogoMiseDao;
    }

    /**
     * 店マスタ(UIGroupTogoMiseDao)の設定
     * @param 店マスタ(UIGroupTogoMiseDao)
     */
    public void setBizadminMstGroupTogoMiseDao(UIGroupTogoMiseDao dao) {
        this.bizadminUIGroupTogoMiseDao = dao;
    }


    public UpdatePasswordLogic getUpdatePasswordLogic() {
        return updatePasswordLogic;
    }

    public void setUpdatePasswordLogic(UpdatePasswordLogic updatePasswordLogic) {
        this.updatePasswordLogic = updatePasswordLogic;
    }

    public GetUserShozokuKbnLogic getGetUserShozokuKbnLogic() {
        return getUserShozokuKbnLogic;
    }

    public void setGetUserShozokuKbnLogic(
            GetUserShozokuKbnLogic getUserShozokuKbnLogic) {
        this.getUserShozokuKbnLogic = getUserShozokuKbnLogic;
    }
}