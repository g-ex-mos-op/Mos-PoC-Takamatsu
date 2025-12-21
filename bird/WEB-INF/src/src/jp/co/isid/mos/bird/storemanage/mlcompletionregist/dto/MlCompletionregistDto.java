package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIKenshuInfo;

/**
 * マスタライセンス研修修了登録Dto
 * 
 * @author xkinu
 */
public class MlCompletionregistDto  implements CsvOutputDto {
	/**　マスターライセンスエントリーコード保持定数 */
	private static final String sEntryCd[] = 
		new String[] {MlCompletionregistAction.ENTRY_CD_BASIC
			,         MlCompletionregistAction.ENTRY_CD_TRIP
			,         MlCompletionregistAction.ENTRY_CD_RENEWAL};
	/** マスターライセンスエントリー名称保持定数 */
	private static final String sEntryNameKj[] = 
		new String[] {"ベーシック研修", "出張ベーシック研修", "マスターライセンス更新研修"};
	/** マスターライセンスエントリー名称保持定数 */
	private static final String sEntryNameR[] = 
		new String[] {"BASIC", "TRIP", "RENEW"};
	/** マスターライセンスエントリー情報保持定数 */
	private static Hashtable hEntryList = new Hashtable();
	static {
		for (int i = 0; i<sEntryCd.length; i++) {
			hEntryList.put(sEntryCd[i]
				, new String[] {sEntryNameKj[i], sEntryNameR[i]});
		}
	};
	/** 画面遷移区分：初期値 [-1] */
	public static final int SCENECHANGE_KBN_INIT = -1;
	/** 画面遷移区分：現行画面からの遷移時 [0] */
	public static final int SCENECHANGE_KBN_SELF = 0;
	/** 画面遷移区分：条件画面から編集画面への遷移時 [1] */
	public static final int SCENECHANGE_KBN_COND_TO_EDIT = 1;
	/** 画面遷移区分：編集画面から確認画面への遷移時 [2] */
	public static final int SCENECHANGE_KBN_EDIT_TO_CONF = 2;
	/** 画面遷移区分：確認画面から編集画面の遷移(戻り)時 [3] */
	public static final int SCENECHANGE_KBN_CONF_TO_EDIT = 3;
	/** 画面遷移区分：編集画面から条件画面への遷移(戻り)時 [4] */
	public static final int SCENECHANGE_KBN_EDIT_TO_COND = 4;
	
    // ユーザーID
    private String userId;
    // 今日の日付
    private String today;
    //ユーザータイプコード
    private String usertypeCd;
    //制限区分
    private boolean limit;

    /** 画面遷移区分 */
    private int scenechangedKbn = SCENECHANGE_KBN_INIT;

    
    /**
     * 条件画面：会社リスト
     * 
     * ＜2006/04/13現在＞
     * 　将来的にトモスや四季菜のスタッフ管理も踏まえて
     * 　会社欄を設けるが、当面は「モス」固定。（デフォルト）
     */
    private List companyList;
    /**
     * 条件画面：研修選択タイトルデータ
     * 
     * エントリコードをKeyに各研修対象の
     * タイトルデータを格納されます。
     * 
     * mapEntryTitle.put("01", ベーシック研修List);
     * mapEntryTitle.put("05", 出張ベーシック研修List);
     * mapEntryTitle.put("30", マスターライセンス更新研修List);
     */
    private Map mapEntryTitle;
    /**
     * 条件画面：支部リスト
     */
    private List sibuList;
    //条件画面：エントリーコード
    private String entryCd;
    //条件画面：エントリー年
    private String entryYear;
    //条件画面：エントリー回
    private String entryKai;
    //条件画面：エントリータイトル
    private String entryTitle;
    //条件画面：会社コード
    private String companyCd;
    //条件画面：会社名称
    private String companyName;
    //条件画面：支部コード
    private String sibuCd;
    //条件画面：支部名称
    private String sibuName;
    //条件画面：オプション
    private boolean optionFlg;
    //編集画面：プルダウンリスト・コース
    private List courseList;
    //編集画面：プルダウンリスト・コース 選択値
    private String courseListIndex;
    // 編集画面 一括設定コース状況
    private String paramCourseStatus;
    // 編集画面 一括設定年月SETUP_YM
    private String setUpYm;
    
