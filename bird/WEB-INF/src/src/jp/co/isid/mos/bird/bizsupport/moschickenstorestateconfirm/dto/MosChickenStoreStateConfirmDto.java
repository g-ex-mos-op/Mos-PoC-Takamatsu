package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.dto.MosChickenDto;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity.CodSibuList;

/**
 * 店別予約状況一覧確認 DTO
 * 
 * モスチキン用の画面のDTOです。
 * 
 * @author xkinu
 *
 */
public class MosChickenStoreStateConfirmDto extends MosChickenDto {
    /* 対象管理番号 */
    private String targetCkanriNo;
    /* 対象タイトル */
    private String targetTitle;
    /* 対象支部コード  */
    private String targetSibuCd;
    /* 対象支部名称  */
    private String targetSibuName;
    /* 対象食包材コード  */
    private String targetShokuCd;
    /* 対象食包材名称カナ(半角)  */
    private String targetShokuNameKna;
    /* 対象期間From */
    private String targetDateFrom;
    /* 対象期間To */
    private String targetDateTo;
    /* 条件項目：[[タイトル]] */
    private Map mapListTitle = new HashMap();
    /* 条件項目：[[対象食包材]] */
    private Map mapListShokuhou = new HashMap();
    /* 条件項目：[[対象支部]] */
    private Map mapListSibu = new HashMap();
    /* 条件項目：[[対象期間]] */
    private Map mapListDate = new HashMap();
    /* 条件項目：[[状況情報]] */
    private Map mapListSearchData = new HashMap();
    /* 条件項目：[[タイトル]]1件のみか判断フラグ */
    private Map mapTitle1CntFlg = new HashMap();
    /* 条件項目：[[対象食包材]]1件のみか判断フラグ */
    private Map mapShokuhou1CntFlg = new HashMap();
    /* 条件項目：[[対象支部]]1件のみか判断フラグ */
    private Map mapSibu1CntFlg = new HashMap();
    /* 条件項目：[[対象期間]]1件のみか判断フラグ */
    private Map mapDate1CntFlg = new HashMap();
    private Map mapIchiranSibuCd = new HashMap();
    private Map mapIchiranSibuName = new HashMap();
    /* 判断フラグ：JSF実行ボタン無効フラグ */
    private Map mapExecDisabledFlg = new HashMap();

