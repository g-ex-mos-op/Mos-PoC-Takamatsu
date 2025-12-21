package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.UIOnerGepoAvgCSVDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.dao.UIOnerGepoCSVDao;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.UIAvgUriage;
import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.UISuiiNipo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * CSVダウンロードロジック
 * 
 * @author xyamauchi
 */
public class OnerGepoCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID **********/
    public static final String LOGIC_ID = "BBR015L11";
    
    /* DAO ******************/
    private UIOnerGepoCSVDao groupEigyoNipoUIOnerGepoCSVDao;
    private UIOnerGepoAvgCSVDao groupEigyoNipoUIOnerGepoAvgCSVDao;
    
    /* Formatter ************/
    private NumericFormatter numFormatter1   = new NumericFormatter(true, 1);
    private NumericFormatter numFormatter0   = new NumericFormatter(true, 0);
    private DateFormatter    dateFormatterD = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日('E')'");
    
    private DateFormatter  mmdd = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_MMDD_SLASH);
    
    /* ログインユーザ情報 ***/
    private BirdUserInfo birdUserInfo;

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return MoscardNipoConstants.CSV_FILE_NAME_GEP;
	}

    public List getOutputData(CsvOutputDto csvOutputDto) {
        NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;
        List listCsvData;
        
        listCsvData = getNoInputData(dto);        
        return listCsvData;
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ

    }
    
    private List getNoInputData(NipoRefConditionParameterDto dto) {
        List listCsvData = new ArrayList();
        
        String companyCd = dto.getCompanyCd();
        String onerCd    = getOnerCD(dto);
        
        //対象年月
        String taishoYM = dto.getKikanGepoYM();
        
        //【LOGIC】検索処理(アベレージ売上一覧)
        List listAvgData = 
            getGroupEigyoNipoUIOnerGepoAvgCSVDao().selectAvg(companyCd, onerCd, taishoYM);
                
        //【LOGIC】検索処理(売上推移表一覧)
        List listSuiData = 
            getGroupEigyoNipoUIOnerGepoCSVDao().selectSuiNipo(companyCd, onerCd, taishoYM);
         
        //店一覧取得
        List miseList = makeMiseList(listAvgData);
        
        
        if ((miseList == null || miseList.isEmpty())
                && (listAvgData == null || listAvgData.isEmpty())
                && (listSuiData == null || listSuiData.isEmpty())) 
        {
            throw new NoResultException(MoscardNipoConstants.EMPTY);
        }
            
        
        for (Iterator ite = miseList.iterator(); ite.hasNext();) {
            UIAvgUriage e = (UIAvgUriage) ite.next();
            
            String miseCd = e.getMiseCd(); 
            
            //検索条件行作成
            listCsvData.addAll(makeSearchCond(e, taishoYM));
            //空行追加
            listCsvData.add(new ArrayList(0));
            //アベレージ売上一覧ヘッダ作成
            listCsvData.addAll(makeHeaderAvg());
            //アベレージ売上一覧データ作成
            listCsvData.addAll(makeDataAvg(listAvgData, miseCd));
            //空行追加
            listCsvData.add(new ArrayList(0));
            //売上推移表一覧ヘッダ作成
            listCsvData.addAll(makeHeaderSui());
            //売上推移表一覧データ作成
            listCsvData.addAll(makeDataSui(listSuiData, miseCd));  
            //空行追加
            listCsvData.add(new ArrayList(0));
            
            ite.remove();
        }        
        return listCsvData;
    }

    
    /**
     * アベレージ売上一覧データリスト作成
     */
    private List makeDataAvg(List listResult, String miseCd) {
        List listData = new ArrayList();
        
        for ( int i = 0; i < listResult.size(); i++ ) {
            UIAvgUriage entity = (UIAvgUriage) listResult.get(i);
            List listRow = new ArrayList();
            
            if(entity.getMiseCd().equals(miseCd)){
                listRow.add(entity.getThreeName());
                listRow.add(numFormatter0.format(entity.getUriageAvg()));
                listRow.add(numFormatter0.format(entity.getUriageZenDogetuAvg()));
                listRow.add(numFormatter1.format(entity.getUriageZennenhi()));
                
                listRow.add(numFormatter0.format(entity.getKyakusuAvg()));
                listRow.add(numFormatter0.format(entity.getKyakusuZenDogetuAvg()));              
                listRow.add(numFormatter1.format(entity.getKyakusuZennenhi()));  

                listRow.add(numFormatter0.format(entity.getKyakutanka()));  
                listRow.add(numFormatter0.format(entity.getKyakutankaZenDogetu()));
                listRow.add(numFormatter1.format(entity.getKyakutankaZenDogetuhi()));         
                
                listRow.add(numFormatter0.format(entity.getEigyoDaysSum()));
                listRow.add(numFormatter0.format(entity.getEigyoDaysZenDogetuSum()));
                
                listRow.add(numFormatter0.format(entity.getIssueCntAvg()));
                listRow.add(numFormatter0.format(entity.getIssueCntZenDogetuAvg()));
                listRow.add(numFormatter1.format(entity.getIssueCntZennenhi()));                

                listRow.add(numFormatter0.format(entity.getChargeKinAvg()));
                listRow.add(numFormatter0.format(entity.getChargeKinZenDogetuAvg()));
                listRow.add(numFormatter1.format(entity.getChargeKinZennenhi()));
                
                listRow.add(numFormatter0.format(entity.getChargeKenAvg()));
                listRow.add(numFormatter0.format(entity.getChargeKenZenDogetuAvg()));
                listRow.add(numFormatter1.format(entity.getChargeKenZennenhi()));
                
                listRow.add(numFormatter0.format(entity.getChargeTanka()));
                listRow.add(numFormatter0.format(entity.getChargeTankaZenDogetu()));
                listRow.add(numFormatter1.format(entity.getChargeTankaZenDogetuhi()));
                
                listRow.add(numFormatter0.format(entity.getKessaiKinAvg()));
                listRow.add(numFormatter0.format(entity.getKessaiKinZenDogetuAvg()));
                listRow.add(numFormatter1.format(entity.getKessaiKinZennenhi()));
                
                listRow.add(numFormatter0.format(entity.getKessaiKenAvg()));
                listRow.add(numFormatter0.format(entity.getKessaiKenZenDogetuAvg()));
                listRow.add(numFormatter1.format(entity.getKessaiKenZennenhi()));
                
                listRow.add(numFormatter0.format(entity.getKessaiTanka()));
                listRow.add(numFormatter0.format(entity.getKessaiTankaZenDogetu()));
                listRow.add(numFormatter1.format(entity.getKessaiTankaZenDogetuhi()));
                
                listData.add(listRow);                
            }
        }
        
        return listData;
    }   
    
    /**
     * 店一覧リスト作成
     */
    private List makeMiseList(List listResult) {
        List miseList = new ArrayList();

        for ( int i = 0; i < listResult.size(); i++ ) {
            UIAvgUriage entity = (UIAvgUriage) listResult.get(i);
            
            if(entity.getThreeKbn().equals("9")){
                
                miseList.add(entity);
            }
        }
        return miseList;
    }
    
    /**
     * 売上推移表一覧データリスト作成
     */
    private List makeDataSui(List listResult, String miseCd) {
        List listData = new ArrayList();

        for ( int i = 0; i < listResult.size(); i++ ) {
            UISuiiNipo entity = (UISuiiNipo) listResult.get(i);
            
            if(entity.getMiseCd().equals(miseCd)){
            
                List listRow = new ArrayList();
                listRow.add(dateFormatterD.format(entity.getEigyoDt(), true));
                listRow.add(numFormatter0.format(entity.getTenkoKbnKj()));
                listRow.add(numFormatter0.format(entity.getUriage()));
                listRow.add(numFormatter0.format(entity.getUriageRui()));
                listRow.add(numFormatter0.format(entity.getYosan()));
                listRow.add(numFormatter1.format(entity.getYosanhi()));
                listRow.add(numFormatter0.format(entity.getYosanRui()));
                listRow.add(numFormatter1.format(entity.getYosanhiRui()));
                listRow.add(mmdd.format(entity.getZenDoyoDt(), true));
                listRow.add(entity.getTenkoKbnZenDoyoKj());            
                listRow.add(numFormatter0.format(entity.getUriageZenDoyo()));
                listRow.add(numFormatter0.format(entity.getUriageZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getUriageZennenhiRui()));
                
                listRow.add(numFormatter0.format(entity.getKyakusu()));
                listRow.add(numFormatter0.format(entity.getKyakusuRui()));
                listRow.add(numFormatter0.format(entity.getKyakusuZenDoyo()));
                listRow.add(numFormatter0.format(entity.getKyakusuZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getKyakusuZennenhiRui()));
                listRow.add(numFormatter0.format(entity.getKyakutanka()));
                listRow.add(numFormatter0.format(entity.getKyakutankaZenDoyo() ));
                listRow.add(numFormatter1.format(entity.getKyakutankaZenDoyonenhi()));
                //発行枚数
                listRow.add(numFormatter0.format(entity.getIssueCnt()));
                listRow.add(numFormatter0.format(entity.getIssueCntRui()));
                listRow.add(numFormatter0.format(entity.getIssueCntZenDoyo()));
                listRow.add(numFormatter0.format(entity.getIssueCntZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getIssueCntZennenhiRui()));
                //チャージ金額
                listRow.add(numFormatter0.format(entity.getChargeKin()));
                listRow.add(numFormatter1.format(entity.getChargeKinUriagehi()));
                listRow.add(numFormatter0.format(entity.getChargeKinRui()));
                listRow.add(numFormatter0.format(entity.getChargeKinUriagehiRui()));
                listRow.add(numFormatter0.format(entity.getChargeKinZenDoyo()));
                listRow.add(numFormatter0.format(entity.getChargeKinZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getChargeKinZenuriagehi()));
                //チャージ件数
                listRow.add(numFormatter0.format(entity.getChargeKen()));
                listRow.add(numFormatter1.format(entity.getChargeKenKyakusuhi()));
                listRow.add(numFormatter0.format(entity.getChargeKenRui()));
                listRow.add(numFormatter0.format(entity.getChargeKenKyakusuhiRui()));
                listRow.add(numFormatter0.format(entity.getChargeKenZenDoyo()));
                listRow.add(numFormatter0.format(entity.getChargeKenZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getChargeKenZenKyakusuhi()));
                //チャージ単価
                listRow.add(numFormatter0.format(entity.getChargeTanka()));
                listRow.add(numFormatter0.format(entity.getChargeTankaZenDoyo()));
                listRow.add(numFormatter1.format(entity.getChargeTankaZenhi())); 
                //決済金額
                listRow.add(numFormatter0.format(entity.getKessaiKin()));
                listRow.add(numFormatter1.format(entity.getKessaiKinUriagehi()));
                listRow.add(numFormatter0.format(entity.getKessaiKinRui()));
                listRow.add(numFormatter1.format(entity.getKessaiKinUriagehiRui()));
                listRow.add(numFormatter0.format(entity.getKessaiKinZenDoyo()));
                listRow.add(numFormatter0.format(entity.getKessaiKinZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getKessaiKinZenUriagehi()));
                //決済件数
                listRow.add(numFormatter0.format(entity.getKessaiKen()));
                listRow.add(numFormatter1.format(entity.getKessaiKenKyakusuhi()));
                listRow.add(numFormatter0.format(entity.getKessaiKenRui()));
                listRow.add(numFormatter1.format(entity.getKessaiKenKyakusuhiRui()));
                listRow.add(numFormatter0.format(entity.getKessaiKenZenDoyo()));
                listRow.add(numFormatter0.format(entity.getKessaiKenZenDojituRui()));
                listRow.add(numFormatter1.format(entity.getKessaiKenZenKyakusuhi()));
                //決済単価
                listRow.add(numFormatter0.format(entity.getKessaiTanka()));
                listRow.add(numFormatter1.format(entity.getKessaiTankaTankahi()));
                listRow.add(numFormatter0.format(entity.getKessaiTankaZenDoyo()));
                listRow.add(numFormatter1.format(entity.getKessaiTankaZenhi()));
                
//                listRow.add(numFormatter0.format(entity.getChargeKinCancel()));
//                listRow.add(numFormatter0.format(entity.getChargeKinCancelRui()));
//                listRow.add(numFormatter0.format(entity.getChargeKenCancel()));
//                listRow.add(numFormatter0.format(entity.getChargeKenCancelRui()));
//                listRow.add(numFormatter0.format(entity.getUseKinCancel()));
//                listRow.add(numFormatter0.format(entity.getUseKinCancelRui()));
//                listRow.add(numFormatter0.format(entity.getUseKenCancel()));
//                listRow.add(numFormatter0.format(entity.getUseKenCancelRui()));
//                listRow.add(numFormatter0.format(entity.getBonusVIssue()));
//                listRow.add(numFormatter0.format(entity.getBonusVIssueRui()));
//                listRow.add(numFormatter0.format(entity.getBonusVUse()));
//                listRow.add(numFormatter0.format(entity.getBonusVUseRui()));
//                listRow.add(numFormatter0.format(entity.getCouponVIssue()));
//                listRow.add(numFormatter0.format(entity.getCouponVIssueRui()));
//                listRow.add(numFormatter0.format(entity.getCouponVUse()));
//                listRow.add(numFormatter0.format(entity.getCouponVUseRui()));
//                listRow.add(numFormatter0.format(entity.getZandaka()));
                
                listData.add(listRow);
            }
        }
  
        return listData;
    }
    
    
    /**
     * 検索条件行リスト作成
     * @param dto
     * @return
     */
    private List makeSearchCond(UIAvgUriage entity, String taishoYM) {
        List listSearchCond = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, "yyyy'年'MM'月度'");

        //1行目
        List listHeader1 = new ArrayList();
        listHeader1.add("");
        listHeader1.add("対象店舗");
        listHeader1.add(entity.getMiseCd() + "号店");
        listHeader1.add(entity.getMiseNameKj());
        listSearchCond.add(listHeader1);
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("");
        listHeader2.add("対象期間");
        listHeader2.add(dateFormatter.format(taishoYM, true));
        listSearchCond.add(listHeader2);
        
        return listSearchCond;
        
    }
    
    
    /**
     * アベレージ売上一覧ヘッダリスト作成
     * @return
     */
    private List makeHeaderAvg() {
        List listHeader = new ArrayList();
        
        //1行目
        List listHeader1 = new ArrayList();
        listHeader1.add("");
        listHeader1.add("アベレージ売上高");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("客数");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("客単価");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("日数");
        listHeader1.add("");
        listHeader1.add("アベレージ発行枚数");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("アベレージチャージ金額");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ件数");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ単価");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("アベレージ決済金額");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("決済件数");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("決済単価");
        listHeader1.add("");
        listHeader1.add("");
        listHeader.add(listHeader1);
        
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader2.add("当月");
        listHeader2.add("前年同月");
        listHeader2.add("伸率");
        listHeader.add(listHeader2);
        
        return listHeader;
    }

    /**
     * 売上推移表一覧ヘッダリスト作成
     * @return
     */
    private List makeHeaderSui() {
        List listHeader = new ArrayList();
        
        //1行目
        List listHeader1 = new ArrayList();
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("売上高");
        listHeader1.add("");
        listHeader1.add("売上高予算対比");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("売上高前年値");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("客数");
        listHeader1.add("");
        listHeader1.add("客数前年値");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("客単価");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("発行枚数");
        listHeader1.add("");
        listHeader1.add("発行枚数前年値");        
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ金額");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ金額前年値");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ件数");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ件数前年値");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("チャージ単価");
        listHeader1.add("チャージ単価前年値");
        listHeader1.add("");
        listHeader1.add("決済金額");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("決済金額前年値");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("決済件数");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("決済件数前年値");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("決済単価");
        listHeader1.add("");
        listHeader1.add("決済単価前年値");
        listHeader1.add("");
