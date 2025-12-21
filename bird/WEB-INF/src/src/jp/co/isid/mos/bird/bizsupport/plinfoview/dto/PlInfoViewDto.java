package jp.co.isid.mos.bird.bizsupport.plinfoview.dto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLDataInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 未入力オーナー一覧
 * @author xnkusama
 */
public class PlInfoViewDto implements DownloadDto, CsvOutputDto {

    /* CSVの出力モード */
    public static final String CSV_MODE_RC_KOSEHI_ARI  = "01";
    public static final String CSV_MODE_RC_KOSEHI_NASI = "02";
    public static final String CSV_MODE_FC_KINGAKU     = "03";
    public static final String CSV_MODE_FC_KOSEHI      = "04";
    public static final String CSV_MODE_GENKA_GENZAIRYO_INFO = "05";
    public static final String CSV_MODE_GENKA_YASAI_INFO     = "06";
    public static final String CSV_MODE_GENKA_HOUSO_INFO     = "07";
    public static final String CSV_MODE_GENKA_BUPPAN_INFO    = "08";
    public static final String CSV_MODE_GENKA_KONETUHI_INFO  = "09";
    public static final String CSV_MODE_GENKA_KEIHI_INFO     = "10";
    /* 検索条件 */
    //対象年月
    private Map condTargetYM = new HashMap();
    //対象店舗
    private Map condTaishoTenpo = new HashMap();
    //対象店舗（オーナー用）
    private Map condTaishoTenpoOner = new HashMap();
    //企業コード
    private String condCompanyCd;
    //事業本部
    private Map condJigyouCd = new HashMap();
    //営業エリア
    private Map condSlareaCd = new HashMap();
    //支部
    private Map condSibuCd = new HashMap();
    //ブロック
    private Map condBlockCd = new HashMap();
    //店コード
    private Map condMiseCd = new HashMap();
    //店コード（オーナー用）
    private Map condMiseCdOner = new HashMap();
    //店名称
    private Map condMiseName = new HashMap();
    //店オープン日
    private Map condMiseOpenDt = new HashMap();
    //店オーナーコード（店舗検索時の店舗オーナー）
    private Map condMiseOnerCd = new HashMap();
    //店オーナー名称(店舗検索時の店舗オーナー名称）
    private Map condMiseOnerName = new HashMap();
    //オーナー
    private Map condOnerCd = new HashMap();
    //オーナー名称
    private Map condOnerName = new HashMap();
    //店舗種別
    private Map condTenpoShu = new HashMap();
    //検索タイプ
    private Map condSearchType = new HashMap();
    //PLタイプ
    private Map condPlType = new HashMap();

//  add start xkhata 2007-02-13
    // 売上フラグ
    private boolean uriageFlg;
    
    private boolean fcRcFlg;
// add end
    
    /* システム情報 */
    //ログインユーザーID
    private String userId;
    //アプリ日付
    private String appDt;
    //システム日付
    private String sysDt;
    
    /* 検索タイプ RC:true */
    private Map bResultFcRc = new HashMap();
    
    /* 条件画面プルダウンリスト */
    //対象年月
    private List condListTargetYM;
    //事業本部
    private List condListJigyouCd;
    //営業エリア
    private List condListSlareaCd;
    //支部
    private List condListSibuCd;
    //ブロック
    private List condListBlockCd;
    //オーナー保有店舗
    private List condListOnerTenpo;
    
    /* ユーザー情報 */
    //ユーザータイプコード
    private String userTypeCd;
    //制限ユーザー true:制限あり
    private boolean limitKbn = false;
    
    /*検索結果*/
    private List listEntityTogetuPlusZennen;
    //本部用 当月、前年Entity
    private UIPLDataInfo entityTougetu;
    private UIPLDataInfo entityZennen;
    //直営用 当月、前年データList
    private List listRCTougetu;
    //最終検索時の対象店舗（店、オーナー検索の対処）
    private Map condLastSearchTaishoTenpo = new HashMap();
    

    /* 直営結果画面用データ保持 */
    // 指定年月の構成比計算元
    private BigDecimal bigKouseihiMoto;
    private BigDecimal bigKouseihiMotoZennen;

    /* CSV関連 */
    // CSV出力モード
    private Map csvMode = new HashMap();
    
