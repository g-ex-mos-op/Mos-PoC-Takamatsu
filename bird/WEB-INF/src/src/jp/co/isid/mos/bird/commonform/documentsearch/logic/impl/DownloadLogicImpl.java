
package jp.co.isid.mos.bird.commonform.documentsearch.logic.impl;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchConditionDto;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class DownloadLogicImpl implements DownloadLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCO001L02";

    // ファイルセパレーター
    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
        DocumentSearchConditionDto dto = (DocumentSearchConditionDto) downloadDto;
        UIDocSearch entity = (UIDocSearch) dto.getDocumentList().get(dto.getDownloadIndex());

//      String fileName = entity.getFileName();
        String fileName = entity.getRegDate() + "_" + entity.getSeq() + "_1_";
               fileName += entity.getFileName();


        return fileName;
    }

    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
        String path = "";
        DocumentSearchConditionDto dto = (DocumentSearchConditionDto) downloadDto;
        if(dto.getSelectInfoShu().equals("02")){
            path = BirdContext.getProperty("fileProperty", "filePathTutatu");
        }else if(dto.getSelectInfoShu().equals("03")){
            path = BirdContext.getProperty("fileProperty", "filePathBunsho");
        }else if(dto.getSelectInfoShu().equals("04")){
            path = BirdContext.getProperty("fileProperty", "filePathForm");
        }

        path = path + FILE_SEPARATOR;

        return path + getFileName(downloadDto);
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
}
