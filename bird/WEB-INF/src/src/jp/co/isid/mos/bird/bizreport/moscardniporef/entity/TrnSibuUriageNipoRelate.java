package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 
 * @author   xkhata
 * @modifier xkinu  2007/06/05 クローズ店予算表示対応
 *
 */
public class TrnSibuUriageNipoRelate {
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称クラス
     */
    private String sibuNameClass;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 当年店舗数
     */
    private BigDecimal tenpoCount;
    
    /**
     * 前年店舗数
     */
    private BigDecimal zenTenpoCount;
    
    /**
     * 当年店舗数/前年店舗数
     */
    private String tenpoCountSlash;
    
    /**
     * 本部コード
     */
    private String honbuCd;
    
    /**
     * 本部名称
     */
    private String honbuName;
    
    /**
     * 事業部コード
     */
    private String jigyoCd;
    
    /**
     * 事業部名称
     */
    private String jigyoName;
    
    /**
     * 営業エリアコード
     */
    private String slareaCd;
    
    /**
     * 営業エリア名称
     */
    private String slareaName;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 予算
     */
    private BigDecimal yosan;
    
    /**
     * 達成率(予算差)
     */
    private BigDecimal tasseiYosan;
    
    /**
     * 前年実績
     */
    private BigDecimal zenUriage;
    
    /**
     * 前年比(前年差)
     */
    private BigDecimal zenHiSa;
    
    /**
     * オーナー予算クラス
     */
    private String onerYosanClass;
    
    /**
     * オーナー予算
     */
    private BigDecimal onerYosan;
    
    /**
     * 表示区分
     */
    private String dispKbn;
    
    /**
     * trのクラス
     */
    private String rClass;
    
    /**
     * 営業日補正用店舗数
     */
    private BigDecimal hoseiTenpoCnt;
    
    /**
     * 営業日補正用前年店舗数
     */
    private BigDecimal hoseiZenTenpoCnt;
    
    /**
     * 予算対象店舗数
     * 作成日：2007/06/05 ADD xkinu クローズ店予算表示対応
     */
    private BigDecimal yosanMiseCnt = new BigDecimal("0");
    
    /**
     * 発行枚数
     */
    private BigDecimal issueCnt;
        
    /**
     * 発行枚数クラス
     */
    private String issueCntClass;
    
    /**
     * チャージ金額
     */
    private BigDecimal chargeKin;
        
    /**
     * チャージ金額クラス
     */
    private String chargeKinClass;
    
    /**
     * チャージ件数
     */
    private BigDecimal chargeKen;
        
    /**
     * チャージ件数クラス
     */
    private String chargeKenClass;
    
    /**
     * 前年発行枚数
     */
    private BigDecimal zenIssueCnt;
    
    /**
     * 前年発行枚数クラス
     */
    private String zenIssueCntClass;
    
    /**
     * 発行枚数（前年比）
     * */
    private BigDecimal zenIssueCntHiritu = new BigDecimal("0");
    
    /**
     * 発行枚数（前年比）クラス
     */
    private String zenIssueCntHirituClass;
    
    /**
     * チャージ金額
     */
    private BigDecimal zenChargeKin;
    
    /**
     * チャージ金額クラス
     */
    private String zenChargeKinClass;
    
    /**
     * チャージ金額（前年比）
     */
    private BigDecimal zenHiChargeKin;
    
    /**
     * チャージ金額（前年比）クラス
     */
    private String zenHiChargeKinClass;
    
    /**
     * チャージ金額（売上比）
     */
    private BigDecimal urihiChargeKin;
    
    /**
     * チャージ金額（売上比）クラス
     */
    private String urihiChargeKinClass;
    
    /**
     * 前年チャージ件数
     */
    private BigDecimal zenChargeKen;
    
    /**
     * 前年チャージ件数クラス
     */
    private String zenChargeKenClass;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 前年客数
     */
    private BigDecimal zenKyakusu;
    
    /**
     * チャージ件数（客数比）
     */
    private BigDecimal chargeKenKyakuHi;
    
    /**
     * チャージ件数（客数比）クラス
     */
    private String chargeKenKyakuHiClass;
    /**
     * チャージ件数（前年比）
     */
    private BigDecimal chargeKenZenHi;
    /**
     * チャージ件数（前年比）クラス
     */
    private String chargeKenZenHiClass;
    /**
     * チャージ単価
     */
    private BigDecimal chargeTanka;
    /**
     * チャージ単価クラス
     */
    private String chargeTankaClass;
    /**
    * 前年チャージ単価
    */
   private BigDecimal zenChargeTanka;
    
    /**
     * チャージ単価比
     */
    private BigDecimal chargeTankahi;
    /**
     * チャージ単価比クラス
     */
    private String chargeTankahiClass;
    
    /**
     * チャージ単価（前年比）
     */
    private BigDecimal zenChargeTankahi;
    /**
     * チャージ単価（前年比）クラス
     */
    private String zenChargeTankahiClass;
    
    /**
     * 決済金額
     */
    private BigDecimal kessaiKin;

    /**
     * 前年決済金額
     */
    private BigDecimal zenKessaiKin;
    
    /**
     * 決済金額クラス
     */
    private String kessaiKinClass;
    
    /**
     * 決済件数
     */
    private BigDecimal kessaiKen;
        
    /**
     * 決済件数クラス
     */
    private String kessaiKenClass;
    
    /**
     * 前年決済件数
     */
    private BigDecimal zenKessaiKen;
        
    /**
     * 決済金額（売上比）
     */
    private BigDecimal kessaiKinUriHi;
        
    /**
     * 決済金額（売上比）クラス
     */
    private String kessaiKinUriHiClass;
    
    /**
     * 決済金額（前年比）
     */
    private BigDecimal kessaiKinZenHi;
        
    /**
     * 決済金額（前年比）クラス
     */
    private String kessaiKinZenHiClass;
    
    /**
     * 決済件数（客数比）
     */
    private BigDecimal kessaiKenKyakuHi;
        
    /**
     * 決済件数（客数比）クラス
     */
    private String kessaiKenKyakuHiClass;
    