    /* 条件画面：対象研修コード */
    private String targetCd;
    /* 条件画面：対象研修コード (ベーシック用)*/
    private String basicTargetCd;
    /* 条件画面：対象研修コード (出張用)*/
    private String tripTargetCd;
    /* 条件画面：対象研修コード (更新用)*/
    private String renewalTargetCd;
    /**
     * 登録：対象リスト
     */
    private List registDataList;
    /**
     * CSV：出力対象リスト
     */
    private List csvList;
    
    
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /** 研修修了情報全クリア可能ユーザ判断フラグ*/
    private boolean allClear = false;
    /**
     * 初期クリア処理
     *
     */
    public void initClear() {
    	setEntryCd(null);
    	setEntryYear(null);
    	setEntryKai(null);
    	setEntryTitle(null);
    	setBasicTargetCd(null);
    	setTripTargetCd(null);
    	setRenewalTargetCd(null);
    	setCompanyCd(null);
    	setCompanyName(null);
    	setSibuCd(null);
    	setSibuName(null);
    	setTargetCd(null);
    	setOptionFlg(false);
    	setCsvList(null);
    	setRegistDataList(null);
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
     * 支部コードリスト 取得処理
     * 
     * @return sibuList を戻します。
     */
    public List getSibuList() {
        return sibuList;
    }
    /**
     * 支部コードリスト 設定処理
     * 
     * @param sibuList を設定。
     */
    public void setSibuList(List sibuList) {
        this.sibuList = sibuList;
    }
    /**
     * 支部コード取得処理
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
    	if(!isNull(getSibuCd())) {
    		setSibuName(getSibuName(cd));
    	}
    	else {
    		setSibuName("全支部");
    	}
    }
    /**
     * ターゲットコード 取得処理
     * 
     * @return targetCd を戻します。
     */
    public String getTargetCd() {
        return targetCd;
    }
    /**
     * ターゲットコード 設定処理
     * 
     * @param targetCd を設定。
     */
    private void setTargetCd(String cd) {
        this.targetCd = cd;
    }
    /**
     * ベーシックターゲットコード 設定処理
     * 
     * @param targetCd を設定。
     */
    public void setBasicTargetCd(String cd) {
    	basicTargetCd = cd;
    }
    /**
     * ベーシックターゲットコード 設定処理
     * 
     * @param targetCd を設定。
     */
    public String getBasicTargetCd() {
        return basicTargetCd;
    }
    /**
     * 出張研修ターゲットコード 取得処理
     * 
     * @param targetCd を設定。
     */
    public String getTripTargetCd() {
        return tripTargetCd;
    }
   /**
     * 出張研修ターゲットコード 設定処理
     * 
     * @param targetCd を設定。
     */
    public void setTripTargetCd(String cd) {
    	tripTargetCd = cd;
    }
    /**
     * 更新研修ターゲットコード 取得処理
     */
    public String getRenewalTargetCd() {
        return renewalTargetCd;
    }
    /**
     * 更新研修ターゲットコード 設定処理
     * 
     * @param targetCd を設定。
     */
    public void setRenewalTargetCd(String cd) {
    	renewalTargetCd = cd;
    }
    /**
     * エントリーコード 取得処理
     * 
     * @return entryCd を戻します。
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコード 設定処理
     * 
     * @param entryCd を設定。
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    /**
     * エントリー年 取得処理
     * 
     * @return entryYear を戻します。
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー回 取得処理
     * @param targetCd
     * @return
     */
    private String getEntryYear(final String targetCd) {
        if(!isNull(targetCd)) {
        	List list = getEntryList(getEntryCd());
	        if(list != null) {
	            for (int i = 0; i < list.size(); i++) {
	            	UIKenshuInfo data = (UIKenshuInfo) list.get(i);
	            	if(data.getTargetCd().equals(getTargetCd())) {
	            		return data.getEntryYear();
	            	}
	            }
        	}
        }
        return "";
    }
    /**
     * エントリー年 設定処理
     * 
     * @param entryYear を設定。
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    /**
     * エントリー回 取得処理     * 
     * @return entryKai を戻します。
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回 取得処理
     * @param targetCd
     * @return
     */
    private String getEntryKai(final String targetCd) {
        if(!isNull(targetCd)) {
        	List list = getEntryList(getEntryCd());
	        if(list != null) {
	            for (int i = 0; i < list.size(); i++) {
	            	UIKenshuInfo data = (UIKenshuInfo) list.get(i);
	            	if(data.getTargetCd().equals(getTargetCd())) {
	            		return data.getEntryKai();
	            	}
	            }
        	}
        }
        return "";
    }
    /**
     * エントリー回 設定処理
     * 
     * @param entryKai を設定。
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    /**
     * エントリータイトル 取得処理
     * @return entryTitle を戻します。
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトル 取得処理
     * @param targetCd
     * @return
     */
    private String getEntryTitle(final String targetCd) {
        if(!isNull(targetCd)) {
        	List list = getEntryList(getEntryCd());
	        if(list != null) {
	            for (int i = 0; i < list.size(); i++) {
	            	UIKenshuInfo data = (UIKenshuInfo) list.get(i);
	            	if(data.getTargetCd().equals(getTargetCd())) {
	            		return data.getEntryTitle();
	            	}
	            }
        	}
        }
        return "";
    }
    /**
     * エントリータイトル 設定処理
     * 
     * @param entryTitle を設定。
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    /**
     * 研修漢字名称取得処理
     * 
     * @return companyNameKj を戻します。
     */
    public String getEntryNameKj() {
        if(!isNull(getEntryCd())) {
        	String[] namas = (String[]) hEntryList.get(getEntryCd());
        	return namas[0];
        }
        return "";
    }
    /**
     * 研修ローマ字名称取得処理
     * 
     * @return companyNameR を戻します。
     */
    public String getEntryNameR() {
        if(!isNull(getEntryCd())) {
        	String[] namas = (String[]) hEntryList.get(getEntryCd());
        	return namas[1];
        }
        return "";
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
    	if(!isNull(getCompanyCd())) {
    		setCompanyName(getCompanyName(companyCd));
    	}
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
    private String getCompanyName(String cd) {
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
     * 支部名称取得処理
     * 
     * @return sibuName を戻します。
     */
    public String getSibuName() {
        return  sibuName;
    }

    /**
     * 支部名称取得処理
     * 支部コードリストから引数のコードをキーに名称を取得します。
     * @return sibuName を戻します。
     */
    private String getSibuName(String cd) {

        if(sibuList != null && sibuList.size() > 0){

            for (int i = 0; i < getSibuList().size(); i++) {
                MstSibu data = (MstSibu) getSibuList().get(i);
                if(data.getSibuCd().equals(cd)) {
                    return data.getSibuName();
                }
            }
        }
        return  "";
    }
    /**
     * 支部名称設定処理
     * 
     * @param condSibuCd を設定。
     */
    private void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    /**
     * オプション取得処理
     * 
     * 0:全て対象
     * 1:エントリー者のみ表示
     * @return optionFlg を戻します。
     */
    public boolean getOptionFlg() {
        return optionFlg;
    }
    /**
     * オプション設定処理
     * 
     * false:全て対象
     * true :エントリー者のみ表示
     * @param optionFlg を設定。
     */
    public void setOptionFlg(boolean optionFlg) {
        this.optionFlg = optionFlg;
    }
    /**
     * オプション文言取得処理
     * @return String 文言
     */
    public String getOptionNote() {
    	if(getOptionFlg()) {
    		return "エントリー者のみ表示";
    	}
    	else {
    		return "全て表示";
    	}
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
     * 研修選択タイトルデータ取得処理
     * 
     * @return mapEntryTitle を戻します。
     */
    public Map getMapEntryTitle() {
        return mapEntryTitle;
    }
    /**
     * 研修選択タイトルデータ設定処理
     * 
     * @param mapEntryTitle を設定。
     */
    public void setMapEntryTitle(Map mapEntryTitle) {
        this.mapEntryTitle = mapEntryTitle;
    }
    
    private List getTriningList(final String entryCd) {
    	return (List) getMapEntryTitle().get(entryCd);
    }

    /**
     * ベーシック研修プルダウンリストデータ取得
     * 
     * @return List を戻します。
     */
    public List getBasicTrainingList() {
        return getTriningList(MlCompletionregistAction.ENTRY_CD_BASIC);
    }
    /**
     * ベーシック研修プルダウンリストデータ件数取得処理
     * 
     * @return int 件数を戻します。
     */
    public int getBasicTrainingListSize() {
    	if(getBasicTrainingList() != null) {
    		return getBasicTrainingList().size();
    	}
    	return 0;
    }
    /**
     * 出張研修プルダウンリストデータ取得
     * 
     * @return List を戻します。
     */
    public List getTripTrainingList() {
        return getTriningList(MlCompletionregistAction.ENTRY_CD_TRIP);
    }
    /**
     * 出張研修プルダウンリストデータ件数取得処理
     * 
     * @return int 件数を戻します。
     */
    public int getTripTrainingListSize() {
    	if(getTripTrainingList() != null) {
    		return getTripTrainingList().size();
    	}
    	return 0;
    }
    /**
     * 更新研修プルダウンリストデータ設定
     * 
     * @return List を戻します。
     */
    public List getRenewalTrainingList() {
        return getTriningList(MlCompletionregistAction.ENTRY_CD_RENEWAL);
    }
    /**
     * 更新研修プルダウンリストデータ件数取得処理
     * 
     * @return int 件数を戻します。
     */
    public int getRenewalTrainingListSize() {
    	if(getRenewalTrainingList() != null) {
    		return getRenewalTrainingList().size();
    	}
    	return 0;
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
     * YYYYMMDD型です。
     * @return today を戻します。
     */
    public String getToday() {
        return today;
    }
    /**
     * 
     * @param userId userId を設定。
     */
    public void setToday(String date) {
        this.today = date;
    }
    /**
     * YYYYMMDD型です。
     * @return today を戻します。
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
     * 登録対象リスト 取得処理
     * 
     * @return csvList を戻します。
     */
    public List getCsvList() {
        return csvList;
    }
    /**
     * 登録対象リスト 設定処理
     * 
     * @param csvList を設定。
     */
    public void setCsvList(List csvList) {
        this.csvList = csvList;
    }
    /**
     * @return courseList を戻します。
     */
    public List getCourseList() {
        return courseList;
    }
    /**
     * @param courseList を設定。
     */
    public void setCourseList(List courseList) {
        this.courseList = courseList;
    }
    
	/**
	 * @return courseListIndex を戻します。
	 */
	public String getCourseListIndex() {
		return courseListIndex;
	}
	/**
	 * @param courseListIndex courseListIndex を設定。
	 */
	public void setCourseListIndex(String courseListIndex) {
		this.courseListIndex = courseListIndex;
	}
	
	/**
	 * @return paramCourseStatus を戻します。
	 */
	public String getParamCourseStatus() {
		return paramCourseStatus;
	}
	/**
	 * @param paramCourseStatus paramCourseStatus を設定。
	 */
	public void setParamCourseStatus(String paramCourseStatus) {
		this.paramCourseStatus = paramCourseStatus;
	}
	
	/**
	 * @return setUpYm を戻します。
	 */
	public String getSetUpYm() {
		return setUpYm;
	}
	/**
	 * @param setUpYm setUpYm を設定。
	 */
	public void setSetUpYm(String setUpYm) {
		this.setUpYm = setUpYm;
	}
    /**
     * 登録対象リスト 設定処理
     * 
     */
    public void setRegistDataList(List registData) {
        registDataList = registData;
    }
    /**
     * 登録対象リスト 取得処理
     * 
     * @return registDataList を戻します。
     */
    public List getRegistDataList() {
        return registDataList;
    }
    /**
     *  登録対象リストデータ件数取得処理
     * 
     * @return int 件数を戻します。
     */
    public int getRegistDataListSize() {
    	if(getRegistDataList() != null) {
    		return getRegistDataList().size();
    	}
    	return 0;
    }
    /**
     * 条件選択対象エントリー情報設定処理
     * 事前処理
     *
     */
    public void setEntryData() {

        //<1> ベーシック研修の時
        if(isBasicEntry()) {
        	setTargetCd(getBasicTargetCd());
        }
        
        //<2> 出張ベーシック研修の時
        if(isTripEntry()) {
        	setTargetCd(getTripTargetCd());
        }
        
        //<3> マスターライセンス更新研修の時
        if(isRenewalEntry()) {
        	setTargetCd(getRenewalTargetCd());
        }

        //共通的な処理
        if(!isNull(getTargetCd())) {
        	setEntryTitle(getEntryTitle(getTargetCd()));
        	setEntryYear(getEntryYear(getTargetCd()));
        	setEntryKai(getEntryKai(getTargetCd()));
        }
    }

    /**
     * エントリー情報取得処理
     * 
     * 引数のエントリーコード対象の
     * エントリー情報リストの取得を行います。
     * @param entryCd
     * @return
     */
    private List getEntryList(final String entryCd) {
    	List list = null;
    	if(entryCd.equals(MlCompletionregistAction.ENTRY_CD_BASIC)) {
    		list = (List) getBasicTrainingList();
    	}
    	else if(entryCd.equals(MlCompletionregistAction.ENTRY_CD_TRIP)) {
    		list = (List) getTripTrainingList();
    	}
    	else if(entryCd.equals(MlCompletionregistAction.ENTRY_CD_RENEWAL)) {
    		list = (List) getRenewalTrainingList();
    	}
    	return list;
    }

    /**
     * ベーシック研修判断処理
     * 選択された研修が「ベーシック研修」か否かを判別します。
     * @return true･･･ベーシック研修である、false･･･ベーシック研修でない
     */
    public boolean isBasicEntry() {
        return getEntryCd().equals(MlCompletionregistAction.ENTRY_CD_BASIC);
    }

    /**
     * 更新研修判断処理
     * 選択された研修が「更新研修」か否かを判別します。
     * @return true･･･更新研修である、false･･･更新研修でない
     */
    public boolean isRenewalEntry() {
    	return getEntryCd().equals(MlCompletionregistAction.ENTRY_CD_RENEWAL);
    }

    /**
     * 出張ベーシック研修判断処理
     * 選択された研修が「出張ベーシック研修」か否かを判別します。
     * @return true･･･出張ベーシック研修である、false･･･出張研修でない
     */
    public boolean isTripEntry() {
        return getEntryCd().equals(MlCompletionregistAction.ENTRY_CD_TRIP);
    }

    /**
     * CSV用項目ヘッダリスト
     * @return
     * @throws ApplicationException
     */
    public List getListCsvTableHeader() {
    	List listCsvTableHeader = new ArrayList();
   		listCsvTableHeader.add("オーナーコード");
        listCsvTableHeader.add("オーナー名称");
        listCsvTableHeader.add("支部取込コード");
        listCsvTableHeader.add("支部取込名称");
        listCsvTableHeader.add("店コード");
        listCsvTableHeader.add("店名称");
		listCsvTableHeader.add("スタッフ氏名");
		listCsvTableHeader.add("修了状況");
		listCsvTableHeader.add("年月");
       if(isTripEntry()) {
    		listCsvTableHeader.add("点数");
        }
        return listCsvTableHeader;
    }
    /**
     * StringオブジェクトNull(又は空)判断処理
     * 
     * @param str
     * @return
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * 登録対象エントリーが存在するか否か。
     * 
     * 
     * @return
     */
    public boolean isHaveTrainingLists() {
        int listCnt = getBasicTrainingListSize()+getTripTrainingListSize()+getRenewalTrainingListSize();
        return (listCnt>0);
        
    }
    /**
     * 初期値エントリーコード設定処理
     * 
     * @return
     */
    public void setInitEntryCd() {
        if(isEntryDefault(MlCompletionregistAction.ENTRY_CD_BASIC)) {
            setEntryCd(MlCompletionregistAction.ENTRY_CD_BASIC);
        }
        if(isEntryDefault(MlCompletionregistAction.ENTRY_CD_TRIP)) {
            setEntryCd(MlCompletionregistAction.ENTRY_CD_TRIP);
        }
        if(isEntryDefault(MlCompletionregistAction.ENTRY_CD_RENEWAL)) {
            setEntryCd(MlCompletionregistAction.ENTRY_CD_RENEWAL);
        }
    }
    /**
     * 登録対象デフォルトエントリー判断処理
     * @param entryCd
     * @return
     */
    private boolean isEntryDefault(String entryCd) {
        if(MlCompletionregistAction.ENTRY_CD_BASIC.equals(entryCd)) {
            return getBasicTrainingListSize() > 0;
        }
        if(MlCompletionregistAction.ENTRY_CD_TRIP.equals(entryCd)) {
            return (getBasicTrainingListSize() == 0 && getTripTrainingListSize() > 0);
        }
        if(MlCompletionregistAction.ENTRY_CD_RENEWAL.equals(entryCd)) {
            return (getBasicTrainingListSize() == 0 && getTripTrainingListSize() == 0 && getRenewalTrainingListSize() > 0);
        }
        return false;
    }
    /**
     * 
     * @return
     */
   public static String getYmAlert() {
       return "年月をYYYYMM又はYYYY/MMの型で入力して下さい。";
   }

	/**
	 * システム日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * システム日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
    /**
     * @return limit を戻します。
     */
    public boolean isLimit() {
        return limit;
    }
    /**
     * @param limit 設定する limit。
     */
    public void setLimit(boolean limit) {
        this.limit = limit;
    }
	/**
	 * 研修修了情報全クリア可能ユーザ判断フラグ取得処理
	 * 
	 * @return allClear を戻します。
	 */
	public boolean isAllClear() {
		return allClear;
	}
	/**
	 * 研修修了情報全クリア可能ユーザ判断フラグ設定処理
	 * 
	 * @param zenClearFlg 設定する allClear。
	 */
	public void setAllClear(boolean zenClearFlg) {
		this.allClear = zenClearFlg;
	}
}