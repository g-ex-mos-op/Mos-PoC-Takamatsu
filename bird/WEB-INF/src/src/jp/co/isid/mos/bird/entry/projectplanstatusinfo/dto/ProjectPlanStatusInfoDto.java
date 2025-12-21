/*
 * 作成日: 2006/11/16
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 事業方針説明会申込状況確認Dto
 * 
 * @author xamaruyama
 */
public class ProjectPlanStatusInfoDto implements DownloadDto, CsvOutputDto{

    // 制限ユーザ判別用フラグ(初期化しない)
    private boolean limit;

    // システム日付(初期化しない)
    private String sysdate;

    // ユーザID(初期化しない)
    private String userId;

    // 支部プルダウンの内容
    // 会社コード
    private String companyCd = "00";

    // 支部コード
    private String sibuCd;

    // 支部プルダウンの内容
    private List sibuList;

    // 支部プルダウンで選ばれたもの
    private String sibuListChoice;

    // 対象条件プルダウンの内容
    private List taishouJokenList = new ArrayList();

    // 対象プルダウンの内容
    private List taishouList = new ArrayList();
    /**
     * 対象条件コース情報エンティティー
     */
    private Map entityUIStatusInfo = new HashMap();

    // 対象が事業方針、懇親会、共栄の場合の変数

    // 対象条件プルダウンで選ばれたもの
    private String taishouJokenChoice;

    // 対象プルダウンで選ばれたもの
    private String taishouListChoice;

    // 支部プルダウンで選ばれたもの
    private String sibuInfoChoice;

    // SVコードを入力した場合
    private String svCd;

    // 区分プルダウン
    private List attendKbn;

    // 区分プルダウンで選ばれたもの
    private String attendKbnChoice;
    /** 
     * 別画面経由用
     * 検索結果対象条件項目：支部コード
     */
    private Map pSibuCd = new HashMap();
    /** 
     * 別画面経由用
     * 検索結果対象条件項目：SVコード
     */
    private Map pSvCd = new HashMap();
    /** 
     * 別画面経由用
     * 検索結果対象条件項目：対象条件
     */
    private Map pTaishoJoken = new HashMap();
    /** 
     * 別画面経由用
     * 検索結果対象条件項目：対象
     */
    private Map pTaisho = new HashMap();
    /** 
     * 検索結果対象条件項目：支部コード
     */
    private Map spSibuCd = new HashMap();
    /** 
     * 検索結果対象条件項目：SVコード
     */
    private Map spSvCd = new HashMap();
    /** 
     * 検索結果対象条件項目：対象条件
     */
    private Map spTaishoJoken = new HashMap();
    /** 
     * 検索結果対象条件項目：対象
     */
    private Map spTaisho = new HashMap();
    /** 
     * 検索結果対象条件項目：区分
     */
    private Map spKbn = new HashMap();
    /**
     * 照会用出欠席情報
     */ 
    private Map sibuInfo = new HashMap();

    //出席オーナー数
    private Map onerAttendanceCnt = new HashMap();
    
    //欠席オーナー数
    private Map onerAbsentCnt = new HashMap();
    // 出席人数
    private Map attendCnt = new HashMap();
    // 欠席人数
    private Map absentCnt = new HashMap();
    // 未登録オーナー数
    private Map mitourokuOner = new HashMap();
    /**
     * 本部手配宿泊検索結果
     */
    private Map honbuTehaiList = new HashMap();
    
    // 手配をお願いした支部
    private Map tehaiSibu = new HashMap();
    
//  手配をお願いしたオーナーのコード
    private String tehaiOnerCd;
    
//   手配をお願いしたオーナーの名称
    private String tehaiOnerNameKj;
    
//   オーナーごとのシングル部屋の数
    private String singleCnt;

//  オーナーごとのツイン部屋の数
    private String twinCnt;

//   オーナーごとのシングル部屋の総和
    private String singleSum;

//   オーナーごとのツイン部屋の総和
    private String twinSum;

//  手配をお願いしたオーナーの備考
    private String tehaiNote;

//  支部名称
    private String sibuName;

// オーナーコード
    private String onerCd;

// オーナーの漢字名称
    private String onerNameKj;

//  出欠席の○×
    private String ShukketuKbn;

//  出席者カウント
    private String ShussekiCnt;

//  備考
    private String note;
    
//  CSV作成結果
    private List csvSakusei;
    
//  共通画面から遷移してきたことを表すフラグ
    private int initFlg;
    
