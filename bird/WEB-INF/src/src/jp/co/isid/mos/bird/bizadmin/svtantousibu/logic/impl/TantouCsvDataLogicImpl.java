/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.dao.MstTantouMiseDao;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouMise;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * SV担当店ダウンロードロジック
 * @author xnkusama
 *
 */
public class TantouCsvDataLogicImpl implements CsvOutputLogic  {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA004L02";
    
    private MstTantouMiseDao mstTantouMiseDao;
    
    /**
     * リモート閲覧支部の取得
     * @param 会社コード
     * @param 支部コード
     * @param 未設定フラグ
     * @param システム日付
     * @return　List
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        SvTantouSibuDto dto = (SvTantouSibuDto) csvOutputDto;
        String companyCd = dto.getCompanyCd();
        String sibuCd = dto.getSibuCd();
        boolean flgMisettei = dto.getFlgMisettei();
        String sysDate = dto.getSysDate();
        // CSVデータ用List
        List listCsv = new ArrayList();
        //DAO【SV担当店舗情報Dao．登録情報の取得】
        List listTantouMise = getMstTantouMiseDao().getTantouMise(companyCd, sibuCd, sysDate);
        
        //パラメータ．未設定フラグ＝"1"の場合、SV設定済み店舗を削除
        if (flgMisettei) {
            for (Iterator ite = listTantouMise.iterator(); ite.hasNext();) {
                MstTantouMise mstTantouMise = (MstTantouMise) ite.next();
                if (!isNull(mstTantouMise.getSvCd())) {
                    ite.remove();
                }
            }
        }
        
        // データ存在確認
        if (listTantouMise.isEmpty()) {
            throw new NoResultException();
        }
        //CSV用List作成
        listCsv.add(makeHeader());
        for (Iterator ite = listTantouMise.iterator(); ite.hasNext();) {
            listCsv.add(makeData((MstTantouMise) ite.next()));
        }
        
        return listCsv;
    }

    /* ファイル名取得 */
    public String getFileName(final CsvOutputDto csvOutputDto) {
        return "SVTANTOTEN.csv";
    }

    /* 事前条件チェック処理 */
    public void validate(final CsvOutputDto csvOutputDto) {
        
    }

    /**
     * ヘッダ行リスト作成
     */
    private List makeHeader() {
        List listHeader = new ArrayList();
        listHeader.add("支部コード");
        listHeader.add("支部名称");
        listHeader.add("ブロックコード");
        listHeader.add("ブロック名称");
        listHeader.add("店コード");
        listHeader.add("店名称");
        listHeader.add("担当SVコード");
        listHeader.add("担当SV名称");
        return listHeader;
    }
    
    /**
     * データ行リスト作成
     * @param MstTantouMise
     */
    private List makeData(MstTantouMise entity) {
        List listData = new ArrayList();
        listData.add(entity.getSibuCd());
        listData.add(entity.getSibuName());
        listData.add(entity.getBlockCd());
        listData.add(entity.getBlockName());
        listData.add(entity.getMiseCd());
        listData.add(entity.getMiseNameKj());
        listData.add(entity.getSvCd());
        listData.add(entity.getUserNameKj());
        return listData;
    }
    
    /**
     * Null、ブランクチェック
     * @param value
     * @return true:Nullまたはブランク
     */
    private boolean isNull(String value) {
        return (value == null || "".equals(value.trim())) ? true : false;
    }
    
    public MstTantouMiseDao getMstTantouMiseDao() {
        return mstTantouMiseDao;
    }

    public void setMstTantouMiseDao(MstTantouMiseDao mstTantouMiseDao) {
        this.mstTantouMiseDao = mstTantouMiseDao;
    }

}
