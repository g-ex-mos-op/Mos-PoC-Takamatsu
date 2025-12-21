/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.logic.impl;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.common.dao.MstMiseDao;
import jp.co.isid.mos.bird.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.LoginDao;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.pmossles.control.PmosUserInfo;
import jp.co.isid.mos.bird.pmossles.login.action.LoginAction;
import jp.co.isid.mos.bird.pmossles.login.dao.UIMiseUserDao;
import jp.co.isid.mos.bird.pmossles.login.entity.UIMiseUser;
import jp.co.isid.mos.bird.pmossles.login.exception.PmosIllegalAccessException;
import jp.co.isid.mos.bird.pmossles.login.logic.LoginLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
/**
 * ログインロジック
 * 
 * 作成日:2010/07/23
 * @author xkinu
 * 
 * 更新日 2012/02/09 xkinu 追加 p-mossles時のチェック処理仕様変更
 * 　　・パスワード有効期限チェックは行わない。(e-mosslesログインロジック修正)
 * 　　・ＩＤとパスワードの同一チェックは行わない。(e-mosslesログインロジック修正)
 * ＜対応＞
 *　DTO【ログイン情報】.p-mossles遷移判断値の変数を追加し、trueを設定します。
 */
public class LoginLogicImpl extends jp.co.isid.mos.bird.framework.logic.impl.LoginLogicImpl implements LoginLogic {
	/** ロジックＩＤ：PPO000L01 */
    public static final String LOGIC_ID = LoginAction.SCREEN_ID+"L01";
    /** 遷移可能VIEWID情報 */
    private Map mapPmosViewId;
    /*パスワードポリシー*/
    private Map birdPasswordPolicy;
    /** DAO【ログイン】*/
    private LoginDao loginDao;
    /** DAO【ユーザー情報】*/
    private UIMiseUserDao pmosLoginUIMiseUserDao;
    /** DAO【ユーザー所属区分情報】*/
    private MstUserShozokuDao mstUserShozokuDao;
    /** DAO【店舗マスタ情報】*/
	private MstMiseDao mstMiseDao;

