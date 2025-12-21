/*
 * 作成日: 2006/08/04
 *
 */
package jp.co.isid.mos.bird.bill.buyinglistview.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bill.buyinglistview.action.BuyingListViewAction;
import jp.co.isid.mos.bird.bill.buyinglistview.dto.BuyingListViewDto;
import jp.co.isid.mos.bird.bill.buyinglistview.entity.UIOnerCd;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetExistDataYearLogic;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetOnerCdLogic;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetOnerMiseCntLogic;
import jp.co.isid.mos.bird.bill.buyinglistview.logic.GetPDFListLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.InvalidRelationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.exception.TooManyResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * お買上明細詳細
 *
 * @author xlee
 */
public class BuyingListViewActionImpl implements BuyingListViewAction {

	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BBS012A01";

    /** アクションID定義:検索アクション */
    public static final String execute_ACTION_ID    = "BBS012A02";

	/** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_CONDITION    = "BBS012V01";

    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_ONERSEARCH = "BCO006V01";

    /** ユーザータイプ：本部 */
    private static final String USER_TYPE_HONBU = "01";

    /** ユーザータイプ：オーナー */
    private static final String USER_TYPE_ONER = "02";

// delete 2023/02/14 USI範  begin
//    /** 検収日付のFROM-最近２ヶ月が選択された場合*/
//    private static final String DATE_FROM = "01";
//
//    /** 検収日付のTO-最近２ヶ月が選択された場合 */
//    private static final String DATE_TO = "99";
//
//    /** 検収日付のFROM-年が選択された場合 */
//    private static final String DATE_FROM_FULL = "010100";
//
//    /** 検収日付のTO-年が選択された場合 */
//    private static final String DATE_TO_FULL = "123199";
// delete 2023/02/14 USI範  end
// add 2023/02/14 USI範  begin
    /** 対象期間From　システム日付のデフォルト設定*/
    private static final String DATE_FROM = "01";
// add 2023/02/14 USI範  begin

	/** お買上明細：Request */
	private BuyingListViewDto buyingListViewDto;

    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;

    /** オーナーコード取得ロジック */
    private GetOnerCdLogic getOnerCdLogic;

    /** 請求書PDF情報取得ロジック */
    private GetPDFListLogic getPDFListLogic;

    /** 請求データ存在年取得ロジック */
    private GetExistDataYearLogic getExistDataYearLogic;

    /** オーナー保有店舗数取得ロジック */
    private GetOnerMiseCntLogic getOnerMiseCntLogic;

    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;

    /** オーナー情報 */
    private OwnerSearchDto ownerSearchDto;

    /**
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 日付情報を設定します。
     * @param birdDateInfo 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * お買上詳細明細情報DTOを取得します。:Request
     * @return お買上詳細明細情報DTO
     */
    public BuyingListViewDto getBuyingListViewDto() {
        return buyingListViewDto;
    }

    /**
     * お買上詳細明細情報DTOを設定します。
     * @param buyingListViewDto　お買上詳細明細情報DTO
     */
    public void setBuyingListViewDto(BuyingListViewDto buyingListViewDto) {
        this.buyingListViewDto = buyingListViewDto;
    }

    /**
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetOnerCdLogic getGetOnerCdLogic() {
        return getOnerCdLogic;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetOnerCdLogic(GetOnerCdLogic getOnerCdLogic) {
        this.getOnerCdLogic = getOnerCdLogic;
    }


    /**
    *　請求書PDF情報取得ロジックを取得します。
    * @return 請求書PDF情報取得ロジック
    */
   public GetPDFListLogic getGetPDFListLogic() {
       return getPDFListLogic;
   }

   /**
    * 請求書PDF情報取得ロジックを設定します。
    * @param getPDFListLogic　請求書PDF情報取得ロジック
    */
   public void setGetPDFListLogic(GetPDFListLogic getPDFListLogic) {
       this.getPDFListLogic = getPDFListLogic;
   }

