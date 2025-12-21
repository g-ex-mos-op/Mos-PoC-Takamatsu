/*
 * çÏê¨ì˙: 2006/06/30
 *
 */
package jp.co.isid.mos.bird.entry.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.dao.UIEntryCourseDao;
import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.entry.common.logic.SearchDefaultCourseListLogic;

/**
 * @author xyuchida
 */
public class SearchDefaultCourseListLogicImpl implements
        SearchDefaultCourseListLogic {

    public static final String LOGIC_ID = "BEN101L01";
    
    private UIEntryCourseDao uiEntryCourseDao;

    public UIEntryCourseDao getUiEntryCourseDao() {
        return uiEntryCourseDao;
    }
    public void setUiEntryCourseDao(UIEntryCourseDao uiEntryCourseDao) {
        this.uiEntryCourseDao = uiEntryCourseDao;
    }

    public List execute(String entryCd) {
        List defaultCourseList = getUiEntryCourseDao().getDefaultCourseList(entryCd);
        UIEntryCourse uiEntryCourse = new UIEntryCourse();
        uiEntryCourse.setCourseCd("99");
        uiEntryCourse.setCourseName("îCà”ì¸óÕ");
        defaultCourseList.add(uiEntryCourse);
        return defaultCourseList;
    }
}
