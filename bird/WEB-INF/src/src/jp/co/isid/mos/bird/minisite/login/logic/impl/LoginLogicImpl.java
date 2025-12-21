/**
 *
 */
package jp.co.isid.mos.bird.minisite.login.logic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.common.util.BlowfishUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.LoginDao;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.minisite.login.action.LoginAction;
import jp.co.isid.mos.bird.minisite.login.dao.UIUserDao;
import jp.co.isid.mos.bird.minisite.login.entity.UIUser;
import jp.co.isid.mos.bird.minisite.login.exception.MinisiteIllegalAccessException;
import jp.co.isid.mos.bird.minisite.login.logic.LoginLogic;
/**
 * ログインロジック
 *
 * 作成日:2017/08/05
 * @author zcj
 *
 */
public class LoginLogicImpl extends jp.co.isid.mos.bird.framework.logic.impl.LoginLogicImpl implements LoginLogic {
	/** ロジックＩＤ：MNI000L01 */
    public static final String LOGIC_ID = LoginAction.SCREEN_ID  + "L01";
    /** 半角スペース */
    private static final String SPACE = " ";
    /** 半角プラス */
    private static final String PLUS = "+";
    /** 半角スラッシュ */
    private static final String SLASH = "/";
    /** 遷移可能VIEWID情報 */
    @SuppressWarnings("rawtypes")
	private Map minisiteViewList;
    /*パスワードポリシー*/
    @SuppressWarnings("rawtypes")
	private Map birdPasswordPolicy;
    /** DAO【ログイン】*/
    private LoginDao loginDao;
    /** DAO【ユーザー情報】*/
    private UIUserDao minisiteLoginUIMiseUserDao;
    /** DAO【ユーザー所属区分情報】*/
    private MstUserShozokuDao mstUserShozokuDao;

	/**
	 * 事前条件確認処理
	 *
	 * @param request HttpServletRequest
	 */
    private void validate(HttpServletRequest request) {
    	//セッションを取得します。
        HttpSession session = request.getSession();
        //キー
        String key = (String) request.getParameter(LoginAction.PK_KEY);
    	//必須チェック
    	if(CommonUtil.isNull(key)){
            throw new MinisiteIllegalAccessException("パラメータ必須チェック");
        }
		//CONTROL【BIRD日付情報】の初期化を行います。
        session.setAttribute("birdDateInfo", null);
        //CONTROL【BIRDユーザー情報】の初期化を行います。
        session.setAttribute("birdUserInfo", null);
        //MINISITE用BIRDユーザー情報をインスタンス化し、戻り値[店情報]を格納します。
        session.setAttribute("minisiteUserInfo", null);
    }

    /**
	 * アクセス時刻確認処理
	 *
	 * @param time
	 *
	 * @return チェック結果
	 */
    private void validateTime(String time) {
    	boolean rtFlg = true;
    	boolean timeoutFlg = true;

    	//数字と桁数チェック
    	CodeVerifier verifierTimeCd = new CodeVerifier(14);
    	if (!verifierTimeCd.validate(time) && time.getBytes().length != 14) {
    		rtFlg = false;
    	} else {
    		// 前後30分の範囲内
    		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    		fmt.setLenient(false);
    		try {
    			long systemDate = System.currentTimeMillis();
				Date dt = fmt.parse(time.substring(0, 8) +
						" "
						+ time.substring(8,10)
						+ ":"
						+ time.substring(10,12)
						+ ":"
						+ time.substring(12,14));
    			long urlDate = dt.getTime();
    			long diff = Math.abs(systemDate - urlDate) / (1000 * 60);
    			if (diff > 30) {
    				timeoutFlg = false;
    			}

			} catch (ParseException e) {
				rtFlg = false;
			}
    	}
    	if (!rtFlg) {
    		throw new MinisiteIllegalAccessException("アクセス時刻桁数チェック");
    	}
    	if (!timeoutFlg) {
    		throw new MinisiteIllegalAccessException("アクセス時刻有効期限切れ");
    	}
    }

