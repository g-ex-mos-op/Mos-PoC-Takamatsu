/*
 * 作成日: 2006/04/07
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIPlRCDataInfo;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface UIPlRCDataInfoDao {

    public static final Class BEAN = UIPlRCDataInfo.class;

    public static final String getPlRCDataInfo_ARGS = "companyCd,sibuCd,fromDt,toDt";

    public List getPlRCDataInfo(String companCd, String sibuCd ,String fromDt, String toDt) ;


}
