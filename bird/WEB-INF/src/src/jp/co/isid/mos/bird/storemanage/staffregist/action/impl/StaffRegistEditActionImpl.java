
/*
 * 作成日: 2006/04/07
 * 更新日: 2007/08/31 SVユーザーをオーナーユーザーと同じ編集権限で使えるようにする
 * 　　　　　　　　　　　BR11GMRL(汎用画面別ロール制御)を使用して制御する
 * 　　　　　　　　　　　　設定区分 01:MHA　02:SV　03:オーナー
 */
package jp.co.isid.mos.bird.storemanage.staffregist.action.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuVerifier;
import jp.co.isid.mos.bird.storemanage.staffregist.action.StaffRegistEditAction;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistDto;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistIFDto;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CheckDuplicateNameLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CheckStaffStateLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CreateStaffInfoLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.GetMiseListLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.GetStaffInfoLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.StaffListLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.StaffRegistLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.impl.CheckEntryLogicImpl;
import jp.co.isid.mos.bird.storemanage.staffregist.util.StaffRegistUtil;

/**
 * スタッフ新規異動登録アクション
 * @author xylee
 *
 * 更新日 2009/09/02 xkinu 一括ダウンロード機能追加と同姓同名確認処理の追加対応
 */

public class StaffRegistEditActionImpl implements StaffRegistEditAction {


    /* アクションID */
    public static final String initialize_ACTION_ID = "BSM004A11";
    public static final String regist_ACTION_ID =   "BSM004A12";
    public static final String back_ACTION_ID =         "BSM004A13";

    private static final String VIEWID_SELECT = "BSM004V01";

    private static final String VIEW_ID = "BSM004";
    private static final String BR11_BUNRUI_LEAVEDT_RETURNDT = "01";
    private static final String BR11_BUNRUI_NAME_EDIT = "02";
    private static final String TAISHOKU = "2";

    private BirdDateInfo birdDateInfo;
    private BirdUserInfo birdUserInfo;

    // DTO
    private StaffRegistDto staffRegistDto;
    private StaffRegistIFDto staffRegistIFDto;
    private PullDownMenuDto pullDownMenuDto;

    // Logic
    private StaffListLogic staffListLogic;
    private GetMiseListLogic getMiseListLogic;
    private CreateStaffInfoLogic createStaffInfoLogic;
    private GetStaffInfoLogic getStaffInfoLogic;
    private StaffRegistLogic staffRegistLogic;
    private CheckStaffStateLogic checkStaffStateLogic;
    private GetGamenRoleLogic gamenRoleLogic;
//  add start 2007/02/19 退職者選択の際にＭＬ試験等登録していないかチェック対応
    private CheckEntryLogicImpl checkEntryLogicImpl;
//  add end
    //add 2007/08/31 氏名、フリガナ、性別、生年月日を編集できる設定区分のリスト
    private List listSpecificItemEditableKbn;
    //LOGIC【同姓同名チェックロジック】
    private CheckDuplicateNameLogic staffRegistCheckDuplicateNameLogic;
    /**
     * 初期処理
     *
     * 更新日 2009/09/02 xkinu 一括ダウンロード機能追加と同姓同名確認処理の追加対応
     * @return 画面遷移情報
     */

