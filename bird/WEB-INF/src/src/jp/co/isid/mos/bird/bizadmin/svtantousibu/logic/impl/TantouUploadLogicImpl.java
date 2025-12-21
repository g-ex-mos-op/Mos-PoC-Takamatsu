/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dao.UIMstMiseDao;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.MstTantouMise;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.entity.UIMstMise;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.TantouUploadLogic;
import jp.co.isid.mos.bird.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.dao.MstUserDao;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * SV担当店アップロードロジック
 * @author xnkusama
 *
 * 2008/12/03 修正 SV担当店登録エンハンス対応(販社SVも登録可能にする) xayumi  
 *
 */
public class TantouUploadLogicImpl implements TantouUploadLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA004L03";
    
    /* DAO */
    private MstUserDao svTantouSibuMstUserDao;
    private UIMstMiseDao svTantouSibuUIMstMiseDao;
    
    /** ユーザ所属会社DAO 2008/12/03 追加     */
    private CodCompanyDao codCompanyDao;
    
    /**
     * SV担当店アップロード処理
     * @param SvTantouSibuDto 画面DTO
     * @return　boolean true:エラーあり
     */
    public boolean execute(SvTantouSibuDto dto) {
        
        //アップロードファイルチェック
        if (dto.getUploadedFile() == null
                || !dto.getUploadedFile().getName().toLowerCase().endsWith(".csv")) 
        {
            throw new NotSelectedException("CSVファイル", "uploadedFile", 0);
        }
        if (dto.getUploadedFile().getSize() == 0) {
            throw new InvalidInputException("ファイル形式", "詳しくはヘルプをご確認下さい。");
        }
        /** CSVファイル読み込み */
        String[][] csvArray = null;
        try {
            csvArray = CsvInputUtil.loadCSV(
                    dto.getUploadedFile().getInputStream(),
                    CsvInputUtil.OPTION_DEFAULT);
        } catch (FileNotFoundException e) {
            throw new NotSelectedException("CSVファイル", "");            
        } catch (IOException e) {
            throw new FtlSystemException("CSVファイル", "", e);
        }
        
        /** フォーマットチェック（項目数） **/
        checkCsvFormat(csvArray);
        
        /** 各項目のチェック ＋ エラーCSVデータと登録用Entityの作成*/
        checkCsvValue(csvArray, dto);
        
        return false;
    }

    /**
     * CSVフォーマットチェック
     * @param csvArray
     */
    private void checkCsvFormat(String[][] csvArray) {
        // 選択ファイルが１行の場合、エラー
        //１行目は項目ヘッダである前提なので読み込まないので、csvArrayは空になっている
        if (csvArray.length == 0) {
            throw new InvalidInputException("ファイル形式", "詳しくはヘルプをご確認下さい。");
        }
        
        // 1行目の列数を確認
        if (csvArray[0].length != SvTantouSibuCommon.CSV_UPLOAD_COL_NUM) {
            throw new InvalidInputException("ファイル形式", "詳しくはヘルプをご確認下さい。");
        }

//--- このチェックをすると、必須チェックができなくなるので削除
//        // 各行の列数を確認（Nullの項目がある場合、エラー）
//        for (int i = 0; i < csvArray.length; i++) {
//            for (int j = 0; j < SvTantouSibuCommon.CSV_UPLOAD_COL_NUM; j++) {
//                if (csvArray[i][j] == null) {
//                    throw new InvalidInputException("ファイル形式", "詳しくはヘルプをご確認下さい。");
//                }
//            }
//        }
    }
    
    /**
     * CSV各項目の妥当性チェック ＋ エラーCSVと登録用Entityも同時に作成
     * 　・店コードの存在チェック
     * 　・SVコードの存在チェック
     * 
     * @param csvArray
     * @param dto
     */
    private void checkCsvValue(String[][] csvArray, SvTantouSibuDto dto) {
        boolean isError = false;
        List listErrCsv = new ArrayList();
        List listTantouMise = new ArrayList();
        //フォーマッター
        CodeFormatter miseCdFormatter = new CodeFormatter(5);
        miseCdFormatter.setFormatPattern("00000");
        CodeFormatter svCdFormatter = new CodeFormatter(8);
        svCdFormatter.setFormatPattern("00000000");
        
        //ヘッダ
        listErrCsv.add(makeHeader());
        //1行ずつ店コード、SVコードの値をチェック
        for (int i = 0; i < csvArray.length; i++) {
            String errMsg = "";
            List listRowData = new ArrayList();
            MstTantouMise mstTantouMise = new MstTantouMise();
            mstTantouMise.setCompanyCd(dto.getCompanyCd());
            for (int j = 0; j < SvTantouSibuCommon.CSV_UPLOAD_COL_NUM; j++) {
                listRowData.add(csvArray[i][j] == null ? "" : csvArray[i][j]);
                if (j == SvTantouSibuCommon.CSV_UPLOAD_COL_MISE_CD) {
                    String miseCd = csvArray[i][j];
                    //必須チェック
                    if (isNull(miseCd)) {
                        errMsg = "店コードが入力されていません。";
                        continue;
                    }
                    //レングスチェック
                    else if (miseCd.getBytes().length > 5) {
                        errMsg = "店コードが存在しません。";
                        continue;
                    }
                    //店コードに前ゼロ付加
                    miseCd = miseCdFormatter.format(miseCd, true);
                    //店コード存在チェック
                    UIMstMise mstMise = getSvTantouSibuUIMstMiseDao().getMiseInfo(mstTantouMise.getCompanyCd(), miseCd);
                    if (mstMise == null) {
                        errMsg = "店コードが存在しません。";
                        continue;
                    }
                    //店コード重複チェック
                    for (int k = 0; k < csvArray.length; k++) {
                        if (i != k) {
                            //チェック対象の店コードに前ゼロ付加
                            String targetMiseCd = csvArray[k][SvTantouSibuCommon.CSV_UPLOAD_COL_MISE_CD];
                            if (isNull(targetMiseCd)) {
                                continue;
                            }
                            targetMiseCd = miseCdFormatter.format(targetMiseCd, true);
                            if (miseCd.equals(targetMiseCd)) {
                                errMsg = "店コードが重複しています。";
                                break;
                            }
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                        mstTantouMise.setMiseCd(miseCd);
                        mstTantouMise.setMiseNameKj(mstMise.getMiseNameKj());
                        mstTantouMise.setSibuCd(mstMise.getSibuCd());
                        mstTantouMise.setSibuName(mstMise.getSibuName());
                        mstTantouMise.setBlockCd(mstMise.getBlockCd());
                        mstTantouMise.setBlockName(mstMise.getBlockName());
                        mstTantouMise.setCloseDt(mstMise.getCloseDt());
                    }
                }
                else if (j == SvTantouSibuCommon.CSV_UPLOAD_COL_SV_CD) {
                    String svCd = csvArray[i][j];
                    //必須チェック
                    if (isNull(svCd)) {
                        errMsg += "担当SVコードが入力されていません。";
                        continue;
                    }
                    //レングスチェック
                    if (svCd.getBytes().length > 8) {
                        errMsg += "担当SVのユーザIDが存在しません。";
                        continue;
                    }
                    //SVに前ゼロ付加
                    svCd = svCdFormatter.format(svCd, true);
                    //SVコード存在チェック
                    List listUser = getSvTantouSibuMstUserDao().getMstUser(svCd);
                    if (listUser == null || listUser.isEmpty()) {
                        errMsg += "担当SVのユーザIDが存在しません。";
                        continue;
                    }
//** 2008/12/03 追加 Start ------------------------------------------------------                    
                    // SVコードがモスを閲覧できない場合はエラー
                    if (!chkCompany(svCd)) {
                        errMsg += "担当SVに指定されたユーザーにはモス店舗データの閲覧権限がありません。";
                        continue;
                    }
//** 2008/12/03 追加 End --------------------------------------------------------  
                    
                    MstUser mstUser = (MstUser) listUser.get(0);
//** 2008/12/03 削除
//                    // SVの所属会社がモス以外の場合はエラー
//                    if (isNull(mstUser.getRCompanyCd()) || !SvTantouSibuCommon.COMPANY_CD_MOS.equals(mstUser.getRCompanyCd())) {
//                        errMsg += "担当SVの所属会社はモスフードサービスでなければなりません。";
//                        continue;
//                    }
                    
                    //正常の場合
                    mstTantouMise.setSvCd(svCd);
                    mstTantouMise.setUserNameKj(mstUser.getUser_name());
                }
            }
            //エラー判定
            if (!"".equals(errMsg)) {
                isError = true;
            }
            //エラー情報セット
            listRowData.add(errMsg);
            //1行分のデータをリストにセット
            listErrCsv.add(listRowData);
            listTantouMise.add(mstTantouMise);
        }
        
        //エラーがあった場合
        if (isError) {
            listTantouMise = null;
            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
            dto.setListUploadData(null);
        }
        else {
            dto.setListUploadErrorData(null);
            dto.setUploadError(false);
            //登録データをソート
            Collections.sort(listTantouMise, new SortComparator());
            dto.setListUploadData(listTantouMise);
        }
        
    }
    
    /**
     * 2008/12/03 追加
     * 
     * SVコードの所属会社に、モス('00')が存在しているかチェック。
     * ※対象SVがモスを閲覧できるかどうか確認。
     * 
     * @return isExist（true＝モス('00')が存在している／false＝存在していない）
     * 
     */
    private boolean chkCompany(String userId) {
        boolean isExist = false;

        // ユーザ所属会社リスト取得
        List companyList = getCodCompanyDao().select(userId);
        
        if (companyList!=null){
            for (Iterator ite = companyList.iterator(); ite.hasNext();) {
                CodCompany listCompany = (CodCompany) ite.next();
                if (SvTantouSibuCommon.COMPANY_CD_MOS.equals(listCompany.getCompanyCd())) {
                    isExist = true;
                    break;                
                }
                ite.remove();
                continue;
            }            
        }
        return isExist;
    }   
    
    /**
     * エラーCSVヘッダ
     * @return
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
        listHeader.add("エラー内容");
        return listHeader;
    }
    
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            MstTantouMise entity1 = (MstTantouMise) obj1;
            MstTantouMise entity2 = (MstTantouMise) obj2;
            
            String val1 = entity1.getSibuCd() + entity1.getBlockCd() + entity1.getMiseCd();
            
            String val2 = entity2.getSibuCd() + entity2.getBlockCd() + entity2.getMiseCd();
            
            return val1.compareTo(val2);
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

    public MstUserDao getSvTantouSibuMstUserDao() {
        return svTantouSibuMstUserDao;
    }

    public void setSvTantouSibuMstUserDao(MstUserDao svTantouSibuMstUserDao) {
        this.svTantouSibuMstUserDao = svTantouSibuMstUserDao;
    }

    public UIMstMiseDao getSvTantouSibuUIMstMiseDao() {
        return svTantouSibuUIMstMiseDao;
    }

    public void setSvTantouSibuUIMstMiseDao(UIMstMiseDao svTantouSibuUIMstMiseDao) {
        this.svTantouSibuUIMstMiseDao = svTantouSibuUIMstMiseDao;
    }

//** 2008/12/03 追加 Start ------------------------------------------------------
    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }
//** 2008/12/03 追加 End --------------------------------------------------------
}