    /* ウィンドウ管理 */
    // ウィンドウID
    private int windowId = 0;
    // 最大ウィンドウID
    private int maxWindowId = 0;
    // 共通フォーム呼出フラグ
    private Map callFormFlag = new HashMap();

    /* 検索条件退避 */
    //対象年月
    private Map tempTargetYM = new HashMap();
    //対象店舗
    private Map tempTaishoTenpo = new HashMap();
    //対象店舗（オーナー用）
    private Map tempTaishoTenpoOner = new HashMap();
    //事業本部
    private Map tempJigyouCd = new HashMap();
    //営業エリア
    private Map tempSlareaCd = new HashMap();
    //支部
    private Map tempSibuCd = new HashMap();
    //ブロック
    private Map tempBlockCd = new HashMap();
    //店コード
    private Map tempMiseCd = new HashMap();
    //店コード（オーナー用）
    private Map tempMiseCdOner = new HashMap();
    //オーナー
    private Map tempOnerCd = new HashMap();
    //店舗種別
    private Map tempTenpoShu = new HashMap();
    //検索タイプ
    private Map tempSearchType = new HashMap();
    
//    //売上フラグ
//// add start xkhata 2007/02/08
//    private Map tempUriagFlg = new HashMap();
//// add end

    public String getCondBlockCd() {
        return (String) condBlockCd.get(new Integer(getWindowId()));
	}
	public void setCondBlockCd(String condBlockCd) {
	    this.condBlockCd.put(new Integer(getWindowId()), condBlockCd);
	}
	public String getCondSlareaCd() {
        return (String) condSlareaCd.get(new Integer(getWindowId()));
	}
	public void setCondSlareaCd(String condSlareaCd) {
        this.condSlareaCd.put(new Integer(getWindowId()), condSlareaCd);
	}
	public String getCondJigyouCd() {
        return (String) condJigyouCd.get(new Integer(getWindowId()));
	}
	public void setCondJigyouCd(String condJigyouCd) {
        this.condJigyouCd.put(new Integer(getWindowId()), condJigyouCd);
	}
	public String getCondMiseCd() {
        return (String) condMiseCd.get(new Integer(getWindowId()));
	}
	public void setCondMiseCd(String condMiseCd) {
        this.condMiseCd.put(new Integer(getWindowId()), condMiseCd);
	}
	public String getCondOnerCd() {
        return (String) condOnerCd.get(new Integer(getWindowId()));
	}
	public void setCondOnerCd(String condOnerCd) {
        this.condOnerCd.put(new Integer(getWindowId()), condOnerCd);
	}
	public String getCondSibuCd() {
        return (String) condSibuCd.get(new Integer(getWindowId()));
	}
	public void setCondSibuCd(String condSibuCd) {
        this.condSibuCd.put(new Integer(getWindowId()), condSibuCd);
	}
	public String getCondTaishoTenpo() {
        return (String) condTaishoTenpo.get(new Integer(getWindowId()));
	}
	public void setCondTaishoTenpo(String condTaishoTenpo) {
        this.condTaishoTenpo.put(new Integer(getWindowId()), condTaishoTenpo);
	}
	public String getCondTargetYM() {
        return (String) condTargetYM.get(new Integer(getWindowId()));
	}
	public void setCondTargetYM(String condTargetYM) {
        this.condTargetYM.put(new Integer(getWindowId()), condTargetYM);
	}
	public String getCondTenpoShu() {
        return (String) condTenpoShu.get(new Integer(getWindowId()));
	}
	public void setCondTenpoShu(String condTenpoShu) {
        this.condTenpoShu.put(new Integer(getWindowId()), condTenpoShu);
	}
	public List getCondListBlockCd() {
		return condListBlockCd;
	}
	public void setCondListBlockCd(List condListBlockCd) {
		this.condListBlockCd = condListBlockCd;
	}
	public List getCondListSlareaCd() {
		return condListSlareaCd;
	}
	public void setCondListSlareaCd(List condListSlareaCd) {
		this.condListSlareaCd = condListSlareaCd;
	}
	public List getCondListJigyouCd() {
		return condListJigyouCd;
	}
	public void setCondListJigyouCd(List condListJigyouCd) {
		this.condListJigyouCd = condListJigyouCd;
	}
	public List getCondListSibuCd() {
		return condListSibuCd;
	}
	public void setCondListSibuCd(List condListSibuCd) {
		this.condListSibuCd = condListSibuCd;
	}
	public List getCondListTargetYM() {
		return condListTargetYM;
	}
	public void setCondListTargetYM(List condListTargetYM) {
		this.condListTargetYM = condListTargetYM;
	}
	public String getUserTypeCd() {
		return userTypeCd;
	}
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}
	public String getCondSearchType() {
        return (String) condSearchType.get(new Integer(getWindowId()));
	}
	public void setCondSearchType(String condSearchType) {
        this.condSearchType.put(new Integer(getWindowId()), condSearchType);
	}
	public String getCondTaishoTenpoOner() {
        return (String) condTaishoTenpoOner.get(new Integer(getWindowId()));
	}
	public void setCondTaishoTenpoOner(String condTaishoTenpoOner) {
        this.condTaishoTenpoOner.put(new Integer(getWindowId()), condTaishoTenpoOner);
	}
	public String getCondCompanyCd() {
		//TODO 2006/04/10企業コード固定 
        //      モス以外の企業コードが使われる場合は修正が必要
        condCompanyCd = "00";
		return condCompanyCd;
	}
	public void setCondCompanyCd(String condCompanyCd) {
		this.condCompanyCd = condCompanyCd;
	}
	public List getCondListOnerTenpo() {
		return condListOnerTenpo;
	}
	public void setCondListOnerTenpo(List condListOnerTenpo) {
		this.condListOnerTenpo = condListOnerTenpo;
	}
	public String getCondMiseCdOner() {
        return (String) condMiseCdOner.get(new Integer(getWindowId()));
	}
	public void setCondMiseCdOner(String condMiseCdOner) {
        this.condMiseCdOner.put(new Integer(getWindowId()), condMiseCdOner);
	}
	public UIPLDataInfo getEntityTougetu() {
        return entityTougetu;
	}
	public void setEntityTougetu(UIPLDataInfo entityTougetu) {
        this.entityTougetu = entityTougetu;
	}
	public UIPLDataInfo getEntityZennen() {
        return entityZennen;
	}
	public void setEntityZennen(UIPLDataInfo entityZennen) {
        this.entityZennen = entityZennen;
	}
	public String getCondPlType() {
        return (String) condPlType.get(new Integer(getWindowId()));
	}
	public void setCondPlType(String condPlType) {
        this.condPlType.put(new Integer(getWindowId()), condPlType);
	}
	public List getListEntityTogetuPlusZennen() {
        return listEntityTogetuPlusZennen;
	}
	public void setListEntityTogetuPlusZennen(List listEntityTogetuPlusZennen) {
        this.listEntityTogetuPlusZennen = listEntityTogetuPlusZennen;
	}
	public BigDecimal getBigKouseihiMoto() {
		return bigKouseihiMoto;
	}
	public void setBigKouseihiMoto(BigDecimal bigKouseihiMoto) {
		this.bigKouseihiMoto = bigKouseihiMoto;
	}
	public BigDecimal getBigKouseihiMotoZennen() {
		return bigKouseihiMotoZennen;
	}
	public void setBigKouseihiMotoZennen(BigDecimal bigKouseihiMotoZennen) {
		this.bigKouseihiMotoZennen = bigKouseihiMotoZennen;
	}
	public List getListRCTougetu() {
		return listRCTougetu;
	}
	public void setListRCTougetu(List listRCTougetu) {
		this.listRCTougetu = listRCTougetu;
	}
    /**
     * 結果画面のFC、RCモード
     * true:RC false:FC
     * @return
     */
    public boolean isResultFcRc() {
        Boolean fcRc = (Boolean) bResultFcRc.get(new Integer(getWindowId()));
        return (fcRc == null) ? false : fcRc.booleanValue();
    }
    public void setResultFcRC(boolean resultFcRc) {
        this.bResultFcRc.put(new Integer(getWindowId()), Boolean.valueOf(resultFcRc));
    }
    
    /**
     * 条件画面 対象条件プルダウン用List取得処理
     * @return
     */
    public List getListCondTaishoTenpo() {
        List listTaishoTenpo = new ArrayList();
        
        SelectItem item1 = new SelectItem("", "FC全社");
        SelectItem item2 = new SelectItem("2", "事業本部");
        SelectItem item3 = new SelectItem("3", "営業エリア");
        SelectItem item4 = new SelectItem("4", "支部");
        SelectItem item6 = new SelectItem("6", "店舗");
        SelectItem item7 = new SelectItem("7", "オーナー");
        
        if (!isLimitKbn()) {
            listTaishoTenpo.add(item1);
            listTaishoTenpo.add(item2);
        }
        listTaishoTenpo.add(item3);
        listTaishoTenpo.add(item4);
        listTaishoTenpo.add(item6);
        listTaishoTenpo.add(item7);
        
        return listTaishoTenpo;
    }

    /**
     * 条件画面（オーナー） 対象条件プルダウン用List取得処理
     * @return
     */
    public List getListCondTaishoTenpoOner() {
        List listTaishoTenpo = new ArrayList();
        
        SelectItem item1 = new SelectItem("", "全社");
        SelectItem item2 = new SelectItem("1", "本社");
        SelectItem item3 = new SelectItem("2", "全店合計");
        SelectItem item4 = new SelectItem("3", "全店平均");
        SelectItem item6 = new SelectItem("4", "店舗");
        
        listTaishoTenpo.add(item1);
        listTaishoTenpo.add(item2);
        listTaishoTenpo.add(item3);
        listTaishoTenpo.add(item4);
        listTaishoTenpo.add(item6);
        
        return listTaishoTenpo;
    }
	public String getCsvMode() {
        return (String) csvMode.get(new Integer(getWindowId()));
	}
	public void setCsvMode(String csvMode) {
        this.csvMode.put(new Integer(getWindowId()), csvMode);
	}
	public String getCondMiseName() {
        return (String) condMiseName.get(new Integer(getWindowId()));
	}
	public void setCondMiseName(String condMiseName) {
        this.condMiseName.put(new Integer(getWindowId()), condMiseName);
	}
    public String getCondMiseOpenDt() {
        return (String) condMiseOpenDt.get(new Integer(getWindowId()));
    }
    public void setCondMiseOpenDt(String condMiseOpenDt) {
        this.condMiseOpenDt.put(new Integer(getWindowId()), condMiseOpenDt);
    }
    public String getCondMiseOnerCd() {
        return (String) condMiseOnerCd.get(new Integer(getWindowId()));
    }
    public void setCondMiseOnerCd(String condMiseOnerCd) {
        this.condMiseOnerCd.put(new Integer(getWindowId()), condMiseOnerCd);
    }
    public String getCondMiseOnerName() {
        return (String) condMiseOnerName.get(new Integer(getWindowId()));
    }
    public void setCondMiseOnerName(String condMiseOnerName) {
        this.condMiseOnerName.put(new Integer(getWindowId()), condMiseOnerName);
    }
	public String getCondOnerName() {
        return (String) condOnerName.get(new Integer(getWindowId()));
	}
	public void setCondOnerName(String condOnerName) {
        this.condOnerName.put(new Integer(getWindowId()), condOnerName);
	}
