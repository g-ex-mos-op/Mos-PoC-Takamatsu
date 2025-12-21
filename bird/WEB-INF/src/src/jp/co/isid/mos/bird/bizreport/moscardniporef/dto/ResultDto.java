package jp.co.isid.mos.bird.bizreport.moscardniporef.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefResultDto;

/**
 * DTO【結果情報】クラス
 * @author xkinu
 *
 */
public class ResultDto extends NipoRefResultDto{
 
	 // 支部別売上情報
    /**
     * 売上リスト
     */
    private List uriageList = new ArrayList(0);
    
    /**
     * 客数リスト
     */
    private List kyakusuList = new ArrayList(0);
    
    /**
     * 売上平均リスト(支部別)
     */
    private List uriAvgList = new ArrayList(0);
    
    /**
     * 客数平均リスト(支部別)
     */
    private List kyakuAvgList = new ArrayList(0);
    

    /**
     * 売上リストを取得します。
     * @return 売上リスト(支部別)
     */
    public List getUriageList() {
        return uriageList;
    }
 
    /**
     * 売上リストを設定します。
     * @param uriageList 売上リスト
     */
    public void setUriageList(List uriageList) {
        this.uriageList = uriageList;
    }
    
    /**
     * 客数リストを取得します。
     * @return 客数リスト
     */
    public List getKyakusuList() {
        return kyakusuList;
    }

    /**
     * 客数リストを設定します。
     * @param kyakusuList 客数リスト
     */
    public void setKyakusuList(List kyakusuList) {
        this.kyakusuList = kyakusuList;
    }
    
    /**
     * 売上平均リスト(支部別)を取得します。
     * @return 売上平均リスト(支部別)
     */
    public List getUriAvgList() {
        return uriAvgList;
    }
 
    /**
     * 売上平均リスト(支部別)を設定します。
     * @param uriAvgList 売上平均リスト(支部別)
     */
    public void setUriAvgList(List uriAvgList) {
        this.uriAvgList = uriAvgList;
    }
    
    /**
     * 客数平均リスト(支部別)を取得します。
     * @return 客数平均リスト(支部別)
     */
    public List getKyakuAvgList() {
        return kyakuAvgList;
    }
 
    /**
     * 客数平均リスト(支部別)を設定します。
     * @param kyakuAvgList 客数平均リスト(支部別)
     */
    public void setKyakuAvgList(List kyakuAvgList) {
        this.kyakuAvgList = kyakuAvgList;
    }

 

    /**
     * 売上情報クリア
     */
    public void clear() {
    	super.clear();
        setUriageList(new ArrayList(0));
        setKyakusuList(new ArrayList(0));
        setUriAvgList(new ArrayList(0));
        setKyakuAvgList(new ArrayList(0));
    }
}