    /**
     * 初期処理
     *
     * @return 画面遷移情報
     */
    public String initialize() {
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

        BuyingListViewDto buyingListViewDto = (BuyingListViewDto) container.getComponent("buyingListViewDto");

        GetOnerCdLogic getOnerCdLogic = (GetOnerCdLogic) container.getComponent("buyinglistview.getOnerCdLogic");
        GetPDFListLogic getPDFListLogic = (GetPDFListLogic) container.getComponent("buyinglistview.getPDFListLogic");
        //BIRD内画面から遷移された場合、メニューから遷移された場合、初期処理をおこなう。
        // 初期処理
        if (pullDownMenuDto.isClearFlg() || (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
        	//メニューから遷移された場合、DTO初期化する
        	buyingListViewDto.clear();
        	// ユーザタイプ判定
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            // ユーザID
            String userId =  birdUserInfo.getMstUser().getUser_id();
            //検収日付
            String kenshuDate = birdDateInfo.getKenshuDt();

            //検索条件の日付
            String toDate = "";
            String frDate = "";
// add 2023/02/14 USI範  begin
        	//検索条件の金額
            String kinGaku = "";
            String kinGakuTo = "";
// add 2023/02/14 USI範 end
// delete 2023/02/14 USI範  begin
        	//対象期間：検収日付
//        	List listTaishoKikan = new ArrayList();
//            SelectItem item0 = new SelectItem("0000", "直近2ヶ月"); //default
//            listTaishoKikan.add(item0);
// delete 2023/02/14 USI範  end

// add 2023/02/14 USI範  begin
            //対象期間　システム日付のデフォルト設定
//add 2023/04/27 USI金 begin
            String sysDate = birdDateInfo.getSysDate();
//add 2023/04/27 USI金 end
            String systemTimeFrom = "";
            try {
//modify 2023/04/27 USI金 begin
//            	systemTimeFrom = DateManager.getPrevMonth(getSystemTime().substring(0,6),1)+ DATE_FROM;
            	systemTimeFrom = DateManager.getPrevMonth(sysDate.substring(0,6),1)+ DATE_FROM;
//modify 2023/04/27 USI金 end
            }
            catch (Exception ex) {
                throw new FtlSystemException("日付処理", null, ex);
            }
            getBuyingListViewDto().setfrDate(systemTimeFrom);
//modify 2023/04/27 USI金 begin
//            getBuyingListViewDto().settoDate(getSystemTime().substring(0,8));
//            String systemTimeTo = getSystemTime().substring(0,8);
            getBuyingListViewDto().settoDate(sysDate);
            getBuyingListViewDto().setSysDate(sysDate);
            String systemTimeTo = sysDate;
//modify 2023/04/27 USI金 end
// add 2023/02/14 USI範 end
// delete 2023/02/14 USI範 begin
            //請求データ存在年取得
//            List listExistData = getGetExistDataYearLogic().execute();
//            for (Iterator ite = listExistData.iterator(); ite.hasNext();) {
//                UISeikyuPDFInfo entity = (UISeikyuPDFInfo) ite.next();
//
//                SelectItem item = new SelectItem(String.valueOf(entity.getSeikyuDt()), entity.getSeikyuDt() + "年");
//                listTaishoKikan.add(item);
//            }
// delete 2023/02/14 USI範 end
//            int tmpYY = Integer.parseInt(DateManager.getCurrentYear(kenshuDate));
//            for(int i = tmpYY; i > tmpYY-4; i--) {
//            	SelectItem item = new SelectItem(String.valueOf(i),String.valueOf(i)+"年");
//            	listTaishoKikan.add(item);
//            }，
// delete 2023/02/14 USI範  begin
//             buyingListViewDto.setListCondTaishoKikan(listTaishoKikan);
// delete 2023/02/14 USI範 end
        	//ユーザータイプをセッションに保持
        	buyingListViewDto.setUserTypeCd(userTypeCd);
        	//メニューフラグfalseにセット
        	pullDownMenuDto.setClearFlg(false);

        	//本部ユーザーの場合
            if (userTypeCd.equals(USER_TYPE_HONBU)) {
            	//検索項目の初期化:
                buyingListViewDto.setOnerCd("");
            } else if (userTypeCd.equals(USER_TYPE_ONER)) {

            	//オーナーユーザーの場合:オーナーコード取得
            	UIOnerCd uiOnerCd = getOnerCdLogic.execute(userId);
            	//オーナーコード
            	String onerCd = uiOnerCd.getOnerCd();
            	buyingListViewDto.setOnerCd(onerCd);

            	//ダウンロードするPDFファイルを選択する為に使用
            	buyingListViewDto.setCondKbn("1");

// delete  2023/02/14 USI範  begin
            	//請求書PDF情報の検索
        		//初期化処理：対象期間がち直近2ヶ月
//            	try {
//					frDate = DateManager.getPrevMonth(kenshuDate.substring(0,6),2)+ DATE_FROM;
//				} catch (Exception e) {
//					throw new FtlSystemException("お買上げ明細照会初期処理");
//				}
//            	toDate = kenshuDate.substring(0,6) + DATE_TO;
// delete 2023/02/14 USI範  end
// modify 2023/02/14 USI範  begin
//            	/*請求書PDF情報を検索
//            	  param1:オーナーコード-onerCd
//          	      param2:From年月-frDate
//          	      param3:TO年月-toDate
//          	    */
//            	/*請求書PDF情報を検索
//            	  param1:オーナーコード-onerCd
//          	      param2:From年月-frDate
//          	      param3:TO年月-toDate
//          	      param4:From年月-kinGaku
//          	      param5:TO年月-kinGakuTo
//          	    */
// modify 2023/02/14 USI範  end
// modify 2023/02/14 USI範  begin
 //           	List pdfInfoList = getPDFListLogic.execute(onerCd, frDate, toDate);
             	List pdfInfoList = getPDFListLogic.execute(onerCd, systemTimeFrom,systemTimeTo, kinGaku, kinGakuTo);
// modify 2023/02/14 USI範  end
// add 2023/02/14 USI範  begin
             	   if(pdfInfoList.size() > 2000) {
                    		throw new TooManyResultException("(該当件数:"+pdfInfoList.size()+"件/最大:2000件)"); //E0105
                    	 }
// add 2023/02/14 USI範  end
            	    buyingListViewDto.setBuyingDataList(pdfInfoList);
//                    オーナー保有店舗数種特
                  getBuyingListViewDto()
                     	.setOnerMiseCnt(getOnerMiseCntLogic
// modify 2023/02/14 USI範  begin
 //                                       .execute(onerCd, frDate.substring(0, 6), toDate.substring(0, 6)));
                               			.execute(onerCd, systemTimeFrom.substring(0,6),systemTimeTo.substring(0,6)));
// modify 2023/02/14 USI範  end
            } else {
                throw new CannotAccessException();
            }
        } else {
			// オーナー選択から戻り
	        OwnerSearchDto ownerSearchDto = (OwnerSearchDto) container.getComponent(OwnerSearchDto.class);
			if(ownerSearchDto.isActionFlag()){
	            //検索条件の設定
				buyingListViewDto.setCondOnerCd(ownerSearchDto.getOnerCd());
				ownerSearchDto.setActionFlag(false);
			}
        }
        //2006/10/27 追加 start
        // BIRD内画面から遷移された場合の処理
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            String onerCd = getCommonCodeDto().getOnerCd();
            //本部ユーザー
            if(buyingListViewDto.getUserTypeCd().equals(USER_TYPE_HONBU)) {
                getBuyingListViewDto().setCondOnerCd(onerCd);
            //オーナユーザー
            } else if(buyingListViewDto.getUserTypeCd().equals(USER_TYPE_ONER)) {
                 getBuyingListViewDto().setOnerCd(onerCd);
            }
// delete  2023/02/14 USI範  begin
            //直近２か月
//            getBuyingListViewDto().setCondTaishoKikanCd("0000");
// delete  2023/02/14 USI範  end

            //結果表示
            execute();
        }
        //2006/10/27 追加 end

        // 自画面へ遷移
        return null;
    }

