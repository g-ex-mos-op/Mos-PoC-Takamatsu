package jp.co.isid.mos.bird.storeinfo.miseinformationextract.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;

/**
 * 店マスタ情報一括抽出画面 Dto
 * @author boukoumei
 */
public class MiseInfoExtractDto implements DownloadDto, CsvOutputDto, ExcelOutputDto {

    /** ユーザーID */
    private String userId;
    /** 会社コード */
    private String companyCd ="00";
    /** 支部コード */
    private String sibuCd;
    /** クローズ店フラグ（含む／含まない) */
    private String closeFlg;
    /** CSVデータ */
    private List csvList;
    /** EXCLEデータ */
    private List excleList;
    /** 条件画面用：CSVタイトルリスト */
    private List titleList;
    /** システム日付 */
    private String sysDate;
    /** 選択ラジオボタンIndex */
    private int index;
    /** 集計区分情報リスト*/
    private List shukeiKbnList;
    /** 集計区分情報*/
    private String shukeiKbnCd = ShukeiKbn.OUT_RC;
    /** 支部リスト*/
    private List taishoSibuList = new ArrayList(0);
    /** SVコード */
    private String svCd;
    /** SV名称 */
    private String svName;
	/** ウィンドウID */
	private int windowId;
	/** カテゴリ:基本情報 */
	private boolean katekoriCheck1 = true;
	/** カテゴリ:日付・店舗継承 */
	private boolean katekoriCheck2;
	/** カテゴリ:特性・営業時間 */
	private boolean katekoriCheck3;

    private String taishoJoken = TaishoJoken.CODE_ALL;

    public void clear() {
        setCsvList(null);
        setExcleList(null);
        setShukeiKbnList(null);
        setTaishoSibuList(null);
    }

    /**
     * 条件画面で選択された会社名称取得
     * @return sibuName を戻します。
     */
    public String getSibuName() {

        String sibuName     = "";
        String selectSibuCd = getSibuCd();
        List listPullSibu   = getTaishoSibuList();

        if (selectSibuCd!= null
                && !selectSibuCd.equals("")
                && listPullSibu != null) {

            for (int i = 0; i < listPullSibu.size(); i++) {
            	SelectItem item  = (SelectItem) listPullSibu.get(i);
                if (selectSibuCd.equals(item.getValue())) {
                    sibuName = item.getLabel();
                    break;
                }
            }
        }
        return sibuName;
    }