	/**
	 * MINISITEログイン処理実行
	 *
	 * @param request HttpServletRequest
	 * @return String  遷移VIEWID
	 * @see jp.co.isid.mos.bird.framework.logic.LoginLogic#execute(jp.co.isid.mos.bird.framework.dto.LoginDto, javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("rawtypes")
	public String execute(HttpServletRequest request) {

		//１、【パラメータ必須チェック】
		validate(request);
        //パラメータをBase64デコード、Blowfishで復号化を実施する。
		//キー値
		String key = (String) request.getParameter(LoginAction.PK_KEY);
		//変換したキー
		String strKey = key;
		try {
			try {
				if (key.contains(SPACE)) {
					//URLにある文字「+」はスペースに文字化けされたので、スペースを「+」に変換してから、Base64.getEncoderで複号する。
					strKey = key.replaceAll(SPACE, PLUS);
					key = BlowfishUtil.decryptNotUrl(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
				} else if (key.contains(PLUS) || key.contains(SLASH)) {
					//URLには、「+」が存在する または 「/」が存在する場合、URL変換不要、Base64.getEncoderで複号する。
					key = BlowfishUtil.decryptNotUrl(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
				} else {
					//URLには、「+」「/」が存在しない場合、Base64.getUrlEncoderで変換してから複号化する。
					key = BlowfishUtil.decryptUrl(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
				}
		    } catch (java.lang.IllegalArgumentException e) {
				//Base64で複号できない場合、旧メソッド（URLDecoder＋BASE64Decoder）で複号する。
				key = BlowfishUtil.decrypt(BlowfishUtil.MINISITE_DECRYPT_KEY, strKey);
		    }
	    } catch (Exception e) {
	        	// Blowfishで復号できない
	        	throw new MinisiteIllegalAccessException("パラメータは認識できません。");
	    }

        // ２、複合した文字列が規定の文字列であるかチェック。
		//ビューID
        String viewId = null;
        //ユーザーID
        String userId = null;
        //アクセス時刻
        String time = null;
        CodeVerifier verifierKeyCd = new CodeVerifier(22, true);
        if (!verifierKeyCd.validate(key) || key.getBytes().length != 22) {
        	throw new MinisiteIllegalAccessException("パラメータ桁数チェック");
        } else {
        	userId = key.substring(0, 8);
        	time = key.substring(8);

        	@SuppressWarnings("unchecked")
			Iterator<String> iter = getMinisiteViewList().keySet().iterator();
        	while(iter.hasNext()){
        		viewId = iter.next();
        	}
        }

        // ３、【パラメータ妥当性チェック（時刻）】
        validateTime(time);

        // ４、【ユーザーIDの存在チェック】
        CodeVerifier verifierUserCd = new CodeVerifier(8, true);
        if (!verifierUserCd.validate(userId) ||
        		userId.getBytes().length != 8) {
        	throw new MinisiteIllegalAccessException("ユーザーIDの桁数チェック");
        }
      	List listUser = getMinisiteLoginUIMiseUserDao().select(userId);
      	//    下記のExceptionを発生させます。
      	if(listUser.size() == 0 || listUser.size() > 1) {
      		throw new MinisiteIllegalAccessException("ユーザーIDの存在チェック");
      	}
      	UIUser uiUser = (UIUser)listUser.get(0);


        //５、【失敗回数チェック】
      	//６、【使用停止チェック】
      	//７、【パスワード有効期限チェック】
      	//８、【オーナー解約チェック】
      	//上記５～８のチェックは通常ログイン時と同じロジック
        CtlPasswordPolicy ctlPswdPolicy =
        	(CtlPasswordPolicy) getBirdPasswordPolicy().get(getPswdCheckType(uiUser.getUserId()));

		//新しくDTO【ログイン情報】をインスタン化し、下記の値を設定します。
		LoginDto loginDto = new LoginDto();
    	//DTO【ログイン情報】．ユーザーＩＤへ
    	//    処理４のUIUser[ユーザー情報].ユーザーＩＤを設定します。
    	loginDto.setUserId(uiUser.getUserId());
    	//DTO【ログイン情報】．ユーザーパスワードへ
    	//    処理４のUIUser[ユーザー情報].ユーザーパスワードを設定します。
        byte[] pswdByte = uiUser.getUserPswd();
        String pswd = "";
        if (pswdByte != null) {
            pswd = new String(pswdByte);
        }
    	loginDto.setUserPswd(pswd);
        //DTO【ログイン情報】.画面ＩＤへ先頭1桁から6桁の文字列を設定します。
        loginDto.setGamenId(viewId.substring(0, 6));
        //DTO【ログイン情報】へユーザーパスワードポリシー.パスワード失敗確定回数を設定します。
        loginDto.setPswdLockCnt(ctlPswdPolicy.getFailureToLockCnt());
        //DTO【ログイン情報】へユーザーパスワードポリシー.パスワード設定可能文字列種類を設定します。
        loginDto.setPswdAvailableTerm(ctlPswdPolicy.getPswdAvailableTerm());
        //DTO【ログイン情報】.旧e-mosslesからの遷移判断値へfalseに設定します。
        loginDto.setFromEmosFlg(false);
//        //DTO【ログイン情報】.minisiteからの遷移判断値へtrueを設定します。
//        loginDto.setPmossles(true);

        //継承クラスLOGIC【ログイン処理】の実行処理を行います。戻り値[ログインBIRDユーザー情報]を取得します。
        super.execute(loginDto, request);

        //セッションからキー”birdUserInfo”でCONTROL【BIRDユーザー情報】を取得します。
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");

        //CONTROL【BIRDユーザー情報】.p-mossles判断値をtrueに設定します。
        birdUserInfo.setPmossles(true);

        //共通パラメータ用DTO関連の設定を行います。
        setupCommonDto(request);

        return viewId;
	}

	/**
     * チェック用ユーザータイプ判定
     * @param userId
     * @param userShozokuKbn
     * @return
     */
    @SuppressWarnings("rawtypes")
	private String getPswdCheckType(String userId) {
        String pswdCheckType = "";

        //共通Dao【ユーザ所属．ユーザー所属の取得】を実行し、戻り値List[[ユーザー所属区分]]を取得します。
        List listUserShozoku = getMstUserShozokuDao().getMstUserShozoku(userId);
        if (listUserShozoku != null && !listUserShozoku.isEmpty()) {
            //取得できた場合、1件目の所属区分でチェックタイプを取得
            pswdCheckType = ((MstUserShozoku) listUserShozoku.get(0)).getShozokuKbn();
        }
        else {
            throw new GenericErrorException(
            		"ログイン", "ユーザー所属区分の取得に失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。");
        }

        return pswdCheckType;
    }
    /**
     * 共通パラメータ用DTO関連処理
     */
    @SuppressWarnings("rawtypes")
	private void setupCommonDto(HttpServletRequest request) {
        S2Container container = SingletonS2ContainerFactory.getContainer();

        CommonCodeDto commonCodeDto = (CommonCodeDto) container.getComponent(CommonCodeDto.class);
        if (commonCodeDto != null) {
            String sIsUseCommonDto = "";
            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String pName = (String) e.nextElement();
                if (pName.lastIndexOf(":isUseCommonDto") != -1) {
                    sIsUseCommonDto = request.getParameter(pName);
                    break;
                }
            }
            if ("1".equals(sIsUseCommonDto)) {
                commonCodeDto.setUseCommonDto(true);
            }
            else {
                commonCodeDto.setUseCommonDto(false);
            }
        }
    }
	/**
	 * @return クラス変数birdPasswordPolicy を戻します。
	 */
	@SuppressWarnings("rawtypes")
	public Map getBirdPasswordPolicy() {
		return birdPasswordPolicy;
	}

	/**
	 * @param birdPasswordPolicy を クラス変数birdPasswordPolicyへ設定します。
	 */
	@SuppressWarnings("rawtypes")
	public void setBirdPasswordPolicy(Map birdPasswordPolicy) {
		this.birdPasswordPolicy = birdPasswordPolicy;
	}

	/**
	 * @return クラス変数mstUserShozokuDao を戻します。
	 */
	public MstUserShozokuDao getMstUserShozokuDao() {
		return mstUserShozokuDao;
	}

	/**
	 * @param mstUserShozokuDao を クラス変数mstUserShozokuDaoへ設定します。
	 */
	public void setMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
		this.mstUserShozokuDao = mstUserShozokuDao;
	}
	/**
	 * @return クラス変数loginDao を戻します。
	 */
	public LoginDao getLoginDao() {
		return loginDao;
	}
	/**
	 * @param loginDao を クラス変数loginDaoへ設定します。
	 */
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	/**
	 * DAO【ユーザーマスタ情報】取得処理
	 * @return
	 */
	public UIUserDao getMinisiteLoginUIMiseUserDao() {
		return minisiteLoginUIMiseUserDao;
	}
	/**
	 * DAO【ユーザーマスタ情報】設定処理
	 * @param minisiteLoginUIMiseUserDao
	 */
	public void setMinisiteLoginUIMiseUserDao(UIUserDao minisiteLoginUIMiseUserDao) {
		this.minisiteLoginUIMiseUserDao = minisiteLoginUIMiseUserDao;
	}
	/**
	 * minisite遷移可能VIEWID情報取得処理
	 * @return クラス変数minisiteViewList を戻します。
	 */
	@SuppressWarnings("rawtypes")
	public Map getMinisiteViewList() {
		return minisiteViewList;
	}
	/**
	 * minisite遷移可能VIEWID情報設定処理
	 * @param minisiteViewList を クラス変数minisiteViewListへ設定します。
	 */
	@SuppressWarnings("rawtypes")
	public void setMinisiteViewList(Map minisiteViewList) {
		this.minisiteViewList = minisiteViewList;
	}
}
