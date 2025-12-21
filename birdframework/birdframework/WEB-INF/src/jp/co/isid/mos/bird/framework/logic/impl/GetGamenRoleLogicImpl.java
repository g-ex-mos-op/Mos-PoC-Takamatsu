package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dao.CtlGamenRoleDao;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ユーザがの汎用画面ロール制御を取得します
 * @author xylee
 */
public class GetGamenRoleLogicImpl implements GetGamenRoleLogic {

    /**
     * 画面ロールを取得
     */
    private static final Class CLASS_GAMEN_ROLE_DAO = CtlGamenRoleDao.class;

    /**
     * 画面ロール制御を取得します
     * @param gamenRoleDto 画面ロール制御
     * @return ユーザの画面ロール
     */
    public List excute(final GamenRoleDto gamenRoleDto) {

    	S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        CtlGamenRoleDao gamenRoleDao = (CtlGamenRoleDao) s2Con
                .getComponent(CLASS_GAMEN_ROLE_DAO);
        //画面ロールを取得
        List gamenRoles = gamenRoleDao.getGamenRole(gamenRoleDto.getUserId(),gamenRoleDto.getGamenId(),gamenRoleDto.getBunruiCd());
        List gamenRole = new ArrayList();
        if (gamenRoles == null || gamenRoles.size() <= 0) {
			throw new NotExistException("汎用画面別ロール制御");
        }
    	
        CtlGamenRole ctlGamenRole = (CtlGamenRole) gamenRoles.get(0);
    	gamenRole.add(ctlGamenRole);

        return gamenRole;
    
    }  

    /**
     *  汎用画面別ロール制御 データ取得
     *  　画面ID、分類コード、ユーザーIDを指定し、
     *  　該当する制御情報をListで返す
     *  @param GamenRoleDto dto 汎用画面別ロール制御DTO
     *  @return List 指定パラメータで検索した結果（CtlGamenRole）
     */
    public List getGamenRole(final GamenRoleDto dto) {
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        CtlGamenRoleDao gamenRoleDao = (CtlGamenRoleDao) s2Con.getComponent(CLASS_GAMEN_ROLE_DAO);
       return gamenRoleDao.getGamenRole(dto.getUserId(), dto.getGamenId(), dto.getBunruiCd());
    }
}