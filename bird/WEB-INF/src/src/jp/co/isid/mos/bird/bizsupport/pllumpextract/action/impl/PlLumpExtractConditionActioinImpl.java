/*
 * 作成日: 2006/04/05
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;


import jp.co.isid.mos.bird.bizsupport.pllumpextract.action.PlLumpExtractConditionAction;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.dto.PlLumpExtractConditionDto;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.CodCompanyInfo;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.MstSibuInfo;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetCompanyInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetOnerInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetRCSibuInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetPlMaxMonthLogic;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.impl.PlLumpExtractCsvOutputLogicImpl;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchConditionDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class PlLumpExtractConditionActioinImpl implements PlLumpExtractConditionAction {

    public static final String initialize_ACTION_ID   = "BBS007A01";
    public static final String accessOwner_ACTION_ID   = "BBS007A02";   
    
    /* ビューID(P/L一括抽出)*/
    private String PLAll_VIEW_ID = "BBS007V01";
   
    /* ビューID(オーナー選択)*/
    private String OWNER_VIEW_ID = "BCO006V01";
    
    /* 本部タイプ */
    private String HONBU_TYPE = "01";
    /* オーナータイプ */
    private String ONER_TYPE = "02";     
    /* 店舗タイプ */
    private String TENPO_TYPE = "03";
    
    /* FCモード */
    private String FC_MODE = "0";
    
    /* RCモード */    
    private String RC_MODE = "1";
    /* 年月 */
    private static int NENGETSU_DISPLAY_MONTH = 13;
    
    /* 年 */
    private static int NENGETSU_DISPLAY_YEAR = 2;
    
    /* plLumpExtractConditionDto */
    private PlLumpExtractConditionDto plLumpExtractConditionDto;
    
	/* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    
    /* OwnerSearchConditionDto */
    private OwnerSearchConditionDto ownerSearchConditioinDto;
    
    /*  OwnerSearchDto */
    private OwnerSearchDto ownerSearchDto;

    /* GetRcSibuInfoLogic */
    private GetRCSibuInfoLogic getRcSibuInfoLogic;
    
    /* GetOnerInfoLogic */
    private GetOnerInfoLogic getOnerInfoLogic;
    
    /* GetCompanyInfoLogic */
    private GetCompanyInfoLogic getCompanyInfoLogic;
    
    /* GetPlMaxMonthLogic */
    private GetPlMaxMonthLogic getPlMaxMonthLogic;
    
    /*  csvoutputlogic*/
    private CsvOutputLogic plFcLumpExtractCsvOutputLogicImpl;
    
    /* plMode */
    private String plMode;
    
    /* String */
    private String checkDateFC;
    private String checkDateRC;
    private String checkData;
    private String checkOutput;
    private String ownerCd;
    private String checkKouseihi;
    
    /**
     * plLumpExtractConditionDto設定
     * @return plExtractConditionDto
     */
    public PlLumpExtractConditionDto getPlLumpExtractConditionDto() {
    	return this.plLumpExtractConditionDto;
    }
    
    /**
     * plLumpExtractCondition設定
     * @param plLumpExtractCondition
     */
    
    public void setPlLumpExtractConditionDto( PlLumpExtractConditionDto plLumpExtractConditionDto) {
    	this.plLumpExtractConditionDto = plLumpExtractConditionDto;
    }
    
    /**
     * pullDownMenuDtoの設定
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * pullDownMenuDtoの設定
     * @param pullDownMenuDto pullDownMenuDto 
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    /**
     * ownerSearchConditionDtoの設定
     * @return ownerSearchConditionDto
     */
    public OwnerSearchConditionDto getOwnerSearchConditionDto() {
    	return this.ownerSearchConditioinDto;
    }
    
    /**
     * ownerSearchConditioniDtoの設定
     * @param ownerSearchConditionDto
     */
    public void setOwnerSearchConditioinDto ( OwnerSearchConditionDto ownerSearchConditionDto ) {
    	this.ownerSearchConditioinDto = ownerSearchConditionDto;
    }
    
    /**
     * ownerSearchDtoの設定
     * @return
     */
    public OwnerSearchDto getOwnerSearchDto() {
    	return this.ownerSearchDto;
    }
    
    /**
     * ownerSearchDtoの設定
     * @param ownerSearchDto
     */
    public void setOwnerSearchDto( OwnerSearchDto ownerSearchDto ) {
    	this.ownerSearchDto = ownerSearchDto;
    }
    /**
     * getRcSibuInfoLogic設定
     * @return
     */
    public GetRCSibuInfoLogic getGetRcSibuInfoLogic() {
    	return this.getRcSibuInfoLogic;
    }
        
    /**
     * getRcSibuInfoLogic設定
     * @param getRcSibuInfoLogic
     */
    public void setGetRcSibuInfoLogic( GetRCSibuInfoLogic getRcSibuInfoLogic ) {
    	this.getRcSibuInfoLogic = getRcSibuInfoLogic;
    }
    
    /**
     * getOnerInfoLogic設定
     * @return
     */
    public GetOnerInfoLogic getGetOnerInfoLogic() {
    	return this.getOnerInfoLogic;
    }
    
    /**
     * getOnerInfoLogic設定
     * @param
     */    
    public void setGetOnerInfoLogic( GetOnerInfoLogic getOnerInfoLogic ) {
    	this.getOnerInfoLogic = getOnerInfoLogic;
    }
    
    /**
     * getCompanyInfoLogic設定
     * @return
     */
    public GetCompanyInfoLogic getGetCompanyInfoLogic() {
    	return this.getCompanyInfoLogic;
    }
    
    /**
     * getCompanyInfoLogic設定
     * @param
     */
    public void setGetCompanyInfoLogic ( GetCompanyInfoLogic getCompanyInfoLogic ) {
    	this.getCompanyInfoLogic = getCompanyInfoLogic;
    }
    
    /**
     * getPlMaxMonthLogic設定
     * @return
     */
    public GetPlMaxMonthLogic getGetPlMaxMonthLogic() {
        return this.getPlMaxMonthLogic;
    }
    
    /**
     * getPlMaxMonthLogic設定
     * @param
     */
    public void setGetPlMaxMonthLogic ( GetPlMaxMonthLogic getPlMaxMonthLogic ) {
        this.getPlMaxMonthLogic = getPlMaxMonthLogic;
    }
    
    /**
     * CSV出力ロジック設定
     * @return
     */
    public CsvOutputLogic getPlFcLumpExtractCsvOutputLogicImpl() {
    	return this.plFcLumpExtractCsvOutputLogicImpl;
    }
    
    /**
     * CSV出力ロジック設定
     * @param plLumpExtractCsvOutputLogicImpl
     */
    public void setPlFcLumpExtractCsvOutputLogicImpl( PlLumpExtractCsvOutputLogicImpl plFcLumpExtractCsvOutputLogicImpl) {
    	this.plFcLumpExtractCsvOutputLogicImpl = plFcLumpExtractCsvOutputLogicImpl;
    }
    
    /**
     * plMode設定
     * @return
     */
    public String getPlMode() {
    	return this.plMode;
    }
    
    /**
     * plMode設定
     * @param plMode
     */
    public void setPlMode(String plMode) {
    	this.plMode = plMode;
    }
    
    /**
     * checkDate設定
     * @return
     */
    public String getCheckDateFC() {
    	return this.checkDateFC;
    }
    
    /**
     * checkDate設定
     * @param checkDate
     */
    public void setCheckDateFC( String checkDateFC) {
    	this.checkDateFC = checkDateFC;
    }

    /**
     * checkDate設定
     * @return
     */
    public String getCheckDateRC() {
        return this.checkDateRC;
    }
    
    /**
     * checkDate設定
     * @param checkDate
     */
    public void setCheckDateRC( String checkDateRC ) {
        this.checkDateRC = checkDateRC;
    }

    /**
     * checkData設定
     * @return
     */
    public String getCheckData() {
        return this.checkData;
    }
    
    /**
     * checkData設定
     * @param checkData
     */
    public void setCheckData( String checkData ) {
        this.checkData = checkData;
    }
        
    /**
     * checkOutput設定
     * @return
     */
    public String getCheckOutput() {
    	return this.checkOutput;
    }
    
    /**
     * checkOutput設定
     * @return
     */
    public void setCheckOutput( String checkOutput ) {
    	this.checkOutput = checkOutput;
    }
    
    /**
     * checkKouseihi設定
     * @return
     */
    public String getCheckKouseihi() {
            return this.checkKouseihi;
    }

    /**
     * checkKouseihi設定
     * @return
     */
    public void setCheckKouseihi( String checkKouseihi) {
            this.checkKouseihi = checkKouseihi;
    }
    
    /**
     * ownerCd設定
     * @return
     */
   public String getOwenrCd() {
   	    return this.ownerCd;
   }

   /**
     * ownerCe設定
     * @param ownerCd
     */
    public void setOwnerCd( String ownerCd) {
    	this.ownerCd = ownerCd;
    }
    
    /**
     * 初期処理
     * @return null
     */
    public String initialize() {
    
        if ( getPullDownMenuDto().isClearFlg() ) {
        	getPlLumpExtractConditionDto().clear();
            
            // 初期処理
            S2Container container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
            BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");
            BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession().getAttribute("birdDateInfo");
            getPullDownMenuDto().setClearFlg( false );
            
            getPlLumpExtractConditionDto().setLocalFlg( birdUserInfo.isLocal() );
            getPlLumpExtractConditionDto().setLimitFlg(birdUserInfo.isLimit() );
            getPlLumpExtractConditionDto().setUserId( birdUserInfo.getUserID() );
            
            getPlLumpExtractConditionDto().setPlMode( FC_MODE );
            getPlLumpExtractConditionDto().setMonthTypeFc("closing");
            getPlLumpExtractConditionDto().setDataTypeFc("money");
            //getPlLumpExtractConditionDto().setOutputTypeFc("csv");
            getPlLumpExtractConditionDto().setMonthTypeRc("target");
            getPlLumpExtractConditionDto().setDataTypeRc("need");
            //getPlLumpExtractConditionDto().setOutputTypeRc("csv");

            // ユーザタイプ判断
            if ( birdUserInfo.getMstUser().getUserTypeCd().equals( HONBU_TYPE ) ) {
                getPlLumpExtractConditionDto().setUserInfoKbn( HONBU_TYPE );
                
                // 管理会社企業コード取得
                List compList = getCompanyInfoLogic.execute( birdUserInfo.getMstUser().getRCompanyCd() ) ;
                CodCompanyInfo compEntity = (CodCompanyInfo)compList.get(0);
                
                getPlLumpExtractConditionDto().setCompanyCd(compEntity.getCompanyCd()  );
                // 直営支部取得
                List rcList = getRcSibuInfoLogic.execute( getPlLumpExtractConditionDto().getCompanyCd());
                
                if ( rcList == null || rcList.size() == 0 ) {
                	throw new FtlSystemException("直営支部取得時に");
                }
                
                List rcSibuList = new ArrayList();
                
                for ( int  i = 0; i < rcList.size(); i++ ) {
                	MstSibuInfo entity = (MstSibuInfo)rcList.get(i);
                	rcSibuList.add( new SelectItem( entity.getSibuCd(), entity.getSibuName() ) );
                }
                
                MstSibuInfo mstSibuInfo = (MstSibuInfo)rcList.get(0);
                getPlLumpExtractConditionDto().setRcStr( mstSibuInfo.getSibuCd() );

                getPlLumpExtractConditionDto().setRcList( rcSibuList );
                
            } else if ( birdUserInfo.getMstUser().getUserTypeCd().equals( ONER_TYPE ) ) {
            	List onerCdList = birdUserInfo.getUserOner();
                
                getPlLumpExtractConditionDto().setOwnerCd( ((UIUserOner)onerCdList.get(0)).getOnerCd());
                getPlLumpExtractConditionDto().setCompanyCd(((UIUserOner)onerCdList.get(0)).getCompanyCd());
                
                getPlLumpExtractConditionDto().setUserInfoKbn( ONER_TYPE );
            } else if ( birdUserInfo.getMstUser().getUserTypeCd().equals( TENPO_TYPE ) ) {
                getPlLumpExtractConditionDto().setUserInfoKbn( TENPO_TYPE );
// start add 20060509 xkhata 店舗タイプのとき対応  
                throw new CannotAccessException();
// end 
                
            }
            
            // 年月リスト一覧
            getPlLumpExtractConditionDto().setClosingMonthList( getDateYearList( birdDateInfo ) );
            getPlLumpExtractConditionDto().setTargetMonthList(  getDateMonthList( birdDateInfo, FC_MODE ) );
            getPlLumpExtractConditionDto().setPeriodMonthFromList(  getDateMonthList( birdDateInfo, FC_MODE ) );
            getPlLumpExtractConditionDto().setPeriodMonthToList(  getDateMonthList( birdDateInfo, FC_MODE ) );

// start add 20060606 xtoshi 直営PLの年月コンボボックス内容変更対応
            
            // 基準年月の取得(直営PLデータのMAX年月を取得)
            getPlMaxMonthLogic = getGetPlMaxMonthLogic();
            getPlLumpExtractConditionDto().setPlMaxMonth( getPlMaxMonthLogic.execute() );
            
            getPlLumpExtractConditionDto().setTargetMonthRcList(  getDateMonthList( birdDateInfo, RC_MODE ) );
            getPlLumpExtractConditionDto().setPeriodMonthFromRcList(  getDateMonthList( birdDateInfo, RC_MODE ) );
            getPlLumpExtractConditionDto().setPeriodMonthToRcList(  getDateMonthList( birdDateInfo, RC_MODE ) );

// end
            
        } else {
        	if ( getOwnerSearchDto() !=  null ) {
                if ( getOwnerSearchDto().getOnerCd() != null ) {
            		getPlLumpExtractConditionDto().setOwnerCd( getOwnerSearchDto().getOnerCd() ) ;
                    getOwnerSearchDto().clear();
                }
            }
        }
        return null;
    }
    
    /**
     * オーナー選択画面へ遷移
     * @return 
     */
    public String accessOwner() {
        
        getOwnerSearchDto().clear();
    	getOwnerSearchDto().setNavigationCase( PLAll_VIEW_ID);
        getOwnerSearchDto().setInitFlag(true);
        return   OWNER_VIEW_ID;
    }
    

    /**
     * 年度リストの取得
     * @param birdDateInfo
     * @return List
     */
    public List getDateYearList( BirdDateInfo birdDateInfo ) {
    	List yearList = new ArrayList();
        String sysDate = birdDateInfo.getSysDate();
        DateFormatter formatter = new DateFormatter();
        
        String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        String sysNendo = DateManager.getCurrentYear( sysMonth );
        
        for ( int i = 0; i < NENGETSU_DISPLAY_YEAR; i++ ) {
            String year = "";
            try {
                year = DateManager.getPrevYear(sysNendo, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理");
            }

            SelectItem item = null;
            if ( year.equals( sysNendo )) {
                item = new SelectItem( year,"当年度");  
            } else {
            	item = new SelectItem( year, "前年度");
            }
            yearList.add(item);    
        }
        
        return yearList;
        
    }
    
    /**
     * 年月リストの取得
     * @param birdDateInfo
     * @return List
     */
    public List getDateMonthList( BirdDateInfo birdDateInfo, String fcRcKbn) {
        List monthList = new ArrayList();
        String sysDate = birdDateInfo.getSysDate();
        DateFormatter formatter = new DateFormatter();
        
// start add 20060606 xtoshi 直営PLの年月コンボボックス内容変更対応
//
//      String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
//      
//       for ( int i = 1; i <= NENGETSU_DISPLAY_MONTH; i++ ) {
//           String month = "";
//           try {
//               month = DateManager.getPrevMonth(sysMonth, i);
//           }
//           catch (Exception ex) {
//               throw new FtlSystemException("条件画面初期処理");
//           }
//                                    
//           SelectItem item = new SelectItem(
//                                       month, 
//                                       formatter.format(month, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM)); 
//           monthList.add(item);    
//       }

        if(fcRcKbn.equals(FC_MODE)){
            //FCの場合
            
            String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
       
            for ( int i = 1; i <= NENGETSU_DISPLAY_MONTH; i++ ) {
                String month = "";
                try {
                    month = DateManager.getPrevMonth(sysMonth, i);
                }
                catch (Exception ex) {
                    throw new FtlSystemException("条件画面初期処理");
                }
                                     
                SelectItem item = new SelectItem(
                                                month, 
                                                formatter.format(month, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM)); 
                monthList.add(item);    
            }
            
        }else{
            //RCの場合
            
            try {
                // 基準年月(直営PLデータのMAX年月を取得)
                String plMonth = getPlLumpExtractConditionDto().getPlMaxMonth();
                // 基準年月の前年度を求める
                String plNendo = DateManager.getPrevYear(DateManager.getCurrentYear(plMonth), 1);
                // 表示最終月（この月は表示しない）
                String lastNengetu = plNendo + "03";
                for (int i = 0; !DateManager.getPrevMonth(plMonth, i).equals(lastNengetu); i++) {
                    String month = DateManager.getPrevMonth(plMonth, i);
                    
                    SelectItem item = new SelectItem(month,
                                                      formatter.format(month, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM));
                    monthList.add(item);
                    
                }
            }
            catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理");
            }
            
        }
// end
        
        return monthList;
        
    }
    
}
