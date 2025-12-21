package jp.co.isid.mos.bird.communication.notificationreference.logic.impl;

import java.io.File;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.communication.notificationreference.dto.NotificationReferenceDto;
import jp.co.isid.mos.bird.communication.notificationreference.entity.UIViewTutatu;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class NotificationReferenceDownloadTenpuLogicImpl implements DownloadLogic {


    public static final String MODE_ATTACH_FILE1 = "1";
    public static final String MODE_ATTACH_FILE2 = "2";
    public static final String MODE_ATTACH_FILE3 = "3";
    public static final String MODE_KANREN_FILE = "4-";

    // 情報種別：通達
    private static final String INFO_SHU_TUTAU = "02";
    // 情報種別：文書
    private static final String INFO_SHU_BUNSHO = "03";
    // 情報種別：フォーム
    private static final String INFO_SHU_FORM = "04";


    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
        NotificationReferenceDto dto = (NotificationReferenceDto) downloadDto;
        UIViewTutatu entity = (UIViewTutatu) dto.getTutatuList().get(dto.getDownloadIndex());

        String fileName = "";
        String indexStr = dto.getSelectedAttachFileIndex();

        if(indexStr.equals(MODE_ATTACH_FILE1)){
            fileName = entity.getAttachFl1();
        }else if(indexStr.equals(MODE_ATTACH_FILE2)){
            fileName = entity.getAttachFl2();
        }else if(indexStr.equals(MODE_ATTACH_FILE3)){
            fileName = entity.getAttachFl3();
        }else{
            if(indexStr.startsWith(MODE_KANREN_FILE)){
//                int no = indexStr.lastIndexOf(MODE_KANREN_FILE) + 1;
                int no = indexStr.indexOf(MODE_KANREN_FILE) + 2;
                String num = indexStr.substring(no);
                int listNo = Integer.valueOf(num).intValue();

                UIDocSearch flData = (UIDocSearch)entity.getKanrenFileList().get(listNo);
                String name = flData.getFileName();
                if(name != null){
                    name = name.trim();
                }
                fileName =  name;
            }
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
        NotificationReferenceDto dto = (NotificationReferenceDto) downloadDto;
        UIViewTutatu entity = (UIViewTutatu) dto.getTutatuList().get(dto.getDownloadIndex());

        String indexStr = dto.getSelectedAttachFileIndex();
        String path = BirdContext.getProperty("fileProperty", "filePathTutatu");

        if(indexStr.startsWith(MODE_KANREN_FILE)){
            int no = indexStr.indexOf(MODE_KANREN_FILE) + 2;
            String num = indexStr.substring(no);
            int listNo = Integer.valueOf(num).intValue();
            UIDocSearch flData = (UIDocSearch)entity.getKanrenFileList().get(listNo);
            String infoShu = flData.getInfoShu();

            if (INFO_SHU_BUNSHO.equals(infoShu)) {
                path = BirdContext.getProperty("fileProperty", "filePathBunsho");
            }
            else if (INFO_SHU_FORM.equals(infoShu)) {
                path = BirdContext.getProperty("fileProperty", "filePathForm");
            }
            else if (INFO_SHU_TUTAU.equals(infoShu)) {
                path = BirdContext.getProperty("fileProperty", "filePathTutatu");
            }
        }else{
            //添付ファイルダウンロード時は初期設定の通達Pathを使用
        }

        return path + File.separator + getSavedFileName(downloadDto);
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

    private String getSavedFileName(final DownloadDto downloadDto) {
        NotificationReferenceDto dto = (NotificationReferenceDto) downloadDto;
        UIViewTutatu entity = (UIViewTutatu) dto.getTutatuList().get(dto.getDownloadIndex());

        String fileName = entity.getRegDate() + "_" + entity.getSeq() + "_";
        String indexStr = dto.getSelectedAttachFileIndex();

        if(indexStr.equals(MODE_ATTACH_FILE1)){
            fileName += "2_" + entity.getAttachFl1();
        }else if(indexStr.equals(MODE_ATTACH_FILE2)){
            fileName += "3_" + entity.getAttachFl2();
        }else if(indexStr.equals(MODE_ATTACH_FILE3)){
            fileName += "4_" + entity.getAttachFl3();
        }else{
            if(indexStr.startsWith(MODE_KANREN_FILE)){
//                int no = indexStr.lastIndexOf(MODE_KANREN_FILE) + 1;
                int no = indexStr.indexOf(MODE_KANREN_FILE) + 2;
                String num = indexStr.substring(no);
                int listNo = Integer.valueOf(num).intValue();

                UIDocSearch flData = (UIDocSearch)entity.getKanrenFileList().get(listNo);
                String name = flData.getFileName();
                if(name != null){
                    name = name.trim();
                }
                fileName = flData.getRegDate() + "_" + flData.getSeq() + "_1_" + name;
            }
        }

        return fileName;
    }

}
