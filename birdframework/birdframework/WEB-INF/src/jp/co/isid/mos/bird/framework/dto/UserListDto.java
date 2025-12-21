package jp.co.isid.mos.bird.framework.dto;


/**
 * ユーザリストDTO
 * @author xnlee
 */
public class UserListDto {

    /* ユーザID */
    private String _userId;
    /* ユーザNAME */
    private String _userNameKj;
    /* ユーザNAME_KANA */
    private String _userNameKana;
 	
	/**
	 * @return _userId を戻します。
	 */
	public String getUserId() {
		return _userId;
	}
	/**
	 * @param id _userId を設定。
	 */
	public void setUserId(String userId) {
		_userId = userId;
	}
	/**
	 * @return _userNameKana を戻します。
	 */
	public String getUserNameKana() {
		return _userNameKana;
	}
	/**
	 * @param nameKana _userNameKana を設定。
	 */
	public void setUserNameKana(String nameKana) {
		_userNameKana = nameKana;
	}
	/**
	 * @return _userNameKj を戻します。
	 */
	public String getUserNameKj() {
		return _userNameKj;
	}
	/**
	 * @param nameKj _userNameKj を設定。
	 */
	public void setUserNameKj(String nameKj) {
		_userNameKj = nameKj;
	}
	/**
	 * @return _userId + _userNameを戻します。
	 */
	public String getUserIdNameKana() {
		return _userId +" "+_userNameKj;
	}
	
}