    public String getCloseName() {

        String closeName = "含む";

        if(getCloseFlg().equals("outClose")){
            closeName = "含まない";
        }

        return closeName;
    }

    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 条件画面で選択された会社コード取得
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 条件画面で選択された会社コード設定
     * @param String companyCd を設定。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }

    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }

    /**
     * クローズ店フラグ（含む／含まない)を取得します。
     * @return クローズ店フラグ（含む／含まない)
     */
    public String getCloseFlg() {
        return closeFlg;
    }

    /**
     * クローズ店フラグ（含む／含まない)を設定します。
     * @param closeFlg クローズ店フラグ（含む／含まない)
     */
    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg;
    }

    /**
     * CSVデータ を取得します。
     * @return CSVデータ
     */
    public List getCsvList() {
        return csvList;
    }

    /**
     * CSVデータ を設定します。
     * @param csvList CSVデータ
     */
    public void setCsvList(List csvList) {
        this.csvList = csvList;
    }

    /**
     * EXCLEデータを取得します。
     * @return EXCLEデータ
     */
    public List getExcleList() {
        return excleList;
    }

    /**
     * EXCLEデータを設定します。
     * @param excleList EXCLEデータ
     */
    public void setExcleList(List excleList) {
        this.excleList = excleList;
    }

    /**
     * CSVタイトルリストを取得します。
     * @return CSVタイトルリスト
     */
    public List getTitleList() {
        return titleList;
    }

    /**
     * CSVタイトルリストを設定します。
     * @param titleList CSVタイトルリスト
     */
    public void setTitleList(List titleList) {
        this.titleList = titleList;
    }

    /**
     * システム日付を取得します。
     * @return システム日付
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * システム日付を設定します。
     * @param sysDate システム日付
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * 選択ラジオボタンIndexを取得します。
     * @return 選択ラジオボタンIndex
     */
    public int getIndex() {
        return index;
    }

    /**
     * 選択ラジオボタンIndexを設定します。
     * @param index 選択ラジオボタンIndex
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * 集計区分情報リストを取得します。
     * @return 集計区分情報リスト
     */
    public List getShukeiKbnList() {
        return shukeiKbnList;
    }

    /**
     * 集計区分情報リストを設定します。
     * @param shukeiKbnList 集計区分情報リスト
     */
    public void setShukeiKbnList(List shukeiKbnList) {
        this.shukeiKbnList = shukeiKbnList;
    }

    /**
     * 集計区分情報を取得します。
     * @return 集計区分情報
     */
    public String getShukeiKbnCd() {
        return shukeiKbnCd;
    }

    /**
     * 集計区分情報を設定します。
     * @param shukeiKbnCd 集計区分情報
     */
    public void setShukeiKbnCd(String shukeiKbnCd) {
        this.shukeiKbnCd = shukeiKbnCd;
    }

    /**
     * 支部リストを取得します。
     * @return 支部リスト
     */
    public List getTaishoSibuList() {
        return taishoSibuList;
    }

    /**
     * 支部リストを設定します。
     * @param taishoSibuList 支部リスト
     */
    public void setTaishoSibuList(List taishoSibuList) {
        this.taishoSibuList = taishoSibuList;
    }

    /**
     * SVコードを取得する
     * @return svCd
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * SVコードを設定する
     * @param svCd
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }

    /**
     * SV名称を取得する
     * @return svName
     */
    public String getSvName() {
        return svName;
    }
    /**
     * SV名称を設定する
     * @param svName
     */
    public void setSvName(String svName) {
        this.svName = svName;
    }
	/**
	 * ウィンドウID
	 * @return クラス変数windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * ウィンドウID
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

    /**
     * SV指定(担当店一覧)判断フラグ
     *
     * @return
     */
    public boolean isSvFlg() {
    	return ShukeiKbn.SV_CD.equals(getShukeiKbnCd());
    }
    /**
     * カテゴリ:基本情報を取得します。
     * @return カテゴリ:基本情報
     */
    public boolean getKatekoriCheck1() {
        return katekoriCheck1;
    }

    /**
     * カテゴリ:基本情報を設定します。
     * @param katekoriCheck1 カテゴリ:基本情報
     */
    public void setKatekoriCheck1(boolean katekoriCheck1) {
        this.katekoriCheck1 = true;
    }

    /**
     * カテゴリ:日付・店舗継承を取得します。
     * @return カテゴリ:日付・店舗継承
     */
    public boolean getKatekoriCheck2() {
        return katekoriCheck2;
    }

    /**
     * カテゴリ:日付・店舗継承を設定します。
     * @param katekoriCheck2 カテゴリ:日付・店舗継承
     */
    public void setKatekoriCheck2(boolean katekoriCheck2) {
        this.katekoriCheck2 = katekoriCheck2;
    }

    /**
     * カテゴリ:特性・営業時間を取得します。
     * @return カテゴリ:特性・営業時間
     */
    public boolean getKatekoriCheck3() {
        return katekoriCheck3;
    }

    /**
     * カテゴリ:特性・営業時間を設定します。
     * @param katekoriCheck3 カテゴリ:特性・営業時間
     */
    public void setKatekoriCheck3(boolean katekoriCheck3) {
        this.katekoriCheck3 = katekoriCheck3;
    }

	/**
	 * @return クラス変数taishoJoken を戻します。
	 */
	public String getTaishoJoken() {
		return taishoJoken;
	}
	/**
	 * @param taishoJoken を クラス変数taishoJokenへ設定します。
	 */
	public void setTaishoJoken(String taishoJoken) {
		this.taishoJoken = taishoJoken;
	}

	@Override
	public String getFileName() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String strDate = format.format(date);
		return "mise_list_" + strDate + ".xlsx";
	}
}