	/**
	 * 事前条件確認処理
	 * 
	 * 注意：Officeのハイパーリンクからは別セッション(OFFICEとIEの仕様?:2011/01/14現在)になります。    
	 * 補足：URLの直書き、テキストファイルからのリンクは同一セッションの処理でログイン処理が行われます。
	 * @param request HttpServletRequest
	 */
    private void validate(HttpServletRequest request) 
    {
    	//セッションを取得します。
        HttpSession session = request.getSession();
        String viewId = (String) request.getParameter(LoginAction.PK_VIEWID);
        String logprm = (String) request.getParameter(LoginAction.PK_LOGPRM);
    	//必須チェック
    	if(CommonUtil.isNull(logprm) || CommonUtil.isNull(viewId)){
            throw new PmosIllegalAccessException("パラメータ必須チェック");
        }
		//CONTROL【BIRD日付情報】の初期化を行います。
        session.setAttribute("birdDateInfo", null);
        //CONTROL【BIRDユーザー情報】の初期化を行います。
        session.setAttribute("birdUserInfo", null);
        //P-mossles用BIRDユーザー情報をインスタンス化し、戻り値[店情報]を格納します。
        session.setAttribute("pmosUserInfo", null);
    }
	/**
	 * p-mosslesログイン処理実行
	 * 
	 * 注意：Officeのハイパーリンクからは別セッション(OFFICEとIEの仕様?:2011/01/14現在)になります。    
	 * 補足：URLの直書き、テキストファイルからのリンクは同一セッションの処理でログイン処理が行われます。
	 * @param request HttpServletRequest
	 * @return String  遷移VIEWID
	 * @see jp.co.isid.mos.bird.framework.logic.LoginLogic#execute(jp.co.isid.mos.bird.framework.dto.LoginDto, javax.servlet.http.HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) {
		
		//０．事前条件確認処理
		validate(request);

        String viewId = (String) request.getParameter(LoginAction.PK_VIEWID);
        String logprm = (String) request.getParameter(LoginAction.PK_LOGPRM);
    	//１．パラメータの妥当性チェックを行います。
		//    次の条件が一つでも当てはまらない場合は、下記のExceptionを発生させます。
    	CodeVerifier verifierCd = new CodeVerifier(12);
    	boolean isCheckErr = false;
		//・パラメータ.ログインコードの文字数が12桁であること。
		//・パラメータ.ログインコードの文字数が半角数字のみであること。
		//・パラメータ.ログインコードの先頭2桁の文字列が数字”00”であること。
		//・パラメータ.ログインコードの先頭から4桁目の文字列が”5”であること。
		//・パラメータ.ログインコードの先頭から6桁目の文字列が”7”であること。
		//・パラメータ.ログインコードの先頭から8桁目の文字列が”1”であること。
		//・パラメータ.ログインコードの先頭から10桁目の文字列が”9”であること。
		//・パラメータ.ログインコードの先頭から12桁目の文字列をチェックディジットとし、下記の計算値と同じ数字になること。
		//    チェックディジットの値：【【((3桁目＋7桁目＋11桁目）×3＋（5桁目＋9桁目）×7）】/9の余り　
		//・パラメータ.遷移VIEWIDの文字数が9桁であること。
		//・パラメータ.遷移VIEWIDの文字数が半角英数字のみであること。
		//・パラメータ.遷移VIEWIDの先頭から6桁目の文字列が”V”であること。
    	
    	if (logprm.length()!=12 || viewId.length()!=9) {
            throw new PmosIllegalAccessException("パラメータ桁数チェック");
    	}

    	//先頭2桁は会社コードとして取得します。
    	String companyCd = logprm.substring(0, 2);
		String logprm3 = logprm.substring(2, 3);
		String logprm4 = logprm.substring(3, 4);
		String logprm5 = logprm.substring(4, 5);
		String logprm6 = logprm.substring(5, 6);
		String logprm7 = logprm.substring(6, 7);
		String logprm9 = logprm.substring(8, 9);
		String logprm11 = logprm.substring(10, 11);
		//チェックディジット値：【((3桁目＋7桁目＋11桁目）×3＋（5桁目＋9桁目）×7）】/9の余り
		int int3711 = Integer.valueOf(logprm3).intValue()+Integer.valueOf(logprm7).intValue()+Integer.valueOf(logprm11).intValue();
		int int59  = Integer.valueOf(logprm5).intValue()+Integer.valueOf(logprm9).intValue();
		int intCheckDjt = (int3711*3+int59*7)%9;
   		if (isCheckErr==false 
   				&& (
   				   !getMapPmosViewId().containsKey(viewId)
   				|| !verifierCd.validate(logprm)
    			|| !"00".equals(logprm.substring(0, 2))
    			|| !"5".equals(logprm4)
    			|| !"7".equals(logprm6)
    			|| !"1".equals(logprm.substring(7, 8))
    			|| !"9".equals(logprm.substring(9, 10))
    			|| !String.valueOf(intCheckDjt).equals(logprm.substring(11, 12))
    			) 
    	) {
            throw new PmosIllegalAccessException("パラメータ妥当性チェック");
   		}
        //ユーザーＩＤ取得と設定処理を行います。
    	//２．ログインコードを会社コード、店コードに展開します。
    	//店コードの1桁目をログインコード先頭9文字目の値とします。
    	String miseCd = logprm.substring(8, 9);
    	//店コードの2桁目をログインコード先頭3文字目の値とします。
    	miseCd += logprm3;
    	//店コードの3桁目をログインコード先頭11文字目の値とします。
    	miseCd += logprm.substring(10, 11);
    	//店コードの4桁目をログインコード先頭5文字目の値とします。
    	miseCd += logprm5;
    	//店コードの5桁目をログインコード先頭7文字目の値とします。
    	miseCd += logprm7;
    	
    	//３．DAO【店マスタ情報】検索を実行し、戻り値[店情報]を取得します。
    	//    (【店コード存在チェック】仕様番号：SP010203)
    	MstMise mstMise = getMstMiseDao().selectMise(companyCd, miseCd);
    	//４．処理３の[店情報]＝nullの場合は下記のExceptionを発生させます。(仕様番号：SP010204)
    	if(mstMise==null) {
            throw new PmosIllegalAccessException("店コード存在チェック");
    	}

    	//５．DAO【ユーザー情報】検索を実行し、戻り値List[[ユーザー情報]]を取得します。
    	//    (【ユーザーＩＤチェック】仕様番号：SP010205)
    	List listUser = getPmosLoginUIMiseUserDao().select(companyCd, miseCd);
    	//６．処理５のList[[ユーザー情報]]の件数が0件又は2件以上の場合は、
    	//    下記のExceptionを発生させます。
    	if(listUser.size()==0 || listUser.size()>1) {
            throw new GenericErrorException(
            		"ログイン", "この店舗はご利用いただけません。");
    	}
        //７．処理５のList[[ユーザー情報]]からインデックス位置0(ゼロ)番目の値を[ユーザー情報]とし取得します。
    	UIMiseUser miseUser = (UIMiseUser)listUser.get(0);
        
        //８．ユーザーパスワードポリシーを取得します。 
        CtlPasswordPolicy ctlPswdPolicy = 
        	(CtlPasswordPolicy) getBirdPasswordPolicy().get(getPswdCheckType(miseUser.getUserId()));
        
		//９．新しくDTO【ログイン情報】をインスタン化し、下記の値を設定します。
		LoginDto loginDto = new LoginDto();
    	//DTO【ログイン情報】．ユーザーＩＤへ
    	//    処理７のMstUser[ユーザー情報].ユーザーＩＤを設定します。
    	loginDto.setUserId(miseUser.getUserId());
    	//DTO【ログイン情報】．ユーザーパスワードへ
    	//    処理７のMstUser[ユーザー情報].ユーザーパスワードを設定します。
        byte[] pswdByte = miseUser.getUserPswd();
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
        //DTO【ログイン情報】.p-mosslesからの遷移判断値へtrueを設定します。(2012/02/09追加)
        loginDto.setPmossles(true);
        
        //10．継承クラスLOGIC【ログイン処理】の実行処理を行います。戻り値[ログインBIRDユーザー情報]を取得します。
        super.execute(loginDto, request);
        
        //11．セッションからキー”birdUserInfo”でCONTROL【BIRDユーザー情報】を取得します。
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");

        //12．CONTROL【BIRDユーザー情報】.p-mossles判断値をtrueに設定します。
        birdUserInfo.setPmossles(true);
    	
    	//13.セッションへ"pmosUserInfo"をキーに、
    	//   P-mossles用BIRDユーザー情報をインスタンス化し、処理３の[店情報]を格納します。
    	request.getSession().setAttribute("pmosUserInfo"
    			, new PmosUserInfo(birdUserInfo.getMstUser(), mstMise));

        //14.共通パラメータ用DTO関連の設定を行います。
        setupCommonDto(request);
        
        return viewId;
	}

	/**
     * チェック用ユーザータイプ判定
     * @param userId
     * @param userShozokuKbn
     * @return
     */
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
	public Map getBirdPasswordPolicy() {
		return birdPasswordPolicy;
	}

