/*
 * 作成日: 2006/4/14
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLDataInfo;



/**
 * PLデータ情報(TrnPLInfoDao)
 * @author kusama
 */
public interface UIPLDataInfoDao {

    public static final Class BEAN = UIPLDataInfo.class;
    public static final String getPLData_ARGS  = "plType, plYm, plYmZen, companyCd, code1, code2, taishoTenpo, tenpoShu, openDtCond1, openDtCond2, openDtCond3, userId, isLimit, isUriage";
    public static final String getPLDataOnerAvg_ARGS = "plType, plYm, plYmZen, companyCd, searchType, miseCd, onerCd, userId, isLimit, isUriage";
    public static final String getPLDataOnerSum_ARGS = "plType, plYm, plYmZen, companyCd, searchType, miseCd, onerCd, userId, isLimit, isUriage";

    /**
     * PLデータの取得
     * 本部ユーザー用
     * @param plType        PLの種類
     * @param plYm          年月
     * @param plYmZen       前年年月
     * @param companyCd     企業コード
     * @param code1         コード１
     * @param code2         コード２
     * @param taishoTenpo   対象店舗
     * @param tenpoShu      店舗種別
     * @param openDtCond1   店舗種別によるオープン条件１
     * @param openDtCond2   店舗種別によるオープン条件２
     * @param openDtCond3   店舗種別によるオープン条件３
     * @param userId        ユーザーID
     * @param isLimit       支部制限 true：制限あり
     * @param isUriage     売上高で絞るかどうか「true：絞る,false：絞らない」
     * @return UIPLDataInfo 検索結果
     */
    public List getPLData(String plType, 
                            String plYm,
                            String plYmZen,
                            String companyCd,
                            String code1,
                            String code2,
                            String taishoTenpo,
                            String tenpoShu,
                            String openDtCond1,
                            String openDtCond2,
                            String openDtCond3,
                            String userId,
                            boolean isLimit,
                            boolean isUriage);
    
    /**
     * PLデータの取得
     * オーナー用 （平均）
     * @param plType        PLの種類
     * @param plYm
     * @param plYmZen
     * @param companyCd
     * @param taishoTenpo 対象店舗
     * @param miseCd
     * @param onerCd
     * @param isLimit       支部制限 true：制限あり
     * @return
     */
    public List getPLDataOnerAvg(String plType, 
                                  String plYm,
                                  String plYmZen,
                                  String companyCd,
                                  String searchType,
                                  String miseCd,
                                  String onerCd,
                                  String userId,
                                  boolean isLimit,
                                  boolean isUriage);

    /**
     * PLデータの取得
     * オーナー用 （合計）
     * @param plType        PLの種類
     * @param plYm
     * @param plYmZen
     * @param companyCd
     * @param taishoTenpo 対象店舗
     * @param miseCd
     * @param onerCd
     * @param isLimit       支部制限 true：制限あり
     * @return
     */
    public List getPLDataOnerSum(String plType,
                                  String plYm, 
                                  String plYmZen,
                                  String companyCd,
                                  String searchType,
                                  String miseCd,
                                  String onerCd,
                                  String userId,
                                  boolean isLimit,
                                  boolean isUriage);
}