    public String initialize() {

        // 他画面からの遷移
        if (getStaffRegistIFDto().isInitialFlag()) {
            getStaffRegistIFDto().setOtherScreenFlg(true);
            MstStaff mstStaff = null;
            String companyCd = null;
            String onerCd = null;
            String menuId = null;
            String subMenuId = null;

            HttpServletRequest request = (HttpServletRequest) getS2Container().getComponent("request");
            Enumeration enu = request.getParameterNames();

            while (enu.hasMoreElements()) {
                String strElement = (String) enu.nextElement();
                if (strElement.indexOf(":initMenuId") != -1) {
                    menuId = request.getParameter(strElement) == null ? "" : request.getParameter(strElement);
                    getMenuAction().setInitMenuId(menuId);
                }
                else if (strElement.indexOf(":initSubMenuId") != -1) {
                    subMenuId = request.getParameter(strElement) == null ? "" : request.getParameter(strElement);
                    getMenuAction().setInitSubMenuId(subMenuId);
                }
                if (menuId != null && subMenuId != null) {
                    break;
                }
            }



            // 新規
            if (getStaffRegistIFDto().getEditMode() == 1) {
                // キー項目取得
                companyCd = getStaffRegistIFDto().getCompanyCd();
                onerCd = getStaffRegistIFDto().getOnerCd();
                // 新規スタッフ情報生成
                mstStaff = getCreateStaffInfoLogic().execute(companyCd, onerCd);

            // 更新
            } else if (getStaffRegistIFDto().getEditMode() == 2) {
                // スタッフID取得
                String staffId = getStaffRegistIFDto().getStaffId();
                // スタッフ情報取得
                mstStaff = getGetStaffInfoLogic().execute(staffId);
                // キー項目取得
                companyCd = mstStaff.getCompanyCd();
                onerCd = mstStaff.getOnerCd();
            } else {
                throw new FtlSystemException("加盟店スタッフ新規異動登録");
            }
            MstStaff mstStaffTmp = new MstStaff();
            mstStaffTmp.setStaffFNameKj(mstStaff.getStaffFNameKj());
            mstStaffTmp.setStaffLNameKj(mstStaff.getStaffLNameKj());
            mstStaffTmp.setStaffFNameKna(mstStaff.getStaffFNameKna());
            mstStaffTmp.setStaffLNameKna(mstStaff.getStaffLNameKna());
            mstStaffTmp.setBirthday(mstStaff.getBirthday());
            mstStaffTmp.setSex(mstStaff.getSex());
            getStaffRegistDto().setMstStaffTmp(mstStaffTmp);

            // スタッフ情報設定
            getStaffRegistDto().setMstStaff(mstStaff);

            // 店舗リスト取得
            List mstStaffMiseList = getGetMiseListLogic().execute(
                    companyCd, onerCd, getBirdDateInfo().getSysDate());
            getStaffRegistDto().setMstStaffMiseList(mstStaffMiseList);

            getStaffRegistIFDto().setInitialFlag(false);

            //--2007/01/29 休職日復職日編集可能ユーザか否か
            boolean flg = isLeaveReturnUser(getBirdUserInfo().getUserID());
            getStaffRegistDto().setLeaveReturnFlg(flg);
            /** 同姓同名チェック要に設定 */
            getStaffRegistDto().setDuplicateNameAlert(StaffRegistUtil.ALERT_OFF);
            getStaffRegistDto().setDuplicateName(false);
        }

        // 氏名、フリガナ、性別、生年月日
        getStaffRegistDto().setSpecificItemEdit(true);
        if (!isSpecificItemEdit()) {
            if(!getStaffRegistDto().getMstStaff().isInsertFlag()){
                // 編集可能チェック
                if(!getCheckStaffStateLogic().execute(getStaffRegistDto().getMstStaff().getStaffId())) {
                    getStaffRegistDto().setSpecificItemEdit(false);
                }
            }
        }
        // スタッフ活動プルダウン生成
        List situationKbnList = new ArrayList();
        situationKbnList.add(new SelectItem("0","活動中"));
        if(getStaffRegistDto().isLeaveReturnFlg()) {
            situationKbnList.add(new SelectItem("1","休職中"));
        }
        situationKbnList.add(new SelectItem("2","退職"));
        getStaffRegistDto().setSituationKbnList(situationKbnList);


        // 自画面へ遷移
        return null;
    }

    /**
     * 休職日・復職日を表示するユーザか否かを判別する
     * @return true･･･表示可能なユーザ、false･･･表示可能なユーザでない
     */
    private boolean isLeaveReturnUser(String userId) {

        //引数用DTO作成
        GamenRoleDto dto = new GamenRoleDto();
        dto.setUserId(userId);
        dto.setGamenId(VIEW_ID);
        dto.setBunruiCd(BR11_BUNRUI_LEAVEDT_RETURNDT);

        try{
            getGamenRoleLogic().excute(dto);
        }catch(NotExistException ne){
            return false;
        }

        return true;
    }

