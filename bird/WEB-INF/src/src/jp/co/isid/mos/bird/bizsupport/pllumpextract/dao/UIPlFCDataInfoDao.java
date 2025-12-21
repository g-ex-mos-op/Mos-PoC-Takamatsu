/*
 * 作成日: 2006/04/27
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIPlDataInfo;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface UIPlFCDataInfoDao {
	
    public static final Class BEAN = UIPlDataInfo.class;
    
    public static final String getPlFCData_ARGS = "companyCd,onerCd,fromDt,toDt";
    
    public static final String getPlFCTotalData_ARGS = "companyCd,fromDt,toDt,gyotaiKbnList";
    /**
     * PLデータ取得
     * @param companyCd
     * @param onerCd
     * @param fromDt
     * @param toDt
     * @return
     */
    public List getPlFCData( String companyCd, String onerCd, String fromDt, String toDt);
    
    public List getPlFCTotalData( String companyCd, String fromDt, String toDt, List gyotaiKbnList);
    
}
