package jp.co.isid.mos.bird.bizreport.common.dto;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.CodBlock;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.bizreport.common.entity.CodTaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.entity.CodTaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.entity.CodTenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.entity.CodZenDataShubetu;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 業務管理 グループ業績共通Dto
 * 
 * @author xkinu
 */
public class BizReportGroupDto {
	/** 画面遷移区分：初期値 [-1] */
	public static final int SCENECHANGE_KBN_INIT = -1;
	/** 画面遷移区分：現行画面からの遷移時 [0] */
	public static final int SCENECHANGE_KBN_SELF = 0;
	/** 画面遷移区分：照会画面への遷移時 [1] */
	public static final int SCENECHANGE_KBN_VIEW = 1;

    /** ユーザータイプコード：01:本部 */
    public static final String USERTYPE_HONBU = "01";
    /** ユーザータイプコード：02:オーナー */
    public static final String USERTYPE_ONER = "02";
    /** ユーザータイプコード：03:店舗 */
    public static final String USERTYPE_MISE = "03";
    //画面ID
    private String viewId;
    // ユーザーID
    private String userId;
    // 今日の日付
    private String today;
    // アプリ日付
    private String appdate;
    //ユーザータイプコード
    private String usertypeCd;
    //対象店舗数
    private int taishoTenpoCnt;
    /** 画面遷移区分 */
    private int scenechangedKbn = SCENECHANGE_KBN_INIT;
    /** 別ウィンドウフラグ */
    private String newWindowFlg;
    //条件画面：会社コード
    private String companyCd;
    //条件画面：会社名称
    private String companyName;
    /**
     * 条件項目：店舗種別
     *    ・全店：ALL
     *    ・前年対象店：ZENNENTEN
     *    ・予算対象店：YOSANTEN
     *    ・新店：SINTEN
     */
    private String tenpoShu;
    /**
     * 条件項目：店舗種別名称
     *    ・全店：ALL
     *    ・前年対象店：ZENNENTEN
     *    ・予算対象店：YOSANTEN
     *    ・新店：SINTEN
     */
    private String tenpoShuName;
    /**
     * 条件項目：前年データ種別
     *    ・前年同月営業日補正：DOGETUHOSEI
     *    ・前年同月：DOGETU
     *    ・前年同日：DOJITU
     *    ・前年同曜：DOYO
     */
    private String zennenDataShubetu;
    /**
     * 条件項目：前年データ種別名称
     *    ・前年同月営業日補正：DOGETUHOSEI
     *    ・前年同月：DOGETU
     *    ・前年同日：DOJITU
     *    ・前年同曜：DOYO
     */
    private String zennenDataShubetuName;
    /** 
     * 条件項目：対象条件
     *    ・全社:ALL
     *    ・モスバーガー：MOS
     *    ・モスバーガー以外：NOTMOS
     *    ・事業本部：JIGYOU
     *    ・営業エリア：SLAREA
     *    ・支部：SIBU
     *    ・個店：MISE
     */
    private String taishoJoken;
    /** 
     * 条件項目：対象条件名称
     *    ・全社:ALL
     *    ・モスバーガー：MOS
     *    ・モスバーガー以外：NOTMOS
     *    ・事業本部：JIGYOU
     *    ・営業エリア：SLAREA
     *    ・支部：SIBU
     *    ・個店：MISE
     */
    private String taishoJokenName;
    /** 
     * 条件項目：対象期間
     *    ・直近13ヶ月:KIKAN_13
     *    ・任意月指定：KIKAN_12
     *    ・年度指定：KIKAN_NENDO
     *    
     */
    private String taishoKikan;
    /** 
     * 条件項目：対象期間名称
     *    ・直近13ヶ月:KIKAN_13
     *    ・任意月指定：KIKAN_12
     *    ・年度指定：KIKAN_NENDO
     */
    private String taishoKikanName;
    /** 
     * 条件項目：期間指定（1番目）
     */
    private String kikanSitei1;
    /** 
     * 条件項目：期間指定（1番目）名称
     */
    private String kikanSitei1Name;
    /** 
     * 条件項目：期間指定（2番目）
     */
    private String kikanSitei2;
    /** 
     * 条件項目：期間指定（2番目）名称
     */
    private String kikanSitei2Name;
    /**
     * 条件項目：表示対象
     *  対象条件で選択された対象コード
     */
    private String hyojiTaisho;
    /**
     * 条件項目：表示対象
     *  対象条件で選択された対象コード名称
     */
    private String hyojiTaishoName;
    /**
     * 条件画面：会社リスト
     */
    private List companyList;   
    /**
     * 条件画面：店舗種別リスト
     */
    private List TenpoShubetuList;
    /**
     * 条件画面：前年データ種別リスト
     */
    private List zennenDataShubetuList;
    /**
     * 条件画面：対象条件リスト
     */
    private List taishoJokenList;
    /**
     * 条件画面：表示対象リスト
     */
    private List hyojiTaishoList;
    /**
     * 条件画面：表示対象セグメントリスト
     */
    private List segmentList;
    /**
     * 条件画面：表示対象事業本部リスト
     */
    private List jigyoList;
    /**
     * 条件画面：表示対象支部リスト
     */
    private List sibuList;
    /**
     * 条件画面：表示対象営業エリアリスト
     */
    private List slareaList;
    /**
     * 条件画面：表示対象店舗（オーナー・店舗用）リスト
     */
    private List miseList;
    /**
     * 条件画面：対象期間リスト
     */
    private List taishoKikanList;
    /**
     * 条件画面：期間指定（1番目）リスト
     */
    private List kikanSitei1List;
    /**
     * 条件画面：期間指定（2番目）リスト
     */
    private List kikanSitei2List;
    /**
     * 画面表示年度リスト
     */
    private List nendoList;
    /**
     * 画面表示任意月指定年月リスト
     */
    private List monthsList;
    /**
     * 条件画面：ブロックリスト
     */
    private List blockList;
    //条件画面：ブロック
    private String blockCd;
    //条件画面：ブロック名称
    private String blockName;

