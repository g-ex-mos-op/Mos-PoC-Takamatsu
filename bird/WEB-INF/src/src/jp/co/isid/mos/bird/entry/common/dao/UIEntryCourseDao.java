/*
 * 作成日: 2006/06/30
 *
 */
package jp.co.isid.mos.bird.entry.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;

/**
 * エントリーコース管理DAO
 * 
 * @author xyuchida
 */
public interface UIEntryCourseDao {

    public static final Class BEAN = UIEntryCourse.class;

    public static final String updateEntryCourse_PERSISTENT_PROPS = "courseCd, courseName, lastUser, lastPgm";

    public static final String getEntryCourseList_ARGS = "entryCd, entryYear, entryKai";

    public int insertEntryCourse(UIEntryCourse uiEntryCourse);

    public int updateEntryCourse(UIEntryCourse uiEntryCourse);

    public List getEntryCourseList(String entryCd, String entryYear, String entryKai);

    public List getDefaultCourseList(String entryCd);
}
