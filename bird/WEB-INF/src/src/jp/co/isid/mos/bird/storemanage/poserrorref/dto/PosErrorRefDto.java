/*
 * 作成日:2007/02/08
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;

/**
 * POS集信エラー店舗一覧DTO
 * @author xkonishi
 *
 */
public class PosErrorRefDto extends CommonDto {
    
    /**
     * 最大データ保持件数
     */
    private int maxSize;
    
    /**
     * 会社リスト
     */
    private List companyList;
    
    /**
     * 集信日リスト
     */
    private List shuDtList;
    
    /**
     * 会社コード
     */
    private Map companyCd = new HashMap();
    
    /**
     * 会社名称
     */
    private Map companyName = new HashMap();
    
    /**
     * 店コード
     */
    private Map miseCd = new HashMap();
    
    /**
     * オーナーコード
     */
    private Map onerCd = new HashMap();
    
    /**
     * 集信日
     */
    private Map shuDt = new HashMap();
        
    /**
     * 集信エラー店舗情報
     */
    private Map shushinErrorInfo = new LinkedHashMap();
    
    /**
     * ボタン名フラグ
     */
    private Map btnNameFlg = new HashMap();
    
 
    /**
     * 検索データ存在判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isExistSearchData(int windowId) {
        return shushinErrorInfo.containsKey(new Integer(windowId));
    }
    
    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
        
    public boolean isBtnNameFlg(int windowId) {
        Boolean result = (Boolean)btnNameFlg.get(new Integer(windowId));
        return result.booleanValue();
    }

    public void setBtnNameFlg(int windowId, boolean btnNameFlg) {
        Boolean flg = new Boolean(btnNameFlg);
        this.btnNameFlg.put(new Integer(windowId), flg);
    }

    public String getCompanyCd(int windowId) {
        return (String)companyCd.get(new Integer(windowId));
    }

    public void setCompanyCd(int windowId, String companyCd) {
        this.companyCd.put(new Integer(windowId), companyCd);
    }

    public List getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    public String getCompanyName(int windowId) {
        return (String)companyName.get(new Integer(windowId));
    }

    public void setCompanyName(int windowId, String companyName) {
        this.companyName.put(new Integer(windowId), companyName);
    }

    public String getMiseCd(int windowId) {
        return (String)miseCd.get(new Integer(windowId));
    }

    public void setMiseCd(int windowId, String miseCd) {
        this.miseCd.put(new Integer(windowId), miseCd);
    }

    public String getOnerCd(int windowId) {
        return (String)onerCd.get(new Integer(windowId));
    }

    public void setOnerCd(int windowId, String onerCd) {
        this.onerCd.put(new Integer(windowId), onerCd);
    }

    public String getShuDt(int windowId) {
        return (String)shuDt.get(new Integer(windowId));
    }

    public void setShuDt(int windowId, String  shuDt) {
        this.shuDt.put(new Integer(windowId), shuDt);
    }

    public List getShuDtList() {
        return shuDtList;
    }

    public void setShuDtList(List shuDtList) {
        this.shuDtList = shuDtList;
    }

    public List getShushinErrorInfo(int windowId) {
        return (List)shushinErrorInfo.get(new Integer(windowId));
    }

    public void setShushinErrorInfo(int windowId, List data) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.shushinErrorInfo.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.shushinErrorInfo.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.shushinErrorInfo.size() > getMaxSize()) {
            // 最古データを削除
            this.shushinErrorInfo.remove(this.shushinErrorInfo.keySet().toArray()[0]);
        }
        // リスト設定
        this.shushinErrorInfo.put(new Integer(windowId), data);
    }
}