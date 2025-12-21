package jp.co.isid.mos.bird.bizadmin.accountlist.entity;

import jp.co.isid.mos.bird.bizadmin.accountlist.code.AccountListConst;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * ご利用情報照会／変更
 * 
 * 更新日:2012/02/07 xkinu 画面上「状態」表示項目文言判別の仕様変更対応
 *  ご利用中/未使用は、使用停止フラグのみで判断していましたが、
 *  表示対象が店舗ユーザーの場合のみ、申込日も見るようになります。
 *  「ご利用中」:使用停止フラグがOFF（BR01USER.STOP_FLG<>'1'）かつ申込日が空でない（BR01USER.APPLIED_DT<>''）
 *  「未使用」使用停止フラグがON（BR01USER.STOP_FLG='1'）または申込日が空（BR01USER.APPLIED_DT=''）
 * 上記の対応に伴いSELECT項目へUSERTYPE_CDを追加しました。
 *
 */
public class UIAccountList {
    
    public static final String TABLE = "BR01USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    
    /** ユーザータイプコード(追加:2012/02/07) */
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String appliedDt_COLUMN = "APPLIED_DT";
    
    public static final String mailAdd_COLUMN = "MAIL_ADD";
    
    public static final String stopFlg_COLUMN = "STOP_FLG";
    
    public static final String maxflg1_COLUMN = "MAXFLG1";
    
    public static final String maxflg2_COLUMN = "MAXFLG2";
    
    public static final String maxflg3_COLUMN = "MAXFLG3";
    
    public static final String maxflg4_COLUMN = "MAXFLG4";
    
    public static final String maxflg5_COLUMN = "MAXFLG5";
    
    public static final String keiyakuType_COLUMN = "KEIYAKU_TYPE";
    
    public static final String keiyakuTypeNm_COLUMN = "KEIYAKU_TYPE_NM";
    
    public static final String closeDt_COLUMN = "CLOSE_DT";
    
    public static final String closeFlg_COLUMN = "CLOSE_FLG";
    
    /**
     * ユーザーID
     */
    private String userId;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * ユーザー名称（漢字）
     */
    private String userNameKj;
    
    /**
     * ユーザー名称（カナ）
     */
    private String userNameKana;
    
    /**
     * ユーザータイプコード
     * 追加日:2012/02/07
     */
    private String usertypeCd;
    
    /**
     * お申込日
     */
    private String appliedDt;
    
    /**
     * メールアドレス
     */
    private String mailAdd;
    
    /**
     * 使用停止フラグ
     */
    private String stopFlg;
    
    /**
     * オプション区分１
     */
    private String maxflg1;
    
    /**
     * オプション区分２
     */
    private String maxflg2;
    
    /**
     * オプション区分３
     */
    private String maxflg3;
    
    /**
     * オプション区分４
     */
    private String maxflg4;
    
    /**
     * オプション区分５
     */
    private String maxflg5;
    
    /**
     * 契約タイプ
     */
    private String keiyakuType;
    
    /**
     * 契約タイプ名称
     */
    private String keiyakuTypeNm;
    
    /**
     * 店舗クローズ日
     */
    private String closeDt;
    
    /**
     * 店舗ユーザーかつクローズ店の場合1
     */
    private String closeFlg;
    
    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * ユーザー名称（漢字）を取得します。
     * @return ユーザー名称（漢字）
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザー名称（漢字）を設定します。
     * @param userNameKj ユーザー名称（漢字）
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * ユーザー名称（カナ）を取得します。
     * @return ユーザー名称（カナ）
     */
    public String getUserNameKana() {
        return userNameKana;
    }
    /**
     * ユーザー名称（カナ）を設定します。
     * @param userNameKana ユーザー名称（カナ）
     */
    public void setUserNameKana(String userNameKana) {
        this.userNameKana = userNameKana;
    }
    
    /**
     * メールアドレスを取得します。
     * @return メールアドレス
     */
    public String getMailAdd() {
        return mailAdd;
    }
    /**
     * メールアドレスを設定します。
     * @param mailAdd メールアドレス
     */
    public void setMailAdd(String mailAdd) {
        this.mailAdd = mailAdd;
    }
    
