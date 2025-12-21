/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.CodMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.MstSvOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.UIOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.UIPlFCDataInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.UIPlRCDataInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.dto.PlLumpExtractConditionDto;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.CodMiseInfo;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.MstSVOnerInfo;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIOnerInfo;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIPlDataInfo;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIPlRCDataInfo;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class PlLumpExtractCsvOutputLogicImpl implements CsvOutputLogic {

    /* ロジックID */    
    public static final String LOGIC_ID = "BBS007L03";
    
    /* ファイル名*/
    private String fc_file_name = "PLALL.csv";
    private String rc_file_name = "PLCHOKU.csv";
    
    /* 年月 */
    private static int NENGETSU_DISPLAY_MONTH = 13;
    
    // Dao
    /* MstSvOnerInfoDao */
    private MstSvOnerInfoDao mstSvOnerInfoDao;   
    /* UIOnerInfoDao */
    private UIOnerInfoDao uIOnerInfoDao;    
    /* UIPlRcDataInfo */
    private UIPlRCDataInfoDao uIPlRcDataInfoDao;
    /*  CodMiseInfoDao */
    private CodMiseInfoDao codMiseInfoDao;
    /* UIPlDataInfoDao*/
    private UIPlFCDataInfoDao uIPlFCDataInfoDao;
    
    
    // String
    // 構成比情報
    private String kouseiAri = "need";
    
    // 年月情報
    // 対象月
    private String target="target";
    // 期間指定
    private String period="period";
    
    // 売上高合計項目番号
    private String uriGou = "003";
    
    // 構成比　or　金額
    // 金額
    private String money ="money";
   
    // ArrayList
    // FC項目毎の可変長配列
    //オーナー：行
    private List ownerList = new ArrayList();
     //対象店舗：行
    private List shopList = new ArrayList();
     //単位：行
    private List creditList = new ArrayList();
     //null行
    private List nullList = new ArrayList();
     //項目行
    private List itemList = new ArrayList();
     //売上高行
    private List sellList = new ArrayList();
     //物販売上高行
    private List buppanList = new ArrayList();
     //売上合計行
    private List totalList = new ArrayList();
     //前年売上高行
    private List lastSellList = new ArrayList();
     //前年比(%)行
    private List sellHiList = new ArrayList();
     //原材料原価計行
    private List genzaiGenkaList = new ArrayList();
     //野菜原価計行
    private List yasaiGenkaList = new ArrayList();
     //包装資材原価計行
    private List housouSizaiList = new ArrayList();
     //物販原価計行
    private List buppanGenkaKeiList = new ArrayList();
     //売上原価計行
    private List uriageGenkaKeiList = new ArrayList();
     //売上総利益行
    private List uriageJunList = new ArrayList();
     //役員報酬行
    private List yakuinHoushuList = new ArrayList();
     //給料手当行
    private List salaryList = new ArrayList();
     //雑給行
    private List zakkyuList = new ArrayList();
     //賞与行
    private List shoyoList = new ArrayList();
     //人件費計行
    private List zinkenList = new ArrayList();
     //家賃地代行
    private List yachinList = new ArrayList();
     //水道光熱費行
    private List suikouList = new ArrayList();
     //ロイヤルティ行
    private List royList = new ArrayList();
     //支払手数料行
    private List tesuList = new ArrayList();
     //広告費行
    private List koukokuList = new ArrayList();
     //消耗品費行
    private List shoumouList = new ArrayList();
     //法定福利費行
    private List houteiList = new ArrayList();
     //福利厚生費行
    private List hukuriList = new ArrayList();
     //交際費行
    private List kousaiList = new ArrayList();
     //旅費交通費行
    private List ryohiList = new ArrayList();
     //通信費行
    private List tuusinList = new ArrayList();
     //賃借リース料行
    private List leaseList = new ArrayList();
     //車輌費行
    private List sharyoList = new ArrayList();
     //租税公課行
    private List sozeiList = new ArrayList();
     //保険料行
    private List hokenList = new ArrayList();
     //運賃行
    private List unchinList = new ArrayList();
     //修繕費行
    private List shuuzenList = new ArrayList();
     //雑費行
    private List zappiList = new ArrayList();
     //経費小計行
    private List keihiList = new ArrayList();
     //償却前利益行
    private List maeriekiList = new ArrayList();
     //減価償却費行
    private List shokyakuList = new ArrayList();
     //営業外収益行
    private List gaishuuekiList = new ArrayList();
     //営業外費用行
    private List gaihiyouList = new ArrayList();
     //共通費配賦行
    private List haihuList = new ArrayList();
     //税引前利益行
    private List zeimaeList = new ArrayList();
     //［資金繰りの計算］行
    private List sikinHeadList = new ArrayList();
     //当月利益行
    private List touriekiList = new ArrayList();
     //減価償却費行
    private List shokyakuhiList = new ArrayList();
     //キャッシュフロー行
    private List cashList = new ArrayList();
     //借入返済行
    private List kariireList = new ArrayList();
     //割賦支払行
    private List kappuList = new ArrayList();
     //支払合計行
    private List siharaikeiList = new ArrayList();
     //差引余剰金行
    private List yojouList = new ArrayList();
     //［当月残高］行
    private List zanHeadList = new ArrayList();
     //借入金残高行
    private List kariirezanList = new ArrayList();
     //リース残高行
    private List leasezanList = new ArrayList();
     //割賦残高行
    private List kappuzanList = new ArrayList();
     //借入金等残高行
    private List kariireetczanList = new ArrayList();
     
    /**
     * mstSvOnerInfoDao
     * @return
     */
    public MstSvOnerInfoDao getMstSvOnerInfoDao() {
    	return this.mstSvOnerInfoDao;
    }
    
    /**
     * mstSvOnerInfoDao
     * @param mstSvOnerInfoDao
     */
    public void setMstSvOnerInfoDao( MstSvOnerInfoDao mstSvOnerInfoDao ) {
    	this.mstSvOnerInfoDao = mstSvOnerInfoDao;
    }
    
    /**
     * uIOnerInfoDao
     * @return
     */
    public UIOnerInfoDao getUIOnerInfoDao() {
    	return this.uIOnerInfoDao;
    }
    
       /**
     * uIOnerInfoDao
     * @param uIOnerInfoDao
     */
    public void setUIOnerInfoDao( UIOnerInfoDao uIOnerInfoDao ) {
        this.uIOnerInfoDao = uIOnerInfoDao;
    }
    
    /**
     * uIPlRCDataInfo
     * @return
     */
    public UIPlRCDataInfoDao getUIPlRCDataInfoDao() {
    	return this.uIPlRcDataInfoDao;
    }
    
    /**
     * uIPlRCDataInfo
     * @param uIPlRcDataInfoDao
     */
    public void setUIPlRcDataInfoDao( UIPlRCDataInfoDao uIPlRcDataInfoDao) {
    	this.uIPlRcDataInfoDao = uIPlRcDataInfoDao;
    }
    
    /**
     * codMiseInfoDao
     * @return
     */
    public CodMiseInfoDao getCodMiseInfoDao() {
    	return this.codMiseInfoDao;
    }
    
    /**
     * codMiseInfoDao
     * @param codMiseInfoDao
     */
    public void setCodMiseInfoDao( CodMiseInfoDao codMiseInfoDao) {
    	this.codMiseInfoDao = codMiseInfoDao;
    }
    
    /**
     * uIPlDataInfoDao
     * @return
     */
    public UIPlFCDataInfoDao getUIPlFCDataInfoDao() {
    	return this.uIPlFCDataInfoDao;
    }
    
    /**
     *uIPlDatainfoDao 
     * @param uIPlDataInfoDao
     */
    public void setUIPlDataInfoDao( UIPlFCDataInfoDao uIPlFCDataInfoDao ) {
    	this.uIPlFCDataInfoDao = uIPlFCDataInfoDao;
    }
    
    /**
     * リストのクリア
     *
     */
    public void clearList() {
        ownerList = new ArrayList();
        shopList = new ArrayList();
        creditList = new ArrayList();
        nullList = new ArrayList();
        itemList = new ArrayList();
        sellList = new ArrayList();
        buppanList = new ArrayList();
        totalList = new ArrayList();
        lastSellList = new ArrayList();
        sellHiList = new ArrayList();
        genzaiGenkaList = new ArrayList();
        yasaiGenkaList = new ArrayList();
        housouSizaiList = new ArrayList();
        buppanGenkaKeiList = new ArrayList();
        uriageGenkaKeiList = new ArrayList();
        uriageJunList = new ArrayList();
        yakuinHoushuList = new ArrayList();
        salaryList = new ArrayList();
        zakkyuList = new ArrayList();
        shoyoList = new ArrayList();
        zinkenList = new ArrayList();
        yachinList = new ArrayList();
        suikouList = new ArrayList();
        royList = new ArrayList();
        tesuList = new ArrayList();
        koukokuList = new ArrayList();
        shoumouList = new ArrayList();
        houteiList = new ArrayList();
        hukuriList = new ArrayList();
        kousaiList = new ArrayList();
        ryohiList = new ArrayList();
        tuusinList = new ArrayList();
        leaseList = new ArrayList();
        sharyoList = new ArrayList();
        sozeiList = new ArrayList();
        hokenList = new ArrayList();
        unchinList = new ArrayList();
        shuuzenList = new ArrayList();
        zappiList = new ArrayList();
        keihiList = new ArrayList();
        maeriekiList = new ArrayList();
        shokyakuList = new ArrayList();
        gaishuuekiList = new ArrayList();
        gaihiyouList = new ArrayList();
        haihuList = new ArrayList();
        zeimaeList = new ArrayList();
        sikinHeadList = new ArrayList();
        touriekiList = new ArrayList();
        shokyakuhiList = new ArrayList();
        cashList = new ArrayList();
        kariireList = new ArrayList();
        kappuList = new ArrayList();
        siharaikeiList = new ArrayList();
        yojouList = new ArrayList();
        zanHeadList = new ArrayList();
        kariirezanList = new ArrayList();
        leasezanList = new ArrayList();
        kappuzanList = new ArrayList();
        kariireetczanList = new ArrayList();
    }
    /* FCモード縦リスト(金額) */
    protected static final String[] ROW_STR_KIN = new String[] {
            "オーナー：",
            "対象店舗：",
            "単位：",
            "",
            "項目",
            "売上高",
            "物販売上高",
            "売上合計",
            "前年売上高",
            "前年比(%)",
            "原材料原価計",
            "野菜原価計",
            "包装資材原価計",
            "物販原価計",
            "売上原価計",
            "売上総利益",
            "役員報酬",
            "給料手当",
            "雑給",
// change start xkhata 20060824 名称変更
//            "賞与",
            "賞与・退職金",
// change end
            "人件費計",
            "家賃地代",
            "水道光熱費",
            "ロイヤルティ",
            "支払手数料",
            "広告費",
            "消耗品費",
            "法定福利費",
            "福利厚生費",
            "交際費",
            "旅費交通費",
            "通信費",
            "賃借リース料",
            "車輌費",
            "租税公課",
            "保険料",
            "運賃",
            "修繕費",
            "雑費",
            "経費小計",
            "償却前利益",
            "減価償却費",
            "営業外収益",
            "営業外費用",
            "共通費配賦",
            "税引前利益",
            "［資金繰りの計算］",
            "当月利益",
            "減価償却費",
            "キャッシュフロー",
            "借入返済",
            "割賦支払",
            "支払合計",
            "差引余剰金",
            "［当月残高］",
            "借入金残高",
            "リース残高",
            "割賦残高",
            "借入金等残高",
    };

    // RCモード横リスト
    public String[] RC_COL_LIST = new String[] {
            "直営売上高",
            "受取手数料",
            "売上高合計",
            "期首商品棚卸高",
            "商品仕入高",
            "内部仕入高",
            "期末商品棚卸高",
            "売上原価",
            "売上総利益",
            "給料手当",
            "時間外手当",
            "雑給",
            "賞与",
            "法定福利費",
            "人件費計",
            "広告宣伝費",
            "販売促進費",
            "運賃",
            "福利厚生費",
            "採用費",
            "消耗品費",
            "家賃地代",
            "水道光熱費",
            "修繕費",
            "保険料",
            "旅費交通費",
            "通勤費",
            "通信費",
            "交際費",
            "会議費",
            "諸会費",
            "寄付金",
            "支払手数料",
            "賃貸料",
            "租税公課",
            "研究開発費",
            "新聞図書費",
            "減価償却費",
            "長期前払費用償却",
            "現金過不足",
            "のれん償却",//<--2010/04/13 CHANGE 名前変更
            "資産除去債務費用",//<--2010/04/13 ADD 項目追加
            "雑費",
            "小計",
            "販売管理費合計",
            "償却前営業利益",
            "営業利益",
            "負ののれん償却",//<--2010/04/13 ADD 項目追加
            "賃貸収入",//<--2010/04/13 ADD 項目追加
            "雑収入",
            "営業外収益計",//<--2010/04/13 ADD 項目追加
            "雑損失",
            "営業外費用計",//<--2010/04/13 ADD 項目追加
            "経常利益",
            "固定資産売却益",
            "特別利益計",//<--2010/04/13 ADD 項目追加
            "固定資産除売却損",
            "特別損失計",//<--2010/04/13 ADD 項目追加
            "当期純利益",
    };
	    
    /**
     * RC_HEADER_LIST作成
     * @return
     */
    public List getRCHeaderList( String dataType) {

        /*  rcKIngakuHedList */
        List rcKingakuHedList = new ArrayList();
        
        boolean spaceFlg = false;
        
        rcKingakuHedList.add("");
        rcKingakuHedList.add("");

        if ( dataType.equals(kouseiAri) ) {
            spaceFlg = true;
        }
        
        for( int i = 0; i < RC_COL_LIST.length; i++ ) {
        	rcKingakuHedList.add( RC_COL_LIST[i] );
            if ( spaceFlg ) {
            	rcKingakuHedList.add("");
            }
        }
        
        return rcKingakuHedList;
    }

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		// TODO 自動生成されたメソッド・スタブ
        PlLumpExtractConditionDto plLumpDto = (PlLumpExtractConditionDto)csvOutputDto;
        
        if ( plLumpDto.getPlMode().equals("0") ) {
        	return fc_file_name;
        } else {
            return rc_file_name;
        }
	}

    /**
     * RCモードヘッダー作成
     * @param csvOutputDto
     * @return List
     */
    protected List getRCHeaderData(CsvOutputDto csvOutputDto) {
    	List headerList = new ArrayList();
        DateFormatter formatter = new DateFormatter();
        
        PlLumpExtractConditionDto dto = (PlLumpExtractConditionDto)csvOutputDto;
        
        // 支部名称追加
        List sibuList = new ArrayList();
        for ( int i = 0; i < dto.getRcList().size() ;i++ ) {
           SelectItem sibu = (SelectItem)dto.getRcList().get(i);
           if ( dto.getRcStr().equals( sibu.getValue() ) ) {
           	    sibuList.add("支部：");
                sibuList.add(sibu.getValue() + " " +   (String)sibu.getLabel().trim() );
                break;
            }
        }
        
        // 対象期間追加
        List pList = new ArrayList();
        pList.add("対象期間：");
        if ( dto.getMonthTypeRc().equals( period ) )  {
             String fromStr = formatter.format(
                    dto.getPeriodMonthFromStrRc(), DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM );
             String toStr = formatter.format(
                    dto.getPeriodMonthToStrRc(), DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM );
             
        	pList.add(fromStr + " から " + toStr );
        } else {
        	pList.add( formatter.format(
                    dto.getTargetMonthStrRc(),DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM)  );
        }
        
        // 単位追加
        List creditList = new ArrayList();
        creditList.add("単位：");
        creditList.add("円");
        
        // カラム(上)項目追加
        List columnTopList = getRCHeaderList(dto.getDataTypeRc() );
        
        // カラム(下)項目追加
        List columnBottomLIst = new ArrayList();
        columnBottomLIst.add("店コード");
        columnBottomLIst.add("店名称");
        
        for ( int i = 0; i < RC_COL_LIST.length; i++ ) {
            columnBottomLIst.add("金額");
        	if ( dto.getDataTypeRc().equals( kouseiAri ) ) {
                columnBottomLIst.add("構成比");
            }
        }
        
        headerList.add(sibuList);
        headerList.add(pList);
        headerList.add(creditList);
        headerList.add(columnTopList);
        headerList.add(columnBottomLIst);
        
        return headerList;
        
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

        List outputcsvList = new ArrayList();
        PlLumpExtractConditionDto dto = (PlLumpExtractConditionDto)csvOutputDto;
        clearList();
        
        // FCモード
        if ( dto.getPlMode().equals( "0") ){
            
            Map dtMap = getDtMap( csvOutputDto );
            
            String fromDt = (String)dtMap.get("fromDt");
            String toDt = (String)dtMap.get("toDt");
            String totalToDt = (String)dtMap.get("totalToDt");
            String totalFromDt = (String)dtMap.get("totalFromDt");
            
            List koumokuDtList = (List)dtMap.get("koumokuDtList");
            
            String zenToDt = (String)dtMap.get("zenToDt");
            String zenFromDt = (String)dtMap.get("zenFromDt");
            String zenToTotalDt = (String)dtMap.get("zenToTotalDt");
            String zenFromTotalDt = (String)dtMap.get("zenFromTotalDt");
            
            String outDt = (String)dtMap.get("outDt");
            String zenOutDt = (String)dtMap.get("zenOutDt");
            
            // 業態区分リスト
            List gyotaiList = new ArrayList();
            // モスバーガー店舗
            gyotaiList.add(new String("010") );
           // 緑モス
            gyotaiList.add( new String("013"));

            // 対象オーナーの店を取得
            List miseList = getCodMiseInfoDao().getOwnerMiseInfo( dto.getCompanyCd(),dto.getOwnerCd(),fromDt, toDt );
            // 対象オーナーのPL情報を取得
            List dataList = getUIPlFCDataInfoDao().getPlFCData( dto.getCompanyCd(), dto.getOwnerCd(), fromDt, toDt);
// add start xkhata 対象データがなかったときの対応
            if( dataList == null || dataList.size() == 0 ) {
                throw new NoResultException();
            }
// end
            // 全店平均用リスト
            List totalList = new ArrayList();
            List zentenTotalList = new ArrayList();
            // 前年取得用リスト
            List zenDataList = new ArrayList();
            List zenTotalList = new ArrayList();
                        
            // データが金額のとき
            if ( dto.getDataTypeFc().equals( money ) ) {
                zenDataList = getUIPlFCDataInfoDao().getPlFCData(dto.getCompanyCd(),dto.getOwnerCd(),zenFromDt,zenToDt);
            }
            
            // ユーザタイプが本部のとき
            if ( UserType.isHonbu(dto.getUserInfoKbn()) ) {
                if ( !dto.getLimitFlg() ) {
                	totalList = getUIPlFCDataInfoDao().getPlFCTotalData( dto.getCompanyCd(),totalFromDt,totalToDt,gyotaiList);
                    zenTotalList = getUIPlFCDataInfoDao().getPlFCTotalData( dto.getCompanyCd(),zenFromTotalDt,zenToTotalDt,gyotaiList);
                    zentenTotalList = getZentenGou( totalList,zenTotalList,csvOutputDto,zenOutDt);
                }
            }
            
            // 店リストの先頭に本社(オーナーの店情報を追加)
            UIOnerInfo uIOnerInfo = (UIOnerInfo)dto.getOnerList().get(0);
            CodMiseInfo ownerInfo = new CodMiseInfo();
            ownerInfo.setMiseCd( uIOnerInfo.getOnerCd() );
            ownerInfo.setMiseNameKj( "本社" );
            ownerInfo.setOpenDt("");
            miseList.add( 0, ownerInfo);
            
            for ( int i = 0; i < miseList.size(); i++ ) {
            	CodMiseInfo codMise = (CodMiseInfo)miseList.get(i);
                clearList();
                List kbtList = new ArrayList();
                List zenKbtList = new ArrayList();
                
                int count = 0;
                
                // 期間内の対象店舗のPLデータの抽出
                for ( int j = 0; j < dataList.size(); j++ ) {
                    UIPlDataInfo uip = (UIPlDataInfo)dataList.get(j);
                	if ( codMise.getMiseCd().equals( uip.getMiseCd() ) ) {
                		kbtList.add( uip);
                        if ( !uip.getPlYm().equals( outDt) ) {
                            count++;
                        }
                    }
                }
                
                int zenCount = 0;
                
                // 前年期間内の対象店舗のPLデータの抽出
                for ( int j = 0; j < zenDataList.size(); j++ ) {
                	UIPlDataInfo uip = (UIPlDataInfo)zenDataList.get(j);
                    if ( codMise.getMiseCd().equals( uip.getMiseCd() ) ) {
                    	zenKbtList.add(uip);
                        if ( !uip.getPlYm().equals( zenOutDt) ) {
                            zenCount++;
                        }
                    }
                }
                // 抽出したデータを下に、出力のフォーマットに合わせる
                Map map = new HashMap();
                map.put("kdtList",kbtList);
                map.put("zentenTotalList",zentenTotalList);
                map.put("koumokuDtList",koumokuDtList);
                map.put("zenDataList",zenKbtList);
                map.put("csv",csvOutputDto);
                map.put("cod",codMise);
                map.put("outDt",outDt);
                map.put("count",new Integer(count) );
                map.put("zenCount", new Integer(zenCount) );
                map.put("touDataSize", new Integer(totalList.size()) );
                map.put("zenDataSize", new Integer(zenTotalList.size()) );
                
                List fcList = setPlFcDataList(map);
                
                for ( int j = 0; j < fcList.size(); j++ ) {
                	outputcsvList.add( fcList.get(j) );
                }
                
            }
        // 直営モード
        } else {
            List headList = getRCHeaderData( csvOutputDto);
            
            for ( int i = 0; i < headList.size(); i++ ) {
            	outputcsvList.add( headList.get(i) ) ;
            }
            String fromDt = new String();
            
            if ( dto.getMonthTypeRc().equals( period ) ) {
            	fromDt = dto.getPeriodMonthFromStrRc();
            } else {
            	fromDt = dto.getTargetMonthStrRc();
            }
            
            String toDt = new String();
            
            if( dto.getMonthTypeRc().equals( period ) ) {
                toDt = dto.getPeriodMonthToStrRc();
            } else {
            	toDt = dto.getTargetMonthStrRc();
            }
            
            List miseList = getCodMiseInfoDao().getMiseInfo( dto.getCompanyCd(), dto.getRcStr().trim(), fromDt, toDt );
            List rcDataList = getUIPlRCDataInfoDao().getPlRCDataInfo( dto.getCompanyCd(),dto.getRcStr().trim(),fromDt,toDt);
            
            List rcList = setPlRcList( rcDataList,miseList,dto);
            
            for ( int i = 0; i < rcList.size(); i++ ) {
            	outputcsvList.add( rcList.get(i) );
            }
            
        }
		return outputcsvList;
	}


    /**
     * FCPLデータ作成処理
     * @param Map
     *         kdtList
     *         zentenTotalList
     *         koumokuDtList
     *         zenDataList
     *         csv
     *         cod
     *         outDt
     *         count
     *         zenCount
     *         touDataSize
     *         zenDataSize
     * @return
     */
    public List setPlFcDataList( Map map ) {

        List kdtList = (List)map.get("kdtList");
        List zentenTotalList = (List)map.get("zentenTotalList");
        List koumokuDtList = (List)map.get("koumokuDtList");
        List zenDataList = (List)map.get("zenDataList");
        CsvOutputDto csv = (CsvOutputDto)map.get("csv");
        CodMiseInfo cod = (CodMiseInfo)map.get("cod");
        String outDt = (String)map.get("outDt");
        int count = ( (Integer)map.get("count") ).intValue();
        int zenCount = ( (Integer)map.get("zenCount")).intValue();
        int touDataSize = ( (Integer)map.get("touDataSize")).intValue();
        int zenDataSize = ( (Integer)map.get("zenDataSize")).intValue();
        
        List plDataList = new ArrayList();
        PlLumpExtractConditionDto dto = (PlLumpExtractConditionDto)csv;
        UIOnerInfo oner = (UIOnerInfo)dto.getOnerList().get(0);
        String openDt = "オープン日：";
        DateFormatter formatter = new DateFormatter();
        
        // オーナー行
        ownerList.add( ROW_STR_KIN[0] );
        ownerList.add(oner.getOnerCd() + " "  + oner.getOnerNameKj().trim() );
        ownerList.add("");
        ownerList.add( openDt );
        ownerList.add( 
                formatter.format(cod.getOpenDt(),DateFormatter.PATTERN_KANJI_YMD,DateFormatter.DATE_TYPE_YMD) );
        plDataList.add( ownerList );
        
        // 対象店舗行
        shopList.add( ROW_STR_KIN[1] );
        shopList.add( cod.getMiseCd() + " " + cod.getMiseNameKj().trim() );
        // 対象条件(2012/01/20追加)
        shopList.add("");
        shopList.add( "対象条件：" );
        String taishoJoken = "";
        // 期間指定時(YYYY年MM月～YYYY年MM月)
        if ( dto.getMonthTypeFc().equals( period ) ) {
        	taishoJoken = changeYMKanji(dto.getPeriodMonthFromStr()) + " ～ " + changeYMKanji(dto.getPeriodMonthToStr());
        }
        // 対象月指定時(YYYY年MM月)
        else if ( dto.getMonthTypeFc().equals( target ) ){
        	taishoJoken =changeYMKanji(dto.getTargetMonthStr());
        }
        // 決算月時(当年度または前年度で表記)
        else {
        	String tonen = (String) ((SelectItem) dto.getClosingMonthList().get(0)).getValue();
        	taishoJoken = (tonen.equals(dto.getClosingMonthStr())?"当":"前")+"年度";
        }
        shopList.add(taishoJoken);
        plDataList.add( shopList );
        
        // 単位行
        creditList.add( ROW_STR_KIN[2] );
        if ( dto.getDataTypeFc().equals( money ) ) {
        	creditList.add( "円");
        } else {
        	creditList.add("％");
        }
        plDataList.add( creditList );
        
        // null行
        plDataList.add( nullList );
        
        // 項目行
        itemList.add(ROW_STR_KIN[4]);
        for ( int i = 0; i < koumokuDtList.size(); i++ ) {
        	itemList.add(
                    formatter.format(
                            (String)koumokuDtList.get( koumokuDtList.size() - i - 1) ,DateFormatter.PATTERN_MONTH_KANJI_YM,DateFormatter.DATE_TYPE_YM));
        }
        
        // 項目追加
    	sellList.add(ROW_STR_KIN[5]);
        buppanList.add(ROW_STR_KIN[6]);
        totalList.add( ROW_STR_KIN[7]);
        lastSellList.add( ROW_STR_KIN[8]);
        sellHiList.add(ROW_STR_KIN[9]);
        genzaiGenkaList.add(ROW_STR_KIN[10]);
        yasaiGenkaList.add(ROW_STR_KIN[11]);
        housouSizaiList.add(ROW_STR_KIN[12]);
        buppanGenkaKeiList.add(ROW_STR_KIN[13]);
        uriageGenkaKeiList.add(ROW_STR_KIN[14]);
        uriageJunList.add(ROW_STR_KIN[15]);
        yakuinHoushuList.add(ROW_STR_KIN[16]);
        salaryList.add( ROW_STR_KIN[17]);
        zakkyuList.add(ROW_STR_KIN[18]);
        shoyoList.add( ROW_STR_KIN[19]);
        zinkenList.add(ROW_STR_KIN[20]);
        yachinList.add( ROW_STR_KIN[21]);
        suikouList.add(ROW_STR_KIN[22]);
        royList.add( ROW_STR_KIN[23]);
        tesuList.add(ROW_STR_KIN[24]);
        koukokuList.add(ROW_STR_KIN[25]);
        shoumouList.add(ROW_STR_KIN[26]);
        houteiList.add(ROW_STR_KIN[27]);
        hukuriList.add(ROW_STR_KIN[28]);
        kousaiList.add(ROW_STR_KIN[29]);
        ryohiList.add(ROW_STR_KIN[30]);
        tuusinList.add(ROW_STR_KIN[31]);
        leaseList.add(ROW_STR_KIN[32]);
        sharyoList.add(ROW_STR_KIN[33]);
        sozeiList.add(ROW_STR_KIN[34]);
        hokenList.add(ROW_STR_KIN[35]);
        unchinList.add(ROW_STR_KIN[36]);
        shuuzenList.add(ROW_STR_KIN[37]);
        zappiList.add(ROW_STR_KIN[38]);
        keihiList.add(ROW_STR_KIN[39]);
        maeriekiList.add(ROW_STR_KIN[40]);
        shokyakuList.add(ROW_STR_KIN[41]);
        gaishuuekiList.add(ROW_STR_KIN[42]);
        gaihiyouList.add(ROW_STR_KIN[43]);
        haihuList.add(ROW_STR_KIN[44]);
        zeimaeList.add(ROW_STR_KIN[45]);
        
        sikinHeadList.add(ROW_STR_KIN[46]);
        touriekiList.add(ROW_STR_KIN[47]);
        shokyakuhiList.add(ROW_STR_KIN[48]);
        cashList.add(ROW_STR_KIN[49]);
        kariireList.add(ROW_STR_KIN[50]);
        kappuList.add(ROW_STR_KIN[51]);
        siharaikeiList.add(ROW_STR_KIN[52]);
        yojouList.add(ROW_STR_KIN[53]);
        
        zanHeadList.add(ROW_STR_KIN[54]);
        kariirezanList.add(ROW_STR_KIN[55]);
        leasezanList.add(ROW_STR_KIN[56]);
        kappuzanList.add(ROW_STR_KIN[57]);
        kariireetczanList.add(ROW_STR_KIN[58]);

        NumericFormatter num = new NumericFormatter();
        
        // 金額のとき
        if ( dto.getDataTypeFc().equals( money ) )  {
            for ( int i = 0; i < koumokuDtList.size(); i++ ) {
                String plYm = (String)koumokuDtList.get(koumokuDtList.size() - i - 1);
                boolean existFlg = false;
                            
            	for ( int j = 0; j < kdtList.size(); j++ ) {
                    UIPlDataInfo uip = (UIPlDataInfo)kdtList.get(j);
            		if (plYm.equals(uip.getPlYm() ) ) {
                        
                        BigDecimal zenUriGou = new BigDecimal(0);;
                        BigDecimal zenUriHi = new BigDecimal(0);;
                        
                        for ( int k = 0; k < zenDataList.size(); k++ ) {
                            UIPlDataInfo zenUip = (UIPlDataInfo)zenDataList.get(k);
                            
                            String zenPlYM = new String();
                            try{
                            	zenPlYM = DateManager.getPrevMonth(plYm,12);
                            }catch (Exception ex) {
                            	throw new FtlSystemException("年月取得処理");
                            }
                            if ( zenUip.getPlYm().equals( zenPlYM) ) {
                            	zenUriGou = new BigDecimal( zenUip.getUriagedaka().intValue());
                                if ( zenUriGou.intValue() != 0 ) {
                                    zenUriHi = Calculator.percentage( uip.getUriagedaka(),zenUriGou,2);                                    
                                }
                            }
                        }
    
                        // 売上高行
                        sellList.add(num.format( uip.getUriage().toString(), "##,###,###,##0" ));
                        buppanList.add(num.format(uip.getBuppan().toString(),"##,###,###,##0" ));
                        totalList.add(num.format(uip.getUriagedaka().toString(),"##,###,###,##0" ));
                        
                        lastSellList.add( num.format(zenUriGou.toString(), "##,###,###,##0" ) );
                        sellHiList.add( zenUriHi );
                                            
                        genzaiGenkaList.add(num.format(uip.getGenzairyoKei().toString(),"##,###,###,##0" ));
                        yasaiGenkaList.add(num.format(uip.getYasaiKei().toString(),"##,###,###,##0" ));
                        housouSizaiList.add(num.format(uip.getHouzaiKei().toString(),"##,###,###,##0" ));
                        buppanGenkaKeiList.add(num.format(uip.getBuppanKei().toString(), "##,###,###,##0" ));
                        uriageGenkaKeiList.add(num.format(uip.getUriagegenka().toString(), "##,###,###,##0" ));
                        uriageJunList.add(num.format(uip.getUriageSoRieki().toString(), "##,###,###,##0" ));
                        yakuinHoushuList.add(num.format(uip.getYakuinSalary().toString(), "##,###,###,##0" ));
                        salaryList.add(num.format(uip.getSalarySalary().toString(), "##,###,###,##0" ));
                        zakkyuList.add(num.format(uip.getZakkyuSalary().toString(), "##,###,###,##0" ));
                        shoyoList.add(num.format(uip.getBonusKei().add(uip.getRetireKei() ).toString(), "##,###,###,##0" ));
                        zinkenList.add(num.format(uip.getSalary().toString(), "##,###,###,##0" ));
                        yachinList.add(num.format(uip.getYachin().toString(), "##,###,###,##0" ));
                        suikouList.add(num.format(uip.getSuikouHi().toString(), "##,###,###,##0" ));
                        royList.add(num.format(uip.getRoyalty().toString(), "##,###,###,##0" ));
                        tesuList.add(num.format(uip.getTesuryo().toString(),"##,###,###,##0"));;
                        koukokuList.add(num.format(uip.getKoukoku().toString(), "##,###,###,##0" ));
                        shoumouList.add(num.format(uip.getShoumou().toString(), "##,###,###,##0" ));
                        houteiList.add(num.format(uip.getHouteiFukuri().toString(), "##,###,###,##0"));;
                        hukuriList.add(num.format(uip.getFukuriKousei().toString(), "##,###,###,##0" ));
                        kousaiList.add(num.format(uip.getKousai().toString(), "##,###,###,##0" ));
                        ryohiList.add(num.format(uip.getRyohi().toString(), "##,###,###,##0" ));
                        tuusinList.add(num.format(uip.getTusin().toString(), "##,###,###,##0"));;
                        leaseList.add(num.format(uip.getLease().toString(), "##,###,###,##0" ));
                        sharyoList.add(num.format(uip.getSharyo().toString(), "##,###,###,##0"));;
                        sozeiList.add(num.format(uip.getSozei().toString(), "##,###,###,##0" ));
                        hokenList.add(num.format(uip.getHoken().toString(), "##,###,###,##0"));;
                        unchinList.add(num.format(uip.getUnchin().toString(), "##,###,###,##0" ));
                        shuuzenList.add(num.format(uip.getShuzen().toString(), "##,###,###,##0" ));
                        zappiList.add(num.format(uip.getZappi().toString(), "##,###,###,##0" ));
                        keihiList.add(num.format(uip.getKeihiShokei().toString(), "##,###,###,##0" ));
                        maeriekiList.add(num.format(uip.getShokyakuRieki().toString(), "##,###,###,##0" ));
                        shokyakuList.add(num.format(uip.getGenkaShokyaku().toString(), "##,###,###,##0" ));
                        gaishuuekiList.add(num.format(uip.getEigaiShueki().toString(), "##,###,###,##0" ));
                        gaihiyouList.add(num.format(uip.getEigaiHiyo().toString(), "##,###,###,##0" ));
                        haihuList.add(num.format(uip.getHonshahiHai().toString(), "##,###,###,##0" ));
                        zeimaeList.add(num.format(uip.getRieki().toString(), "##,###,###,##0" ));
                        sikinHeadList.add("");
    
                        int rieki = uip.getShokyakuRieki().intValue() 
                                     - uip.getGenkaShokyaku().intValue()
                                     + uip.getEigaiShueki().intValue()
                                     - uip.getEigaiHiyo().intValue()
                                     - uip.getHonshahiHai().intValue();
                                     
                        touriekiList.add(num.format(String.valueOf(rieki), "##,###,###,##0" ));
    
                        shokyakuhiList.add(num.format(uip.getGenkaShokyaku().toString(),"##,###,###,##0" ));
                        BigDecimal cash = new BigDecimal( String.valueOf( rieki ) );
                        cash = cash.add( uip.getGenkaShokyaku() );
    
                        cashList.add(num.format(cash.toString(),"##,###,###,##0" ));
    
                        kariireList.add(num.format(uip.getKashiireDown().toString(),"##,###,###,##0" ));
    
                        kappuList.add(num.format(uip.getKappuDown().toString(),"##,###,###,##0" ));
                        siharaikeiList.add(num.format(uip.getTouGenshoKei().toString(),"##,###,###,##0" ));
    
                        BigDecimal sasi = cash;
                        sasi = sasi.subtract( uip.getKashiireDown() );
                        sasi = sasi.subtract( uip.getKappuDown() );
                        
                        yojouList.add(num.format(sasi.toString(),"##,###,###,##0" ));
                        
                        zanHeadList.add("");
                        
                        kariirezanList.add(num.format(uip.getKashiireZandaka().toString(),"##,###,###,##0" ));
                        leasezanList.add(num.format(uip.getLeaseZandaka().toString(),"##,###,###,##0" ));
                        kappuzanList.add(num.format(uip.getKappuZandaka().toString(),"##,###,###,##0" ));
                        kariireetczanList.add(num.format(uip.getTouZandakaKei().toString(), "##,###,###,##0" ));
    
            			existFlg = true;
                        break;
                    }
                }
                
                if ( existFlg == false ) {
                    
                    BigDecimal zenUriGou = new BigDecimal(0);
                    BigDecimal zenUriHi = new BigDecimal(0.0);
                    String zenPlYm = new String();
                    
                    for ( int k = 0; k < zenDataList.size(); k++ ) {
                        UIPlDataInfo zenUip = (UIPlDataInfo)zenDataList.get(k);
                        
                        try{
                        	zenPlYm = DateManager.getPrevMonth(plYm,12);
                        } catch (Exception ex) {
                                throw new FtlSystemException("年月取得処理");
                        }
                        
                        if ( zenUip.getPlYm().equals( zenPlYm) ) {
                            zenUriGou =  zenUip.getUriagedaka();
                        }
                        
                    }
                    
                    sellList.add(new BigDecimal(0));
                    buppanList.add(new BigDecimal(0));
                    totalList.add(new BigDecimal(0));
                    lastSellList.add( num.format(zenUriGou.toString(),"##,###,###,##0"));
                    sellHiList.add(num.format(zenUriHi.toString(),"##,###,###,##0"));
                    genzaiGenkaList.add(new BigDecimal(0));
                    yasaiGenkaList.add(new BigDecimal(0));
                    housouSizaiList.add(new BigDecimal(0));
                    buppanGenkaKeiList.add(new BigDecimal(0));
                    uriageGenkaKeiList.add(new BigDecimal(0));
                    uriageJunList.add(new BigDecimal(0));
                    yakuinHoushuList.add(new BigDecimal(0));
                    salaryList.add(new BigDecimal(0));
                    zakkyuList.add(new BigDecimal(0));
                    shoyoList.add(new BigDecimal(0));
                    zinkenList.add(new BigDecimal(0));
                    yachinList.add(new BigDecimal(0));
                    suikouList.add(new BigDecimal(0));
                    royList.add(new BigDecimal(0));
                    tesuList.add(new BigDecimal(0));
                    koukokuList.add(new BigDecimal(0));
                    shoumouList.add(new BigDecimal(0));
                    houteiList.add(new BigDecimal(0));
                    hukuriList.add(new BigDecimal(0));
                    kousaiList.add(new BigDecimal(0));
                    ryohiList.add(new BigDecimal(0));
                    tuusinList.add(new BigDecimal(0));
                    leaseList.add(new BigDecimal(0));
                    sharyoList.add(new BigDecimal(0));
                    sozeiList.add(new BigDecimal(0));
                    hokenList.add(new BigDecimal(0));
                    unchinList.add(new BigDecimal(0));
                    shuuzenList.add(new BigDecimal(0));
                    zappiList.add(new BigDecimal(0));
                    keihiList.add(new BigDecimal(0));
                    maeriekiList.add(new BigDecimal(0));
                    shokyakuList.add(new BigDecimal(0));
                    gaishuuekiList.add(new BigDecimal(0));
                    gaihiyouList.add(new BigDecimal(0));
                    haihuList.add(new BigDecimal(0));
                    zeimaeList.add(new BigDecimal(0));
                    
                    sikinHeadList.add("");
                    touriekiList.add(new BigDecimal(0));
                    shokyakuhiList.add(new BigDecimal(0));
                    cashList.add(new BigDecimal(0));
                    kariireList.add(new BigDecimal(0));
                    kappuList.add(new BigDecimal(0));
                    siharaikeiList.add(new BigDecimal(0));
                    yojouList.add(new BigDecimal(0));
                    
                    zanHeadList.add("");
                    kariirezanList.add(new BigDecimal(0));
                    leasezanList.add(new BigDecimal(0));
                    kappuzanList.add(new BigDecimal(0));
                    kariireetczanList.add(new BigDecimal(0));
                }
            }
            
            // 合計
            List zenNenGou = getZentenGou( kdtList,zenDataList,csv,outDt);
                      
            int monthCount = koumokuDtList.size();
            
            if ( monthCount == 13 ) {
            	monthCount--;
            }
            itemList.add( monthCount  + "ヶ月合計");
            setTotalList(zenNenGou);
            
            itemList.add("各月平均");
            setHeikinList( zenNenGou, count, zenCount);

            boolean localFlg = false;
            
            if ( UserType.isHonbu(dto.getUserInfoKbn()) ) {
                if ( !dto.getLimitFlg() ) {
                    localFlg = true;
                }
            }
            

            if ( localFlg ) {
                itemList.add( "全店平均");
                setHeikinList(zentenTotalList,touDataSize,zenDataSize);
            }
        // 構成比のとき
        } else {
            for ( int i = 0; i < koumokuDtList.size(); i++ ) {
                String plYm = (String)koumokuDtList.get(koumokuDtList.size() - i - 1);
                boolean existFlg = false;
                for ( int j = 0; j < kdtList.size(); j++ ) {
                    UIPlDataInfo uip = (UIPlDataInfo)kdtList.get(j);
                    if (plYm.equals(uip.getPlYm() ) ) {
                        
                        BigDecimal uriGou = uip.getUriagedaka();

                        // 売上高行
                        sellList.add(Calculator.percentage(uip.getUriage(),uriGou,2));
                        buppanList.add(Calculator.percentage(uip.getBuppan(),uriGou,2));
                        totalList.add(Calculator.percentage(uip.getUriagedaka(),uriGou,2));

                        genzaiGenkaList.add(Calculator.percentage(uip.getGenzairyoKei(),uriGou,2));
                        yasaiGenkaList.add(Calculator.percentage(uip.getYasaiKei(),uriGou,2));
                        housouSizaiList.add(Calculator.percentage(uip.getHouzaiKei(),uriGou,2));
                        buppanGenkaKeiList.add(Calculator.percentage(uip.getBuppanKei() ,uriGou,2));
                        uriageGenkaKeiList.add(Calculator.percentage(uip.getUriagegenka() ,uriGou,2));
                        uriageJunList.add(Calculator.percentage(uip.getUriageSoRieki() ,uriGou,2));
                        yakuinHoushuList.add(Calculator.percentage(uip.getYakuinSalary() ,uriGou,2));
                        salaryList.add(Calculator.percentage(uip.getSalarySalary() ,uriGou,2));
                        zakkyuList.add(Calculator.percentage(uip.getZakkyuSalary() ,uriGou,2));
                        shoyoList.add(Calculator.percentage(uip.getBonusKei().add(uip.getRetireKei() ),uriGou,2) );
                        zinkenList.add(Calculator.percentage(uip.getSalary() ,uriGou,2));
                        yachinList.add(Calculator.percentage(uip.getYachin() ,uriGou,2));
                        suikouList.add(Calculator.percentage(uip.getSuikouHi() ,uriGou,2));
                        royList.add(Calculator.percentage(uip.getRoyalty() ,uriGou,2));
                        tesuList.add(Calculator.percentage(uip.getTesuryo(),uriGou,2));
                        koukokuList.add(Calculator.percentage(uip.getKoukoku() ,uriGou,2));
                        shoumouList.add(Calculator.percentage(uip.getShoumou() ,uriGou,2));
                        houteiList.add(Calculator.percentage(uip.getHouteiFukuri() ,uriGou,2));
                        hukuriList.add(Calculator.percentage(uip.getFukuriKousei() ,uriGou,2));
                        kousaiList.add(Calculator.percentage(uip.getKousai() ,uriGou,2));
                        ryohiList.add(Calculator.percentage(uip.getRyohi() ,uriGou,2));
                        tuusinList.add(Calculator.percentage(uip.getTusin() ,uriGou,2));
                        leaseList.add(Calculator.percentage(uip.getLease() ,uriGou,2));
                        sharyoList.add(Calculator.percentage(uip.getSharyo() ,uriGou,2));
                        sozeiList.add(Calculator.percentage(uip.getSozei() ,uriGou,2));
                        hokenList.add(Calculator.percentage(uip.getHoken() ,uriGou,2));
                        unchinList.add(Calculator.percentage(uip.getUnchin() ,uriGou,2));
                        shuuzenList.add(Calculator.percentage(uip.getShuzen() ,uriGou,2));
                        zappiList.add(Calculator.percentage(uip.getZappi() ,uriGou,2));
                        keihiList.add(Calculator.percentage(uip.getKeihiShokei() ,uriGou,2));
                        maeriekiList.add(Calculator.percentage(uip.getShokyakuRieki() ,uriGou,2));
                        shokyakuList.add(Calculator.percentage(uip.getGenkaShokyaku() ,uriGou,2));
                        gaishuuekiList.add(Calculator.percentage(uip.getEigaiShueki() ,uriGou,2));
                        gaihiyouList.add(Calculator.percentage(uip.getEigaiHiyo() ,uriGou,2));
                        haihuList.add(Calculator.percentage(uip.getHonshahiHai() ,uriGou,2));
                        zeimaeList.add(Calculator.percentage(uip.getRieki() ,uriGou,2));
                        sikinHeadList.add("");
    
                        int rieki = uip.getShokyakuRieki().intValue() 
                                     - uip.getGenkaShokyaku().intValue()
                                     + uip.getEigaiShueki().intValue()
                                     - uip.getEigaiHiyo().intValue()
                                     - uip.getHonshahiHai().intValue();
                                     
                        touriekiList.add( Calculator.percentage(new BigDecimal( String.valueOf(rieki) ) ,uriGou,2));
    
                        shokyakuhiList.add(Calculator.percentage(uip.getGenkaShokyaku(),uriGou,2));
                        BigDecimal cash = new BigDecimal( String.valueOf( rieki ) );
                        cash.add( uip.getGenkaShokyaku() );
    
                        cashList.add(Calculator.percentage(cash,uriGou,2));
    
                        kariireList.add(Calculator.percentage(uip.getKashiireDown(),uriGou,2));
    
                        kappuList.add(Calculator.percentage(uip.getKappuDown(),uriGou,2));
                        siharaikeiList.add(Calculator.percentage(uip.getTouGenshoKei(),uriGou,2));
    
                        BigDecimal sasi = cash;
                        sasi.subtract( uip.getKashiireDown() );
                        sasi.subtract( uip.getKappuDown() );
                        
                        yojouList.add(Calculator.percentage(sasi,uriGou,2));
                        
                        zanHeadList.add("");
                        
                        kariirezanList.add(Calculator.percentage(uip.getKashiireZandaka(),uriGou,2));
                        leasezanList.add(Calculator.percentage(uip.getLeaseZandaka(),uriGou,2));
                        kappuzanList.add(Calculator.percentage(uip.getKappuZandaka(),uriGou,2));
                        kariireetczanList.add(Calculator.percentage(uip.getTouZandakaKei() ,uriGou,2));
    
                        existFlg = true;
                        break;
                    }
                }
                
                if ( existFlg == false ) {
                    sellList.add(new BigDecimal(0.0));
                    buppanList.add(new BigDecimal(0.0));
                    totalList.add(new BigDecimal(0.0));
                    genzaiGenkaList.add(new BigDecimal(0.0));
                    yasaiGenkaList.add(new BigDecimal(0.0));
                    housouSizaiList.add(new BigDecimal(0.0));
                    buppanGenkaKeiList.add(new BigDecimal(0.0));
                    uriageGenkaKeiList.add(new BigDecimal(0.0));
                    uriageJunList.add(new BigDecimal(0.0));
                    yakuinHoushuList.add(new BigDecimal(0.0));
                    salaryList.add(new BigDecimal(0.0));
                    zakkyuList.add(new BigDecimal(0.0));
                    shoyoList.add(new BigDecimal(0.0));
                    zinkenList.add(new BigDecimal(0.0));
                    yachinList.add(new BigDecimal(0.0));
                    suikouList.add(new BigDecimal(0.0));
                    royList.add(new BigDecimal(0.0));
                    tesuList.add(new BigDecimal(0.0));
                    koukokuList.add(new BigDecimal(0.0));
                    shoumouList.add(new BigDecimal(0.0));
                    houteiList.add(new BigDecimal(0.0));
                    hukuriList.add(new BigDecimal(0.0));
                    kousaiList.add(new BigDecimal(0.0));
                    ryohiList.add(new BigDecimal(0.0));
                    tuusinList.add(new BigDecimal(0.0));
                    leaseList.add(new BigDecimal(0.0));
                    sharyoList.add(new BigDecimal(0.0));
                    sozeiList.add(new BigDecimal(0.0));
                    hokenList.add(new BigDecimal(0.0));
                    unchinList.add(new BigDecimal(0.0));
                    shuuzenList.add(new BigDecimal(0.0));
                    zappiList.add(new BigDecimal(0.0));
                    keihiList.add(new BigDecimal(0.0));
                    maeriekiList.add(new BigDecimal(0.0));
                    shokyakuList.add(new BigDecimal(0.0));
                    gaishuuekiList.add(new BigDecimal(0.0));
                    gaihiyouList.add(new BigDecimal(0.0));
                    haihuList.add(new BigDecimal(0.0));
                    zeimaeList.add(new BigDecimal(0.0));
                    
                    sikinHeadList.add("");
                    touriekiList.add(new BigDecimal(0.0));
                    shokyakuhiList.add(new BigDecimal(0.0));
                    cashList.add(new BigDecimal(0.0));
                    kariireList.add(new BigDecimal(0.0));
                    kappuList.add(new BigDecimal(0.0));
                    siharaikeiList.add(new BigDecimal(0.0));
                    yojouList.add(new BigDecimal(0.0));
                    
                    zanHeadList.add("");
                    kariirezanList.add(new BigDecimal(0.0));
                    leasezanList.add(new BigDecimal(0.0));
                    kappuzanList.add(new BigDecimal(0.0));
                    kariireetczanList.add(new BigDecimal(0.0));   
                }
            }
            
            // 合計
            List zenNenGou = getZentenGou( kdtList,zenDataList,csv,outDt);
                      
            int monthCount = koumokuDtList.size();
            
            if ( monthCount == 13 ) {
                monthCount--;
            }
            
            itemList.add( monthCount  + "ヶ月合計");
            setTotalKoumokuList(zenNenGou);
                       
            itemList.add("各月平均");
            setHeikinKoumokuList( zenNenGou, count);

            boolean localFlg = false;
            
            if ( UserType.isHonbu(dto.getUserInfoKbn()) ) {
                if ( !dto.getLimitFlg() ) {
                    localFlg = true;
                }
            }
            
            if ( localFlg ) {
                itemList.add( "全店平均");
                setHeikinKoumokuList(zentenTotalList,touDataSize);
            }

        }
        
        // 出力
        plDataList.add(itemList);
        plDataList.add(sellList);
        plDataList.add(buppanList);
        plDataList.add(totalList);
        
        if ( dto.getDataTypeFc().equals( money ) ) {
        	plDataList.add(lastSellList);
        	plDataList.add(sellHiList);
        }
        
        plDataList.add(genzaiGenkaList);
        plDataList.add(yasaiGenkaList);
        plDataList.add(housouSizaiList);
        plDataList.add(buppanGenkaKeiList);
        plDataList.add(uriageGenkaKeiList);
        plDataList.add(uriageJunList);
        plDataList.add(yakuinHoushuList);
        plDataList.add(salaryList);
        plDataList.add(zakkyuList);
        plDataList.add(shoyoList);
        plDataList.add(zinkenList);
        plDataList.add(yachinList);
        plDataList.add(suikouList);
        plDataList.add(royList);
        plDataList.add(tesuList);
        plDataList.add(koukokuList);
        plDataList.add(shoumouList);
        plDataList.add(houteiList);
        plDataList.add(hukuriList);
        plDataList.add(kousaiList);
        plDataList.add(ryohiList);
        plDataList.add(tuusinList);
        plDataList.add(leaseList);
        plDataList.add(sharyoList);
        plDataList.add(sozeiList);
        plDataList.add(hokenList);
        plDataList.add(unchinList);
        plDataList.add(shuuzenList);
        plDataList.add(zappiList);
        plDataList.add(keihiList);
        plDataList.add(maeriekiList);
        plDataList.add(shokyakuList);
        plDataList.add(gaishuuekiList);
        plDataList.add(gaihiyouList);
        plDataList.add(haihuList);
        plDataList.add(zeimaeList);
        plDataList.add(nullList);
        plDataList.add(sikinHeadList);
        plDataList.add(touriekiList);
        plDataList.add(shokyakuhiList);
        plDataList.add(cashList);
        plDataList.add(kariireList);
        plDataList.add(kappuList);
//        plDataList.add(siharaikeiList);
        plDataList.add(yojouList);
        plDataList.add(nullList);
        plDataList.add(zanHeadList);
        plDataList.add(kariirezanList);
        plDataList.add(leasezanList);
        plDataList.add(kappuzanList);
        plDataList.add(kariireetczanList);

        // 最終行の空白行
        plDataList.add(nullList);
        
        return plDataList;
    }
     
    /**
     * 平均セット( 構成比版)
     * @param zenNenGou
     */
    public void setHeikinKoumokuList(List zenNenGou, int size) {

        sellList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(0),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
						2));
        
        buppanList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(1),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
						2));
                
        totalList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
						2));
        
        genzaiGenkaList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(3),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
						2));

        yasaiGenkaList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(4),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        housouSizaiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(5),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        buppanGenkaKeiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(6),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        uriageGenkaKeiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(7),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        uriageJunList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(8),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        yakuinHoushuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(9),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        salaryList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(10),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        zakkyuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(11),new BigDecimal(size), 2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size), 2),
                        2));
        
        shoyoList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(12),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        zinkenList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(13),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        yachinList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(14),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        suikouList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(15),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        royList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(16),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        tesuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(17),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        koukokuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(18),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        shoumouList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(19),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        houteiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(20),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        hukuriList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(21),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        kousaiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(22),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        ryohiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(23),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        tuusinList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(24),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        leaseList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(25),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        sharyoList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(26),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        sozeiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(27),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        hokenList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(28),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        unchinList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(29),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        shuuzenList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(30),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        zappiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(31),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        keihiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(32),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        maeriekiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(33),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        shokyakuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(34),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        gaishuuekiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(35),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        gaihiyouList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(36),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        haihuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(37),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        zeimaeList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(38),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        touriekiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(39),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        shokyakuhiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(40),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        cashList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(41),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        kariireList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(42),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        kappuList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(43),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        siharaikeiList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(44),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        yojouList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(45),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        kariirezanList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(46),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        leasezanList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(47),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        kappuzanList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(48),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));
        
        kariireetczanList.add(
                Calculator.percentage(
                        Calculator.divide((BigDecimal)zenNenGou.get(49),new BigDecimal(size),2),
                        Calculator.divide((BigDecimal)zenNenGou.get(2),new BigDecimal(size),2),
                        2));

    }
    /**
     * 平均セット
     * @param zenNenGou
     */
    public void setHeikinList(List zenNenGou, int size, int zenSize) {

        
        NumericFormatter num = new NumericFormatter();
                
        sellList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(0),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        buppanList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(1),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        totalList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(2),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        lastSellList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(3),new BigDecimal(zenSize) ) 
                .toString(),"##,###,###,##0") );
        
        sellHiList.add( Calculator.percentage
                ( (BigDecimal)zenNenGou.get(2),(BigDecimal)zenNenGou.get(3) ,2 ) 
                );
                
        genzaiGenkaList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(5),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        yasaiGenkaList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(6),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        housouSizaiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(7),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        buppanGenkaKeiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(8),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        uriageGenkaKeiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(9),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        uriageJunList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(10),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        yakuinHoushuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(11),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        salaryList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(12),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        zakkyuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(13),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        shoyoList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(14),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        zinkenList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(15),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        yachinList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(16),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        suikouList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(17),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        royList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(18),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        tesuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(19),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        koukokuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(20),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        shoumouList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(21),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        houteiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(22),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        hukuriList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(23),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        kousaiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(24),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        ryohiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(25),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        tuusinList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(26),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        leaseList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(27),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        sharyoList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(28),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        sozeiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(29),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        hokenList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(30),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        unchinList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(31),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        shuuzenList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(32),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        zappiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(33),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        keihiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(34),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        maeriekiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(35),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        shokyakuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(36),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        gaishuuekiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(37),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        gaihiyouList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(38),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        haihuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(39),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        zeimaeList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(40),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        touriekiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(41),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        shokyakuhiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(42),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        cashList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(43),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        kariireList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(44),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        kappuList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(45),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        siharaikeiList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(46),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        yojouList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(47),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        kariirezanList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(48),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        leasezanList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(49),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        kappuzanList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(50),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
        kariireetczanList.add(num.format(
                Calculator.divide( (BigDecimal)zenNenGou.get(51),new BigDecimal(size) ) 
                .toString(),"##,###,###,##0") );
    }
    
    /**
     * 合計セット(構成比版)
     * @param zenNenGou
     */
    public void setTotalKoumokuList( List zenNenGou) {
            
        sellList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(0),(BigDecimal)zenNenGou.get(2),2));
        
        buppanList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(1),(BigDecimal)zenNenGou.get(2),2));
                
        totalList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(2),(BigDecimal)zenNenGou.get(2),2));
        
        genzaiGenkaList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(3),(BigDecimal)zenNenGou.get(2),2));

        yasaiGenkaList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(4),(BigDecimal)zenNenGou.get(2),2));
        
        housouSizaiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(5),(BigDecimal)zenNenGou.get(2),2));
        
        buppanGenkaKeiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(6),(BigDecimal)zenNenGou.get(2),2));
        
        uriageGenkaKeiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(7),(BigDecimal)zenNenGou.get(2),2));
        
        uriageJunList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(8),(BigDecimal)zenNenGou.get(2),2));
        
        yakuinHoushuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(9),(BigDecimal)zenNenGou.get(2),2));
        
        salaryList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(10),(BigDecimal)zenNenGou.get(2),2));
        
        zakkyuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(11),(BigDecimal)zenNenGou.get(2),2));
        
        shoyoList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(12),(BigDecimal)zenNenGou.get(2),2));
        
        zinkenList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(13),(BigDecimal)zenNenGou.get(2),2));
        
        yachinList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(14),(BigDecimal)zenNenGou.get(2),2));
        
        suikouList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(15),(BigDecimal)zenNenGou.get(2),2));
        
        royList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(16),(BigDecimal)zenNenGou.get(2),2));
        
        tesuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(17),(BigDecimal)zenNenGou.get(2),2));
        
        koukokuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(18),(BigDecimal)zenNenGou.get(2),2));
        
        shoumouList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(19),(BigDecimal)zenNenGou.get(2),2));
        
        houteiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(20),(BigDecimal)zenNenGou.get(2),2));
        
        hukuriList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(21),(BigDecimal)zenNenGou.get(2),2));
        
        kousaiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(22),(BigDecimal)zenNenGou.get(2),2));
        
        ryohiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(23),(BigDecimal)zenNenGou.get(2),2));
        
        tuusinList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(24),(BigDecimal)zenNenGou.get(2),2));
        
        leaseList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(25),(BigDecimal)zenNenGou.get(2),2));
        
        sharyoList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(26),(BigDecimal)zenNenGou.get(2),2));
        
        sozeiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(27),(BigDecimal)zenNenGou.get(2),2));
        
        hokenList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(28),(BigDecimal)zenNenGou.get(2),2));
        
        unchinList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(29),(BigDecimal)zenNenGou.get(2),2));
        
        shuuzenList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(30),(BigDecimal)zenNenGou.get(2),2));
        
        zappiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(31),(BigDecimal)zenNenGou.get(2),2));
        
        keihiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(32),(BigDecimal)zenNenGou.get(2),2));
        
        maeriekiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(33),(BigDecimal)zenNenGou.get(2),2));
        
        shokyakuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(34),(BigDecimal)zenNenGou.get(2),2));
        
        gaishuuekiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(35),(BigDecimal)zenNenGou.get(2),2));
        
        gaihiyouList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(36),(BigDecimal)zenNenGou.get(2),2));
        
        haihuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(37),(BigDecimal)zenNenGou.get(2),2));
        
        zeimaeList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(38),(BigDecimal)zenNenGou.get(2),2));
        
        touriekiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(39),(BigDecimal)zenNenGou.get(2),2));
        
        shokyakuhiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(40),(BigDecimal)zenNenGou.get(2),2));
        
        cashList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(41),(BigDecimal)zenNenGou.get(2),2));
        
        kariireList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(42),(BigDecimal)zenNenGou.get(2),2));
        
        kappuList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(43),(BigDecimal)zenNenGou.get(2),2));
        
        siharaikeiList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(44),(BigDecimal)zenNenGou.get(2),2));
        
        yojouList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(45),(BigDecimal)zenNenGou.get(2),2));
        
        kariirezanList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(46),(BigDecimal)zenNenGou.get(2),2));
        
        leasezanList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(47),(BigDecimal)zenNenGou.get(2),2));
        
        kappuzanList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(48),(BigDecimal)zenNenGou.get(2),2));
        
        kariireetczanList.add(
                Calculator.percentage((BigDecimal)zenNenGou.get(49),(BigDecimal)zenNenGou.get(2),2));
        

    }

    /**
     * 合計セット
     * @param zenNenGou
     */
    public void setTotalList( List zenNenGou) {
        
        NumericFormatter num = new NumericFormatter();
        
        sellList.add(num.format(((BigDecimal)zenNenGou.get(0)).toString(),"##,###,###,##0" ));
        buppanList.add(num.format(((BigDecimal)zenNenGou.get(1)).toString(),"##,###,###,##0" ));
        totalList.add(num.format(((BigDecimal)zenNenGou.get(2)).toString(),"##,###,###,##0" ));
        lastSellList.add(num.format(((BigDecimal)zenNenGou.get(3)).toString(),"##,###,###,##0" ));
        
        sellHiList.add(num.format(
                ((BigDecimal)zenNenGou.get(4)).toString(),"##,###,###,##0" ));
        
        genzaiGenkaList.add(num.format(((BigDecimal)zenNenGou.get(5)).toString(),"##,###,###,##0" ));
        yasaiGenkaList.add(num.format(((BigDecimal)zenNenGou.get(6)).toString(),"##,###,###,##0" ));
        housouSizaiList.add(num.format(((BigDecimal)zenNenGou.get(7)).toString(),"##,###,###,##0" ));
        buppanGenkaKeiList.add(num.format(((BigDecimal)zenNenGou.get(8)).toString(),"##,###,###,##0" ));
        uriageGenkaKeiList.add(num.format(((BigDecimal)zenNenGou.get(9)).toString(),"##,###,###,##0" ));
        uriageJunList.add(num.format(((BigDecimal)zenNenGou.get(10)).toString(),"##,###,###,##0" ));
        yakuinHoushuList.add(num.format(((BigDecimal)zenNenGou.get(11)).toString(),"##,###,###,##0" ));
        salaryList.add(num.format(((BigDecimal)zenNenGou.get(12)).toString(),"##,###,###,##0" ));
        zakkyuList.add(num.format(((BigDecimal)zenNenGou.get(13)).toString(),"##,###,###,##0" ));
        shoyoList.add(num.format(((BigDecimal)zenNenGou.get(14)).toString(),"##,###,###,##0" ));
        zinkenList.add(num.format(((BigDecimal)zenNenGou.get(15)).toString(),"##,###,###,##0" ));
        yachinList.add(num.format(((BigDecimal)zenNenGou.get(16)).toString(),"##,###,###,##0" ));
        suikouList.add(num.format(((BigDecimal)zenNenGou.get(17)).toString(),"##,###,###,##0" ));
        royList.add(num.format(((BigDecimal)zenNenGou.get(18)).toString(),"##,###,###,##0" ));
        tesuList.add(num.format(((BigDecimal)zenNenGou.get(19)).toString(),"##,###,###,##0" ));
        koukokuList.add(num.format(((BigDecimal)zenNenGou.get(20)).toString(),"##,###,###,##0" ));
        shoumouList.add(num.format(((BigDecimal)zenNenGou.get(21)).toString(),"##,###,###,##0" ));
        houteiList.add(num.format(((BigDecimal)zenNenGou.get(22)).toString(),"##,###,###,##0" ));
        hukuriList.add(num.format(((BigDecimal)zenNenGou.get(23)).toString(),"##,###,###,##0" ));
        kousaiList.add(num.format(((BigDecimal)zenNenGou.get(24)).toString(),"##,###,###,##0" ));
        ryohiList.add(num.format(((BigDecimal)zenNenGou.get(25)).toString(),"##,###,###,##0" ));
        tuusinList.add(num.format(((BigDecimal)zenNenGou.get(26)).toString(),"##,###,###,##0" ));
        leaseList.add(num.format(((BigDecimal)zenNenGou.get(27)).toString(),"##,###,###,##0" ));
        sharyoList.add(num.format(((BigDecimal)zenNenGou.get(28)).toString(),"##,###,###,##0" ));
        sozeiList.add(num.format(((BigDecimal)zenNenGou.get(29)).toString(),"##,###,###,##0" ));
        hokenList.add(num.format(((BigDecimal)zenNenGou.get(30)).toString(),"##,###,###,##0" ));
        unchinList.add(num.format(((BigDecimal)zenNenGou.get(31)).toString(),"##,###,###,##0" ));
        shuuzenList.add(num.format(((BigDecimal)zenNenGou.get(32)).toString(),"##,###,###,##0" ));
        zappiList.add(num.format(((BigDecimal)zenNenGou.get(33)).toString(),"##,###,###,##0" ));
        keihiList.add(num.format(((BigDecimal)zenNenGou.get(34)).toString(),"##,###,###,##0" ));
        maeriekiList.add(num.format(((BigDecimal)zenNenGou.get(35)).toString(),"##,###,###,##0" ));
        shokyakuList.add(num.format(((BigDecimal)zenNenGou.get(36)).toString(),"##,###,###,##0" ));
        gaishuuekiList.add(num.format(((BigDecimal)zenNenGou.get(37)).toString(),"##,###,###,##0" ));
        gaihiyouList.add(num.format(((BigDecimal)zenNenGou.get(38)).toString(),"##,###,###,##0" ));
        haihuList.add(num.format(((BigDecimal)zenNenGou.get(39)).toString(),"##,###,###,##0" ));
        zeimaeList.add(num.format(((BigDecimal)zenNenGou.get(40)).toString(),"##,###,###,##0" ));
        
        touriekiList.add(num.format(((BigDecimal)zenNenGou.get(41)).toString(),"##,###,###,##0" ));
        shokyakuhiList.add(num.format(((BigDecimal)zenNenGou.get(42)).toString(),"##,###,###,##0" ));
        cashList.add(num.format(((BigDecimal)zenNenGou.get(43)).toString(),"##,###,###,##0" ));
        kariireList.add(num.format(((BigDecimal)zenNenGou.get(44)).toString(),"##,###,###,##0" ));
        kappuList.add(num.format(((BigDecimal)zenNenGou.get(45)).toString(),"##,###,###,##0" ));
        siharaikeiList.add(num.format(((BigDecimal)zenNenGou.get(46)).toString(),"##,###,###,##0" ));
        yojouList.add(num.format(((BigDecimal)zenNenGou.get(47)).toString(),"##,###,###,##0" ));
        
        kariirezanList.add(num.format(((BigDecimal)zenNenGou.get(48)).toString(),"##,###,###,##0" ));
        leasezanList.add(num.format(((BigDecimal)zenNenGou.get(49)).toString(),"##,###,###,##0" ));
        kappuzanList.add(num.format(((BigDecimal)zenNenGou.get(50)).toString(),"##,###,###,##0" ));
        kariireetczanList.add(num.format(((BigDecimal)zenNenGou.get(51)).toString(),"##,###,###,##0" ));

    }

    /**
     * 直営PLデータ作成処理
     * @param plList
     * @param miseList
     * @param dto
     * @return List
     */
    public List setPlRcList( List plList, List miseList, CsvOutputDto dto) {
        List rcLIst = new ArrayList();
        PlLumpExtractConditionDto plDto = (PlLumpExtractConditionDto)dto;
        boolean koumokuFlg = false;
        
        if ( plDto.getDataTypeRc().equals( kouseiAri ) ) {
            koumokuFlg = true;
        }

        for(int i = 0; i < miseList.size(); i++) {
            // 金額リスト
            List moneyList = new ArrayList();
            // 構成比リスト
            List kouseiList = new ArrayList();

            CodMiseInfo miseEty = (CodMiseInfo)miseList.get(i);
            int uriGoukei = 0;
            NumericFormatter num = new NumericFormatter();
            // 金額取得
            for( int j =0; j < plList.size(); j++ ) {
                UIPlRCDataInfo plEty = (UIPlRCDataInfo)plList.get(j);
                if( miseEty.getMiseCd().equals( plEty.getMiseCd() ) ) {
                    moneyList.add( plEty.getKingaku());
                    if( plEty.getKoumokuNo().equals(uriGou) ) {
                        uriGoukei = Integer.parseInt( plEty.getKingaku() );
                    }
                }
            }
            
            // 構成比取得
            if (koumokuFlg ) {
                for(int j = 0; j < moneyList.size(); j++ ) {
                     BigDecimal kouseiHi = new BigDecimal(0);
                     
                    if ( uriGoukei != 0 ) {
                        kouseiHi = 
                            Calculator.percentage( 
                                    new BigDecimal( Integer.parseInt( (String)moneyList.get(j) ) ) ,
                                    new BigDecimal(uriGoukei),
                                    2);
                    }
                    kouseiList.add(  kouseiHi  );
                }
            }
            
            if ( moneyList != null && moneyList.size() > 0 ){
                List totalList = new ArrayList();
                totalList.add( miseEty.getMiseCd() );
                totalList.add( miseEty.getMiseNameKj().trim() );
                            
                for ( int k = 0; k < moneyList.size(); k++ ) {
                    totalList.add( num.format( (String)moneyList.get(k),"##,###,###,##0" ) );
                    if ( plDto.getDataTypeRc().equals(kouseiAri ) ) {
                        totalList.add(kouseiList.get(k));
                    }
                }
    /*            if ( moneyList.size() == 0 ) {
                   int count = rcCount;
                   if ( koumokuFlg ) {
                        count *= 2;
                   }
                    for( int k = 0; k < count; k++ ) {
                        totalList.add("0");
                    }
                }
    */            
                rcLIst.add(totalList);
            }
        }
        
        return rcLIst;
    }    
    
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#validate(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public void validate(CsvOutputDto csvOutputDto) {
        List svOnerList = new ArrayList();
        List onerList = new ArrayList();
        
        PlLumpExtractConditionDto dto = (PlLumpExtractConditionDto)csvOutputDto;

        if( dto.getPlMode().equals( "0" ) ) {
                if ( dto.getOwnerCd() == null || (dto.getOwnerCd().trim()).length() == 0) {
                	throw new NotNullException("オーナーコード");
                }
            
                if ( dto.getUserInfoKbn().equals("01") ) {
                	if (  dto.getLimitFlg() ) {
                		svOnerList = getMstSvOnerInfoDao().getSVOnerInfo( dto.getUserId(),dto.getCompanyCd() );
                        
                        if ( svOnerList == null || svOnerList.size() == 0 ) {
                            throw new NotExistException("対象のオーナー");
                        }
                        
                        boolean onerFlg = false;
                        for ( int i = 0; i < svOnerList.size(); i++ ) {
                        	MstSVOnerInfo mstSVOnerInfo = (MstSVOnerInfo)svOnerList.get(i);
                           if ( mstSVOnerInfo.getOnerCd().equals( dto.getOwnerCd() ) ) {
                           	    onerFlg = true;
                           }
                        }
                        
                        if ( !onerFlg ) {
                            throw new NotExistException("対象のオーナー");
                        }
                    }
                }
                
                onerList = getUIOnerInfoDao().getOnerInfo( dto.getCompanyCd(),dto.getOwnerCd() );
                
                if ( onerList == null || onerList.size() == 0) {
                	throw new NotExistException("対象のオーナー");
                }

                if ( dto.getMonthTypeFc().equals(period) ) {
                    if ( Integer.parseInt( dto.getPeriodMonthFromStr() ) >= Integer.parseInt( dto.getPeriodMonthToStr() ) ) {
                        throw new ConstraintsViolationException("期間指定はFROM < TOで","");
                    }
                }

                dto.setOnerList( onerList );
        } else {
            if ( dto.getMonthTypeRc().equals(period) ) {
                if ( Integer.parseInt( dto.getPeriodMonthFromStrRc() ) >= Integer.parseInt( dto.getPeriodMonthToStrRc() ) ) {
                    throw new ConstraintsViolationException("期間指定はFROM < TOで","");
                }
            }
        }
		// TODO 自動生成されたメソッド・スタブ
	}

    /**
     * 年月リストの取得
     * @param birdDateInfo
     * @return List
     */
    public List getDateMonthList( String date ) {
        List monthList = new ArrayList();
       
        for ( int i = 0; i < NENGETSU_DISPLAY_MONTH; i++ ) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(date, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("年月取得処理");
            }
            monthList.add(month);    
        }
        
        return monthList;
        
    }

    /**
     * 期間指定時の年月リストの取得
     * @param birdDateInfo
     * @return List
     */
    public List getDateMonthList( String fromDt,String toDt ) {
        List monthList = new ArrayList();
               
        for ( int i = 0; i < NENGETSU_DISPLAY_MONTH; i++ ) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(toDt, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("年月取得処理");
            }
            monthList.add(month);
            
            if ( month.equals( fromDt ) ) {
            	break;
            }
        }
        
        return monthList;
        
    }
    
    /**
     * 期間取得(当年、前年)
     * @param csvdto
     * @return
     */
    public Map getDtMap( CsvOutputDto csvdto) {
        
        Map dtMap = new HashMap();
               
        PlLumpExtractConditionDto dto = (PlLumpExtractConditionDto)csvdto;
        
        String fromDt = new String();
        String toDt = new String();
        String zenFromDt = new String();
        String zenToDt = new String();
        
        List koumokuDtList = new ArrayList();
        List zenKoumokuDtList = new ArrayList();
        
        String totalFromDt = new String();
        String totalToDt = new String();
        String zenFromTotalDt = new String();
        String zenToTotalDt = new String();

        List totalDtList = new ArrayList();
        List zenTotalDtList = new ArrayList();

        List onerList = dto.getOnerList();
        UIOnerInfo uiOner =  (UIOnerInfo)onerList.get(0);
        
        String outDt = new String();
        String zenOutDt = new String();
        
        // 期間指定時
        if ( dto.getMonthTypeFc().equals( period ) ) {
            fromDt = dto.getPeriodMonthFromStr();
            toDt = dto.getPeriodMonthToStr();
            
            koumokuDtList = getDateMonthList( fromDt, toDt );
            
            totalDtList = getDateMonthList( fromDt);
            
            if( koumokuDtList.size() > 12 ) {
                totalFromDt = (String)totalDtList.get( totalDtList.size() - 2);
            } else {
                totalFromDt = fromDt;
            }
            totalToDt = toDt;
        // 対象月指定時
        } else if ( dto.getMonthTypeFc().equals( target ) ){
            toDt = dto.getTargetMonthStr();
            koumokuDtList = getDateMonthList( toDt );
            
            fromDt = (String)koumokuDtList.get( koumokuDtList.size() -1 );
            totalFromDt = (String)koumokuDtList.get( koumokuDtList.size() - 2);
            totalToDt = toDt;
        // 決算月時
        } else {
// start xkhata 20060509 決算月対応
            String nen = dto.getClosingMonthStr().trim();
            
            if ( Integer.parseInt(uiOner.getKessanM().trim() ) < 4 ) {
                nen = String.valueOf( Integer.parseInt( nen) + 1 );
            }
// end 
            toDt =  nen + (uiOner.getKessanM()).trim();
            koumokuDtList = getDateMonthList( toDt );
            
            fromDt = (String)koumokuDtList.get( koumokuDtList.size() -1 );
            totalFromDt = (String)koumokuDtList.get( koumokuDtList.size() - 2);
            totalToDt = toDt;
        }
        try {
            zenFromDt = DateManager.getPrevMonth( fromDt,12);
            zenToDt = DateManager.getPrevMonth( toDt,12 );
            zenFromTotalDt = DateManager.getPrevMonth( totalFromDt,12);
            zenToTotalDt = DateManager.getPrevMonth( totalToDt,12);
            
            outDt = DateManager.getPrevMonth( toDt,12);
            zenOutDt = DateManager.getPrevMonth(zenToTotalDt,12);
            
            for ( int i = 0; i < koumokuDtList.size(); i++ ) {
                zenKoumokuDtList.add( DateManager.getPrevYear( (String)koumokuDtList.get(i),1) );
            }
            
            for ( int i = 0; i < totalDtList.size(); i++ ) {
                zenTotalDtList.add( DateManager.getPrevYear( (String)totalDtList.get(i),1 ) );
            }
            
        }catch (Exception ex) {
            throw new FtlSystemException("年月取得処理");
        }
        
        dtMap.put("fromDt", fromDt);
        dtMap.put("toDt",toDt);
        dtMap.put("zenFromDt",zenFromDt);
        dtMap.put("zenToDt", zenToDt);
        
        dtMap.put("zenKoumokuDtList", zenKoumokuDtList);        
        dtMap.put("koumokuDtList", koumokuDtList);

        dtMap.put("totalFromDt", totalFromDt);
        dtMap.put("totalToDt", totalToDt);
        dtMap.put("zenFromTotalDt", zenFromTotalDt);
        dtMap.put("zenToTotalDt", zenToTotalDt);
        
        dtMap.put("totalDtList", totalDtList);
        dtMap.put("zenTotalDtList", zenTotalDtList);
        
        dtMap.put("outDt",outDt);
        dtMap.put("zenOutDt",zenOutDt);
        
        return dtMap;
    }

    /**
     * 項目毎の合計を求める 
     * @param totalList
     * @return
     */
    public List getZentenGou( List totalList ,List zenTotalList, CsvOutputDto csv, String fromDt) {
        List keiList = new ArrayList();
        
        PlLumpExtractConditionDto dto = (PlLumpExtractConditionDto)csv;
        
        Map dtMap = getDtMap( csv);
        
        String zenOutDt = (String)dtMap.get("zenOutDt");
        
        // 全店合計を取得
        BigDecimal uriagebg = new BigDecimal(0);
        BigDecimal buppanuribg = new BigDecimal(0);
        BigDecimal urigoubg = new BigDecimal(0);
        BigDecimal genzaigenkabg = new BigDecimal(0);
        BigDecimal yasaigenkabg = new BigDecimal(0);
        BigDecimal housougenkabg = new BigDecimal(0);
        BigDecimal buppangenkabg = new BigDecimal(0);
        BigDecimal urigenkabg = new BigDecimal(0);
        BigDecimal uririekibg = new BigDecimal(0);
        BigDecimal yakuinbg = new BigDecimal(0);
        BigDecimal kyuuryoubg = new BigDecimal(0);
        BigDecimal zakyubg = new BigDecimal(0);
        BigDecimal shouyobg = new BigDecimal(0);
        BigDecimal zinkenhibg = new BigDecimal(0);
        BigDecimal yachinbg = new BigDecimal(0);
        BigDecimal suikouhibg = new BigDecimal(0);
        BigDecimal royalbg = new BigDecimal(0);
        BigDecimal siharaibg = new BigDecimal(0);
        BigDecimal koukokukbg = new BigDecimal(0);
        BigDecimal shoumoubg = new BigDecimal(0);
        BigDecimal houteibg = new BigDecimal(0);
        BigDecimal hukurikoubg = new BigDecimal(0);
        BigDecimal kousaibg = new BigDecimal(0);
        BigDecimal ryohibg = new BigDecimal(0);
        BigDecimal tuusinbg = new BigDecimal(0);
        BigDecimal leacebg = new BigDecimal(0);
        BigDecimal sharyoubg = new BigDecimal(0);
        BigDecimal sozeibg = new BigDecimal(0);
        BigDecimal hokenbg = new BigDecimal(0);
        BigDecimal unchinbg = new BigDecimal(0);
        BigDecimal shuuzennbg = new BigDecimal(0);
        BigDecimal zappibg = new BigDecimal(0);
        BigDecimal keihikeibg = new BigDecimal(0);
        BigDecimal shoukyakumaebg = new BigDecimal(0);
        BigDecimal gennkabg = new BigDecimal(0);
        BigDecimal eigyougaishubg = new BigDecimal(0);
        BigDecimal eigyougaihibg = new BigDecimal(0);
        BigDecimal kyouuuhibg = new BigDecimal(0);
        BigDecimal zeibikimaebg = new BigDecimal(0);
        BigDecimal tougeturiekibg = new BigDecimal(0);
        BigDecimal genkashoukyakubg = new BigDecimal(0);
        BigDecimal cashflowbg = new BigDecimal(0);
        BigDecimal kariirebg = new BigDecimal(0);
        BigDecimal kappubg = new BigDecimal(0);
        BigDecimal siharaikeibg = new BigDecimal(0);
        BigDecimal sashihikibg = new BigDecimal(0);
        BigDecimal kariirezanbg = new BigDecimal(0);
        BigDecimal leacezanbg = new BigDecimal(0);
        BigDecimal kappuzanbg = new BigDecimal(0);
        BigDecimal kariireetczanbg = new BigDecimal(0);

        BigDecimal zenGoukei = new BigDecimal(0);
        BigDecimal zennenHi = new BigDecimal(0.0);

        for( int i = 0; i < totalList.size(); i++ ) {

                UIPlDataInfo entity = (UIPlDataInfo)totalList.get(i);
                
                boolean daFlg = true;
                
                if ( entity.getPlYm().equals( fromDt) ) {
                        daFlg = false;
                 }
                
                if ( daFlg) {

                    uriagebg=uriagebg.add( entity.getUriage() );
                    buppanuribg=buppanuribg.add( entity.getBuppan());
                    urigoubg=urigoubg.add( entity.getUriagedaka());
                    genzaigenkabg=genzaigenkabg.add( entity.getGenzairyoKei() );
                    yasaigenkabg=yasaigenkabg.add( entity.getYasaiKei() );
                    housougenkabg=housougenkabg.add( entity.getHouzaiKei() );
                    buppangenkabg=buppangenkabg.add( entity.getBuppanKei() );
                    urigenkabg=urigenkabg.add( entity.getUriagegenka() );
                    uririekibg=uririekibg.add( entity.getUriageSoRieki() );
                    yakuinbg=yakuinbg.add( entity.getYakuinSalary() );
                    kyuuryoubg=kyuuryoubg.add( entity.getSalarySalary() );
                    zakyubg=zakyubg.add( entity.getZakkyuSalary() );
                    shouyobg=shouyobg.add( entity.getBonusKei().add(entity.getRetireKei() ) );
                    zinkenhibg=zinkenhibg.add( entity.getSalary() );
                    yachinbg=yachinbg.add( entity.getYachin() );
                    suikouhibg=suikouhibg.add( entity.getSuikouHi() );
                    royalbg=royalbg.add( entity.getRoyalty() );
                    siharaibg=siharaibg.add( entity.getTesuryo());
                    koukokukbg=koukokukbg.add( entity.getKoukoku() );
                    shoumoubg=shoumoubg.add( entity.getShoumou() );
                    houteibg=houteibg.add( entity.getHouteiFukuri() );
                    hukurikoubg=hukurikoubg.add( entity.getFukuriKousei() );
                    kousaibg=kousaibg.add( entity.getKousai() );
                    ryohibg=ryohibg.add( entity.getRyohi() );
                    tuusinbg=tuusinbg.add( entity.getTusin() );
                    leacebg=leacebg.add( entity.getLease() );
                    sharyoubg=sharyoubg.add( entity.getSharyo() );
                    sozeibg=sozeibg.add( entity.getSozei() );
                    hokenbg=hokenbg.add( entity.getHoken() );
                    unchinbg=unchinbg.add( entity.getUnchin() );
                    shuuzennbg=shuuzennbg.add( entity.getShuzen() );
                    zappibg=zappibg.add( entity.getZappi() );
                    keihikeibg=keihikeibg.add( entity.getKeihiShokei() );
                    shoukyakumaebg=shoukyakumaebg.add( entity.getShokyakuRieki() );
                    gennkabg=gennkabg.add( entity.getGenkaShokyaku() );
                    eigyougaishubg=eigyougaishubg.add( entity.getEigaiShueki() );
                    eigyougaihibg=eigyougaihibg.add( entity.getEigaiHiyo() );
                    kyouuuhibg=kyouuuhibg.add( entity.getHonshahiHai() );
                    zeibikimaebg=zeibikimaebg.add( entity.getRieki() );
        
                    int rieki = entity.getShokyakuRieki().intValue() 
                                 - entity.getGenkaShokyaku().intValue()
                                 + entity.getEigaiShueki().intValue()
                                 - entity.getEigaiHiyo().intValue()
                                 - entity.getHonshahiHai().intValue();
                                              
                    tougeturiekibg=tougeturiekibg.add( new BigDecimal( String.valueOf(rieki) ) );
        
                    genkashoukyakubg=genkashoukyakubg.add( entity.getGenkaShokyaku() );
        
                    BigDecimal cash = new BigDecimal( String.valueOf( rieki ) );
                    cash = cash.add( entity.getGenkaShokyaku() );
        
                    cashflowbg=cashflowbg.add(  cash );
                    kariirebg=kariirebg.add( entity.getKashiireDown() );
                    kappubg=kappubg.add( entity.getKappuDown() );
                    siharaikeibg=siharaikeibg.add( entity.getTouGenshoKei() );
        
                    BigDecimal sasi = cash;
                    sasi = sasi.subtract( entity.getKashiireDown() );
                    sasi = sasi.subtract( entity.getKappuDown() );
        
                    sashihikibg=sashihikibg.add( sasi );
        
                    kariirezanbg=kariirezanbg.add( entity.getKashiireZandaka() );
                    leacezanbg=leacezanbg.add( entity.getLeaseZandaka() );
                    kappuzanbg=kappuzanbg.add( entity.getKappuZandaka() );
                    kariireetczanbg=kariireetczanbg.add( entity.getTouZandakaKei() );
                }

        }
        
        if ( dto.getDataTypeFc().equals( money) ) {
            for ( int i = 0; i < zenTotalList.size();i++ ) {
                UIPlDataInfo zenEnt = (UIPlDataInfo)zenTotalList.get(i);
                if ( !zenEnt.getPlYm().equals( zenOutDt ) ) {
                	zenGoukei = zenGoukei.add( zenEnt.getUriagedaka());
                }
            }
        }
        
        keiList.add( uriagebg );
        keiList.add( buppanuribg );
        keiList.add( urigoubg );
        
        if ( dto.getDataTypeFc().equals( money ) ) {
            keiList.add( zenGoukei);
            if ( zenGoukei.intValue() != 0 ) {
            	keiList.add( Calculator.percentage( urigoubg,zenGoukei,2) );
            } else {
            	keiList.add( zennenHi );
            }
        }
        
        keiList.add( genzaigenkabg );
        keiList.add( yasaigenkabg );
        keiList.add( housougenkabg );
        keiList.add( buppangenkabg );
        keiList.add( urigenkabg );
        keiList.add( uririekibg );
        keiList.add( yakuinbg );
        keiList.add( kyuuryoubg );
        keiList.add( zakyubg );
        keiList.add( shouyobg );
        keiList.add( zinkenhibg );
        keiList.add( yachinbg );
        keiList.add( suikouhibg );
        keiList.add( royalbg );
        keiList.add( siharaibg );
        keiList.add( koukokukbg );
        keiList.add( shoumoubg );
        keiList.add( houteibg );
        keiList.add( hukurikoubg );
        keiList.add( kousaibg );
        keiList.add( ryohibg );
        keiList.add( tuusinbg );
        keiList.add( leacebg );
        keiList.add( sharyoubg );
        keiList.add( sozeibg );
        keiList.add( hokenbg );
        keiList.add( unchinbg );
        keiList.add( shuuzennbg );
        keiList.add( zappibg );
        keiList.add( keihikeibg );
        keiList.add( shoukyakumaebg );
        keiList.add( gennkabg );
        keiList.add( eigyougaishubg );
        keiList.add( eigyougaihibg );
        keiList.add( kyouuuhibg );
        keiList.add( zeibikimaebg );
        keiList.add( tougeturiekibg );
        keiList.add( genkashoukyakubg );
        keiList.add( cashflowbg );
        keiList.add( kariirebg );
        keiList.add( kappubg );
        keiList.add( siharaikeibg );
        keiList.add( sashihikibg );
        keiList.add( kariirezanbg );
        keiList.add( leacezanbg );
        keiList.add( kappuzanbg );
        keiList.add( kariireetczanbg );

        return keiList;
    }
    /**
     * yyyy年MM月へ変換処理
     * 
     * @param ym
     * @return
     */
    private static String changeYMKanji(final String ym) {
    	DateFormatter formatter = new DateFormatter();
    	return formatter.format(
    			ym
    			,DateFormatter.PATTERN_MONTH_KANJI_YM
    			,DateFormatter.DATE_TYPE_YM);
    }
}