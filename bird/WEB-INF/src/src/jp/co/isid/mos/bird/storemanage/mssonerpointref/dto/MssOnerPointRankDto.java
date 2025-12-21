package jp.co.isid.mos.bird.storemanage.mssonerpointref.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.mssonerpointref.entity.CodSibu;

/**
 * 個店管理 ミステリーショッパーズ オーナー別獲得ポイントランクDto
 * 
 * @author xkinu
 */
public class MssOnerPointRankDto {

    /** ユーザータイプコード：01:本部 */
    public static final String USERTYPE_HONBU = "01";
    /** ユーザータイプコード：02:オーナー */
    public static final String USERTYPE_ONER = "02";
    /** ユーザータイプコード：03:店舗 */
    public static final String USERTYPE_MISE = "03";
    /** 対象条件：支部 */
    public static final String TAISHO_SIBU = "SIBU";
    /** 対象条件：オーナー */
    public static final String TAISHO_ONER = "ONER";
    /** 対象条件：個店 */
    public static final String TAISHO_MISE = "MISE";
    //画面ID
    private String viewId;
    // ユーザーID
    private String userId;
    // 今日の日付
    private String today;
    // アプリ日付
    private String appdate;
    //ユーザータイプコード
    private String usertypeCd;
    //ユーザー支部制限判断フラグ
    private boolean limitFlg = false;
    //対象店舗数
    private int taishoTenpoCnt;
    /** 画面遷移区分 */
    private int scenechangedKbn = -1;
    //条件画面：会社コード
    private String companyCd;
    //条件画面：会社名称
    private String companyName;
    //条件画面：支部コード
    private String sibuCd;
    //条件画面：支部名称
    private String sibuName;
    //条件画面：オーナーコード
    private String onerCd;
    //条件画面：オーナー名称
    private String onerName;
    //条件画面：店舗コード
    private String miseCd;
    //条件画面：店舗名称
    private String miseName;
    //条件画面：SVコード
    private String svCd;
    //条件画面：SV名称
    private String svName;
    /** 
     * 条件項目：対象条件
     *    ・支部：SIBU
     *    ・オーナー：ONER
     *    ・個店：MISE
     */
    private String searchType;
    /** 
     * 条件項目：年度
     */
    private String nendo;
    /** 
     * 条件項目：期間指定（1番目）
     */
    private String kai;
    /**
     * 条件画面：会社リスト
     */
    private List companyList;   
    /**
     * 条件画面：表示対象支部リスト
     */
    private List sibuList;
    /**
     * 条件画面：年度リスト
     */
    private List nendoList;
    /**
     * 条件画面：回リスト
     */
    private List kaiList;

    /**
     * 初期クリア処理
     *
     */
    public void initClear() {
        setCompanyList(null);
        setCompanyCd(null);
        setCompanyName(null);
        setSearchType(null);
        setSibuList(null);
        setSibuCd(null);
        setSibuName(null);
        setOnerCd(null);
        setOnerName(null);
        setMiseCd(null);
        setMiseName(null);
        setSvCd(null);
        setSvName(null);
        setNendoList(null);
        setNendo(null);
        setKaiList(null);
        setKai(null);
    }
    /**
     * 会社コードリスト 取得処理
     * 
     * @return listCompany を戻します。
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 会社コードリスト 設定処理
     * 
     * @param companyList を設定。
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }
    /**
     * 表示対象 支部コードリスト 取得処理
     * 
     * @return sibuList を戻します。
     */
    public List getSibuList() {
        return sibuList;
    }
    /**
     * 表示対象 支部コードリスト 設定処理
     * 
     * @param sibuList を設定。
     */
    public void setSibuList(List list) {
        this.sibuList = list;
    }
    /**
     * 表示対象 支部リスト件数 取得処理
     * 
     * @return sibuList を戻します。
     */
    public int getSibuListSize() {
        if(sibuList == null) {
            return 0;
        }
        return sibuList.size();
    }
    /**
     * 支部コード 取得処理
     * 
     * @return sibuCd を戻します。
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コード 設定処理
     * 
     * @param sibuCd を設定。
     */
    public void setSibuCd(String cd) {
        this.sibuCd = cd;
    }
    /**
     * 支部名称取得処理
     * 
     * 支部コードリストから
     * 引数のコードをキーに名称を取得します。
     * @return sibuName を戻します。
     */
    public String getSibuName(String cd) {
        if(getSibuList() == null) {
            return null;
        }
        for (int i = 0; i < getSibuList().size(); i++) {
            CodSibu data = (CodSibu) getSibuList().get(i);
            if(data.getSibuCd().equals(cd)) {
                return data.getSibuName();
            }
        }
        return  "";
    }
    /**
     * 支部名称 取得処理
     * 
     * @return sibuName を戻します。
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称 設定処理
     * 
     * @param sibuName を設定。
     */
    public void setSibuName(String name) {
        this.sibuName = name;
    }
    /**
     * オーナーコード 取得処理
     * 
     * @return onerCd を戻します。
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコード 設定処理
     * 
     * @param onerCd を設定。
     */
    public void setOnerCd(String cd) {
        this.onerCd = cd;
    }
    /**
     * オーナー名称 取得処理
     * 
     * @return onerName を戻します。
     */
    public String getOnerName() {
        return  onerName;
    }

