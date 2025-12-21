package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.SyukeiKbnResultData;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.TrnSyuseiAridaka;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIMeisaiReviseInf;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dao.TrnSyuseiAridakaDao;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewMeisaiResultDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.SearchMeisaiLogic;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlKaikeiKbnInfoDao;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.GetterMethod;
import jp.co.isid.mos.bird.common.kaikei.util.KaikeiUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 売上修正確認明細検索ロジック
 * @author kawa
 */
public class SearchMeisaiLogicImpl implements SearchMeisaiLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBR009L03";

    /** 会計集計区分情報 */
    private CtlSyukeiKbnDao ctlSyukeiKbnDao;
    
    /** 会計区分情報 */
    private CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao;
    
    /** 会計区分系共通定数クラス */
    private KaikeiUtil kaikeiUtil;
    
    /** 会計区分別在高日次修正Dao */
    private TrnSyuseiAridakaDao trnSyuseiAridakaDao;
    
    /**
     * 0.事前条件処理
     * @param sessionDto 売上修正セッションDTOクラス
     * @param meisaiRequestJokenDto 売上修正明細照会・条件部DTOクラス
     * 更新日:2014/12/12 周春建　会計区分拡張対応
     */
    private void validate(
            UriMainteViewSesDto sessionDto
            , MeisaiRequestDto meisaiRequestJokenDto) {

    // 内部情報チェック
        // システム日付
        if (CommonUtil.isNull(sessionDto.getSysDate())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_SYSDATE);
        }
        // 集計区分
        if (CommonUtil.isNull(meisaiRequestJokenDto.getSyukeiCd())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_SYUKEIKBN);
        }

    // マスタ情報チェック

        // 会計区分情報取得
        //List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(UriMainteViewConstants.BIRD_DISP_FLG_ON);
        // 会社コード
        if (CommonUtil.isNull(meisaiRequestJokenDto.getCompanyCd())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_COMPANYCD);
        }
        //会計区分マスタの抽出条件には会社コードも含める
        List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(UriMainteViewConstants.BIRD_DISP_FLG_ON,meisaiRequestJokenDto.getCompanyCd());
        if(kaikeiKbnList == null || kaikeiKbnList.isEmpty()){
            throw new NoResultException();
        }
        
        // 集計区分情報取得
        List syukeiKbnList = getCtlSyukeiKbnDao().select(UriMainteViewConstants.BIRD_DISP_FLG_ON);
        if(syukeiKbnList == null || syukeiKbnList.isEmpty()){
            throw new NoResultException();
        }
        
        // 集計区分別会計区分マスタ情報
        Map syukeiKbnMaster = KaikeiUtil.makeKaikeiSyukeiKbnInfo(syukeiKbnList,kaikeiKbnList);

        if (!syukeiKbnMaster.containsKey(meisaiRequestJokenDto.getSyukeiCd())) {
            // 集計区分および紐付く会計区分が存在しない場合、データなしエラー
            throw new NoResultException();
        }
        
    // 必須チェック
