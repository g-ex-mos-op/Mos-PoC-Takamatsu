/*
 * 作成日: 2006/03/14
 * 変更日：2006/9/13　miyagi
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.accountedit.dao.CtlMatrixRirekiDao;
import jp.co.isid.mos.bird.bizadmin.accountedit.dao.UIUserMatrixInfoDao;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.entity.CtlMatrixRireki;
import jp.co.isid.mos.bird.bizadmin.accountedit.entity.UIUserMatrixInfo;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.RenewEditAccountLogic;
import jp.co.isid.mos.bird.bizadmin.common.code.ZokuseiKbn;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserGyotaiDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserOnerDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIGroupTogoMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserGyotaiDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIGroupTogoMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserShozokuKbn;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstHanshaOnerDao;
import jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.framework.dao.CtlLoginFailKaisuDao;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 2006/05/15 ユーザータイプが'01'(本部)の場合、ユーザー対応オーナーをdelete、updateするのを修正(delete、insertする)
 * @author Noh
 * 
 * 2006/09/13 ユーザータイプが'01'(本部)の場合、利用者名のみ更新されない課題（課題番号：113）の対応により修正
 * 2006/09/19　もともとe-mosslesのIDが登録されていなかった店舗ユーザのIDを登録できるように修正　
 * 2008/11/19 ユーザー所属区分の設定をテーブルを使用し行うようにする（本部のみ）
 * 2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 * 更新日:2009/12/14 xkinu ロック解除機能追加
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 */
public class RenewEditAccountLogicImpl implements RenewEditAccountLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA001L05";
    /*DAO*/
    private UIUserOnerDao bizadminUIUserOnerDao;
    private UIUserCompanyDao bizadminUIUserCompanyDao;
    private UIUserGyotaiDao bizadminUIUserGyotaiDao;
    private UIUserMiseDao bizadminUIUserMiseDao;
    private UIUserDao bizadminUIUserDao;
    private UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao;
    
    /** DAO【ユーザ公開対象所属区分管理】*/
    private MstUserShozokuDao bizadminMstUserShozokuDao;
    private MstUserCompanyDao bizadminMstUserCompanyDao;
    private MstUserGyotaiDao bizadminMstUserGyotaiDao;
    private MstUserOnerDao bizadminMstUserOnerDao;
    private MstUserMiseDao bizadminMstUserMiseDao;
    private CtlMatrixRirekiDao accounteditCtlMatrixRirekiDao;
    private UIUserMatrixInfoDao accounteditUIUserMatrixInfoDao;
    private MstHanshaOnerDao mstHanshaOnerDao;

    
    /*LOGIC*/
    private UpdatePasswordLogic updatePasswordLogic;
	/** DAO【パスワード誤回数DAO】*/
	private CtlLoginFailKaisuDao ctlLoginFailKaisuDao;

    private static final String RC = "2";
    private static final String FC = "1";
    private static final String NOT_RENKEI = "STAFF";
    
    private static final String PGM = LOGIC_ID.substring(0, 6);
    /**
     * 処理実行
     */
    public void execute(AccounteditDto sessionDto ,GamenRoleDto gamenRoleDto ) {
		String regUserTypeCd = sessionDto.getUIUser().getUsertypeCd();
		//更新日
		Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        //登録情報が本部ユーザー情報の場合
        if (UserType.isHonbu(regUserTypeCd)) {
        	executeHonbu(sessionDto, gamenRoleDto, currentTimestamp);
        }
        //登録情報がオーナーユーザー情報の場合
        else if (UserType.isOner(regUserTypeCd)) {
        	executeOner(sessionDto, gamenRoleDto, currentTimestamp);
        }
        //登録情報が店舗ユーザー情報の場合
        else if (UserType.isTenpo(regUserTypeCd)) {
        	executeTenpo(sessionDto, gamenRoleDto, currentTimestamp);
        }

        //システム管理者のみの登録処理を行います。
        if(sessionDto.isSystemAdminUser()){
        	//ロック解除処理を実行します。
            doPwLockReset(sessionDto, gamenRoleDto, currentTimestamp);
        	//マトリクスリセット処理を実行します。
            doMatrixReset(sessionDto);
        }
        sessionDto.clear();
    }
    /**
     * 本部ユーザー情報用
     * 登録処理実行
     * 
     * ユーザー情報(BR01USER)
     * ユーザー管理業態(BM05USGT)
     * 
     * @param sessionDto
     * @param gamenRoleDto
     */
    private void executeHonbu(AccounteditDto sessionDto
    		,GamenRoleDto gamenRoleDto, Timestamp currentTimestamp)
    {
        //DAO【ユーザー情報】更新を実行します。
    	registBR01USER(gamenRoleDto, sessionDto.getUIUser());
        //システム管理者のみの登録処理を行います。
        if(sessionDto.isSystemAdminUser()) {
    		String regUserId = sessionDto.getUIUser().getUserId();

            //業態セット
    		registBM05USGT(sessionDto, gamenRoleDto, currentTimestamp);
            
            //ユーザー対応オーナーの既存データを一度削除します。
    		bizadminMstUserOnerDao.deleteByUserId(regUserId);
            //オーナーセット
        	String onerCd = sessionDto.getUserOner().getOnerCd();
            if(CommonUtil.isNull(onerCd) == false){
	            UIUserOner onerinsert = sessionDto.getUserOner();
	            onerinsert.setUserId(sessionDto.getUIUser().getUserId());
	            onerinsert.setFirstUser(gamenRoleDto.getUserId());
	            onerinsert.setFirstPgm(gamenRoleDto.getGamenId());
	            onerinsert.setFirstTmsp(currentTimestamp);
	            onerinsert.setLastUser(gamenRoleDto.getUserId());
	            onerinsert.setLastPgm(gamenRoleDto.getGamenId());
	            onerinsert.setLastTmsp(currentTimestamp);
                //新規登録を行います。
                getBizadminMstUserOnerDao().insert(onerinsert);
            }
        }
    }
    /**
     * オーナーユーザー情報用
     * 登録処理実行
     * 
     * ユーザー情報(BR01USER)
     * ユーザ所属会社(BM03USCP)
     * ユーザ公開対象所属区分(BM13SHKM)
     * ユーザ管理業態(BM05USGT)
     * ユーザ対応オーナー(BM06UONR)
     * 
     * @param sessionDto
     * @param gamenRoleDto
     */
    private void executeOner(AccounteditDto sessionDto
    		,GamenRoleDto gamenRoleDto, Timestamp currentTimestamp)
    {
		String regUserId = sessionDto.getUIUser().getUserId();
        // 日付フォーマット(YYYYMMDD型)
        formatNoSlash(sessionDto);
        //DAO【ユーザー情報】更新を実行します。
        registBR01USER(gamenRoleDto, sessionDto.getUIUser());
        //システム管理者のみの登録処理を行います。
        if(sessionDto.isSystemAdminUser()) {
            /* ユーザ所属会社(BM03USCP)登録 */
        	_onerRegistBM03USCP(sessionDto, gamenRoleDto, currentTimestamp);
            //ユーザ公開対象所属区分登録処理
            registBM13SHKM(_onerGetListUserShozokuKbn(sessionDto)
            		, regUserId, gamenRoleDto.getUserId(), currentTimestamp);
            /* 業態変更 */
        	registBM05USGT(sessionDto, gamenRoleDto, currentTimestamp);
            //ユーザー対応オーナーの既存データを一度削除します。
        	bizadminMstUserOnerDao.deleteByUserId(regUserId);
        	for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
    			UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
    			if(eOner.isChecked()) {
    	            //ユーザー対応オーナー情報を登録します。
    				eOner.setUserId(sessionDto.getUIUser().getUserId());
    				eOner.setFirstUser(gamenRoleDto.getUserId());
    				eOner.setFirstPgm(gamenRoleDto.getGamenId());
    				eOner.setFirstTmsp(currentTimestamp);
    	            eOner.setLastUser(gamenRoleDto.getUserId());
    	            eOner.setLastPgm(gamenRoleDto.getGamenId());               
    	            eOner.setLastTmsp(currentTimestamp);               
    	            getBizadminMstUserOnerDao().insert(eOner);
    			}
            }
        }
    	//パスワード変更がされた場合は、共通パスワード登録ロジックを実行
        if(sessionDto.getUserPassWordImple() != null){
            getUpdatePasswordLogic().execute(regUserId
            		, sessionDto.getUserPassWordImple()
            		, UpdatePasswordLogic.HENKO_KBN_UPDATE
            		, gamenRoleDto.getUserId(), gamenRoleDto.getGamenId());
        }
    }
    /**
     * 店舗ユーザー情報用
     * 登録処理実行
     * 
     * ユーザー情報(BR01USER)
     * ユーザ所属会社(BM03USCP)
     * ユーザ公開対象所属区分(BM13SHKM)
     * ユーザ管理業態(BM05USGT)
     * ユーザ対応店舗(BM07UTEN)
     * 
     * @param sessionDto
     * @param gamenRoleDto
     */
    private void executeTenpo(AccounteditDto sessionDto
    		,GamenRoleDto gamenRoleDto, Timestamp currentTimestamp)
    {
		String regUserId = sessionDto.getUIUser().getUserId();
        
        // 日付フォーマット(YYYYMMDD型)
        formatNoSlash(sessionDto);
        //DTO【アカウント情報編集情報保持】.[更新対象ユーザー情報]を変数に設定します。
        UIUser uIUser = sessionDto.getUIUser();

        UIGroupTogoMise eMstGTMise = null;
        String miseKbn = "";
        //直営店舗ユーザの場合にe-mosslesへ連携しない
        List list = getBizadminUIGroupTogoMiseDao().selectMise(
        		sessionDto.getUserMise().getMiseCd(), sessionDto.getUserMise().getCompanyCd());
        eMstGTMise = (UIGroupTogoMise)list.get(0);
        miseKbn = CommonUtil.isNull(eMstGTMise.getMiseKbn())?null:eMstGTMise.getMiseKbn().substring(1,2);
        //ＲＣの場合
        if(RC.equals(miseKbn)){
            uIUser.setFirstUser(NOT_RENKEI);
        //ＦＣの場合
        }else if(FC.equals(miseKbn)){
             uIUser.setFirstUser(gamenRoleDto.getUserId());
        }
        //DAO【ユーザー情報】更新を実行します。
        registBR01USER(gamenRoleDto, uIUser);
        
        //システム管理者のみの登録処理を行います。
        if(sessionDto.isSystemAdminUser()) {
            //ユーザ公開対象所属区分登録処理
        	registBM13SHKM(_tenpoGetListUserShozokuKbn(sessionDto)
            		, regUserId, gamenRoleDto.getUserId(), currentTimestamp);
            // ユーザ所属会社(BM03USCP)更新
        	_tenpoRegistBM03USCP(sessionDto, gamenRoleDto, currentTimestamp, miseKbn);

            //登録用List[[ユーザ管理業態]]を作成します。
            List gyoutaiList = new ArrayList(0);
            UIUserGyotai uiUserGyotai = new UIUserGyotai();
        	// 選択した店舗の業態を登録用List[[ユーザ管理業態]]へ設定します。
            uiUserGyotai.setGyotaiKbn(eMstGTMise.getGyotaiKbn());
            gyoutaiList.add(uiUserGyotai);
            //DTO【自画面Session】へ登録用List[[ユーザ管理業態]]を設定します。
            sessionDto.setUserGyotaiList(gyoutaiList);
            //ユーザ管理業態登録処理を行います。
            registBM05USGT(sessionDto, gamenRoleDto, currentTimestamp);
            
            /* ユーザ対応店舗の変更情報 */
            sessionDto.getUserMise().setLastPgm(gamenRoleDto.getGamenId());
            sessionDto.getUserMise().setLastUser(gamenRoleDto.getUserId());
            bizadminMstUserMiseDao.update(sessionDto.getUserMise());
        }
        //オーナー、店舗ユーザーでパスワード変更がされた場合は、共通パスワード登録ロジックを実行
        if(sessionDto.getUserPassWordImple() != null){
            getUpdatePasswordLogic().execute(regUserId
            		, sessionDto.getUserPassWordImple()
            		, UpdatePasswordLogic.HENKO_KBN_UPDATE
            		, gamenRoleDto.getUserId(), gamenRoleDto.getGamenId());
        }
    }
    /**
     * ユーザー情報登録処理
     * 
     * @param gamenRoleDto
     * @param userBr01
     */
    private void registBR01USER(GamenRoleDto gamenRoleDto, UIUser userBr01) {
        /* ユーザ情報の変更情報 */
    	userBr01.setLastPgm(gamenRoleDto.getGamenId());
    	userBr01.setLastUser(gamenRoleDto.getUserId());
        //DAO【ユーザー情報】更新を実行します。
        bizadminUIUserDao.updateUser(userBr01);    	
    }
    /**
     * ユーザ公開対象所属区分(BM13SHKM)登録処理
     * 
     * @param listShozokuKbn
     * @param userId 更新対象ユーザーID
     * @param regUserId 登録ユーザーID(ログインユーザーID)
     */
    private void registBM13SHKM(List listShozokuKbn, String userId, String regUserId, Timestamp currentTimestamp) {
    	//１．DAO【ユーザ公開対象所属区分管理】削除を実行します。
        bizadminMstUserShozokuDao.deleteByUserId(userId);
        //２．編集後の所属区分を全て新規登録します。
        if (listShozokuKbn != null) {
            for (Iterator ite = listShozokuKbn.iterator(); ite.hasNext();) {
                /*所属区分Entity*/
                MstUserShozoku eShozoku = new MstUserShozoku();
                
                eShozoku.setShozokuKbn(ite.next().toString());
                eShozoku.setUserId(userId);
                eShozoku.setFirstUser(regUserId);
                eShozoku.setFirstPgm(PGM);
                eShozoku.setFirstTmsp(currentTimestamp);
                eShozoku.setLastUser(regUserId);
                eShozoku.setLastPgm(PGM);
                eShozoku.setLastTmsp(currentTimestamp);
                //DAO【ユーザ公開対象所属区分管理】新規登録を実行します。
                bizadminMstUserShozokuDao.insert(eShozoku);
            }
        }
    }

    /**
     * ユーザ管理業態新規登録
     * @param sessionDto
     */
    private void registBM05USGT(
    		AccounteditDto sessionDto, GamenRoleDto gamenRoleDto
    		, Timestamp currentTimestamp)
    {
    	//既存のデータを削除します。
        bizadminMstUserGyotaiDao.deleteByUserId(sessionDto.getUIUser().getUserId());
        if(sessionDto.getUserGyotaiList() != null){
	        /* 新しいデータを入力 */
	        UIUserGyotai gyotai = new UIUserGyotai();
	
	        gyotai.setFirstPgm(gamenRoleDto.getGamenId());
	        gyotai.setFirstUser(gamenRoleDto.getUserId());
	        gyotai.setFirstTmsp(currentTimestamp);
	        gyotai.setLastPgm(gamenRoleDto.getGamenId());
	        gyotai.setLastUser(gamenRoleDto.getUserId());
	        gyotai.setLastTmsp(currentTimestamp);
            for (int j = 0; j < sessionDto.getUserGyotaiList().size(); j++) {
                if (sessionDto.getUserGyotaiList().get(j) instanceof UIGyotai) {
                    UIGyotai unit = (UIGyotai) sessionDto.getUserGyotaiList().get(j);
                    gyotai.setGyotaiKbn(unit.getGyotaiKbn());
                }
                if (sessionDto.getUserGyotaiList().get(j) instanceof UIUserGyotai) {
                    UIUserGyotai unit = (UIUserGyotai) sessionDto.getUserGyotaiList().get(j);
                    gyotai.setGyotaiKbn(unit.getGyotaiKbn());
                }
                gyotai.setUserId(sessionDto.getUIUser().getUserId());
                bizadminMstUserGyotaiDao.insert(gyotai);
            }
        }
    }
        

    /**
     * 日付フォーマット (YYYYMMDD型)
     * @param userRegistDto
     */
    private void formatNoSlash(AccounteditDto sessionDto) {

        DateFormatter dateFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        sessionDto.getUIUser().setSeikyuDt(dateFormatter.format(sessionDto
                .getUIUser().getSeikyuDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
        sessionDto.getUIUser().setSeikyuUpdtDt(dateFormatter.format(sessionDto
                .getUIUser().getSeikyuUpdtDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
        sessionDto.getUIUser().setAppliedDt(dateFormatter.format(sessionDto
                .getUIUser().getAppliedDt(), DateFormatter.PATTERN_NORMAL,
                DateFormatter.DATE_TYPE_YMD));
    }

    /**
     * オーナーユーザー用
     * ユーザー所属会社登録処理
     * 
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
     * @param sessionDto
     * @param gamenRoleDto
     * @param currentTimestamp
     */
    private void _onerRegistBM03USCP(AccounteditDto sessionDto, GamenRoleDto gamenRoleDto
    		, Timestamp currentTimestamp) {
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
    	List companyList = getBizadminUIUserCompanyDao().selectAllCompany(sessionDto.getUIUser().getUserId(), true, isForeignIn);
    	Map mapCompany = new HashMap();
    	for(int c=0; c<companyList.size(); c++) {
    		UIUserCompany eCompany = (UIUserCompany)companyList.get(c);
    		mapCompany.put(eCompany.getRCompanyCd(), eCompany);
    	}
        List onerList = sessionDto.getUserOnerList();
    	for(int i=0; i<onerList.size(); i++) {
			UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
			UIUserCompany userComp = (UIUserCompany)mapCompany.get(eOner.getCompanyCd());
			if(eOner.isChecked()) {
				UIUserCompany eCompany = (UIUserCompany)mapCompany.get(eOner.getCompanyCd());
				eCompany.setZokuseiKbn(ZokuseiKbn.KEIYAKU);
				eCompany.setLastUser(gamenRoleDto.getUserId());
				eCompany.setLastPgm(PGM);
				if(CommonUtil.isNull(eOner.getFirstUser()) ) {
					//新規登録
					eCompany.setUserId(sessionDto.getUIUser().getUserId());
                    eCompany.setZokuseiKbn(ZokuseiKbn.KEIYAKU);
                    eCompany.setFirstUser(gamenRoleDto.getUserId());
                    eCompany.setFirstPgm(PGM);
                    eCompany.setFirstTmsp(currentTimestamp);
                    eCompany.setLastTmsp(currentTimestamp);
	                bizadminMstUserCompanyDao.insert(eCompany);
				}
				else {
					//更新登録
					bizadminMstUserCompanyDao.update(eCompany);
				}
			}
			else {
				if(CommonUtil.isNull(eOner.getFirstUser())==false) {
					userComp.setUserId(sessionDto.getUIUser().getUserId());
					//削除登録
					bizadminMstUserCompanyDao.delete((UIUserCompany)mapCompany.get(eOner.getCompanyCd()));
				}
			}
    	}

    }
    /**
     * 店舗ユーザー用
     * ユーザー所属会社登録処理
     * @param sessionDto
     * @param gamenRoleDto
     * @param currentTimestamp
     */
    private void _tenpoRegistBM03USCP(AccounteditDto sessionDto, GamenRoleDto gamenRoleDto
    		, Timestamp currentTimestamp, String miseKbn) {
        // 既存データの削除
    	bizadminMstUserCompanyDao.deleteByUserId(sessionDto.getUIUser().getUserId());
        // 属性区分決定
        // 選択された店舗の店区分から
        // ユーザ所属会社の属性区分の値を決定します。
        String zokuseiKbn = "";
        if (RC.equals(miseKbn)) {
            // RC→所属
            zokuseiKbn = ZokuseiKbn.SHOZOKU;
        } else if(FC.equals(miseKbn)) {
            // FC→契約
            zokuseiKbn = ZokuseiKbn.KEIYAKU;
        }
        sessionDto.getUserCompany().setZokuseiKbn(zokuseiKbn);
        sessionDto.getUserCompany().setFirstTmsp(currentTimestamp);
        sessionDto.getUserCompany().setFirstPgm(gamenRoleDto.getGamenId());
        sessionDto.getUserCompany().setFirstUser(gamenRoleDto.getUserId());
        sessionDto.getUserCompany().setLastPgm(gamenRoleDto.getGamenId());
        sessionDto.getUserCompany().setLastUser(gamenRoleDto.getUserId());
        sessionDto.getUserCompany().setLastTmsp(currentTimestamp);
        //DAO【ユーザー所属会社】新規登録を実行します。
        bizadminMstUserCompanyDao.insert(sessionDto.getUserCompany());
    }
    /**
     * オーナーユーザー用
     * 登録ユーザー所属区分保持リスト取得処理
     * 
     * @param sessionDto
     * @return
     */
    private List _onerGetListUserShozokuKbn(AccounteditDto sessionDto) {
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
    private List _tenpoGetListUserShozokuKbn(AccounteditDto sessionDto) {
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
     * 本部ユーザー用
     * 登録ユーザー所属区分保持リスト取得処理
     * 
     * 
     * @param userTypeCd
     * @param rcompanyCd
     * @param miseCd
     * @return
     */
//    private List _honbuGetListUserShozokuKbn(AccounteditDto sessionDto) {
//		List listShozokuKbn = new ArrayList();
//		List listHansha = null;
//		UIUserOner userOner = sessionDto.getUserOner();
//        if (CommonUtil.isNull(userOner.getOnerCd())) {
//        	//販社オーナー情報テーブル検索
//            listHansha = getMstHanshaOnerDao().getHanshaOner(
//            		userOner.getCompanyCd(), userOner.getOnerCd());
//        }
//        //所属区分を決定します。
//        if (listHansha == null || listHansha.isEmpty()) {
//        	//オーナーコードが空(null or ブランク)の場合、
//        	//又は販社オーナー情報(BM59HONR)に存在しないコードの場合は
//        	//属性区分を本部に設定します。
//            listShozokuKbn.add(UserShozokuKbn.HONBU);
//        }
//        else {
//        	//販社オーナー情報(BM59HONR)に存在する場合は
//        	//属性区分を販社に設定します。
//            listShozokuKbn.add(UserShozokuKbn.HANSHA);
//        }
//        return listShozokuKbn;
//    }

    /**
     * マトリクスリセット
     * @param sessionDto
     */
    private void doMatrixReset(AccounteditDto sessionDto) {
		String regUserId = sessionDto.getUIUser().getUserId();
        //マトリクスリセットがチェックオフの場合は、処理終了
        if (!sessionDto.isMatrixResetFlg()) {
            return;
        }
        //BR16MTRX取得
        List listUserMatrix = getAccounteditUIUserMatrixInfoDao().getUserMatrixInfo(regUserId);
        //BR16MTRXが取得できたら削除
        if (listUserMatrix != null && !listUserMatrix.isEmpty()) {
            UIUserMatrixInfo entityUserMatrix = (UIUserMatrixInfo) listUserMatrix.get(0);
            getAccounteditUIUserMatrixInfoDao().deleteUserMatrixInfo(entityUserMatrix);
            entityUserMatrix = null;
        }
        //BR78MTXRからユーザーのデータを全件取得
        List listCtlMatrixRireki = accounteditCtlMatrixRirekiDao.getUserMatrixInfo(regUserId);
        //BR78MTXRに当日のレコードが存在する場合、そのレコードを削除
        if (listCtlMatrixRireki != null && !listCtlMatrixRireki.isEmpty()) {
            for (Iterator ite = listCtlMatrixRireki.iterator(); ite.hasNext();) {
                CtlMatrixRireki entityCtlMatrixRireki = (CtlMatrixRireki) ite.next();
                accounteditCtlMatrixRirekiDao.deleteUserMatrixInfo(entityCtlMatrixRireki);
            }
        }
        //後処理
        listUserMatrix = null;
        listCtlMatrixRireki = null;
    }
    /**
     * ロック解除処理
     * 
     * @param sessionDto
     * @param gamenRoleDto
     * @param currentTimestamp
     */
    private void doPwLockReset(AccounteditDto sessionDto
    		, GamenRoleDto gamenRoleDto, Timestamp currentTimestamp)
    {
        //０．ロック解除がチェックオフの場合は、処理終了
        if (!sessionDto.isPwLockResetFlg()) {
            return;
        }
        //１．DTOから[パスワード誤回数]を取得します。
        CtlLoginFailKaisu ctlLoginFailKaisu = sessionDto.getCtlLoginFailKaisu();
        //２．変数.既存登録判断値を作成し、登録処理判断値として、
        //    処理１の[パスワード誤回数].更新ユーザーIDがNullの場合はtrueをそれ以外はfalseを設定します。
        if(CommonUtil.isNull(ctlLoginFailKaisu.getLastUser())==false) {
	    	//３．[パスワード誤回数].更新タイムスタンプへ現在の日付を設定します。
	    	ctlLoginFailKaisu.setLastTmsp(currentTimestamp);
	    	//４．[パスワード誤回数].更新プログラムへ現在のロジックID先頭6バイトを設定します。
	    	ctlLoginFailKaisu.setLastPgm(PGM);
	    	//５．[パスワード誤回数].更新ユーザーIDへログインユーザーIDを設定します。
	    	ctlLoginFailKaisu.setLastUser(gamenRoleDto.getUserId());
	        //６．[パスワード誤回数]失敗回数へ0(ゼロ)を設定します。
        	ctlLoginFailKaisu.setLoginFail(new BigDecimal("0"));
	        //７．DAO【パスワード誤回数DAO】更新を実行します。
	        getCtlLoginFailKaisuDao().updateCtlLoginFailKaisu(ctlLoginFailKaisu);
        }

    }
    /**
     * @return bizadminUIGroupTogoMiseDao を戻します。
     */
    public UIGroupTogoMiseDao getBizadminUIGroupTogoMiseDao() {
        return bizadminUIGroupTogoMiseDao;
    }
    
    /**
     * @param bizadminUIGroupTogoMiseDao bizadminUIGroupTogoMiseDao を設定。
     */
    public void setBizadminUIGroupTogoMiseDao(UIGroupTogoMiseDao mstGroupTogoMiseDao) {
        this.bizadminUIGroupTogoMiseDao = mstGroupTogoMiseDao;
    }

    public UIUserOnerDao getBizadminUIUserOnerDao() {
        return bizadminUIUserOnerDao;
    }

    public void setBizadminUIUserOnerDao(UIUserOnerDao userOnerDao) {
        bizadminUIUserOnerDao = userOnerDao;
    }

    public UIUserCompanyDao getBizadminUIUserCompanyDao() {
        return bizadminUIUserCompanyDao;
    }

    public void setBizadminUIUserCompanyDao(UIUserCompanyDao userCompanyDao) {
        bizadminUIUserCompanyDao = userCompanyDao;
    }

    public UIUserGyotaiDao getBizadminUIUserGyotaiDao() {
        return bizadminUIUserGyotaiDao;
    }

    public void setBizadminUIUserGyotaiDao(UIUserGyotaiDao userGyotaiDao) {
        bizadminUIUserGyotaiDao = userGyotaiDao;
    }

    public UIUserMiseDao getBizadminUIUserMiseDao() {
        return bizadminUIUserMiseDao;
    }

    public void setBizadminUIUserMiseDao(UIUserMiseDao userMiseDao) {
        bizadminUIUserMiseDao = userMiseDao;
    }

    public UIUserDao getBizadminUIUserDao() {
        return bizadminUIUserDao;
    }

    public void setBizadminUIUserDao(UIUserDao userDao) {
        bizadminUIUserDao = userDao;
    }

    public MstUserShozokuDao getBizadminMstUserShozokuDao() {
        return bizadminMstUserShozokuDao;
    }

    public void setBizadminMstUserShozokuDao(MstUserShozokuDao iuserShozokuDao) {
        bizadminMstUserShozokuDao = iuserShozokuDao;
    }
    
    public UpdatePasswordLogic getUpdatePasswordLogic() {
        return updatePasswordLogic;
    }

    public void setUpdatePasswordLogic(UpdatePasswordLogic updatePasswordLogic) {
        this.updatePasswordLogic = updatePasswordLogic;
    }

    public MstHanshaOnerDao getMstHanshaOnerDao() {
        return mstHanshaOnerDao;
    }

    public void setMstHanshaOnerDao(MstHanshaOnerDao mstHanshaOnerDao) {
        this.mstHanshaOnerDao = mstHanshaOnerDao;
    }

    public CtlMatrixRirekiDao getAccounteditCtlMatrixRirekiDao() {
        return accounteditCtlMatrixRirekiDao;
    }

    public void setAccounteditCtlMatrixRirekiDao(
            CtlMatrixRirekiDao accounteditCtlMatrixRirekiDao) {
        this.accounteditCtlMatrixRirekiDao = accounteditCtlMatrixRirekiDao;
    }

    public UIUserMatrixInfoDao getAccounteditUIUserMatrixInfoDao() {
        return accounteditUIUserMatrixInfoDao;
    }

    public void setAccounteditUIUserMatrixInfoDao(
            UIUserMatrixInfoDao accounteditUIUserMatrixInfoDao) {
        this.accounteditUIUserMatrixInfoDao = accounteditUIUserMatrixInfoDao;
    }

	/**
	 * @return ctlLoginFailKaisuDao を戻します。
	 */
	public CtlLoginFailKaisuDao getCtlLoginFailKaisuDao() {
		return ctlLoginFailKaisuDao;
	}

	/**
	 * @param ctlLoginFailKaisuDao を クラス変数ctlLoginFailKaisuDaoへ設定します。
	 */
	public void setCtlLoginFailKaisuDao(CtlLoginFailKaisuDao ctlLoginFailKaisuDao) {
		this.ctlLoginFailKaisuDao = ctlLoginFailKaisuDao;
	}
	/**
	 * @return クラス変数mstUserOnerDao を戻します。
	 */
	public MstUserOnerDao getBizadminMstUserOnerDao() {
		return bizadminMstUserOnerDao;
	}
	/**
	 * @param bizadminMstUserOnerDao を クラス変数mstUserOnerDaoへ設定します。
	 */
	public void setBizadminMstUserOnerDao(MstUserOnerDao mstUserOnerDao) {
		this.bizadminMstUserOnerDao = mstUserOnerDao;
	}
	/**
	 * @return クラス変数bizadminMstUserGyotaiDao を戻します。
	 */
	public MstUserGyotaiDao getBizadminMstUserGyotaiDao() {
		return bizadminMstUserGyotaiDao;
	}
	/**
	 * @param bizadminMstUserGyotaiDao を クラス変数bizadminMstUserGyotaiDaoへ設定します。
	 */
	public void setBizadminMstUserGyotaiDao(
			MstUserGyotaiDao bizadminMstUserGyotaiDao) {
		this.bizadminMstUserGyotaiDao = bizadminMstUserGyotaiDao;
	}
	/**
	 * @return クラス変数bizadminMstUserCompanyDao を戻します。
	 */
	public MstUserCompanyDao getBizadminMstUserCompanyDao() {
		return bizadminMstUserCompanyDao;
	}
	/**
	 * @param bizadminMstUserCompanyDao を クラス変数bizadminMstUserCompanyDaoへ設定します。
	 */
	public void setBizadminMstUserCompanyDao(
			MstUserCompanyDao bizadminMstUserCompanyDao) {
		this.bizadminMstUserCompanyDao = bizadminMstUserCompanyDao;
	}
	/**
	 * @return クラス変数bizadminMstUserMiseDao を戻します。
	 */
	public MstUserMiseDao getBizadminMstUserMiseDao() {
		return bizadminMstUserMiseDao;
	}
	/**
	 * @param bizadminMstUserMiseDao を クラス変数bizadminMstUserMiseDaoへ設定します。
	 */
	public void setBizadminMstUserMiseDao(MstUserMiseDao bizadminMstUserMiseDao) {
		this.bizadminMstUserMiseDao = bizadminMstUserMiseDao;
	}
}