    /**
     * オーナー名称設定処理
     * 
     * @param onerName を設定。
     */
    public void setOnerName(String name) {
        this.onerName = name;
    }
    /**
     * 個店コード 取得処理
     * 
     * @return miseCd を戻します。
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 個店コード 設定処理
     * 
     * @param miseCd を設定。
     */
    public void setMiseCd(String cd) {
        this.miseCd = cd;
    }
    /**
     * 個店名称 取得処理
     * 
     * @return miseName を戻します。
     */
    public String getMiseName() {
        return  miseName;
    }

    /**
     * 個店名称設定処理
     * 
     * @param miseName を設定。
     */
    public void setMiseName(String name) {
        this.miseName = name;
    }
    /**
     * 対象条件 取得処理
     * 
     * @return searchType を戻します。
     */
    public String getSearchType() {
        return searchType;
    }
    /**
     * 対象条件 設定処理
     * 
     * @param searchType を設定。
     */
    public void setSearchType(String cd) {
        this.searchType = cd;
    }
    /**
     * 対象条件名称 取得処理
     * 
     * @return String を戻します。
     */
    public String getSearchTypeName() {
        if(getSearchType().equals(TAISHO_SIBU)) {
            return "支部";
        }
        else if(getSearchType().equals(TAISHO_ONER)) {
            return "オーナー";
        }
        else if(getSearchType().equals(TAISHO_MISE)) {
            return "個店";
        }
        return  "";
    }
    /**
     * SVコード 設定処理
     * 
     * @param svCd を設定。
     */
    public void setSvCd(String cd) {
        this.svCd = cd;
    }
    /**
     * SVコード 取得処理
     * 
     * @return svCd を戻します。
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * SV名称 取得処理
     * 
     * @return name を戻します。
     */
    public String getSvName() {
        return svName;
    }
    /**
     * SV名称 設定処理
     * 
     * @param name を設定。
     */
    public void setSvName(String name) {
        this.svName = name;
    }
    /**
     * 対象期間リスト 取得処理
     * 
     * @return nendoList を戻します。
     */
    public List getKaiList() {
        return kaiList;
    }
    /**
     * 対象期間リスト 取得処理
     * 
     * @return nendoList を戻します。
     */
    public int getKaiListSize() {
        if(kaiList == null) {
            return 0;
        }    
        return kaiList.size();
    }
    /**
     * 対象期間リスト 設定処理
     * 
     * @param nendoList を設定。
     */
    public void setKaiList(List list) {
        this.kaiList = list;
    }
    /**
     * 対象期間 取得処理
     * 
     * @return kai を戻します。
     */
    public String getKai() {
        return kai;
    }
    /**
     * 対象期間 設定処理
     * 
     * @param kai を設定。
     */
    public void setKai(String cd) {
        this.kai = cd;
    }
    /**
     * 年度 設定処理
     * 
     * @param nendo を設定。
     */
    public void setNendo(String cd) {
        this.nendo = cd;
    }
    /**
     * 年度 取得処理
     * 
     * @return nendo を戻します。
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 画面表示年度3年度分リスト 取得処理
     * 
     * @return nendoList を戻します。
     */
    public List getNendoList() {
        return nendoList;
    }
    /**
     * 画面表示年度3年度分 設定処理
     * @param nendoList を設定。
     */
    public void setNendoList(List list) {
        this.nendoList = list;
    }
    /**
     * 画面表示年度3年度分件数 取得処理
     * 
     * @param int 件数を取得。
     */
    public int getNendoListSize() {
        if(nendoList == null) {
            return 0;
        }    
        return this.getNendoList().size();
    }

    /**
     * 会社コード取得処理
     * 
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コード設定処理
     * 
     * @param companyCd を設定。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * 会社名称取得処理
     * 
     * @return companyName を戻します。
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称取得処理
     * 
     * 会社コードリストから
     * 引数のコードをキーに名称を取得します。
     * @return companyName を戻します。
     */
    public String getCompanyName(String cd) {
        if(getCompanyList() == null) {
            return null;
        }
        for (int i = 0; i < getCompanyList().size(); i++) {
        	CodCompany data = (CodCompany) getCompanyList().get(i);
        	if(data.getCompanyCd().equals(cd)) {
        		return data.getCompanyName();
        	}
        }
        return  "";
    }
    /**
     * 会社名称設定処理
     * 
     * @param companyName を設定。
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 画面遷移区分取得処理
     * 
     * @return scenechangedKbn を戻します。
     */
    public int getScenechangedKbn() {
        return scenechangedKbn;
    }
    /**
     * 画面遷移区分設定処理
     * 
     * @param scenechangedKbn を設定。
     */
    public void setScenechangedKbn(int kbn) {
        this.scenechangedKbn = kbn;
    }