    /**
     * オーナー検索フォーム
     *
     * @return　オーナー検索画面
     */
    public String callOnerForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(VIEWID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getBuyingListViewDto().getCondCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //オーナーコードを保持
        getBuyingListViewDto().setOnerCd(getBuyingListViewDto().getOnerCd());
        return VIEWID_ONERSEARCH;
    }

    /**
     * お買上詳細情報を取得します。
     *
     * @return　遷移する画面
     */
    public String execute() {
// modify 2023/02/14 USI範  begin
    	//　オーナーコードチェック
    	// オーナーコード、金額チェック
// modify 2023/02/14 USI範  end
    	validate();

    	//本部ユーザーの場合、オーナーコードは画面から入力されたオーナーコードを設定する
    	//オーナユーザーの場合はセッションに保持されているオーナーコードを設定する
    	//オーナーコード取得する
    	String onerCd = "";
// delete  2023/02/14 USI範  begin
//    	String frDate = "";
//    	String toDate = "";
// delete  2023/02/14 USI範  end
    	//検索結果はクリア
    	getBuyingListViewDto().setBuyingDataList(null);
// delete  2023/02/14 USI範  begin
    	//検収日付
//    	String kenshuDate = getBirdDateInfo().getKenshuDt();

//    	if(getBuyingListViewDto().getCondTaishoKikanCd().equals("0000")) {
//    		//直近2ヶ月を選択した場合
//
//        	try {
//				frDate = DateManager.getPrevMonth(kenshuDate.substring(0,6), 2) + DATE_FROM;
//			} catch (Exception e) {
//				 throw new FtlSystemException("お買上げ明細照会検索処理");
//			}
//        	toDate = kenshuDate.substring(0,6) + DATE_TO;
//    	} else {
        //検収日付
//    	frDate = getBuyingListViewDto().getCondTaishoKikanCd() + DATE_FROM_FULL; //010100
//    	toDate = getBuyingListViewDto().getCondTaishoKikanCd() + DATE_TO_FULL; //123199
//    	}
// delete  2023/02/14 USI範  end
        //検収日付
// add 2023/02/14 USI範  begin
    	//画面対象期間、金額取得
    	String frDate = getBuyingListViewDto().getfrDate();
    	String toDate = getBuyingListViewDto().gettoDate();
    	String kinGaku =getBuyingListViewDto().getKinGaku();
    	String kinGakuTo = getBuyingListViewDto().getKinGakuTo();
    	//SQL用　文字列入れ替え
    	if(frDate != null) {
    		frDate = frDate.trim();
    	}
    	if(toDate != null) {
    		toDate = toDate.trim();
    	}
    	if(kinGaku != null) {
    		kinGaku = kinGaku.trim();
    	}
    	if(kinGakuTo != null) {
    		kinGakuTo = kinGakuTo.trim();
    	}
// add 2023/02/14 USI範  end

    	if(getBuyingListViewDto().getUserTypeCd().equals(USER_TYPE_HONBU)) {
    		//本部ユーザー
    		onerCd = getBuyingListViewDto().getCondOnerCd();
// add 2023/02/14 USI範  begin
        	//SQL用　文字列入れ替え
        	if(onerCd != null) {
        		onerCd = onerCd.trim();
        	}
// add 2023/02/14 USI範  end
    	} else if(getBuyingListViewDto().getUserTypeCd().equals(USER_TYPE_ONER)) {
    		//オーナユーザー
    		onerCd = getBuyingListViewDto().getOnerCd();
    	}
// modify 2023/02/14 USI範  begin
    	/*請求書PDF情報を検索
    	  param1:オーナーコード-onerCd
    	  param2:From年月-frDate
    	  param3:TO年月-toDate
    	*/
    	/*請求書PDF情報を検索
  	      param1:オーナーコード-onerCd
  	      param2:From年月-frDate
  	      param3:TO年月-toDate
  	      param4:From金額-kinGaku
  	      param5:TO金額-kinGakuTo
  	    */
// modify 2023/02/14 USI範  end
// delete 2023/02/14 USI範  begin
//    	List pdfInfoList = getGetPDFListLogic().execute(onerCd, frDate, toDate);
//    	getBuyingListViewDto().setBuyingDataList(pdfInfoList);
// delete 2023/02/14 USI範  end
// add 2023/02/14 USI範  begin
//modify 2023/05/09 ISID-AO小林 begin
    	//入力チェック
    	if(! isNull(frDate)  || ! isNull(toDate)) {
        //対象期間from、対象期間toの入力チェック
            	DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
            	if( ! isNull(frDate) && !dateVerifier.validate(frDate)) {
            		throw new InputConstraintException("対象期間From", "YYYYMMDD形式", "");//E0508  対象期間From、YYYYMMDD形式
            		}else if ( ! isNull(toDate) && !dateVerifier.validate(toDate)) {
            			throw new InputConstraintException("対象期間To", "YYYYMMDD形式", "");//E0508  対象期間To、YYYYMMDD形式
            		}
//modify 2023/05/09 ISID-AO小林 end
    	//対象期間toは対象期間fromより大きい	の場合、エラー処理
            	if (! isNull(frDate) && ! isNull(toDate) && frDate.compareTo(toDate) > 0) {
            			throw new NotRelevantException("対象期間の範囲指定","対象期間From ≦ 対象期間To");//E0606　対象期間の範囲指定、対象期間From ≦ 対象期間To
            }
//modify 2023/05/09 ISID-AO小林 begin
    	//modify 2023/04/26 ISID-AO小林 begin
        //入力9年以内の日付チェック
//modify 2023/04/27 USI金 begin
//            	int systemDate = Integer.parseInt(getSystemTime().substring(0,4));
            	int systemDate = Integer.parseInt(birdDateInfo.getSysDate().substring(0,4));
//modify 2023/04/27 USI金 end
            	if(! isNull(frDate)) {
                	int frDateNen = Integer.parseInt(frDate.substring(0,4));
            	if( systemDate - frDateNen >=9 ) {
            		throw new InvalidRelationException("対象期間From", "9年以上前の日付");//E0603 対象期間From、9年以上前の日付
            		}
            	}
            	if(! isNull(toDate)) {
                	int toDateNen = Integer.parseInt(toDate.substring(0,4));
            	if( systemDate - toDateNen >=9){
            		throw new InvalidRelationException("対象期間To", "9年以上前の日付");//E0603 対象期間To、9年以上前の日付
            	}
            	}
    		}
    	//modify 2023/04/26 ISID-AO小林 end
//modify 2023/05/09 ISID-AO小林 end
    	//オーナーコード、対象期間、金額入力チェック
    	int inputOnerCd= 0;
    	int inputDate= 0;
    	int inputKinGaku= 0;
    	if(! isNull(onerCd) && !onerCd.equals("")) {
    		inputOnerCd = 1;
        }
    	if(! isNull(frDate) || ! isNull(toDate)) {
    		inputDate = 1;
    	}
        if(! isNull(kinGaku) || ! isNull(kinGakuTo)) {
        	inputKinGaku = 1;
    	}
        if(inputOnerCd + inputDate+ inputKinGaku < 2) {
        	if(buyingListViewDto.getUserTypeCd().equals(USER_TYPE_HONBU)){
        //本部の場合、入力項目必須検索条件チェック
	 			throw new NotNullException("オーナーコード、対象期間、金額のいずれか2項目"); //E0204 オーナーコード、対象期間、金額のいずれか2項目
    	//オーナーの場合、入力項目必須検索条件チェック
        	}else {
	 			throw new NotNullException("対象期間、金額のどちらか"); //E0204 対象期間、金額のどちらか
        	}
        }else {
        //事前に抽出対象件数のチェック
    		List pdfInfoList = getGetPDFListLogic().execute(onerCd, frDate, toDate,kinGaku,kinGakuTo);
           	 if(pdfInfoList.size() > 2000) {
           		throw new TooManyResultException("(該当件数:"+pdfInfoList.size()+"件/最大:2000件)"); //E0105
           	 }
        // お買上明細List取得
           	 getBuyingListViewDto().setBuyingDataList(pdfInfoList);
        }
// add 2023/02/14 USI範  end
    	//並べ順の初期値をセットする
        	getBuyingListViewDto().setCondKbn("1");

        //オーナー保有店舗数種特
// add 2023/02/14 USI範  begin
        	if(! isNull(frDate)) {
        		frDate = frDate.substring(0, 6);
        	}
        	if(! isNull(toDate)) {
        		toDate = toDate.substring(0, 6);
        	}
// add 2023/02/14 USI範  end
        	getBuyingListViewDto()
        	.setOnerMiseCnt(getOnerMiseCntLogic
// modify 2023/02/14 USI範  begin
//                            .execute(onerCd, frDate.substring(0, 6), toDate.substring(0, 6)));
                         .execute(onerCd, frDate, toDate));
// modify 2023/02/14 USI範  end
        return null;
    }

