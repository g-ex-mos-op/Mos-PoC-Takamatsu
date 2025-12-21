package jp.co.isid.mos.bird.bizsupport.tempstorereplace.dto;

import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UIJigyoList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UISibuList;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;



/**
 * 仮店舗置換えメンテナンスDTO
 * 
 * @author Aspac
 */
public class TempStoreReplaceDto implements CsvOutputDto {

    
    /**
     * ユーザID
     */
    private String userid;
    
    /**
     * システム日付け
     */
    private String sysdate;
    
    /**
     * selectIndex
     */
    private int selectIndex;
    
    
    /**
     * 置換対象データ有無フラグ
     */
    private boolean processFlg = true;
    
    
    
    /****************************
     * 画面選択
     ******************************/

    /**
     * 会社コード
     */
    private String condCompanyCd;
    
    /**
     * 対象年度
     */
    private String condNendo;
    
    
    /**
     * 置換確定フラグ
     * ※0:確定店舗情報を含まない 1:含む 
     */
    private String fixedKind = "1";
    
    
    /**
     * 対象条件コード
     * 0:全社 1:事業本部 2:支部
     */
    private String condTargetCd;
    
    /**
     * 支部コード
     */
    private String condSibuCd;
    
    /**
     * 支部名称
     */
    private String condSibuName;
    
    /**
     * 事業コード
     */
    private String condJigyouCd;
    
    /**
     * 事業名称
     */
    private String condJigyoName;
    
    
    /****************************
     * 画面選択リスト
     ******************************/
    

    
    /**
     * 会社情報リスト
     */
    private List listCompany;
    
    /**
     * 年度プルダウンリスト
     */
    private List listNendo;
    
    /**
     * 対象条件プルダウンリスト
     */
    private List listTargetSearch;
    
    /**
     * 事業プルダウンリスト
     */
    private List listJigyo;
    
    /**
     * 支部プルダウンリスト
     */
    private List listSibu;

    
    /****************************
     * その他
     ******************************/
    
    
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 対象店舗種別
     * 全社？事業本部？支部？
     */
    private String targetStoreShu;
    
    /**
     * 確定フラグ文言
     */
    private String fixedStr;
    
    /**
     * 仮店舗置換え状況リスト
     */
    private List listState;
    
    
    /**
     * 編集画面確認メッセージ
     */
    private static final String CONFIRM_MSG = "編集中の内容がある場合は破棄されます。よろしいですか？";
    

