/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.inforegist.documentregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;


/**
 * 文書情報DAO
 * @author xnkusama
 */
public interface UIBunshoInfoDao {

    public Class BEAN = UIBunshoInfo.class;

    public static final String getBunsho_ARGS = "regDate, cateId, subCateId, rCompanyCd, bumonCd, kikanFrom, kikanTo, from, to";
    public static final String getNumber_ARGS = "tourokuDt";
    public static final String countBunsho_ARGS = "regDate, cateId, subCateId, rCompanyCd, bumonCd, kikanFrom, kikanTo";
    public static final String getDefaultSubCatery_ARGS = "regDate, cateId, bumonCd";
    public static final String getShokaiList_ARGS = "regDate, cateId, subCateId, rCompanyCd, bumonCd";
    
    public static final String updateBunsho_PERSISTENT_PROPS 
                                    = "cateId, subCateId, title, discription, pubDateFrom, pubDateTo, "
                                    + "pubUser, fileName, attachName1, attachName2, attachName3, "
                                    + "attachFl1, attachFl2, attachFl3, limitKbn, sortSeq, sakujoFlg, "
                                    + "lastUser, lastPgm";
    
    /**
     * 文書情報の取得
     * @param regDate   対象年月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @param rCompanyCd 企業コード
     * @param bumonCd   部門コード
     * @param kikanFrom 対象期間From
     * @param kikanTo 対象期間To
     * @param from      件数FROM
     * @param to        件数TO
     * @return
     */
    public List getBunsho(
                    String regDate,
                    String cateId,
                    String subCateId,
                    String rCompanyCd,
                    String bumonCd,
                    String kikanFrom,
                    String kikanTo,
                    int from,
                    int to);
    
    /**
     * シーケンス番号の取得
     * @param regDate
     * @return
     */
    public int getNumber(String regDate);
    
    /**
     * 新規文書情報の登録
     * @param UIBunshoInfo
     * @return
     */
    public int insertBunsho(UIBunshoInfo entity);
    
    /**
     * 文書情報の更新
     * @param UIBunshoInfo
     * @return
     */
    public int updateBunsho(UIBunshoInfo entity);

    /**
     * 文書情報の取得
     * @param regDate   対象年月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @param rCompanyCd 企業コード
     * @param bumonCd   部門コード
     * @param kikanFrom 対象期間From
     * @param kikanTo 対象期間To
     * @return
     */
    public int countBunsho(
                    String regDate,
                    String cateId,
                    String subCateId,
                    String rCompanyCd,
                    String bumonCd,
                    String kikanFrom,
                    String kikanTo);
    
    /**
     * 総件数の取得
     * @param regDate   対象月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @return
     */
    public int getCount(
                    String regDate,
                    String cateId,
                    String subCateId);
    
    
    /**
     * デフォルトサブカテゴリの取得
     * @param regDate
     * @param cateId
     * @return
     */
    public List getDefaultSubCatery(
                    String regDate,
                    String cateId,
                    String bumonCd);
    
    /**
     * 照会所順用 文書一覧情報の取得
     * @param regDate   対象年月
     * @param cateId    カテゴリID
     * @param subCateId サブカテゴリID
     * @param rCompanyCd 企業コード
     * @param bumonCd   部門コード
     * @return
     */
    public List getShokaiList(
                    String regDate,
                    String cateId,
                    String subCateId,
                    String rCompanyCd,
                    String bumonCd);
}