//        listHeader1.add("入金取消金額");
//        listHeader1.add("");
//        listHeader1.add("入金取消件数");
//        listHeader1.add("");
//        listHeader1.add("利用取消金額");
//        listHeader1.add("");
//        listHeader1.add("利用取消件数");
//        listHeader1.add("");
//        listHeader1.add("発行ボーナスバリュー");
//        listHeader1.add("");
//        listHeader1.add("利用ボーナスバリュー");
//        listHeader1.add("");
//        listHeader1.add("発行クーポンバリュー");
//        listHeader1.add("");
//        listHeader1.add("利用クーポンバリュー");
//        listHeader1.add("");
//        listHeader1.add("");
        listHeader.add(listHeader1);
        
        /** データ部：当日*/
        String LABEL_DAY = "当日";
        String LABEL_RUI = "累計";
        String LABEL_ZENSHU = "同曜日";
        String LABEL_ZENNENHI = "前年対比";
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("日付");
        listHeader2.add("天候");
        listHeader2.add(LABEL_DAY);
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_DAY);
        listHeader2.add("予算比");
        listHeader2.add(LABEL_RUI);
        listHeader2.add("予算比");
        listHeader2.add("日付");
        listHeader2.add("天候");
        listHeader2.add(LABEL_ZENSHU+"売上高");
        listHeader2.add("売上高累計");
        listHeader2.add(LABEL_ZENNENHI);
        listHeader2.add(LABEL_DAY);
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENSHU+"客数");
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENNENHI);
        listHeader2.add(LABEL_DAY);
        listHeader2.add("前年同曜日");
        listHeader2.add(LABEL_ZENNENHI);
        //発行枚数
        listHeader2.add(LABEL_DAY);
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENSHU+"発行枚数");
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENNENHI);
        //ﾁｬｰｼﾞ金額
        listHeader2.add(LABEL_DAY);
        listHeader2.add("売上比");
        listHeader2.add(LABEL_RUI);
        listHeader2.add("売上比"+LABEL_RUI);
        listHeader2.add(LABEL_ZENSHU+"チャージ金額");
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENNENHI);
        //ﾁｬｰｼﾞ件数
        listHeader2.add(LABEL_DAY);
        listHeader2.add("客数比");
        listHeader2.add(LABEL_RUI);
        listHeader2.add("客数比"+LABEL_RUI);
        listHeader2.add(LABEL_ZENSHU+"チャージ件数");
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENNENHI);
        //チャージ単価
        listHeader2.add(LABEL_DAY);
        listHeader2.add(LABEL_ZENSHU+"チャージ単価");
        listHeader2.add(LABEL_ZENNENHI);
        //決済金額
        listHeader2.add(LABEL_DAY);
        listHeader2.add("売上比");
        listHeader2.add(LABEL_RUI);
        listHeader2.add("売上比"+LABEL_RUI);
        listHeader2.add(LABEL_ZENSHU+"決済金額");
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENNENHI);
        //決済件数
        listHeader2.add(LABEL_DAY);
        listHeader2.add("客数比");
        listHeader2.add(LABEL_RUI);
        listHeader2.add("客数比"+LABEL_RUI);
        listHeader2.add(LABEL_ZENSHU+"決済件数");
        listHeader2.add(LABEL_RUI);
        listHeader2.add(LABEL_ZENNENHI);
        //決済単価
        listHeader2.add(LABEL_DAY);
        listHeader2.add("単価比");
        listHeader2.add(LABEL_ZENSHU+"決済単価");
        listHeader2.add(LABEL_ZENNENHI);
