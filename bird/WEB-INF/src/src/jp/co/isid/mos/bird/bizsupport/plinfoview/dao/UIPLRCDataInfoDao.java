/*
 * 作成日: 2006/3/16
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLRCDataInfo;



/**
 * PLデータ情報（直営）(TrnPLInfoDao)
 * @author kusama
 */
public interface UIPLRCDataInfoDao {

    public static final Class BEAN = UIPLRCDataInfo.class;
    public static final String getPLRCData_ARGS  = "plType, plYm, plYmZen, companyCd, miseCd";
    public static final String getPLRCDataCsv_ARGS  = "plType, plYm, plYmZen, companyCd, miseCd";
    public static final String getPLRCKoumokuCsv_ARGS  = "plType, plYm, plYmZen, companyCd, miseCd";

    /**
     * PLデータの取得
     * 本部ユーザー用
     * @param plType        PLの種類
     * @param plYm          年月
     * @param plYmZen       前年年月
     * @param companyCd     企業コード
     * @param miseCd        店コード
     * @return List 検索結果
     */
    public List getPLRCData(String plType, 
                            String plYm,
                            String plYmZen,
                            String companyCd,
                            String miseCd);
    
    /**
     * PLデータの取得（CSV用）
     * 本部ユーザー用
     * @param plType        PLの種類
     * @param plYm          年月
     * @param plYmZen       前年年月
     * @param companyCd     企業コード
     * @param miseCd        店コード
     * @return List 検索結果
     */
    public List getPLRCDataCsv(String plType, 
                            String plYm,
                            String plYmZen,
                            String companyCd,
                            String miseCd);

    /**
     * PLデータの項目取得（CSV用）
     * 本部ユーザー用
     * @param plType        PLの種類
     * @param plYm          年月
     * @param plYmZen       前年年月
     * @param companyCd     企業コード
     * @param miseCd        店コード
     * @return List 検索結果
     */
    public List getPLRCKoumokuCsv(String plType, 
                            String plYm,
                            String plYmZen,
                            String companyCd,
                            String miseCd);
}