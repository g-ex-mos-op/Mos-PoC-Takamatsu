/*
 * 作成日: 2006/01/18
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dao.CtlDateDao;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 日付関連情報取得ロジック
 * @author xnkusama
 */
public class DateInfoSetupLogicImpl implements DateInfoSetupLogic {

    public BirdDateInfo getBirdDateInfo() {
        BirdDateInfo dateInfo = new BirdDateInfo();
        
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        
        /* コントロール日付 */
        CtlDateDao ctlDateDao = (CtlDateDao) s2Con.getComponent(CtlDateDao.class);
        List dateInfoList = ctlDateDao.getDateInfo();
        dateInfo.setInfo(dateInfoList, BirdDateInfo.CTL_DATE_INFO);

        return dateInfo;
    }
}
