package jp.co.isid.mos.bird.entry.hanyoapplication.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MlCourseKanri;

/**
 * マスターライセンス研修コース管理
 * @author kusama
 */
public interface MlCourseKanriDao {

    public static final Class BEAN = MlCourseKanri.class;
    public static final String getMlCourseInfo_ARGS = "entryCd, entryYear, entryKai";

    /**
     * マスターライセンス研修コース情報取得(getMlCourseInfo)
     * @return int 検索結果
     */
    public List getMlCourseInfo(String entryCd, String entryYear, String entryKai);

}