//// add start xkhata
//    public boolean getUriageFlg() {
//        if ( this.uriageFlg.get(new Integer(getWindowId())) != null ) { 
//            return ((Boolean)this.uriageFlg.get(new Integer(getWindowId()))).booleanValue();
//        } else {
//            return false;
//        }
//    }
//    public void setUriageFlg(boolean uriageFlg) {
//        this.uriageFlg.put(new Integer(getWindowId()), new Boolean(uriageFlg));
//    }
    
// add end
	public boolean isLimitKbn() {
		return limitKbn;
	}
	public void setLimitKbn(boolean limitKbn) {
		this.limitKbn = limitKbn;
	}
	public String getAppDt() {
		return appDt;
	}
	public void setAppDt(String appDt) {
		this.appDt = appDt;
	}
	public String getSysDt() {
		return sysDt;
	}
	public void setSysDt(String sysDt) {
		this.sysDt = sysDt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public int getMaxWindowId() {
        return maxWindowId;
    }
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    public boolean isCallFormFlag() {
        Boolean flag = (Boolean) callFormFlag.get(new Integer(getWindowId()));
        return (flag == null) ? false : flag.booleanValue();
    }
    public void setCallFormFlag(boolean callFormFlag) {
        this.callFormFlag.put(new Integer(getWindowId()), Boolean.valueOf(callFormFlag));
    }
    // ウィンドウID生成
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    public String getTempTargetYM() {
        return (String) tempTargetYM.get(new Integer(getWindowId()));
    }
    public void setTempTargetYM(String tempTargetYM) {
        this.tempTargetYM.put(new Integer(getWindowId()), tempTargetYM);
    }
    public String getTempTaishoTenpo() {
        return (String) tempTaishoTenpo.get(new Integer(getWindowId()));
    }
    public void setTempTaishoTenpo(String tempTaishoTenpo) {
        this.tempTaishoTenpo.put(new Integer(getWindowId()), tempTaishoTenpo);
    }
    public String getTempTaishoTenpoOner() {
        return (String) tempTaishoTenpoOner.get(new Integer(getWindowId()));
    }
    public void setTempTaishoTenpoOner(String tempTaishoTenpoOner) {
        this.tempTaishoTenpoOner.put(new Integer(getWindowId()), tempTaishoTenpoOner);
    }
    public String getTempJigyouCd() {
        return (String) tempJigyouCd.get(new Integer(getWindowId()));
    }
    public void setTempJigyouCd(String tempJigyouCd) {
        this.tempJigyouCd.put(new Integer(getWindowId()), tempJigyouCd);
    }
    public String getTempSlareaCd() {
        return (String) tempSlareaCd.get(new Integer(getWindowId()));
    }
    public void setTempSlareaCd(String tempSlareaCd) {
        this.tempSlareaCd.put(new Integer(getWindowId()), tempSlareaCd);
    }
    public String getTempSibuCd() {
        return (String) tempSibuCd.get(new Integer(getWindowId()));
    }
    public void setTempSibuCd(String tempSibuCd) {
        this.tempSibuCd.put(new Integer(getWindowId()), tempSibuCd);
    }
    public String getTempBlockCd() {
        return (String) tempBlockCd.get(new Integer(getWindowId()));
    }
    public void setTempBlockCd(String tempBlockCd) {
        this.tempBlockCd.put(new Integer(getWindowId()), tempBlockCd);
    }
    public String getTempMiseCd() {
        return (String) tempMiseCd.get(new Integer(getWindowId()));
    }
    public void setTempMiseCd(String tempMiseCd) {
        this.tempMiseCd.put(new Integer(getWindowId()), tempMiseCd);
    }
    public String getTempMiseCdOner() {
        return (String) tempMiseCdOner.get(new Integer(getWindowId()));
    }
    public void setTempMiseCdOner(String tempMiseCdOner) {
        this.tempMiseCdOner.put(new Integer(getWindowId()), tempMiseCdOner);
    }
    public String getTempOnerCd() {
        return (String) tempOnerCd.get(new Integer(getWindowId()));
    }
    public void setTempOnerCd(String tempOnerCd) {
        this.tempOnerCd.put(new Integer(getWindowId()), tempOnerCd);
    }
    public String getTempTenpoShu() {
        return (String) tempTenpoShu.get(new Integer(getWindowId()));
    }
    public void setTempTenpoShu(String tempTenpoShu) {
        this.tempTenpoShu.put(new Integer(getWindowId()), tempTenpoShu);
    }
    public String getTempSearchType() {
        return (String) tempSearchType.get(new Integer(getWindowId()));
    }
    public void setTempSearchType(String tempSearchType) {
        this.tempSearchType.put(new Integer(getWindowId()), tempSearchType);
    }
    public String getCondLastSearchTaishoTenpo() {
        return (String) condLastSearchTaishoTenpo.get(new Integer(getWindowId()));
    }
    public void setCondLastSearchTaishoTenpo(String condLastSearchTaishoTenpo) {
        this.condLastSearchTaishoTenpo.put(new Integer(getWindowId()), condLastSearchTaishoTenpo);
    }

//  add start xkhata 2007/02/13
    public boolean getUriageFlg() {
        return this.uriageFlg;
    }
    public void setUriageFlg( boolean uriageFlg) {
        this.uriageFlg = uriageFlg;
    }

    public boolean getFcRcFlg() {
        return this.fcRcFlg;
    }
    public void setFcRcFlg(boolean fcRcFlg) {
        this.fcRcFlg = fcRcFlg;
    }
// add end
}