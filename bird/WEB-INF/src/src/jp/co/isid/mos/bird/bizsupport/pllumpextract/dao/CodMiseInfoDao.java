/*
 * 作成日: 2006/04/24
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.CodMiseInfo;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface CodMiseInfoDao {
    
    public static final Class BEAN = CodMiseInfo.class;
    
    public static final String getMiseInfo_ARGS = "companyCd, sibuCd, fromDt, toDt";
    public static final String getOwnerMiseInfo_ARGS = "companyCd, onerCd, fromDt, toDt";
    
    /**
     * 対象支部の店舗取得用
     * @param companyCd
     * @param sibuCd
     * @return
     */
    public List getMiseInfo(String companyCd, String sibuCd, String fromDt, String toDt);
    
    /**
     * オーナーに所属する店舗取得用
     * @param companyCd
     * @param onerCd
     * @return
     */
    public List getOwnerMiseInfo(String companyCd, String onerCd, String fromDt, String toDt);
    
}
