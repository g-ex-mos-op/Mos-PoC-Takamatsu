package jp.co.isid.mos.bird.bizreport.common.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dto.BizReportGroupDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodTaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.logic.BizReportCommonLogic;
import jp.co.isid.mos.bird.bizreport.common.logic.TaishoJokenListLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 対象条件データ検索ロジック
 * 
 * パラメーター：下記の値を保持したMapを引数とする。
 *               「companyCd」会社コード
 *               
 * 2008/06/16 エリア大定数追加
 * @author xkinu
 */
public class TaishoJokenListLogicImpl implements TaishoJokenListLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR000L11";
    /** パラメーター名：会社コード */
    public static final String PM_COMPANY_CD = KEY_COMPANY_CD;
    /** 対象条件：全社   */
    public static final String CODE_ALL = "ALL";
    /** コード値：セグメント */
    public static final String CODE_SEGMENT = "SEGMENT";
    /** 対象条件：事業本部 */
    public static final String CODE_JIGYOU = "JIGYOU";
    /** 対象条件：営業エリア */
    public static final String CODE_SLAREA = "SLAREA";
    /** 対象条件：支部 */
    public static final String CODE_SIBU = "SIBU";
    /** 対象条件：個店 */
    public static final String CODE_MISE = "MISE";
    /** 対象条件：支部（エリア大） */
    public static final String CODE_AREADAI = "AREADAI";

    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map map) throws ApplicationException {
        String companyCd = (String)map.get("companyCd");
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
    }
//    
    /**
     * 条件画面出力データ検索を行う
     * 
     * @param map
     * @return List
     * @throws ApplicationException
     */
    public List execute(final Map map) throws ApplicationException {
        //事前条件処理実行
        validate(map);
        //ユーザータイプコード
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        String companyCd = (String)map.get(PM_COMPANY_CD);
        boolean limitFlg = getBirdUserInfo().isLimit();
        List list = new ArrayList();
        int index =0;
        // 対象条件データ取得
        if(userTypeCd.equals(BizReportGroupDto.USERTYPE_HONBU)) {
            executeListHonbu(list, index, companyCd, limitFlg);
        } else  if(userTypeCd.equals(BizReportGroupDto.USERTYPE_ONER)) {
            executeListOner(list, index);
        } else if(userTypeCd.equals(BizReportGroupDto.USERTYPE_MISE)) {        
            executeListMise(list, index);
        }
        return list;
    }
    /**
     * 対象条件の種類を取得
     * 
     * @param userTypeCd
     * @param companyCd
     * @return List　データリスト
     * @throws ApplicationException
     */
    private void executeListHonbu(List list, int index, String companyCd, boolean limitFlg) throws ApplicationException {
        if (!limitFlg) {
            list.add(index++, createEntity(CODE_ALL, "全社"));
        }
        if(companyCd.equals(BizReportCommonLogic.COMPANY_CD_MOS)) {
            if (!limitFlg) {
                list.add(index++, createEntity(CODE_SEGMENT, "セグメント"));
                list.add(index++, createEntity(CODE_JIGYOU, "事業本部"));
            }
            list.add(index++, createEntity(CODE_SLAREA, "営業エリア"));
        }
        list.add(index++, createEntity(CODE_SIBU, "支部"));
        list.add(index++, createEntity(CODE_MISE, "個店"));
        if (list == null || list.size() == 0) {
            throw new NotExistException("対象条件情報");
        }
    }
    /**
     * 対象条件の種類を取得
     * 
     * @return List 対象条件データ
     * @exception ApplicationException
     */
    private void executeListOner(List list, int index) throws ApplicationException {
        list.add(index++, createEntity(CODE_ALL, "全店"));
        executeListMise(list, index);
    }
    /**
     * 対象条件の種類を取得
     * 
     * @return List 対象条件データ
     * @exception ApplicationException
     */
    private void executeListMise(List list, int index) throws ApplicationException {
       list.add(index++, createEntity(CODE_MISE, "店舗"));
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private CodTaishoJoken createEntity(String code, String name) {
        CodTaishoJoken entity = new CodTaishoJoken();
        entity.setCode(code);
        entity.setName(name);
        return entity;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
