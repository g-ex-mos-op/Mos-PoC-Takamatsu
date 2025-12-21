/**
 * 作成日：2006/9/13
 * 参照：jp.co.isid.mos.bird.framework.logic.GetBirdUserLogic
 *
 */

package jp.co.isid.mos.bird.bizadmin.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;
/**
 * 自分自身を含めたユーザー一覧を取得します
 * @author miyagi
 *
 */
public interface GetBirdUserSelfLogic {
    
    /**
     * ユーザ一覧（自分含む）をユーザタイプ別に取得します
     * @param mstUser  BIRDユーザ情報
     * @param userOner ユーザ対応オーナー
     * @param userMise ユーザ対応店舗
     * @return ユーザ一覧
     */
    public List execute(final MstUser mstUser, final List userOner,final List userMise);

}
