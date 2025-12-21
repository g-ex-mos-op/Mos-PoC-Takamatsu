/*
 * 作成日: 2008/11/17
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;


/**
 * ユーザ公開対象所属区分 ロジックインターフェース
 * @author xnkusama
 */
public interface GetUserShozokuKbnLogic {

    /**
     * ユーザ公開対象所属区分
     * @param userRegistDto
     */
    public List execute(UserRegistDto dto);
}
