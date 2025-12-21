/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.campkako.util.CampKakoUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.RowType;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.NipoMise;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;

/**
 * 店舗一覧用キャンペーン日報エンティティ
 * 
 * @author xnkusama
 *
 */
public class UINipoMise extends NipoMise {
    
    public static final String miseGroupSeq_COLUMN = "MISE_GROUP_SEQ";
    private BigDecimal miseGroupSeq;
    
    /**
	 * ブロック名称取得処理
	 * 
	 * @return eigyoDt を戻します。
	 */
	public String getBlockName() {
		//総合計の場合
		if(RowType.CD_TOTAL.equals(getRowType())) {
			return RowType.getName(getRowType());
		}
		else if(!CommonUtil.isNull(RowType.getName(getRowType()))) {
			if(!CommonUtil.isNull(super.getBlockName())) {
				//合計行の場合
				return super.getBlockName()+RowType.getName(getRowType());
			}
		}
		return super.getBlockName();
	}
    /**
	 * エリア大コード対象店舗名称取得処理
	 * 
	 * @return eigyoDt を戻します。
	 */
	public String getMiseNameKjForAreaDai() {
		//総合計の場合
		if(RowType.CD_TOTAL.equals(getRowType())) {
			return RowType.getName(getRowType());
		}
		return super.getMiseNameKj();
	}

    /**
     * 金額構成比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getKingakuKoseiHiCssClass() {
    	return CampKakoUtil.CssClassHiritu;
    }
    /**
     * 売上前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getUriageZennenHiCssClass() {
    	return CampKakoUtil.getCssClassHiritu(getRowType(), getUriageZennenHi());
    }
    /**
     * 客数前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getKyakusuZennenHiCssClass() {
    	return CampKakoUtil.getCssClassHiritu(getRowType(), getKyakusuZennenHi());
    }
    /**
     * 客単価前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getKyakutankaZennenHiCssClass() {
    	return CampKakoUtil.getCssClassHiritu(getRowType(), getKyakutankaZennenHi());
    }
    /**
     * 売上前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getNetUriageZennenHiCssClass() {
    	return CampKakoUtil.getCssClassHiritu(getRowType(), getNetUriageZennenHi());
    }
    /**
     * 客数前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getNetKyakusuZennenHiCssClass() {
    	return CampKakoUtil.getCssClassHiritu(getRowType(), getNetKyakusuZennenHi());
    }
    /**
     * 客単価前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getNetKyakutankaZennenHiCssClass() {
    	return CampKakoUtil.getCssClassHiritu(getRowType(), getNetKyakutankaZennenHi());
    }
    public BigDecimal getMiseGroupSeq() {
        return miseGroupSeq;
    }
    public void setMiseGroupSeq(BigDecimal miseGroupSeq) {
        this.miseGroupSeq = miseGroupSeq;
    }
}