    /**
	 * 登録
	 *
	 * 更新日 2009/09/02 xkinu 一括ダウンロード機能追加と同姓同名確認処理の追加対応
	 * @return 画面遷移情報
	 */
	public String regist() {
	    // 入力値チェック
	    validate();

	    MstStaff mstStaff = getStaffRegistDto().getMstStaff();
	    // 生年月日 をセット
		String birthday = concatYearMonthDay(mstStaff.getBirthdayYear(), mstStaff.getBirthdayMonth(),
				mstStaff.getBirthdayDay());
	    mstStaff.setBirthday(birthday);
	    // 異動日 をセット
		String moveDt = concatYearMonthDay(mstStaff.getMoveDtYear(), mstStaff.getMoveDtMonth(),
				mstStaff.getMoveDtDay());
	    mstStaff.setMoveDt((moveDt == null) ? "" : moveDt);
	    // 退職日 をセット
		String retireDt = concatYearMonthDay(mstStaff.getRetireDtYear(), mstStaff.getRetireDtMonth(),
				mstStaff.getRetireDtDay());
	    mstStaff.setRetireDt((retireDt == null) ? "" : retireDt);
	    // 休職日 をセット
		String leaveDt = concatYearMonthDay(mstStaff.getLeaveDtYear(), mstStaff.getLeaveDtMonth(),
				mstStaff.getLeaveDtDay());
	    mstStaff.setLeaveDt((leaveDt == null) ? "" : leaveDt);
	    // 復職日 をセット
		String returnDt = concatYearMonthDay(mstStaff.getReturnDtYear(), mstStaff.getReturnDtMonth(),
				mstStaff.getReturnDtDay());
	    mstStaff.setReturnDt((returnDt == null) ? "" : returnDt);


	    // 退職日が入力されている場合、活動状況を退職に設定
	    if (retireDt != null && retireDt.length() > 0) {
	        mstStaff.setSituationKbn("2");
	    }
	    // 休職日がシステム日付以前で入力されていて、復職日と退職日が空欄の場合、活動状況を休職中に設定
	    String sysdate = getBirdDateInfo().getSysDate();
	    if ((leaveDt != null && leaveDt.length() > 0 && leaveDt.compareTo(sysdate) <= 0) &&
	            (returnDt == null || returnDt.equals("")) &&
	            (retireDt == null || retireDt.equals(""))) {
	        mstStaff.setSituationKbn("1");
	    }

	    //汎用画面別ロール制御で編集可かを判断
	    if (!getStaffRegistDto().isSpecificItemEdit()) {
	        if (chkStaffBaseInfoChange()) {
	            throw new GenericMessageException("対象スタッフは研修受講済み、又は、マスターライセンスを受験されているため氏名、フリガナ、性別、生年月日の修正はできません。");
	        }
	    }
		//直前のリクエスト時と氏名が異なる場合は同姓同名チェックを行います。
	    if (chkStaffNameChange()) {
	    	//LOGIC【同姓同名存在チェック】
	    	boolean existDuplicate = getStaffRegistCheckDuplicateNameLogic().execute(getStaffRegistDto());
	    	getStaffRegistDto().setDuplicateName(existDuplicate);
	    	getStaffRegistDto().setDuplicateNameAlert(StaffRegistUtil.ALERT_ON);
	    	//直前の変更前情報に変更後名称を上書きします。
	    	getStaffRegistDto().getMstStaffTmp().setStaffFNameKj(mstStaff.getStaffFNameKj());
	    	getStaffRegistDto().getMstStaffTmp().setStaffLNameKj(mstStaff.getStaffLNameKj());
	    	getStaffRegistDto().getMstStaffTmp().setStaffFNameKna(mstStaff.getStaffFNameKna());
	    	getStaffRegistDto().getMstStaffTmp().setStaffLNameKna(mstStaff.getStaffLNameKna());
	    }
	    //同姓同名が存在していて、承認していない場合は現行画面に戻り確認アラートを出力します。
		if(getStaffRegistDto().isDuplicateName()
				&& getStaffRegistDto().getDuplicateNameAlert().equals(StaffRegistUtil.ALERT_ON)) {
			//同姓同名のデータが存在するため、現行画面へ戻りアラートメッセージを表示します。
			return null;
		}

	    // 登録
	    getStaffRegistLogic().execute(getStaffRegistDto(), getBirdUserInfo().getUserID());

	    String navigationCase = null;
	    // 他画面からの遷移
	    if (getStaffRegistIFDto().getEditMode() != 0) {
	        // 指定された画面へ遷移
	        navigationCase = getStaffRegistIFDto().getNavigationCase();
	        // 編集モードクリア
	        getStaffRegistIFDto().setEditMode(0);
	        // スタッフID設定
	        getStaffRegistIFDto().setStaffId(staffRegistDto.getMstStaff().getStaffId());
	        // アクションフラグ = "登録"
	        getStaffRegistIFDto().setActionFlg(true);
	    } else {
	        // 検索結果クリアの為フラグON
	        getPullDownMenuDto().setClearFlg(true);
	        // 通常モードでは検索画面へ遷移
	        navigationCase = VIEWID_SELECT;
	    }
	    getStaffRegistDto().setDuplicateNameAlert(StaffRegistUtil.ALERT_OFF);
	    getStaffRegistDto().setDuplicateName(false);

	    return navigationCase;
	}