	/**
	 * @param birdPasswordPolicy を クラス変数birdPasswordPolicyへ設定します。
	 */
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
	 * DAO【店舗マスタ情報】取得処理
	 * @return
	 */
	public UIMiseUserDao getPmosLoginUIMiseUserDao() {
		return pmosLoginUIMiseUserDao;
	}
	/**
	 * DAO【店舗マスタ情報】設定処理
	 * @param pmosLoginMstUserDao
	 */
	public void setPmosLoginUIMiseUserDao(UIMiseUserDao miseUserDao) {
		this.pmosLoginUIMiseUserDao = miseUserDao;
	}
	/**
	 * DAO【店舗マスタ情報】取得処理
	 * @return
	 */
	public MstMiseDao getMstMiseDao() {
		return mstMiseDao;
	}
	/**
	 * DAO【店舗マスタ情報】設定処理
	 * @param mstMiseDao
	 */
	public void setMstMiseDao(MstMiseDao mstMiseDao) {
		this.mstMiseDao = mstMiseDao;
	}
	/**
	 * p-mossles遷移可能VIEWID情報取得処理
	 * @return クラス変数mapPmosViewId を戻します。
	 */
	public Map getMapPmosViewId() {
		return mapPmosViewId;
	}
	/**
	 * p-mossles遷移可能VIEWID情報設定処理
	 * @param mapPmosViewId を クラス変数mapPmosViewIdへ設定します。
	 */
	public void setMapPmosViewId(Map mapPmosViewId) {
		this.mapPmosViewId = mapPmosViewId;
	}
}
