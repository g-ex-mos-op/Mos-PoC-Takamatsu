/**
 *
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.SiteKbn;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;
import jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.dao.UIMiseCntDao;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.dao.UIMoscardDao;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.entity.UIMoscard;
import jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * LOGIC【検索結果取得】
 *
 * 作成日:2013/05/02
 * @author xkinu
 *
 */
public class SearchLogicImpl extends SuiiRefSearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR016L01";
	/** DAO【月別店舗数情報】 */
	private UIMiseCntDao moscardSuiiRefUIMiseCntDao;
	/** DAO【MOSCARD推移情報】*/
	private UIMoscardDao moscardSuiiRefUIMoscardDao;
	/*
	 * 結果取得処理
	 *
	 * @param 推移表共通DTO【検索条件】
	 * @param 推移表共通DTO【結果情報】
	 * @see jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.SearchLogic#execute(jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto)
	 */
	public SuiiRefResultDto execute(
			boolean isCsv, SuiiRefParameterDto parameterDto, SuiiRefResultDto suiiRefResultDto)
	{
		//１.super.結果取得処理を実行し、戻り値をDTO【結果情報】とします。
		SuiiRefResultDto resultDto = super.execute(isCsv, parameterDto, suiiRefResultDto);
		//２.推移表共通DTO【結果情報】.フォーカス対象UITabData[タブ情報]取得処理を実行し、戻値をUITabData[フォーカスタブ情報]とします。
		UITabData uiFocusTab = (resultDto.getUITabData(parameterDto.getFocusTab()));
		//３.UITabData[フォーカスタブ情報].全店舗数(当年or前年or予算のみ)>0の場合、下記の前年比行の作成処理を行います。
		if(uiFocusTab.getMiseCntAll()>0 && !isCsv && resultDto.getListFocusTabResult() != null && !resultDto.getListFocusTabResult().isEmpty()) {
			//1.推移表共通DTO【結果情報】.List[[フォーカスタブ検索結果]]をList[[検索結果]]とします。
			List list1TabResult = resultDto.getListFocusTabResult();
			//2.List[[検索結果]]最終行のEntityをUIMoscard[合計行]とします。
			UIMoscard lastRowEntity = (UIMoscard)list1TabResult.get(list1TabResult.size()-1);
			//3.UIMoscard[前年比]を生成します。
			UIMoscard newRowEntity = new UIMoscard();
			//4.UIMoscard[前年比].営業日へ"前年比"を設定します。
			newRowEntity.setEigyoDt("前年比");
			//5.UIMoscard[前年比].営業日ラベルへUIMoscard[前年比].営業日を設定します。
			newRowEntity.setEigyoDtLabel(newRowEntity.getEigyoDt());
			//6.UIMoscard[前年比].クラス名へ前年比用の背景色が定義されているclass名を設定します。
			newRowEntity.setCssClassName("body_zennenHi");
			//7.UIMoscard[合計行]の前年比の値をUIMoscard[前年比]へ複写します。
			//チャージ(前年比)
			newRowEntity.setChargeKinZennenhi(lastRowEntity.getChargeKinZennenhi());
			newRowEntity.setChargeKenZennenhi(lastRowEntity.getChargeKenZennenhi());
			newRowEntity.setChargeTankaZennenhi(lastRowEntity.getChargeTankaZennenhi());
			//決済(前年比)
			newRowEntity.setKessaiKinZennenhi(lastRowEntity.getKessaiKinZennenhi());
			newRowEntity.setKessaiKenZennenhi(lastRowEntity.getKessaiKenZennenhi());
			newRowEntity.setKessaiTankaZennenhi(lastRowEntity.getKessaiTankaZennenhi());
			//NETチャージ(前年比)
			newRowEntity.setNetChargeKinZennenhi(lastRowEntity.getNetChargeKinZennenhi());
			newRowEntity.setNetChargeKenZennenhi(lastRowEntity.getNetChargeKenZennenhi());
			newRowEntity.setNetChargeTankaZennenhi(lastRowEntity.getNetChargeTankaZennenhi());
			//NET決済(前年比)
			newRowEntity.setNetKessaiKinZennenhi(lastRowEntity.getNetKessaiKinZennenhi());
			newRowEntity.setNetKessaiKenZennenhi(lastRowEntity.getNetKessaiKenZennenhi());
			newRowEntity.setNetKessaiTankaZennenhi(lastRowEntity.getNetKessaiTankaZennenhi());
			//8.List[[検索結果]]へUIMoscard[前年比]を追加します。
			list1TabResult.add(newRowEntity);
		}
		//４．推移表共通DTO【結果情報】をリターンします。
		return resultDto;
	}
	/*
	 * 推移表共通DTO【結果情報】生成取得処理
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#newSuiiRefResultDto()
	 */
	protected SuiiRefResultDto newSuiiRefResultDto() {
		// TODO 自動生成されたメソッド・スタブ
		return new SuiiRefResultDto();
	}
    /* 12ヶ月平均エンティティ作成処理
     *
     * UIMoscard[12ヶ月合計]からUIMoscard[12ヶ月平均]を作成します。
     *
     * @param SuiiRefUIEntity UIMoscard[12ヶ月合計]
     * @param int 平均件数
     * @param int 売上値小数桁数(海外対応用)
     * @return SuiiRefUIEntity UIMoscard[12ヶ月平均]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntity12Avg(jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity, jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity, int)
	 */
	protected SuiiRefUIEntity createEntity12Avg(SuiiRefUIEntity entiry12Sum, int avgCnt, int scale) {
        //１.UIMoscard[12ヶ月平均]を生成します。
		UIMoscard entiry12Avg = new UIMoscard();
		//２.推移表共通定数クラス.12ヶ月売上データ平均値算出設定を実行します。
        SuiiRefUtil.createEntity12Avg(entiry12Avg, entiry12Sum, avgCnt, scale);

        UIMoscard e12Total = (UIMoscard)entiry12Sum;
        BigDecimal decAvcCnt = new BigDecimal(avgCnt);
        //３．UIMoscard[12ヶ月平均]へ下記の処理から平均値の設定を行います。
        //当年データ
        entiry12Avg.setChargeKenCancel(Calculator.divide(e12Total.getChargeKenCancel(), decAvcCnt));
        entiry12Avg.setChargeKinCancel(Calculator.divide(e12Total.getChargeKinCancel(), decAvcCnt));
        entiry12Avg.setUseKenCancel(Calculator.divide(e12Total.getUseKenCancel(), decAvcCnt));
        entiry12Avg.setUseKinCancel(Calculator.divide(e12Total.getUseKinCancel(), decAvcCnt));
        entiry12Avg.setBonusVIssue(Calculator.divide(e12Total.getBonusVIssue(), decAvcCnt));
        entiry12Avg.setBonusVUse(Calculator.divide(e12Total.getBonusVUse(), decAvcCnt));
        entiry12Avg.setCouponVIssue(Calculator.divide(e12Total.getCouponVIssue(), decAvcCnt));
        entiry12Avg.setCouponVUse(Calculator.divide(e12Total.getCouponVUse(), decAvcCnt));
        entiry12Avg.setIssueCnt(Calculator.divide(e12Total.getIssueCnt(), decAvcCnt));
        entiry12Avg.setChargeKin(Calculator.divide(e12Total.getChargeKin(), decAvcCnt));
        entiry12Avg.setChargeKen(Calculator.divide(e12Total.getChargeKen(), decAvcCnt));
        entiry12Avg.setKessaiKin(Calculator.divide(e12Total.getKessaiKin(), decAvcCnt));
        entiry12Avg.setKessaiKen(Calculator.divide(e12Total.getKessaiKen(), decAvcCnt));
        entiry12Avg.setZandaka(Calculator.divide(e12Total.getZandaka(), decAvcCnt));//残高
        //前年データ
        entiry12Avg.setZenIssueCnt(Calculator.divide(e12Total.getZenIssueCnt(), decAvcCnt));
        entiry12Avg.setZenChargeKin(Calculator.divide(e12Total.getZenChargeKin(), decAvcCnt));
        entiry12Avg.setZenChargeKen(Calculator.divide(e12Total.getZenChargeKen(), decAvcCnt));
        entiry12Avg.setZenKessaiKin(Calculator.divide(e12Total.getZenKessaiKin(), decAvcCnt));
        entiry12Avg.setZenKessaiKen(Calculator.divide(e12Total.getZenKessaiKen(), decAvcCnt));
        //NET当年データ
        entiry12Avg.setNetIssueCnt(Calculator.divide(e12Total.getNetIssueCnt(), decAvcCnt));
        entiry12Avg.setNetChargeKin(Calculator.divide(e12Total.getNetChargeKin(), decAvcCnt));
        entiry12Avg.setNetChargeKen(Calculator.divide(e12Total.getNetChargeKen(), decAvcCnt));
        entiry12Avg.setNetKessaiKin(Calculator.divide(e12Total.getNetKessaiKin(), decAvcCnt));
        entiry12Avg.setNetKessaiKen(Calculator.divide(e12Total.getNetKessaiKen(), decAvcCnt));
       //NET前年データ
        entiry12Avg.setNetZenIssueCnt(Calculator.divide(e12Total.getNetZenIssueCnt(), decAvcCnt));
        entiry12Avg.setNetZenChargeKin(Calculator.divide(e12Total.getNetZenChargeKin(), decAvcCnt));
        entiry12Avg.setNetZenChargeKen(Calculator.divide(e12Total.getNetZenChargeKen(), decAvcCnt));
        entiry12Avg.setNetZenKessaiKin(Calculator.divide(e12Total.getNetZenKessaiKin(), decAvcCnt));
        entiry12Avg.setNetZenKessaiKen(Calculator.divide(e12Total.getNetZenKessaiKen(), decAvcCnt));

        //４.単価比率値算出処理を実行し、算出した平均値から単価・比率を算出しその値をUIMoscard[12ヶ月平均]へ設定します。
        calculaterTankaHiritu(entiry12Avg, scale);
        //５.UIMoscard[12ヶ月平均]をリターンします。
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
     * @return　UIMoscard[累計]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityGepoRuikei(java.lang.String, java.lang.String, java.util.List, int, java.util.List)
	 */
	protected SuiiRefUIEntity createEntityGepoRuikei(String cssClassName, String taishoTitle
			, List listResult, int endRowIndex, List listSuiiNipoTogetu
			, int scale)
	{
		//１.UIMoscard[累計]を生成します。
		UIMoscard sumentity = new UIMoscard();
        //２.合計エンティティ値設定処理を実行します。
		summaryEntitySum(sumentity, cssClassName, taishoTitle, listResult, 0, endRowIndex, scale);
		//３.パラメータ.List[[日次当月推移情報]]の件数>0の場合場合、
        if(listSuiiNipoTogetu != null && listSuiiNipoTogetu.size() > 0) {
            //合計エンティティ値設定処理を実行します。
        	//(UIMoscard[累計]へシステム日付年月の売上発生データの値を加算)
        	summaryEntitySum(sumentity, cssClassName, taishoTitle, listSuiiNipoTogetu, 0, listSuiiNipoTogetu.size(), scale);
        }
        //４.UIMoscard[累計]をリターンします。
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
		//１.日次合計エンティティ作成処理を実行し、戻り値UIMoscard[月次合計]をリターンします。
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
     * @return　UIMoscard[日次合計]
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#createEntityGepoSum(java.lang.String, java.lang.String, java.util.List, int, int)
	 */
	protected SuiiRefUIEntity createEntityNipoSum(String cssClassName, String taishoTitle
			, List listResult, int startRowIndex, int endRowIndex, int scale)
	{
		//１.UIMoscard[日次合計]を生成します。
		UIMoscard sumentity = new UIMoscard();
		//２.合計エンティティ値設定処理を実行します。
		summaryEntitySum(sumentity, cssClassName, taishoTitle, listResult, startRowIndex, endRowIndex, scale);
		//３.UIMoscard[日次合計]をリターンします。
		return sumentity;
	}
	/**
	 * 合計エンティティ値設定処理
	 *
	 * @param sumentity　　UIMoscard[合計]
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param scale         売上値小数桁数(海外対応用)
	 */
	private void summaryEntitySum(UIMoscard sumentity, String cssClassName, String taishoTitle
			, List listResult, int startRowIndex, int endRowIndex
			, int scale)
	{
		//１.推移表共通定数クラス.売上データ集計行生成取得処理を実行し、売上推移表値をパラメータ.UIMoscard[合計]へ設定します。
        SuiiRefUtil.createSumEntity(sumentity, cssClassName, taishoTitle, listResult, startRowIndex, endRowIndex, scale);
        //２.
        //当年データ
        BigDecimal chargeKinCancel = SuiiRefUtil.nullCheckAndFormated(sumentity.getChargeKinCancel(), 0);
        BigDecimal chargeKenCancel = SuiiRefUtil.nullCheckAndFormated(sumentity.getChargeKenCancel(), 0);
        BigDecimal useKinCancel = SuiiRefUtil.nullCheckAndFormated(sumentity.getUseKinCancel(), 0);
        BigDecimal useKenCancel = SuiiRefUtil.nullCheckAndFormated(sumentity.getUseKenCancel(), 0);
        BigDecimal bonusVIssue = SuiiRefUtil.nullCheckAndFormated(sumentity.getBonusVIssue(), 0);
        BigDecimal bonusVUse = SuiiRefUtil.nullCheckAndFormated(sumentity.getBonusVUse(), 0);
        BigDecimal couponVIssue = SuiiRefUtil.nullCheckAndFormated(sumentity.getCouponVIssue(), 0);
        BigDecimal couponVUse = SuiiRefUtil.nullCheckAndFormated(sumentity.getCouponVUse(), 0);
        BigDecimal issueCnt = SuiiRefUtil.nullCheckAndFormated(sumentity.getIssueCnt(), 0);
        BigDecimal chargeKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getChargeKin(), 0);
        BigDecimal chargeKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getChargeKen(), 0);
        BigDecimal kessaiKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getKessaiKin(), 0);
        BigDecimal kessaiKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getKessaiKen(), 0);
        BigDecimal zandaka = SuiiRefUtil.nullCheckAndFormated(sumentity.getZandaka(), 0);

        //前年データ
        BigDecimal zenIssueCnt = SuiiRefUtil.nullCheckAndFormated(sumentity.getZenIssueCnt(), 0);
        BigDecimal zenChargeKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getZenChargeKin(), 0);
        BigDecimal zenChargeKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getZenChargeKen(), 0);
        BigDecimal zenKessaiKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getZenKessaiKin(), 0);
        BigDecimal zenKessaiKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getZenKessaiKen(), 0);
        //NET当年データ
        BigDecimal netIssueCnt = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetIssueCnt(), 0);
        BigDecimal netChargeKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetChargeKin(), 0);
        BigDecimal netChargeKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetChargeKen(), 0);
        BigDecimal netKessaiKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetKessaiKin(), 0);
        BigDecimal netKessaiKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetKessaiKen(), 0);
        //NET前年データ
        BigDecimal netZenIssueCnt = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetZenIssueCnt(), 0);
        BigDecimal netZenChargeKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetZenChargeKin(), 0);
        BigDecimal netZenChargeKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetZenChargeKen(), 0);
        BigDecimal netZenKessaiKin = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetZenKessaiKin(), 0);
        BigDecimal netZenKessaiKen = SuiiRefUtil.nullCheckAndFormated(sumentity.getNetZenKessaiKen(), 0);

        for(int i=startRowIndex; i<endRowIndex; i++) {
        	UIMoscard entity = (UIMoscard)listResult.get(i);
            if(entity == null) continue;
            issueCnt     = issueCnt.add(entity.getIssueCnt());
            chargeKin     = chargeKin.add(entity.getChargeKin());
            chargeKen     = chargeKen.add(entity.getChargeKen());
            kessaiKin     = kessaiKin.add(entity.getKessaiKin());
            kessaiKen     = kessaiKen.add(entity.getKessaiKen());
            //前年データ
            zenIssueCnt     = zenIssueCnt.add(entity.getZenIssueCnt());
            zenChargeKin     = zenChargeKin.add(entity.getZenChargeKin());
            zenChargeKen     = zenChargeKen.add(entity.getZenChargeKen());
            zenKessaiKin     = zenKessaiKin.add(entity.getZenKessaiKin());
            zenKessaiKen     = zenKessaiKen.add(entity.getZenKessaiKen());
            //NET値
            netIssueCnt     = netIssueCnt.add(entity.getNetIssueCnt());
            netChargeKin     = netChargeKin.add(entity.getNetChargeKin());
            netChargeKen     = netChargeKen.add(entity.getNetChargeKen());
            netKessaiKin     = netKessaiKin.add(entity.getNetKessaiKin());
            netKessaiKen     = netKessaiKen.add(entity.getNetKessaiKen());
            //NET前年データ
            netZenIssueCnt     = netZenIssueCnt.add(entity.getNetZenIssueCnt());
            netZenChargeKin     = netZenChargeKin.add(entity.getNetZenChargeKin());
            netZenChargeKen     = netZenChargeKen.add(entity.getNetZenChargeKen());
            netZenKessaiKin     = netZenKessaiKin.add(entity.getNetZenKessaiKin());
            netZenKessaiKen     = netZenKessaiKen.add(entity.getNetZenKessaiKen());
            //取消・発行・残高
            chargeKinCancel     = chargeKinCancel.add(entity.getChargeKinCancel());
            chargeKenCancel     = chargeKenCancel.add(entity.getChargeKenCancel());
            useKinCancel     = useKinCancel.add(entity.getUseKinCancel());
            useKenCancel     = useKenCancel.add(entity.getUseKenCancel());
            bonusVIssue     = bonusVIssue.add(entity.getBonusVIssue());
            bonusVUse     = bonusVUse.add(entity.getBonusVUse());
            couponVIssue     = couponVIssue.add(entity.getCouponVIssue());
            couponVUse     = couponVUse.add(entity.getCouponVUse());
            if(entity.getEigyoDays().compareTo(new BigDecimal("0")) > 0) {
            	zandaka     = entity.getZandaka();
            }
        }
        sumentity.setIssueCnt(issueCnt);
        sumentity.setChargeKin(chargeKin);
        sumentity.setChargeKen(chargeKen);
        sumentity.setKessaiKin(kessaiKin);
        sumentity.setKessaiKen(kessaiKen);
        sumentity.setZenIssueCnt(zenIssueCnt);
        sumentity.setZenChargeKin(zenChargeKin);
        sumentity.setZenChargeKen(zenChargeKen);
        sumentity.setZenKessaiKin(zenKessaiKin);
        sumentity.setZenKessaiKen(zenKessaiKen);
        sumentity.setNetIssueCnt(netIssueCnt);
        sumentity.setNetChargeKin(netChargeKin);
        sumentity.setNetChargeKen(netChargeKen);
        sumentity.setNetKessaiKin(netKessaiKin);
        sumentity.setNetKessaiKen(netKessaiKen);
        sumentity.setNetZenIssueCnt(netZenIssueCnt);
        sumentity.setNetZenChargeKin(netZenChargeKin);
        sumentity.setNetZenChargeKen(netZenChargeKen);
        sumentity.setNetZenKessaiKin(netZenKessaiKin);
        sumentity.setNetZenKessaiKen(netZenKessaiKen);
        sumentity.setChargeKinCancel(chargeKinCancel);
        sumentity.setChargeKenCancel(chargeKenCancel);
        //取消・発行
        sumentity.setUseKinCancel(useKinCancel);
        sumentity.setUseKenCancel(useKenCancel);
        sumentity.setBonusVIssue(bonusVIssue);
        sumentity.setBonusVUse(bonusVUse);
        sumentity.setCouponVIssue(couponVIssue);
        sumentity.setCouponVUse(couponVUse);
        //前受残高
        if(SuiiRefUtil.EIGYO_DT_LABEL_12AVG.equals(sumentity.getEigyoDtLabel())
        		|| SuiiRefUtil.EIGYO_DT_LABEL_12TOTAL.equals(sumentity.getEigyoDtLabel())
        		|| SuiiRefUtil.EIGYO_DT_LABEL_TUUKI.equals(sumentity.getEigyoDtLabel()))
        {
        	//集計行の意味に相応しくない為、表示対象外として0を設定します。
        	zandaka = SuiiRefUtil.nullCheckAndFormated(sumentity.getZandaka(), 0);
        }
        sumentity.setZandaka(zandaka);

        calculaterTankaHiritu(sumentity, scale);
	}
	/**
	 * 単価比率値算出処理
	 * @param entity  UIMoscard[検索結果]
	 * @param scale   売上値小数桁数(海外対応用)
	 */
	private void calculaterTankaHiritu(UIMoscard entity, int scale) {
		//１.推移表共通定数クラス.単価比率値算出処理を実行し、パラメータ.UIMoscard[検索結果]へ売上推移表値の単価・比率算出を行います。
        //単価計算処理
		SuiiRefUtil.calcTanka(entity, scale);
        //比率計算処理
		SuiiRefUtil.calcHiritu(entity);
        //当年単価
        entity.setChargeTanka(Calculator.divide(entity.getChargeKin(), entity.getChargeKen(),0));
        entity.setKessaiTanka(Calculator.divide(entity.getKessaiKin(), entity.getKessaiKen(),0));
        //前年単価
        entity.setZenChargeTanka(Calculator.divide(entity.getZenChargeKin(), entity.getZenChargeKen(),0));
        entity.setZenKessaiTanka(Calculator.divide(entity.getZenKessaiKin(), entity.getZenKessaiKen(),0));
        //NET当年単価
        entity.setNetChargeTanka(Calculator.divide(entity.getNetChargeKin(), entity.getNetChargeKen(),0));
        entity.setNetKessaiTanka(Calculator.divide(entity.getNetKessaiKin(), entity.getNetKessaiKen(),0));
        //NET前年単価
        entity.setNetZenChargeTanka(Calculator.divide(entity.getNetZenChargeKin(), entity.getNetZenChargeKen(),0));
        entity.setNetZenKessaiTanka(Calculator.divide(entity.getNetZenKessaiKin(), entity.getNetZenKessaiKen(),0));
       //売上比
        entity.setChargeUriagehi(Calculator.percentage(entity.getChargeKin(), entity.getUriage(),2));
        entity.setKessaiUriagehi(Calculator.percentage(entity.getKessaiKin(), entity.getUriage(),2));
        //客数比
        entity.setChargeKyakusuhi(Calculator.percentage(entity.getChargeKen(), entity.getKyakusu(),2));
        entity.setKessaiKyakusuhi(Calculator.percentage(entity.getKessaiKen(), entity.getKyakusu(),2));
        //単価比
        entity.setChargeTankahi(Calculator.percentage(entity.getChargeTanka(), entity.getKyakutanka(),2));
        entity.setKessaiTankahi(Calculator.percentage(entity.getKessaiTanka(), entity.getKyakutanka(),2));
        //前年比
        entity.setIssueCntZennenhi(Calculator.percentage(entity.getIssueCnt(), entity.getZenIssueCnt(),2));
        entity.setChargeKinZennenhi(Calculator.percentage(entity.getChargeKin(), entity.getZenChargeKin(),2));
        entity.setChargeKenZennenhi(Calculator.percentage(entity.getChargeKen(), entity.getZenChargeKen(),2));
        entity.setChargeTankaZennenhi(Calculator.percentage(entity.getChargeTanka(), entity.getZenChargeTanka(),2));
        entity.setKessaiKinZennenhi(Calculator.percentage(entity.getKessaiKin(), entity.getZenKessaiKin(),2));
        entity.setKessaiKenZennenhi(Calculator.percentage(entity.getKessaiKen(), entity.getZenKessaiKen(),2));
        entity.setKessaiTankaZennenhi(Calculator.percentage(entity.getKessaiTanka(), entity.getZenKessaiTanka(),2));
        //NET前年比
        entity.setNetIssueCntZennenhi(Calculator.percentage(entity.getNetIssueCnt(), entity.getNetZenIssueCnt(),2));
        entity.setNetChargeKinZennenhi(Calculator.percentage(entity.getNetChargeKin(), entity.getNetZenChargeKin(),2));
        entity.setNetChargeKenZennenhi(Calculator.percentage(entity.getNetChargeKen(), entity.getNetZenChargeKen(),2));
        entity.setNetChargeTankaZennenhi(Calculator.percentage(entity.getNetChargeTanka(), entity.getNetZenChargeTanka(),2));
        entity.setNetKessaiKinZennenhi(Calculator.percentage(entity.getNetKessaiKin(), entity.getNetZenKessaiKin(),2));
        entity.setNetKessaiKenZennenhi(Calculator.percentage(entity.getNetKessaiKen(), entity.getNetZenKessaiKen(),2));
        entity.setNetKessaiTankaZennenhi(Calculator.percentage(entity.getNetKessaiTanka(), entity.getNetZenKessaiTanka(),2));
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
	        //2..e-mosslesサイト区分別に検索処理を実行します。
	        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
	        	listSuii = getMoscardSuiiRefUIMoscardDao().selectNipoFuture(
        				getBirdUserInfo(), getBirdDateInfo()
	            		, parameterDto, futureFromYmd, futureToYmd, zennenYm, zennenFrom, zennenTo
	            		, sysDate , sysDateGetumatu, togetuGessho, zennenGessho);
	        }
	        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