//        // 会社コード
//        if (CommonUtil.isNull(meisaiRequestJokenDto.getCompanyCd())) {
//            throw new MissingDataException(UriMainteViewConstants.MSG_USER_COMPANYCD);
//        }
        // 修正日
        if (CommonUtil.isNull(meisaiRequestJokenDto.getSyuseiDate())) {
            throw new MissingDataException(UriMainteViewConstants.MSG_USER_SYUSEIBI);
        }
        //集計区分別会計区分マスタ情報を設定する
        sessionDto.setKaikeiKbnMasterInfo(syukeiKbnMaster);
    }
    
    /**
     * 明細検索
     * @param proceedsManageGepoDto 売上修正セッションDTOクラス
     * @param meisaiRequestJokenDto 売上修正明細照会・条件部DTOクラス
     * @param meisaiRequestResultDto 売上修正明細照会・結果部DTOクラス
     */
    public void execute(
            UriMainteViewSesDto sessionDto
          , MeisaiRequestDto meisaiRequestJokenDto
          , UriMainteViewMeisaiResultDto meisaiRequestResultDto) {
        
        // 事前条件処理
        validate(sessionDto, meisaiRequestJokenDto);
        
        // 変数.SyukeiKbnResultData[集計区分結果情報保持]を生成します。
        SyukeiKbnResultData syukeiKbnResultData = new SyukeiKbnResultData();
        
        // DTO【Session情報】.Map[集計区分別会計区分情報]からDTO【Request明細条件】．集計区分をキーにUIList[集計区分情報]を取得します。
        UILists syukeiKbnInfo = (UILists)sessionDto.getKaikeiKbnMasterInfo().get(meisaiRequestJokenDto.getSyukeiCd());
        syukeiKbnResultData.setCd(syukeiKbnInfo.getKey());
        syukeiKbnResultData.setName(syukeiKbnInfo.getKeyName());
        
        // Dao【会計区分別在高修正】．検索を実行し、List[[検索結果]]を取得します。
        List result = getTrnSyuseiAridakaDao().select(sessionDto.getSysDate(), 
                meisaiRequestJokenDto.getCompanyCd(), meisaiRequestJokenDto.getSyuseiDate());
        
        // List[[検索結果]]のデータが存在しない場合は下記のException発生させます。
        if (result == null || result.isEmpty()) {
            throw new NoResultException();
        }
        
        // DTO【Request明細条件】の検索条件項目の値をDTO【Request明細結果】へ設定します。
        meisaiRequestResultDto.setWindowId(meisaiRequestJokenDto.getWindowId());
        meisaiRequestResultDto.setCompanyCd(meisaiRequestJokenDto.getCompanyCd());
        meisaiRequestResultDto.setSyuseiDate(meisaiRequestJokenDto.getSyuseiDate());
        meisaiRequestResultDto.setSyukeiCd(meisaiRequestJokenDto.getSyukeiCd());
        meisaiRequestResultDto.setSyukeiNm(meisaiRequestJokenDto.getSyukeiNm());
        
        List companyList = sessionDto.getCompanyList();
        for(Iterator it = companyList.iterator(); it.hasNext();){
        	MstUserCompany company = (MstUserCompany)it.next();
        	if (meisaiRequestJokenDto.getCompanyCd().equals(company.getCompanyCd())){
        		meisaiRequestResultDto.setCompanyName(company.getCompanyName());
        		break;
        	}
        }
        
        // メソッド【タブ別情報作成取得処理】を実行します。
        createTabData(syukeiKbnInfo, syukeiKbnResultData, result);
        
        // DTO【Request明細結果】．SyukeiKbnResultData[集計区分結果情報]へ変数.SyukeiKbnResultData[集計区分結果情報保持]を設定します。
        meisaiRequestResultDto.setSyukeiKbnResultData(syukeiKbnResultData);
        
        
        // 処理終了
    }
    /**
     * 画面表示用タブ別情修正状況報作成取得処理
     * 
     * @param uiSyukeiKbn UIList[集計区分情報]
     * @param skResultData SyukeiKbnResultData[集計区分結果情報]
     * @param listBD31Data List[[BD31KKUP結果]]
     */
    private void createTabData(UILists uiSyukeiKbn, SyukeiKbnResultData skResultData, List listBD31Data) {
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
                        listKcd, listBD31Data, mapGetMethod); 
                //for-7.UIUriMaintenanceResult[結果タブ情報]．List[[データ]]へ処理for-6のList[[会計区分修正情報]]を設定します。
                uiTab.setListData(list1TabResult);
            }
            catch (Exception ex) {
                //Exceptionが発生した場合下記のExceptionを発生させます。
                throw new FtlSystemException("売上修正確認明細画面：会計区分修正状況作成", null, ex);
            }
            //for-9.変数.Map[[結果タブ別情報]]へList[[タブ番号]].タブ番号をキーにUIUriMaintenanceResult[タブ別情報]を設定します。
            mapTabData.put(uiTab.getKey(), uiTab);  
        }//end of for(int t=0; t<listTabNo.size(); t++)
		//６．売上修正共通【静的処理クラス】.日別合計値取得処理を実行します。
		calculateDayTotal(listTabReault);
        //７．SyukeiKbnResultData[集計区分結果情報].List[[タブ別結果情報]]へ変数．List[[結果タブ別情報]]を設定します。
        skResultData.setListTabResult(listTabReault);
        //９．処理終了
    }
    /**
     * 画面レイアウト用情報作成処理
     * 
     * @param listKaikeiKbn List[[画面表示会計区分]]
     * @param targetTabNo
     * @param listBD31Data List[[BD31KKUP結果]]
     * @param mapGetMethod Map[Getterメソッド]
     * 
     * @return List[[会計区分修正情報]]
     * @throws Exception
     */
    private static List createScreenLayoutData(
            List listKcd, List listBD31Data, Map mapGetMethod
            )
    throws Exception
    {
    	
        //０．リターン値List[[会計区分修正情報]]を生成します。
        List listRevisedData = new ArrayList(0);
        //１．引数．List[[BD31KKUP結果]]の件数分、下記の処理を行います。
        for(int i=0; i<listBD31Data.size(); i++) {
            //for-1.現行インデックス位置のエンティティを取得します。
            //       引数．List[[BD31KKUP結果]]からTrnSyuseiAridaka[BD31KKUP結果]を取得します。
            TrnSyuseiAridaka eBD31Data = (TrnSyuseiAridaka)listBD31Data.get(i);
            //for-2.新規に変数.Entity[会計区分修正状況]を生成します。
            UIMeisaiReviseInf eRevised = new UIMeisaiReviseInf();
            eRevised.setTaishoName(eBD31Data.getMiseNameKj());
            eRevised.setEigyoDt(eBD31Data.getEigyoDt());
            //for-3.引数List[[画面表示会計区分]]件数分、変数.Entity[会計区分修正状況]に以下の値を設定します。
            for(int s=0; s<listKcd.size(); s++) {
                String kaikeiKbnCd = (String)listKcd.get(s);
                //for2-1.現行インデックスの引数List[[画面表示会計区分]][会計区分].会計区分コードをキーに
                //Map[Getterメソッド]、Map[修正Getterメソッド]からGetterMethod[Getterメソッド]を取得し、以下の値を取得します。
                GetterMethod gMethod = (GetterMethod)mapGetMethod.get(kaikeiKbnCd);
                //変数.dec[件数]へ GetterMethod[Getterメソッド].件数Methodを実行した値を設定します。
                BigDecimal decKen = (BigDecimal)gMethod.getMethodKen().invoke(eBD31Data, new Object[]{});
                //変数.dec[金額]へGetterMethod[Getterメソッド].金額Methodを実行した値を設定します。
                BigDecimal decKin = (BigDecimal)gMethod.getMethodKin().invoke(eBD31Data, new Object[]{});
                
                // (2)-1 変数.数値コードへ引数List[[画面表示会計区分]][会計区分].会計区分コードを数値変換した値を設定します。
                int kaikeiKbn = Integer.parseInt(kaikeiKbnCd);
                // (2)-2 変数.更新項目No範囲開始インデックスへ「計算式：(変数.数値コード-1)*2」を設定します。
                int startIndex = (kaikeiKbn-2)*2;
                // (2)-3 変数.更新項目No範囲終了インデックスへ「計算式：変数.更新項目No範囲開始インデックス+2」を設定します。
                int endIndex = startIndex+2;
                // (2)-4 TrnSyuseiAridaka[BD31KKUP結果].更新項目Noから
                //       変数更新項目No範囲開始インデックスから変数.更新項目No範囲終了インデックスの範囲の文字列を取得します。
                String upStr = (eBD31Data.getUpNo()).substring(startIndex,endIndex);                
                // (2)-5 変数.boolean[件数変更]へ処理(2)-4で取得したした文字列の1文字目="1"の場合はtrueを、そうでない場合はfalseを設定します。
                boolean kenUp = false;
                if(upStr.charAt(0) == '1') kenUp = true;
                // (2)-6 変数.boolean[金額変更]へ処理(2)-6で取得したした文字列の2文字目="1"の場合はtrueを、そうでない場合はfalseを設定します。	
                boolean kinUp = false;
                if(upStr.charAt(1) == '1')	kinUp = true;
                //for2-2.変数.Entity[会計区分修正状況]へfor2-1の各変数を設定します。
                //IF-1．処理for3の現行インデックス==0(ゼロ)の場合
                if(s==0) {
                    //変数.Entity[会計区分修正状況]．明細1存在フラグ　＝ TRUE
                    eRevised.setExistsMeisai1(true);
                    //変数.Entity[会計区分修正状況]．件数1修正フラグ　＝ 変数.boolean[件数変更]
                    eRevised.setRevisedKen1(kenUp);
                    //変数.Entity[会計区分修正状況]．件数1修正値　　　＝ 変数.dec[件数]
                    eRevised.setReviseKen1(decKen);
                    //変数.Entity[会計区分修正状況]．金額1修正フラグ　＝ 変更.boolean[金額変更]
                    eRevised.setRevisedKin1(kinUp);
                    //変数.Entity[会計区分修正状況]．金額1修正値　　　＝ 変数.dec[金額]
                    eRevised.setReviseKin1(decKin);
                }//end of if(s==0)
                //IF-1．処理for3の現行インデックス==1の場合
                else if(s==1) {
                    //変数.Entity[会計区分修正状況]．明細2存在フラグ　＝ TRUE
                    eRevised.setExistsMeisai2(true);
                    //変数.Entity[会計区分修正状況]．件数2修正フラグ　＝ 変数.boolean[件数変更]
                    eRevised.setRevisedKen2(kenUp);
                    //変数.Entity[会計区分修正状況]．件数2修正値　　　＝ 変数.dec[件数]
                    eRevised.setReviseKen2(decKen);
                    //変数.Entity[会計区分修正状況]．金額2修正フラグ　＝ 変更.boolean[金額変更]
                    eRevised.setRevisedKin2(kinUp);
                    //変数.Entity[会計区分修正状況]．金額2修正値　　　＝ 変数.dec[金額]
                    eRevised.setReviseKin2(decKin);
                }//end of if(s==1)
                //IF-1．処理for3の現行インデックス==2の場合
                else if(s==2) {
                    //変数.Entity[会計区分修正状況]．明細3存在フラグ　＝ TRUE
                    eRevised.setExistsMeisai3(true);
                    //変数.Entity[会計区分修正状況]．件数3修正フラグ　＝ 変数.boolean[件数変更]
                    eRevised.setRevisedKen3(kenUp);
                    //変数.Entity[会計区分修正状況]．件数3修正値　　　＝ 変数.dec[件数]
                    eRevised.setReviseKen3(decKen);
                    //変数.Entity[会計区分修正状況]．金額3修正フラグ　＝ 変更.boolean[金額変更]
                    eRevised.setRevisedKin3(kinUp);
                    //変数.Entity[会計区分修正状況]．金額3修正値　　　＝ 変数.dec[金額]
                    eRevised.setReviseKin3(decKin);
                }//end of if(s==2)
                //IF-1．処理for3の現行インデックス==3の場合
                else if(s==3) {
                    //変数.Entity[会計区分修正状況]．明細4存在フラグ　＝ TRUE
                    eRevised.setExistsMeisai4(true);
                    //変数.Entity[会計区分修正状況]．件数4修正フラグ　＝ 変数.boolean[件数変更]
                    eRevised.setRevisedKen4(kenUp);
                    //変数.Entity[会計区分修正状況]．件数4修正値　　　＝ 変数.dec[件数]
                    eRevised.setReviseKen4(decKen);
                    //変数.Entity[会計区分修正状況]．金額4修正フラグ　＝ 変更.boolean[金額変更]
                    eRevised.setRevisedKin4(kinUp);
                    //変数.Entity[会計区分修正状況]．金額4修正値　　　＝ 変数.dec[金額]
                    eRevised.setReviseKin4(decKin);
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
     * 日別合計値計算処理
     * 
     * @param syukeiKbnResultData
     * @return
     */
    public static List calculateDayTotal(List listTabResult) {
	    List listDayTotal = new ArrayList(0);
		UIUriMaintenanceResult uiResultData = (UIUriMaintenanceResult)listTabResult.get(0);
		//日数分下記の処理を行います。
		for(int i=0; i<uiResultData.getListData().size(); i++) {
			BigDecimal total[] = {new BigDecimal("0"), new BigDecimal("0")};
			//タブ分下記の処理を行います。
			for(int t=0; t<listTabResult.size(); t++) {
				UIUriMaintenanceResult uiTabResult = (UIUriMaintenanceResult)listTabResult.get(t);
				//for2-2.Map[タブ別
				UIMeisaiReviseInf eRevising = (UIMeisaiReviseInf)uiTabResult.getListData().get(i);
				total[0] = total[0].add(eRevising.getReviseKenTotal1_4());
				total[1] = total[1].add(eRevising.getReviseKinTotal1_4());
			}
			listDayTotal.add(total);
		}
		for(int i=0; i<listDayTotal.size(); i++) {
			BigDecimal total[] = (BigDecimal[])listDayTotal.get(i);
			for(int t=0; t<listTabResult.size(); t++) {
				UIUriMaintenanceResult uiTabResult = (UIUriMaintenanceResult)listTabResult.get(t);
				//for2-2.Map[タブ別
				UIMeisaiReviseInf eRevising = (UIMeisaiReviseInf)uiTabResult.getListData().get(i);
				eRevising.setReviseKenTotal(total[0]);
				eRevising.setReviseKinTotal(total[1]);
			}
		}
		return listDayTotal;
    }
    
    /**
     * 会計集計区分情報を取得する
     * @return ctlSyukeiKbnDao 会計集計区分情報
     */
    public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
        return ctlSyukeiKbnDao;
    }

    /**
     * 会計集計区分情報を設定する
     * @param ctlSyukeiKbnDao 会計集計区分情報
     */
    public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
        this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
    }

    /**
     * 会計区分情報を取得する
     * @return ctlKaikeiKbnInfoDao 会計区分情報
     */
    public CtlKaikeiKbnInfoDao getCtlKaikeiKbnInfoDao() {
        return ctlKaikeiKbnInfoDao;
    }

    /**
     * 会計区分情報を設定する
     * @param ctlKaikeiKbnInfoDao 会計区分情報
     */
    public void setCtlKaikeiKbnInfoDao(CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao) {
        this.ctlKaikeiKbnInfoDao = ctlKaikeiKbnInfoDao;
    }


    /**
     * 会計区分系共通定数クラスを取得する
     * @return kaikeiUtil 会計区分系共通定数クラス
     */
    public KaikeiUtil getKaikeiUtil() {
        return kaikeiUtil;
    }


    /**
     * 会計区分系共通定数クラスを設定する
     * @param kaikeiUtil 会計区分系共通定数クラス
     */
    public void setKaikeiUtil(KaikeiUtil kaikeiUtil) {
        this.kaikeiUtil = kaikeiUtil;
    }

    /**
     * 会計区分別在高日次修正Daoを戻します。
     * @return trnSyuseiAridakaDao 会計区分別在高日次修正Dao
     */
    public TrnSyuseiAridakaDao getTrnSyuseiAridakaDao() {
        return trnSyuseiAridakaDao;
    }

    /**
     * 会計区分別在高日次修正Daoを設定します。
     * @param trnSyuseiAridakaDao 会計区分別在高日次修正Dao
     */
    public void setTrnSyuseiAridakaDao(TrnSyuseiAridakaDao trnSyuseiAridakaDao) {
        this.trnSyuseiAridakaDao = trnSyuseiAridakaDao;
    }

}
