package jp.co.isid.mos.bird.bizreport.groupsuiiref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.SiteKbn;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.dao.UIMiseCntDao;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.dao.UISuiiGepoDao;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.dao.UISuiiGepoOnerDao;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.dao.UISuiiNipoDao;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.dao.UISuiiNipoOnerDao;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiGepo;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiNipo;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * グループ業績売上推移検索ロジック
 *
 * @modifire 2008/07/14 T.Kinugawa(ASPAC) 年度月次画面の当年以降未来月の当年売上・客数・客単価は
 *                                      全て0(ゼロ)と表示するよう変更する。
 * @modifire 2007/05/11 T.Kinugawa(ASPAC) e-mosslesと同じ前年値を表示するよう修正。
 * @modifire xkinu 2007/06/06 前回の『表示対象』値が表示される不具合対応。
 *
 * @author xkinu
 */
public class SearchLogicImpl extends SuiiRefSearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR002L02";

    /*【DAO】推移表データ(日次)検索Dao */
    private UIMiseCntDao groupSuiiRefUIMiseCntDao;
    /*【DAO】推移表データ(日次)検索Dao */
    private UISuiiNipoDao groupSuiiRefUISuiiNipoDao;
    /*【DAO】オーナー予算推移表データ(日次)検索Dao */
    private UISuiiNipoOnerDao groupSuiiRefUISuiiNipoOnerDao;
    /*【DAO】推移表データ(月次)検索Dao */
    private UISuiiGepoDao groupSuiiRefUISuiiGepoDao;
    /*【DAO】オーナー予算推移表データ(月次次)検索Dao */
    private UISuiiGepoOnerDao groupSuiiRefUISuiiGepoOnerDao;
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#newSuiiRefResultDto()
     */
    protected SuiiRefResultDto newSuiiRefResultDto() {
        return new SuiiRefResultDto();
    }
   /**
     * 月次データ検索処理
     *
     * ＜任意月指定の場合＞
     * 　【日次】売上計上日数分＋合計行のデータを検索＆算出を行う。
     * 　指定年月含めて過去12ヶ月分のデータを検索します。
     *   ただし、指定年月が当年当月(アプリ日付の年月)の場合は
     *   直近13ヶ月』仕様になり、指定年月含めて過去13ヶ月分のデータを検索
     *   【月次】の合計・平均値には当年当月の値が含まれない値になります。
     *   ソート順は降順
     *
     * ＜年度の場合＞
     * 　【月次】指定年度の〔12ヶ月分〕、〔期別〕、〔通期〕、〔合計〕の計20行のデータを検索＆算出を行う。
     *   ソート順は降順
     *
     * @param parameterDto 推移表共通DTO【Request情報】
     * @return
     * @throws Exception
     */
    protected List selectListGepo(SuiiRefParameterDto parameterDto, String fromYYYYMM, String toYYYYMM)
    {
        String sysDate = getBirdDateInfo().getSysDate();
        String userId = getBirdUserInfo().getUserID();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
        String companyCd = parameterDto.getCompanyCd();
        String tenpoShubetu = parameterDto.getTenpoShubetu();
        String zennenDataShubetu = parameterDto.getZennenDataShubetu();
        String taishoJoken = parameterDto.getTaishoJoken();
        String hyojiTaisho = parameterDto.getHyojiTaisho();
        String blockCd = parameterDto.getBlockCd();
        boolean limitFlg = getBirdUserInfo().isLimit();
        List listSuii = null;

        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
            //サイト区分”うみ”(本部)又は”おひさま”(直営店又は販社)の場合
            listSuii =getGroupSuiiRefUISuiiGepoDao().select(
                    sysDate, userId, userTypeCd
                    , companyCd, tenpoShubetu, zennenDataShubetu, taishoJoken, hyojiTaisho, blockCd
                    , fromYYYYMM, toYYYYMM, limitFlg);
            //店舗検索時、店舗所属支部をパラメータにセットする
            setParamterSyozokuSibu(listSuii,parameterDto);
        }
        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
            String onerCd = null;
            if(UserType.isOner(userTypeCd)) {
                onerCd = ((UIUserOner)SuiiRefUtil.getUserOnerCd(getBirdUserInfo(), companyCd)).getOnerCd();
            }
            //サイト区分”やま”（オーナー又は店舗(ＦＣ店)）の場合
            listSuii =getGroupSuiiRefUISuiiGepoOnerDao().select(
                    userId, userTypeCd, onerCd
                    , companyCd, zennenDataShubetu, taishoJoken, hyojiTaisho
                    , fromYYYYMM, toYYYYMM);
        }
        //３.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii, parameterDto);
        //４．List[[検索結果]]をリターンします。
        return listSuii;

    }
    /**
     * 当月予算取得処理
     *
     * @param parameterDto
     * @param togetuYm
     * @return
     */
    protected BigDecimal selectTogetuYosan(SuiiRefParameterDto parameterDto, String togetuYm) {
        String userId = getBirdUserInfo().getUserID();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        String companyCd = parameterDto.getCompanyCd();
        String tenpoShubetu = parameterDto.getTenpoShubetu();
        String taishoJoken = parameterDto.getTaishoJoken();
        String hyojiTaisho = parameterDto.getHyojiTaisho();
        String blockCd = parameterDto.getBlockCd();
        boolean limitFlg = getBirdUserInfo().isLimit();
        BigDecimal decYosan = getGroupSuiiRefUISuiiGepoDao().selectTogetuYosan(
                getBirdDateInfo().getSysDate(), userId, userTypeCd
                , companyCd, tenpoShubetu
                , taishoJoken, hyojiTaisho, blockCd, togetuYm, limitFlg);
        return decYosan;

    }
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#selectListGepoFuture(jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    protected List selectListGepoFuture(
            SuiiRefParameterDto parameterDto
            , String kikanSiteiFromYm
            , String futureYMFrom, String futureYMTo
            , String zennenYMFrom, String zennenYMTo)
    {
        String sysDate = getBirdDateInfo().getSysDate();
        String userId = getBirdUserInfo().getUserID();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        String companyCd = parameterDto.getCompanyCd();
        String tenpoShubetu = parameterDto.getTenpoShubetu();
        String zennenDataShubetu = parameterDto.getZennenDataShubetu();
        String taishoJoken = parameterDto.getTaishoJoken();
        String hyojiTaisho = parameterDto.getHyojiTaisho();
        String blockCd = parameterDto.getBlockCd();
        boolean limitFlg = getBirdUserInfo().isLimit();
        //DAO【推移表データ(月次)】未来日検索を実行し、List[[未来データ]]を取得します。
        List listSuii = getGroupSuiiRefUISuiiGepoDao().selectFuture(
                sysDate, userId, userTypeCd
                , companyCd, tenpoShubetu, zennenDataShubetu
                    , taishoJoken, hyojiTaisho, blockCd
                    , kikanSiteiFromYm
                    , futureYMFrom, futureYMTo
                    , zennenYMFrom, zennenYMTo
                    , limitFlg);

        //店舗検索時、店舗所属支部をパラメータにセットする
        setParamterSyozokuSibu(listSuii,parameterDto);

        //３.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii, parameterDto);
        //４．List[[検索結果]]をリターンします。
        return listSuii;
    }
    /**
     * 日次データ検索処理
     *
     * @param parameterDto 画面データ保持クラス
     * @return
     * @throws Exception
     */
    protected List selectListNipo(SuiiRefParameterDto parameterDto)
    {
        String sysDate = getBirdDateInfo().getSysDate();
        String userId = getBirdUserInfo().getUserID();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
        String companyCd = parameterDto.getCompanyCd();
        String tenpoShubetu = parameterDto.getTenpoShubetu();
        String zennenDataShubetu = parameterDto.getZennenDataShubetu();
        String taishoJoken = parameterDto.getTaishoJoken();
        String hyojiTaisho = parameterDto.getHyojiTaisho();
        String blockCd = parameterDto.getBlockCd();
        boolean limitFlg = getBirdUserInfo().isLimit();
        String taishoYm = parameterDto.getFocusTab();
        String kikanFromAddDay = SuiiRefUtil.setKikanFromDay(taishoYm);
        String kikanToAddDay = SuiiRefUtil.setKikanToDay(taishoYm);
        boolean isLasyYmLeapYearMonth = isLasyYmLeapYearMonth(parameterDto.getFocusTab());
        boolean isDogetu = ZennenDataShubetu.CODE_DOGETU.equals(parameterDto.getZennenDataShubetu());
        List listSuii = null;

        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
            //ユーザータイプが本部か店舗(直営店/販社)の処理になります。
            //DAO【事業計画予算対象推移表日次データ】検索を実行します。
            listSuii =getGroupSuiiRefUISuiiNipoDao().select(sysDate, userId, userTypeCd, limitFlg
                        , companyCd, zennenDataShubetu, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd
                        , taishoYm, kikanFromAddDay, kikanToAddDay);
            //前年の月がうるう月で、店舗種別「全店」又は前年データ種別が「前年同月」の場合はうるう日の設定を行います。
            if( isLasyYmLeapYearMonth && isDogetu )
            {
                //うるう日情報を追加
                listSuii.addAll(getGroupSuiiRefUISuiiNipoDao().selectLeap0229(
                          sysDate, userId, userTypeCd, limitFlg
                        , companyCd, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd
                        , taishoYm));
            }
            //店舗検索時、店舗所属支部をパラメータにセットする
            setParamterSyozokuSibu(listSuii,parameterDto);
        }
        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
            //ユーザータイプがオーナーか店舗(加盟店)の処理になります。
            //DAO【オーナー予算対象推移表日次データ】検索を実行します。
            String onerCd = null;
            if(UserType.isOner(userTypeCd)) {
                onerCd = ((UIUserOner)SuiiRefUtil.getUserOnerCd(getBirdUserInfo(), companyCd)).getOnerCd();
            }
            listSuii =getGroupSuiiRefUISuiiNipoOnerDao().select(
                    sysDate, userId, userTypeCd, onerCd
                    , companyCd, taishoJoken, hyojiTaisho, zennenDataShubetu
                    , taishoYm);
            //前年の月がうるう月で、前年データ種別が「前年同月」の場合はうるう日の設定を行います。
            if( isLasyYmLeapYearMonth && isDogetu ) {
                //うるう日情報を追加
                listSuii.addAll(getGroupSuiiRefUISuiiNipoOnerDao().selectLeap0229(
                          sysDate, userId, userTypeCd, onerCd
                        , companyCd, taishoJoken, hyojiTaisho
                        , taishoYm));
            }
        }
        //３.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii, parameterDto);
        //４．List[[検索結果]]をリターンします。
        return listSuii;

    }
    /**
     * 未来日データ取得処理
     *
     * @param parameterDto
     * @return
     */
    protected List selectListNipoFuture(SuiiRefParameterDto parameterDto) {
        String taishoYm = parameterDto.getFocusTab();
        String kikanToAddDay = SuiiRefUtil.setKikanToDay(taishoYm);
        String sysDate = getBirdDateInfo().getSysDate();
        boolean isFutureExist = sysDate.compareTo(kikanToAddDay)<1;
        List listSuii = new ArrayList(0);
        if(isFutureExist) {
            String userId = getBirdUserInfo().getUserID();
            String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
            String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
            String companyCd = parameterDto.getCompanyCd();
            String tenpoShubetu = parameterDto.getTenpoShubetu();
            String zennenDataShubetu = parameterDto.getZennenDataShubetu();
            String taishoJoken = parameterDto.getTaishoJoken();
            String hyojiTaisho = parameterDto.getHyojiTaisho();
            String blockCd = parameterDto.getBlockCd();
            boolean limitFlg = getBirdUserInfo().isLimit();
            String futureFromYmd = null;
            String futureToYmd = null;
            String zennenYm = null;
            String zennenFrom = null;
            String zennenTo = null;
            String sysDateGetumatu = null;
            String togetuGessho = SuiiRefUtil.setKikanFromDay(taishoYm);
            String zennenGessho = null;
            futureFromYmd = sysDate;
            futureToYmd = kikanToAddDay;
            try {
                zennenYm = DateManager.getPrevMonth(taishoYm, 12);
                zennenFrom = zennenYm + sysDate.substring(6, 8);
                zennenTo = zennenYm + "31";
                zennenGessho = zennenYm + "01";
                sysDateGetumatu = sysDate.substring(0, 6) + DateManager.getLastDayOfMonth(sysDate);
            }
            catch (Exception ex) {
                throw new FtlSystemException("推移表日次データ検索処理内で"
                        , "期間指定年月を加算する際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
                listSuii = getGroupSuiiRefUISuiiNipoDao().selectFuture(
                      sysDate, userId, userTypeCd, limitFlg
                    , companyCd, zennenDataShubetu, tenpoShubetu, taishoJoken, hyojiTaisho, blockCd
                    , taishoYm, futureFromYmd, futureToYmd, zennenYm, zennenFrom, zennenTo, sysDateGetumatu
                    , togetuGessho, zennenGessho);
                //店舗検索時、店舗所属支部をパラメータにセットする
                setParamterSyozokuSibu(listSuii,parameterDto);
            }
            else if(SiteKbn.YAMA.equals(userSiteKbn)) {
                String onerCd = null;
                if(UserType.isOner(userTypeCd)) {
                    onerCd = ((UIUserOner)SuiiRefUtil.getUserOnerCd(getBirdUserInfo(), companyCd)).getOnerCd();
                }
                listSuii = getGroupSuiiRefUISuiiNipoOnerDao().selectFuture(
                        sysDate, userId, userTypeCd, onerCd
                        , companyCd, taishoJoken, hyojiTaisho, zennenDataShubetu
                        , taishoYm, futureFromYmd, futureToYmd, zennenYm, zennenFrom, zennenTo, sysDateGetumatu
                        , togetuGessho, zennenGessho);
            }
        }
        //３.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii, parameterDto);
        //４．List[[検索結果]]をリターンします。
        return listSuii;
    }
    /**
     * 累計フィールド値算出処理
     *
     * @param cssClassName
     * @param taishoTitle
     * @param listSuiiGepo
     * @param endRowIndex
     * @param listSuiiNipoTogetu
     * @return
     */
    protected SuiiRefUIEntity createEntityGepoRuikei(
            String cssClassName, String taishoTitle
            , List listSuiiGepo, int endRowIndex, List listSuiiNipoTogetu
            , int scale)
    {
        SuiiRefUIEntity sumentity = new UISuiiGepo();
        //対象期間開始年月からシステム日付前月のデータまでをサマリ。
        createEntitySum(sumentity, cssClassName, taishoTitle, listSuiiGepo, 0, endRowIndex, scale);
        if(listSuiiNipoTogetu != null && listSuiiNipoTogetu.size() > 0) {
            //システム日付年月の売上発生データの値を加算。
            createEntitySum(sumentity, cssClassName, taishoTitle, listSuiiNipoTogetu, 0, listSuiiNipoTogetu.size(), scale);
        }

        return sumentity;
    }
    /**
     * 12ヶ月平均生成処理
     *
     * @param entiry12Sum 12ヶ月合計エンティティ
     * @param avgCnt
     * @return
     * @throws Exception
     */
    protected SuiiRefUIEntity createEntity12Avg(SuiiRefUIEntity entiry12Sum, int avgCnt, int scale)
    {
        SuiiRefUIEntity entiry12Avg = new UISuiiNipo();
        //12ヶ月売上データ平均値算出設定
        SuiiRefUtil.createEntity12Avg(entiry12Avg, entiry12Sum, avgCnt, scale);
        return entiry12Avg;
    }
    /**
     * 集計行生成取得処理
     *
     * @param sumentity
     * @param cssClassName
     * @param taishoTitle
     * @param lists
     * @param startRowIndex
     * @param endRowIndex
     * @return
     */
    protected void createEntitySum(
            SuiiRefUIEntity sumentity, String cssClassName
            , String taishoTitle, List lists, int startRowIndex, int endRowIndex, int scale)
    {
        //売上データ集計行生成取得処理
        SuiiRefUtil.createSumEntity(sumentity, cssClassName, taishoTitle, lists, startRowIndex, endRowIndex, scale);
    }
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityGepoSum(java.lang.String, java.lang.String, java.util.List, int, int)
     */
    protected SuiiRefUIEntity createEntityGepoSum(String cssClassName, String taishoTitle
            , List lists, int startRowIndex, int endRowIndex, int scale)
    {
        SuiiRefUIEntity sumentity = new UISuiiGepo();
        createEntitySum(sumentity, cssClassName, taishoTitle, lists, startRowIndex, endRowIndex, scale);
        return sumentity;
    }
    /**
     * @return クラス変数groupSuiiRefUISuiiGepoDao を戻します。
     */
    public UISuiiGepoDao getGroupSuiiRefUISuiiGepoDao() {
        return groupSuiiRefUISuiiGepoDao;
    }
    /**
     * @param groupSuiiRefUISuiiGepoDao を クラス変数groupSuiiRefUISuiiGepoDaoへ設定します。
     */
    public void setGroupSuiiRefUISuiiGepoDao(UISuiiGepoDao groupSuiiRefUISuiiGepoDao) {
        this.groupSuiiRefUISuiiGepoDao = groupSuiiRefUISuiiGepoDao;
    }
    /**
     * @return クラス変数groupSuiiRefUISuiiGepoOnerDao を戻します。
     */
    public UISuiiGepoOnerDao getGroupSuiiRefUISuiiGepoOnerDao() {
        return groupSuiiRefUISuiiGepoOnerDao;
    }
    /**
     * @param groupSuiiRefUISuiiGepoOnerDao を クラス変数groupSuiiRefUISuiiGepoOnerDaoへ設定します。
     */
    public void setGroupSuiiRefUISuiiGepoOnerDao(
            UISuiiGepoOnerDao groupSuiiRefUISuiiGepoOnerDao) {
        this.groupSuiiRefUISuiiGepoOnerDao = groupSuiiRefUISuiiGepoOnerDao;
    }
    /**
     * @return クラス変数groupSuiiRefUISuiiNipoDao を戻します。
     */
    public UISuiiNipoDao getGroupSuiiRefUISuiiNipoDao() {
        return groupSuiiRefUISuiiNipoDao;
    }
    /**
     * @param groupSuiiRefUISuiiNipoDao を クラス変数groupSuiiRefUISuiiNipoDaoへ設定します。
     */
    public void setGroupSuiiRefUISuiiNipoDao(UISuiiNipoDao groupSuiiRefUISuiiNipoDao) {
        this.groupSuiiRefUISuiiNipoDao = groupSuiiRefUISuiiNipoDao;
    }
    /**
     * @return クラス変数groupSuiiRefUISuiiNipoOnerDao を戻します。
     */
    public UISuiiNipoOnerDao getGroupSuiiRefUISuiiNipoOnerDao() {
        return groupSuiiRefUISuiiNipoOnerDao;
    }
    /**
     * @param groupSuiiRefUISuiiNipoOnerDao を クラス変数groupSuiiRefUISuiiNipoOnerDaoへ設定します。
     */
    public void setGroupSuiiRefUISuiiNipoOnerDao(
            UISuiiNipoOnerDao groupSuiiRefUISuiiNipoOnerDao) {
        this.groupSuiiRefUISuiiNipoOnerDao = groupSuiiRefUISuiiNipoOnerDao;
    }
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityNipoSum(java.lang.String, java.lang.String, java.util.List, int, int)
     */
    protected SuiiRefUIEntity createEntityNipoSum(
            String cssClassName, String taishoTitle, List lists, int startRowIndex, int endRowIndex, int scale) {
        SuiiRefUIEntity sumentity = new UISuiiNipo();
        createEntitySum(sumentity, cssClassName, taishoTitle, lists, startRowIndex, endRowIndex, scale);
        return sumentity;
    }
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#selectListMiseCnt(boolean)
     */
    protected List selectListMiseCnt(boolean isCsv, SuiiRefParameterDto parameterDto) {
        return getGroupSuiiRefUIMiseCntDao().select(
                getBirdDateInfo().getAppDate().substring(0,6)
                , getBirdUserInfo().getEmosslesAppId()
                , getBirdUserInfo().getMstUser().getUserTypeCd()
                , getBirdUserInfo().isLimit(), getBirdUserInfo().getUserID()
                , parameterDto, isCsv);
    }
    /**
     * @return クラス変数groupSuiiRefUIMiseCntDao を戻します。
     */
    public UIMiseCntDao getGroupSuiiRefUIMiseCntDao() {
        return groupSuiiRefUIMiseCntDao;
    }
    /**
     * @param groupSuiiRefUIMiseCntDao を クラス変数groupSuiiRefUIMiseCntDaoへ設定します。
     */
    public void setGroupSuiiRefUIMiseCntDao(UIMiseCntDao groupSuiiRefUIMiseCntDao) {
        this.groupSuiiRefUIMiseCntDao = groupSuiiRefUIMiseCntDao;
    }
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#newGepoEntity()
     */
    protected SuiiRefUIEntity newGepoEntity() {
        // TODO 自動生成されたメソッド・スタブ
        return new UISuiiGepo();
    }
	/**
	 * List用単価比率値算出処理
	 * 各単価・比率値算出し設定します。
	 * @param listSuii List[[検索結果]]
	 */
	private void calculaterTankaHiritu(List listSuii, SuiiRefParameterDto parameterDto) {
        int scale = 0;//初期値0桁
    	String pattern = parameterDto.getCodCompany().getDispFormat();
    	if(!CommonUtil.isNull(pattern)) {
    		if(pattern.indexOf(".")>=0) {
    			scale = pattern.length() - pattern.indexOf(".") - 1;
        	}
    	}
		//１.List[[検索結果]]の件数分下記の処理を行います。
        for(int i=0; i<listSuii.size(); i++) {
        	//for-1.List[[検索結果]]から現行インデックスUIMoscard[検索結果]を取得します。
        	SuiiRefUIEntity entity = (SuiiRefUIEntity)listSuii.get(i);
        	//for-2.UIMoscard[検索結果].営業日ラベルへUIMoscard[検索結果].営業日を設定します。
        	entity.setEigyoDtLabel(entity.getEigyoDt());
        	//for-3.単価比率値算出処理を実行し、UIMoscard[検索結果]の各単価・比率値算出し設定します。
        	SuiiRefUtil.calcTanka(entity, scale);
        	SuiiRefUtil.calcHiritu(entity);
        }

	}

    /**
     * 個店検索時、店舗所属支部コードをパラメータDTOにセットする add xou.zoubun 2019/11/19
     * @param listSuii List[[検索結果]]
     * @param parameterDto パラメータ
     */
    private void setParamterSyozokuSibu(List listSuii, SuiiRefParameterDto parameterDto) {
        if (listSuii != null && listSuii.size() > 0 ) {
            SuiiRefUIEntity entity = (SuiiRefUIEntity)listSuii.get(0);
            parameterDto.setSyozokuSibu(entity.getSyozokuSibu());
//delete xou.zoubun 余計なロジックのため、削除する
//        } else {
//            parameterDto.setSyozokuSibu("");
        }
    }
}
