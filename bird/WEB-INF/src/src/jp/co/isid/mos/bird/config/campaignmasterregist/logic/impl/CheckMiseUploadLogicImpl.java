package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.MstMiseDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterListDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampMise;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstMise;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.CheckMiseUploadLogic;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * キャンペーン情報取得
 * @author xnkusama
 *
 */
public class CheckMiseUploadLogicImpl implements CheckMiseUploadLogic {
    
    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L05";
    
    /** DAO */
    private MstMiseDao campmasterregistMstMiseDao;
    
    /** 定数 */
//    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    private static final String FILE_SEPARATOR = "\\";

    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((MstCampMise) obj1).getMiseCd();
            
            String val2 = ((MstCampMise) obj2).getMiseCd();
            
            return val1.compareTo(val2);
        }
    }
    
    /**
     * 入力内容チェック
     * @param dto
     * @return
     */
    public void  execute(CampaignMasterRegistDto dto, CampaignMasterListDto dtoReq) {
        UploadedFile uploadFile = dtoReq.getKotenUploadFile();
        List listUploadData = new ArrayList();
        boolean flgMiseErr = false;
        
        // ファイル指定チェック
        if (uploadFile == null) {
            throw new NotSelectedException("CSVファイル");
        }
        // ファイル拡張子チェック
        if (!uploadFile.getName().toUpperCase().endsWith(".CSV")) {
            throw new NotSelectedException("CSVファイル");
        }
        // ファイルサイズチェック
        if (uploadFile.getSize() == 0) {
            throw new GenericMessageException("ファイルが空または存在しません。");
        }
        
        String[][] csvArray = null;
        try {
            csvArray = CsvInputUtil.loadCSV(
                    uploadFile.getInputStream(),
                    CsvInputUtil.OPTION_CSV_NO_DATA_HEAD_ROW);
        } catch (FileNotFoundException e) {
            throw new GenericMessageException("ファイルが存在しません。", "kotenUploadFile");            
        } catch (IOException e) {
            throw new InvalidInputException("CSVファイル");
        }
        
        if (csvArray.length == 0) {
            throw new GenericMessageException("ファイルが空または存在しません。");
        }
        
        Map mapMise = new HashMap();
        CodeFormatter codeFormatter = new CodeFormatter(5);
        NumericVerifier numericVerifier = new NumericVerifier();
        codeFormatter.setFormatPattern("00000");
        // 店存在チェック
        for (int i = 0; i < csvArray.length; i++) {
        	flgMiseErr = false;
            MstMise entityMise = null;
            MstCampMise mstCampMise = new MstCampMise();
            String inputMiseCd = csvArray[i][0];
            if (inputMiseCd != null) {
                if ("".equals(inputMiseCd.trim()) || !numericVerifier.validate(inputMiseCd)) {
                    flgMiseErr = true;
                    mstCampMise.setMiseCd(inputMiseCd);
                   	mstCampMise.setErrInfo("１列目は半角数値で入力してください。ヘッダーは不要です。");
                }
                else if(inputMiseCd.getBytes().length <= 5){
                    entityMise = getCampmasterregistMstMiseDao().getMiseInfo(codeFormatter.format(inputMiseCd, true));
                    if(entityMise != null) {
                        mstCampMise.setMiseCd(codeFormatter.format(inputMiseCd, true));
                        mstCampMise.setCompanyCd(entityMise.getCompanyCd());
                        mstCampMise.setMiseNameKj(entityMise.getMiseNameKj());
                        int cnt = 0;
                        if (mapMise.get(mstCampMise.getMiseCd()) != null) {
                            cnt = ((Integer) mapMise.get(mstCampMise.getMiseCd())).intValue();                        
                        }
                        cnt = cnt + 1;
                        mapMise.put(mstCampMise.getMiseCd(), new Integer(cnt));
                    }
                }
            }
            if (entityMise == null && flgMiseErr== false) {
                flgMiseErr = true;
                mstCampMise.setMiseCd(inputMiseCd);
                mstCampMise.setErrInfo("店コードが存在しません。");
            }
            listUploadData.add(mstCampMise);
        }
        
        // 重複チェック
        for (Iterator ite = listUploadData.iterator(); ite.hasNext();) {
            MstCampMise entity = (MstCampMise) ite.next();
            if (CommonUtil.isNull(entity.getErrInfo())) {
                if (mapMise.get(entity.getMiseCd()) != null && ((Integer) mapMise.get(entity.getMiseCd())).intValue() > 1) {
                    entity.setErrInfo("店コードが重複しています。");
                    flgMiseErr = true;
                }
            }
            else {
                flgMiseErr = true;
            }
        }
        
        if (!flgMiseErr) {
            Collections.sort(listUploadData, new SortComparator());
        }
        dto.setUploadFileData(listUploadData);
        dto.setUploadFileName(uploadFile.getName().substring(uploadFile.getName().lastIndexOf(FILE_SEPARATOR) + 1));
        dto.setUploadErrorFlg(flgMiseErr);
    }

    public MstMiseDao getCampmasterregistMstMiseDao() {
        return campmasterregistMstMiseDao;
    }

    public void setCampmasterregistMstMiseDao(MstMiseDao campmasterregistMstMiseDao) {
        this.campmasterregistMstMiseDao = campmasterregistMstMiseDao;
    }
}
