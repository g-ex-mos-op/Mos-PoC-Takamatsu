/**
 *
 */
package jp.co.isid.mos.bird.bizreport.netordersuiiref.logic.impl;

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
import jp.co.isid.mos.bird.bizreport.netordersuiiref.dao.UIMiseCntDao;
import jp.co.isid.mos.bird.bizreport.netordersuiiref.dao.UINetorderDao;
import jp.co.isid.mos.bird.bizreport.netordersuiiref.entity.UINetorder;
import jp.co.isid.mos.bird.bizreport.netordersuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * LOGIC【検索結果取得】
 *
 */
public class SearchLogicImpl extends SuiiRefSearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR018L01";
	/** DAO【月別店舗数情報】 */
	private UIMiseCntDao netorderSuiiRefUIMiseCntDao;
	/** DAO【ネット注文売上推移情報】*/
	private UINetorderDao netorderSuiiRefUINetorderDao;
	/*
	 * 結果取得処理
	 *
	 * @param 推移表共通DTO【検索条件】
	 * @param 推移表共通DTO【結果情報】
	 * @see jp.co.isid.mos.bird.bizreport.netordersuiiref.logic.SearchLogic#execute(jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto)
	 */
	public SuiiRefResultDto execute(
			boolean isCsv, SuiiRefParameterDto parameterDto, SuiiRefResultDto suiiRefResultDto)
	{
		//１.super.結果取得処理を実行し、戻り値をDTO【結果情報】とします。
		SuiiRefResultDto resultDto = super.execute(isCsv, parameterDto, suiiRefResultDto);
		//２．推移表共通DTO【結果情報】をリターンします。
		return resultDto;
	}
	/*
	 * 推移表共通DTO【結果情報】生成取得処理
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#newSuiiRefResultDto()
	 */
	protected SuiiRefResultDto newSuiiRefResultDto() {
		return new SuiiRefResultDto();
	}
    /* 12ヶ月平均エンティティ作成処理
     *
     * UINetorder[12ヶ月合計]からUINetorder[12ヶ月平均]を作成します。
     *
     * @param SuiiRefUIEntity UINetorder[12ヶ月合計]
     * @param int 平均件数
     * @param int 売上値小数桁数(海外対応用)
     * @return SuiiRefUIEntity UINetorder[12ヶ月平均]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntity12Avg(jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity, jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity, int)
	 */
	protected SuiiRefUIEntity createEntity12Avg(SuiiRefUIEntity entiry12Sum, int avgCnt, int scale) {
        //１.UINetorder[12ヶ月平均]を生成します。
		UINetorder entiry12Avg = new UINetorder();
		//２.推移表共通定数クラス.12ヶ月売上データ平均値算出設定を実行します。
        SuiiRefUtil.createEntity12Avg(entiry12Avg, entiry12Sum, avgCnt, scale);

        UINetorder e12Total = (UINetorder)entiry12Sum;
        BigDecimal decAvcCnt = new BigDecimal(avgCnt);
        //３．UINetorder[12ヶ月平均]へ下記の処理から平均値の設定を行います。
        //当年データ
        //ネット注文
        entiry12Avg.setEigyoDaysNsum(Calculator.divide(e12Total.getEigyoDaysNsum(), decAvcCnt));
        entiry12Avg.setMiseCntKbnNsum(Calculator.divide(e12Total.getMiseCntKbnNsum(), decAvcCnt));
        entiry12Avg.setUriageNsum(Calculator.divide(e12Total.getUriageNsum(), decAvcCnt));
        entiry12Avg.setKyakusuNsum(Calculator.divide(e12Total.getKyakusuNsum(), decAvcCnt));
        //ネットテイク
        entiry12Avg.setEigyoDaysNtake(Calculator.divide(e12Total.getEigyoDaysNtake(), decAvcCnt));
        entiry12Avg.setMiseCntKbnNtake(Calculator.divide(e12Total.getMiseCntKbnNtake(), decAvcCnt));
        entiry12Avg.setUriageNtake(Calculator.divide(e12Total.getUriageNtake(), decAvcCnt));
        entiry12Avg.setKyakusuNtake(Calculator.divide(e12Total.getKyakusuNtake(), decAvcCnt));
        //ネット宅配
        entiry12Avg.setEigyoDaysNtaku(Calculator.divide(e12Total.getEigyoDaysNtaku(), decAvcCnt));
        entiry12Avg.setMiseCntKbnNtaku(Calculator.divide(e12Total.getMiseCntKbnNtaku(), decAvcCnt));
        entiry12Avg.setUriageNtaku(Calculator.divide(e12Total.getUriageNtaku(), decAvcCnt));
        entiry12Avg.setKyakusuNtaku(Calculator.divide(e12Total.getKyakusuNtaku(), decAvcCnt));
        //前年データ
        //ネット注文
        entiry12Avg.setUriageNsumZen(Calculator.divide(e12Total.getUriageNsumZen(), decAvcCnt));
        entiry12Avg.setKyakusuNsumZen(Calculator.divide(e12Total.getKyakusuNsumZen(), decAvcCnt));
        //ネットテイク
        entiry12Avg.setUriageNtakeZen(Calculator.divide(e12Total.getUriageNtakeZen(), decAvcCnt));
        entiry12Avg.setKyakusuNtakeZen(Calculator.divide(e12Total.getKyakusuNtakeZen(), decAvcCnt));
        //ネット宅配
        entiry12Avg.setUriageNtakuZen(Calculator.divide(e12Total.getUriageNtakuZen(), decAvcCnt));
        entiry12Avg.setKyakusuNtakuZen(Calculator.divide(e12Total.getKyakusuNtakuZen(), decAvcCnt));

        //NET当年データ
        //ネット注文
        entiry12Avg.setNetUriageNsum(Calculator.divide(e12Total.getNetUriageNsum(), decAvcCnt));
        entiry12Avg.setNetKyakusuNsum(Calculator.divide(e12Total.getNetKyakusuNsum(), decAvcCnt));
        //ネットテイク
        entiry12Avg.setNetUriageNtake(Calculator.divide(e12Total.getNetUriageNtake(), decAvcCnt));
        entiry12Avg.setNetKyakusuNtake(Calculator.divide(e12Total.getNetKyakusuNtake(), decAvcCnt));
        //ネット宅配
        entiry12Avg.setNetUriageNtaku(Calculator.divide(e12Total.getNetUriageNtaku(), decAvcCnt));
        entiry12Avg.setNetKyakusuNtaku(Calculator.divide(e12Total.getNetKyakusuNtaku(), decAvcCnt));
        //NET前年データ
        //ネット注文
        entiry12Avg.setNetUriageNsumZen(Calculator.divide(e12Total.getNetUriageNsumZen(), decAvcCnt));
        entiry12Avg.setNetKyakusuNsumZen(Calculator.divide(e12Total.getNetKyakusuNsumZen(), decAvcCnt));
        //ネットテイク
        entiry12Avg.setNetUriageNtakeZen(Calculator.divide(e12Total.getNetUriageNtakeZen(), decAvcCnt));
        entiry12Avg.setNetKyakusuNtakeZen(Calculator.divide(e12Total.getNetKyakusuNtakeZen(), decAvcCnt));
        //ネット宅配
        entiry12Avg.setNetUriageNtakuZen(Calculator.divide(e12Total.getNetUriageNtakuZen(), decAvcCnt));
        entiry12Avg.setNetKyakusuNtakuZen(Calculator.divide(e12Total.getNetKyakusuNtakuZen(), decAvcCnt));

        //４.単価比率値算出処理を実行し、算出した平均値から単価・比率を算出しその値をUINetorder[12ヶ月平均]へ設定します。
        calculaterTankaHiritu(entiry12Avg, scale);
        //５．12ヶ月平均のネット注文売上平均は、各月のネット注文売上平均を合計して月数で割った値になります。（ほかの売上や件数などと同様の計算です）
        entiry12Avg.setNsumUriageAvg(Calculator.divide(e12Total.getNsumUriageAvg(), decAvcCnt));
        entiry12Avg.setNtakeUriageAvg(Calculator.divide(e12Total.getNtakeUriageAvg(), decAvcCnt));
        entiry12Avg.setNtakuUriageAvg(Calculator.divide(e12Total.getNtakuUriageAvg(), decAvcCnt));
        entiry12Avg.setNsumKyakusuAvg(Calculator.divide(e12Total.getNsumKyakusuAvg(), decAvcCnt));
        entiry12Avg.setNtakeKyakusuAvg(Calculator.divide(e12Total.getNtakeKyakusuAvg(), decAvcCnt));
        entiry12Avg.setNtakuKyakusuAvg(Calculator.divide(e12Total.getNtakuKyakusuAvg(), decAvcCnt));
        //６.UINetorder[12ヶ月平均]をリターンします。
        return entiry12Avg;
	}
	/*
     * 累計エンティティ作成処理
     * 対象期間開始年月からシステム日付前月のデータまでをサマリ。
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param listSuiiNipoTogetu List[[日次当月推移情報]]
     * @param scale         売上値小数桁数(海外対応用)
     * @return　UINetorder[累計]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityGepoRuikei(java.lang.String, java.lang.String, java.util.List, int, java.util.List)
	 */
	protected SuiiRefUIEntity createEntityGepoRuikei(String cssClassName, String taishoTitle
			, List listResult, int endRowIndex, List listSuiiNipoTogetu
			, int scale)
	{
		//１.UINetorder[累計]を生成します。
		UINetorder sumentity = new UINetorder();
        //２.合計エンティティ値設定処理を実行します。
		summaryEntitySum(sumentity, cssClassName, taishoTitle, listResult, 0, endRowIndex, scale);
		//３.パラメータ.List[[日次当月推移情報]]の件数>0の場合、
        if(listSuiiNipoTogetu != null && listSuiiNipoTogetu.size() > 0) {
            //合計エンティティ値設定処理を実行します。
        	//(UINetorder[累計]へシステム日付年月の売上発生データの値を加算)
        	summaryEntitySum(sumentity, cssClassName, taishoTitle, listSuiiNipoTogetu, 0, listSuiiNipoTogetu.size(), scale);
        }
        //４.UINetorder[累計]をリターンします。
        return sumentity;
	}
	/* 月次合計エンティティ作成処理
	 *
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param scale         売上値小数桁数(海外対応用)
     * @return　SuiiRefUIEntity[月次合計]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityGepoSum(java.lang.String, java.lang.String, java.util.List, int, int)
	 */
	protected SuiiRefUIEntity createEntityGepoSum(String cssClassName, String taishoTitle
			, List listResult, int startRowIndex, int endRowIndex, int scale) {
		//１.日次合計エンティティ作成処理を実行し、戻り値UINetorder[月次合計]をリターンします。
		return createEntityNipoSum(cssClassName, taishoTitle, listResult, startRowIndex, endRowIndex, scale);
	}
	/* 日次合計エンティティ作成処理
	 *
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param scale         売上値小数桁数(海外対応用)
     * @return　UINetorder[日次合計]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityGepoSum(java.lang.String, java.lang.String, java.util.List, int, int)
	 */
	protected SuiiRefUIEntity createEntityNipoSum(String cssClassName, String taishoTitle
			, List listResult, int startRowIndex, int endRowIndex, int scale)
	{
		//１.UINetorder[日次合計]を生成します。
		UINetorder sumentity = new UINetorder();
		//２.合計エンティティ値設定処理を実行します。
		summaryEntitySum(sumentity, cssClassName, taishoTitle, listResult, startRowIndex, endRowIndex, scale);
		//３.UINetorder[日次合計]をリターンします。
		return sumentity;
	}
	/**
	 * 合計エンティティ値設定処理
	 *
	 * @param sumentity　　UINetorder[合計]
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param scale         売上値小数桁数(海外対応用)
	 */
	private void summaryEntitySum(UINetorder sumentity, String cssClassName, String taishoTitle
			, List listResult, int startRowIndex, int endRowIndex
			, int scale)
	{
		//１.推移表共通定数クラス.売上データ集計行生成取得処理を実行し、売上推移表値をパラメータ.UINetorder[合計]へ設定します。
        SuiiRefUtil.createSumEntity(sumentity, cssClassName, taishoTitle, listResult, startRowIndex, endRowIndex, scale);
        //２.
        //当年データ
        //ネット注文
        BigDecimal eigyoDaysNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getEigyoDaysNsum(), 0);
        BigDecimal miseCntKbnNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getMiseCntKbnNsum(), 0);
        BigDecimal uriageNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNsum(), 0);
        BigDecimal kyakusuNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNsum(), 0);
        BigDecimal avgUriageNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getNsumUriageAvg(), 0);
        BigDecimal avgKyakusuNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getNsumKyakusuAvg(), 0);
        //ネットテイク
        BigDecimal eigyoDaysNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getEigyoDaysNtake(), 0);
        BigDecimal miseCntKbnNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getMiseCntKbnNtake(), 0);
        BigDecimal uriageNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNtake(), 0);
        BigDecimal kyakusuNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNtake(), 0);
        BigDecimal avgUriageNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getNtakeUriageAvg(), 0);
        BigDecimal avgKyakusuNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getNtakeKyakusuAvg(), 0);
        //ネット宅配
        BigDecimal eigyoDaysNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getEigyoDaysNtaku(), 0);
        BigDecimal miseCntKbnNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getMiseCntKbnNtaku(), 0);
        BigDecimal uriageNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNtaku(), 0);
        BigDecimal kyakusuNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNtaku(), 0);
        BigDecimal avgUriageNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getNtakuUriageAvg(), 0);
        BigDecimal avgKyakusuNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getNtakuKyakusuAvg(), 0);

        //前年データ
        //ネット注文
        BigDecimal uriageNsumZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNsumZen(), 0);
        BigDecimal kyakusuNsumZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNsumZen(), 0);
        //ネットテイク
        BigDecimal uriageNtakeZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNtakeZen(), 0);
        BigDecimal kyakusuNtakeZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNtakeZen(), 0);
        //ネット宅配
        BigDecimal uriageNtakuZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNtakuZen(), 0);
        BigDecimal kyakusuNtakuZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNtakuZen(), 0);

        //NET当年データ
        //ネット注文
        BigDecimal netUriageNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetUriageNsum(), 0);
        BigDecimal netKyakusuNsum = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetKyakusuNsum(), 0);
        //ネットテイク
        BigDecimal netUriageNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetUriageNtake(), 0);
        BigDecimal netKyakusuNtake = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetKyakusuNtake(), 0);
        //ネット宅配
        BigDecimal netUriageNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetUriageNtaku(), 0);
        BigDecimal netKyakusuNtaku = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetKyakusuNtaku(), 0);
        //NET前年データ
        //ネット注文
        BigDecimal netUriageNsumZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNsumZen(), 0);
        BigDecimal netKyakusuNsumZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNsumZen(), 0);
        //ネットテイク
        BigDecimal netUriageNtakeZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getUriageNtakeZen(), 0);
        BigDecimal netKyakusuNtakeZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getKyakusuNtakeZen(), 0);
        //ネット宅配
        BigDecimal netUriageNtakuZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetUriageNtakuZen(), 0);
        BigDecimal netKyakusuNtakuZen = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetKyakusuNtakuZen(), 0);
        //日次店舗合計
        BigDecimal miseCntNsumTotal   = SuiiRefUtil.nullCheckAndFormated(sumentity.getMiseCntKbnNsum(), 0);
        BigDecimal miseCntNtakeTotal   = SuiiRefUtil.nullCheckAndFormated(sumentity.getMiseCntKbnNtake(), 0);
        BigDecimal miseCntNtakuTotal   = SuiiRefUtil.nullCheckAndFormated(sumentity.getMiseCntKbnNtaku(), 0);

        for(int i=startRowIndex; i<endRowIndex; i++) {
        	UINetorder entity = (UINetorder)listResult.get(i);
            if(entity == null)
            	continue;
            //当年データ
            //ネット注文
            eigyoDaysNsum      = eigyoDaysNsum.add(entity.getEigyoDaysNsum());
            miseCntKbnNsum     = miseCntKbnNsum.add(entity.getMiseCntKbnNsum());
            uriageNsum         = uriageNsum.add(entity.getUriageNsum());
            kyakusuNsum        = kyakusuNsum.add(entity.getKyakusuNsum());
            avgUriageNsum      = avgUriageNsum.add(entity.getNsumUriageAvg());
            avgKyakusuNsum     = avgKyakusuNsum.add(entity.getNsumKyakusuAvg());
            //ネットテイク
            eigyoDaysNtake     = eigyoDaysNtake.add(entity.getEigyoDaysNtake());
            miseCntKbnNtake    = miseCntKbnNtake.add(entity.getMiseCntKbnNtake());
            uriageNtake        = uriageNtake.add(entity.getUriageNtake());
            kyakusuNtake       = kyakusuNtake.add(entity.getKyakusuNtake());
            avgUriageNtake     = avgUriageNtake.add(entity.getNtakeUriageAvg());
            avgKyakusuNtake    = avgKyakusuNtake.add(entity.getNtakeKyakusuAvg());
            //ネット宅配
            eigyoDaysNtaku     = eigyoDaysNtaku.add(entity.getEigyoDaysNtaku());
            miseCntKbnNtaku    = miseCntKbnNtaku.add(entity.getMiseCntKbnNtaku());
            uriageNtaku        = uriageNtaku.add(entity.getUriageNtaku());
            kyakusuNtaku       = kyakusuNtaku.add(entity.getKyakusuNtaku());
            avgUriageNtaku     = avgUriageNtaku.add(entity.getNtakuUriageAvg());
            avgKyakusuNtaku    = avgKyakusuNtaku.add(entity.getNtakuKyakusuAvg());
            //前年データ
            //ネット注文
            uriageNsumZen      = uriageNsumZen.add(entity.getUriageNsumZen());
            kyakusuNsumZen     = kyakusuNsumZen.add(entity.getKyakusuNsumZen());
            //ネットテイク
            uriageNtakeZen     = uriageNtakeZen.add(entity.getUriageNtakeZen());
            kyakusuNtakeZen    = kyakusuNtakeZen.add(entity.getKyakusuNtakeZen());
            //ネット宅配
            uriageNtakuZen     = uriageNtakuZen.add(entity.getUriageNtakuZen());
            kyakusuNtakuZen    = kyakusuNtakuZen.add(entity.getKyakusuNtakuZen());

            //NET当年データ
            //ネット注文
            netUriageNsum      = netUriageNsum.add(entity.getNetUriageNsum());
            netKyakusuNsum     = netKyakusuNsum.add(entity.getNetKyakusuNsum());
            //ネットテイク
            netUriageNtake     = netUriageNtake.add(entity.getNetUriageNtake());
            netKyakusuNtake    = netKyakusuNtake.add(entity.getNetKyakusuNtake());
            //ネット宅配
            netUriageNtaku     = netUriageNtaku.add(entity.getNetUriageNtaku());
            netKyakusuNtaku    = netKyakusuNtaku.add(entity.getNetKyakusuNtaku());
            //NET前年データ
            //ネット注文
            netUriageNsumZen   = netUriageNsumZen.add(entity.getNetUriageNsumZen());
            netKyakusuNsumZen  = netKyakusuNsumZen.add(entity.getNetKyakusuNsumZen());
            //ネットテイク
            netUriageNtakeZen  = netUriageNtakeZen.add(entity.getNetUriageNtakeZen());
            netKyakusuNtakeZen = netKyakusuNtakeZen.add(entity.getNetKyakusuNtakeZen());
            //ネット宅配
            netUriageNtakuZen  = netUriageNtakuZen.add(entity.getNetUriageNtakuZen());
            netKyakusuNtakuZen = netKyakusuNtakuZen.add(entity.getNetKyakusuNtakuZen());

            miseCntNsumTotal = entity.getMiseCntNsumTotal();
            miseCntNtakeTotal = entity.getMiseCntNtakeTotal();
            miseCntNtakuTotal = entity.getMiseCntNtakuTotal();
        }

        //当年データ
        //ネット注文
        sumentity.setEigyoDaysNsum(eigyoDaysNsum);
        sumentity.setMiseCntKbnNsum(miseCntKbnNsum);
        sumentity.setUriageNsum(uriageNsum);
        sumentity.setKyakusuNsum(kyakusuNsum);
        //ネットテイク
        sumentity.setEigyoDaysNtake(eigyoDaysNtake);
        sumentity.setMiseCntKbnNtake(miseCntKbnNtake);
        sumentity.setUriageNtake(uriageNtake);
        sumentity.setKyakusuNtake(kyakusuNtake);
        //ネット宅配
        sumentity.setEigyoDaysNtaku(eigyoDaysNtaku);
        sumentity.setMiseCntKbnNtaku(miseCntKbnNtaku);
        sumentity.setUriageNtaku(uriageNtaku);
        sumentity.setKyakusuNtaku(kyakusuNtaku);
        //前年データ
        //ネット注文
        sumentity.setUriageNsumZen(uriageNsumZen);
        sumentity.setKyakusuNsumZen(kyakusuNsumZen);
        //ネットテイク
        sumentity.setUriageNtakeZen(uriageNtakeZen);
        sumentity.setKyakusuNtakeZen(kyakusuNtakeZen);
        //ネット宅配
        sumentity.setUriageNtakuZen(uriageNtakuZen);
        sumentity.setKyakusuNtakuZen(kyakusuNtakuZen);

        //NET当年データ
        //ネット注文
        sumentity.setNetUriageNsum(netUriageNsum);
        sumentity.setNetKyakusuNsum(netKyakusuNsum);
        //ネットテイク
        sumentity.setNetUriageNtake(netUriageNtake);
        sumentity.setNetKyakusuNtake(netKyakusuNtake);
        //ネット宅配
        sumentity.setNetUriageNtaku(netUriageNtaku);
        sumentity.setNetKyakusuNtaku(netKyakusuNtaku);
        //NET前年データ
        //ネット注文
        sumentity.setNetUriageNsumZen(netUriageNsumZen);
        sumentity.setNetKyakusuNsumZen(netKyakusuNsumZen);
        //ネットテイク
        sumentity.setNetUriageNtakeZen(netUriageNtakeZen);
        sumentity.setNetKyakusuNtakeZen(netKyakusuNtakeZen);
        //ネット宅配
        sumentity.setNetUriageNtakuZen(netUriageNtakuZen);
        sumentity.setNetKyakusuNtakuZen(netKyakusuNtakuZen);

        if (SuiiRefUtil.EIGYO_DT_LABEL_GETU.equals(sumentity.getEigyoDtLabel())){
        	sumentity.setMiseCntKbnNsum(miseCntNsumTotal);
      	  	sumentity.setMiseCntKbnNtake(miseCntNtakeTotal);
      	  	sumentity.setMiseCntKbnNtaku(miseCntNtakuTotal);
        }

        calculaterTankaHiritu(sumentity, scale);
        //合計のネット注文売上(件数)平均は、各月のネット注文売上（件数）平均を合計した値となります
        if (!SuiiRefUtil.EIGYO_DT_LABEL_GETU.equals(sumentity.getEigyoDtLabel())){
        	sumentity.setNsumUriageAvg(avgUriageNsum);
            sumentity.setNtakeUriageAvg(avgUriageNtake);
            sumentity.setNtakuUriageAvg(avgUriageNtaku);
            sumentity.setNsumKyakusuAvg(avgKyakusuNsum);
            sumentity.setNtakeKyakusuAvg(avgKyakusuNtake);
            sumentity.setNtakuKyakusuAvg(avgKyakusuNtaku);
        }
	}
	/**
	 * 単価比率値算出処理
	 * @param entity  UINetorder[検索結果]
	 * @param scale   売上値小数桁数(海外対応用)
	 */
	private void calculaterTankaHiritu(UINetorder entity, int scale) {
		//１.推移表共通定数クラス.単価比率値算出処理を実行し、パラメータ.UINetorder[検索結果]へ売上推移表値の単価・比率算出を行います。
        //単価計算処理
		SuiiRefUtil.calcTanka(entity, scale);
        //比率計算処理
		SuiiRefUtil.calcHiritu(entity);

		//ネット注文
		//前年比(ネット注文売上)
		entity.setNsumUriageZenhi(Calculator.percentage(entity.getUriageNsum(), entity.getUriageNsumZen(),2));
		entity.setNetNsumUriageZenhi(Calculator.percentage(entity.getNetUriageNsum(), entity.getNetUriageNsumZen(),2));
		//構成比(売上)
		entity.setNsumUriageKouseihi(Calculator.percentage(entity.getUriageNsum(), entity.getUriage(),2));
		//ネット注文売上平均
		entity.setNsumUriageAvg(Calculator.divide(entity.getUriageNsum(), entity.getMiseCntKbnNsum(),0));
		//前年比(ネット注文件数)
		entity.setNsumKyakusuZenhi(Calculator.percentage(entity.getKyakusuNsum(), entity.getKyakusuNsumZen(),2));
		entity.setNetNsumKyakusuZenhi(Calculator.percentage(entity.getNetKyakusuNsum(), entity.getNetKyakusuNsumZen(),2));
		//構成比(件数)
		entity.setNsumKyakusuKouseihi(Calculator.percentage(entity.getKyakusuNsum(), entity.getKyakusu(),2));
		//ネット注文件数平均
		entity.setNsumKyakusuAvg(Calculator.divide(entity.getKyakusuNsum(), entity.getMiseCntKbnNsum(),0));
		//客単価(ネット注文)
		entity.setNsumKyakuTanka(Calculator.divide(entity.getUriageNsum(), entity.getKyakusuNsum(),scale));
		//前年客単価(ネット注文)
		entity.setNsumKyakuTankaZen(Calculator.divide(entity.getUriageNsumZen(), entity.getKyakusuNsumZen(),scale));
		//前年比(客単価)
		entity.setNsumKyakuTankaZenhi(Calculator.percentage(entity.getNsumKyakuTanka(), entity.getNsumKyakuTankaZen(),2));
		//前年比対象客単価(ネット売上)
		entity.setNetNsumKyakuTanka(Calculator.divide(entity.getNetUriageNsum(), entity.getNetKyakusuNsum(),scale));
		//前年比対象前年客単価(ネット売上)
		entity.setNetNsumKyakuTankaZen(Calculator.divide(entity.getNetUriageNsumZen(), entity.getNetKyakusuNsumZen(),scale));
		//前年比(客単価)
		entity.setNetNsumKyakuTankaZenhi(Calculator.percentage(entity.getNetNsumKyakuTanka(), entity.getNetNsumKyakuTankaZen(),2));

		//ネットテイク
		//前年比(ネットテイク)
		entity.setNtakeUriageZenhi(Calculator.percentage(entity.getUriageNtake(), entity.getUriageNtakeZen(),2));
		entity.setNetNtakeUriageZenhi(Calculator.percentage(entity.getNetUriageNtake(), entity.getNetUriageNtakeZen(),2));
		//構成比(売上)
		entity.setNtakeUriageKouseihi(Calculator.percentage(entity.getUriageNtake(), entity.getUriage(),2));
		//ネットテイク売上平均
		entity.setNtakeUriageAvg(Calculator.divide(entity.getUriageNtake(), entity.getMiseCntKbnNtake(),0));
		//前年比(ネットテイク件数)
		entity.setNtakeKyakusuZenhi(Calculator.percentage(entity.getKyakusuNtake(), entity.getKyakusuNtakeZen(),2));
		entity.setNetNtakeKyakusuZenhi(Calculator.percentage(entity.getNetKyakusuNtake(), entity.getNetKyakusuNtakeZen(),2));
		//構成比(件数)
		entity.setNtakeKyakusuKouseihi(Calculator.percentage(entity.getKyakusuNtake(), entity.getKyakusu(),2));
		//ネットテイク件数平均
		entity.setNtakeKyakusuAvg(Calculator.divide(entity.getKyakusuNtake(), entity.getMiseCntKbnNtake(),0));
		//客単価(ネットテイク)
		entity.setNtakeKyakuTanka(Calculator.divide(entity.getUriageNtake(), entity.getKyakusuNtake(),scale));
		//前年客単価(ネットテイク)
		entity.setNtakeKyakuTankaZen(Calculator.divide(entity.getUriageNtakeZen(), entity.getKyakusuNtakeZen(),scale));
		//前年比(客単価)
		entity.setNtakeKyakuTankaZenhi(Calculator.percentage(entity.getNtakeKyakuTanka(), entity.getNtakeKyakuTankaZen(),2));
		//前年比対象客単価(ネットテイク)
		entity.setNetNtakeKyakuTanka(Calculator.divide(entity.getNetUriageNtake(), entity.getNetKyakusuNtake(),scale));
		//前年比対象前年客単価(ネットテイク)
		entity.setNetNtakeKyakuTankaZen(Calculator.divide(entity.getNetUriageNtakeZen(), entity.getNetKyakusuNtakeZen(),scale));
		//前年比(客単価)
		entity.setNetNtakeKyakuTankaZenhi(Calculator.percentage(entity.getNetNtakeKyakuTanka(), entity.getNetNtakeKyakuTankaZen(),2));

		//ネット宅配
		//前年比(ネット宅配)
		entity.setNtakuUriageZenhi(Calculator.percentage(entity.getUriageNtaku(), entity.getUriageNtakuZen(),2));
		entity.setNetNtakuUriageZenhi(Calculator.percentage(entity.getNetUriageNtaku(), entity.getNetUriageNtakuZen(),2));
		//構成比(売上)
		entity.setNtakuUriageKouseihi(Calculator.percentage(entity.getUriageNtaku(), entity.getUriage(),2));
		//ネット宅配売上平均
		entity.setNtakuUriageAvg(Calculator.divide(entity.getUriageNtaku(), entity.getMiseCntKbnNtaku(),0));
		//前年比(ネット宅配件数)
		entity.setNtakuKyakusuZenhi(Calculator.percentage(entity.getKyakusuNtaku(), entity.getKyakusuNtakuZen(),2));
		entity.setNetNtakuKyakusuZenhi(Calculator.percentage(entity.getNetKyakusuNtaku(), entity.getNetKyakusuNtakuZen(),2));
		//構成比(件数)
		entity.setNtakuKyakusuKouseihi(Calculator.percentage(entity.getKyakusuNtaku(), entity.getKyakusu(),2));
		//ネット宅配件数平均
		entity.setNtakuKyakusuAvg(Calculator.divide(entity.getKyakusuNtaku(), entity.getMiseCntKbnNtaku(),0));
		//客単価(ネット宅配)
		entity.setNtakuKyakuTanka(Calculator.divide(entity.getUriageNtaku(), entity.getKyakusuNtaku(),scale));
		//前年客単価(ネット宅配)
		entity.setNtakuKyakuTankaZen(Calculator.divide(entity.getUriageNtakuZen(), entity.getKyakusuNtakuZen(),scale));
		//前年比(客単価)
		entity.setNtakuKyakuTankaZenhi(Calculator.percentage(entity.getNtakuKyakuTanka(), entity.getNtakuKyakuTankaZen(),2));
		//前年比対象客単価(ネット宅配)
		entity.setNetNtakuKyakuTanka(Calculator.divide(entity.getNetUriageNtaku(), entity.getNetKyakusuNtaku(),scale));
		//前年比対象前年客単価(ネット宅配)
		entity.setNetNtakuKyakuTankaZen(Calculator.divide(entity.getNetUriageNtakuZen(), entity.getNetKyakusuNtakuZen(),scale));
		//前年比(客単価)
		entity.setNetNtakuKyakuTankaZenhi(Calculator.percentage(entity.getNetNtakuKyakuTanka(), entity.getNetNtakuKyakuTankaZen(),2));
	}

	/* 未来日データ取得処理
	 *
     * 未来日の前年実績は、一世代のみの店番継承を考慮する。
     * 一年間に２度以上店番継承が行われた場合は、前年実績は取得できない
     *
	 * @param 推移表共通DTO【検索条件】
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#selectFutureData(jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto)
	 */
	protected List selectListNipoFuture(SuiiRefParameterDto parameterDto) {
        String taishoYm = parameterDto.getFocusTab();
        String kikanToAddDay = SuiiRefUtil.setKikanToDay(taishoYm);
    	String sysDate = getBirdDateInfo().getSysDate();
        boolean isFutureExist = sysDate.compareTo(kikanToAddDay)<1;
		//１.List[[検索結果]]を生成します。
        List listSuii = new ArrayList(0);
        //２．システム日付が検索対象期間内の場合、下記の処理を行います。
        if(isFutureExist) {
            String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
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
	        //1.未来日の前年期間日付として下記の項目を算出します。
	        try {
	        	//前年年月＝推移表共通DTO【検索条件】.フォーカスタブの年月値から12ヶ月前の年月
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
	        //2.e-mosslesサイト区分別に検索処理を実行します。
	        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
	        	listSuii = getNetorderSuiiRefUINetorderDao().selectNipoFuture(
        				getBirdUserInfo(), getBirdDateInfo()
	            		, parameterDto, futureFromYmd, futureToYmd, zennenYm, zennenFrom, zennenTo
	            		, sysDate , sysDateGetumatu, togetuGessho, zennenGessho);
	        }
	        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
	        	listSuii = getNetorderSuiiRefUINetorderDao().selectNipoOnerFuture(
	        			getBirdUserInfo(), getBirdDateInfo()
	            		, parameterDto, futureFromYmd, futureToYmd, zennenYm, zennenFrom, zennenTo
	            		, sysDate , sysDateGetumatu, togetuGessho, zennenGessho);
	        }
	        //3.List用単価比率値算出処理を実行します。
	        calculaterTankaHiritu(listSuii);
        }
        //３.List[[検索結果]]リターンします。
        return listSuii;
	}
	/*
     * 未来月次検索処理
     *
     * @param parameterDto 推移表共通DTO【検索条件】
     * @param kijunYm　基準年月
     * @param futureYMFrom 未来開始年月
     * @param futureYMTo 未来終了年月
     * @param zennenYMFrom 前年開始年月
     * @param zennenYMTo 前年終了年月
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#selectListGepoFuture(jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	protected List selectListGepoFuture(SuiiRefParameterDto parameterDto
			, String kijunYm
			, String futureYMFrom, String futureYMTo
			, String zennenYMFrom, String zennenYMTo)
	{
		//１.DAO【ネット注文推移情報】未来月次検索を実行し、戻り値をList[[検索結果]]とします。
		List listSuii = getNetorderSuiiRefUINetorderDao().selectGepoFuture(getBirdUserInfo(), getBirdDateInfo()
				, parameterDto, kijunYm, futureYMFrom, futureYMTo, zennenYMFrom, zennenYMTo);
		//２.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii);
        //３.List[[検索結果]]リターンします。
		return listSuii;
	}
	/* 当月予算取得処理
	 *
     * @param parameterDto 推移表共通DTO【検索条件】
     * @param togetuYm 当月年月
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#selectTogetuYosan(jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto, java.lang.String)
	 */
	protected BigDecimal selectTogetuYosan(SuiiRefParameterDto parameterDto, String togetuYm) {
    	//１.DAO【ネット注文推移情報】.当月予算取得を実行し、BigDecimal[当月予算]を取得します。
    	BigDecimal decYosan = getNetorderSuiiRefUINetorderDao().selectGepoTogetuYosan(
    			getBirdUserInfo(), getBirdDateInfo()
        		, parameterDto
        		, togetuYm+"01", togetuYm+"31");
    	//２.BigDecimal[当月予算]をリターンします。
		return decYosan;
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
     * @param parameterDto 推移表共通DTO【検索条件】
     * @param fromYYYYMM 検索対象期間FROM
     * @param toYYYYMM 検索対象期間TO
     *
     */
    protected List selectListGepo(SuiiRefParameterDto parameterDto, String fromYYYYMM, String toYYYYMM)
    {
        String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
        //１.List[[検索結果]]を生成します。
        List listSuii = new ArrayList(0);
        //２.e-mosslesサイト区分別に検索処理を実行します。
        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
            //サイト区分”うみ”(本部)又は”おひさま”(直営店又は販社)の場合
        	//DAO【ネット注文売上推移情報】.月次検索を実行し、戻り値をList[[検索結果]]へ設定します。
            listSuii.addAll(getNetorderSuiiRefUINetorderDao().selectGepo(
            		getBirdUserInfo(), getBirdDateInfo(), parameterDto, fromYYYYMM, toYYYYMM
            		, parameterDto.isSelectHosei()?ZennenDataShubetu.CODE_DOGETU:parameterDto.getZennenDataShubetu()));
        }
        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
            //サイト区分”やま”（オーナー又は店舗(ＦＣ店)）の場合
        	//DAO【ネット注文売上推移情報】.オーナー用月次検索を実行し、戻り値をList[[検索結果]]へ設定します。
            listSuii =getNetorderSuiiRefUINetorderDao().selectGepoOner(parameterDto);
        }
        //３.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii);
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
        String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
        boolean isLasyYmLeapYearMonth = isLasyYmLeapYearMonth(parameterDto.getFocusTab());
        boolean isDogetu = ZennenDataShubetu.CODE_DOGETU.equals(parameterDto.getZennenDataShubetu());
        //１.List[[検索結果]]を生成します。
        List listSuii = new ArrayList(0);
        List listMonthCnt = new ArrayList(0);
        //２.e-mosslesサイト区分別に検索処理を実行します。
        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
        	//ユーザータイプが本部か店舗(直営店/販社)の処理になります。
            //DAO【ネット注文売上推移情報】検索を実行します。
            listSuii =getNetorderSuiiRefUINetorderDao().selectNipo(getBirdUserInfo(), getBirdDateInfo()
            		, parameterDto, parameterDto.isSelectHosei()?ZennenDataShubetu.CODE_DOGETU:parameterDto.getZennenDataShubetu());
        }
        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
        	//ユーザータイプがオーナーか店舗(加盟店)の処理になります。
        	//DAO【ネット注文売上推移情報】検索を実行します。
            listSuii =getNetorderSuiiRefUINetorderDao().selectNipoOner(
            		getBirdUserInfo(), getBirdDateInfo()
            		, parameterDto);
	    }
        //３.前年の月がうるう月で、前年データ種別が「前年同月」の場合はうるう日の設定を行います。
    	if(isLasyYmLeapYearMonth && isDogetu )
    	{
    		//うるう日情報を追加
            listSuii.addAll(getNetorderSuiiRefUINetorderDao().selectNipoLeap0229(
            		getBirdUserInfo(), getBirdDateInfo()
            		, parameterDto));
    	}
    	//
    	listMonthCnt = selectListGepo(parameterDto,parameterDto.getKikanSitei(),parameterDto.getKikanSitei());
    	if (listMonthCnt != null && listMonthCnt.size()>0)
    	{
    		calMonthMiseCnt(listSuii,listMonthCnt,parameterDto.getKikanSitei());
    	}
        //４.List用単価比率値算出処理を実行します。
        calculaterTankaHiritu(listSuii);
        //５．List[[検索結果]]をリターンします。
        return listSuii;

    }
	/*
	 * 店舗数検索結果取得処理
	 *
	 * DAO【タブ情報】検索を実行し、List[UITabData[店舗数]]を取得します。
	 * @param isCsv CSVフラグ
	 * @param parameterDto　推移表共通DTO【検索条件】
	 * @return List[[店舗数]]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#selectListMiseCnt(boolean, jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto)
	 */
	protected List selectListMiseCnt(boolean isCsv, SuiiRefParameterDto parameterDto) {
		//１.DAO【月別店舗数情報】.検索を実行し、戻り値List[[店舗数]]をリターンします。
		return getNetorderSuiiRefUIMiseCntDao().select(
				  getBirdDateInfo().getAppDate().substring(0,6)
				, getBirdUserInfo().getEmosslesAppId()
				, getBirdUserInfo().getMstUser().getUserTypeCd()
				, getBirdUserInfo().isLimit(), getBirdUserInfo().getUserID()
				, parameterDto, isCsv);
	}
	/**
	 * List用単価比率値算出処理
	 * 各単価・比率値算出し設定します。
	 * @param listSuii List[[検索結果]]
	 */
	private void calculaterTankaHiritu(List listSuii) {
		//１.List[[検索結果]]の件数分下記の処理を行います。
        for(int i=0; i<listSuii.size(); i++) {
        	//for-1.List[[検索結果]]から現行インデックスUINetorder[検索結果]を取得します。
        	UINetorder entity = (UINetorder)listSuii.get(i);
        	//for-2.UINetorder[検索結果].営業日ラベルへUINetorder[検索結果].営業日を設定します。
        	entity.setEigyoDtLabel(entity.getEigyoDt());
        	//for-3.単価比率値算出処理を実行し、UINetorder[検索結果]の各単価・比率値算出し設定します。
        	calculaterTankaHiritu(entity, 0);
        }

	}
	/**
	 * 日次ネット注文、ネットテイク、ネット宅配実績店舗数合計
	 * @param listSuii List[[検索結果]]
	 */
	private void calMonthMiseCnt(List listSuii,List listMiseCntTotal,String kikanSitei) {
		//１.List[[検索結果]]の件数分下記の処理を行います。
		UINetorder entityMonthMiseCnt = null;
		for (int i=0;i<listMiseCntTotal.size();i++)
		{
			entityMonthMiseCnt = (UINetorder)listMiseCntTotal.get(i);
			if (kikanSitei.equals(entityMonthMiseCnt.getEigyoDt()))
			{
				break;
			}
		}
        for(int i=0; i<listSuii.size(); i++) {
        	//for-1.List[[検索結果]]から現行インデックスUINetorder[検索結果]を取得します。
        	UINetorder entity = (UINetorder)listSuii.get(i);
        	//for-2.UINetorder[検索結果].日次用実績店舗数合計へUINetorder[検索結果].営業日を設定します。
        	entity.setMiseCntNsumTotal(entityMonthMiseCnt.getMiseCntKbnNsum());
        	entity.setMiseCntNtakeTotal(entityMonthMiseCnt.getMiseCntKbnNtake());
        	entity.setMiseCntNtakuTotal(entityMonthMiseCnt.getMiseCntKbnNtaku());
        }

	}
	/* 月次エンティティ生成取得処理
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#newGepoEntity()
	 */
	protected SuiiRefUIEntity newGepoEntity() {
		return new UINetorder();
	}
	/**
	 * @return netorderSuiiRefUIMiseCntDao を戻します。
	 */
	public UIMiseCntDao getNetorderSuiiRefUIMiseCntDao() {
		return netorderSuiiRefUIMiseCntDao;
	}
	/**
	 * @param netorderSuiiRefUIMiseCntDao 設定する netorderSuiiRefUIMiseCntDao。
	 */
	public void setNetorderSuiiRefUIMiseCntDao(
			UIMiseCntDao netorderSuiiRefUIMiseCntDao) {
		this.netorderSuiiRefUIMiseCntDao = netorderSuiiRefUIMiseCntDao;
	}
	/**
	 * @return netorderSuiiRefUINetorderDao を戻します。
	 */
	public UINetorderDao getNetorderSuiiRefUINetorderDao() {
		return netorderSuiiRefUINetorderDao;
	}
	/**
	 * @param netorderSuiiRefUINetorderDao 設定する netorderSuiiRefUINetorderDao。
	 */
	public void setNetorderSuiiRefUINetorderDao(
			UINetorderDao netorderSuiiRefUINetorderDao) {
		this.netorderSuiiRefUINetorderDao = netorderSuiiRefUINetorderDao;
	}
}