    //オーナー検索
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}

    //オーナー検索
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}

    /**
     * 入力チェック
     */
    private void validate() {
// modify 2023/02/14 USI範  begin
//        // オーナーコード必須チェック
    	//オーナーコード、金額チェック
    	String onerCd = getBuyingListViewDto().getCondOnerCd();
    	String kinGaku =getBuyingListViewDto().getKinGaku();
    	String kinGakuTo= getBuyingListViewDto().getKinGakuTo();
// modify 2023/02/14 USI範  end
//         半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		NumericVerifier numericVerifier = new NumericVerifier();
		if (onerCd != null && !onerCd.equals("")
				&& !codeVerifier.validate(onerCd)) {
			//以前検索結果がある場合はクリア
			if(getBuyingListViewDto().getBuyingDataListSize() > 0) {
				getBuyingListViewDto().setBuyingDataList(null);
			}
			throw new InvalidInputException("オーナーコード", "onerCd", "");
		}
// add 2023/02/14 USI範  begin
		//金額From入力チェック
		if ( !numericVerifier.validate(kinGaku)) {
			//以前検索結果がある場合はクリア
			if(getBuyingListViewDto().getBuyingDataListSize() > 0) {
				getBuyingListViewDto().setBuyingDataList(null);
			}
    		throw new InputConstraintException("金額From", "数字", "");//E0508  金額From、数字
		}
		//金額To入力チェック
		if (  !numericVerifier.validate(kinGakuTo)) {
			//以前検索結果がある場合はクリア
			if(getBuyingListViewDto().getBuyingDataListSize() > 0) {
				getBuyingListViewDto().setBuyingDataList(null);
			}
    		throw new InputConstraintException("金額To", "数字", "");//E0508  金額To、数字
		}
		//金額Toは金額Fromより大きい	の場合、エラー処理
		if(! isNull(kinGaku) && ! isNull(kinGakuTo)) {
			if (Long.parseLong(kinGaku) > Long.parseLong(kinGakuTo)) {
				if(getBuyingListViewDto().getBuyingDataListSize() > 0) {
					getBuyingListViewDto().setBuyingDataList(null);
			}
				throw new NotRelevantException("金額の範囲指定","金額From ≦ 金額To");//E0606　金額の範囲指定、金額From ≦ 金額To
		}
				}
// add 2023/02/14 USI範  end
    }
// add 2023/02/14 USI範  begin
    /**
     * システムタイム取得
     */
    public static  String getSystemTime() {
    Date imajikan = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN);
    String ret = "";
    synchronized (sdf) {
        sdf.setLenient(true);
        ret = sdf.format(imajikan);
    }
    return ret;
    }

    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
// add 2023/02/14 USI範  end

    public GetExistDataYearLogic getGetExistDataYearLogic() {
        return getExistDataYearLogic;
    }

    public void setGetExistDataYearLogic(GetExistDataYearLogic getExistDataYearLogic) {
        this.getExistDataYearLogic = getExistDataYearLogic;
    }

    /**
     * 共通コードDTO取得
     * @return 共通コードDTO
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) SingletonS2ContainerFactory.getContainer().getComponent(CommonCodeDto.class);
    }

    public GetOnerMiseCntLogic getGetOnerMiseCntLogic() {
        return getOnerMiseCntLogic;
    }

    public void setGetOnerMiseCntLogic(GetOnerMiseCntLogic getOnerMiseCntLogic) {
        this.getOnerMiseCntLogic = getOnerMiseCntLogic;
    }

	}