//	        	String onerCd = ((UIUserOner)SuiiRefUtil.getUserOnerCd(getBirdUserInfo(), companyCd)).getOnerCd();
	        	listSuii = getMoscardSuiiRefUIMoscardDao().selectNipoOnerFuture(
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
		//１.DAO【MOSCARD推移情報】未来月次検索を実行し、戻り値をList[[検索結果]]とします。
		List listSuii = getMoscardSuiiRefUIMoscardDao().selectGepoFuture(getBirdUserInfo(), getBirdDateInfo()
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
    	//１.DAO【MOSCARD推移情報】.当月予算取得を実行し、BigDecimal[当月予算]を取得します。
    	BigDecimal decYosan = getMoscardSuiiRefUIMoscardDao().selectGepoTogetuYosan(
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
        	//DAO【MOSCARD推移情報】.月次検索を実行し、戻り値をList[[検索結果]]へ設定します。
            listSuii.addAll(getMoscardSuiiRefUIMoscardDao().selectGepo(
            		getBirdUserInfo(), getBirdDateInfo(), parameterDto, fromYYYYMM, toYYYYMM
            		, parameterDto.isSelectHosei()?ZennenDataShubetu.CODE_DOGETU:parameterDto.getZennenDataShubetu()));
        }
        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
            //サイト区分”やま”（オーナー又は店舗(ＦＣ店)）の場合
        	//DAO【MOSCARD推移情報】.オーナー用月次検索を実行し、戻り値をList[[検索結果]]へ設定します。
            listSuii =getMoscardSuiiRefUIMoscardDao().selectGepoOner(parameterDto);
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
        //２.e-mosslesサイト区分別に検索処理を実行します。
        if(SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn)) {
        	//ユーザータイプが本部か店舗(直営店/販社)の処理になります。
            //DAO【MOSCARD推移情報】検索を実行します。
            listSuii =getMoscardSuiiRefUIMoscardDao().selectNipo(getBirdUserInfo(), getBirdDateInfo()
            		, parameterDto, parameterDto.isSelectHosei()?ZennenDataShubetu.CODE_DOGETU:parameterDto.getZennenDataShubetu());
        }
        else if(SiteKbn.YAMA.equals(userSiteKbn)) {
        	//ユーザータイプがオーナーか店舗(加盟店)の処理になります。
        	//DAO【MOSCARD推移情報】検索を実行します。
            listSuii =getMoscardSuiiRefUIMoscardDao().selectNipoOner(
            		getBirdUserInfo(), getBirdDateInfo()
            		, parameterDto);
	    }
        //３.前年の月がうるう月で、前年データ種別が「前年同月」の場合はうるう日の設定を行います。
    	if(isLasyYmLeapYearMonth && isDogetu )
    	{
    		//うるう日情報を追加
            listSuii.addAll(getMoscardSuiiRefUIMoscardDao().selectNipoLeap0229(
            		getBirdUserInfo(), getBirdDateInfo()
            		, parameterDto));
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
		return getMoscardSuiiRefUIMiseCntDao().select(
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
        	//for-1.List[[検索結果]]から現行インデックスUIMoscard[検索結果]を取得します。
        	UIMoscard entity = (UIMoscard)listSuii.get(i);
        	//for-2.UIMoscard[検索結果].営業日ラベルへUIMoscard[検索結果].営業日を設定します。
        	entity.setEigyoDtLabel(entity.getEigyoDt());
        	//for-3.単価比率値算出処理を実行し、UIMoscard[検索結果]の各単価・比率値算出し設定します。
        	calculaterTankaHiritu(entity, 0);
        }

	}
	/**
	 * @return クラス変数moscardSuiiRefUIMiseCntDao を戻します。
	 */
	public UIMiseCntDao getMoscardSuiiRefUIMiseCntDao() {
		return moscardSuiiRefUIMiseCntDao;
	}
	/**
	 * @param moscardSuiiRefUIMiseCntDao を クラス変数moscardSuiiRefUIMiseCntDaoへ設定します。
	 */
	public void setMoscardSuiiRefUIMiseCntDao(
			UIMiseCntDao moscardSuiiRefUIMiseCntDao) {
		this.moscardSuiiRefUIMiseCntDao = moscardSuiiRefUIMiseCntDao;
	}
	/* 月次エンティティ生成取得処理
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#newGepoEntity()
	 */
	protected SuiiRefUIEntity newGepoEntity() {
		// TODO 自動生成されたメソッド・スタブ
		return new UIMoscard();
	}
	/**
	 * @return クラス変数moscardSuiiRefUIMoscardDao を戻します。
	 */
	public UIMoscardDao getMoscardSuiiRefUIMoscardDao() {
		return moscardSuiiRefUIMoscardDao;
	}
	/**
	 * @param moscardSuiiRefUIMoscardDao を クラス変数moscardSuiiRefUIMoscardDaoへ設定します。
	 */
	public void setMoscardSuiiRefUIMoscardDao(
			UIMoscardDao moscardSuiiRefUIMoscardDao) {
		this.moscardSuiiRefUIMoscardDao = moscardSuiiRefUIMoscardDao;
	}
}