    /**
     * 決済件数（前年比）
     */
    private BigDecimal kessaiKenZenHi;
        
    /**
     * 決済件数（前年比）クラス
     */
    private String kessaiKenZenHiClass;
    
    /**
     * 決済単価
     */
    private BigDecimal kessaiKinTanka;
    
    /**
     * 決済単価クラス
     */
    private String kessaiKinTankaClass;
    
    /**
     * 決済単価
     */
    private BigDecimal zenKessaiKinTanka;
    
    /**
     * 決済単価（単価比）
     */
    private BigDecimal kessaiKinTankaHi;
    
    /**
     * 決済単価（単価比）クラス
     */
    private String kessaiKinTankaHiClass;
    
   
    /**
     * 決済単価（前年比）
     */
    private BigDecimal kessaiKinZenTankaHi;
           
    /**
     * 決済単価（前年比）クラス
     */
    private String kessaiKinZenTankaHiClass;
    
    /**
     * 予算
     */
    private BigDecimal sibuYosan;
    
    /**
     * 発行枚数(前年差)
     */
    private BigDecimal zenSaIssueCnt;
    
    /**
     * ﾁｬｰｼﾞ金額(前年差)
     */
    private BigDecimal zenSaChargeKin;
    
    /**
     * ﾁｬｰｼ件数(前年差)
     */
    private BigDecimal zenSaChargeKen;
    
    /**
     * 客単価
     */
    private BigDecimal kyakuTanka;
    
    /**
     * 前年客単価
     */
    private BigDecimal  zenKyakuTanka;
    
    /**
     * 客単価（前年比）
     */
    private BigDecimal  ｋyakuTankaZenhi;
    
    /**
     * 客単価（単価比）
     */
    private BigDecimal  kyakuTankaTanhi;
    /**
     * 客数(前年差)
     */
    private BigDecimal zenSaKyakusu;
    /**
     * 決済金額(前年差)
     */
    private BigDecimal zenSaKessaiKin;
    /**
     * 決済件数(前年差)
     */
    private BigDecimal zenSaKessaiKen;
    
    /**
     * 入金取消金額
     * */
    private BigDecimal chargeKinCancel;
    /**
     * 入金取消件数
     * */
    private BigDecimal chargeKenCancel;
    /**
     * 利用取消金額
     * */
    private BigDecimal useKinCancel;
    /**
     * 利用取消件数
     * */
    private BigDecimal useKenCancel;
    /**
     * 発行ボーナスバリュー
     * */
    private BigDecimal bonusVIssue;
    /**
     * 利用ボーナスバリュー
     * */
    private BigDecimal bonusVUse;
    /**
     * 発行クーポンバリュー
     * */
    private BigDecimal couponVIssue;
    
    /**
     * 利用クーポンバリュー
     * */
    private BigDecimal couponVUse;
    
    /**
     * 前受残高
     * */
    private BigDecimal zandaka;

    /**
     * SVコード
     */
    private String svCd;
    