    /**
     * 初期クリア処理
     *
     */
    public void initClear() {
        setCompanyList(null);
        setCompanyCd(null);
        setCompanyName(null);
        setTenpoShubetuList(null);
        setTenpoShubetu(null);
        setTenpoShubetuName(null);
        setTaishoKikanList(null);
        setTaishoKikan(null);
        setTaishoKikanName(null);
        setKikanSitei1List(null);
        setKikanSitei1(null);
        setKikanSitei1Name(null);
        setKikanSitei2List(null);
        setKikanSitei2(null);
        setKikanSitei2Name(null);
        setTaishoJokenList(null);
        setTaishoJoken(null);
        setTaishoJokenName(null);
        setHyojiTaishoList(null);
        setHyojiTaisho(null);
        setHyojiTaishoName(null);
        setBlockList(null);
        setBlockCd(null);
        setBlockName(null);
        setZenDataShubetuList(null);
        setZenDataShubetu(null);
        setZenDataShubetuName(null);
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
     * 表示対象コードリスト 取得処理
     * 
     * @return hyojiTaishoList を戻します。
     */
    public List getHyojiTaishoList() {
        return hyojiTaishoList;
    }
    /**
     * 表示対象コードリスト 設定処理
     * 
     * @param hyojiTaishoList を設定。
     */
    public void setHyojiTaishoList(List hyojiTaishoList) {
        this.hyojiTaishoList = hyojiTaishoList;
    }
    /**
     * 表示対象リスト件数 取得処理
     * 
     * @return hyojiTaishoList を戻します。
     */
    public int getHyojiTaishoListSize() {
        if(hyojiTaishoList == null) {
            return 0;
        }
        return hyojiTaishoList.size();
    }
    /**
     * 表示対象取得処理
     * 
     * @return sibuCd を戻します。
     */
    public String getHyojiTaisho() {
        return hyojiTaisho;
    }
    /**
     * 表示対象 設定処理
     * 
     * @param sibuCd を設定。
     */
    public void setHyojiTaisho(String cd) {
        this.hyojiTaisho = cd;
        if(!isNull(cd)) {
            setHyojiTaishoName(getHyojiTaishoName(cd));
        }
   }
    /**
     * 表示対象 セグメントリスト 取得処理
     * 
     * @return segmentList を戻します。
     */
    public List getSegmentList() {
        return segmentList;
    }
    /**
     * 表示対象 セグメントリスト 設定処理
     * 
     * @param segmentList を設定。
     */
    public void setSegmentList(List segmentList) {
        this.segmentList = segmentList;
    }
    /**
     * 表示対象 セグメントリスト件数 取得処理
     * 
     * @return jigyoList を戻します。
     */
    public int getSegmentListSize() {
        return (segmentList == null) ? 0 : segmentList.size();
    }
    /**
     * 表示対象 事業本部コードリスト 取得処理
     * 
     * @return jigyoList を戻します。
     */
    public List getJigyoList() {
        return jigyoList;
    }
    /**
     * 表示対象 事業本部コードリスト 設定処理
     * 
     * @param jigyoList を設定。
     */
    public void setJigyoList(List list) {
        this.jigyoList = list;
    }
    /**
     * 表示対象 事業本部リスト件数 取得処理
     * 
     * @return jigyoList を戻します。
     */
    public int getJigyoListSize() {
        if(jigyoList == null) {
            return 0;
        }
        return jigyoList.size();
    }
    /**
     * 表示対象 営業エリアコードリスト 取得処理
     * 
     * @return slareaList を戻します。
     */
    public List getSlareaList() {
        return slareaList;
    }
    /**
     * 表示対象 営業エリアコードリスト 設定処理
     * 
     * @param slareaList を設定。
     */
    public void setSlareaList(List list) {
        this.slareaList = list;
    }
    /**
     * 表示対象 営業エリアリスト件数 取得処理
     * 
     * @return slareaList を戻します。
     */
    public int getSlareaListSize() {
        if(slareaList == null) {
            return 0;
        }
        return slareaList.size();
    }
    /**
     * 表示対象 支部コードリスト 取得処理
     * 
     * @return sibuList を戻します。
     */
    public List getSibuList() {
        return sibuList;
    }
    /**
     * 表示対象 支部コードリスト 設定処理
     * 
     * @param sibuList を設定。
     */
    public void setSibuList(List list) {
        this.sibuList = list;
    }
    /**
     * 表示対象 支部リスト件数 取得処理
     * 
     * @return sibuList を戻します。
     */
    public int getSibuListSize() {
        if(sibuList == null) {
            return 0;
        }
        return sibuList.size();
    }
    /**
     * 表示対象 店舗コードリスト 取得処理
     * 
     * @return miseList を戻します。
     */
    public List getMiseList() {
        return miseList;
    }
    /**
     * 表示対象 店舗コードリスト 設定処理
     * 
     * @param miseList を設定。
     */
    public void setMiseList(List list) {
        this.miseList = list;
    }
    /**
     * 表示対象 店舗リスト件数 取得処理
     * 
     * @return miseList を戻します。
     */
    public int getMiseListSize() {
        if(miseList == null) {
            return 0;
        }
        return miseList.size();
    }
    /**
     * 表示対象名称取得処理
     * 
     * @return sibuName を戻します。
     */
    public String getHyojiTaishoName() {
        return  hyojiTaishoName;
    }
    /**
     * 表示対象名称取得処理
     * 
     * 支部コードリストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return sibuName を戻します。
     */
    public String getHyojiTaishoName(String cd) {
        if(getHyojiTaishoList() == null) {
            return null;
        }
        for (int i = 0; i < getHyojiTaishoList().size(); i++) {
            CodHyojiTaisho data = (CodHyojiTaisho) getHyojiTaishoList().get(i);
            if(data.getHyojitaishoCd().equals(cd)) {
                return data.getHyojitaishoName();
            }
        }
        return  "";
    }
    /**
     * 表示対象名称設定処理
     * 
     * @param hyojiTaishoName を設定。
     */
    public void setHyojiTaishoName(String hyojiTaishoName) {
        this.hyojiTaishoName = hyojiTaishoName;
    }
    /**
     * 店舗種別リスト 取得処理
     * 
     * @return TenpoShubetuList を戻します。
     */
    public List getTenpoShubetuList() {
        return TenpoShubetuList;
    }
    /**
     * 店舗種別リスト 設定処理
     * 
     * @param TenpoShubetuList を設定。
     */
    public void setTenpoShubetuList(List TenpoShubetuList) {
        this.TenpoShubetuList = TenpoShubetuList;
    }
    /**
     * 店舗種別 取得処理
     * 
     * @return tenpoShu を戻します。
     */
    public String getTenpoShubetu() {
        return tenpoShu;
    }
    /**
     * 店舗種別 設定処理
     * 
     * @param tenpoShu を設定。
     */
    public void setTenpoShubetu(String cd) {
        this.tenpoShu = cd;
        if(!isNull(cd)) {
            setTenpoShubetuName(getTenpoShubetuName(cd));
        }
    }
    /**
     * 店舗種別名称 取得処理
     * 
     * @return tenpoShuName を戻します。
     */
    public String getTenpoShubetuName() {
        return  tenpoShuName;
    }
    /**
     * 店舗種別名称 取得処理
     * 
     * 店舗種別リストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return tenpoShuName を戻します。
     */
    public String getTenpoShubetuName(String cd) {
        if(getTenpoShubetuList() == null) {
            return null;
        }
        for (int i = 0; i < getTenpoShubetuList().size(); i++) {
            CodTenpoShubetu data = (CodTenpoShubetu) getTenpoShubetuList().get(i);
            if(data.getCode().equals(cd)) {
                return data.getName();
            }
        }
        return  "";
    }
    /**
     * 前年データ種別リスト 取得処理
     * 
     * @return zennenDataShubetuList を戻します。
     */
    public List getZenDataShubetuList() {
        return zennenDataShubetuList;
    }
    /**
     * 前年データ種別 リスト 設定処理
     * 
     * @param zennenDataShubetuList を設定。
     */
    public void setZenDataShubetuList(List zennenDataShubetuList) {
        this.zennenDataShubetuList = zennenDataShubetuList;
    }
    /**
     * 前年データ種別 取得処理
     * 
     * @return zennenDataShubetu を戻します。
     */
    public String getZenDataShubetu() {
        return zennenDataShubetu;
    }
    /**
     * 前年データ種別 設定処理
     * 
     * @param zennenDataShubetu を設定。
     */
    public void setZenDataShubetu(String cd) {
        this.zennenDataShubetu = cd;
        if(!isNull(cd)) {
            setZenDataShubetuName(getZenDataShubetuName(cd));
        }
    }
    /**
     * 前年データ種別別名称 取得処理
     * 
     * @return zennenDataShubetuName を戻します。
     */
    public String getZenDataShubetuName() {
        return  zennenDataShubetuName;
    }
    /**
     * 前年データ種別 設定処理
     * 
     * @param zennenDataShubetuName を設定。
     */
    public void setZenDataShubetuName(String name) {
        this.zennenDataShubetuName = name;
    }
    /**
     * 前年データ種別名称 取得処理
     * 
     * 店舗種別リストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return zennenDataShubetuName を戻します。
     */
    public String getZenDataShubetuName(String cd) {
        if(getZenDataShubetuList() == null) {
            return null;
        }
        for (int i = 0; i < getZenDataShubetuList().size(); i++) {
            CodZenDataShubetu data = (CodZenDataShubetu) getZenDataShubetuList().get(i);
            if(data.getCode().equals(cd)) {
                return data.getName();
            }
        }
        return  "";
    }

    /**
     * 店舗種別名称設定処理
     * 
     * @param tenpoShuName を設定。
     */
    public void setTenpoShubetuName(String name) {
        this.tenpoShuName = name;
    }
    /**
     * 対象条件コードリスト 取得処理
     * 
     * @return taishoJokenList を戻します。
     */
    public List getTaishoJokenList() {
        return taishoJokenList;
    }
    /**
     * 対象条件コードリスト 設定処理
     * 
     * @param taishoJokenList を設定。
     */
    public void setTaishoJokenList(List taishoJokenList) {
        this.taishoJokenList = taishoJokenList;
    }
    /**
     * 対象条件 取得処理
     * 
     * @return taishoJoken を戻します。
     */
    public String getTaishoJoken() {
        return taishoJoken;
    }
    /**
     * 対象条件 設定処理
     * 
     * @param taishoJoken を設定。
     */
    public void setTaishoJoken(String cd) {
        this.taishoJoken = cd;
        if(!isNull(cd)) {
            getTaishoJokenName(getTaishoJokenName(cd));
        }
    }
    /**
     * 対象条件名称 取得処理
     * 
     * @return sibuName を戻します。
     */
    public String getTaishoJokenName() {
        return  taishoJokenName;
    }
    /**
     * 対象条件名称 設定処理
     * 
     * @param taishoJokenName を設定。
     */
    public void setTaishoJokenName(String name) {
        this.taishoJokenName = name;
    }
    /**
     * 対象条件名称 取得処理
     * 
     * 対象条件リストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return taishoJokenName を戻します。
     */
    public String getTaishoJokenName(String cd) {
        if(getTaishoJokenList() == null) {
            return null;
        }
        for (int i = 0; i < getTaishoJokenList().size(); i++) {
            CodTaishoJoken data = (CodTaishoJoken) getTaishoJokenList().get(i);
            if(data.getCode().equals(cd)) {
                return data.getName();
            }
        }
        return  "";
    }
    /**
     * ブロックコードリスト 取得処理
     * 
     * @return blockList を戻します。
     */
    public List getBlockList() {
        return blockList;
    }
    /**
     * ブロックコードリスト 設定処理
     * 
     * @param blockList を設定。
     */
    public void setBlockList(List blockList) {
        this.blockList = blockList;
    }
    /**
     * ブロックコードリスト件数 取得処理
     * 
     * @return int を戻します。
     */
    public int getBlockListSize() {
        if(blockList == null) {
            return 0;
        }
        return blockList.size();
    }
    /**
     * 対象期間リスト 取得処理
     * 
     * @return taishoKikanList を戻します。
     */
    public List getTaishoKikanList() {
        return taishoKikanList;
    }
    /**
     * 対象期間リスト 設定処理
     * 
     * @param taishoKikanList を設定。
     */
    public void setTaishoKikanList(List taishoKikanList) {
        this.taishoKikanList = taishoKikanList;
    }
    /**
     * 対象期間 取得処理
     * 
     * @return taishoKikan を戻します。
     */
    public String getTaishoKikan() {
        return taishoKikan;
    }
    /**
     * 対象期間 設定処理
     * 
     * @param taishoKikan を設定。
     */
    public void setTaishoKikan(String cd) {
        this.taishoKikan = cd;
        if(!isNull(cd)) {
            setTaishoKikanName(getTaishoKikanName(cd));
        }
    }
    /**
     * 対象期間名称 取得処理
     * 
     * @return taishoKikanName を戻します。
     */
    public String getTaishoKikanName() {
        return  taishoKikanName;
    }
    /**
     * 対象期間名称 取得処理
     * 
     * 対象期間リストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return taishoKikanName を戻します。
     */
    public String getTaishoKikanName(String cd) {
        if(getTaishoKikanList() == null) {
            return null;
        }
        for (int i = 0; i < getTaishoKikanList().size(); i++) {
            CodTaishoKikan data = (CodTaishoKikan) getTaishoKikanList().get(i);
            if(data.getCode().equals(cd)) {
                return data.getName();
            }
        }
        return  "";
    }
    /**
     * 対象期間名称 設定処理
     * 
     * @return taishoKikanName を戻します。
     */
    public void setTaishoKikanName(String name) {
        taishoKikanName=name;
    }
    /**
     * 期間指定（1番目）リスト 取得処理
     * 
     * @return kikanSitei1List を戻します。
     */
    public List getKikanSitei1List() {
        return kikanSitei1List;
    }
    /**
     * 期間指定（1番目）リスト 設定処理
     * 
     * @param kikanSitei1List を設定。
     */
    public void setKikanSitei1List(List list) {
        this.kikanSitei1List = list;
    }
    /**
     * 期間指定（1番目） 取得処理
     * 
     * @return kikanSitei1 を戻します。
     */
    public String getKikanSitei1() {
        return kikanSitei1;
    }
    /**
     * 期間指定（1番目） 設定処理
     * 
     * @param kikanSitei1 を設定。
     */
    public void setKikanSitei1(String cd) {
        this.kikanSitei1 = cd;
    }
    /**
     * 期間指定名称 取得処理
     * 
     * @return KikanSiteiName を戻します。
     */
    public String getKikanSitei1Name() {
        return  kikanSitei1Name;
    }
    /**
     * 期間指定名称 取得処理
     * 
     * 期間指定リストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return KikanSiteiName を戻します。
     */
    public String getKikanSitei1Name(String cd) {
        if(getKikanSitei1List() == null) {
            return null;
        }
        for (int i = 0; i < getKikanSitei1List().size(); i++) {
            CodKikanSitei data = (CodKikanSitei) getKikanSitei1List().get(i);
            if(data.getCode().equals(cd)) {
                return data.getName();
            }
        }
        return  "";
    }
    /**
     * 期間指定名称 設定処理
     * 
     * @return KikanSiteiName を戻します。
     */
    public void setKikanSitei1Name(String name) {
        kikanSitei1Name=name;
    }

    /**
     * 期間指定（2番目） 取得処理
     * 
     * @return kikanSitei2 を戻します。
     */
    public String getKikanSitei2() {
        return kikanSitei2;
    }
    /**
     * 期間指定（2番目） 設定処理
     * 
     * @param kikanSitei2 を設定。
     */
    public void setKikanSitei2(String cd) {
        this.kikanSitei2 = cd;
    }
    /**
     * 期間指定（2番目）名称 取得処理
     * 
     * @return kikanSitei2Name を戻します。
     */
    public String getKikanSitei2Name() {
        return  kikanSitei2Name;
    }
    /**
     * 期間指定（2番目）名称 取得処理
     * 
     * 期間指定リストから
     * 引数のコードをキーに名称を取得します。
     * 
     * @return kikanSitei2Name を戻します。
     */
    public String getKikanSitei2Name(String cd) {
        if(getKikanSitei2List() == null) {
            return null;
        }
        for (int i = 0; i < getKikanSitei2List().size(); i++) {
            CodKikanSitei data = (CodKikanSitei) getKikanSitei2List().get(i);
            if(data.getCode().equals(cd)) {
                return data.getName();
            }
        }
        return  "";
    }
    /**
     * 期間指定（2番目）名称 設定処理
     * 
     * @return kikanSitei2Name を戻します。
     */
    public void setKikanSitei2Name(String name) {
        kikanSitei2Name=name;
    }

    /**
     * 期間指定（2番目）リスト 取得処理
     * 
     * @return kikanSitei2List を戻します。
     */
    public List getKikanSitei2List() {
        return kikanSitei2List;
    }
    /**
     * 期間指定（2番目）リスト 設定処理
     * 
     * @param kikanSitei2List を設定。
     */
    public void setKikanSitei2List(List list) {
        this.kikanSitei2List = list;
    }
    /**
     * 画面表示年度13ヶ月分リスト 取得処理
     * 
     * @return monthsList を戻します。
     */
    public List getMonth12List() {
        return monthsList;
    }
    /**
     * 画面表示年度13ヶ月分リスト 設定処理
     * @param nendoList を設定。
     */
    public void setMonth12List(List list) {
        this.monthsList = list;
    }
    /**
     * 画面表示年度13ヶ月分リスト件数 取得処理
     * 
     * @param int 件数を取得。
     */
    public int getMonth12ListSize() {
        return (getMonth12List() == null) ? 0 : getMonth12List().size();
    }
    /**
     * 画面表示年度3年度分リスト 取得処理
     * 
     * @return nendoList を戻します。
     */
    public List getNendoList() {
        return nendoList;
    }
    /**
     * 画面表示年度3年度分 設定処理
     * @param nendoList を設定。
     */
    public void setNendoList(List list) {
        this.nendoList = list;
    }
    /**
     * 画面表示年度3年度分件数 取得処理
     * 
     * @param int 件数を取得。
     */
    public int getNendoListSize() {
        return (getNendoList() == null) ? 0 : getNendoList().size();
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
    public String getCompanyName(String cd) {
        if(getCompanyList() == null) {
            return null;
        }
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
     * ブロックコード取得処理
     * 
     * @return blockCd を戻します。
     */
    public String getBlockCd() {
        return blockCd;
    }
    /**
     * ブロックコード設定処理
     * 
     * @param blockCd を設定。
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    /**
     * ブロック名称取得処理
     * 
     * @return blockName を戻します。
     */
    public String getBlockName() {
        return blockName;
    }
    /**
     * ブロック名称取得処理
     * 
     * 会社コードリストから
     * 引数のコードをキーに名称を取得します。
     * @return companyName を戻します。
     */
    public String getBlockName(String cd) {
        if(getBlockList() == null) {
            return null;
        }
        for (int i = 0; i < getBlockList().size(); i++) {
            CodBlock data = (CodBlock) getBlockList().get(i);
            if(data.getBlockCd().equals(cd)) {
                return data.getBlockName();
            }
        }
        return  "";
    }
    /**
     * ブロック名称設定処理
     * 
     * @param blockName を設定。
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
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
     * @return viewId を戻します。
     */
    public String getViewId() {
        return viewId;
    }
    /**
     * @param id viewId を設定。
     */
    public void setViewId(String id) {
        this.viewId = id;
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
     * システム日付設定処理
     * YYYYMMDD型です。
     * @return today を設定します。
     */
    public void setToday(String date) {
        this.today = date;
    }
    /**
     * システム日付取得処理
     * YYYYMMDD型です。
     * @return today を戻します。
     */
    public String getToday() {
        return today;
    }
    /**
     * アプリ日付設定処理
     * YYYYMMDD型です。
     * @return appdate を設定します。
     */
    public void setAppDate(String date) {
        this.appdate = date;
    }
    /**
     * アプリ日付取得処理
     * YYYYMMDD型です。
     * @return appdate を戻します。
     */
    public String getAppDate() {
        return appdate;
    }
    /**
     * @param userId userId を取得します。
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
     * @param taishoTenpoCnt を取得します。
     */
    public int getTaishoTenpoCnt() {
        return taishoTenpoCnt;
    }
    /**
     * @param taishoTenpoCnt を取得します。
     */
    public String getTaishoTenpoCntComma() {
        NumericFormatter numFmt = new NumericFormatter();
        return numFmt.format(String.valueOf(taishoTenpoCnt));
    }
    /**
     * 
     * @param taishoTenpoCntを設定。
     */
    public void setTaishoTenpoCnt(int cnt) {
        this.taishoTenpoCnt = cnt;
    }
    /**
     * 別ウィンドウフラグ取得処理
     * @return　String
     */
    public String getNewWindowFlg() {
        return newWindowFlg;
    }
    /**
     * 別ウィンドウフラグ設定処理
     * @param flg
     */
    public void setNewWindowFlg(String flg) {
        newWindowFlg = flg;
    }
    /**
     * 天候区分漢字名称変換処理
     * @param tenko
     * @return
     */
    public static String formatTenkoKbnKj(BigDecimal tenko) {
        String name = "";
        if(tenko != null) {
            int kbn = Integer.valueOf(tenko.toString()).intValue();
            if(1==kbn) {
                name = "晴";
            }
            if(2==kbn) {
                name = "曇";
            }
            if(3==kbn) {
                name = "雨";
            }
            if(4==kbn) {
                name = "雪";
            }
            if(5==kbn) {
                name = "嵐";
            }
        }
        return name;
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
}