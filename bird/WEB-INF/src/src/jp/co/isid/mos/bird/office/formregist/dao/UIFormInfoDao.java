/*
 * 作成日: 2006/01/23
 * 更新日: 2007/08/06 更新処理時に企業コードを更新対象カラムに追加
 */
package jp.co.isid.mos.bird.office.formregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;


/**
 * フォーム情報DAO
 * @author xytamura
 */
public interface UIFormInfoDao {

    public Class BEAN = UIFormInfo.class;

    public static final String getForm_ARGS = "regDate, cateId, subCateId, userId, kikanFrom, kikanTo, from, to";
    public static final String getFormList_ARGS = "regDate, cateId, subCateId";
    public static final String getNumber_ARGS = "tourokuDt";
    public static final String countForm_ARGS = "regDate, cateId, subCateId, userId, kikanFrom, kikanTo";
    public static final String getDefaultSubCatery_ARGS = "regDate, cateId, userId";
    
    public static final String updateForm_PERSISTENT_PROPS 
                                    = "cateId, subCateId, title, discription, pubDateFrom, pubDateTo, "
                                    + "pubUser, fileName, attachName1, attachName2, attachName3, "
                                    + "attachFl1, attachFl2, attachFl3, limitKbn, sortSeq, sakujoFlg, "
                                    + "lastUser, lastPgm, rCompanyCd";
    

    /**
     * フォーム情報の取得
     * @param regDate   対象年月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @param userId    ユーザーID
     * @param kikanFrom 対象期間From
     * @parma kikanTo   対象期間To
     * @param from      件数FROM
     * @param to        件数TO
     * @return
     */
    public List getForm(
                    String regDate,
                    String cateId,
                    String subCateId,
                    String userId,
                    String kikanFrom,
                    String kikanTo,
                    int from,
                    int to);

    

    /**
     * フォーム一覧情報の取得
     * @param regDate   対象年月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @return
     */
    public List getFormList(
                    String regDate,
                    String cateId,
                    String subCateId );

    /**
     * シーケンス番号の取得
     * @param regDate
     * @return
     */
    public int getNumber(String regDate);
    
    /**
     * 新規フォーム情報の登録
     * @param UIFormInfo
     * @return
     */
    public int insertForm(UIFormInfo entity);
    
    /**
     * フォーム情報の更新
     * @param UIFormInfo
     * @return
     */
    public int updateForm(UIFormInfo entity);


    /**
     * フォーム情報の取得
     * @param regDate   対象年月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @param userId    ユーザーID
     * @param kikanFrom 対象期間From
     * @parma kikanTo   対象期間To
     * @return
     */
    public int countForm(
                    String regDate,
                    String cateId,
                    String subCateId,
                    String userId,
                    String kikanFrom,
                    String kikanTo);

    
    
    /**
     * デフォルトサブカテゴリの取得
     * @param regDate
     * @param cateId
     * @return
     */
    public List getDefaultSubCatery(
                    String regDate,
                    String cateId,
                    String userId);
}
