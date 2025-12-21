/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.SyukeiKbnResultData;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.TrnSyuseiAridaka;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIKKbnReviseData;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.TrnSyuseiAridakaDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.KaikeiKbnSearchLogic;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlKaikeiKbnInfoDao;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.common.kaikei.entity.GetterMethod;
import jp.co.isid.mos.bird.common.kaikei.util.KaikeiUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * LOGIC【会計区分売上修正検索】
 * 
 * 作成日:2012/07/27
 * @author xkinu
 *
 */
public class KaikeiKbnSearchLogicImpl implements KaikeiKbnSearchLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L09";
	
	/** Dao【会計集計区分情報】*/
	private CtlSyukeiKbnDao ctlSyukeiKbnDao;
	/** Dao【会計区分情報】*/
	private CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao;
	/** Dao【会計区分別在高日別修正】*/
	private TrnSyuseiAridakaDao uriMaintenanceTrnSyuseiAridakaDao;

	
	/**
	 * 事前条件処理
	 * 
	 * @param sessionDto
	 * @return List[[表示対象集計区分情報]]
	 * 更新日:2014/12/10 周春建　会計区分拡張対応
	 */
	private void validate(UriMaintenanceDto sessionDto){
		//処理に必要な内部情報は満たされているか。
		if(CommonUtil.isNull(sessionDto.getSysDate())) {
			throw new MissingDataException("システム日付");
		}
		if(CommonUtil.isNull(sessionDto.getCondCompanyCd())) {
			throw new MissingDataException("会社コード");
		}
		if(CommonUtil.isNull(sessionDto.getCondMiseCd())) {
			throw new MissingDataException("店コード");
		}
		if(CommonUtil.isNull(sessionDto.getCondTargetYm())) {
			throw new MissingDataException("対象年月");
		}
		//マスタ情報が存在するか？
		//1.Dao【会計集計区分情報】．検索を実行し、戻り値List[[集計区分情報]]を取得します。
		List listSyukeiKbn = getCtlSyukeiKbnDao().select("1");
		sessionDto.setListSyukeiKbn(listSyukeiKbn);
		//2.Dao【会計区分情報】．検索を実行し、戻り値List[[会計区分情報]]を取得します。
		//List listKaikeiKbn = getCtlKaikeiKbnInfoDao().select("1");
		//会計区分マスタの抽出条件には会社コードも含める
		List listKaikeiKbn = getCtlKaikeiKbnInfoDao().select("1",sessionDto.getCondCompanyCd());
		//3．変数．Map[集計区分別会計区分情報]を生成し、
		//   BIRD共通【会計区分系共通定数クラス】．集計区分別会計区分マスタ情報作成処理を実行し、戻り値を設定します。
		Map mapSyukeiKbnData = KaikeiUtil.makeKaikeiSyukeiKbnInfo(listSyukeiKbn, listKaikeiKbn);
		//4．DTO【Session情報】．Map[集計区分別会計区分情報]へ変数．Map[集計区分別会計区分情報]を設定します。
		sessionDto.setMapSyukeiKbnMstData(mapSyukeiKbnData);

	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.urimaintenance.logic.SearchKaikeiKbnTrnData#execute(jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto, jp.co.isid.mos.bird.bizreport.urimaintenance.dto.RequestDto)
	 */
	public void execute(UriMaintenanceDto sessionDto) {
		//０．事前条件処理し、戻り値List[[表示対象集計区分情報]]を取得します。
		validate(sessionDto);
		//１.処理０のList[[表示対象集計区分情報]]の件数分下記の処理を行います。
		for(int s=0; s<sessionDto.getListSyukeiKbn().size(); s++) {
			//for-1.現行行の[表示対象集計区分情報]を取得します。
            CtlSyukeiKbn syukeiKbn = (CtlSyukeiKbn)(sessionDto.getListSyukeiKbn().get(s));
			//for-2.SyukeiKbnResultData[集計区分結果情報保持]を生成します。
			SyukeiKbnResultData skResultData = new SyukeiKbnResultData();
			//for-3.SyukeiKbnResultData[集計区分結果情報保持]へ下記の値を設定します。
			skResultData.setCd(syukeiKbn.getSyukeiCd()); //SyukeiKbnResultData[集計区分結果情報保持].コードへ[表示対象集計区分情報].集計区分コード
			skResultData.setName(syukeiKbn.getSyukeiName());//SyukeiKbnResultData[集計区分結果情報保持].名称へ[表示対象集計区分情報].集計区分名称
			//for-4.DTO【Session情報】.Map[集計区分別会計区分結果情報]へ
			//    UIList[集計区分情報].キーをキーにSyukeiKbnResultData[集計区分結果情報保持]を格納します。
			sessionDto.getMapSKbnResultData().put(syukeiKbn.getSyukeiCd(), skResultData);
		}
		//２．Dao【会計区分別在高日別修正】．修正状況検索を実行し、List[[修正状況検索結果]]を取得します。
		List listSearchReviseData = getUriMaintenanceTrnSyuseiAridakaDao().select(
				sessionDto.getSysDate()
			  , sessionDto.getCondCompanyCd()
			  , sessionDto.getCondMiseCd()
			  , sessionDto.getCondTargetYm());
		//３．処理２のList[[修正状況検索結果]]がnull又はデータが存在しない場合は下記のException発生させます。	
		if(listSearchReviseData.isEmpty() || listSearchReviseData.size()==1) {
			throw new NoResultException();
		}
		//４．DTO【Session情報】.List[[会計区分別在高日別修正]]へList[[修正状況検索結果]]を設定します。
		sessionDto.setListKKbnSearchData(listSearchReviseData);
		//５．Dao【会計区分別在高日別修正】．会計区分別在高日次検索を実行し、List[[会計区分別在高日次検索結果]]を取得します。
		List listSearchBD30Data = getUriMaintenanceTrnSyuseiAridakaDao().selectBD30(
				sessionDto.getSysDate()
			  , sessionDto.getCondCompanyCd()
			  , sessionDto.getCondMiseCd()
			  , sessionDto.getCondTargetYm());
		
		//６.処理０のList[[表示対象集計区分情報]]の件数分下記の処理を行います。
		for(int s=0; s<sessionDto.getListSyukeiKbn().size(); s++) {
			//for-1.現行行の[表示対象集計区分情報]を取得します。
            CtlSyukeiKbn syukeiKbn = (CtlSyukeiKbn)(sessionDto.getListSyukeiKbn().get(s));
            //for-2.DTO【Session情報】.Map[集計区分別会計区分情報]から[表示対象集計区分情報]集計区分コードをキーに
            //      UIList[集計区分情報]を取得します。
            UILists uiSyukeiKbn = (UILists)sessionDto.getMapSyukeiKbnMstData().get(syukeiKbn.getSyukeiCd());
            //for-3.DTO【Session情報】.Map[集計区分別会計区分結果情報]から[表示対象集計区分情報]集計区分コードをキーに
            //      SyukeiKbnResultData[集計区分結果情報保持]を取得します。
            SyukeiKbnResultData skResultData = (SyukeiKbnResultData)sessionDto.getMapSKbnResultData().get(syukeiKbn.getSyukeiCd());
			/*
			 * for-4．メソッド【タブ別情修正状況報作成取得処理】を実行し、戻り値[集計区分結果情報]を取得します。	
			 * パラメータ：	UIList[集計区分情報]
			 *              SyukeiKbnResultData[集計区分結果情報保持]
			 *              List[[修正状況検索結果]]
			 *              List[[会計区分別在高日次検索結果]]
			 */
			createTabData(uiSyukeiKbn, skResultData, listSearchReviseData, listSearchBD30Data);
		}
		//７．処理終了
	}
   	/**
	 * 画面表示用タブ別情修正状況報作成取得処理
	 * 
	 * @param uiSyukeiKbn UIList[集計区分情報]
	 * @param skResultData SyukeiKbnResultData[集計区分結果情報]
	 * @param listBD31Data List[[BD31KKUP結果]]
	 * @param listSearchBD30Data List[[BD30KKAD結果]]
	 */
    private void createTabData(UILists uiSyukeiKbn, SyukeiKbnResultData skResultData, List listBD31Data, List listBD30Data) {
		//１．変数．List[[結果タブ別情報]]を生成します。
		List listTabReault = new ArrayList(0);
		CodeFormatter codefm = new CodeFormatter(2);
		codefm.setFormatPattern("00");
		//２．UIList[集計区分情報]．List[[会計区分]]の件数÷４（余りがある場合は＋１）の値分下記の処理を行います。
		int iKKbnCnt = uiSyukeiKbn.getListData().size();
		int tabCnt = (iKKbnCnt/4)+(iKKbnCnt%4>0?1:0);
		for(int t=1; t<=tabCnt; t++) {
			//for-1.UIUriMaintenanceResult[タブ情報]を生成し、下記の値を設定します。
			//UIUriMaintenanceResult[タブ情報]．キー = （for文インデックス+1)→”0#”フォーマット変換値（前ゼロ付加）を設定します。
			//UIUriMaintenanceResult[タブ情報]．キー名称 = ”その”＋（for文インデックス+1)
			UIUriMaintenanceResult tab = new UIUriMaintenanceResult(
					codefm.format(String.valueOf(t), true), "その"+String.valueOf(t));
			//for-2.変数．List[[結果タブ情報]]へUIUriMaintenanceResult[タブ情報]を追加します。
			listTabReault.add(tab);
		}
		//３．BIRD共通【会計区分系共通定数クラス】.メソッド【件数・金額Getterメソッド情報取得処理】を実行し、
		//    戻り値Map[Getterメソッド]を取得します。
		Map mapGetMethod = KaikeiUtil.getMapGetterMethod(uiSyukeiKbn.getListData(), TrnSyuseiAridaka.class, "Kaikei");
		//４．変数.Map[[結果タブ別情報]]を生成します。
		Map mapTabData = new HashMap();
		//５．変数．List[[結果タブ別情報]]分、下記の処理を行います。
		for(int t=0; t<listTabReault.size(); t++) {
			//for-1.変数．List[[結果タブ別情報]]から現行行UIUriMaintenanceResult[結果タブ情報]を取得します。
			UIUriMaintenanceResult uiTab = (UIUriMaintenanceResult)listTabReault.get(t);
			//for-2．BIRD共通【会計区分系共通定数クラス】.メソッド【画面表示会計区分情報取得処理】を実行し、戻り値Map[画面表示情報]を取得します。
			Map mapScreenInf = KaikeiUtil.getScreenDispInfo(uiSyukeiKbn.getListData(), uiTab.getKey());
			//for-3．変数List[[画面表示会計区分]]を生成し、
			//       変数List[[画面表示会計区分]]へMap[画面表示情報]キー："CODE"で取得したList[[画面表示会計区分]]を設定します。
			List listKcd = (List)mapScreenInf.get(KaikeiUtil.MK_CODE);
			//for-4．変数List[[画面表示会計区分名称]]を生成し、
			//       変数List[[画面表示会計区分名称]]へMap[画面表示情報]キー："NAME"で取得したList[[画面表示会計区分名称]]を設定します。
			List listKname = (List)mapScreenInf.get(KaikeiUtil.MK_NAME);
			//for-5.変数List[[画面表示会計区分コード＆名称]]を生成し、
			//      変数List[[画面表示会計区分]]と変数List[[画面表示会計区分名称]]からコードと名称を対で１インデックスに格納します。
			List listKcdKname = new ArrayList(0);
			for(int h=0; h<listKcd.size(); h++) {
				UILists uiKaikei = new UILists((String)listKcd.get(h), (String)listKname.get(h));
				listKcdKname.add(uiKaikei);
			}
			//for-4.UIUriMaintenanceResult[結果タブ情報]．List[[ヘッダー]]へ変数List[[画面表示会計区分コード＆名称]]を設定します。
			uiTab.setListHeader(listKcdKname);
		    //以下for-6～8までtry-catchで囲みます。
			try {
				//for-6.メソッド【画面レイアウト用情報作成処理】を実行し、List[[会計区分修正情報]]を取得します。
				//　　　パラメータ：UIList[集計区分情報]．List[[会計区分]]
				//                  UIUriMaintenanceResult[結果タブ情報].キー
				//                  List[[BD31KKUP結果]]
				//                  List[[BD30KKAD結果]]
				//                  Map[Getterメソッド]
				List list1TabResult = createScreenLayoutData(
						listKcd, listBD31Data, listBD30Data, mapGetMethod);	
				//for-7.UIUriMaintenanceResult[結果タブ情報]．List[[データ]]へ処理for-6のList[[会計区分修正情報]]を設定します。
				uiTab.setListData(list1TabResult);
				//for-8.IListDataHeader[結果タブ情報].[合計行]へList[[会計区分修正情報]]最終インデックス値[会計区分修正状況]を設定します。
				int totalPointIndex = list1TabResult.size()-1;
				uiTab.setReviseTotal((UIKKbnReviseData)list1TabResult.get(totalPointIndex));
//				list1TabResult.remove(totalPointIndex);
			}
			catch (Exception ex) {
				//Exceptionが発生した場合下記のExceptionを発生させます。
				throw new FtlSystemException("売上修正画面：会計区分修正状況作成", null, ex);
			}
			//for-9.変数.Map[[結果タブ別情報]]へList[[タブ番号]].タブ番号をキーにUIUriMaintenanceResult[タブ別情報]を設定します。
			mapTabData.put(uiTab.getKey(), uiTab);	
		}//end of for(int t=0; t<listTabNo.size(); t++)
		//６．SyukeiKbnResultData[集計区分結果情報].Map[タブ別結果情報]へ変数.Map[[結果タブ別情報]]を設定します。
		skResultData.setMapTabResult(mapTabData);
		//７．SyukeiKbnResultData[集計区分結果情報].List[[タブ別結果情報]]へ変数．List[[結果タブ別情報]]を設定します。
		skResultData.setListTabResult(listTabReault);
		//８．売上修正共通【静的処理クラス】.日別合計値取得処理を実行します。
		UriMaintenanceCommon.calculateDayTotal(listTabReault, true);
		//９．処理終了
	}
	/**
	 * 画面レイアウト用情報作成処理
	 * 
	 * @param listKaikeiKbn List[[画面表示会計区分]]
	 * @param targetTabNo
	 * @param listBD31Data List[[BD31KKUP結果]]
	 * @param listSearchBD30Data List[[BD30KKAD結果]]
	 * @param mapGetMethod Map[Getterメソッド]
	 * 
	 * @return List[[会計区分修正情報]]
	 * @throws Exception
	 */
	private static List createScreenLayoutData(
			List listKcd, List listBD31Data, List listBD30Data, Map mapGetMethod
			)
	throws Exception
	{
		//０．リターン値List[[会計区分修正情報]]を生成します。
		List listRevisedData = new ArrayList(0);
		//１．引数．List[[BD31KKUP結果]]の件数分、下記の処理を行います。
		for(int i=0; i<listBD31Data.size(); i++) {
			//for-1.現行インデックス位置のエンティティを取得します。
			//       引数．List[[BD31KKUP結果]]からTrnSyuseiAridaka[BD31KKUP結果]を取得します。
			//       引数．List[[BD30KKAD結果]]からTrnSyuseiAridaka[BD30KKAD結果]を取得します。
			TrnSyuseiAridaka eBD31Data = (TrnSyuseiAridaka)listBD31Data.get(i);
			TrnSyuseiAridaka eBD30Data = (TrnSyuseiAridaka)listBD30Data.get(i);
			//for-2.新規に変数.Entity[会計区分修正情報]を生成します。
			UIKKbnReviseData eRevised = new UIKKbnReviseData();
			eRevised.setEigyoDt(eBD31Data.getEigyoDt());
			//for-3.引数List[[画面表示会計区分]]件数分、変数.Entity[会計区分修正情報]に以下の値を設定します。
			for(int s=0; s<listKcd.size(); s++) {
				String kaikeiKbnCd = (String)listKcd.get(s);
				//for2-1.現行インデックスの引数List[[画面表示会計区分]][会計区分].会計区分コードをキーに
				//Map[Getterメソッド]、Map[修正Getterメソッド]からGetterMethod[Getterメソッド]を取得し、以下の値を取得します。
				GetterMethod gMethod = (GetterMethod)mapGetMethod.get(kaikeiKbnCd);
				//変数.dec[件数]へ GetterMethod[Getterメソッド].件数Methodを実行した値を設定します。
				BigDecimal decKen = (BigDecimal)gMethod.getMethodKen().invoke(eBD30Data, new Object[]{});
				//変数.dec[金額]へ GetterMethod[Getterメソッド].金額Methodを実行した値を設定します。
				BigDecimal decKin = (BigDecimal)gMethod.getMethodKin().invoke(eBD30Data, new Object[]{});
				//変数.dec[修正済件数]へ GetterMethod[Getterメソッド].件数Methodを実行した値を設定します。
				BigDecimal decRevisedKen = (BigDecimal)gMethod.getMethodKen().invoke(eBD31Data, new Object[]{});
				decRevisedKen = decRevisedKen != null?decRevisedKen:decKen;
				//変数.dec[修正済金額]へGetterMethod[Getterメソッド].金額Methodを実行した値を設定します。
				BigDecimal decRevisedKin = (BigDecimal)gMethod.getMethodKin().invoke(eBD31Data, new Object[]{});
				decRevisedKin = decRevisedKin != null?decRevisedKin:decKin;
				//for2-2.変数.Entity[会計区分修正情報]へfor2-1の各変数を設定します。
				//IF-1．処理for3の現行インデックス==0(ゼロ)の場合
				if(s==0) {
					//変数.Entity[会計区分修正情報]．明細1存在フラグ　＝ TRUE
					eRevised.setExistsMeisai1(true);
					//変数.Entity[会計区分修正情報]．明細件数1　＝ 変数.dec[件数]
					eRevised.setMeisaiKen1(decKen);
					//変数.Entity[会計区分修正情報]．明細金額1　＝ 変数.dec[金額]
					eRevised.setMeisaiKin1(decKin);
					//変数.Entity[会計区分修正情報]．登録済件数1修正値　＝ 変数.dec[修正済件数]
					eRevised.setBd31Ken1(decRevisedKen);
					//変数.Entity[会計区分修正情報]．登録済金額1修正値　＝ 変数.dec[修正済金額]
					eRevised.setBd31Kin1(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数1修正値　＝ 変数.dec[修正済件数]
					eRevised.setReviseKen1(decRevisedKen);
					//変数.Entity[会計区分修正情報]．明細金額1修正値　＝ 変数.dec[修正済金額]
					eRevised.setReviseKin1(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数1修正値　＝ 変数.dec[修正済件数]
					eRevised.setStrReviseKen1(decRevisedKen.toString());
					//変数.Entity[会計区分修正情報]．明細金額1修正値　＝ 変数.dec[修正済金額]
					eRevised.setStrReviseKin1(decRevisedKin.toString());
					continue;
				}//end of if(s==0)
				//IF-1．処理for3の現行インデックス==1の場合
				if(s==1) {
					//変数.Entity[会計区分修正情報]．明細2存在フラグ　＝ TRUE
					eRevised.setExistsMeisai2(true);
					//変数.Entity[会計区分修正情報]．明細件数2　＝ 変数.dec[件数]
					eRevised.setMeisaiKen2(decKen);
					//変数.Entity[会計区分修正情報]．明細金額2　＝変数.dec[金額]
					eRevised.setMeisaiKin2(decKin);
					//変数.Entity[会計区分修正情報]．登録済件数2修正値　＝ 変数.dec[修正済件数]
					eRevised.setBd31Ken2(decRevisedKen);
					//変数.Entity[会計区分修正情報]．登録済金額2修正値　＝ 変数.dec[修正済金額]
					eRevised.setBd31Kin2(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数2修正値　＝ 変数.dec[修正済件数]
					eRevised.setReviseKen2(decRevisedKen);
					//変数.Entity[会計区分修正情報]．明細金額2修正値　＝ 変数.dec[修正済金額]
					eRevised.setReviseKin2(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数2修正値　＝ 変数.dec[修正済件数]
					eRevised.setStrReviseKen2(decRevisedKen.toString());
					//変数.Entity[会計区分修正情報]．明細金額2修正値　＝ 変数.dec[修正済金額]
					eRevised.setStrReviseKin2(decRevisedKin.toString());
					continue;
				}//end of if(s==1)
				//IF-1．処理for3の現行インデックス==2の場合
				if(s==2) {
					//変数.Entity[会計区分修正情報]．明細3存在フラグ　＝ TRUE
					eRevised.setExistsMeisai3(true);
					//変数.Entity[会計区分修正情報]．明細件数3　＝ 変数.dec[件数]
					eRevised.setMeisaiKen3(decKen);
					//変数.Entity[会計区分修正情報]．明細金額3　＝変数.dec[金額]
					eRevised.setMeisaiKin3(decKin);
					//変数.Entity[会計区分修正情報]．登録済件数3修正値　＝ 変数.dec[修正済件数]
					eRevised.setBd31Ken3(decRevisedKen);
					//変数.Entity[会計区分修正情報]．登録済金額3修正値　＝ 変数.dec[修正済金額]
					eRevised.setBd31Kin3(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数3修正値　＝ 変数.dec[修正済件数]
					eRevised.setReviseKen3(decRevisedKen);
					//変数.Entity[会計区分修正情報]．明細金額3修正値　＝ 変数.dec[修正済金額]
					eRevised.setReviseKin3(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数3修正値　＝ 変数.dec[修正済件数]
					eRevised.setStrReviseKen3(decRevisedKen.toString());
					//変数.Entity[会計区分修正情報]．明細金額3修正値　＝ 変数.dec[修正済金額]
					eRevised.setStrReviseKin3(decRevisedKin.toString());
					continue;
				}//end of if(s==2)
				//IF-1．処理for3の現行インデックス==3の場合
				if(s==3) {
					//変数.Entity[会計区分修正情報]．明細４存在フラグ　＝ TRUE
					eRevised.setExistsMeisai4(true);
					//変数.Entity[会計区分修正情報]．明細件数４　＝ 変数.dec[件数]
					eRevised.setMeisaiKen4(decKen);
					//変数.Entity[会計区分修正情報]．明細金額４　＝変数.dec[金額]
					eRevised.setMeisaiKin4(decKin);
					//変数.Entity[会計区分修正情報]．登録済件数4修正値　＝ 変数.dec[修正済件数]
					eRevised.setBd31Ken4(decRevisedKen);
					//変数.Entity[会計区分修正情報]．登録済金額4修正値　＝ 変数.dec[修正済金額]
					eRevised.setBd31Kin4(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数4修正値　＝ 変数.dec[修正済件数]
					eRevised.setReviseKen4(decRevisedKen);
					//変数.Entity[会計区分修正情報]．明細金額4修正値　＝ 変数.dec[修正済金額]
					eRevised.setReviseKin4(decRevisedKin);
					//変数.Entity[会計区分修正情報]．明細件数4修正値　＝ 変数.dec[修正済件数]
					eRevised.setStrReviseKen4(decRevisedKen.toString());
					//変数.Entity[会計区分修正情報]．明細金額4修正値　＝ 変数.dec[修正済金額]
					eRevised.setStrReviseKin4(decRevisedKin.toString());
				}//end of if(s==3)
			}//end of for(int s=0; s<listKcd.size(); s++)
			//for-3.変数．List[[会計区分修正情報]]へEntity[明細情報]を格納します。
			listRevisedData.add(eRevised);
			//for-4.処理for-1へ戻ります。
			
		}// end of for(int i=0; i<listRevisedData.size(); i++)
		
		//２．リターン値List[[会計区分修正情報]]をリターンします。
		return listRevisedData;
		//３．処理終了。
	}
	/**
	 * Dao【会計区分情報】取得処理
	 * @return クラス変数ctlKaikeiKbnInfoDao を戻します。
	 */
	public CtlKaikeiKbnInfoDao getCtlKaikeiKbnInfoDao() {
		return ctlKaikeiKbnInfoDao;
	}
	/**
	 * Dao【会計区分情報】設定処理
	 * @param ctlKaikeiKbnInfoDao を クラス変数ctlKaikeiKbnInfoDaoへ設定します。
	 */
	public void setCtlKaikeiKbnInfoDao(CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao) {
		this.ctlKaikeiKbnInfoDao = ctlKaikeiKbnInfoDao;
	}
	/**
	 * Dao【会計集計区分情報】取得処理
	 * @return クラス変数ctlSyukeiKbnDao を戻します。
	 */
	public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
		return ctlSyukeiKbnDao;
	}
	/**
	 * Dao【会計集計区分情報】設定処理
	 * @param ctlSyukeiKbnDao を クラス変数ctlSyukeiKbnDaoへ設定します。
	 */
	public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
		this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
	}
	/**
	 * Dao【会計区分別在高日別修正】取得処理
	 * @return クラス変数uriMaintenanceTrnSyuseiAridakaDao を戻します。
	 */
	public TrnSyuseiAridakaDao getUriMaintenanceTrnSyuseiAridakaDao() {
		return uriMaintenanceTrnSyuseiAridakaDao;
	}
	/**
	 * Dao【会計区分別在高日別修正】設定処理
	 * @param dao を クラス変数uriMaintenanceTrnSyuseiAridakaDaoへ設定します。
	 */
	public void setUriMaintenanceTrnSyuseiAridakaDao(
			TrnSyuseiAridakaDao dao) {
		this.uriMaintenanceTrnSyuseiAridakaDao = dao;
	}

}
