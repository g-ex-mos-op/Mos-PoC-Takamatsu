/*
 * 作成日: 2006/02/15
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.logic.impl;

import java.io.File;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.communication.contactreference.entity.UIViewRenraku;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

/**
 * @author xyuchida
 *
 */
public class ContactReferenceDownloadLogicImpl implements DownloadLogic {

    public static final String LOGIC_ID = "BCM001L03";

    private static final String FILENAME_DELIMITER = "_";

    private static final String INFO_SHU_TUTATU= "02";
    private static final String INFO_SHU_DOC= "03";
    private static final String INFO_SHU_FORM= "04";


    public String getFileName(DownloadDto downloadDto) {

        UIViewRenraku uiViewRenraku = (UIViewRenraku) downloadDto;
        // 対象ファイル名取得
        String fileName =  "";

        int index = uiViewRenraku.getFileIndex();
        if(index == 2){
            fileName += uiViewRenraku.getAttachFl1();
        }else if(index == 3){
            fileName += uiViewRenraku.getAttachFl2();
        }else if(index == 4){
            fileName += uiViewRenraku.getAttachFl3();
        }else if(index >= 5){
            int indexKanren = index - 5;
            UIDocSearch entityKanren = (UIDocSearch) uiViewRenraku.getListKenrenFile().get(indexKanren);
            fileName += entityKanren.getFileName();
        }
        return fileName;
    }

    public String getFileFullPath(DownloadDto downloadDto) {
        String path = BirdContext.getProperty("fileProperty", "filePathRenraku");

        UIViewRenraku uiViewRenraku = (UIViewRenraku) downloadDto;

        // 対象ファイル名取得
        String fileName =  uiViewRenraku.getRegDate()
        + FILENAME_DELIMITER
        + uiViewRenraku.getSeq()
        + FILENAME_DELIMITER
        + uiViewRenraku.getFileIndex()
        + FILENAME_DELIMITER;

        int index = uiViewRenraku.getFileIndex();
        if(index == 2){
            fileName += uiViewRenraku.getAttachFl1();
        }else if(index == 3){
            fileName += uiViewRenraku.getAttachFl2();
        }else if(index == 4){
            fileName += uiViewRenraku.getAttachFl3();
        }else if(index >= 5){
            int indexKanren = index - 5;
            UIDocSearch entityKanren = (UIDocSearch) uiViewRenraku.getListKenrenFile().get(indexKanren);
            fileName =  entityKanren.getRegDate()
            + FILENAME_DELIMITER
            + entityKanren.getSeq()
            + FILENAME_DELIMITER
            + "1"
            + FILENAME_DELIMITER;
            fileName += entityKanren.getFileName();
            //関連文書のパス取得
            path = getFileRootPath(entityKanren);
        }
        // サーバ上ファイル名を生成し返却
        return path + File.separator + fileName.trim();

    }

    public void validate(DownloadDto downloadDto) throws ApplicationException {
    }

    public String getContentType(DownloadDto downloadDto) {
    	// ファイル名を取得
    	String filename = getFileName(downloadDto);
    	// 拡張子を取得
    	String[] fileType = filename.split(".");
    	if (fileType.length<=1){
    		// 拡張子なしの場合
    		return "application/octet-stream";
    	} else {
    		if ("zip".equals((fileType[fileType.length-1].toLowerCase()))){
        		// 拡張子が「zip」の場合
    			return "application/zip";
    		} else if ("pdf".equals((fileType[fileType.length-1].toLowerCase()))){
        		// 拡張子が「pdf」の場合
    			return "application/pdf";
    		} else if ("csv".equals((fileType[fileType.length-1].toLowerCase()))){
        		// 拡張子が「csv」の場合
    			return "text/plain";
    		} else if ("txt".equals((fileType[fileType.length-1].toLowerCase()))){
        		// 拡張子が「txt」の場合
    			return "text/plain";
    		}  else{
        		// 拡張子が上記以外の場合
    			return "application/octet-stream";
    		}
    	}
    }

    /**
     * 関連文書のパス取得
     * @param entity 関連文書
     * @return パス
     */
    private String getFileRootPath(UIDocSearch entity){
        //通達
        if(INFO_SHU_TUTATU.equals(entity.getInfoShu())){
            return BirdContext.getProperty("fileProperty", "filePathTutatu");

        //文書
        }else if(INFO_SHU_DOC.equals(entity.getInfoShu())){
            return BirdContext.getProperty("fileProperty", "filePathBunsho");

        //フォーム
        }else if(INFO_SHU_FORM.equals(entity.getInfoShu())){
            return BirdContext.getProperty("fileProperty", "filePathForm");

        }
        return "";
    }

}
