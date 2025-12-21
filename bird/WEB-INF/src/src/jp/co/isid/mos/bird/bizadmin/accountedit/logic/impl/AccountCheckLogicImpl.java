/*
 * 作成日: 2006/02/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.AccountCheckLogic;
import jp.co.isid.mos.bird.bizadmin.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIGroupTogoMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIGyotaiCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UITogoOwnerDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIGroupTogoMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIGyotaiCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UITogoOwner;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserShozokuKbn;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstHanshaOnerDao;
import jp.co.isid.mos.bird.common.logic.ValidatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuKatakanaVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.JinmeiVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;

/**
 * アカウント情報の入力チェック
 *　2006/04/20　ユーザー名（カナ）の必須チェックを削除
 *  2006/05/15  ユーザータイプコード＝'02'（オーナー）の場合、ユーザー対応オーナーが取得できるかチェックをはずす。(データがない場合はinsertするため。)
 *  2006/07/04  パスワード変更処理
 *  2006/09/25　e-mosID－必須チェック（店舗ユーザむけ）　MIYAGI
 *  2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 *  * @author 慮
 */
public class AccountCheckLogicImpl implements AccountCheckLogic {

    /* ロジックＩＤ*/	
    public static final String LOGIC_ID = "BBA001L02";
    
	private UITogoOwnerDao bizadminUITogoOwnerDao;
	private UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao;
    private UIUserDao  bizadminUIUserDao;
	private UIGyotaiCompanyDao bizadminUIGyotaiCompanyDao;
    private MstUserShozokuDao bizadminMstUserShozokuDao;
    private MstHanshaOnerDao mstHanshaOnerDao;
    private ValidatePasswordLogic validatePasswordLogic;
    /**
     * 事前条件処理
     * @param userId
     */
    private void _validate(AccounteditDto dto)
    {
        if (dto == null) {
            throw new MissingDataException("アカウント変更情報");
        }
        if (CommonUtil.isNull(dto.getUIUser().getUserId())) {
            throw new MissingDataException("ユーザーID");
        }
    }
    /**
     * 実行処理
     */
	public List execute(AccounteditDto dto) {
		String regUserTypeCd = dto.getUIUser().getUsertypeCd();
		if(UserType.isHonbu(regUserTypeCd)) {
			//本部情報
			_executeHonbuUser(dto);
		}
		if(UserType.isOner(regUserTypeCd)) {
			//オーナー情報
			_executeOnerUser(dto);
		}
		if(UserType.isTenpo(regUserTypeCd)) {
			//店舗情報
			_executeTenpoUser(dto);
		}
        return null;
	}
    /**
     * オーナーユーザー情報用
     * 事前条件処理
     * 
     * @param sessionDto
     */
    private void _validateHonbuUser(AccounteditDto sessionDto) {
    	_validate(sessionDto);
        if (sessionDto.getUIUser() == null) {
            throw new MissingDataException("ユーザー情報");
        }
        if (sessionDto.getUserCompany() == null) {
            throw new MissingDataException("ユーザー所属会社情報");
        }
        if (sessionDto.getUserOner() == null) {
            throw new MissingDataException("ユーザー対応オーナー情報");
        }
    }
    /**
     * 本部ユーザー用
     * 実行処理
     */
	private void _executeHonbuUser(AccounteditDto sessionDto){
		
		//０．事前条件処理を行います。
		_validateHonbuUser(sessionDto);

		//１．システム管理者の場合下記のチェックを行います。
		if(sessionDto.isSystemAdminUser()){
			//入力値整合性チェック
			//業態の必須チェック
			if(sessionDto.getUserGyotaiList() == null || sessionDto.getUserGyotaiList().size() == 0){
				throw new NotNullException("業態","gyoutaiButton",null);
			}
			/*必須項目チェック*/
            if(CommonUtil.isNull(sessionDto.getUIUser().getLimitKbn())){  
                throw new NotNullException("リモート制限","limitKbn",null);
            }
	        // 選択会社コードリスト取得
	        List selectedComapnyList = new ArrayList(0);
	        for(int i=0; i<sessionDto.getUserCompanyList().size(); i++) {
	        	UIUserCompany entity = (UIUserCompany)sessionDto.getUserCompanyList().get(i);
        		selectedComapnyList.add(entity.getCompanyCd());
	        }
            // 業態整合性チェック
            _checkGyotai(sessionDto, selectedComapnyList);
            //入力値整合性チェック
            String onerCd = sessionDto.getUserOner().getOnerCd();
            if(!CommonUtil.isNull(onerCd)) {
	    		//オーナーコード文字整合性チェック
	    		_checkHankakuNumCode(onerCd, "オーナーコード","onerCd", 0);
	            // 文字列長チェック
	    		_checkByteLength(onerCd, 5, "オーナーコード", "onerCd", 0);
	    		Map mapUserBC05Comp = new HashMap();
	    		for(int c=0; c<sessionDto.getUserCompanyList().size(); c++) {
	    			UIUserCompany comp = (UIUserCompany)(sessionDto.getUserCompanyList().get(c));
	    			mapUserBC05Comp.put(comp.getCompanyCd(), "");
	    		}
            	if(!mapUserBC05Comp.containsKey(sessionDto.getUserOner().getCompanyCd()) ){
            		throw new GenericCommentException("指定された所属会社とオーナーが不整合","onerCd", 0);
            	}
            /*
    		・パラメータ.オーナーコードリストが存在する場合、オーナーコードのチェックを行う。
    		4-2．パラメータ．オーナーコードリストが①で取得した[[グループ統合オーナーマスタ]]．オーナーコードに存在するかチェックする。
    				　　         1．存在しない場合、【E0103】（“指定された業態が”）を発生させる。
    				　　         2．件数が０件の場合
    				　　         ・【E0103】（“オーナーコード”）を発生させる。							
             */
        		List onerCdList = bizadminUITogoOwnerDao.select(
        				onerCd, sessionDto.getUserOner().getCompanyCd());
        		if(onerCdList == null || onerCdList.size() <=0) {
       				throw new NotExistException("オーナーコード","onerCd",null);
                }
        		else {
    	            UITogoOwner mstTogoOwner = (UITogoOwner)onerCdList.get(0);
    	            sessionDto.getUserOner().setOnerCd(mstTogoOwner.getOnerCd());
    	            sessionDto.getUserOner().setOnerNameKj(mstTogoOwner.getOnerNameKj());
        		}
        	}
        }
		//メールアドレス整合性チェック
		_checkMailAddress(sessionDto);
 	}
    /**
     * オーナーユーザー情報用
     * 事前条件処理
     * 
     * @param sessionDto
     */
    private void _validateOnerUser(AccounteditDto sessionDto) {
    	_validate(sessionDto);
        if (sessionDto.getUIUser() == null) {
            throw new MissingDataException("ユーザー情報");
        }
        if (sessionDto.getUserOnerList() == null) {
            throw new MissingDataException("ユーザー対応オーナー情報");
        }
    }
	/**
     * オーナーユーザー情報用
     * 実行処理
     */
	private void _executeOnerUser(AccounteditDto sessionDto){
		//０．事前条件処理を行います。
		_validateOnerUser(sessionDto);

		String userNameLabelName = "ご利用者名";
		String userNameKj = sessionDto.getUIUser().getUserNameKj();
		//１．必須のパラメータが満たされていること。
        if(CommonUtil.isNull(userNameKj)){
            throw new NotNullException("ご利用者名","usernameKj",null);
        }
        
		if(sessionDto.isSystemAdminUser()){
			boolean noInputOner = true;
			for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
				if(eOner.isChecked()) {
					noInputOner = false;
					break;
				}
			}
			if(noInputOner){
				throw new NotNullException("会社選択","kaisyaCheck", null);
			}
			for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
				if(eOner.isChecked() && CommonUtil.isNull(eOner.getOnerCd())){
					throw new NotNullException(
							eOner.getCompanyName()+" オーナーコード","onerCd", i);
				}
			}
			if(sessionDto.getUserGyotaiList()==null || (sessionDto.getUserGyotaiList().size() == 0)){
				throw new NotNullException("業態","gyoutaiButton",null);
			}
	        // 選択会社コードリスト取得
	        List selectedComapnyList = new ArrayList(0);
	        for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
	        	UIUserOner entity = (UIUserOner)sessionDto.getUserOnerList().get(i);
	        	if( entity.isChecked() ) {
	        		selectedComapnyList.add(entity.getCompanyCd());
	        	}
	        }
            // 業態整合性チェック
            _checkGyotai(sessionDto, selectedComapnyList);
            
			for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
				if(eOner.isChecked()){
					_checkHankakuNumCode(
							eOner.getOnerCd()
							, eOner.getCompanyName()+" オーナーコード"
							,"onerCd", i);
				}
			}
            //2006/07/04 ADD START パスワード追加対応 INAZAWA
            if(!CommonUtil.isNull(sessionDto.getUserPassWordImple())){
                if(CommonUtil.isNull(sessionDto.getUserPassWordKakuninImple())){
                    throw new NotNullException("確認用パスワード","userPassWordKakuninImple",null);
                }
                if(!sessionDto.getUserPassWordKakuninImple().equals(sessionDto.getUserPassWordImple())){
                    throw new ConstraintsViolationException("パスワードリセットと確認用パスワードは、一致し", "");
                }
                sessionDto.setUserPassWord(sessionDto.getUserPassWordImple());
                sessionDto.setUserPassWordKakunin(sessionDto.getUserPassWordKakuninImple());
            }else{
                sessionDto.setUserPassWord(sessionDto.getUserPassWord());
                sessionDto.setUserPassWordKakunin(sessionDto.getUserPassWordKakunin());
            }
            if(!CommonUtil.isNull(sessionDto.getUserPassWordKakuninImple())){
                if(CommonUtil.isNull(sessionDto.getUserPassWordImple())){
                    throw new NotNullException("確認用パスワードが入力されている場合、パスワードリセット","userPassWordImple",null);
                }
            }
            //パスワードポリシーチェック
            if(!CommonUtil.isNull(sessionDto.getUserPassWordKakuninImple())){
            	String shozokuKbn = "";
                List listHansha = null;
                List listOner = sessionDto.getUserOnerList();
                for (Iterator ite = listOner.iterator(); ite.hasNext();) {
                    UIUserOner eOner = (UIUserOner) ite.next();
                    if(eOner.isChecked()) {
    	                listHansha = getMstHanshaOnerDao().getHanshaOner(eOner.getCompanyCd(), eOner.getOnerCd());
    	                if (listHansha != null && !listHansha.isEmpty()) {
    	                    break;
    	                }
                	}
                }
                if (listHansha == null || listHansha.isEmpty()) {
                    shozokuKbn = UserShozokuKbn.ONER;
                }
                else {
                    shozokuKbn = UserShozokuKbn.HANSHA;
                }
                getValidatePasswordLogic().execute(sessionDto.getUIUser().getUserId()
                		, sessionDto.getUserPassWordKakuninImple(), shozokuKbn);
            }
			//1．パラメータ．請求フラグ＝'1'（請求対象）の場合、ご請求開始日必須
			if(sessionDto.getUIUser().getSekyuFlg().equals("1")){
				if(CommonUtil.isNull(sessionDto.getUIUser().getSeikyuDt())){
					throw new NotNullException("ご請求開始日","seikyuDt",null);
				}
			}

		}
		_checkUserNameKj(sessionDto.getUIUser().getUserNameKj(), "ご利用者名");
		_checkUserNameKana(sessionDto.getUIUser().getUserNameKana(), "ご利用者名（カナ）");
        // 文字列長チェック
		_checkByteLength(userNameKj, 40, userNameLabelName, "usernameKj", 0);
		_checkByteLength(sessionDto.getUIUser().getUserNameKana(), 40, userNameLabelName+"（カナ）", "usernameKana", 0);
		if(sessionDto.isSystemAdminUser()){
	        //オーナーコード文字列長チェック
			for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
				if(eOner.isChecked() && eOner.getOnerCd().trim().getBytes().length > 5){
					_checkByteLength(eOner.getOnerCd(), 5, eOner.getCompanyName()+" オーナーコード", "onerCd", i);
				}
			}
			for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
				if(eOner.isChecked()) {
					List mosList = bizadminUITogoOwnerDao.select(eOner.getOnerCd().trim(), eOner.getCompanyCd());
	    			if(mosList == null || mosList.size() <= 0) {
	    				throw new NotExistException(
	    						eOner.getCompanyName()+" オーナーコード","onerCd",i);
	    			}
	    			else {
	                    UITogoOwner entity = (UITogoOwner)mosList.get(0);
	                    eOner.setOnerNameKj(entity.getOnerNameKj());
	    			}
				}
			}
			//日付整合性チェック
			_checkDate(sessionDto);
		}
		//メールアドレス整合性チェック
		_checkMailAddress(sessionDto);
        //システム管理者の場合確認処理前に下記の事前処理を行います。
        if(sessionDto.isSystemAdminUser()) {
        	//ユーザー対応オーナー情報から画面で設定した会社のチェック状態をList[[ユーザー所属会社]]に同期させます。
        	for(int i=0; i<sessionDto.getUserOnerList().size(); i++) {
        		UIUserOner eOner = (UIUserOner)sessionDto.getUserOnerList().get(i);
       			UIUserCompany eCompany = (UIUserCompany)sessionDto.getUserCompanyList().get(i);
       			//各会社のチェック状態を同期させます。
       			eCompany.setChecked(eOner.isChecked());
        	}
        }
 	}
    /**
     * 店舗ユーザー情報用
     * 事前条件処理
     * 
     * @param sessionDto
     */
    private void _validateTenpoUser(AccounteditDto sessionDto) {
    	_validate(sessionDto);
        if (sessionDto.getUIUser() == null) {
            throw new MissingDataException("ユーザー情報");
        }
        if (sessionDto.getUserCompany() == null) {
            throw new MissingDataException("ユーザー所属会社情報");
        }
    }
	/**
     * 店舗ユーザー情報用
     * 実行処理
     */
	private void _executeTenpoUser(AccounteditDto sessionDto){
		//０．事前条件処理を行います。
		_validateTenpoUser(sessionDto);
		
		String userNameLabelName = "ご利用者名";
		String userNameKj = sessionDto.getUIUser().getUserNameKj();
		//１．必須のパラメータが満たされていること。
        if(CommonUtil.isNull(userNameKj)){
            throw new NotNullException(userNameLabelName,"usernameKj",null);
        }
		String miseCd = sessionDto.getUserMise()!= null?sessionDto.getUserMise().getMiseCd():null;
		if(sessionDto.isSystemAdminUser()){
			if(CommonUtil.isNull(miseCd)){
				throw new NotNullException("店コード","miseCd",null);
			}
		}
        //2006/07/04 ADD START パスワード追加対応 INAZAWA
        if(!CommonUtil.isNull(sessionDto.getUserPassWordImple())){
            if(CommonUtil.isNull(sessionDto.getUserPassWordKakuninImple())){
                throw new NotNullException("確認用パスワード","userPassWordKakuninImple",null);
            }
            if(!sessionDto.getUserPassWordKakuninImple().equals(sessionDto.getUserPassWordImple())){
                throw new ConstraintsViolationException("パスワードリセットと確認用パスワードは、一致し", "");
            }
            sessionDto.setUserPassWord(sessionDto.getUserPassWordImple());
            sessionDto.setUserPassWordKakunin(sessionDto.getUserPassWordKakuninImple());
        }else{
            sessionDto.setUserPassWord(sessionDto.getUserPassWord());
            sessionDto.setUserPassWordKakunin(sessionDto.getUserPassWordKakunin());
        }
        if(!CommonUtil.isNull(sessionDto.getUserPassWordKakuninImple())){
            if(CommonUtil.isNull(sessionDto.getUserPassWordImple())){
                throw new NotNullException("確認用パスワードが入力されている場合、パスワードリセット","userPassWordImple",null);
            }
        }
        //パスワードポリシーチェック
        if(!CommonUtil.isNull(sessionDto.getUserPassWordKakuninImple())){
        	String shozokuKbn = "";
            String hanshaOwner = 
            	getBizadminUIGroupTogoMiseDao().selectHansha(
            			miseCd, sessionDto.getUserMise().getCompanyCd());       
        
            if(hanshaOwner != null){
                shozokuKbn = UserShozokuKbn.HANSHA_TENPO;  
            } else {
                String aiteKbn = getBizadminMstUserShozokuDao().select(
                		miseCd, sessionDto.getUserMise().getCompanyCd());
                if (aiteKbn != null) {
                    if (aiteKbn.equals("2")) {
                        shozokuKbn = UserShozokuKbn.RC_TENPO;
                    } else if (aiteKbn.equals("1")) {
                        shozokuKbn = UserShozokuKbn.FC_TENPO;
                    } else {
                        shozokuKbn = UserShozokuKbn.FC_TENPO;
                    }
                }               
            }
            getValidatePasswordLogic().execute(sessionDto.getUIUser().getUserId()
            		, sessionDto.getUserPassWordKakuninImple(), shozokuKbn);
    		//店コード整合性チェック
    		_checkHankakuNumCode(miseCd, "店コード", "miseCd", 0);
        }
		_checkUserNameKj(userNameKj, userNameLabelName);
		_checkUserNameKana(sessionDto.getUIUser().getUserNameKana(), userNameLabelName+"（カナ）");
        // 文字列長チェック
		_checkByteLength(userNameKj, 40, userNameLabelName, "usernameKj", 0);
		_checkByteLength(sessionDto.getUIUser().getUserNameKana(), 40, userNameLabelName+"（カナ）", "usernameKana", 0);
		if(sessionDto.isSystemAdminUser()){
			_checkByteLength(miseCd, 5, "店コード", "miseCd", 0);
			//日付整合性チェック
			_checkDate(sessionDto);
		}
		//メールアドレス整合性チェック
		_checkMailAddress(sessionDto);
        /*
		７．パラメータ.店コードが存在する場合、店コードのチェックを行う。
				　　①．Dao【店マスタ．店マスタの取得】を実行する。
				　　         パラメータ： パラメータ．管理会社企業コード／パラメータ.店コード
				　　②．件数が0件の場合、【E0103】（“店コード”）を発生させる。
         */
        if(sessionDto.isSystemAdminUser()){
        	List miseList = bizadminUIGroupTogoMiseDao.selectMise(miseCd, sessionDto.getUserCompany().getRCompanyCd());
        	if(miseList == null || miseList.size() <= 0){
        		throw new NotExistException("店コード","miseCd",null);
        	}else{
                UIGroupTogoMise entity = (UIGroupTogoMise)miseList.get(0);
                sessionDto.getUserMise().setMiseNameKj(entity.getMiseNameKj());
            }
        }
        //コードと名称の同期を取ります。
    	//会社名称設定
        for (Iterator i = sessionDto.getCompanyList().iterator(); i.hasNext();) {
        	CodCompany codCompany = (CodCompany) i.next();
            if(codCompany.getCompanyCd().equals(sessionDto.getUserCompany().getRCompanyCd())){
            	//ユーザー管理会社の情報へコード・名称の同期を取ります。
            	sessionDto.getUserCompany().setRCompanyName(codCompany.getCompanyName());
            	//ユーザー対応店舗の情報へコード・名称の同期を取ります。
            	sessionDto.getUserMise().setCompanyCd(codCompany.getCompanyCd());
            	sessionDto.getUserMise().setCompanyName(codCompany.getCompanyName());
            }
        }
	}
	/**
	 * 半角数字コードチェック処理
	 * 
	 * @param date
	 * @param dateLabelName
	 * @param htmlElementName
	 * @return String スラッシュを除去したyyyyMMddの型の値を戻します。
	 */
	private void _checkHankakuNumCode(String code, String dateLabelName, String htmlElementName, int htmlElementIndex) {
		// コード欄 半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		if(!CommonUtil.isNull(code) 
				&& !codeVerifier.validate(code.trim())){
			throw new InvalidInputException(dateLabelName,htmlElementName,htmlElementIndex);
		}
	}
	/**
	 * ユーザー名称整合性チェック処理
	 * 
	 * @param name
	 * @param labelName
	 */
	private void _checkUserNameKj(String name, String labelName) {
        DefaultJapaneseVerifier defaultJapaneseVerifier = new JinmeiVerifier();
        if (!defaultJapaneseVerifier.validate(name.trim())) {
            throw new InvalidInputException(labelName, "usernameKj",null);
        }
	}
	/**
	 * ユーザー名称(カナ)整合性チェック処理
	 * 
	 * @param name
	 * @param labelName
	 */
	private void _checkUserNameKana(String name, String labelName) {
        ZenkakuKatakanaVerifier zenkakuKatakanaVerifier = new ZenkakuKatakanaVerifier();
        //半角カナも入力可
        HankakuKatakanaVerifier hankakuKatakanaVerifier = new HankakuKatakanaVerifier();
        if (!zenkakuKatakanaVerifier.validate(name.trim())) {
            if (!hankakuKatakanaVerifier.validate(name.trim())) {
                throw new InvalidInputException(labelName,"usernameKana",null);
            }
        }
	}
	/**
	 * 上限バイト数チェック処理
	 * 
	 * @param data
	 * @param maxLength
	 * @param dateLabelName
	 * @param htmlElementName
	 * @param htmlElementIndex
	 */
	private void _checkByteLength(String data, int maxLength, String dateLabelName, String htmlElementName
			, int htmlElementIndex) {
		if(!CommonUtil.isNull(data) 
				&& data.getBytes().length > maxLength)
		{
			throw new InvalidInputException(dateLabelName,htmlElementName, htmlElementIndex);
		}
	}
	/**
	 * 日付整合性チェック
	 * @param sessionDto
	 */
	private void _checkDate(AccounteditDto sessionDto) {
    	//下記の日付の整合性をチェックします。
    	//不整合の場合は、『～は正しくありません。』のエラーがスローされます。
       	sessionDto.getUIUser().setSeikyuDt(
       			_checkDate(sessionDto.getUIUser().getSeikyuDt()
       					, "ご請求開始日", "seikyuDt"));
       	sessionDto.getUIUser().setSeikyuUpdtDt(
       			_checkDate(sessionDto.getUIUser().getSeikyuUpdtDt()
       					, "ご請求変更予定日", "seikyuUpdtDt"));
       	sessionDto.getUIUser().setAppliedDt(
       			_checkDate(sessionDto.getUIUser().getAppliedDt()
       					, "お申込日", "apploedDt"));
	}
	/**
	 * メールアドレスチェック
	 * @param sessionDto
	 */
	private void _checkMailAddress(AccounteditDto sessionDto) {
    	//メールアドレスチェック
    	String mailAdd = sessionDto.getUIUser().getMailAdd();
    	if(!CommonUtil.isNull(mailAdd)) {
    		if(mailAdd.getBytes().length > 60){
    			throw new InvalidInputException("メールアドレス","mailAdd",null);
    		}
        	if (!_checkMailAdress(mailAdd.trim())) {
                throw new InvalidInputException("メールアドレス","mailAdd",null);
        	}
    	}		
	}
	/**
	 * メールアドレスチェック
	 * @return boolean
	 */
	private boolean _checkMailAdress(String str){
		if ((!CommonUtil.isNull(str)) &&
		  ((str.indexOf("@") < 0) ||
		  (str.indexOf(".") < 0) ||
		  (str.indexOf("@") != str.lastIndexOf("@")))){
			return false;
		}
		if (!CommonUtil.isNull(str)) {
	    	String mailStr = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@._-";
	    	for (int i=0; i<str.length(); i++) {
	    		char checkChar = str.charAt(i);
	    		if (mailStr.indexOf(checkChar,0)==-1){
	    			return false;
	    		}
	    	}
		}
		return true;
	}
	/**
	 * yyyy/MM/dd日付型のチェック処理
	 * 
	 * @param date
	 * @param dateLabelName
	 * @param htmlElementName
	 * @return String スラッシュを除去したyyyyMMddの型の値を戻します。
	 */
	private String _checkDate(String date, String dateLabelName, String htmlElementName) {
		// 2-3.日付型の欄にYYYY/MM/DDの型で入力されているかチェックする
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, 
        		DateFormatter.PATTERN_SLASH);
    	if (!CommonUtil.isNull(date)) {
    		if(!dateVerifier.validate(date.trim())) {
            throw new InvalidInputException(dateLabelName,htmlElementName,null);
        	}
           	return dateFormatter.format(date.trim(),
            		DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD);
        }
    	return date;
	}

    /**
     * 業態整合性チェック
     * @param accounteditDto
     * @return チェック結果
     */
    private void _checkGyotai(AccounteditDto sessionDto, List selectedComapnyList) {

        // 業態区分リスト作成
        List gyotaiKbnList = new ArrayList();
        for (Iterator it = sessionDto.getUserGyotaiList().iterator(); it.hasNext();) {
            Object obj = it.next();
            String gyotaiKbn = null;
            if(obj instanceof UIGyotai){
                gyotaiKbn = ((UIGyotai) obj).getGyotaiKbn();
            }
            if(obj instanceof UIUserGyotai){
                gyotaiKbn = ((UIUserGyotai) obj).getGyotaiKbn();
            }
            gyotaiKbnList.add(gyotaiKbn);
        }
        // 選択した業態に対応する会社コードのリストを作成
        List mstGyotaiCompanyList = bizadminUIGyotaiCompanyDao.select(gyotaiKbnList);
        List gyotaiCompanyList = new ArrayList();
        for (Iterator it = mstGyotaiCompanyList.iterator(); it.hasNext();) {
            UIGyotaiCompany mstGyotaiCompany = (UIGyotaiCompany) it.next();
            gyotaiCompanyList.add(mstGyotaiCompany.getCompanyCd());
        }
        // 選択した会社に対応する業態が選択されているかチェック
        // 選択した業態に対応する会社が選択されているかチェック
        if (!gyotaiCompanyList.containsAll(selectedComapnyList)
        		|| !selectedComapnyList.containsAll(gyotaiCompanyList)) {
            throw new GenericCommentException("指定された業態と会社が不整合", "gyoutaiButton", null);
        }
    }

	/**
	 * @return bizadminUIGyotaiCompanyDao を戻します。
	 */
	public UIGyotaiCompanyDao getBizadminUIGyotaiCompanyDao() {
		return bizadminUIGyotaiCompanyDao;
	}
	/**
	 * @param bizadminUIGyotaiCompanyDao bizadminUIGyotaiCompanyDao を設定。
	 */
	public void setBizadminUIGyotaiCompanyDao(UIGyotaiCompanyDao mstGyotaiCompanyDao) {
		this.bizadminUIGyotaiCompanyDao = mstGyotaiCompanyDao;
	}
	/**
	 * @return bizadminUITogoOwnerDao を戻します。
	 */
	public UITogoOwnerDao getBizadminUITogoOwnerDao() {
		return bizadminUITogoOwnerDao;
	}
	/**
	 * @param bizadminUITogoOwnerDao bizadminUITogoOwnerDao を設定。
	 */
	public void setBizadminUITogoOwnerDao(UITogoOwnerDao mstTogoOwnerDao) {
		this.bizadminUITogoOwnerDao = mstTogoOwnerDao;
	}

    /**
     * @return bizadminUIUserDao を戻します。
     */
    public UIUserDao getBizadminUIUserDao() {
        return bizadminUIUserDao;
    }

    /**
     * @param userDao bizadminUIUserDao を設定。
     */
    public void setBizadminUIUserDao(UIUserDao userDao) {
        this.bizadminUIUserDao = userDao;
    }

    public UIGroupTogoMiseDao getBizadminUIGroupTogoMiseDao() {
        return bizadminUIGroupTogoMiseDao;
    }

    public void setBizadminUIGroupTogoMiseDao(UIGroupTogoMiseDao mstGroupTogoMiseDao) {
        this.bizadminUIGroupTogoMiseDao = mstGroupTogoMiseDao;
    }

    public ValidatePasswordLogic getValidatePasswordLogic() {
        return validatePasswordLogic;
    }

    public void setValidatePasswordLogic(ValidatePasswordLogic validatePasswordLogic) {
        this.validatePasswordLogic = validatePasswordLogic;
    }

    public MstUserShozokuDao getBizadminMstUserShozokuDao() {
        return bizadminMstUserShozokuDao;
    }

    public void setBizadminMstUserShozokuDao(MstUserShozokuDao iuserShozokuDao) {
        bizadminMstUserShozokuDao = iuserShozokuDao;
    }

    public MstHanshaOnerDao getMstHanshaOnerDao() {
        return mstHanshaOnerDao;
    }

    public void setMstHanshaOnerDao(MstHanshaOnerDao mstHanshaOnerDao) {
        this.mstHanshaOnerDao = mstHanshaOnerDao;
    }


}
