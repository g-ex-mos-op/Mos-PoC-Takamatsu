package jp.co.isid.mos.bird.bizadmin.svtantousibu.dto;

import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * リモート閲覧支部・SV担当店舗登録DTO
 * @author xnkusama
 *
 */
public class SvTantouSibuDto implements CsvOutputDto{

    /* 画面制御関連プロパティ */
    // ウィンドウID
    private int windowId = 0;
    // 最大ウィンドウID
    private int maxWindowId = 0;
    // 画面用セッションKey
    private String viewSessionKey;
    // セッションKey
    private HashMap sessionKeyMap = new HashMap();
    // 処理エラー（条件画面から「実行」押下時にクリアする）
    private boolean processError = false;

    /* 共通 */
    // BirdUserInfo
    private BirdUserInfo birdUserInfo;
    
    /* 検索条件 */
    // 処理選択 1:リモート閲覧支部登録　2:SV担当店ダウンロード　3:SV担当店アップロード登録
    private HashMap processMode = new HashMap();
    // 会社コード
    private String companyCd = "00";
    // ユーザーID
    private HashMap userId = new HashMap();
    // ユーザー名称
    private HashMap userName = new HashMap();
    // 支部コード
    private HashMap sibuCd = new HashMap();
    // 支部プルダウン
    private HashMap listSibu = new HashMap();
    // SV未設定店のみ
    private HashMap flgMisettei = new HashMap();
    // アップロードファイル
    private HashMap uploadedFile = new HashMap();
    // システム日付
    private String sysDate;
    
    /* リモート閲覧支部登録 */
    // 登録済みデータ
    private HashMap listDbData = new HashMap();
    // 画面編集用リスト
    private HashMap listEditData = new HashMap();
    
    /* CSVアップロード */
    // エラーフラグ
    private HashMap uploadError = new HashMap();
    // 登録内容リスト
    private HashMap listUploadData = new HashMap();
    // エラーデータ
    private HashMap listUploadErrorData = new HashMap();
    
    /**
     * メニューから遷移した時のDTOクリア処理
     */
    public void clearInitDto() {
        //ウィンドウID更新
        updateWindowid();
        //ユーザーID
        setUserId("");
        //対象支部
        setSibuCd("");
        //処理選択初期化：リモート閲覧支部登録
        setProcessMode(SvTantouSibuCommon.PROCESS_MODE_REGIST_REMOTE_SIBU);
        //SV未設定店のみ初期化：チェックOff
        setFlgMisettei(SvTantouSibuCommon.MISETTEITEN_ONLY_OFF);
    }
    
    /**
     * ウィンドウIDを取得します。
     * @return　ウィンドウID
     */
    public int getWindowId() {
        return windowId;
    }
    
    /**
     * ウィンドウIDを設定します。
     * @param windowId　ウィンドウID
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    /**
     * viewSessionKey取得
     */
    public String getViewSessionKey() {
        return this.viewSessionKey;
    }
    
    /**
     * viewSessionKey取得
     * @param viewSessionKey
     */
    public void setViewSessionKey( String viewSessionKey ) {
        this.viewSessionKey = viewSessionKey;
    }

    /**
     * sessionKey取得
     */
    public String getSessionKey() {
        return (String) sessionKeyMap.get(new Integer(getWindowId()));
    }
    
    /**
     * sessionKey取得
     * @param sessionKey
     */
    public void setSessioniKey( String sessionKey ) {
        this.sessionKeyMap.put(new Integer(getWindowId()), sessionKey);
    }

    /**
     * 最大ウィンドウID取得
     * @return
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    /**
     * 最大ウィンドウID設定
     * @param int
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    
    /**
     * ウィンドウID生成
     */ 
    private int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    /**
     * ウィンドウID更新
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    public String getProcessMode() {
        return (String) processMode.get(new Integer(getWindowId()));
    }

    public void setProcessMode(String processMode) {
        this.processMode.put(new Integer(getWindowId()), processMode);
    }

    public String getUserId() {
        return (String) userId.get(new Integer(getWindowId()));
    }

    public void setUserId(String userId) {
        this.userId.put(new Integer(getWindowId()), userId);
    }

    public List getListSibu() {
        return (List) listSibu.get(new Integer(getWindowId()));
    }

    public void setListSibu(List listSibu) {
        this.listSibu.put(new Integer(getWindowId()), listSibu);
    }

    public String getSibuCd() {
        return (String) sibuCd.get(new Integer(getWindowId()));
    }

    public void setSibuCd(String sibuCd) {
        this.sibuCd.put(new Integer(getWindowId()), sibuCd);
    }

    public UploadedFile getUploadedFile() {
        return (UploadedFile) uploadedFile.get(new Integer(getWindowId()));
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile.put(new Integer(getWindowId()), uploadedFile);
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public boolean getFlgMisettei() {
        return ((Boolean) flgMisettei.get(new Integer(getWindowId()))).booleanValue();
    }

    public void setFlgMisettei(boolean flgMisettei) {
        this.flgMisettei.put(new Integer(getWindowId()), new Boolean(flgMisettei));
    }

    public String getUserName() {
        return (String) userName.get(new Integer(getWindowId()));
    }

    public void setUserName(String userName) {
        this.userName.put(new Integer(getWindowId()), userName);
    }

    public List getListDbData() {
        return (List) listDbData.get(new Integer(getWindowId()));
    }

    public void setListDbData(List listDbData) {
        this.listDbData.put(new Integer(getWindowId()), listDbData);
    }

    public List getListEditData() {
        return (List) listEditData.get(new Integer(getWindowId()));
    }

    public void setListEditData(List listEditData) {
        this.listEditData.put(new Integer(getWindowId()), listEditData);
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public boolean isUploadError() {
        return ((Boolean) uploadError.get(new Integer(getWindowId()))).booleanValue();
   }

    public void setUploadError(boolean uploadError) {
        this.uploadError.put(new Integer(getWindowId()), new Boolean(uploadError));
    }

    public List getListUploadData() {
        return (List) listUploadData.get(new Integer(getWindowId()));
    }

    public void setListUploadData(List listUploadData) {
        this.listUploadData.put(new Integer(getWindowId()), listUploadData);
    }

    public List getListUploadErrorData() {
        return (List) listUploadErrorData.get(new Integer(getWindowId()));
    }

    public void setListUploadErrorData(List listUploadErrorData) {
        this.listUploadErrorData.put(new Integer(getWindowId()), listUploadErrorData);
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public boolean isProcessError() {
        return processError;
    }

    public void setProcessError(boolean processError) {
        this.processError = processError;
    }
}
