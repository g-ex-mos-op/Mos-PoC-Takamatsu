package jp.co.isid.mos.bird.communication.notificationreference.logic.impl;

import java.io.File;

import jp.co.isid.mos.bird.communication.notificationreference.dto.NotificationReferenceDto;
import jp.co.isid.mos.bird.communication.notificationreference.entity.UIViewTutatu;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

public class NotificationReferenceDownloadLogicImpl implements DownloadLogic {

    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
     * 更新日:2009/06/22 既読リンク文字色変更対応
     */
    public String getFileName(final DownloadDto downloadDto) {
        NotificationReferenceDto dto = (NotificationReferenceDto) downloadDto;
        return dto.getDownloadFileName();
    }

    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     *
     * 更新日:2009/06/22 既読リンク文字色変更対応
     *
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
        String path = BirdContext.getProperty("fileProperty", "filePathTutatu");
        String fileName = "";
        NotificationReferenceDto dto = (NotificationReferenceDto) downloadDto;
        if(dto.getDownloadFileName() != null && dto.getMapFileNameIndex().containsKey(dto.getDownloadFileName()) ) {
            int downloadIndex = Integer.valueOf((String)dto.getMapFileNameIndex().get(dto.getDownloadFileName())).intValue();
            UIViewTutatu entity = (UIViewTutatu) dto.getTutatuList().get(downloadIndex);
            // 通達メインファイル
            fileName = entity.getRegDate() + "_" + entity.getSeq() + "_1_" + entity.getFileName();
            return path + File.separator + fileName;
        }
        throw new GenericErrorException("操作", "ただいまのファイルのダウンロードの操作は無効です。");
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
    	String filename = getFileFullPath(downloadDto);
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
