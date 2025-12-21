package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao.CodCompanyDao;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.logic.impl.GetGamenRoleLogicImpl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * 【条件項目情報の取得】ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = BunsAutoAmtRegistUtil.SCREEN_ID+"L01";
    /* DAO【会社リスト】*/
    private CodCompanyDao bunsAutoAmtRegistCodCompanyDao;
 
    public Map execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //２．リターン値Mapをインスタンス化する。
        Map remap = new HashMap();
        //ユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        String userId = userInfo.getUserID();
        
        //３．DAO【会社リスト】．検索 を実行し、
        //    実行結果[[会社リスト]]を取得する。
        List listCompany = getBunsAutoAmtRegistCodCompanyDao().select(userId);
        //４．処理３の実行結果をリターン値Mapへ格納する。
        remap.put(RK_LIST_COMPANY, listCompany);
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        GamenRoleDto gamenRoleDto = (GamenRoleDto)params.get(PK_DTO_GAMENROE);
        //コンテナーから共通ロジック【ユーザー汎用画面制御ロール情報】を取得します。
        GetGamenRoleLogic getGamenRoleLogic = (GetGamenRoleLogic) container.getComponent(GetGamenRoleLogicImpl.class);
        String flgDownload = "1";
        try {
            //５． 共通ロジック【ユーザー汎用画面制御ロール情報】.検索を下記の条件で実行し、 
            //     実行結果[[ダウンロード許可ロール情報]]を取得する。
            //     ＜条件＞画面ID＝’BBS026’
            //             ユーザーID＝パラメーター．BIRDユーザー情報.ユーザーID
            //             分類コード＝’01’
            gamenRoleDto.setGamenId(BunsAutoAmtRegistUtil.SCREEN_ID);
            gamenRoleDto.setUserId(userId);
            gamenRoleDto.setBunruiCd("01");
            getGamenRoleLogic.excute(gamenRoleDto);
        }catch(NotExistException noExistEx) {
            flgDownload = "0";
        }
        //６．処理５の実行結果もとに導き出した値をリターン値Mapへ格納する。
        remap.put(RK_FLG_DOWNLOAD, flgDownload);

        String flgRegist = "1";
        try {
            //７． 共通ロジック【ユーザー汎用画面制御ロール情報】.検索を下記の条件で実行し、 
            //     実行結果[[登録許可ロール情報]]を取得する。
            //     ＜条件＞画面ID    ＝’BBS026’
            //             ユーザーID＝パラメーター．BIRDユーザー情報.ユーザーID
            //             分類コード＝’02’
            gamenRoleDto.setGamenId(BunsAutoAmtRegistUtil.SCREEN_ID);
            gamenRoleDto.setUserId(userId);
            gamenRoleDto.setBunruiCd("02");
            getGamenRoleLogic.excute(gamenRoleDto);
        }catch(NotExistException noExistEx) {
            flgRegist = "0";
        }
        //８．処理７の実行結果もとに導き出した値をリターン値Mapへ格納する。
        remap.put(RK_FLG_REGIST, flgRegist);
        //９．[[対象条件]]を作成する。
        List listTaishoJoken = ConditionTaishoJoken.getPullDownList();
        //１０．[[対象条件]]をリターン値Mapへ格納する。
        remap.put(RK_LIST_TAISHOJOKEN, listTaishoJoken);
        
        //１１．リターン値Mapをリターンする。
        return remap;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //システム日付
        String sysDate = (String)params.get(PK_SYSDATE);
        if (BunsAutoAmtRegistUtil.isNull(sysDate)) {
            throw new MissingDataException("システム日付");
        }
        GamenRoleDto gamenRoleDtoo = (GamenRoleDto)params.get(PK_DTO_GAMENROE);
        if(gamenRoleDtoo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("汎用画面DTO");
        }
    }
    /**
     * @return bunsAutoAmtRegistCodCompanyDao を戻します。
     */
    public CodCompanyDao getBunsAutoAmtRegistCodCompanyDao() {
        return bunsAutoAmtRegistCodCompanyDao;
    }
    /**
     * @param bunsAutoAmtRegistCodCompanyDao 設定する bunsAutoAmtRegistCodCompanyDao。
     */
    public void setBunsAutoAmtRegistCodCompanyDao(CodCompanyDao dao) {
        this.bunsAutoAmtRegistCodCompanyDao = dao;
    }
}
