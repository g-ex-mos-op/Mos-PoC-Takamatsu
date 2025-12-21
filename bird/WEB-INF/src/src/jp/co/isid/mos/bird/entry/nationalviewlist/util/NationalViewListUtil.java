package jp.co.isid.mos.bird.entry.nationalviewlist.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListReqDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIAttendAbsentCntInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.impl.SearchLogicImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoResultException;



/**
 * 全国大会申込状況確認
 * static 処理保持クラス
 * 
 * @author xkinu
 *
 */
public class NationalViewListUtil {

    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";
    /** VIEWID：共通各種申込状況確認初期画面 */
    public static final String VIEW_ID_STASUSINFOCONDITION   = "BEN092V01";
    /** 画面ID */
    public static final String SCREEN_ID = "BEN016";
    /** VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /** VIEWID：照会画面 */
    public static final String VIEW_ID_CONFIRM= SCREEN_ID+"V02";
    /** 処理項目：性別 男 '0' */
    public static final String SEX_MAN = "0";
    /** 処理項目：性別 女 '1' */
    public static final String SEX_WOMAN = "1";
    
    /**
     * コンストラクター
     *
     */
    private NationalViewListUtil() {
        super();
    }
    /**
     * 共通初期化処理
     * 
     * １．SV選択画面から遷移してきた場合。
     * １－１．DTO【申込状況確認】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
     * １－２．SVを選択後遷移してきた場合。
     * １－３．DTO【SV選択】.遷移区分を初期値に戻す。
     * １－４．DTO【SV選択】.クリア処理を実行する。
     * ２．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
     * @param dto
     * @param svSearchDto
     * @return
     */
    public static int actionInitialize(NationalViewListDto dto, NationalViewListReqDto reqDto, SvSearchDto svSearchDto) {
        int windowId = reqDto.getWindowId();
        //１．SV選択画面から遷移してきた場合。
        if(svSearchDto.getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            //１－１．【DTO】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
            windowId = svSearchDto.getWindowId();
            reqDto.setWindowId(windowId);
            //１－２．SVを選択後遷移してきた場合。
            if(svSearchDto.getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                //1．SVコードを設定する。
                dto.setJokenSvCd(windowId, svSearchDto.getSvCd());
            }
            //１－３．DTO【SV選択】.遷移区分を初期値に戻す。
            svSearchDto.setReturnKind(SvSearchDto.RETURNKIND_INIT);
            //１－４．DTO【SV選択】.クリア処理を実行する。
            svSearchDto.clear();
        }
        return windowId;
    }
    /**
     * 共通アクション戻る処理
     * 
     * 画面の｢戻る｣ボタンを押した場合
     * @param dto
     * @param eventListDto
     * @return
     */
    public static String actionBack(NationalViewListDto dto, NationalViewListReqDto reqDto, EventListDto eventListDto){
        //１．DTO【申込状況確認】クリア処理を行う。
        dto.clear(reqDto.getWindowId());
        //２．共通DTO【各種イベント申込状況確認】リターンタイプに’戻る’の値を設定する。
        eventListDto.setReturnKind(EventListDto.RETURNKIND_BACK);
        //３．共通各種イベント申込状況確認画面VIEWIDをリターンする。
        return NationalViewListUtil.VIEW_ID_STASUSINFOCONDITION;
    }
    /**
     * 共通アクションSV検索処理
     * 
     * SV検索フォーム呼び出し処理
     * 
     * @param viewId 遷移元情報
     * @param dto
     * @param svSearchDto
     * @return
     */
    public static String actionCallSvForm(
            String viewId, NationalViewListDto dto, NationalViewListReqDto reqDto, SvSearchDto svSearchDto)  {
        int windowId = reqDto.getWindowId();
        //１．遷移元情報を設定
        svSearchDto.setNavigationCase(viewId);
        //２．初期化
        svSearchDto.setInitFlag(true);
        //３．複数WindowID
        svSearchDto.setWindowId(windowId);
        //４．会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(dto.getTargetCompanyCd());
        svSearchDto.setRCompanyCdList(listCompany);
        
        //５．現ウィンドウID の検索対象条件項目値の保管を行う。
        dto.holdJokenParam(reqDto);
        //６．SV検索画面遷移VIEWIDをリターンする。
        return NationalViewListUtil.VIEW_ID_SVSEARCH;
    }
    /**
     * 実行ボタン処理
     * 
     */
    public static void actionSearch(Map params
            , NationalViewListDto dto, NationalViewListReqDto reqDto, SearchLogic logic) {
        int windowId = reqDto.getWindowId();
        try {
            //１．現ウィンドウID の検索対象条件項目値の保管を行う。
            dto.holdJokenParam(reqDto);
            //２．ロジック【検索対象報取得】を実行し、[[出欠件数情報]]と[[申込状況一覧]]を取得する。            
            Map rparams = logic.execute(params);
            //２－１．処理２.結果戻り値から[[出欠件数情報]]を取得する。
            List listAttendance = (List)rparams.get(SearchLogicImpl.RK_LIST_ATTENDANCE);
            //２－２．[[出欠件数情報]]の取得結果がない場合は下記の処理を行う。
            if (listAttendance == null || listAttendance.size() == 0) {
                throw new NoResultException();
            }
            //２－３．処理２－１[[出欠件数情報]]結果の一番目[出欠件数情報]を取得する。
            UIAttendAbsentCntInfo eAttendCnt= (UIAttendAbsentCntInfo)listAttendance.get(0);
    
            //２－４．DTO【申込状況確認】へ処理２－３[出欠件数情報]を設定する。            
            dto.setEntityAttendAbsentInfo(windowId, eAttendCnt);
    
            //２－５．処理２.結果戻り値から[[申込状況一覧]]を取得する。
            List listSibuOnerStatusInfo = (List)rparams.get(SearchLogicImpl.RK_LIST_STATUS); 
            //２－６．処理２－５の[[申込状況一覧]]の取得結果が該当データなしの場合は、下記の処理を行う。
            if (listSibuOnerStatusInfo == null || listSibuOnerStatusInfo.size() == 0) {
                throw new NoResultException();
            }
            //２－７．DTO【申込状況確認】.検索結果リストへ処理２－５[[申込状況一覧]]の値を設定する。    
            dto.setListSearchData(windowId, listSibuOnerStatusInfo);
            dto.holdCsvParam(reqDto);
        
        }
        catch (NoResultException noRltEx){
            //Ⅰ．前回検索結果データをクリアするため、DTO【申込状況確認】検索結果クリア処理を実行する。
            dto.searchDataClear(windowId);
            //Ⅱ．Exception　MSG【E0102】を発生させる。
            throw noRltEx;
        }
    }
    /**
     * 対象CSVダウンロードデータ再検索処理
     * 
     */
    public static void actionSearch(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
            , NationalViewListDto dto, NationalViewListReqDto reqDto, SearchLogic logic) {
        int windowId = reqDto.getWindowId();
         //１．ロジック【検索対象報取得】用パラメーターを生成する。
        Map params = new HashMap();
        params.put(SearchLogicImpl.PK_USERINFO, birdUserInfo);
        params.put(SearchLogicImpl.PK_ENTITY_COURSE, dto.getEntityStatusInfo(windowId));
        params.put(SearchLogicImpl.PK_SYSDATE, birdDateInfo.getSysDate());
        params.put(SearchLogicImpl.PK_COMPANY_CD, dto.getTargetCompanyCd());
        params.put(SearchLogicImpl.PK_TAISHOJOKEN, reqDto.getTargetTaishoJoken());
        params.put(SearchLogicImpl.PK_SIBU_CD, reqDto.getTargetSibuCd());
        params.put(SearchLogicImpl.PK_SV_CD, reqDto.getTargetSvCd());
        params.put(SearchLogicImpl.PK_FROM_DT, dto.getEntityStatusInfo(reqDto.getWindowId()).getHonbuFromDt());
        actionSearch(params, dto, reqDto, logic);
    }
    /**
     * 対象CSVダウンロードデータ再検索処理
     * 
     */
    public static void actionSearchCsvData(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
            , NationalViewListDto dto, NationalViewListReqDto reqDto, SearchLogic logic) {
        int windowId = reqDto.getWindowId();
        //１．ロジック【検索対象報取得】用パラメーターを生成する。
        Map params = new HashMap();
        params.put(SearchLogicImpl.PK_USERINFO, birdUserInfo);
        params.put(SearchLogicImpl.PK_ENTITY_COURSE, dto.getEntityStatusInfo(windowId));
        params.put(SearchLogicImpl.PK_SYSDATE, birdDateInfo.getSysDate());
        params.put(SearchLogicImpl.PK_COMPANY_CD, dto.getTargetCompanyCd());
        params.put(SearchLogicImpl.PK_TAISHOJOKEN, dto.getCsvTaishoJoken(windowId));
        params.put(SearchLogicImpl.PK_SIBU_CD, dto.getCsvSibuCd(windowId));
        params.put(SearchLogicImpl.PK_SV_CD, dto.getCsvSvCd(windowId));
        params.put(SearchLogicImpl.PK_FROM_DT, dto.getEntityStatusInfo(reqDto.getWindowId()).getHonbuFromDt());
        actionSearch(params, dto, reqDto, logic);
    }

    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}