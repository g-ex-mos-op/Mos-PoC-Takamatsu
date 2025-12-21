package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.logic.ValidatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.portal.login.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.portal.login.entity.UIBirdUser;
import jp.co.isid.mos.bird.portal.login.logic.UpdatePasswordLogic;


/**
 * @author xnkusama
 */
public class UpdatePasswordLogicImpl implements UpdatePasswordLogic {
    
    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BPO000L05";
    
    private static final String STOP_FLG_ERR_VALUE = "1";
    private static final String VIEW_ID = "BPO000";

    /*DAO*/
    private UIBirdUserDao uiBirdUserDao;
    /*LOGIC*/
    private ValidatePasswordLogic validatePasswordLogic;
    private jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic updatePasswordLogic;

    /**
     * パスワード変更処理
     * @param LoginDto
     */
    public void update(final LoginDto dto) throws ApplicationException {
        // 事前条件チェック
        validate(dto);
        
        /* １．DAO【ユーザー情報の取得】を実行 */
        List list = getUiBirdUserDao().getUserInfo(dto.getUserId());
        
        /* ２．処理１の結果が０件の場合 */
        if (list == null || list.size() == 0) {
            throw new GenericErrorException(
                            "ログイン", 
                            "ログインに失敗しました。ユーザーＩＤと現在のパスワードを再度ご確認下さい。");
        }
        UIBirdUser birdUser = (UIBirdUser) list.get(0);
        
        /* ３．処理１で取得した[BIRDユーザー]．使用停止フラグ ＝ ’１’ の場合 */
        if (STOP_FLG_ERR_VALUE.equals(birdUser.getStopFlg())) {
            throw new CannotExecuteWithReasonException(
                            "そのユーザーIDは現在使用停止になっている",
                            "パスワード変更");
        }
        
        /* ４．処理１で取得した[BIRDユーザー]．パスワード ≠ パラメータ．現在のパスワード の場合 */
        byte[] db_pswd = birdUser.getUserPswd();
        byte[] inp_pswd = dto.getOldPassword().getBytes();
        
        //2008/11/18 update start 右側のスペースは無視する
        String sDbPswd = CommonUtil.rtrim(new String(db_pswd));
        String sInpPswd = CommonUtil.rtrim(new String(inp_pswd));
        if (!sDbPswd.equals(sInpPswd)) {
            throw new GenericErrorException(
                    "ログイン", 
                    "ログインに失敗しました。ユーザーＩＤと現在のパスワードを再度ご確認下さい。");
        }
        //2008/11/18 update end
        
        /* ５．パラメータ．新しいパスワード ≠ パラメータ．確認用パスワード の場合 */
        if (!CommonUtil.rtrim(dto.getNewPassword()).equals(CommonUtil.rtrim(dto.getNewPasswordKakunin()))) {
            throw new ConstraintsViolationException("新しいパスワードと確認用パスワードは、一致し");
        }

        /* ６．DAO【パスワード登録】を実行 */
        //共通 パスワードチェックロジック実行
        getValidatePasswordLogic().execute(dto.getUserId(), dto.getNewPassword(), null, true);
        //共通 パスワード更新ロジック実行
        getUpdatePasswordLogic().execute(birdUser.getUserId(), 
                                    dto.getNewPassword(), 
                                    jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic.HENKO_KBN_UPDATE, 
                                    birdUser.getUserId(), 
                                    VIEW_ID);
    }
    
    /*
     * 事前条件チェック
     */
    private void validate(final LoginDto dto) throws ApplicationException {
        // ユーザーID
        String userId = dto.getUserId();
        // 現在のパスワード
        String oldPassword = dto.getOldPassword();
        // 新しいパスワード
        String newPassword = CommonUtil.rtrim(dto.getNewPassword());
        // 確認用パスワード
        String newPasswordKakunin = CommonUtil.rtrim(dto.getNewPasswordKakunin());
        
        /* １．必須チェック */
        if (userId == null) {
            throw new NotNullException("ユーザーID", "");
        }
        if (oldPassword == null) {
            throw new NotNullException("現在のパスワード", "");
        }
        if (newPassword == null) {
            throw new NotNullException("新しいパスワード", "");
        }
        if (newPasswordKakunin == null) {
            throw new NotNullException("確認用パスワード", "");
        }
        //１．パスワード確認処理（全ユーザー対象)
    	if(userId.equals(newPassword)) {
	        // パスワード変更エラー
	        throw new GenericErrorException("パスワード変更", "ＩＤと新しいパスワードが同じです。パスワードにＩＤと同じものは使用できません。");
    	}
        //２．新しいパスワード ＝ 確認用パスワード
        if (!newPassword.equals(newPasswordKakunin)) {
            throw new ConstraintsViolationException("新しいパスワードと確認用パスワードは、一致し", "");
        }
    }

    public ValidatePasswordLogic getValidatePasswordLogic() {
        return validatePasswordLogic;
    }

    public void setValidatePasswordLogic(ValidatePasswordLogic validatePasswordLogic) {
        this.validatePasswordLogic = validatePasswordLogic;
    }

    public UIBirdUserDao getUiBirdUserDao() {
        return uiBirdUserDao;
    }

    public void setUiBirdUserDao(UIBirdUserDao uiBirdUserDao) {
        this.uiBirdUserDao = uiBirdUserDao;
    }

    public jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic getUpdatePasswordLogic() {
        return updatePasswordLogic;
    }

    public void setUpdatePasswordLogic(
            jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic updatePasswordLogic) {
        this.updatePasswordLogic = updatePasswordLogic;
    }
}