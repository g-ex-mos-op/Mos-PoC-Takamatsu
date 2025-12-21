package jp.co.isid.mos.bird.communication.documentdownload.logic.impl;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.communication.documentdownload.dto.DocumentDownloadDto;
import jp.co.isid.mos.bird.communication.documentdownload.entity.UIViewBunshoInfo;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class DocumentDownloadLogicImpl implements DownloadLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BCM003L04";

    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    private static final String INFO_SHU_TUTATU= "02";
    private static final String INFO_SHU_DOC= "03";
    private static final String INFO_SHU_FORM= "04";

    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
        DocumentDownloadDto dto = (DocumentDownloadDto) downloadDto;
        String fileName = "";
        switch (getDownloadMode(downloadDto)) {
            case 1:
                UIViewBunshoInfo entity = dto.getEntityViewDetail();
                // 文書ファイル
                fileName = entity.getFileName();
                break;
            case 2:
                int index = dto.getSelectedAttachFileIndex();
                UIViewBunshoInfo targetEntity = (UIViewBunshoInfo)dto.getListBunsho().get(dto.getDownloadFileIndex());

                if(index == 2){
                    fileName = targetEntity.getAttachFl1();
                    break;
                }else if(index == 3){
                    fileName = targetEntity.getAttachFl2();
                    break;
                }else if(index == 4){
                    fileName = targetEntity.getAttachFl3();
                    break;
                }else if(index >= 5){
                    int indexKanren = index - 5;
                    UIDocSearch entityKanren = (UIDocSearch) targetEntity.getListKenrenFile().get(indexKanren);
                    fileName = entityKanren.getFileName();
                    break;
                }else{
                    break;
                }
            case 3:
                int viewIndex = dto.getViewSelectedAttachFileIndex();
                UIViewBunshoInfo viewTargetEntity = (UIViewBunshoInfo)dto.getListBunsho().get(dto.getSelectIndex());

                if(viewIndex == 2){
                    fileName = viewTargetEntity.getAttachFl1();
                    break;
                }else if(viewIndex == 3){
                    fileName = viewTargetEntity.getAttachFl2();
                    break;
                }else if(viewIndex == 4){
                    fileName = viewTargetEntity.getAttachFl3();
                    break;
                }else if(viewIndex >= 5){
                    int indexKanren = viewIndex - 5;
                    UIDocSearch entityKanren = (UIDocSearch) viewTargetEntity.getListKenrenFile().get(indexKanren);
                    fileName = entityKanren.getFileName();
                    break;
                }else{
                    break;
                }


//            case 3:
//                int indexKanren = dto.getDownloadKanrenIndex();
//
//                UIDocSearch entityKanren = (UIDocSearch) dto.getListKanrenBunsho().get(indexKanren);
//
//                fileName = entityKanren.getFileName();
//                break;
            default:
                break;
        }

        return fileName.trim();
    }

    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
        String path = BirdContext.getProperty("fileProperty", "filePathBunsho");
        String fileName = "";
        DocumentDownloadDto dto = (DocumentDownloadDto) downloadDto;


        switch (getDownloadMode(downloadDto)) {
            case 1:
                 UIViewBunshoInfo entity = dto.getEntityViewDetail();
                // 文書ファイル
                fileName = entity.getRegDate() + "_" + entity.getSeq() + "_1_" + entity.getFileName();
                break;
            case 2:
                int index = dto.getSelectedAttachFileIndex();
                 UIViewBunshoInfo targetEntity = (UIViewBunshoInfo)dto.getListBunsho().get(dto.getDownloadFileIndex());

                fileName = targetEntity.getRegDate() + "_" + targetEntity.getSeq() + "_" + index + "_";

                if(index == 2){
                    fileName += targetEntity.getAttachFl1();
                    break;
                }else if(index == 3){
                    fileName += targetEntity.getAttachFl2();
                    break;
                }else if(index == 4){
                    fileName += targetEntity.getAttachFl3();
                    break;
                }else if(index >= 5){
                    int indexKanren = index - 5;
                    UIDocSearch entityKanren = (UIDocSearch) targetEntity.getListKenrenFile().get(indexKanren);
                    fileName = entityKanren.getRegDate() + "_" + entityKanren.getSeq() + "_1_";
                    fileName += entityKanren.getFileName();
                    //関連文書のパス取得
                    path = getFileRootPath(entityKanren);
                    break;
                }else{
                    break;
                }
            case 3:
                int viewIndex = dto.getViewSelectedAttachFileIndex();
                UIViewBunshoInfo viewTargetEntity = (UIViewBunshoInfo)dto.getListBunsho().get(dto.getSelectIndex());

                fileName = viewTargetEntity.getRegDate() + "_" + viewTargetEntity.getSeq() + "_" + viewIndex + "_";

                if(viewIndex == 2){
                    fileName += viewTargetEntity.getAttachFl1();
                    break;
                }else if(viewIndex == 3){
                    fileName += viewTargetEntity.getAttachFl2();
                    break;
                }else if(viewIndex == 4){
                    fileName += viewTargetEntity.getAttachFl3();
                    break;
                }else if(viewIndex >= 5){
                    int indexKanren = viewIndex - 5;
                    UIDocSearch entityKanren = (UIDocSearch) viewTargetEntity.getListKenrenFile().get(indexKanren);
                    fileName = entityKanren.getRegDate() + "_" + entityKanren.getSeq() + "_1_" + entityKanren.getFileName();
                    //関連文書のパス取得
                    path = getFileRootPath(entityKanren);
                    break;
                }else{
                    break;
                }

//            case 3:
//                int indexKanren = dto.getDownloadKanrenIndex();
//
//                UIDocSearch entityKanren = (UIDocSearch) dto.getListKanrenBunsho().get(indexKanren);
//
//                fileName = entityKanren.getRegDate() + "_" + entityKanren.getSeq() + "_1_";
//
//                fileName += entityKanren.getFileName();
//                break;
            default:
                break;
        }
        return path + FILE_SEPARATOR + fileName.trim();
    }


    /**
     * 事前チェック処理<p>
     * ファイルの存在チェックや権限チェックを行って下さい。<br>
     * ※ 存在チェックは、共通ダウンロードアクション内でも行っています。
     * @param downloadDto
     * @throws ApplicationException
     */
    public void validate(final DownloadDto downloadDto) throws ApplicationException {

    }

    /**
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     */
    public String getContentType(final DownloadDto downloadDto) {
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


    /*
     * ダウンロードするファイルの種類を取得
     * １：文書ファイル
     * ２：添付ファイル
     */
    private int getDownloadMode(DownloadDto downloadDto) {
        DocumentDownloadDto dto = (DocumentDownloadDto) downloadDto;
        return dto.getDownloadMode();
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
