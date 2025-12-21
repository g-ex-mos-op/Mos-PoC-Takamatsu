/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.bizreport.common.logic.CompanyListLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.AccessCtlCheckLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 推移表共通LOGIC【条件部情報取得】
 * 
 * 作成日:2013/05/07
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** 売上推移表の条件部情報取得共通ロジックID */
    public static final String LOGIC_ID = "BBR000L02";
    /* 業務管理共通LOGIC【会社データ】 */
    private CompanyListLogic companyListLogic;
    /* BIRD共通LOGIC【表示対象取得】*/
    private GetHyojiTaishoLogic getHyojiTaishoLogic;
    /** FW共通LOGC【メニュー権限チェック】 */
    private AccessCtlCheckLogic accessCtlCheckLogic;
	
	/**
	 * 実行処理
	 * @return　SuiiRefConditionDto 推移表共通DTO【条件部情報】
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.ConditionLogic#execute()
	 */
	public SuiiRefConditionDto execute() {
		//０．事前条件判断
		validate();
		String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
		boolean isLimit = getBirdUserInfo().isLimit();
		//１．推移表共通DTO【条件部情報】を生成します。
		SuiiRefConditionDto conditionDto = new SuiiRefConditionDto();
		
		//２．業務管理共通LOGIC【会社リスト】.実行を行い、戻り値List[[会社]]を取得します。
    	Map mapParam = new HashMap();
    	mapParam.put(CompanyListLogic.PK_FOREING_IN, new Boolean(true));
        List listCompany = getCompanyListLogic().execute(mapParam);
        
        //３．推移表共通DTO【条件部情報】.List[[会社]]へ処理２のList[[会社]]を設定します。
        conditionDto.setListCompany(listCompany);
        
        //４．コード定数クラス【店舗種別】.リスト取得を実行しList[UILists[店舗種別]]を取得します。
        List listTenpoShubetu = TenpoShubetu.getUIListPullDownList(userTypeCd);
        //５．処理４のList[[店舗種別]]の件数分のList[[前年データ種別]]を設定します。
        for (int ts=0; ts<listTenpoShubetu.size(); ts++) {
        	UILists uiTenpoShubetu = (UILists)listTenpoShubetu.get(ts);
            uiTenpoShubetu.setListData(ZennenDataShubetu.getPullDownList(
            		userTypeCd, uiTenpoShubetu.getKey()));
        }
        //６．推移表共通DTO【条件部情報】.List[UILists[店舗種別]]へ処理４のList[UILists[店舗種別]]を設定します。
        conditionDto.setListTenpoShubetu(listTenpoShubetu);
        
        //７．定数クラス【対象期間】.リスト取得を実行しList[UILists[対象期間]]を取得します。
        List listTaishoKikan  = TaishoKikan.getUIListPullDownList(userTypeCd);
        String appDate = getBirdDateInfo().getAppDate();
        //８．処理７のList[UILists[対象期間]]の件数分、下記の処理を行います。
        for (int tk=0; tk<listTaishoKikan.size(); tk++) {
        	//UILists[対象期間]へ期間指定を設定します。
        	UILists uiTaishoKikan = (UILists)listTaishoKikan.get(tk);
        	String taishoKikan = uiTaishoKikan.getKey();
        	if(TaishoKikan.CODE_MONTH.equals(taishoKikan)) {
        		//UILists[対象期間].キー＝”MONTH”の場合、アプリ日付の年月から過去13ヶ月分のリストを生成します。
        		uiTaishoKikan.setListData(executeListMonth(appDate, 13));
        	}
        	if(TaishoKikan.CODE_NENDO.equals(taishoKikan)) {
        		//UILists[対象期間].キー＝”NENDO”の場合、アプリ日付の年度から過去3年度分のリストを生成します。
        		uiTaishoKikan.setListData(executeListNendo(appDate, 3));
        	}
        }
        //９．推移表共通DTO【条件部情報】.List[UILists[対象期間]]へ処理７のList[[対象期間]]を設定します。
        conditionDto.setListTaishoKikan(listTaishoKikan);
        //１０．List[[ブロック]]を生成します。
        List listBlock = new ArrayList(0);
        //１１．会社別に値が異なる条件情報を取得します。
        for (int c=0; c<listCompany.size(); c++) {
            //for-c-1.List[[会社]]のForインデックス番目のCodCompany[会社]を取得します。
            CodCompany eCompany = (CodCompany)listCompany.get(c);
            //for-c-2.CodCompany[会社].会社コードを取得し、変数.現行会社コードとします。
            String companyCd = eCompany.getCompanyCd();
            //for-c-3.推移表共通CODE【対象条件コード定数クラス】対象条件リストを取得を行い、List[[対象条件]]を取得します。
            List listTaishoJoken = TaishoJoken.getUIListsPullDownList(userTypeCd, companyCd, isLimit);
            Map map = new HashMap();
            //会社コード
            map.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, companyCd);
        	map.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, appDate);
            //for-c-4.処理for-c-3のList[[対象条件]]の件数分下記の処理を行います。
            for (int tj=0; tj<listTaishoJoken.size(); tj++) {
            	//for-tj-1.Forインデックス番目のUILists[対象条件]を取得します。
            	UILists taishoJoken = (UILists)listTaishoJoken.get(tj);
            	String taishoJokenCd = taishoJoken.getKey();
            	//for-tj-2.本部ユーザーで、UILists[対象条件].キーが"個店(店舗)"の場合
            	if (UserType.isHonbu(userTypeCd)
            			&& TaishoJoken.CODE_MISE.equals(taishoJokenCd)) {
            		//処理for-c-4へ戻ります。
           			continue;
            	}
            	//for-tj-3.オーナーユーザーで「全店」の場合下記の処理を行います。
            	if (UserType.isOner(userTypeCd)
            			&& TaishoJoken.CODE_ALL.equals(taishoJokenCd))
            	{
            		//ONER-1.変数List[[オーナー]]を生成します。
            		List listOner = new ArrayList(0);
            		//ONER-2.CTL【BIRDユーザー情報】.List[[ユーザーオーナー情報取得]]から
            		//       変数.現行会社コードに該当するUIUserOner[オーナー情報]を取得します。
            		UIUserOner userOner = SuiiRefUtil.getUserOnerCd(getBirdUserInfo(), companyCd);
            		if(userOner !=null) {
            			//ONER-3.UIUserOner[オーナー情報]が有った場合、下記の処理を行います。
            			//1.CodHyojiTaisho[オーナー]を生成します。
	            		CodHyojiTaisho cod = new CodHyojiTaisho();
	            		//2.CodHyojiTaisho[オーナー].表示対象へUIUserOner[オーナー情報]オーナーコードを設定します。
	            		cod.setHyojitaishoCd(userOner.getOnerCd());
	            		//3.CodHyojiTaisho[オーナー].表示対象名称へTaishoJoken.CODE_ALLの名称を設定します。
	            		cod.setHyojitaishoName(TaishoJoken.getName(userTypeCd, taishoJokenCd));
	            		//4.変数List[[オーナー]]へCodHyojiTaisho[オーナー]を追加します。
	            		listOner.add(cod);
	            		//5.処理for-tj-1のUILists[対象条件].List[[データ]]へ変数List[[オーナー]]を設定します。
	            		taishoJoken.setListData(listOner);
            		}
            		//ONER-4.処理for-c-4へ戻ります。
            		continue;
            	}
            	//for-tj-3.業務管理共通LOGIC【表示対象取得】を実行し、戻り値Map[表示対象]を取得します。
            	map.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, taishoJokenCd);
            	Map reMap = getGetHyojiTaishoLogic().execute(map);
            	taishoJoken.setListData((List)reMap.get(GetHyojiTaishoLogicImpl.RK_LIST));
            	//for-tj-4.ブロック情報が有る場合は、
            	if(reMap.containsKey(GetHyojiTaishoLogicImpl.RK_LIST_BLOCK)) {
            		//List[[ブロック]]へMap[表示対象]からキー：ブロックリストの値を設定します。
            		listBlock = (List)reMap.get(GetHyojiTaishoLogicImpl.RK_LIST_BLOCK);
            	}
            }
            //for-c-5.推移表共通DTO【条件部情報】.List[[対象条件]]へ処理for-c-3のList[[対象条件]]を設定します。
            conditionDto.setListsTaishoJoken(companyCd, listTaishoJoken);
        }
        //１２．推移表共通DTO【条件部情報】.List[[ブロック]]へ処理１０のList[[ブロック]]を設定します。
        conditionDto.setListBlock(listBlock);
        //１３．MOSCARDメニュー権限設定(MOS CARDタブの表示制御用)
        //推移表共通DTO【条件部情報】.MOSCARDメニューアクセス権限判断値へ戻り値を設定します。
        conditionDto.setAccessMoscard(getAccessCtlCheckLogic().isAccessMenu(
        		getBirdUserInfo().getUserID(), SuiiRefUtil.VIEW_ID_M.substring(0,6)));
        //１４．NETORDERメニュー権限設定(MOS CARDタブの表示制御用)
        //推移表共通DTO【条件部情報】.NETORDERメニューアクセス権限判断値へ戻り値を設定します。
        conditionDto.setAccessNetorder(getAccessCtlCheckLogic().isAccessMenu(
        		getBirdUserInfo().getUserID(), SuiiRefUtil.VIEW_ID_N.substring(0,6)));
		//１４．推移表共通DTO【条件部情報】をリターンします。
		return conditionDto;
		//処理終了
	}
	/**
	 * 
	 * @param birdUserInfo
	 * @param birdDateInfo
	 */
	private void validate() {
        if (getBirdDateInfo() == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        if (getBirdUserInfo() == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
	}
    /**
     * 期間指定の種類を取得
     * 
     * アプリ年月含めて過去「リスト個数」月分を生成します。
     * 
     * @param String アプリ日付
     * @param int リスト個数
     * @return List 期間指定データ
     * @exception ApplicationException
     */
    private List executeListMonth(String appDate, int cnt) throws ApplicationException {
        List list = new ArrayList();
        String app = appDate.substring(0, 6);
        DateFormatter dformat = new DateFormatter();
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevMonth(app, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年月["+app+"]から["+index+"]を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = dformat.format(code, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
            list.add(index, createEntity(code, name));
        }
        if (list == null || list.size() == 0) {
            throw new NotExistException("期間指定 年月情報");
        }
        return list;
    }
    /**
     * 年度指定の種類を取得
     * 
     * アプリ年度含めて過去「リスト個数」年度分を生成します。
     * 
     * @param String アプリ日付
     * @param int リスト個数
     * @return List 期間指定データ
     * @exception ApplicationException
     */
    private List executeListNendo(String appDate, int cnt) throws ApplicationException {
        List list = new ArrayList();
        String app = DateManager.getCurrentYear(appDate);
        for (int index=0; index<cnt; index++) {
            String code = "";
            try {
                code = DateManager.getPrevYear(app, index);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年度["+app+"]から["+index+"]を引く際のDateManager.getPrevYearメソッド処理で例外が発生しました。"
                        , ex);
            }
            String name = code;
            list.add(index, createEntity(code, name));
        }
        if (list == null || list.size() == 0) {
            throw new NotExistException("期間指定 年度情報");
        }
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private CodKikanSitei createEntity(String code, String name) {
        CodKikanSitei entity = new CodKikanSitei();
        entity.setCode(code);
        entity.setName(name);
        return entity;
    }
	/**
	 * 業務管理共通LOGIC【会社データ】
	 * @return クラス変数companyListLogic を戻します。
	 */
	public CompanyListLogic getCompanyListLogic() {
		return companyListLogic;
	}
	/**
	 *  業務管理共通LOGIC【会社データ】
	 * @param logic を クラス変数companyListLogicへ設定します。
	 */
	public void setCompanyListLogic(CompanyListLogic logic) {
		this.companyListLogic = logic;
	}
	/**
	 * @return クラス変数getHyojiTaishoLogic を戻します。
	 */
	public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
		return getHyojiTaishoLogic;
	}
	/**
	 * @param getHyojiTaishoLogic を クラス変数getHyojiTaishoLogicへ設定します。
	 */
	public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic getHyojiTaishoLogic) {
		this.getHyojiTaishoLogic = getHyojiTaishoLogic;
	}
	/**
	 * @return クラス変数accessCtlCheckLogic を戻します。
	 */
	public AccessCtlCheckLogic getAccessCtlCheckLogic() {
		return accessCtlCheckLogic;
	}
	/**
	 * @param accessCtlCheckLogic を クラス変数accessCtlCheckLogicへ設定します。
	 */
	public void setAccessCtlCheckLogic(AccessCtlCheckLogic accessCtlCheckLogic) {
		this.accessCtlCheckLogic = accessCtlCheckLogic;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * CTL【BIRDユーザー情報】取得処理
     * @return birdUserInfo を戻します。
     */
    protected BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
	/**
	 * BIRD日付情報取得処理
	 * @return birdDateInfo を戻します。
	 */
    protected BirdDateInfo getBirdDateInfo() {
		return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
	}

}