    /**
     * 戻る
     *
     * @return 画面遷移情報
     */
    public String back() {

        String navigationCase = null;
        // 他画面からの遷移
        if (getStaffRegistIFDto().getEditMode() != 0) {
            // 指定された画面へ遷移
            navigationCase = getStaffRegistIFDto().getNavigationCase();
            // 編集モードクリア
            getStaffRegistIFDto().setEditMode(0);
            // アクションフラグ = "戻る"
            getStaffRegistIFDto().setActionFlg(false);
        } else {
            // 通常モードでは条件画面へ遷移
            navigationCase = VIEWID_SELECT;
        }

        return navigationCase;
    }
    /**
     * 事前確認処理
     *
     */
    private void validate() {

        // Verifier
        ZenkakuVerifier zenkakuVerifier = new ZenkakuVerifier();
        ZenkakuKatakanaVerifier zenkakuKatakanaVerifier = new ZenkakuKatakanaVerifier();
        CodeVerifier codeVerifier = new CodeVerifier(5, true);
        DateVerifier dateVerifier = new DateVerifier();

        boolean isSpecificItemEdit = getStaffRegistDto().isSpecificItemEdit();
        MstStaff mstStaff = getStaffRegistDto().getMstStaff();
        // 氏名(氏)
        String staffLNameKj = mstStaff.getStaffLNameKj();
//---2007/09/18 氏名、フリガナ、性別、生年月日が編集不可の場合は、必須チェックをしないように変更
        if ((staffLNameKj == null || staffLNameKj.trim().length() == 0) && isSpecificItemEdit) {
            throw new NotNullException("氏名(氏)", "staffLNameKj", null);
        }
        if (!zenkakuVerifier.validate(staffLNameKj.trim())) {
        	InvalidInputException invalidInputEx = new InvalidInputException("氏名(氏)"
        			, "入力された”"+staffLNameKj.trim()+"”の中に登録できない文字が含まれています。");
        	invalidInputEx.setHtmlElementIndex(0);
        	invalidInputEx.setHtmlElementName("staffLNameKj");
        	throw invalidInputEx;
        }
        if (staffLNameKj.trim().getBytes().length > 20) {
            throw new NotRelevantException("氏名(氏)", "全角10文字以内", "staffLNameKj", null);
        }

        // 氏名(名)
        String staffFNameKj = mstStaff.getStaffFNameKj();
        if ((staffFNameKj == null || staffFNameKj.trim().length() == 0) && isSpecificItemEdit) {
            throw new NotNullException("氏名(名)", "staffFNameKj", null);
        }
        if (!zenkakuVerifier.validate(staffFNameKj.trim())) {
        	InvalidInputException invalidInputEx = new InvalidInputException("氏名(名)"
        			, "入力された”"+staffFNameKj.trim()+"”の中に登録できない文字が含まれています。");
        	invalidInputEx.setHtmlElementIndex(0);
        	invalidInputEx.setHtmlElementName("staffFNameKj");
        	throw invalidInputEx;
        }
        if (staffFNameKj.trim().getBytes().length > 20) {
            throw new NotRelevantException("氏名(名)", "全角10文字以内", "staffFNameKj", null);
        }

        // フリガナ(氏)
        String staffLNameKna = mstStaff.getStaffLNameKna();
        if ((staffLNameKna == null || staffLNameKna.trim().length() == 0) && isSpecificItemEdit) {
            throw new NotNullException("フリガナ(氏)", "staffLNameKna", null);
        }
        if (!zenkakuKatakanaVerifier.validate(staffLNameKna)) {
            throw new InvalidInputException("フリガナ(氏)", "staffLNameKna", null);
        }
        if (staffLNameKna.trim().getBytes().length > 20) {
            throw new NotRelevantException("フリガナ(氏)", "全角10文字以内", "staffLNameKna", null);
        }

        // フリガナ(名)
        String staffFNameKna = mstStaff.getStaffFNameKna();
        if ((staffFNameKna == null || staffFNameKna.trim().length() == 0) && isSpecificItemEdit) {
            throw new NotNullException("フリガナ(名)", "staffFNameKna", null);
        }
        if (!zenkakuKatakanaVerifier.validate(staffFNameKna)) {
            throw new InvalidInputException("フリガナ(名)", "staffFNameKna", null);
        }
        if (staffFNameKna.trim().getBytes().length > 20) {
            throw new NotRelevantException("フリガナ(名)", "全角10文字以内", "staffFNameKna", null);
        }
        //スタッフ氏名の値からスペース(全角スペースも含む）を除外した値を再設定します。
        mstStaff.setStaffLNameKj(staffLNameKj.replace('　', ' ').trim());
        mstStaff.setStaffFNameKj(staffFNameKj.replace('　', ' ').trim());
        mstStaff.setStaffLNameKna(staffLNameKna.replace('　', ' ').trim());
        mstStaff.setStaffFNameKna(staffFNameKna.replace('　', ' ').trim());


        // 店コード
        String miseCd1 = mstStaff.getMiseCd1();
        if (miseCd1 == null || miseCd1.trim().length() == 0) {
            throw new NotNullException("店舗", "miseCd1", null);
        }
        if (!codeVerifier.validate(miseCd1)) {
            throw new InvalidInputException("店舗", "miseCd1", null);
        }

        // 活動状況
        String situationKbn = mstStaff.getSituationKbn();
        if (situationKbn == null || situationKbn.trim().length() != 1) {
            throw new NotNullException("活動状況", "situationKbn", null);
        }

        // 性別
        String sex = mstStaff.getSex();
        if ((sex == null || sex.trim().length() != 1) && isSpecificItemEdit) {
            throw new NotNullException("性別", "sex", null);
        }

        NumericVerifier numericVerifier = new NumericVerifier();

        // 生年月日
        if (isSpecificItemEdit) {
            String birthdayYear = mstStaff.getBirthdayYear();
            if (birthdayYear == null || birthdayYear.trim().length() == 0) {
                throw new NotNullException("生年月日", "birthdayYear", null);
            }
	        if (birthdayYear.length() != 4 || !numericVerifier.validate(birthdayYear) ){
	            throw new NotRelevantException("生年月日：年", "半角数値４文字", "birthdayYear", null);
	        }
            String birthdayMonth = mstStaff.getBirthdayMonth();
            if (birthdayMonth == null || birthdayMonth.trim().length() == 0) {
                throw new NotNullException("生年月日", "birthdayMonth", null);
            }
            String birthdayDay = mstStaff.getBirthdayDay();
            if (birthdayDay == null || birthdayDay.trim().length() == 0) {
                throw new NotNullException("生年月日", "birthdayDay", null);
            }
            String birthday =  concatYearMonthDay(birthdayYear, birthdayMonth, birthdayDay);
            if (birthday == null || !dateVerifier.validate(birthday)) {
                throw new InvalidInputException("生年月日", "birthdayYear", null);
            }
            // 生年月日 < システム日付
            String sysdate = getBirdDateInfo().getSysDate();
            if(birthday != null && birthday.trim().length() > 0 &&
                    (birthday.compareTo(sysdate) >= 0)) {
                throw new GenericMessageException("生年月日は過去日でなければなりません。", "birthdayYear", null);
            }
        }

        // 異動日チェック
        String moveDtYear = mstStaff.getMoveDtYear();
        String moveDtMonth = mstStaff.getMoveDtMonth();
        String moveDtDay = mstStaff.getMoveDtDay();
        // 1フィールドでも入力があればチェック対象とする
        if ((moveDtYear != null && moveDtYear.trim().length() > 0)
                || (moveDtMonth != null && moveDtMonth.trim().length() > 0)
                || (moveDtDay != null && moveDtDay.trim().length() > 0)) {
            if (moveDtYear == null || moveDtYear.trim().length() == 0) {
                throw new InvalidInputException("異動日", "moveDtYear", null);
            }
	        if (moveDtYear.length() != 4 || !numericVerifier.validate(moveDtYear) ){
	            throw new NotRelevantException("異動日：年", "半角数値４文字", "moveDtYear", null);
	        }
            if (moveDtMonth == null || moveDtMonth.trim().length() == 0) {
                throw new InvalidInputException("異動日", "moveDtMonth", null);
            }
            if (moveDtDay == null || moveDtDay.trim().length() == 0) {
                throw new InvalidInputException("異動日", "moveDtDay", null);
            }
            String moveDt = concatYearMonthDay(moveDtYear , moveDtMonth , moveDtDay);
            if (moveDt == null || !dateVerifier.validate(moveDt)) {
                throw new InvalidInputException("異動日", "moveDtYear", null);
            }
        }

        // 退職日チェック
        String retireDtYear = mstStaff.getRetireDtYear();
        String retireDtMonth = mstStaff.getRetireDtMonth();
        String retireDtDay = mstStaff.getRetireDtDay();
        String retireDt    = null;
//add start 2007/02/19 退職者選択の際にＭＬ試験等登録していないかチェック対応
        if(mstStaff.getSituationKbn().equals(TAISHOKU)){
            getCheckEntryLogicImpl().execute(getBirdDateInfo().getSysDate(),mstStaff.getStaffId());
        }
//add end
//add start 2007/02/20 MLPh4 他画面からの遷移処理
        // 他画面からの遷移の際の退職者チェック
        if (getStaffRegistIFDto().isOtherScreenFlg()) {
            if(situationKbn.equals("2")){
                throw new CannotExecuteException("マスターライセンスエントリー者の活動状況を「退職」にすることは");
            }
            getStaffRegistIFDto().setOtherScreenFlg(false);
        }
//add end

        // 1フィールドでも入力があればチェック対象とする
        if ((retireDtYear != null && retireDtYear.trim().length() > 0)
                || (retireDtMonth != null && retireDtMonth.trim().length() > 0)
                || (retireDtDay != null && retireDtDay.trim().length() > 0)) {
            if (retireDtYear == null || retireDtYear.trim().length() == 0) {
                throw new InvalidInputException("退職日", "retireDtYear", null);
            }
	        if (retireDtYear.length() != 4 || !numericVerifier.validate(retireDtYear) ){
	            throw new NotRelevantException("退職日：年", "半角数値４文字", "retireDtYear", null);
	        }
            if (retireDtMonth == null || retireDtMonth.trim().length() == 0) {
                throw new InvalidInputException("退職日", "retireDtMonth", null);
            }
            if (retireDtDay == null || retireDtDay.trim().length() == 0) {
                throw new InvalidInputException("退職日", "retireDtDay", null);
            }
            retireDt = concatYearMonthDay(retireDtYear, retireDtMonth, retireDtDay);
            if (retireDt == null || !dateVerifier.validate(retireDt)) {
                throw new InvalidInputException("退職日", "retireDtYear", null);
            }
        }

        // 退職日 整合性チェック
        if(retireDt != null && retireDt.length() > 0 &&
                !situationKbn.equals("2")) {
            throw new GenericMessageException("活動状況と退職日の設定が一致していません。", "situationKbn", null);
        }

        // 退職日 整合性チェック
        if(situationKbn.equals("2") &&
                 (retireDt == null || retireDt.length() <= 0)) {
            throw new GenericMessageException("活動状況と退職日の設定が一致していません。", "retireDtYear", null);
        }

        // 備考
        String note = mstStaff.getNote();
        if (note != null && note.getBytes().length > 40) {
            throw new InvalidInputException("備考", "note", null);
        }

        //休職日・復職日の表示ユーザの場合のみ
        if(getStaffRegistDto().isLeaveReturnFlg()){

            // 休職日チェック
            String leaveDtYear  = mstStaff.getLeaveDtYear();
            String leaveDtMonth = mstStaff.getLeaveDtMonth();
            String leaveDtDay   = mstStaff.getLeaveDtDay();
            String leaveDt      = "";
            // 1フィールドでも入力があればチェック対象とする
            if ((leaveDtYear != null && leaveDtYear.trim().length() > 0)
                    || (leaveDtMonth != null && leaveDtMonth.trim().length() > 0)
                    || (leaveDtDay   != null && leaveDtDay.trim().length() > 0)) {
                if (leaveDtYear == null || leaveDtYear.trim().length() == 0) {
                    throw new InvalidInputException("休職日", "leaveDtYear", null);
                }
    	        if (leaveDtYear.length() != 4 || !numericVerifier.validate(leaveDtYear) ){
    	            throw new NotRelevantException("休職日：年", "半角数値４文字", "leaveDtYear", null);
    	        }
                if (leaveDtMonth == null || leaveDtMonth.trim().length() == 0) {
                    throw new InvalidInputException("休職日", "leaveDtMonth", null);
                }
                if (leaveDtDay == null || leaveDtDay.trim().length() == 0) {
                    throw new InvalidInputException("休職日", "leaveDtDay", null);
                }
                leaveDt = concatYearMonthDay(leaveDtYear, leaveDtMonth, leaveDtDay);
                if (leaveDt == null || !dateVerifier.validate(leaveDt)) {
                    throw new InvalidInputException("休職日", "leaveDtYear", null);
                }
            }

            // 復職日チェック
            String returnDtYear  = mstStaff.getReturnDtYear();
            String returnDtMonth = mstStaff.getReturnDtMonth();
            String returnDtDay   = mstStaff.getReturnDtDay();
            String returnDt      = "";
            // 1フィールドでも入力があればチェック対象とする
            if ((returnDtYear != null && returnDtYear.trim().length() > 0)
                    || (returnDtMonth != null && returnDtMonth.trim().length() > 0)
                    || (returnDtDay   != null && returnDtDay.trim().length() > 0)) {
                if (returnDtYear == null || returnDtYear.trim().length() == 0) {
                    throw new InvalidInputException("復職日", "returnDtYear", null);
                }
    	        if (returnDtYear.length() != 4 || !numericVerifier.validate(returnDtYear) ){
    	            throw new NotRelevantException("復職日：年", "半角数値４文字", "returnDtYear", null);
    	        }
                if (returnDtMonth == null || returnDtMonth.trim().length() == 0) {
                    throw new InvalidInputException("復職日", "returnDtMonth", null);
                }
                if (returnDtDay == null || returnDtDay.trim().length() == 0) {
                    throw new InvalidInputException("復職日", "returnDtDay", null);
                }
                returnDt = concatYearMonthDay(returnDtYear, returnDtMonth, returnDtDay);
                if (returnDt == null || !dateVerifier.validate(returnDt)) {
                    throw new InvalidInputException("復職日", "returnDtYear", null);
                }
            }

            // 休職日＜退職日チェック
            if((leaveDt != null && leaveDt.length() > 0 ) &&
                    (retireDt != null && retireDt.length() > 0) &&
                    (leaveDt.compareTo(retireDt) >= 0)){
                throw new ConstraintsViolationException("休職日 < 退職日で", "retireDtYear", null);
            }

            // 休職日＜復職日チェック
            if((leaveDt != null && leaveDt.length() > 0 ) &&
                    (returnDt != null && returnDt.length() > 0) &&
                    (leaveDt.compareTo(returnDt) >= 0)){
                throw new ConstraintsViolationException("休職日 < 復職日で", "returnDtYear", null);
            }

            // 休職日 整合性チェック
            if((leaveDt != null && leaveDt.length() > 0 ) &&
                    ((returnDt == null || returnDt.length() <= 0) &&
                     (retireDt == null || retireDt.length() <= 0)) &&
                     !situationKbn.equals("1")) {
                throw new GenericMessageException("活動状況と休職日・復職日の設定が一致していません。", "situationKbn", null);
            }

            // 休職中 整合性チェック
            if(situationKbn.equals("1") &&
                    ((returnDt == null || returnDt.length() <= 0) &&
                     (retireDt == null || retireDt.length() <= 0) &&
                     (leaveDt  == null || leaveDt.length()  <= 0))) {
                throw new GenericMessageException("活動状況と休職日・復職日の設定が一致していません。", "leaveDtYear", null);
            }
        }

    }