    /**
     * 使用停止フラグを取得します。
     * @return 使用停止フラグ
     */
    public String getStopFlg() {
        return stopFlg;
    }
    /**
     * 使用停止フラグを設定します。
     * @param stopFlg 使用停止フラグ
     */
    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
    }
    
    /**
     * オプション区分１を取得します。
     * @return オプション区分１
     */
    public String getMaxflg1() {
        return maxflg1;
    }
    /**
     * オプション区分１を設定します。
     * @param maxflg1 オプション区分１
     */
    public void setMaxflg1(String maxflg1) {
        this.maxflg1 = maxflg1;
    }
    
    /**
     * オプション区分２を取得します。
     * @return オプション区分２
     */
    public String getMaxflg2() {
        return maxflg2;
    }
    /**
     * オプション区分２を設定します。
     * @param maxflg2 オプション区分２
     */
    public void setMaxflg2(String maxflg2) {
        this.maxflg2 = maxflg2;
    }
    
    /**
     * オプション区分３を取得します。
     * @return オプション区分３
     */
    public String getMaxflg3() {
        return maxflg3;
    }
    /**
     * オプション区分３を設定します。
     * @param maxflg3 オプション区分３
     */
    public void setMaxflg3(String maxflg3) {
        this.maxflg3 = maxflg3;
    }
    
    /**
     * オプション区分４を取得します。
     * @return オプション区分４
     */
    public String getMaxflg4() {
        return maxflg4;
    }
    /**
     * オプション区分４を設定します。
     * @param maxflg4 オプション区分４
     */
    public void setMaxflg4(String maxflg4) {
        this.maxflg4 = maxflg4;
    }
    
    /**
     * オプション区分５を取得します。
     * @return オプション区分５
     */
    public String getMaxflg5() {
        return maxflg5;
    }
    /**
     * オプション区分５を設定します。
     * @param maxflg5 オプション区分５
     */
    public void setMaxflg5(String maxflg5) {
        this.maxflg5 = maxflg5;
    }
    
    /**
     * 契約タイプを取得します。
     * @return 契約タイプ
     */
    public String getKeiyakuType() {
        return keiyakuType;
    }
    /**
     * 契約タイプを設定します。
     * @param keiyakuType 契約タイプ
     */
    public void setKeiyakuType(String keiyakuType) {
        this.keiyakuType = keiyakuType;
    }
    
    /**
     * 契約タイプ名称を取得します。
     * @return 契約タイプ名称
     */
    public String getKeiyakuTypeNm() {
        return keiyakuTypeNm;
    }
    /**
     * 契約タイプ名称を設定します。
     * @param keiyakuTypeNm 契約タイプ名称
     */
    public void setKeiyakuTypeNm(String keiyakuTypeNm) {
        this.keiyakuTypeNm = keiyakuTypeNm;
    }
    public String getAppliedDt() {
        return appliedDt;
    }
    public void setAppliedDt(String appliedDt) {
        this.appliedDt = appliedDt;
    }
    public String getCloseDt() {
        return closeDt;
    }
    public void setCloseDt(String closeDt) {
        this.closeDt = closeDt;
    }
    public String getCloseFlg() {
        return closeFlg;
    }
    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg;
    }
	/**
	 * ユーザータイプコード取得処理
     * 追加日:2012/02/07
	 * @return クラス変数usertypeCd を戻します。
	 */
	public String getUsertypeCd() {
		return usertypeCd;
	}
	/**
	 * ユーザータイプコード設定処理
     * 追加日:2012/02/07
	 * @param usertypeCd を クラス変数usertypeCdへ設定します。
	 */
	public void setUsertypeCd(String usertypeCd) {
		this.usertypeCd = usertypeCd;
	}
	/** 
	 * 状態値取得処理
	 * @return
	 */
    public String getJotaiKbn() {
    	String jotaiFlg = getStopFlg();
    	if(UserType.TENPO.equals(getUsertypeCd())) {
    		/**
    		 *  表示対象が店舗ユーザーの場合のみ、申込日も見るようになります。
    		 *  
    		 */
    		if (!AccountListConst.STOP_FLG_ON.equals(getStopFlg())
    				&& !CommonUtil.isNull(getAppliedDt())) {
    			//「ご利用中」
    			// 使用停止フラグがOFF（BR01USER.STOP_FLG<>'1'）
    			// かつ申込日が空でない（BR01USER.APPLIED_DT<>''）
    		}
    		else {
    			//「未使用」
    			// 使用停止フラグがON（BR01USER.STOP_FLG='1'）
    			// または、申込日が空（BR01USER.APPLIED_DT=''）
    			jotaiFlg = AccountListConst.STOP_FLG_ON;
    		}
    		
    	}
    	return jotaiFlg;
    }
    /**
     * 状態値設定処理
     * 注)直接値の設定は出来ません。getJotaiKbn()を参照。
     * @param kbn
     */
    public void setJotaiKbn(String kbn) {
    	//何もしない
    }
}