    /**
     * @return viewId を戻します。
     */
    public String getViewId() {
        return viewId;
    }
    /**
     * @param id viewId を設定。
     */
    public void setViewId(String id) {
        this.viewId = id;
    }
    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 支部制限判断フラグ取得処理
     * 
     * @return limitFlg を戻します。
     */
    public boolean isLimit() {
        return limitFlg;
    }
    /**
     * 支部制限判断フラグ設定処理
     * 
     * @param flg を設定。
     */
    public void setLimit(boolean flg) {
        this.limitFlg = flg;
    }
    /**
     * システム日付設定処理
     * YYYYMMDD型です。
     * @return today を設定します。
     */
    public void setToday(String date) {
        this.today = date;
    }
    /**
     * システム日付取得処理
     * YYYYMMDD型です。
     * @return today を戻します。
     */
    public String getToday() {
        return today;
    }
    /**
     * アプリ日付設定処理
     * YYYYMMDD型です。
     * @return appdate を設定します。
     */
    public void setAppDate(String date) {
        this.appdate = date;
    }
    /**
     * アプリ日付取得処理
     * YYYYMMDD型です。
     * @return appdate を戻します。
     */
    public String getAppDate() {
        return appdate;
    }
    /**
     * @param userId userId を取得します。
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * 
     * @param userId userId を設定。
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
    }
    /**
     * @param taishoTenpoCnt を取得します。
     */
    public int getTaishoTenpoCnt() {
        return taishoTenpoCnt;
    }
    /**
     * @param taishoTenpoCnt を取得します。
     */
    public String getTaishoTenpoCntComma() {
        NumericFormatter numFmt = new NumericFormatter();
        return numFmt.format(String.valueOf(taishoTenpoCnt));
    }
    /**
     * 
     * @param taishoTenpoCntを設定。
     */
    public void setTaishoTenpoCnt(int cnt) {
        this.taishoTenpoCnt = cnt;
    }
    
    /**
     * 推移表データ検索パラメーター処理
     * 
     * @param BizReportRefDto dto 画面データ保持クラス
     * @return
     * @throws ApplicationException
     */
    public Map createMapJokenParam() 
    throws ApplicationException {
        // ロジックパラメーター
        Map map = new HashMap();
        //システム日付
        map.put("sysDate", getToday());
        //ユーザーID
        map.put("userId", getUserId());
        //ユーザータイプコード
        map.put("userTypeCd", getUsertypeCd());
        //支部制限フラグ
        map.put("limit", Boolean.valueOf(isLimit()));
        //会社コード
        map.put("companyCd", getCompanyCd());
        map.put("companyName", getCompanyName());
        //対象条件
        String searchType = getSearchType();
        map.put("searchType", searchType);
        map.put("searchTypeName", getSearchTypeName());
        CodeFormatter cdf = new CodeFormatter(5, "00000");
        cdf.setFormatPattern("00000");
        CodeFormatter cdf8 = new CodeFormatter(8, "00000000");
        cdf8.setFormatPattern("00000000");
        if(searchType != null && searchType.equals(TAISHO_SIBU)) {
            map.put("sibuCd", getSibuCd());
            map.put("sibuName", getSibuName());
            String svCd = getSvCd();
            if(svCd != null && svCd.length() > 0){
                svCd = cdf8.format(svCd, true);
            }
            map.put("svCd", svCd);
            map.put("svuName", getSvName());
            setSvCd(svCd);
        }
        if(searchType != null && searchType.equals(TAISHO_ONER)) {
            String onerCd = getOnerCd();
            if(onerCd != null && onerCd.length() > 0){
                onerCd = cdf.format(onerCd, true);
            }
            map.put("onerCd", onerCd);
            map.put("oneName", getOnerName());
            setOnerCd(onerCd);
        }
        if(searchType != null && searchType.equals(TAISHO_MISE)) {
            String miseCd =getMiseCd();
            if(miseCd != null && miseCd.length() > 0){
                miseCd = cdf.format(miseCd, true);
            }
            map.put("miseCd", miseCd);
            map.put("miseName", getMiseName());
            setMiseCd(miseCd);
        }
        //実施年度
        map.put("nendo", getNendo());
        //実施回数
        map.put("kai", getKai());
        return map;
    }

}