package jp.co.isid.mos.bird.office.formregist.logic.impl;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;

public class DownloadFormTenpuLogicImpl implements DownloadLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L12";

    public static final int MODE_ATTACH_FILE1 = 1;
    public static final int MODE_ATTACH_FILE2 = 2;
    public static final int MODE_ATTACH_FILE3 = 3;

    // ファイルセパレーター
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    // フォーム用ディレクトリ名
//    private String DIR_NAME = "form";
    private static final String KEY_FILE= "filePathForm";


    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
        FormConditionDto dto = (FormConditionDto) downloadDto;
        UIFormInfo entity = (UIFormInfo) dto.getListForm().get(dto.getDownloadIndex());

        String fileName = "";

        switch (entity.getSelectedAttachFileIndex()) {
            case MODE_ATTACH_FILE1:
                fileName =  entity.getAttachFl1();
                break;
            case MODE_ATTACH_FILE2:
                fileName = entity.getAttachFl2();
                break;
            case MODE_ATTACH_FILE3:
                fileName =  entity.getAttachFl3();
                break;
            default:
                break;
        }

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
        String path = BirdContext.getProperty("fileProperty", KEY_FILE);
//        path = path + FILE_SEPARATOR + DIR_NAME + FILE_SEPARATOR;

        FormConditionDto dto = (FormConditionDto) downloadDto;
        UIFormInfo entity = (UIFormInfo) dto.getListForm().get(dto.getDownloadIndex());


        String fileName = entity.getRegDate() + "_" + entity.getSeq() + "_";
        switch (entity.getSelectedAttachFileIndex()) {
            case MODE_ATTACH_FILE1:
                fileName += "2_" + getFileName(downloadDto);
                break;
            case MODE_ATTACH_FILE2:
                fileName += "3_" + getFileName(downloadDto);
                break;
            case MODE_ATTACH_FILE3:
                fileName += "4_" + getFileName(downloadDto);
                break;
            default:
                break;
        }

        return path + FILE_SEPARATOR  +fileName;
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
    	String[] fileType = filename.split("\\.");
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
    			return "text/comma-separated-values";
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
