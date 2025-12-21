package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampMise;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * 対象店舗CSVダウンロードAction
 *
 * @author   xkhata
 */
public class CampMasterRegistCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L07";


    /**
     * CSVファイル名称を取得する
     * @param   csvOutputDto    CSV出力DTO
     * @return  String          CSVファイル名称
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        CampaignMasterRegistDto dto = (CampaignMasterRegistDto) csvOutputDto;
        String filename = "CAMPMISE.csv";
        if (CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_ERROR == dto.getUploadStatus()) {
            filename = dto.getUploadFileName().substring(0, dto.getUploadFileName().lastIndexOf(".")) + "_ERROR.csv";
        }
        return filename;
    }

    /**
     * 入力チェックをする
     * @param csvOutputDto  CSV出力DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
        // 処理なし
    }

    /**
     * CSV出力データを作成する
     * @param   csvOutputDto    CSV出力DTO
     * @return  List            CSV出力データリスト
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        CampaignMasterRegistDto dto = (CampaignMasterRegistDto) csvOutputDto;
        List listCsv = new ArrayList();
        
        for (Iterator ite = dto.getUploadFileData().iterator(); ite.hasNext();) {
            MstCampMise mstCampMise = (MstCampMise) ite.next();
            List listRowData = new ArrayList();
            listRowData.add(mstCampMise.getMiseCd());
            if (mstCampMise.getErrInfo() == null) {
                listRowData.add("");
            }
            else {
                listRowData.add(mstCampMise.getErrInfo());
            }
            
            listCsv.add(listRowData);
        }
        
        return listCsv;
    }
}