/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.itemtotal.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品別合計DTO
 *  2006/10/23 add 明細行と合計行の判別フラグプロパティ追加
 * @author xlee
 */
public class ShobetuGoukeiInfoDto{
    
    /**
     *　請求書分類
     */
    private String seikyusyoBunrui;
    
    /**
     *　分類名
     */
    private String seBnrName;
    
    /**
     *　記号
     */
    private String seikyuBunruiKigou;
    
    /**
     *　ソーキー
     */
    private String seikyuBunruiSeq;
    
    /**
     *　実商品コード
     */
    private String shoCdJitu;
    
    /**
     *　商品名
     */
    private String shoNameKj;
    
    /**
     *　数量
     */
    private BigDecimal shoAmount;
    
    /**
     *　単位
     */
    private String nisuName;
    
    /**
     *　単価
     */
    private BigDecimal nohinTanka;
    
    /**
     *　金額
     */
    private BigDecimal kingaku;
    
    /**
     *　構成比
     */
    private BigDecimal kouseihi;
    
    /**
     *　売上比
     */
    private BigDecimal uriagehi;
    
    /**
     * 明細行フラグ
     *   true:明細行
     *   2006/10/23 add
     */
    private boolean meisaiFlg = true;
    
    /**
     * ページ数
     * 2007/01/11add
     */
    private int pageCnt;
    
    /**
     * 判断フラグ
     * 2007/01/11 add
     */
    private String handanFlg;
    
    /**
     * 行カウント
     * 2007/01/11 add
     */
    private int lineCnt;
    
    /**
     * 明細有り無し判断
     * 2007/01/11 add
     */
    private String meisaiNoFlg;
    
//    /**
//     *　課税構成非
//     */
//    private BigDecimal shohinGoukei;
//    
//    /**
//     *　非課税構成非
//     */
//    private BigDecimal hikazeiGoukei;
//    
//    /**
//     *　合計構成非
//     */
//    private BigDecimal zenShohinGoukei;
//    
//    /**
//     *　課税構成非（全体）
//     */
//    private BigDecimal shohinAllGoukei;
//    
//    /**
//     *　非課税構成非（全体）
//     */
//    private BigDecimal hikazeiAllGoukei;
//    
//    /**
//     *　合計構成非（全体）
//     */
//    private BigDecimal zenShohinAllGoukei;
        
    /**
     *　商品別情報リスト
     */
    private List shohinbetuInfoList;
    
    /**
     * 請求書分類リストを設定します。
     * @param seikyusyoBunrui 請求書分類リスト
     */
    public void setSeikyusyoBunrui(String seikyusyoBunrui) {
        this.seikyusyoBunrui = seikyusyoBunrui;
    }
    
    /**
     * 請求書分類リストトを取得します。
     * @return　請求書分類リスト
     */
    public String getSeikyusyoBunrui() {        
        return seikyusyoBunrui;
    }
    
    /**
     * 請求書分類名を設定します。
     * @param seBnrName 請求書分類名
     */
    public void setSeBnrName(String seBnrName) {
        this.seBnrName = seBnrName;
    }
    
    /**
     * 請求書分類名を取得します。
     * @return　請求書分類名
     */
    public String getSeBnrName() {
        
        return seBnrName;
    }
	
    /**
     * 請求書分類記号を設定します。
     * @param seikyuBunruiKigou 請求書分類記号
     */
    public void setSeikyuBunruiKigou(String seikyuBunruiKigou) {
        this.seikyuBunruiKigou = seikyuBunruiKigou;
    }
    
    /**
     *　請求書分類記号を取得します。
     * @return　請求書分類記号
     */
    public String getSeikyuBunruiKigou() {
        return seikyuBunruiKigou;
    }
    
    /**
     * 請求書分類ソートキーを設定します。
     * @param seikyuBunruiSeq 請求書分類ソートキー
     */
    public void setSeikyuBunruiSeq(String seikyuBunruiSeq) {
        this.seikyuBunruiSeq = seikyuBunruiSeq;
    }
    
    /**
     * 請求書分類ソートキーを取得します。
     * @return　請求書分類ソートキー
     */
    public String getSeikyuBunruiSeq() {
        return seikyuBunruiSeq;
    }

    /**
     * 実商品コードを設定します。
     * @param shoCdJitu 実商品コード
     */
    public void setShoCdJitu(String shoCdJitu) {
        this.shoCdJitu = shoCdJitu;
    }
    
    /**
     * 実商品コードを取得します。
     * @return　実商品コード
     */
    public String getShoCdJitu() {
        return shoCdJitu;
    }
    
    /**
     * 商品名を設定します。
     * @param shoNameKj 商品名
     */
    public void setShoNameKj(String shoNameKj) {
        this.shoNameKj = shoNameKj;
    }
    
    /**
     * 商品名を取得します。
     * @return　商品名
     */
    public String getShoNameKj() {
        return shoNameKj;
    }

    /**
     * 数量を設定します。
     * @param shoAmount 数量
     */
    public void setShoAmount(BigDecimal shoAmount) {
        this.shoAmount = shoAmount;
    }
    
    /**
     * 数量を取得します。
     * @return　数量
     */
    public BigDecimal getShoAmount() {
        return shoAmount;
    }

    /**
     * 単位を設定します。
     * @param nisuName 単位
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }
    
    /**
     * 単位を取得します。
     * @return　単位
     */
    public String getNisuName() {
        
        return nisuName;
    }