    /**
     * @return execDisabledFlg を戻します。
     */
    public boolean isExecDisabledFlg() {
        return ((Boolean)mapExecDisabledFlg.get(new Integer(getWindowId()))).booleanValue();
    }
    /**
     * @param execDisabledFlg 設定する execDisabledFlg。
     */
    public void setExecDisabledFlg(boolean execDisabledFlg) {
        mapExecDisabledFlg.put(new Integer(getWindowId()), new Boolean(execDisabledFlg));
    }
    /**
     * @return ichiranSibuCc を戻します。
     */
    public String getIchiranSibuCd() {
        return (String)mapIchiranSibuCd.get(new Integer(getWindowId()));
    }
    /**
     * @param ichiranSibuCc 設定する ichiranSibuCc。
     */
    public void setIchiranSibuCd(String ichiranSibuCd) {
        mapIchiranSibuCd.put(new Integer(getWindowId()), ichiranSibuCd);
    }
    /**
     * @return ichiranSibuName を戻します。
     */
    public String getIchiranSibuName() {
        return (String)mapIchiranSibuName.get(new Integer(getWindowId()));
    }
    /**
     * @param ichiranSibuName 設定する ichiranSibuName。
     */
    public void setIchiranSibuName(String ichiranSibuName) {
        mapIchiranSibuName.put(new Integer(getWindowId()), ichiranSibuName);
    }
    /**
     * 指定支部コードの支部名称取得処理
     * 
     * @param sibuCd
     * @return
     */
    public String getListSibuName(String sibuCd){
        String name = null;
        List sibuList = getListSibu();
        if(sibuList == null) return name;
        for(int i=0; i<sibuList.size(); i++){
            CodSibuList entity = (CodSibuList)sibuList.get(i);
            if(sibuCd.equals(entity.getSibuCd())){
                name = entity.getSibuName();
                break;
            }
        }
        return name;
    }
    /**
     * クリア処理
     *
     */
    public void clear(){
        //条件項目値クリア
        clearConditionData();
        
        setTargetSibuCd(null);
        setTargetSibuName(null);
        setListSibu(null);
        setListDate(null);
        setListSearchData(null);
   }
    /**
     * 対象支部コード取得処理
     * 
     * @return
     */
    public String getTargetSibuCd() {
        return targetSibuCd;
    }
    /**
     * 対象支部コード設定処理
     * 
     * @param targetSibuCd
     */
    public void setTargetSibuCd(String targetSibuCd) {
        this.targetSibuCd = targetSibuCd;
    }
    /**
     * 対象支部名称取得処理
     * 
     * @return
     */
    public String getTargetSibuName() {
        return targetSibuName;
    }
    /**
     * 対象支部名称設定処理
     * 
     * @param targetSibuName
     */
    public void setTargetSibuName(String targetSibuName) {
        this.targetSibuName = targetSibuName;
    }
    /**
     * 対象管理番号取得処理
     * 
     * @return String
     */
    public String getTargetCkanriNo() {
        return targetCkanriNo;
    }
    /**
     * 対象管理番号設定処理
     * 
     * @param targetCkanriNo
     */
    public void setTargetCkanriNo(String targetCkanriNo) {
        this.targetCkanriNo = targetCkanriNo;
    }
    /**
     * 対象タイトル取得処理
     * 
     * @return
     */
    public String getTargetTitle() {
        return targetTitle;
    }
    /**
     * 対象タイトル設定処理
     * 
     * @param targetTitle
     */
    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }
    /**
     * 条件項目値クリア処理
     *
     */
    public void clearConditionData(){
        setTargetCkanriNo(null);
        setTargetTitle(null);
        setTargetShokuCd(null);
        setTargetShokuNameKna(null);
        setTargetSibuCd(null);
        setTargetSibuName(null);
        setTargetDateFrom(null);
        setTargetDateTo(null);
    }
    /**
     * @return listSibu を戻します。
     */
    public List getListSibu() {
        return (List)mapListSibu.get(new Integer(getWindowId()));
    }
    /**
     * @param listSibu 設定する listSibu。
     */
    public void setListSibu(List listSibu) {
        mapListSibu.put(new Integer(getWindowId()), listSibu);
        setSibu1CntFlg((listSibu != null && listSibu.size() == 1));
    }
    /**
     * @return sibu1CntFlg を戻します。
     */
    public boolean isSibu1CntFlg() {
        return ((Boolean)mapSibu1CntFlg.get(new Integer(getWindowId()))).booleanValue();
    }
    /**
     * @param sibu1CntFlg 設定する sibu1CntFlg。
     */
    public void setSibu1CntFlg(boolean sibu1CntFlg) {
        mapSibu1CntFlg.put(new Integer(getWindowId()), new Boolean(sibu1CntFlg));
    }
    /**
     * @return listDate を戻します。
     */
    public List getListDate() {
        return (List)mapListDate.get(new Integer(getWindowId()));
    }
    /**
     * @param listDate 設定する listDate。
     */
    public void setListDate(List listDate) {
        mapListDate.put(new Integer(getWindowId()), listDate);
        setDate1CntFlg((listDate != null && listDate.size() == 1));
    }
    /**
     * @return date1CntFlg を戻します。
     */
    public boolean isDate1CntFlg() {
        return ((Boolean)mapDate1CntFlg.get(new Integer(getWindowId()))).booleanValue();
    }
    /**
     * @param date1CntFlg 設定する date1CntFlg。
     */
    public void setDate1CntFlg(boolean date1CntFlg) {
        mapDate1CntFlg.put(new Integer(getWindowId()), new Boolean(date1CntFlg));
    }
    /**
     * @return listSearchData を戻します。
     */
    public List getListSearchData() {
        return (List)mapListSearchData.get(new Integer(getWindowId()));
    }
    /**
     * @param listSearchData 設定する listSearchData。
     */
    public void setListSearchData(List listListSearchData) {
        mapListSearchData.put(new Integer(getWindowId()), listListSearchData);
    }
    /**
     * @return listTitle を戻します。
     */
    public List getListTitle() {
        return (List)mapListTitle.get(new Integer(getWindowId()));
    }
    /**
     * @param listTitle 設定する listTitle。
     */
    public void setListTitle(List listTitle) {
        mapListTitle.put(new Integer(getWindowId()), listTitle);
        setTitle1CntFlg((listTitle != null && listTitle.size() == 1));
    }
    /**
     * @return title1CntFlg を戻します。
     */
    public boolean isTitle1CntFlg() {
        return ((Boolean)mapTitle1CntFlg.get(new Integer(getWindowId()))).booleanValue();
    }
    /**
     * @param title1CntFlg 設定する title1CntFlg。
     */
    public void setTitle1CntFlg(boolean title1CntFlg) {
        mapTitle1CntFlg.put(new Integer(getWindowId()), new Boolean(title1CntFlg));
    }
    /**
     * @return targetDateFrom を戻します。
     */
    public String getTargetDateFrom() {
        return targetDateFrom;
    }
    /**
     * @param targetDateFrom 設定する targetDateFrom。
     */
    public void setTargetDateFrom(String targetDateFrom) {
        this.targetDateFrom = targetDateFrom;
    }
    /**
     * @return targetDateTo を戻します。
     */
    public String getTargetDateTo() {
        return targetDateTo;
    }
    /**
     * @param targetDateTo 設定する targetDateTo。
     */
    public void setTargetDateTo(String targetDateTo) {
        this.targetDateTo = targetDateTo;
    }
    /**
     * @return listShokuhou を戻します。
     */
    public List getListShokuhou() {
        return (List)mapListShokuhou.get(new Integer(getWindowId()));
    }
    /**
     * @param listShokuhou 設定する listShokuhou。
     */
    public void setListShokuhou(List listShokuhou) {
        mapListShokuhou.put(new Integer(getWindowId()), listShokuhou);
        setShokuhou1CntFlg((listShokuhou != null && listShokuhou.size() == 1));
    }
    /**
     * @return date1CntFlg を戻します。
     */
    public boolean isShokuhou1CntFlg() {
        return ((Boolean)mapShokuhou1CntFlg.get(new Integer(getWindowId()))).booleanValue();
    }
    /**
     * @param date1CntFlg 設定する date1CntFlg。
     */
    public void setShokuhou1CntFlg(boolean flg) {
        mapShokuhou1CntFlg.put(new Integer(getWindowId()), new Boolean(flg));
    }
    /**
     * @return targetShokuCd を戻します。
     */
    public String getTargetShokuCd() {
        return targetShokuCd;
    }
    /**
     * @param targetShokuCd 設定する targetShokuCd。
     */
    public void setTargetShokuCd(String targetShokuCd) {
        this.targetShokuCd = targetShokuCd;
    }
    /**
     * @return targetShokuNameKna を戻します。
     */
    public String getTargetShokuNameKna() {
        return targetShokuNameKna;
    }
    /**
     * @param targetShokuNameKna 設定する targetShokuNameKna。
     */
    public void setTargetShokuNameKna(String targetShokuNameKna) {
        this.targetShokuNameKna = targetShokuNameKna;
    }
}
