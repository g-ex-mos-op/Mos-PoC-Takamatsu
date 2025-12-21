package jp.co.isid.mos.bird.entry.basicviewlist.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.entry.basicviewlist.action.BasicViewListConfirmAction;
import jp.co.isid.mos.bird.entry.basicviewlist.dto.BasicViewListDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class BasicViewListConfirmActionImpl implements CommonAction, BasicViewListConfirmAction {

    /* アクションID */
    public static String initialize_ACTION_ID  = "BEN003A03";
    public static String cancel_ACTION_ID      = "BEN003A04";
    public static String downloadCsv_ACTION_ID = "BEN003A05";
    
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BEN003V01"; //条件画面
    
    /* ACTION */
    private CsvOutputAction basicViewListCsvOutputAction;
    
    /* LOGIC */

    
    
    /* DTO */
    // ベーシック研修申込状況確認Dto //
    private BasicViewListDto basicViewListDto;
   

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

	////////////////////
	/*     ACTION     */
	////////////////////

	
	/////////////////
	/*     DTO     */
	/////////////////
	/**
	 * ベーシック研修申込状況確認dtoの設定
	 * @return basicViewListDto を戻します。
	 */
	public BasicViewListDto getBasicViewListDto() {
		return basicViewListDto;
	}
	/**
	 * ベーシック研修申込状況確認dtoの設定
	 * @param basicViewListDto を設定。
	 */
	public void setBasicViewListDto(BasicViewListDto basicViewListDto) {
		this.basicViewListDto = basicViewListDto;
	}
	
	
	///////////////////
	/*     LOGIC     */
	///////////////////



        
	/**
     * ベーシック研修申込状況確認 照会画面 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
        
        return null;
    }

    
    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv() {

        // CSVダウンロード
        try {
            getBasicViewListCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    
    
    /**
     * 戻る遷移(条件画面へ)
     * @return 画面遷移情報
     */
    public String cancel() {
        
        return VIEWID_CONDITION;
    }
    

    public CsvOutputAction getBasicViewListCsvOutputAction() {
        return basicViewListCsvOutputAction;
    }
    public void setBasicViewListCsvOutputAction(
            CsvOutputAction basicViewListCsvOutputAction) {
        this.basicViewListCsvOutputAction = basicViewListCsvOutputAction;
    }
    
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
}