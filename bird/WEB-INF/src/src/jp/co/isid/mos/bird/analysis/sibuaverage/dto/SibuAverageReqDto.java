package jp.co.isid.mos.bird.analysis.sibuaverage.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.sibuaverage.code.ShukeiKbn;
import jp.co.isid.mos.bird.analysis.sibuaverage.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 支部平均比較リクエストDTO
 * @author xnkusama
 *
 */
public class SibuAverageReqDto implements CsvOutputDto {
    
    /** 画面制御 */
    private int windowId;
    
    /**ユーザー情報*/
    private String userTypeCd;
    private String targetOnerCd;
    /** 検索条件 */
    private String companyCd = "00";
    private String taishoJoken;
    private String kikanSitei;
    private String hyojiTaishoMise;
    private String hyojiTaishoOner;
    /** List[[集計区分]](2012/11/02追加) */
    private List listShukeiKbn = new ArrayList(0);
    /** 集計区分(2012/11/02追加) */
    private String shukeiKbn = ShukeiKbn.IN_RC;
    private String taishoSibu;
    private String zenDataShu = ZennenDataShubetu.CODE_DOGETU;
    private boolean newWindowFlg;
    
    /** 検索結果 */
    private String taishoKikanDisp;
    private String hyojiTaishoDisp;
    private String hyojiTaishoOnerName;
    private String taishoSibuDisp;
    private boolean reSearchFlg;
    private boolean sibuMode = false;
    private List listData = null;
    
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getHyojiTaishoDisp() {
        return hyojiTaishoDisp;
    }
    public void setHyojiTaishoDisp(String hyojiTaishoDisp) {
        this.hyojiTaishoDisp = hyojiTaishoDisp;
    }
    public String getTaishoKikanDisp() {
        return taishoKikanDisp;
    }
    public void setTaishoKikanDisp(String kikan) {
        this.taishoKikanDisp = kikan;
    }
    public String getTaishoJoken() {
        return taishoJoken;
    }
    public void setTaishoJoken(String taishoJoken) {
        this.taishoJoken = taishoJoken;
    }
    public String getKikanSitei() {
        return kikanSitei;
    }
    public void setKikanSitei(String taishoKikan) {
        this.kikanSitei = taishoKikan;
    }
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public String getHyojiTaisho() {
        return TaishoJoken.CODE_MISE.equals(getTaishoJoken())?getHyojiTaishoMise():getTargetOnerCd();
    }
    public boolean isNewWindowFlg() {
        return newWindowFlg;
    }
    public void setNewWindowFlg(boolean newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }
    public List getListData() {
        return listData;
    }
    public void setListData(List listData) {
        this.listData = listData;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public boolean isReSearchFlg() {
        return reSearchFlg;
    }
    public void setReSearchFlg(boolean reSearchFlg) {
        this.reSearchFlg = reSearchFlg;
    }
    public String getTaishoSibu() {
        return taishoSibu;
    }
    public void setTaishoSibu(String taishoSibu) {
        this.taishoSibu = taishoSibu;
    }
    public String getZenDataShu() {
        return zenDataShu;
    }
    public void setZenDataShu(String zenDataShu) {
        this.zenDataShu = zenDataShu;
    }
    public String getTaishoSibuDisp() {
        return taishoSibuDisp;
    }
    public void setTaishoSibuDisp(String taishoSibuDisp) {
        this.taishoSibuDisp = taishoSibuDisp;
    }
    /**
     * 前年データ種別名称取得処理
     * @return
     */
    public String getZenDataShuName() {
        return ZennenDataShubetu.getName(zenDataShu, getUserTypeCd());
    }
    public String getHyojiTaishoMise() {
        return hyojiTaishoMise;
    }
    public void setHyojiTaishoMise(String hyojiTaishoMise) {
        this.hyojiTaishoMise = hyojiTaishoMise;
    }
    public String getHyojiTaishoOner() {
        return hyojiTaishoOner;
    }
    public void setHyojiTaishoOner(String hyojiTaishoOner) {
        this.hyojiTaishoOner = hyojiTaishoOner;
    }
    public boolean isSibuMode() {
        return sibuMode;
    }
    public void setSibuMode(boolean sibuMode) {
        this.sibuMode = sibuMode;
    }
    public String getHyojiTaishoOnerName() {
        return hyojiTaishoOnerName;
    }
    public void setHyojiTaishoOnerName(String hyojiTaishoOnerName) {
        this.hyojiTaishoOnerName = hyojiTaishoOnerName;
    }
	/**
	 * 検索結果:集計区分名称取得処理
	 * @return
	 */
	public String getShukeiKbnName() {
		return ShukeiKbn.getName(getShukeiKbn());
	}
	/**
	 * @return クラス変数shukeiKbn を戻します。
	 */
	public String getShukeiKbn() {
		return shukeiKbn;
	}
	/**
	 * @param shukeiKbn を クラス変数shukeiKbnへ設定します。
	 */
	public void setShukeiKbn(String shukeiKbn) {
		this.shukeiKbn = shukeiKbn;
	}
	/**
	 * @return クラス変数listShukeiKbn を戻します。
	 */
	public List getListShukeiKbn() {
		return listShukeiKbn;
	}
	/**
	 * @param listShukeiKbn を クラス変数listShukeiKbnへ設定します。
	 */
	public void setListShukeiKbn(List listShukeiKbn) {
		this.listShukeiKbn = listShukeiKbn;
	}
	public List getListZennendataShubetu() {
		return ZennenDataShubetu.getPullDownList(getUserTypeCd());
	}
	/**
	 * ログインユーザー別出力文言
	 * @return
	 */
	public String getUserMsg() {
		return "※「支部平均（売上・前年比）」は、前年対象店(前年度通期で売上実績のある店舗)を集計し、算出しています。";
	}
	/**
	 * @return クラス変数targetOnerCd を戻します。
	 */
	public String getTargetOnerCd() {
		return targetOnerCd;
	}
	/**
	 * @param targetOnerCd を クラス変数targetOnerCdへ設定します。
	 */
	public void setTargetOnerCd(String targetOnerCd) {
		this.targetOnerCd = targetOnerCd;
	}
}
