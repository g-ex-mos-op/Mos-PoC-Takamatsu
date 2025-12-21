package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;


/**
 * 業務管理 売上推移表・宅配売上推移表の結果画面用Dto
 * 
 * 2007/03/12 ADD T.Kinugawa(ASPAC) 複数ウィンドウ機能対応の不備の対応
 * すでに検索済みのデータを再度検索するときに検索済み条件パラメーターの値が必要。
 * 現行では、リクエストされたものを使用し検索済みデータとは異なるデータの検索結果を表示していた。
 * そのため、検索済み条件パラメーターの値を保持する機能を追加し、この値で検索処理を行うよう修正した。
 * 
 * @autho xwatanabe
 */
public class TakuhaiSuiiRefResultDto extends SuiiRefResultDto implements CsvOutputDto{
       
    //---------------------------
    // 検索結果：リスト
    //---------------------------
    /** 宅配売上推移日次リスト：画面表示用 */
    private List nipoGamenList     = null;
    /** 宅配売上推移月次リスト：画面表示用 */
    private List gepoGamenList     = null;

    //---------------------------
    // 検索結果：リスト以外
    //---------------------------
    /** 対象店舗数(日報) */
    private int totalTenpoCountNipo  = 0;
    /** 表示対象表示文字列(日報) */
    private String hyojiTaishoNipoStr   ="";
    /** 対象店舗数(月報) */
    private int totalTenpoCountGepo  = 0;
    /** 表示対象表示文字列(月報) */
    private String hyojiTaishoGepoStr   = "";
    /** どのタブが選択されたか */
    private String targetTab            = "";
    /** 表示対象名称 */
    private String hyojiTaishoName = "";
    
    

    /**
     * 情報のクリア
     */
    public void clear() {
    	super.clear();
        setNipoGamenList(null);     //宅配売上推移日次の画面用リスト
        setGepoGamenList(null);     //宅配売上推移月次の画面用リスト
        setTotalTenpoCountNipo(0);
        setHyojiTaishoNipoStr(null);
        setTotalTenpoCountGepo(0);
        setHyojiTaishoGepoStr(null);
        setHyojiTaishoName(null);
        setTargetTab(null);
    }
    /////////////////////////////////ここより下、ゲッター・セッター//////////////////////////////////

    /**
     * 宅配売上推移日次の画面用リストを取得
     * @return List 宅配売上推移日次画面用リスト
     */
    public List getNipoGamenList() {
        return nipoGamenList;
    }
    /**
     * 宅配売上推移日次の画面用リストをセット
     * @param List 宅配売上推移日次画面用リスト
     */
    public void setNipoGamenList(List list) {
        nipoGamenList=list;
    }

    /**
     * 日次の画面用リストサイズを取得
     * @return int 日次リストサイズ
     */
    public int getNipoGamenListSize() {
        return (getNipoGamenList() == null) ? 0 : getNipoGamenList().size();
    }
    
    /**
     * 宅配売上推移月次の画面用リストを取得
     * @return List 宅配売上推移月次画面用リスト
     */
    public List getGepoGamenList() {
        return gepoGamenList;
    }
    /**
     * 宅配売上推移月次の画面用リストをセット
     * @param List 宅配売上推移月次画面用リスト
     */
    public void setGepoGamenList(List list) {
        gepoGamenList=list;
    }

    /**
     * 月次の画面用リストサイズを取得
     * @return int 月次リストサイズ
     */
    public int getGepoGamenListSize() {
        return (getGepoGamenList() == null) ? 0 : getGepoGamenList().size();
    }

    /**
     * 対象店舗数(日報)を取得
     * @return int 対象店舗数
     */
    public int getTotalTenpoCountNipo() {
        return totalTenpoCountNipo;
    }
    /**
     * 対象店舗数(日報)をセット
     * @param int 対象店舗数
     */
    public void setTotalTenpoCountNipo(int num) {
        totalTenpoCountNipo=num;
    }

    /**
     * 表示対象表示文字列(日報)を取得
     * @return String 表示対象表示文字列(日報)
     */
    public String getHyojiTaishoNipoStr() {
        return hyojiTaishoNipoStr;
    }
    /**
     * 表示対象表示文字列(日報)をセット
     * @param String 表示対象表示文字列(日報)
     */
    public void setHyojiTaishoNipoStr(String str) {
        hyojiTaishoNipoStr=str;
    }

    /**
     * 対象店舗数(月報)を取得
     * @return int 対象店舗数
     */
    public int getTotalTenpoCountGepo() {
        return totalTenpoCountGepo;
    }
    /**
     * 対象店舗数(月報)をセット
     * @param int 対象店舗数
     */
    public void setTotalTenpoCountGepo(int num) {
        totalTenpoCountGepo = num;
    }

    /**
     * 表示対象表示文字列(月報)を取得
     * @return String 表示対象表示文字列(月報)
     */
    public String getHyojiTaishoGepoStr() {
        return hyojiTaishoGepoStr;
    }
    /**
     * 表示対象表示文字列(月報)をセット
     * @param String 表示対象表示文字列(月報)
     */
    public void setHyojiTaishoGepoStr(String str) {
        hyojiTaishoGepoStr = str;
    }
    
    
    /**
     * ターゲットタブを取得
     * @return ターゲットタブ
     */
    public String getTargetTab() {
        return targetTab;
    }
    /**
     * ターゲットタブをセット
     * @param ターゲットタブ
     */
    public void setTargetTab(String str) {
        targetTab = str;
    }

/////////////////////////////////////////
	/**
	 * @return クラス変数hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName() {
		return hyojiTaishoName;
	}

	/**
	 * @param name を クラス変数hyojiTaishoNameへ設定します。
	 */
	public void setHyojiTaishoName(String name) {
		this.hyojiTaishoName = name;
	}
}