    /**
     * CSVダウンロードタイプ判断フラグ
     * 
     * 0:結果ダウンロード以外
     * 1:結果ダウンロード
     */
    private String kekkaCsv;
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    
    /**
     * 現行ウィンドウIDでMapに保持されてる
     * 値を全てクリアします。
     *
     */
    public void clear(){
        paramClear();
        searchDataClear();
    }
    
    /**
     * 現行ウィンドウIDで
     * Mapに保持されてる条件パラメーター値
     *
     */
    public void paramClear(){
        setSibuCd(null);
        setSibuList(null);
        setSibuListChoice(null);
        setSvCd(null);
        setTaishouJokenChoice(null);
        setTaishouList(null);
        setEntityUIStatusInfo(null);
        setPSibuCd(null);
        setPSvCd(null);    
        setPTaishoJoken(null);
        setPTaisho(null);
        setSpSibuCd(null);
        setSpSvCd(null);
        setSpTaishoJoken(null);
        setSpTaisho(null);
        setSpKbn(null);
    }
    
    /**
     * 検索結果クリア処理
     *
     */
    public void searchDataClear(){
        //１．出欠席データ検索結果クリア
        setSibuInfo(null);
        setOnerAttendanceCnt(null);
        setOnerAbsentCnt(null);
        setOnerMitourokuCnt(null);
        setAttendCnt(null);
        setAbsentCnt(null);
        //２．宿泊データ検索結果クリア
        setHonbuTehaiList(null);
        setSingleSum(null);
        setTwinSum(null); 
    }
    
    /**
     *  照会用出欠席情報取得処理
     * @return
     */
    public List getSibuInfo() {
        return (List)sibuInfo.get(new Integer(getWindowId()));
    }
    /**
     *  照会用出欠席情報設定処理
     *  
     * @param sibuInfo
     */
    public void setSibuInfo(List sibuInfo) {
        this.sibuInfo.put(new Integer(getWindowId()), sibuInfo);
    }

    // 支部プルダウン情報
    public List getSibuList() {
        return sibuList;
    }

    public void setSibuList(List sibuList) {
        this.sibuList = sibuList;
    }
    
//  対象プルダウンで選ばれたもののsetterとgetter
    public List getTaishouJokenList() {
        return taishouJokenList;
    }

    public void setTaishoJokenList(List taishouJokenList) {
        this.taishouJokenList = taishouJokenList;
    }

    // SVコードのsetterとgetter
    public String getSvCd() {
        return svCd;
    }

    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }

