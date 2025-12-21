package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.UILoginBirdUser;

/**
 * @author xnkusama
 *
 */
public interface LoginDao {
	public Class BEAN = UILoginBirdUser.class;

	public static final String getUserInfo_ARGS = "user_id";

    public List getUserInfo(String user_id);
}