    /**
     * スタッフ氏名(漢字)変更確認処理
     *
     * 作成日:2009/08/31
     * スタッフ情報の氏名が変更されているかをチェックする
     * @return true：変更されている　false：変更されていない
     */
    private boolean chkStaffNameChange() {

        MstStaff mstStaff2 = getStaffRegistDto().getMstStaffTmp();
        //編集対象スタッフ情報
        MstStaff mstStaff1 = getStaffRegistDto().getMstStaff();

        // 氏名が変更されている
        if (!isEquals(mstStaff1.getStaffFNameKj(), mstStaff2.getStaffFNameKj())) {
            return true;
        }
        // 氏名が変更されている
        if (!isEquals(mstStaff1.getStaffLNameKj(), mstStaff2.getStaffLNameKj())) {
            return true;
        }
        // フリガナが変更されている
        if (!isEquals(mstStaff1.getStaffFNameKna(), mstStaff2.getStaffFNameKna())) {
            return true;
        }
        // フリガナが変更されている
        if (!isEquals(mstStaff1.getStaffLNameKna(), mstStaff2.getStaffLNameKna())) {
            return true;
        }
        return false;
    }
    /**
     * スタッフ情報の氏名、フリガナ、性別、生年月日、が変更されているかをチェックする
     *
     * 更新日 2009/09/02 xkinu 同姓同名確認処理の追加対応
     * @return true：変更されている　false：変更されていない
     */
    private boolean chkStaffBaseInfoChange() {

        //編集対象スタッフ情報
        MstStaff mstStaff1 = getStaffRegistDto().getMstStaff();
        MstStaff mstStaff2 = getStaffRegistDto().getMstStaffTmp();
        if(chkStaffNameChange()) {
        	return true;
        }
        // 性別が変更されている
        //if(!mstStaff1.getSex().equals(mstStaff2.getSex())) {
        if (!isEquals(mstStaff1.getSex(), mstStaff2.getSex())) {
            return true;
        }
        // 生年月日
        //if(!mstStaff1.getBirthday().equals(mstStaff2.getBirthday())) {
        if (!isEquals(mstStaff1.getBirthday(), mstStaff2.getBirthday())) {
            return true;
        }

        return false;
    }

