/**
 * 
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.nationalviewlist.dao.CodSvDao;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.CheckSvExistLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuVerifier;

/**
 * 【SVコード存在チェック】ロジック
 * 
 * @author xkinu
 *
 */
public class CheckSvExistLogicImpl implements CheckSvExistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = NationalViewListUtil.SCREEN_ID+"L04";
    /** 
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD = "companyCd";
    /* パラメーターキー：SVコード */
    public static final String PK_SV_CD = "svCd";
    //DAO【SVリスト】
    private CodSvDao nationalViewListCodSvDao;
    /** 
     * 実行処理
     * 
     * @param params
     * @return 前ゼロ付加のSVコード
     * @see jp.co.isid.mos.bird.entry.nationalviewlist.logic.CheckSvExistLogic#execute(java.util.Map)
     */
    public String execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        //SVコード
        CodeFormatter cdf = new CodeFormatter(8, "00000000");
        cdf.setFormatPattern("00000000");
        String svCd = (String)params.get(PK_SV_CD);
        //２．パラメータ.SVコードを前ゼロ付加する。
        svCd = cdf.format(svCd, true);
        //３．Dao【SVリスト】の検索を実行する。
        List list = getNationalViewListCodSvDao().select(companyCd, svCd);
        if(list == null || list.size() < 1){
            //MSG【E0103】’が存在しません。’
            throw new NotExistException("該当SVコード", "targetSvCd",0);
        } 
        //４．パラメータ.SVコードをリターンする。
        return svCd;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //会社コード
        String companyCd = (String)params.get(PK_COMPANY_CD);
        if (ProjectPlanStatusInfoUtil.isNull(companyCd)) {
            throw new MissingDataException("会社コード");
        }
        //SVコード必須チェック
        String svCd = (String)params.get(PK_SV_CD);
        if(NationalViewListUtil.isNull(svCd)){
            throw new NotNullException("SVコード", "targetSvCd", 0);
        }
        //SVコード妥当性チェック
        CodeFormatter cdf = new CodeFormatter(8, "00000000");
        cdf.setFormatPattern("00000000");
        if(NationalViewListUtil.isNull(svCd)){
            throw new NoInputException("SVコード", "targetSvCd", 0);
        }
        HankakuVerifier hankakuVerifier = new HankakuVerifier();
        if(!hankakuVerifier.validate(svCd) || svCd.length() > 8){
            throw new GenericMessageException("SVコードは半角英数字8桁以内で入力してください。", "svCd", 0);               
        }
    }
    /**
     * @return nationalViewListCodSvDao を戻します。
     */
    public CodSvDao getNationalViewListCodSvDao() {
        return nationalViewListCodSvDao;
    }
    /**
     * @param nationalViewListCodSvDao 設定する nationalViewListCodSvDao。
     */
    public void setNationalViewListCodSvDao(CodSvDao nationalViewListCodSvDao) {
        this.nationalViewListCodSvDao = nationalViewListCodSvDao;
    }

}
