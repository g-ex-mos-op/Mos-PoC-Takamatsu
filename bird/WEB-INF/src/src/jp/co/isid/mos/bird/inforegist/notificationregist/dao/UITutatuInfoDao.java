/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;


/**
 * 通達情報DAO
 * @author m.onodera
 */
public interface UITutatuInfoDao {

    public Class BEAN = UITutatuInfo.class;

    public static final String getTutatu_ARGS = "regDate, cateId, kanriNo, userId, companyCd, from, to";
    public static final String getCount_ARGS = "regDate, cateId, kanriNo, userId, companyCd";
//    public static final String getBunshoList_ARGS = "regDate, cateId, subCateId, rCompanyCd, bumonCd";
    public static final String getNumber_ARGS = "tourokuDt";
    
    public static final String updateTutatu_PERSISTENT_PROPS 
                                    = "cateId, title, subTitle, pubDate, pubUser,"
                                    + "kanriNo, fileName, attachName1, attachName2, attachName3, "
                                    + "attachFl1, attachFl2, attachFl3, sakujoFlg, "
                                    + "lastUser, lastPgm";
    
    /**
     * 文書情報の取得
     * 
     * @param regDate    対象年月
     * @param cateId     カテゴリID
     * @param kanriNo    管理番号
     * @param userId     ユーザーID
     * @param companyCd  会社コード
     * @param from       件数FROM
     * @param to         件数TO
     * @return
     */
    public List getTutatu(
                    String regDate,
                    String cateId,
                    String kanriNo,
                    String userId,
                    String companyCd,
                    int from,
                    int to);
    
    /**
     * 総件数の取得
     * @param regDate   対象月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @param userId     ユーザーID
     * @param companyCd  会社コード
     * @return
     */
    public int getCount(
                    String regDate,
                    String cateId,
                    String kanriNo,
                    String userId,
                    String companyCd
                    );
    
    
    /**
     * シーケンス番号の取得
     * @param regDate
     * @return
     */
    public int getNumber(String regDate);
    
    /**
     * 新規通達情報の登録
     * @param UITutatuInfo
     * @return
     */
    public int insertTutatu(UITutatuInfo entity);
    
    /**
     * 通達情報の更新
     * @param UITutatuInfo
     * @return
     */
    public int updateTutatu(UITutatuInfo entity);

}