//  対象プルダウンのsetterとgetter
    public List getTaishouList() {
        return taishouList;
    }

    public void setTaishouList(List taishouList) {
        this.taishouList = taishouList;
    }

    public List getAttendKbn() {
        return attendKbn;
    }

    public void setAttendKbn(List attendKbn) {
        this.attendKbn = attendKbn;
    }

    public String getSibuInfoChoice() {
        return sibuInfoChoice;
    }

    public void setSibuInfoChoice(String sibuInfoChoice) {
        this.sibuInfoChoice = sibuInfoChoice;
    }

    public String getTaishouJokenChoice() {
        return taishouJokenChoice;
    }

    public void setTaishouJokenChoice(String taishouJokenChoice) {
        this.taishouJokenChoice = taishouJokenChoice;
    }

    public String getTaishouListChoice() {
        return taishouListChoice;
    }

    public void setTaishouListChoice(String taishouListChoice) {
        this.taishouListChoice = taishouListChoice;
    }

    public String getAttendKbnChoice() {
        return attendKbnChoice;
    }

    public void setAttendKbnChoice(String kbn) {
        this.attendKbnChoice = kbn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOnerCd() {
        return onerCd;
    }

    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    public String getOnerNameKj() {
        return onerNameKj;
    }

    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }

    public String getShussekiCnt() {
        return ShussekiCnt;
    }

    public void setShussekiCnt(String ShussekiCnt) {
        this.ShussekiCnt = ShussekiCnt;
    }

    public String getSibuName() {
        return sibuName;
    }

    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }

    public String getShukketuKbn() {
        return ShukketuKbn;
    }

    public void setShukketuKbn(String ShukketuKbn) {
        this.ShukketuKbn = ShukketuKbn;
    }

    // 会社コードのsetterとgetter
    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    // 支部コードのsetterとgetter
    public String getSibuCd() {
        return sibuCd;
    }

    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    /**
     * 出席オーナー取得処理
     * 
     * @return
     */
    public BigDecimal getOnerAttendanceCnt() {
        return (BigDecimal)onerAttendanceCnt.get(new Integer(getWindowId()));
    }
    /**
     * 出席オーナー設定処理
     * 
     * @param onerAttendanceCnt
     */
    public void setOnerAttendanceCnt(BigDecimal onerAttendanceCnt) {
        this.onerAttendanceCnt.put(new Integer(getWindowId()), onerAttendanceCnt);
    }
    /**
     * 欠席オーナー取得処理
     * @return
     */
    public BigDecimal getOnerAbsentCnt() {
        return (BigDecimal)onerAbsentCnt.get(new Integer(getWindowId()));
    }
    /**
     * 欠席オーナー設定処理
     * @param absentOner
     */
    public void setOnerAbsentCnt(BigDecimal absentOner) {
        this.onerAbsentCnt.put(new Integer(getWindowId()), absentOner);
    }

    /**
     * 未登録オーナー取得処理
     * 
     * @return mitourokuOner
     */
    public BigDecimal getOnerMitourokuCnt() {
        return (BigDecimal)mitourokuOner.get(new Integer(getWindowId()));
    }

    /**
     * 未登録オーナー設定処理
     * 
     * @param mitourokuOner
     */
    public void setOnerMitourokuCnt(BigDecimal mitourokuOner) {
        this.mitourokuOner.put(new Integer(getWindowId()), mitourokuOner);
    }
    /**
     * 欠席人数取得処理
     * 
     * @return mitourokuOner
     */
    public BigDecimal getAbsentCnt() {
        return (BigDecimal)absentCnt.get(new Integer(getWindowId()));
    }

    /**
     * 欠席人数設定処理
     * 
     * @param absentCnt
     */
    public void setAbsentCnt(BigDecimal absentCnt) {
        this.absentCnt.put(new Integer(getWindowId()), absentCnt);
    }
    /**
     * 出席人数取得処理
     * @return
     */
    public BigDecimal getAttendCnt() {
        return (BigDecimal)attendCnt.get(new Integer(getWindowId()));
    }
    /**
     * 出席人数設定処理
     * @param attendCnt
     */
    public void setAttendCnt(BigDecimal attendCnt) {
        this.attendCnt.put(new Integer(getWindowId()), attendCnt);
    }

    public List getHonbuTehaiList() {
        return (List)honbuTehaiList.get(new Integer(getWindowId()));
    }

    public void setHonbuTehaiList(List honbuTehaiList) {
        this.honbuTehaiList.put(new Integer(getWindowId()), honbuTehaiList);
    }

    public String getSingleCnt() {
        return singleCnt;
    }

    public void setSingleCnt(String singleCnt) {
        this.singleCnt = singleCnt;
    }

    public String getTehaiNote() {
        return tehaiNote;
    }

    public void setTehaiNote(String tehaiNote) {
        this.tehaiNote = tehaiNote;
    }

    public String getTehaiOnerCd() {
        return tehaiOnerCd;
    }

    public void setTehaiOnerCd(String tehaiOnerCd) {
        this.tehaiOnerCd = tehaiOnerCd;
    }

    public String getTehaiOnerNameKj() {
        return tehaiOnerNameKj;
    }

    public void setTehaiOnerNameKj(String tehaiOnerNameKj) {
        this.tehaiOnerNameKj = tehaiOnerNameKj;
    }
    /**
     * 対象条件「支部」本部手配宿泊用取得処理
     * @return
     */
    public String getTehaiSibu() {
        return (String)tehaiSibu.get(new Integer(getWindowId()));
    }
    /**
     * 対象条件「支部」本部手配宿泊用設定処理
     * @param tehaiSibu
     */
    public void setTehaiSibu(String tehaiSibu) {
        this.tehaiSibu.put(new Integer(getWindowId()), tehaiSibu);
    }

    public String getTwinCnt() {
        return twinCnt;
    }

    public void setTwinCnt(String twinCnt) {
        this.twinCnt = twinCnt;
    }

    public String getSingleSum() {
        return singleSum;
    }

    public void setSingleSum(String singleSum) {
        this.singleSum = singleSum;
    }

    public String getTwinSum() {
        return twinSum;
    }

    public void setTwinSum(String twinSum) {
        this.twinSum = twinSum;
    }

    public String getSibuListChoice() {
        return sibuListChoice;
    }

    public void setSibuListChoice(String sibuListChoice) {
        this.sibuListChoice = sibuListChoice;
    }

    public List getCsvSakusei() {
        return csvSakusei;
    }

    public void setCsvSakusei(List csvSakusei) {
        this.csvSakusei = csvSakusei;
    }

    public int getInitFlg() {
        return initFlg;
    }

    public void setInitFlg(int initFlg) {
        this.initFlg = initFlg;
    }
    /**
     * @return maxWindowId を戻します。
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }

    /**
     * @param maxWindowId 設定する maxWindowId。
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }

    /**
     * @return windowId を戻します。
     */
    public int getWindowId() {
        return windowId;
    }

    /**
     * @param windowId 設定する windowId。
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    /**
     * @return entityUIStatusInfo を戻します。
     */
    public UIStatusInfo getEntityUIStatusInfo() {
        return (UIStatusInfo)entityUIStatusInfo.get(new Integer(getWindowId()));
    }

    /**
     * @param entityUIStatusInfo 設定する entityUIStatusInfo。
     */
    public void setEntityUIStatusInfo(UIStatusInfo entityUIStatusInfo) {
        this.entityUIStatusInfo.put(new Integer(getWindowId()), entityUIStatusInfo);
    }
    public boolean isEmptyEntityUIStatusInfo() {
        if(getEntityUIStatusInfo() == null) {
            return true;
        }
        return false;
    }
    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 別画面経由用
     * 
     * @return pSibuCd を戻します。
     */
    public String getPSibuCd() {
        return (String)pSibuCd.get(new Integer(getWindowId()));
    }
    /**
     * 別画面経由用
     * 
     * @param pSibuCd 設定する sibuCd。
     */
    public void setPSibuCd(String sibuCd) {
        this.pSibuCd.put(new Integer(getWindowId()), sibuCd);
    }
    /**
     * 別画面経由用
     * 
     * @return pSvCd を戻します。
     */
    public String getPSvCd() {
        return (String)pSvCd.get(new Integer(getWindowId()));
    }
    /**
     * 別画面経由用
     * 
     * @param pSvCd 設定する svCd。
     */
    public void setPSvCd(String svCd) {
        this.pSvCd.put(new Integer(getWindowId()), svCd);
    }
    /**
     * 別画面経由用
     * 
     * @return pTaisho を戻します。
     */
    public String getPTaisho() {
        return (String)pTaisho.get(new Integer(getWindowId()));
    }
    /**
     * 別画面経由用
     * 
     * @param pTaisho 設定する spTaisho。
     */
    public void setPTaisho(String taisho) {
        this.pTaisho.put(new Integer(getWindowId()), taisho);
    }
    /**
     * 別画面経由用
     * 
     * @return pTaishoJoken を戻します。
     */
    public String getPTaishoJoken() {
        return (String)pTaishoJoken.get(new Integer(getWindowId()));
    }
    /**
     * 別画面経由用
     * 
     * @param pTaishoJoken 設定する taishoJoken。
     */
    public void setPTaishoJoken(String taishoJoken) {
        this.pTaishoJoken.put(new Integer(getWindowId()), taishoJoken);
    }

    /**
     * @return spSibuCd を戻します。
     */
    public String getSpSibuCd() {
        return (String)spSibuCd.get(new Integer(getWindowId()));
    }
    /**
     * @param spSibuCd 設定する spSibuCd。
     */
    public void setSpSibuCd(String spSibuCd) {
        this.spSibuCd.put(new Integer(getWindowId()), spSibuCd);
    }
    /**
     * @return spSvCd を戻します。
     */
    public String getSpSvCd() {
        return (String)spSvCd.get(new Integer(getWindowId()));
    }
    /**
     * @param spSvCd 設定する spSvCd。
     */
    public void setSpSvCd(String spSvCd) {
        this.spSvCd.put(new Integer(getWindowId()), spSvCd);
    }
    /**
     * @return spTaisho を戻します。
     */
    public String getSpTaisho() {
        return (String)spTaisho.get(new Integer(getWindowId()));
    }
    /**
     * @param spTaisho 設定する spTaisho。
     */
    public void setSpTaisho(String spTaisho) {
        this.spTaisho.put(new Integer(getWindowId()), spTaisho);
    }
    /**
     * @return spTaishoJoken を戻します。
     */
    public String getSpTaishoJoken() {
        return (String)spTaishoJoken.get(new Integer(getWindowId()));
    }
    /**
     * @param spTaishoJoken 設定する spTaishoJoken。
     */
    public void setSpTaishoJoken(String spTaishoJoken) {
        this.spTaishoJoken.put(new Integer(getWindowId()), spTaishoJoken);
    }
    /**
     * @return kbn を戻します。
     */
    public String getSpKbn() {
        return (String)spKbn.get(new Integer(getWindowId()));
    }
    /**
     * @param kbn 設定する spTaishoJoken。
     */
    public void setSpKbn(String kbn) {
        this.spKbn.put(new Integer(getWindowId()), kbn);
    }

    /**
     * 現ウィンドウID のCSV検索対象条件項目保管処理
     * 
     * 保管対象パラメーター
     * ・対象条件
     * ・対象
     * ・支部コード
     * ・SVコード
     *
     */
    public void holdCsvParam(){
       
        setSpTaishoJoken(getTaishouJokenChoice());
        setSpTaisho(getTaishouListChoice());
        setSpSibuCd(getSibuListChoice());
        setSpSvCd(getSvCd());
        setSpKbn(null);
    }
    /**
     * 現ウィンドウID の保管データから
     * 検索対象条件項目への設定処理
     * 
     * 保管対象パラメーター
     * ・対象条件
     * ・対象
     * ・支部コード
     * ・SVコード
     *
     */
    public void settingJokenParam(){
        setTaishouJokenChoice(getPTaishoJoken());
        setTaishouListChoice(getPTaisho());
        setSibuListChoice(getPSibuCd());
        setSvCd(getPSvCd());
    }
    /**
     * 現ウィンドウID の検索対象条件項目保管処理
     * 
     * 保管対象パラメーター
     * ・対象条件
     * ・対象
     * ・支部コード
     * ・SVコード
     *
     */
    public void holdJokenParam(){
        setPTaishoJoken(getTaishouJokenChoice());
        setPTaisho(getTaishouListChoice());
        setPSibuCd(getSibuListChoice());
        setPSvCd(getSvCd());
    }


    /**
     * 
     * @return kekkaCsv を戻します。
     */
    public String getKekkaCsv() {
        return kekkaCsv;
    }


    /**
     * @param kekkaCsv 設定する kekkaCsv。
     */
    public void setKekkaCsv(String kekkaCsv) {
        this.kekkaCsv = kekkaCsv;
    }
    /**
     * 
     * @return
     */
    public boolean isKekka(){
        if(!ProjectPlanStatusInfoUtil.isNull(getKekkaCsv()) 
                && getKekkaCsv().equals("1")){
            return true;
        }
        return false;
    }
}