    /**
     * SV名称
     */
    private String usernamekj;
    
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
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
     * 支部名称クラスを取得します。
     * @return 支部名称クラス
     */
    public String getSibuNameClass() {
        return sibuNameClass;
    }
    /**
     * 支部名称クラスを設定します。
     * @param sibuNameClass 支部名称クラス
     */
    public void setSibuNameClass(String sibuNameClass) {
        this.sibuNameClass = sibuNameClass;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * 当年店舗数を取得します。
     * @return 当年店舗数
     */
    public BigDecimal getTenpoCount() {
        return tenpoCount;
    }
    /**
     * 当年店舗数を設定します。
     * @param tenpoCount 当年店舗数
     */
    public void setTenpoCount(BigDecimal tenpoCount) {
        this.tenpoCount = tenpoCount;
    }
    
    /**
     * 前年店舗数を取得します。
     * @return 前年店舗数
     */
    public BigDecimal getZenTenpoCount() {
        return zenTenpoCount;
    }
    /**
     * 前年店舗数を設定します。
     * @param zenTenpoCount 前年店舗数
     */
    public void setZenTenpoCount(BigDecimal zenTenpoCount) {
        this.zenTenpoCount = zenTenpoCount;
    }
    
    /**
     * 当年店舗数/前年店舗数を取得します。
     * @return 当年店舗数/前年店舗数
     */
    public String getTenpoCountSlash() {
        NumericFormatter num = new NumericFormatter();
        String returnNum = "(" + num.format( this.tenpoCount,true) + "/" +  num.format( this.zenTenpoCount,true) + ")";
        return returnNum;
    }
    /**
     * 当年店舗数/前年店舗数を設定します。
     * @param tenpoCountSlash 当年店舗数/前年店舗数
     */
    public void setTenpoCountSlash(String tenpoCountSlash) {
        this.tenpoCountSlash = tenpoCountSlash;
    }
    
    /**
     * 本部コードを取得します。
     * @return 本部コード
     */
    public String getHonbuCd() {
        return honbuCd;
    }
    /**
     * 本部コードを設定します。
     * @param honbuCd 本部コード
     */
    public void setHonbuCd(String honbuCd) {
        this.honbuCd = honbuCd;
    }
    
    /**
     * 本部名称を取得します。
     * @return 本部名称
     */
    public String getHonbuName() {
        return honbuName;
    }
    /**
     * 本部名称を設定します。
     * @param honbuName 本部名称
     */
    public void setHonbuName(String honbuName) {
        this.honbuName = honbuName;
    }
    
    /**
     * 事業部コードを取得します。
     * @return 事業部コード
     */
    public String getJigyoCd() {
        return jigyoCd;
    }
    /**
     * 事業部コードを設定します。
     * @param jigyoCd 事業部コード
     */
    public void setJigyoCd(String jigyoCd) {
        this.jigyoCd = jigyoCd;
    }
    
    /**
     * 事業部名称を取得します。
     * @return 事業部名称
     */
    public String getJigyoName() {
        return jigyoName;
    }
    /**
     * 事業部名称を設定します。
     * @param jigyoName 事業部名称
     */
    public void setJigyoName(String jigyoName) {
        this.jigyoName = jigyoName;
    }
    
    /**
     * 営業エリアコードを取得します。
     * @return 営業エリアコード
     */
    public String getSlareaCd() {
        return slareaCd;
    }
    /**
     * 営業エリアコードを設定します。
     * @param slareaCd 営業エリアコード
     */
    public void setSlareaCd(String slareaCd) {
        this.slareaCd = slareaCd;
    }
    
    /**
     * 営業エリア名称を取得します。
     * @return 営業エリア名称
     */
    public String getSlareaName() {
        return slareaName;
    }
    /**
     * 営業エリア名称を設定します。
     * @param slareaName 営業エリア名称
     */
    public void setSlareaName(String slareaName) {
        this.slareaName = slareaName;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
        
    /**
     * 予算を取得します。
     * @return 予算
     */
    public BigDecimal getYosan() {
        return yosan;
    }
    /**
     * 予算を設定します。
     * @param yosan 予算
     */
    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 達成率(予算差)を取得します。
     * @return 達成率(予算差)
     */
    public BigDecimal getTasseiYosan() {
        return tasseiYosan;
    }
    /**
     * 達成率(予算差)を設定します。
     * @param tasseiYosan 達成率(予算差)
     */
    public void setTasseiYosan(BigDecimal tasseiYosan) {
        this.tasseiYosan = tasseiYosan;
    }    
    /**
     * 前年実績を取得します。
     * @return 前年実績
     */
    public BigDecimal getZenUriage() {
        return zenUriage;
    }
    /**
     * 前年実績を設定します。
     * @param zenUriage 前年実績
     */
    public void setZenUriage(BigDecimal zenUriage) {
        this.zenUriage = zenUriage;
    }
        
    /**
     * 前年比(前年差)を取得します。
     * @return 前年比(前年差)
     */
    public BigDecimal getZenHiSa() {
        return zenHiSa;
    }
    /**
     * 前年比(前年差)を設定します。
     * @param zenHiSa 前年比(前年差)
     */
    public void setZenHiSa(BigDecimal zenHiSa) {
        this.zenHiSa = zenHiSa;
    }
    
    /**
     * オーナー予算クラスを取得します。
     * @return オーナー予算クラス
     */
    public String getOnerYosanClass() {
        return onerYosanClass;
    }
    /**
     * オーナー予算クラスを設定します。
     * @param onerYosanClass オーナー予算クラス
     */
    public void setOnerYosanClass(String onerYosanClass) {
        this.onerYosanClass = onerYosanClass;
    }
    
    /**
     * オーナー予算を取得します。
     * @return オーナー予算
     */
    public BigDecimal getOnerYosan() {
        return onerYosan;
    }
    /**
     * オーナー予算を設定します。
     * @param onerYosan オーナー予算
     */
    public void setOnerYosan(BigDecimal onerYosan) {
        this.onerYosan = onerYosan;
    }
    
    /**
     * 表示区分を取得します。
     * @return 表示区分
     */
    public String getDispKbn() {
        return dispKbn;
    }
    /**
     * 表示区分を設定します。
     * @param dispKbn 表示区分
     */
    public void setDispKbn(String dispKbn) {
        this.dispKbn = dispKbn;
    }
    
    /**
     * trのクラスを取得します。
     * @return trのクラス
     */
    public String getRClass() {
        return rClass;
    }
    /**
     * trのクラスを設定します。
     * @param rClass trのクラス
     */
    public void setRClass(String rClass) {
        this.rClass = rClass;
    }
    
    /**
     * 補正店舗カウントを取得します。
     * @return
     */
    public BigDecimal getHoseiTenpoCnt() {
        return this.hoseiTenpoCnt;
    }
    
    /**
     * 補正店舗カウントを設定します。
     * @param hoseiTenpoCnt
     */
    public void setHoseiTenpoCnt( BigDecimal hoseiTenpoCnt ) {
        this.hoseiTenpoCnt = hoseiTenpoCnt;
    }
    
    /**
     * 補正前年店舗カウントを取得します。
     * @return
     */
    public BigDecimal getHoseiZenTenpoCnt() {
        return this.hoseiZenTenpoCnt;
    }
    
    /**
     * 補正前年店舗カウントを設定します。
     * @param hoseiZenTenpoCnt
     */
    public void setHoseiZenTenpoCnt( BigDecimal hoseiZenTenpoCnt) {
        this.hoseiZenTenpoCnt = hoseiZenTenpoCnt;
    }
	/**
	 * 予算対象店舗数取得処理
	 * @return yosanMiseCnt を戻します。
	 */
	public BigDecimal getYosanMiseCnt() {
		return yosanMiseCnt;
	}
	/**
	 * 予算対象店舗数設定処理
	 * @param yosanMiseCnt 設定する yosanMiseCnt。
	 */
	public void setYosanMiseCnt(BigDecimal yosanMiseCnt) {
		this.yosanMiseCnt = yosanMiseCnt;
	}
    
    /**
     * 発行枚数
     * @return issueCnt を戻します。
     */
    public BigDecimal getIssueCnt() {
        return issueCnt;
    }
    /**
     * 発行枚数
     * @param issueCnt 設定する 
     */
    public void setIssueCnt(BigDecimal issueCnt) {
        this.issueCnt = issueCnt;
    }
    
    /**
     * 発行枚数クラスを取得します。
     * @return 売上クラス
     */
    public String getIssueCntClass() {
        return issueCntClass;
    }
    /**
     * 発行枚数クラスを設定します。
     * @param issueCntClass 発行枚数クラス
     */
    public void setIssueCntClass(String issueCntClass) {
        this.issueCntClass = issueCntClass;
    }
    
    /**
     * チャージ金額
     * @return chargeKin を戻します。
     */
    public BigDecimal getChargeKin() {
        return chargeKin;
    }
    /**
     * チャージ金額
     * @param chargeKin 設定する 
     */
    public void setChargeKin(BigDecimal chargeKin) {
        this.chargeKin = chargeKin;
    }
    /**
     * チャージ金額クラスを取得します。
     * @return 売上クラス
     */
    public String getChargeKinClass() {
        return chargeKinClass;
    }
    /**
     * チャージ金額クラスを設定します。
     * @param issueCntClass 発行枚数クラス
     */
    public void setChargeKinClass(String chargeKinClass) {
        this.chargeKinClass = chargeKinClass;
    }
    /**
     * チャージ件数
     * @return chargeKin を戻します。
     */
    public BigDecimal getChargeKen() {
        return chargeKen;
    }
    /**
     * チャージ件数
     * @param chargeKin 設定する 
     */
    public void setChargeKen(BigDecimal chargeKen) {
        this.chargeKen = chargeKen;
    }
    /**
     * チャージ件数クラスを取得します。
     * @return 売上クラス
     */
    public String getChargeKenClass() {
        return chargeKenClass;
    }
    /**
     * チャージ件数クラスを設定します。
     * @param issueCntClass 発行枚数クラス
     */
    public void setChargeKenClass(String chargeKenClass) {
        this.chargeKenClass = chargeKenClass;
    }
    /**
     * 前年発行枚数
     * @return chargeKin を戻します。
     */
    public BigDecimal getZenIssueCnt() {
        return zenIssueCnt;
    }
    /**
     * 前年発行枚数
     * @param chargeKin 設定する 
     */
    public void setZenIssueCnt(BigDecimal zenIssueCnt) {
        this.zenIssueCnt = zenIssueCnt;
    }
    /**
     * 前年発行枚数クラス
     * @return chargeKin を戻します。
     */
    public String getZenIssueCntClass() {
        return zenIssueCntClass;
    }
    /**
     * 前年発行枚数クラス
     * @param chargeKin 設定する 
     */
    public void setZenIssueCntClass(String zenIssueCntClass) {
        this.zenIssueCntClass = zenIssueCntClass;
    }
    
    /**
     * 発行枚数（前年比）を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenIssueCntHiritu() {
        return zenIssueCntHiritu;
    }
    /**
     * 発行枚数（前年比）を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenIssueCntHiritu(BigDecimal zenIssueCntHiritu) {
        this.zenIssueCntHiritu = zenIssueCntHiritu;
    }
    /**
     * 発行枚数（前年比）クラスを取得します。
     * @return 達成率（売上)クラス
     */
    public String getZenIssueCntHirituClass() {
        return zenIssueCntHirituClass;
    }
    /**
     * 発行枚数（前年比）クラスを設定します。
     * @param tasseiUriClass 達成率（売上)クラス
     */
    public void setZenIssueCntHirituClass(String zenIssueCntHirituClass) {
        this.zenIssueCntHirituClass = zenIssueCntHirituClass;
    }
    /**
     * 前年チャージ金額
     * @return zenChargeKin を戻します。
     */
    public BigDecimal getZenChargeKin() {
        return zenChargeKin;
    }
    /**
     * 前年チャージ金額
     * @param zenChargeKin 設定する 
     */
    public void setZenChargeKin(BigDecimal zenChargeKin) {
        this.zenChargeKin = zenChargeKin;
    }
    /**
     * 前年チャージ金額クラス
     * @return zenChargeKinClass を戻します。
     */
    public String getZenChargeKinClass() {
        return zenChargeKinClass;
    }
    /**
     * 前年チャージ金額クラス
     * @param zenChargeKinClass 設定する 
     */
    public void setZenChargeKinClass(String zenChargeKinClass) {
        this.zenChargeKinClass = zenChargeKinClass;
    }
    
    /**
     * チャージ金額（前年比）
     * @return zenChargeKin を戻します。
     */
    public BigDecimal getZenHiChargeKin() {
        return zenHiChargeKin;
    }
    /**
     * チャージ金額（前年比）
     * @param zenChargeKin 設定する 
     */
    public void setZenHiChargeKin(BigDecimal zenHiChargeKin) {
        this.zenHiChargeKin = zenHiChargeKin;
    }
    /**
     * チャージ金額（前年比）クラス
     * @return zenChargeKinClass を戻します。
     */
    public String getZenHiChargeKinClass() {
        return zenHiChargeKinClass;
    }
    /**
     * チャージ金額（前年比）クラス
     * @param zenChargeKinClass 設定する 
     */
    public void setZenHiChargeKinClass(String zenHiChargeKinClass) {
        this.zenHiChargeKinClass = zenHiChargeKinClass;
    }
    
    /**
     * チャージ金額（売上比）
     * @return urihiChargeKin を戻します。
     */
    public BigDecimal getUrihiChargeKin() {
        return urihiChargeKin;
    }
    /**
     * チャージ金額（売上比）
     * @param urihiChargeKin 設定する 
     */
    public void setUrihiChargeKin(BigDecimal urihiChargeKin) {
        this.urihiChargeKin = urihiChargeKin;
    }
    /**
     * チャージ金額（売上比）クラス
     * @return urihiChargeKinClass を戻します。
     */
    public String getUrihiChargeKinClass() {
        return urihiChargeKinClass;
    }
    /**
     * チャージ金額（売上比）クラス
     * @param urihiChargeKinClass 設定する 
     */
    public void setUrihiChargeKinClass(String urihiChargeKinClass) {
        this.urihiChargeKinClass = urihiChargeKinClass;
    }
    
    /**
     * 前年チャージ件数
     * @return zenChargeKen を戻します。
     */
    public BigDecimal getZenChargeKen() {
        return zenChargeKen;
    }
    /**
     * 前年チャージ件数
     * @param zenChargeKen 設定する 
     */
    public void setZenChargeKen(BigDecimal zenChargeKen) {
        this.zenChargeKen = zenChargeKen;
    }
    /**
     * 前年チャージ件数クラス
     * @return zenChargeKenClass を戻します。
     */
    public String geZenChargeKenClass() {
        return zenChargeKenClass;
    }
    /**
     * 前年チャージ金額クラス
     * @param zenChargeKenClass 設定する 
     */
    public void setZenChargeKenClass(String zenChargeKenClass) {
        this.zenChargeKenClass = zenChargeKenClass;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu) {
        this.kyakusu = kyakusu;
    }
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getZenKyakusu() {
        return zenKyakusu;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusu 前年客数
     */
    public void setZenKyakusu(BigDecimal zenkyakusu) {
        this.zenKyakusu = zenkyakusu;
    }
    
    /**
    * チャージ件数（客数比）を取得します。
    * @return チャージ件数（客数比）
    */
   public BigDecimal getChargeKenKyakuHi() {
       return chargeKenKyakuHi;
   }
   /**
    * チャージ件数（客数比）を設定します。
    * @param kyakusu チャージ件数（客数比）
    */
   public void setChargeKenKyakuHi(BigDecimal chargeKenKyakuHi) {
       this.chargeKenKyakuHi = chargeKenKyakuHi;
   }
    
    /**
     * チャージ件数（客数比）クラス
     * @return chargeKenKyakuHiClass を戻します。
     */
    public String getChargeKenKyakuHiClass() {
        return chargeKenKyakuHiClass;
    }
    /**
     * チャージ件数（客数比）クラス
     * @param chargeKenKyakuHiClass 設定する 
     */
    public void setChargeKenKyakuHiClass(String chargeKenKyakuHiClass) {
        this.chargeKenKyakuHiClass = chargeKenKyakuHiClass;
    }
    /**
     * チャージ件数（前年比）を取得します。
     * @return チャージ件数（前年比）
     */
    public BigDecimal getChargeKenZenHi() {
        return chargeKenZenHi;
    }
    /**
     * チャージ件数（前年比）を設定します。
     * @param chargeKenZenHi チャージ件数（前年比）
     */
    public void setChargeKenZenHi(BigDecimal chargeKenZenHi) {
        this.chargeKenZenHi = chargeKenZenHi;
    }
    
    /**
     * チャージ金額（前年比）クラス
     * @return zenChargeKinClass を戻します。
     */
    public String getChargeKenZenHiClass() {
        return chargeKenZenHiClass;
    }
    /**
     * チャージ金額（前年比）クラス
     * @param zenKyakusuHiClass 設定する 
     */
    public void setChargeKenZenHiClass(String chargeKenZenHiClass) {
        this.chargeKenZenHiClass = chargeKenZenHiClass;
    }
    
    /**
     * チャージ単価を取得します。
     * @return zenChargeKinClass を戻します。
     */
    public BigDecimal getChargeTanka() {
        return chargeTanka;
    }
    /**
     * チャージ単価を設定します。
     * @param zenChargeKinClass 設定する 
     */
    public void setChargeTanka(BigDecimal chargeTanka) {
        this.chargeTanka = chargeTanka;
    }
    /**
     * チャージ単価クラス
     * @return zenChargeKinClass を戻します。
     */
    public String getChargeTankaClass() {
        return chargeTankaClass;
    }
    /**
     * チャージ単価クラス
     * @param zenChargeKinClass 設定する 
     */
    public void setChargeTankaClass(String chargeTankaClass) {
        this.chargeTankaClass = chargeTankaClass;
    }
    /**
     * 前年チャージ単価を取得します。
     * @return zenChargeKinClass を戻します。
     */
    public BigDecimal getZenChargeTanka() {
        return zenChargeTanka;
    }
    /**
     * 前年チャージ単価を設定します。
     * @param zenChargeTanka 設定する 
     */
    public void setZenChargeTanka(BigDecimal zenChargeTanka) {
        this.zenChargeTanka = zenChargeTanka;
    }
    
    /**
     * チャージ単価（単価比）を取得します。
     * @return zenChargeKinClass を戻します。
     */
    public BigDecimal getChargeTankahi() {
        return chargeTankahi;
    }
    /**
     * チャージ単価（単価比）を設定します。
     * @param zenChargeKinClass 設定する 
     */
    public void setChargeTankahi(BigDecimal chargeTankahi) {
        this.chargeTankahi = chargeTankahi;
    }
    /**
     * チャージ単価（単価比）クラス
     * @return zenChargeKinClass を戻します。
     */
    public String getChargeTankahiClass() {
        return chargeTankahiClass;
    }
    /**
     * チャージ単価（単価比）クラス
     * @param zenChargeKinClass 設定する 
     */
    public void setChargeTankahiClass(String chargeTankahiClass) {
        this.chargeTankahiClass = chargeTankahiClass;
    }
    /**
     * チャージ単価（客数比）を取得します。
     * @return zenChargeKinClass を戻します。
     */
    public BigDecimal getZenChargeTankahi() {
        return zenChargeTankahi;
    }
    /**
     * チャージ単価（客数比）を設定します。
     * @param zenChargeKinClass 設定する 
     */
    public void setZenChargeTankahi(BigDecimal zenChargeTankahi) {
        this.zenChargeTankahi = zenChargeTankahi;
    }
    /**
     * チャージ単価（客数比）クラス
     * @return zenChargeKinClass を戻します。
     */
    public String getZenChargeTankahiClass() {
        return zenChargeTankahiClass;
    }
    /**
     * チャージ単価（客数比）クラス
     * @param zenChargeKinClass 設定する 
     */
    public void setZenChargeTankahiClass(String zenChargeTankahiClass) {
        this.zenChargeTankahiClass = zenChargeTankahiClass;
    }
    /**
     * 決済金額を取得します。
     * @return kessaiKin を戻します。
     */
    public BigDecimal getKessaiKin() {
        return kessaiKin;
    }
    /**
     * 決済金額を設定します。
     * @param kessaiKin 設定する 
     */
    public void setKessaiKin(BigDecimal kessaiKin) {
        this.kessaiKin = kessaiKin;
    }
    /**
     * 決済金額クラス
     * @return kessaiKinClass を戻します。
     */
    public String getKessaiKinClass() {
        return kessaiKinClass;
    }
    /**
     * 決済金額クラス
     * @param kessaiKinClass 設定する 
     */
    public void setKessaiKinClass(String kessaiKinClass) {
        this.kessaiKinClass = kessaiKinClass;
    }
    /**
     * 前年決済金額を取得します。
     * @return zenKessaiKin を戻します。
     */
    public BigDecimal getZenKessaiKin() {
        return zenKessaiKin;
    }
    /**
     * 前年決済金額を設定します。
     * @param zenKessaiKin 設定する 
     */
    public void setZenKessaiKin(BigDecimal zenKessaiKin) {
        this.zenKessaiKin = zenKessaiKin;
    }   
    /**
     * 決済件数を取得します。
     * @return kessaiKin を戻します。
     */
    public BigDecimal getKessaiKen() {
        return kessaiKen;
    }
    /**
     * 決済件数を設定します。
     * @param kessaiKin 設定する 
     */
    public void setKessaiKen(BigDecimal kessaiKen) {
        this.kessaiKen = kessaiKen;
    }    
    
    /**
     * 前年決済件数を取得します。
     * @return zenKessaiKen を戻します。
     */
    public BigDecimal getZenKessaiKen() {
        return zenKessaiKen;
    }
    /**
     * 前年決済件数を設定します。
     * @param zenKessaiKen 設定する 
     */
    public void setZenKessaiKen(BigDecimal zenKessaiKen) {
        this.zenKessaiKen = zenKessaiKen;
    }   
    
    /**
     * 決済件数クラスを取得します。
     * @return kessaiKenClass を戻します。
     */
    public String getKessaiKenClass() {
        return kessaiKenClass;
    }
    /**
     * 決済件数クラスを設定します。
     * @param kessaiKenClass 設定する 
     */
    public void setKessaiKenClass(String kessaiKenClass) {
        this.kessaiKenClass = kessaiKenClass;
    }
    /**
     * 決済金額（売上比）を取得します。
     * @return kessaiKinUriHi を戻します。
     */
    public BigDecimal getKessaiKinUriHi() {
        return kessaiKinUriHi;
    }
    /**
     * 決済金額（売上比）を設定します。
     * @param kessaiKinUriHi 設定する 
     */
    public void setKessaiKinUriHi(BigDecimal kessaiKinUriHi) {
        this.kessaiKinUriHi = kessaiKinUriHi;
    }
    /**
     * 決済金額（売上比）クラス
     * @return kessaiKinUriHiClass を戻します。
     */
    public String getKessaiKinUriHiClass() {
        return kessaiKinUriHiClass;
    }
    /**
     * 決済金額（売上比）クラス
     * @param kessaiKinUriHiClass 設定する 
     */
    public void setKessaiKinUriHiClass(String kessaiKinUriHiClass) {
        this.kessaiKinUriHiClass = kessaiKinUriHiClass;
    }
    /**
     * 決済金額（前年比）を取得します。
     * @return kessaiKinZenHi を戻します。
     */
    public BigDecimal getKessaiKinZenHi() {
        return kessaiKinZenHi;
    }
    /**
     * 決済金額（前年比）を設定します。
     * @param kessaiKinZenHi 設定する 
     */
    public void setKessaiKinZenHi(BigDecimal kessaiKinZenHi) {
        this.kessaiKinZenHi = kessaiKinZenHi;
    }
    /**
     * 決済金額（前年比）クラス
     * @return kessaiKinZenHiClass を戻します。
     */
    public String getKessaiKinZenHiClass() {
        return kessaiKinZenHiClass;
    }
    /**
     * 決済金額（前年比）クラス
     * @param kessaiKinZenHiClass 設定する 
     */
    public void setKessaiKinZenHiClass(String kessaiKinZenHiClass) {
        this.kessaiKinZenHiClass = kessaiKinZenHiClass;
    }
    
    /**
     * 決済件数（客数比）を取得します。
     * @return kessaiKenKyakuHi を戻します。
     */
    public BigDecimal getKessaiKenKyakuHi() {
        return kessaiKenKyakuHi;
    }
    /**
     * 決済件数（客数比）を設定します。
     * @param kessaiKenKyakuHi 設定する 
     */
    public void setKessaiKenKyakuHi(BigDecimal kessaiKenKyakuHi) {
        this.kessaiKenKyakuHi = kessaiKenKyakuHi;
    }
    /**
     * 決済件数（客数比）クラス
     * @return kessaiKenKyakuHiClass を戻します。
     */
    public String getKessaiKenKyakuHiClass() {
        return kessaiKenKyakuHiClass;
    }
    /**
     * 決済件数（客数比）クラス
     * @param kessaiKenKyakuHiClass 設定する 
     */
    public void setKessaiKenKyakuHiClass(String kessaiKenKyakuHiClass) {
        this.kessaiKenKyakuHiClass = kessaiKenKyakuHiClass;
    }
    
    /**
     * 決済件数（前年比）を取得します。
     * @return kessaiKenZenHi を戻します。
     */
    public BigDecimal getKessaiKenZenHi() {
        return kessaiKenZenHi;
    }
    /**
     * 決済件数（前年比）を設定します。
     * @param kessaiKenKyakuHi 設定する 
     */
    public void setKessaiKenZenHi(BigDecimal kessaiKenZenHi) {
        this.kessaiKenZenHi = kessaiKenZenHi;
    }
    /**
     * 決済件数（前年比）クラス
     * @return kessaiKenKyakuHiClass を戻します。
     */
    public String getKessaiKenZenHiClass() {
        return kessaiKenZenHiClass;
    }
    /**
     * 決済件数（前年比）クラス
     * @param kessaiKenKyakuHiClass 設定する 
     */
    public void setKessaiKenZenHiClass(String kessaiKenZenHiClass) {
        this.kessaiKenZenHiClass = kessaiKenZenHiClass;
    }
    
    /**
     * 決済単価を取得します。
     * @return kessaiKinTanka を戻します。
     */
    public BigDecimal getKessaiKinTanka() {
        return kessaiKinTanka;
    }
    /**
     * 決済単価を設定します。
     * @param kessaiKinTanka 設定する 
     */
    public void setKessaiKinTanka(BigDecimal kessaiKinTanka) {
        this.kessaiKinTanka = kessaiKinTanka;
    }
    
    /**
     * 決済単価クラス
     * @return kessaiKinTankaHiClass を戻します。
     */
    public String getKessaiKinTankaClass() {
        return kessaiKinTankaClass;
    }
    /**
     * 決済単価クラス
     * @param kessaiKinTankaClass 設定する 
     */
    public void setKessaiKinTankaClass(String kessaiKinTankaClass) {
        this.kessaiKinTankaClass = kessaiKinTankaClass;
    }
    
    /**
     * 決済単価（単価比）を取得します。
     * @return kessaiKinTanka を戻します。
     */
    public BigDecimal getKessaiKinTankaHi() {
        return kessaiKinTankaHi;
    }
    /**
     * 決済単価（単価比）を設定します。
     * @param kessaiKinTankaHi 設定する 
     */
    public void setKessaiKinTankaHi(BigDecimal kessaiKinTankaHi) {
        this.kessaiKinTankaHi = kessaiKinTankaHi;
    }
    /**
     * 決済単価（単価比）クラス
     * @return kessaiKinZenTankaHiClass を戻します。
     */
    public String getKessaiKinTankaHiClass() {
        return kessaiKinTankaHiClass;
    }
    /**
     * 決済単価（単価比）クラス
     * @param kessaiKinZenTankaHiClass 設定する 
     */
    public void setKessaiKinTankaHiClass(String kessaiKinTankaHiClass) {
        this.kessaiKinTankaHiClass = kessaiKinTankaHiClass;
    }   
    /**
     * 決済単価（前年比）を取得します。
     * @return kessaiKinZenTankaHi を戻します。
     */
    public BigDecimal getKessaiKinZenTankaHi() {
        return kessaiKinZenTankaHi;
    }
    /**
     * 決済単価（前年比）を設定します。
     * @param kessaiKinZenTankaHi 設定する 
     */
    public void setKessaiKinZenTankaHi(BigDecimal kessaiKinZenTankaHi) {
        this.kessaiKinZenTankaHi = kessaiKinZenTankaHi;
    }
    /**
     * 決済単価（前年比）クラス
     * @return kessaiKinZenTankaHiClass を戻します。
     */
    public String getKessaiKinZenTankaHiClass() {
        return kessaiKinZenTankaHiClass;
    }
    /**
     * 決済単価（前年比）クラス
     * @param kessaiKinZenTankaHiClass 設定する 
     */
    public void setKessaiKinZenTankaHiClass(String kessaiKinZenTankaHiClass) {
        this.kessaiKinZenTankaHiClass = kessaiKinZenTankaHiClass;
    }
    /**
     * 予算を取得します。
     * @return Yosan を戻します。
     */
    public BigDecimal getSibuYosan() {
        return sibuYosan;
    }
    /**
     * 予算を設定します。
     * @param Yosan 設定する 
     */
    public void setSibuYosan(BigDecimal sibuYosan) {
        this.sibuYosan = sibuYosan;
    }   
    
    /**
     * 発行枚数(前年差)を取得します。
     * @return 発行枚数(前年差)
     */
    public BigDecimal getZenSaIssueCnt() {
        return zenSaIssueCnt;
    }
    /**
     * 発行枚数(前年差)を設定します。
     * @param zenSaIssueCnt 発行枚数(前年差)
     */
    public void setZenSaIssueCnt(BigDecimal zenSaIssueCnt) {
        this.zenSaIssueCnt = zenSaIssueCnt;
    }
    /**
     * チャージ金額(前年差)を取得します。
     * @return チャージ金額(前年差)
     */
    public BigDecimal getZenSaChargeKin() {
        return zenSaChargeKin;
    }
    /**
     * チャージ金額(前年差)を設定します。
     * @param zenSaCharge チャージ金額(前年差)
     */
    public void setZenSaChargeKin(BigDecimal zenSaChargeKin) {
        this.zenSaChargeKin = zenSaChargeKin;
    }

    /**
     * チャージ件数(前年差)を取得します。
     * @return チャージ件数(前年差)
     */
    public BigDecimal getZenSaChargeKen() {
        return zenSaChargeKen;
    }
    /**
     * チャージ件数(前年差)を設定します。
     * @param zenSaChargeKen チャージ金件数(前年差)
     */
    public void setZenSaChargeKen(BigDecimal zenSaChargeKen) {
        this.zenSaChargeKen = zenSaChargeKen;
    }
   
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakuTanka() {
        return kyakuTanka;
    }
    /**
     * 客単価を設定します。
     * @param kyakuTanka 客単価
     */
    public void setKyakuTanka(BigDecimal kyakuTanka) {
        this.kyakuTanka = kyakuTanka;
    }
    
    /**
     * 前年客単価を取得します。
     * @return 前年客単価
     */
    public BigDecimal getZenTanka() {
        return zenKyakuTanka;
    }
    /**
     * 前年客単価を設定します。
     * @param zenZyakuTanka 前年客単価
     */
    public void setZenTanka(BigDecimal zenKyakuTanka) {
        this.zenKyakuTanka = zenKyakuTanka;
    }
    
    /**
     * 客単価（前年比）を取得します。
     * @return 客単価（前年比）
     */
    public BigDecimal getKyakuTankaZenhi() {
        return ｋyakuTankaZenhi;
    }
    /**
     * 客単価（前年比）を設定します。
     * @param 客単価（前年比）
     */
    public void setKyakuTankaZenhi(BigDecimal ｋyakuTankaZenhi) {
        this.ｋyakuTankaZenhi = ｋyakuTankaZenhi;
    }
    
    /**
     * 客単価（単価比）を取得します。
     * @return 客単価（単価比）
     */
    public BigDecimal getKyakuTankaTanhi() {
        return kyakuTankaTanhi;
    }
    /**
     * 客単価（単価比）を設定します。
     * @param 客単価（単価比）
     */
    public void setKyakuTankaTanhi(BigDecimal kyakuTankaTanhi) {
        this.kyakuTankaTanhi = kyakuTankaTanhi;
    }
    /**
     * 客数(前年差)を取得します。
     * @return 客数(前年差)
     */
    public BigDecimal getZenSaKyakusu() {
        return zenSaKyakusu;
    }
    /**
     * 客数(前年差)を設定します。
     * @param zenSaKyakusu 客数(前年差)
     */
    public void setZenSaKyakusu(BigDecimal zenSaKyakusu) {
        this.zenSaKyakusu = zenSaKyakusu;
    }
    /**
     * 決済金額（前年差）を取得します。
     * @return 決済金額（前年差）
     */
    public BigDecimal getZenSaKessaiKin() {
        return zenSaKessaiKin;
    }
    /**
     * 決済金額（前年差）を設定します。
     * @param zenSaKessaiKin 決済金額（前年差）
     */
    public void setZenSaKessaiKin(BigDecimal zenSaKessaiKin) {
        this.zenSaKessaiKin = zenSaKessaiKin;
    }
    /**
     * 決済件数（前年差）を取得します。
     * @return 決済件数（前年差）
     */
    public BigDecimal getZenSaKessaiKen() {
        return zenSaKessaiKen;
    }
    /**
     * 決済件数(前年差)を設定します。
     * @param zenSaKessaiKen 決済件数（前年差）
     */
    public void setZenSaKessaiKen(BigDecimal zenSaKessaiKen) {
        this.zenSaKessaiKen = zenSaKessaiKen;
    }
    /**
     * 前年決済単価を取得します。
     * @return zenKessaiKinTanka を戻します。
     */
    public BigDecimal getZenKessaiKinTanka() {
        return zenKessaiKinTanka;
    }
    /**
     * 前年決済単価を設定します。
     * @param zenKessaiKinTanka 設定する 
     */
    public void setZenKessaiKinTanka(BigDecimal zenKessaiKinTanka) {
        this.zenKessaiKinTanka = zenKessaiKinTanka;
    }
    
    /**
     * 入金取消金額を取得します。
     * @return 入金取消金額
     */
    public BigDecimal getChargeKinCancel() {
        return chargeKinCancel;
    }
    /**
     * 入金取消金額を設定します。
     * @param chargeKinCancel 入金取消金額
     */
    public void setChargeKinCancel(BigDecimal chargeKinCancel) {
        this.chargeKinCancel = chargeKinCancel;
    }
    
    /**
     * 入金取消件数を取得します。
     * @return 入金取消件数
     */
    public BigDecimal getChargeKenCancel() {
        return chargeKenCancel;
    }
    /**
     * 入金取消件数を設定します。
     * @param chargeKenCancel 入金取消件数
     */
    public void setChargeKenCancel(BigDecimal chargeKenCancel) {
        this.chargeKenCancel = chargeKenCancel;
    }
    
    /**
     * 利用取消金額を取得します。
     * @return 利用取消金額
     */
    public BigDecimal getUseKinCancel() {
        return useKinCancel;
    }
    /**
     * 利用取消金額を設定します。
     * @param useKinCancel 利用取消金額
     */
    public void setUseKinCancel(BigDecimal useKinCancel) {
        this.useKinCancel = useKinCancel;
    }

    /**
     * 利用取消件数を取得します。
     * @return 利用取消件数
     */
    public BigDecimal getUseKenCancel() {
        return useKenCancel;
    }
    /**
     * 利用取消件数を設定します。
     * @param useKinCancel 利用取消件数
     */
    public void setUseKenCancel(BigDecimal useKenCancel) {
        this.useKenCancel = useKenCancel;
    }
    
    /**
     * 発行ボーナスバリューを取得します。
     * @return 発行ボーナスバリュー
     */
    public BigDecimal getBonusVIssue() {
        return bonusVIssue;
    }
    /**
     * 発行ボーナスバリューを設定します。
     * @param bonusVIssue 発行ボーナスバリュー
     */
    public void setBonusVIssue(BigDecimal bonusVIssue) {
        this.bonusVIssue = bonusVIssue;
    }
    
    /**
     * 利用ボーナスバリューを取得します。
     * @return 利用ボーナスバリュー
     */
    public BigDecimal getBonusVUse() {
        return bonusVUse;
    }
    
    /**
     * 利用ボーナスバリューを設定します。
     * @param bonusVUse 利用ボーナスバリュー
     */
    public void setBonusVUse(BigDecimal bonusVUse) {
        this.bonusVUse = bonusVUse;
    }
    
    /**
     * 発行クーポンバリューを取得します。
     * @return 発行クーポンバリュー
     */
    public BigDecimal getCouponVIssue() {
        return couponVIssue;
    }
    /**
     * 発行クーポンバリューを設定します。
     * @param couponVIssue 発行クーポンバリュー
     */
    public void setCouponVIssue(BigDecimal couponVIssue) {
        this.couponVIssue = couponVIssue;
    }
    
    /**
     * 利用クーポンバリューを取得します。
     * @return 利用クーポンバリュー
     */
    public BigDecimal getCouponVUse() {
        return couponVUse;
    }
    /**
     * 利用クーポンバリューを設定します。
     * @param couponVUse 利用クーポンバリュー
     */
    public void setCouponVUse(BigDecimal couponVUse) {
        this.couponVUse = couponVUse;
    }
    /**
     * 前受残高を取得します。
     * @return 前受残高
     */
    public BigDecimal getZandaka() {
        return zandaka;
    }
    /**
     * 前受残高を設定します。
     * @param zandaka 前受残高
     */
    public void setZandaka(BigDecimal zandaka) {
        this.zandaka = zandaka;
    }
    
    /**
     * SVコードを取得します。
     * @return SVコード
     */
    public String getSvCd() {
        if(svCd == null) {
            return "";
        }
        return svCd;
    }
    /**
     * SVコードを設定します。
     * @param svCd SVコード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    
    /**
     * SV名称を取得します。
     * @return SV名称
     */
    public String getUsernamekj() {
        if(usernamekj == null) {
            return "";
        }
        return usernamekj;
    }
    /**
     * SV名称を設定します。
     * @param usernamekj_ SV名称
     */
    public void setUsernamekj(String usernamekj) {
        this.usernamekj = usernamekj;
    }
    
}