//        //入金取消金額
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //入金取消件数
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //利用取消金額
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //利用取消件数
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //発行バリューボーナス
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //利用バリューボーナス
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //発行クーポンボーナス
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //利用クーポンボーナス
//        listHeader2.add(LABEL_DAY);
//        listHeader2.add(LABEL_RUI);
//        //前受残高
//        listHeader2.add("前受残高");
        
        listHeader.add(listHeader2);
        
        return listHeader;
    }
    
    
    /**
     * オーナーコード取得処理
     * @param dto
     * @return
     */
    private String getOnerCD(NipoRefConditionParameterDto dto){
        String companyCd = dto.getCompanyCd();
        
        // オーナーコード取得            
        String onerCd = null;
        List ownerList = dto.getBirdUserInfo().getUserOner();
        for (Iterator it = ownerList.iterator(); it.hasNext();) {
            UIUserOner uIUserOner = (UIUserOner) it.next();
            if (companyCd.equals(uIUserOner.getCompanyCd())) {
                onerCd = uIUserOner.getOnerCd();
                break;
            }
        }
        return onerCd;
    }


    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public UIOnerGepoCSVDao getGroupEigyoNipoUIOnerGepoCSVDao() {
        return groupEigyoNipoUIOnerGepoCSVDao;
    }

    public void setGroupEigyoNipoUIOnerGepoCSVDao(
            UIOnerGepoCSVDao groupEigyoNipoUIOnerGepoCSVDao) {
        this.groupEigyoNipoUIOnerGepoCSVDao = groupEigyoNipoUIOnerGepoCSVDao;
    }

    public UIOnerGepoAvgCSVDao getGroupEigyoNipoUIOnerGepoAvgCSVDao() {
        return groupEigyoNipoUIOnerGepoAvgCSVDao;
    }

    public void setGroupEigyoNipoUIOnerGepoAvgCSVDao(
            UIOnerGepoAvgCSVDao groupEigyoNipoUIOnerGepoAvgCSVDao) {
        this.groupEigyoNipoUIOnerGepoAvgCSVDao = groupEigyoNipoUIOnerGepoAvgCSVDao;
    }
}
