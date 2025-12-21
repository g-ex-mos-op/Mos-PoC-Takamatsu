/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dao.MstTantouSibuDao;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouSibu;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.UIAllSibu;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.RemoteSibuLogic;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.framework.dao.MstUserDao;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * リモート閲覧支部の取得ロジック
 * @author xnkusama
 *
 */
public class RemoteSibuLogicImpl implements RemoteSibuLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA004L01";
    
    /* DAO */
    private MstUserDao svTantouSibuMstUserDao; 
    private MstSibuDao mstSibuDao;
    private MstTantouSibuDao mstTantouSibuDao;
    
    /**
     * リモート閲覧支部の取得
     * @param 会社コード
     * @param ユーザーID
     * @return　Map
     * 　Mapのキー
     * 　　支部一覧　　　　：KEY_SIBU_LIST
     * 　　リモート閲覧支部：KEY_REMOTE_SIBU
     */
    public Map execute(SvTantouSibuDto dto) {
        String companyCd = dto.getCompanyCd();
        String userId = dto.getUserId();
        
        // パラメータチェック
        validate(companyCd, userId);

        // ユーザーIDに前ゼロ付加
        CodeFormatter codeFormatter = new CodeFormatter(8);
        codeFormatter.setFormatPattern("00000000");
        userId = codeFormatter.format(userId, true);
        dto.setUserId(userId);
        
        Map mapSibuInfo = new HashMap();
        // ユーザー情報の取得
        List listUser = getSvTantouSibuMstUserDao().getMstUser(userId);
        if (listUser == null || listUser.isEmpty()) {
            throw new NotExistException("ユーザID", "userId", 0);
        }
        
        //店舗が存在する支部の一覧を取得
        List listMstSibu = getMstSibuDao().getSibuHoyuTenpo(companyCd, userId, false);
        if (listMstSibu == null || listMstSibu.isEmpty()) {
            throw new NotExistException("支部情報");
        }
        
        //登録済みのリモート閲覧支部情報を取得
        List listTantoSibu = getMstTantouSibuDao().getTantouSibu(companyCd, userId);
        
        //編集画面用の支部一覧Listを作成
        List listAllSibu = new ArrayList();
        for (Iterator ite = listMstSibu.iterator(); ite.hasNext();) {
            UIAllSibu uiAllSibu = new UIAllSibu();
            MstSibu mstSibu = (MstSibu) ite.next();
            uiAllSibu.setCompanyCd(companyCd);
            uiAllSibu.setSibuCd(mstSibu.getSibuCd());
            uiAllSibu.setSibuName(mstSibu.getSibuName());
            uiAllSibu.setSelCheck(SvTantouSibuCommon.ETURAN_OFF);
            //対象支部がリモート閲覧支部情報に登録されている場合は、
            //[支部一覧]．選択チェック=true、レコード存在フラ=trueにする
            for (Iterator ite2 = listTantoSibu.iterator(); ite2.hasNext();) {
                MstTantouSibu mstTantouSibu = (MstTantouSibu) ite2.next();
                if (mstSibu.getSibuCd().equals(mstTantouSibu.getSibuCd())) {
                    uiAllSibu.setSelCheck(SvTantouSibuCommon.ETURAN_ON);
                    uiAllSibu.setExistRecord(SvTantouSibuCommon.ETURAN_ON);
                    break;
                }
            }
            listAllSibu.add(uiAllSibu);
        }
        
        //取得した情報をリターン用Mapに格納
        mapSibuInfo.put(RETURN_MAP_KEY_SIBULIST, listAllSibu);
        mapSibuInfo.put(RETURN_MAP_KEY_REMOTESIBU, listTantoSibu);
        mapSibuInfo.put(RETURN_MAP_KEY_USER, listUser.get(0));
        
        return mapSibuInfo;
    }

    /**
     * パラメータチェック
     * @return
     */
    private void validate(String companyCd, String userId) {
        //会社コード必須チェック
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        //ユーザーID必須チェック
        if (isNull(userId)) {
            throw new NoInputException("ユーザID", "userId", 0);
        }
        //ユーザーID型チェック
        CodeVerifier codeVerifier = new CodeVerifier(8, true);
        if (!codeVerifier.validate(userId)) {
            throw new NotExistException("ユーザID", "userId", 0);
        }
    }

    /**
     * Null、ブランクチェック
     * @param value
     * @return true:Nullまたはブランク
     */
    private boolean isNull(String value) {
        return (value == null || "".equals(value.trim())) ? true : false;
    }
    
    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }


    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }


    public MstTantouSibuDao getMstTantouSibuDao() {
        return mstTantouSibuDao;
    }


    public void setMstTantouSibuDao(MstTantouSibuDao mstTantouSibuDao) {
        this.mstTantouSibuDao = mstTantouSibuDao;
    }


    public MstUserDao getSvTantouSibuMstUserDao() {
        return svTantouSibuMstUserDao;
    }

    public void setSvTantouSibuMstUserDao(MstUserDao svTantouSibuMstUserDao) {
        this.svTantouSibuMstUserDao = svTantouSibuMstUserDao;
    }

}