    /**
     * 単価を設定します。
     * @param nohinTanka 単価
     */
    public void setNohinTanka(BigDecimal nohinTanka) {
        this.nohinTanka = nohinTanka;
    }
    
    /**
     * 単価を取得します。
     * @return　単価
     */
    public BigDecimal getNohinTanka() {
        
        return nohinTanka;
    }

    /**
     * 金額を設定します。
     * @param kingaku 金額
     */
    public void setKingaku(BigDecimal kingaku) {
        this.kingaku = kingaku;
    }
    
    /**
     * 金額を取得します。
     * @return　金額
     */
    public BigDecimal getKingaku() {
        
        return kingaku;
    }

    /**
     * 課税構成比を設定します。
     * @param kouseihi 構成比
     */
    public void setKouseihi(BigDecimal kouseihi) {
        this.kouseihi = kouseihi;
    }
    
    /**
     * 課税構成比を取得します。
     * @return　構成比
     */
    public BigDecimal getKouseihi() {
        
        return kouseihi;
    }
    
//    /**
//     * 構成比（全体）を設定します。
//     * @param shohinAllGoukei 構成比（全体）
//     */
//    public void setShohinAllGoukei(BigDecimal shohinAllGoukei) {
//        this.shohinAllGoukei = shohinAllGoukei;
//    }
//    
//    /**
//     * 構成比（全体）を取得します。
//     * @return　構成比（全体）
//     */
//    public BigDecimal getShohinAllGoukei() {
//        return shohinAllGoukei;
//    }
//    
//    /**
//     * 非課税構成比（全体）を設定します。
//     * @param condTaishoKikanCd 非課税構成比
//     */
//    public void setHikazeiAllGoukei(BigDecimal hikazeiAllGoukei) {
//        this.hikazeiAllGoukei = hikazeiAllGoukei;
//    }
//    
//    /**
//     * 非課税構成比（全体）を取得します。
//     * @return　非課税構成比
//     */
//    public BigDecimal getHikazeiAllGoukei() {
//        return hikazeiAllGoukei;
//    } 
//    
//    /**
//     *　合計構成比（全体）を設定します。
//     * @param zenShohinAllGoukei 合計構成比（全体）
//     */
//    public void setZenShohinAllGoukei(BigDecimal zenShohinAllGoukei) {
//        this.zenShohinAllGoukei = zenShohinAllGoukei;
//    }
//    
//    /**
//     * 合計構成比（全体）を取得します。
//     * @return　合計構成比（全体）
//     */
//    public BigDecimal getZenShohinAllGoukei() {
//        return zenShohinAllGoukei;
//    } 
//    
//    /**
//     * 商品別課税構成比を設定します。
//     * @param shohinGoukei 商品別課税構成比
//     */
//    public void setShohinGoukei(BigDecimal shohinGoukei) {
//        this.shohinGoukei = shohinGoukei;
//    }
//    
//    /**
//     * 商品別課税構成比を取得します。
//     * @return　商品別課税構成比
//     */
//    public BigDecimal getShohinGoukei() {
//        return shohinGoukei;
//    }
//    
//    /**
//     * 商品別非課税構成比を設定します。
//     * @param hikazeiGoukei 商品別非課税構成比
//     */
//    public void setHikazeiGoukei(BigDecimal hikazeiGoukei) {
//        this.hikazeiGoukei = hikazeiGoukei;
//    }
//    
//    /**
//     * 商品別非課税構成比を取得します。
//     * @return　商品別非課税構成比
//     */
//    public BigDecimal getHikazeiGoukei() {
//        return hikazeiGoukei;
//    } 
//    
//    /**
//     * 商品合計構成比を設定します。
//     * @param zenShohinGoukei 商品合計構成比
//     */
//    public void setZenShohinGoukei(BigDecimal zenShohinGoukei) {
//        this.zenShohinGoukei = zenShohinGoukei;
//    }
//    
//    /**
//     * 商品合計構成比を取得します。
//     * @return　商品合計構成比
//     */
//    public BigDecimal getZenShohinGoukei() {
//        return zenShohinGoukei;
//    } 
    
    /**
     * 売上非を設定します。
     * @param setUriagehi 対象期間
     */
    public void setUriagehi(BigDecimal uriagehi) {
        this.uriagehi = uriagehi;
    }
    
    /**
     * 売上非を取得します。
     * @return　売上非
     */
    public BigDecimal getUriagehi() {
        return uriagehi;
    }
    
    /**
     * 商品別情報を取得します。
     * @return 商品別情報リスト
     */
	public List getShohinbetuInfoList() {
        return shohinbetuInfoList;
	}
	
	/**
     * 商品別情報を取得します。
     * @param shohinbetuInfoList　商品別情報リス
     */
	public void setShohinbetuInfoList(List shohinbetuInfoList) {
        this.shohinbetuInfoList = shohinbetuInfoList;
	}

    public boolean isMeisaiFlg() {
        return meisaiFlg;
    }

    public void setMeisaiFlg(boolean meisaiFlg) {
        this.meisaiFlg = meisaiFlg;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }

    public String getHandanFlg() {
        return handanFlg;
    }

    public void setHandanFlg(String handanFlg) {
        this.handanFlg = handanFlg;
    }

    public int getLineCnt() {
        return lineCnt;
    }

    public void setLineCnt(int lineCnt) {
        this.lineCnt = lineCnt;
    }

    public String getMeisaiNoFlg() {
        return meisaiNoFlg;
    }

    public void setMeisaiNoFlg(String meisaiNoFlg) {
        this.meisaiNoFlg = meisaiNoFlg;
    }
}