    /**
     * パラメータの２つの値が同じか判別
     *   ※Nullと空文字は同じとみなす1
     * @param value1
     * @param value2
     * @return boolean  true:一致
     */
    private boolean isEquals(String value1, String value2) {
        String val1 = value1 == null ? "" : value1;
        String val2 = value2 == null ? "" : value2;

        return val1.equals(val2);
    }
    /**
     * 氏名、フリガナ、性別、生年月日を編集できるユーザーかチェック
     * @return boolean true 編集可
     */
    private boolean isSpecificItemEdit() {
        boolean isEdit = false;

        GamenRoleDto gamenRoleDto = new GamenRoleDto();
        gamenRoleDto.setUserId(getBirdUserInfo().getUserID());
        gamenRoleDto.setGamenId(VIEW_ID);
        gamenRoleDto.setBunruiCd(BR11_BUNRUI_NAME_EDIT);

        List list = getGamenRoleLogic().getGamenRole(gamenRoleDto);

        for (Iterator ite = getListSpecificItemEditableKbn().iterator(); ite.hasNext();) {
            String editableKbn = (String) ite.next();
            for (Iterator ite2 = list.iterator(); ite2.hasNext();) {
                CtlGamenRole ctlGamenRole = (CtlGamenRole) ite2.next();
                String seteiKbn = ctlGamenRole.getSeteiKbn();
                if (seteiKbn != null && seteiKbn.equals(editableKbn)) {
                    isEdit = true;
                    break;
                }
            }
            if (isEdit) {
                break;
            }
        }
        return isEdit;
    }


    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public StaffRegistDto getStaffRegistDto() {
        return staffRegistDto;
    }
    public void setStaffRegistDto(StaffRegistDto staffRegistDto) {
        this.staffRegistDto = staffRegistDto;
    }
    public StaffRegistIFDto getStaffRegistIFDto() {
        return staffRegistIFDto;
    }
    public void setStaffRegistIFDto(StaffRegistIFDto staffRegistIFDto) {
        this.staffRegistIFDto = staffRegistIFDto;
    }
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public StaffListLogic getStaffListLogic() {
        return staffListLogic;
    }
    public void setStaffListLogic(StaffListLogic staffListLogic) {
        this.staffListLogic = staffListLogic;
    }
    public GetMiseListLogic getGetMiseListLogic() {
        return getMiseListLogic;
    }
    public void setGetMiseListLogic(GetMiseListLogic getMiseListLogic) {
        this.getMiseListLogic = getMiseListLogic;
    }
    public CreateStaffInfoLogic getCreateStaffInfoLogic() {
        return createStaffInfoLogic;
    }
    public void setCreateStaffInfoLogic(
            CreateStaffInfoLogic createStaffInfoLogic) {
        this.createStaffInfoLogic = createStaffInfoLogic;
    }
    public GetStaffInfoLogic getGetStaffInfoLogic() {
        return getStaffInfoLogic;
    }
    public void setGetStaffInfoLogic(GetStaffInfoLogic getStaffInfoLogic) {
        this.getStaffInfoLogic = getStaffInfoLogic;
    }
    public StaffRegistLogic getStaffRegistLogic() {
        return staffRegistLogic;
    }
    public void setStaffRegistLogic(StaffRegistLogic staffRegistLogic) {
        this.staffRegistLogic = staffRegistLogic;
    }
    public CheckStaffStateLogic getCheckStaffStateLogic() {
        return checkStaffStateLogic;
    }
    public void setCheckStaffStateLogic(CheckStaffStateLogic checkStaffStateLogic) {
        this.checkStaffStateLogic = checkStaffStateLogic;
    }

