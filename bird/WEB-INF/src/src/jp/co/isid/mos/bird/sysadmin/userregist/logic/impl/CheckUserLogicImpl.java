/*
 * 作成日: 2006/2/17
 * 更新日: 2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic.impl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.dao.UIGroupTogoMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIGyotaiCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UITogoOwnerDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIGroupTogoMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIGyotaiCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UITogoOwner;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.logic.ValidatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.framework.exception.AlreadyExistException;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuKatakanaVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.JinmeiVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodBumon;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.CheckUserLogic;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetUserShozokuKbnLogic;

/**
 * 登録内容のチェック処理ロジック
 * @author itamoto
 */
public class CheckUserLogicImpl implements CheckUserLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA006L02";

	private UITogoOwnerDao bizadminUITogoOwnerDao;
	private UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao;
    private UIUserDao  bizadminUIUserDao;
	private UIGyotaiCompanyDao bizadminUIGyotaiCompanyDao;
   /* ValidatePasswordLogicImpl */
    private ValidatePasswordLogic validatePasswordLogic;
    /* GetUserShozokuKbnLogicImpl */
    private GetUserShozokuKbnLogic getUserShozokuKbnLogic;
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws ApplicationException
     */
    private void validate(UserRegistDto userRegistDto) {
    	String selectedCompanyCd = userRegistDto.getUserCompany().getRCompanyCd();
        //コードと名称の同期を取ります。
        if(userRegistDto.getCompanyList() != null) {
    	//会社名称設定
        for (Iterator i = userRegistDto.getCompanyList().iterator(); i.hasNext();) {
        	CodCompany codCompany = (CodCompany) i.next();
            if(codCompany.getCompanyCd().equals(selectedCompanyCd)){
            	userRegistDto.getUserCompany().setRCompanyName(codCompany.getCompanyName());
            	break;
            }
        }
        }
        if(userRegistDto.getBumonList() != null) {
	        //部門名称設定
	        for (Iterator i = userRegistDto.getBumonList().iterator(); i.hasNext();) {
	            CodBumon codBumon = (CodBumon) i.next();
	            if(codBumon.getBumonCd().equals(userRegistDto.getUIUser().getBumonCd())){
	            	userRegistDto.getUIUser().setBumonName(codBumon.getBumonName());
	            	break;
	            }
	        }
        }
    	String userTypeCd = userRegistDto.getUIUser().getUsertypeCd();
        if(UserType.TENPO.equals(userTypeCd)) {
        	//会社プルダウン選択値を[ユーザー対応店舗情報].会社コードに同期します。
        	userRegistDto.getUserMise().setCompanyCd(selectedCompanyCd);
        	userRegistDto.getUserMise().setCompanyName(userRegistDto.getUserCompany().getRCompanyName());
        }
        
    }
	/**
     * 登録内容のチェック処理
     * @param userRegistDto
     */
    public List execute(UserRegistDto userRegistDto) {
    	//事前条件処理
    	validate(userRegistDto);
    	
    	List returnList = null;
    	
		CodeVerifier codeVerifier = new CodeVerifier(true);
		String userTypeCd = userRegistDto.getUIUser().getUsertypeCd();
    	// １．必須のパラメータが満たされていること。
    	if (CommonUtil.isNull(userTypeCd)) {
			throw new NotNullException("ユーザータイプ");
    	}
		// ユーザIDチェック
    	String userId = userRegistDto.getUIUser().getUserId();
    	if (CommonUtil.isNull(userId)) {
			throw new NotNullException("ユーザーID","userId",null);
    	}
    	// ２．パラメータが妥当であること。
		// コード欄 半角英数字
		if (!codeVerifier.validate(userId)) {
			throw new InvalidInputException("ユーザーID","userId",null);
		}
		// 文字列長チェック
    	if (userId.getBytes().length > 8) {
			throw new InvalidInputException("ユーザーID","userId",null);
		}
		// １．既に対象ユーザが登録済でないか存在チェックを行う。
		//    ①．Dao【BIRDユーザ．BIRDユーザの取得】を実行する。
		//          パラメータ： パラメータ．ユーザID
    	//    ②．件数が1件以上の場合、【E0101】（“ユーザ”）を発生させる。
    	UIUser uIUser = bizadminUIUserDao.getUser(userId);
    	if (uIUser != null) {
    		throw new AlreadyExistException("ユーザーID","userId",null);
    	}
		CodeFormatter codefm = new CodeFormatter(8);
		codefm.setFormatPattern("00000000");
		userId = codefm.format(userId, true).trim();
		userRegistDto.getUIUser().setUserId(userId);
		
        //2006/07/04 ADD START パスワード追加対応 INAZAWA
        if (CommonUtil.isNull(userRegistDto.getUserPassWord())) {
            throw new NotNullException("初期パスワード","password",null);
        }
        if (CommonUtil.isNull(userRegistDto.getUserPassWordKakunin())) {
            throw new NotNullException("確認用パスワード","password2",null);
        }
        if(!userRegistDto.getUserPassWordKakunin().equals(userRegistDto.getUserPassWord())){
            throw new ConstraintsViolationException("初期パスワードと確認用パスワードは、一致し", "password", null);
        }
        //2006/07/04 ADD END パスワード追加対応 INAZAWA
        

		// ユーザ名チェック
        String userNameKj = userRegistDto.getUIUser().getUserNameKj();
        // ユーザ名必須チェック
		if (CommonUtil.isNull(userNameKj)) {
			throw new NotNullException("ユーザー名","userNameKj",null);
    	}
    	// 文字型の欄に文字以外の型が入力されていないかチェック
        //2006/05/02 外字だが、人名のため使用可能になるように修正
        DefaultJapaneseVerifier defaultJapaneseVerifier = new JinmeiVerifier();
    	if (!defaultJapaneseVerifier.validate(userNameKj)) {
            throw new InvalidInputException("ユーザー名","userNameKj",null);
    	}
		// 文字列長チェック
    	if (userNameKj.getBytes().length > 40) {
            throw new InvalidInputException("ユーザー名","userNameKj",null);
    	}
    	// 文字型の欄に文字以外の型が入力されていないかチェック
    	ZenkakuKatakanaVerifier zenkakuKatakanaVerifier = new ZenkakuKatakanaVerifier();
        HankakuKatakanaVerifier hankakuKatakanaVerifier = new HankakuKatakanaVerifier();
        String userNameKana = userRegistDto.getUIUser().getUserNameKana();
        if (!zenkakuKatakanaVerifier.validate(userNameKana)) {
            if (!hankakuKatakanaVerifier.validate(userNameKana)) {
                throw new InvalidInputException("ユーザー名（カナ）","userNameKana",null);
            }
    	}
		// 文字列長チェック
    	if (!CommonUtil.isNull(userNameKana)
				&& userNameKana.getBytes().length > 40) {
            throw new InvalidInputException("ユーザー名（カナ）","userNameKana",null);
    	}
        List ownerList = new ArrayList();
        codeVerifier = new CodeVerifier();
    	
        if (UserType.isHonbu(userTypeCd)) {
        	// 会社情報同期
        	String selectedCompanyCd = "";
        	for(int c=0; c<userRegistDto.getUserCompanyList().size(); c++) {
        		UIUserCompany userComp = (UIUserCompany)userRegistDto.getUserCompanyList().get(c);
        		if(userComp.getRCompanyCd().equals(userRegistDto.getUserCompany().getRCompanyCd())) {
        			userRegistDto.getUserCompany().setRCompanyName(userComp.getRCompanyName());
        			userRegistDto.getUserCompany().setCompanyCd(userComp.getCompanyCd());
        			userRegistDto.getUserCompany().setCompanyName(userComp.getCompanyName());
        			selectedCompanyCd = userComp.getCompanyCd();
        			break;
        		}
        	}
            // 部門チェック
            if (CommonUtil.isNull(userRegistDto.getUIUser().getBumonCd())) {
                throw new NotNullException("部門", "bumonCd", null);
            }
			// 業態必須チェック
			// 2．パラメータ．業態区分が１件以上存在すること。
			if(userRegistDto.getUserGyotaiList()==null || (userRegistDto.getUserGyotaiList().size() == 0)){
				throw new NotNullException("業態","gyoutaiButton",null);
			}
	        List selectedComapnyList = new ArrayList(0);
       		selectedComapnyList.add(selectedCompanyCd);
            // 業態整合性チェック
            _checkGyotai(userRegistDto, selectedComapnyList);
            //入力値整合性チェック
            String onerCd = userRegistDto.getUserOner().getOnerCd();
            if(!CommonUtil.isNull(userRegistDto.getUserOner().getOnerCd())) {
	    		//オーナーコード文字整合性チェック
	    		_checkHankakuNumCode(onerCd, "オーナーコード","onerCd", 0);
	            // 文字列長チェック
	    		_checkByteLength(onerCd, 5, "オーナーコード", "onerCd", 0);
	            if(!selectedCompanyCd.equals(userRegistDto.getUserOner().getCompanyCd()) ) {
	                throw new GenericCommentException("指定された所属会社とオーナーが不整合","onerCd", 0);
	            }
        		List onerCdList = bizadminUITogoOwnerDao.select(
        				onerCd, userRegistDto.getUserOner().getCompanyCd());
        		if(onerCdList == null || onerCdList.size() <=0) {
       				throw new NotExistException("オーナーコード","onerCd",null);
                }
        		else {
    	            UITogoOwner mstTogoOwner = (UITogoOwner)onerCdList.get(0);
    	            userRegistDto.getUserOner().setOnerCd(mstTogoOwner.getOnerCd());
    	            userRegistDto.getUserOner().setOnerNameKj(mstTogoOwner.getOnerNameKj());
        		}
            }
        }

/*************************** 2007/01/31 add start xamaruyama ***************************/

         // ※パラメータ．ユーザタイプコード＝'02'（オーナー）の場合
        if (UserType.isOner(userTypeCd)) {
            //契約タイプの妥当性チェック（プルダウンが選択されているか）
            if (userRegistDto.getKeiyakuChoice() == null || "".equals(userRegistDto.getKeiyakuChoice())) {
                throw new NotNullException("契約タイプ", "keiyakuChoice", null);
            }
            //管理会社必須チェック
			boolean notCheckedCompany = true;
			for(int i=0; i<userRegistDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)userRegistDto.getUserOnerList().get(i);
				if(eOner.isChecked()) {
					notCheckedCompany = false;
					break;
				}
			}
			if(notCheckedCompany){
				throw new NotNullException("会社選択","kaisyaCheck", null);
			}
			for(int i=0; i<userRegistDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)userRegistDto.getUserOnerList().get(i);
				if(eOner.isChecked() && CommonUtil.isNull(eOner.getOnerCd())){
					throw new NotNullException(
							eOner.getCompanyName()+" オーナーコード","onerCd", i);
				}
			}
           
			// 業態区分チェック
			// 2．パラメータ．業態区分が１件以上存在すること。
			if(userRegistDto.getUserGyotaiList()==null || (userRegistDto.getUserGyotaiList().size() == 0)){
				throw new NotNullException("業態","gyoutaiButton",null);
			}

	    	// ９．７.①で[[オーナーマスタ]]を取得した場合、[[オーナーマスタ]]をリターンする。
		    // 選択会社コードリスト取得
	        List selectedComapnyList = new ArrayList(0);
	        for(int i=0; i<userRegistDto.getUserOnerList().size(); i++) {
	        	UIUserOner entity = (UIUserOner)userRegistDto.getUserOnerList().get(i);
	        	if( entity.isChecked() ) {
	        		selectedComapnyList.add(entity.getCompanyCd());
	        		
	        	}
	        }
            //10.チェックがついていない場合、オーナー名を消す。
            for(int i=0; i<userRegistDto.getUserOnerList().size(); i++) {                
                UIUserOner eOner = (UIUserOner)userRegistDto.getUserOnerList().get(i);               
                if( !eOner.isChecked() ) {                  
                    eOner.setOnerNameKj(null);
              }
            }
            // 業態整合性チェック
            _checkGyotai(userRegistDto, selectedComapnyList);
	        //オーナーコード文字列長チェック
			for(int i=0; i<userRegistDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)userRegistDto.getUserOnerList().get(i);
				if(eOner.isChecked() && eOner.getOnerCd().trim().getBytes().length > 5){
					_checkByteLength(eOner.getOnerCd(), 5, eOner.getCompanyName()+" オーナーコード", "onerCd", i);
				}
			}
			for(int i=0; i<userRegistDto.getUserOnerList().size(); i++) {
				UIUserOner eOner = (UIUserOner)userRegistDto.getUserOnerList().get(i);
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
        	if ("1".equals(userRegistDto.getUIUser().getSekyuFlg())
        			&& CommonUtil.isNull(userRegistDto.getUIUser().getSeikyuDt())) {
        		throw new NotNullException("請求開始日","seikyuDt",null);
        	}
			//日付整合性チェック
			_checkDate(userRegistDto);
			returnList = ownerList;
		}

    	// ※パラメータ．ユーザタイプコード＝'03'（店舗）の場合
		if (UserType.isTenpo(userTypeCd)) {
	    	// 店コードチェック
			String miseCd = userRegistDto.getUserMise().getMiseCd();
			// 2．パラメータ．店コード必須
			if (CommonUtil.isNull(miseCd)) {
				throw new NotNullException("店コード","miseCd",null);
			}
			// コード欄 半角数字
			codeVerifier = new CodeVerifier();
			if (!codeVerifier.validate(miseCd)) {
            throw new InvalidInputException("店コード","miseCd",null);
			}
			// 文字列長チェック
			if (miseCd.getBytes().length > 5) {
	            throw new InvalidInputException("店コード","miseCd",null);
	    	}

			// ７．パラメータ.店コードが存在する場合、店コードのチェックを行う。
			//    ①．Dao【店マスタ．店マスタの取得】を実行する。
			//          パラメータ： パラメータ．管理会社企業コード／パラメータ.店コード
			//    ②．件数が0件の場合、【E0103】（“店コード”）を発生させる。
        	List miseList = bizadminUIGroupTogoMiseDao.selectMise(miseCd, userRegistDto.getUserCompany().getRCompanyCd());
        	if(miseList == null || miseList.size() <= 0){
        		throw new NotExistException("店コード","miseCd",null);
        	}else{
                UIGroupTogoMise entity = (UIGroupTogoMise)miseList.get(0);
                userRegistDto.getUserMise().setMiseNameKj(entity.getMiseNameKj());
            }
            // 日付整合性チェック
            _checkDate(userRegistDto);
            // 業態排他チェック
	        returnList = miseList;
		}
        // メールアドレスチェック
        if (!checkMailAdress(userRegistDto.getUIUser().getMailAdd())) {
            throw new InvalidInputException("メールアドレス","mailAdd",null);
        }
        

        //2008/11/17 共通パスワードチェック 追加
        //ユーザ公開対象所属区分 取得
        List listUserShozokuKbn = getGetUserShozokuKbnLogic().execute(userRegistDto);
        //パスワードチェックロジック実行
        String userShozokuKbn = "";
        if (listUserShozokuKbn != null && !listUserShozokuKbn.isEmpty()) {
            userShozokuKbn = listUserShozokuKbn.get(0).toString();
        }
        getValidatePasswordLogic().execute(userId, userRegistDto.getUserPassWord(), userShozokuKbn);    
        
		// ８．６.①で[[店マスタ]]を取得した場合、[[店マスタ]]をリターンする。
        return returnList;
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
	 * 日付整合性チェック
	 * @param sessionDto
	 */
	private void _checkDate(UserRegistDto sessionDto) {
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
       					, "お申込日", "appliedDt"));
	}

	/**
	 * メールアドレスチェック
	 * @return boolean
	 */
	private boolean checkMailAdress(String str){
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
     * 業態整合性チェック
     * 
	 * パラメータ.業態区分リストが存在する場合、業態のチェックを行う。
	 * ①．Dao【業態運営会社．業態運営会社の取得】を実行する。
	 *         パラメータ： パラメータ.業態区分リスト
	 * ②．①で取得した[[業態運営会社]]．管理会社企業コード が
	 *    パラメータ．企業コードリストに存在するかチェックする。
	 *        1．存在しない場合、【E0607】（“指定された業態と会社が不整合”）を発生させる。
     * @param userRegistDto
     * @return チェック結果
     */
    private void _checkGyotai(UserRegistDto userRegistDto, List selectedComapnyList) {

        // 業態区分リスト作成
        List gyotaiKbnList = new ArrayList();
        for (Iterator it = userRegistDto.getUserGyotaiList().iterator(); it.hasNext();) {
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

    public ValidatePasswordLogic getValidatePasswordLogic() {
        return validatePasswordLogic;
    }
    public void setValidatePasswordLogic(ValidatePasswordLogic validatePasswordLogic) {
        this.validatePasswordLogic = validatePasswordLogic;
    }
    public GetUserShozokuKbnLogic getGetUserShozokuKbnLogic() {
        return getUserShozokuKbnLogic;
    }
    public void setGetUserShozokuKbnLogic(
            GetUserShozokuKbnLogic getUserShozokuKbnLogic) {
        this.getUserShozokuKbnLogic = getUserShozokuKbnLogic;
    }
	/**
	 * @return クラス変数bizadminUIGroupTogoMiseDao を戻します。
	 */
	public UIGroupTogoMiseDao getBizadminUIGroupTogoMiseDao() {
		return bizadminUIGroupTogoMiseDao;
	}
	/**
	 * @param bizadminUIGroupTogoMiseDao を クラス変数bizadminUIGroupTogoMiseDaoへ設定します。
	 */
	public void setBizadminUIGroupTogoMiseDao(
			UIGroupTogoMiseDao bizadminUIGroupTogoMiseDao) {
		this.bizadminUIGroupTogoMiseDao = bizadminUIGroupTogoMiseDao;
	}
	/**
	 * @return クラス変数bizadminUIGyotaiCompanyDao を戻します。
	 */
	public UIGyotaiCompanyDao getBizadminUIGyotaiCompanyDao() {
		return bizadminUIGyotaiCompanyDao;
	}
	/**
	 * @param bizadminUIGyotaiCompanyDao を クラス変数bizadminUIGyotaiCompanyDaoへ設定します。
	 */
	public void setBizadminUIGyotaiCompanyDao(
			UIGyotaiCompanyDao bizadminUIGyotaiCompanyDao) {
		this.bizadminUIGyotaiCompanyDao = bizadminUIGyotaiCompanyDao;
	}
	/**
	 * @return クラス変数bizadminUITogoOwnerDao を戻します。
	 */
	public UITogoOwnerDao getBizadminUITogoOwnerDao() {
		return bizadminUITogoOwnerDao;
	}
	/**
	 * @param bizadminUITogoOwnerDao を クラス変数bizadminUITogoOwnerDaoへ設定します。
	 */
	public void setBizadminUITogoOwnerDao(UITogoOwnerDao bizadminUITogoOwnerDao) {
		this.bizadminUITogoOwnerDao = bizadminUITogoOwnerDao;
	}
	/**
	 * @return クラス変数bizadminUIUserDao を戻します。
	 */
	public UIUserDao getBizadminUIUserDao() {
		return bizadminUIUserDao;
	}
	/**
	 * @param bizadminUIUserDao を クラス変数bizadminUIUserDaoへ設定します。
	 */
	public void setBizadminUIUserDao(UIUserDao bizadminUIUserDao) {
		this.bizadminUIUserDao = bizadminUIUserDao;
	}
}
