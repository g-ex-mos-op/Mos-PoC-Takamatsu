/*
 * 作成日: 2008/10/08
 *
 */
package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.UIOnerGepoAvgCSVDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dao.UIOnerGepoCSVDao;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.UIAvgUriage;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.UISuiiNipo;
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
 * @author xayumi
 */
public class OnerGepoCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID **********/
    public static final String LOGIC_ID = "BBR001L11";
    
    /* DAO ******************/
    private UIOnerGepoCSVDao groupEigyoNipoUIOnerGepoCSVDao;
    private UIOnerGepoAvgCSVDao groupEigyoNipoUIOnerGepoAvgCSVDao;
    
    /* Formatter ************/
    private NumericFormatter numFormatter1   = new NumericFormatter(true, 2);
    private NumericFormatter numFormatter0   = new NumericFormatter(true, 0, true);
    private DateFormatter    dateFormatterD = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日('E')'");
    
    private DateFormatter  mmdd = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_MMDD_SLASH);
    
    /* ログインユーザ情報 ***/
    private BirdUserInfo birdUserInfo;

    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "EIGYOGEP.csv";
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
            throw new NoResultException(EigyoNipoConstants.EMPTY);
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
//                listRow.add(dateFormatterMD.format(entity.getZenDoyoDt(), true));
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
        listHeader.add(listHeader1);
        
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("日付");
        listHeader2.add("天候");
        listHeader2.add("当日");
        listHeader2.add("累計");
        listHeader2.add("当日");
        listHeader2.add("予算比");
        listHeader2.add("累計");
        listHeader2.add("予算比");
        listHeader2.add("日付");
        listHeader2.add("天候");
        listHeader2.add("同曜日売上高");
        listHeader2.add("売上高累計");
        listHeader2.add("前年対比");
        listHeader2.add("当日");
        listHeader2.add("累計");
        listHeader2.add("同曜日客数");
        listHeader2.add("累計");
        listHeader2.add("前年対比");
        listHeader2.add("当日");
        listHeader2.add("前年同曜日");
        listHeader2.add("前年対比");
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
