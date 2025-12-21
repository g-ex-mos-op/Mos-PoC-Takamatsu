package jp.co.isid.mos.bird.bizreport.posreportref.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportref.code.Tab;
import jp.co.isid.mos.bird.bizreport.posreportref.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

public class PosReportRefDto {
    
    /**
     * ユーザーID
     */
    private String userId;
    /**
     * ユーザータイプ
     */
    private String userTypeCd;
    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * 最新日付(MMDD)
     */
    private String latestDate;
    /**
     * 最新日付(YYYY年MM月DD日(DAY))
     */
    private String latestDateDay;
    
    /**
     * 店コード
     */
    private String miseCd;

    /**
     * 店名称
     */
    private String miseNameKj;
    /**
     * 対象条件
     * 作成日:2010/10/25
     */
    private String taishoJoken;
    /**
     * 表示対象
     * 作成日:2010/10/25
     */
    private String hyojiTaisho;
    /**
     * 表示対象名称
     * 作成日:2010/10/25
     */
    private String hyojiTaishoName;

    /* 検索結果：[[店一覧情報]] */
    private List listPosReportMiseInfo;

    /* 検索結果：[[店別推移情報]] */
    private List listPosReportSuiiInfo;
    
    /* 検索結果：[[店別メニュー別情報]] */
    private List listMiseMenuInfo;
    
    /* 検索結果：[[店別時間帯別情報]] */
    private List listMiseHourInfo;

    /* 検索結果：[[店一覧情報サイズ]] */
    private int posReportMiseInfoSize;
    
    /* 検索結果：[[店別キャンペーンメニュー情報]] */
    private List listCampMenu;

    public void clear(){
        setLatestDate(null);
        setListMiseHourInfo(null);
        setListMiseMenuInfo(null);
        setListPosReportMiseInfo(null);
        setListPosReportSuiiInfo(null);
        setMiseCd(null);
        setMiseNameKj(null);
    }
    /**
     * userIdを取得
     * @return userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * userIdを設定
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * userTypeCdを設定
     * @return userTypeCd
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    /**
     * userTypeCdを取得
     * @param userTypeCd
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    /**
     * companyCdを設定
     * @param companyCd
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * companyCdを取得
     * @return companyCd
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * latestDateを取得
     * @return latestDate
     */
    public String getLatestDate() {
        return latestDate;
    }

    /**
     * latestDateを取得
     * @return latestDate
     */
    public String getLatestDateDay() {
        DateFormatter formatter = new DateFormatter();
        String ymdKanji = formatter.format(getLatestDate(), DateFormatter.PATTERN_KANJI_YMD, DateFormatter.DATE_TYPE_YMD);
        //曜日
        String dayWeekNm = "("+formatter.format(getLatestDate(),DateFormatter.PATTERN_DAY_OF_WEEK,DateFormatter.DATE_TYPE_YMD)+"曜日"+")";
        latestDateDay = ymdKanji + dayWeekNm;
        return latestDateDay;
    }

    /**
     * latestDateを設定
     * @param latestDate
     */
    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }
    /**
     * miseCdを取得
     * @return miseCd
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * miseCdを設定
     * @param miseCd
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    /**
     * miseNameKjを取得
     * @return miseNameKj
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * miseNameKjを設定
     * @param miseNameKj
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    /**
     * listPosReportMiseInfoを取得
     * @return listPosReportMiseInfo
     */
    public List getListPosReportMiseInfo() {
        return listPosReportMiseInfo;
    }
    /**
     * listPosReportMiseInfoを設定
     * @param listPosReportMiseInfo
     */
    public void setListPosReportMiseInfo(List listPosReportMiseInfo) {
        this.listPosReportMiseInfo = listPosReportMiseInfo;
    }
    /**
     * listPosReportSuiiInfoを取得
     * @return listPosReportSuiiInfo
     */
    public List getListPosReportSuiiInfo() {
        return listPosReportSuiiInfo;
    }
    /**
     * listPosReportSuiiInfoを設定
     * @param listPosReportSuiiInfo
     */
    public void setListPosReportSuiiInfo(List listPosReportSuiiInfo) {
        this.listPosReportSuiiInfo = listPosReportSuiiInfo;
    }
    /**
     * listMiseMenuInfoを取得
     * @return listMiseMenuInfo
     */
    public List getListMiseMenuInfo() {
        return listMiseMenuInfo;
    }
    /**
     * listMiseMenuInfoを設定
     * @param listMiseMenuInfo
     */
    public void setListMiseMenuInfo(List listMiseMenuInfo) {
        this.listMiseMenuInfo = listMiseMenuInfo;
    }
    /**
     * listMiseHourInfoを設定
     * @return listMiseHourInfo
     */
    public List getListMiseHourInfo() {
        return listMiseHourInfo;
    }
    /**
     * listMiseHourInfoを設定
     * @param listMiseHourInfo
     */
    public void setListMiseHourInfo(List listMiseHourInfo) {
        this.listMiseHourInfo = listMiseHourInfo;
    }
    /**
     * posReportMiseInfoSizeを取得
     * @return posReportMiseInfoSize
     */
    public int getPosReportMiseInfoSize() {
        posReportMiseInfoSize = 0;
        if(getListPosReportMiseInfo() != null){
            posReportMiseInfoSize = getListPosReportMiseInfo().size();
        }
        return posReportMiseInfoSize;
    }
    /**
     * 表示対象取得処理
     * 作成日:2010/10/25
     * @return　支部 又は エリア大コード
     */
	public String getHyojiTaisho() {
		return hyojiTaisho;
	}
	/**
	 * 表示対象設定処理
     * 作成日:2010/10/25
	 * @param hyojiTaisho　支部 又は エリア大コード
	 */
	public void setHyojiTaisho(String hyojiTaisho) {
		this.hyojiTaisho = hyojiTaisho;
	}
	/**
	 * 対象条件取得処理
     * 作成日:2010/10/25
	 * @return "SIBU_CD" or "AREA_DAI"
	 */
	public String getTaishoJoken() {
		return taishoJoken;
	}
	/**
	 * 対象条件設定処理
     * 作成日:2010/10/25
	 * @param taishoJoken　"SIBU_CD" or "AREA_DAI"
	 */
	public void setTaishoJoken(String taishoJoken) {
		this.taishoJoken = taishoJoken;
	}
	public String getHyojiTaishoName() {
		return hyojiTaishoName;
	}
	public void setHyojiTaishoName(String hyojiTaishoName) {
		this.hyojiTaishoName = hyojiTaishoName;
	}
	/**
	 * List[[表示対象]]
	 * 各インデックスにENTITY【リスト情報】が格納されています。
	 */
	private List listHyojiTaisho;
	/**
	 * 表示対象リスト取得処理
	 * @return　List[[表示対象]]
	 */
	public List getListHyojiTaisho() {
		return listHyojiTaisho;
	}
	/**
	 * 表示対象リスト設定処理
	 * @param listHyojiTaisho　List[[表示対象]]
	 */
	public void setListHyojiTaisho(List listHyojiTaisho) {
		this.listHyojiTaisho = listHyojiTaisho;
	}
	/**
	 * 対象条件リスト取得処理
	 * @return List[[対象条件]]
	 */
	public List getListTaishoJoken() {
		return TaishoJoken.getPullDownList();
	}
	/**
	 * タブリスト取得処理
	 * @return
	 */
	public List getListTab() {
		return Tab.getPullDownList();
	}
	public List getListCampMenu() {
		return listCampMenu;
	}
	public void setListCampMenu(List listCampMenu) {
		this.listCampMenu = listCampMenu;
	}
}