    /**
     * 編集画面確認メッセージを取得します。
     * @return 編集画面確認メッセージ
     */
    public String getConfirmMsg() {
        return CONFIRM_MSG;
    }
    public void setConfirmMsg(String str) {
    }

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userid;
    }
    /**
     * ユーザIDを設定します。
     * @param ユーザID
     */
    public void setUserId(String userid) {
        this.userid = userid;
    }
    
    
    /**
     * システム日付けを取得します。
     * @return システム日付け
     */
    public String getSysdate() {
        return sysdate;
    }
    /**
     * システム日付けを設定します。
     * @param システム日付け
     */
    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }
    

    
    /**
     * 画面セレクトインデックスを取得します。
     * @return 画面セレクトインデックス
     */
    public int getSelectIndex() {
        return selectIndex;
    }
    /**
     * 画面セレクトインデックスを設定します。
     * @param 画面セレクトインデックス
     */
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    

    /**
     * 置換対象データ有無フラグを取得します。
     * @return 置換対象データ有無フラグ
     */
    public boolean isProcessFlg() {
        return processFlg;
    }
    /**
     * 置換対象データ有無フラグを設定します。
     * @return 置換対象データ有無フラグ
     */
    public void setProcessFlg(boolean processFlg) {
        this.processFlg = processFlg;
    }
    
    

    /**
     * 会社情報リストを取得します。
     * @return 会社情報リスト 
     */
    public List getListCompany() {
        return listCompany;
    }
    /**
     * 会社情報リストを設定します。
     * @return 会社情報リスト 
     */
    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }
    

    /**
     * 年度プルダウンリストを取得する
     * @return 年度プルダウンリスト
     */
    public List getListNendo() {
        return listNendo;
    }
    /**
     * 年度プルダウンリストを設定する
     * @param 年度プルダウンリスト
     */
    public void setListNendo(List listNendo) {
        this.listNendo = listNendo;
    }
    
    
    

    /**
     * 対象条件プルダウンリストを取得する
     * @return 対象条件プルダウンリスト
     */
    public List getListTargetSearch() {
        return listTargetSearch;
    }
    /**
     * 対象条件プルダウンリスト設定する
     * @param 対象条件プルダウンリスト
     */
    public void setListTargetSearch(List listTargetSearch) {
        this.listTargetSearch = listTargetSearch;
    }
    
    

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCondCompanyCd() {
        return condCompanyCd;
    }
    /**
     * 会社コードを設定します。
     * @param 会社コード
     */
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }

    

    /**
     * 対象年度を取得します。
     * @return 対象年度
     */
    public String getCondNendo() {
        return condNendo;
    }
    /**
     * 対象年度を設定します。
     * @param 対象年度
     */
    public void setCondNendo(String condNendo) {
        this.condNendo = condNendo;
    }
    
    
    
    /**
     * 置換確定フラグを取得します。
     * @return 置換確定フラグ
     */    
    public String getFixedKind() {
        return fixedKind;
    }
    /**
     * 置換確定フラグを設定します。
     * @param 置換確定フラグ
     */
    public void setFixedKind(String fixedKind) {
        this.fixedKind = fixedKind;
    }
    
    
    
    
    /**
     * 対象条件コードを取得します。
     * @return 対象条件コード
     */
    public String getCondTargetCd() {
        return condTargetCd;
    }
    /**
     * 対象条件コードを設定します。
     * @param 対象条件コード
     */
    public void setCondTargetCd(String condTargetCd) {
        this.condTargetCd = condTargetCd;
    }
    
    
    
    /**
     * 事業コードを取得します。
     * @return 事業コード
     */
    public String getCondJigyouCd() {
        return condJigyouCd;
    }
    /**
     * 事業コードを設定します。
     * @param 事業コード
     */
    public void setCondJigyouCd(String condJigyouCd) {
        this.condJigyouCd = condJigyouCd;
    }
    
    
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getCondSibuCd() {
        return condSibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param 支部コード
     */
    public void setCondSibuCd(String condSibuCd) {
        this.condSibuCd = condSibuCd;
    }
    
    
    /**
     * 事業名称を取得します。
     * @return 事業名称
     */
    public String getCondJigyoName() {
        return condJigyoName;
    }
    /**
     * 事業名称を設定します。
     * @param 事業名称
     */
    public void setCondJigyoName(String condJigyoName) {
        this.condJigyoName = condJigyoName;
    }
    
    
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getCondSibuName() {
        return condSibuName;
    }
    /**
     * 支部名称を設定します。
     * @param 支部名称
     */
    public void setCondSibuName(String condSibuName) {
        this.condSibuName = condSibuName;
    }
 
    
    
    
    /**
     * 事業プルダウンリストを取得します。
     * @return 事業プルダウンリスト
     */
    public List getListJigyo() {
        return listJigyo;
    }
    /**
     * 事業プルダウンリストを設定します。
     * @param 事業プルダウンリスト
     */
    public void setListJigyo(List listJigyo) {
        this.listJigyo = listJigyo;
    }
    
    
    
    /**
     * 支部プルダウンリストを取得します。
     * @return 支部プルダウンリスト
     */
    public List getListSibu() {
        return listSibu;
    }
    /**
     * 支部プルダウンリストを設定します。
     * @param 支部プルダウンリスト
     */
    public void setListSibu(List listSibu) {
        this.listSibu = listSibu;
    }

    
    /**
     * 仮店舗置換え状況リストを取得します。
     * @return 仮店舗置換え状況リスト 
     */
    public List getListState() {
        return listState;
    }
    /**
     * 仮店舗置換え状況リストを設定します。
     * @param 仮店舗置換え状況リスト 
     */
    public void setListState(List listState) {
        this.listState = listState;
    }
    
    
    /**
     * 企業名称を取得します。
     * @return
     */
    public String getCompanyName() {
        
        List lstComp = getListCompany();
        String compCd = getCondCompanyCd();
        for (Iterator ite = lstComp.iterator(); ite.hasNext();) {
            CodCompany compInfo = (CodCompany) ite.next();
            if(compInfo.getCompanyCd().equals(compCd)){
                setCompanyName(compInfo.getCompanyName());
            }
        }
        
        return companyName;
    }
    /**
     * 企業名称を設定します。
     * @return
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
    
    /**
     * 対象店舗種別名を取得します。
     * 全社？事業本部？支部？
     * @return
     */
    public String getTargetStoreShu() {
        String targetStoreShu = "";
        if(this.condTargetCd.equals("0"))
            targetStoreShu = "全店";
        else if(this.condTargetCd.equals("1"))
            targetStoreShu = getCondJigyoName();
        else if(this.condTargetCd.equals("2"))
            targetStoreShu = getCondSibuName();
        
        return targetStoreShu;
    }
    public void setTargetStoreShu(String targetStoreShu) {
        this.targetStoreShu = targetStoreShu;
    }
    
    
    /**
     * 置換え確定店舗情報を含む？含まない？
     */
    public String getFixedStr() {
        String fixed = "";
        if(fixedKind.equals("1")) fixed = "含む";
        else fixed = "含まない";
        return fixed;
    }
    public void setFixedStr(String fixedStr) {
        this.fixedStr = fixedStr;
    }
    
    
    /**
     * 初期化
     * 
     */
    public void reset(){
       setFixedKind("1");
       setCondTargetCd("0");
       setCondCompanyCd("00");
       List listNendo = this.getListNendo();
       if(listNendo!=null && listNendo.size() > 0){
           SelectItem item = (SelectItem)listNendo.get(0);
           setCondNendo(item.getValue().toString());
       }
       List listSibu = this.getListSibu();
       if(listSibu!=null && listSibu.size() > 0){
           UISibuList item = (UISibuList)listSibu.get(0);
           setCondSibuCd(item.getSibuCd());
       }
       List listJigyo = this.getListJigyo();
       if(listJigyo!=null && listJigyo.size() > 0){
           UIJigyoList item = (UIJigyoList)listJigyo.get(0);
           setCondJigyouCd(item.getJigyouCd());
       }
    }

}