    /**
     * @return gamenRoleLogic を戻します。
     */
    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }

    /**
     * @param gamenRoleLogic 設定する gamenRoleLogic。
     */
    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }
//  add start 2007/02/19 退職者選択の際にＭＬ試験等登録していないかチェック対応
    public CheckEntryLogicImpl getCheckEntryLogicImpl() {
        return checkEntryLogicImpl;
    }

    public void setCheckEntryLogicImpl(CheckEntryLogicImpl checkEntryLogicImpl) {
        this.checkEntryLogicImpl = checkEntryLogicImpl;
    }
// add end

    public List getListSpecificItemEditableKbn() {
        return listSpecificItemEditableKbn;
    }

    public void setListSpecificItemEditableKbn(List listSpecificItemEditKbn) {
        this.listSpecificItemEditableKbn = listSpecificItemEditKbn;
    }

	public CheckDuplicateNameLogic getStaffRegistCheckDuplicateNameLogic() {
		return staffRegistCheckDuplicateNameLogic;
	}

	public void setStaffRegistCheckDuplicateNameLogic(
			CheckDuplicateNameLogic staffRegistCheckDuplicateNameLogic) {
		this.staffRegistCheckDuplicateNameLogic = staffRegistCheckDuplicateNameLogic;
	}
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    private MenuActionImpl getMenuAction() {
        return (MenuActionImpl) getS2Container().getComponent(MenuActionImpl.class);
    }

    /**
     * 年月日を連結
     * @param year 年
     * @param month 月
     * @param day 日
     * @return YYYYMMDD
     */
    private String concatYearMonthDay(String year, String month, String day){
    	if(year != null &&  month != null && day != null){

			CodeFormatter formatter = new CodeFormatter(2);
			formatter.setFormatPattern("00");

    		return year + formatter.format(month, true) + formatter.format(day, true);
    	}
    	return null;
    }
}
