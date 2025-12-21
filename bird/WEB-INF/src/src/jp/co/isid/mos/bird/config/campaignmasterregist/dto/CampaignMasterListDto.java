package jp.co.isid.mos.bird.config.campaignmasterregist.dto;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * キャンペーンマスタ登録DTO
 * @author xnkusama
 *
 */
public class CampaignMasterListDto {

    /**
     * キャンペーン一覧の選択行番号
     */
    private int listSelectedIndex;

    /**
     * アップロードファイル
     */
    private UploadedFile kotenUploadFile;
    
    public int getListSelectedIndex() {
        return listSelectedIndex;
    }

    public void setListSelectedIndex(int listSelectedIndex) {
        this.listSelectedIndex = listSelectedIndex;
    }

    public UploadedFile getKotenUploadFile() {
        return kotenUploadFile;
    }

    public void setKotenUploadFile(UploadedFile kotenUploadFile) {
        this.kotenUploadFile = kotenUploadFile;
    }
}